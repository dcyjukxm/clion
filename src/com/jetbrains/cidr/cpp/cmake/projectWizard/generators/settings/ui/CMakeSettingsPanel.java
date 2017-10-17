// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.cpp.cmake.projectWizard.generators.settings.ui;

import org.jetbrains.annotations.Nullable;
import com.intellij.uiDesigner.core.GridConstraints;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.Component;
import javax.swing.JLabel;
import com.intellij.uiDesigner.core.GridLayoutManager;
import java.awt.LayoutManager;
import java.awt.BorderLayout;
import org.jetbrains.annotations.NotNull;
import com.jetbrains.cidr.cpp.cmake.projectWizard.generators.CMakeProjectGenerator;
import com.intellij.openapi.ui.ComboBox;
import javax.swing.JPanel;

public class CMakeSettingsPanel extends JPanel
{
    private ComboBox<String> myLanguageVersionComboBox;
    private ComboBox<String> myLibraryTypeComboBox;
    
    public CMakeSettingsPanel(@NotNull final CMakeProjectGenerator cMakeProjectGenerator) {
        if (cMakeProjectGenerator == null) {
            throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "cMakeProjectGenerator", "com/jetbrains/cidr/cpp/cmake/projectWizard/generators/settings/ui/CMakeSettingsPanel", "<init>"));
        }
        this.init(cMakeProjectGenerator);
    }
    
    public void init(@NotNull final CMakeProjectGenerator cMakeProjectGenerator) {
        try {
            if (cMakeProjectGenerator == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "cMakeProjectGenerator", "com/jetbrains/cidr/cpp/cmake/projectWizard/generators/settings/ui/CMakeSettingsPanel", "init"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        this.setLayout(new BorderLayout());
        final boolean addLibrarySettingsPanel = cMakeProjectGenerator.addLibrarySettingsPanel();
        final JPanel panel = new JPanel((LayoutManager)new GridLayoutManager(a(addLibrarySettingsPanel), 2));
        final GridConstraints a = a(0, 0);
        final JLabel label = new JLabel("Language standard:");
        panel.add(label, a);
        this.myLanguageVersionComboBox = (ComboBox<String>)new ComboBox((Object[])cMakeProjectGenerator.getLanguageVersions());
        final String languageVersion = cMakeProjectGenerator.getLanguageVersion();
        try {
            if (languageVersion != null) {
                this.myLanguageVersionComboBox.setSelectedItem((Object)languageVersion);
            }
        }
        catch (IllegalArgumentException ex2) {
            throw a(ex2);
        }
        this.myLanguageVersionComboBox.addItemListener((ItemListener)new ItemListener() {
            @Override
            public void itemStateChanged(final ItemEvent itemEvent) {
                if (itemEvent.getStateChange() == 1) {
                    cMakeProjectGenerator.setLanguageVersion((String)itemEvent.getItem());
                }
            }
        });
        panel.add((Component)this.myLanguageVersionComboBox, a(0, 1));
        label.setDisplayedMnemonic('s');
        label.setLabelFor((Component)this.myLanguageVersionComboBox);
        if (addLibrarySettingsPanel) {
            final GridConstraints a2 = a(1, 0);
            final JLabel label2 = new JLabel("Library type:");
            panel.add(label2, a2);
            this.myLibraryTypeComboBox = (ComboBox<String>)new ComboBox((Object[])new String[] { "static", "shared" });
            final String libraryType = cMakeProjectGenerator.getLibraryType();
            try {
                if (libraryType != null) {
                    this.myLibraryTypeComboBox.setSelectedItem((Object)libraryType);
                }
            }
            catch (IllegalArgumentException ex3) {
                throw a(ex3);
            }
            this.myLibraryTypeComboBox.addItemListener((ItemListener)new ItemListener() {
                @Override
                public void itemStateChanged(final ItemEvent itemEvent) {
                    if (itemEvent.getStateChange() == 1) {
                        cMakeProjectGenerator.setLibraryType((String)itemEvent.getItem());
                    }
                }
            });
            panel.add((Component)this.myLibraryTypeComboBox, a(1, 1));
            label2.setDisplayedMnemonic('i');
            label2.setLabelFor((Component)this.myLibraryTypeComboBox);
        }
        this.add(panel, "West");
    }
    
    public void initFields(@Nullable final String selectedItem, @Nullable final String selectedItem2) {
        try {
            if (selectedItem != null) {
                this.myLanguageVersionComboBox.setSelectedItem((Object)selectedItem);
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        try {
            if (this.myLibraryTypeComboBox != null) {
                this.myLibraryTypeComboBox.setSelectedItem((Object)selectedItem2);
            }
        }
        catch (IllegalArgumentException ex2) {
            throw a(ex2);
        }
    }
    
    @NotNull
    private static GridConstraints a(final int row, final int column) {
        final GridConstraints gridConstraints = new GridConstraints();
        GridConstraints gridConstraints2;
        try {
            gridConstraints.setRow(row);
            gridConstraints.setColumn(column);
            gridConstraints.setAnchor(8);
            gridConstraints2 = gridConstraints;
            if (gridConstraints2 == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/cpp/cmake/projectWizard/generators/settings/ui/CMakeSettingsPanel", "createConstraints"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        return gridConstraints2;
    }
    
    private static int a(final boolean b) {
        int n = 1;
        try {
            if (b) {
                ++n;
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        return n;
    }
    
    private static IllegalArgumentException a(final IllegalArgumentException ex) {
        return ex;
    }
}
