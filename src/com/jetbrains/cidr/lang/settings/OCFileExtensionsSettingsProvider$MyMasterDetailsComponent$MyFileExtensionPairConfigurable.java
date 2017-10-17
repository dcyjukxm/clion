// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.lang.settings;

import com.intellij.openapi.options.ConfigurationException;
import com.intellij.openapi.util.text.StringUtil;
import org.jetbrains.annotations.Nullable;
import java.util.List;
import com.intellij.util.ui.JBInsets;
import java.awt.BorderLayout;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import com.intellij.util.ArrayUtil;
import com.intellij.util.PlatformUtils;
import java.util.Collection;
import com.intellij.util.containers.ContainerUtil;
import com.jetbrains.cidr.lang.OCFileTypeHelpers;
import java.awt.Component;
import java.awt.Insets;
import com.intellij.ui.components.JBLabel;
import com.jetbrains.cidr.lang.OCBundle;
import com.intellij.util.ui.JBUI;
import com.intellij.util.ui.GridBag;
import java.awt.LayoutManager;
import javax.swing.JPanel;
import java.awt.GridBagLayout;
import javax.swing.JComponent;
import com.intellij.openapi.ui.ComboBox;
import org.jetbrains.annotations.NotNull;
import com.intellij.openapi.ui.NamedConfigurable;

private class MyFileExtensionPairConfigurable extends NamedConfigurable
{
    @NotNull
    private OCCodeStyleSettings.FileExtensionPair myFileExtensionPair;
    private ComboBox<String> mySourceExtField;
    private ComboBox<String> myHeaderExtField;
    
    public MyFileExtensionPairConfigurable() {
        this.myFileExtensionPair = new OCCodeStyleSettings.FileExtensionPair("cpp", "h");
    }
    
    public MyFileExtensionPairConfigurable(final OCCodeStyleSettings.FileExtensionPair myFileExtensionPair) {
        if (myFileExtensionPair == null) {
            throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "fileExtensionPair", "com/jetbrains/cidr/lang/settings/OCFileExtensionsSettingsProvider$MyMasterDetailsComponent$MyFileExtensionPairConfigurable", "<init>"));
        }
        this.myFileExtensionPair = myFileExtensionPair;
    }
    
    public void setDisplayName(final String s) {
    }
    
    public OCCodeStyleSettings.FileExtensionPair getEditableObject() {
        return this.myFileExtensionPair;
    }
    
    public String getBannerSlogan() {
        return null;
    }
    
    public JComponent createOptionsPanel() {
        final JPanel panel = new JPanel(new GridBagLayout());
        final GridBag setDefaultInsets = new GridBag().setDefaultWeightX(1, 1.0).setDefaultAnchor(1, 23).setDefaultInsets(0, 0, 4, 10);
        final JBInsets insets = JBUI.insets(10, 0, 4, 10);
        panel.add((Component)new JBLabel(OCBundle.message("fileExtensions.sourceExtension", new Object[0])), setDefaultInsets.nextLine().next().insets((Insets)insets));
        List list = ContainerUtil.sorted((Collection)OCFileTypeHelpers.SOURCE_FILE_EXTENSIONS);
        if (!PlatformUtils.isAppCode()) {
            list = ContainerUtil.filter((Collection)list, s -> {
                Label_0025: {
                    try {
                        if ("m".equals(s)) {
                            return false;
                        }
                        final String s2 = "mm";
                        final String s3 = s;
                        final boolean b = s2.equals(s3);
                        if (!b) {
                            break Label_0025;
                        }
                        return false;
                    }
                    catch (IllegalArgumentException ex) {
                        throw a(ex);
                    }
                    try {
                        final String s2 = "mm";
                        final String s3 = s;
                        final boolean b = s2.equals(s3);
                        if (!b) {
                            return true;
                        }
                    }
                    catch (IllegalArgumentException ex2) {
                        throw a(ex2);
                    }
                }
                return false;
            });
        }
        panel.add((Component)(this.mySourceExtField = (ComboBox<String>)new ComboBox((Object[])ArrayUtil.toStringArray((Collection)list))), setDefaultInsets.next().insets((Insets)insets));
        this.mySourceExtField.addItemListener((ItemListener)new ItemListener() {
            @Override
            public void itemStateChanged(final ItemEvent itemEvent) {
                MyMasterDetailsComponent.access$600(MyMasterDetailsComponent.this).run();
            }
        });
        panel.add((Component)new JBLabel(OCBundle.message("fileExtensions.headerExtension", new Object[0])), setDefaultInsets.nextLine().next());
        panel.add((Component)(this.myHeaderExtField = (ComboBox<String>)new ComboBox((Object[])ArrayUtil.toStringArray((Collection)ContainerUtil.sorted((Collection)ContainerUtil.filter((Collection)OCFileTypeHelpers.HEADER_FILE_EXTENSIONS, s -> {
            Label_0023: {
                try {
                    if (s.isEmpty()) {
                        return false;
                    }
                    final String s2 = "pch";
                    final String s3 = s;
                    final boolean b = s2.equals(s3);
                    if (!b) {
                        break Label_0023;
                    }
                    return false;
                }
                catch (IllegalArgumentException ex) {
                    throw a(ex);
                }
                try {
                    final String s2 = "pch";
                    final String s3 = s;
                    final boolean b = s2.equals(s3);
                    if (!b) {
                        return true;
                    }
                }
                catch (IllegalArgumentException ex2) {
                    throw a(ex2);
                }
            }
            return false;
        }))))), setDefaultInsets.next());
        this.myHeaderExtField.addItemListener((ItemListener)new ItemListener() {
            @Override
            public void itemStateChanged(final ItemEvent itemEvent) {
                MyMasterDetailsComponent.access$700(MyMasterDetailsComponent.this).run();
            }
        });
        final JPanel panel2 = new JPanel(new BorderLayout());
        panel2.add(panel, "North");
        return panel2;
    }
    
    public String getDisplayName() {
        String mySourceExt = null;
        Label_0031: {
            try {
                if (this.mySourceExtField == null) {
                    mySourceExt = this.myFileExtensionPair.mySourceExt;
                    break Label_0031;
                }
            }
            catch (IllegalArgumentException ex) {
                throw a(ex);
            }
            mySourceExt = (String)this.mySourceExtField.getSelectedItem();
        }
        final String s = mySourceExt;
        try {
            if (this.myHeaderExtField == null) {
                final String myHeaderExt = this.myFileExtensionPair.myHeaderExt;
                return new OCCodeStyleSettings.FileExtensionPair(s, myHeaderExt).toString();
            }
        }
        catch (IllegalArgumentException ex2) {
            throw a(ex2);
        }
        final String myHeaderExt = (String)this.myHeaderExtField.getSelectedItem();
        return new OCCodeStyleSettings.FileExtensionPair(s, myHeaderExt).toString();
    }
    
    @Nullable
    public String getHelpTopic() {
        return null;
    }
    
    public boolean isModified() {
        Label_0053: {
            try {
                if (!StringUtil.equals((CharSequence)this.mySourceExtField.getSelectedItem(), (CharSequence)this.myFileExtensionPair.mySourceExt)) {
                    break Label_0053;
                }
                final MyFileExtensionPairConfigurable myFileExtensionPairConfigurable = this;
                final ComboBox<String> comboBox = myFileExtensionPairConfigurable.myHeaderExtField;
                final Object o = comboBox.getSelectedItem();
                final CharSequence charSequence = (CharSequence)o;
                final MyFileExtensionPairConfigurable myFileExtensionPairConfigurable2 = this;
                final OCCodeStyleSettings.FileExtensionPair fileExtensionPair = myFileExtensionPairConfigurable2.myFileExtensionPair;
                final String s = fileExtensionPair.myHeaderExt;
                final boolean b = StringUtil.equals(charSequence, (CharSequence)s);
                if (!b) {
                    break Label_0053;
                }
                return false;
            }
            catch (IllegalArgumentException ex) {
                throw a(ex);
            }
            try {
                final MyFileExtensionPairConfigurable myFileExtensionPairConfigurable = this;
                final ComboBox<String> comboBox = myFileExtensionPairConfigurable.myHeaderExtField;
                final Object o = comboBox.getSelectedItem();
                final CharSequence charSequence = (CharSequence)o;
                final MyFileExtensionPairConfigurable myFileExtensionPairConfigurable2 = this;
                final OCCodeStyleSettings.FileExtensionPair fileExtensionPair = myFileExtensionPairConfigurable2.myFileExtensionPair;
                final String s = fileExtensionPair.myHeaderExt;
                final boolean b = StringUtil.equals(charSequence, (CharSequence)s);
                if (!b) {
                    return true;
                }
            }
            catch (IllegalArgumentException ex2) {
                throw a(ex2);
            }
        }
        return false;
    }
    
    public void apply() throws ConfigurationException {
        this.myFileExtensionPair = new OCCodeStyleSettings.FileExtensionPair((String)this.mySourceExtField.getSelectedItem(), (String)this.myHeaderExtField.getSelectedItem());
    }
    
    public void reset() {
        this.mySourceExtField.setSelectedItem((Object)this.myFileExtensionPair.mySourceExt);
        this.myHeaderExtField.setSelectedItem((Object)this.myFileExtensionPair.myHeaderExt);
    }
    
    public void disposeUIResources() {
    }
    
    private static IllegalArgumentException a(final IllegalArgumentException ex) {
        return ex;
    }
}
