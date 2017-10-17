// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.execution.debugger.backend.lang.lexer;

import com.intellij.lexer.FlexLexer;
import java.io.Reader;
import com.intellij.lexer.FlexAdapter;

public class GDBLexer extends FlexAdapter
{
    public GDBLexer() {
        super((FlexLexer)new _GDBLexer(null));
    }
}
