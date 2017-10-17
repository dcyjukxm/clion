// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.lang.refactoring.rename;

import com.intellij.psi.PsiFileSystemItem;

@FunctionalInterface
public interface VariantProcessor
{
    void processVariant(final PsiFileSystemItem p0, final String p1);
}
