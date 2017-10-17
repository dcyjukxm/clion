// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.cpp.cmake.workspace;

import com.intellij.openapi.actionSystem.AnActionEvent;
import com.intellij.icons.AllIcons;
import com.jetbrains.cidr.cpp.CPPBundle;
import com.intellij.openapi.project.DumbAwareAction;

private class StopCMakeReloadAction extends DumbAwareAction
{
    public StopCMakeReloadAction() {
        super(CPPBundle.message("cmake.action.stopReload", new Object[0]), (String)null, AllIcons.Actions.Suspend);
    }
    
    public void update(final AnActionEvent anActionEvent) {
        super.update(anActionEvent);
        anActionEvent.getPresentation().setEnabled(CMakeOutputConsole.access$400(CMakeOutputConsole.this) != null && CMakeOutputConsole.access$400(CMakeOutputConsole.this).isRunning());
    }
    
    public void actionPerformed(final AnActionEvent anActionEvent) {
        if (CMakeOutputConsole.access$400(CMakeOutputConsole.this) != null) {
            CMakeOutputConsole.access$400(CMakeOutputConsole.this).cancel();
        }
    }
}
