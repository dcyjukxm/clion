// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.projectView;

import org.jetbrains.annotations.NotNull;
import com.intellij.ide.projectView.impl.AbstractProjectViewPane;
import com.intellij.openapi.wm.ex.ToolWindowManagerEx;
import com.intellij.openapi.fileEditor.FileEditorManager;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.components.Storage;
import com.intellij.openapi.components.State;
import com.intellij.ide.projectView.impl.ProjectViewImpl;

@State(name = "ProjectView", storages = { @Storage("$WORKSPACE_FILE$") })
public class CidrProjectView extends ProjectViewImpl
{
    public CidrProjectView(final Project project, final FileEditorManager fileEditorManager, final ToolWindowManagerEx toolWindowManagerEx) {
        super(project, fileEditorManager, toolWindowManagerEx);
    }
    
    @Override
    public void addProjectPane(@NotNull final AbstractProjectViewPane abstractProjectViewPane) {
        try {
            if (abstractProjectViewPane == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "pane", "com/jetbrains/cidr/projectView/CidrProjectView", "addProjectPane"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        try {
            if (abstractProjectViewPane instanceof CidrView) {
                super.addProjectPane(abstractProjectViewPane);
            }
        }
        catch (IllegalArgumentException ex2) {
            throw a(ex2);
        }
    }
    
    @Override
    public void removeProjectPane(@NotNull final AbstractProjectViewPane abstractProjectViewPane) {
        try {
            if (abstractProjectViewPane == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "pane", "com/jetbrains/cidr/projectView/CidrProjectView", "removeProjectPane"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        try {
            if (abstractProjectViewPane instanceof CidrView) {
                super.removeProjectPane(abstractProjectViewPane);
            }
        }
        catch (IllegalArgumentException ex2) {
            throw a(ex2);
        }
    }
    
    @Override
    protected boolean isShowMembersOptionSupported() {
        return false;
    }
    
    private static IllegalArgumentException a(final IllegalArgumentException ex) {
        return ex;
    }
}
