// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.cpp.cmake.projectWizard;

import com.intellij.openapi.actionSystem.DefaultActionGroup;
import com.intellij.ide.util.projectWizard.AbstractNewProjectDialog;

class NewCMakeProjectAction$1 extends AbstractNewProjectDialog {
    @Override
    protected DefaultActionGroup createRootStep() {
        return new CLionCMakeNewProjectStep();
    }
}