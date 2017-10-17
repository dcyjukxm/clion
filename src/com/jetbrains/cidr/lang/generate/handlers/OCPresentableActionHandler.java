// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.lang.generate.handlers;

import org.jetbrains.annotations.Nullable;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.actionSystem.Presentation;
import com.intellij.psi.PsiFile;
import org.jetbrains.annotations.NotNull;
import com.intellij.openapi.editor.Editor;
import com.intellij.codeInsight.generation.actions.PresentableLanguageCodeInsightActionHandler;

public abstract class OCPresentableActionHandler implements PresentableLanguageCodeInsightActionHandler
{
    @Override
    public void update(@NotNull final Editor editor, @NotNull final PsiFile psiFile, final Presentation presentation) {
        try {
            if (editor == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "editor", "com/jetbrains/cidr/lang/generate/handlers/OCPresentableActionHandler", "update"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        try {
            if (psiFile == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "file", "com/jetbrains/cidr/lang/generate/handlers/OCPresentableActionHandler", "update"));
            }
        }
        catch (IllegalArgumentException ex2) {
            throw a(ex2);
        }
        final OCClassActionHandlerBase handler = this.getHandler(editor, psiFile);
        try {
            if (handler == null) {
                return;
            }
        }
        catch (IllegalArgumentException ex3) {
            throw a(ex3);
        }
        final String actionName = handler.getActionName();
        try {
            if (actionName != null) {
                presentation.setText(actionName);
                presentation.setDescription(handler.getActionDescription());
            }
        }
        catch (IllegalArgumentException ex4) {
            throw a(ex4);
        }
    }
    
    public boolean isValidFor(final Editor editor, final PsiFile psiFile) {
        try {
            if (this.getHandler(editor, psiFile) != null) {
                return true;
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        return false;
    }
    
    public void invoke(@NotNull final Project project, @NotNull final Editor editor, @NotNull final PsiFile psiFile) {
        try {
            if (project == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "project", "com/jetbrains/cidr/lang/generate/handlers/OCPresentableActionHandler", "invoke"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        try {
            if (editor == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "editor", "com/jetbrains/cidr/lang/generate/handlers/OCPresentableActionHandler", "invoke"));
            }
        }
        catch (IllegalArgumentException ex2) {
            throw a(ex2);
        }
        try {
            if (psiFile == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "file", "com/jetbrains/cidr/lang/generate/handlers/OCPresentableActionHandler", "invoke"));
            }
        }
        catch (IllegalArgumentException ex3) {
            throw a(ex3);
        }
        final OCClassActionHandlerBase handler = this.getHandler(editor, psiFile);
        try {
            if (handler != null) {
                handler.invoke(project, editor, psiFile);
            }
        }
        catch (IllegalArgumentException ex4) {
            throw a(ex4);
        }
    }
    
    @Nullable
    protected abstract OCClassActionHandlerBase getHandler(final Editor p0, final PsiFile p1);
    
    private static IllegalArgumentException a(final IllegalArgumentException ex) {
        return ex;
    }
}
