// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.lang.quickfixes;

import com.intellij.codeInspection.CommonProblemDescriptor;
import com.intellij.codeInspection.ProblemDescriptor;
import com.intellij.psi.PsiDocumentManager;
import com.intellij.codeInsight.FileModificationService;
import org.jetbrains.annotations.Nullable;
import com.intellij.openapi.editor.Editor;
import com.intellij.openapi.project.Project;
import org.jetbrains.annotations.NotNull;
import com.intellij.util.IncorrectOperationException;
import com.intellij.psi.PsiFile;
import com.intellij.codeInspection.LocalQuickFix;
import com.intellij.codeInsight.intention.IntentionAction;

public abstract class OCQuickFix implements IntentionAction, LocalQuickFix
{
    protected abstract String getTextInternal();
    
    public abstract boolean isAvailable();
    
    protected void invoke(final PsiFile psiFile) {
    }
    
    @NotNull
    public String getText() {
        String textInternal = null;
        Label_0055: {
            String s = null;
            Label_0020: {
                try {
                    if (this.isAvailable()) {
                        break Label_0055;
                    }
                    s = "Invalid";
                    if (s == null) {
                        break Label_0020;
                    }
                    return s;
                }
                catch (IncorrectOperationException ex) {
                    throw a(ex);
                }
                try {
                    s = "Invalid";
                    if (s == null) {
                        throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/quickfixes/OCQuickFix", "getText"));
                    }
                }
                catch (IncorrectOperationException ex2) {
                    throw a(ex2);
                }
            }
            return s;
            try {
                textInternal = this.getTextInternal();
                if (textInternal == null) {
                    throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/quickfixes/OCQuickFix", "getText"));
                }
            }
            catch (IncorrectOperationException ex3) {
                throw a(ex3);
            }
        }
        return textInternal;
    }
    
    @NotNull
    public String getName() {
        String text;
        try {
            text = this.getText();
            if (text == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/quickfixes/OCQuickFix", "getName"));
            }
        }
        catch (IncorrectOperationException ex) {
            throw a(ex);
        }
        return text;
    }
    
    public boolean isAvailable(@NotNull final Project project, final Editor editor, final PsiFile psiFile) {
        try {
            if (project == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "project", "com/jetbrains/cidr/lang/quickfixes/OCQuickFix", "isAvailable"));
            }
        }
        catch (IncorrectOperationException ex) {
            throw a(ex);
        }
        return this.isAvailable();
    }
    
    public void invoke(@NotNull final Project project, @Nullable final Editor editor, final PsiFile psiFile) throws IncorrectOperationException {
        try {
            if (project == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "project", "com/jetbrains/cidr/lang/quickfixes/OCQuickFix", "invoke"));
            }
        }
        catch (IncorrectOperationException ex) {
            throw a(ex);
        }
        try {
            if (FileModificationService.getInstance().prepareFileForWrite(psiFile)) {
                PsiDocumentManager.getInstance(project).commitAllDocuments();
                this.invoke(psiFile);
            }
        }
        catch (IncorrectOperationException ex2) {
            throw a(ex2);
        }
    }
    
    public void applyFix(@NotNull final Project project, @NotNull final ProblemDescriptor problemDescriptor) {
        try {
            if (project == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "project", "com/jetbrains/cidr/lang/quickfixes/OCQuickFix", "applyFix"));
            }
        }
        catch (IncorrectOperationException ex) {
            throw a(ex);
        }
        try {
            if (problemDescriptor == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "descriptor", "com/jetbrains/cidr/lang/quickfixes/OCQuickFix", "applyFix"));
            }
        }
        catch (IncorrectOperationException ex2) {
            throw a(ex2);
        }
        if (this.isAvailable()) {
            final PsiFile containingFile = problemDescriptor.getPsiElement().getContainingFile();
            this.invoke(containingFile.getProject(), null, containingFile);
        }
    }
    
    public boolean startInWriteAction() {
        return true;
    }
    
    public boolean isSupportedInBatchMode() {
        return true;
    }
    
    private static IncorrectOperationException a(final IncorrectOperationException ex) {
        return ex;
    }
}
