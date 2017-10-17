// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.project;

import com.intellij.util.messages.Topic;

public interface CidrRootConfigurationListener
{
    public static final Topic<CidrRootConfigurationListener> TOPIC = Topic.create("Root configuration", (Class)CidrRootConfigurationListener.class);
    
    void configurationChanged();
}
