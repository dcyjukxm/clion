// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.lang.daemon;

import org.jetbrains.annotations.Nullable;
import com.jetbrains.cidr.lang.psi.OCFile;
import com.intellij.codeHighlighting.TextEditorHighlightingPass;
import com.intellij.openapi.editor.Editor;
import org.jetbrains.annotations.NotNull;
import com.intellij.psi.PsiFile;
import com.intellij.codeHighlighting.TextEditorHighlightingPassRegistrar;
import com.intellij.openapi.project.Project;
import com.intellij.codeHighlighting.TextEditorHighlightingPassFactory;
import com.intellij.openapi.components.AbstractProjectComponent;

public class OCLargeFileHighlighterFactory extends AbstractProjectComponent implements TextEditorHighlightingPassFactory
{
    public OCLargeFileHighlighterFactory(final Project project, final TextEditorHighlightingPassRegistrar textEditorHighlightingPassRegistrar) {
        super(project);
        textEditorHighlightingPassRegistrar.registerTextEditorHighlightingPass(this, null, null, false, -1);
    }
    
    @Nullable
    public TextEditorHighlightingPass createHighlightingPass(@NotNull final PsiFile psiFile, @NotNull final Editor editor) {
        try {
            if (psiFile == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "file", "com/jetbrains/cidr/lang/daemon/OCLargeFileHighlighterFactory", "createHighlightingPass"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        try {
            if (editor == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "editor", "com/jetbrains/cidr/lang/daemon/OCLargeFileHighlighterFactory", "createHighlightingPass"));
            }
        }
        catch (IllegalArgumentException ex2) {
            throw a(ex2);
        }
        try {
            if (psiFile instanceof OCFile) {
                return new OCLargeFileHighlighter(psiFile, editor.getDocument());
            }
        }
        catch (IllegalArgumentException ex3) {
            throw a(ex3);
        }
        return null;
    }
    
    private static IllegalArgumentException a(final IllegalArgumentException ex) {
        return ex;
    }
}
