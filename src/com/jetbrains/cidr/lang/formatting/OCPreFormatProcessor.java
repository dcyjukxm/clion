// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.lang.formatting;

import com.intellij.psi.util.PsiTreeUtil;
import com.jetbrains.cidr.lang.psi.OCMessageArgument;
import com.jetbrains.cidr.lang.parser.OCTokenTypes;
import com.jetbrains.cidr.lang.util.OCElementUtil;
import com.intellij.psi.PsiFile;
import com.intellij.psi.PsiElement;
import com.jetbrains.cidr.lang.OCLanguage;
import com.intellij.openapi.util.TextRange;
import org.jetbrains.annotations.NotNull;
import com.intellij.lang.ASTNode;
import com.intellij.psi.impl.source.codeStyle.PreFormatProcessor;

public class OCPreFormatProcessor implements PreFormatProcessor
{
    @NotNull
    @Override
    public TextRange process(@NotNull final ASTNode astNode, @NotNull final TextRange textRange) {
        try {
            if (astNode == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "element", "com/jetbrains/cidr/lang/formatting/OCPreFormatProcessor", "process"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        try {
            if (textRange == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "range", "com/jetbrains/cidr/lang/formatting/OCPreFormatProcessor", "process"));
            }
        }
        catch (IllegalArgumentException ex2) {
            throw a(ex2);
        }
        final PsiElement psi = astNode.getPsi();
        PsiFile containingFile = null;
        Label_0228: {
            Label_0205: {
                TextRange textRange3 = null;
                Label_0170: {
                    Label_0146: {
                        TextRange textRange2 = null;
                        Label_0111: {
                            try {
                                if (psi != null) {
                                    break Label_0146;
                                }
                                textRange2 = textRange;
                                if (textRange2 == null) {
                                    break Label_0111;
                                }
                                return textRange2;
                            }
                            catch (IllegalArgumentException ex3) {
                                throw a(ex3);
                            }
                            try {
                                textRange2 = textRange;
                                if (textRange2 == null) {
                                    throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/formatting/OCPreFormatProcessor", "process"));
                                }
                            }
                            catch (IllegalArgumentException ex4) {
                                throw a(ex4);
                            }
                        }
                        return textRange2;
                        try {
                            if (psi.getLanguage() == OCLanguage.getInstance()) {
                                break Label_0205;
                            }
                            textRange3 = textRange;
                            if (textRange3 == null) {
                                break Label_0170;
                            }
                            return textRange3;
                        }
                        catch (IllegalArgumentException ex5) {
                            throw a(ex5);
                        }
                    }
                    try {
                        textRange3 = textRange;
                        if (textRange3 == null) {
                            throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/formatting/OCPreFormatProcessor", "process"));
                        }
                    }
                    catch (IllegalArgumentException ex6) {
                        throw a(ex6);
                    }
                }
                return textRange3;
                try {
                    if (psi.isValid()) {
                        containingFile = psi.getContainingFile();
                        break Label_0228;
                    }
                }
                catch (IllegalArgumentException ex7) {
                    throw a(ex7);
                }
            }
            containingFile = null;
        }
        final PsiFile psiFile = containingFile;
        TextRange create = null;
        Label_0282: {
            TextRange textRange4 = null;
            Label_0247: {
                try {
                    if (psiFile != null) {
                        break Label_0282;
                    }
                    textRange4 = textRange;
                    if (textRange4 == null) {
                        break Label_0247;
                    }
                    return textRange4;
                }
                catch (IllegalArgumentException ex8) {
                    throw a(ex8);
                }
                try {
                    textRange4 = textRange;
                    if (textRange4 == null) {
                        throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/formatting/OCPreFormatProcessor", "process"));
                    }
                }
                catch (IllegalArgumentException ex9) {
                    throw a(ex9);
                }
            }
            return textRange4;
            try {
                create = TextRange.create(a(psiFile, textRange.getStartOffset(), true), a(psiFile, textRange.getEndOffset(), false));
                if (create == null) {
                    throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/formatting/OCPreFormatProcessor", "process"));
                }
            }
            catch (IllegalArgumentException ex10) {
                throw a(ex10);
            }
        }
        return create;
    }
    
    private static int a(final PsiFile psiFile, int textOffset, final boolean b) {
        int n = 0;
        Label_0016: {
            try {
                if (b) {
                    n = textOffset;
                    break Label_0016;
                }
            }
            catch (IllegalArgumentException ex) {
                throw a(ex);
            }
            n = textOffset - 1;
        }
        PsiElement element = psiFile.findElementAt(n);
        try {
            if (element == null) {
                return textOffset;
            }
        }
        catch (IllegalArgumentException ex2) {
            throw a(ex2);
        }
        while (true) {
            PsiElement psiElement = null;
            Label_0116: {
                Label_0117: {
                    Label_0097: {
                        Label_0079: {
                            Label_0053: {
                                try {
                                    if (OCElementUtil.getElementType(element) != OCTokenTypes.BLOCK_COMMENT) {
                                        break Label_0079;
                                    }
                                    final boolean b2 = b;
                                    if (b2) {
                                        break Label_0053;
                                    }
                                    break Label_0053;
                                }
                                catch (IllegalArgumentException ex3) {
                                    throw a(ex3);
                                }
                                try {
                                    final boolean b2 = b;
                                    if (b2) {
                                        return element.getTextOffset();
                                    }
                                }
                                catch (IllegalArgumentException ex4) {
                                    throw a(ex4);
                                }
                            }
                            final int n2 = element.getTextRange().getEndOffset();
                            textOffset = n2;
                            return textOffset;
                            try {
                                if (element instanceof OCMessageArgument) {
                                    break Label_0117;
                                }
                                final boolean b3 = b;
                                if (b3) {
                                    break Label_0097;
                                }
                                break Label_0117;
                            }
                            catch (IllegalArgumentException ex5) {
                                throw a(ex5);
                            }
                        }
                        try {
                            final boolean b3 = b;
                            if (b3) {
                                psiElement = PsiTreeUtil.getPrevSiblingOfType(element, (Class)OCMessageArgument.class);
                                break Label_0116;
                            }
                        }
                        catch (IllegalArgumentException ex6) {
                            throw a(ex6);
                        }
                    }
                    break Label_0117;
                    while (true) {
                        PsiElement psiElement2 = null;
                        Label_0151: {
                            Label_0132: {
                                try {
                                    if (element == null) {
                                        return textOffset;
                                    }
                                    final boolean b4 = b;
                                    if (b4) {
                                        break Label_0132;
                                    }
                                    break Label_0132;
                                }
                                catch (IllegalArgumentException ex7) {
                                    throw a(ex7);
                                }
                                try {
                                    final boolean b4 = b;
                                    if (b4) {
                                        psiElement2 = element.getPrevSibling();
                                        break Label_0151;
                                    }
                                }
                                catch (IllegalArgumentException ex8) {
                                    throw a(ex8);
                                }
                            }
                            psiElement2 = element.getNextSibling();
                        }
                        final PsiElement psiElement3 = psiElement2;
                        if (psiElement3 != null) {
                            textOffset = psiElement3.getTextOffset();
                        }
                        element = psiElement3;
                    }
                }
                psiElement = PsiTreeUtil.getNextSiblingOfType(element, (Class)OCMessageArgument.class);
            }
            element = psiElement;
            continue;
        }
    }
    
    private static IllegalArgumentException a(final IllegalArgumentException ex) {
        return ex;
    }
}
