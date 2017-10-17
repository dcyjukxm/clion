// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.lang.formatting;

import org.jetbrains.annotations.Contract;
import com.jetbrains.cidr.lang.parser.OCPunctuatorElementType;
import com.intellij.psi.tree.IElementType;
import com.jetbrains.cidr.lang.psi.OCMethodSelectorPart;
import com.intellij.psi.util.PsiTreeUtil;
import com.jetbrains.cidr.lang.psi.OCMessageArgument;
import com.jetbrains.cidr.lang.psi.OCArgumentSelector;
import com.jetbrains.cidr.lang.parser.OCTokenTypes;
import com.jetbrains.cidr.lang.util.OCElementUtil;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import com.intellij.openapi.editor.Document;
import com.intellij.lang.ASTNode;
import com.intellij.openapi.util.Pair;
import com.intellij.formatting.IndentInfo;
import com.intellij.psi.formatter.FormatterUtil;
import com.intellij.psi.PsiElement;
import com.jetbrains.cidr.lang.settings.OCCodeStyleSettings;
import com.intellij.psi.codeStyle.CodeStyleSettings;
import com.intellij.openapi.editor.Editor;
import com.intellij.psi.PsiFile;

public static class MethodColonHandler implements Handler
{
    @Override
    public boolean handle(final char c, final PsiFile psiFile, final Editor editor, final CodeStyleSettings codeStyleSettings) {
        try {
            if (c != ':') {
                return false;
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        final Pair<PsiElement, PsiElement> a = a(psiFile.findElementAt(editor.getCaretModel().getOffset() - 1), (OCCodeStyleSettings)codeStyleSettings.getCustomSettings((Class)OCCodeStyleSettings.class));
        try {
            if (a == null) {
                return false;
            }
        }
        catch (IllegalArgumentException ex2) {
            throw a(ex2);
        }
        final PsiElement psiElement = (PsiElement)a.first;
        final PsiElement psiElement2 = (PsiElement)a.second;
        final ASTNode previousNonWhitespaceLeaf = FormatterUtil.getPreviousNonWhitespaceLeaf(psiElement2.getNode());
        try {
            if (previousNonWhitespaceLeaf == null) {
                return false;
            }
        }
        catch (IllegalArgumentException ex3) {
            throw a(ex3);
        }
        final Document document = editor.getDocument();
        final int lineNumber = document.getLineNumber(psiElement.getTextOffset());
        final int lineNumber2 = document.getLineNumber(psiElement2.getTextOffset());
        final int lineNumber3 = document.getLineNumber(previousNonWhitespaceLeaf.getTextRange().getEndOffset());
        try {
            if (lineNumber3 == lineNumber2) {
                return false;
            }
        }
        catch (IllegalArgumentException ex4) {
            throw a(ex4);
        }
        document.replaceString(document.getLineStartOffset(lineNumber2), psiElement2.getTextOffset(), (CharSequence)new IndentInfo(0, Math.max(0, psiElement.getTextRange().getEndOffset() - document.getLineStartOffset(lineNumber) - psiElement2.getTextLength()), 0).generateNewWhiteSpace(codeStyleSettings.getIndentOptions(psiFile.getFileType())));
        return true;
    }
    
    @Nullable
    private static Pair<PsiElement, PsiElement> a(@Nullable final PsiElement psiElement, @NotNull final OCCodeStyleSettings ocCodeStyleSettings) {
        try {
            if (ocCodeStyleSettings == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "settings", "com/jetbrains/cidr/lang/formatting/OCAutoFormatTypedHandler$MethodColonHandler", "findFirstAndCurrentSelectors"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        Label_0065: {
            try {
                if (psiElement == null) {
                    break Label_0065;
                }
                final PsiElement psiElement2 = psiElement;
                final IElementType elementType = OCElementUtil.getElementType(psiElement2);
                final OCPunctuatorElementType ocPunctuatorElementType = OCTokenTypes.COLON;
                if (elementType != ocPunctuatorElementType) {
                    break Label_0065;
                }
                break Label_0065;
            }
            catch (IllegalArgumentException ex2) {
                throw a(ex2);
            }
            try {
                final PsiElement psiElement2 = psiElement;
                final IElementType elementType = OCElementUtil.getElementType(psiElement2);
                final OCPunctuatorElementType ocPunctuatorElementType = OCTokenTypes.COLON;
                if (elementType != ocPunctuatorElementType) {
                    return null;
                }
            }
            catch (IllegalArgumentException ex3) {
                throw a(ex3);
            }
        }
        PsiElement psiElement3 = psiElement.getParent();
        PsiElement psiElement4 = null;
        Label_0216: {
            Label_0201: {
                Label_0142: {
                    try {
                        if (!(psiElement3 instanceof OCArgumentSelector) || !ocCodeStyleSettings.METHOD_CALL_ARGUMENTS_ALIGN_BY_COLONS) {
                            break Label_0142;
                        }
                    }
                    catch (IllegalArgumentException ex4) {
                        throw a(ex4);
                    }
                    PsiElement psiElement5 = psiElement3.getParent();
                    do {
                        psiElement5 = psiElement5.getPrevSibling();
                        if (psiElement5 instanceof OCMessageArgument) {
                            psiElement4 = PsiTreeUtil.findChildOfType(psiElement5, (Class)OCArgumentSelector.class);
                        }
                    } while (psiElement5 != null);
                    break Label_0201;
                    try {
                        if (!(psiElement3 instanceof OCMethodSelectorPart) || !ocCodeStyleSettings.METHOD_PARAMETERS_ALIGN_BY_COLONS) {
                            break Label_0201;
                        }
                    }
                    catch (IllegalArgumentException ex5) {
                        throw a(ex5);
                    }
                }
                PsiElement prevSibling = psiElement3;
                do {
                    prevSibling = prevSibling.getPrevSibling();
                    if (prevSibling instanceof OCMethodSelectorPart) {
                        psiElement4 = prevSibling;
                    }
                } while (prevSibling != null);
                psiElement4 = a(psiElement4);
                psiElement3 = a(psiElement3);
                try {
                    if (psiElement4 == null) {
                        break Label_0216;
                    }
                    final PsiElement psiElement6 = psiElement3;
                    if (psiElement6 == null) {
                        break Label_0216;
                    }
                    return (Pair<PsiElement, PsiElement>)Pair.create((Object)psiElement4, (Object)psiElement3);
                }
                catch (IllegalArgumentException ex6) {
                    throw a(ex6);
                }
            }
            try {
                final PsiElement psiElement6 = psiElement3;
                if (psiElement6 == null) {
                    final Pair create = null;
                    return (Pair<PsiElement, PsiElement>)create;
                }
            }
            catch (IllegalArgumentException ex7) {
                throw a(ex7);
            }
        }
        final Pair create = Pair.create((Object)psiElement4, (Object)psiElement3);
        return (Pair<PsiElement, PsiElement>)create;
    }
    
    @Contract("null -> null")
    @Nullable
    private static PsiElement a(@Nullable final PsiElement psiElement) {
        try {
            if (psiElement == null) {
                return null;
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        final ASTNode childByType = psiElement.getNode().findChildByType((IElementType)OCTokenTypes.IDENTIFIER);
        Label_0051: {
            try {
                if (childByType == null) {
                    break Label_0051;
                }
                final ASTNode astNode = childByType;
                final ASTNode astNode2 = astNode.getTreeNext();
                final IElementType elementType = OCElementUtil.getElementType(astNode2);
                final OCPunctuatorElementType ocPunctuatorElementType = OCTokenTypes.COLON;
                if (elementType != ocPunctuatorElementType) {
                    break Label_0051;
                }
                return childByType.getPsi();
            }
            catch (IllegalArgumentException ex2) {
                throw a(ex2);
            }
            try {
                final ASTNode astNode = childByType;
                final ASTNode astNode2 = astNode.getTreeNext();
                final IElementType elementType = OCElementUtil.getElementType(astNode2);
                final OCPunctuatorElementType ocPunctuatorElementType = OCTokenTypes.COLON;
                if (elementType != ocPunctuatorElementType) {
                    return null;
                }
            }
            catch (IllegalArgumentException ex3) {
                throw a(ex3);
            }
        }
        return childByType.getPsi();
    }
    
    private static IllegalArgumentException a(final IllegalArgumentException ex) {
        return ex;
    }
}
