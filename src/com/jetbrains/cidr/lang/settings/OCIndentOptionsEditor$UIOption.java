// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.lang.settings;

import java.awt.Component;
import com.intellij.util.ReflectionUtil;
import javax.swing.JComponent;
import javax.swing.Box;
import org.jetbrains.annotations.NotNull;
import javax.swing.JLabel;
import javax.swing.JCheckBox;
import javax.swing.JTextField;
import java.lang.reflect.Field;

private class UIOption
{
    private Field field;
    private Class<?> type;
    private JTextField edit;
    private JCheckBox checkBox;
    private JLabel label;
    
    public UIOption(@NotNull final String s, final String s2) {
        if (s == null) {
            throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "fieldName", "com/jetbrains/cidr/lang/settings/OCIndentOptionsEditor$UIOption", "<init>"));
        }
        if (s2 == null) {
            throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "title", "com/jetbrains/cidr/lang/settings/OCIndentOptionsEditor$UIOption", "<init>"));
        }
        if (s == "SPACE_SEPARATOR") {
            OCIndentOptionsEditor.this.add((JComponent)new Box.Filler(OCIndentOptionsEditor.access$000(), OCIndentOptionsEditor.access$000(), OCIndentOptionsEditor.access$000()));
            return;
        }
        try {
            this.field = ReflectionUtil.findField((Class)OCCodeStyleSettings.class, (Class)null, s);
        }
        catch (NoSuchFieldException ex4) {}
        Label_0155: {
            try {
                if (this.field != null) {
                    final Class<?> type = this.field.getType();
                    break Label_0155;
                }
            }
            catch (NoSuchFieldException ex) {
                throw b(ex);
            }
            final Class<?> type = null;
            try {
                this.type = type;
                if (Boolean.TYPE.equals(this.type)) {
                    OCIndentOptionsEditor.this.add((JComponent)(this.checkBox = new JCheckBox(s2)), OCIndentOptionsEditor.access$100().contains(s));
                    return;
                }
            }
            catch (NoSuchFieldException ex2) {
                throw b(ex2);
            }
        }
        try {
            if (Integer.TYPE.equals(this.type)) {
                this.edit = OCIndentOptionsEditor.access$200(OCIndentOptionsEditor.this);
                (this.label = new JLabel(s2 + ":")).setLabelFor(this.edit);
                OCIndentOptionsEditor.this.add((JComponent)this.label, (JComponent)this.edit);
            }
        }
        catch (NoSuchFieldException ex3) {
            throw b(ex3);
        }
    }
    
    public void setEnabled(final boolean enabled) {
        try {
            if (Boolean.TYPE.equals(this.type)) {
                this.checkBox.setEnabled(enabled);
                return;
            }
        }
        catch (IllegalArgumentException ex) {
            throw b(ex);
        }
        try {
            if (Integer.TYPE.equals(this.type)) {
                this.edit.setEnabled(enabled);
                this.label.setEnabled(enabled);
            }
        }
        catch (IllegalArgumentException ex2) {
            throw b(ex2);
        }
    }
    
    public void writeToUI(final OCCodeStyleSettings ocCodeStyleSettings) {
        try {
            try {
                if (Boolean.TYPE.equals(this.type)) {
                    this.checkBox.setSelected(this.field.getBoolean(ocCodeStyleSettings));
                    return;
                }
            }
            catch (IllegalAccessException ex) {
                throw b(ex);
            }
            try {
                if (Integer.TYPE.equals(this.type)) {
                    this.edit.setText(String.valueOf(this.field.getInt(ocCodeStyleSettings)));
                }
            }
            catch (IllegalAccessException ex2) {
                throw b(ex2);
            }
        }
        catch (IllegalAccessException ex3) {}
    }
    
    public void readFromUI(final OCCodeStyleSettings ocCodeStyleSettings) {
        try {
            try {
                if (Boolean.TYPE.equals(this.type)) {
                    this.field.setBoolean(ocCodeStyleSettings, this.checkBox.isSelected());
                    return;
                }
            }
            catch (IllegalAccessException ex) {
                throw b(ex);
            }
            try {
                if (Integer.TYPE.equals(this.type)) {
                    this.field.setInt(ocCodeStyleSettings, OCIndentOptionsEditor.access$300(OCIndentOptionsEditor.this, this.edit, 0, this.field.getInt(ocCodeStyleSettings)));
                }
            }
            catch (IllegalAccessException ex2) {
                throw b(ex2);
            }
        }
        catch (IllegalAccessException ex3) {}
    }
    
    public boolean isModified(final OCCodeStyleSettings ocCodeStyleSettings) {
        try {
            try {
                if (Boolean.TYPE.equals(this.type)) {
                    return OCIndentOptionsEditor.access$400(this.checkBox, this.field.getBoolean(ocCodeStyleSettings));
                }
            }
            catch (IllegalArgumentException ex) {
                throw b(ex);
            }
            try {
                if (Integer.TYPE.equals(this.type)) {
                    return OCIndentOptionsEditor.access$500(this.edit, this.field.getInt(ocCodeStyleSettings));
                }
            }
            catch (IllegalArgumentException ex2) {
                throw b(ex2);
            }
        }
        catch (IllegalAccessException ex3) {}
        return false;
    }
    
    private static Exception b(final Exception ex) {
        return ex;
    }
}
