// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.modulemap.psi;

import org.jetbrains.annotations.Nullable;

public interface ModuleMapHeaderDeclaration extends ModuleMapPsiElement
{
    @Nullable
    ModuleMapHeaderName getHeaderName();
    
    boolean isUmbrella();
    
    boolean isExclude();
    
    boolean isPrivate();
    
    boolean isTextual();
}
