// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.execution.testing.google;

import org.jetbrains.annotations.Nullable;
import org.jetbrains.annotations.NotNull;
import com.intellij.psi.PsiElement;

class CidrGoogleTestObject
{
    public final KIND kind;
    @NotNull
    public final PsiElement element;
    @Nullable
    public final String instantiation;
    @Nullable
    public final String param;
    
    public CidrGoogleTestObject(final KIND kind, @NotNull final PsiElement element, @Nullable final String instantiation, @Nullable final String param) {
        if (element == null) {
            throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "element", "com/jetbrains/cidr/execution/testing/google/CidrGoogleTestObject", "<init>"));
        }
        this.kind = kind;
        this.element = element;
        this.instantiation = instantiation;
        this.param = param;
    }
    
    public CidrGoogleTestObject(final KIND kind, @NotNull final PsiElement psiElement) {
        if (psiElement == null) {
            throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "element", "com/jetbrains/cidr/execution/testing/google/CidrGoogleTestObject", "<init>"));
        }
        this(kind, psiElement, null, null);
    }
    
    enum KIND
    {
        FILE, 
        SUITE, 
        TEST;
    }
}
