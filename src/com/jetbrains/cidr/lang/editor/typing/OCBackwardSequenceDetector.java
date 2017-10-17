// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.lang.editor.typing;

import java.util.Iterator;
import org.jetbrains.annotations.NotNull;
import java.util.List;

public class OCBackwardSequenceDetector extends OCBackwardStructureDetector
{
    @NotNull
    private final List<OCBackwardStructureDetector> mySequence;
    
    public OCBackwardSequenceDetector(@NotNull final List<OCBackwardStructureDetector> mySequence) {
        if (mySequence == null) {
            throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "sequence", "com/jetbrains/cidr/lang/editor/typing/OCBackwardSequenceDetector", "<init>"));
        }
        this.mySequence = mySequence;
    }
    
    @Override
    public boolean next(@NotNull final TokenIterator tokenIterator) {
        try {
            if (tokenIterator == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "it", "com/jetbrains/cidr/lang/editor/typing/OCBackwardSequenceDetector", "next"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw b(ex);
        }
        for (final OCBackwardStructureDetector ocBackwardStructureDetector : this.mySequence) {
            try {
                if (!ocBackwardStructureDetector.next(tokenIterator)) {
                    return false;
                }
                continue;
            }
            catch (IllegalArgumentException ex2) {
                throw b(ex2);
            }
        }
        return true;
    }
    
    private static IllegalArgumentException b(final IllegalArgumentException ex) {
        return ex;
    }
}
