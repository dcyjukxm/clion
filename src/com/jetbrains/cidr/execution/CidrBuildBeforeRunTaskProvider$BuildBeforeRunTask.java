// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.execution;

import com.intellij.openapi.util.Key;
import com.intellij.execution.BeforeRunTask;

private static class BuildBeforeRunTask extends BeforeRunTask<BuildBeforeRunTask>
{
    private BuildBeforeRunTask() {
        super((Key)CidrBuildBeforeRunTaskProvider.ID);
        this.setEnabled(true);
    }
}
