// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.lang.formatting;

import com.intellij.openapi.util.TextRange;
import com.intellij.psi.PsiFile;
import com.jetbrains.cidr.lang.OCLanguage;
import com.intellij.psi.codeStyle.CodeStyleSettings;
import org.jetbrains.annotations.NotNull;
import com.intellij.psi.PsiElement;
import com.intellij.psi.impl.source.codeStyle.PostFormatProcessor;

public class OCPostFormatProcessor implements PostFormatProcessor
{
    @Override
    public PsiElement processElement(@NotNull final PsiElement psiElement, @NotNull final CodeStyleSettings codeStyleSettings) {
        try {
            if (psiElement == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "source", "com/jetbrains/cidr/lang/formatting/OCPostFormatProcessor", "processElement"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        try {
            if (codeStyleSettings == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "settings", "com/jetbrains/cidr/lang/formatting/OCPostFormatProcessor", "processElement"));
            }
        }
        catch (IllegalArgumentException ex2) {
            throw a(ex2);
        }
        try {
            if (psiElement.getLanguage() != OCLanguage.getInstance()) {
                return psiElement;
            }
        }
        catch (IllegalArgumentException ex3) {
            throw a(ex3);
        }
        return new OCPostFormatVisitor(codeStyleSettings).process(psiElement);
    }
    
    @NotNull
    @Override
    public TextRange processText(@NotNull final PsiFile psiFile, @NotNull final TextRange textRange, @NotNull final CodeStyleSettings codeStyleSettings) {
        try {
            if (psiFile == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "source", "com/jetbrains/cidr/lang/formatting/OCPostFormatProcessor", "processText"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        try {
            if (textRange == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "rangeToReformat", "com/jetbrains/cidr/lang/formatting/OCPostFormatProcessor", "processText"));
            }
        }
        catch (IllegalArgumentException ex2) {
            throw a(ex2);
        }
        try {
            if (codeStyleSettings == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "settings", "com/jetbrains/cidr/lang/formatting/OCPostFormatProcessor", "processText"));
            }
        }
        catch (IllegalArgumentException ex3) {
            throw a(ex3);
        }
        TextRange process = null;
        Label_0191: {
            TextRange textRange2 = null;
            Label_0156: {
                try {
                    if (psiFile.getLanguage() == OCLanguage.getInstance()) {
                        break Label_0191;
                    }
                    textRange2 = textRange;
                    if (textRange2 == null) {
                        break Label_0156;
                    }
                    return textRange2;
                }
                catch (IllegalArgumentException ex4) {
                    throw a(ex4);
                }
                try {
                    textRange2 = textRange;
                    if (textRange2 == null) {
                        throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/formatting/OCPostFormatProcessor", "processText"));
                    }
                }
                catch (IllegalArgumentException ex5) {
                    throw a(ex5);
                }
            }
            return textRange2;
            try {
                process = new OCPostFormatVisitor(codeStyleSettings).process(psiFile, textRange);
                if (process == null) {
                    throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/formatting/OCPostFormatProcessor", "processText"));
                }
            }
            catch (IllegalArgumentException ex6) {
                throw a(ex6);
            }
        }
        return process;
    }
    
    private static IllegalArgumentException a(final IllegalArgumentException ex) {
        return ex;
    }
}
