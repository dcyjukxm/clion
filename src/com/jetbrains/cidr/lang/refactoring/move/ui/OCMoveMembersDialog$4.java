// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.lang.refactoring.move.ui;

import com.intellij.refactoring.classMembers.MemberInfoBase;
import com.intellij.refactoring.classMembers.MemberInfoChange;
import org.jetbrains.annotations.NotNull;
import com.intellij.psi.PsiElement;
import com.jetbrains.cidr.lang.refactoring.move.OCMemberInfo;
import com.jetbrains.cidr.lang.psi.OCSymbolDeclarator;
import com.jetbrains.cidr.lang.symbols.OCSymbolHolderVirtualPsiElement;
import com.intellij.refactoring.classMembers.UsedByDependencyMemberInfoModel;

class OCMoveMembersDialog$4 extends UsedByDependencyMemberInfoModel<OCSymbolHolderVirtualPsiElement, OCSymbolDeclarator, OCMemberInfo> {
    public int checkForProblems(@NotNull final OCMemberInfo ocMemberInfo) {
        try {
            if (ocMemberInfo == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "memberInfo", "com/jetbrains/cidr/lang/refactoring/move/ui/OCMoveMembersDialog$4", "checkForProblems"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw b(ex);
        }
        return Math.max(OCMoveMembersDialog.access$000(OCMoveMembersDialog.this).checkForProblems(ocMemberInfo), OCMoveMembersDialog.access$100(OCMoveMembersDialog.this).checkForProblems(ocMemberInfo));
    }
    
    @Override
    public void memberInfoChanged(final MemberInfoChange<OCSymbolHolderVirtualPsiElement, OCMemberInfo> memberInfoChange) {
        super.memberInfoChanged((com.intellij.refactoring.classMembers.MemberInfoChange<T, M>)memberInfoChange);
        OCMoveMembersDialog.access$000(OCMoveMembersDialog.this).memberInfoChanged(memberInfoChange);
        OCMoveMembersDialog.access$100(OCMoveMembersDialog.this).memberInfoChanged(memberInfoChange);
    }
    
    public boolean isMemberEnabled(final OCMemberInfo ocMemberInfo) {
        return OCMoveMembersDialog.this.isMemberEnabled(ocMemberInfo);
    }
    
    private static IllegalArgumentException b(final IllegalArgumentException ex) {
        return ex;
    }
}