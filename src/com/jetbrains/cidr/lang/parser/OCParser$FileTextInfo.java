// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.lang.parser;

import com.intellij.openapi.editor.impl.LineSet;
import org.jetbrains.annotations.NotNull;

private static class FileTextInfo
{
    @NotNull
    final String text;
    @NotNull
    final LineSet lineSet;
    final long timestamp;
    
    private FileTextInfo(@NotNull final String text, @NotNull final LineSet lineSet, final long timestamp) {
        if (text == null) {
            throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "text", "com/jetbrains/cidr/lang/parser/OCParser$FileTextInfo", "<init>"));
        }
        if (lineSet == null) {
            throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "lineSet", "com/jetbrains/cidr/lang/parser/OCParser$FileTextInfo", "<init>"));
        }
        this.text = text;
        this.lineSet = lineSet;
        this.timestamp = timestamp;
    }
}
