// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.cpp.cmake.workspace;

import com.intellij.util.messages.Topic;

public interface CMakeWorkspaceListener
{
    public static final Topic<CMakeWorkspaceListener> TOPIC = new Topic(CMakeWorkspaceListener.class.getSimpleName(), (Class)CMakeWorkspaceListener.class);
    
    default void reloadingStarted() {
    }
    
    default void reloadingRescheduled() {
    }
    
    default void generationStarted() {
    }
    
    default void generationFinished() {
    }
    
    default void filesRefreshedAfterGeneration() {
    }
    
    default void beforeApplying() {
    }
    
    default void reloadingFinished(final boolean b) {
    }
}
