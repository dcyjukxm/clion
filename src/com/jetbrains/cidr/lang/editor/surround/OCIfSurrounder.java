// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.lang.editor.surround;

import com.intellij.util.IncorrectOperationException;
import com.intellij.openapi.editor.Editor;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import com.jetbrains.cidr.lang.psi.OCStatement;
import com.jetbrains.cidr.lang.psi.OCDeclarationOrExpression;
import com.jetbrains.cidr.lang.refactoring.util.OCChangeUtil;
import com.intellij.openapi.util.TextRange;
import com.intellij.psi.PsiElement;
import com.intellij.openapi.project.Project;
import com.jetbrains.cidr.lang.psi.OCIfStatement;

public class OCIfSurrounder extends OCStatementSurrounder<OCIfStatement>
{
    @Override
    protected String getStatementTemplate(final Project project, final PsiElement psiElement) {
        return "if (a) {\n}";
    }
    
    @Override
    protected PsiElement getInsertionPlace(final OCIfStatement ocIfStatement) {
        return (PsiElement)ocIfStatement.getThenBranch();
    }
    
    @Override
    protected TextRange getSelectionRange(final OCIfStatement ocIfStatement) {
        final OCDeclarationOrExpression condition = ocIfStatement.getCondition();
        final int startOffset = condition.getRangeWithMacros().getStartOffset();
        OCChangeUtil.delete((PsiElement)condition);
        return new TextRange(startOffset, startOffset);
    }
    
    public String getTemplateDescription() {
        return "if";
    }
}
