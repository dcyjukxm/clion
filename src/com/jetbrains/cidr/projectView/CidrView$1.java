// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.projectView;

import com.jetbrains.cidr.lang.workspace.OCWorkspaceModificationListener;

class CidrView$1 implements OCWorkspaceModificationListener {
    @Override
    public void projectsChanged() {
        this.somethingChanged();
    }
    
    @Override
    public void projectFilesChanged() {
        this.somethingChanged();
    }
    
    @Override
    public void sourceFilesChanged() {
        this.somethingChanged();
    }
    
    @Override
    public void buildSettingsChanged() {
        this.somethingChanged();
    }
    
    @Override
    public void selectedResolveConfigurationChanged() {
        this.somethingChanged();
    }
    
    @Override
    public void buildFinished() {
        this.somethingChanged();
    }
    
    void somethingChanged() {
        CidrView.this.queueUpdate();
    }
}