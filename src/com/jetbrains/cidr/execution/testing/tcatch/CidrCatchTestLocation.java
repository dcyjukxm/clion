// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.execution.testing.tcatch;

import org.jetbrains.annotations.NotNull;
import com.intellij.openapi.project.Project;
import com.intellij.psi.PsiElement;
import com.intellij.execution.PsiLocation;

public class CidrCatchTestLocation extends PsiLocation<PsiElement>
{
    private final CidrCatchTestCache myTestInfo;
    
    public CidrCatchTestLocation(@NotNull final Project project, @NotNull final CidrCatchTestCache myTestInfo) {
        if (project == null) {
            throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "project", "com/jetbrains/cidr/execution/testing/tcatch/CidrCatchTestLocation", "<init>"));
        }
        if (myTestInfo == null) {
            throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "testInfo", "com/jetbrains/cidr/execution/testing/tcatch/CidrCatchTestLocation", "<init>"));
        }
        super(project, (PsiElement)myTestInfo.getDeclarator());
        this.myTestInfo = myTestInfo;
    }
    
    @NotNull
    public CidrCatchTestCache getTestInfo() {
        CidrCatchTestCache myTestInfo;
        try {
            myTestInfo = this.myTestInfo;
            if (myTestInfo == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/execution/testing/tcatch/CidrCatchTestLocation", "getTestInfo"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        return myTestInfo;
    }
    
    private static IllegalArgumentException a(final IllegalArgumentException ex) {
        return ex;
    }
}
