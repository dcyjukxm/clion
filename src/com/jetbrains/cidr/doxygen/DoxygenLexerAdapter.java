// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.doxygen;

import com.intellij.lexer.FlexLexer;
import com.jetbrains.cidr.doxygen.lexer._DoxygenLexer;
import java.io.Reader;
import com.intellij.lexer.FlexAdapter;

public class DoxygenLexerAdapter extends FlexAdapter
{
    public DoxygenLexerAdapter() {
        super((FlexLexer)new _DoxygenLexer(null));
    }
}
