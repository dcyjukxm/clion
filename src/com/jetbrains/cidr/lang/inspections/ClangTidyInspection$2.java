// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.lang.inspections;

import java.util.Collection;
import com.intellij.openapi.util.text.StringUtil;
import java.util.List;
import javax.swing.JTextField;
import com.intellij.openapi.ui.Messages;
import java.awt.event.ActionEvent;
import com.intellij.ui.components.JBTextField;
import java.awt.event.ActionListener;

class ClangTidyInspection$2 implements ActionListener {
    final /* synthetic */ JBTextField val$textField;
    
    @Override
    public void actionPerformed(final ActionEvent actionEvent) {
        Messages.showTextAreaDialog((JTextField)this.val$textField, "Edit Clang-Tidy Checks", "ClangTidyInspection", s -> StringUtil.split(s, ","), list -> StringUtil.join((Collection)list, ","));
    }
}