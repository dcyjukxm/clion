// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.lang.editor.typing;

import com.intellij.psi.tree.TokenSet;
import com.jetbrains.cidr.lang.parser.OCTokenTypes;
import com.intellij.psi.tree.IElementType;
import org.jetbrains.annotations.NotNull;
import com.intellij.openapi.editor.highlighter.HighlighterIterator;

public class OCHighlightingTokenIterator implements OCBackwardStructureDetector.TokenIterator
{
    @NotNull
    private final HighlighterIterator myIterator;
    
    public OCHighlightingTokenIterator(@NotNull final HighlighterIterator myIterator) {
        if (myIterator == null) {
            throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "iterator", "com/jetbrains/cidr/lang/editor/typing/OCHighlightingTokenIterator", "<init>"));
        }
        this.myIterator = myIterator;
    }
    
    @Override
    public IElementType getTokenType() {
        return this.myIterator.getTokenType();
    }
    
    @Override
    public boolean atEnd() {
        return this.myIterator.atEnd();
    }
    
    @Override
    public void retreat() {
        this.myIterator.retreat();
    }
    
    @Override
    public void skipWhitespaces() {
        while (true) {
            Label_0037: {
                try {
                    if (this.myIterator.atEnd()) {
                        return;
                    }
                    final TokenSet set = OCTokenTypes.WHITE_SPACE_OR_COMMENT_BIT_SET;
                    final OCHighlightingTokenIterator ocHighlightingTokenIterator = this;
                    final HighlighterIterator highlighterIterator = ocHighlightingTokenIterator.myIterator;
                    final IElementType elementType = highlighterIterator.getTokenType();
                    final boolean b = set.contains(elementType);
                    if (b) {
                        break Label_0037;
                    }
                    return;
                }
                catch (IllegalArgumentException ex) {
                    throw a(ex);
                }
                try {
                    final TokenSet set = OCTokenTypes.WHITE_SPACE_OR_COMMENT_BIT_SET;
                    final OCHighlightingTokenIterator ocHighlightingTokenIterator = this;
                    final HighlighterIterator highlighterIterator = ocHighlightingTokenIterator.myIterator;
                    final IElementType elementType = highlighterIterator.getTokenType();
                    final boolean b = set.contains(elementType);
                    if (b) {
                        this.myIterator.retreat();
                        continue;
                    }
                }
                catch (IllegalArgumentException ex2) {
                    throw a(ex2);
                }
            }
            break;
        }
    }
    
    @Override
    public Marker mark() {
        return new MyMarker(this.myIterator.getStart());
    }
    
    private static IllegalArgumentException a(final IllegalArgumentException ex) {
        return ex;
    }
    
    private class MyMarker implements Marker
    {
        private final int myStart;
        
        public MyMarker(final int myStart) {
            this.myStart = myStart;
        }
        
        @Override
        public void rollback() {
            if (OCHighlightingTokenIterator.this.myIterator.atEnd()) {
                OCHighlightingTokenIterator.this.myIterator.advance();
                if (OCHighlightingTokenIterator.this.myIterator.atEnd()) {
                    OCHighlightingTokenIterator.this.myIterator.retreat();
                    OCHighlightingTokenIterator.this.myIterator.retreat();
                }
            }
            while (this.myStart > OCHighlightingTokenIterator.this.myIterator.getStart()) {
                OCHighlightingTokenIterator.this.myIterator.advance();
            }
            while (this.myStart < OCHighlightingTokenIterator.this.myIterator.getStart()) {
                OCHighlightingTokenIterator.this.myIterator.retreat();
            }
        }
    }
}
