// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.cpp.cmake.console;

import com.intellij.openapi.editor.markup.TextAttributes;
import com.intellij.execution.filters.HyperlinkInfo;
import com.intellij.execution.filters.Filter;

class CMakeOutputFilter$1 extends Filter.ResultItem {
    public int getHighlighterLayer() {
        return 5000;
    }
}