// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.lang.editor.surround;

import com.intellij.util.IncorrectOperationException;
import com.intellij.openapi.editor.Editor;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import com.jetbrains.cidr.lang.psi.OCStatement;
import com.intellij.openapi.util.TextRange;
import com.intellij.psi.PsiElement;
import com.intellij.openapi.project.Project;
import com.jetbrains.cidr.lang.psi.OCBlockStatement;

public class OCBlockSurrounder extends OCStatementSurrounder<OCBlockStatement>
{
    @Override
    protected String getStatementTemplate(final Project project, final PsiElement psiElement) {
        return "{\n}";
    }
    
    @Override
    protected PsiElement getInsertionPlace(final OCBlockStatement ocBlockStatement) {
        return (PsiElement)ocBlockStatement;
    }
    
    @Override
    protected TextRange getSelectionRange(final OCBlockStatement ocBlockStatement) {
        final int endOffset = ocBlockStatement.getTextRange().getEndOffset();
        return new TextRange(endOffset, endOffset);
    }
    
    public String getTemplateDescription() {
        return "{ }";
    }
}
