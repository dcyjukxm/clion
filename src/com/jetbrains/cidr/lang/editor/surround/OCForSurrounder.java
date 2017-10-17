// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.lang.editor.surround;

import com.intellij.openapi.editor.Editor;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import com.jetbrains.cidr.lang.psi.OCStatement;
import com.jetbrains.cidr.lang.refactoring.util.OCChangeUtil;
import com.intellij.util.IncorrectOperationException;
import com.intellij.openapi.util.TextRange;
import com.intellij.psi.PsiElement;
import com.intellij.openapi.project.Project;
import com.jetbrains.cidr.lang.psi.OCForStatement;

public class OCForSurrounder extends OCStatementSurrounder<OCForStatement>
{
    @Override
    protected String getStatementTemplate(final Project project, final PsiElement psiElement) {
        return "for (a) {\n}";
    }
    
    @Override
    protected PsiElement getInsertionPlace(final OCForStatement ocForStatement) {
        return (PsiElement)ocForStatement.getBody();
    }
    
    @Override
    protected TextRange getSelectionRange(final OCForStatement ocForStatement) {
        final OCStatement initializer = ocForStatement.getInitializer();
        try {
            if (initializer == null) {
                return TextRange.EMPTY_RANGE;
            }
        }
        catch (IncorrectOperationException ex) {
            throw c(ex);
        }
        final int startOffset = ((PsiElement)initializer).getTextRange().getStartOffset();
        OCChangeUtil.delete((PsiElement)initializer);
        return new TextRange(startOffset, startOffset);
    }
    
    public String getTemplateDescription() {
        return "for";
    }
    
    private static IncorrectOperationException c(final IncorrectOperationException ex) {
        return ex;
    }
}
