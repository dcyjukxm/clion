// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.lang.refactoring.move.ui;

import java.util.Collection;
import com.intellij.refactoring.classMembers.MemberInfoChange;
import com.intellij.openapi.editor.event.DocumentEvent;
import com.intellij.openapi.editor.event.DocumentListener;

class OCMoveMembersDialog$6 implements DocumentListener {
    public void documentChanged(final DocumentEvent documentEvent) {
        OCMoveMembersDialog.this.myMemberInfoModel = OCMoveMembersDialog.this.createModel();
        OCMoveMembersDialog.this.myMemberSelectionPanel.getTable().setMemberInfoModel(OCMoveMembersDialog.this.myMemberInfoModel);
        OCMoveMembersDialog.this.myMemberInfoModel.memberInfoChanged(new MemberInfoChange((Collection)OCMoveMembersDialog.this.myMemberInfos));
        OCMoveMembersDialog.this.myMemberSelectionPanel.getTable().fireExternalDataChange();
        OCMoveMembersDialog.access$200(OCMoveMembersDialog.this);
    }
}