// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.lang.refactoring.move;

import com.jetbrains.cidr.lang.symbols.OCSymbolHolderBase;
import com.jetbrains.cidr.lang.symbols.objc.OCPropertySymbol;
import com.intellij.psi.PsiElementVisitor;
import com.intellij.openapi.util.Comparing;
import com.jetbrains.cidr.lang.symbols.cpp.OCStructSymbol;
import com.jetbrains.cidr.lang.symbols.cpp.OCFunctionSymbol;
import com.intellij.util.Processor;
import com.jetbrains.cidr.lang.symbols.objc.OCMemberSymbol;
import com.intellij.util.CommonProcessors;
import com.jetbrains.cidr.lang.symbols.objc.OCClassSymbol;
import com.jetbrains.cidr.lang.symbols.OCSymbolWithParent;
import com.intellij.psi.PsiReference;
import com.jetbrains.cidr.lang.psi.OCReference;
import com.jetbrains.cidr.lang.psi.OCExternalReference;
import com.jetbrains.cidr.lang.psi.OCLiteralExpression;
import java.util.Iterator;
import com.jetbrains.cidr.lang.psi.OCQualifiedExpression;
import com.jetbrains.cidr.lang.psi.OCReferenceElement;
import com.jetbrains.cidr.lang.psi.OCExpression;
import com.jetbrains.cidr.lang.symbols.objc.OCMethodSymbol;
import com.jetbrains.cidr.lang.psi.OCReferenceExpression;
import com.jetbrains.cidr.lang.psi.OCSendMessageExpression;
import com.jetbrains.cidr.lang.psi.visitors.OCRecursiveVisitor;
import com.jetbrains.cidr.lang.symbols.objc.OCInstanceVariableSymbol;
import com.jetbrains.cidr.lang.psi.OCDeclarator;
import com.jetbrains.cidr.lang.psi.OCFile;
import com.jetbrains.cidr.lang.psi.OCSymbolDeclarator;
import java.util.HashSet;
import java.util.Set;
import com.jetbrains.cidr.lang.symbols.OCSymbol;
import com.intellij.openapi.vfs.VirtualFile;
import com.intellij.psi.PsiElement;
import com.jetbrains.cidr.lang.symbols.OCSymbolHolderVirtualPsiElement;
import com.intellij.refactoring.classMembers.DependentMembersCollectorBase;

public class OCDependentMembersCollector extends DependentMembersCollectorBase<OCSymbolHolderVirtualPsiElement, PsiElement>
{
    private VirtualFile myTopLevelFile;
    private OCSymbol myClassSymbol;
    private OCSymbol myTargetClassSymbol;
    private Set<OCSymbol> myDependenciesFromSameFile;
    
    public OCDependentMembersCollector(final PsiElement psiElement, final PsiElement psiElement2) {
        super((Object)psiElement, (Object)psiElement2);
        this.myDependenciesFromSameFile = new HashSet<OCSymbol>();
        if (psiElement instanceof OCSymbolDeclarator) {
            this.myClassSymbol = ((OCSymbolDeclarator)psiElement).getSymbol();
            this.myTargetClassSymbol = ((psiElement2 != null) ? ((OCSymbolDeclarator)psiElement2).getSymbol() : null);
        }
        else {
            this.myTopLevelFile = ((OCFile)psiElement).getVirtualFile();
        }
    }
    
    public void collect(final OCSymbolHolderVirtualPsiElement ocSymbolHolderVirtualPsiElement) {
        PsiElement psiElement = ((OCSymbolHolderBase<OCSymbol<PsiElement>>)ocSymbolHolderVirtualPsiElement).getSymbol().locateDefinition();
        if (psiElement == null) {
            return;
        }
        if (psiElement instanceof OCDeclarator) {
            psiElement = psiElement.getParent();
        }
        if (ocSymbolHolderVirtualPsiElement.getSymbol() instanceof OCInstanceVariableSymbol) {
            final OCPropertySymbol associatedProperty = ((OCSymbolHolderBase<OCInstanceVariableSymbol>)ocSymbolHolderVirtualPsiElement).getSymbol().getAssociatedProperty();
            if (associatedProperty != null) {
                this.myCollection.add(new OCSymbolHolderVirtualPsiElement(associatedProperty));
            }
        }
        psiElement.accept((PsiElementVisitor)new OCRecursiveVisitor() {
            @Override
            public void visitSendMessageExpression(final OCSendMessageExpression ocSendMessageExpression) {
                super.visitSendMessageExpression(ocSendMessageExpression);
                final OCMethodSymbol knownResponder = ocSendMessageExpression.getProbableResponders().getKnownResponder();
                final OCExpression receiverExpression = ocSendMessageExpression.getReceiverExpression();
                if ((knownResponder != null && knownResponder.isStatic()) || (receiverExpression instanceof OCReferenceExpression && ((OCReferenceExpression)receiverExpression).getSelfSuperToken() != null)) {
                    this.a(ocSymbolHolderVirtualPsiElement.getSymbol(), knownResponder);
                }
                else {
                    this.b(ocSymbolHolderVirtualPsiElement.getSymbol(), knownResponder);
                }
            }
            
            @Override
            public void visitReferenceElement(final OCReferenceElement ocReferenceElement) {
                super.visitReferenceElement(ocReferenceElement);
                this.a(ocSymbolHolderVirtualPsiElement.getSymbol(), ocReferenceElement.resolveToSymbol());
            }
            
            @Override
            public void visitQualifiedExpression(final OCQualifiedExpression ocQualifiedExpression) {
                super.visitQualifiedExpression(ocQualifiedExpression);
                if (ocQualifiedExpression.getQualifier() instanceof OCReferenceExpression && ((OCReferenceExpression)ocQualifiedExpression.getQualifier()).isSelfSuperOrThis()) {
                    for (final OCSymbol ocSymbol : ocQualifiedExpression.resolveToOverloadsSymbols()) {
                        this.a(ocSymbol, ocSymbol);
                    }
                }
            }
            
            @Override
            public void visitLiteralExpression(final OCLiteralExpression ocLiteralExpression) {
                super.visitLiteralExpression(ocLiteralExpression);
                for (final PsiReference psiReference : ocLiteralExpression.getReferences()) {
                    if (psiReference instanceof OCExternalReference) {
                        this.a(ocSymbolHolderVirtualPsiElement.getSymbol(), ((OCReference)psiReference).resolveToSymbol());
                    }
                }
            }
            
            private void a(final OCSymbol ocSymbol, final OCSymbol ocSymbol2) {
                boolean b = false;
                if (ocSymbol2 != null && OCDependentMembersCollector.this.myTopLevelFile != null) {
                    b = true;
                    OCDependentMembersCollector.this.myCollection.add(new OCSymbolHolderVirtualPsiElement(ocSymbol2));
                    final OCSymbol<PsiElement> definitionSymbol = ocSymbol2.getDefinitionSymbol();
                    if (definitionSymbol != null) {
                        OCDependentMembersCollector.this.myCollection.add(new OCSymbolHolderVirtualPsiElement(definitionSymbol));
                    }
                }
                else if (ocSymbol2 instanceof OCSymbolWithParent) {
                    final OCSymbol parent = ((OCSymbolWithParent<PsiElement, OCSymbol>)ocSymbol2).getParent();
                    final OCSymbol<PsiElement> definitionSymbol2 = ocSymbol2.getDefinitionSymbol();
                    if ((OCDependentMembersCollector.this.myClassSymbol != null && OCDependentMembersCollector.this.myClassSymbol.equals(parent)) || (OCDependentMembersCollector.this.myClassSymbol instanceof OCClassSymbol && ((OCClassSymbol)OCDependentMembersCollector.this.myClassSymbol).isSameCategory(parent))) {
                        b = true;
                        if (ocSymbol2 instanceof OCMethodSymbol && OCDependentMembersCollector.this.myTargetClassSymbol instanceof OCClassSymbol && !((OCClassSymbol)OCDependentMembersCollector.this.myTargetClassSymbol).processMembers(OCDependentMembersCollector.this.myClassSymbol.getName().equals(OCDependentMembersCollector.this.myTargetClassSymbol.getName()) ? ((OCClassSymbol)OCDependentMembersCollector.this.myTargetClassSymbol).getCategoryName() : null, ocSymbol2.getName(), (Class<? extends OCMemberSymbol>)OCMethodSymbol.class, (com.intellij.util.Processor<? super OCMemberSymbol>)new CommonProcessors.FindFirstProcessor(), false)) {
                            b = false;
                        }
                        if (ocSymbol2 instanceof OCFunctionSymbol && OCDependentMembersCollector.this.myTargetClassSymbol instanceof OCStructSymbol && !((OCStructSymbol)OCDependentMembersCollector.this.myTargetClassSymbol).processMembers(ocSymbol2.getName(), (Processor<OCSymbol>)new CommonProcessors.FindFirstProcessor())) {
                            b = false;
                        }
                        if (b) {
                            OCDependentMembersCollector.this.myCollection.add(new OCSymbolHolderVirtualPsiElement((definitionSymbol2 != null) ? definitionSymbol2 : ocSymbol2));
                        }
                    }
                }
                if (!b) {
                    this.b(ocSymbol, ocSymbol2);
                }
            }
            
            private void b(final OCSymbol ocSymbol, final OCSymbol<?> ocSymbol2) {
                if (ocSymbol != null && ocSymbol2 != null && !ocSymbol2.getKind().isLocal() && Comparing.equal((Object)ocSymbol.getContainingFile(), (Object)ocSymbol2.getContainingFile()) && ocSymbol2.processPredeclarations((com.intellij.util.Processor<OCSymbol<?>>)(ocSymbol2 -> Comparing.equal((Object)ocSymbol.getContainingFile(), (Object)ocSymbol2.getContainingFile())))) {
                    OCDependentMembersCollector.this.myDependenciesFromSameFile.add(ocSymbol2);
                }
            }
        });
    }
    
    public Set<OCSymbol> getDependenciesFromSameFile() {
        return this.myDependenciesFromSameFile;
    }
}
