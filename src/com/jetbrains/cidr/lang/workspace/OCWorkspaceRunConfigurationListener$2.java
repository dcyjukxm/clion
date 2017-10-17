// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.lang.workspace;

import com.intellij.openapi.project.Project;

static final class OCWorkspaceRunConfigurationListener$2 implements OCWorkspaceModificationListener {
    final /* synthetic */ Project val$project;
    
    @Override
    public void projectsChanged() {
        OCWorkspaceRunConfigurationListener.access$000(this.val$project);
    }
}