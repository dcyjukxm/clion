// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.cpp.toolchains;

import org.jdom.Element;
import java.util.Collection;
import com.intellij.util.containers.ContainerUtil;
import java.util.ArrayList;
import com.intellij.openapi.util.text.StringUtil;
import kotlin.Triple;
import com.intellij.openapi.util.Comparing;
import com.jetbrains.cidr.toolchains.CidrToolSet;
import javax.swing.ComboBoxEditor;
import com.intellij.openapi.ui.FixedComboBoxEditor;
import javax.swing.ComboBoxModel;
import com.intellij.ui.CollectionComboBoxModel;
import java.util.Collections;
import java.util.Arrays;
import java.awt.Component;
import java.awt.Label;
import com.jetbrains.cidr.cpp.CPPBundle;
import java.awt.LayoutManager;
import java.awt.GridBagLayout;
import org.jetbrains.annotations.NotNull;
import com.intellij.util.ui.GridBag;
import javax.swing.JPanel;
import java.util.List;
import com.intellij.openapi.ui.ComboBox;

static final class MSVC$1 implements OptionsConfigurable {
    private ComboBox<String> myArchComboBox;
    private ComboBox<String> myPlatformField;
    private ComboBox<String> myVersionField;
    final /* synthetic */ List val$archs;
    
    @Override
    public void createComponents(final JPanel panel, @NotNull final GridBag gridBag) {
        try {
            if (gridBag == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "gridBag", "com/jetbrains/cidr/cpp/toolchains/MSVC$1", "createComponents"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        final JPanel panel2 = new JPanel(new GridBagLayout());
        panel2.add(new Label(CPPBundle.message("msvc.option.arch", new Object[0])));
        panel.add(panel2, gridBag.next());
        final GridBag setDefaultAnchor = new GridBag().setDefaultInsets(0, 0, 0, 10).setDefaultAnchor(21);
        final JPanel panel3 = new JPanel(new GridBagLayout());
        panel.add(panel3, gridBag.next().coverLine().fillCell());
        panel3.add((Component)(this.myArchComboBox = this.a(this.val$archs, "x86")), setDefaultAnchor.next());
        panel3.add(new Label(CPPBundle.message("msvc.option.platform", new Object[0])), setDefaultAnchor.next());
        panel3.add((Component)(this.myPlatformField = this.a(Arrays.asList("store", "uwp", "onecore"), "")), setDefaultAnchor.next());
        panel3.add(new Label(CPPBundle.message("msvc.option.version", new Object[0])), setDefaultAnchor.next());
        final String toolTipText = "8.1 or Windows 10 SDK version";
        (this.myVersionField = this.a(Collections.singletonList("8.1"), toolTipText)).setToolTipText(toolTipText);
        panel3.add((Component)this.myVersionField, setDefaultAnchor.next().fillCell().coverLine().weightx(1.0).insetRight(0));
    }
    
    private ComboBox<String> a(final List<String> list, final String text) {
        final ComboBox comboBox = new ComboBox((ComboBoxModel)new CollectionComboBoxModel((List)list));
        final FixedComboBoxEditor editor = new FixedComboBoxEditor();
        comboBox.setEditor((ComboBoxEditor)editor);
        editor.getField().getEmptyText().setText(text);
        comboBox.setEditable(true);
        return (ComboBox<String>)comboBox;
    }
    
    @Override
    public boolean isModified(@NotNull final List<Option> list) {
        try {
            if (list == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "currentOptions", "com/jetbrains/cidr/cpp/toolchains/MSVC$1", "isModified"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        try {
            if (!Comparing.equal((Object)MSVC.access$000(list), (Object)this.getFieldValues())) {
                return true;
            }
        }
        catch (IllegalArgumentException ex2) {
            throw a(ex2);
        }
        return false;
    }
    
    @NotNull
    public Triple<Arch, Platform, Version> getFieldValues() {
        final String trim = StringUtil.trim(StringUtil.nullize((String)this.myArchComboBox.getSelectedItem(), true));
        final String trim2 = StringUtil.trim(StringUtil.nullize((String)this.myPlatformField.getSelectedItem(), true));
        final String trim3 = StringUtil.trim(StringUtil.nullize((String)this.myVersionField.getSelectedItem(), true));
        Arch arch = null;
        Label_0078: {
            try {
                if (trim == null) {
                    arch = null;
                    break Label_0078;
                }
            }
            catch (IllegalArgumentException ex) {
                throw a(ex);
            }
            arch = new Arch(trim);
        }
        final Triple triple = new Triple((Object)arch, (Object)((trim2 == null) ? null : new Platform(trim2)), (Object)((trim3 == null) ? null : new Version(trim3)));
        if (triple == null) {
            throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/cpp/toolchains/MSVC$1", "getFieldValues"));
        }
        return (Triple<Arch, Platform, Version>)triple;
    }
    
    @NotNull
    @Override
    public List<Option> apply() {
        final Triple<Arch, Platform, Version> fieldValues = this.getFieldValues();
        final ArrayList list = new ArrayList<Option>(3);
        ArrayList list2;
        try {
            ContainerUtil.addIfNotNull((Collection)list, fieldValues.component1());
            ContainerUtil.addIfNotNull((Collection)list, fieldValues.component2());
            ContainerUtil.addIfNotNull((Collection)list, fieldValues.component3());
            list2 = list;
            if (list2 == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/cpp/toolchains/MSVC$1", "apply"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        return (List<Option>)list2;
    }
    
    @Override
    public void reset(@NotNull final List<Option> list) {
        try {
            if (list == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "currentOptions", "com/jetbrains/cidr/cpp/toolchains/MSVC$1", "reset"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        final Triple access$000 = MSVC.access$000(list);
        ComboBox<String> myVersionField = null;
        Object value3 = null;
        Label_0142: {
            Label_0110: {
                ComboBox<String> myPlatformField = null;
                Label_0078: {
                    ComboBox<String> myArchComboBox;
                    try {
                        myArchComboBox = this.myArchComboBox;
                        if (access$000.component1() == null) {
                            final Object value = null;
                            break Label_0078;
                        }
                    }
                    catch (IllegalArgumentException ex2) {
                        throw a(ex2);
                    }
                    final Object value = ((Arch)access$000.component1()).getValue();
                    try {
                        myArchComboBox.setSelectedItem(value);
                        myPlatformField = this.myPlatformField;
                        if (access$000.component2() == null) {
                            final Object value2 = null;
                            break Label_0110;
                        }
                    }
                    catch (IllegalArgumentException ex3) {
                        throw a(ex3);
                    }
                }
                final Object value2 = ((Platform)access$000.component2()).getValue();
                try {
                    myPlatformField.setSelectedItem(value2);
                    myVersionField = this.myVersionField;
                    if (access$000.component3() == null) {
                        value3 = null;
                        break Label_0142;
                    }
                }
                catch (IllegalArgumentException ex4) {
                    throw a(ex4);
                }
            }
            value3 = ((Version)access$000.component3()).getValue();
        }
        myVersionField.setSelectedItem(value3);
    }
    
    private static IllegalArgumentException a(final IllegalArgumentException ex) {
        return ex;
    }
}