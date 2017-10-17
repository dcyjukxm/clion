// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.lang.refactoring.changeSignature.actions;

import com.intellij.openapi.actionSystem.impl.SimpleDataContext;
import com.jetbrains.cidr.lang.refactoring.changeSignature.OCChangeSignatureActionHandler;
import com.jetbrains.cidr.lang.refactoring.OCExtractMethodHandler;
import com.intellij.featureStatistics.FeatureUsageTracker;
import com.intellij.psi.PsiFile;
import com.intellij.openapi.editor.Editor;
import com.intellij.openapi.project.Project;
import com.intellij.refactoring.RefactoringActionHandler;
import com.intellij.openapi.actionSystem.DataContext;
import com.jetbrains.cidr.lang.OCActionUtil;
import com.jetbrains.cidr.lang.OCLanguageKind;
import com.intellij.openapi.actionSystem.AnActionEvent;
import com.jetbrains.cidr.lang.OCLanguage;
import com.intellij.lang.Language;
import org.jetbrains.annotations.NotNull;
import com.intellij.psi.PsiElement;
import org.jetbrains.annotations.Nullable;
import com.jetbrains.cidr.lang.refactoring.changeSignature.OCCallableKind;
import com.intellij.refactoring.actions.BaseRefactoringAction;

public class OCExtractBlockParameterAction extends BaseRefactoringAction
{
    @Nullable
    protected OCCallableKind getCallableKind() {
        return OCCallableKind.BLOCK;
    }
    
    @Override
    protected boolean isAvailableInEditorOnly() {
        return true;
    }
    
    @Override
    protected boolean isEnabledOnElements(@NotNull final PsiElement[] array) {
        try {
            if (array == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "elements", "com/jetbrains/cidr/lang/refactoring/changeSignature/actions/OCExtractBlockParameterAction", "isEnabledOnElements"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        return false;
    }
    
    @Override
    protected boolean isAvailableForLanguage(final Language language) {
        try {
            if (OCLanguage.getInstance() == language) {
                return true;
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        return false;
    }
    
    @Override
    public void update(final AnActionEvent anActionEvent) {
        try {
            if (OCActionUtil.updateSmartAction(anActionEvent, OCLanguageKind.OBJ_C)) {
                super.update(anActionEvent);
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
    }
    
    public RefactoringActionHandler getHandler(@NotNull final DataContext dataContext) {
        try {
            if (dataContext == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "dataContext", "com/jetbrains/cidr/lang/refactoring/changeSignature/actions/OCExtractBlockParameterAction", "getHandler"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        return this.getHandler();
    }
    
    public RefactoringActionHandler getHandler() {
        return (RefactoringActionHandler)new RefactoringActionHandler() {
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
        };
    }
    
    private static IllegalArgumentException a(final IllegalArgumentException ex) {
        return ex;
    }
}
