// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.lang.quickfixes;

import com.jetbrains.cidr.lang.util.OCElementUtil;
import com.jetbrains.cidr.lang.psi.OCDeclaration;
import com.jetbrains.cidr.lang.psi.OCStatement;
import org.jetbrains.annotations.NotNull;
import com.intellij.openapi.util.TextRange;
import com.intellij.psi.PsiFile;
import com.intellij.psi.PsiElement;
import com.jetbrains.cidr.lang.psi.OCFunctionDeclaration;
import com.jetbrains.cidr.lang.daemon.OCSuppressionGroup;
import org.jetbrains.annotations.Nullable;
import com.jetbrains.cidr.lang.psi.OCElement;

public static class ForStatement extends OCSuppressClangDiagnosticIntentionAction
{
    private ForStatement(@Nullable final OCElement ocElement, @Nullable final OCSuppressionGroup ocSuppressionGroup) {
        super((PsiElement)((ocElement != null && !(ocElement instanceof OCFunctionDeclaration)) ? ocElement : null), ocSuppressionGroup, "statement", null);
    }
    
    public ForStatement(@Nullable final PsiFile psiFile, @NotNull final TextRange textRange, @Nullable final OCSuppressionGroup ocSuppressionGroup) {
        if (textRange == null) {
            throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "range", "com/jetbrains/cidr/lang/quickfixes/OCSuppressClangDiagnosticIntentionAction$ForStatement", "<init>"));
        }
        this((OCElement)OCElementUtil.getParentOfType(psiFile, textRange, OCStatement.class, OCDeclaration.class), ocSuppressionGroup);
    }
}
