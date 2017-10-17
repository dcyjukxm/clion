// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.cpp.cmake;

import com.intellij.lang.Language;

public class CMakeLanguage extends Language
{
    public static final CMakeLanguage INSTANCE;
    
    protected CMakeLanguage() {
        super("CMake");
    }
    
    public static CMakeLanguage getInstance() {
        return CMakeLanguage.INSTANCE;
    }
    
    static {
        INSTANCE = new CMakeLanguage();
    }
}
