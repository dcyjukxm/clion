// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.cpp.cmake.workspace;

import com.intellij.openapi.application.ApplicationManager;
import com.jetbrains.cidr.cpp.cmake.CMakeSettingsListener;

class CMakeWorkspace$2 implements CMakeSettingsListener {
    @Override
    public void autoReloadChanged() {
        CMakeWorkspace.access$000(CMakeWorkspace.this).schedulePendingAutoReloads();
    }
    
    @Override
    public void configurationsChanged() {
        ApplicationManager.getApplication().runWriteAction(() -> CMakeWorkspace.access$100(CMakeWorkspace.this, true));
        CMakeWorkspace.this.scheduleReload(false);
    }
}