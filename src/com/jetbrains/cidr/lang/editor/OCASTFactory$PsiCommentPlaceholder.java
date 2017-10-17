// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.lang.editor;

import org.jetbrains.annotations.Nullable;
import com.jetbrains.cidr.lang.psi.OCDeclaration;
import com.jetbrains.cidr.lang.psi.OCCallable;
import com.jetbrains.cidr.lang.psi.OCMacroCall;
import com.intellij.psi.PsiComment;
import com.intellij.psi.PsiWhiteSpace;
import com.intellij.psi.PsiElement;
import com.intellij.psi.tree.IElementType;
import com.intellij.psi.impl.source.tree.injected.InjectionBackgroundSuppressor;
import com.intellij.psi.PsiDocCommentBase;
import com.intellij.psi.impl.source.tree.PsiCommentImpl;

public static class PsiCommentPlaceholder extends PsiCommentImpl implements PsiDocCommentBase, InjectionBackgroundSuppressor
{
    public PsiCommentPlaceholder(final IElementType type, final CharSequence text) {
        super(type, text);
    }
    
    @Nullable
    public PsiElement getOwner() {
        for (PsiElement psiElement = this.getNextSibling(); psiElement != null; psiElement = psiElement.getNextSibling()) {
            if (!(psiElement instanceof PsiWhiteSpace)) {
                if (!(psiElement instanceof PsiComment)) {
                    if (!(psiElement instanceof OCMacroCall)) {
                        if (psiElement instanceof OCCallable || psiElement instanceof OCDeclaration) {
                            return psiElement;
                        }
                        break;
                    }
                }
            }
        }
        final PsiElement parent = this.getParent();
        if (parent instanceof OCCallable || parent instanceof OCDeclaration) {
            return parent;
        }
        return null;
    }
}
