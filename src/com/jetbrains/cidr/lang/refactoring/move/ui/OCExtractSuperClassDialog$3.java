// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.lang.refactoring.move.ui;

import com.intellij.refactoring.classMembers.MemberInfoBase;
import org.jetbrains.annotations.NotNull;
import com.intellij.psi.PsiElement;
import com.jetbrains.cidr.lang.refactoring.move.OCMemberInfo;
import com.jetbrains.cidr.lang.psi.OCSymbolDeclarator;
import com.jetbrains.cidr.lang.symbols.OCSymbolHolderVirtualPsiElement;
import com.intellij.refactoring.classMembers.AbstractUsesDependencyMemberInfoModel;

class OCExtractSuperClassDialog$3 extends AbstractUsesDependencyMemberInfoModel<OCSymbolHolderVirtualPsiElement, OCSymbolDeclarator, OCMemberInfo> {
    @Override
    protected int doCheck(@NotNull final OCMemberInfo ocMemberInfo, final int n) {
        try {
            if (ocMemberInfo == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "memberInfo", "com/jetbrains/cidr/lang/refactoring/move/ui/OCExtractSuperClassDialog$3", "doCheck"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw c(ex);
        }
        try {
            if (n != 2) {
                return n;
            }
            final OCMemberInfo ocMemberInfo2 = ocMemberInfo;
            final boolean b = ocMemberInfo2.isStatic();
            if (b) {
                return 1;
            }
            return n;
        }
        catch (IllegalArgumentException ex2) {
            throw c(ex2);
        }
        try {
            final OCMemberInfo ocMemberInfo2 = ocMemberInfo;
            final boolean b = ocMemberInfo2.isStatic();
            if (b) {
                return 1;
            }
        }
        catch (IllegalArgumentException ex3) {
            throw c(ex3);
        }
        return n;
    }
    
    public boolean isMemberEnabled(final OCMemberInfo ocMemberInfo) {
        return OCExtractSuperClassDialog.this.isMemberEnabled(ocMemberInfo);
    }
    
    private static IllegalArgumentException c(final IllegalArgumentException ex) {
        return ex;
    }
}