// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.lang.editor;

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
