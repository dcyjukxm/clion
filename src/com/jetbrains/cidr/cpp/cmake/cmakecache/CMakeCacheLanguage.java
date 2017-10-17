// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.cpp.cmake.cmakecache;

import com.intellij.lang.Language;

public class CMakeCacheLanguage extends Language
{
    public static final CMakeCacheLanguage INSTANCE;
    
    protected CMakeCacheLanguage() {
        super("CMakeCache");
    }
    
    public static CMakeCacheLanguage getInstance() {
        return CMakeCacheLanguage.INSTANCE;
    }
    
    static {
        INSTANCE = new CMakeCacheLanguage();
    }
}
