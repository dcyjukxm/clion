// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.doxygen;

import com.jetbrains.cidr.doxygen.psi.DxFile;
import org.jetbrains.annotations.NotNull;
import com.intellij.psi.PsiErrorElement;
import com.intellij.codeInsight.highlighting.HighlightErrorFilter;

public class DxErrorFilter extends HighlightErrorFilter
{
    public boolean shouldHighlightErrorElement(@NotNull final PsiErrorElement psiErrorElement) {
        try {
            if (psiErrorElement == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "element", "com/jetbrains/cidr/doxygen/DxErrorFilter", "shouldHighlightErrorElement"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        try {
            if (!(psiErrorElement.getContainingFile() instanceof DxFile)) {
                return true;
            }
        }
        catch (IllegalArgumentException ex2) {
            throw a(ex2);
        }
        return false;
    }
    
    private static IllegalArgumentException a(final IllegalArgumentException ex) {
        return ex;
    }
}
