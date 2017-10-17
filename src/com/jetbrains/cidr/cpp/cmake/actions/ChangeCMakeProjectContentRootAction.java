// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.cpp.cmake.actions;

import com.intellij.openapi.application.Result;
import com.intellij.openapi.application.WriteAction;
import com.intellij.openapi.vfs.VirtualFile;
import com.intellij.openapi.fileChooser.FileChooser;
import com.intellij.openapi.fileChooser.FileChooserDescriptor;
import com.jetbrains.cidr.cpp.cmake.workspace.CMakeWorkspace;
import org.jetbrains.annotations.NotNull;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.actionSystem.Presentation;
import com.intellij.openapi.actionSystem.AnActionEvent;
import com.jetbrains.cidr.cpp.CPPBundle;
import com.intellij.openapi.project.DumbAware;
import com.intellij.openapi.actionSystem.AnAction;

public class ChangeCMakeProjectContentRootAction extends AnAction implements DumbAware
{
    public static final String ID = "CMake.ChangeCMakeProjectContentRoot";
    
    public ChangeCMakeProjectContentRootAction() {
        super(CPPBundle.message("cmake.action.changeContentRoot", new Object[0]));
    }
    
    public void update(final AnActionEvent anActionEvent) {
        Presentation presentation = null;
        boolean enabled = false;
        Label_0025: {
            try {
                super.update(anActionEvent);
                presentation = anActionEvent.getPresentation();
                if (getEventProject(anActionEvent) != null) {
                    enabled = true;
                    break Label_0025;
                }
            }
            catch (IllegalArgumentException ex) {
                throw a(ex);
            }
            enabled = false;
        }
        presentation.setEnabled(enabled);
    }
    
    public void actionPerformed(final AnActionEvent anActionEvent) {
        final Project eventProject = getEventProject(anActionEvent);
        try {
            if (eventProject == null) {
                return;
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        perform(eventProject);
    }
    
    public static void perform(@NotNull final Project project) {
        try {
            if (project == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "project", "com/jetbrains/cidr/cpp/cmake/actions/ChangeCMakeProjectContentRootAction", "perform"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        final CMakeWorkspace instance = CMakeWorkspace.getInstance(project);
        FileChooser.chooseFile(new FileChooserDescriptor(false, true, false, false, false, false), project, instance.getEffectiveContentRoot(), virtualFile -> new WriteAction<Object>() {
            final /* synthetic */ CMakeWorkspace val$workspace;
            final /* synthetic */ VirtualFile val$file;
            
            protected void run(@NotNull final Result<Object> result) throws Throwable {
                try {
                    if (result == null) {
                        throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "result", "com/jetbrains/cidr/cpp/cmake/actions/ChangeCMakeProjectContentRootAction$1", "run"));
                    }
                }
                catch (Throwable t) {
                    throw a(t);
                }
                this.val$workspace.changeContentRoot(this.val$file);
            }
            
            private static Throwable a(final Throwable t) {
                return t;
            }
        }.execute());
    }
    
    private static IllegalArgumentException a(final IllegalArgumentException ex) {
        return ex;
    }
}
