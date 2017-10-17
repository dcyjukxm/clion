// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.lang.refactoring.util;

import com.jetbrains.cidr.lang.symbols.OCVisibility;
import com.intellij.openapi.util.Pair;
import com.intellij.psi.SmartPsiElementPointer;
import com.jetbrains.cidr.lang.psi.OCDeclarator;
import com.jetbrains.cidr.lang.symbols.OCSymbolWithParent;
import com.jetbrains.cidr.lang.util.OCElementUtil;
import com.intellij.psi.PsiElement;
import java.util.Map;
import com.jetbrains.cidr.lang.symbols.OCSymbol;
import com.jetbrains.cidr.lang.psi.visitors.OCRecursiveModifyableVisitor;

static final class OCBindUtil$2 extends OCRecursiveModifyableVisitor {
    final /* synthetic */ OCSymbol val$newParentSymbol;
    final /* synthetic */ Map val$elemsToEscalateVisibility;
    
    @Override
    public void visitElement(final PsiElement psiElement) {
        if (OCElementUtil.isPartOfMacroSubstitution(psiElement)) {
            return;
        }
        super.visitElement(psiElement);
        if (psiElement.getCopyableUserData(OCBindUtil.access$000()) != null) {
            final OCSymbolWithParent ocSymbolWithParent = (OCSymbolWithParent)psiElement.getCopyableUserData(OCBindUtil.access$100());
            final PsiElement psiElement2 = (PsiElement)psiElement.getCopyableUserData(OCBindUtil.access$200());
            final Boolean b = (Boolean)psiElement.getCopyableUserData(OCBindUtil.access$300());
            final Boolean b2 = (Boolean)psiElement.getCopyableUserData(OCBindUtil.access$400());
            psiElement.putCopyableUserData(OCBindUtil.access$000(), (Object)null);
            psiElement.putCopyableUserData(OCBindUtil.access$300(), (Object)null);
            psiElement.putCopyableUserData(OCBindUtil.access$400(), (Object)null);
            psiElement.putCopyableUserData(OCBindUtil.access$100(), (Object)null);
            psiElement.putCopyableUserData(OCBindUtil.access$200(), (Object)null);
            assert b2 != null;
            if (b == Boolean.TRUE) {
                OCBindUtil.bindReferenceAndMakeVisible((psiElement instanceof OCDeclarator) ? ((OCDeclarator)psiElement).getNamespaceQualifier().getReference() : OCBindUtil.getQualifierReference(psiElement), this.val$newParentSymbol, this.val$newParentSymbol.locateDefinition(), this.val$elemsToEscalateVisibility, b2);
            }
            else if (ocSymbolWithParent != null) {
                OCBindUtil.bindReferenceAndMakeVisible(psiElement.getReference(), ocSymbolWithParent, psiElement2, this.val$elemsToEscalateVisibility, b2);
            }
        }
    }
}