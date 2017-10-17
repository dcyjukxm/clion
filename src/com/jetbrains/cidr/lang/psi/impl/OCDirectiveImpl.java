// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.lang.psi.impl;

import com.jetbrains.cidr.lang.psi.visitors.OCVisitor;
import com.intellij.psi.tree.TokenSet;
import com.intellij.psi.tree.IElementType;
import com.jetbrains.cidr.lang.parser.OCTokenTypes;
import com.jetbrains.cidr.lang.util.OCElementUtil;
import com.intellij.psi.impl.source.tree.ForeignLeafPsiElement;
import com.intellij.openapi.util.TextRange;
import com.intellij.openapi.util.Pair;
import com.intellij.psi.PsiElement;
import org.jetbrains.annotations.NotNull;
import com.intellij.lang.ASTNode;
import com.jetbrains.cidr.lang.psi.OCDirective;

public class OCDirectiveImpl extends OCElementBase implements OCDirective
{
    public OCDirectiveImpl(@NotNull final ASTNode astNode) {
        if (astNode == null) {
            throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "node", "com/jetbrains/cidr/lang/psi/impl/OCDirectiveImpl", "<init>"));
        }
        super(astNode);
    }
    
    @NotNull
    @Override
    public PsiElement getHeaderToken() {
        PsiElement firstChild;
        try {
            firstChild = this.getFirstChild();
            if (firstChild == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/psi/impl/OCDirectiveImpl", "getHeaderToken"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        return firstChild;
    }
    
    @NotNull
    @Override
    public Pair<String, TextRange> getContent(final boolean b) {
        Pair<String, TextRange> content;
        try {
            content = this.getContent(this.getHeaderToken(), b);
            if (content == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/psi/impl/OCDirectiveImpl", "getContent"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        return content;
    }
    
    @NotNull
    @Override
    public Pair<String, TextRange> getContent(@NotNull final PsiElement psiElement, final boolean b) {
        try {
            if (psiElement == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "after", "com/jetbrains/cidr/lang/psi/impl/OCDirectiveImpl", "getContent"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        int startOffset = -1;
        int endOffset = -1;
        final StringBuilder sb = new StringBuilder();
        PsiElement psiElement2 = psiElement.getNextSibling();
        String s = "";
        while (true) {
            Label_0297: {
                try {
                    if (psiElement2 == null) {
                        break;
                    }
                    if (psiElement2 instanceof ForeignLeafPsiElement) {
                        break Label_0297;
                    }
                }
                catch (IllegalArgumentException ex2) {
                    throw a(ex2);
                }
                final IElementType elementType = OCElementUtil.getElementType(psiElement2);
                boolean b3 = false;
                Label_0131: {
                    Label_0122: {
                        try {
                            if (!b) {
                                break Label_0122;
                            }
                            final TokenSet set = OCTokenTypes.WHITE_SPACE_OR_COMMENT_BIT_SET;
                            final IElementType elementType2 = elementType;
                            final boolean b2 = set.contains(elementType2);
                            if (b2) {
                                break Label_0122;
                            }
                            break Label_0122;
                        }
                        catch (IllegalArgumentException ex3) {
                            throw a(ex3);
                        }
                        try {
                            final TokenSet set = OCTokenTypes.WHITE_SPACE_OR_COMMENT_BIT_SET;
                            final IElementType elementType2 = elementType;
                            final boolean b2 = set.contains(elementType2);
                            if (b2) {
                                b3 = true;
                                break Label_0131;
                            }
                        }
                        catch (IllegalArgumentException ex4) {
                            throw a(ex4);
                        }
                    }
                    b3 = false;
                }
                final boolean b4 = b3;
                final TextRange textRange = psiElement2.getTextRange();
                Label_0178: {
                    Label_0171: {
                        try {
                            if (startOffset >= 0) {
                                break Label_0178;
                            }
                            if (!b4) {
                                break Label_0171;
                            }
                        }
                        catch (IllegalArgumentException ex5) {
                            throw a(ex5);
                        }
                        psiElement2 = psiElement2.getNextSibling();
                        continue;
                    }
                    startOffset = textRange.getStartOffset();
                }
                if (!b4) {
                    endOffset = textRange.getEndOffset();
                    if (!s.isEmpty()) {
                        sb.append(s);
                        s = "";
                    }
                    sb.append(psiElement2.getText());
                }
                else if (OCTokenTypes.COMMENTS.contains(elementType)) {
                    s += " ";
                }
                else if (elementType != OCTokenTypes.EOL_ESCAPE) {
                    s += psiElement2.getText();
                }
            }
            psiElement2 = psiElement2.getNextSibling();
        }
        Pair create = null;
        Label_0354: {
            String string;
            try {
                string = sb.toString();
                if (startOffset < 0) {
                    final TextRange textRange2 = TextRange.create(psiElement.getTextRange().getEndOffset(), psiElement.getTextRange().getEndOffset());
                    break Label_0354;
                }
            }
            catch (IllegalArgumentException ex6) {
                throw a(ex6);
            }
            final TextRange textRange2 = TextRange.create(startOffset, endOffset);
            try {
                create = Pair.create((Object)string, (Object)textRange2);
                if (create == null) {
                    throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/psi/impl/OCDirectiveImpl", "getContent"));
                }
            }
            catch (IllegalArgumentException ex7) {
                throw a(ex7);
            }
        }
        return (Pair<String, TextRange>)create;
    }
    
    @Override
    public void accept(@NotNull final OCVisitor ocVisitor) {
        try {
            if (ocVisitor == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "visitor", "com/jetbrains/cidr/lang/psi/impl/OCDirectiveImpl", "accept"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        ocVisitor.visitDirective(this);
    }
    
    private static IllegalArgumentException a(final IllegalArgumentException ex) {
        return ex;
    }
}
