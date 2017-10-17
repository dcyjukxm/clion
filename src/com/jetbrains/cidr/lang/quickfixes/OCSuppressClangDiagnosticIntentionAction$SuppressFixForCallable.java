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

public static class SuppressFixForCallable extends SuppressFix
{
    public SuppressFixForCallable(@Nullable final OCSuppressionGroup ocSuppressionGroup) {
        super(ocSuppressionGroup);
    }
    
    @NotNull
    @Override
    protected OCSuppressClangDiagnosticIntentionAction createFix(final PsiElement psiElement) {
        ForCallable forCallable;
        try {
            forCallable = new ForCallable(psiElement.getContainingFile(), OCElementUtil.getRangeWithMacros(psiElement), this.mySuppressionGroup);
            if (forCallable == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/quickfixes/OCSuppressClangDiagnosticIntentionAction$SuppressFixForCallable", "createFix"));
            }
        }
        catch (IllegalStateException ex) {
            throw a(ex);
        }
        return forCallable;
    }
    
    @NotNull
    public String getName() {
        String s;
        try {
            s = "Suppress for method/function";
            if (s == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/quickfixes/OCSuppressClangDiagnosticIntentionAction$SuppressFixForCallable", "getName"));
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
