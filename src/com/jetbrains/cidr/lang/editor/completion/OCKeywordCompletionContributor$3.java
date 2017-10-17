// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.lang.editor.completion;

import com.intellij.psi.tree.IElementType;
import com.intellij.psi.impl.source.tree.TreeUtil;
import com.jetbrains.cidr.lang.parser.OCTokenTypes;
import com.intellij.util.ProcessingContext;
import org.jetbrains.annotations.NotNull;
import com.jetbrains.cidr.lang.psi.OCDeclarator;
import com.intellij.patterns.PatternCondition;

class OCKeywordCompletionContributor$3 extends PatternCondition<OCDeclarator> {
    public boolean accepts(@NotNull final OCDeclarator ocDeclarator, final ProcessingContext processingContext) {
        try {
            if (ocDeclarator == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "declarator", "com/jetbrains/cidr/lang/editor/completion/OCKeywordCompletionContributor$3", "accepts"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        try {
            if (TreeUtil.findChildBackward(ocDeclarator.getNode(), OCTokenTypes.TRY_KEYWORD) != null) {
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