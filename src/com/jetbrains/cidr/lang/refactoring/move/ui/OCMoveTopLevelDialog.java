// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.lang.refactoring.move.ui;

import com.intellij.refactoring.classMembers.AbstractMemberInfoStorage;
import com.jetbrains.cidr.lang.symbols.OCSymbolHolderVirtualPsiElement;
import com.intellij.refactoring.classMembers.MemberInfoModel;
import com.jetbrains.cidr.lang.refactoring.move.OCTopLevelModel;
import com.jetbrains.cidr.lang.actions.newFile.OCNewSourceFileAction;
import com.jetbrains.cidr.lang.actions.newFile.OCNewFileActionBase;
import com.jetbrains.cidr.lang.refactoring.move.OCMemberInfo;
import java.util.List;
import com.jetbrains.cidr.lang.refactoring.move.OCMoveTopLevelProcessor;
import com.jetbrains.cidr.lang.refactoring.move.OCMoveProcessor;
import com.intellij.util.PathUtil;
import com.intellij.openapi.options.ConfigurationException;
import javax.swing.Box;
import com.intellij.openapi.editor.event.DocumentEvent;
import com.intellij.openapi.editor.event.DocumentListener;
import java.awt.Component;
import javax.swing.JLabel;
import java.awt.LayoutManager;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import javax.swing.JComponent;
import org.jetbrains.annotations.Nullable;
import com.jetbrains.cidr.lang.symbols.cpp.OCNamespaceSymbol;
import com.jetbrains.cidr.lang.psi.OCCppNamespace;
import com.jetbrains.cidr.lang.symbols.OCSymbol;
import com.jetbrains.cidr.lang.psi.OCSymbolDeclarator;
import com.intellij.psi.PsiElement;
import com.intellij.openapi.util.Condition;
import org.jetbrains.annotations.NotNull;
import com.jetbrains.cidr.lang.psi.OCFile;
import com.jetbrains.cidr.lang.ui.OCFileReferenceEditor;

public class OCMoveTopLevelDialog extends OCAbstractMoveDialog
{
    private OCFileReferenceEditor myTargetFileField;
    
    public OCMoveTopLevelDialog(@NotNull final OCFile mySourceFile, final Condition<PsiElement> condition, final String text) {
        if (mySourceFile == null) {
            throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "file", "com/jetbrains/cidr/lang/refactoring/move/ui/OCMoveTopLevelDialog", "<init>"));
        }
        super(null, null, condition, mySourceFile.getProject());
        this.mySourceFile = mySourceFile;
        this.myMemberInfos = ((AbstractMemberInfoStorage<T, PsiElement, OCMemberInfo>)this.myStorage).getClassMemberInfos((PsiElement)mySourceFile);
        this.myMemberInfoModel = this.createModel();
        this.setMembersChecked();
        this.setTitle("Move Declarations");
        this.init();
        if (text != null) {
            this.myTargetFileField.setText(text);
        }
    }
    
    public OCMoveTopLevelDialog(@NotNull final OCCppNamespace ocCppNamespace, final OCNamespaceSymbol ocNamespaceSymbol, final Condition<PsiElement> condition, final String text) {
        if (ocCppNamespace == null) {
            throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "namespace", "com/jetbrains/cidr/lang/refactoring/move/ui/OCMoveTopLevelDialog", "<init>"));
        }
        super(ocCppNamespace, ocNamespaceSymbol, condition, ocCppNamespace.getProject());
        this.myMemberInfos = ((AbstractMemberInfoStorage<T, PsiElement, OCMemberInfo>)this.myStorage).getClassMemberInfos((PsiElement)ocCppNamespace);
        this.myMemberInfoModel = this.createModel();
        this.setMembersChecked();
        this.setTitle("Move Declarations");
        this.init();
        if (text != null) {
            this.myTargetFileField.setText(text);
        }
    }
    
    @Nullable
    protected String getHelpId() {
        return "refactoring.moveMembers";
    }
    
    @Nullable
    protected JComponent createNorthPanel() {
        final JPanel panel = new JPanel(new BorderLayout());
        panel.add(new JLabel("Move declarations to file (existing or new):"), "North");
        panel.add((Component)(this.myTargetFileField = OCFileReferenceEditor.create(this.myProject)), "Center");
        this.myTargetFileField.addDocumentListener((DocumentListener)new DocumentListener() {
            public void documentChanged(final DocumentEvent documentEvent) {
                OCMoveTopLevelDialog.this.validateButtons();
            }
        });
        final Box verticalBox = Box.createVerticalBox();
        verticalBox.add(panel);
        verticalBox.add(Box.createVerticalStrut(7));
        final JPanel panel2 = new JPanel(new BorderLayout());
        panel2.add(verticalBox, "Center");
        return panel2;
    }
    
    public JComponent getPreferredFocusedComponent() {
        return (JComponent)this.myTargetFileField;
    }
    
    @Override
    public boolean allowsEmptySelection() {
        return false;
    }
    
    @Override
    protected void checkCanRun() throws ConfigurationException {
        super.checkCanRun();
        final String targetName = this.getTargetName();
        final OCFile b = this.b();
        try {
            if (targetName.isEmpty()) {
                throw new ConfigurationException("No target file is selected");
            }
        }
        catch (ConfigurationException ex) {
            throw c((Exception)ex);
        }
        try {
            if (!PathUtil.isValidFileName(targetName)) {
                throw new ConfigurationException("Name of the file is invalid");
            }
        }
        catch (ConfigurationException ex2) {
            throw c((Exception)ex2);
        }
        Label_0139: {
            Label_0117: {
                Label_0076: {
                    try {
                        if (b == null) {
                            break Label_0117;
                        }
                        final OCFile ocFile = b;
                        final boolean b2 = ocFile.isInProjectSources();
                        if (!b2) {
                            break Label_0076;
                        }
                        break Label_0117;
                    }
                    catch (ConfigurationException ex3) {
                        throw c((Exception)ex3);
                    }
                    try {
                        final OCFile ocFile = b;
                        final boolean b2 = ocFile.isInProjectSources();
                        if (!b2) {
                            throw new ConfigurationException("File \"" + b.getName() + "\" is not located inside the project");
                        }
                    }
                    catch (ConfigurationException ex4) {
                        throw c((Exception)ex4);
                    }
                }
                try {
                    if (b == null) {
                        return;
                    }
                    final OCFile ocFile2 = b;
                    final OCMoveTopLevelDialog ocMoveTopLevelDialog = this;
                    final OCFile ocFile3 = ocMoveTopLevelDialog.mySourceFile;
                    final boolean b3 = ocFile2.equals(ocFile3);
                    if (b3) {
                        break Label_0139;
                    }
                    return;
                }
                catch (ConfigurationException ex5) {
                    throw c((Exception)ex5);
                }
            }
            try {
                final OCFile ocFile2 = b;
                final OCMoveTopLevelDialog ocMoveTopLevelDialog = this;
                final OCFile ocFile3 = ocMoveTopLevelDialog.mySourceFile;
                final boolean b3 = ocFile2.equals(ocFile3);
                if (b3) {
                    throw new ConfigurationException("Source and target files should be different");
                }
            }
            catch (ConfigurationException ex6) {
                throw c((Exception)ex6);
            }
        }
    }
    
    @Override
    public boolean checkConflicts() {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     0: new             Ljava/lang/StringBuilder;
        //     3: dup            
        //     4: invokespecial   java/lang/StringBuilder.<init>:()V
        //     7: ldc             "Target file \""
        //     9: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //    12: aload_0        
        //    13: invokevirtual   com/jetbrains/cidr/lang/refactoring/move/ui/OCMoveTopLevelDialog.getTargetName:()Ljava/lang/String;
        //    16: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //    19: ldc             "\" doesn't yet exist."
        //    21: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //    24: invokevirtual   java/lang/StringBuilder.toString:()Ljava/lang/String;
        //    27: astore_1       
        //    28: aload_0        
        //    29: invokespecial   com/jetbrains/cidr/lang/refactoring/move/ui/OCMoveTopLevelDialog.b:()Lcom/jetbrains/cidr/lang/psi/OCFile;
        //    32: ifnonnull       77
        //    35: invokestatic    com/jetbrains/cidr/lang/actions/newFile/OCNewFileActionBase.isNewFileActionSupported:()Z
        //    38: ifeq            85
        //    41: goto            48
        //    44: invokestatic    com/jetbrains/cidr/lang/refactoring/move/ui/OCMoveTopLevelDialog.c:(Ljava/lang/Exception;)Ljava/lang/Exception;
        //    47: athrow         
        //    48: aload_0        
        //    49: invokevirtual   com/jetbrains/cidr/lang/refactoring/move/ui/OCMoveTopLevelDialog.getProject:()Lcom/intellij/openapi/project/Project;
        //    52: aload_1        
        //    53: aload_0        
        //    54: invokevirtual   com/jetbrains/cidr/lang/refactoring/move/ui/OCMoveTopLevelDialog.getTitle:()Ljava/lang/String;
        //    57: ldc             "Create New File"
        //    59: ldc             "Cancel"
        //    61: invokestatic    com/intellij/openapi/ui/Messages.getQuestionIcon:()Ljavax/swing/Icon;
        //    64: invokestatic    com/intellij/openapi/ui/Messages.showYesNoDialog:(Lcom/intellij/openapi/project/Project;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljavax/swing/Icon;)I
        //    67: ifne            85
        //    70: goto            77
        //    73: invokestatic    com/jetbrains/cidr/lang/refactoring/move/ui/OCMoveTopLevelDialog.c:(Ljava/lang/Exception;)Ljava/lang/Exception;
        //    76: athrow         
        //    77: iconst_1       
        //    78: goto            86
        //    81: invokestatic    com/jetbrains/cidr/lang/refactoring/move/ui/OCMoveTopLevelDialog.c:(Ljava/lang/Exception;)Ljava/lang/Exception;
        //    84: athrow         
        //    85: iconst_0       
        //    86: ireturn        
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                                
        //  -----  -----  -----  -----  ------------------------------------
        //  28     41     44     48     Ljava/lang/IllegalArgumentException;
        //  35     70     73     77     Ljava/lang/IllegalArgumentException;
        //  48     81     81     85     Ljava/lang/IllegalArgumentException;
        // 
        // The error that occurred was:
        // 
        // java.lang.IllegalStateException: Expression is linked from several locations: Label_0048:
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
    public String getTargetName() {
        String trim;
        try {
            trim = this.myTargetFileField.getText().trim();
            if (trim == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/refactoring/move/ui/OCMoveTopLevelDialog", "getTargetName"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw c(ex);
        }
        return trim;
    }
    
    @Nullable
    private OCFile b() {
        try {
            if (this.myTargetFileField != null) {
                return this.myTargetFileField.getFile(this.myProject);
            }
        }
        catch (IllegalArgumentException ex) {
            throw c(ex);
        }
        return null;
    }
    
    @Override
    protected String getOKButtonText() {
        return "&Move";
    }
    
    private PsiElement a() {
        try {
            if (this.mySourceClass != null) {
                final Object o = this.mySourceClass;
                return (PsiElement)o;
            }
        }
        catch (IllegalArgumentException ex) {
            throw c(ex);
        }
        final Object o = this.mySourceFile;
        return (PsiElement)o;
    }
    
    @Nullable
    @Override
    protected OCMoveProcessor createProcessor() {
        return new OCMoveTopLevelProcessor(this.a(), this.getTargetName(), this.b(), this.getSelectedMemberInfos()) {
            @Override
            protected String getCommandName() {
                return OCMoveTopLevelDialog.this.getTitle();
            }
            
            @Nullable
            @Override
            protected OCNewFileActionBase getNewClassAction() {
                return new OCNewSourceFileAction();
            }
            
            @Override
            protected boolean importTargetFromSource() {
                return ((OCTopLevelModel)OCMoveTopLevelDialog.this.myMemberInfoModel).importTargetFromSource();
            }
            
            @Override
            protected boolean importSourceFromTarget() {
                return ((OCTopLevelModel)OCMoveTopLevelDialog.this.myMemberInfoModel).importSourceFromTarget();
            }
        };
    }
    
    @Override
    protected MemberInfoModel<OCSymbolHolderVirtualPsiElement, OCMemberInfo> createModel() {
        return (MemberInfoModel<OCSymbolHolderVirtualPsiElement, OCMemberInfo>)new OCTopLevelModel(this.a());
    }
    
    @Override
    protected String getConflictMessage(final OCMemberInfo ocMemberInfo, final OCSymbol ocSymbol) {
        return null;
    }
    
    private static Exception c(final Exception ex) {
        return ex;
    }
}
