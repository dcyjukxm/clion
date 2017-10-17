// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.modulemap.psi;

import org.jetbrains.annotations.Nullable;

public interface ModuleMapConfigMacrosDeclaration extends ModuleMapPsiElement
{
    @Nullable
    ModuleMapAttributes getAttributes();
    
    @Nullable
    ModuleMapConfigMacroList getConfigMacroList();
}
