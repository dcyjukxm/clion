// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.cpp.cmake.settings;

import java.awt.event.ItemEvent;
import com.intellij.openapi.Disposable;
import com.intellij.openapi.util.Disposer;
import com.jetbrains.cidr.cpp.cmake.model.CMakeModel;
import com.jetbrains.cidr.cpp.cmake.model.CMakeModelConfigurationData;
import com.jetbrains.cidr.cpp.CPPToolchainsConfigurable;
import java.util.function.Function;
import javax.swing.ComboBoxModel;
import com.intellij.ui.CollectionComboBoxModel;
import com.intellij.util.containers.ContainerUtil;
import java.util.Map;
import java.util.Collections;
import com.jetbrains.cidr.toolchains.CidrToolSet;
import com.jetbrains.cidr.cpp.cmake.CMakeSettings;
import com.intellij.openapi.util.text.StringUtil;
import com.jetbrains.cidr.cpp.cmake.CMakeSettingsKt;
import com.intellij.ui.components.JBTextField;
import com.intellij.openapi.util.SystemInfo;
import com.jetbrains.cidr.cpp.CPPToolchains;
import com.jetbrains.cidr.cpp.cmake.model.CMakeGenerator;
import com.intellij.util.ui.JBInsets;
import com.intellij.util.ui.GridBag;
import com.intellij.openapi.util.io.FileUtil;
import com.intellij.openapi.vfs.VfsUtilCore;
import org.jetbrains.annotations.Nullable;
import java.io.File;
import com.intellij.openapi.vfs.VfsUtil;
import com.intellij.openapi.vfs.VirtualFile;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.ui.TextBrowseFolderListener;
import com.intellij.openapi.fileChooser.FileChooserDescriptor;
import java.util.Arrays;
import java.awt.Insets;
import com.intellij.util.ui.UIUtil;
import com.jetbrains.cidr.cpp.CPPBundle;
import com.intellij.util.ui.JBUI;
import java.awt.LayoutManager;
import java.awt.GridBagLayout;
import com.intellij.openapi.ui.TextFieldWithBrowseButton;
import com.intellij.execution.configuration.EnvironmentVariablesTextFieldWithBrowseButton;
import java.awt.Component;
import java.util.List;
import com.jetbrains.cidr.cpp.toolchains.MSVC;
import com.intellij.ui.components.JBLabel;
import com.intellij.ui.RawCommandLineEditor;
import com.intellij.openapi.ui.ComboBox;
import org.jetbrains.annotations.NotNull;
import javax.swing.JPanel;

private class ConfigurationEditor extends JPanel
{
    @NotNull
    private final ConfigurationListEditor myParentEditor;
    private ComboBox<String> myConfigNameComboBox;
    private RawCommandLineEditor myCMakeOptions;
    private JBLabel myCMakeOptionsHint;
    private MSVC.OptionsConfigurable myToolSetConfigurable;
    private List<Component> myMyToolSetComponents;
    private EnvironmentVariablesTextFieldWithBrowseButton myEnvironmentField;
    private TextFieldWithBrowseButton myGenerationDirField;
    
    public ConfigurationEditor(final ConfigurationListEditor myParentEditor) {
        if (myParentEditor == null) {
            throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "parentEditor", "com/jetbrains/cidr/cpp/cmake/settings/CMakeConfigurable$ConfigurationEditor", "<init>"));
        }
        super(new GridBagLayout());
        this.myParentEditor = myParentEditor;
        final GridBag bagConstraints = CMakeConfigurable.createBagConstraints(true);
        final JBInsets insets = JBUI.insets(0, -1, -1, -1);
        this.add((Component)new JBLabel(CPPBundle.message("cmake.settings.configuration", new Object[0])), bagConstraints.nextLine().next());
        this.add((Component)(this.myConfigNameComboBox = (ComboBox<String>)new ComboBox()), bagConstraints.next().fillCellNone());
        this.myConfigNameComboBox.setEditable(true);
        this.add((Component)new JBLabel(CPPBundle.message("cmake.settings.generationOptions", new Object[0])), bagConstraints.nextLine().next());
        this.add((Component)(this.myCMakeOptions = CMakeConfigurable.access$1100(CPPBundle.message("cmake.settings.generationOptions.dialog", new Object[0]))), bagConstraints.next().coverLine());
        this.add((Component)(this.myCMakeOptionsHint = new JBLabel("", UIUtil.ComponentStyle.SMALL, UIUtil.FontColor.BRIGHTER)), bagConstraints.nextLine().next().next().coverLine().insets((Insets)insets));
        this.myToolSetConfigurable = MSVC.createOptionsConfigurable();
        if (this.myToolSetConfigurable != null) {
            final int componentCount = this.getComponentCount();
            this.myToolSetConfigurable.createComponents(this, bagConstraints.nextLine());
            this.myMyToolSetComponents = Arrays.asList(this.getComponents()).subList(componentCount, this.getComponentCount());
        }
        this.add((Component)new JBLabel(CPPBundle.message("cmake.settings.environment", new Object[0])), bagConstraints.nextLine().next());
        this.add((Component)(this.myEnvironmentField = new EnvironmentVariablesTextFieldWithBrowseButton()), bagConstraints.next().coverLine());
        this.add((Component)new JBLabel(CPPBundle.message("cmake.settings.generationDir", new Object[0])), bagConstraints.nextLine().next());
        this.add((Component)(this.myGenerationDirField = new TextFieldWithBrowseButton()), bagConstraints.next().coverLine().insetTop(12));
        this.add((Component)new JBLabel(CPPBundle.message("cmake.settings.generationDir.specified.hint", new Object[0]), UIUtil.ComponentStyle.SMALL, UIUtil.FontColor.BRIGHTER), bagConstraints.nextLine().next().next().coverLine().insets((Insets)insets));
        this.myConfigNameComboBox.addItemListener(itemEvent -> {
            try {
                if (itemEvent.getStateChange() == 1) {
                    this.a();
                }
            }
            catch (IllegalArgumentException ex) {
                throw a(ex);
            }
            return;
        });
        this.myGenerationDirField.addBrowseFolderListener((TextBrowseFolderListener)new TextBrowseFolderListener(new FileChooserDescriptor(false, true, false, false, false, false), CMakeConfigurable.access$1200(CMakeConfigurable.this)) {
            @Nullable
            protected VirtualFile getInitialFile() {
                File file = ConfigurationEditor.this.myParentEditor.getEffectiveGenerationDir(ConfigurationEditor.this);
                while (true) {
                    try {
                        if (file == null || file.exists()) {
                            break;
                        }
                    }
                    catch (IllegalArgumentException ex) {
                        throw a(ex);
                    }
                    file = file.getParentFile();
                }
                try {
                    if (file == null) {
                        return CMakeConfigurable.access$700(CMakeConfigurable.this).getEffectiveContentRoot();
                    }
                }
                catch (IllegalArgumentException ex2) {
                    throw a(ex2);
                }
                return VfsUtil.findFileByIoFile(file, false);
            }
            
            @NotNull
            protected String chosenFileToResultingText(@NotNull final VirtualFile virtualFile) {
                try {
                    if (virtualFile == null) {
                        throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "chosenFile", "com/jetbrains/cidr/cpp/cmake/settings/CMakeConfigurable$ConfigurationEditor$1", "chosenFileToResultingText"));
                    }
                }
                catch (IllegalArgumentException ex) {
                    throw a(ex);
                }
                final File virtualToIoFile = VfsUtilCore.virtualToIoFile(virtualFile);
                final File projectSubDir = CMakeConfigurable.access$700(CMakeConfigurable.this).getProjectSubDir(new File(""));
                String chosenFileToResultingText = null;
                Label_0141: {
                    if (FileUtil.isAncestor(projectSubDir, virtualToIoFile, true)) {
                        final String relativePath = FileUtil.getRelativePath(projectSubDir, virtualToIoFile);
                        String s = null;
                        Label_0106: {
                            try {
                                if (relativePath == null) {
                                    break Label_0141;
                                }
                                s = relativePath;
                                if (s == null) {
                                    break Label_0106;
                                }
                                return s;
                            }
                            catch (IllegalArgumentException ex2) {
                                throw a(ex2);
                            }
                            try {
                                s = relativePath;
                                if (s == null) {
                                    throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/cpp/cmake/settings/CMakeConfigurable$ConfigurationEditor$1", "chosenFileToResultingText"));
                                }
                            }
                            catch (IllegalArgumentException ex3) {
                                throw a(ex3);
                            }
                        }
                        return s;
                    }
                    try {
                        chosenFileToResultingText = super.chosenFileToResultingText(virtualFile);
                        if (chosenFileToResultingText == null) {
                            throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/cpp/cmake/settings/CMakeConfigurable$ConfigurationEditor$1", "chosenFileToResultingText"));
                        }
                    }
                    catch (IllegalArgumentException ex4) {
                        throw a(ex4);
                    }
                }
                return chosenFileToResultingText;
            }
            
            private static IllegalArgumentException a(final IllegalArgumentException ex) {
                return ex;
            }
        });
    }
    
    private void a() {
        this.myParentEditor.configNameChanged(this);
        this.b();
    }
    
    private void b() {
        this.myCMakeOptionsHint.setText(CPPBundle.message("cmake.settings.generationOptions.hint", CMakeGenerator.getBuildTypeOption(this.d())));
    }
    
    public void updateEnvironment(@Nullable final CPPToolchains.WinEnvironment winEnvironment) {
        CPPToolchains.WinEnvironment winEnvironment2;
        final CPPToolchains.WinEnvironment winEnvironment3;
        final boolean visible;
        this.myMyToolSetComponents.forEach(component -> {
            Label_0021_1: {
                try {
                    if (SystemInfo.isWindows) {
                        winEnvironment2 = CPPToolchains.WinEnvironment.MSVC;
                        if (winEnvironment3 == winEnvironment2) {
                            break Label_0021_1;
                        }
                        else {
                            break Label_0021_1;
                        }
                    }
                    else {
                        break Label_0021_1;
                    }
                }
                catch (IllegalArgumentException ex) {
                    throw a(ex);
                }
                try {
                    winEnvironment2 = CPPToolchains.WinEnvironment.MSVC;
                    if (winEnvironment3 == winEnvironment2) {
                        break Label_0021_1;
                    }
                }
                catch (IllegalArgumentException ex2) {
                    throw a(ex2);
                }
            }
            component.setVisible(visible);
        });
    }
    
    private void a(@NotNull final String text) {
        try {
            if (text == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "dirName", "com/jetbrains/cidr/cpp/cmake/settings/CMakeConfigurable$ConfigurationEditor", "updateGenerationDirHint"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        ((JBTextField)this.myGenerationDirField.getTextField()).getEmptyText().setText(text);
    }
    
    @NotNull
    private String d() {
        String normalizeConfigName;
        try {
            normalizeConfigName = CMakeSettingsKt.normalizeConfigName(String.valueOf(this.myConfigNameComboBox.getSelectedItem()));
            if (normalizeConfigName == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/cpp/cmake/settings/CMakeConfigurable$ConfigurationEditor", "getConfigName"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        return normalizeConfigName;
    }
    
    private File c() {
        try {
            if (StringUtil.isEmptyOrSpaces(this.myGenerationDirField.getText())) {
                return null;
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        return new File(this.myGenerationDirField.getText());
    }
    
    public boolean isModified(@NotNull final CMakeSettings.Configuration configuration) {
        try {
            if (configuration == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "config", "com/jetbrains/cidr/cpp/cmake/settings/CMakeConfigurable$ConfigurationEditor", "isModified"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        try {
            if (!StringUtil.equals((CharSequence)configuration.getConfigName(), (CharSequence)this.d())) {
                return true;
            }
        }
        catch (IllegalArgumentException ex2) {
            throw a(ex2);
        }
        try {
            if (!StringUtil.equals((CharSequence)configuration.getGenerationOptions(), (CharSequence)CMakeSettingsKt.normalizeOptions(this.myCMakeOptions.getText()))) {
                return true;
            }
        }
        catch (IllegalArgumentException ex3) {
            throw a(ex3);
        }
        Label_0126: {
            try {
                if (this.myToolSetConfigurable == null) {
                    break Label_0126;
                }
                final ConfigurationEditor configurationEditor = this;
                final MSVC.OptionsConfigurable optionsConfigurable = configurationEditor.myToolSetConfigurable;
                final CMakeSettings.Configuration configuration2 = configuration;
                final List<CidrToolSet.Option> list = configuration2.getToolSetOptions();
                final boolean b = optionsConfigurable.isModified(list);
                if (b) {
                    return true;
                }
                break Label_0126;
            }
            catch (IllegalArgumentException ex4) {
                throw a(ex4);
            }
            try {
                final ConfigurationEditor configurationEditor = this;
                final MSVC.OptionsConfigurable optionsConfigurable = configurationEditor.myToolSetConfigurable;
                final CMakeSettings.Configuration configuration2 = configuration;
                final List<CidrToolSet.Option> list = configuration2.getToolSetOptions();
                final boolean b = optionsConfigurable.isModified(list);
                if (b) {
                    return true;
                }
            }
            catch (IllegalArgumentException ex5) {
                throw a(ex5);
            }
            try {
                if (configuration.getGenerationPassSystemEnvironment() != this.myEnvironmentField.isPassParentEnvs()) {
                    return true;
                }
            }
            catch (IllegalArgumentException ex6) {
                throw a(ex6);
            }
        }
        try {
            if (!configuration.getAdditionalGenerationEnvironment().equals(CMakeSettingsKt.normalizeEnvironment(this.myEnvironmentField.getEnvs()))) {
                return true;
            }
        }
        catch (IllegalArgumentException ex7) {
            throw a(ex7);
        }
        File file = null;
        Label_0209: {
            try {
                if (StringUtil.isEmptyOrSpaces(this.myGenerationDirField.getText())) {
                    file = null;
                    break Label_0209;
                }
            }
            catch (IllegalArgumentException ex8) {
                throw a(ex8);
            }
            file = new File(this.myGenerationDirField.getText());
        }
        final File file2 = file;
        try {
            if (!FileUtil.filesEqual(configuration.getGenerationDir(), file2)) {
                return true;
            }
        }
        catch (IllegalArgumentException ex9) {
            throw a(ex9);
        }
        return false;
    }
    
    @NotNull
    public CMakeSettings.Configuration create() {
        String d = null;
        String text = null;
        boolean passParentEnvs = false;
        Map<String, String> normalizeEnvironment = null;
        Object o = null;
        Label_0058: {
            try {
                d = this.d();
                text = this.myCMakeOptions.getText();
                passParentEnvs = this.myEnvironmentField.isPassParentEnvs();
                normalizeEnvironment = CMakeSettingsKt.normalizeEnvironment(this.myEnvironmentField.getEnvs());
                if (this.myToolSetConfigurable == null) {
                    o = Collections.emptyList();
                    break Label_0058;
                }
            }
            catch (IllegalArgumentException ex) {
                throw a(ex);
            }
            o = this.myToolSetConfigurable.apply();
        }
        final CMakeSettings.Configuration configuration = new CMakeSettings.Configuration(d, text, passParentEnvs, normalizeEnvironment, (List<? extends CidrToolSet.Option>)o, this.c());
        if (configuration == null) {
            throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/cpp/cmake/settings/CMakeConfigurable$ConfigurationEditor", "create"));
        }
        return configuration;
    }
    
    public void reset(@NotNull final CMakeSettings.Configuration configuration) {
        try {
            if (configuration == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "config", "com/jetbrains/cidr/cpp/cmake/settings/CMakeConfigurable$ConfigurationEditor", "reset"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        final String configName = configuration.getConfigName();
        List<String> list = this.b(configName);
        if (!list.contains(configName)) {
            list = (List<String>)ContainerUtil.append((List)list, (Object[])new String[] { configName });
        }
        this.myConfigNameComboBox.setModel((ComboBoxModel)new CollectionComboBoxModel((List)list));
        final int intValue = list.stream().map((Function<? super Object, ? extends Integer>)String::length).max(Integer::compareTo).orElse(15);
        try {
            this.myConfigNameComboBox.setPrototypeDisplayValue((Object)StringUtil.repeat("A", intValue));
            this.myConfigNameComboBox.setSelectedItem((Object)configName);
            this.myCMakeOptions.setText(configuration.getGenerationOptions());
            if (this.myToolSetConfigurable != null) {
                this.myToolSetConfigurable.reset(configuration.getToolSetOptions());
            }
        }
        catch (IllegalArgumentException ex2) {
            throw a(ex2);
        }
        this.myEnvironmentField.setPassParentEnvs(configuration.getGenerationPassSystemEnvironment());
        this.myEnvironmentField.setEnvs(configuration.getAdditionalGenerationEnvironment());
        final File generationDir = configuration.getGenerationDir();
        TextFieldWithBrowseButton myGenerationDirField = null;
        String path = null;
        Label_0246: {
            try {
                myGenerationDirField = this.myGenerationDirField;
                if (generationDir == null) {
                    path = "";
                    break Label_0246;
                }
            }
            catch (IllegalArgumentException ex3) {
                throw a(ex3);
            }
            path = generationDir.getPath();
        }
        myGenerationDirField.setText(path);
        this.b();
        this.updateEnvironment(CPPToolchainsConfigurable.getSelectedEnvironment());
    }
    
    @NotNull
    private List<String> b(@NotNull final String s) {
        try {
            if (s == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "configurationName", "com/jetbrains/cidr/cpp/cmake/settings/CMakeConfigurable$ConfigurationEditor", "getRegisteredConfigurationsTypesFor"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        final List<CMakeModelConfigurationData> modelConfigurationData = CMakeConfigurable.access$700(CMakeConfigurable.this).getModelConfigurationData();
        CMakeModelConfigurationData cMakeModelConfigurationData = (CMakeModelConfigurationData)ContainerUtil.find((Iterable)modelConfigurationData, cMakeModelConfigurationData -> {
            try {
                if (s == null) {
                    throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "configurationName", "com/jetbrains/cidr/cpp/cmake/settings/CMakeConfigurable$ConfigurationEditor", "lambda$getRegisteredConfigurationsTypesFor$2"));
                }
            }
            catch (IllegalArgumentException ex) {
                throw a(ex);
            }
            return cMakeModelConfigurationData.getConfigurationName().equals(s);
        });
        if (cMakeModelConfigurationData == null) {
            cMakeModelConfigurationData = (CMakeModelConfigurationData)ContainerUtil.getFirstItem((List)modelConfigurationData);
        }
        List<String> list = null;
        Label_0099: {
            try {
                if (cMakeModelConfigurationData == null) {
                    final List<String> list2;
                    list = (list2 = CMakeModel.DEFAULT_CONFIGURATION_TYPES);
                    break Label_0099;
                }
            }
            catch (IllegalArgumentException ex2) {
                throw a(ex2);
            }
            List<String> list2;
            list = (list2 = cMakeModelConfigurationData.getRegisteredConfigurationTypes());
            try {
                if (list2 == null) {
                    throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/cpp/cmake/settings/CMakeConfigurable$ConfigurationEditor", "getRegisteredConfigurationsTypesFor"));
                }
            }
            catch (IllegalArgumentException ex3) {
                throw a(ex3);
            }
        }
        return list;
    }
    
    @Override
    public String toString() {
        return this.d();
    }
    
    public void dispose() {
        try {
            Disposer.dispose((Disposable)this.myGenerationDirField);
            if (this.myToolSetConfigurable != null) {
                this.myToolSetConfigurable.disposeUIResources();
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
    }
    
    private static IllegalArgumentException a(final IllegalArgumentException ex) {
        return ex;
    }
}
