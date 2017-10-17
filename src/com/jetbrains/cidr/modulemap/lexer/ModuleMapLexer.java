// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.modulemap.lexer;

import com.intellij.lexer.Lexer;
import com.intellij.lexer.MergingLexerAdapter;
import com.intellij.psi.tree.TokenSet;
import com.jetbrains.cidr.modulemap.ModuleMapParserTypes;
import com.intellij.psi.TokenType;
import com.intellij.psi.tree.IElementType;
import com.intellij.lexer.FlexLexer;
import com.intellij.lexer.FlexAdapter;
import com.intellij.lexer.LookAheadLexer;

public class ModuleMapLexer extends LookAheadLexer
{
    public ModuleMapLexer() {
        super((Lexer)new MergingLexerAdapter((Lexer)new FlexAdapter((FlexLexer)new _ModuleMapLexer() {
            @Override
            public void reset(final CharSequence charSequence, final int n, final int n2, final int n3) {
                this.onReset();
                super.reset(charSequence, n, n2, n3);
            }
        }), TokenSet.create(new IElementType[] { TokenType.WHITE_SPACE, ModuleMapParserTypes.EOL_COMMENT, ModuleMapParserTypes.BLOCK_COMMENT })));
    }
}
