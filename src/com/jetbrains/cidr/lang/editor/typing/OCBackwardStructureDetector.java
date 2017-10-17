// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.lang.editor.typing;

import java.util.Arrays;
import com.intellij.psi.tree.TokenSet;
import com.intellij.psi.tree.IElementType;
import org.jetbrains.annotations.NotNull;

public abstract class OCBackwardStructureDetector
{
    public boolean tryNext(@NotNull final TokenIterator tokenIterator) {
        try {
            if (tokenIterator == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "tokenIterator", "com/jetbrains/cidr/lang/editor/typing/OCBackwardStructureDetector", "tryNext"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        try {
            if (tokenIterator.atEnd()) {
                return false;
            }
        }
        catch (IllegalArgumentException ex2) {
            throw a(ex2);
        }
        final TokenIterator.Marker mark = tokenIterator.mark();
        try {
            if (!this.next(tokenIterator)) {
                mark.rollback();
                return false;
            }
        }
        catch (IllegalArgumentException ex3) {
            throw a(ex3);
        }
        return true;
    }
    
    public abstract boolean next(@NotNull final TokenIterator p0);
    
    public static OCBackwardStructureDetector token(@NotNull final IElementType... array) {
        try {
            if (array == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "types", "com/jetbrains/cidr/lang/editor/typing/OCBackwardStructureDetector", "token"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        return new OCBackwardTokenDetector(true, TokenSet.create(array));
    }
    
    public static OCBackwardStructureDetector tokenExcept(@NotNull final IElementType... array) {
        try {
            if (array == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "types", "com/jetbrains/cidr/lang/editor/typing/OCBackwardStructureDetector", "tokenExcept"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        return new OCBackwardTokenDetector(false, TokenSet.create(array));
    }
    
    public static OCBackwardStructureDetector block(@NotNull final IElementType elementType, @NotNull final IElementType elementType2) {
        try {
            if (elementType == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "leftBorder", "com/jetbrains/cidr/lang/editor/typing/OCBackwardStructureDetector", "block"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        try {
            if (elementType2 == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "rightBorder", "com/jetbrains/cidr/lang/editor/typing/OCBackwardStructureDetector", "block"));
            }
        }
        catch (IllegalArgumentException ex2) {
            throw a(ex2);
        }
        return new OCBackwardBlockDetector(elementType, elementType2);
    }
    
    public static OCBackwardStructureDetector sequence(final OCBackwardStructureDetector... array) {
        return new OCBackwardSequenceDetector(Arrays.asList(array));
    }
    
    public static OCBackwardStructureDetector or(@NotNull final OCBackwardStructureDetector... array) {
        try {
            if (array == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "variants", "com/jetbrains/cidr/lang/editor/typing/OCBackwardStructureDetector", "or"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        return new OCBackwardOrDetector(Arrays.asList(array));
    }
    
    public static OCBackwardStructureDetector repeat(@NotNull final Cardinality cardinality, @NotNull final OCBackwardStructureDetector ocBackwardStructureDetector) {
        try {
            if (cardinality == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "cardinality", "com/jetbrains/cidr/lang/editor/typing/OCBackwardStructureDetector", "repeat"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        try {
            if (ocBackwardStructureDetector == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "detector", "com/jetbrains/cidr/lang/editor/typing/OCBackwardStructureDetector", "repeat"));
            }
        }
        catch (IllegalArgumentException ex2) {
            throw a(ex2);
        }
        return new OCBackwardRepeatDetector(cardinality, ocBackwardStructureDetector);
    }
    
    private static IllegalArgumentException a(final IllegalArgumentException ex) {
        return ex;
    }
    
    public enum Cardinality
    {
        ONE_OR_MORE, 
        ZERO_OR_MORE, 
        MAYBE_ONE;
    }
    
    public interface TokenIterator
    {
        Marker mark();
        
        IElementType getTokenType();
        
        boolean atEnd();
        
        void retreat();
        
        void skipWhitespaces();
        
        public interface Marker
        {
            void rollback();
        }
    }
}
