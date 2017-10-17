// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.execution.testing;

import javax.swing.JList;
import com.intellij.ui.SimpleColoredComponent;
import com.jetbrains.cidr.execution.CidrRunConfigurationSettingsEditor;

private class MyTestsRenderer extends MyBaseRenderer
{
    public MyTestsRenderer() {
        super(CidrTestRunConfigurationEditor.access$300(CidrTestRunConfigurationEditor.this));
    }
    
    @Override
    protected boolean isChecking() {
        return CidrTestRunConfigurationEditor.this.isChecking();
    }
    
    @Override
    protected void customizeCellRenderer(final SimpleColoredComponent simpleColoredComponent, final JList list, final Object o, final int n, final boolean b, final boolean b2) {
        if (o instanceof String) {
            final String s = (String)o;
            if (s.isEmpty()) {
                simpleColoredComponent.append(CidrTestRunConfigurationEditor.this.getAllTestsMessage(), this.grayed(b));
            }
            else if (!CidrTestRunConfigurationEditor.access$400(CidrTestRunConfigurationEditor.this).contains(s)) {
                this.appendNotFound(s, b);
            }
            else {
                simpleColoredComponent.append(s);
            }
        }
    }
}
