// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.cpp.cmake.psi;

import com.intellij.lang.Language;
import com.jetbrains.cidr.cpp.cmake.CMakeLanguage;
import com.intellij.psi.tree.IFileElementType;

public class CMakeFileElementType extends IFileElementType
{
    public CMakeFileElementType() {
        super((Language)CMakeLanguage.getInstance());
    }
}
