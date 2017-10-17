// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.lang.editor;

import org.jetbrains.annotations.Nullable;
import java.util.Iterator;
import com.intellij.openapi.util.TextRange;
import com.intellij.util.containers.ContainerUtil;
import com.jetbrains.cidr.lang.parser.OCTokenTypes;
import com.intellij.psi.tree.IElementType;
import java.util.ArrayList;
import java.util.List;

public class OCLexingBlockCollector
{
    List<Block> blocks;
    List<Block> blockStack;
    private int xorOffset;
    private int parenBalance;
    
    public OCLexingBlockCollector() {
        this.blocks = new ArrayList<Block>();
        this.blockStack = new ArrayList<Block>();
        this.xorOffset = -1;
        this.parenBalance = 0;
    }
    
    public void update(final int xorOffset, final IElementType elementType) {
        if (elementType == OCTokenTypes.XOR) {
            this.xorOffset = xorOffset;
            this.parenBalance = 0;
        }
        else if (elementType == OCTokenTypes.SEMICOLON) {
            this.xorOffset = -1;
        }
        else if (elementType == OCTokenTypes.LPAR && this.xorOffset >= 0) {
            ++this.parenBalance;
        }
        else if (elementType == OCTokenTypes.RPAR && this.xorOffset >= 0) {
            --this.parenBalance;
        }
        else if (elementType == OCTokenTypes.LBRACE) {
            if (this.xorOffset >= 0 && this.parenBalance == 0) {
                final Block block = new Block(this.xorOffset, Integer.MAX_VALUE);
                this.blocks.add(block);
                this.blockStack.add(block);
            }
            else {
                final Block block2 = (Block)ContainerUtil.getLastItem((List)this.blockStack);
                if (block2 != null) {
                    final Block block3 = block2;
                    ++block3.braceBalance;
                }
            }
            this.xorOffset = -1;
        }
        else if (elementType == OCTokenTypes.RBRACE) {
            final Block block4 = (Block)ContainerUtil.getLastItem((List)this.blockStack);
            if (block4 != null) {
                final Block block5 = block4;
                if (--block5.braceBalance <= 0) {
                    block4.endOffset = xorOffset + 1;
                    this.blockStack.remove(this.blockStack.size() - 1);
                }
            }
        }
    }
    
    @Nullable
    public TextRange getLastCompleteBlockRange(final int n) {
        Block block = null;
        int access$000 = -1;
        for (final Block block2 : this.blocks) {
            if (block2.endOffset <= n && block2.endOffset > access$000) {
                block = block2;
                access$000 = block.endOffset;
            }
        }
        return (block == null) ? null : new TextRange(block.startOffset, block.endOffset);
    }
    
    private static class Block
    {
        private final int startOffset;
        private int endOffset;
        int braceBalance;
        
        public Block(final int startOffset, final int endOffset) {
            this.braceBalance = 1;
            this.startOffset = startOffset;
            this.endOffset = endOffset;
        }
    }
}
