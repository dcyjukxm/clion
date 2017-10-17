// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.lang.editor;

import com.intellij.psi.tree.IElementType;
import com.intellij.lang.ASTNode;
import com.jetbrains.cidr.lang.parser.OCElementType;
import com.intellij.psi.PsiManager;
import com.jetbrains.cidr.lang.refactoring.util.OCChangeUtil;
import com.jetbrains.cidr.lang.psi.OCDeclarationStatement;
import com.jetbrains.cidr.lang.util.OCElementFactory;
import com.intellij.psi.codeStyle.CodeStyleManager;
import com.jetbrains.cidr.lang.psi.OCExpression;
import com.intellij.psi.search.SearchScope;
import com.intellij.psi.search.searches.ReferencesSearch;
import com.intellij.psi.search.LocalSearchScope;
import com.jetbrains.cidr.lang.psi.OCExpressionStatement;
import com.jetbrains.cidr.lang.psi.OCAssignmentExpression;
import com.jetbrains.cidr.lang.psi.OCReferenceExpression;
import com.intellij.psi.PsiElement;
import com.jetbrains.cidr.lang.psi.OCReferenceElement;
import com.jetbrains.cidr.lang.psi.OCDeclarator;
import com.jetbrains.cidr.lang.psi.OCDeclaration;
import com.jetbrains.cidr.lang.parser.OCTokenTypes;
import com.intellij.util.IncorrectOperationException;
import com.intellij.psi.PsiFile;
import com.intellij.openapi.editor.Document;
import com.intellij.openapi.diagnostic.Logger;
import com.intellij.codeInsight.editorActions.JoinRawLinesHandlerDelegate;

public class DeclarationJoinLinesHandler implements JoinRawLinesHandlerDelegate
{
    private static final Logger LOG;
    
    public int tryJoinLines(final Document document, final PsiFile psiFile, final int n, final int n2) {
        final PsiElement element = psiFile.findElementAt(n);
        final PsiElement element2 = psiFile.findElementAt(n2);
        Label_0042: {
            try {
                if (element == null) {
                    return -1;
                }
                final PsiElement psiElement = element2;
                if (psiElement == null) {
                    return -1;
                }
                break Label_0042;
            }
            catch (IncorrectOperationException ex) {
                throw a(ex);
            }
            try {
                final PsiElement psiElement = element2;
                if (psiElement == null) {
                    return -1;
                }
            }
            catch (IncorrectOperationException ex2) {
                throw a(ex2);
            }
            try {
                if (element.getNode().getElementType() != OCTokenTypes.SEMICOLON) {
                    return -1;
                }
            }
            catch (IncorrectOperationException ex3) {
                throw a(ex3);
            }
        }
        try {
            if (!(element.getParent() instanceof OCDeclaration)) {
                return -1;
            }
        }
        catch (IncorrectOperationException ex4) {
            throw a(ex4);
        }
        final OCDeclaration ocDeclaration = (OCDeclaration)element.getParent();
        try {
            if (ocDeclaration.getDeclarators().size() != 1) {
                return -1;
            }
        }
        catch (IncorrectOperationException ex5) {
            throw a(ex5);
        }
        final OCDeclarator ocDeclarator = ocDeclaration.getDeclarators().get(0);
        try {
            if (element2.getNode().getElementType() != OCTokenTypes.IDENTIFIER) {
                return -1;
            }
        }
        catch (IncorrectOperationException ex6) {
            throw a(ex6);
        }
        try {
            if (!(element2.getParent() instanceof OCReferenceElement)) {
                return -1;
            }
        }
        catch (IncorrectOperationException ex7) {
            throw a(ex7);
        }
        final OCReferenceElement ocReferenceElement = (OCReferenceElement)element2.getParent();
        final PsiElement resolve = ocReferenceElement.resolve();
        final PsiManager manager = ocReferenceElement.getManager();
        try {
            if (!manager.areElementsEquivalent(resolve, (PsiElement)ocDeclarator)) {
                return -1;
            }
        }
        catch (IncorrectOperationException ex8) {
            throw a(ex8);
        }
        try {
            if (!(ocReferenceElement.getParent() instanceof OCReferenceExpression)) {
                return -1;
            }
        }
        catch (IncorrectOperationException ex9) {
            throw a(ex9);
        }
        final OCReferenceExpression ocReferenceExpression = (OCReferenceExpression)ocReferenceElement.getParent();
        try {
            if (!(ocReferenceExpression.getParent() instanceof OCAssignmentExpression)) {
                return -1;
            }
        }
        catch (IncorrectOperationException ex10) {
            throw a(ex10);
        }
        final OCAssignmentExpression ocAssignmentExpression = (OCAssignmentExpression)ocReferenceExpression.getParent();
        try {
            if (!(ocAssignmentExpression.getParent() instanceof OCExpressionStatement)) {
                return -1;
            }
        }
        catch (IncorrectOperationException ex11) {
            throw a(ex11);
        }
        try {
            if (ReferencesSearch.search((PsiElement)ocDeclarator, (SearchScope)new LocalSearchScope((PsiElement)ocAssignmentExpression.getSourceExpression()), false).findFirst() != null) {
                return -1;
            }
        }
        catch (IncorrectOperationException ex12) {
            throw a(ex12);
        }
        final OCElementType operationSign = ocAssignmentExpression.getOperationSign();
        OCExpression sourceExpression;
        if (operationSign == OCTokenTypes.EQ) {
            sourceExpression = ocAssignmentExpression.getSourceExpression();
        }
        else {
            try {
                if (ocDeclarator.getInitializer() == null) {
                    return -1;
                }
            }
            catch (IncorrectOperationException ex13) {
                throw a(ex13);
            }
            String s = null;
            if (operationSign == OCTokenTypes.ANDEQ) {
                s = "&";
            }
            else if (operationSign == OCTokenTypes.MULTEQ) {
                s = "*";
            }
            else if (operationSign == OCTokenTypes.DIVEQ) {
                s = "/";
            }
            else if (operationSign == OCTokenTypes.GTGTEQ) {
                s = ">>";
            }
            else if (operationSign == OCTokenTypes.LTLTEQ) {
                s = "<<";
            }
            else if (operationSign == OCTokenTypes.MINUSEQ) {
                s = "-";
            }
            else if (operationSign == OCTokenTypes.OREQ) {
                s = "|";
            }
            else if (operationSign == OCTokenTypes.PERCEQ) {
                s = "%";
            }
            else if (operationSign == OCTokenTypes.PLUSEQ) {
                s = "+";
            }
            else if (operationSign == OCTokenTypes.XOREQ) {
                s = "^";
            }
            try {
                sourceExpression = (OCExpression)CodeStyleManager.getInstance(manager).reformat((PsiElement)OCElementFactory.expressionFromText(ocDeclarator.getInitializer().getTextWithMacros() + s + ocAssignmentExpression.getSourceExpression().getTextWithMacros(), (PsiElement)psiFile));
            }
            catch (IncorrectOperationException ex14) {
                DeclarationJoinLinesHandler.LOG.error((Throwable)ex14);
                return -1;
            }
        }
        final OCExpressionStatement ocExpressionStatement = (OCExpressionStatement)ocAssignmentExpression.getParent();
        final int startOffset = ocDeclaration.getTextRange().getStartOffset();
        try {
            final OCDeclarationStatement declarationStatement = OCElementFactory.declarationStatement(ocDeclarator.getName(), ocDeclarator.getType(), sourceExpression, (PsiElement)psiFile);
            final OCDeclarator ocDeclarator2 = declarationStatement.getDeclaration().getDeclarators().get(0);
            final OCDeclarationStatement ocDeclarationStatement = (OCDeclarationStatement)CodeStyleManager.getInstance(manager).reformatRange((PsiElement)declarationStatement, ocDeclarator2.getNameIdentifier().getTextRange().getEndOffset(), ocDeclarator2.getInitializer().getRangeWithMacros().getStartOffset() + 1);
            ocDeclaration.replace((PsiElement)ocDeclarationStatement);
            OCChangeUtil.delete((PsiElement)ocExpressionStatement);
            return startOffset + ocDeclarationStatement.getTextRange().getEndOffset() - ocDeclarationStatement.getTextRange().getStartOffset();
        }
        catch (IncorrectOperationException ex15) {
            DeclarationJoinLinesHandler.LOG.error((Throwable)ex15);
            return -1;
        }
    }
    
    public int tryJoinRawLines(final Document document, final PsiFile psiFile, final int n, final int n2) {
        final PsiElement element = psiFile.findElementAt(n);
        final PsiElement element2 = psiFile.findElementAt(n2);
        Label_0074: {
            Label_0042: {
                try {
                    if (element == null) {
                        return -1;
                    }
                    final PsiElement psiElement = element2;
                    if (psiElement == null) {
                        return -1;
                    }
                    break Label_0042;
                }
                catch (IncorrectOperationException ex) {
                    throw a(ex);
                }
                try {
                    final PsiElement psiElement = element2;
                    if (psiElement == null) {
                        return -1;
                    }
                }
                catch (IncorrectOperationException ex2) {
                    throw a(ex2);
                }
                try {
                    if (element != element2) {
                        return -1;
                    }
                    final PsiElement psiElement2 = element;
                    final ASTNode astNode = psiElement2.getNode();
                    final IElementType elementType = astNode.getElementType();
                    final OCElementType ocElementType = OCTokenTypes.RAW_STRING_LITERAL;
                    if (elementType == ocElementType) {
                        break Label_0074;
                    }
                    return -1;
                }
                catch (IncorrectOperationException ex3) {
                    throw a(ex3);
                }
            }
            try {
                final PsiElement psiElement2 = element;
                final ASTNode astNode = psiElement2.getNode();
                final IElementType elementType = astNode.getElementType();
                final OCElementType ocElementType = OCTokenTypes.RAW_STRING_LITERAL;
                if (elementType == ocElementType) {
                    return element.getTextRange().getEndOffset();
                }
            }
            catch (IncorrectOperationException ex4) {
                throw a(ex4);
            }
        }
        return -1;
    }
    
    static {
        LOG = Logger.getInstance("#com.intellij.codeInsight.editorActions.DeclarationJoinLinesHandler");
    }
    
    private static IncorrectOperationException a(final IncorrectOperationException ex) {
        return ex;
    }
}
