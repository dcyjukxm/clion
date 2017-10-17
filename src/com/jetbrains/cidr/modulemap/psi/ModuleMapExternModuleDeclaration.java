// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.modulemap.psi;

import com.intellij.psi.PsiElement;
import org.jetbrains.annotations.Nullable;

public interface ModuleMapExternModuleDeclaration extends ModuleMapPsiElement
{
    @Nullable
    ModuleMapModuleId getModuleId();
    
    @Nullable
    PsiElement getFileName();
}
