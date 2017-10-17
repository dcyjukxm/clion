// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.lang.editor.completion;

import com.intellij.util.ProcessingContext;
import org.jetbrains.annotations.NotNull;
import com.jetbrains.cidr.lang.psi.OCCatchSection;
import com.intellij.patterns.PatternCondition;

static final class OCCompletionPatterns$5 extends PatternCondition<OCCatchSection> {
    final /* synthetic */ boolean val$isCpp;
    
    public boolean accepts(@NotNull final OCCatchSection ocCatchSection, final ProcessingContext processingContext) {
        try {
            if (ocCatchSection == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "section", "com/jetbrains/cidr/lang/editor/completion/OCCompletionPatterns$5", "accepts"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        try {
            if (ocCatchSection.isCppStatement() == this.val$isCpp) {
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