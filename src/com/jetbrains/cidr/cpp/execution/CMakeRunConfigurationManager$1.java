// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.cpp.execution;

import com.jetbrains.cidr.cpp.cmake.workspace.CMakeWorkspaceListener;

class CMakeRunConfigurationManager$1 implements CMakeWorkspaceListener {
    @Override
    public void reloadingFinished(final boolean b) {
        if (b) {
            return;
        }
        if (CMakeRunConfigurationManager.access$000(CMakeRunConfigurationManager.this)) {
            CMakeRunConfigurationManager.access$100(CMakeRunConfigurationManager.this);
        }
    }
}