// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.lang.lexer;

import com.intellij.lexer.FlexLexer;
import java.io.Reader;
import com.intellij.lexer.FlexAdapter;

public class OCStringsLexer extends FlexAdapter
{
    public OCStringsLexer() {
        this(false);
    }
    
    public OCStringsLexer(final boolean b) {
        super((FlexLexer)new _OCStringsLexer(null));
        final _OCStringsLexer ocStringsLexer = (_OCStringsLexer)this.getFlex();
        if (b) {
            ocStringsLexer.forHighlighting();
        }
    }
}
