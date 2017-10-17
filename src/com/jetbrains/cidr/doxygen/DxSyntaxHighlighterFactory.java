// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.doxygen;

import org.jetbrains.annotations.NotNull;
import com.intellij.openapi.fileTypes.SyntaxHighlighter;
import com.intellij.openapi.vfs.VirtualFile;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.fileTypes.SyntaxHighlighterFactory;

public class DxSyntaxHighlighterFactory extends SyntaxHighlighterFactory
{
    @NotNull
    public SyntaxHighlighter getSyntaxHighlighter(final Project project, final VirtualFile virtualFile) {
        DxSyntaxHighlighter dxSyntaxHighlighter;
        try {
            dxSyntaxHighlighter = new DxSyntaxHighlighter();
            if (dxSyntaxHighlighter == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/doxygen/DxSyntaxHighlighterFactory", "getSyntaxHighlighter"));
            }
        }
        catch (IllegalStateException ex) {
            throw a(ex);
        }
        return (SyntaxHighlighter)dxSyntaxHighlighter;
    }
    
    private static IllegalStateException a(final IllegalStateException ex) {
        return ex;
    }
}
