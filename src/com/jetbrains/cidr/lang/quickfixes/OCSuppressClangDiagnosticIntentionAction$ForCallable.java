// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.lang.quickfixes;

import com.jetbrains.cidr.lang.util.OCElementUtil;
import com.jetbrains.cidr.lang.psi.OCFunctionDeclaration;
import com.jetbrains.cidr.lang.psi.OCMethod;
import org.jetbrains.annotations.NotNull;
import com.intellij.openapi.util.TextRange;
import com.intellij.psi.PsiFile;
import com.intellij.psi.PsiElement;
import com.jetbrains.cidr.lang.daemon.OCSuppressionGroup;
import org.jetbrains.annotations.Nullable;
import com.jetbrains.cidr.lang.psi.OCCallable;

public static class ForCallable extends OCSuppressClangDiagnosticIntentionAction
{
    private ForCallable(@Nullable final OCCallable ocCallable, @Nullable final OCSuppressionGroup ocSuppressionGroup) {
        super((PsiElement)ocCallable, ocSuppressionGroup, (ocCallable != null) ? ocCallable.getKind().toStringLowercase() : "", null);
    }
    
    public ForCallable(@Nullable final PsiFile psiFile, @NotNull final TextRange textRange, @Nullable final OCSuppressionGroup ocSuppressionGroup) {
        if (textRange == null) {
            throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "range", "com/jetbrains/cidr/lang/quickfixes/OCSuppressClangDiagnosticIntentionAction$ForCallable", "<init>"));
        }
        this((OCCallable)OCElementUtil.getParentOfType(psiFile, textRange, OCMethod.class, OCFunctionDeclaration.class), ocSuppressionGroup);
    }
}
