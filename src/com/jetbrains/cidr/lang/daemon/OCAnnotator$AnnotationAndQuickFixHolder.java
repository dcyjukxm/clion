// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.lang.daemon;

import com.intellij.codeInsight.intention.IntentionAction;
import org.jetbrains.annotations.NotNull;
import com.intellij.lang.annotation.Annotation;
import com.intellij.lang.annotation.AnnotationHolder;

public interface AnnotationAndQuickFixHolder extends AnnotationHolder
{
    void createQuickFix(@NotNull final Annotation p0, @NotNull final IntentionAction p1);
}
