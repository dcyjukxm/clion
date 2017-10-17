// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.lang.refactoring.move.ui;

import com.intellij.refactoring.classMembers.MemberInfoBase;
import java.util.Iterator;
import org.jetbrains.annotations.NotNull;
import com.intellij.psi.PsiElement;
import com.jetbrains.cidr.lang.refactoring.move.OCMemberInfo;
import com.jetbrains.cidr.lang.psi.OCSymbolDeclarator;
import com.jetbrains.cidr.lang.symbols.OCSymbolHolderVirtualPsiElement;
import com.intellij.refactoring.classMembers.UsedByDependencyMemberInfoModel;

class OCMoveMembersDialog$2 extends UsedByDependencyMemberInfoModel<OCSymbolHolderVirtualPsiElement, OCSymbolDeclarator, OCMemberInfo> {
    public int checkForProblems(@NotNull final OCMemberInfo ocMemberInfo) {
        try {
            if (ocMemberInfo == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "memberInfo", "com/jetbrains/cidr/lang/refactoring/move/ui/OCMoveMembersDialog$2", "checkForProblems"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw b(ex);
        }
        final int checkForProblems = super.checkForProblems((M)ocMemberInfo);
        Label_0075: {
            try {
                if (checkForProblems != 2) {
                    return checkForProblems;
                }
                final OCMemberInfo ocMemberInfo2 = ocMemberInfo;
                final boolean b = ocMemberInfo2.isStatic();
                if (b) {
                    return 1;
                }
                break Label_0075;
            }
            catch (IllegalArgumentException ex2) {
                throw b(ex2);
            }
            try {
                final OCMemberInfo ocMemberInfo2 = ocMemberInfo;
                final boolean b = ocMemberInfo2.isStatic();
                if (b) {
                    return 1;
                }
            }
            catch (IllegalArgumentException ex3) {
                throw b(ex3);
            }
        }
        for (final OCMemberInfo ocMemberInfo3 : OCMoveMembersDialog.this.getSelectedMemberInfos()) {
            try {
                if (!ocMemberInfo3.isStatic()) {
                    return checkForProblems;
                }
                continue;
            }
            catch (IllegalArgumentException ex4) {
                throw b(ex4);
            }
        }
        return 1;
    }
    
    public boolean isMemberEnabled(final OCMemberInfo ocMemberInfo) {
        return OCMoveMembersDialog.this.isMemberEnabled(ocMemberInfo);
    }
    
    private static IllegalArgumentException b(final IllegalArgumentException ex) {
        return ex;
    }
}