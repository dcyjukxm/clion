// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.lang.workspace;

import com.intellij.util.messages.Topic;

public interface OCWorkspaceModificationListener
{
    public static final Topic<OCWorkspaceModificationListener> TOPIC = Topic.create("Workspace modifications", (Class)OCWorkspaceModificationListener.class);
    
    default void projectsChanged() {
    }
    
    default void projectFilesChanged() {
    }
    
    default void sourceFilesChanged() {
    }
    
    default void buildSettingsChanged() {
    }
    
    default void selectedResolveConfigurationChanged() {
    }
    
    default void buildFinished() {
    }
}
