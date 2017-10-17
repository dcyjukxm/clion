// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.cpp.cmake.actions;

import org.jetbrains.annotations.Nullable;
import com.intellij.openapi.project.Project;
import com.jetbrains.cidr.cpp.cmake.workspace.CMakeWorkspace;
import com.jetbrains.cidr.cpp.cmake.CMakeSettings;
import com.intellij.openapi.actionSystem.AnActionEvent;
import com.jetbrains.cidr.cpp.CPPBundle;
import com.intellij.openapi.project.DumbAware;
import com.intellij.openapi.actionSystem.ToggleAction;

public class ToggleCMakeAutoReloadAction extends ToggleAction implements DumbAware
{
    public static final String ID = "CMake.ToggleCMakeAutoReload";
    
    public ToggleCMakeAutoReloadAction() {
        super(CPPBundle.message("cmake.action.autoReloadCmakeProject", new Object[0]));
    }
    
    public void update(final AnActionEvent anActionEvent) {
        super.update(anActionEvent);
        anActionEvent.getPresentation().setEnabled(getEventProject(anActionEvent) != null);
    }
    
    public boolean isSelected(final AnActionEvent anActionEvent) {
        final CMakeSettings a = a(anActionEvent);
        return a != null && a.isAutoReloadEnabled();
    }
    
    public void setSelected(final AnActionEvent anActionEvent, final boolean autoReloadEnabled) {
        final CMakeSettings a = a(anActionEvent);
        if (a != null) {
            a.setAutoReloadEnabled(autoReloadEnabled);
        }
    }
    
    @Nullable
    private static CMakeSettings a(final AnActionEvent anActionEvent) {
        final Project eventProject = getEventProject(anActionEvent);
        return (eventProject == null) ? null : CMakeWorkspace.getInstance(eventProject).getSettings();
    }
}
