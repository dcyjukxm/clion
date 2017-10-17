// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.projectView;

import javax.swing.tree.TreeModel;
import com.intellij.openapi.project.Project;
import com.intellij.ide.projectView.impl.ProjectViewTree;

class CidrFilesViewHelper$1 extends ProjectViewTree {
    public String toString() {
        return CidrFilesViewHelper.TITLE + " " + super.toString();
    }
}