// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.lang.settings;

import com.intellij.openapi.project.Project;
import com.intellij.codeInsight.daemon.impl.DaemonCodeAnalyzerEx;
import com.intellij.openapi.project.ProjectManager;
import org.jetbrains.annotations.Nls;
import com.intellij.openapi.application.ApplicationManager;
import com.intellij.openapi.options.ConfigurationException;
import org.jetbrains.annotations.Nullable;
import java.awt.Component;
import java.awt.Insets;
import java.awt.GridBagConstraints;
import com.intellij.util.ui.JBUI;
import java.awt.LayoutManager;
import javax.swing.JPanel;
import java.awt.GridBagLayout;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.event.ActionListener;
import com.intellij.openapi.ui.TextFieldWithBrowseButton;
import javax.swing.JTextField;
import com.intellij.ide.util.BrowseFilesListener;
import com.intellij.openapi.fileChooser.FileChooserDescriptorFactory;
import javax.swing.JComponent;
import org.jetbrains.annotations.NotNull;
import com.intellij.ui.components.JBTextField;
import com.intellij.ui.components.JBCheckBox;
import com.jetbrains.cidr.lang.daemon.clang.tidy.ClangTidySettings;
import com.intellij.openapi.options.SearchableConfigurable;

public class CPPLanguageConfigurable implements SearchableConfigurable
{
    private final ClangTidySettings mySettings;
    private JBCheckBox myCheckBox;
    private JBTextField myTextField;
    
    public CPPLanguageConfigurable() {
        this.mySettings = ClangTidySettings.getInstance();
    }
    
    @NotNull
    public String getId() {
        String s;
        try {
            s = "language.cpp";
            if (s == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/settings/CPPLanguageConfigurable", "getId"));
            }
        }
        catch (IllegalStateException ex) {
            throw a(ex);
        }
        return s;
    }
    
    @Nullable
    public JComponent createComponent() {
        this.myCheckBox = new JBCheckBox("Use external clang-tidy instead of the built-in one:", this.mySettings.getUseExternalClangTidy());
        this.myTextField = new JBTextField(this.mySettings.getExternalClangTidyPath(), 30);
        final TextFieldWithBrowseButton textFieldWithBrowseButton = new TextFieldWithBrowseButton((JTextField)this.myTextField, (ActionListener)new BrowseFilesListener((JTextField)this.myTextField, "Clang-Tidy Path", "", FileChooserDescriptorFactory.createSingleFileOrExecutableAppDescriptor()));
        textFieldWithBrowseButton.setEnabled(this.myCheckBox.isSelected());
        this.myCheckBox.addChangeListener((ChangeListener)new ChangeListener() {
            @Override
            public void stateChanged(final ChangeEvent changeEvent) {
                textFieldWithBrowseButton.setEnabled(CPPLanguageConfigurable.this.myCheckBox.isSelected());
            }
        });
        final JPanel panel = new JPanel(new GridBagLayout());
        panel.add((Component)this.myCheckBox, new GridBagConstraints(0, 0, 1, 1, 0.0, 0.0, 18, 2, (Insets)JBUI.emptyInsets(), 0, 0));
        panel.add((Component)textFieldWithBrowseButton, new GridBagConstraints(0, 1, 1, 1, 1.0, 1.0, 18, 2, (Insets)JBUI.emptyInsets(), 0, 0));
        return panel;
    }
    
    public boolean isModified() {
        Label_0044: {
            try {
                if (this.myCheckBox.isSelected() != this.mySettings.getUseExternalClangTidy()) {
                    break Label_0044;
                }
                final CPPLanguageConfigurable cppLanguageConfigurable = this;
                final JBTextField jbTextField = cppLanguageConfigurable.myTextField;
                final String s = jbTextField.getText();
                final CPPLanguageConfigurable cppLanguageConfigurable2 = this;
                final ClangTidySettings clangTidySettings = cppLanguageConfigurable2.mySettings;
                final String s2 = clangTidySettings.getExternalClangTidyPath();
                final boolean b = s.equals(s2);
                if (!b) {
                    break Label_0044;
                }
                return false;
            }
            catch (IllegalStateException ex) {
                throw a(ex);
            }
            try {
                final CPPLanguageConfigurable cppLanguageConfigurable = this;
                final JBTextField jbTextField = cppLanguageConfigurable.myTextField;
                final String s = jbTextField.getText();
                final CPPLanguageConfigurable cppLanguageConfigurable2 = this;
                final ClangTidySettings clangTidySettings = cppLanguageConfigurable2.mySettings;
                final String s2 = clangTidySettings.getExternalClangTidyPath();
                final boolean b = s.equals(s2);
                if (!b) {
                    return true;
                }
            }
            catch (IllegalStateException ex2) {
                throw a(ex2);
            }
        }
        return false;
    }
    
    public void apply() throws ConfigurationException {
        this.b();
        c();
    }
    
    public void reset() {
        ApplicationManager.getApplication().runReadAction(() -> {
            this.myCheckBox.setSelected(this.mySettings.getUseExternalClangTidy());
            this.myTextField.setText(this.mySettings.getExternalClangTidyPath());
        });
    }
    
    @Nls
    public String getDisplayName() {
        return "C/C++";
    }
    
    private void b() {
        ApplicationManager.getApplication().runWriteAction(() -> {
            this.mySettings.setUseExternalClangTidy(this.myCheckBox.isSelected());
            this.mySettings.setExternalClangTidyPath(this.myTextField.getText());
        });
    }
    
    private static void c() {
        for (final Project project : ProjectManager.getInstance().getOpenProjects()) {
            Label_0051: {
                try {
                    if (!project.isInitialized()) {
                        break Label_0051;
                    }
                    final Project project2 = project;
                    final boolean b = project2.isDisposed();
                    if (!b) {
                        break Label_0051;
                    }
                    break Label_0051;
                }
                catch (IllegalStateException ex) {
                    throw a(ex);
                }
                try {
                    final Project project2 = project;
                    final boolean b = project2.isDisposed();
                    if (!b) {
                        DaemonCodeAnalyzerEx.getInstanceEx(project).restart();
                    }
                }
                catch (IllegalStateException ex2) {
                    throw a(ex2);
                }
            }
        }
    }
    
    private static IllegalStateException a(final IllegalStateException ex) {
        return ex;
    }
}
