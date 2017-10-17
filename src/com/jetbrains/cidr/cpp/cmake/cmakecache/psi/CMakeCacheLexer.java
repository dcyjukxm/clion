// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.cpp.cmake.cmakecache.psi;

import com.intellij.lexer.FlexLexer;
import com.intellij.lexer.FlexAdapter;

public class CMakeCacheLexer extends FlexAdapter
{
    public CMakeCacheLexer() {
        super((FlexLexer)new _CMakeCacheLexer());
    }
}
