// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.lang.daemon.clang.tidy;

import com.intellij.openapi.options.Configurable;
import com.jetbrains.cidr.lang.inspections.ClangTidyInspection;
import com.intellij.codeInspection.ex.InspectionToolWrapper;
import com.intellij.profile.codeInspection.ui.ProjectInspectionToolsConfigurable;
import com.intellij.profile.codeInspection.ProjectInspectionProfileManager;
import com.intellij.openapi.options.ShowSettingsUtil;
import com.intellij.psi.PsiFile;
import com.intellij.openapi.editor.Editor;
import com.intellij.openapi.project.Project;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nls;
import com.intellij.util.IncorrectOperationException;
import com.intellij.codeInsight.intention.IntentionAction;

public class OpenClangTidySettingsAction implements IntentionAction
{
    @Nls
    @NotNull
    public String getText() {
        String s;
        try {
            s = "Open settings";
            if (s == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/daemon/clang/tidy/OpenClangTidySettingsAction", "getText"));
            }
        }
        catch (IncorrectOperationException ex) {
            throw a(ex);
        }
        return s;
    }
    
    @Nls
    @NotNull
    public String getFamilyName() {
        String s;
        try {
            s = "Open Clang-Tidy Settings";
            if (s == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/daemon/clang/tidy/OpenClangTidySettingsAction", "getFamilyName"));
            }
        }
        catch (IncorrectOperationException ex) {
            throw a(ex);
        }
        return s;
    }
    
    public boolean isAvailable(@NotNull final Project project, final Editor editor, final PsiFile psiFile) {
        try {
            if (project == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "project", "com/jetbrains/cidr/lang/daemon/clang/tidy/OpenClangTidySettingsAction", "isAvailable"));
            }
        }
        catch (IncorrectOperationException ex) {
            throw a(ex);
        }
        return true;
    }
    
    public void invoke(@NotNull final Project project, final Editor editor, final PsiFile psiFile) throws IncorrectOperationException {
        try {
            if (project == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "project", "com/jetbrains/cidr/lang/daemon/clang/tidy/OpenClangTidySettingsAction", "invoke"));
            }
        }
        catch (IncorrectOperationException ex) {
            throw a(ex);
        }
        ShowSettingsUtil.getInstance().editConfigurable(project, (Configurable)new ProjectInspectionToolsConfigurable(ProjectInspectionProfileManager.getInstance(project)) {
            @Override
            protected boolean acceptTool(final InspectionToolWrapper inspectionToolWrapper) {
                return super.acceptTool(inspectionToolWrapper) && inspectionToolWrapper.getTool() instanceof ClangTidyInspection;
            }
        });
    }
    
    public boolean startInWriteAction() {
        return false;
    }
    
    private static IncorrectOperationException a(final IncorrectOperationException ex) {
        return ex;
    }
}
