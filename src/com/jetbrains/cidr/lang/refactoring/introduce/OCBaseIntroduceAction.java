// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.lang.refactoring.introduce;

import com.jetbrains.cidr.lang.OCLanguageKind;
import com.jetbrains.cidr.lang.OCActionUtil;
import com.intellij.openapi.actionSystem.AnActionEvent;
import org.jetbrains.annotations.Nullable;
import com.intellij.refactoring.RefactoringActionHandler;
import com.intellij.openapi.actionSystem.DataContext;
import com.jetbrains.cidr.lang.OCLanguage;
import com.intellij.lang.Language;
import org.jetbrains.annotations.NotNull;
import com.intellij.psi.PsiElement;
import com.intellij.refactoring.actions.BaseRefactoringAction;

public abstract class OCBaseIntroduceAction extends BaseRefactoringAction
{
    @Override
    protected boolean isAvailableInEditorOnly() {
        return true;
    }
    
    @Override
    protected boolean isEnabledOnElements(@NotNull final PsiElement[] array) {
        try {
            if (array == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "elements", "com/jetbrains/cidr/lang/refactoring/introduce/OCBaseIntroduceAction", "isEnabledOnElements"));
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
    
    @Nullable
    @Override
    protected RefactoringActionHandler getHandler(@NotNull final DataContext dataContext) {
        try {
            if (dataContext == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "dataContext", "com/jetbrains/cidr/lang/refactoring/introduce/OCBaseIntroduceAction", "getHandler"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        return this.getHandler();
    }
    
    @Nullable
    public abstract RefactoringActionHandler getHandler();
    
    @Override
    public void update(final AnActionEvent anActionEvent) {
        try {
            if (OCActionUtil.updateSmartAction(anActionEvent, this.getRequiredLanguageKind())) {
                super.update(anActionEvent);
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
    }
    
    @Nullable
    protected OCLanguageKind getRequiredLanguageKind() {
        return null;
    }
    
    private static IllegalArgumentException a(final IllegalArgumentException ex) {
        return ex;
    }
}
