// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.lang.refactoring.move.actions;

import com.jetbrains.cidr.lang.OCActionUtil;
import com.jetbrains.cidr.lang.OCLanguageKind;
import com.jetbrains.cidr.lang.actions.newFile.OCNewFileActionBase;
import com.intellij.openapi.actionSystem.AnActionEvent;
import org.jetbrains.annotations.Nullable;
import com.intellij.refactoring.RefactoringActionHandler;
import org.jetbrains.annotations.NotNull;
import com.intellij.lang.refactoring.RefactoringSupportProvider;
import com.jetbrains.cidr.lang.refactoring.move.handlers.OCExtractCategoryHandler;
import com.intellij.refactoring.actions.ExtractSuperActionBase;

public class OCExtractCategoryAction extends ExtractSuperActionBase
{
    private static final OCExtractCategoryHandler HANDLER;
    
    @Nullable
    @Override
    protected RefactoringActionHandler getRefactoringHandler(@NotNull final RefactoringSupportProvider refactoringSupportProvider) {
        try {
            if (refactoringSupportProvider == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "provider", "com/jetbrains/cidr/lang/refactoring/move/actions/OCExtractCategoryAction", "getRefactoringHandler"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw b(ex);
        }
        return (RefactoringActionHandler)OCExtractCategoryAction.HANDLER;
    }
    
    @Override
    public void update(final AnActionEvent anActionEvent) {
        Label_0023: {
            try {
                if (!OCNewFileActionBase.isNewFileActionSupported()) {
                    break Label_0023;
                }
                final AnActionEvent anActionEvent2 = anActionEvent;
                final OCLanguageKind ocLanguageKind = OCLanguageKind.OBJ_C;
                final boolean b = OCActionUtil.updateSmartAction(anActionEvent2, ocLanguageKind);
                if (b) {
                    break Label_0023;
                }
                return;
            }
            catch (IllegalArgumentException ex) {
                throw b(ex);
            }
            try {
                final AnActionEvent anActionEvent2 = anActionEvent;
                final OCLanguageKind ocLanguageKind = OCLanguageKind.OBJ_C;
                final boolean b = OCActionUtil.updateSmartAction(anActionEvent2, ocLanguageKind);
                if (b) {
                    super.update(anActionEvent);
                }
                return;
            }
            catch (IllegalArgumentException ex2) {
                throw b(ex2);
            }
        }
        OCActionUtil.hideAction(anActionEvent);
    }
    
    static {
        HANDLER = new OCExtractCategoryHandler();
    }
    
    private static IllegalArgumentException b(final IllegalArgumentException ex) {
        return ex;
    }
}
