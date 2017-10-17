// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.cpp.cmake.actions;

import com.intellij.openapi.project.Project;
import com.jetbrains.cidr.cpp.cmake.settings.CMakeConfigurable;
import com.intellij.openapi.options.ShowSettingsUtil;
import org.jetbrains.annotations.NotNull;
import com.intellij.openapi.actionSystem.AnActionEvent;
import com.intellij.icons.AllIcons;
import com.jetbrains.cidr.cpp.CPPBundle;
import com.intellij.openapi.project.DumbAwareAction;

public class OpenCMakeSettingsAction extends DumbAwareAction
{
    private final int myConfigIndex;
    
    public OpenCMakeSettingsAction(final int myConfigIndex) {
        super(CPPBundle.message("cmake.action.settings", new Object[0]), (String)null, AllIcons.General.Settings);
        this.myConfigIndex = myConfigIndex;
    }
    
    public void actionPerformed(@NotNull final AnActionEvent anActionEvent) {
        try {
            if (anActionEvent == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "e", "com/jetbrains/cidr/cpp/cmake/actions/OpenCMakeSettingsAction", "actionPerformed"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        final Project eventProject = getEventProject(anActionEvent);
        try {
            if (eventProject == null) {
                return;
            }
        }
        catch (IllegalArgumentException ex2) {
            throw a(ex2);
        }
        ShowSettingsUtil.getInstance().showSettingsDialog(eventProject, (Class)CMakeConfigurable.class, cMakeConfigurable -> cMakeConfigurable.setPreselectedConfig(this.myConfigIndex));
    }
    
    private static IllegalArgumentException a(final IllegalArgumentException ex) {
        return ex;
    }
}
