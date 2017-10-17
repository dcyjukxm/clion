// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.lang.editor.completion;

import com.intellij.patterns.ObjectPattern;
import com.jetbrains.cidr.lang.psi.OCDeclarationStatement;
import com.intellij.psi.util.PsiTreeUtil;
import com.jetbrains.cidr.lang.psi.OCExpression;
import com.intellij.patterns.ElementPattern;
import com.intellij.patterns.PsiElementPattern;
import com.intellij.psi.PsiElement;
import com.intellij.codeInsight.template.TemplateContextType;
import org.jetbrains.annotations.NotNull;
import com.jetbrains.cidr.lang.OCLanguageKind;

public static class ExpressionBase extends OCCodeContextType
{
    public ExpressionBase(@NotNull final OCLanguageKind ocLanguageKind) {
        if (ocLanguageKind == null) {
            throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "needKind", "com/jetbrains/cidr/lang/editor/completion/OCCodeContextType$ExpressionBase", "<init>"));
        }
        super(ocLanguageKind, "OC_EXPRESSION" + LanguageInfo.getSuffix(ocLanguageKind), "Expression", LanguageInfo.getBaseContext(ocLanguageKind));
    }
    
    @Override
    protected boolean isInContext(@NotNull final PsiElement psiElement) {
        try {
            if (psiElement == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "element", "com/jetbrains/cidr/lang/editor/completion/OCCodeContextType$ExpressionBase", "isInContext"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw b(ex);
        }
        return a(psiElement);
    }
    
    private static boolean a(final PsiElement psiElement) {
        Label_0076: {
            Label_0051: {
                try {
                    if (((PsiElementPattern.Capture)OCCompletionPatterns.REFERENCE_ELEMENT.andNot((ElementPattern)OCCompletionPatterns.AFTER_DOT)).accepts((Object)psiElement)) {
                        break Label_0051;
                    }
                    final PsiElementPattern.Capture<PsiElement> capture = OCCompletionPatterns.TYPE_IN_DECLARATION;
                    final PsiElementPattern.Capture<PsiElement> capture2 = OCCompletionPatterns.AFTER_DOT;
                    final ObjectPattern objectPattern = capture.andNot((ElementPattern)capture2);
                    final PsiElementPattern.Capture capture3 = (PsiElementPattern.Capture)objectPattern;
                    final PsiElement psiElement2 = psiElement;
                    final boolean b = capture3.accepts((Object)psiElement2);
                    if (!b) {
                        return false;
                    }
                    break Label_0051;
                }
                catch (IllegalArgumentException ex) {
                    throw b(ex);
                }
                try {
                    final PsiElementPattern.Capture<PsiElement> capture = OCCompletionPatterns.TYPE_IN_DECLARATION;
                    final PsiElementPattern.Capture<PsiElement> capture2 = OCCompletionPatterns.AFTER_DOT;
                    final ObjectPattern objectPattern = capture.andNot((ElementPattern)capture2);
                    final PsiElementPattern.Capture capture3 = (PsiElementPattern.Capture)objectPattern;
                    final PsiElement psiElement2 = psiElement;
                    final boolean b = capture3.accepts((Object)psiElement2);
                    if (!b) {
                        return false;
                    }
                }
                catch (IllegalArgumentException ex2) {
                    throw b(ex2);
                }
                try {
                    if (PsiTreeUtil.getParentOfType(psiElement, (Class)OCExpression.class) != null) {
                        break Label_0076;
                    }
                    final PsiElement psiElement3 = psiElement;
                    final Class<OCDeclarationStatement> clazz = OCDeclarationStatement.class;
                    final PsiElement psiElement4 = PsiTreeUtil.getParentOfType(psiElement3, (Class)clazz);
                    if (psiElement4 != null) {
                        break Label_0076;
                    }
                    return false;
                }
                catch (IllegalArgumentException ex3) {
                    throw b(ex3);
                }
            }
            try {
                final PsiElement psiElement3 = psiElement;
                final Class<OCDeclarationStatement> clazz = OCDeclarationStatement.class;
                final PsiElement psiElement4 = PsiTreeUtil.getParentOfType(psiElement3, (Class)clazz);
                if (psiElement4 != null) {
                    return true;
                }
            }
            catch (IllegalArgumentException ex4) {
                throw b(ex4);
            }
        }
        return false;
    }
    
    private static IllegalArgumentException b(final IllegalArgumentException ex) {
        return ex;
    }
}
