// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.lang.editor.surround;

import com.intellij.codeInsight.CodeInsightBundle;
import com.intellij.codeInsight.lookup.LookupElement;
import com.intellij.codeInsight.template.Result;
import com.intellij.codeInsight.template.ExpressionContext;
import com.intellij.codeInsight.template.Expression;
import com.intellij.codeInsight.template.TextResult;
import com.intellij.psi.PsiElement;
import com.intellij.codeInsight.template.Template;
import com.intellij.codeInsight.template.TemplateManager;
import com.intellij.openapi.editor.ScrollType;
import com.intellij.openapi.util.TextRange;
import com.intellij.openapi.editor.Editor;
import com.intellij.openapi.project.Project;
import com.intellij.util.IncorrectOperationException;
import com.jetbrains.cidr.lang.psi.OCExpression;

class OCaWithCastSurrounder extends OCExpressionSurrounder
{
    static final /* synthetic */ boolean $assertionsDisabled;
    
    @Override
    public boolean isApplicable(final OCExpression ocExpression) {
        try {
            if (!ocExpression.getType().isVoid()) {
                return true;
            }
        }
        catch (IncorrectOperationException ex) {
            throw c(ex);
        }
        return false;
    }
    
    @Override
    public TextRange surroundExpression(final Project project, final Editor editor, final OCExpression ocExpression) throws IncorrectOperationException {
        Label_0022: {
            try {
                if (OCaWithCastSurrounder.$assertionsDisabled) {
                    break Label_0022;
                }
                final OCExpression ocExpression2 = ocExpression;
                final boolean b = ocExpression2.isValid();
                if (!b) {
                    break Label_0022;
                }
                break Label_0022;
            }
            catch (IncorrectOperationException ex) {
                throw c(ex);
            }
            try {
                final OCExpression ocExpression2 = ocExpression;
                final boolean b = ocExpression2.isValid();
                if (!b) {
                    throw new AssertionError();
                }
            }
            catch (IncorrectOperationException ex2) {
                throw c(ex2);
            }
        }
        final Template a = a(project, ocExpression);
        final TextRange rangeWithMacros = ocExpression.getRangeWithMacros();
        editor.getDocument().deleteString(rangeWithMacros.getStartOffset(), rangeWithMacros.getEndOffset());
        editor.getCaretModel().moveToOffset(rangeWithMacros.getStartOffset());
        editor.getScrollingModel().scrollToCaret(ScrollType.RELATIVE);
        TemplateManager.getInstance(project).startTemplate(editor, a);
        return null;
    }
    
    private static Template a(final Project project, final OCExpression ocExpression) {
        final String textWithMacros = ocExpression.getTextWithMacros();
        final Template template = TemplateManager.getInstance(project).createTemplate("", "");
        template.setToReformat(true);
        template.addTextSegment("((");
        int i = 0;
        String s;
        for (s = ocExpression.getType().getGuessedType().getCanonicalName((PsiElement)ocExpression); s.endsWith("*"); s = s.substring(0, s.length() - 1).trim()) {
            ++i;
        }
        template.addVariable("type", new Expression() {
            final /* synthetic */ TextResult val$result = new TextResult(s);
            
            public Result calculateResult(final ExpressionContext expressionContext) {
                return (Result)this.val$result;
            }
            
            public Result calculateQuickResult(final ExpressionContext expressionContext) {
                return this.calculateResult(expressionContext);
            }
            
            public LookupElement[] calculateLookupItems(final ExpressionContext expressionContext) {
                return null;
            }
        }, true);
        final StringBuilder sb = new StringBuilder();
        try {
            while (i > 0) {
                sb.append('*');
                --i;
            }
        }
        catch (IncorrectOperationException ex) {
            throw c(ex);
        }
        sb.append(")").append(textWithMacros).append(")");
        template.addTextSegment(sb.toString());
        template.addEndVariable();
        return template;
    }
    
    public String getTemplateDescription() {
        return CodeInsightBundle.message("surround.with.cast.template", new Object[0]);
    }
    
    static {
        boolean $assertionsDisabled2 = false;
        Label_0017: {
            try {
                if (!OCaWithCastSurrounder.class.desiredAssertionStatus()) {
                    $assertionsDisabled2 = true;
                    break Label_0017;
                }
            }
            catch (IncorrectOperationException ex) {
                throw c(ex);
            }
            $assertionsDisabled2 = false;
        }
        $assertionsDisabled = $assertionsDisabled2;
    }
    
    private static IncorrectOperationException c(final IncorrectOperationException ex) {
        return ex;
    }
}
