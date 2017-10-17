// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.lang.daemon;

import java.util.Iterator;
import java.util.ArrayList;
import java.util.List;
import com.intellij.codeInsight.intention.IntentionAction;
import com.intellij.openapi.editor.colors.TextAttributesKey;
import com.intellij.openapi.util.TextRange;
import com.intellij.lang.annotation.Annotation;
import com.intellij.codeInspection.ProblemHighlightType;
import org.jetbrains.annotations.NotNull;
import com.jetbrains.cidr.lang.inspections.OCInspection;
import org.jetbrains.annotations.Nullable;
import com.intellij.psi.PsiElement;

public interface OCAnnotatorSink
{
    @Nullable
    Annotation addErrorAnnotation(@Nullable final PsiElement p0, @Nullable final Class<? extends OCInspection> p1, @Nullable final String p2, @NotNull final String p3, @Nullable final ProblemHighlightType p4);
    
    @Nullable
    Annotation addErrorAnnotation(@Nullable final PsiElement p0, @Nullable final TextRange p1, @Nullable final Class<? extends OCInspection> p2, @Nullable final String p3, @NotNull final String p4, @Nullable final ProblemHighlightType p5);
    
    @Nullable
    Annotation addErrorAnnotation(@Nullable final TextRange p0, @Nullable final Class<? extends OCInspection> p1, @Nullable final String p2, @NotNull final String p3, @Nullable final ProblemHighlightType p4);
    
    @Nullable
    Annotation addWarningAnnotation(@Nullable final PsiElement p0, @Nullable final Class<? extends OCInspection> p1, @Nullable final String p2, @NotNull final String p3, @Nullable final ProblemHighlightType p4);
    
    @Nullable
    Annotation addWarningAnnotation(@Nullable final PsiElement p0, @Nullable final TextRange p1, @Nullable final Class<? extends OCInspection> p2, @Nullable final String p3, @NotNull final String p4, @Nullable final ProblemHighlightType p5);
    
    @Nullable
    Annotation highlight(@Nullable final PsiElement p0, @Nullable final TextAttributesKey p1);
    
    void highlight(@NotNull final TextRange p0, @Nullable final TextAttributesKey p1);
    
    void registerQuickFix(@Nullable final Annotation p0, @NotNull final IntentionAction p1);
    
    @Nullable
    default Annotation addErrorAnnotation(@Nullable final PsiElement psiElement, @Nullable final String s, @NotNull final String s2) {
        try {
            if (s2 == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "message", "com/jetbrains/cidr/lang/daemon/OCAnnotatorSink", "addErrorAnnotation"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        return this.addErrorAnnotation(psiElement, null, s, s2, null);
    }
    
    @Nullable
    default Annotation addErrorAnnotation(@Nullable final PsiElement psiElement, @Nullable final Class<? extends OCInspection> clazz, @Nullable final String s, @NotNull final String s2) {
        try {
            if (s2 == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "message", "com/jetbrains/cidr/lang/daemon/OCAnnotatorSink", "addErrorAnnotation"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        return this.addErrorAnnotation(psiElement, clazz, s, s2, null);
    }
    
    default List<Annotation> addErrorAnnotations(@NotNull final List<? extends PsiElement> list, @Nullable final Class<? extends OCInspection> clazz, @Nullable final String s, @NotNull final String s2) {
        try {
            if (list == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "elements", "com/jetbrains/cidr/lang/daemon/OCAnnotatorSink", "addErrorAnnotations"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        try {
            if (s2 == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "message", "com/jetbrains/cidr/lang/daemon/OCAnnotatorSink", "addErrorAnnotations"));
            }
        }
        catch (IllegalArgumentException ex2) {
            throw a(ex2);
        }
        final ArrayList<Annotation> list2 = new ArrayList<Annotation>();
        final Iterator<? extends PsiElement> iterator = list.iterator();
        while (iterator.hasNext()) {
            final Annotation addErrorAnnotation = this.addErrorAnnotation((PsiElement)iterator.next(), clazz, s, s2);
            try {
                if (addErrorAnnotation == null) {
                    continue;
                }
                list2.add(addErrorAnnotation);
            }
            catch (IllegalArgumentException ex3) {
                throw a(ex3);
            }
        }
        return list2;
    }
    
    @Nullable
    default Annotation addWarningAnnotation(@Nullable final PsiElement psiElement, @Nullable final Class<? extends OCInspection> clazz, @Nullable final String s, @NotNull final String s2) {
        try {
            if (s2 == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "message", "com/jetbrains/cidr/lang/daemon/OCAnnotatorSink", "addWarningAnnotation"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        return this.addWarningAnnotation(psiElement, clazz, s, s2, null);
    }
    
    default List<Annotation> addWarningAnnotations(@NotNull final List<? extends PsiElement> list, @Nullable final Class<? extends OCInspection> clazz, @Nullable final String s, @NotNull final String s2) {
        try {
            if (list == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "elements", "com/jetbrains/cidr/lang/daemon/OCAnnotatorSink", "addWarningAnnotations"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        try {
            if (s2 == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "message", "com/jetbrains/cidr/lang/daemon/OCAnnotatorSink", "addWarningAnnotations"));
            }
        }
        catch (IllegalArgumentException ex2) {
            throw a(ex2);
        }
        final ArrayList<Annotation> list2 = new ArrayList<Annotation>();
        final Iterator<? extends PsiElement> iterator = list.iterator();
        while (iterator.hasNext()) {
            final Annotation addWarningAnnotation = this.addWarningAnnotation((PsiElement)iterator.next(), clazz, s, s2, ProblemHighlightType.GENERIC_ERROR_OR_WARNING);
            try {
                if (addWarningAnnotation == null) {
                    continue;
                }
                list2.add(addWarningAnnotation);
            }
            catch (IllegalArgumentException ex3) {
                throw a(ex3);
            }
        }
        return list2;
    }
    
    default void registerQuickFixes(@Nullable final List<Annotation> list, @NotNull final IntentionAction intentionAction) {
        try {
            if (intentionAction == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "intentionAction", "com/jetbrains/cidr/lang/daemon/OCAnnotatorSink", "registerQuickFixes"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        if (list != null) {
            final Iterator<Annotation> iterator = list.iterator();
            while (iterator.hasNext()) {
                this.registerQuickFix(iterator.next(), intentionAction);
            }
        }
    }
    
    default IllegalArgumentException a(final IllegalArgumentException ex) {
        return ex;
    }
}
