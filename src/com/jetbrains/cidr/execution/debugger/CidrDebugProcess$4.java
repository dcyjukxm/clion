// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.execution.debugger;

import com.intellij.openapi.project.Project;
import com.intellij.psi.util.CachedValueProvider;
import java.util.List;
import com.intellij.util.CachedValueImpl;

class CidrDebugProcess$4 extends CachedValueImpl<List<String>> {
    @Override
    public boolean isFromMyProject(final Project project) {
        return true;
    }
}