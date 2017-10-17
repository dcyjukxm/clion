// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.lang.editor.surround;

import java.util.List;
import com.intellij.psi.search.SearchScope;
import com.intellij.psi.search.searches.ReferencesSearch;
import com.jetbrains.cidr.lang.search.scopes.OCSearchScope;
import com.intellij.psi.PsiReference;
import com.jetbrains.cidr.lang.psi.OCExpression;
import java.util.Iterator;
import com.intellij.openapi.project.Project;
import java.util.Collection;
import com.intellij.psi.util.PsiUtilCore;
import java.util.Collections;
import com.intellij.util.IncorrectOperationException;
import com.jetbrains.cidr.lang.refactoring.util.OCChangeUtil;
import com.jetbrains.cidr.lang.psi.OCAssignmentExpression;
import com.jetbrains.cidr.lang.util.OCElementFactory;
import com.intellij.psi.codeStyle.CodeStyleManager;
import com.jetbrains.cidr.lang.psi.OCExpressionStatement;
import com.jetbrains.cidr.lang.psi.OCDeclarator;
import com.jetbrains.cidr.lang.psi.OCDeclarationStatement;
import java.util.ArrayList;
import com.intellij.psi.PsiElement;
import com.intellij.openapi.diagnostic.Logger;

public class OCSurroundUtil
{
    private static final Logger LOG;
    
    static PsiElement[] moveDeclarationsOut(final PsiElement psiElement, final PsiElement[] array, final boolean b) {
        try {
            final Project project = psiElement.getManager().getProject();
            final ArrayList<OCDeclarationStatement> list = new ArrayList<OCDeclarationStatement>();
            for (final PsiElement psiElement2 : array) {
                Label_0472: {
                    if (psiElement2 instanceof OCDeclarationStatement) {
                        final OCDeclarationStatement ocDeclarationStatement = (OCDeclarationStatement)psiElement2;
                        if (a(psiElement, array, ocDeclarationStatement)) {
                            for (final PsiElement psiElement3 : ocDeclarationStatement.getDeclaration().getDeclarators()) {
                                final OCDeclarator ocDeclarator = (OCDeclarator)psiElement3;
                                final OCExpression initializer = ocDeclarator.getInitializer();
                                if (initializer != null) {
                                    final OCExpressionStatement ocExpressionStatement = (OCExpressionStatement)CodeStyleManager.getInstance(project).reformat((PsiElement)OCElementFactory.statementFromText(ocDeclarator.getName() + "=x;", psiElement3));
                                    OCChangeUtil.replaceHandlingMacros((PsiElement)((OCAssignmentExpression)ocExpressionStatement.getExpression()).getSourceExpression(), (PsiElement)initializer);
                                    list.add((OCDeclarationStatement)psiElement.addAfter((PsiElement)ocExpressionStatement, (PsiElement)ocDeclarationStatement));
                                }
                            }
                            OCDeclarationStatement ocDeclarationStatement2;
                            if (!list.isEmpty()) {
                                ocDeclarationStatement2 = (OCDeclarationStatement)psiElement.addBefore((PsiElement)ocDeclarationStatement, (PsiElement)list.get(0));
                                OCChangeUtil.delete((PsiElement)ocDeclarationStatement);
                            }
                            else {
                                ocDeclarationStatement2 = ocDeclarationStatement;
                            }
                            for (final PsiElement psiElement4 : ocDeclarationStatement2.getDeclaration().getDeclarators()) {
                                final OCDeclarator ocDeclarator2 = (OCDeclarator)psiElement4;
                                final OCExpression initializer2 = ocDeclarator2.getInitializer();
                                Label_0370: {
                                    try {
                                        if (initializer2 == null) {
                                            continue;
                                        }
                                        final boolean b2 = b;
                                        if (!b2) {
                                            break Label_0370;
                                        }
                                        break Label_0370;
                                    }
                                    catch (IncorrectOperationException ex) {
                                        throw a(ex);
                                    }
                                    try {
                                        final boolean b2 = b;
                                        if (!b2) {
                                            OCChangeUtil.delete((PsiElement)initializer2);
                                            continue;
                                        }
                                    }
                                    catch (IncorrectOperationException ex2) {
                                        throw a(ex2);
                                    }
                                }
                                OCChangeUtil.replaceHandlingMacros((PsiElement)ocDeclarator2, (PsiElement)((OCDeclarationStatement)OCElementFactory.statementFromText(OCElementFactory.declarationText(Collections.emptyList(), ocDeclarator2.getName(), ocDeclarator2.getType(), ocDeclarator2.getResolvedType().getDefaultValue(psiElement4), psiElement4, null, false), psiElement)).getDeclaration().getDeclarators().get(0));
                            }
                            break Label_0472;
                        }
                    }
                    list.add((OCDeclarationStatement)psiElement2);
                }
            }
            return PsiUtilCore.toPsiElementArray((Collection)list);
        }
        catch (IncorrectOperationException ex3) {
            OCSurroundUtil.LOG.error((Throwable)ex3);
            return array;
        }
    }
    
    private static boolean a(final PsiElement psiElement, final PsiElement[] array, final OCDeclarationStatement ocDeclarationStatement) {
        final List<OCDeclarator> declarators = ocDeclarationStatement.getDeclaration().getDeclarators();
        final int endOffset = array[array.length - 1].getTextRange().getEndOffset();
        for (final PsiElement psiElement2 : declarators) {
            if (psiElement2 instanceof OCDeclarator) {
                final PsiReference[] array2 = (PsiReference[])ReferencesSearch.search(psiElement2, (SearchScope)OCSearchScope.getProjectSourcesScope(psiElement2.getProject()), false).toArray((Object[])PsiReference.EMPTY_ARRAY);
                if (array2.length <= 0) {
                    continue;
                }
                final PsiReference psiReference = array2[array2.length - 1];
                try {
                    if (psiReference.getElement().getTextOffset() > endOffset) {
                        return true;
                    }
                    continue;
                }
                catch (IncorrectOperationException ex) {
                    throw a(ex);
                }
            }
        }
        return false;
    }
    
    static {
        LOG = Logger.getInstance("#com.jetbrains.cidr.lang.editor.surround.OCSurroundUtil");
    }
    
    private static IncorrectOperationException a(final IncorrectOperationException ex) {
        return ex;
    }
}
