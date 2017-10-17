// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.lang.editor;

import org.jetbrains.annotations.Nullable;
import com.intellij.lang.ASTNode;
import com.jetbrains.cidr.lang.parser.OCElementTypes;
import com.intellij.psi.tree.TokenSet;
import com.jetbrains.cidr.lang.parser.OCTokenTypes;
import com.intellij.psi.tree.IElementType;
import com.intellij.openapi.util.TextRange;
import org.jetbrains.annotations.NotNull;
import com.intellij.psi.PsiElement;
import com.intellij.codeInsight.hint.DeclarationRangeHandler;

public class OCDeclarationRangeHandler implements DeclarationRangeHandler
{
    @NotNull
    public TextRange getDeclarationRange(@NotNull final PsiElement psiElement) {
        try {
            if (psiElement == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "container", "com/jetbrains/cidr/lang/editor/OCDeclarationRangeHandler", "getDeclarationRange"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        final TextRange textRange = psiElement.getTextRange();
        final ASTNode a = a(psiElement, TokenSet.create(new IElementType[] { OCTokenTypes.IDENTIFIER }));
        TextRange textRange2 = null;
        Label_0087: {
            try {
                if (a != null) {
                    textRange2 = a.getTextRange();
                    break Label_0087;
                }
            }
            catch (IllegalArgumentException ex2) {
                throw a(ex2);
            }
            textRange2 = null;
        }
        final TextRange textRange3 = textRange2;
        int n = 0;
        Label_0110: {
            try {
                if (textRange3 != null) {
                    n = textRange3.getStartOffset();
                    break Label_0110;
                }
            }
            catch (IllegalArgumentException ex3) {
                throw a(ex3);
            }
            n = textRange.getStartOffset();
        }
        final int n2 = n;
        int endOffset;
        if (textRange3 != null) {
            endOffset = textRange3.getEndOffset();
        }
        else {
            final ASTNode a2 = a(psiElement, OCElementTypes.BLOCK_STATEMENTS);
            int n3 = 0;
            Label_0162: {
                try {
                    if (a2 != null) {
                        n3 = a2.getTextRange().getStartOffset();
                        break Label_0162;
                    }
                }
                catch (IllegalArgumentException ex4) {
                    throw a(ex4);
                }
                n3 = textRange.getEndOffset();
            }
            endOffset = n3;
        }
        TextRange textRange4;
        try {
            textRange4 = new TextRange(n2, endOffset);
            if (textRange4 == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/editor/OCDeclarationRangeHandler", "getDeclarationRange"));
            }
        }
        catch (IllegalArgumentException ex5) {
            throw a(ex5);
        }
        return textRange4;
    }
    
    @Nullable
    private static ASTNode a(final PsiElement psiElement, final TokenSet set) {
        ASTNode astNode = psiElement.getNode().getFirstChildNode();
        while (true) {
            Label_0042: {
                try {
                    if (astNode == null) {
                        break;
                    }
                    final TokenSet set2 = set;
                    final ASTNode astNode2 = astNode;
                    final IElementType elementType = astNode2.getElementType();
                    final boolean b = set2.contains(elementType);
                    if (b) {
                        return astNode;
                    }
                    break Label_0042;
                }
                catch (IllegalArgumentException ex) {
                    throw a(ex);
                }
                try {
                    final TokenSet set2 = set;
                    final ASTNode astNode2 = astNode;
                    final IElementType elementType = astNode2.getElementType();
                    final boolean b = set2.contains(elementType);
                    if (b) {
                        return astNode;
                    }
                }
                catch (IllegalArgumentException ex2) {
                    throw a(ex2);
                }
            }
            astNode = astNode.getTreeNext();
        }
        return null;
    }
    
    private static IllegalArgumentException a(final IllegalArgumentException ex) {
        return ex;
    }
}
