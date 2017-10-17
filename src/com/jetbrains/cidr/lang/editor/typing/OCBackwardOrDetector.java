// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.lang.editor.typing;

import java.util.Iterator;
import org.jetbrains.annotations.NotNull;
import java.util.List;

public class OCBackwardOrDetector extends OCBackwardStructureDetector
{
    @NotNull
    private final List<OCBackwardStructureDetector> myDetectors;
    
    public OCBackwardOrDetector(@NotNull final List<OCBackwardStructureDetector> myDetectors) {
        if (myDetectors == null) {
            throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "detectors", "com/jetbrains/cidr/lang/editor/typing/OCBackwardOrDetector", "<init>"));
        }
        this.myDetectors = myDetectors;
    }
    
    @Override
    public boolean next(@NotNull final TokenIterator tokenIterator) {
        try {
            if (tokenIterator == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "it", "com/jetbrains/cidr/lang/editor/typing/OCBackwardOrDetector", "next"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw b(ex);
        }
        for (final OCBackwardStructureDetector ocBackwardStructureDetector : this.myDetectors) {
            try {
                if (ocBackwardStructureDetector.tryNext(tokenIterator)) {
                    return true;
                }
                continue;
            }
            catch (IllegalArgumentException ex2) {
                throw b(ex2);
            }
        }
        return false;
    }
    
    private static IllegalArgumentException b(final IllegalArgumentException ex) {
        return ex;
    }
}
