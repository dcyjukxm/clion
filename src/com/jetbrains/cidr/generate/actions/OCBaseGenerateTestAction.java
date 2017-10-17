// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.generate.actions;

import com.intellij.codeInsight.CodeInsightActionHandler;
import com.jetbrains.cidr.lang.OCActionUtil;
import com.intellij.openapi.actionSystem.AnActionEvent;
import com.intellij.psi.PsiFile;
import com.intellij.openapi.editor.Editor;
import com.intellij.openapi.project.Project;
import org.jetbrains.annotations.NotNull;
import com.jetbrains.cidr.lang.OCLanguageKind;
import com.jetbrains.cidr.generate.handlers.OCAbstractGenerateTestHandler;
import com.intellij.codeInsight.actions.CodeInsightAction;

public class OCBaseGenerateTestAction extends CodeInsightAction
{
    private final OCAbstractGenerateTestHandler myHandler;
    private final OCLanguageKind myLanguageKind;
    
    protected OCBaseGenerateTestAction(final OCAbstractGenerateTestHandler myHandler, final OCLanguageKind myLanguageKind) {
        this.myHandler = myHandler;
        this.myLanguageKind = myLanguageKind;
    }
    
    @NotNull
    protected OCAbstractGenerateTestHandler getHandler() {
        OCAbstractGenerateTestHandler myHandler;
        try {
            myHandler = this.myHandler;
            if (myHandler == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/generate/actions/OCBaseGenerateTestAction", "getHandler"));
            }
        }
        catch (IllegalStateException ex) {
            throw a(ex);
        }
        return myHandler;
    }
    
    protected boolean isValidForFile(@NotNull final Project project, @NotNull final Editor editor, @NotNull final PsiFile psiFile) {
        try {
            if (project == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "project", "com/jetbrains/cidr/generate/actions/OCBaseGenerateTestAction", "isValidForFile"));
            }
        }
        catch (IllegalStateException ex) {
            throw a(ex);
        }
        try {
            if (editor == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "editor", "com/jetbrains/cidr/generate/actions/OCBaseGenerateTestAction", "isValidForFile"));
            }
        }
        catch (IllegalStateException ex2) {
            throw a(ex2);
        }
        try {
            if (psiFile == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "file", "com/jetbrains/cidr/generate/actions/OCBaseGenerateTestAction", "isValidForFile"));
            }
        }
        catch (IllegalStateException ex3) {
            throw a(ex3);
        }
        return this.myHandler.isValidFor(editor, psiFile);
    }
    
    public void update(final AnActionEvent anActionEvent) {
        try {
            if (OCActionUtil.updateSmartAction(anActionEvent, this.myLanguageKind)) {
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
