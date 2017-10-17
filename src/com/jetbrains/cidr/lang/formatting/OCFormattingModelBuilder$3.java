// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.lang.formatting;

import com.intellij.lang.ASTNode;
import org.jetbrains.annotations.NotNull;
import com.intellij.openapi.util.TextRange;
import com.intellij.openapi.editor.Document;
import com.intellij.formatting.FormattingDocumentModel;

static final class OCFormattingModelBuilder$3 implements FormattingDocumentModel {
    final /* synthetic */ Document val$document;
    
    public int getLineNumber(final int n) {
        return this.val$document.getLineNumber(n);
    }
    
    public int getLineStartOffset(final int n) {
        return this.val$document.getLineStartOffset(n);
    }
    
    public CharSequence getText(final TextRange textRange) {
        return this.val$document.getText(textRange);
    }
    
    public int getTextLength() {
        return this.val$document.getTextLength();
    }
    
    @NotNull
    public Document getDocument() {
        Document val$document;
        try {
            val$document = this.val$document;
            if (val$document == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/formatting/OCFormattingModelBuilder$3", "getDocument"));
            }
        }
        catch (IllegalStateException ex) {
            throw a(ex);
        }
        return val$document;
    }
    
    public boolean containsWhiteSpaceSymbolsOnly(final int n, final int n2) {
        return false;
    }
    
    @NotNull
    public CharSequence adjustWhiteSpaceIfNecessary(@NotNull final CharSequence charSequence, final int n, final int n2, final ASTNode astNode, final boolean b) {
        try {
            if (charSequence == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "whiteSpaceText", "com/jetbrains/cidr/lang/formatting/OCFormattingModelBuilder$3", "adjustWhiteSpaceIfNecessary"));
            }
        }
        catch (IllegalStateException ex) {
            throw a(ex);
        }
        try {
            if (charSequence == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/formatting/OCFormattingModelBuilder$3", "adjustWhiteSpaceIfNecessary"));
            }
        }
        catch (IllegalStateException ex2) {
            throw a(ex2);
        }
        return charSequence;
    }
    
    private static IllegalStateException a(final IllegalStateException ex) {
        return ex;
    }
}