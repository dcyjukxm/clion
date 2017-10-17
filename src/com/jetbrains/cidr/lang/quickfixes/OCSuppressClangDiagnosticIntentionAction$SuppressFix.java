// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.lang.quickfixes;

import com.intellij.codeInspection.CommonProblemDescriptor;
import com.intellij.codeInspection.ProblemDescriptor;
import com.intellij.openapi.editor.Editor;
import com.intellij.openapi.project.Project;
import org.jetbrains.annotations.NotNull;
import com.intellij.psi.PsiElement;
import org.jetbrains.annotations.Nullable;
import com.jetbrains.cidr.lang.daemon.OCSuppressionGroup;
import com.intellij.codeInspection.SuppressQuickFix;

private abstract static class SuppressFix implements SuppressQuickFix
{
    @Nullable
    protected OCSuppressionGroup mySuppressionGroup;
    
    protected SuppressFix(@Nullable final OCSuppressionGroup mySuppressionGroup) {
        this.mySuppressionGroup = mySuppressionGroup;
    }
    
    @NotNull
    protected abstract OCSuppressClangDiagnosticIntentionAction createFix(final PsiElement p0);
    
    public boolean isAvailable(@NotNull final Project project, @NotNull final PsiElement psiElement) {
        try {
            if (project == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "project", "com/jetbrains/cidr/lang/quickfixes/OCSuppressClangDiagnosticIntentionAction$SuppressFix", "isAvailable"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        try {
            if (psiElement == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "context", "com/jetbrains/cidr/lang/quickfixes/OCSuppressClangDiagnosticIntentionAction$SuppressFix", "isAvailable"));
            }
        }
        catch (IllegalArgumentException ex2) {
            throw a(ex2);
        }
        return this.createFix(psiElement).isAvailable(project, null, psiElement);
    }
    
    @NotNull
    public String getFamilyName() {
        String name;
        try {
            name = this.getName();
            if (name == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/quickfixes/OCSuppressClangDiagnosticIntentionAction$SuppressFix", "getFamilyName"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        return name;
    }
    
    public void applyFix(@NotNull final Project project, @NotNull final ProblemDescriptor problemDescriptor) {
        try {
            if (project == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "project", "com/jetbrains/cidr/lang/quickfixes/OCSuppressClangDiagnosticIntentionAction$SuppressFix", "applyFix"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        try {
            if (problemDescriptor == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "descriptor", "com/jetbrains/cidr/lang/quickfixes/OCSuppressClangDiagnosticIntentionAction$SuppressFix", "applyFix"));
            }
        }
        catch (IllegalArgumentException ex2) {
            throw a(ex2);
        }
        this.createFix(problemDescriptor.getPsiElement()).invoke(project, null, problemDescriptor.getPsiElement());
    }
    
    public boolean isSuppressAll() {
        return false;
    }
    
    private static IllegalArgumentException a(final IllegalArgumentException ex) {
        return ex;
    }
}
