// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.lang.util;

import com.jetbrains.cidr.lang.preprocessor.OCMacroForeignLeafElement;
import com.intellij.psi.tree.IElementType;
import com.intellij.psi.tree.TokenSet;
import com.jetbrains.cidr.lang.parser.OCTokenTypes;
import com.jetbrains.cidr.lang.OCLanguageKind;
import org.jetbrains.annotations.NotNull;
import com.jetbrains.cidr.lang.psi.OCExpression;
import com.jetbrains.cidr.lang.lexer.OCHighlightingLexer;
import com.intellij.lang.ASTNode;

private static class StrFragmentIterator
{
    public static final char END_MARKER = '\0';
    private ASTNode child;
    private int pos;
    private int endPos;
    private boolean BOF;
    private boolean hasStringLiteral;
    private char cur;
    private char prev;
    private char prevPrev;
    private final OCHighlightingLexer lexer;
    
    public StrFragmentIterator(@NotNull final OCExpression ocExpression) {
        if (ocExpression == null) {
            throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "expression", "com/jetbrains/cidr/lang/util/OCFormatSpecifiersUtil$StrFragmentIterator", "<init>"));
        }
        this.pos = -1;
        this.endPos = -1;
        this.BOF = true;
        this.hasStringLiteral = false;
        this.cur = '\0';
        this.prev = '\0';
        this.prevPrev = '\0';
        this.lexer = new OCHighlightingLexer(OCLanguageKind.OBJ_CPP, false, false, false);
        this.child = ocExpression.getNode().getFirstChildNode();
    }
    
    public boolean wasEmpty() {
        try {
            if (!this.hasStringLiteral) {
                return true;
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        return false;
    }
    
    private boolean a() {
        Label_0121: {
            boolean hasStringLiteral = false;
            boolean b3 = false;
            Label_0117: {
                Label_0096: {
                    while (true) {
                        Label_0021: {
                            try {
                                if (this.child == null) {
                                    break Label_0121;
                                }
                                final StrFragmentIterator strFragmentIterator = this;
                                final boolean b = strFragmentIterator.BOF;
                                if (b) {
                                    break Label_0021;
                                }
                                break Label_0021;
                            }
                            catch (IllegalArgumentException ex) {
                                throw a(ex);
                            }
                            try {
                                final StrFragmentIterator strFragmentIterator = this;
                                final boolean b = strFragmentIterator.BOF;
                                if (b) {
                                    this.BOF = false;
                                    break Label_0046;
                                }
                            }
                            catch (IllegalArgumentException ex2) {
                                throw a(ex2);
                            }
                        }
                        this.child = this.child.getTreeNext();
                        Label_0076: {
                            try {
                                if (this.child == null) {
                                    break Label_0096;
                                }
                                final TokenSet set = OCTokenTypes.ALL_STRINGS;
                                final StrFragmentIterator strFragmentIterator2 = this;
                                final ASTNode astNode = strFragmentIterator2.child;
                                final IElementType elementType = OCElementUtil.getElementType(astNode);
                                final boolean b2 = set.contains(elementType);
                                if (!b2) {
                                    break Label_0076;
                                }
                                break Label_0096;
                            }
                            catch (IllegalArgumentException ex3) {
                                throw a(ex3);
                            }
                            try {
                                final TokenSet set = OCTokenTypes.ALL_STRINGS;
                                final StrFragmentIterator strFragmentIterator2 = this;
                                final ASTNode astNode = strFragmentIterator2.child;
                                final IElementType elementType = OCElementUtil.getElementType(astNode);
                                final boolean b2 = set.contains(elementType);
                                if (!b2) {
                                    this.child = this.child.getTreeNext();
                                    continue;
                                }
                            }
                            catch (IllegalArgumentException ex4) {
                                throw a(ex4);
                            }
                        }
                        break;
                    }
                    try {
                        hasStringLiteral = this.hasStringLiteral;
                        if (this.child != null) {
                            b3 = true;
                            break Label_0117;
                        }
                    }
                    catch (IllegalArgumentException ex5) {
                        throw a(ex5);
                    }
                }
                b3 = false;
            }
            this.hasStringLiteral = (hasStringLiteral | b3);
            try {
                if (this.child != null) {
                    return true;
                }
            }
            catch (IllegalArgumentException ex6) {
                throw a(ex6);
            }
        }
        return false;
    }
    
    private void b() {
        try {
            this.lexer.start((CharSequence)this.child.getText());
            if (OCTokenTypes.RAW_STRING_LITERALS.contains(OCElementUtil.getElementType(this.child))) {
                this.lexer.advance();
                this.lexer.advance();
                this.lexer.advance();
                this.pos = this.lexer.getTokenStart();
                this.endPos = this.lexer.getTokenEnd();
                return;
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        try {
            if (OCHighlightingLexer.PREFIX_TYPE == this.lexer.getTokenType()) {
                this.lexer.advance();
            }
        }
        catch (IllegalArgumentException ex2) {
            throw a(ex2);
        }
        this.pos = this.lexer.getTokenStart() + 1;
        this.endPos = this.lexer.getBufferEnd() - 1;
    }
    
    public int getPos() {
        Label_0024: {
            try {
                if (this.child == null) {
                    break Label_0024;
                }
                final StrFragmentIterator strFragmentIterator = this;
                final ASTNode astNode = strFragmentIterator.child;
                final boolean b = astNode instanceof OCMacroForeignLeafElement;
                if (b) {
                    break Label_0024;
                }
                return this.child.getStartOffset() + this.pos;
            }
            catch (IllegalArgumentException ex) {
                throw a(ex);
            }
            try {
                final StrFragmentIterator strFragmentIterator = this;
                final ASTNode astNode = strFragmentIterator.child;
                final boolean b = astNode instanceof OCMacroForeignLeafElement;
                if (b) {
                    return -1;
                }
            }
            catch (IllegalArgumentException ex2) {
                throw a(ex2);
            }
        }
        return this.child.getStartOffset() + this.pos;
    }
    
    public char getPrevChar() {
        return this.prev;
    }
    
    public char getPrevPrevChar() {
        return this.prevPrev;
    }
    
    public char getNextChar() {
        while (true) {
            while (true) {
                try {
                    this.prevPrev = this.prev;
                    this.prev = this.cur;
                    if (this.pos == -1) {
                        break Label_0044;
                    }
                    final StrFragmentIterator strFragmentIterator = this;
                    final int n = strFragmentIterator.pos;
                    final int n2 = 1;
                    final int n3 = n + n2;
                    final StrFragmentIterator strFragmentIterator2 = this;
                    final int n4 = strFragmentIterator2.endPos;
                    if (n3 >= n4) {
                        break Label_0044;
                    }
                    return this.cur = this.lexer.getBufferSequence().charAt(++this.pos);
                }
                catch (IllegalArgumentException ex) {
                    throw a(ex);
                }
                try {
                    final StrFragmentIterator strFragmentIterator = this;
                    final int n = strFragmentIterator.pos;
                    final int n2 = 1;
                    final int n3 = n + n2;
                    final StrFragmentIterator strFragmentIterator2 = this;
                    final int n4 = strFragmentIterator2.endPos;
                    if (n3 < n4) {
                        return this.cur = this.lexer.getBufferSequence().charAt(++this.pos);
                    }
                    if (!this.a()) {
                        return '\0';
                    }
                }
                catch (IllegalArgumentException ex2) {
                    throw a(ex2);
                }
                this.b();
                if (this.pos < this.endPos) {
                    return this.cur = this.lexer.getBufferSequence().charAt(this.pos);
                }
                continue;
            }
        }
    }
    
    private static IllegalArgumentException a(final IllegalArgumentException ex) {
        return ex;
    }
}
