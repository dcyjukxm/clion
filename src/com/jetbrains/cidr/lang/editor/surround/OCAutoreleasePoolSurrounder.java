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
import com.intellij.openapi.util.TextRange;
import com.intellij.psi.PsiElement;
import com.intellij.openapi.project.Project;
import com.jetbrains.cidr.lang.psi.OCAutoReleasePoolStatement;

public class OCAutoreleasePoolSurrounder extends OCStatementSurrounder<OCAutoReleasePoolStatement>
{
    @Override
    protected String getStatementTemplate(final Project project, final PsiElement psiElement) {
        return "@autoreleasepool {\n}";
    }
    
    @Override
    protected PsiElement getInsertionPlace(final OCAutoReleasePoolStatement ocAutoReleasePoolStatement) {
        return (PsiElement)ocAutoReleasePoolStatement.getBody();
    }
    
    @Override
    protected TextRange getSelectionRange(final OCAutoReleasePoolStatement ocAutoReleasePoolStatement) {
        final int endOffset = ocAutoReleasePoolStatement.getTextRange().getEndOffset();
        return new TextRange(endOffset, endOffset);
    }
    
    public String getTemplateDescription() {
        return "@autoreleasepool";
    }
    
    @Override
    protected OCLanguageKind getLanguageKind() {
        return OCLanguageKind.OBJ_C;
    }
}
