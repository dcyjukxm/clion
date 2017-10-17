// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.execution.debugger.breakpoints;

import javax.swing.JSeparator;
import java.awt.Dimension;
import com.intellij.util.ui.UIUtil$ComponentStyle;
import java.awt.Component;
import java.awt.Insets;
import java.awt.GridBagConstraints;
import javax.swing.JLabel;
import java.awt.LayoutManager;
import java.awt.GridBagLayout;
import com.intellij.openapi.util.text.StringUtil;
import com.intellij.util.ui.UIUtil;
import javax.swing.JComponent;
import org.jetbrains.annotations.NotNull;
import com.intellij.openapi.project.Project;
import com.intellij.ui.components.JBLabel;
import com.intellij.ui.EditorTextField;
import com.intellij.ui.TextFieldWithAutoCompletion;
import javax.swing.JPanel;
import com.intellij.xdebugger.breakpoints.XBreakpoint;
import com.intellij.xdebugger.breakpoints.ui.XBreakpointCustomPropertiesPanel;

public abstract class CidrSymbolicBreakpointPropertiesPanel extends XBreakpointCustomPropertiesPanel<XBreakpoint<CidrSymbolicBreakpointType.Properties>>
{
    private JPanel myPanel;
    private TextFieldWithAutoCompletion mySymbolPatternField;
    private EditorTextField myModuleNameField;
    private JBLabel mySymbolExampleLabel;
    private JBLabel myModuleExampleLabel;
    protected final Project myProject;
    
    protected CidrSymbolicBreakpointPropertiesPanel(@NotNull final Project myProject) {
        if (myProject == null) {
            throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "project", "com/jetbrains/cidr/execution/debugger/breakpoints/CidrSymbolicBreakpointPropertiesPanel", "<init>"));
        }
        this.myProject = myProject;
        this.a();
        this.myModuleNameField.setPlaceholder("All Modules");
    }
    
    @NotNull
    public JComponent getComponent() {
        JPanel myPanel;
        try {
            myPanel = this.myPanel;
            if (myPanel == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/execution/debugger/breakpoints/CidrSymbolicBreakpointPropertiesPanel", "getComponent"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        return myPanel;
    }
    
    private void b() {
        this.mySymbolPatternField = this.createSymbolNameField();
        (this.mySymbolExampleLabel = new JBLabel()).setFontColor(UIUtil.FontColor.BRIGHTER);
        (this.myModuleExampleLabel = new JBLabel()).setFontColor(UIUtil.FontColor.BRIGHTER);
    }
    
    @NotNull
    protected abstract TextFieldWithAutoCompletion createSymbolNameField();
    
    public TextFieldWithAutoCompletion getSymbolPatternField() {
        return this.mySymbolPatternField;
    }
    
    public void saveTo(@NotNull final XBreakpoint<CidrSymbolicBreakpointType.Properties> xBreakpoint) {
        try {
            if (xBreakpoint == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "breakpoint", "com/jetbrains/cidr/execution/debugger/breakpoints/CidrSymbolicBreakpointPropertiesPanel", "saveTo"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        final CidrSymbolicBreakpointType.Properties properties = (CidrSymbolicBreakpointType.Properties)xBreakpoint.getProperties();
        properties.setSymbolPattern(this.mySymbolPatternField.getText().trim());
        properties.setModuleName(StringUtil.nullize(this.myModuleNameField.getText().trim()));
    }
    
    public void loadFrom(@NotNull final XBreakpoint<CidrSymbolicBreakpointType.Properties> xBreakpoint) {
        try {
            if (xBreakpoint == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "breakpoint", "com/jetbrains/cidr/execution/debugger/breakpoints/CidrSymbolicBreakpointPropertiesPanel", "loadFrom"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        final CidrSymbolicBreakpointType.Properties properties = (CidrSymbolicBreakpointType.Properties)xBreakpoint.getProperties();
        try {
            if (properties != null) {
                this.mySymbolPatternField.setText(properties.getSymbolPattern());
                this.myModuleNameField.setText(properties.getModuleName());
            }
        }
        catch (IllegalArgumentException ex2) {
            throw a(ex2);
        }
    }
    
    private /* synthetic */ void a() {
        this.b();
        final JPanel myPanel = new JPanel();
        (this.myPanel = myPanel).setLayout(new GridBagLayout());
        final JLabel label = new JLabel();
        label.setText("Module name");
        final GridBagConstraints gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.anchor = 17;
        gridBagConstraints.insets = new Insets(0, 0, 0, 10);
        myPanel.add(label, gridBagConstraints);
        final JLabel label2 = new JLabel();
        label2.setText("Symbol name");
        final GridBagConstraints gridBagConstraints2 = new GridBagConstraints();
        gridBagConstraints2.gridx = 0;
        gridBagConstraints2.gridy = 0;
        gridBagConstraints2.anchor = 17;
        gridBagConstraints2.insets = new Insets(0, 0, 0, 10);
        myPanel.add(label2, gridBagConstraints2);
        final EditorTextField myModuleNameField = new EditorTextField();
        this.myModuleNameField = myModuleNameField;
        final GridBagConstraints gridBagConstraints3 = new GridBagConstraints();
        gridBagConstraints3.gridx = 1;
        gridBagConstraints3.gridy = 2;
        gridBagConstraints3.weightx = 1.0;
        gridBagConstraints3.anchor = 17;
        gridBagConstraints3.fill = 2;
        myPanel.add((Component)myModuleNameField, gridBagConstraints3);
        final JBLabel mySymbolExampleLabel = this.mySymbolExampleLabel;
        mySymbolExampleLabel.setComponentStyle(UIUtil$ComponentStyle.SMALL);
        mySymbolExampleLabel.setMinimumSize(new Dimension(380, 28));
        ((JLabel)mySymbolExampleLabel).setText("<html>E.g. [UIViewController viewDidLoad], viewWillAppear:,<br/> +[UIView commitAnimations], Namespace::Clazz::foo(int *)</html>");
        mySymbolExampleLabel.setFontColor(UIUtil.FontColor.BRIGHTER);
        final GridBagConstraints gridBagConstraints4 = new GridBagConstraints();
        gridBagConstraints4.gridx = 1;
        gridBagConstraints4.gridy = 1;
        gridBagConstraints4.weightx = 1.0;
        gridBagConstraints4.anchor = 18;
        gridBagConstraints4.insets = new Insets(0, 7, 5, 0);
        myPanel.add((Component)mySymbolExampleLabel, gridBagConstraints4);
        final JBLabel myModuleExampleLabel = this.myModuleExampleLabel;
        myModuleExampleLabel.setComponentStyle(UIUtil$ComponentStyle.SMALL);
        ((JLabel)myModuleExampleLabel).setText("E.g. UIKit, libobjc.A.dylib, IOSApp");
        myModuleExampleLabel.setFontColor(UIUtil.FontColor.BRIGHTER);
        final GridBagConstraints gridBagConstraints5 = new GridBagConstraints();
        gridBagConstraints5.gridx = 1;
        gridBagConstraints5.gridy = 3;
        gridBagConstraints5.weightx = 1.0;
        gridBagConstraints5.anchor = 18;
        gridBagConstraints5.insets = new Insets(0, 7, 5, 0);
        myPanel.add((Component)myModuleExampleLabel, gridBagConstraints5);
        final TextFieldWithAutoCompletion mySymbolPatternField = this.mySymbolPatternField;
        final GridBagConstraints gridBagConstraints6 = new GridBagConstraints();
        gridBagConstraints6.gridx = 1;
        gridBagConstraints6.gridy = 0;
        gridBagConstraints6.weightx = 1.0;
        gridBagConstraints6.anchor = 17;
        gridBagConstraints6.fill = 2;
        myPanel.add((Component)mySymbolPatternField, gridBagConstraints6);
        final JSeparator separator = new JSeparator();
        final GridBagConstraints gridBagConstraints7 = new GridBagConstraints();
        gridBagConstraints7.gridx = 0;
        gridBagConstraints7.gridy = 4;
        gridBagConstraints7.gridwidth = 2;
        gridBagConstraints7.weighty = 1.0;
        gridBagConstraints7.fill = 2;
        myPanel.add(separator, gridBagConstraints7);
        label.setLabelFor((Component)mySymbolPatternField);
        label2.setLabelFor((Component)mySymbolPatternField);
    }
    
    private static IllegalArgumentException a(final IllegalArgumentException ex) {
        return ex;
    }
}
