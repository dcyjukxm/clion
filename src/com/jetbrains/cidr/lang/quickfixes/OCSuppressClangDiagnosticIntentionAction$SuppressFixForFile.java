// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.lang.quickfixes;

import com.intellij.codeInspection.ProblemDescriptor;
import com.intellij.openapi.project.Project;
import org.jetbrains.annotations.NotNull;
import com.jetbrains.cidr.lang.util.OCElementUtil;
import com.jetbrains.cidr.lang.psi.OCFile;
import com.intellij.psi.PsiElement;
import org.jetbrains.annotations.Nullable;
import com.jetbrains.cidr.lang.daemon.OCSuppressionGroup;

public static class SuppressFixForFile extends SuppressFix
{
    public SuppressFixForFile(@Nullable final OCSuppressionGroup ocSuppressionGroup) {
        super(ocSuppressionGroup);
    }
    
    @NotNull
    @Override
    protected OCSuppressClangDiagnosticIntentionAction createFix(final PsiElement psiElement) {
        ForFile forFile;
        try {
            forFile = new ForFile((OCFile)psiElement.getContainingFile(), OCElementUtil.getRangeWithMacros(psiElement), this.mySuppressionGroup);
            if (forFile == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/quickfixes/OCSuppressClangDiagnosticIntentionAction$SuppressFixForFile", "createFix"));
            }
        }
        catch (IllegalStateException ex) {
            throw a(ex);
        }
        return forFile;
    }
    
    @NotNull
    public String getName() {
        String s;
        try {
            s = "Suppress for file";
            if (s == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/quickfixes/OCSuppressClangDiagnosticIntentionAction$SuppressFixForFile", "getName"));
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
