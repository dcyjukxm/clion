// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.lang.refactoring.changeSignature.actions;

import com.intellij.openapi.actionSystem.DataContext;
import com.intellij.featureStatistics.FeatureUsageTracker;
import com.intellij.openapi.actionSystem.impl.SimpleDataContext;
import com.jetbrains.cidr.lang.refactoring.changeSignature.OCChangeSignatureActionHandler;
import com.intellij.psi.PsiFile;
import com.intellij.openapi.editor.Editor;
import org.jetbrains.annotations.NotNull;
import com.intellij.openapi.project.Project;
import com.intellij.lang.LanguageCodeInsightActionHandler;

class OCConvertAction$1 implements LanguageCodeInsightActionHandler {
    public void invoke(@NotNull final Project project, @NotNull final Editor editor, @NotNull final PsiFile psiFile) {
        try {
            if (project == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "project", "com/jetbrains/cidr/lang/refactoring/changeSignature/actions/OCConvertAction$1", "invoke"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        try {
            if (editor == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "editor", "com/jetbrains/cidr/lang/refactoring/changeSignature/actions/OCConvertAction$1", "invoke"));
            }
        }
        catch (IllegalArgumentException ex2) {
            throw a(ex2);
        }
        try {
            if (psiFile == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "file", "com/jetbrains/cidr/lang/refactoring/changeSignature/actions/OCConvertAction$1", "invoke"));
            }
        }
        catch (IllegalArgumentException ex3) {
            throw a(ex3);
        }
        final DataContext simpleContext = SimpleDataContext.getSimpleContext(OCChangeSignatureActionHandler.CALLABLE_KIND.getName(), OCConvertAction.this.getCallableKind());
        FeatureUsageTracker.getInstance().triggerFeatureUsed("refactoring.convertToMethod");
        new OCChangeSignatureActionHandler().invoke(project, editor, psiFile, simpleContext);
    }
    
    public boolean startInWriteAction() {
        return false;
    }
    
    public boolean isValidFor(final Editor editor, final PsiFile psiFile) {
        return OCConvertAction.this.isValidForFile(psiFile.getProject(), editor, psiFile);
    }
    
    private static IllegalArgumentException a(final IllegalArgumentException ex) {
        return ex;
    }
}