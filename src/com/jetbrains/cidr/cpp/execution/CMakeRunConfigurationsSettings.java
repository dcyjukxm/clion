// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.cpp.execution;

import org.jetbrains.annotations.Nullable;
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
import org.jetbrains.annotations.NotNull;
import com.intellij.openapi.project.Project;
import com.intellij.execution.configurations.RunConfigurationsSettings;

public class CMakeRunConfigurationsSettings implements RunConfigurationsSettings
{
    @NotNull
    private final Project myProject;
    
    public CMakeRunConfigurationsSettings(@NotNull final Project myProject) {
        if (myProject == null) {
            throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "project", "com/jetbrains/cidr/cpp/execution/CMakeRunConfigurationsSettings", "<init>"));
        }
        this.myProject = myProject;
    }
    
    @NotNull
    public UnnamedConfigurable createConfigurable() {
        MyConfigurable myConfigurable = null;
        Label_0062: {
            try {
                if (!this.myProject.isDefault()) {
                    break Label_0062;
                }
                final EmptyConfigurable emptyConfigurable = new EmptyConfigurable();
                if (emptyConfigurable != null) {
                    return (UnnamedConfigurable)emptyConfigurable;
                }
            }
            catch (IllegalArgumentException ex) {
                throw a(ex);
            }
            throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/cpp/execution/CMakeRunConfigurationsSettings", "createConfigurable"));
            try {
                myConfigurable = new MyConfigurable(CMakeRunConfigurationManager.getInstance(this.myProject));
                if (myConfigurable == null) {
                    throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/cpp/execution/CMakeRunConfigurationsSettings", "createConfigurable"));
                }
            }
            catch (IllegalArgumentException ex2) {
                throw a(ex2);
            }
        }
        return (UnnamedConfigurable)myConfigurable;
    }
    
    private static IllegalArgumentException a(final IllegalArgumentException ex) {
        return ex;
    }
    
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
    
    private static class EmptyConfigurable implements UnnamedConfigurable
    {
        @Nullable
        public JComponent createComponent() {
            return new JPanel();
        }
        
        public boolean isModified() {
            return false;
        }
        
        public void apply() throws ConfigurationException {
        }
        
        public void reset() {
        }
        
        public void disposeUIResources() {
        }
    }
}
