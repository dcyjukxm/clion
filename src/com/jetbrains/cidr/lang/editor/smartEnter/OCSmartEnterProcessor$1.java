// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.lang.editor.smartEnter;

import org.jetbrains.annotations.NotNull;
import com.intellij.openapi.editor.Editor;
import com.intellij.psi.PsiFile;
import com.intellij.psi.PsiElement;
import com.intellij.lang.SmartEnterProcessorWithFixers;

class OCSmartEnterProcessor$1 extends FixEnterProcessor {
    @Override
    public boolean doEnter(final PsiElement psiElement, final PsiFile psiFile, @NotNull final Editor editor, final boolean b) {
        try {
            if (editor == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "editor", "com/jetbrains/cidr/lang/editor/smartEnter/OCSmartEnterProcessor$1", "doEnter"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw b(ex);
        }
        return PlainEnterProcessor.expandCodeBlock(editor, psiElement);
    }
    
    private static IllegalArgumentException b(final IllegalArgumentException ex) {
        return ex;
    }
}