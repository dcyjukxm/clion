// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.lang.daemon;

import com.intellij.codeInsight.intention.IntentionAction;
import com.intellij.openapi.editor.colors.TextAttributesKey;
import com.intellij.openapi.util.TextRange;
import com.intellij.lang.annotation.Annotation;
import com.intellij.codeInspection.ProblemHighlightType;
import org.jetbrains.annotations.NotNull;
import com.jetbrains.cidr.lang.inspections.OCInspection;
import org.jetbrains.annotations.Nullable;
import com.intellij.psi.PsiElement;

public class OCNullAnnotatorSink implements OCAnnotatorSink
{
    public static final OCNullAnnotatorSink INSTANCE;
    
    @Nullable
    @Override
    public Annotation addErrorAnnotation(@Nullable final PsiElement psiElement, @Nullable final Class<? extends OCInspection> clazz, @Nullable final String s, @NotNull final String s2, @Nullable final ProblemHighlightType problemHighlightType) {
        try {
            if (s2 == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "message", "com/jetbrains/cidr/lang/daemon/OCNullAnnotatorSink", "addErrorAnnotation"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw b(ex);
        }
        return null;
    }
    
    @Nullable
    @Override
    public Annotation addErrorAnnotation(@Nullable final PsiElement psiElement, @Nullable final TextRange textRange, @Nullable final Class<? extends OCInspection> clazz, @Nullable final String s, @NotNull final String s2, @Nullable final ProblemHighlightType problemHighlightType) {
        try {
            if (s2 == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "message", "com/jetbrains/cidr/lang/daemon/OCNullAnnotatorSink", "addErrorAnnotation"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw b(ex);
        }
        return null;
    }
    
    @Nullable
    @Override
    public Annotation addErrorAnnotation(@Nullable final TextRange textRange, @Nullable final Class<? extends OCInspection> clazz, @Nullable final String s, @NotNull final String s2, @Nullable final ProblemHighlightType problemHighlightType) {
        try {
            if (s2 == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "message", "com/jetbrains/cidr/lang/daemon/OCNullAnnotatorSink", "addErrorAnnotation"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw b(ex);
        }
        return null;
    }
    
    @Nullable
    @Override
    public Annotation addWarningAnnotation(@Nullable final PsiElement psiElement, @Nullable final Class<? extends OCInspection> clazz, @Nullable final String s, @NotNull final String s2, @Nullable final ProblemHighlightType problemHighlightType) {
        try {
            if (s2 == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "message", "com/jetbrains/cidr/lang/daemon/OCNullAnnotatorSink", "addWarningAnnotation"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw b(ex);
        }
        return null;
    }
    
    @Nullable
    @Override
    public Annotation addWarningAnnotation(@Nullable final PsiElement psiElement, @Nullable final TextRange textRange, @Nullable final Class<? extends OCInspection> clazz, @Nullable final String s, @NotNull final String s2, @Nullable final ProblemHighlightType problemHighlightType) {
        try {
            if (s2 == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "message", "com/jetbrains/cidr/lang/daemon/OCNullAnnotatorSink", "addWarningAnnotation"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw b(ex);
        }
        return null;
    }
    
    @Nullable
    @Override
    public Annotation highlight(@Nullable final PsiElement psiElement, @Nullable final TextAttributesKey textAttributesKey) {
        return null;
    }
    
    @Override
    public void highlight(@NotNull final TextRange textRange, @Nullable final TextAttributesKey textAttributesKey) {
        try {
            if (textRange == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "range", "com/jetbrains/cidr/lang/daemon/OCNullAnnotatorSink", "highlight"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw b(ex);
        }
    }
    
    @Override
    public void registerQuickFix(@Nullable final Annotation annotation, @NotNull final IntentionAction intentionAction) {
        try {
            if (intentionAction == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "intentionAction", "com/jetbrains/cidr/lang/daemon/OCNullAnnotatorSink", "registerQuickFix"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw b(ex);
        }
    }
    
    static {
        INSTANCE = new OCNullAnnotatorSink();
    }
    
    private static IllegalArgumentException b(final IllegalArgumentException ex) {
        return ex;
    }
}
