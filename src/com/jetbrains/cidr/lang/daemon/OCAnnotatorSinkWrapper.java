// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.lang.daemon;

import com.intellij.codeInsight.intention.IntentionAction;
import com.intellij.openapi.editor.colors.TextAttributesKey;
import com.intellij.psi.PsiElement;
import com.intellij.lang.annotation.Annotation;
import com.intellij.codeInspection.ProblemHighlightType;
import com.jetbrains.cidr.lang.inspections.OCInspection;
import org.jetbrains.annotations.Nullable;
import com.intellij.openapi.util.TextRange;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import kotlin.Metadata;

@Metadata(mv = { 1, 1, 7 }, bv = { 1, 0, 2 }, k = 1, d1 = { "\u0000N\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\b\u0016\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0001¢\u0006\u0002\u0010\u0003JU\u0010\u0004\u001a\u0004\u0018\u00010\u00052\n\b\u0001\u0010\u0006\u001a\u0004\u0018\u00010\u00072\u001a\b\u0001\u0010\b\u001a\u0014\u0012\u000e\b\u0001\u0012\n \u000b*\u0004\u0018\u00010\n0\n\u0018\u00010\t2\n\b\u0001\u0010\f\u001a\u0004\u0018\u00010\r2\b\b\u0001\u0010\u000e\u001a\u00020\r2\n\b\u0001\u0010\u000f\u001a\u0004\u0018\u00010\u0010H\u0097\u0001Ja\u0010\u0004\u001a\u0004\u0018\u00010\u00052\n\b\u0001\u0010\u0011\u001a\u0004\u0018\u00010\u00122\n\b\u0001\u0010\u0006\u001a\u0004\u0018\u00010\u00072\u001a\b\u0001\u0010\b\u001a\u0014\u0012\u000e\b\u0001\u0012\n \u000b*\u0004\u0018\u00010\n0\n\u0018\u00010\t2\n\b\u0001\u0010\f\u001a\u0004\u0018\u00010\r2\b\b\u0001\u0010\u000e\u001a\u00020\r2\n\b\u0001\u0010\u000f\u001a\u0004\u0018\u00010\u0010H\u0097\u0001JU\u0010\u0004\u001a\u0004\u0018\u00010\u00052\n\b\u0001\u0010\u0011\u001a\u0004\u0018\u00010\u00122\u001a\b\u0001\u0010\b\u001a\u0014\u0012\u000e\b\u0001\u0012\n \u000b*\u0004\u0018\u00010\n0\n\u0018\u00010\t2\n\b\u0001\u0010\f\u001a\u0004\u0018\u00010\r2\b\b\u0001\u0010\u000e\u001a\u00020\r2\n\b\u0001\u0010\u000f\u001a\u0004\u0018\u00010\u0010H\u0097\u0001Ja\u0010\u0013\u001a\u0004\u0018\u00010\u00052\n\b\u0001\u0010\u0011\u001a\u0004\u0018\u00010\u00122\n\b\u0001\u0010\u0006\u001a\u0004\u0018\u00010\u00072\u001a\b\u0001\u0010\b\u001a\u0014\u0012\u000e\b\u0001\u0012\n \u000b*\u0004\u0018\u00010\n0\n\u0018\u00010\t2\n\b\u0001\u0010\f\u001a\u0004\u0018\u00010\r2\b\b\u0001\u0010\u000e\u001a\u00020\r2\n\b\u0001\u0010\u000f\u001a\u0004\u0018\u00010\u0010H\u0097\u0001JU\u0010\u0013\u001a\u0004\u0018\u00010\u00052\n\b\u0001\u0010\u0011\u001a\u0004\u0018\u00010\u00122\u001a\b\u0001\u0010\b\u001a\u0014\u0012\u000e\b\u0001\u0012\n \u000b*\u0004\u0018\u00010\n0\n\u0018\u00010\t2\n\b\u0001\u0010\f\u001a\u0004\u0018\u00010\r2\b\b\u0001\u0010\u000e\u001a\u00020\r2\n\b\u0001\u0010\u000f\u001a\u0004\u0018\u00010\u0010H\u0097\u0001J\u001f\u0010\u0014\u001a\u00020\u00152\b\b\u0001\u0010\u0006\u001a\u00020\u00072\n\b\u0001\u0010\u0016\u001a\u0004\u0018\u00010\u0017H\u0096\u0001J#\u0010\u0014\u001a\u0004\u0018\u00010\u00052\n\b\u0001\u0010\u0011\u001a\u0004\u0018\u00010\u00122\n\b\u0001\u0010\u0016\u001a\u0004\u0018\u00010\u0017H\u0097\u0001J\u001f\u0010\u0018\u001a\u00020\u00152\n\b\u0001\u0010\u0019\u001a\u0004\u0018\u00010\u00052\b\b\u0001\u0010\u001a\u001a\u00020\u001bH\u0096\u0001¨\u0006\u001c" }, d2 = { "Lcom/jetbrains/cidr/lang/daemon/OCAnnotatorSinkWrapper;", "Lcom/jetbrains/cidr/lang/daemon/OCAnnotatorSink;", "b", "(Lcom/jetbrains/cidr/lang/daemon/OCAnnotatorSink;)V", "addErrorAnnotation", "Lcom/intellij/lang/annotation/Annotation;", "range", "Lcom/intellij/openapi/util/TextRange;", "inspectionClass", "Ljava/lang/Class;", "Lcom/jetbrains/cidr/lang/inspections/OCInspection;", "kotlin.jvm.PlatformType", "clangID", "", "message", "highlightType", "Lcom/intellij/codeInspection/ProblemHighlightType;", "element", "Lcom/intellij/psi/PsiElement;", "addWarningAnnotation", "highlight", "", "key", "Lcom/intellij/openapi/editor/colors/TextAttributesKey;", "registerQuickFix", "annotation", "intentionAction", "Lcom/intellij/codeInsight/intention/IntentionAction;", "cidr-lang" })
public class OCAnnotatorSinkWrapper implements OCAnnotatorSink
{
    private final /* synthetic */ OCAnnotatorSink $$delegate_0;
    
    public OCAnnotatorSinkWrapper(@NotNull final OCAnnotatorSink $$delegate_0) {
        Intrinsics.checkParameterIsNotNull((Object)$$delegate_0, "b");
        this.$$delegate_0 = $$delegate_0;
    }
    
    @Nullable
    @Override
    public Annotation addErrorAnnotation(@Nullable final TextRange textRange, @Nullable final Class<? extends OCInspection> clazz, @Nullable final String s, @NotNull final String s2, @Nullable final ProblemHighlightType problemHighlightType) {
        Intrinsics.checkParameterIsNotNull((Object)s2, "message");
        return this.$$delegate_0.addErrorAnnotation(textRange, clazz, s, s2, problemHighlightType);
    }
    
    @Nullable
    @Override
    public Annotation addErrorAnnotation(@Nullable final PsiElement psiElement, @Nullable final TextRange textRange, @Nullable final Class<? extends OCInspection> clazz, @Nullable final String s, @NotNull final String s2, @Nullable final ProblemHighlightType problemHighlightType) {
        Intrinsics.checkParameterIsNotNull((Object)s2, "message");
        return this.$$delegate_0.addErrorAnnotation(psiElement, textRange, clazz, s, s2, problemHighlightType);
    }
    
    @Nullable
    @Override
    public Annotation addErrorAnnotation(@Nullable final PsiElement psiElement, @Nullable final Class<? extends OCInspection> clazz, @Nullable final String s, @NotNull final String s2, @Nullable final ProblemHighlightType problemHighlightType) {
        Intrinsics.checkParameterIsNotNull((Object)s2, "message");
        return this.$$delegate_0.addErrorAnnotation(psiElement, clazz, s, s2, problemHighlightType);
    }
    
    @Nullable
    @Override
    public Annotation addWarningAnnotation(@Nullable final PsiElement psiElement, @Nullable final TextRange textRange, @Nullable final Class<? extends OCInspection> clazz, @Nullable final String s, @NotNull final String s2, @Nullable final ProblemHighlightType problemHighlightType) {
        Intrinsics.checkParameterIsNotNull((Object)s2, "message");
        return this.$$delegate_0.addWarningAnnotation(psiElement, textRange, clazz, s, s2, problemHighlightType);
    }
    
    @Nullable
    @Override
    public Annotation addWarningAnnotation(@Nullable final PsiElement psiElement, @Nullable final Class<? extends OCInspection> clazz, @Nullable final String s, @NotNull final String s2, @Nullable final ProblemHighlightType problemHighlightType) {
        Intrinsics.checkParameterIsNotNull((Object)s2, "message");
        return this.$$delegate_0.addWarningAnnotation(psiElement, clazz, s, s2, problemHighlightType);
    }
    
    @Override
    public void highlight(@NotNull final TextRange textRange, @Nullable final TextAttributesKey textAttributesKey) {
        Intrinsics.checkParameterIsNotNull((Object)textRange, "range");
        this.$$delegate_0.highlight(textRange, textAttributesKey);
    }
    
    @Nullable
    @Override
    public Annotation highlight(@Nullable final PsiElement psiElement, @Nullable final TextAttributesKey textAttributesKey) {
        return this.$$delegate_0.highlight(psiElement, textAttributesKey);
    }
    
    @Override
    public void registerQuickFix(@Nullable final Annotation annotation, @NotNull final IntentionAction intentionAction) {
        Intrinsics.checkParameterIsNotNull((Object)intentionAction, "intentionAction");
        this.$$delegate_0.registerQuickFix(annotation, intentionAction);
    }
}
