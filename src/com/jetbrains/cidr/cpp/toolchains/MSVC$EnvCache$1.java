// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.cpp.toolchains;

import com.intellij.openapi.wm.IdeFrame;
import com.intellij.openapi.application.ApplicationActivationListener;

static final class MSVC$EnvCache$1 implements ApplicationActivationListener {
    public void applicationDeactivated(final IdeFrame ideFrame) {
        EnvCache.access$100().clear();
    }
}