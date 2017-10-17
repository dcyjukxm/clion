// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.modulemap.psi;

import org.jetbrains.annotations.Nullable;
import org.jetbrains.annotations.NotNull;

public interface ModuleMapAttributes extends ModuleMapPsiElement
{
    @NotNull
    ModuleMapAttribute getAttribute();
    
    @Nullable
    ModuleMapAttributes getAttributes();
}
