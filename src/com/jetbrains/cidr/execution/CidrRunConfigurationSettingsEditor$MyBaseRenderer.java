// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.execution;

import com.jetbrains.cidr.CidrBundle;
import com.intellij.ui.SimpleTextAttributes;
import javax.swing.Icon;
import com.intellij.openapi.util.text.StringUtil;
import java.awt.Component;
import javax.swing.JList;
import com.intellij.ui.ErrorLabel;
import javax.swing.JComponent;
import com.intellij.ui.SimpleColoredComponent;
import javax.swing.ListCellRenderer;
import com.intellij.ui.GroupedElementsRenderer;

protected static class MyBaseRenderer extends GroupedElementsRenderer.List implements ListCellRenderer
{
    private final boolean hasTargetsInSeveralProjects;
    private SimpleColoredComponent myComponent;
    
    public MyBaseRenderer(final boolean hasTargetsInSeveralProjects) {
        this.hasTargetsInSeveralProjects = hasTargetsInSeveralProjects;
    }
    
    protected JComponent createItemComponent() {
        this.myTextLabel = new ErrorLabel();
        return (JComponent)(this.myComponent = new SimpleColoredComponent() {
            {
                this.setFocusBorderAroundIcon(true);
            }
        });
    }
    
    public Component getListCellRendererComponent(final JList list, final Object o, final int n, final boolean opaque, final boolean b) {
        this.myComponent.clear();
        this.customizeCellRenderer(this.myComponent, list, o, n, opaque, b);
        final String s = (n == -1) ? null : this.getSeparatorAbove(list, o, n);
        this.configureComponent((String)null, (String)null, (Icon)null, (Icon)null, opaque, s != null, StringUtil.isEmptyOrSpaces(s) ? null : s, -1);
        this.myComponent.setOpaque(opaque);
        this.myRendererComponent.setBackground(this.myComponent.getBackground());
        return (Component)this.myRendererComponent;
    }
    
    protected String getSeparatorAbove(final JList list, final Object o, final int n) {
        return (n > 0 && list.getModel().getElementAt(n - 1) == null) ? "" : null;
    }
    
    protected void customizeCellRenderer(final SimpleColoredComponent simpleColoredComponent, final JList list, Object name, final int n, final boolean b, final boolean b2) {
        if (name instanceof InvalidItem) {
            this.appendNotFound(((InvalidItem)name).myValue, b);
        }
        else {
            if (name instanceof CidrBuildTarget) {
                final CidrBuildTarget cidrBuildTarget = (CidrBuildTarget)name;
                simpleColoredComponent.setIcon(cidrBuildTarget.getIcon());
                simpleColoredComponent.append(cidrBuildTarget.getName());
                if (this.hasTargetsInSeveralProjects) {
                    simpleColoredComponent.append(" (" + cidrBuildTarget.getProjectName() + ")", this.grayed(b));
                }
                return;
            }
            if (name instanceof CidrBuildConfiguration) {
                name = ((CidrBuildConfiguration)name).getName();
            }
            this.addValue(simpleColoredComponent, list, name, b);
        }
    }
    
    protected void appendNotFound(final Object o, final boolean b) {
        this.myComponent.append(String.valueOf(o), b ? SimpleTextAttributes.REGULAR_ATTRIBUTES : SimpleTextAttributes.ERROR_ATTRIBUTES);
        this.myComponent.append(" (" + (this.isChecking() ? CidrBundle.message("test.configuration.checking", new Object[0]) : CidrBundle.message("build.configuration.dialog.item.parameterNotFound", new Object[0])) + ")", this.grayed(b));
    }
    
    protected boolean isChecking() {
        return false;
    }
    
    protected SimpleTextAttributes grayed(final boolean b) {
        return b ? SimpleTextAttributes.REGULAR_ATTRIBUTES : SimpleTextAttributes.GRAYED_ATTRIBUTES;
    }
    
    protected void addValue(final SimpleColoredComponent simpleColoredComponent, final JList list, Object o, final boolean b) {
        SimpleTextAttributes simpleTextAttributes = SimpleTextAttributes.REGULAR_ATTRIBUTES;
        if (o == null) {
            o = ((list.getModel().getSize() == 0) ? CidrBundle.message("build.configuration.dialog.item.parameterNoVariants", new Object[0]) : CidrBundle.message("build.configuration.dialog.item.parameterNotSelected", new Object[0]));
            simpleTextAttributes = this.grayed(b);
        }
        simpleColoredComponent.append(String.valueOf(o), simpleTextAttributes);
    }
}
