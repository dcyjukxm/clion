// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.lang.daemon.clang.tidy;

import com.jetbrains.cidr.lang.inspections.ClangTidyInspection;
import com.intellij.icons.AllIcons;
import javax.swing.Icon;
import org.jetbrains.annotations.Nls;
import com.intellij.codeInspection.ex.InspectionToolWrapper;
import com.intellij.profile.codeInspection.ProjectInspectionProfileManager;
import com.intellij.codeInspection.ex.InspectionProfileModifiableModel;
import com.intellij.util.Consumer;
import com.intellij.codeInspection.ex.InspectionProfileModifiableModelKt;
import com.intellij.util.IncorrectOperationException;
import com.intellij.psi.PsiElement;
import com.intellij.openapi.editor.Editor;
import org.jetbrains.annotations.NotNull;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.util.Iconable;
import com.intellij.codeInspection.SuppressIntentionAction;

public abstract class DisableClangTidyIntentionAction extends SuppressIntentionAction implements Iconable
{
    public void invoke(@NotNull final Project project, final Editor editor, @NotNull final PsiElement psiElement) throws IncorrectOperationException {
        try {
            if (project == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "project", "com/jetbrains/cidr/lang/daemon/clang/tidy/DisableClangTidyIntentionAction", "invoke"));
            }
        }
        catch (IncorrectOperationException ex) {
            throw a(ex);
        }
        try {
            if (psiElement == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "element", "com/jetbrains/cidr/lang/daemon/clang/tidy/DisableClangTidyIntentionAction", "invoke"));
            }
        }
        catch (IncorrectOperationException ex2) {
            throw a(ex2);
        }
        InspectionProfileModifiableModelKt.modifyAndCommitProjectProfile(project, (Consumer<InspectionProfileModifiableModel>)(inspectionProfileModifiableModel -> {
            try {
                if (project == null) {
                    throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "project", "com/jetbrains/cidr/lang/daemon/clang/tidy/DisableClangTidyIntentionAction", "lambda$invoke$0"));
                }
            }
            catch (IncorrectOperationException ex) {
                throw a(ex);
            }
            final InspectionToolWrapper inspectionTool = inspectionProfileModifiableModel.getInspectionTool("ClangTidyInspection", project);
            if (inspectionTool != null) {
                final ClangTidyInspection clangTidyInspection = (ClangTidyInspection)inspectionTool.getTool();
                clangTidyInspection.setClangTidyChecks(this.modifyConfiguration(clangTidyInspection.getClangTidyChecks()));
            }
        }));
    }
    
    @NotNull
    protected abstract String modifyConfiguration(@NotNull final String p0);
    
    public boolean isAvailable(@NotNull final Project project, final Editor editor, @NotNull final PsiElement psiElement) {
        try {
            if (project == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "project", "com/jetbrains/cidr/lang/daemon/clang/tidy/DisableClangTidyIntentionAction", "isAvailable"));
            }
        }
        catch (IncorrectOperationException ex) {
            throw a(ex);
        }
        try {
            if (psiElement == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "element", "com/jetbrains/cidr/lang/daemon/clang/tidy/DisableClangTidyIntentionAction", "isAvailable"));
            }
        }
        catch (IncorrectOperationException ex2) {
            throw a(ex2);
        }
        final InspectionToolWrapper inspectionTool = ProjectInspectionProfileManager.getInstance(project).getCurrentProfile().getInspectionTool("ClangTidyInspection", project);
        try {
            if (inspectionTool != null) {
                return true;
            }
        }
        catch (IncorrectOperationException ex3) {
            throw a(ex3);
        }
        return false;
    }
    
    @Nls
    @NotNull
    public String getFamilyName() {
        String text;
        try {
            text = this.getText();
            if (text == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/daemon/clang/tidy/DisableClangTidyIntentionAction", "getFamilyName"));
            }
        }
        catch (IncorrectOperationException ex) {
            throw a(ex);
        }
        return text;
    }
    
    @NotNull
    public abstract String getText();
    
    public Icon getIcon(final int n) {
        return AllIcons.Actions.Cancel;
    }
    
    private static IncorrectOperationException a(final IncorrectOperationException ex) {
        return ex;
    }
}
