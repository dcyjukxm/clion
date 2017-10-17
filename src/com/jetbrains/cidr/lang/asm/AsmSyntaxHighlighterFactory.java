// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.lang.asm;

import org.jetbrains.annotations.NotNull;
import com.intellij.openapi.fileTypes.SyntaxHighlighter;
import com.intellij.openapi.vfs.VirtualFile;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.fileTypes.SyntaxHighlighterFactory;

public class AsmSyntaxHighlighterFactory extends SyntaxHighlighterFactory
{
    @NotNull
    public SyntaxHighlighter getSyntaxHighlighter(final Project project, final VirtualFile virtualFile) {
        AsmSyntaxHighlighter asmSyntaxHighlighter;
        try {
            asmSyntaxHighlighter = new AsmSyntaxHighlighter();
            if (asmSyntaxHighlighter == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/asm/AsmSyntaxHighlighterFactory", "getSyntaxHighlighter"));
            }
        }
        catch (IllegalStateException ex) {
            throw a(ex);
        }
        return (SyntaxHighlighter)asmSyntaxHighlighter;
    }
    
    private static IllegalStateException a(final IllegalStateException ex) {
        return ex;
    }
}
