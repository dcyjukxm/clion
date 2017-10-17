// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.lang.editor.completion;

import com.intellij.patterns.ObjectPattern;
import com.jetbrains.cidr.lang.psi.OCDeclarationStatement;
import com.jetbrains.cidr.lang.psi.OCExpression;
import com.intellij.patterns.ElementPattern;
import com.intellij.patterns.PsiElementPattern;
import com.intellij.psi.impl.source.tree.LeafElement;
import com.intellij.lang.ASTNode;
import com.intellij.psi.impl.source.tree.TreeUtil;
import com.intellij.psi.util.PsiTreeUtil;
import com.jetbrains.cidr.lang.psi.OCExpressionStatement;
import com.intellij.psi.PsiElement;
import com.intellij.codeInsight.template.TemplateContextType;
import org.jetbrains.annotations.NotNull;
import com.jetbrains.cidr.lang.OCLanguageKind;

public static class StatementBase extends OCCodeContextType
{
    public StatementBase(@NotNull final OCLanguageKind ocLanguageKind) {
        if (ocLanguageKind == null) {
            throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "needKind", "com/jetbrains/cidr/lang/editor/completion/OCCodeContextType$StatementBase", "<init>"));
        }
        super(ocLanguageKind, "OC_STATEMENT" + LanguageInfo.getSuffix(ocLanguageKind), "Statement", LanguageInfo.getBaseContext(ocLanguageKind));
    }
    
    @Override
    protected boolean isInContext(@NotNull final PsiElement psiElement) {
        try {
            if (psiElement == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "element", "com/jetbrains/cidr/lang/editor/completion/OCCodeContextType$StatementBase", "isInContext"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw b(ex);
        }
        return a(psiElement);
    }
    
    private static boolean a(final PsiElement psiElement) {
        try {
            if (!a(psiElement)) {
                return false;
            }
        }
        catch (IllegalArgumentException ex) {
            throw b(ex);
        }
        try {
            if (OCCompletionPatterns.AT_STATEMENT_LEVEL.accepts((Object)psiElement)) {
                return true;
            }
        }
        catch (IllegalArgumentException ex2) {
            throw b(ex2);
        }
        final OCExpressionStatement ocExpressionStatement = (OCExpressionStatement)PsiTreeUtil.getParentOfType(psiElement, (Class)OCExpressionStatement.class, true);
        try {
            if (ocExpressionStatement == null) {
                return false;
            }
            final OCExpressionStatement ocExpressionStatement2 = ocExpressionStatement;
            final ASTNode astNode = ocExpressionStatement2.getNode();
            final LeafElement leafElement = TreeUtil.findFirstLeaf(astNode);
            final PsiElement psiElement2 = psiElement;
            final ASTNode astNode2 = psiElement2.getNode();
            if (leafElement == astNode2) {
                return true;
            }
            return false;
        }
        catch (IllegalArgumentException ex3) {
            throw b(ex3);
        }
        try {
            final OCExpressionStatement ocExpressionStatement2 = ocExpressionStatement;
            final ASTNode astNode = ocExpressionStatement2.getNode();
            final LeafElement leafElement = TreeUtil.findFirstLeaf(astNode);
            final PsiElement psiElement2 = psiElement;
            final ASTNode astNode2 = psiElement2.getNode();
            if (leafElement == astNode2) {
                return true;
            }
        }
        catch (IllegalArgumentException ex4) {
            throw b(ex4);
        }
        return false;
    }
    
    private static IllegalArgumentException b(final IllegalArgumentException ex) {
        return ex;
    }
}
