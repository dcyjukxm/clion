// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.lang.editor;

import com.jetbrains.cidr.lang.psi.OCFile;
import org.jetbrains.annotations.NotNull;
import com.intellij.psi.PsiFile;
import com.intellij.codeInsight.daemon.impl.HighlightRangeExtension;

public class OCHighlightRangeExtension implements HighlightRangeExtension
{
    @Override
    public boolean isForceHighlightParents(@NotNull final PsiFile psiFile) {
        try {
            if (psiFile == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "file", "com/jetbrains/cidr/lang/editor/OCHighlightRangeExtension", "isForceHighlightParents"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        return psiFile instanceof OCFile;
    }
    
    private static IllegalArgumentException a(final IllegalArgumentException ex) {
        return ex;
    }
}
