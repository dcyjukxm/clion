// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.lang.editor.typing;

import org.jetbrains.annotations.NotNull;
import com.intellij.psi.tree.IElementType;

public class OCBackwardBlockDetector extends OCBackwardStructureDetector
{
    @NotNull
    private final IElementType myRight;
    @NotNull
    private final IElementType myLeft;
    
    public OCBackwardBlockDetector(@NotNull final IElementType myLeft, @NotNull final IElementType myRight) {
        if (myLeft == null) {
            throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "left", "com/jetbrains/cidr/lang/editor/typing/OCBackwardBlockDetector", "<init>"));
        }
        if (myRight == null) {
            throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "right", "com/jetbrains/cidr/lang/editor/typing/OCBackwardBlockDetector", "<init>"));
        }
        this.myRight = myRight;
        this.myLeft = myLeft;
    }
    
    @Override
    public boolean next(@NotNull final TokenIterator tokenIterator) {
        try {
            if (tokenIterator == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "it", "com/jetbrains/cidr/lang/editor/typing/OCBackwardBlockDetector", "next"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw b(ex);
        }
        Label_0085: {
            try {
                tokenIterator.skipWhitespaces();
                if (tokenIterator.atEnd()) {
                    return false;
                }
                final TokenIterator tokenIterator2 = tokenIterator;
                final IElementType elementType = tokenIterator2.getTokenType();
                final OCBackwardBlockDetector ocBackwardBlockDetector = this;
                final IElementType elementType2 = ocBackwardBlockDetector.myRight;
                if (elementType != elementType2) {
                    return false;
                }
                break Label_0085;
            }
            catch (IllegalArgumentException ex2) {
                throw b(ex2);
            }
            try {
                final TokenIterator tokenIterator2 = tokenIterator;
                final IElementType elementType = tokenIterator2.getTokenType();
                final OCBackwardBlockDetector ocBackwardBlockDetector = this;
                final IElementType elementType2 = ocBackwardBlockDetector.myRight;
                if (elementType != elementType2) {
                    return false;
                }
            }
            catch (IllegalArgumentException ex3) {
                throw b(ex3);
            }
        }
        int n = 1;
        while (true) {
            try {
                tokenIterator.retreat();
                tokenIterator.skipWhitespaces();
                if (tokenIterator.atEnd()) {
                    break;
                }
            }
            catch (IllegalArgumentException ex4) {
                throw b(ex4);
            }
            final IElementType tokenType = tokenIterator.getTokenType();
            try {
                if (tokenType == this.myRight) {
                    ++n;
                    continue;
                }
            }
            catch (IllegalArgumentException ex5) {
                throw b(ex5);
            }
            Label_0162: {
                try {
                    if (tokenType != this.myLeft) {
                        continue;
                    }
                    final int n2 = --n;
                    if (n2 == 0) {
                        break Label_0162;
                    }
                    continue;
                }
                catch (IllegalArgumentException ex6) {
                    throw b(ex6);
                }
                try {
                    final int n2 = --n;
                    if (n2 == 0) {
                        tokenIterator.retreat();
                        return true;
                    }
                    continue;
                }
                catch (IllegalArgumentException ex7) {
                    throw b(ex7);
                }
            }
        }
        return false;
    }
    
    private static IllegalArgumentException b(final IllegalArgumentException ex) {
        return ex;
    }
}
