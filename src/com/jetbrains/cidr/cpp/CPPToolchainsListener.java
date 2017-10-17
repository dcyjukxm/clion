// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.cpp;

import com.intellij.util.messages.Topic;

public interface CPPToolchainsListener
{
    public static final Topic<CPPToolchainsListener> TOPIC = new Topic(CPPToolchainsListener.class.getSimpleName(), (Class)CPPToolchainsListener.class);
    
    void environmentChanged();
}
