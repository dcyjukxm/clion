// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.cpp.cmake.workspace;

import com.intellij.ide.caches.CachesInvalidator;

public class CMakeWorkspaceStateInvalidator extends CachesInvalidator
{
    public void invalidateCaches() {
        CMakeWorkspace.invalidateCaches();
    }
}
