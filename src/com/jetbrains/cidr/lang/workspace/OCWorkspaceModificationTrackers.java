// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.lang.workspace;

import com.intellij.openapi.application.ApplicationManager;
import org.jetbrains.annotations.Nullable;
import com.intellij.util.messages.MessageBus;
import com.intellij.util.messages.Topic;
import com.intellij.psi.PsiManager;
import com.intellij.openapi.components.ServiceManager;
import org.jetbrains.annotations.NotNull;
import com.intellij.openapi.util.DefaultModificationTracker;
import com.intellij.psi.impl.PsiModificationTrackerImpl;
import com.intellij.openapi.project.Project;

public class OCWorkspaceModificationTrackers
{
    private final Project myProject;
    private final PsiModificationTrackerImpl myPsiTracker;
    private final DefaultModificationTracker myProjectsListTracker;
    private final DefaultModificationTracker myProjectFilesListTracker;
    private final DefaultModificationTracker mySourceFilesListTracker;
    private final DefaultModificationTracker myBuildSettingsChangesTracker;
    private final DefaultModificationTracker mySelectedResolveConfigurationTracker;
    private final DefaultModificationTracker myBuildsTracker;
    
    @NotNull
    public static OCWorkspaceModificationTrackers getInstance(@NotNull final Project project) {
        try {
            if (project == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "project", "com/jetbrains/cidr/lang/workspace/OCWorkspaceModificationTrackers", "getInstance"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        OCWorkspaceModificationTrackers ocWorkspaceModificationTrackers;
        try {
            ocWorkspaceModificationTrackers = (OCWorkspaceModificationTrackers)ServiceManager.getService(project, (Class)OCWorkspaceModificationTrackers.class);
            if (ocWorkspaceModificationTrackers == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/workspace/OCWorkspaceModificationTrackers", "getInstance"));
            }
        }
        catch (IllegalArgumentException ex2) {
            throw a(ex2);
        }
        return ocWorkspaceModificationTrackers;
    }
    
    public OCWorkspaceModificationTrackers(@NotNull final Project myProject) {
        if (myProject == null) {
            throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "project", "com/jetbrains/cidr/lang/workspace/OCWorkspaceModificationTrackers", "<init>"));
        }
        this.myProject = myProject;
        this.myPsiTracker = (PsiModificationTrackerImpl)PsiManager.getInstance(myProject).getModificationTracker();
        myProject.getMessageBus();
        final MessageBus messageBus;
        this.myProjectsListTracker = new MyModificationTracker(() -> ((OCWorkspaceModificationListener)messageBus.syncPublisher((Topic)OCWorkspaceModificationListener.TOPIC)).projectsChanged());
        this.myProjectFilesListTracker = new MyModificationTracker(() -> ((OCWorkspaceModificationListener)messageBus.syncPublisher((Topic)OCWorkspaceModificationListener.TOPIC)).projectFilesChanged());
        this.mySourceFilesListTracker = new MyModificationTracker(() -> ((OCWorkspaceModificationListener)messageBus.syncPublisher((Topic)OCWorkspaceModificationListener.TOPIC)).sourceFilesChanged());
        this.myBuildSettingsChangesTracker = new MyModificationTracker(() -> ((OCWorkspaceModificationListener)messageBus.syncPublisher((Topic)OCWorkspaceModificationListener.TOPIC)).buildSettingsChanged());
        this.mySelectedResolveConfigurationTracker = new MyModificationTracker(() -> ((OCWorkspaceModificationListener)messageBus.syncPublisher((Topic)OCWorkspaceModificationListener.TOPIC)).selectedResolveConfigurationChanged());
        this.myBuildsTracker = new MyModificationTracker(() -> ((OCWorkspaceModificationListener)messageBus.syncPublisher((Topic)OCWorkspaceModificationListener.TOPIC)).buildFinished());
    }
    
    @NotNull
    public DefaultModificationTracker getProjectsListTracker() {
        DefaultModificationTracker myProjectsListTracker;
        try {
            myProjectsListTracker = this.myProjectsListTracker;
            if (myProjectsListTracker == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/workspace/OCWorkspaceModificationTrackers", "getProjectsListTracker"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        return myProjectsListTracker;
    }
    
    @NotNull
    public DefaultModificationTracker getProjectFilesListTracker() {
        DefaultModificationTracker myProjectFilesListTracker;
        try {
            myProjectFilesListTracker = this.myProjectFilesListTracker;
            if (myProjectFilesListTracker == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/workspace/OCWorkspaceModificationTrackers", "getProjectFilesListTracker"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        return myProjectFilesListTracker;
    }
    
    @NotNull
    public DefaultModificationTracker getSourceFilesListTracker() {
        DefaultModificationTracker mySourceFilesListTracker;
        try {
            mySourceFilesListTracker = this.mySourceFilesListTracker;
            if (mySourceFilesListTracker == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/workspace/OCWorkspaceModificationTrackers", "getSourceFilesListTracker"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        return mySourceFilesListTracker;
    }
    
    @NotNull
    public DefaultModificationTracker getBuildSettingsChangesTracker() {
        DefaultModificationTracker myBuildSettingsChangesTracker;
        try {
            myBuildSettingsChangesTracker = this.myBuildSettingsChangesTracker;
            if (myBuildSettingsChangesTracker == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/workspace/OCWorkspaceModificationTrackers", "getBuildSettingsChangesTracker"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        return myBuildSettingsChangesTracker;
    }
    
    @NotNull
    public DefaultModificationTracker getSelectedResolveConfigurationTracker() {
        DefaultModificationTracker mySelectedResolveConfigurationTracker;
        try {
            mySelectedResolveConfigurationTracker = this.mySelectedResolveConfigurationTracker;
            if (mySelectedResolveConfigurationTracker == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/workspace/OCWorkspaceModificationTrackers", "getSelectedResolveConfigurationTracker"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        return mySelectedResolveConfigurationTracker;
    }
    
    @NotNull
    public DefaultModificationTracker getBuildsTracker() {
        DefaultModificationTracker myBuildsTracker;
        try {
            myBuildsTracker = this.myBuildsTracker;
            if (myBuildsTracker == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/workspace/OCWorkspaceModificationTrackers", "getBuildsTracker"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        return myBuildsTracker;
    }
    
    private static IllegalArgumentException a(final IllegalArgumentException ex) {
        return ex;
    }
    
    private class MyModificationTracker extends DefaultModificationTracker
    {
        @Nullable
        private final Runnable myPublisher;
        
        private MyModificationTracker(final Runnable myPublisher) {
            this.myPublisher = myPublisher;
        }
        
        public void incModificationCount() {
            super.incModificationCount();
            if (this.myPublisher != null) {
                this.myPublisher.run();
            }
            ApplicationManager.getApplication().invokeLater(() -> PsiManager.getInstance(OCWorkspaceModificationTrackers.this.myProject).dropPsiCaches(), OCWorkspaceModificationTrackers.this.myProject.getDisposed());
        }
    }
}
