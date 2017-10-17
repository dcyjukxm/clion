// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.cpp.cmake.workspace;

import com.jetbrains.cidr.cpp.CPPToolchainsListener;

class CMakeWorkspace$1 implements CPPToolchainsListener {
    @Override
    public void environmentChanged() {
        CMakeWorkspace.this.scheduleReload(true);
    }
}