// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.lang.psi.impl;

import com.jetbrains.cidr.lang.psi.visitors.OCVisitor;
import org.jetbrains.annotations.Nullable;
import com.intellij.psi.PsiElement;
import com.jetbrains.cidr.lang.parser.OCPunctuatorElementType;
import com.jetbrains.cidr.lang.parser.OCElementType;
import com.intellij.psi.tree.IElementType;
import com.jetbrains.cidr.lang.parser.OCTokenTypes;
import org.jetbrains.annotations.NotNull;
import com.intellij.lang.ASTNode;
import com.jetbrains.cidr.lang.psi.OCArgumentSelector;

public class OCArgumentSelectorImpl extends OCElementBase implements OCArgumentSelector
{
    public OCArgumentSelectorImpl(@NotNull final ASTNode astNode) {
        if (astNode == null) {
            throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "node", "com/jetbrains/cidr/lang/psi/impl/OCArgumentSelectorImpl", "<init>"));
        }
        super(astNode);
    }
    
    @NotNull
    @Override
    public String getSelectorName() {
        final StringBuilder sb = new StringBuilder();
        for (ASTNode astNode = this.getNode().getFirstChildNode(); astNode != null; astNode = astNode.getTreeNext()) {
            final IElementType elementType = astNode.getElementType();
            Label_0050: {
                try {
                    if (elementType == OCTokenTypes.IDENTIFIER) {
                        break Label_0050;
                    }
                    final OCElementType ocElementType = (OCElementType)elementType;
                    final OCPunctuatorElementType ocPunctuatorElementType = OCTokenTypes.COLON;
                    if (ocElementType == ocPunctuatorElementType) {
                        break Label_0050;
                    }
                    continue;
                }
                catch (IllegalArgumentException ex) {
                    throw a(ex);
                }
                try {
                    final OCElementType ocElementType = (OCElementType)elementType;
                    final OCPunctuatorElementType ocPunctuatorElementType = OCTokenTypes.COLON;
                    if (ocElementType == ocPunctuatorElementType) {
                        sb.append(astNode.getText());
                    }
                }
                catch (IllegalArgumentException ex2) {
                    throw a(ex2);
                }
            }
        }
        String string;
        try {
            string = sb.toString();
            if (string == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/psi/impl/OCArgumentSelectorImpl", "getSelectorName"));
            }
        }
        catch (IllegalArgumentException ex3) {
            throw a(ex3);
        }
        return string;
    }
    
    @Nullable
    @Override
    public PsiElement getSelectorIdentifier() {
        return this.findChildByType(OCTokenTypes.IDENTIFIER);
    }
    
    @Override
    public void accept(@NotNull final OCVisitor ocVisitor) {
        try {
            if (ocVisitor == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "visitor", "com/jetbrains/cidr/lang/psi/impl/OCArgumentSelectorImpl", "accept"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        ocVisitor.visitArgumentSelector(this);
    }
    
    private static IllegalArgumentException a(final IllegalArgumentException ex) {
        return ex;
    }
}
