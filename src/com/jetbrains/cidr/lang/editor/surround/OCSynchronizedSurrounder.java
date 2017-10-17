// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.lang.editor.surround;

import com.intellij.util.IncorrectOperationException;
import com.intellij.openapi.editor.Editor;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import com.jetbrains.cidr.lang.psi.OCStatement;
import com.jetbrains.cidr.lang.OCLanguageKind;
import com.jetbrains.cidr.lang.psi.OCExpression;
import com.jetbrains.cidr.lang.refactoring.util.OCChangeUtil;
import com.intellij.openapi.util.TextRange;
import com.intellij.psi.PsiElement;
import com.intellij.openapi.project.Project;
import com.jetbrains.cidr.lang.psi.OCSynchronizedStatement;

public class OCSynchronizedSurrounder extends OCStatementSurrounder<OCSynchronizedStatement>
{
    @Override
    protected String getStatementTemplate(final Project project, final PsiElement psiElement) {
        return "@synchronized (a) {\n}";
    }
    
    @Override
    protected PsiElement getInsertionPlace(final OCSynchronizedStatement ocSynchronizedStatement) {
        return (PsiElement)ocSynchronizedStatement.getBody();
    }
    
    @Override
    protected TextRange getSelectionRange(final OCSynchronizedStatement ocSynchronizedStatement) {
        final OCExpression lockExpression = ocSynchronizedStatement.getLockExpression();
        final int startOffset = lockExpression.getRangeWithMacros().getStartOffset();
        OCChangeUtil.delete((PsiElement)lockExpression);
        return new TextRange(startOffset, startOffset);
    }
    
    public String getTemplateDescription() {
        return "@synchronized";
    }
    
    @Override
    protected OCLanguageKind getLanguageKind() {
        return OCLanguageKind.OBJ_C;
    }
}
