// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.cpp.cmake.actions;

import com.intellij.openapi.project.Project;
import com.jetbrains.cidr.cpp.cmake.workspace.CMakeWorkspace;
import com.intellij.openapi.actionSystem.AnActionEvent;
import com.intellij.icons.AllIcons;
import com.jetbrains.cidr.cpp.CPPBundle;
import com.intellij.openapi.project.DumbAwareAction;

public class ClearCMakeCacheAndReloadAction extends DumbAwareAction
{
    public static final String ID = "CMake.ClearCacheAndReload";
    
    public ClearCMakeCacheAndReloadAction() {
        super(CPPBundle.message("cmake.action.dropCacheAndReload", new Object[0]), CPPBundle.message("cmake.action.dropCacheAndReload.description", new Object[0]), AllIcons.Actions.ForceRefresh);
    }
    
    public void actionPerformed(final AnActionEvent anActionEvent) {
        final Project eventProject = getEventProject(anActionEvent);
        if (eventProject != null) {
            CMakeWorkspace.getInstance(eventProject).scheduleClearGeneratedFilesAndReload();
        }
    }
}
