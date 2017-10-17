// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.lang.editor.completion;

import com.intellij.util.ProcessingContext;
import org.jetbrains.annotations.NotNull;
import com.jetbrains.cidr.lang.psi.OCTryStatement;
import com.intellij.patterns.PatternCondition;

static final class OCCompletionPatterns$4 extends PatternCondition<OCTryStatement> {
    final /* synthetic */ boolean val$isCpp;
    
    public boolean accepts(@NotNull final OCTryStatement ocTryStatement, final ProcessingContext processingContext) {
        try {
            if (ocTryStatement == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "statement", "com/jetbrains/cidr/lang/editor/completion/OCCompletionPatterns$4", "accepts"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        try {
            if (ocTryStatement.isCppStatement() == this.val$isCpp) {
                return true;
            }
        }
        catch (IllegalArgumentException ex2) {
            throw a(ex2);
        }
        return false;
    }
    
    private static IllegalArgumentException a(final IllegalArgumentException ex) {
        return ex;
    }
}