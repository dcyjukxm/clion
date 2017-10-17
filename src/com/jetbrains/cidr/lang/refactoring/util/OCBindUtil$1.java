// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.lang.refactoring.util;

import java.util.Iterator;
import java.util.List;
import com.intellij.psi.PsiReference;
import com.intellij.psi.util.PsiTreeUtil;
import com.jetbrains.cidr.lang.symbols.OCSymbolWithParent;
import com.jetbrains.cidr.lang.psi.OCPolyVariantReference;
import com.jetbrains.cidr.lang.psi.OCReference;
import com.jetbrains.cidr.lang.psi.OCReferenceElement;
import com.jetbrains.cidr.lang.util.OCElementUtil;
import com.intellij.psi.PsiElement;
import com.jetbrains.cidr.lang.symbols.OCSymbol;
import java.util.Collection;
import com.jetbrains.cidr.lang.psi.visitors.OCRecursiveModifyableVisitor;

static final class OCBindUtil$1 extends OCRecursiveModifyableVisitor {
    final /* synthetic */ Collection val$scope;
    final /* synthetic */ OCSymbol val$parentSymbol;
    final /* synthetic */ boolean val$encodeLocals;
    
    @Override
    public void visitElement(final PsiElement psiElement) {
        if (OCElementUtil.isPartOfMacroSubstitution(psiElement)) {
            return;
        }
        super.visitElement(psiElement);
        OCSymbol<PsiElement> ocSymbol = null;
        if (psiElement instanceof OCReferenceElement) {
            ocSymbol = (OCSymbol<PsiElement>)((OCReferenceElement)psiElement).resolveToSymbol();
        }
        else {
            final PsiReference reference = psiElement.getReference();
            if (reference instanceof OCReference) {
                ocSymbol = (OCSymbol<PsiElement>)((OCReference<OCSymbol<PsiElement>>)reference).resolveToSymbol();
            }
            else if (reference instanceof OCPolyVariantReference) {
                final List<OCSymbol> resolveToSymbols = ((OCPolyVariantReference<OCSymbol>)reference).resolveToSymbols();
                ocSymbol = (resolveToSymbols.isEmpty() ? null : resolveToSymbols.iterator().next());
            }
        }
        if (ocSymbol != null) {
            final PsiElement locateDefinition = ocSymbol.locateDefinition();
            if (ocSymbol instanceof OCSymbolWithParent) {
                boolean b = false;
                if (locateDefinition != null) {
                    final Iterator<PsiElement> iterator = this.val$scope.iterator();
                    while (iterator.hasNext()) {
                        if (PsiTreeUtil.isContextAncestor((PsiElement)iterator.next(), locateDefinition, false)) {
                            b = true;
                            break;
                        }
                    }
                }
                if (b) {
                    if (this.val$parentSymbol != null) {
                        final PsiReference qualifierReference = OCBindUtil.getQualifierReference(psiElement);
                        if (qualifierReference instanceof OCReference && this.val$parentSymbol.isSameSymbol(((OCReference<OCSymbol>)qualifierReference).resolveToSymbol())) {
                            OCBindUtil.encodeAsRefToParent(psiElement);
                        }
                        else if (qualifierReference instanceof OCPolyVariantReference) {
                            final Iterator<OCSymbol> iterator2 = ((OCPolyVariantReference<OCSymbol>)qualifierReference).resolveToSymbols().iterator();
                            while (iterator2.hasNext()) {
                                if (this.val$parentSymbol.isSameSymbol(iterator2.next())) {
                                    OCBindUtil.encodeAsRefToParent(psiElement);
                                    break;
                                }
                            }
                        }
                    }
                }
                else if (this.val$encodeLocals || !ocSymbol.getKind().isLocal()) {
                    psiElement.putCopyableUserData(OCBindUtil.access$000(), (Object)OCBindUtil.access$000());
                    psiElement.putCopyableUserData(OCBindUtil.access$100(), (Object)ocSymbol);
                    psiElement.putCopyableUserData(OCBindUtil.access$200(), (Object)locateDefinition);
                    psiElement.putCopyableUserData(OCBindUtil.access$300(), (Object)false);
                    psiElement.putCopyableUserData(OCBindUtil.access$400(), (Object)(((OCSymbolWithParent<?, ?>)ocSymbol).getParent() == this.val$parentSymbol));
                }
            }
        }
    }
}