// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.cpp.cmake.projectWizard;

import javax.swing.event.TreeModelEvent;
import com.intellij.util.ui.tree.TreeModelAdapter;

class ImportCMakeProjectStepAdapter$2 extends TreeModelAdapter {
    public void treeNodesChanged(final TreeModelEvent treeModelEvent) {
        ImportCMakeProjectStepAdapter.access$300(ImportCMakeProjectStepAdapter.this);
        ImportCMakeProjectStepAdapter.this.myValidateCallback.run();
    }
}