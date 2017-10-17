// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.cpp.cmake.projectWizard;

import com.intellij.openapi.ui.VerticalFlowLayout;
import org.jetbrains.annotations.Nullable;
import javax.swing.JComponent;
import java.awt.Component;
import java.awt.LayoutManager;
import java.awt.BorderLayout;
import com.jetbrains.cidr.cpp.cmake.projectWizard.generators.CMakeProjectGenerator;
import javax.swing.JPanel;
import com.intellij.ide.util.projectWizard.AbstractNewProjectStep;
import com.intellij.platform.DirectoryProjectGenerator;
import com.intellij.ide.util.projectWizard.ProjectSettingsStepBase;

public class CMakeProjectSpecificSettingsStep extends ProjectSettingsStepBase
{
    public CMakeProjectSpecificSettingsStep(final DirectoryProjectGenerator directoryProjectGenerator, final AbstractNewProjectStep.AbstractCallback abstractCallback) {
        super(directoryProjectGenerator, abstractCallback);
    }
    
    @Nullable
    @Override
    protected JPanel createAdvancedSettings() {
        if (this.myProjectGenerator instanceof CMakeProjectGenerator) {
            final JComponent settingsPanel = ((CMakeProjectGenerator)this.myProjectGenerator).getSettingsPanel();
            if (settingsPanel != null) {
                final JPanel panel = new JPanel(new BorderLayout());
                panel.add(settingsPanel, "North");
                return panel;
            }
        }
        return null;
    }
    
    @Override
    protected JPanel createBasePanel() {
        final JPanel panel = new JPanel((LayoutManager)new VerticalFlowLayout(0, 3));
        panel.add(super.createBasePanel());
        return panel;
    }
}
