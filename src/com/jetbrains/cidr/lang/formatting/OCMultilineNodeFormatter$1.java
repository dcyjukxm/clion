// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.lang.formatting;

import com.intellij.openapi.util.TextRange;
import com.intellij.openapi.editor.Document;

class OCMultilineNodeFormatter$1 implements DocumentModifier {
    final /* synthetic */ Document val$document;
    final /* synthetic */ TextRange val$textRange;
    
    @Override
    public TextRange change(final boolean b, final String s, final String s2) {
        this.val$document.replaceString(this.val$textRange.getStartOffset(), this.val$textRange.getEndOffset(), (CharSequence)s);
        return new TextRange(this.val$textRange.getStartOffset(), this.val$textRange.getEndOffset() + s.length() - s2.length());
    }
}