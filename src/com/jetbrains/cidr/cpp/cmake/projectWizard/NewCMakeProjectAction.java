// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.cpp.cmake.projectWizard;

import com.intellij.openapi.actionSystem.DefaultActionGroup;
import com.intellij.ide.util.projectWizard.AbstractNewProjectDialog;
import com.intellij.openapi.actionSystem.Presentation;
import com.intellij.openapi.wm.impl.welcomeScreen.NewWelcomeScreen;
import javax.swing.Icon;
import com.intellij.openapi.actionSystem.ActionPlaces;
import com.intellij.openapi.actionSystem.AnActionEvent;
import com.intellij.icons.AllIcons;
import com.intellij.openapi.project.DumbAware;
import com.intellij.openapi.actionSystem.AnAction;

public class NewCMakeProjectAction extends AnAction implements DumbAware
{
    public static final String NEW_CMAKE_PROJECT_ACTION_NAME = "New Project";
    public static final String NEW_CMAKE_PROJECT_ACTION_DESCRIPTION = "Create a new CMake Project";
    
    public NewCMakeProjectAction() {
        super("New Project", "Create a new CMake Project", AllIcons.General.CreateNewProject);
    }
    
    public void update(final AnActionEvent anActionEvent) {
        final Presentation presentation = anActionEvent.getPresentation();
        if (ActionPlaces.isMainMenuOrActionSearch(anActionEvent.getPlace())) {
            presentation.setIcon((Icon)null);
        }
        if (NewWelcomeScreen.isNewWelcomeScreen(anActionEvent)) {
            anActionEvent.getPresentation().setIcon(AllIcons.Welcome.CreateNewProject);
        }
    }
    
    public void actionPerformed(final AnActionEvent anActionEvent) {
        new AbstractNewProjectDialog() {
            @Override
            protected DefaultActionGroup createRootStep() {
                return new CLionCMakeNewProjectStep();
            }
        }.show();
    }
}
