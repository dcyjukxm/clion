// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.cpp.execution;

import com.intellij.openapi.options.ConfigurationException;
import org.jetbrains.annotations.Nullable;
import javax.swing.JPanel;
import javax.swing.JComponent;
import com.intellij.openapi.options.UnnamedConfigurable;

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
