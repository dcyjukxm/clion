// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.lang.resolve;

import com.intellij.openapi.util.Pair;
import com.jetbrains.cidr.lang.preprocessor.OCInclusionContext;
import com.intellij.psi.impl.source.tree.ForeignLeafPsiElement;
import com.jetbrains.cidr.lang.symbols.OCSymbolOffsetUtil;
import com.jetbrains.cidr.lang.util.OCElementUtil;
import com.intellij.lang.ASTNode;
import com.intellij.psi.impl.source.codeStyle.CodeEditUtil;
import com.intellij.psi.tree.IElementType;
import com.jetbrains.cidr.lang.parser.OCTokenTypes;
import com.jetbrains.cidr.lang.refactoring.util.OCChangeUtil;
import com.intellij.psi.PsiElement;
import com.jetbrains.cidr.lang.util.OCElementFactory;
import com.jetbrains.cidr.lang.psi.impl.OCImportModuleStatementImpl;
import com.intellij.util.IncorrectOperationException;
import com.intellij.openapi.util.TextRange;
import org.jetbrains.annotations.NotNull;
import com.jetbrains.cidr.lang.psi.impl.OCIncludeDirectiveImpl;
import com.intellij.psi.AbstractElementManipulator;

public class OCImportManipulator extends AbstractElementManipulator<OCIncludeDirectiveImpl>
{
    public OCIncludeDirectiveImpl handleContentChange(@NotNull final OCIncludeDirectiveImpl ocIncludeDirectiveImpl, @NotNull final TextRange textRange, final String s) throws IncorrectOperationException {
        try {
            if (ocIncludeDirectiveImpl == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "element", "com/jetbrains/cidr/lang/resolve/OCImportManipulator", "handleContentChange"));
            }
        }
        catch (IncorrectOperationException ex) {
            throw a(ex);
        }
        try {
            if (textRange == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "range", "com/jetbrains/cidr/lang/resolve/OCImportManipulator", "handleContentChange"));
            }
        }
        catch (IncorrectOperationException ex2) {
            throw a(ex2);
        }
        if (ocIncludeDirectiveImpl instanceof OCImportModuleStatementImpl) {
            return (OCIncludeDirectiveImpl)OCChangeUtil.replaceHandlingMacros((PsiElement)ocIncludeDirectiveImpl, OCElementFactory.topLevelDeclarationFromText("@import " + s + ";", (PsiElement)ocIncludeDirectiveImpl));
        }
        final ASTNode childByType = ocIncludeDirectiveImpl.getNode().findChildByType((IElementType)OCTokenTypes.HEADER_PATH_LITERAL);
        if (childByType != null) {
            CodeEditUtil.replaceChild(ocIncludeDirectiveImpl.getNode(), childByType, OCElementFactory.topLevelDeclarationFromText("#import " + s, (PsiElement)ocIncludeDirectiveImpl).getNode().findChildByType((IElementType)OCTokenTypes.HEADER_PATH_LITERAL));
        }
        return ocIncludeDirectiveImpl;
    }
    
    @NotNull
    public TextRange getRangeInElement(@NotNull final OCIncludeDirectiveImpl ocIncludeDirectiveImpl) {
        try {
            if (ocIncludeDirectiveImpl == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "element", "com/jetbrains/cidr/lang/resolve/OCImportManipulator", "getRangeInElement"));
            }
        }
        catch (IncorrectOperationException ex) {
            throw a(ex);
        }
        Label_0100: {
            TextRange textRange = null;
            Label_0065: {
                try {
                    if (!OCElementUtil.isPartOfMacroSubstitution((PsiElement)ocIncludeDirectiveImpl)) {
                        break Label_0100;
                    }
                    textRange = TextRange.EMPTY_RANGE;
                    if (textRange == null) {
                        break Label_0065;
                    }
                    return textRange;
                }
                catch (IncorrectOperationException ex2) {
                    throw a(ex2);
                }
                try {
                    textRange = TextRange.EMPTY_RANGE;
                    if (textRange == null) {
                        throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/resolve/OCImportManipulator", "getRangeInElement"));
                    }
                }
                catch (IncorrectOperationException ex3) {
                    throw a(ex3);
                }
            }
            return textRange;
        }
        final ASTNode node = ocIncludeDirectiveImpl.getNode();
        if (ocIncludeDirectiveImpl instanceof OCImportModuleStatementImpl) {
            ASTNode astNode = node.findChildByType((IElementType)OCTokenTypes.IDENTIFIER);
            Label_0184: {
                TextRange textRange3 = null;
                Label_0149: {
                    try {
                        if (astNode != null) {
                            break Label_0184;
                        }
                        final TextRange textRange2 = TextRange.EMPTY_RANGE;
                        final ASTNode astNode2 = node;
                        final int n = astNode2.getTextLength();
                        textRange3 = textRange2.shiftRight(n);
                        if (textRange3 == null) {
                            break Label_0149;
                        }
                        return textRange3;
                    }
                    catch (IncorrectOperationException ex4) {
                        throw a(ex4);
                    }
                    try {
                        final TextRange textRange2 = TextRange.EMPTY_RANGE;
                        final ASTNode astNode2 = node;
                        final int n = astNode2.getTextLength();
                        textRange3 = textRange2.shiftRight(n);
                        if (textRange3 == null) {
                            throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/resolve/OCImportManipulator", "getRangeInElement"));
                        }
                    }
                    catch (IncorrectOperationException ex5) {
                        throw a(ex5);
                    }
                }
                return textRange3;
            }
            int n2 = OCSymbolOffsetUtil.getTextOffsetInTopMacroSubstitution(astNode.getPsi()) - node.getStartOffset();
            while (true) {
                Label_0254: {
                    try {
                        if (astNode == null) {
                            break;
                        }
                        if (astNode.getElementType() != OCTokenTypes.IDENTIFIER) {
                            break Label_0254;
                        }
                    }
                    catch (IncorrectOperationException ex6) {
                        throw a(ex6);
                    }
                    n2 = OCSymbolOffsetUtil.getTextOffsetInTopMacroSubstitution(astNode.getPsi()) + astNode.getTextLength() - node.getStartOffset();
                }
                astNode = astNode.getTreeNext();
            }
            TextRange textRange4;
            try {
                textRange4 = new TextRange(n2, n2);
                if (textRange4 == null) {
                    throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/resolve/OCImportManipulator", "getRangeInElement"));
                }
            }
            catch (IncorrectOperationException ex7) {
                throw a(ex7);
            }
            return textRange4;
        }
        final ASTNode childByType = node.findChildByType((IElementType)OCTokenTypes.HEADER_PATH_LITERAL);
        Label_0460: {
            TextRange textRange8 = null;
            Label_0425: {
                Label_0386: {
                    TextRange textRange6 = null;
                    Label_0351: {
                        try {
                            if (childByType != null) {
                                break Label_0386;
                            }
                            final TextRange textRange5 = TextRange.EMPTY_RANGE;
                            final ASTNode astNode3 = node;
                            final int n3 = astNode3.getTextLength();
                            textRange6 = textRange5.shiftRight(n3);
                            if (textRange6 == null) {
                                break Label_0351;
                            }
                            return textRange6;
                        }
                        catch (IncorrectOperationException ex8) {
                            throw a(ex8);
                        }
                        try {
                            final TextRange textRange5 = TextRange.EMPTY_RANGE;
                            final ASTNode astNode3 = node;
                            final int n3 = astNode3.getTextLength();
                            textRange6 = textRange5.shiftRight(n3);
                            if (textRange6 == null) {
                                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/resolve/OCImportManipulator", "getRangeInElement"));
                            }
                        }
                        catch (IncorrectOperationException ex9) {
                            throw a(ex9);
                        }
                    }
                    return textRange6;
                    try {
                        if (!(childByType instanceof ForeignLeafPsiElement)) {
                            break Label_0460;
                        }
                        final OCIncludeDirectiveImpl ocIncludeDirectiveImpl2 = ocIncludeDirectiveImpl;
                        final boolean b = true;
                        final Pair<String, TextRange> pair = ocIncludeDirectiveImpl2.getContent(b);
                        final Object o = pair.second;
                        final TextRange textRange7 = (TextRange)o;
                        final ASTNode astNode4 = node;
                        final int n4 = astNode4.getStartOffset();
                        final int n5 = -n4;
                        textRange8 = textRange7.shiftRight(n5);
                        if (textRange8 == null) {
                            break Label_0425;
                        }
                        return textRange8;
                    }
                    catch (IncorrectOperationException ex10) {
                        throw a(ex10);
                    }
                }
                try {
                    final OCIncludeDirectiveImpl ocIncludeDirectiveImpl2 = ocIncludeDirectiveImpl;
                    final boolean b = true;
                    final Pair<String, TextRange> pair = ocIncludeDirectiveImpl2.getContent(b);
                    final Object o = pair.second;
                    final TextRange textRange7 = (TextRange)o;
                    final ASTNode astNode4 = node;
                    final int n4 = astNode4.getStartOffset();
                    final int n5 = -n4;
                    textRange8 = textRange7.shiftRight(n5);
                    if (textRange8 == null) {
                        throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/resolve/OCImportManipulator", "getRangeInElement"));
                    }
                }
                catch (IncorrectOperationException ex11) {
                    throw a(ex11);
                }
            }
            return textRange8;
        }
        final int n6 = childByType.getStartOffset() - node.getStartOffset();
        final String text = childByType.getText();
        TextRange empty_RANGE = null;
        switch (text.length() - OCInclusionContext.extractPath(text, false).getPath().length()) {
            case 1: {
                empty_RANGE = new TextRange(1, text.length());
                break;
            }
            case 2: {
                empty_RANGE = new TextRange(1, text.length() - 1);
                break;
            }
            default: {
                empty_RANGE = TextRange.EMPTY_RANGE;
                break;
            }
        }
        TextRange shiftRight;
        try {
            shiftRight = empty_RANGE.shiftRight(n6);
            if (shiftRight == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/resolve/OCImportManipulator", "getRangeInElement"));
            }
        }
        catch (IncorrectOperationException ex12) {
            throw a(ex12);
        }
        return shiftRight;
    }
    
    private static IncorrectOperationException a(final IncorrectOperationException ex) {
        return ex;
    }
}
