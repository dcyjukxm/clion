// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.lang.refactoring.changeSignature.actions;

import com.intellij.codeInsight.CodeInsightActionHandler;
import com.jetbrains.cidr.lang.OCActionUtil;
import com.jetbrains.cidr.lang.OCLanguageKind;
import com.intellij.openapi.actionSystem.AnActionEvent;
import com.intellij.openapi.actionSystem.DataContext;
import com.intellij.featureStatistics.FeatureUsageTracker;
import com.intellij.openapi.actionSystem.impl.SimpleDataContext;
import com.jetbrains.cidr.lang.refactoring.changeSignature.OCChangeSignatureActionHandler;
import com.intellij.psi.PsiFile;
import com.intellij.openapi.editor.Editor;
import org.jetbrains.annotations.NotNull;
import com.intellij.openapi.project.Project;
import com.intellij.lang.LanguageCodeInsightActionHandler;
import com.jetbrains.cidr.lang.refactoring.changeSignature.OCCallableKind;
import com.intellij.psi.PsiElement;
import com.intellij.codeInsight.actions.CodeInsightAction;

public abstract class OCConvertAction extends CodeInsightAction
{
    protected abstract boolean isAvailableFor(final PsiElement p0);
    
    protected abstract OCCallableKind getCallableKind();
    
    @NotNull
    public LanguageCodeInsightActionHandler getHandler() {
        LanguageCodeInsightActionHandler languageCodeInsightActionHandler;
        try {
            languageCodeInsightActionHandler = new LanguageCodeInsightActionHandler() {
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
            };
            if (languageCodeInsightActionHandler == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/refactoring/changeSignature/actions/OCConvertAction", "getHandler"));
            }
        }
        catch (IllegalStateException ex) {
            throw a(ex);
        }
        return (LanguageCodeInsightActionHandler)languageCodeInsightActionHandler;
    }
    
    protected boolean isValidForFile(@NotNull final Project project, @NotNull final Editor editor, @NotNull final PsiFile psiFile) {
        try {
            if (project == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "project", "com/jetbrains/cidr/lang/refactoring/changeSignature/actions/OCConvertAction", "isValidForFile"));
            }
        }
        catch (IllegalStateException ex) {
            throw a(ex);
        }
        try {
            if (editor == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "editor", "com/jetbrains/cidr/lang/refactoring/changeSignature/actions/OCConvertAction", "isValidForFile"));
            }
        }
        catch (IllegalStateException ex2) {
            throw a(ex2);
        }
        try {
            if (psiFile == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "file", "com/jetbrains/cidr/lang/refactoring/changeSignature/actions/OCConvertAction", "isValidForFile"));
            }
        }
        catch (IllegalStateException ex3) {
            throw a(ex3);
        }
        return this.isAvailableFor(new OCChangeSignatureActionHandler().findTargetMember(psiFile, editor));
    }
    
    public void update(final AnActionEvent anActionEvent) {
        try {
            if (OCActionUtil.updateSmartAction(anActionEvent, OCLanguageKind.OBJ_C)) {
                super.update(anActionEvent);
            }
        }
        catch (IllegalStateException ex) {
            throw a(ex);
        }
    }
    
    private static IllegalStateException a(final IllegalStateException ex) {
        return ex;
    }
}
