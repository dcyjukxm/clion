// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.lang.formatting;

import com.intellij.formatting.Spacing;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import com.intellij.formatting.Block;
import java.util.List;
import com.intellij.formatting.Alignment;
import com.intellij.formatting.Wrap;
import com.intellij.lang.ASTNode;
import com.intellij.psi.formatter.common.AbstractBlock;

static final class OCFormattingModelBuilder$2 extends AbstractBlock {
    @Override
    protected List<Block> buildChildren() {
        return OCFormattingModelBuilder$2.EMPTY;
    }
    
    @Nullable
    public Spacing getSpacing(@Nullable final Block block, @NotNull final Block block2) {
        try {
            if (block2 == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "child2", "com/jetbrains/cidr/lang/formatting/OCFormattingModelBuilder$2", "getSpacing"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw b(ex);
        }
        return null;
    }
    
    public boolean isLeaf() {
        return false;
    }
    
    private static IllegalArgumentException b(final IllegalArgumentException ex) {
        return ex;
    }
}