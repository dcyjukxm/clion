// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.lang.refactoring.move.ui;

import com.intellij.refactoring.classMembers.MemberInfoBase;
import com.intellij.psi.PsiElement;
import com.jetbrains.cidr.lang.refactoring.move.OCMemberInfo;
import com.jetbrains.cidr.lang.psi.OCSymbolDeclarator;
import com.jetbrains.cidr.lang.symbols.OCSymbolHolderVirtualPsiElement;
import com.intellij.refactoring.classMembers.UsedByDependencyMemberInfoModel;

class OCPushDownDialog$1 extends UsedByDependencyMemberInfoModel<OCSymbolHolderVirtualPsiElement, OCSymbolDeclarator, OCMemberInfo> {
    public boolean isMemberEnabled(final OCMemberInfo ocMemberInfo) {
        return OCPushDownDialog.this.isMemberEnabled(ocMemberInfo);
    }
}