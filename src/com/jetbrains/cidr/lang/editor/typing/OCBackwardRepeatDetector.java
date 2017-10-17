// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.lang.editor.typing;

import org.jetbrains.annotations.NotNull;

public class OCBackwardRepeatDetector extends OCBackwardStructureDetector
{
    @NotNull
    private final OCBackwardStructureDetector myDetector;
    @NotNull
    private final Cardinality myCardinality;
    
    public OCBackwardRepeatDetector(@NotNull final Cardinality myCardinality, @NotNull final OCBackwardStructureDetector myDetector) {
        if (myCardinality == null) {
            throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "cardinality", "com/jetbrains/cidr/lang/editor/typing/OCBackwardRepeatDetector", "<init>"));
        }
        if (myDetector == null) {
            throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "detector", "com/jetbrains/cidr/lang/editor/typing/OCBackwardRepeatDetector", "<init>"));
        }
        this.myDetector = myDetector;
        this.myCardinality = myCardinality;
    }
    
    @Override
    public boolean next(@NotNull final TokenIterator tokenIterator) {
        try {
            if (tokenIterator == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "it", "com/jetbrains/cidr/lang/editor/typing/OCBackwardRepeatDetector", "next"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw b(ex);
        }
        int n = 0;
        while (this.myDetector.tryNext(tokenIterator)) {
            ++n;
            if (this.myCardinality == Cardinality.MAYBE_ONE) {
                return true;
            }
        }
        Label_0093: {
            try {
                if (n > 0) {
                    break Label_0093;
                }
                final OCBackwardRepeatDetector ocBackwardRepeatDetector = this;
                final Cardinality cardinality = ocBackwardRepeatDetector.myCardinality;
                final Cardinality cardinality2 = Cardinality.ONE_OR_MORE;
                if (cardinality != cardinality2) {
                    break Label_0093;
                }
                return false;
            }
            catch (IllegalArgumentException ex2) {
                throw b(ex2);
            }
            try {
                final OCBackwardRepeatDetector ocBackwardRepeatDetector = this;
                final Cardinality cardinality = ocBackwardRepeatDetector.myCardinality;
                final Cardinality cardinality2 = Cardinality.ONE_OR_MORE;
                if (cardinality != cardinality2) {
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
