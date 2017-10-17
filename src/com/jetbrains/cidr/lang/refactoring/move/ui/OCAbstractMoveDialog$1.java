// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.lang.refactoring.move.ui;

import com.intellij.refactoring.classMembers.MemberInfoChange;
import com.jetbrains.cidr.lang.refactoring.move.OCMemberInfo;
import com.jetbrains.cidr.lang.symbols.OCSymbolHolderVirtualPsiElement;
import com.intellij.refactoring.classMembers.MemberInfoChangeListener;

class OCAbstractMoveDialog$1 implements MemberInfoChangeListener<OCSymbolHolderVirtualPsiElement, OCMemberInfo> {
    public void memberInfoChanged(final MemberInfoChange<OCSymbolHolderVirtualPsiElement, OCMemberInfo> memberInfoChange) {
        OCAbstractMoveDialog.access$000(OCAbstractMoveDialog.this);
    }
}