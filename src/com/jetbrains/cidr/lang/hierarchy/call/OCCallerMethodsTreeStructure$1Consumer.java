// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.lang.hierarchy.call;

import com.intellij.codeInsight.highlighting.ReadWriteAccessDetector;
import com.jetbrains.cidr.lang.search.usages.OCReadWriteAccessDetector;
import com.jetbrains.cidr.lang.psi.OCQualifiedExpression;
import com.jetbrains.cidr.lang.psi.OCReferenceElement;
import com.jetbrains.cidr.lang.psi.OCDeclarator;
import java.util.List;
import com.jetbrains.cidr.lang.psi.OCDeclaration;
import com.jetbrains.cidr.lang.psi.OCProperty;
import com.jetbrains.cidr.lang.symbols.OCSymbol;
import com.intellij.psi.util.PsiTreeUtil;
import com.jetbrains.cidr.lang.psi.OCCallable;
import com.intellij.psi.search.searches.ReferencesSearch;
import com.jetbrains.cidr.lang.symbols.objc.OCPropertySymbol;
import com.jetbrains.cidr.lang.symbols.objc.OCPropertySymbolImpl;
import com.jetbrains.cidr.lang.psi.OCPropertyAttribute;
import com.jetbrains.cidr.lang.psi.OCSendMessageExpression;
import com.jetbrains.cidr.lang.psi.OCElement;
import com.intellij.psi.search.SearchScope;
import com.intellij.psi.PsiElement;
import java.util.HashSet;
import com.intellij.psi.PsiReference;
import com.intellij.util.Processor;

class 1Consumer implements Processor<PsiReference>
{
    private final HashSet<PsiElement> myProcessed;
    final /* synthetic */ SearchScope val$searchScope;
    final /* synthetic */ OCCallHierarchyNodeAggregator val$aggregator;
    
    1Consumer(final SearchScope val$searchScope, final OCCallHierarchyNodeAggregator val$aggregator) {
        this.val$searchScope = val$searchScope;
        this.val$aggregator = val$aggregator;
        this.myProcessed = new HashSet<PsiElement>();
    }
    
    public boolean process(final PsiReference psiReference) {
        final PsiElement element = psiReference.getElement();
        String messageSelector = null;
        if (element instanceof OCElement && this.myProcessed.add(element)) {
            if (element instanceof OCSendMessageExpression) {
                messageSelector = ((OCSendMessageExpression)element).getMessageSelector();
            }
            else if (element instanceof OCPropertyAttribute) {
                final OCProperty parentProperty = ((OCPropertyAttribute)element).getParentProperty();
                final String name = ((OCPropertyAttribute)element).getName();
                if (name == null) {
                    return true;
                }
                final OCPropertySymbol.PropertyAttribute attribute = OCPropertySymbolImpl.parseAttribute(name);
                final boolean b = attribute == OCPropertySymbol.PropertyAttribute.GETTER;
                final boolean b2 = attribute == OCPropertySymbol.PropertyAttribute.SETTER;
                if (parentProperty == null) {
                    return true;
                }
                final OCDeclaration declaration = parentProperty.getDeclaration();
                if (declaration == null) {
                    return true;
                }
                final List<OCDeclarator> declarators = declaration.getDeclarators();
                if (declarators.size() != 1) {
                    return true;
                }
                ReferencesSearch.search(new ReferencesSearch.SearchParameters((PsiElement)declarators.get(0), this.val$searchScope, false)).forEach(psiReference -> {
                    final PsiElement element = psiReference.getElement();
                    if (element instanceof OCReferenceElement || element instanceof OCQualifiedExpression) {
                        final ReadWriteAccessDetector.Access expressionAccess = new OCReadWriteAccessDetector().getExpressionAccess(element);
                        if ((expressionAccess == ReadWriteAccessDetector.Access.Read && b) || (expressionAccess == ReadWriteAccessDetector.Access.Write && b2) || (expressionAccess == ReadWriteAccessDetector.Access.ReadWrite && (b || b2))) {
                            return this.process(psiReference);
                        }
                    }
                    return true;
                });
                return true;
            }
            final OCCallable ocCallable = (OCCallable)PsiTreeUtil.getContextOfType(element, new Class[] { OCCallable.class });
            if (ocCallable != null) {
                final OCSymbol symbol = ocCallable.getSymbol();
                if (symbol != null) {
                    this.val$aggregator.addNodeDescriptor(symbol, messageSelector, (OCElement)element);
                }
            }
        }
        return true;
    }
}
