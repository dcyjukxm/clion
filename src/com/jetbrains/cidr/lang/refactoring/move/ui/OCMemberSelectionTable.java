// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.lang.refactoring.move.ui;

import com.intellij.refactoring.classMembers.MemberInfoBase;
import com.intellij.icons.AllIcons;
import javax.swing.Icon;
import com.intellij.util.ui.EmptyIcon;
import com.intellij.util.PlatformIcons;
import com.intellij.util.ui.JBUI;
import com.intellij.ui.RowIcon;
import com.intellij.refactoring.classMembers.MemberInfoModel;
import java.util.Collection;
import com.jetbrains.cidr.lang.refactoring.move.OCMemberInfo;
import com.jetbrains.cidr.lang.symbols.OCSymbolHolderVirtualPsiElement;
import com.intellij.refactoring.ui.AbstractMemberSelectionTable;

public class OCMemberSelectionTable extends AbstractMemberSelectionTable<OCSymbolHolderVirtualPsiElement, OCMemberInfo>
{
    public OCMemberSelectionTable(final Collection<OCMemberInfo> collection, final MemberInfoModel<OCSymbolHolderVirtualPsiElement, OCMemberInfo> memberInfoModel) {
        super(collection, memberInfoModel, null);
    }
    
    @Override
    protected Object getAbstractColumnValue(final OCMemberInfo ocMemberInfo) {
        return null;
    }
    
    @Override
    protected boolean isAbstractColumnEditable(final int n) {
        return false;
    }
    
    @Override
    protected void setVisibilityIcon(final OCMemberInfo ocMemberInfo, final RowIcon rowIcon) {
        rowIcon.setIcon((Icon)EmptyIcon.create(JBUI.scale(5), PlatformIcons.PUBLIC_ICON.getIconHeight()), 1);
    }
    
    @Override
    protected Icon getOverrideIcon(final OCMemberInfo ocMemberInfo) {
        Object o;
        if (Boolean.TRUE.equals(ocMemberInfo.getOverrides())) {
            o = AllIcons.General.OverridingMethod;
        }
        else if (Boolean.FALSE.equals(ocMemberInfo.getOverrides())) {
            o = AllIcons.General.ImplementingMethod;
        }
        else {
            o = EmptyIcon.create(AllIcons.General.ImplementingMethod);
        }
        return (Icon)o;
    }
}
