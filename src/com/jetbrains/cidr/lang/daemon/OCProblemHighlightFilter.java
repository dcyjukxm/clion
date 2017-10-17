// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.lang.daemon;

import com.jetbrains.cidr.lang.psi.OCStringsFile;
import com.jetbrains.cidr.lang.psi.OCFile;
import org.jetbrains.annotations.NotNull;
import com.intellij.psi.PsiFile;
import com.intellij.codeInsight.daemon.ProblemHighlightFilter;

public class OCProblemHighlightFilter extends ProblemHighlightFilter
{
    public boolean shouldHighlight(@NotNull final PsiFile psiFile) {
        try {
            if (psiFile == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "psiFile", "com/jetbrains/cidr/lang/daemon/OCProblemHighlightFilter", "shouldHighlight"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        Label_0071: {
            try {
                if (psiFile instanceof OCFile) {
                    return true;
                }
                final PsiFile psiFile2 = psiFile;
                final boolean b = psiFile2 instanceof OCStringsFile;
                if (b) {
                    return true;
                }
                break Label_0071;
            }
            catch (IllegalArgumentException ex2) {
                throw a(ex2);
            }
            try {
                final PsiFile psiFile2 = psiFile;
                final boolean b = psiFile2 instanceof OCStringsFile;
                if (b) {
                    return true;
                }
            }
            catch (IllegalArgumentException ex3) {
                throw a(ex3);
            }
        }
        for (final OCAnnotatorHelper ocAnnotatorHelper : OCAnnotator.getAnnotatorHelpers()) {
            try {
                if (!ocAnnotatorHelper.shouldHighlight(psiFile)) {
                    return false;
                }
            }
            catch (IllegalArgumentException ex4) {
                throw a(ex4);
            }
        }
        return true;
    }
    
    private static IllegalArgumentException a(final IllegalArgumentException ex) {
        return ex;
    }
}
