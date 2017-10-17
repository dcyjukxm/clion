// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.cpp.cmake.console;

import com.intellij.execution.filters.HyperlinkInfo;
import com.intellij.execution.impl.EditorHyperlinkSupport;
import java.util.Collection;
import com.intellij.util.containers.ContainerUtil;
import com.intellij.openapi.editor.markup.RangeHighlighter;
import com.intellij.openapi.editor.ScrollType;
import com.intellij.openapi.editor.LogicalPosition;
import com.intellij.pom.NavigatableAdapter;

class CMakeConsoleViewImpl$2 extends NavigatableAdapter {
    final /* synthetic */ int val$line;
    
    public void navigate(final boolean b) {
        CMakeConsoleViewImpl.access$502(CMakeConsoleViewImpl.this, true);
        CMakeConsoleViewImpl.this.getEditor().getCaretModel().moveToLogicalPosition(new LogicalPosition(this.val$line, 0));
        CMakeConsoleViewImpl.this.getEditor().getScrollingModel().scrollToCaret(ScrollType.MAKE_VISIBLE);
        CMakeConsoleViewImpl.access$600(CMakeConsoleViewImpl.this);
        final RangeHighlighter rangeHighlighter = (RangeHighlighter)ContainerUtil.getFirstItem((Collection)CMakeConsoleViewImpl.this.getHyperlinks().findAllHyperlinksOnLine(this.val$line), (Object)null);
        final HyperlinkInfo hyperlinkInfo = (rangeHighlighter == null) ? null : EditorHyperlinkSupport.getHyperlinkInfo(rangeHighlighter);
        if (hyperlinkInfo != null) {
            hyperlinkInfo.navigate(CMakeConsoleViewImpl.this.getProject());
        }
    }
}