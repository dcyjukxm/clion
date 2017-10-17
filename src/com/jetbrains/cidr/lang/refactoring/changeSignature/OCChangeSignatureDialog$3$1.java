// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.lang.refactoring.changeSignature;

import com.intellij.openapi.editor.event.DocumentEvent;
import com.intellij.openapi.editor.event.DocumentListener;

class OCChangeSignatureDialog$3$1 implements DocumentListener {
    final /* synthetic */ int val$columnFinal;
    
    public void documentChanged(final DocumentEvent documentEvent) {
        JBTableRowEditor.this.fireDocumentChanged(documentEvent, this.val$columnFinal);
    }
}