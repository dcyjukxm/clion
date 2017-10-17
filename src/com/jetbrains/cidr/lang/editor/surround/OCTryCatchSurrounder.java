// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.lang.editor.surround;

import com.intellij.util.IncorrectOperationException;
import com.intellij.openapi.editor.Editor;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import com.jetbrains.cidr.lang.OCLanguageKind;
import com.jetbrains.cidr.lang.psi.OCCatchSection;
import com.jetbrains.cidr.lang.psi.OCStatement;
import com.intellij.openapi.util.TextRange;
import com.jetbrains.cidr.lang.refactoring.OCNameSuggester;
import com.jetbrains.cidr.lang.symbols.OCSymbolKind;
import com.intellij.psi.PsiElement;
import com.intellij.openapi.project.Project;
import com.jetbrains.cidr.lang.psi.OCTryStatement;

public class OCTryCatchSurrounder extends OCStatementSurrounder<OCTryStatement>
{
    @Override
    protected String getStatementTemplate(final Project project, final PsiElement psiElement) {
        final String suggestUniqueName = OCNameSuggester.suggestUniqueName(OCSymbolKind.CATCH_EXCEPTION_VARIABLE, "exception", psiElement);
        return "@try {\n}\n@catch (NSException *" + suggestUniqueName + ") {\nNSLog(@\"Exception occurred: %@, %@\", " + suggestUniqueName + ", [" + suggestUniqueName + " userInfo]);}";
    }
    
    @Override
    protected PsiElement getInsertionPlace(final OCTryStatement ocTryStatement) {
        return (PsiElement)ocTryStatement.getBody();
    }
    
    @Override
    protected TextRange getSelectionRange(final OCTryStatement ocTryStatement) {
        return ocTryStatement.getCatchSections().get(0).getBody().getStatements().get(0).getTextRange();
    }
    
    public String getTemplateDescription() {
        return "@try / @catch";
    }
    
    @Override
    protected OCLanguageKind getLanguageKind() {
        return OCLanguageKind.OBJ_C;
    }
}
