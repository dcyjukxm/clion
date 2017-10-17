// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.cpp.cmake.projectWizard;

import com.intellij.openapi.actionSystem.Presentation;
import com.intellij.openapi.wm.impl.welcomeScreen.NewWelcomeScreen;
import javax.swing.Icon;
import com.intellij.openapi.actionSystem.ActionPlaces;
import org.jetbrains.annotations.NotNull;
import com.intellij.openapi.actionSystem.AnActionEvent;
import com.intellij.icons.AllIcons;
import com.intellij.openapi.project.DumbAware;
import com.intellij.openapi.actionSystem.AnAction;

public class ImportCMakeProjectAction extends AnAction implements DumbAware
{
    public ImportCMakeProjectAction() {
        super(AllIcons.General.ImportProject);
    }
    
    public void update(@NotNull final AnActionEvent anActionEvent) {
        try {
            if (anActionEvent == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "e", "com/jetbrains/cidr/cpp/cmake/projectWizard/ImportCMakeProjectAction", "update"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        final Presentation presentation = anActionEvent.getPresentation();
        try {
            if (ActionPlaces.isMainMenuOrActionSearch(anActionEvent.getPlace())) {
                presentation.setIcon((Icon)null);
            }
        }
        catch (IllegalArgumentException ex2) {
            throw a(ex2);
        }
        try {
            if (NewWelcomeScreen.isNewWelcomeScreen(anActionEvent)) {
                anActionEvent.getPresentation().setIcon(AllIcons.Welcome.ImportProject);
            }
        }
        catch (IllegalArgumentException ex3) {
            throw a(ex3);
        }
    }
    
    public void actionPerformed(@NotNull final AnActionEvent anActionEvent) {
        try {
            if (anActionEvent == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "e", "com/jetbrains/cidr/cpp/cmake/projectWizard/ImportCMakeProjectAction", "actionPerformed"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        ImportCMakeProjectWizard.startRunWizard();
    }
    
    private static IllegalArgumentException a(final IllegalArgumentException ex) {
        return ex;
    }
}
