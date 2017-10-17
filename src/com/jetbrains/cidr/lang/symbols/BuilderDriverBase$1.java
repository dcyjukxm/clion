// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.lang.symbols;

import com.intellij.psi.tree.IElementType;
import com.jetbrains.cidr.lang.preprocessor.OCMacroForeignLeafElement;
import org.jetbrains.annotations.NotNull;
import com.intellij.lang.ASTNode;

static final class BuilderDriverBase$1 implements NamedNodeStructure<ASTNode> {
    public int getStartOffset(@NotNull final ASTNode astNode) {
        try {
            if (astNode == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "node", "com/jetbrains/cidr/lang/symbols/BuilderDriverBase$1", "getStartOffset"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        try {
            if (astNode instanceof OCMacroForeignLeafElement) {
                return ((OCMacroForeignLeafElement)astNode).getRealStartOffset();
            }
        }
        catch (IllegalArgumentException ex2) {
            throw a(ex2);
        }
        return astNode.getStartOffset();
    }
    
    public int getEndOffset(@NotNull final ASTNode astNode) {
        try {
            if (astNode == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "node", "com/jetbrains/cidr/lang/symbols/BuilderDriverBase$1", "getEndOffset"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        return astNode.getTextRange().getEndOffset();
    }
    
    public IElementType getTokenType(@NotNull final ASTNode astNode) {
        try {
            if (astNode == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "node", "com/jetbrains/cidr/lang/symbols/BuilderDriverBase$1", "getTokenType"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        return astNode.getElementType();
    }
    
    @Override
    public String getNodeText(final ASTNode astNode) {
        return astNode.getText();
    }
    
    private static IllegalArgumentException a(final IllegalArgumentException ex) {
        return ex;
    }
}