// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.lang.documentation.doxygen.api;

import com.intellij.psi.PsiComment;
import java.util.List;
import org.jetbrains.annotations.NotNull;
import com.intellij.psi.PsiElement;
import com.intellij.openapi.extensions.ExtensionPointName;

public abstract class Doxygen
{
    public static final ExtensionPointName<Doxygen> EP_NAME;
    
    public abstract DoxygenCommentGroup getCommentGroup(@NotNull final PsiElement p0);
    
    @NotNull
    public abstract List<PsiComment> getCommentScope(@NotNull final PsiElement p0);
    
    public abstract boolean isDoxygenComment(@NotNull final PsiComment p0);
    
    static {
        EP_NAME = ExtensionPointName.create("cidr.lang.doxygenExtension");
    }
}
