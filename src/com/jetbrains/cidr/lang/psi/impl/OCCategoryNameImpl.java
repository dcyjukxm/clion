// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.lang.psi.impl;

import com.jetbrains.cidr.lang.refactoring.util.OCChangeUtil;
import com.jetbrains.cidr.lang.util.OCElementFactory;
import com.jetbrains.cidr.lang.util.OCElementUtil;
import org.jetbrains.annotations.NonNls;
import com.intellij.psi.tree.IElementType;
import com.jetbrains.cidr.lang.parser.OCTokenTypes;
import com.intellij.psi.PsiElement;
import com.intellij.util.IncorrectOperationException;
import com.jetbrains.cidr.lang.psi.visitors.OCVisitor;
import org.jetbrains.annotations.NotNull;
import com.intellij.lang.ASTNode;
import com.jetbrains.cidr.lang.psi.OCCategoryName;

public class OCCategoryNameImpl extends OCElementBase implements OCCategoryName
{
    public OCCategoryNameImpl(@NotNull final ASTNode astNode) {
        if (astNode == null) {
            throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "node", "com/jetbrains/cidr/lang/psi/impl/OCCategoryNameImpl", "<init>"));
        }
        super(astNode);
    }
    
    @Override
    public void accept(@NotNull final OCVisitor ocVisitor) {
        try {
            if (ocVisitor == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "visitor", "com/jetbrains/cidr/lang/psi/impl/OCCategoryNameImpl", "accept"));
            }
        }
        catch (IncorrectOperationException ex) {
            throw a(ex);
        }
        ocVisitor.visitCategoryName(this);
    }
    
    public PsiElement getNameIdentifier() {
        return this.findChildByType(OCTokenTypes.IDENTIFIER);
    }
    
    @NotNull
    @Override
    public String getName() {
        final PsiElement nameIdentifier = this.getNameIdentifier();
        String s = null;
        Label_0024: {
            try {
                if (nameIdentifier != null) {
                    final String text;
                    s = (text = nameIdentifier.getText());
                    break Label_0024;
                }
            }
            catch (IncorrectOperationException ex) {
                throw a(ex);
            }
            String text;
            s = (text = "");
            try {
                if (text == null) {
                    throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/psi/impl/OCCategoryNameImpl", "getName"));
                }
            }
            catch (IncorrectOperationException ex2) {
                throw a(ex2);
            }
        }
        return s;
    }
    
    public PsiElement setName(@NonNls @NotNull final String s) throws IncorrectOperationException {
        try {
            if (s == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "name", "com/jetbrains/cidr/lang/psi/impl/OCCategoryNameImpl", "setName"));
            }
        }
        catch (IncorrectOperationException ex) {
            throw a(ex);
        }
        final PsiElement nameIdentifier = this.getNameIdentifier();
        Label_0102: {
            Label_0067: {
                try {
                    if (s.isEmpty()) {
                        break Label_0102;
                    }
                    final PsiElement psiElement = nameIdentifier;
                    if (psiElement != null) {
                        break Label_0067;
                    }
                    break Label_0067;
                }
                catch (IncorrectOperationException ex2) {
                    throw a(ex2);
                }
                try {
                    final PsiElement psiElement = nameIdentifier;
                    if (psiElement != null) {
                        OCElementUtil.replaceWithIdentifier(nameIdentifier, s, (PsiElement)this);
                        return (PsiElement)this;
                    }
                }
                catch (IncorrectOperationException ex3) {
                    throw a(ex3);
                }
            }
            this.addBefore(OCElementFactory.createIdentifier(s, (PsiElement)this), this.findNotNullChildByType(OCTokenTypes.RPAR));
            return (PsiElement)this;
            try {
                if (nameIdentifier != null) {
                    OCChangeUtil.delete(nameIdentifier);
                }
            }
            catch (IncorrectOperationException ex4) {
                throw a(ex4);
            }
        }
        return (PsiElement)this;
    }
    
    private static IncorrectOperationException a(final IncorrectOperationException ex) {
        return ex;
    }
}
