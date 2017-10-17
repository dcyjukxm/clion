// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.lang.refactoring.move;

import com.intellij.openapi.util.Comparing;
import com.jetbrains.cidr.lang.symbols.cpp.OCStructSymbol;
import com.jetbrains.cidr.lang.symbols.cpp.OCFunctionSymbol;
import com.intellij.util.Processor;
import com.jetbrains.cidr.lang.symbols.objc.OCMemberSymbol;
import com.intellij.util.CommonProcessors;
import com.jetbrains.cidr.lang.symbols.objc.OCClassSymbol;
import com.intellij.psi.PsiElement;
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
import com.jetbrains.cidr.lang.symbols.OCSymbol;
import com.jetbrains.cidr.lang.psi.OCReferenceExpression;
import com.jetbrains.cidr.lang.psi.OCSendMessageExpression;
import com.jetbrains.cidr.lang.symbols.OCSymbolHolderVirtualPsiElement;
import com.jetbrains.cidr.lang.psi.visitors.OCRecursiveVisitor;

class OCDependentMembersCollector$1 extends OCRecursiveVisitor {
    final /* synthetic */ OCSymbolHolderVirtualPsiElement val$member;
    
    @Override
    public void visitSendMessageExpression(final OCSendMessageExpression ocSendMessageExpression) {
        super.visitSendMessageExpression(ocSendMessageExpression);
        final OCMethodSymbol knownResponder = ocSendMessageExpression.getProbableResponders().getKnownResponder();
        final OCExpression receiverExpression = ocSendMessageExpression.getReceiverExpression();
        if ((knownResponder != null && knownResponder.isStatic()) || (receiverExpression instanceof OCReferenceExpression && ((OCReferenceExpression)receiverExpression).getSelfSuperToken() != null)) {
            this.a(this.val$member.getSymbol(), knownResponder);
        }
        else {
            this.b(this.val$member.getSymbol(), knownResponder);
        }
    }
    
    @Override
    public void visitReferenceElement(final OCReferenceElement ocReferenceElement) {
        super.visitReferenceElement(ocReferenceElement);
        this.a(this.val$member.getSymbol(), ocReferenceElement.resolveToSymbol());
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
                this.a(this.val$member.getSymbol(), ((OCReference)psiReference).resolveToSymbol());
            }
        }
    }
    
    private void a(final OCSymbol ocSymbol, final OCSymbol ocSymbol2) {
        boolean b = false;
        if (ocSymbol2 != null && OCDependentMembersCollector.access$000(OCDependentMembersCollector.this) != null) {
            b = true;
            OCDependentMembersCollector.access$100(OCDependentMembersCollector.this).add(new OCSymbolHolderVirtualPsiElement(ocSymbol2));
            final OCSymbol<PsiElement> definitionSymbol = ocSymbol2.getDefinitionSymbol();
            if (definitionSymbol != null) {
                OCDependentMembersCollector.access$200(OCDependentMembersCollector.this).add(new OCSymbolHolderVirtualPsiElement(definitionSymbol));
            }
        }
        else if (ocSymbol2 instanceof OCSymbolWithParent) {
            final OCSymbol parent = ((OCSymbolWithParent<PsiElement, OCSymbol>)ocSymbol2).getParent();
            final OCSymbol<PsiElement> definitionSymbol2 = ocSymbol2.getDefinitionSymbol();
            if ((OCDependentMembersCollector.access$300(OCDependentMembersCollector.this) != null && OCDependentMembersCollector.access$300(OCDependentMembersCollector.this).equals(parent)) || (OCDependentMembersCollector.access$300(OCDependentMembersCollector.this) instanceof OCClassSymbol && ((OCClassSymbol)OCDependentMembersCollector.access$300(OCDependentMembersCollector.this)).isSameCategory(parent))) {
                b = true;
                if (ocSymbol2 instanceof OCMethodSymbol && OCDependentMembersCollector.access$400(OCDependentMembersCollector.this) instanceof OCClassSymbol && !((OCClassSymbol)OCDependentMembersCollector.access$400(OCDependentMembersCollector.this)).processMembers(OCDependentMembersCollector.access$300(OCDependentMembersCollector.this).getName().equals(OCDependentMembersCollector.access$400(OCDependentMembersCollector.this).getName()) ? ((OCClassSymbol)OCDependentMembersCollector.access$400(OCDependentMembersCollector.this)).getCategoryName() : null, ocSymbol2.getName(), (Class<? extends OCMemberSymbol>)OCMethodSymbol.class, (com.intellij.util.Processor<? super OCMemberSymbol>)new CommonProcessors.FindFirstProcessor(), false)) {
                    b = false;
                }
                if (ocSymbol2 instanceof OCFunctionSymbol && OCDependentMembersCollector.access$400(OCDependentMembersCollector.this) instanceof OCStructSymbol && !((OCStructSymbol)OCDependentMembersCollector.access$400(OCDependentMembersCollector.this)).processMembers(ocSymbol2.getName(), (Processor<OCSymbol>)new CommonProcessors.FindFirstProcessor())) {
                    b = false;
                }
                if (b) {
                    OCDependentMembersCollector.access$500(OCDependentMembersCollector.this).add(new OCSymbolHolderVirtualPsiElement((definitionSymbol2 != null) ? definitionSymbol2 : ocSymbol2));
                }
            }
        }
        if (!b) {
            this.b(ocSymbol, ocSymbol2);
        }
    }
    
    private void b(final OCSymbol ocSymbol, final OCSymbol<?> ocSymbol2) {
        if (ocSymbol != null && ocSymbol2 != null && !ocSymbol2.getKind().isLocal() && Comparing.equal((Object)ocSymbol.getContainingFile(), (Object)ocSymbol2.getContainingFile()) && ocSymbol2.processPredeclarations((com.intellij.util.Processor<OCSymbol<?>>)(ocSymbol2 -> Comparing.equal((Object)ocSymbol.getContainingFile(), (Object)ocSymbol2.getContainingFile())))) {
            OCDependentMembersCollector.access$600(OCDependentMembersCollector.this).add(ocSymbol2);
        }
    }
}