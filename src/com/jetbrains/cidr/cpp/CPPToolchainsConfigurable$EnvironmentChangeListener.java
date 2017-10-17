// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.cpp;

import org.jetbrains.annotations.Nullable;

public interface EnvironmentChangeListener
{
    void environmentChanged(@Nullable final CPPToolchains.WinEnvironment p0);
}
