// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.lang.inspections;

import javax.swing.event.DocumentEvent;
import com.intellij.ui.FieldPanel;
import com.intellij.ui.DocumentAdapter;

class ClangTidyInspection$3 extends DocumentAdapter {
    final /* synthetic */ FieldPanel val$fieldPanel;
    
    protected void textChanged(final DocumentEvent documentEvent) {
        ClangTidyInspection.access$002(ClangTidyInspection.this, this.val$fieldPanel.getTextField().getText());
    }
}