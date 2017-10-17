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
import com.intellij.ide.actions.TemplateKindCombo;
import com.intellij.openapi.vfs.StandardFileSystems;
import com.intellij.openapi.util.io.FileUtilRt;
import com.intellij.openapi.util.Couple;
import com.jetbrains.cidr.lang.psi.OCFile;
import java.util.Iterator;
import com.jetbrains.cidr.lang.OCLanguageKind;
import com.intellij.psi.codeStyle.CodeStyleSettingsManager;
import com.jetbrains.cidr.lang.settings.OCCodeStyleSettings;
import com.intellij.util.containers.ImmutableList;
import org.jetbrains.annotations.NotNull;
import com.intellij.openapi.project.Project;
import javax.swing.Icon;
import org.jetbrains.annotations.Nullable;
import com.intellij.openapi.fileTypes.FileType;

public abstract class OCNewFileWithSelectedExtensionAction<T extends CreateFileDialog> extends OCNewFileActionBase<T>
{
    private static final String DEFAULT_H_EXT = "h";
    private static final String NEW_CPP_HELP_TOPIC_ID = "New_CPP_File_Dialog";
    
    protected OCNewFileWithSelectedExtensionAction(@Nullable final FileType fileType, final String s, final String s2, final Icon icon) {
        super(fileType, s, s2, icon);
    }
    
    @NotNull
    protected ImmutableList<OCCodeStyleSettings.FileExtensionPair> getExtensions(@NotNull final Project project) {
        try {
            if (project == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "project", "com/jetbrains/cidr/lang/actions/newFile/OCNewFileWithSelectedExtensionAction", "getExtensions"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        final OCCodeStyleSettings ocCodeStyleSettings = (OCCodeStyleSettings)CodeStyleSettingsManager.getInstance(project).getCurrentSettings().getCustomSettings((Class)OCCodeStyleSettings.class);
        ImmutableList<OCCodeStyleSettings.FileExtensionPair> file_EXTENSION_PAIRS_ORDERED;
        try {
            file_EXTENSION_PAIRS_ORDERED = ocCodeStyleSettings.FILE_EXTENSION_PAIRS_ORDERED;
            if (file_EXTENSION_PAIRS_ORDERED == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/actions/newFile/OCNewFileWithSelectedExtensionAction", "getExtensions"));
            }
        }
        catch (IllegalArgumentException ex2) {
            throw a(ex2);
        }
        return file_EXTENSION_PAIRS_ORDERED;
    }
    
    @NotNull
    private String a(@NotNull final Project p0, @NotNull final String p1, @NotNull final OCLanguageKind p2) {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     0: aload_1        
        //     1: ifnonnull       44
        //     4: new             Ljava/lang/IllegalArgumentException;
        //     7: dup            
        //     8: ldc             "Argument for @NotNull parameter '%s' of %s.%s must not be null"
        //    10: ldc             3
        //    12: anewarray       Ljava/lang/Object;
        //    15: dup            
        //    16: ldc             0
        //    18: ldc             "project"
        //    20: aastore        
        //    21: dup            
        //    22: ldc             1
        //    24: ldc             "com/jetbrains/cidr/lang/actions/newFile/OCNewFileWithSelectedExtensionAction"
        //    26: aastore        
        //    27: dup            
        //    28: ldc             2
        //    30: ldc             "findSourceExt"
        //    32: aastore        
        //    33: invokestatic    java/lang/String.format:(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
        //    36: invokespecial   java/lang/IllegalArgumentException.<init>:(Ljava/lang/String;)V
        //    39: athrow         
        //    40: invokestatic    com/jetbrains/cidr/lang/actions/newFile/OCNewFileWithSelectedExtensionAction.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //    43: athrow         
        //    44: aload_2        
        //    45: ifnonnull       88
        //    48: new             Ljava/lang/IllegalArgumentException;
        //    51: dup            
        //    52: ldc             "Argument for @NotNull parameter '%s' of %s.%s must not be null"
        //    54: ldc             3
        //    56: anewarray       Ljava/lang/Object;
        //    59: dup            
        //    60: ldc             0
        //    62: ldc             "headerExt"
        //    64: aastore        
        //    65: dup            
        //    66: ldc             1
        //    68: ldc             "com/jetbrains/cidr/lang/actions/newFile/OCNewFileWithSelectedExtensionAction"
        //    70: aastore        
        //    71: dup            
        //    72: ldc             2
        //    74: ldc             "findSourceExt"
        //    76: aastore        
        //    77: invokestatic    java/lang/String.format:(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
        //    80: invokespecial   java/lang/IllegalArgumentException.<init>:(Ljava/lang/String;)V
        //    83: athrow         
        //    84: invokestatic    com/jetbrains/cidr/lang/actions/newFile/OCNewFileWithSelectedExtensionAction.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //    87: athrow         
        //    88: aload_3        
        //    89: ifnonnull       132
        //    92: new             Ljava/lang/IllegalArgumentException;
        //    95: dup            
        //    96: ldc             "Argument for @NotNull parameter '%s' of %s.%s must not be null"
        //    98: ldc             3
        //   100: anewarray       Ljava/lang/Object;
        //   103: dup            
        //   104: ldc             0
        //   106: ldc             "headerKind"
        //   108: aastore        
        //   109: dup            
        //   110: ldc             1
        //   112: ldc             "com/jetbrains/cidr/lang/actions/newFile/OCNewFileWithSelectedExtensionAction"
        //   114: aastore        
        //   115: dup            
        //   116: ldc             2
        //   118: ldc             "findSourceExt"
        //   120: aastore        
        //   121: invokestatic    java/lang/String.format:(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
        //   124: invokespecial   java/lang/IllegalArgumentException.<init>:(Ljava/lang/String;)V
        //   127: athrow         
        //   128: invokestatic    com/jetbrains/cidr/lang/actions/newFile/OCNewFileWithSelectedExtensionAction.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   131: athrow         
        //   132: aload_0        
        //   133: aload_1        
        //   134: invokevirtual   com/jetbrains/cidr/lang/actions/newFile/OCNewFileWithSelectedExtensionAction.getExtensions:(Lcom/intellij/openapi/project/Project;)Lcom/intellij/util/containers/ImmutableList;
        //   137: invokevirtual   com/intellij/util/containers/ImmutableList.iterator:()Ljava/util/Iterator;
        //   140: astore          4
        //   142: aload           4
        //   144: invokeinterface java/util/Iterator.hasNext:()Z
        //   149: ifeq            287
        //   152: aload           4
        //   154: invokeinterface java/util/Iterator.next:()Ljava/lang/Object;
        //   159: checkcast       Lcom/jetbrains/cidr/lang/settings/OCCodeStyleSettings$FileExtensionPair;
        //   162: astore          5
        //   164: aload           5
        //   166: getfield        com/jetbrains/cidr/lang/settings/OCCodeStyleSettings$FileExtensionPair.myHeaderExt:Ljava/lang/String;
        //   169: aload_2        
        //   170: invokevirtual   java/lang/String.equals:(Ljava/lang/Object;)Z
        //   173: ifeq            284
        //   176: aload_1        
        //   177: new             Ljava/lang/StringBuilder;
        //   180: dup            
        //   181: invokespecial   java/lang/StringBuilder.<init>:()V
        //   184: ldc             "."
        //   186: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   189: aload           5
        //   191: getfield        com/jetbrains/cidr/lang/settings/OCCodeStyleSettings$FileExtensionPair.mySourceExt:Ljava/lang/String;
        //   194: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   197: invokevirtual   java/lang/StringBuilder.toString:()Ljava/lang/String;
        //   200: invokestatic    com/jetbrains/cidr/lang/workspace/OCLanguageKindCalculator.tryFileExtension:(Lcom/intellij/openapi/project/Project;Ljava/lang/String;)Lcom/jetbrains/cidr/lang/OCLanguageKind;
        //   203: astore          6
        //   205: aload           6
        //   207: ifnull          284
        //   210: aload_3        
        //   211: invokeinterface com/jetbrains/cidr/lang/OCLanguageKind.isCpp:()Z
        //   216: aload           6
        //   218: invokeinterface com/jetbrains/cidr/lang/OCLanguageKind.isCpp:()Z
        //   223: if_icmpne       284
        //   226: goto            233
        //   229: invokestatic    com/jetbrains/cidr/lang/actions/newFile/OCNewFileWithSelectedExtensionAction.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   232: athrow         
        //   233: aload           5
        //   235: getfield        com/jetbrains/cidr/lang/settings/OCCodeStyleSettings$FileExtensionPair.mySourceExt:Ljava/lang/String;
        //   238: dup            
        //   239: ifnonnull       283
        //   242: goto            249
        //   245: invokestatic    com/jetbrains/cidr/lang/actions/newFile/OCNewFileWithSelectedExtensionAction.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   248: athrow         
        //   249: new             Ljava/lang/IllegalStateException;
        //   252: dup            
        //   253: ldc             "@NotNull method %s.%s must not return null"
        //   255: ldc             2
        //   257: anewarray       Ljava/lang/Object;
        //   260: dup            
        //   261: ldc             0
        //   263: ldc             "com/jetbrains/cidr/lang/actions/newFile/OCNewFileWithSelectedExtensionAction"
        //   265: aastore        
        //   266: dup            
        //   267: ldc             1
        //   269: ldc             "findSourceExt"
        //   271: aastore        
        //   272: invokestatic    java/lang/String.format:(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
        //   275: invokespecial   java/lang/IllegalStateException.<init>:(Ljava/lang/String;)V
        //   278: athrow         
        //   279: invokestatic    com/jetbrains/cidr/lang/actions/newFile/OCNewFileWithSelectedExtensionAction.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   282: athrow         
        //   283: areturn        
        //   284: goto            142
        //   287: aload_3        
        //   288: invokeinterface com/jetbrains/cidr/lang/OCLanguageKind.getDefaultSourceExtension:()Ljava/lang/String;
        //   293: dup            
        //   294: ifnonnull       331
        //   297: new             Ljava/lang/IllegalStateException;
        //   300: dup            
        //   301: ldc             "@NotNull method %s.%s must not return null"
        //   303: ldc             2
        //   305: anewarray       Ljava/lang/Object;
        //   308: dup            
        //   309: ldc             0
        //   311: ldc             "com/jetbrains/cidr/lang/actions/newFile/OCNewFileWithSelectedExtensionAction"
        //   313: aastore        
        //   314: dup            
        //   315: ldc             1
        //   317: ldc             "findSourceExt"
        //   319: aastore        
        //   320: invokestatic    java/lang/String.format:(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
        //   323: invokespecial   java/lang/IllegalStateException.<init>:(Ljava/lang/String;)V
        //   326: athrow         
        //   327: invokestatic    com/jetbrains/cidr/lang/actions/newFile/OCNewFileWithSelectedExtensionAction.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   330: athrow         
        //   331: areturn        
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                                
        //  -----  -----  -----  -----  ------------------------------------
        //  0      40     40     44     Ljava/lang/IllegalArgumentException;
        //  44     84     84     88     Ljava/lang/IllegalArgumentException;
        //  88     128    128    132    Ljava/lang/IllegalArgumentException;
        //  205    226    229    233    Ljava/lang/IllegalArgumentException;
        //  210    242    245    249    Ljava/lang/IllegalArgumentException;
        //  233    279    279    283    Ljava/lang/IllegalArgumentException;
        //  287    327    327    331    Ljava/lang/IllegalArgumentException;
        // 
        // The error that occurred was:
        // 
        // java.lang.IllegalStateException: Expression is linked from several locations: Label_0233:
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
    
    @NotNull
    private String a(@NotNull final Project project, @NotNull final String s) {
        try {
            if (project == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "project", "com/jetbrains/cidr/lang/actions/newFile/OCNewFileWithSelectedExtensionAction", "findHeaderExt"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        try {
            if (s == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "sourceExt", "com/jetbrains/cidr/lang/actions/newFile/OCNewFileWithSelectedExtensionAction", "findHeaderExt"));
            }
        }
        catch (IllegalArgumentException ex2) {
            throw a(ex2);
        }
        for (final OCCodeStyleSettings.FileExtensionPair fileExtensionPair : this.getExtensions(project)) {
            String s2 = null;
            Label_0145: {
                try {
                    if (!fileExtensionPair.mySourceExt.equals(s)) {
                        continue;
                    }
                    final OCCodeStyleSettings.FileExtensionPair fileExtensionPair2 = fileExtensionPair;
                    s2 = fileExtensionPair2.myHeaderExt;
                    if (s2 == null) {
                        break Label_0145;
                    }
                    return s2;
                }
                catch (IllegalArgumentException ex3) {
                    throw a(ex3);
                }
                try {
                    final OCCodeStyleSettings.FileExtensionPair fileExtensionPair2 = fileExtensionPair;
                    s2 = fileExtensionPair2.myHeaderExt;
                    if (s2 == null) {
                        throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/actions/newFile/OCNewFileWithSelectedExtensionAction", "findHeaderExt"));
                    }
                }
                catch (IllegalArgumentException ex4) {
                    throw a(ex4);
                }
            }
            return s2;
        }
        String s3;
        try {
            s3 = "h";
            if (s3 == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/actions/newFile/OCNewFileWithSelectedExtensionAction", "findHeaderExt"));
            }
        }
        catch (IllegalArgumentException ex5) {
            throw a(ex5);
        }
        return s3;
    }
    
    @NotNull
    protected Couple<String> determineExtensions(@NotNull final OCFile ocFile) {
        try {
            if (ocFile == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "file", "com/jetbrains/cidr/lang/actions/newFile/OCNewFileWithSelectedExtensionAction", "determineExtensions"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        Couple<String> determineExtensions;
        try {
            determineExtensions = this.determineExtensions(ocFile, null, null);
            if (determineExtensions == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/actions/newFile/OCNewFileWithSelectedExtensionAction", "determineExtensions"));
            }
        }
        catch (IllegalArgumentException ex2) {
            throw a(ex2);
        }
        return determineExtensions;
    }
    
    @NotNull
    protected Couple<String> determineExtensions(@NotNull final OCFile ocFile, @Nullable String s, @Nullable String s2) {
        try {
            if (ocFile == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "sampleFile", "com/jetbrains/cidr/lang/actions/newFile/OCNewFileWithSelectedExtensionAction", "determineExtensions"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        final Project project = ocFile.getProject();
        Label_0130: {
            Label_0120: {
                Label_0067: {
                    try {
                        if (s != null) {
                            break Label_0130;
                        }
                        final String s3 = s2;
                        if (s3 == null) {
                            break Label_0067;
                        }
                        break Label_0130;
                    }
                    catch (IllegalArgumentException ex2) {
                        throw a(ex2);
                    }
                    try {
                        final String s3 = s2;
                        if (s3 != null) {
                            break Label_0130;
                        }
                        if (!ocFile.isHeader()) {
                            break Label_0120;
                        }
                    }
                    catch (IllegalArgumentException ex3) {
                        throw a(ex3);
                    }
                }
                s2 = FileUtilRt.getExtension(ocFile.getName());
                final OCFile associatedFile = ocFile.getAssociatedFile();
                if (associatedFile != null) {
                    s = FileUtilRt.getExtension(associatedFile.getName());
                }
                break Label_0130;
            }
            s = FileUtilRt.getExtension(ocFile.getName());
        }
        if (s == null) {
            s = this.a(project, s2, ocFile.getKind());
        }
        else if (s2 == null) {
            s2 = this.a(project, s);
        }
        Couple of;
        try {
            of = Couple.of((Object)s, (Object)s2);
            if (of == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/actions/newFile/OCNewFileWithSelectedExtensionAction", "determineExtensions"));
            }
        }
        catch (IllegalArgumentException ex4) {
            throw a(ex4);
        }
        return (Couple<String>)of;
    }
    
    @Override
    protected boolean isValidName(final String s) {
        return StandardFileSystems.local().isValidName(s);
    }
    
    @NotNull
    @Override
    protected T createDialog(@NotNull final String s) {
        try {
            if (s == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "defaultName", "com/jetbrains/cidr/lang/actions/newFile/OCNewFileWithSelectedExtensionAction", "createDialog"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        CreateFileDialog createFileDialog;
        try {
            createFileDialog = new CreateFileDialog(s);
            if (createFileDialog == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/actions/newFile/OCNewFileWithSelectedExtensionAction", "createDialog"));
            }
        }
        catch (IllegalArgumentException ex2) {
            throw a(ex2);
        }
        return (T)createFileDialog;
    }
    
    private static IllegalArgumentException a(final IllegalArgumentException ex) {
        return ex;
    }
    
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
}
