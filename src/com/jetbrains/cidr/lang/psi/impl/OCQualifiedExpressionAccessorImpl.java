// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.lang.psi.impl;

import com.intellij.psi.PsiReference;
import org.jetbrains.annotations.Nullable;
import com.intellij.psi.PsiElement;
import com.jetbrains.cidr.lang.psi.OCElement;
import com.jetbrains.cidr.lang.psi.OCExpression;
import com.intellij.psi.tree.TokenSet;
import com.jetbrains.cidr.lang.psi.OCQualifiedExpression;
import com.jetbrains.cidr.lang.parser.OCTokenTypes;
import org.jetbrains.annotations.NotNull;
import com.intellij.lang.ASTNode;
import com.jetbrains.cidr.lang.resolve.references.OCOperatorReference;

public class OCQualifiedExpressionAccessorImpl extends OCElementWithReferenceBase<OCOperatorReference>
{
    public OCQualifiedExpressionAccessorImpl(@NotNull final ASTNode astNode) {
        if (astNode == null) {
            throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "node", "com/jetbrains/cidr/lang/psi/impl/OCQualifiedExpressionAccessorImpl", "<init>"));
        }
        super(astNode);
    }
    
    @Nullable
    @Override
    protected OCOperatorReference createReference() {
        final String name = OCTokenTypes.DEREF.getName();
        if (name.equals(this.getText())) {
            final OCExpression qualifier = ((OCQualifiedExpression)this.getParent()).getQualifier();
            final ASTNode[] children = this.getNode().getChildren((TokenSet)null);
            String s;
            OCOperatorReference.OperatorPlacement infix;
            try {
                s = name;
                infix = OCOperatorReference.OperatorPlacement.INFIX;
                if (children.length > 0) {
                    final PsiElement psi = children[0].getPsi();
                    return new OCOperatorReference(this, s, infix, psi, new OCExpression[] { qualifier });
                }
            }
            catch (IllegalArgumentException ex) {
                throw c(ex);
            }
            final PsiElement psi = null;
            return new OCOperatorReference(this, s, infix, psi, new OCExpression[] { qualifier });
        }
        return null;
    }
    
    private static IllegalArgumentException c(final IllegalArgumentException ex) {
        return ex;
    }
}
