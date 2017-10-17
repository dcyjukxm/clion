// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.execution.debugger.breakpoints;

import com.intellij.uiDesigner.core.Spacer;
import java.awt.FlowLayout;
import java.awt.Dimension;
import com.intellij.uiDesigner.core.GridConstraints;
import java.awt.LayoutManager;
import com.intellij.uiDesigner.core.GridLayoutManager;
import java.awt.Insets;
import org.jetbrains.annotations.Nullable;
import org.jetbrains.annotations.NotNull;
import javax.swing.JComponent;
import java.util.Enumeration;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.Component;
import javax.swing.AbstractButton;
import javax.swing.JRadioButton;
import com.intellij.ui.components.JBCheckBox;
import javax.swing.ButtonGroup;
import javax.swing.JPanel;
import com.intellij.xdebugger.breakpoints.XBreakpoint;
import com.intellij.xdebugger.breakpoints.ui.XBreakpointCustomPropertiesPanel;

public class CidrExceptionBreakpointPropertiesPanel extends XBreakpointCustomPropertiesPanel<XBreakpoint<CidrExceptionBreakpointType.Properties>>
{
    private JPanel myPanel;
    private JPanel myWhenThrownPanel;
    private ButtonGroup myWhenThrownGroup;
    private JBCheckBox myWhenCaughtCheckBox;
    private JBCheckBox myWhenThrownCheckBox;
    
    public CidrExceptionBreakpointPropertiesPanel() {
        this.a();
        this.myWhenThrownGroup = new ButtonGroup();
        for (final CidrExceptionBreakpointType.Properties.Type type : CidrExceptionBreakpointType.Properties.Type.values()) {
            Label_0131: {
                try {
                    if (!type.isAvailable()) {
                        break Label_0131;
                    }
                }
                catch (IllegalStateException ex) {
                    throw a(ex);
                }
                String s = null;
                Label_0100: {
                    try {
                        if (type == CidrExceptionBreakpointType.Properties.Type.ANY_EXCEPTION) {
                            s = type.getDisplayString();
                            break Label_0100;
                        }
                    }
                    catch (IllegalStateException ex2) {
                        throw a(ex2);
                    }
                    s = type.getDisplayString() + " only";
                }
                final JRadioButton radioButton = new JRadioButton(s);
                a(radioButton, type);
                this.myWhenThrownGroup.add(radioButton);
                this.myWhenThrownPanel.add(radioButton);
            }
        }
        Label_0195: {
            try {
                if (this.myWhenThrownGroup.getButtonCount() < 2) {
                    this.myWhenThrownPanel.setVisible(false);
                    break Label_0195;
                }
            }
            catch (IllegalStateException ex3) {
                throw a(ex3);
            }
            this.myWhenThrownCheckBox.setText(this.myWhenThrownCheckBox.getText() + ":");
        }
        this.myWhenThrownCheckBox.addChangeListener((ChangeListener)new ChangeListener() {
            @Override
            public void stateChanged(final ChangeEvent changeEvent) {
                CidrExceptionBreakpointPropertiesPanel.this.b();
            }
        });
    }
    
    private void b() {
        final boolean selected = this.myWhenThrownCheckBox.isSelected();
        final Enumeration<AbstractButton> elements = this.myWhenThrownGroup.getElements();
        try {
            while (elements.hasMoreElements()) {
                elements.nextElement().setEnabled(selected);
            }
        }
        catch (IllegalStateException ex) {
            throw a(ex);
        }
    }
    
    @NotNull
    public JComponent getComponent() {
        JPanel myPanel;
        try {
            myPanel = this.myPanel;
            if (myPanel == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/execution/debugger/breakpoints/CidrExceptionBreakpointPropertiesPanel", "getComponent"));
            }
        }
        catch (IllegalStateException ex) {
            throw a(ex);
        }
        return myPanel;
    }
    
    @Nullable
    private static AbstractButton a(final ButtonGroup buttonGroup) {
        final Enumeration<AbstractButton> elements = buttonGroup.getElements();
        while (elements.hasMoreElements()) {
            final AbstractButton abstractButton = elements.nextElement();
            try {
                if (abstractButton.isSelected()) {
                    return abstractButton;
                }
                continue;
            }
            catch (IllegalStateException ex) {
                throw a(ex);
            }
        }
        return null;
    }
    
    @Nullable
    private static AbstractButton a(@NotNull final ButtonGroup buttonGroup, @Nullable final Object o) {
        try {
            if (buttonGroup == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "group", "com/jetbrains/cidr/execution/debugger/breakpoints/CidrExceptionBreakpointPropertiesPanel", "findButton"));
            }
        }
        catch (IllegalStateException ex) {
            throw a(ex);
        }
        final Enumeration<AbstractButton> elements = buttonGroup.getElements();
        while (elements.hasMoreElements()) {
            final AbstractButton abstractButton = elements.nextElement();
            try {
                if (a(abstractButton) == o) {
                    return abstractButton;
                }
                continue;
            }
            catch (IllegalStateException ex2) {
                throw a(ex2);
            }
        }
        return null;
    }
    
    private static void a(final JRadioButton radioButton, final Object o) {
        radioButton.putClientProperty("value", o);
    }
    
    @Nullable
    private static Object a(@Nullable final AbstractButton abstractButton) {
        try {
            if (abstractButton == null) {
                return null;
            }
        }
        catch (IllegalStateException ex) {
            throw a(ex);
        }
        return abstractButton.getClientProperty("value");
    }
    
    public void saveTo(@NotNull final XBreakpoint<CidrExceptionBreakpointType.Properties> xBreakpoint) {
        try {
            if (xBreakpoint == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "breakpoint", "com/jetbrains/cidr/execution/debugger/breakpoints/CidrExceptionBreakpointPropertiesPanel", "saveTo"));
            }
        }
        catch (IllegalStateException ex) {
            throw a(ex);
        }
        final CidrExceptionBreakpointType.Properties properties = (CidrExceptionBreakpointType.Properties)xBreakpoint.getProperties();
        try {
            if (properties == null) {
                return;
            }
        }
        catch (IllegalStateException ex2) {
            throw a(ex2);
        }
        CidrExceptionBreakpointType.Properties properties3 = null;
        CidrExceptionBreakpointType.Properties.Type any_EXCEPTION = null;
        Label_0120: {
            Label_0095: {
                CidrExceptionBreakpointType.Properties properties2;
                try {
                    properties2 = properties;
                    if (this.myWhenThrownCheckBox.isSelected()) {
                        final CidrExceptionBreakpointType.Properties.Type whenThrown = (CidrExceptionBreakpointType.Properties.Type)a(a(this.myWhenThrownGroup));
                        break Label_0095;
                    }
                }
                catch (IllegalStateException ex3) {
                    throw a(ex3);
                }
                final CidrExceptionBreakpointType.Properties.Type whenThrown = null;
                try {
                    properties2.setWhenThrown(whenThrown);
                    properties3 = properties;
                    if (this.myWhenCaughtCheckBox.isSelected()) {
                        any_EXCEPTION = CidrExceptionBreakpointType.Properties.Type.ANY_EXCEPTION;
                        break Label_0120;
                    }
                }
                catch (IllegalStateException ex4) {
                    throw a(ex4);
                }
            }
            any_EXCEPTION = null;
        }
        properties3.setWhenCaught(any_EXCEPTION);
    }
    
    public void loadFrom(@NotNull final XBreakpoint<CidrExceptionBreakpointType.Properties> xBreakpoint) {
        try {
            if (xBreakpoint == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "breakpoint", "com/jetbrains/cidr/execution/debugger/breakpoints/CidrExceptionBreakpointPropertiesPanel", "loadFrom"));
            }
        }
        catch (IllegalStateException ex) {
            throw a(ex);
        }
        final CidrExceptionBreakpointType.Properties properties = (CidrExceptionBreakpointType.Properties)xBreakpoint.getProperties();
        try {
            if (properties == null) {
                this.myWhenThrownGroup.clearSelection();
                this.myWhenThrownCheckBox.setSelected(false);
                this.myWhenCaughtCheckBox.setSelected(false);
                return;
            }
        }
        catch (IllegalStateException ex2) {
            throw a(ex2);
        }
        final CidrExceptionBreakpointType.Properties.Type whenThrown = properties.getWhenThrown();
        JBCheckBox myWhenThrownCheckBox = null;
        boolean selected = false;
        Label_0108: {
            try {
                myWhenThrownCheckBox = this.myWhenThrownCheckBox;
                if (whenThrown != null) {
                    selected = true;
                    break Label_0108;
                }
            }
            catch (IllegalStateException ex3) {
                throw a(ex3);
            }
            selected = false;
        }
        myWhenThrownCheckBox.setSelected(selected);
        AbstractButton a = a(this.myWhenThrownGroup, whenThrown);
        if (a == null) {
            a = this.myWhenThrownGroup.getElements().nextElement();
        }
        JBCheckBox myWhenCaughtCheckBox = null;
        boolean selected2 = false;
        Label_0180: {
            try {
                this.myWhenThrownGroup.setSelected(a.getModel(), true);
                this.b();
                myWhenCaughtCheckBox = this.myWhenCaughtCheckBox;
                if (properties.getWhenCaught() != null) {
                    selected2 = true;
                    break Label_0180;
                }
            }
            catch (IllegalStateException ex4) {
                throw a(ex4);
            }
            selected2 = false;
        }
        myWhenCaughtCheckBox.setSelected(selected2);
    }
    
    private /* synthetic */ void a() {
        final JPanel myPanel = new JPanel();
        (this.myPanel = myPanel).setLayout((LayoutManager)new GridLayoutManager(2, 1, new Insets(0, 0, 0, 0), -1, -1, false, false));
        final JPanel panel = new JPanel();
        panel.setLayout((LayoutManager)new GridLayoutManager(2, 3, new Insets(0, 0, 0, 0), -1, -1, false, false));
        myPanel.add(panel, new GridConstraints(0, 0, 1, 1, 0, 3, 3, 3, (Dimension)null, (Dimension)null, (Dimension)null));
        final JPanel myWhenThrownPanel = new JPanel();
        (this.myWhenThrownPanel = myWhenThrownPanel).setLayout(new FlowLayout(1, 0, 0));
        panel.add(myWhenThrownPanel, new GridConstraints(0, 1, 1, 1, 0, 3, 3, 3, (Dimension)null, (Dimension)null, (Dimension)null));
        panel.add((Component)new Spacer(), new GridConstraints(0, 2, 1, 1, 0, 1, 6, 1, (Dimension)null, (Dimension)null, (Dimension)null));
        final JBCheckBox myWhenThrownCheckBox = new JBCheckBox();
        ((AbstractButton)(this.myWhenThrownCheckBox = myWhenThrownCheckBox)).setText("When thrown");
        panel.add((Component)myWhenThrownCheckBox, new GridConstraints(0, 0, 1, 1, 8, 0, 3, 3, (Dimension)null, (Dimension)null, (Dimension)null));
        final JBCheckBox myWhenCaughtCheckBox = new JBCheckBox();
        ((AbstractButton)(this.myWhenCaughtCheckBox = myWhenCaughtCheckBox)).setText("When caught");
        panel.add((Component)myWhenCaughtCheckBox, new GridConstraints(1, 0, 1, 3, 8, 0, 0, 0, (Dimension)null, (Dimension)null, (Dimension)null));
        myPanel.add((Component)new Spacer(), new GridConstraints(1, 0, 1, 1, 0, 2, 1, 6, (Dimension)null, (Dimension)null, (Dimension)null));
    }
    
    private static IllegalStateException a(final IllegalStateException ex) {
        return ex;
    }
}
