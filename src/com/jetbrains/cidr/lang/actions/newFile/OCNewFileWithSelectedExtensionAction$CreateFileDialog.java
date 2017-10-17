// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.lang.actions.newFile;

import com.intellij.application.options.CodeStyleAbstractPanel;
import com.intellij.application.options.codeStyle.NewCodeStyleSettingsPanel;
import com.intellij.openapi.options.Configurable;
import com.intellij.application.options.TabbedLanguageCodeStylePanel;
import com.intellij.application.options.codeStyle.CodeStyleMainPanel;
import com.intellij.openapi.options.ShowSettingsUtil;
import com.jetbrains.cidr.lang.OCLog;
import com.intellij.ide.actions.ShowSettingsUtilImpl;
import com.intellij.openapi.options.ex.ConfigurableVisitor;
import com.jetbrains.cidr.lang.OCLanguage;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import com.intellij.icons.AllIcons;
import com.intellij.ui.InplaceButton;
import org.jetbrains.annotations.Nullable;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import com.jetbrains.cidr.lang.OCBundle;
import java.awt.Component;
import java.awt.LayoutManager;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import com.intellij.util.messages.Topic;
import com.jetbrains.cidr.lang.settings.OCFileExtensionsSettingsListener;
import com.intellij.openapi.application.ApplicationManager;
import com.intellij.util.ui.FormBuilder;
import com.intellij.util.containers.ContainerUtil;
import com.intellij.util.PlatformUtils;
import org.jetbrains.annotations.NotNull;
import com.intellij.util.containers.ImmutableList;
import com.intellij.ide.actions.TemplateKindCombo;
import com.jetbrains.cidr.lang.settings.OCCodeStyleSettings;

protected class CreateFileDialog extends CreateFileDialogBase
{
    protected OCCodeStyleSettings.FileExtensionPair myFileExtensionPair;
    protected TemplateKindCombo myKindCombo;
    @NotNull
    protected ImmutableList<OCCodeStyleSettings.FileExtensionPair> pairs;
    
    public CreateFileDialog(final OCNewFileWithSelectedExtensionAction ocNewFileWithSelectedExtensionAction, final String s) {
        this(ocNewFileWithSelectedExtensionAction, "New " + ocNewFileWithSelectedExtensionAction.getTemplatePresentation().getText(), s);
    }
    
    public CreateFileDialog(final String s, final String s2) {
        super(s, s2, PlatformUtils.isCLion() ? "New_CPP_File_Dialog" : null);
        this.pairs = (ImmutableList<OCCodeStyleSettings.FileExtensionPair>)ContainerUtil.immutableList((Object[])new OCCodeStyleSettings.FileExtensionPair[0]);
    }
    
    @Override
    protected void addNameField(final FormBuilder formBuilder) {
        this.addNameField(formBuilder, true);
    }
    
    @Override
    public void fillGenericControls(final FormBuilder formBuilder) {
        super.fillGenericControls(formBuilder);
        this.createFileTypesCombo(formBuilder);
    }
    
    protected void createFileTypesCombo(final FormBuilder formBuilder) {
        ApplicationManager.getApplication().getMessageBus().connect(this.getDisposable()).subscribe((Topic)OCFileExtensionsSettingsListener.TOPIC, (Object)new OCFileExtensionsSettingsListener() {
            @Override
            public void settingsUpdated() {
                CreateFileDialog.this.reloadExtensions();
            }
        });
        final JPanel panel = new JPanel(new BorderLayout(5, 0));
        panel.add((Component)(this.myKindCombo = new TemplateKindCombo()), "Center");
        panel.add((Component)this.a(), "East");
        formBuilder.addLabeledComponent(OCBundle.message("create.field.type", new Object[0]), (JComponent)panel);
        this.reloadExtensions();
        ((JComboBox)this.myKindCombo.getChildComponent()).addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(final ItemEvent itemEvent) {
                if (itemEvent.getStateChange() == 1) {
                    final int selectedIndex = CreateFileDialog.this.myKindCombo.getComboBox().getSelectedIndex();
                    if (selectedIndex < CreateFileDialog.this.pairs.size()) {
                        CreateFileDialog.this.myFileExtensionPair = (OCCodeStyleSettings.FileExtensionPair)CreateFileDialog.this.pairs.get(selectedIndex);
                    }
                }
                CreateFileDialog.this.validateOkAction();
            }
        });
        this.myKindCombo.registerUpDownHint(this.myNameField);
    }
    
    protected void reloadExtensions() {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     0: aload_0        
        //     1: getfield        com/jetbrains/cidr/lang/actions/newFile/OCNewFileWithSelectedExtensionAction$CreateFileDialog.myFileExtensionPair:Lcom/jetbrains/cidr/lang/settings/OCCodeStyleSettings$FileExtensionPair;
        //     4: astore_1       
        //     5: aload_0        
        //     6: getfield        com/jetbrains/cidr/lang/actions/newFile/OCNewFileWithSelectedExtensionAction$CreateFileDialog.myKindCombo:Lcom/intellij/ide/actions/TemplateKindCombo;
        //     9: invokevirtual   com/intellij/ide/actions/TemplateKindCombo.clear:()V
        //    12: invokestatic    com/intellij/util/containers/ContainerUtil.newHashSet:()Ljava/util/HashSet;
        //    15: astore_2       
        //    16: invokestatic    com/intellij/util/containers/ContainerUtil.newArrayList:()Ljava/util/ArrayList;
        //    19: astore_3       
        //    20: aload_0        
        //    21: getfield        com/jetbrains/cidr/lang/actions/newFile/OCNewFileWithSelectedExtensionAction$CreateFileDialog.this$0:Lcom/jetbrains/cidr/lang/actions/newFile/OCNewFileWithSelectedExtensionAction;
        //    24: aload_0        
        //    25: getfield        com/jetbrains/cidr/lang/actions/newFile/OCNewFileWithSelectedExtensionAction$CreateFileDialog.this$0:Lcom/jetbrains/cidr/lang/actions/newFile/OCNewFileWithSelectedExtensionAction;
        //    28: getfield        com/jetbrains/cidr/lang/actions/newFile/OCNewFileWithSelectedExtensionAction.myState:Lcom/jetbrains/cidr/lang/actions/newFile/OCNewFileActionBase$DisposableState;
        //    31: getfield        com/jetbrains/cidr/lang/actions/newFile/OCNewFileActionBase$DisposableState.project:Lcom/intellij/openapi/project/Project;
        //    34: invokevirtual   com/jetbrains/cidr/lang/actions/newFile/OCNewFileWithSelectedExtensionAction.getExtensions:(Lcom/intellij/openapi/project/Project;)Lcom/intellij/util/containers/ImmutableList;
        //    37: invokevirtual   com/intellij/util/containers/ImmutableList.iterator:()Ljava/util/Iterator;
        //    40: astore          4
        //    42: aload           4
        //    44: invokeinterface java/util/Iterator.hasNext:()Z
        //    49: ifeq            114
        //    52: aload           4
        //    54: invokeinterface java/util/Iterator.next:()Ljava/lang/Object;
        //    59: checkcast       Lcom/jetbrains/cidr/lang/settings/OCCodeStyleSettings$FileExtensionPair;
        //    62: astore          5
        //    64: aload_0        
        //    65: aload           5
        //    67: invokevirtual   com/jetbrains/cidr/lang/actions/newFile/OCNewFileWithSelectedExtensionAction$CreateFileDialog.getPresentableName:(Lcom/jetbrains/cidr/lang/settings/OCCodeStyleSettings$FileExtensionPair;)Ljava/lang/String;
        //    70: astore          6
        //    72: aload_2        
        //    73: aload           6
        //    75: invokeinterface java/util/Set.add:(Ljava/lang/Object;)Z
        //    80: ifeq            111
        //    83: aload_0        
        //    84: getfield        com/jetbrains/cidr/lang/actions/newFile/OCNewFileWithSelectedExtensionAction$CreateFileDialog.myKindCombo:Lcom/intellij/ide/actions/TemplateKindCombo;
        //    87: aload           6
        //    89: aconst_null    
        //    90: aload           6
        //    92: invokevirtual   com/intellij/ide/actions/TemplateKindCombo.addItem:(Ljava/lang/String;Ljavax/swing/Icon;Ljava/lang/String;)V
        //    95: aload_3        
        //    96: aload           5
        //    98: invokeinterface java/util/List.add:(Ljava/lang/Object;)Z
        //   103: pop            
        //   104: goto            111
        //   107: invokestatic    com/jetbrains/cidr/lang/actions/newFile/OCNewFileWithSelectedExtensionAction$CreateFileDialog.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   110: athrow         
        //   111: goto            42
        //   114: aload_3        
        //   115: invokeinterface java/util/List.isEmpty:()Z
        //   120: ifne            205
        //   123: aload_0        
        //   124: aload_3        
        //   125: invokestatic    com/intellij/util/containers/ContainerUtil.immutableList:(Ljava/util/List;)Lcom/intellij/util/containers/ImmutableList;
        //   128: putfield        com/jetbrains/cidr/lang/actions/newFile/OCNewFileWithSelectedExtensionAction$CreateFileDialog.pairs:Lcom/intellij/util/containers/ImmutableList;
        //   131: aload_1        
        //   132: ifnull          175
        //   135: goto            142
        //   138: invokestatic    com/jetbrains/cidr/lang/actions/newFile/OCNewFileWithSelectedExtensionAction$CreateFileDialog.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   141: athrow         
        //   142: aload_2        
        //   143: aload_0        
        //   144: aload_1        
        //   145: invokevirtual   com/jetbrains/cidr/lang/actions/newFile/OCNewFileWithSelectedExtensionAction$CreateFileDialog.getPresentableName:(Lcom/jetbrains/cidr/lang/settings/OCCodeStyleSettings$FileExtensionPair;)Ljava/lang/String;
        //   148: invokeinterface java/util/Set.contains:(Ljava/lang/Object;)Z
        //   153: ifeq            175
        //   156: goto            163
        //   159: invokestatic    com/jetbrains/cidr/lang/actions/newFile/OCNewFileWithSelectedExtensionAction$CreateFileDialog.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   162: athrow         
        //   163: aload_0        
        //   164: aload_1        
        //   165: putfield        com/jetbrains/cidr/lang/actions/newFile/OCNewFileWithSelectedExtensionAction$CreateFileDialog.myFileExtensionPair:Lcom/jetbrains/cidr/lang/settings/OCCodeStyleSettings$FileExtensionPair;
        //   168: goto            190
        //   171: invokestatic    com/jetbrains/cidr/lang/actions/newFile/OCNewFileWithSelectedExtensionAction$CreateFileDialog.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   174: athrow         
        //   175: aload_0        
        //   176: aload_0        
        //   177: getfield        com/jetbrains/cidr/lang/actions/newFile/OCNewFileWithSelectedExtensionAction$CreateFileDialog.pairs:Lcom/intellij/util/containers/ImmutableList;
        //   180: iconst_0       
        //   181: invokevirtual   com/intellij/util/containers/ImmutableList.get:(I)Ljava/lang/Object;
        //   184: checkcast       Lcom/jetbrains/cidr/lang/settings/OCCodeStyleSettings$FileExtensionPair;
        //   187: putfield        com/jetbrains/cidr/lang/actions/newFile/OCNewFileWithSelectedExtensionAction$CreateFileDialog.myFileExtensionPair:Lcom/jetbrains/cidr/lang/settings/OCCodeStyleSettings$FileExtensionPair;
        //   190: aload_0        
        //   191: getfield        com/jetbrains/cidr/lang/actions/newFile/OCNewFileWithSelectedExtensionAction$CreateFileDialog.myKindCombo:Lcom/intellij/ide/actions/TemplateKindCombo;
        //   194: aload_0        
        //   195: aload_0        
        //   196: getfield        com/jetbrains/cidr/lang/actions/newFile/OCNewFileWithSelectedExtensionAction$CreateFileDialog.myFileExtensionPair:Lcom/jetbrains/cidr/lang/settings/OCCodeStyleSettings$FileExtensionPair;
        //   199: invokevirtual   com/jetbrains/cidr/lang/actions/newFile/OCNewFileWithSelectedExtensionAction$CreateFileDialog.getPresentableName:(Lcom/jetbrains/cidr/lang/settings/OCCodeStyleSettings$FileExtensionPair;)Ljava/lang/String;
        //   202: invokevirtual   com/intellij/ide/actions/TemplateKindCombo.setSelectedName:(Ljava/lang/String;)V
        //   205: return         
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                                
        //  -----  -----  -----  -----  ------------------------------------
        //  72     104    107    111    Ljava/lang/IllegalArgumentException;
        //  114    135    138    142    Ljava/lang/IllegalArgumentException;
        //  123    156    159    163    Ljava/lang/IllegalArgumentException;
        //  142    171    171    175    Ljava/lang/IllegalArgumentException;
        // 
        // The error that occurred was:
        // 
        // java.lang.IllegalStateException: Expression is linked from several locations: Label_0142:
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
    
    @Nullable
    @Override
    protected String collectOkActionErrors() {
        try {
            if (this.myKindCombo.getComboBox().getItemCount() == 0) {
                return OCBundle.message("fileExtensions.noCompatibleTypes", new Object[0]);
            }
        }
        catch (IllegalArgumentException ex) {
            throw b(ex);
        }
        return super.collectOkActionErrors();
    }
    
    @NotNull
    protected String getPresentableName(@NotNull final OCCodeStyleSettings.FileExtensionPair fileExtensionPair) {
        try {
            if (fileExtensionPair == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "pair", "com/jetbrains/cidr/lang/actions/newFile/OCNewFileWithSelectedExtensionAction$CreateFileDialog", "getPresentableName"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw b(ex);
        }
        String string;
        try {
            string = fileExtensionPair.toString();
            if (string == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/actions/newFile/OCNewFileWithSelectedExtensionAction$CreateFileDialog", "getPresentableName"));
            }
        }
        catch (IllegalArgumentException ex2) {
            throw b(ex2);
        }
        return string;
    }
    
    @NotNull
    private InplaceButton a() {
        InplaceButton inplaceButton;
        try {
            inplaceButton = new InplaceButton(OCBundle.message("fileExtensions.editFileTypesTooltip", new Object[0]), AllIcons.General.Settings, (ActionListener)new ActionListener() {
                @Override
                public void actionPerformed(final ActionEvent actionEvent) {
                    final Configurable find = new ConfigurableVisitor.ByID("preferences.sourceCode." + OCLanguage.getInstance().getDisplayName()).find(ShowSettingsUtilImpl.getConfigurableGroups(OCNewFileWithSelectedExtensionAction.this.myState.project, true));
                    OCLog.LOG.assertTrue(find != null);
                    final CodeStyleMainPanel codeStyleMainPanel;
                    final NewCodeStyleSettingsPanel[] array;
                    int length;
                    int i;
                    final TabbedLanguageCodeStylePanel tabbedLanguageCodeStylePanel;
                    ShowSettingsUtil.getInstance().editConfigurable(OCNewFileWithSelectedExtensionAction.this.myState.project, find, () -> {
                        codeStyleMainPanel = (CodeStyleMainPanel)find.createComponent();
                        OCLog.LOG.assertTrue(codeStyleMainPanel != null);
                        codeStyleMainPanel.getPanels();
                        for (length = array.length; i < length; ++i) {
                            array[i].getSelectedPanel();
                            OCLog.LOG.assertTrue(tabbedLanguageCodeStylePanel != null);
                            tabbedLanguageCodeStylePanel.changeTab(OCBundle.message("fileExtensions.tabName", new Object[0]));
                        }
                    });
                }
            });
            if (inplaceButton == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/actions/newFile/OCNewFileWithSelectedExtensionAction$CreateFileDialog", "createPreferencesButton"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw b(ex);
        }
        return inplaceButton;
    }
    
    private static IllegalArgumentException b(final IllegalArgumentException ex) {
        return ex;
    }
}
