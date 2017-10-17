// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.lang.generate.actions;

import com.intellij.psi.PsiFile;
import com.intellij.openapi.editor.Editor;
import com.intellij.openapi.project.Project;
import org.jetbrains.annotations.NotNull;
import com.intellij.codeInsight.CodeInsightActionHandler;
import com.jetbrains.cidr.lang.generate.handlers.OCGenerateDescriptionHandler;

public class OCGenerateDescriptionAction extends OCObjCGenerateAction
{
    private static final OCGenerateDescriptionHandler HANDLER;
    
    @NotNull
    protected CodeInsightActionHandler getHandler() {
        OCGenerateDescriptionHandler handler;
        try {
            handler = OCGenerateDescriptionAction.HANDLER;
            if (handler == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/generate/actions/OCGenerateDescriptionAction", "getHandler"));
            }
        }
        catch (IllegalStateException ex) {
            throw a(ex);
        }
        return (CodeInsightActionHandler)handler;
    }
    
    protected boolean isValidForFile(@NotNull final Project project, @NotNull final Editor editor, @NotNull final PsiFile psiFile) {
        try {
            if (project == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "project", "com/jetbrains/cidr/lang/generate/actions/OCGenerateDescriptionAction", "isValidForFile"));
            }
        }
        catch (IllegalStateException ex) {
            throw a(ex);
        }
        try {
            if (editor == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "editor", "com/jetbrains/cidr/lang/generate/actions/OCGenerateDescriptionAction", "isValidForFile"));
            }
        }
        catch (IllegalStateException ex2) {
            throw a(ex2);
        }
        try {
            if (psiFile == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "file", "com/jetbrains/cidr/lang/generate/actions/OCGenerateDescriptionAction", "isValidForFile"));
            }
        }
        catch (IllegalStateException ex3) {
            throw a(ex3);
        }
        return OCGenerateDescriptionAction.HANDLER.isValidFor(editor, psiFile);
    }
    
    static {
        HANDLER = new OCGenerateDescriptionHandler();
    }
    
    private static IllegalStateException a(final IllegalStateException ex) {
        return ex;
    }
}
