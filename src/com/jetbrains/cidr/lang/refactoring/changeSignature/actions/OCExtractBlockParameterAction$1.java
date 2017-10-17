// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.lang.refactoring.changeSignature.actions;

import com.intellij.psi.PsiElement;
import com.intellij.openapi.actionSystem.impl.SimpleDataContext;
import com.jetbrains.cidr.lang.refactoring.changeSignature.OCChangeSignatureActionHandler;
import com.jetbrains.cidr.lang.refactoring.OCExtractMethodHandler;
import com.intellij.featureStatistics.FeatureUsageTracker;
import com.intellij.openapi.actionSystem.DataContext;
import com.intellij.psi.PsiFile;
import com.intellij.openapi.editor.Editor;
import org.jetbrains.annotations.NotNull;
import com.intellij.openapi.project.Project;
import com.intellij.refactoring.RefactoringActionHandler;

class OCExtractBlockParameterAction$1 implements RefactoringActionHandler {
    public void invoke(@NotNull final Project project, final Editor editor, final PsiFile psiFile, final DataContext dataContext) {
        try {
            if (project == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "project", "com/jetbrains/cidr/lang/refactoring/changeSignature/actions/OCExtractBlockParameterAction$1", "invoke"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        FeatureUsageTracker.getInstance().triggerFeatureUsed("refactoring.extractBlockParameter");
        new OCExtractMethodHandler().invoke(project, editor, psiFile, SimpleDataContext.getSimpleContext(OCChangeSignatureActionHandler.CALLABLE_KIND.getName(), OCExtractBlockParameterAction.this.getCallableKind()));
    }
    
    public void invoke(@NotNull final Project project, @NotNull final PsiElement[] array, final DataContext dataContext) {
        try {
            if (project == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "project", "com/jetbrains/cidr/lang/refactoring/changeSignature/actions/OCExtractBlockParameterAction$1", "invoke"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        try {
            if (array == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "elements", "com/jetbrains/cidr/lang/refactoring/changeSignature/actions/OCExtractBlockParameterAction$1", "invoke"));
            }
        }
        catch (IllegalArgumentException ex2) {
            throw a(ex2);
        }
    }
    
    private static IllegalArgumentException a(final IllegalArgumentException ex) {
        return ex;
    }
}