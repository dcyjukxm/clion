// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.lang.formatting;

import com.intellij.lang.ASTNode;
import com.intellij.openapi.util.TextRange;
import org.jetbrains.annotations.NotNull;
import com.intellij.formatting.Block;
import com.intellij.formatting.FormattingDocumentModel;
import com.intellij.psi.formatter.common.AbstractBlock;
import com.intellij.formatting.FormattingModel;

static final class OCFormattingModelBuilder$4 implements FormattingModel {
    final /* synthetic */ AbstractBlock val$block;
    final /* synthetic */ FormattingDocumentModel val$model;
    
    @NotNull
    public Block getRootBlock() {
        AbstractBlock val$block;
        try {
            val$block = this.val$block;
            if (val$block == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/formatting/OCFormattingModelBuilder$4", "getRootBlock"));
            }
        }
        catch (IllegalStateException ex) {
            throw a(ex);
        }
        return (Block)val$block;
    }
    
    @NotNull
    public FormattingDocumentModel getDocumentModel() {
        FormattingDocumentModel val$model;
        try {
            val$model = this.val$model;
            if (val$model == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/formatting/OCFormattingModelBuilder$4", "getDocumentModel"));
            }
        }
        catch (IllegalStateException ex) {
            throw a(ex);
        }
        return val$model;
    }
    
    public TextRange replaceWhiteSpace(final TextRange textRange, final String s) {
        return textRange;
    }
    
    public TextRange shiftIndentInsideRange(final ASTNode astNode, final TextRange textRange, final int n) {
        return textRange;
    }
    
    public void commitChanges() {
    }
    
    private static IllegalStateException a(final IllegalStateException ex) {
        return ex;
    }
}