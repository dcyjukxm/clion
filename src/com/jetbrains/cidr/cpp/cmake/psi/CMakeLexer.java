// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.cpp.cmake.psi;

import com.intellij.lexer.FlexLexer;
import com.intellij.lexer.FlexAdapter;

public class CMakeLexer extends FlexAdapter
{
    public CMakeLexer() {
        super((FlexLexer)new _CMakeLexer() {
            @Override
            public void reset(final CharSequence charSequence, final int n, final int n2, final int n3) {
                this.onReset();
                super.reset(charSequence, n, n2, n3);
            }
        });
    }
}
