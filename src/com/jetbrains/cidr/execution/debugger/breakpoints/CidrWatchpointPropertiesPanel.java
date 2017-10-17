// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.execution.debugger.breakpoints;

import java.awt.Color;
import com.intellij.uiDesigner.core.Spacer;
import java.awt.Component;
import java.awt.Dimension;
import com.intellij.uiDesigner.core.GridConstraints;
import java.awt.LayoutManager;
import com.intellij.uiDesigner.core.GridLayoutManager;
import java.awt.Insets;
import javax.swing.JComponent;
import com.intellij.xdebugger.XDebugProcess;
import com.intellij.xdebugger.XDebugSession;
import com.jetbrains.cidr.execution.debugger.CidrDebugProcess;
import com.intellij.xdebugger.XDebuggerManager;
import javax.swing.ComboBoxModel;
import com.intellij.ui.EnumComboBoxModel;
import com.jetbrains.cidr.execution.debugger.backend.LLWatchpoint;
import org.jetbrains.annotations.NotNull;
import com.intellij.openapi.project.Project;
import javax.swing.JLabel;
import javax.swing.JComboBox;
import javax.swing.JPanel;
import com.intellij.xdebugger.breakpoints.XBreakpoint;
import com.intellij.xdebugger.breakpoints.ui.XBreakpointCustomPropertiesPanel;

class CidrWatchpointPropertiesPanel extends XBreakpointCustomPropertiesPanel<XBreakpoint<CidrWatchpointType.CidrWatchpointProperties>>
{
    private JPanel rootPane;
    private JComboBox myLifetimeComboBox;
    private JComboBox myAccessComboBox;
    private JLabel errorLabel;
    private JLabel myLifeTimeLabel;
    
    public CidrWatchpointPropertiesPanel(@NotNull final Project project) {
        if (project == null) {
            throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "project", "com/jetbrains/cidr/execution/debugger/breakpoints/CidrWatchpointPropertiesPanel", "<init>"));
        }
        this.a();
        this.myLifetimeComboBox.setModel((ComboBoxModel)new EnumComboBoxModel((Class)LLWatchpoint.Lifetime.class));
        this.myAccessComboBox.setModel((ComboBoxModel)new EnumComboBoxModel((Class)LLWatchpoint.AccessType.class));
        final XDebugSession currentSession = XDebuggerManager.getInstance(project).getCurrentSession();
        XDebugProcess debugProcess = null;
        Label_0106: {
            try {
                if (currentSession == null) {
                    debugProcess = null;
                    break Label_0106;
                }
            }
            catch (IllegalArgumentException ex) {
                throw a(ex);
            }
            debugProcess = currentSession.getDebugProcess();
        }
        final CidrDebugProcess cidrDebugProcess = (CidrDebugProcess)debugProcess;
        Label_0131: {
            try {
                if (!(cidrDebugProcess instanceof CidrDebugProcess)) {
                    return;
                }
                final CidrDebugProcess cidrDebugProcess2 = cidrDebugProcess;
                final CidrDebugProcess cidrDebugProcess3 = cidrDebugProcess2;
                final boolean b = cidrDebugProcess3.supportsWatchpointLifetime();
                if (!b) {
                    break Label_0131;
                }
                return;
            }
            catch (IllegalArgumentException ex2) {
                throw a(ex2);
            }
            try {
                final CidrDebugProcess cidrDebugProcess2 = cidrDebugProcess;
                final CidrDebugProcess cidrDebugProcess3 = cidrDebugProcess2;
                final boolean b = cidrDebugProcess3.supportsWatchpointLifetime();
                if (!b) {
                    this.myLifetimeComboBox.setVisible(false);
                    this.myLifeTimeLabel.setVisible(false);
                }
            }
            catch (IllegalArgumentException ex3) {
                throw a(ex3);
            }
        }
    }
    
    @NotNull
    public JComponent getComponent() {
        JPanel rootPane;
        try {
            rootPane = this.rootPane;
            if (rootPane == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/execution/debugger/breakpoints/CidrWatchpointPropertiesPanel", "getComponent"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        return rootPane;
    }
    
    public void saveTo(@NotNull final XBreakpoint<CidrWatchpointType.CidrWatchpointProperties> xBreakpoint) {
        try {
            if (xBreakpoint == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "breakpoint", "com/jetbrains/cidr/execution/debugger/breakpoints/CidrWatchpointPropertiesPanel", "saveTo"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        final CidrWatchpointType.CidrWatchpointProperties cidrWatchpointProperties = (CidrWatchpointType.CidrWatchpointProperties)xBreakpoint.getProperties();
        try {
            if (cidrWatchpointProperties != null) {
                cidrWatchpointProperties.setAccessType((LLWatchpoint.AccessType)this.myAccessComboBox.getSelectedItem());
                cidrWatchpointProperties.setLifetime((LLWatchpoint.Lifetime)this.myLifetimeComboBox.getSelectedItem());
            }
        }
        catch (IllegalArgumentException ex2) {
            throw a(ex2);
        }
    }
    
    public void loadFrom(@NotNull final XBreakpoint<CidrWatchpointType.CidrWatchpointProperties> xBreakpoint) {
        try {
            if (xBreakpoint == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "breakpoint", "com/jetbrains/cidr/execution/debugger/breakpoints/CidrWatchpointPropertiesPanel", "loadFrom"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        final CidrWatchpointType.CidrWatchpointProperties cidrWatchpointProperties = (CidrWatchpointType.CidrWatchpointProperties)xBreakpoint.getProperties();
        Label_0107: {
            JLabel label = null;
            Label_0098: {
                try {
                    if (cidrWatchpointProperties == null) {
                        break Label_0107;
                    }
                    final CidrWatchpointPropertiesPanel cidrWatchpointPropertiesPanel = this;
                    final JComboBox comboBox = cidrWatchpointPropertiesPanel.myLifetimeComboBox;
                    final CidrWatchpointType.CidrWatchpointProperties cidrWatchpointProperties2 = cidrWatchpointProperties;
                    final LLWatchpoint.Lifetime lifetime = cidrWatchpointProperties2.getLifetime();
                    comboBox.setSelectedItem(lifetime);
                    final CidrWatchpointPropertiesPanel cidrWatchpointPropertiesPanel2 = this;
                    final JComboBox comboBox2 = cidrWatchpointPropertiesPanel2.myAccessComboBox;
                    final CidrWatchpointType.CidrWatchpointProperties cidrWatchpointProperties3 = cidrWatchpointProperties;
                    final LLWatchpoint.AccessType accessType = cidrWatchpointProperties3.getAccessType();
                    comboBox2.setSelectedItem(accessType);
                    final CidrWatchpointPropertiesPanel cidrWatchpointPropertiesPanel3 = this;
                    label = cidrWatchpointPropertiesPanel3.errorLabel;
                    final CidrWatchpointType.CidrWatchpointProperties cidrWatchpointProperties4 = cidrWatchpointProperties;
                    final String s = cidrWatchpointProperties4.getError();
                    if (s != null) {
                        break Label_0098;
                    }
                    break Label_0098;
                }
                catch (IllegalArgumentException ex2) {
                    throw a(ex2);
                }
                try {
                    final CidrWatchpointPropertiesPanel cidrWatchpointPropertiesPanel = this;
                    final JComboBox comboBox = cidrWatchpointPropertiesPanel.myLifetimeComboBox;
                    final CidrWatchpointType.CidrWatchpointProperties cidrWatchpointProperties2 = cidrWatchpointProperties;
                    final LLWatchpoint.Lifetime lifetime = cidrWatchpointProperties2.getLifetime();
                    comboBox.setSelectedItem(lifetime);
                    final CidrWatchpointPropertiesPanel cidrWatchpointPropertiesPanel2 = this;
                    final JComboBox comboBox2 = cidrWatchpointPropertiesPanel2.myAccessComboBox;
                    final CidrWatchpointType.CidrWatchpointProperties cidrWatchpointProperties3 = cidrWatchpointProperties;
                    final LLWatchpoint.AccessType accessType = cidrWatchpointProperties3.getAccessType();
                    comboBox2.setSelectedItem(accessType);
                    final CidrWatchpointPropertiesPanel cidrWatchpointPropertiesPanel3 = this;
                    label = cidrWatchpointPropertiesPanel3.errorLabel;
                    final CidrWatchpointType.CidrWatchpointProperties cidrWatchpointProperties4 = cidrWatchpointProperties;
                    final String s = cidrWatchpointProperties4.getError();
                    if (s != null) {
                        final boolean visible = true;
                        break Label_0107;
                    }
                }
                catch (IllegalArgumentException ex3) {
                    throw a(ex3);
                }
            }
            final boolean visible = false;
            try {
                label.setVisible(visible);
                if (cidrWatchpointProperties.getError() != null) {
                    this.errorLabel.setText(cidrWatchpointProperties.getError());
                }
                return;
            }
            catch (IllegalArgumentException ex4) {
                throw a(ex4);
            }
        }
        this.myLifetimeComboBox.setEnabled(false);
        this.myAccessComboBox.setEnabled(false);
        this.errorLabel.setVisible(false);
    }
    
    private /* synthetic */ void a() {
        final JPanel rootPane = new JPanel();
        (this.rootPane = rootPane).setLayout((LayoutManager)new GridLayoutManager(1, 1, new Insets(0, 0, 0, 0), -1, -1, false, false));
        final JPanel panel = new JPanel();
        panel.setLayout((LayoutManager)new GridLayoutManager(3, 2, new Insets(0, 0, 0, 0), -1, -1, false, false));
        rootPane.add(panel, new GridConstraints(0, 0, 1, 1, 0, 3, 3, 3, (Dimension)null, (Dimension)null, (Dimension)null));
        panel.add((Component)new Spacer(), new GridConstraints(2, 1, 1, 1, 0, 2, 1, 6, (Dimension)null, (Dimension)null, (Dimension)null));
        final JPanel panel2 = new JPanel();
        panel2.setLayout((LayoutManager)new GridLayoutManager(1, 5, new Insets(0, 0, 0, 0), -1, -1, false, false));
        panel.add(panel2, new GridConstraints(0, 0, 1, 2, 0, 3, 3, 3, (Dimension)null, (Dimension)null, (Dimension)null));
        panel2.add(this.myLifetimeComboBox = new JComboBox(), new GridConstraints(0, 1, 1, 1, 8, 1, 2, 0, (Dimension)null, (Dimension)null, (Dimension)null));
        final JLabel myLifeTimeLabel = new JLabel();
        (this.myLifeTimeLabel = myLifeTimeLabel).setText("Life Time:");
        panel2.add(myLifeTimeLabel, new GridConstraints(0, 0, 1, 1, 8, 0, 0, 0, (Dimension)null, (Dimension)null, (Dimension)null));
        panel2.add(this.myAccessComboBox = new JComboBox(), new GridConstraints(0, 3, 1, 1, 8, 1, 2, 0, (Dimension)null, (Dimension)null, (Dimension)null));
        final JLabel label = new JLabel();
        label.setText("Access Type:");
        panel2.add(label, new GridConstraints(0, 2, 1, 1, 8, 0, 0, 0, (Dimension)null, (Dimension)null, (Dimension)null));
        panel2.add((Component)new Spacer(), new GridConstraints(0, 4, 1, 1, 0, 1, 6, 1, (Dimension)null, (Dimension)null, (Dimension)null));
        final JPanel panel3 = new JPanel();
        panel3.setLayout((LayoutManager)new GridLayoutManager(1, 2, new Insets(0, 0, 0, 0), -1, -1, false, false));
        panel.add(panel3, new GridConstraints(1, 0, 1, 1, 0, 3, 3, 3, (Dimension)null, (Dimension)null, (Dimension)null));
        final JLabel errorLabel = new JLabel();
        (this.errorLabel = errorLabel).setText("Error");
        errorLabel.setForeground(new Color(-65536));
        panel3.add(errorLabel, new GridConstraints(0, 0, 1, 1, 8, 0, 0, 0, (Dimension)null, (Dimension)null, (Dimension)null));
        panel3.add((Component)new Spacer(), new GridConstraints(0, 1, 1, 1, 0, 1, 6, 1, (Dimension)null, (Dimension)null, (Dimension)null));
    }
    
    private static IllegalArgumentException a(final IllegalArgumentException ex) {
        return ex;
    }
}
