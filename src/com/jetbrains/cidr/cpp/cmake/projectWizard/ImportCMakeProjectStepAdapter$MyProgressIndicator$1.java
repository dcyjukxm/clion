// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.cpp.cmake.projectWizard;

import com.intellij.openapi.progress.util.AbstractProgressIndicatorExBase;

class ImportCMakeProjectStepAdapter$MyProgressIndicator$1 extends AbstractProgressIndicatorExBase {
    @Override
    public void cancel() {
        super.cancel();
        MyProgressIndicator.access$400(MyProgressIndicator.this);
    }
}