// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.lang.refactoring.move.ui;

import com.intellij.refactoring.classMembers.MemberInfoBase;
import com.jetbrains.cidr.lang.refactoring.move.OCMemberInfo;
import com.jetbrains.cidr.lang.symbols.OCSymbolHolderVirtualPsiElement;
import com.intellij.refactoring.classMembers.MemberInfoTooltipManager;

class OCMoveMembersDialog$5 implements MemberInfoTooltipManager.TooltipProvider<OCSymbolHolderVirtualPsiElement, OCMemberInfo> {
    @Override
    public String getTooltip(final OCMemberInfo ocMemberInfo) {
        final String tooltipText = OCMoveMembersDialog.access$000(OCMoveMembersDialog.this).getTooltipText(ocMemberInfo);
        return (tooltipText != null) ? tooltipText : OCMoveMembersDialog.access$100(OCMoveMembersDialog.this).getTooltipText(ocMemberInfo);
    }
}