// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.lang.settings;

import com.intellij.util.messages.Topic;

public interface OCFileExtensionsSettingsListener
{
    public static final Topic<OCFileExtensionsSettingsListener> TOPIC = new Topic(OCFileExtensionsSettingsListener.class.getSimpleName(), (Class)OCFileExtensionsSettingsListener.class);
    
    void settingsUpdated();
}
