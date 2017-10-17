// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.cpp;

import com.intellij.openapi.application.Application;
import com.intellij.util.messages.Topic;
import com.intellij.openapi.application.ApplicationManager;
import com.intellij.openapi.util.Ref;
import org.jetbrains.annotations.Nullable;

class CPPToolchainsConfigurable$1 implements EnvironmentChangeListener {
    @Override
    public void environmentChanged(@Nullable final CPPToolchains.WinEnvironment winEnvironment) {
        CPPToolchainsConfigurable.access$002(Ref.create((Object)winEnvironment));
        final Application application = ApplicationManager.getApplication();
        if (application != null) {
            ((EnvironmentChangeListener)application.getMessageBus().syncPublisher((Topic)CPPToolchainsConfigurable.TOPIC)).environmentChanged(winEnvironment);
        }
    }
}