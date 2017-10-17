// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.cpp.execution;

import com.intellij.openapi.options.ConfigurationException;
import java.awt.Component;
import com.jetbrains.cidr.cpp.CPPBundle;
import com.intellij.util.ui.GridBag;
import java.awt.LayoutManager;
import javax.swing.JPanel;
import java.awt.GridBagLayout;
import javax.swing.JComponent;
import javax.swing.JCheckBox;
import com.intellij.openapi.options.UnnamedConfigurable;

private static class MyConfigurable implements UnnamedConfigurable
{
    private final CMakeRunConfigurationManager myManager;
    private JCheckBox myShouldGenerateConfigs;
    private JCheckBox myShouldDeleteObsoleteConfigs;
    
    public MyConfigurable(final CMakeRunConfigurationManager myManager) {
        this.myManager = myManager;
    }
    
    public JComponent createComponent() {
        final JPanel panel = new JPanel(new GridBagLayout());
        final GridBag gridBag = new GridBag();
        gridBag.setDefaultAnchor(512);
        panel.add(this.myShouldGenerateConfigs = new JCheckBox(CPPBundle.message("cmake.run.shouldGenerateConfigs", new Object[0])), gridBag.nextLine().next().weightx(1.0));
        panel.add(this.myShouldDeleteObsoleteConfigs = new JCheckBox(CPPBundle.message("cmake.run.shouldDeleteObsoleteConfigs", new Object[0])), gridBag.nextLine().next().weightx(1.0));
        return panel;
    }
    
    public boolean isModified() {
        return this.myShouldGenerateConfigs.isSelected() != this.myManager.shouldGenerateConfigurations() || this.myShouldDeleteObsoleteConfigs.isSelected() != this.myManager.shouldDeleteObsoleteConfigurations();
    }
    
    public void apply() throws ConfigurationException {
        this.myManager.setShouldGenerateConfigurations(this.myShouldGenerateConfigs.isSelected());
        this.myManager.setShouldDeleteObsoleteConfigurations(this.myShouldDeleteObsoleteConfigs.isSelected());
    }
    
    public void reset() {
        this.myShouldGenerateConfigs.setSelected(this.myManager.shouldGenerateConfigurations());
        this.myShouldDeleteObsoleteConfigs.setSelected(this.myManager.shouldDeleteObsoleteConfigurations());
    }
    
    public void disposeUIResources() {
    }
}
