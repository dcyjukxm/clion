// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.cpp.cmake.actions;

import com.intellij.openapi.project.Project;
import com.jetbrains.cidr.cpp.cmake.workspace.CMakeWorkspace;
import com.intellij.openapi.actionSystem.AnActionEvent;
import com.jetbrains.cidr.cpp.CPPBundle;
import com.intellij.openapi.project.DumbAware;
import com.intellij.openapi.actionSystem.AnAction;

public class ReloadCMakeProjectAction extends AnAction implements DumbAware
{
    public static final String ID = "CMake.ReloadCMakeProject";
    
    public ReloadCMakeProjectAction() {
        super(CPPBundle.message("cmake.action.reloadCmakeProject", new Object[0]));
    }
    
    public void update(final AnActionEvent anActionEvent) {
        super.update(anActionEvent);
        anActionEvent.getPresentation().setEnabled(getEventProject(anActionEvent) != null);
    }
    
    public void actionPerformed(final AnActionEvent anActionEvent) {
        final Project eventProject = getEventProject(anActionEvent);
        if (eventProject == null) {
            return;
        }
        CMakeWorkspace.getInstance(eventProject).scheduleReload(true);
    }
}
