// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.lang.asm;

import com.intellij.lexer.FlexLexer;
import java.io.Reader;
import com.intellij.lexer.FlexAdapter;

public class AsmLexer extends FlexAdapter
{
    public AsmLexer() {
        super((FlexLexer)new _AsmLexer(null));
    }
}
