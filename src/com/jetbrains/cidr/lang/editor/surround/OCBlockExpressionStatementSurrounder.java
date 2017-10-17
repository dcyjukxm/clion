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
import com.jetbrains.cidr.lang.util.OCElementUtil;
import com.intellij.openapi.util.TextRange;
import com.jetbrains.cidr.lang.psi.OCDeclarator;
import com.jetbrains.cidr.lang.psi.OCBlockExpression;
import com.intellij.psi.PsiElement;
import com.intellij.openapi.project.Project;
import com.jetbrains.cidr.lang.psi.OCDeclarationStatement;

public class OCBlockExpressionStatementSurrounder extends OCStatementSurrounder<OCDeclarationStatement>
{
    @Override
    protected String getStatementTemplate(final Project project, final PsiElement psiElement) {
        return "void (^a)() = ^{\n};";
    }
    
    @Override
    protected PsiElement getInsertionPlace(final OCDeclarationStatement ocDeclarationStatement) {
        return (PsiElement)((OCBlockExpression)ocDeclarationStatement.getDeclaration().getDeclarators().get(0).getInitializer()).getBody();
    }
    
    @Override
    protected TextRange getSelectionRange(final OCDeclarationStatement ocDeclarationStatement) {
        final PsiElement nameIdentifier = ocDeclarationStatement.getDeclaration().getDeclarators().get(0).getNameIdentifier();
        final int startOffset = OCElementUtil.getRangeWithMacros(nameIdentifier).getStartOffset();
        nameIdentifier.delete();
        return new TextRange(startOffset, startOffset);
    }
    
    public String getTemplateDescription() {
        return "void (^) = ^{ }";
    }
    
    @Override
    protected OCLanguageKind getLanguageKind() {
        return OCLanguageKind.OBJ_C;
    }
}
