// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.cpp;

import com.intellij.openapi.module.ModuleType;
import com.intellij.openapi.module.impl.ModuleTypeManagerImpl;

public class CPPModuleTypeManager extends ModuleTypeManagerImpl
{
    @Override
    public ModuleType getDefaultModuleType() {
        return new CPPModuleType();
    }
}
