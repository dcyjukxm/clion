// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.lang.actions.newFile;

import java.io.ObjectInputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.awt.event.FocusEvent;
import com.intellij.openapi.fileChooser.FileTextField;
import com.intellij.openapi.fileChooser.FileChooserDescriptor;
import com.intellij.openapi.fileChooser.FileChooserFactory;
import com.intellij.openapi.fileChooser.FileChooserDescriptorFactory;
import javax.accessibility.Accessible;
import javax.swing.JLabel;
import com.intellij.icons.AllIcons;
import java.awt.LayoutManager;
import java.awt.BorderLayout;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import java.awt.event.FocusListener;
import java.awt.Dimension;
import com.intellij.util.ui.FormBuilder;
import java.awt.Component;
import com.intellij.openapi.wm.IdeFocusManager;
import javax.swing.JPanel;
import javax.swing.JComponent;
import com.intellij.openapi.ui.TextFieldWithBrowseButton;
import javax.swing.JTextField;
import com.intellij.openapi.util.Condition;
import java.util.Arrays;
import com.intellij.psi.PsiFileFactory;
import java.util.Locale;
import java.util.UUID;
import com.intellij.util.containers.ContainerUtil;
import com.intellij.openapi.application.Application;
import com.intellij.ide.fileTemplates.FileTemplate;
import com.intellij.openapi.ui.Messages;
import com.jetbrains.cidr.lang.OCBundle;
import java.util.Collection;
import com.intellij.util.ArrayUtil;
import com.intellij.psi.PsiManager;
import com.intellij.openapi.vfs.VfsUtil;
import com.intellij.openapi.vfs.LocalFileSystem;
import com.intellij.openapi.command.CommandProcessor;
import com.intellij.openapi.application.Result;
import com.intellij.openapi.command.UndoConfirmationPolicy;
import com.intellij.openapi.command.WriteCommandAction;
import com.jetbrains.cidr.lang.formatting.OCFormattingModelBuilder;
import com.intellij.openapi.application.ApplicationManager;
import com.intellij.openapi.util.text.StringUtil;
import java.util.Properties;
import com.intellij.ide.fileTemplates.FileTemplateManager;
import com.intellij.openapi.diagnostic.Logger;
import com.jetbrains.cidr.lang.OCLog;
import com.intellij.ide.util.EditorHelper;
import java.util.Iterator;
import com.intellij.openapi.vfs.VirtualFile;
import com.intellij.openapi.ui.DialogWrapper;
import com.intellij.psi.PsiDirectory;
import com.intellij.openapi.project.Project;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiFile;
import com.intellij.ide.IdeView;
import com.intellij.openapi.actionSystem.AnActionEvent;
import com.intellij.openapi.actionSystem.DataContext;
import com.intellij.openapi.extensions.ExtensionPointName;
import com.intellij.openapi.extensions.Extensions;
import java.util.ArrayList;
import javax.swing.Icon;
import org.jetbrains.annotations.NotNull;
import java.util.List;
import org.jetbrains.annotations.Nullable;
import com.intellij.openapi.fileTypes.FileType;
import com.intellij.openapi.util.Key;
import com.intellij.ide.actions.CreateInDirectoryActionBase;

public abstract class OCNewFileActionBase<T extends CreateFileDialogBase> extends CreateInDirectoryActionBase
{
    public static final Key<FileFactory> FILE_FACTORY;
    protected static final String HEADER_COMMENTS = "HEADER_COMMENTS";
    protected static final String NAME_TEMPLATE_PROPERTY = "NAME";
    protected static final String FILENAME_TEMPLATE_PROPERTY_1 = "FILE_NAME";
    protected static final String FILENAME_TEMPLATE_PROPERTY_2 = "FILENAME";
    protected static final String HEADER_FILENAME_TEMPLATE_PROPERTY = "HEADER_FILENAME";
    public static final String PROJECT_TEMPLATE_PROPERTY1 = "PROJECT_NAME";
    public static final String PROJECT_TEMPLATE_PROPERTY2 = "PROJECTNAME";
    public static final String USER_NAME_PROPERTY = "USER_NAME";
    public static final String ORGANIZATION_NAME_PROPERTY1 = "ORGANIZATIONNAME";
    public static final String ORGANIZATION_NAME_PROPERTY2 = "ORGANIZATION_NAME";
    protected static final String CLASS_TEMPLATE_NAME = "Objective-C Class.m";
    protected static final String CPP_CLASS_TEMPLATE_NAME = "C++ Class.cc";
    protected static final String HEADER_TEMPLATE_NAME = "Objective-C Header File.h";
    protected static final String CPP_CLASS_HEADER_TEMPLATE_NAME = "C++ Class Header.h";
    protected static final String C_HEADER_TEMPLATE_NAME = "C Header File.h";
    protected static final String C_SOURCE_TEMPLATE_NAME = "C Source File.c";
    protected static final String C_INCLUDE_GUARD_TEMPLATE_NAME = "INCLUDE_GUARD";
    @Nullable
    private final FileType myFileType;
    @NotNull
    private final List<AuxAction> myAuxActions;
    @Nullable
    protected final OCNewFileHelper myHelper;
    protected DisposableState<T> myState;
    protected String mySelectedName;
    
    protected OCNewFileActionBase(@Nullable final FileType myFileType, final String s, final String s2, final Icon icon) {
        super(s, s2, icon);
        this.myAuxActions = new ArrayList<AuxAction>();
        this.myState = null;
        this.myFileType = myFileType;
        final OCNewFileHelperProvider[] array = (OCNewFileHelperProvider[])Extensions.getExtensions((ExtensionPointName)OCNewFileHelperProvider.EP_NAME);
        OCNewFileHelper helper = null;
        Label_0065: {
            try {
                if (array.length == 1) {
                    helper = array[0].createHelper();
                    break Label_0065;
                }
            }
            catch (RuntimeException ex) {
                throw b(ex);
            }
            helper = null;
        }
        this.myHelper = helper;
    }
    
    public static boolean isNewFileActionSupported() {
        try {
            if (((OCNewFileHelperProvider[])Extensions.getExtensions((ExtensionPointName)OCNewFileHelperProvider.EP_NAME)).length == 1) {
                return true;
            }
        }
        catch (RuntimeException ex) {
            throw b(ex);
        }
        return false;
    }
    
    public void addAuxAction(@NotNull final AuxAction auxAction) {
        try {
            if (auxAction == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "action", "com/jetbrains/cidr/lang/actions/newFile/OCNewFileActionBase", "addAuxAction"));
            }
        }
        catch (RuntimeException ex) {
            throw b(ex);
        }
        this.myAuxActions.add(auxAction);
    }
    
    protected boolean isAvailable(final DataContext dataContext) {
        Label_0022: {
            try {
                if (!super.isAvailable(dataContext)) {
                    return false;
                }
                final OCNewFileActionBase ocNewFileActionBase = this;
                final boolean b = ocNewFileActionBase.isAvailable();
                if (b) {
                    break Label_0022;
                }
                return false;
            }
            catch (RuntimeException ex) {
                throw b(ex);
            }
            try {
                final OCNewFileActionBase ocNewFileActionBase = this;
                final boolean b = ocNewFileActionBase.isAvailable();
                if (b) {
                    return true;
                }
            }
            catch (RuntimeException ex2) {
                throw b(ex2);
            }
        }
        return false;
    }
    
    protected boolean isAvailable() {
        try {
            if (this.myHelper != null) {
                return true;
            }
        }
        catch (RuntimeException ex) {
            throw b(ex);
        }
        return false;
    }
    
    public final void actionPerformed(final AnActionEvent p0) {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     0: aload_1        
        //     1: invokevirtual   com/intellij/openapi/actionSystem/AnActionEvent.getDataContext:()Lcom/intellij/openapi/actionSystem/DataContext;
        //     4: astore_2       
        //     5: getstatic       com/intellij/openapi/actionSystem/LangDataKeys.IDE_VIEW:Lcom/intellij/openapi/actionSystem/DataKey;
        //     8: aload_2        
        //     9: invokevirtual   com/intellij/openapi/actionSystem/DataKey.getData:(Lcom/intellij/openapi/actionSystem/DataContext;)Ljava/lang/Object;
        //    12: checkcast       Lcom/intellij/ide/IdeView;
        //    15: astore_3       
        //    16: aload_3        
        //    17: ifnull          68
        //    20: aload_0        
        //    21: invokevirtual   com/jetbrains/cidr/lang/actions/newFile/OCNewFileActionBase.isAvailable:()Z
        //    24: ifeq            68
        //    27: goto            34
        //    30: invokestatic    com/jetbrains/cidr/lang/actions/newFile/OCNewFileActionBase.b:(Ljava/lang/Exception;)Ljava/lang/Exception;
        //    33: athrow         
        //    34: aload_0        
        //    35: getfield        com/jetbrains/cidr/lang/actions/newFile/OCNewFileActionBase.myHelper:Lcom/jetbrains/cidr/lang/actions/newFile/OCNewFileHelper;
        //    38: ifnull          68
        //    41: goto            48
        //    44: invokestatic    com/jetbrains/cidr/lang/actions/newFile/OCNewFileActionBase.b:(Ljava/lang/Exception;)Ljava/lang/Exception;
        //    47: athrow         
        //    48: aload_0        
        //    49: getfield        com/jetbrains/cidr/lang/actions/newFile/OCNewFileActionBase.myHelper:Lcom/jetbrains/cidr/lang/actions/newFile/OCNewFileHelper;
        //    52: aload_2        
        //    53: invokeinterface com/jetbrains/cidr/lang/actions/newFile/OCNewFileHelper.initFromDataContext:(Lcom/intellij/openapi/actionSystem/DataContext;)Z
        //    58: ifne            73
        //    61: goto            68
        //    64: invokestatic    com/jetbrains/cidr/lang/actions/newFile/OCNewFileActionBase.b:(Ljava/lang/Exception;)Ljava/lang/Exception;
        //    67: athrow         
        //    68: return         
        //    69: invokestatic    com/jetbrains/cidr/lang/actions/newFile/OCNewFileActionBase.b:(Ljava/lang/Exception;)Ljava/lang/Exception;
        //    72: athrow         
        //    73: aload_3        
        //    74: invokeinterface com/intellij/ide/IdeView.getDirectories:()[Lcom/intellij/psi/PsiDirectory;
        //    79: astore          4
        //    81: aload_0        
        //    82: new             Lcom/jetbrains/cidr/lang/actions/newFile/OCNewFileActionBase$DisposableState;
        //    85: dup            
        //    86: getstatic       com/intellij/openapi/actionSystem/CommonDataKeys.PSI_FILE:Lcom/intellij/openapi/actionSystem/DataKey;
        //    89: aload_2        
        //    90: invokevirtual   com/intellij/openapi/actionSystem/DataKey.getData:(Lcom/intellij/openapi/actionSystem/DataContext;)Ljava/lang/Object;
        //    93: checkcast       Lcom/intellij/psi/PsiFile;
        //    96: aload_1        
        //    97: invokevirtual   com/intellij/openapi/actionSystem/AnActionEvent.getProject:()Lcom/intellij/openapi/project/Project;
        //   100: aload           4
        //   102: arraylength    
        //   103: iconst_1       
        //   104: if_icmpne       118
        //   107: aload           4
        //   109: iconst_0       
        //   110: aaload         
        //   111: goto            119
        //   114: invokestatic    com/jetbrains/cidr/lang/actions/newFile/OCNewFileActionBase.b:(Ljava/lang/Exception;)Ljava/lang/Exception;
        //   117: athrow         
        //   118: aconst_null    
        //   119: aload_0        
        //   120: aload_0        
        //   121: invokevirtual   com/jetbrains/cidr/lang/actions/newFile/OCNewFileActionBase.getDefaultName:()Ljava/lang/String;
        //   124: invokevirtual   com/jetbrains/cidr/lang/actions/newFile/OCNewFileActionBase.createDialog:(Ljava/lang/String;)Lcom/jetbrains/cidr/lang/actions/newFile/OCNewFileActionBase$CreateFileDialogBase;
        //   127: invokespecial   com/jetbrains/cidr/lang/actions/newFile/OCNewFileActionBase$DisposableState.<init>:(Lcom/intellij/psi/PsiFile;Lcom/intellij/openapi/project/Project;Lcom/intellij/psi/PsiDirectory;Lcom/jetbrains/cidr/lang/actions/newFile/OCNewFileActionBase$CreateFileDialogBase;)V
        //   130: putfield        com/jetbrains/cidr/lang/actions/newFile/OCNewFileActionBase.myState:Lcom/jetbrains/cidr/lang/actions/newFile/OCNewFileActionBase$DisposableState;
        //   133: aload_0        
        //   134: aload_0        
        //   135: getfield        com/jetbrains/cidr/lang/actions/newFile/OCNewFileActionBase.myState:Lcom/jetbrains/cidr/lang/actions/newFile/OCNewFileActionBase$DisposableState;
        //   138: getfield        com/jetbrains/cidr/lang/actions/newFile/OCNewFileActionBase$DisposableState.project:Lcom/intellij/openapi/project/Project;
        //   141: aload_2        
        //   142: invokespecial   com/jetbrains/cidr/lang/actions/newFile/OCNewFileActionBase.a:(Lcom/intellij/openapi/project/Project;Lcom/intellij/openapi/actionSystem/DataContext;)[Lcom/intellij/psi/PsiFile;
        //   145: astore          5
        //   147: aload           5
        //   149: ifnull          166
        //   152: aload_0        
        //   153: aload_3        
        //   154: aload           5
        //   156: invokevirtual   com/jetbrains/cidr/lang/actions/newFile/OCNewFileActionBase.selectResult:(Lcom/intellij/ide/IdeView;[Lcom/intellij/psi/PsiFile;)V
        //   159: goto            166
        //   162: invokestatic    com/jetbrains/cidr/lang/actions/newFile/OCNewFileActionBase.b:(Ljava/lang/Exception;)Ljava/lang/Exception;
        //   165: athrow         
        //   166: aload_0        
        //   167: aconst_null    
        //   168: putfield        com/jetbrains/cidr/lang/actions/newFile/OCNewFileActionBase.myState:Lcom/jetbrains/cidr/lang/actions/newFile/OCNewFileActionBase$DisposableState;
        //   171: goto            184
        //   174: astore          6
        //   176: aload_0        
        //   177: aconst_null    
        //   178: putfield        com/jetbrains/cidr/lang/actions/newFile/OCNewFileActionBase.myState:Lcom/jetbrains/cidr/lang/actions/newFile/OCNewFileActionBase$DisposableState;
        //   181: aload           6
        //   183: athrow         
        //   184: return         
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                        
        //  -----  -----  -----  -----  ----------------------------
        //  147    159    162    166    Ljava/lang/RuntimeException;
        //  81     114    114    118    Ljava/lang/RuntimeException;
        //  48     69     69     73     Ljava/lang/RuntimeException;
        //  34     61     64     68     Ljava/lang/RuntimeException;
        //  20     41     44     48     Ljava/lang/RuntimeException;
        //  16     27     30     34     Ljava/lang/RuntimeException;
        //  73     166    174    184    Any
        //  174    176    174    184    Any
        // 
        // The error that occurred was:
        // 
        // java.lang.IllegalStateException: Expression is linked from several locations: Label_0034:
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
    
    protected void selectResult(@NotNull final IdeView ideView, @NotNull final PsiFile[] array) {
        try {
            if (ideView == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "view", "com/jetbrains/cidr/lang/actions/newFile/OCNewFileActionBase", "selectResult"));
            }
        }
        catch (RuntimeException ex) {
            throw b(ex);
        }
        try {
            if (array == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "createdElements", "com/jetbrains/cidr/lang/actions/newFile/OCNewFileActionBase", "selectResult"));
            }
        }
        catch (RuntimeException ex2) {
            throw b(ex2);
        }
        for (int length = array.length, i = 0; i < length; ++i) {
            ideView.selectElement((PsiElement)array[i]);
        }
    }
    
    @Nullable
    public PsiFile[] performAction(final Project p0, final PsiDirectory p1, @NotNull final PsiFile p2, @Nullable final String p3) {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     0: aload_3        
        //     1: ifnonnull       44
        //     4: new             Ljava/lang/IllegalArgumentException;
        //     7: dup            
        //     8: ldc             "Argument for @NotNull parameter '%s' of %s.%s must not be null"
        //    10: ldc             3
        //    12: anewarray       Ljava/lang/Object;
        //    15: dup            
        //    16: ldc             0
        //    18: ldc             "file"
        //    20: aastore        
        //    21: dup            
        //    22: ldc             1
        //    24: ldc             "com/jetbrains/cidr/lang/actions/newFile/OCNewFileActionBase"
        //    26: aastore        
        //    27: dup            
        //    28: ldc             2
        //    30: ldc             "performAction"
        //    32: aastore        
        //    33: invokestatic    java/lang/String.format:(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
        //    36: invokespecial   java/lang/IllegalArgumentException.<init>:(Ljava/lang/String;)V
        //    39: athrow         
        //    40: invokestatic    com/jetbrains/cidr/lang/actions/newFile/OCNewFileActionBase.b:(Ljava/lang/Exception;)Ljava/lang/Exception;
        //    43: athrow         
        //    44: aload_1        
        //    45: ifnull          93
        //    48: aload_2        
        //    49: ifnull          93
        //    52: goto            59
        //    55: invokestatic    com/jetbrains/cidr/lang/actions/newFile/OCNewFileActionBase.b:(Ljava/lang/Exception;)Ljava/lang/Exception;
        //    58: athrow         
        //    59: aload_0        
        //    60: getfield        com/jetbrains/cidr/lang/actions/newFile/OCNewFileActionBase.myHelper:Lcom/jetbrains/cidr/lang/actions/newFile/OCNewFileHelper;
        //    63: ifnull          93
        //    66: goto            73
        //    69: invokestatic    com/jetbrains/cidr/lang/actions/newFile/OCNewFileActionBase.b:(Ljava/lang/Exception;)Ljava/lang/Exception;
        //    72: athrow         
        //    73: aload_0        
        //    74: getfield        com/jetbrains/cidr/lang/actions/newFile/OCNewFileActionBase.myHelper:Lcom/jetbrains/cidr/lang/actions/newFile/OCNewFileHelper;
        //    77: aload_3        
        //    78: invokeinterface com/jetbrains/cidr/lang/actions/newFile/OCNewFileHelper.initFromFile:(Lcom/intellij/psi/PsiFile;)Z
        //    83: ifne            99
        //    86: goto            93
        //    89: invokestatic    com/jetbrains/cidr/lang/actions/newFile/OCNewFileActionBase.b:(Ljava/lang/Exception;)Ljava/lang/Exception;
        //    92: athrow         
        //    93: aconst_null    
        //    94: areturn        
        //    95: invokestatic    com/jetbrains/cidr/lang/actions/newFile/OCNewFileActionBase.b:(Ljava/lang/Exception;)Ljava/lang/Exception;
        //    98: athrow         
        //    99: aload_0        
        //   100: new             Lcom/jetbrains/cidr/lang/actions/newFile/OCNewFileActionBase$DisposableState;
        //   103: dup            
        //   104: aload_3        
        //   105: aload_1        
        //   106: aload_2        
        //   107: aload_0        
        //   108: aload           4
        //   110: ifnull          122
        //   113: aload           4
        //   115: goto            126
        //   118: invokestatic    com/jetbrains/cidr/lang/actions/newFile/OCNewFileActionBase.b:(Ljava/lang/Exception;)Ljava/lang/Exception;
        //   121: athrow         
        //   122: aload_0        
        //   123: invokevirtual   com/jetbrains/cidr/lang/actions/newFile/OCNewFileActionBase.getDefaultName:()Ljava/lang/String;
        //   126: invokevirtual   com/jetbrains/cidr/lang/actions/newFile/OCNewFileActionBase.createDialog:(Ljava/lang/String;)Lcom/jetbrains/cidr/lang/actions/newFile/OCNewFileActionBase$CreateFileDialogBase;
        //   129: invokespecial   com/jetbrains/cidr/lang/actions/newFile/OCNewFileActionBase$DisposableState.<init>:(Lcom/intellij/psi/PsiFile;Lcom/intellij/openapi/project/Project;Lcom/intellij/psi/PsiDirectory;Lcom/jetbrains/cidr/lang/actions/newFile/OCNewFileActionBase$CreateFileDialogBase;)V
        //   132: putfield        com/jetbrains/cidr/lang/actions/newFile/OCNewFileActionBase.myState:Lcom/jetbrains/cidr/lang/actions/newFile/OCNewFileActionBase$DisposableState;
        //   135: aload_0        
        //   136: aload_1        
        //   137: aconst_null    
        //   138: invokespecial   com/jetbrains/cidr/lang/actions/newFile/OCNewFileActionBase.a:(Lcom/intellij/openapi/project/Project;Lcom/intellij/openapi/actionSystem/DataContext;)[Lcom/intellij/psi/PsiFile;
        //   141: astore          5
        //   143: aload           5
        //   145: ifnull          166
        //   148: aload_0        
        //   149: aload           5
        //   151: invokevirtual   com/jetbrains/cidr/lang/actions/newFile/OCNewFileActionBase.openCreatedFiles:([Lcom/intellij/psi/PsiFile;)V
        //   154: aload           5
        //   156: astore          6
        //   158: aload_0        
        //   159: aconst_null    
        //   160: putfield        com/jetbrains/cidr/lang/actions/newFile/OCNewFileActionBase.myState:Lcom/jetbrains/cidr/lang/actions/newFile/OCNewFileActionBase$DisposableState;
        //   163: aload           6
        //   165: areturn        
        //   166: aconst_null    
        //   167: astore          6
        //   169: aload_0        
        //   170: aconst_null    
        //   171: putfield        com/jetbrains/cidr/lang/actions/newFile/OCNewFileActionBase.myState:Lcom/jetbrains/cidr/lang/actions/newFile/OCNewFileActionBase$DisposableState;
        //   174: aload           6
        //   176: areturn        
        //   177: astore          7
        //   179: aload_0        
        //   180: aconst_null    
        //   181: putfield        com/jetbrains/cidr/lang/actions/newFile/OCNewFileActionBase.myState:Lcom/jetbrains/cidr/lang/actions/newFile/OCNewFileActionBase$DisposableState;
        //   184: aload           7
        //   186: athrow         
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                        
        //  -----  -----  -----  -----  ----------------------------
        //  99     118    118    122    Ljava/lang/RuntimeException;
        //  73     95     95     99     Ljava/lang/RuntimeException;
        //  59     86     89     93     Ljava/lang/RuntimeException;
        //  48     66     69     73     Ljava/lang/RuntimeException;
        //  44     52     55     59     Ljava/lang/RuntimeException;
        //  0      40     40     44     Ljava/lang/RuntimeException;
        //  99     158    177    187    Any
        //  166    169    177    187    Any
        //  177    179    177    187    Any
        // 
        // The error that occurred was:
        // 
        // java.lang.IllegalStateException: Expression is linked from several locations: Label_0059:
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
    public PsiFile[] performActionWithoutDialog(final String s, @NotNull final PsiFile psiFile, final boolean b) {
        try {
            if (psiFile == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "sampleFile", "com/jetbrains/cidr/lang/actions/newFile/OCNewFileActionBase", "performActionWithoutDialog"));
            }
        }
        catch (RuntimeException ex) {
            throw b(ex);
        }
        final VirtualFile virtualFile = psiFile.getVirtualFile();
        try {
            if (virtualFile == null) {
                return null;
            }
        }
        catch (RuntimeException ex2) {
            throw b(ex2);
        }
        String s2 = s;
        int n = 1;
        while (true) {
            boolean b2 = false;
            final Iterator<String> iterator = this.getNewFileNames(s2, psiFile).getNames().iterator();
            while (iterator.hasNext()) {
                if (virtualFile.getParent().findChild((String)iterator.next()) != null) {
                    b2 = true;
                    break;
                }
            }
            try {
                if (!b2) {
                    break;
                }
            }
            catch (RuntimeException ex3) {
                throw b(ex3);
            }
            final int lastIndex = s.lastIndexOf(46);
            String s3 = null;
            Label_0219: {
                try {
                    if (lastIndex != -1) {
                        s3 = s.substring(0, lastIndex) + n + s.substring(lastIndex);
                        break Label_0219;
                    }
                }
                catch (RuntimeException ex4) {
                    throw b(ex4);
                }
                s3 = s + n;
            }
            s2 = s3;
            ++n;
        }
        Label_0244: {
            try {
                if (b) {
                    return this.performAction(psiFile.getProject(), virtualFile.getPath(), s, s2, null, psiFile);
                }
                final String s4 = s2;
                final String s5 = s;
                if (s4 != s5) {
                    break Label_0244;
                }
                return this.performAction(psiFile.getProject(), virtualFile.getPath(), s, s2, null, psiFile);
            }
            catch (RuntimeException ex5) {
                throw b(ex5);
            }
            try {
                final String s4 = s2;
                final String s5 = s;
                if (s4 != s5) {
                    return null;
                }
            }
            catch (RuntimeException ex6) {
                throw b(ex6);
            }
        }
        return this.performAction(psiFile.getProject(), virtualFile.getPath(), s, s2, null, psiFile);
    }
    
    protected void openCreatedFiles(@NotNull final PsiFile[] array) {
        try {
            if (array == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "createdElements", "com/jetbrains/cidr/lang/actions/newFile/OCNewFileActionBase", "openCreatedFiles"));
            }
        }
        catch (RuntimeException ex) {
            throw b(ex);
        }
        for (int length = array.length, i = 0; i < length; ++i) {
            EditorHelper.openInEditor((PsiElement)array[i]);
        }
    }
    
    private PsiFile[] a(final Project project, @Nullable final DataContext dataContext) {
        try {
            Logger log = null;
            boolean b = false;
            Label_0019: {
                try {
                    log = OCLog.LOG;
                    if (this.myHelper != null) {
                        b = true;
                        break Label_0019;
                    }
                }
                catch (RuntimeException ex) {
                    throw b(ex);
                }
                b = false;
            }
            log.assertTrue(b);
            final DialogWrapper dialog = this.myHelper.createDialog(this.myState.dialogPeer, this.myState.selectedDir, dataContext);
            dialog.show();
            if (dialog.getExitCode() == 0) {
                final String selectedName = this.myState.dialogPeer.getSelectedName();
                return this.performAction(project, this.myState.dialogPeer.getSelectedPath(), selectedName, selectedName, dialog, null);
            }
            return null;
        }
        finally {
            this.myAuxActions.clear();
        }
    }
    
    @Nullable
    protected PsiFile[] performAction(final Project project, final String s, final String mySelectedName, final String s2, final DialogWrapper dialogWrapper, @Nullable final PsiFile psiFile) {
        final FileTemplateManager instance = FileTemplateManager.getInstance(project);
        NewFileNames newFileNames = null;
        Label_0034: {
            try {
                if (this.myState != null) {
                    newFileNames = this.getNewFileNames(s2);
                    break Label_0034;
                }
            }
            catch (Exception ex) {
                throw b(ex);
            }
            newFileNames = this.getNewFileNames(s2, psiFile);
        }
        final List<String> names = newFileNames.getNames();
        final PsiFile[] array = new PsiFile[names.size()];
        for (int i = 0; i < names.size(); ++i) {
            final String s3 = names.get(i);
            final String fileTemplate = this.getFileTemplate(s3);
            final FileTemplate loadFileTemplate = this.loadFileTemplate(fileTemplate, project);
            this.mySelectedName = mySelectedName;
            final Properties properties = new Properties(FileTemplateManager.getInstance(project).getDefaultProperties());
            final List wordsIn = StringUtil.getWordsIn(this.mySelectedName);
            try {
                if (!ApplicationManager.getApplication().isUnitTestMode()) {
                    properties.setProperty("HEADER_COMMENTS", "true");
                }
            }
            catch (Exception ex2) {
                throw b(ex2);
            }
            Label_0191: {
                Properties properties2;
                String s4;
                try {
                    properties2 = properties;
                    s4 = "NAME";
                    if (wordsIn.isEmpty()) {
                        final String mySelectedName2 = this.mySelectedName;
                        break Label_0191;
                    }
                }
                catch (Exception ex3) {
                    throw b(ex3);
                }
                final String mySelectedName2 = wordsIn.get(0);
                try {
                    properties2.setProperty(s4, mySelectedName2);
                    properties.setProperty("FILE_NAME", s3);
                    properties.setProperty("FILENAME", s3);
                    if (this.myHelper != null) {
                        this.myHelper.setProperties(dialogWrapper, properties, psiFile, project);
                    }
                }
                catch (Exception ex4) {
                    throw b(ex4);
                }
            }
            this.setAdditionalProperties(properties, s3, psiFile, project);
            properties.setProperty("INCLUDE_GUARD", includeGuard(properties.getProperty("PROJECT_NAME"), s3));
            final FileFactory a = a(project);
            try {
                String text = null;
                Label_0309: {
                    try {
                        if (loadFileTemplate != null) {
                            text = loadFileTemplate.getText(properties);
                            break Label_0309;
                        }
                    }
                    catch (Exception ex5) {
                        throw b(ex5);
                    }
                    text = "";
                }
                final String s5 = text;
                try {
                    if (!s5.isEmpty()) {
                        array[i] = a.createFileFromText(s3, this.myFileType, s5);
                    }
                }
                catch (Exception ex6) {
                    throw b(ex6);
                }
            }
            catch (Exception ex7) {
                try {
                    if (fileTemplate != null) {
                        throw new RuntimeException(String.format("Unable to load template for file template '%s'!", instance.internalTemplateToSubject(fileTemplate)), ex7);
                    }
                }
                catch (Exception ex8) {
                    throw b(ex8);
                }
            }
        }
        boolean b2 = false;
        Label_0429: {
            Label_0420: {
                try {
                    if (this.myHelper == null) {
                        break Label_0420;
                    }
                    final Application application = ApplicationManager.getApplication();
                    final boolean b = application.isUnitTestMode();
                    if (!b) {
                        break Label_0420;
                    }
                    break Label_0420;
                }
                catch (Exception ex9) {
                    throw b(ex9);
                }
                try {
                    final Application application = ApplicationManager.getApplication();
                    final boolean b = application.isUnitTestMode();
                    if (!b) {
                        b2 = true;
                        break Label_0429;
                    }
                }
                catch (Exception ex10) {
                    throw b(ex10);
                }
            }
            b2 = false;
        }
        final boolean b3 = b2;
        final boolean[] array2 = { false };
        try {
            OCFormattingModelBuilder.requestAlwaysCreateFullModel();
            new WriteCommandAction(project, "Creation of new file(s)", new PsiFile[0]) {
                protected UndoConfirmationPolicy getUndoConfirmationPolicy() {
                    return UndoConfirmationPolicy.REQUEST_CONFIRMATION;
                }
                
                protected void run(@NotNull final Result result) throws Throwable {
                    try {
                        if (result == null) {
                            throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "result", "com/jetbrains/cidr/lang/actions/newFile/OCNewFileActionBase$1", "run"));
                        }
                    }
                    catch (Throwable t) {
                        throw a(t);
                    }
                    CommandProcessor.getInstance().markCurrentCommandAsGlobal(project);
                    if (b3) {
                        VirtualFile virtualFile = LocalFileSystem.getInstance().findFileByPath(s);
                        if (virtualFile == null) {
                            virtualFile = VfsUtil.createDirectoryIfMissing(s);
                        }
                        try {
                            if (virtualFile == null) {
                                array2[0] = true;
                                return;
                            }
                        }
                        catch (Throwable t2) {
                            throw a(t2);
                        }
                        PsiManager instance = null;
                        VirtualFile parent = null;
                        Label_0126: {
                            try {
                                instance = PsiManager.getInstance(project);
                                if (virtualFile.isDirectory()) {
                                    parent = virtualFile;
                                    break Label_0126;
                                }
                            }
                            catch (Throwable t3) {
                                throw a(t3);
                            }
                            parent = virtualFile.getParent();
                        }
                        final PsiDirectory directory = instance.findDirectory(parent);
                        Logger log = null;
                        boolean b = false;
                        Label_0146: {
                            try {
                                log = OCLog.LOG;
                                if (directory != null) {
                                    b = true;
                                    break Label_0146;
                                }
                            }
                            catch (Throwable t4) {
                                throw a(t4);
                            }
                            b = false;
                        }
                        log.assertTrue(b);
                        OCNewFileActionBase.this.myHelper.doCreateFiles(project, directory, ArrayUtil.toStringArray((Collection)names), array, dialogWrapper, psiFile);
                    }
                    final Iterator<AuxAction> iterator = (Iterator<AuxAction>)OCNewFileActionBase.this.myAuxActions.iterator();
                    while (iterator.hasNext()) {
                        iterator.next().run(array);
                    }
                    OCNewFileActionBase.this.myAuxActions.clear();
                }
                
                private static Throwable a(final Throwable t) {
                    return t;
                }
            }.execute();
        }
        finally {
            OCFormattingModelBuilder.releaseAlwaysCreateFullModel();
        }
        try {
            if (array2[0]) {
                Messages.showErrorDialog(OCBundle.message("create.directory.failed.message", s), OCBundle.message("create.directory.failed", new Object[0]));
                return null;
            }
        }
        catch (Exception ex11) {
            throw b(ex11);
        }
        return array;
    }
    
    @NotNull
    public static String includeGuard(@Nullable final String s, @NotNull final String s2) {
        try {
            if (s2 == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "filename", "com/jetbrains/cidr/lang/actions/newFile/OCNewFileActionBase", "includeGuard"));
            }
        }
        catch (RuntimeException ex) {
            throw b(ex);
        }
        final ArrayList list = new ArrayList();
        Label_0158: {
            String s9 = null;
            Label_0123: {
                try {
                    ContainerUtil.addIfNotNull((Collection)list, (Object)a(s));
                    ContainerUtil.addIfNotNull((Collection)list, (Object)a(s2));
                    if (!list.isEmpty()) {
                        break Label_0158;
                    }
                    final StringBuilder sb = new StringBuilder();
                    final String s3 = "INC_";
                    final StringBuilder sb2 = sb.append(s3);
                    final UUID uuid = UUID.randomUUID();
                    final String s4 = uuid.toString();
                    final String s5 = "-";
                    final String s6 = "";
                    final String s7 = s4.replace(s5, s6);
                    final Locale locale = Locale.getDefault();
                    final String s8 = s7.toUpperCase(locale);
                    final StringBuilder sb3 = sb2.append(s8);
                    s9 = sb3.toString();
                    if (s9 == null) {
                        break Label_0123;
                    }
                    return s9;
                }
                catch (RuntimeException ex2) {
                    throw b(ex2);
                }
                try {
                    final StringBuilder sb = new StringBuilder();
                    final String s3 = "INC_";
                    final StringBuilder sb2 = sb.append(s3);
                    final UUID uuid = UUID.randomUUID();
                    final String s4 = uuid.toString();
                    final String s5 = "-";
                    final String s6 = "";
                    final String s7 = s4.replace(s5, s6);
                    final Locale locale = Locale.getDefault();
                    final String s8 = s7.toUpperCase(locale);
                    final StringBuilder sb3 = sb2.append(s8);
                    s9 = sb3.toString();
                    if (s9 == null) {
                        throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/actions/newFile/OCNewFileActionBase", "includeGuard"));
                    }
                }
                catch (RuntimeException ex3) {
                    throw b(ex3);
                }
            }
            return s9;
        }
        final String join = StringUtil.join((Collection)list, "_");
        String s10 = null;
        Label_0203: {
            try {
                if (Character.isJavaIdentifierStart(join.charAt(0))) {
                    final String string;
                    s10 = (string = join);
                    break Label_0203;
                }
            }
            catch (RuntimeException ex4) {
                throw b(ex4);
            }
            String string;
            s10 = (string = "INC_" + join);
            try {
                if (string == null) {
                    throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/actions/newFile/OCNewFileActionBase", "includeGuard"));
                }
            }
            catch (RuntimeException ex5) {
                throw b(ex5);
            }
        }
        return s10;
    }
    
    @Nullable
    private static String a(@Nullable final String s) {
        try {
            if (s == null) {
                return null;
            }
        }
        catch (RuntimeException ex) {
            throw b(ex);
        }
        final String upperCase = StringUtil.join((Collection)StringUtil.getWordsIn(s), "_").trim().replaceFirst("^_+", "").replaceFirst("_+$", "").replaceAll("_+", "_").toUpperCase(Locale.getDefault());
        try {
            if (StringUtil.isEmptyOrSpaces(upperCase)) {
                return null;
            }
        }
        catch (RuntimeException ex2) {
            throw b(ex2);
        }
        return upperCase;
    }
    
    protected FileTemplate loadFileTemplate(final String s, final Project project) {
        final FileTemplateManager instance = FileTemplateManager.getInstance(project);
        try {
            if (s != null) {
                return instance.getInternalTemplate(s);
            }
        }
        catch (RuntimeException ex) {
            throw b(ex);
        }
        return null;
    }
    
    private static FileFactory a(final Project project) {
        final FileFactory fileFactory = (FileFactory)project.getUserData((Key)OCNewFileActionBase.FILE_FACTORY);
        try {
            if (fileFactory != null) {
                return fileFactory;
            }
        }
        catch (RuntimeException ex) {
            throw b(ex);
        }
        return new FileFactory() {
            @Override
            public PsiFile createFileFromText(@NotNull final String s, @Nullable final FileType fileType, @NotNull final String s2) {
                try {
                    if (s == null) {
                        throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "fileName", "com/jetbrains/cidr/lang/actions/newFile/OCNewFileActionBase$2", "createFileFromText"));
                    }
                }
                catch (IllegalArgumentException ex) {
                    throw a(ex);
                }
                try {
                    if (s2 == null) {
                        throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "text", "com/jetbrains/cidr/lang/actions/newFile/OCNewFileActionBase$2", "createFileFromText"));
                    }
                }
                catch (IllegalArgumentException ex2) {
                    throw a(ex2);
                }
                final PsiFileFactory instance = PsiFileFactory.getInstance(project);
                try {
                    if (fileType == null) {
                        return instance.createFileFromText(s, s2);
                    }
                }
                catch (IllegalArgumentException ex3) {
                    throw a(ex3);
                }
                return instance.createFileFromText(s, fileType, (CharSequence)s2);
            }
            
            private static IllegalArgumentException a(final IllegalArgumentException ex) {
                return ex;
            }
        };
    }
    
    protected void setAdditionalProperties(final Properties properties, final String s, @Nullable final PsiFile psiFile, final Project project) {
    }
    
    @Nullable
    protected abstract String getFileTemplate(final String p0);
    
    @NotNull
    protected abstract NewFileNames getNewFileNames(final String p0);
    
    @NotNull
    protected NewFileNames getNewFileNames(final String s, final PsiFile psiFile) {
        NewFileNames newFileNames;
        try {
            newFileNames = this.getNewFileNames(s);
            if (newFileNames == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/actions/newFile/OCNewFileActionBase", "getNewFileNames"));
            }
        }
        catch (RuntimeException ex) {
            throw b(ex);
        }
        return newFileNames;
    }
    
    @NotNull
    protected String getDefaultName() {
        String s;
        try {
            s = "";
            if (s == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/actions/newFile/OCNewFileActionBase", "getDefaultName"));
            }
        }
        catch (RuntimeException ex) {
            throw b(ex);
        }
        return s;
    }
    
    @NotNull
    protected String getDefaultClassPrefix() {
        String s = null;
        Label_0025: {
            try {
                if (this.myHelper != null) {
                    final String defaultClassPrefix;
                    s = (defaultClassPrefix = this.myHelper.getDefaultClassPrefix());
                    break Label_0025;
                }
            }
            catch (RuntimeException ex) {
                throw b(ex);
            }
            String defaultClassPrefix;
            s = (defaultClassPrefix = "");
            try {
                if (defaultClassPrefix == null) {
                    throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/actions/newFile/OCNewFileActionBase", "getDefaultClassPrefix"));
                }
            }
            catch (RuntimeException ex2) {
                throw b(ex2);
            }
        }
        return s;
    }
    
    protected boolean isValidName(final String s) {
        return StringUtil.isJavaIdentifier(s);
    }
    
    @NotNull
    protected abstract T createDialog(@NotNull final String p0);
    
    static {
        FILE_FACTORY = Key.create("FILE_FACTORY");
    }
    
    private static Exception b(final Exception ex) {
        return ex;
    }
    
    protected static class DisposableState<T extends CreateFileDialogBase>
    {
        public final PsiFile selectedFile;
        public final Project project;
        public final PsiDirectory selectedDir;
        @NotNull
        public final T dialogPeer;
        
        public DisposableState(final PsiFile selectedFile, final Project project, final PsiDirectory selectedDir, @NotNull final T dialogPeer) {
            if (dialogPeer == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "dialogPeer", "com/jetbrains/cidr/lang/actions/newFile/OCNewFileActionBase$DisposableState", "<init>"));
            }
            this.selectedFile = selectedFile;
            this.project = project;
            this.selectedDir = selectedDir;
            this.dialogPeer = dialogPeer;
        }
    }
    
    public static class NewFileNames
    {
        @Nullable
        private final String mySourceName;
        @Nullable
        private final String myHeaderName;
        static final /* synthetic */ boolean $assertionsDisabled;
        
        public NewFileNames(@Nullable final String mySourceName, @Nullable final String myHeaderName) {
            Label_0039: {
                if (!NewFileNames.$assertionsDisabled) {
                    Label_0025: {
                        try {
                            if (mySourceName != null) {
                                break Label_0039;
                            }
                            final String s = myHeaderName;
                            if (s == null) {
                                break Label_0025;
                            }
                            break Label_0039;
                        }
                        catch (IllegalStateException ex) {
                            throw a(ex);
                        }
                        try {
                            final String s = myHeaderName;
                            if (s == null) {
                                throw new AssertionError((Object)"either source or header name should be specified");
                            }
                        }
                        catch (IllegalStateException ex2) {
                            throw a(ex2);
                        }
                    }
                }
            }
            this.mySourceName = mySourceName;
            this.myHeaderName = myHeaderName;
        }
        
        @Nullable
        public String getSourceName() {
            return this.mySourceName;
        }
        
        @Nullable
        public String getHeaderName() {
            return this.myHeaderName;
        }
        
        @Override
        public String toString() {
            return this.mySourceName + " & " + this.myHeaderName;
        }
        
        @NotNull
        public List<String> getNames() {
            List filter;
            try {
                filter = ContainerUtil.filter((Collection)Arrays.asList(this.mySourceName, this.myHeaderName), Condition.NOT_NULL);
                if (filter == null) {
                    throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/actions/newFile/OCNewFileActionBase$NewFileNames", "getNames"));
                }
            }
            catch (IllegalStateException ex) {
                throw a(ex);
            }
            return (List<String>)filter;
        }
        
        static {
            boolean $assertionsDisabled2 = false;
            Label_0017: {
                try {
                    if (!OCNewFileActionBase.class.desiredAssertionStatus()) {
                        $assertionsDisabled2 = true;
                        break Label_0017;
                    }
                }
                catch (IllegalStateException ex) {
                    throw a(ex);
                }
                $assertionsDisabled2 = false;
            }
            $assertionsDisabled = $assertionsDisabled2;
        }
        
        private static IllegalStateException a(final IllegalStateException ex) {
            return ex;
        }
    }
    
    public class CreateFileDialogBase extends DialogWrapper
    {
        public static final String NAME_SELECTION_START_TAG = "<@!selection!@>";
        @NotNull
        private String myNameFieldTitle;
        private String myDefaultName;
        protected JTextField myNameField;
        protected TextFieldWithBrowseButton myLocationField;
        private boolean myLocationWasManuallyChanged;
        private DialogWrapperFacade myWrapper;
        @Nullable
        private final String myHelpId;
        static final /* synthetic */ boolean $assertionsDisabled;
        final /* synthetic */ OCNewFileActionBase this$0;
        
        public CreateFileDialogBase(final OCNewFileActionBase ocNewFileActionBase, final String s, @Nullable final String s2, final String s3) {
            this(ocNewFileActionBase, s, OCBundle.message("create.field.name", new Object[0]), s2, s3);
        }
        
        public CreateFileDialogBase(@NotNull final String title, final String myNameFieldTitle, @Nullable final String myDefaultName, final String myHelpId) {
            if (myNameFieldTitle == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "nameFieldTitle", "com/jetbrains/cidr/lang/actions/newFile/OCNewFileActionBase$CreateFileDialogBase", "<init>"));
            }
            super(false);
            this.myDefaultName = myDefaultName;
            this.myNameFieldTitle = myNameFieldTitle;
            this.myHelpId = myHelpId;
            this.setTitle(title);
            this.init();
        }
        
        @Nullable
        public String getHelpId() {
            return this.myHelpId;
        }
        
        @Nullable
        public String getSelectedPath() {
            return this.myLocationField.getText();
        }
        
        @NotNull
        public String getSelectedName() {
            String text;
            try {
                text = this.myNameField.getText();
                if (text == null) {
                    throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/actions/newFile/OCNewFileActionBase$CreateFileDialogBase", "getSelectedName"));
                }
            }
            catch (IllegalArgumentException ex) {
                throw a(ex);
            }
            return text;
        }
        
        public PsiDirectory getSelectedDir() {
            return OCNewFileActionBase.this.myState.selectedDir;
        }
        
        public void setSelectedDir(final PsiDirectory psiDirectory) {
            Label_0023: {
                try {
                    if (CreateFileDialogBase.$assertionsDisabled) {
                        break Label_0023;
                    }
                    final CreateFileDialogBase createFileDialogBase = this;
                    final OCNewFileActionBase ocNewFileActionBase = createFileDialogBase.this$0;
                    final DisposableState<T> disposableState = ocNewFileActionBase.myState;
                    if (disposableState == null) {
                        break Label_0023;
                    }
                    break Label_0023;
                }
                catch (IllegalArgumentException ex) {
                    throw a(ex);
                }
                try {
                    final CreateFileDialogBase createFileDialogBase = this;
                    final OCNewFileActionBase ocNewFileActionBase = createFileDialogBase.this$0;
                    final DisposableState<T> disposableState = ocNewFileActionBase.myState;
                    if (disposableState == null) {
                        throw new AssertionError();
                    }
                }
                catch (IllegalArgumentException ex2) {
                    throw a(ex2);
                }
            }
            OCNewFileActionBase.this.myState = new DisposableState<T>(OCNewFileActionBase.this.myState.selectedFile, OCNewFileActionBase.this.myState.project, psiDirectory, OCNewFileActionBase.this.myState.dialogPeer);
        }
        
        public TextFieldWithBrowseButton getLocationField() {
            return this.myLocationField;
        }
        
        public boolean isLocationWasManuallyChanged() {
            return this.myLocationWasManuallyChanged;
        }
        
        public void setLocationWasManuallyChanged(final boolean myLocationWasManuallyChanged) {
            this.myLocationWasManuallyChanged = myLocationWasManuallyChanged;
        }
        
        public final void validateOkAction() {
            final String collectOkActionErrors = this.collectOkActionErrors();
            Label_0024: {
                try {
                    this.setErrorText((String)null);
                    if (collectOkActionErrors == null) {
                        final boolean okActionEnabled = true;
                        break Label_0024;
                    }
                }
                catch (IllegalArgumentException ex) {
                    throw a(ex);
                }
                final boolean okActionEnabled = false;
                try {
                    this.setOKActionEnabled(okActionEnabled);
                    if (!StringUtil.isEmptyOrSpaces(collectOkActionErrors)) {
                        this.setErrorText(collectOkActionErrors);
                    }
                }
                catch (IllegalArgumentException ex2) {
                    throw a(ex2);
                }
            }
        }
        
        @Nullable
        protected String collectOkActionErrors() {
            final String selectedName = this.getSelectedName();
            try {
                if (StringUtil.isEmptyOrSpaces(selectedName)) {
                    return "";
                }
            }
            catch (IllegalArgumentException ex) {
                throw a(ex);
            }
            try {
                if (!this.a(selectedName)) {
                    return OCBundle.message("create.error.invalid.name", selectedName);
                }
            }
            catch (IllegalArgumentException ex2) {
                throw a(ex2);
            }
            try {
                if (StringUtil.isEmpty(this.myLocationField.getText())) {
                    return "";
                }
            }
            catch (IllegalArgumentException ex3) {
                throw a(ex3);
            }
            final String selectedPath = this.getSelectedPath();
            VirtualFile fileByPath = null;
            Label_0089: {
                try {
                    if (selectedPath == null) {
                        fileByPath = null;
                        break Label_0089;
                    }
                }
                catch (IllegalArgumentException ex4) {
                    throw a(ex4);
                }
                fileByPath = LocalFileSystem.getInstance().findFileByPath(selectedPath);
            }
            final VirtualFile virtualFile = fileByPath;
            Label_0108: {
                try {
                    if (virtualFile == null) {
                        break Label_0108;
                    }
                    final VirtualFile virtualFile2 = virtualFile;
                    final boolean b = virtualFile2.isDirectory();
                    if (!b) {
                        break Label_0108;
                    }
                    break Label_0108;
                }
                catch (IllegalArgumentException ex5) {
                    throw a(ex5);
                }
                try {
                    final VirtualFile virtualFile2 = virtualFile;
                    final boolean b = virtualFile2.isDirectory();
                    if (!b) {
                        return OCBundle.message("create.error.missing.target.dir", new Object[0]);
                    }
                }
                catch (IllegalArgumentException ex6) {
                    throw a(ex6);
                }
            }
            final StringBuilder sb = new StringBuilder();
            for (final String s : OCNewFileActionBase.this.getNewFileNames(selectedName).getNames()) {
                final VirtualFile child = virtualFile.findChild(s);
                Label_0199: {
                    try {
                        if (child == null) {
                            continue;
                        }
                        final VirtualFile virtualFile3 = child;
                        final boolean b2 = virtualFile3.isDirectory();
                        if (b2) {
                            break Label_0199;
                        }
                        break Label_0199;
                    }
                    catch (IllegalArgumentException ex7) {
                        throw a(ex7);
                    }
                    try {
                        final VirtualFile virtualFile3 = child;
                        final boolean b2 = virtualFile3.isDirectory();
                        if (b2) {
                            sb.append(OCBundle.message("create.error.dir.exists", s)).append("<br>");
                            continue;
                        }
                    }
                    catch (IllegalArgumentException ex8) {
                        throw a(ex8);
                    }
                }
                sb.append(OCBundle.message("create.error.file.exists", s)).append("<br>");
            }
            try {
                if (sb.length() > 0) {
                    return sb.toString();
                }
            }
            catch (IllegalArgumentException ex9) {
                throw a(ex9);
            }
            return null;
        }
        
        public void setWrapper(final DialogWrapperFacade myWrapper) {
            this.myWrapper = myWrapper;
        }
        
        public void setOKActionEnabled(final boolean b) {
            try {
                if (this.myWrapper == null) {
                    super.setOKActionEnabled(b);
                    return;
                }
            }
            catch (IllegalArgumentException ex) {
                throw a(ex);
            }
            this.myWrapper.setOKEnabled(b);
        }
        
        protected void setErrorText(@Nullable final String errorMessage, @Nullable final JComponent component) {
            try {
                if (this.myWrapper == null) {
                    super.setErrorText(errorMessage, component);
                    return;
                }
            }
            catch (IllegalArgumentException ex) {
                throw a(ex);
            }
            this.myWrapper.setErrorMessage(errorMessage);
        }
        
        protected boolean areControlsConsistent() {
            return true;
        }
        
        @Nullable
        protected JComponent createCenterPanel() {
            return new JPanel();
        }
        
        public JComponent getPreferredFocusedComponent() {
            return this.myNameField;
        }
        
        public boolean checkCanDoOKAction() {
            try {
                if (!this.areControlsConsistent()) {
                    ApplicationManager.getApplication().invokeLater(() -> IdeFocusManager.getGlobalInstance().doWhenFocusSettlesDown(() -> IdeFocusManager.getGlobalInstance().requestFocus((Component)this.myNameField, true)));
                    return false;
                }
            }
            catch (IllegalArgumentException ex) {
                throw a(ex);
            }
            return true;
        }
        
        protected void doOKAction() {
            try {
                if (this.checkCanDoOKAction()) {
                    super.doOKAction();
                }
            }
            catch (IllegalArgumentException ex) {
                throw a(ex);
            }
        }
        
        public void fillGenericControls(final FormBuilder formBuilder) {
            boolean b2 = false;
            Label_0048: {
                Label_0039: {
                    try {
                        this.addNameField(formBuilder);
                        if (OCNewFileActionBase.this.myHelper == null) {
                            break Label_0039;
                        }
                        final CreateFileDialogBase createFileDialogBase = this;
                        final OCNewFileActionBase ocNewFileActionBase = createFileDialogBase.this$0;
                        final OCNewFileHelper ocNewFileHelper = ocNewFileActionBase.myHelper;
                        final boolean b = ocNewFileHelper.canChangeDir();
                        if (b) {
                            break Label_0039;
                        }
                        break Label_0039;
                    }
                    catch (IllegalArgumentException ex) {
                        throw a(ex);
                    }
                    try {
                        final CreateFileDialogBase createFileDialogBase = this;
                        final OCNewFileActionBase ocNewFileActionBase = createFileDialogBase.this$0;
                        final OCNewFileHelper ocNewFileHelper = ocNewFileActionBase.myHelper;
                        final boolean b = ocNewFileHelper.canChangeDir();
                        if (b) {
                            b2 = true;
                            break Label_0048;
                        }
                    }
                    catch (IllegalArgumentException ex2) {
                        throw a(ex2);
                    }
                }
                b2 = false;
            }
            this.addLocationField(formBuilder, b2);
        }
        
        protected void addNameField(final FormBuilder formBuilder) {
            this.addNameField(formBuilder, false);
        }
        
        protected final void addNameField(final FormBuilder formBuilder, final boolean b) {
            int index = this.myDefaultName.indexOf("<@!selection!@>");
            String text;
            if (index < 0) {
                index = 0;
                text = this.myDefaultName;
            }
            else {
                text = this.myDefaultName.substring(0, index) + this.myDefaultName.substring(index + "<@!selection!@>".length());
            }
            (this.myNameField = new JTextField()).setText(text);
            this.myNameField.setMinimumSize(new Dimension(250, this.myNameField.getPreferredSize().height));
            final NameFocusListener nameFocusListener = new NameFocusListener(index);
            this.myNameField.addFocusListener(nameFocusListener);
            this.myNameField.getDocument().addDocumentListener(new DocumentListener() {
                private void a() {
                    CreateFileDialogBase.this.validateOkAction();
                    nameFocusListener.resetSelectionStart();
                }
                
                @Override
                public void insertUpdate(final DocumentEvent documentEvent) {
                    this.a();
                }
                
                @Override
                public void removeUpdate(final DocumentEvent documentEvent) {
                    this.a();
                }
                
                @Override
                public void changedUpdate(final DocumentEvent documentEvent) {
                    this.a();
                }
            });
            Accessible myNameField = this.myNameField;
            if (b) {
                final JPanel panel = new JPanel(new BorderLayout(5, 0));
                panel.add(this.myNameField, "Center");
                final JLabel label = new JLabel(AllIcons.Ide.UpDown);
                label.setToolTipText(OCBundle.message("create.tooltip.arrows.type", new Object[0]));
                panel.add(label, "East");
                myNameField = panel;
            }
            formBuilder.addLabeledComponent(this.myNameFieldTitle, (JComponent)myNameField);
            this.myNameField.setEnabled(this.isNameFieldEnabled());
        }
        
        protected final void addLocationField(final FormBuilder formBuilder, final boolean b) {
            final FileChooserDescriptor singleFolderDescriptor = FileChooserDescriptorFactory.createSingleFolderDescriptor();
            final FileTextField fileTextField = FileChooserFactory.getInstance().createFileTextField(singleFolderDescriptor, this.myDisposable);
            Label_0145: {
                Label_0113: {
                    try {
                        fileTextField.getField().setColumns(25);
                        (this.myLocationField = new TextFieldWithBrowseButton(fileTextField.getField())).addBrowseFolderListener(OCBundle.message("create.folder.dialog.title", new Object[0]), OCBundle.message("create.folder.dialog.description", new Object[0]), OCNewFileActionBase.this.myState.project, singleFolderDescriptor);
                        if (OCNewFileActionBase.this.myState == null) {
                            break Label_0145;
                        }
                        final CreateFileDialogBase createFileDialogBase = this;
                        final OCNewFileActionBase ocNewFileActionBase = createFileDialogBase.this$0;
                        final DisposableState<T> disposableState = ocNewFileActionBase.myState;
                        final PsiDirectory psiDirectory = disposableState.selectedDir;
                        if (psiDirectory != null) {
                            break Label_0113;
                        }
                        break Label_0145;
                    }
                    catch (IllegalArgumentException ex) {
                        throw a(ex);
                    }
                    try {
                        final CreateFileDialogBase createFileDialogBase = this;
                        final OCNewFileActionBase ocNewFileActionBase = createFileDialogBase.this$0;
                        final DisposableState<T> disposableState = ocNewFileActionBase.myState;
                        final PsiDirectory psiDirectory = disposableState.selectedDir;
                        if (psiDirectory != null) {
                            this.myLocationField.setText(OCNewFileActionBase.this.myState.selectedDir.getVirtualFile().getPath());
                        }
                    }
                    catch (IllegalArgumentException ex2) {
                        throw a(ex2);
                    }
                }
                try {
                    fileTextField.getField().getDocument().addDocumentListener(new DocumentListener() {
                        @Override
                        public void insertUpdate(final DocumentEvent documentEvent) {
                            CreateFileDialogBase.this.validateOkAction();
                            CreateFileDialogBase.this.myLocationWasManuallyChanged = true;
                        }
                        
                        @Override
                        public void removeUpdate(final DocumentEvent documentEvent) {
                            CreateFileDialogBase.this.validateOkAction();
                            CreateFileDialogBase.this.myLocationWasManuallyChanged = (documentEvent.getDocument().getLength() != 0);
                        }
                        
                        @Override
                        public void changedUpdate(final DocumentEvent documentEvent) {
                        }
                    });
                    if (b) {
                        formBuilder.addLabeledComponent(OCBundle.message("create.field.location", new Object[0]), (JComponent)this.myLocationField);
                    }
                }
                catch (IllegalArgumentException ex3) {
                    throw a(ex3);
                }
            }
        }
        
        private boolean a(final String s) {
            return OCNewFileActionBase.this.isValidName(s);
        }
        
        protected boolean isNameFieldEnabled() {
            return true;
        }
        
        static {
            boolean $assertionsDisabled2 = false;
            Label_0017: {
                try {
                    if (!OCNewFileActionBase.class.desiredAssertionStatus()) {
                        $assertionsDisabled2 = true;
                        break Label_0017;
                    }
                }
                catch (IllegalArgumentException ex) {
                    throw a(ex);
                }
                $assertionsDisabled2 = false;
            }
            $assertionsDisabled = $assertionsDisabled2;
        }
        
        private static IllegalArgumentException a(final IllegalArgumentException ex) {
            return ex;
        }
        
        private class NameFocusListener implements FocusListener
        {
            private int mySelectionStart;
            
            public NameFocusListener(final int mySelectionStart) {
                this.mySelectionStart = mySelectionStart;
            }
            
            @Override
            public void focusGained(@NotNull final FocusEvent focusEvent) {
                try {
                    if (focusEvent == null) {
                        throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "e", "com/jetbrains/cidr/lang/actions/newFile/OCNewFileActionBase$CreateFileDialogBase$NameFocusListener", "focusGained"));
                    }
                }
                catch (IllegalArgumentException ex) {
                    throw a(ex);
                }
                CreateFileDialogBase.this.myNameField.setCaretPosition(this.mySelectionStart);
                CreateFileDialogBase.this.myNameField.moveCaretPosition(CreateFileDialogBase.this.myNameField.getText().length());
            }
            
            @Override
            public void focusLost(@NotNull final FocusEvent focusEvent) {
                try {
                    if (focusEvent == null) {
                        throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "e", "com/jetbrains/cidr/lang/actions/newFile/OCNewFileActionBase$CreateFileDialogBase$NameFocusListener", "focusLost"));
                    }
                }
                catch (IllegalArgumentException ex) {
                    throw a(ex);
                }
            }
            
            public void resetSelectionStart() {
                this.mySelectionStart = 0;
            }
            
            private static IllegalArgumentException a(final IllegalArgumentException ex) {
                return ex;
            }
        }
    }
    
    public interface AuxAction
    {
        void run(final PsiFile[] p0);
    }
    
    public interface FileFactory
    {
        PsiFile createFileFromText(@NotNull final String p0, @Nullable final FileType p1, @NotNull final String p2);
    }
}
