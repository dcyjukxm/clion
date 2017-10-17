// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.lang.lexer;

import java.io.Reader;

class OCLexer$1 extends _OCLexer {
    @Override
    public void reset(final CharSequence charSequence, final int n, final int n2, final int n3) {
        final boolean b = n3 == 1024;
        super.reset(charSequence, n, n2, b ? 0 : n3);
        this.myLogicalLineStartOffset = (b ? -1 : n);
    }
}