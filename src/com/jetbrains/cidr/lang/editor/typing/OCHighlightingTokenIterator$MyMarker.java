// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.lang.editor.typing;

private class MyMarker implements Marker
{
    private final int myStart;
    
    public MyMarker(final int myStart) {
        this.myStart = myStart;
    }
    
    @Override
    public void rollback() {
        if (OCHighlightingTokenIterator.access$000(OCHighlightingTokenIterator.this).atEnd()) {
            OCHighlightingTokenIterator.access$000(OCHighlightingTokenIterator.this).advance();
            if (OCHighlightingTokenIterator.access$000(OCHighlightingTokenIterator.this).atEnd()) {
                OCHighlightingTokenIterator.access$000(OCHighlightingTokenIterator.this).retreat();
                OCHighlightingTokenIterator.access$000(OCHighlightingTokenIterator.this).retreat();
            }
        }
        while (this.myStart > OCHighlightingTokenIterator.access$000(OCHighlightingTokenIterator.this).getStart()) {
            OCHighlightingTokenIterator.access$000(OCHighlightingTokenIterator.this).advance();
        }
        while (this.myStart < OCHighlightingTokenIterator.access$000(OCHighlightingTokenIterator.this).getStart()) {
            OCHighlightingTokenIterator.access$000(OCHighlightingTokenIterator.this).retreat();
        }
    }
}
