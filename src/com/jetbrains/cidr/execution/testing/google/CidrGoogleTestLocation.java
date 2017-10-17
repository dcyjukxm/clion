// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.execution.testing.google;

import org.jetbrains.annotations.Nullable;
import org.jetbrains.annotations.NotNull;
import com.intellij.openapi.project.Project;
import com.intellij.psi.PsiElement;
import com.intellij.execution.PsiLocation;

public class CidrGoogleTestLocation extends PsiLocation<PsiElement>
{
    private final String myParam;
    private final String myInstantiation;
    private final boolean mySuiteOnly;
    private final boolean myTyped;
    
    public CidrGoogleTestLocation(@NotNull final Project project, @NotNull final PsiElement psiElement, @Nullable final String myParam, @Nullable final String myInstantiation, final boolean mySuiteOnly, final boolean myTyped) {
        if (project == null) {
            throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "project", "com/jetbrains/cidr/execution/testing/google/CidrGoogleTestLocation", "<init>"));
        }
        if (psiElement == null) {
            throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "psiElement", "com/jetbrains/cidr/execution/testing/google/CidrGoogleTestLocation", "<init>"));
        }
        super(project, psiElement);
        this.myParam = myParam;
        this.myInstantiation = myInstantiation;
        this.mySuiteOnly = mySuiteOnly;
        this.myTyped = myTyped;
    }
    
    public String getParam() {
        return this.myParam;
    }
    
    public String getInstantiation() {
        return this.myInstantiation;
    }
    
    public boolean isSuiteOnly() {
        return this.mySuiteOnly;
    }
    
    public boolean isTyped() {
        return this.myTyped;
    }
}
