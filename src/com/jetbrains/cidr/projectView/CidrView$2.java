// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.projectView;

import com.intellij.openapi.project.Project;
import com.intellij.ide.impl.ProjectViewSelectInTarget;

class CidrView$2 extends ProjectViewSelectInTarget {
    @Override
    public String toString() {
        return CidrView.this.getTitle();
    }
    
    public String getMinorViewId() {
        return CidrView.this.getId();
    }
    
    public float getWeight() {
        return CidrView.this.getWeight();
    }
}