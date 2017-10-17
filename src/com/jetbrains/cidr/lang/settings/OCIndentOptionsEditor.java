// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.lang.settings;

import java.awt.Component;
import com.intellij.util.ReflectionUtil;
import javax.swing.Box;
import java.lang.reflect.Field;
import java.util.Collection;
import java.util.HashSet;
import java.util.Arrays;
import org.jetbrains.annotations.Nullable;
import com.intellij.psi.codeStyle.CustomCodeStyleSettings;
import com.intellij.psi.codeStyle.LanguageCodeStyleSettingsProvider;
import javax.swing.JComponent;
import com.intellij.openapi.application.ApplicationBundle;
import com.intellij.psi.codeStyle.CommonCodeStyleSettings;
import java.util.Iterator;
import com.intellij.psi.codeStyle.CodeStyleSettings;
import org.jetbrains.annotations.NotNull;
import java.util.Set;
import java.awt.Dimension;
import java.util.ArrayList;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JTextField;
import com.intellij.psi.codeStyle.CodeStyleSettingsCustomizable;
import com.intellij.application.options.SmartIndentOptionsEditor;

public class OCIndentOptionsEditor extends SmartIndentOptionsEditor implements CodeStyleSettingsCustomizable
{
    private JTextField myLabelIndent;
    private JLabel myLabelIndentLabel;
    private JCheckBox myLabelIndentAbsolute;
    private final ArrayList<UIOption> indentOptions;
    private final OCLanguageCodeStyleSettingsProvider languageProvider;
    private static final Dimension SPACE_BOX;
    private static final Set<String> INDENTED_OPTIONS;
    
    public OCIndentOptionsEditor(final OCLanguageCodeStyleSettingsProvider languageProvider) {
        this.indentOptions = new ArrayList<UIOption>();
        this.languageProvider = languageProvider;
    }
    
    protected void addUIOption(@NotNull final String s, @NotNull final String s2) {
        try {
            if (s == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "fieldName", "com/jetbrains/cidr/lang/settings/OCIndentOptionsEditor", "addUIOption"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        try {
            if (s2 == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "title", "com/jetbrains/cidr/lang/settings/OCIndentOptionsEditor", "addUIOption"));
            }
        }
        catch (IllegalArgumentException ex2) {
            throw a(ex2);
        }
        this.indentOptions.add(new UIOption(s, s2));
    }
    
    protected static OCCodeStyleSettings getOCSettings(final CodeStyleSettings codeStyleSettings) {
        return (OCCodeStyleSettings)codeStyleSettings.getCustomSettings((Class)OCCodeStyleSettings.class);
    }
    
    public void setEnabled(final boolean enabled) {
        super.setEnabled(enabled);
        this.myLabelIndent.setEnabled(enabled);
        this.myLabelIndentLabel.setEnabled(enabled);
        this.myLabelIndentAbsolute.setEnabled(enabled);
        final Iterator<UIOption> iterator = this.indentOptions.iterator();
        while (iterator.hasNext()) {
            iterator.next().setEnabled(enabled);
        }
    }
    
    public void reset(@NotNull final CodeStyleSettings codeStyleSettings, @NotNull final CommonCodeStyleSettings.IndentOptions indentOptions) {
        try {
            if (codeStyleSettings == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "settings", "com/jetbrains/cidr/lang/settings/OCIndentOptionsEditor", "reset"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        try {
            if (indentOptions == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "options", "com/jetbrains/cidr/lang/settings/OCIndentOptionsEditor", "reset"));
            }
        }
        catch (IllegalArgumentException ex2) {
            throw a(ex2);
        }
        super.reset(codeStyleSettings, indentOptions);
        this.myLabelIndent.setText(Integer.toString(indentOptions.LABEL_INDENT_SIZE));
        this.myLabelIndentAbsolute.setSelected(indentOptions.LABEL_INDENT_ABSOLUTE);
        final OCCodeStyleSettings ocSettings = getOCSettings(codeStyleSettings);
        final Iterator<UIOption> iterator = this.indentOptions.iterator();
        while (iterator.hasNext()) {
            iterator.next().writeToUI(ocSettings);
        }
    }
    
    public void apply(final CodeStyleSettings codeStyleSettings, final CommonCodeStyleSettings.IndentOptions indentOptions) {
        super.apply(codeStyleSettings, indentOptions);
        indentOptions.LABEL_INDENT_SIZE = this.getFieldValue(this.myLabelIndent, Integer.MIN_VALUE, indentOptions.LABEL_INDENT_SIZE);
        indentOptions.LABEL_INDENT_ABSOLUTE = this.myLabelIndentAbsolute.isSelected();
        final OCCodeStyleSettings ocSettings = getOCSettings(codeStyleSettings);
        final Iterator<UIOption> iterator = this.indentOptions.iterator();
        while (iterator.hasNext()) {
            iterator.next().readFromUI(ocSettings);
        }
    }
    
    public boolean isModified(final CodeStyleSettings codeStyleSettings, final CommonCodeStyleSettings.IndentOptions indentOptions) {
        final OCCodeStyleSettings ocSettings = getOCSettings(codeStyleSettings);
        boolean b = super.isModified(codeStyleSettings, indentOptions) | isFieldModified(this.myLabelIndent, indentOptions.LABEL_INDENT_SIZE) | isFieldModified(this.myLabelIndentAbsolute, indentOptions.LABEL_INDENT_ABSOLUTE);
        final Iterator<UIOption> iterator = this.indentOptions.iterator();
        while (iterator.hasNext()) {
            b |= iterator.next().isModified(ocSettings);
        }
        return b;
    }
    
    protected void addComponents() {
        super.addComponents();
        this.myLabelIndent = new JTextField(4);
        this.add((JComponent)(this.myLabelIndentLabel = new JLabel(ApplicationBundle.message("editbox.indent.label.indent", new Object[0]))), (JComponent)this.myLabelIndent);
        this.add((JComponent)(this.myLabelIndentAbsolute = new JCheckBox(ApplicationBundle.message("checkbox.indent.absolute.label.indent", new Object[0]))), true);
        this.languageProvider.customizeSettings((CodeStyleSettingsCustomizable)this, LanguageCodeStyleSettingsProvider.SettingsType.INDENT_SETTINGS);
    }
    
    public void showAllStandardOptions() {
    }
    
    public void showStandardOptions(final String... array) {
    }
    
    public void showCustomOption(final Class<? extends CustomCodeStyleSettings> clazz, final String s, final String s2, @Nullable final String s3, final Object... array) {
        this.showCustomOption(clazz, s, s2, s3, null, null, array);
    }
    
    public void showCustomOption(final Class<? extends CustomCodeStyleSettings> clazz, final String s, final String s2, @Nullable final String s3, @Nullable final CodeStyleSettingsCustomizable.OptionAnchor optionAnchor, @Nullable final String s4, final Object... array) {
        this.addUIOption(s, s2);
    }
    
    public void renameStandardOption(final String s, final String s2) {
    }
    
    public void moveStandardOption(final String s, final String s2) {
    }
    
    static /* synthetic */ JTextField access$200(final OCIndentOptionsEditor ocIndentOptionsEditor) {
        return ocIndentOptionsEditor.createIndentTextField();
    }
    
    static /* synthetic */ int access$300(final OCIndentOptionsEditor ocIndentOptionsEditor, final JTextField textField, final int n, final int n2) {
        return ocIndentOptionsEditor.getFieldValue(textField, n, n2);
    }
    
    static /* synthetic */ boolean access$400(final JCheckBox checkBox, final boolean b) {
        return isFieldModified(checkBox, b);
    }
    
    static /* synthetic */ boolean access$500(final JTextField textField, final int n) {
        return isFieldModified(textField, n);
    }
    
    static {
        SPACE_BOX = new Dimension(10, 8);
        INDENTED_OPTIONS = new HashSet<String>(Arrays.asList("INDENT_INTERFACE_MEMBERS_EXCEPT_IVARS_BLOCK", "INDENT_DIRECTIVE_AS_CODE"));
    }
    
    private static IllegalArgumentException a(final IllegalArgumentException ex) {
        return ex;
    }
    
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
                OCIndentOptionsEditor.this.add((JComponent)new Box.Filler(OCIndentOptionsEditor.SPACE_BOX, OCIndentOptionsEditor.SPACE_BOX, OCIndentOptionsEditor.SPACE_BOX));
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
                        OCIndentOptionsEditor.this.add((JComponent)(this.checkBox = new JCheckBox(s2)), OCIndentOptionsEditor.INDENTED_OPTIONS.contains(s));
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
}
