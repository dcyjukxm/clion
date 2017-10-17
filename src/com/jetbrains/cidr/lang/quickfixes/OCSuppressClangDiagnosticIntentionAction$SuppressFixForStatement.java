// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.lang.quickfixes;

import com.intellij.codeInspection.ProblemDescriptor;
import com.intellij.openapi.project.Project;
import org.jetbrains.annotations.NotNull;
import com.jetbrains.cidr.lang.util.OCElementUtil;
import com.intellij.psi.PsiElement;
import org.jetbrains.annotations.Nullable;
import com.jetbrains.cidr.lang.daemon.OCSuppressionGroup;

public static class SuppressFixForStatement extends SuppressFix
{
    public SuppressFixForStatement(@Nullable final OCSuppressionGroup ocSuppressionGroup) {
        super(ocSuppressionGroup);
    }
    
    @NotNull
    @Override
    protected OCSuppressClangDiagnosticIntentionAction createFix(final PsiElement psiElement) {
        ForStatement forStatement;
        try {
            forStatement = new ForStatement(psiElement.getContainingFile(), OCElementUtil.getRangeWithMacros(psiElement), this.mySuppressionGroup);
            if (forStatement == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/quickfixes/OCSuppressClangDiagnosticIntentionAction$SuppressFixForStatement", "createFix"));
            }
        }
        catch (IllegalStateException ex) {
            throw a(ex);
        }
        return forStatement;
    }
    
    @NotNull
    public String getName() {
        String s;
        try {
            s = "Suppress for statement";
            if (s == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/quickfixes/OCSuppressClangDiagnosticIntentionAction$SuppressFixForStatement", "getName"));
            }
        }
        catch (IllegalStateException ex) {
            throw a(ex);
        }
        return s;
    }
    
    private static IllegalStateException a(final IllegalStateException ex) {
        return ex;
    }
}
