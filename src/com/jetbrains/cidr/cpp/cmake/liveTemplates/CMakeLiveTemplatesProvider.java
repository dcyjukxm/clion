// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.cpp.cmake.liveTemplates;

import com.intellij.codeInsight.template.impl.DefaultLiveTemplatesProvider;

public class CMakeLiveTemplatesProvider implements DefaultLiveTemplatesProvider
{
    @Override
    public String[] getDefaultLiveTemplateFiles() {
        return new String[] { "/liveTemplates/CMake" };
    }
    
    @Override
    public String[] getHiddenLiveTemplateFiles() {
        return null;
    }
}
