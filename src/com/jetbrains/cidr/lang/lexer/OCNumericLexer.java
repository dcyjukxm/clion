// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.lang.lexer;

import com.intellij.lexer.FlexLexer;
import java.io.Reader;
import com.intellij.lexer.FlexAdapter;

public class OCNumericLexer extends FlexAdapter
{
    public OCNumericLexer() {
        super((FlexLexer)new _OCNumeric(null));
    }
}
