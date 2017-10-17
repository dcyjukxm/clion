// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.lang.refactoring.move;

import com.intellij.refactoring.classMembers.MemberInfoBase;
import org.jetbrains.annotations.NotNull;
import com.intellij.psi.PsiElement;
import com.jetbrains.cidr.lang.symbols.OCSymbolHolderVirtualPsiElement;
import com.intellij.refactoring.classMembers.AbstractUsesDependencyMemberInfoModel;

class OCTopLevelModel$1 extends AbstractUsesDependencyMemberInfoModel<OCSymbolHolderVirtualPsiElement, PsiElement, OCMemberInfo> {
    @Override
    protected int doCheck(@NotNull final OCMemberInfo ocMemberInfo, final int n) {
        try {
            if (ocMemberInfo == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "memberInfo", "com/jetbrains/cidr/lang/refactoring/move/OCTopLevelModel$1", "doCheck"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw c(ex);
        }
        return n;
    }
    
    private static IllegalArgumentException c(final IllegalArgumentException ex) {
        return ex;
    }
}