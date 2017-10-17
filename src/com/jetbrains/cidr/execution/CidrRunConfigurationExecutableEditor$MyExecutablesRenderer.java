// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.execution;

import com.intellij.ide.NativeIconProvider;
import com.intellij.ui.SimpleColoredComponent;
import javax.swing.JList;
import java.io.File;

private class MyExecutablesRenderer extends CidrRunConfigurationSettingsEditor.MyBaseRenderer
{
    public MyExecutablesRenderer(final boolean b) {
        super(b);
    }
    
    @Override
    protected void appendNotFound(Object o, final boolean b) {
        if (o instanceof ExecutableData) {
            final String path = ((ExecutableData)o).path;
            o = ((path != null) ? new File(path).getName() : ((ExecutableData)o).target);
        }
        super.appendNotFound(o, b);
    }
    
    @Override
    protected String getSeparatorAbove(final JList list, final Object o, final int n) {
        if (n == 0) {
            return null;
        }
        if (o == CidrRunConfigurationExecutableEditor.access$100(CidrRunConfigurationExecutableEditor.this)) {
            return "";
        }
        final Object element = list.getModel().getElementAt(n - 1);
        if (o instanceof File && !(element instanceof File)) {
            return "";
        }
        return super.getSeparatorAbove(list, o, n);
    }
    
    @Override
    protected void customizeCellRenderer(final SimpleColoredComponent simpleColoredComponent, final JList list, final Object o, final int n, final boolean b, final boolean b2) {
        if (o instanceof File) {
            final File file = (File)o;
            simpleColoredComponent.setIcon(NativeIconProvider.getNativeIcon(file));
            if (!file.exists()) {
                this.appendNotFound(file.getName(), b);
            }
            else {
                simpleColoredComponent.append(file.getName());
            }
            return;
        }
        super.customizeCellRenderer(simpleColoredComponent, list, o, n, b, b2);
    }
}
