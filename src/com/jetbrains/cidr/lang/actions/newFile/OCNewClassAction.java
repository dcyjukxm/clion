// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.lang.actions.newFile;

import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import com.jetbrains.cidr.lang.settings.OCNewClassSettings;
import javax.swing.JComponent;
import com.intellij.openapi.ui.ComboBox;
import com.intellij.util.ui.FormBuilder;
import javax.swing.JComboBox;
import com.intellij.openapi.project.Project;
import java.util.Properties;
import com.jetbrains.cidr.lang.psi.OCFile;
import com.intellij.psi.PsiFile;
import org.jetbrains.annotations.NotNull;
import com.intellij.openapi.fileTypes.FileType;
import com.jetbrains.cidr.lang.OCFileType;
import javax.swing.Icon;
import icons.CidrLangIcons;
import com.jetbrains.cidr.lang.OCBundle;

public class OCNewClassAction<T extends CreateClassDialog> extends OCNewFileActionBase<T>
{
    private static final String PUT_IVARS_TO_IMPLEMENTATION_TEMPLATE_PROPERTY = "PUT_IVARS_TO_IMPLEMENTATION";
    public static final String DEFAULT_M_EXT = ".m";
    public static final String DEFAULT_MM_EXT = ".mm";
    public static final String DEFAULT_H_EXT = ".h";
    
    public OCNewClassAction() {
        this(OCBundle.message("create.class.title", new Object[0]), OCBundle.message("create.class.description", new Object[0]), CidrLangIcons.CodeAssistantClass);
    }
    
    public OCNewClassAction(final String s, final String s2, final Icon icon) {
        super((FileType)OCFileType.INSTANCE, s, s2, icon);
    }
    
    @NotNull
    @Override
    protected String getDefaultName() {
        String defaultClassPrefix;
        try {
            defaultClassPrefix = this.getDefaultClassPrefix();
            if (defaultClassPrefix == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/actions/newFile/OCNewClassAction", "getDefaultName"));
            }
        }
        catch (IllegalStateException ex) {
            throw a(ex);
        }
        return defaultClassPrefix;
    }
    
    @NotNull
    @Override
    protected NewFileNames getNewFileNames(final String s) {
        NewFileNames newFileNames;
        try {
            newFileNames = new NewFileNames(s + this.myState.dialogPeer.getImplementationExtension(), s + ".h");
            if (newFileNames == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/actions/newFile/OCNewClassAction", "getNewFileNames"));
            }
        }
        catch (IllegalStateException ex) {
            throw a(ex);
        }
        return newFileNames;
    }
    
    @NotNull
    @Override
    protected NewFileNames getNewFileNames(final String s, final PsiFile psiFile) {
        OCFile associatedFile = null;
        Label_0032: {
            try {
                if (((OCFile)psiFile).isHeader()) {
                    associatedFile = ((OCFile)psiFile).getAssociatedFile();
                    break Label_0032;
                }
            }
            catch (IllegalStateException ex) {
                throw a(ex);
            }
            associatedFile = (OCFile)psiFile;
        }
        final OCFile ocFile = associatedFile;
        String s4 = null;
        Label_0069: {
            Label_0058: {
                try {
                    if (ocFile == null) {
                        break Label_0058;
                    }
                    final OCFile ocFile2 = ocFile;
                    final String s2 = ocFile2.getName();
                    final String s3 = ".mm";
                    final boolean b = s2.endsWith(s3);
                    if (b) {
                        break Label_0058;
                    }
                    break Label_0058;
                }
                catch (IllegalStateException ex2) {
                    throw a(ex2);
                }
                try {
                    final OCFile ocFile2 = ocFile;
                    final String s2 = ocFile2.getName();
                    final String s3 = ".mm";
                    final boolean b = s2.endsWith(s3);
                    if (b) {
                        s4 = ".mm";
                        break Label_0069;
                    }
                }
                catch (IllegalStateException ex3) {
                    throw a(ex3);
                }
            }
            s4 = ".m";
        }
        final String s5 = s4;
        NewFileNames newFileNames;
        try {
            newFileNames = new NewFileNames(s + s5, s + ".h");
            if (newFileNames == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/actions/newFile/OCNewClassAction", "getNewFileNames"));
            }
        }
        catch (IllegalStateException ex4) {
            throw a(ex4);
        }
        return newFileNames;
    }
    
    @Override
    protected String getFileTemplate(final String s) {
        Label_0032: {
            try {
                if (s.endsWith(".m")) {
                    return "Objective-C Class.m";
                }
                final String s2 = s;
                final String s3 = ".mm";
                final boolean b = s2.endsWith(s3);
                if (b) {
                    return "Objective-C Class.m";
                }
                break Label_0032;
            }
            catch (IllegalStateException ex) {
                throw a(ex);
            }
            try {
                final String s2 = s;
                final String s3 = ".mm";
                final boolean b = s2.endsWith(s3);
                if (b) {
                    return "Objective-C Class.m";
                }
            }
            catch (IllegalStateException ex2) {
                throw a(ex2);
            }
            try {
                if (s.endsWith(".h")) {
                    return "Objective-C Header File.h";
                }
            }
            catch (IllegalStateException ex3) {
                throw a(ex3);
            }
        }
        try {
            assert false;
        }
        catch (IllegalStateException ex4) {
            throw a(ex4);
        }
        return null;
    }
    
    @Override
    protected void setAdditionalProperties(final Properties p0, final String p1, final PsiFile p2, final Project p3) {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     0: aload           4
        //     2: invokestatic    com/intellij/psi/codeStyle/CodeStyleSettingsManager.getSettings:(Lcom/intellij/openapi/project/Project;)Lcom/intellij/psi/codeStyle/CodeStyleSettings;
        //     5: ldc             Lcom/jetbrains/cidr/lang/settings/OCCodeStyleSettings;.class
        //     7: invokevirtual   com/intellij/psi/codeStyle/CodeStyleSettings.getCustomSettings:(Ljava/lang/Class;)Lcom/intellij/psi/codeStyle/CustomCodeStyleSettings;
        //    10: checkcast       Lcom/jetbrains/cidr/lang/settings/OCCodeStyleSettings;
        //    13: astore          5
        //    15: aload_1        
        //    16: ldc             "HEADER_FILENAME"
        //    18: new             Ljava/lang/StringBuilder;
        //    21: dup            
        //    22: invokespecial   java/lang/StringBuilder.<init>:()V
        //    25: aload_0        
        //    26: getfield        com/jetbrains/cidr/lang/actions/newFile/OCNewClassAction.mySelectedName:Ljava/lang/String;
        //    29: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //    32: ldc             ".h"
        //    34: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //    37: invokevirtual   java/lang/StringBuilder.toString:()Ljava/lang/String;
        //    40: invokevirtual   java/util/Properties.setProperty:(Ljava/lang/String;Ljava/lang/String;)Ljava/lang/Object;
        //    43: pop            
        //    44: invokestatic    com/jetbrains/cidr/lang/workspace/compiler/OCCompilerFeatures.supportsIvarsInImplementation:()Z
        //    47: ifeq            85
        //    50: aload           5
        //    52: ifnull          77
        //    55: goto            62
        //    58: invokestatic    com/jetbrains/cidr/lang/actions/newFile/OCNewClassAction.a:(Ljava/lang/IllegalStateException;)Ljava/lang/IllegalStateException;
        //    61: athrow         
        //    62: aload           5
        //    64: getfield        com/jetbrains/cidr/lang/settings/OCCodeStyleSettings.PUT_IVARS_TO_IMPLEMENTATION:Z
        //    67: ifeq            85
        //    70: goto            77
        //    73: invokestatic    com/jetbrains/cidr/lang/actions/newFile/OCNewClassAction.a:(Ljava/lang/IllegalStateException;)Ljava/lang/IllegalStateException;
        //    76: athrow         
        //    77: iconst_1       
        //    78: goto            86
        //    81: invokestatic    com/jetbrains/cidr/lang/actions/newFile/OCNewClassAction.a:(Ljava/lang/IllegalStateException;)Ljava/lang/IllegalStateException;
        //    84: athrow         
        //    85: iconst_0       
        //    86: istore          6
        //    88: aload_1        
        //    89: ldc             "PUT_IVARS_TO_IMPLEMENTATION"
        //    91: iload           6
        //    93: ifeq            105
        //    96: ldc             "true"
        //    98: goto            107
        //   101: invokestatic    com/jetbrains/cidr/lang/actions/newFile/OCNewClassAction.a:(Ljava/lang/IllegalStateException;)Ljava/lang/IllegalStateException;
        //   104: athrow         
        //   105: ldc             "false"
        //   107: invokevirtual   java/util/Properties.setProperty:(Ljava/lang/String;Ljava/lang/String;)Ljava/lang/Object;
        //   110: pop            
        //   111: return         
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                             
        //  -----  -----  -----  -----  ---------------------------------
        //  15     55     58     62     Ljava/lang/IllegalStateException;
        //  50     70     73     77     Ljava/lang/IllegalStateException;
        //  62     81     81     85     Ljava/lang/IllegalStateException;
        //  88     101    101    105    Ljava/lang/IllegalStateException;
        // 
        // The error that occurred was:
        // 
        // java.lang.IllegalStateException: Expression is linked from several locations: Label_0062:
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
    @Override
    protected T createDialog(@NotNull final String s) {
        try {
            if (s == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "defaultName", "com/jetbrains/cidr/lang/actions/newFile/OCNewClassAction", "createDialog"));
            }
        }
        catch (IllegalStateException ex) {
            throw a(ex);
        }
        CreateClassDialog createClassDialog;
        try {
            createClassDialog = new CreateClassDialog(s);
            if (createClassDialog == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/actions/newFile/OCNewClassAction", "createDialog"));
            }
        }
        catch (IllegalStateException ex2) {
            throw a(ex2);
        }
        return (T)createClassDialog;
    }
    
    static {
        boolean $assertionsDisabled2 = false;
        Label_0017: {
            try {
                if (!OCNewClassAction.class.desiredAssertionStatus()) {
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
    
    protected class CreateClassDialog extends CreateFileDialogBase
    {
        private JComboBox myLanguageCombo;
        
        public CreateClassDialog(final String s) {
            super(OCBundle.message("create.class.dialog.title", new Object[0]), s, null);
        }
        
        public CreateClassDialog(final String s, final String s2, final String s3) {
            super(s, s2, s3, null);
        }
        
        @Override
        public void fillGenericControls(final FormBuilder formBuilder) {
            super.fillGenericControls(formBuilder);
            this.myLanguageCombo = (JComboBox)new ComboBox((Object[])new String[] { "Objective-C (.m)", "Objective-C++ (.mm)" });
            formBuilder.addLabeledComponent(OCBundle.message("create.field.language", new Object[0]), (JComponent)this.myLanguageCombo);
            this.myLanguageCombo.setSelectedIndex(OCNewClassSettings.getInstance().myLanguageIndex);
            this.myLanguageCombo.addItemListener(new ItemListener() {
                @Override
                public void itemStateChanged(final ItemEvent itemEvent) {
                    OCNewClassSettings.getInstance().myLanguageIndex = CreateClassDialog.this.myLanguageCombo.getSelectedIndex();
                    CreateClassDialog.this.validateOkAction();
                }
            });
        }
        
        protected String getImplementationExtension() {
            return (this.myLanguageCombo.getSelectedIndex() == 0) ? ".m" : ".mm";
        }
    }
}
