// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.lang.daemon.clang.tidy;

import com.jetbrains.cidr.lang.inspections.ClangTidyInspection;
import com.intellij.codeInspection.ex.InspectionToolWrapper;
import com.intellij.profile.codeInspection.ProjectInspectionProfileManager;
import com.intellij.profile.codeInspection.ui.ProjectInspectionToolsConfigurable;

class OpenClangTidySettingsAction$1 extends ProjectInspectionToolsConfigurable {
    @Override
    protected boolean acceptTool(final InspectionToolWrapper inspectionToolWrapper) {
        return super.acceptTool(inspectionToolWrapper) && inspectionToolWrapper.getTool() instanceof ClangTidyInspection;
    }
}