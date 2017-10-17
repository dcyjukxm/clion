// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.lang.editor.typing;

import com.intellij.psi.tree.IElementType;
import org.jetbrains.annotations.NotNull;
import com.intellij.psi.tree.TokenSet;

public class OCBackwardTokenDetector extends OCBackwardStructureDetector
{
    private final boolean myIncludeSet;
    @NotNull
    private final TokenSet myTypes;
    
    public OCBackwardTokenDetector(final boolean myIncludeSet, @NotNull final TokenSet myTypes) {
        if (myTypes == null) {
            throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "types", "com/jetbrains/cidr/lang/editor/typing/OCBackwardTokenDetector", "<init>"));
        }
        this.myIncludeSet = myIncludeSet;
        this.myTypes = myTypes;
    }
    
    @Override
    public boolean next(@NotNull final TokenIterator tokenIterator) {
        try {
            if (tokenIterator == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "it", "com/jetbrains/cidr/lang/editor/typing/OCBackwardTokenDetector", "next"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw b(ex);
        }
        Label_0086: {
            try {
                tokenIterator.skipWhitespaces();
                if (tokenIterator.atEnd()) {
                    return false;
                }
                final OCBackwardTokenDetector ocBackwardTokenDetector = this;
                final boolean b = ocBackwardTokenDetector.myIncludeSet;
                final OCBackwardTokenDetector ocBackwardTokenDetector2 = this;
                final TokenSet set = ocBackwardTokenDetector2.myTypes;
                final TokenIterator tokenIterator2 = tokenIterator;
                final IElementType elementType = tokenIterator2.getTokenType();
                final boolean b2 = set.contains(elementType);
                if (b == b2) {
                    break Label_0086;
                }
                return false;
            }
            catch (IllegalArgumentException ex2) {
                throw b(ex2);
            }
            try {
                final OCBackwardTokenDetector ocBackwardTokenDetector = this;
                final boolean b = ocBackwardTokenDetector.myIncludeSet;
                final OCBackwardTokenDetector ocBackwardTokenDetector2 = this;
                final TokenSet set = ocBackwardTokenDetector2.myTypes;
                final TokenIterator tokenIterator2 = tokenIterator;
                final IElementType elementType = tokenIterator2.getTokenType();
                final boolean b2 = set.contains(elementType);
                if (b == b2) {
                    tokenIterator.retreat();
                    return true;
                }
            }
            catch (IllegalArgumentException ex3) {
                throw b(ex3);
            }
        }
        return false;
    }
    
    private static IllegalArgumentException b(final IllegalArgumentException ex) {
        return ex;
    }
}
