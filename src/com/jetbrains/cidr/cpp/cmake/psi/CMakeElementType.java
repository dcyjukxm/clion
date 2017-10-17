// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.cpp.cmake.psi;

import com.intellij.lang.Language;
import com.jetbrains.cidr.cpp.cmake.CMakeLanguage;
import org.jetbrains.annotations.NotNull;
import com.intellij.psi.tree.IElementType;

public class CMakeElementType extends IElementType
{
    public CMakeElementType(@NotNull final String s) {
        if (s == null) {
            throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "debugName", "com/jetbrains/cidr/cpp/cmake/psi/CMakeElementType", "<init>"));
        }
        super(s, (Language)CMakeLanguage.getInstance());
    }
}
