// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.lang.workspace;

import com.intellij.openapi.application.ApplicationManager;
import java.util.Objects;
import com.intellij.openapi.application.ReadAction;
import com.intellij.execution.RunManager;
import com.intellij.util.messages.MessageBusConnection;
import com.intellij.util.messages.Topic;
import com.intellij.execution.RunnerAndConfigurationSettings;
import java.util.concurrent.atomic.AtomicInteger;
import com.intellij.execution.RunManagerListener;
import com.intellij.openapi.project.ProjectUtil;
import org.jetbrains.annotations.NotNull;
import com.intellij.openapi.project.Project;
import org.jetbrains.annotations.Nullable;
import com.intellij.openapi.components.BaseComponent;

public class OCWorkspaceRunConfigurationListener implements BaseComponent
{
    @Nullable
    private volatile OCResolveConfiguration mySelectedResolveConfiguration;
    
    protected OCWorkspaceRunConfigurationListener(@NotNull final Project project) {
        if (project == null) {
            throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "project", "com/jetbrains/cidr/lang/workspace/OCWorkspaceRunConfigurationListener", "<init>"));
        }
        final IllegalArgumentException ex;
        ProjectUtil.runWhenProjectOpened(project, () -> {
            try {
                if (project == null) {
                    new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "project", "com/jetbrains/cidr/lang/workspace/OCWorkspaceRunConfigurationListener", "lambda$new$0"));
                    throw ex;
                }
            }
            catch (RuntimeException ex2) {
                throw b(ex2);
            }
            d(project);
            c(project);
        });
    }
    
    private static void c(@NotNull final Project project) {
        try {
            if (project == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "project", "com/jetbrains/cidr/lang/workspace/OCWorkspaceRunConfigurationListener", "listenChanges"));
            }
        }
        catch (RuntimeException ex) {
            throw b(ex);
        }
        final MessageBusConnection connect = project.getMessageBus().connect();
        connect.subscribe((Topic)RunManagerListener.TOPIC, (Object)new RunManagerListener() {
            final AtomicInteger bulkUpdate = new AtomicInteger(0);
            
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
                        d(project);
                    }
                }
                catch (IllegalArgumentException ex) {
                    throw b(ex);
                }
            }
            
            private static IllegalArgumentException b(final IllegalArgumentException ex) {
                return ex;
            }
        });
        connect.subscribe((Topic)OCWorkspaceModificationListener.TOPIC, (Object)new OCWorkspaceModificationListener() {
            @Override
            public void projectsChanged() {
                d(project);
            }
        });
    }
    
    private static void d(@NotNull final Project project) {
        try {
            if (project == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "project", "com/jetbrains/cidr/lang/workspace/OCWorkspaceRunConfigurationListener", "updateSelectedConfiguration"));
            }
        }
        catch (RuntimeException ex) {
            throw b(ex);
        }
        final RunnerAndConfigurationSettings selectedConfiguration = RunManager.getInstance(project).getSelectedConfiguration();
        try {
            if (selectedConfiguration == null || !(selectedConfiguration.getConfiguration() instanceof OCRunConfigurationWithResolveConfiguration)) {
                return;
            }
        }
        catch (RuntimeException ex2) {
            throw b(ex2);
        }
        setSelectedResolveConfiguration(project, ((OCRunConfigurationWithResolveConfiguration)ReadAction.compute(() -> (OCRunConfigurationWithResolveConfiguration)selectedConfiguration.getConfiguration())).getResolveConfiguration());
    }
    
    @Nullable
    public static OCResolveConfiguration getSelectedResolveConfiguration(@NotNull final Project project) {
        try {
            if (project == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "project", "com/jetbrains/cidr/lang/workspace/OCWorkspaceRunConfigurationListener", "getSelectedResolveConfiguration"));
            }
        }
        catch (RuntimeException ex) {
            throw b(ex);
        }
        try {
            if (project.isDefault()) {
                return null;
            }
        }
        catch (RuntimeException ex2) {
            throw b(ex2);
        }
        return a(project).mySelectedResolveConfiguration;
    }
    
    public static void setSelectedResolveConfiguration(@NotNull final Project project, @Nullable final OCResolveConfiguration mySelectedResolveConfiguration) {
        try {
            if (project == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "project", "com/jetbrains/cidr/lang/workspace/OCWorkspaceRunConfigurationListener", "setSelectedResolveConfiguration"));
            }
        }
        catch (RuntimeException ex) {
            throw b(ex);
        }
        try {
            if (project.isDefault()) {
                return;
            }
        }
        catch (RuntimeException ex2) {
            throw b(ex2);
        }
        final OCWorkspaceRunConfigurationListener a = a(project);
        if (!Objects.equals(a.mySelectedResolveConfiguration, mySelectedResolveConfiguration)) {
            a.mySelectedResolveConfiguration = mySelectedResolveConfiguration;
            ApplicationManager.getApplication().runWriteAction(() -> OCWorkspaceModificationTrackers.getInstance(project).getSelectedResolveConfigurationTracker().incModificationCount());
        }
    }
    
    @NotNull
    private static OCWorkspaceRunConfigurationListener a(@NotNull final Project project) {
        try {
            if (project == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "project", "com/jetbrains/cidr/lang/workspace/OCWorkspaceRunConfigurationListener", "getInstance"));
            }
        }
        catch (RuntimeException ex) {
            throw b(ex);
        }
        OCWorkspaceRunConfigurationListener ocWorkspaceRunConfigurationListener;
        try {
            ocWorkspaceRunConfigurationListener = (OCWorkspaceRunConfigurationListener)project.getComponent((Class)OCWorkspaceRunConfigurationListener.class);
            if (ocWorkspaceRunConfigurationListener == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/workspace/OCWorkspaceRunConfigurationListener", "getInstance"));
            }
        }
        catch (RuntimeException ex2) {
            throw b(ex2);
        }
        return ocWorkspaceRunConfigurationListener;
    }
    
    private static RuntimeException b(final RuntimeException ex) {
        return ex;
    }
}
