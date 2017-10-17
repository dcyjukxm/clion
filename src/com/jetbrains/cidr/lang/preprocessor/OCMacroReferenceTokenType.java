// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.lang.preprocessor;

import org.jetbrains.annotations.NotNull;
import com.intellij.psi.impl.source.tree.LeafPsiElement;
import com.intellij.lang.ASTNode;
import com.intellij.psi.tree.IElementType;
import com.intellij.psi.tree.ILeafElementType;
import com.intellij.lang.TokenWrapper;

public class OCMacroReferenceTokenType extends TokenWrapper implements ILeafElementType
{
    private boolean myParamToken;
    private final int myMacroLevel;
    private final boolean isRoot;
    
    public OCMacroReferenceTokenType(final IElementType elementType, final CharSequence charSequence, final boolean myParamToken, final int myMacroLevel, final boolean isRoot) {
        super(elementType, charSequence);
        this.myParamToken = myParamToken;
        this.myMacroLevel = myMacroLevel;
        this.isRoot = isRoot;
    }
    
    public boolean isParamToken() {
        return this.myParamToken;
    }
    
    public int getMacroLevel() {
        return this.myMacroLevel;
    }
    
    public boolean isRoot() {
        return this.isRoot;
    }
    
    @NotNull
    public ASTNode createLeafNode(final CharSequence charSequence) {
        LeafPsiElement leafPsiElement;
        try {
            leafPsiElement = new LeafPsiElement(this.getDelegate(), this.getValue());
            if (leafPsiElement == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/preprocessor/OCMacroReferenceTokenType", "createLeafNode"));
            }
        }
        catch (IllegalStateException ex) {
            throw a(ex);
        }
        return (ASTNode)leafPsiElement;
    }
    
    @Override
    public String toString() {
        final StringBuilder append = new StringBuilder("[").append(this.getDelegate()).append(", ");
        try {
            if (this.myParamToken) {
                append.append("P-");
            }
        }
        catch (IllegalStateException ex) {
            throw a(ex);
        }
        try {
            if (this.isRoot) {
                append.append("R-");
            }
        }
        catch (IllegalStateException ex2) {
            throw a(ex2);
        }
        append.append(this.myMacroLevel).append("]");
        return append.toString();
    }
    
    private static IllegalStateException a(final IllegalStateException ex) {
        return ex;
    }
}
