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
import java.util.Map;
import java.util.Collections;
import com.jetbrains.cidr.toolchains.CidrToolSet;
import com.intellij.openapi.util.text.StringUtil;
import com.jetbrains.cidr.cpp.cmake.CMakeSettingsKt;
import com.intellij.ui.components.JBTextField;
import com.jetbrains.cidr.cpp.cmake.model.CMakeGenerator;
import com.intellij.util.ui.JBInsets;
import com.intellij.util.ui.GridBag;
import com.intellij.openapi.util.io.FileUtil;
import com.intellij.openapi.vfs.VfsUtilCore;
import com.intellij.openapi.vfs.VfsUtil;
import com.intellij.openapi.vfs.VirtualFile;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.ui.TextBrowseFolderListener;
import com.intellij.openapi.fileChooser.FileChooserDescriptor;
import java.util.Arrays;
import java.awt.Insets;
import com.intellij.util.ui.UIUtil;
import com.intellij.util.ui.JBUI;
import java.awt.GridBagLayout;
import com.intellij.openapi.ui.TextFieldWithBrowseButton;
import com.intellij.execution.configuration.EnvironmentVariablesTextFieldWithBrowseButton;
import com.jetbrains.cidr.cpp.toolchains.MSVC;
import com.intellij.ui.components.JBLabel;
import com.intellij.ui.RawCommandLineEditor;
import com.intellij.openapi.ui.ComboBox;
import com.intellij.openapi.util.Pair;
import java.io.File;
import com.jetbrains.cidr.cpp.cmake.workspace.CMakeWorkspace;
import java.util.Collection;
import com.intellij.util.containers.ContainerUtil;
import java.util.List;
import java.util.function.Consumer;
import javax.swing.SwingUtilities;
import org.jetbrains.annotations.Nullable;
import com.jetbrains.cidr.cpp.CPPToolchains;
import java.awt.Component;
import javax.swing.event.ListDataEvent;
import javax.swing.event.ListDataListener;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import com.jetbrains.cidr.cpp.CPPBundle;
import javax.swing.JComponent;
import com.intellij.openapi.actionSystem.ActionToolbarPosition;
import com.intellij.openapi.util.SystemInfo;
import com.intellij.ui.IdeBorderFactory;
import com.intellij.ui.CommonActionsPanel;
import com.jetbrains.cidr.cpp.cmake.CMakeSettings;
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
import java.awt.LayoutManager;
import java.awt.BorderLayout;
import com.intellij.ui.components.JBPanelWithEmptyText;
import com.intellij.ui.JBSplitter;
import com.intellij.ui.components.JBList;
import org.jetbrains.annotations.NotNull;
import com.intellij.ui.CollectionListModel;
import javax.swing.JPanel;

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
        final String selectConfigurationName = CMakeConfigurable.selectConfigurationName(CMakeConfigurable.access$700(CMakeConfigurable.this).getModelConfigurationData(), ContainerUtil.map((Collection)this.myEditors.getItems(), configurationEditor -> configurationEditor.d()));
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
        return CMakeConfigurable.access$700(CMakeConfigurable.this).getEffectiveConfigurationGenerationDirs(ContainerUtil.map((Collection)this.myEditors.getItems(), configurationEditor -> Pair.create((Object)configurationEditor.d(), (Object)configurationEditor.c()))).get(elementIndex);
    }
    
    private static IllegalStateException a(final IllegalStateException ex) {
        return ex;
    }
}
