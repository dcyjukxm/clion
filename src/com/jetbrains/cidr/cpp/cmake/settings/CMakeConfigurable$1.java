// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.cpp.cmake.settings;

import org.jetbrains.annotations.Nullable;
import com.jetbrains.cidr.cpp.CPPToolchains;
import com.jetbrains.cidr.cpp.CPPToolchainsConfigurable;

class CMakeConfigurable$1 implements CPPToolchainsConfigurable.EnvironmentChangeListener {
    @Override
    public void environmentChanged(@Nullable final CPPToolchains.WinEnvironment winEnvironment) {
        CMakeConfigurable.access$000(CMakeConfigurable.this).updateSelectedEnvironment(winEnvironment);
    }
}