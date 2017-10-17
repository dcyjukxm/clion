// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.lang.refactoring.move.ui;

import com.intellij.refactoring.ui.AbstractMemberSelectionTable;
import com.jetbrains.cidr.lang.refactoring.move.OCMemberInfo;
import java.util.Collection;
import com.intellij.psi.PsiElement;
import com.jetbrains.cidr.lang.psi.OCSymbolDeclarator;
import com.intellij.refactoring.classMembers.AbstractUsesDependencyMemberInfoModel;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

class OCPullUpDialog$2 implements ItemListener {
    @Override
    public void itemStateChanged(final ItemEvent itemEvent) {
        if (itemEvent.getStateChange() == 1) {
            OCPullUpDialog.access$100(OCPullUpDialog.this);
            if (OCPullUpDialog.this.myMemberSelectionPanel != null) {
                ((AbstractUsesDependencyMemberInfoModel)OCPullUpDialog.this.myMemberInfoModel).setSuperClass((PsiElement)OCPullUpDialog.access$200(OCPullUpDialog.this).getSelectedItem());
                ((AbstractMemberSelectionTable<T, OCMemberInfo>)OCPullUpDialog.this.myMemberSelectionPanel.getTable()).setMemberInfos(OCPullUpDialog.this.myMemberInfos);
                OCPullUpDialog.this.myMemberSelectionPanel.getTable().fireExternalDataChange();
            }
        }
    }
}