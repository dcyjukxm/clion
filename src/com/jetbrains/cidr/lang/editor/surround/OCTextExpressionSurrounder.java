// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.lang.editor.surround;

import org.jetbrains.annotations.NotNull;
import com.intellij.util.IncorrectOperationException;
import com.intellij.psi.PsiElement;
import com.jetbrains.cidr.lang.util.OCElementFactory;
import com.intellij.psi.codeStyle.CodeStyleManager;
import com.intellij.openapi.util.TextRange;
import com.jetbrains.cidr.lang.psi.OCExpression;
import com.intellij.openapi.editor.Editor;
import com.intellij.openapi.project.Project;

public abstract class OCTextExpressionSurrounder extends OCExpressionSurrounder
{
    @Override
    public TextRange surroundExpression(final Project project, final Editor editor, OCExpression ocExpression) throws IncorrectOperationException {
        ocExpression = (OCExpression)ocExpression.replace((PsiElement)CodeStyleManager.getInstance(project).reformat((PsiElement)OCElementFactory.expressionFromText(this.getBeforeText() + ocExpression.getTextWithMacros() + this.getAfterText(), (PsiElement)ocExpression)));
        final int endOffset = ocExpression.getTextRange().getEndOffset();
        return new TextRange(endOffset, endOffset);
    }
    
    @NotNull
    protected abstract String getBeforeText();
    
    @NotNull
    protected abstract String getAfterText();
}
