// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.lang.refactoring.move.ui;

import java.awt.Component;
import com.intellij.ui.ScrollPaneFactory;
import java.util.Collection;
import java.awt.LayoutManager;
import java.awt.BorderLayout;
import com.jetbrains.cidr.lang.symbols.OCSymbolHolderVirtualPsiElement;
import com.intellij.refactoring.classMembers.MemberInfoModel;
import com.jetbrains.cidr.lang.refactoring.move.OCMemberInfo;
import java.util.List;
import javax.swing.JPanel;

public class OCMemberSelectionPanel extends JPanel
{
    private final OCMemberSelectionTable myTable;
    
    public OCMemberSelectionPanel(final List<OCMemberInfo> list, final MemberInfoModel<OCSymbolHolderVirtualPsiElement, OCMemberInfo> memberInfoModel) {
        super(new BorderLayout());
        this.myTable = new OCMemberSelectionTable(list, memberInfoModel);
        this.add(ScrollPaneFactory.createScrollPane((Component)this.myTable), "Center");
    }
    
    public OCMemberSelectionTable getTable() {
        return this.myTable;
    }
}
