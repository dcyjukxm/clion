// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.lang.editor.surround;

import com.intellij.psi.PsiFile;
import com.jetbrains.cidr.lang.psi.OCFile;
import org.jetbrains.annotations.NotNull;
import com.intellij.psi.PsiElement;
import org.jetbrains.annotations.Nullable;
import com.jetbrains.cidr.lang.OCLanguageKind;
import com.intellij.lang.surroundWith.Surrounder;

public abstract class OCSurrounder implements Surrounder
{
    @Nullable
    protected OCLanguageKind getLanguageKind() {
        return null;
    }
    
    public boolean isApplicable(@NotNull final PsiElement[] array) {
        try {
            if (array == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "elements", "com/jetbrains/cidr/lang/editor/surround/OCSurrounder", "isApplicable"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        try {
            if (array.length == 0) {
                return false;
            }
        }
        catch (IllegalArgumentException ex2) {
            throw a(ex2);
        }
        final OCLanguageKind languageKind = this.getLanguageKind();
        if (languageKind != null) {
            final PsiFile containingFile = array[0].getContainingFile();
            try {
                if (!(containingFile instanceof OCFile)) {
                    return false;
                }
                final PsiFile psiFile = containingFile;
                final OCFile ocFile = (OCFile)psiFile;
                final OCLanguageKind ocLanguageKind = ocFile.getKind();
                final OCLanguageKind ocLanguageKind2 = languageKind;
                final boolean b = ocLanguageKind.conforms(ocLanguageKind2);
                if (!b) {
                    return false;
                }
                return true;
            }
            catch (IllegalArgumentException ex3) {
                throw a(ex3);
            }
            try {
                final PsiFile psiFile = containingFile;
                final OCFile ocFile = (OCFile)psiFile;
                final OCLanguageKind ocLanguageKind = ocFile.getKind();
                final OCLanguageKind ocLanguageKind2 = languageKind;
                final boolean b = ocLanguageKind.conforms(ocLanguageKind2);
                if (!b) {
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
