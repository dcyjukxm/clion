// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.cpp;

import com.intellij.openapi.module.ModuleTypeManager;
import com.intellij.openapi.module.ModuleType;
import com.intellij.ide.util.projectWizard.EmptyModuleBuilder;

class CPPModuleType$1 extends EmptyModuleBuilder {
    public ModuleType getModuleType() {
        return ModuleTypeManager.getInstance().findByID("CPP_MODULE");
    }
}