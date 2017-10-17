// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.lang.search;

import org.jetbrains.annotations.Nullable;
import com.intellij.openapi.util.TextRange;
import org.jetbrains.annotations.NotNull;
import com.intellij.psi.PsiElement;

public static class Usage
{
    @NotNull
    private final PsiElement myElement;
    @Nullable
    private final TextRange myTextRange;
    
    public Usage(@NotNull final PsiElement myElement, @Nullable final TextRange myTextRange) {
        if (myElement == null) {
            throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "element", "com/jetbrains/cidr/lang/search/OCElementInMacroAndNonCompiledCodeReferencesSearch$Usage", "<init>"));
        }
        this.myElement = myElement;
        this.myTextRange = myTextRange;
    }
    
    public Usage(@NotNull final PsiElement myElement) {
        if (myElement == null) {
            throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "element", "com/jetbrains/cidr/lang/search/OCElementInMacroAndNonCompiledCodeReferencesSearch$Usage", "<init>"));
        }
        this.myElement = myElement;
        this.myTextRange = null;
    }
    
    @NotNull
    public PsiElement getElement() {
        PsiElement myElement;
        try {
            myElement = this.myElement;
            if (myElement == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/search/OCElementInMacroAndNonCompiledCodeReferencesSearch$Usage", "getElement"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        return myElement;
    }
    
    @NotNull
    public TextRange getTextRange() {
        TextRange textRange = null;
        Label_0027: {
            try {
                if (this.myTextRange == null) {
                    final TextRange textRange2;
                    textRange = (textRange2 = this.myElement.getTextRange());
                    break Label_0027;
                }
            }
            catch (IllegalArgumentException ex) {
                throw a(ex);
            }
            TextRange textRange2;
            textRange = (textRange2 = this.myTextRange);
            try {
                if (textRange2 == null) {
                    throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/search/OCElementInMacroAndNonCompiledCodeReferencesSearch$Usage", "getTextRange"));
                }
            }
            catch (IllegalArgumentException ex2) {
                throw a(ex2);
            }
        }
        return textRange;
    }
    
    private static IllegalArgumentException a(final IllegalArgumentException ex) {
        return ex;
    }
}
