// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.cpp.cmake.cmakecache.psi;

import com.intellij.lang.Language;
import com.jetbrains.cidr.cpp.cmake.cmakecache.CMakeCacheLanguage;
import com.intellij.psi.tree.IFileElementType;

public class CMakeCacheFileElementType extends IFileElementType
{
    public CMakeCacheFileElementType() {
        super((Language)CMakeCacheLanguage.getInstance());
    }
}
