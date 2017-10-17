// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.cpp.cmake.workspace;

import com.intellij.openapi.editor.event.CaretEvent;
import com.intellij.util.Function;
import com.intellij.openapi.editor.event.CaretListener;

class CMakeWorkspaceWatcher$3 implements CaretListener {
    final /* synthetic */ Function val$getWatchedFileForDocument;
    
    public void caretPositionChanged(final CaretEvent caretEvent) {
        if (this.val$getWatchedFileForDocument.fun((Object)caretEvent.getEditor().getDocument()) != null) {
            CMakeWorkspaceWatcher.access$000(CMakeWorkspaceWatcher.this).restartTimer();
        }
    }
}