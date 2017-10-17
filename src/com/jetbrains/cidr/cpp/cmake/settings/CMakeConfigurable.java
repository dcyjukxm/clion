// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.cpp.cmake.settings;

import java.awt.event.ItemEvent;
import com.intellij.openapi.Disposable;
import com.intellij.openapi.util.Disposer;
import java.util.function.Function;
import javax.swing.ComboBoxModel;
import com.intellij.ui.CollectionComboBoxModel;
import java.util.Map;
import java.util.Collections;
import com.jetbrains.cidr.toolchains.CidrToolSet;
import com.jetbrains.cidr.cpp.cmake.model.CMakeGenerator;
import com.intellij.openapi.util.io.FileUtil;
import com.intellij.openapi.vfs.VfsUtilCore;
import com.intellij.openapi.vfs.VfsUtil;
import com.intellij.openapi.vfs.VirtualFile;
import com.intellij.openapi.ui.TextBrowseFolderListener;
import com.intellij.openapi.fileChooser.FileChooserDescriptor;
import java.util.Arrays;
import com.intellij.openapi.ui.TextFieldWithBrowseButton;
import com.intellij.execution.configuration.EnvironmentVariablesTextFieldWithBrowseButton;
import com.jetbrains.cidr.cpp.toolchains.MSVC;
import com.intellij.openapi.ui.ComboBox;
import com.intellij.openapi.util.Pair;
import java.io.File;
import java.util.function.Consumer;
import javax.swing.SwingUtilities;
import javax.swing.event.ListDataEvent;
import javax.swing.event.ListDataListener;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import com.intellij.openapi.actionSystem.ActionToolbarPosition;
import com.intellij.openapi.util.SystemInfo;
import com.intellij.ui.IdeBorderFactory;
import com.intellij.ui.CommonActionsPanel;
import com.intellij.ui.AnActionButton;
import com.intellij.ui.AnActionButtonRunnable;
import javax.swing.JList;
import com.intellij.openapi.actionSystem.CommonShortcuts;
import com.intellij.openapi.actionSystem.AnActionEvent;
import javax.swing.Icon;
import com.intellij.ui.ToolbarDecorator;
import com.intellij.icons.AllIcons;
import com.intellij.ide.IdeBundle;
import javax.swing.ListModel;
import com.intellij.ui.OnePixelSplitter;
import com.intellij.ui.components.JBPanelWithEmptyText;
import com.intellij.ui.JBSplitter;
import com.intellij.ui.components.JBList;
import com.intellij.ui.CollectionListModel;
import java.util.Iterator;
import com.intellij.util.containers.ContainerUtil;
import com.jetbrains.cidr.cpp.cmake.model.CMakeModel;
import java.util.Collection;
import java.util.LinkedHashSet;
import com.jetbrains.cidr.cpp.cmake.model.CMakeModelConfigurationData;
import java.util.List;
import com.jetbrains.cidr.cpp.cmake.CMakeSettingsKt;
import com.intellij.ui.components.JBTextField;
import com.intellij.openapi.options.ConfigurationException;
import com.intellij.openapi.application.ReadAction;
import com.intellij.util.ui.JBInsets;
import com.intellij.util.ui.GridBag;
import com.intellij.util.messages.Topic;
import com.jetbrains.cidr.cpp.CPPToolchains;
import com.jetbrains.cidr.cpp.CPPToolchainsConfigurable;
import com.intellij.openapi.application.ApplicationManager;
import javax.swing.Box;
import java.awt.BorderLayout;
import java.awt.Insets;
import com.intellij.ui.TitledSeparator;
import com.intellij.openapi.util.text.StringUtil;
import javax.swing.JCheckBox;
import com.intellij.ui.components.JBLabel;
import com.intellij.util.ui.UIUtil;
import java.awt.Component;
import com.intellij.util.ui.JBUI;
import java.awt.LayoutManager;
import javax.swing.JPanel;
import java.awt.GridBagLayout;
import javax.swing.JComponent;
import org.jetbrains.annotations.Nullable;
import org.jetbrains.annotations.Nls;
import com.jetbrains.cidr.cpp.CPPBundle;
import org.jetbrains.annotations.NotNull;
import com.intellij.util.messages.MessageBusConnection;
import com.intellij.ui.RawCommandLineEditor;
import com.intellij.ui.components.JBCheckBox;
import com.jetbrains.cidr.cpp.cmake.CMakeSettings;
import com.jetbrains.cidr.cpp.cmake.workspace.CMakeWorkspace;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.options.SearchableConfigurable;

public class CMakeConfigurable implements SearchableConfigurable
{
    private final Project myProject;
    private final CMakeWorkspace myWorkspace;
    private final CMakeSettings mySettings;
    private JBCheckBox myEnableCMakeAutoReload;
    private ConfigurationListEditor myConfigurationListEditor;
    private RawCommandLineEditor myBuildOptions;
    private int myPreselectedConfig;
    private MessageBusConnection myConnection;
    
    public CMakeConfigurable(final Project myProject) {
        this.myPreselectedConfig = -1;
        this.myProject = myProject;
        this.myWorkspace = CMakeWorkspace.getInstance(myProject);
        this.mySettings = this.myWorkspace.getSettings();
    }
    
    @NotNull
    public String getId() {
        String s;
        try {
            s = "CMakeSettings";
            if (s == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/cpp/cmake/settings/CMakeConfigurable", "getId"));
            }
        }
        catch (RuntimeException ex) {
            throw b(ex);
        }
        return s;
    }
    
    @Nls
    public String getDisplayName() {
        return CPPBundle.message("cmake", new Object[0]);
    }
    
    @Nullable
    public String getHelpTopic() {
        return "reference.build.cmake";
    }
    
    @Nullable
    public JComponent createComponent() {
        final JPanel panel = new JPanel(new GridBagLayout());
        final GridBag bagConstraints = createBagConstraints(false);
        final JBInsets insets = JBUI.insets(12, -1, -1, -1);
        final JBInsets insets2 = JBUI.insets(-1, 20, -1, -1);
        panel.add((Component)(this.myEnableCMakeAutoReload = new JBCheckBox(CPPBundle.message("cmake.action.autoReloadCmakeProject.checkbox", new Object[0]))), bagConstraints.nextLine().next().insets(-1, -1, 0, -1).coverLine());
        panel.add((Component)new JBLabel(CPPBundle.message("cmake.action.autoReloadCmakeProject.checkbox.hint", new Object[0]), UIUtil.ComponentStyle.SMALL, UIUtil.FontColor.BRIGHTER), bagConstraints.nextLine().next().insets(0, UIUtil.getCheckBoxTextHorizontalOffset((JCheckBox)this.myEnableCMakeAutoReload), -1, -1).coverLine());
        panel.add((Component)new TitledSeparator(StringUtil.notNullize(CPPBundle.message("cmake.settings.generationGroup", new Object[0]))), bagConstraints.nextLine().next().coverLine());
        panel.add(this.myConfigurationListEditor = new ConfigurationListEditor(), bagConstraints.nextLine().next().coverLine().insets(((Insets)insets2).top, ((Insets)insets2).left, ((Insets)insets2).bottom, 0));
        panel.add((Component)new TitledSeparator(CPPBundle.message("cmake.settings.buildGroup", new Object[0])), bagConstraints.nextLine().coverLine().insets((Insets)insets));
        panel.add((Component)new JBLabel(CPPBundle.message("cmake.settings.buildOptions", new Object[0])), bagConstraints.nextLine().next().insets((Insets)insets2));
        panel.add((Component)(this.myBuildOptions = a(CPPBundle.message("cmake.settings.buildOptions.dialog", new Object[0]))), bagConstraints.next().coverLine());
        final JPanel panel2 = new JPanel(new BorderLayout());
        panel2.add(panel, "North");
        panel2.add(Box.createVerticalGlue(), "Center");
        (this.myConnection = ApplicationManager.getApplication().getMessageBus().connect()).subscribe((Topic)CPPToolchainsConfigurable.TOPIC, (Object)new CPPToolchainsConfigurable.EnvironmentChangeListener() {
            @Override
            public void environmentChanged(@Nullable final CPPToolchains.WinEnvironment winEnvironment) {
                CMakeConfigurable.this.myConfigurationListEditor.updateSelectedEnvironment(winEnvironment);
            }
        });
        return panel2;
    }
    
    public void setPreselectedConfig(final int myPreselectedConfig) {
        this.myPreselectedConfig = myPreselectedConfig;
    }
    
    @NotNull
    public static GridBag createBagConstraints(final boolean b) {
        GridBag setDefaultFill = null;
        Label_0027: {
            GridBag setDefaultWeightX;
            int n;
            try {
                setDefaultWeightX = new GridBag().setDefaultWeightX(1, 1.0);
                n = 0;
                if (b) {
                    final int n2 = 10;
                    break Label_0027;
                }
            }
            catch (RuntimeException ex) {
                throw b(ex);
            }
            final int n2 = 0;
            try {
                setDefaultFill = setDefaultWeightX.setDefaultInsets(n, n2, 4, 10).setDefaultAnchor(21).setDefaultFill(2);
                if (setDefaultFill == null) {
                    throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/cpp/cmake/settings/CMakeConfigurable", "createBagConstraints"));
                }
            }
            catch (RuntimeException ex2) {
                throw b(ex2);
            }
        }
        return setDefaultFill;
    }
    
    @NotNull
    private static RawCommandLineEditor a(@NotNull final String dialogCaption) {
        try {
            if (dialogCaption == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "dialogCaption", "com/jetbrains/cidr/cpp/cmake/settings/CMakeConfigurable", "createOptionsEditor"));
            }
        }
        catch (RuntimeException ex) {
            throw b(ex);
        }
        final RawCommandLineEditor rawCommandLineEditor = new RawCommandLineEditor();
        RawCommandLineEditor rawCommandLineEditor2;
        try {
            rawCommandLineEditor.setDialogCaption(dialogCaption);
            rawCommandLineEditor2 = rawCommandLineEditor;
            if (rawCommandLineEditor2 == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/cpp/cmake/settings/CMakeConfigurable", "createOptionsEditor"));
            }
        }
        catch (RuntimeException ex2) {
            throw b(ex2);
        }
        return rawCommandLineEditor2;
    }
    
    public boolean isModified() {
        return (boolean)ReadAction.compute(() -> {
            try {
                if (this.mySettings.isAutoReloadEnabled() != this.myEnableCMakeAutoReload.isSelected()) {
                    return true;
                }
            }
            catch (RuntimeException ex) {
                throw b(ex);
            }
            try {
                if (this.myConfigurationListEditor.isModified(this.mySettings.getConfigurations())) {
                    return true;
                }
            }
            catch (RuntimeException ex2) {
                throw b(ex2);
            }
            try {
                if (!StringUtil.equals((CharSequence)this.mySettings.getBuildOptions(), (CharSequence)CMakeSettingsKt.normalizeOptions(this.myBuildOptions.getText()))) {
                    return true;
                }
            }
            catch (RuntimeException ex3) {
                throw b(ex3);
            }
            return false;
        });
    }
    
    public void apply() throws ConfigurationException {
        ApplicationManager.getApplication().runWriteAction(() -> {
            this.mySettings.setAutoReloadEnabled(this.myEnableCMakeAutoReload.isSelected());
            this.mySettings.setConfigurations(this.myConfigurationListEditor.apply());
            this.mySettings.setBuildOptions(this.myBuildOptions.getText());
            return null;
        });
    }
    
    public void reset() {
        ApplicationManager.getApplication().runReadAction(() -> {
            this.myEnableCMakeAutoReload.setSelected(this.mySettings.isAutoReloadEnabled());
            this.myConfigurationListEditor.reset(this.mySettings.getConfigurations());
            this.myBuildOptions.setText(this.mySettings.getBuildOptions());
            ((JBTextField)this.myBuildOptions.getTextField()).getEmptyText().setText(CMakeSettingsKt.getCurrentDefaultBuildOptions());
        });
    }
    
    @NotNull
    static String selectConfigurationName(@NotNull final List<CMakeModelConfigurationData> list, @NotNull final List<String> list2) {
        try {
            if (list == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "modelConfigurationData", "com/jetbrains/cidr/cpp/cmake/settings/CMakeConfigurable", "selectConfigurationName"));
            }
        }
        catch (RuntimeException ex) {
            throw b(ex);
        }
        try {
            if (list2 == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "alreadyAddedConfigurations", "com/jetbrains/cidr/cpp/cmake/settings/CMakeConfigurable", "selectConfigurationName"));
            }
        }
        catch (RuntimeException ex2) {
            throw b(ex2);
        }
        final LinkedHashSet set = new LinkedHashSet();
        final Iterator<CMakeModelConfigurationData> iterator = list.iterator();
        while (iterator.hasNext()) {
            set.addAll(iterator.next().getRegisteredConfigurationTypes());
        }
        try {
            if (set.isEmpty()) {
                set.addAll(CMakeModel.DEFAULT_CONFIGURATION_TYPES);
            }
        }
        catch (RuntimeException ex3) {
            throw b(ex3);
        }
        String s;
        try {
            set.removeAll(list2);
            set.remove("Default");
            s = (String)ContainerUtil.getFirstItem((Collection)set, (Object)"Default");
            if (s == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/cpp/cmake/settings/CMakeConfigurable", "selectConfigurationName"));
            }
        }
        catch (RuntimeException ex4) {
            throw b(ex4);
        }
        return s;
    }
    
    public void disposeUIResources() {
        this.myConfigurationListEditor.dispose();
        this.myConnection.disconnect();
    }
    
    private static RuntimeException b(final RuntimeException ex) {
        return ex;
    }
    
    private class ConfigurationListEditor extends JPanel
    {
        @NotNull
        private final CollectionListModel<ConfigurationEditor> myEditors;
        private final JBList myList;
        private final JBSplitter mySplitter;
        private final JBPanelWithEmptyText myNoEditorsPanel;
        boolean myUpdating;
        
        public ConfigurationListEditor() {
            super(new BorderLayout());
            this.myEditors = (CollectionListModel<ConfigurationEditor>)new CollectionListModel((Object[])new ConfigurationEditor[0]);
            (this.mySplitter = (JBSplitter)new OnePixelSplitter(false, 0.2f)).setSplitterProportionKey("CMakeConfigurable.configurations");
            this.mySplitter.setHonorComponentsMinimumSize(true);
            this.myList = new JBList((ListModel)this.myEditors);
            final ToolbarDecorator.ElementActionButton elementActionButton = new ToolbarDecorator.ElementActionButton(IdeBundle.message("button.copy", new Object[0]), AllIcons.Actions.Copy) {
                public boolean isDumbAware() {
                    return true;
                }
                
                public void actionPerformed(final AnActionEvent anActionEvent) {
                    ConfigurationListEditor.this.c(((ConfigurationEditor)ConfigurationListEditor.this.myEditors.getElementAt(ConfigurationListEditor.this.myList.getSelectedIndex())).create());
                }
            };
            ((AnActionButton)elementActionButton).setShortcut(CommonShortcuts.getDuplicate());
            final ToolbarDecorator setPanelBorder = ToolbarDecorator.createDecorator((JList)this.myList).setAddAction((AnActionButtonRunnable)new AnActionButtonRunnable() {
                public void run(final AnActionButton anActionButton) {
                    ConfigurationListEditor.this.c(null);
                }
            }).setRemoveAction((AnActionButtonRunnable)new AnActionButtonRunnable() {
                public void run(final AnActionButton anActionButton) {
                    ConfigurationListEditor.this.a(() -> ConfigurationListEditor.this.myEditors.remove(ConfigurationListEditor.this.myList.getSelectedIndex()));
                }
            }).addExtraAction((AnActionButton)elementActionButton).setButtonComparator(new String[] { CommonActionsPanel.Buttons.ADD.getText(), CommonActionsPanel.Buttons.REMOVE.getText(), ((AnActionButton)elementActionButton).getTemplatePresentation().getText() }).setPanelBorder(IdeBorderFactory.createBorder(11));
            try {
                if (!SystemInfo.isMac) {
                    setPanelBorder.setToolbarPosition(ActionToolbarPosition.TOP);
                }
            }
            catch (IllegalStateException ex) {
                throw a(ex);
            }
            this.mySplitter.setFirstComponent((JComponent)setPanelBorder.createPanel());
            this.myNoEditorsPanel = new JBPanelWithEmptyText();
            final ConfigurationEditor configurationEditor = new ConfigurationEditor(this);
            configurationEditor.reset(new CMakeSettings.Configuration());
            this.myNoEditorsPanel.setPreferredSize(configurationEditor.getPreferredSize());
            this.myNoEditorsPanel.getEmptyText().setText(CPPBundle.message("cmake.settings.configuration.empty", new Object[0]));
            this.myList.getEmptyText().setText(CPPBundle.message("cmake.settings.configuration.empty", new Object[0]));
            this.myList.setSelectionMode(0);
            this.myList.addListSelectionListener((ListSelectionListener)new ListSelectionListener() {
                @Override
                public void valueChanged(final ListSelectionEvent listSelectionEvent) {
                    if (listSelectionEvent.getValueIsAdjusting()) {
                        return;
                    }
                    ConfigurationListEditor.this.updateSelectedEditor();
                }
            });
            this.myEditors.addListDataListener((ListDataListener)new ListDataListener() {
                @Override
                public void intervalAdded(final ListDataEvent listDataEvent) {
                    ConfigurationListEditor.this.a();
                }
                
                @Override
                public void intervalRemoved(final ListDataEvent listDataEvent) {
                    ConfigurationListEditor.this.a();
                }
                
                @Override
                public void contentsChanged(final ListDataEvent listDataEvent) {
                    ConfigurationListEditor.this.a();
                }
            });
            this.updateSelectedEditor();
            this.add((Component)this.mySplitter);
        }
        
        public void updateSelectedEditor() {
            final int selectedIndex = this.myList.getSelectedIndex();
            try {
                if (selectedIndex >= 0) {
                    this.mySplitter.setSecondComponent((JComponent)this.myEditors.getElementAt(selectedIndex));
                    return;
                }
            }
            catch (IllegalStateException ex) {
                throw a(ex);
            }
            this.mySplitter.setSecondComponent((JComponent)this.myNoEditorsPanel);
        }
        
        public void updateSelectedEnvironment(@Nullable final CPPToolchains.WinEnvironment winEnvironment) {
            this.myEditors.getItems().forEach(configurationEditor -> configurationEditor.updateEnvironment(winEnvironment));
        }
        
        @Override
        public void updateUI() {
            try {
                super.updateUI();
                if (this.myEditors != null) {
                    this.myEditors.getItems().forEach(SwingUtilities::updateComponentTreeUI);
                }
            }
            catch (IllegalStateException ex) {
                throw a(ex);
            }
        }
        
        public void dispose() {
            this.myEditors.getItems().forEach(ConfigurationEditor::dispose);
        }
        
        public boolean isModified(final List<CMakeSettings.Configuration> list) {
            try {
                if (this.myEditors.getSize() != list.size()) {
                    return true;
                }
            }
            catch (IllegalStateException ex) {
                throw a(ex);
            }
            int n = 0;
            while (true) {
                Label_0074: {
                    try {
                        if (n >= list.size()) {
                            break;
                        }
                        final ConfigurationListEditor configurationListEditor = this;
                        final CollectionListModel<ConfigurationEditor> collectionListModel = configurationListEditor.myEditors;
                        final int n2 = n;
                        final Object o = collectionListModel.getElementAt(n2);
                        final ConfigurationEditor configurationEditor = (ConfigurationEditor)o;
                        final List<CMakeSettings.Configuration> list2 = list;
                        final int n3 = n;
                        final CMakeSettings.Configuration configuration = list2.get(n3);
                        final CMakeSettings.Configuration configuration2 = configuration;
                        final boolean b = configurationEditor.isModified(configuration2);
                        if (b) {
                            return true;
                        }
                        break Label_0074;
                    }
                    catch (IllegalStateException ex2) {
                        throw a(ex2);
                    }
                    try {
                        final ConfigurationListEditor configurationListEditor = this;
                        final CollectionListModel<ConfigurationEditor> collectionListModel = configurationListEditor.myEditors;
                        final int n2 = n;
                        final Object o = collectionListModel.getElementAt(n2);
                        final ConfigurationEditor configurationEditor = (ConfigurationEditor)o;
                        final List<CMakeSettings.Configuration> list2 = list;
                        final int n3 = n;
                        final CMakeSettings.Configuration configuration = list2.get(n3);
                        final CMakeSettings.Configuration configuration2 = configuration;
                        final boolean b = configurationEditor.isModified(configuration2);
                        if (b) {
                            return true;
                        }
                    }
                    catch (IllegalStateException ex3) {
                        throw a(ex3);
                    }
                }
                ++n;
            }
            return false;
        }
        
        public List<CMakeSettings.Configuration> apply() {
            return (List<CMakeSettings.Configuration>)ContainerUtil.map((Collection)this.myEditors.getItems(), configurationEditor -> configurationEditor.create());
        }
        
        public void reset(final List<CMakeSettings.Configuration> list) {
            int i;
            int j;
            int n;
            this.a(() -> {
                try {
                    while (i < Math.min(list.size(), this.myEditors.getSize())) {
                        ((ConfigurationEditor)this.myEditors.getElementAt(i)).reset(list.get(i));
                        ++i;
                    }
                }
                catch (IllegalStateException ex) {
                    throw a(ex);
                }
                if (list.size() > this.myEditors.getSize()) {
                    this.myEditors.getSize();
                    try {
                        while (j < list.size()) {
                            this.b(list.get(j));
                            ++j;
                        }
                    }
                    catch (IllegalStateException ex2) {
                        throw a(ex2);
                    }
                }
                else {
                    while (list.size() < this.myEditors.getSize()) {
                        n = this.myEditors.getSize() - 1;
                        this.myEditors.getElementAt(n);
                        this.myEditors.remove(n);
                    }
                }
            });
        }
        
        private void a(final Runnable p0) {
            // 
            // This method could not be decompiled.
            // 
            // Original Bytecode:
            // 
            //     0: aload_0        
            //     1: getfield        com/jetbrains/cidr/cpp/cmake/settings/CMakeConfigurable$ConfigurationListEditor.myList:Lcom/intellij/ui/components/JBList;
            //     4: invokevirtual   com/intellij/ui/components/JBList.requestFocusInWindow:()Z
            //     7: pop            
            //     8: aload_0        
            //     9: getfield        com/jetbrains/cidr/cpp/cmake/settings/CMakeConfigurable$ConfigurationListEditor.this$0:Lcom/jetbrains/cidr/cpp/cmake/settings/CMakeConfigurable;
            //    12: invokestatic    com/jetbrains/cidr/cpp/cmake/settings/CMakeConfigurable.access$600:(Lcom/jetbrains/cidr/cpp/cmake/settings/CMakeConfigurable;)I
            //    15: iconst_m1      
            //    16: if_icmpeq       33
            //    19: aload_0        
            //    20: getfield        com/jetbrains/cidr/cpp/cmake/settings/CMakeConfigurable$ConfigurationListEditor.this$0:Lcom/jetbrains/cidr/cpp/cmake/settings/CMakeConfigurable;
            //    23: invokestatic    com/jetbrains/cidr/cpp/cmake/settings/CMakeConfigurable.access$600:(Lcom/jetbrains/cidr/cpp/cmake/settings/CMakeConfigurable;)I
            //    26: goto            40
            //    29: invokestatic    com/jetbrains/cidr/cpp/cmake/settings/CMakeConfigurable$ConfigurationListEditor.a:(Ljava/lang/IllegalStateException;)Ljava/lang/IllegalStateException;
            //    32: athrow         
            //    33: aload_0        
            //    34: getfield        com/jetbrains/cidr/cpp/cmake/settings/CMakeConfigurable$ConfigurationListEditor.myList:Lcom/intellij/ui/components/JBList;
            //    37: invokevirtual   com/intellij/ui/components/JBList.getSelectedIndex:()I
            //    40: istore_2       
            //    41: aload_0        
            //    42: getfield        com/jetbrains/cidr/cpp/cmake/settings/CMakeConfigurable$ConfigurationListEditor.this$0:Lcom/jetbrains/cidr/cpp/cmake/settings/CMakeConfigurable;
            //    45: iconst_m1      
            //    46: invokestatic    com/jetbrains/cidr/cpp/cmake/settings/CMakeConfigurable.access$602:(Lcom/jetbrains/cidr/cpp/cmake/settings/CMakeConfigurable;I)I
            //    49: pop            
            //    50: aload_0        
            //    51: iconst_1       
            //    52: putfield        com/jetbrains/cidr/cpp/cmake/settings/CMakeConfigurable$ConfigurationListEditor.myUpdating:Z
            //    55: aload_1        
            //    56: invokeinterface java/lang/Runnable.run:()V
            //    61: aload_0        
            //    62: iconst_0       
            //    63: putfield        com/jetbrains/cidr/cpp/cmake/settings/CMakeConfigurable$ConfigurationListEditor.myUpdating:Z
            //    66: goto            77
            //    69: astore_3       
            //    70: aload_0        
            //    71: iconst_0       
            //    72: putfield        com/jetbrains/cidr/cpp/cmake/settings/CMakeConfigurable$ConfigurationListEditor.myUpdating:Z
            //    75: aload_3        
            //    76: athrow         
            //    77: aload_0        
            //    78: invokespecial   com/jetbrains/cidr/cpp/cmake/settings/CMakeConfigurable$ConfigurationListEditor.a:()V
            //    81: aload_0        
            //    82: getfield        com/jetbrains/cidr/cpp/cmake/settings/CMakeConfigurable$ConfigurationListEditor.myEditors:Lcom/intellij/ui/CollectionListModel;
            //    85: invokevirtual   com/intellij/ui/CollectionListModel.getSize:()I
            //    88: istore_3       
            //    89: iload_3        
            //    90: ifle            145
            //    93: aload_0        
            //    94: getfield        com/jetbrains/cidr/cpp/cmake/settings/CMakeConfigurable$ConfigurationListEditor.myList:Lcom/intellij/ui/components/JBList;
            //    97: invokevirtual   com/intellij/ui/components/JBList.getSelectedIndex:()I
            //   100: iconst_m1      
            //   101: if_icmpne       145
            //   104: goto            111
            //   107: invokestatic    com/jetbrains/cidr/cpp/cmake/settings/CMakeConfigurable$ConfigurationListEditor.a:(Ljava/lang/IllegalStateException;)Ljava/lang/IllegalStateException;
            //   110: athrow         
            //   111: aload_0        
            //   112: getfield        com/jetbrains/cidr/cpp/cmake/settings/CMakeConfigurable$ConfigurationListEditor.myList:Lcom/intellij/ui/components/JBList;
            //   115: iload_2        
            //   116: iconst_m1      
            //   117: if_icmpeq       141
            //   120: goto            127
            //   123: invokestatic    com/jetbrains/cidr/cpp/cmake/settings/CMakeConfigurable$ConfigurationListEditor.a:(Ljava/lang/IllegalStateException;)Ljava/lang/IllegalStateException;
            //   126: athrow         
            //   127: iload_3        
            //   128: iconst_1       
            //   129: isub           
            //   130: iload_2        
            //   131: invokestatic    java/lang/Math.min:(II)I
            //   134: goto            142
            //   137: invokestatic    com/jetbrains/cidr/cpp/cmake/settings/CMakeConfigurable$ConfigurationListEditor.a:(Ljava/lang/IllegalStateException;)Ljava/lang/IllegalStateException;
            //   140: athrow         
            //   141: iconst_0       
            //   142: invokevirtual   com/intellij/ui/components/JBList.setSelectedIndex:(I)V
            //   145: aload_0        
            //   146: getfield        com/jetbrains/cidr/cpp/cmake/settings/CMakeConfigurable$ConfigurationListEditor.myList:Lcom/intellij/ui/components/JBList;
            //   149: invokevirtual   com/intellij/ui/components/JBList.getSelectedIndex:()I
            //   152: istore          4
            //   154: iload           4
            //   156: iconst_m1      
            //   157: if_icmpeq       176
            //   160: aload_0        
            //   161: getfield        com/jetbrains/cidr/cpp/cmake/settings/CMakeConfigurable$ConfigurationListEditor.myList:Lcom/intellij/ui/components/JBList;
            //   164: iload           4
            //   166: invokevirtual   com/intellij/ui/components/JBList.ensureIndexIsVisible:(I)V
            //   169: goto            176
            //   172: invokestatic    com/jetbrains/cidr/cpp/cmake/settings/CMakeConfigurable$ConfigurationListEditor.a:(Ljava/lang/IllegalStateException;)Ljava/lang/IllegalStateException;
            //   175: athrow         
            //   176: return         
            //    Exceptions:
            //  Try           Handler
            //  Start  End    Start  End    Type                             
            //  -----  -----  -----  -----  ---------------------------------
            //  0      29     29     33     Ljava/lang/IllegalStateException;
            //  55     61     69     77     Any
            //  89     104    107    111    Ljava/lang/IllegalStateException;
            //  93     120    123    127    Ljava/lang/IllegalStateException;
            //  111    137    137    141    Ljava/lang/IllegalStateException;
            //  154    169    172    176    Ljava/lang/IllegalStateException;
            // 
            // The error that occurred was:
            // 
            // java.lang.IllegalStateException: Expression is linked from several locations: Label_0111:
            //     at com.strobel.decompiler.ast.Error.expressionLinkedFromMultipleLocations(Error.java:27)
            //     at com.strobel.decompiler.ast.AstOptimizer.mergeDisparateObjectInitializations(AstOptimizer.java:2592)
            //     at com.strobel.decompiler.ast.AstOptimizer.optimize(AstOptimizer.java:235)
            //     at com.strobel.decompiler.ast.AstOptimizer.optimize(AstOptimizer.java:42)
            //     at com.strobel.decompiler.languages.java.ast.AstMethodBodyBuilder.createMethodBody(AstMethodBodyBuilder.java:214)
            //     at com.strobel.decompiler.languages.java.ast.AstMethodBodyBuilder.createMethodBody(AstMethodBodyBuilder.java:99)
            //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createMethodBody(AstBuilder.java:757)
            //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createMethod(AstBuilder.java:655)
            //     at com.strobel.decompiler.languages.java.ast.AstBuilder.addTypeMembers(AstBuilder.java:532)
            //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createTypeCore(AstBuilder.java:499)
            //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createTypeNoCache(AstBuilder.java:141)
            //     at com.strobel.decompiler.languages.java.ast.AstBuilder.addTypeMembers(AstBuilder.java:556)
            //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createTypeCore(AstBuilder.java:499)
            //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createTypeNoCache(AstBuilder.java:141)
            //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createType(AstBuilder.java:130)
            //     at com.strobel.decompiler.languages.java.ast.AstBuilder.addType(AstBuilder.java:105)
            //     at com.strobel.decompiler.languages.java.JavaLanguage.buildAst(JavaLanguage.java:71)
            //     at com.strobel.decompiler.languages.java.JavaLanguage.decompileType(JavaLanguage.java:59)
            //     at com.strobel.decompiler.DecompilerDriver.decompileType(DecompilerDriver.java:317)
            //     at com.strobel.decompiler.DecompilerDriver.main(DecompilerDriver.java:141)
            // 
            throw new IllegalStateException("An error occurred while decompiling this method.");
        }
        
        private void a() {
            try {
                if (this.myUpdating) {
                    return;
                }
            }
            catch (IllegalStateException ex) {
                throw a(ex);
            }
            final List items = this.myEditors.getItems();
            ContainerUtil.zip((Iterable)items, (Iterable)CMakeWorkspace.getConfigurationGenerationDirNames(ContainerUtil.map((Collection)items, configurationEditor -> configurationEditor.d()))).forEach(pair -> ((ConfigurationEditor)pair.first).a((String)pair.second));
        }
        
        private void c(@Nullable final CMakeSettings.Configuration configuration) {
            this.a(() -> this.myList.setSelectedIndex(this.myEditors.getElementIndex((Object)this.b(this.a(configuration)))));
        }
        
        @NotNull
        private CMakeSettings.Configuration a(@Nullable final CMakeSettings.Configuration configuration) {
            final String selectConfigurationName = CMakeConfigurable.selectConfigurationName(CMakeConfigurable.this.myWorkspace.getModelConfigurationData(), ContainerUtil.map((Collection)this.myEditors.getItems(), configurationEditor -> configurationEditor.d()));
            CMakeSettings.Configuration configuration2 = null;
            Label_0053: {
                try {
                    if (configuration == null) {
                        final CMakeSettings.Configuration withConfigName;
                        configuration2 = (withConfigName = new CMakeSettings.Configuration(selectConfigurationName));
                        break Label_0053;
                    }
                }
                catch (IllegalStateException ex) {
                    throw a(ex);
                }
                CMakeSettings.Configuration withConfigName;
                configuration2 = (withConfigName = configuration.withConfigName(selectConfigurationName));
                try {
                    if (withConfigName == null) {
                        throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/cpp/cmake/settings/CMakeConfigurable$ConfigurationListEditor", "createNewConfiguration"));
                    }
                }
                catch (IllegalStateException ex2) {
                    throw a(ex2);
                }
            }
            return configuration2;
        }
        
        @NotNull
        private ConfigurationEditor b(@NotNull final CMakeSettings.Configuration configuration) {
            try {
                if (configuration == null) {
                    throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "configuration", "com/jetbrains/cidr/cpp/cmake/settings/CMakeConfigurable$ConfigurationListEditor", "addConfigurationsEditor"));
                }
            }
            catch (IllegalStateException ex) {
                throw a(ex);
            }
            final ConfigurationEditor configurationEditor = new ConfigurationEditor(this);
            ConfigurationEditor configurationEditor2;
            try {
                configurationEditor.reset(configuration);
                this.myEditors.add((Object)configurationEditor);
                configurationEditor2 = configurationEditor;
                if (configurationEditor2 == null) {
                    throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/cpp/cmake/settings/CMakeConfigurable$ConfigurationListEditor", "addConfigurationsEditor"));
                }
            }
            catch (IllegalStateException ex2) {
                throw a(ex2);
            }
            return configurationEditor2;
        }
        
        public void configNameChanged(@NotNull final ConfigurationEditor configurationEditor) {
            try {
                if (configurationEditor == null) {
                    throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "editor", "com/jetbrains/cidr/cpp/cmake/settings/CMakeConfigurable$ConfigurationListEditor", "configNameChanged"));
                }
            }
            catch (IllegalStateException ex) {
                throw a(ex);
            }
            final int elementIndex = this.myEditors.getElementIndex((Object)configurationEditor);
            try {
                if (elementIndex != -1) {
                    this.myEditors.setElementAt((Object)configurationEditor, elementIndex);
                }
            }
            catch (IllegalStateException ex2) {
                throw a(ex2);
            }
        }
        
        @Nullable
        public File getEffectiveGenerationDir(@NotNull final ConfigurationEditor configurationEditor) {
            try {
                if (configurationEditor == null) {
                    throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "editor", "com/jetbrains/cidr/cpp/cmake/settings/CMakeConfigurable$ConfigurationListEditor", "getEffectiveGenerationDir"));
                }
            }
            catch (IllegalStateException ex) {
                throw a(ex);
            }
            final int elementIndex = this.myEditors.getElementIndex((Object)configurationEditor);
            try {
                if (elementIndex == -1) {
                    return null;
                }
            }
            catch (IllegalStateException ex2) {
                throw a(ex2);
            }
            return CMakeConfigurable.this.myWorkspace.getEffectiveConfigurationGenerationDirs(ContainerUtil.map((Collection)this.myEditors.getItems(), configurationEditor -> Pair.create((Object)configurationEditor.d(), (Object)configurationEditor.c()))).get(elementIndex);
        }
        
        private static IllegalStateException a(final IllegalStateException ex) {
            return ex;
        }
    }
    
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
            this.add((Component)(this.myCMakeOptions = a(CPPBundle.message("cmake.settings.generationOptions.dialog", new Object[0]))), bagConstraints.next().coverLine());
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
            this.myGenerationDirField.addBrowseFolderListener((TextBrowseFolderListener)new TextBrowseFolderListener(new FileChooserDescriptor(false, true, false, false, false, false), CMakeConfigurable.this.myProject) {
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
                            return CMakeConfigurable.this.myWorkspace.getEffectiveContentRoot();
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
                    final File projectSubDir = CMakeConfigurable.this.myWorkspace.getProjectSubDir(new File(""));
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
            final List<CMakeModelConfigurationData> modelConfigurationData = CMakeConfigurable.this.myWorkspace.getModelConfigurationData();
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
}
