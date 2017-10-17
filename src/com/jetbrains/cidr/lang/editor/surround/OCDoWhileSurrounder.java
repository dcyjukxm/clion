// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.lang.editor.surround;

import com.intellij.util.IncorrectOperationException;
import com.intellij.openapi.editor.Editor;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import com.jetbrains.cidr.lang.psi.OCStatement;
import com.jetbrains.cidr.lang.psi.OCExpression;
import com.jetbrains.cidr.lang.refactoring.util.OCChangeUtil;
import com.intellij.openapi.util.TextRange;
import com.intellij.psi.PsiElement;
import com.intellij.openapi.project.Project;
import com.jetbrains.cidr.lang.psi.OCDoWhileStatement;

public class OCDoWhileSurrounder extends OCStatementSurrounder<OCDoWhileStatement>
{
    @Override
    protected String getStatementTemplate(final Project project, final PsiElement psiElement) {
        return "do {\n} while (a);";
    }
    
    @Override
    protected PsiElement getInsertionPlace(final OCDoWhileStatement ocDoWhileStatement) {
        return (PsiElement)ocDoWhileStatement.getBody();
    }
    
    @Override
    protected TextRange getSelectionRange(final OCDoWhileStatement ocDoWhileStatement) {
        final OCExpression condition = ocDoWhileStatement.getCondition();
        final int startOffset = condition.getRangeWithMacros().getStartOffset();
        OCChangeUtil.delete((PsiElement)condition);
        return new TextRange(startOffset, startOffset);
    }
    
    public String getTemplateDescription() {
        return "do / while";
    }
}
