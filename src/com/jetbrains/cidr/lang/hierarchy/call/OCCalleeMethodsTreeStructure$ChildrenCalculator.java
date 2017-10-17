// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.lang.hierarchy.call;

import com.jetbrains.cidr.lang.symbols.OCSymbolWithParent;
import com.intellij.codeInsight.highlighting.ReadWriteAccessDetector;
import java.util.HashSet;
import com.jetbrains.cidr.lang.symbols.cpp.OCSymbolWithQualifiedName;
import com.jetbrains.cidr.lang.search.OCFunctionInheritorsSearch;
import com.jetbrains.cidr.lang.search.OCFunctionAncestorsQuery;
import com.jetbrains.cidr.lang.types.OCObjectTypeContext;
import com.jetbrains.cidr.lang.symbols.objc.OCMemberSymbol;
import com.jetbrains.cidr.lang.search.OCMemberInheritorsSearch;
import com.jetbrains.cidr.lang.symbols.OCSymbol;
import com.jetbrains.cidr.lang.psi.OCSendMessageExpression;
import com.intellij.util.Processor;
import com.jetbrains.cidr.lang.search.usages.OCReadWriteAccessDetector;
import com.jetbrains.cidr.lang.symbols.objc.OCClassSymbol;
import com.jetbrains.cidr.lang.symbols.objc.OCPropertySymbol;
import com.jetbrains.cidr.lang.search.OCFunctionReferenceSearch;
import com.jetbrains.cidr.lang.symbols.objc.OCMethodSymbol;
import com.jetbrains.cidr.lang.psi.OCQualifiedExpression;
import com.jetbrains.cidr.lang.psi.OCElement;
import com.jetbrains.cidr.lang.psi.OCCppNewExpression;
import com.jetbrains.cidr.lang.psi.OCCallExpression;
import com.jetbrains.cidr.lang.psi.OCReferenceElement;
import com.intellij.psi.PsiElement;
import com.jetbrains.cidr.lang.psi.OCCallable;
import com.jetbrains.cidr.lang.symbols.cpp.OCFunctionSymbol;

private class ChildrenCalculator extends OCCallHierarchyNodeAggregator
{
    private final boolean myParentIsConstructorDestructor;
    
    private ChildrenCalculator(final OCCallHierarchyNodeDescriptor ocCallHierarchyNodeDescriptor) {
        super(ocCallHierarchyNodeDescriptor);
        final OCCallable enclosingElement = ocCallHierarchyNodeDescriptor.getEnclosingElement();
        if (enclosingElement != null) {
            final OCFunctionSymbol symbol = enclosingElement.getSymbol();
            if (symbol instanceof OCFunctionSymbol) {
                final OCFunctionSymbol ocFunctionSymbol = symbol;
                this.myParentIsConstructorDestructor = (ocFunctionSymbol.isCppConstructor() || ocFunctionSymbol.isCppDestructor());
            }
            else {
                this.myParentIsConstructorDestructor = false;
            }
        }
        else {
            this.myParentIsConstructorDestructor = false;
        }
    }
    
    private void a(final PsiElement psiElement) {
        for (final PsiElement psiElement2 : psiElement.getChildren()) {
            this.a(psiElement2);
            if (psiElement2 instanceof OCReferenceElement) {
                final PsiElement parent = psiElement2.getParent().getParent();
                if (parent instanceof OCCallExpression || parent instanceof OCCppNewExpression) {
                    final OCSymbol resolveToSymbol = ((OCReferenceElement)psiElement2).resolveToSymbol();
                    if (resolveToSymbol instanceof OCFunctionSymbol) {
                        this.a((OCFunctionSymbol)resolveToSymbol, (OCElement)psiElement2, !this.myParentIsConstructorDestructor && ((OCReferenceElement)psiElement2).getNamespaceQualifier() == null);
                    }
                }
            }
            else if (psiElement2 instanceof OCQualifiedExpression) {
                final OCSymbol resolveToSymbol2 = ((OCQualifiedExpression)psiElement2).resolveToSymbol();
                if (resolveToSymbol2 instanceof OCMethodSymbol) {
                    this.a((OCMethodSymbol)resolveToSymbol2, (OCElement)psiElement2);
                }
                else if (psiElement2.getParent() instanceof OCCallExpression && resolveToSymbol2 instanceof OCFunctionSymbol) {
                    this.a((OCFunctionSymbol)resolveToSymbol2, (OCElement)psiElement2, !this.myParentIsConstructorDestructor && OCFunctionReferenceSearch.isCallViaReference(((OCQualifiedExpression)psiElement2).getQualifier()));
                }
                else if (resolveToSymbol2 instanceof OCPropertySymbol) {
                    final OCPropertySymbol ocPropertySymbol = (OCPropertySymbol)resolveToSymbol2;
                    ((OCSymbolWithParent<T, OCClassSymbol>)ocPropertySymbol).getParent().processMembers(OCMethodSymbol.class, (com.intellij.util.Processor<? super OCMethodSymbol>)(ocMethodSymbol -> {
                        if (ocMethodSymbol.getGeneratedFromProperty() == ocPropertySymbol && ((access == ReadWriteAccessDetector.Access.Read && ocMethodSymbol.isGetter()) || (access == ReadWriteAccessDetector.Access.Write && ocMethodSymbol.isSetter()) || access == ReadWriteAccessDetector.Access.ReadWrite)) {
                            this.a(ocMethodSymbol, (OCElement)psiElement2);
                        }
                        return true;
                    }));
                }
            }
            else if (psiElement2 instanceof OCSendMessageExpression) {
                final OCSendMessageExpression ocSendMessageExpression = (OCSendMessageExpression)psiElement2;
                final OCSendMessageExpression.ProbableResponders probableResponders = ocSendMessageExpression.getProbableResponders();
                final OCObjectTypeContext receiverContext = ocSendMessageExpression.getReceiverContext();
                if (receiverContext != null) {
                    OCMethodSymbol knownResponder = probableResponders.getKnownResponder();
                    if (knownResponder != null && !knownResponder.isDefinition()) {
                        final OCMethodSymbol definitionSymbol = knownResponder.getDefinitionSymbol();
                        if (definitionSymbol instanceof OCMethodSymbol) {
                            knownResponder = definitionSymbol;
                        }
                    }
                    final String messageSelector = ocSendMessageExpression.getMessageSelector();
                    final OCCallHierarchyNodeDescriptor addNodeDescriptor = this.addNodeDescriptor(knownResponder, messageSelector, (OCElement)psiElement2);
                    if (addNodeDescriptor != null) {
                        final OCMemberInheritorsSearch.SearchParameters<OCMemberSymbol> parameters = OCMemberInheritorsSearch.getParameters(messageSelector, receiverContext.getType().getClassSymbol(), OCCalleeMethodsTreeStructure.access$000(OCCalleeMethodsTreeStructure.this), (Class<? extends OCMemberSymbol>)OCMethodSymbol.class, receiverContext.getStaticMode());
                        this.a(parameters);
                        OCMemberInheritorsSearch.search(parameters).forEach(ocMethodSymbol -> {
                            if (receiverContext.fitsStaticness(ocMethodSymbol) && ocMethodSymbol.isDefinition()) {
                                addNodeDescriptor.addPossibleResponder(ocMethodSymbol);
                            }
                            return true;
                        });
                    }
                }
            }
        }
    }
    
    private void a(OCMethodSymbol ocMethodSymbol, final OCElement ocElement) {
        if (!ocMethodSymbol.isDefinition()) {
            final OCMethodSymbol definitionSymbol = ocMethodSymbol.getDefinitionSymbol();
            if (definitionSymbol instanceof OCMethodSymbol) {
                ocMethodSymbol = definitionSymbol;
            }
        }
        final OCCallHierarchyNodeDescriptor addNodeDescriptor = this.addNodeDescriptor(ocMethodSymbol, null, ocElement);
        if (addNodeDescriptor != null) {
            final OCMemberInheritorsSearch.SearchParameters<OCMethodSymbol> parameters = OCMemberInheritorsSearch.getParameters(ocMethodSymbol);
            this.a(parameters);
            OCMemberInheritorsSearch.search((OCMemberInheritorsSearch.SearchParameters<OCMemberSymbol>)parameters).forEach(ocMemberSymbol -> {
                if (ocMemberSymbol.isDefinition()) {
                    addNodeDescriptor.addPossibleResponder(ocMemberSymbol);
                }
                return true;
            });
        }
    }
    
    private void a(OCFunctionSymbol ocFunctionSymbol, final OCElement ocElement, final boolean b) {
        if (ocFunctionSymbol.isPredeclaration()) {
            final OCSymbol definitionSymbol = ocFunctionSymbol.getDefinitionSymbol();
            if (definitionSymbol instanceof OCFunctionSymbol) {
                ocFunctionSymbol = (OCFunctionSymbol)definitionSymbol;
            }
        }
        final OCCallHierarchyNodeDescriptor addNodeDescriptor = this.addNodeDescriptor(ocFunctionSymbol, null, ocElement);
        if (addNodeDescriptor != null && b && OCFunctionAncestorsQuery.findFirstVirtual(ocFunctionSymbol, true) != null) {
            final OCFunctionInheritorsSearch.SearchParameters parameters = OCFunctionInheritorsSearch.getParameters(ocFunctionSymbol, ocElement.getContainingOCFile(), true);
            final HashSet set = new HashSet();
            parameters.setImplementationsThenPredeclarations(true);
            parameters.setIncludeSameSymbols(true);
            OCFunctionInheritorsSearch.search(parameters).forEach(ocFunctionSymbol -> {
                if (set.add(ocFunctionSymbol.getResolvedQualifiedName())) {
                    addNodeDescriptor.addPossibleResponder(ocFunctionSymbol);
                }
                return true;
            });
        }
    }
    
    private void a(final OCMemberInheritorsSearch.SearchParameters searchParameters) {
        searchParameters.setIncludeSelfImplementation(true);
        searchParameters.setInterfacesThenImplementations(false);
        searchParameters.setIncludeFromID(true);
        searchParameters.setInheritors(true);
        searchParameters.setAncestors(false);
    }
}
