// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.lang.workspace;

import org.jetbrains.annotations.NotNull;
import com.intellij.execution.RunnerAndConfigurationSettings;
import com.intellij.openapi.project.Project;
import java.util.concurrent.atomic.AtomicInteger;
import com.intellij.execution.RunManagerListener;

static final class OCWorkspaceRunConfigurationListener$1 implements RunManagerListener {
    final AtomicInteger bulkUpdate = new AtomicInteger(0);
    final /* synthetic */ Project val$project;
    
    @Override
    public void beginUpdate() {
        this.bulkUpdate.incrementAndGet();
    }
    
    @Override
    public void endUpdate() {
        try {
            if (this.bulkUpdate.decrementAndGet() == 0) {
                this.doUpdate();
            }
        }
        catch (IllegalArgumentException ex) {
            throw b(ex);
        }
    }
    
    @Override
    public void runConfigurationSelected() {
        this.doUpdate();
    }
    
    @Override
    public void runConfigurationAdded(@NotNull final RunnerAndConfigurationSettings runnerAndConfigurationSettings) {
        try {
            if (runnerAndConfigurationSettings == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "settings", "com/jetbrains/cidr/lang/workspace/OCWorkspaceRunConfigurationListener$1", "runConfigurationAdded"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw b(ex);
        }
        this.doUpdate();
    }
    
    @Override
    public void runConfigurationRemoved(@NotNull final RunnerAndConfigurationSettings runnerAndConfigurationSettings) {
        try {
            if (runnerAndConfigurationSettings == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "settings", "com/jetbrains/cidr/lang/workspace/OCWorkspaceRunConfigurationListener$1", "runConfigurationRemoved"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw b(ex);
        }
        this.doUpdate();
    }
    
    @Override
    public void runConfigurationChanged(@NotNull final RunnerAndConfigurationSettings runnerAndConfigurationSettings) {
        try {
            if (runnerAndConfigurationSettings == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "settings", "com/jetbrains/cidr/lang/workspace/OCWorkspaceRunConfigurationListener$1", "runConfigurationChanged"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw b(ex);
        }
        this.doUpdate();
    }
    
    void doUpdate() {
        try {
            if (this.bulkUpdate.get() == 0) {
                OCWorkspaceRunConfigurationListener.access$000(this.val$project);
            }
        }
        catch (IllegalArgumentException ex) {
            throw b(ex);
        }
    }
    
    private static IllegalArgumentException b(final IllegalArgumentException ex) {
        return ex;
    }
}