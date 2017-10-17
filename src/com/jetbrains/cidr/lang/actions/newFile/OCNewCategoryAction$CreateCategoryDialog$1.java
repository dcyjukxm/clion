// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.lang.actions.newFile;

import com.intellij.openapi.editor.event.DocumentEvent;
import com.intellij.openapi.editor.event.DocumentListener;

class OCNewCategoryAction$CreateCategoryDialog$1 implements DocumentListener {
    public void documentChanged(final DocumentEvent documentEvent) {
        CreateCategoryDialog.this.validateOkAction();
    }
}