// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.cpp.cmake.console;

import com.intellij.openapi.editor.Document;
import com.intellij.openapi.editor.event.DocumentEvent;
import com.intellij.openapi.editor.ScrollType;
import com.intellij.openapi.editor.LogicalPosition;
import com.intellij.openapi.editor.event.DocumentListener;

class CMakeConsoleViewImpl$1 implements DocumentListener {
    private void a(final int n, final CharSequence charSequence) {
        final CMakeConsoleMessageType access$000 = CMakeConsoleViewImpl.access$000(CMakeConsoleViewImpl.this, charSequence);
        if (access$000 != null) {
            final boolean b = access$000 == CMakeConsoleMessageType.ERROR;
            if (b && CMakeConsoleViewImpl.access$100(CMakeConsoleViewImpl.this).isEmpty()) {
                CMakeConsoleViewImpl.this.getEditor().getCaretModel().moveToLogicalPosition(new LogicalPosition(n - 1, 0));
                CMakeConsoleViewImpl.this.getEditor().getScrollingModel().scrollToCaret(ScrollType.CENTER);
            }
            (b ? CMakeConsoleViewImpl.access$100(CMakeConsoleViewImpl.this) : CMakeConsoleViewImpl.access$200(CMakeConsoleViewImpl.this)).add(n);
            CMakeConsoleViewImpl.access$300(CMakeConsoleViewImpl.this, b ? CMakeConsoleMessageType.ERROR : CMakeConsoleMessageType.WARNING);
        }
    }
    
    public void documentChanged(final DocumentEvent documentEvent) {
        if (!CMakeConsoleViewImpl.access$400(CMakeConsoleViewImpl.this, documentEvent.getNewFragment())) {
            return;
        }
        final Document document = documentEvent.getDocument();
        final int lineNumber = document.getLineNumber(documentEvent.getOffset());
        for (int lineNumber2 = document.getLineNumber(documentEvent.getOffset() + documentEvent.getNewLength()), i = lineNumber; i <= lineNumber2; ++i) {
            this.a(i, document.getImmutableCharSequence().subSequence(document.getLineStartOffset(i), document.getLineEndOffset(i)));
        }
    }
}