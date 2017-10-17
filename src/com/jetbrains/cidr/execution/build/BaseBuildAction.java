// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.execution.build;

import com.intellij.openapi.actionSystem.Presentation;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.actionSystem.AnActionEvent;
import org.jetbrains.annotations.NotNull;
import com.intellij.openapi.project.DumbAwareAction;

public abstract class BaseBuildAction extends DumbAwareAction
{
    protected BaseBuildAction(@NotNull final String s) {
        if (s == null) {
            throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "text", "com/jetbrains/cidr/execution/build/BaseBuildAction", "<init>"));
        }
        super(s);
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
        this.doBuild(eventProject);
    }
    
    protected abstract void doBuild(@NotNull final Project p0);
    
    public void update(final AnActionEvent anActionEvent) {
        super.update(anActionEvent);
        final Project eventProject = getEventProject(anActionEvent);
        Presentation presentation = null;
        boolean enabled = false;
        Label_0042: {
            Label_0033: {
                try {
                    presentation = anActionEvent.getPresentation();
                    if (eventProject == null) {
                        break Label_0033;
                    }
                    final BaseBuildAction baseBuildAction = this;
                    final Project project = eventProject;
                    final boolean b = baseBuildAction.isAvailable(project);
                    if (b) {
                        break Label_0033;
                    }
                    break Label_0033;
                }
                catch (IllegalArgumentException ex) {
                    throw a(ex);
                }
                try {
                    final BaseBuildAction baseBuildAction = this;
                    final Project project = eventProject;
                    final boolean b = baseBuildAction.isAvailable(project);
                    if (b) {
                        enabled = true;
                        break Label_0042;
                    }
                }
                catch (IllegalArgumentException ex2) {
                    throw a(ex2);
                }
            }
            enabled = false;
        }
        presentation.setEnabled(enabled);
    }
    
    protected boolean isAvailable(@NotNull final Project project) {
        try {
            if (project == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "project", "com/jetbrains/cidr/execution/build/BaseBuildAction", "isAvailable"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        return true;
    }
    
    private static IllegalArgumentException a(final IllegalArgumentException ex) {
        return ex;
    }
}
