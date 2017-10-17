// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.lang.editor;

import com.jetbrains.cidr.lang.parser.OCTokenTypes;
import com.intellij.psi.impl.cache.impl.OccurrenceConsumer;
import com.intellij.lexer.Lexer;
import com.intellij.psi.impl.cache.impl.BaseFilterLexer;

class OCTodoIndexer$1 extends BaseFilterLexer {
    public void advance() {
        if (OCTokenTypes.COMMENTS.contains(this.myDelegate.getTokenType())) {
            this.scanWordsInToken(2, false, false);
            this.advanceTodoItemCountsInToken();
        }
        this.getDelegate().advance();
    }
}