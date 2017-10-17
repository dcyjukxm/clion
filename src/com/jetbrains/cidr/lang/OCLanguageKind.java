// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.lang;

import org.jetbrains.annotations.NotNull;

public interface OCLanguageKind
{
    public static final OCLanguageKind C = CLanguageKind.C;
    public static final OCLanguageKind CPP = CLanguageKind.CPP;
    public static final OCLanguageKind OBJ_C = CLanguageKind.OBJ_C;
    public static final OCLanguageKind OBJ_CPP = CLanguageKind.OBJ_CPP;
    
    boolean isCpp();
    
    boolean isObjC();
    
    boolean conforms(@NotNull final OCLanguageKind p0);
    
    @NotNull
    String getDefaultSourceExtension();
    
    @NotNull
    String getDisplayName();
    
    boolean supportsPrecompiledHeaders();
}
