// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.lang.refactoring.move.ui;

import com.intellij.refactoring.classMembers.AbstractMemberInfoStorage;
import com.jetbrains.cidr.lang.actions.newFile.OCNewCppClassAction;
import com.jetbrains.cidr.lang.refactoring.move.OCMoveCppProcessor;
import com.jetbrains.cidr.lang.psi.OCStruct;
import com.jetbrains.cidr.lang.actions.newFile.OCNewClassAction;
import com.jetbrains.cidr.lang.actions.newFile.OCNewFileActionBase;
import com.jetbrains.cidr.lang.refactoring.move.OCMoveObjCProcessor;
import com.jetbrains.cidr.lang.psi.OCClassDeclaration;
import java.util.Collections;
import com.jetbrains.cidr.lang.refactoring.move.OCMoveProcessor;
import com.jetbrains.cidr.lang.psi.OCClassDeclarationBase;
import com.jetbrains.cidr.lang.symbols.cpp.OCSymbolWithQualifiedName;
import com.jetbrains.cidr.lang.symbols.cpp.OCStructSymbol;
import com.jetbrains.cidr.lang.symbols.objc.OCClassSymbol;
import com.jetbrains.cidr.lang.search.scopes.OCSearchScope;
import com.intellij.openapi.util.text.StringUtil;
import com.intellij.openapi.options.ConfigurationException;
import javax.swing.Box;
import java.util.Collection;
import com.intellij.openapi.editor.event.DocumentEvent;
import com.intellij.openapi.editor.event.DocumentListener;
import java.awt.Component;
import javax.swing.JLabel;
import java.awt.LayoutManager;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import javax.swing.JComponent;
import java.util.Arrays;
import com.intellij.refactoring.classMembers.MemberInfoModel;
import java.util.List;
import com.intellij.refactoring.classMembers.MemberInfoTooltipManager;
import com.intellij.refactoring.classMembers.MemberInfoChange;
import com.intellij.refactoring.classMembers.AbstractUsesDependencyMemberInfoModel;
import java.util.Iterator;
import com.intellij.refactoring.classMembers.MemberInfoBase;
import com.intellij.refactoring.classMembers.UsedByDependencyMemberInfoModel;
import org.jetbrains.annotations.Nullable;
import com.intellij.refactoring.RefactoringBundle;
import com.intellij.psi.PsiElement;
import com.intellij.openapi.util.Condition;
import com.jetbrains.cidr.lang.symbols.OCSymbol;
import org.jetbrains.annotations.NotNull;
import com.jetbrains.cidr.lang.psi.OCSymbolDeclarator;
import com.jetbrains.cidr.lang.refactoring.move.OCMemberInfo;
import com.jetbrains.cidr.lang.symbols.OCSymbolHolderVirtualPsiElement;
import com.intellij.refactoring.classMembers.DependencyMemberInfoModel;
import com.jetbrains.cidr.lang.ui.OCTypeReferenceEditor;

public class OCMoveMembersDialog extends OCAbstractMoveDialog
{
    private OCTypeReferenceEditor myTargetClassField;
    private DependencyMemberInfoModel<OCSymbolHolderVirtualPsiElement, OCMemberInfo> mySameClassModel;
    private DependencyMemberInfoModel<OCSymbolHolderVirtualPsiElement, OCMemberInfo> myInheritorModel;
    private DependencyMemberInfoModel<OCSymbolHolderVirtualPsiElement, OCMemberInfo> myAncestorModel;
    private DependencyMemberInfoModel<OCSymbolHolderVirtualPsiElement, OCMemberInfo> myUnrelatedModel;
    
    public OCMoveMembersDialog(@NotNull final OCSymbolDeclarator ocSymbolDeclarator, final OCSymbol ocSymbol, final Condition<PsiElement> condition, final String text) {
        if (ocSymbolDeclarator == null) {
            throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "clazz", "com/jetbrains/cidr/lang/refactoring/move/ui/OCMoveMembersDialog", "<init>"));
        }
        super(ocSymbolDeclarator, ocSymbol, condition, ocSymbolDeclarator.getProject());
        this.myMemberInfos = ((AbstractMemberInfoStorage<T, PsiElement, OCMemberInfo>)this.myStorage).getClassMemberInfos((PsiElement)ocSymbolDeclarator);
        this.setMembersChecked();
        this.setTitle(RefactoringBundle.message("move.members.title"));
        this.c();
        this.init();
        if (text != null) {
            this.myTargetClassField.setText(text);
        }
    }
    
    @Nullable
    protected String getHelpId() {
        return "refactoring.moveMembers";
    }
    
    private void c() {
        this.mySameClassModel = (DependencyMemberInfoModel<OCSymbolHolderVirtualPsiElement, OCMemberInfo>)new UsedByDependencyMemberInfoModel<OCSymbolHolderVirtualPsiElement, OCSymbolDeclarator, OCMemberInfo>(this.mySourceClass) {
            public int checkForProblems(@NotNull final OCMemberInfo ocMemberInfo) {
                try {
                    if (ocMemberInfo == null) {
                        throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "memberInfo", "com/jetbrains/cidr/lang/refactoring/move/ui/OCMoveMembersDialog$1", "checkForProblems"));
                    }
                }
                catch (IllegalArgumentException ex) {
                    throw b(ex);
                }
                return 0;
            }
            
            public boolean isMemberEnabled(final OCMemberInfo ocMemberInfo) {
                return OCMoveMembersDialog.this.isMemberEnabled(ocMemberInfo);
            }
            
            private static IllegalArgumentException b(final IllegalArgumentException ex) {
                return ex;
            }
        };
        this.myInheritorModel = (DependencyMemberInfoModel<OCSymbolHolderVirtualPsiElement, OCMemberInfo>)new UsedByDependencyMemberInfoModel<OCSymbolHolderVirtualPsiElement, OCSymbolDeclarator, OCMemberInfo>(this.mySourceClass) {
            public int checkForProblems(@NotNull final OCMemberInfo ocMemberInfo) {
                try {
                    if (ocMemberInfo == null) {
                        throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "memberInfo", "com/jetbrains/cidr/lang/refactoring/move/ui/OCMoveMembersDialog$2", "checkForProblems"));
                    }
                }
                catch (IllegalArgumentException ex) {
                    throw b(ex);
                }
                final int checkForProblems = super.checkForProblems((M)ocMemberInfo);
                Label_0075: {
                    try {
                        if (checkForProblems != 2) {
                            return checkForProblems;
                        }
                        final OCMemberInfo ocMemberInfo2 = ocMemberInfo;
                        final boolean b = ocMemberInfo2.isStatic();
                        if (b) {
                            return 1;
                        }
                        break Label_0075;
                    }
                    catch (IllegalArgumentException ex2) {
                        throw b(ex2);
                    }
                    try {
                        final OCMemberInfo ocMemberInfo2 = ocMemberInfo;
                        final boolean b = ocMemberInfo2.isStatic();
                        if (b) {
                            return 1;
                        }
                    }
                    catch (IllegalArgumentException ex3) {
                        throw b(ex3);
                    }
                }
                for (final OCMemberInfo ocMemberInfo3 : OCMoveMembersDialog.this.getSelectedMemberInfos()) {
                    try {
                        if (!ocMemberInfo3.isStatic()) {
                            return checkForProblems;
                        }
                        continue;
                    }
                    catch (IllegalArgumentException ex4) {
                        throw b(ex4);
                    }
                }
                return 1;
            }
            
            public boolean isMemberEnabled(final OCMemberInfo ocMemberInfo) {
                return OCMoveMembersDialog.this.isMemberEnabled(ocMemberInfo);
            }
            
            private static IllegalArgumentException b(final IllegalArgumentException ex) {
                return ex;
            }
        };
        this.myAncestorModel = (DependencyMemberInfoModel<OCSymbolHolderVirtualPsiElement, OCMemberInfo>)new AbstractUsesDependencyMemberInfoModel<OCSymbolHolderVirtualPsiElement, OCSymbolDeclarator, OCMemberInfo>(this.mySourceClass, null, false) {
            @Override
            protected int doCheck(@NotNull final OCMemberInfo ocMemberInfo, final int n) {
                try {
                    if (ocMemberInfo == null) {
                        throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "memberInfo", "com/jetbrains/cidr/lang/refactoring/move/ui/OCMoveMembersDialog$3", "doCheck"));
                    }
                }
                catch (IllegalArgumentException ex) {
                    throw c(ex);
                }
                try {
                    if (n != 2) {
                        return n;
                    }
                    final OCMemberInfo ocMemberInfo2 = ocMemberInfo;
                    final boolean b = ocMemberInfo2.isStatic();
                    if (b) {
                        return 1;
                    }
                    return n;
                }
                catch (IllegalArgumentException ex2) {
                    throw c(ex2);
                }
                try {
                    final OCMemberInfo ocMemberInfo2 = ocMemberInfo;
                    final boolean b = ocMemberInfo2.isStatic();
                    if (b) {
                        return 1;
                    }
                }
                catch (IllegalArgumentException ex3) {
                    throw c(ex3);
                }
                return n;
            }
            
            public boolean isMemberEnabled(final OCMemberInfo ocMemberInfo) {
                return OCMoveMembersDialog.this.isMemberEnabled(ocMemberInfo);
            }
            
            private static IllegalArgumentException c(final IllegalArgumentException ex) {
                return ex;
            }
        };
        (this.myUnrelatedModel = (DependencyMemberInfoModel<OCSymbolHolderVirtualPsiElement, OCMemberInfo>)new UsedByDependencyMemberInfoModel<OCSymbolHolderVirtualPsiElement, OCSymbolDeclarator, OCMemberInfo>(this.mySourceClass) {
            public int checkForProblems(@NotNull final OCMemberInfo ocMemberInfo) {
                try {
                    if (ocMemberInfo == null) {
                        throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "memberInfo", "com/jetbrains/cidr/lang/refactoring/move/ui/OCMoveMembersDialog$4", "checkForProblems"));
                    }
                }
                catch (IllegalArgumentException ex) {
                    throw b(ex);
                }
                return Math.max(OCMoveMembersDialog.this.myInheritorModel.checkForProblems(ocMemberInfo), OCMoveMembersDialog.this.myAncestorModel.checkForProblems(ocMemberInfo));
            }
            
            @Override
            public void memberInfoChanged(final MemberInfoChange<OCSymbolHolderVirtualPsiElement, OCMemberInfo> memberInfoChange) {
                super.memberInfoChanged((com.intellij.refactoring.classMembers.MemberInfoChange<T, M>)memberInfoChange);
                OCMoveMembersDialog.this.myInheritorModel.memberInfoChanged(memberInfoChange);
                OCMoveMembersDialog.this.myAncestorModel.memberInfoChanged(memberInfoChange);
            }
            
            public boolean isMemberEnabled(final OCMemberInfo ocMemberInfo) {
                return OCMoveMembersDialog.this.isMemberEnabled(ocMemberInfo);
            }
            
            private static IllegalArgumentException b(final IllegalArgumentException ex) {
                return ex;
            }
        }).setTooltipProvider(new MemberInfoTooltipManager.TooltipProvider<OCSymbolHolderVirtualPsiElement, OCMemberInfo>() {
            @Override
            public String getTooltip(final OCMemberInfo ocMemberInfo) {
                final String tooltipText = OCMoveMembersDialog.this.myInheritorModel.getTooltipText(ocMemberInfo);
                return (tooltipText != null) ? tooltipText : OCMoveMembersDialog.this.myAncestorModel.getTooltipText(ocMemberInfo);
            }
        });
    }
    
    @Override
    protected List<? extends MemberInfoModel<OCSymbolHolderVirtualPsiElement, OCMemberInfo>> getAllModels() {
        return Arrays.asList(this.mySameClassModel, this.myInheritorModel, this.myAncestorModel, this.myUnrelatedModel);
    }
    
    @Nullable
    protected JComponent createNorthPanel() {
        final JPanel panel = new JPanel(new BorderLayout());
        panel.add(new JLabel("Move members to class (existing or new):"), "North");
        panel.add((Component)(this.myTargetClassField = OCTypeReferenceEditor.create(null, (Condition<OCSymbol>)(p0 -> {
            // 
            // This method could not be decompiled.
            // 
            // Original Bytecode:
            // 
            //     0: aload_0        
            //     1: getfield        com/jetbrains/cidr/lang/refactoring/move/ui/OCMoveMembersDialog.myClassSymbol:Lcom/jetbrains/cidr/lang/symbols/OCSymbol;
            //     4: instanceof      Lcom/jetbrains/cidr/lang/symbols/objc/OCClassSymbol;
            //     7: ifeq            72
            //    10: aload_1        
            //    11: instanceof      Lcom/jetbrains/cidr/lang/symbols/objc/OCInterfaceSymbol;
            //    14: ifeq            48
            //    17: goto            24
            //    20: invokestatic    com/jetbrains/cidr/lang/refactoring/move/ui/OCMoveMembersDialog.c:(Ljava/lang/Exception;)Ljava/lang/Exception;
            //    23: athrow         
            //    24: ldc             ""
            //    26: aload_1        
            //    27: checkcast       Lcom/jetbrains/cidr/lang/symbols/objc/OCInterfaceSymbol;
            //    30: invokeinterface com/jetbrains/cidr/lang/symbols/objc/OCInterfaceSymbol.getCategoryName:()Ljava/lang/String;
            //    35: invokevirtual   java/lang/String.equals:(Ljava/lang/Object;)Z
            //    38: ifeq            62
            //    41: goto            48
            //    44: invokestatic    com/jetbrains/cidr/lang/refactoring/move/ui/OCMoveMembersDialog.c:(Ljava/lang/Exception;)Ljava/lang/Exception;
            //    47: athrow         
            //    48: aload_1        
            //    49: instanceof      Lcom/jetbrains/cidr/lang/symbols/objc/OCProtocolSymbol;
            //    52: ifeq            70
            //    55: goto            62
            //    58: invokestatic    com/jetbrains/cidr/lang/refactoring/move/ui/OCMoveMembersDialog.c:(Ljava/lang/Exception;)Ljava/lang/Exception;
            //    61: athrow         
            //    62: iconst_1       
            //    63: goto            71
            //    66: invokestatic    com/jetbrains/cidr/lang/refactoring/move/ui/OCMoveMembersDialog.c:(Ljava/lang/Exception;)Ljava/lang/Exception;
            //    69: athrow         
            //    70: iconst_0       
            //    71: ireturn        
            //    72: aload_1        
            //    73: instanceof      Lcom/jetbrains/cidr/lang/symbols/cpp/OCStructSymbol;
            //    76: ifeq            106
            //    79: aload_1        
            //    80: invokeinterface com/jetbrains/cidr/lang/symbols/OCSymbol.getKind:()Lcom/jetbrains/cidr/lang/symbols/OCSymbolKind;
            //    85: getstatic       com/jetbrains/cidr/lang/symbols/OCSymbolKind.STRUCT:Lcom/jetbrains/cidr/lang/symbols/OCSymbolKind;
            //    88: if_acmpne       106
            //    91: goto            98
            //    94: invokestatic    com/jetbrains/cidr/lang/refactoring/move/ui/OCMoveMembersDialog.c:(Ljava/lang/Exception;)Ljava/lang/Exception;
            //    97: athrow         
            //    98: iconst_1       
            //    99: goto            107
            //   102: invokestatic    com/jetbrains/cidr/lang/refactoring/move/ui/OCMoveMembersDialog.c:(Ljava/lang/Exception;)Ljava/lang/Exception;
            //   105: athrow         
            //   106: iconst_0       
            //   107: ireturn        
            //    Exceptions:
            //  Try           Handler
            //  Start  End    Start  End    Type                                
            //  -----  -----  -----  -----  ------------------------------------
            //  0      17     20     24     Ljava/lang/IllegalArgumentException;
            //  10     41     44     48     Ljava/lang/IllegalArgumentException;
            //  24     55     58     62     Ljava/lang/IllegalArgumentException;
            //  48     66     66     70     Ljava/lang/IllegalArgumentException;
            //  72     91     94     98     Ljava/lang/IllegalArgumentException;
            //  79     102    102    106    Ljava/lang/IllegalArgumentException;
            // 
            // The error that occurred was:
            // 
            // java.lang.IllegalStateException: Expression is linked from several locations: Label_0024:
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
        }), (PsiElement)this.mySourceClass, true, this.myProject)), "Center");
        this.myTargetClassField.addDocumentListener((DocumentListener)new DocumentListener() {
            public void documentChanged(final DocumentEvent documentEvent) {
                OCMoveMembersDialog.this.myMemberInfoModel = OCMoveMembersDialog.this.createModel();
                OCMoveMembersDialog.this.myMemberSelectionPanel.getTable().setMemberInfoModel(OCMoveMembersDialog.this.myMemberInfoModel);
                OCMoveMembersDialog.this.myMemberInfoModel.memberInfoChanged(new MemberInfoChange((Collection)OCMoveMembersDialog.this.myMemberInfos));
                OCMoveMembersDialog.this.myMemberSelectionPanel.getTable().fireExternalDataChange();
                OCMoveMembersDialog.this.validateButtons();
            }
        });
        this.myMemberInfoModel = this.createModel();
        final Box verticalBox = Box.createVerticalBox();
        verticalBox.add(panel);
        verticalBox.add(Box.createVerticalStrut(7));
        final JPanel panel2 = new JPanel(new BorderLayout());
        panel2.add(verticalBox, "Center");
        return panel2;
    }
    
    public JComponent getPreferredFocusedComponent() {
        return (JComponent)this.myTargetClassField;
    }
    
    @Override
    public boolean allowsEmptySelection() {
        return false;
    }
    
    @Override
    protected void checkCanRun() throws ConfigurationException {
        super.checkCanRun();
        final String targetName = this.getTargetName();
        final OCSymbol b = this.b();
        try {
            if (targetName.isEmpty()) {
                throw new ConfigurationException("No target class is selected");
            }
        }
        catch (ConfigurationException ex) {
            throw c((Exception)ex);
        }
        Label_0143: {
            Label_0121: {
                Label_0085: {
                    Label_0067: {
                        Label_0053: {
                            try {
                                if (b != null) {
                                    break Label_0067;
                                }
                                final String s = targetName;
                                final boolean b2 = StringUtil.isJavaIdentifier(s);
                                if (!b2) {
                                    break Label_0053;
                                }
                                break Label_0067;
                            }
                            catch (ConfigurationException ex2) {
                                throw c((Exception)ex2);
                            }
                            try {
                                final String s = targetName;
                                final boolean b2 = StringUtil.isJavaIdentifier(s);
                                if (!b2) {
                                    throw new ConfigurationException("Name of the class is invalid");
                                }
                            }
                            catch (ConfigurationException ex3) {
                                throw c((Exception)ex3);
                            }
                        }
                        try {
                            if (b == null) {
                                break Label_0121;
                            }
                            final OCSymbol ocSymbol = b;
                            final boolean b3 = OCSearchScope.isInProjectSources(ocSymbol);
                            if (!b3) {
                                break Label_0085;
                            }
                            break Label_0121;
                        }
                        catch (ConfigurationException ex4) {
                            throw c((Exception)ex4);
                        }
                    }
                    try {
                        final OCSymbol ocSymbol = b;
                        final boolean b3 = OCSearchScope.isInProjectSources(ocSymbol);
                        if (!b3) {
                            throw new ConfigurationException(b.getNameWithKindUppercase() + " is not located inside the project");
                        }
                    }
                    catch (ConfigurationException ex5) {
                        throw c((Exception)ex5);
                    }
                }
                try {
                    if (b == null) {
                        return;
                    }
                    final OCSymbol ocSymbol2 = b;
                    final OCMoveMembersDialog ocMoveMembersDialog = this;
                    final OCSymbol ocSymbol3 = ocMoveMembersDialog.myClassSymbol;
                    final boolean b4 = ocSymbol2.equals(ocSymbol3);
                    if (b4) {
                        break Label_0143;
                    }
                    return;
                }
                catch (ConfigurationException ex6) {
                    throw c((Exception)ex6);
                }
            }
            try {
                final OCSymbol ocSymbol2 = b;
                final OCMoveMembersDialog ocMoveMembersDialog = this;
                final OCSymbol ocSymbol3 = ocMoveMembersDialog.myClassSymbol;
                final boolean b4 = ocSymbol2.equals(ocSymbol3);
                if (b4) {
                    throw new ConfigurationException("Source and target classes should be different");
                }
            }
            catch (ConfigurationException ex7) {
                throw c((Exception)ex7);
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
        //     7: ldc             "Target class \""
        //     9: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //    12: aload_0        
        //    13: invokevirtual   com/jetbrains/cidr/lang/refactoring/move/ui/OCMoveMembersDialog.getTargetName:()Ljava/lang/String;
        //    16: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //    19: ldc             "\" doesn't yet exist."
        //    21: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //    24: invokevirtual   java/lang/StringBuilder.toString:()Ljava/lang/String;
        //    27: astore_1       
        //    28: aload_0        
        //    29: invokespecial   com/jetbrains/cidr/lang/refactoring/move/ui/OCMoveMembersDialog.b:()Lcom/jetbrains/cidr/lang/symbols/OCSymbol;
        //    32: ifnonnull       77
        //    35: invokestatic    com/jetbrains/cidr/lang/actions/newFile/OCNewFileActionBase.isNewFileActionSupported:()Z
        //    38: ifeq            85
        //    41: goto            48
        //    44: invokestatic    com/jetbrains/cidr/lang/refactoring/move/ui/OCMoveMembersDialog.c:(Ljava/lang/Exception;)Ljava/lang/Exception;
        //    47: athrow         
        //    48: aload_0        
        //    49: invokevirtual   com/jetbrains/cidr/lang/refactoring/move/ui/OCMoveMembersDialog.getProject:()Lcom/intellij/openapi/project/Project;
        //    52: aload_1        
        //    53: aload_0        
        //    54: invokevirtual   com/jetbrains/cidr/lang/refactoring/move/ui/OCMoveMembersDialog.getTitle:()Ljava/lang/String;
        //    57: ldc             "Create New Class"
        //    59: ldc             "Cancel"
        //    61: invokestatic    com/intellij/openapi/ui/Messages.getQuestionIcon:()Ljavax/swing/Icon;
        //    64: invokestatic    com/intellij/openapi/ui/Messages.showYesNoDialog:(Lcom/intellij/openapi/project/Project;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljavax/swing/Icon;)I
        //    67: ifne            85
        //    70: goto            77
        //    73: invokestatic    com/jetbrains/cidr/lang/refactoring/move/ui/OCMoveMembersDialog.c:(Ljava/lang/Exception;)Ljava/lang/Exception;
        //    76: athrow         
        //    77: iconst_1       
        //    78: goto            86
        //    81: invokestatic    com/jetbrains/cidr/lang/refactoring/move/ui/OCMoveMembersDialog.c:(Ljava/lang/Exception;)Ljava/lang/Exception;
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
            trim = this.myTargetClassField.getText().trim();
            if (trim == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/refactoring/move/ui/OCMoveMembersDialog", "getTargetName"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw c(ex);
        }
        return trim;
    }
    
    @Nullable
    private OCSymbol b() {
        try {
            if (this.myTargetClassField != null) {
                return this.myTargetClassField.getClassDeclaration(this.myProject);
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
    
    @Nullable
    private TargetRelation a() {
        final OCSymbol b = this.b();
        try {
            if (b == null) {
                return TargetRelation.UNRELATED;
            }
        }
        catch (IllegalArgumentException ex) {
            throw c(ex);
        }
        try {
            if (b == this.myClassSymbol) {
                return TargetRelation.SAME_CLASS;
            }
        }
        catch (IllegalArgumentException ex2) {
            throw c(ex2);
        }
        if (!(b instanceof OCClassSymbol)) {
            final OCStructSymbol ocStructSymbol = (OCStructSymbol)b;
            final OCStructSymbol ocStructSymbol2 = (OCStructSymbol)this.myClassSymbol;
            try {
                if (ocStructSymbol.resolvedNamesEqual(ocStructSymbol2)) {
                    return TargetRelation.SAME_CLASS;
                }
            }
            catch (IllegalArgumentException ex3) {
                throw c(ex3);
            }
            try {
                if (ocStructSymbol.isAncestor(ocStructSymbol2)) {
                    return TargetRelation.ANCESTOR;
                }
            }
            catch (IllegalArgumentException ex4) {
                throw c(ex4);
            }
            try {
                if (ocStructSymbol2.isAncestor(ocStructSymbol)) {
                    return TargetRelation.INHERITOR;
                }
            }
            catch (IllegalArgumentException ex5) {
                throw c(ex5);
            }
            return TargetRelation.UNRELATED;
        }
        final OCClassSymbol ocClassSymbol = (OCClassSymbol)b;
        final OCClassSymbol ocClassSymbol2 = (OCClassSymbol)this.myClassSymbol;
        try {
            if (ocClassSymbol.isSameCategory(ocClassSymbol2)) {
                return TargetRelation.SAME_CLASS;
            }
        }
        catch (IllegalArgumentException ex6) {
            throw c(ex6);
        }
        if (b.isSameSymbol(ocClassSymbol2)) {
            final String categoryName = ocClassSymbol2.getCategoryName();
            final String categoryName2 = ocClassSymbol.getCategoryName();
            Label_0145: {
                Label_0125: {
                    Label_0117: {
                        try {
                            if (categoryName == null) {
                                break Label_0117;
                            }
                            final String s = categoryName;
                            final boolean b2 = s.isEmpty();
                            if (b2) {
                                break Label_0117;
                            }
                            break Label_0125;
                        }
                        catch (IllegalArgumentException ex7) {
                            throw c(ex7);
                        }
                        try {
                            final String s = categoryName;
                            final boolean b2 = s.isEmpty();
                            if (b2) {
                                return TargetRelation.INHERITOR;
                            }
                        }
                        catch (IllegalArgumentException ex8) {
                            throw c(ex8);
                        }
                    }
                    try {
                        if (categoryName2 == null) {
                            break Label_0145;
                        }
                        final String s2 = categoryName2;
                        final boolean b3 = s2.isEmpty();
                        if (b3) {
                            break Label_0145;
                        }
                        return TargetRelation.UNRELATED;
                    }
                    catch (IllegalArgumentException ex9) {
                        throw c(ex9);
                    }
                }
                try {
                    final String s2 = categoryName2;
                    final boolean b3 = s2.isEmpty();
                    if (b3) {
                        return TargetRelation.ANCESTOR;
                    }
                }
                catch (IllegalArgumentException ex10) {
                    throw c(ex10);
                }
            }
            return TargetRelation.UNRELATED;
        }
        try {
            if (ocClassSymbol.isSubclass(ocClassSymbol2)) {
                return TargetRelation.INHERITOR;
            }
        }
        catch (IllegalArgumentException ex11) {
            throw c(ex11);
        }
        try {
            if (ocClassSymbol2.isSubclass(ocClassSymbol)) {
                return TargetRelation.ANCESTOR;
            }
        }
        catch (IllegalArgumentException ex12) {
            throw c(ex12);
        }
        return TargetRelation.UNRELATED;
    }
    
    @Override
    protected MemberInfoModel<OCSymbolHolderVirtualPsiElement, OCMemberInfo> createModel() {
        try {
            if (this.myTargetClassField == null) {
                return null;
            }
        }
        catch (IllegalArgumentException ex) {
            throw c(ex);
        }
        final OCSymbol classDeclaration = this.myTargetClassField.getClassDeclaration(this.myProject);
        final TargetRelation a = this.a();
        try {
            if (a == null) {
                return (MemberInfoModel<OCSymbolHolderVirtualPsiElement, OCMemberInfo>)this.myUnrelatedModel;
            }
        }
        catch (IllegalArgumentException ex2) {
            throw c(ex2);
        }
        Label_0094: {
            try {
                switch (a) {
                    case SAME_CLASS: {
                        return (MemberInfoModel<OCSymbolHolderVirtualPsiElement, OCMemberInfo>)this.mySameClassModel;
                    }
                    case INHERITOR: {
                        break;
                    }
                    case ANCESTOR: {
                        break Label_0094;
                    }
                    case UNRELATED: {
                        return (MemberInfoModel<OCSymbolHolderVirtualPsiElement, OCMemberInfo>)this.myUnrelatedModel;
                    }
                    default: {
                        return (MemberInfoModel<OCSymbolHolderVirtualPsiElement, OCMemberInfo>)this.myUnrelatedModel;
                    }
                }
            }
            catch (IllegalArgumentException ex3) {
                throw c(ex3);
            }
            return (MemberInfoModel<OCSymbolHolderVirtualPsiElement, OCMemberInfo>)this.myInheritorModel;
        }
        ((AbstractUsesDependencyMemberInfoModel)this.myAncestorModel).setSuperClass(classDeclaration.locateDefinition());
        return (MemberInfoModel<OCSymbolHolderVirtualPsiElement, OCMemberInfo>)this.myAncestorModel;
    }
    
    @Override
    protected boolean isMemberEnabled(final OCMemberInfo p0) {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     0: aload_0        
        //     1: invokespecial   com/jetbrains/cidr/lang/refactoring/move/ui/OCMoveMembersDialog.b:()Lcom/jetbrains/cidr/lang/symbols/OCSymbol;
        //     4: astore_2       
        //     5: aload_2        
        //     6: ifnonnull       15
        //     9: iconst_1       
        //    10: ireturn        
        //    11: invokestatic    com/jetbrains/cidr/lang/refactoring/move/ui/OCMoveMembersDialog.c:(Ljava/lang/Exception;)Ljava/lang/Exception;
        //    14: athrow         
        //    15: aload_2        
        //    16: instanceof      Lcom/jetbrains/cidr/lang/symbols/objc/OCClassSymbol;
        //    19: ifeq            49
        //    22: aload_2        
        //    23: checkcast       Lcom/jetbrains/cidr/lang/symbols/objc/OCClassSymbol;
        //    26: invokeinterface com/jetbrains/cidr/lang/symbols/objc/OCClassSymbol.getCategoryName:()Ljava/lang/String;
        //    31: ifnull          49
        //    34: goto            41
        //    37: invokestatic    com/jetbrains/cidr/lang/refactoring/move/ui/OCMoveMembersDialog.c:(Ljava/lang/Exception;)Ljava/lang/Exception;
        //    40: athrow         
        //    41: iconst_1       
        //    42: goto            50
        //    45: invokestatic    com/jetbrains/cidr/lang/refactoring/move/ui/OCMoveMembersDialog.c:(Ljava/lang/Exception;)Ljava/lang/Exception;
        //    48: athrow         
        //    49: iconst_0       
        //    50: istore_3       
        //    51: aload_2        
        //    52: instanceof      Lcom/jetbrains/cidr/lang/symbols/objc/OCProtocolSymbol;
        //    55: ifne            69
        //    58: iload_3        
        //    59: ifeq            92
        //    62: goto            69
        //    65: invokestatic    com/jetbrains/cidr/lang/refactoring/move/ui/OCMoveMembersDialog.c:(Ljava/lang/Exception;)Ljava/lang/Exception;
        //    68: athrow         
        //    69: aload_1        
        //    70: invokevirtual   com/jetbrains/cidr/lang/refactoring/move/OCMemberInfo.getSymbol:()Lcom/jetbrains/cidr/lang/symbols/OCSymbol;
        //    73: instanceof      Lcom/jetbrains/cidr/lang/symbols/objc/OCInstanceVariableSymbol;
        //    76: ifeq            92
        //    79: goto            86
        //    82: invokestatic    com/jetbrains/cidr/lang/refactoring/move/ui/OCMoveMembersDialog.c:(Ljava/lang/Exception;)Ljava/lang/Exception;
        //    85: athrow         
        //    86: iconst_0       
        //    87: ireturn        
        //    88: invokestatic    com/jetbrains/cidr/lang/refactoring/move/ui/OCMoveMembersDialog.c:(Ljava/lang/Exception;)Ljava/lang/Exception;
        //    91: athrow         
        //    92: iload_3        
        //    93: ifeq            148
        //    96: aload_1        
        //    97: invokevirtual   com/jetbrains/cidr/lang/refactoring/move/OCMemberInfo.getSymbol:()Lcom/jetbrains/cidr/lang/symbols/OCSymbol;
        //   100: instanceof      Lcom/jetbrains/cidr/lang/symbols/objc/OCPropertySymbol;
        //   103: ifeq            148
        //   106: goto            113
        //   109: invokestatic    com/jetbrains/cidr/lang/refactoring/move/ui/OCMoveMembersDialog.c:(Ljava/lang/Exception;)Ljava/lang/Exception;
        //   112: athrow         
        //   113: aload_1        
        //   114: invokevirtual   com/jetbrains/cidr/lang/refactoring/move/OCMemberInfo.getSymbol:()Lcom/jetbrains/cidr/lang/symbols/OCSymbol;
        //   117: checkcast       Lcom/jetbrains/cidr/lang/symbols/objc/OCPropertySymbol;
        //   120: new             Lcom/intellij/util/CommonProcessors$FindFirstProcessor;
        //   123: dup            
        //   124: invokespecial   com/intellij/util/CommonProcessors$FindFirstProcessor.<init>:()V
        //   127: invokeinterface com/jetbrains/cidr/lang/symbols/objc/OCPropertySymbol.processSynthesizes:(Lcom/intellij/util/Processor;)Z
        //   132: ifne            148
        //   135: goto            142
        //   138: invokestatic    com/jetbrains/cidr/lang/refactoring/move/ui/OCMoveMembersDialog.c:(Ljava/lang/Exception;)Ljava/lang/Exception;
        //   141: athrow         
        //   142: iconst_0       
        //   143: ireturn        
        //   144: invokestatic    com/jetbrains/cidr/lang/refactoring/move/ui/OCMoveMembersDialog.c:(Ljava/lang/Exception;)Ljava/lang/Exception;
        //   147: athrow         
        //   148: aload_0        
        //   149: getfield        com/jetbrains/cidr/lang/refactoring/move/ui/OCMoveMembersDialog.myMemberInfoModel:Lcom/intellij/refactoring/classMembers/MemberInfoModel;
        //   152: aload_0        
        //   153: getfield        com/jetbrains/cidr/lang/refactoring/move/ui/OCMoveMembersDialog.myAncestorModel:Lcom/intellij/refactoring/classMembers/DependencyMemberInfoModel;
        //   156: if_acmpne       202
        //   159: aload_0        
        //   160: getfield        com/jetbrains/cidr/lang/refactoring/move/ui/OCMoveMembersDialog.myStorage:Lcom/jetbrains/cidr/lang/refactoring/move/OCMemberInfoStorage;
        //   163: aload_2        
        //   164: invokeinterface com/jetbrains/cidr/lang/symbols/OCSymbol.locateDefinition:()Lcom/intellij/psi/PsiElement;
        //   169: invokevirtual   com/jetbrains/cidr/lang/refactoring/move/OCMemberInfoStorage.getExtending:(Lcom/intellij/psi/PsiElement;)Ljava/util/Set;
        //   172: aload_1        
        //   173: invokevirtual   com/jetbrains/cidr/lang/refactoring/move/OCMemberInfo.getSymbol:()Lcom/jetbrains/cidr/lang/symbols/OCSymbol;
        //   176: invokeinterface com/jetbrains/cidr/lang/symbols/OCSymbol.locateDefinition:()Lcom/intellij/psi/PsiElement;
        //   181: invokeinterface java/util/Set.contains:(Ljava/lang/Object;)Z
        //   186: ifeq            202
        //   189: goto            196
        //   192: invokestatic    com/jetbrains/cidr/lang/refactoring/move/ui/OCMoveMembersDialog.c:(Ljava/lang/Exception;)Ljava/lang/Exception;
        //   195: athrow         
        //   196: iconst_0       
        //   197: ireturn        
        //   198: invokestatic    com/jetbrains/cidr/lang/refactoring/move/ui/OCMoveMembersDialog.c:(Ljava/lang/Exception;)Ljava/lang/Exception;
        //   201: athrow         
        //   202: iconst_1       
        //   203: ireturn        
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                                
        //  -----  -----  -----  -----  ------------------------------------
        //  5      11     11     15     Ljava/lang/IllegalArgumentException;
        //  15     34     37     41     Ljava/lang/IllegalArgumentException;
        //  22     45     45     49     Ljava/lang/IllegalArgumentException;
        //  51     62     65     69     Ljava/lang/IllegalArgumentException;
        //  58     79     82     86     Ljava/lang/IllegalArgumentException;
        //  69     88     88     92     Ljava/lang/IllegalArgumentException;
        //  92     106    109    113    Ljava/lang/IllegalArgumentException;
        //  96     135    138    142    Ljava/lang/IllegalArgumentException;
        //  113    144    144    148    Ljava/lang/IllegalArgumentException;
        //  148    189    192    196    Ljava/lang/IllegalArgumentException;
        //  159    198    198    202    Ljava/lang/IllegalArgumentException;
        // 
        // The error that occurred was:
        // 
        // java.lang.IllegalStateException: Expression is linked from several locations: Label_0069:
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
    protected OCMoveProcessor createProcessor() {
        final OCSymbol b = this.b();
        Object o = null;
        Label_0023: {
            try {
                if (b != null) {
                    o = Collections.singletonList(b);
                    break Label_0023;
                }
            }
            catch (IllegalArgumentException ex) {
                throw c(ex);
            }
            o = Collections.emptyList();
        }
        final Object o2 = o;
        try {
            if (this.mySourceClass instanceof OCClassDeclaration) {
                return new OCMoveObjCProcessor((OCClassDeclaration)this.mySourceClass, this.getSelectedMemberInfos(), this.getTargetName(), o2) {
                    @Override
                    protected String getCommandName() {
                        return OCMoveMembersDialog.this.getTitle();
                    }
                    
                    @Nullable
                    @Override
                    protected OCNewFileActionBase getNewClassAction() {
                        return new OCNewClassAction();
                    }
                };
            }
        }
        catch (IllegalArgumentException ex2) {
            throw c(ex2);
        }
        try {
            if (this.mySourceClass instanceof OCStruct) {
                return new OCMoveCppProcessor((OCStruct)this.mySourceClass, this.getSelectedMemberInfos(), this.getTargetName(), o2) {
                    @Override
                    protected String getCommandName() {
                        return OCMoveMembersDialog.this.getTitle();
                    }
                    
                    @Nullable
                    @Override
                    protected OCNewFileActionBase getNewClassAction() {
                        return new OCNewCppClassAction();
                    }
                };
            }
        }
        catch (IllegalArgumentException ex3) {
            throw c(ex3);
        }
        try {
            assert false : this.mySourceClass;
        }
        catch (IllegalArgumentException ex4) {
            throw c(ex4);
        }
        return null;
    }
    
    @Override
    protected String getConflictMessage(final OCMemberInfo ocMemberInfo, final OCSymbol ocSymbol) {
        try {
            if (this.myMemberInfoModel == this.myAncestorModel) {
                return ocSymbol.getNameWithKindUppercase() + " will be inaccessible in the moved code";
            }
        }
        catch (IllegalArgumentException ex) {
            throw c(ex);
        }
        try {
            if (this.myMemberInfoModel == this.myInheritorModel) {
                return "Moved code will be inaccessible from the " + ocSymbol.getNameWithKindLowercase();
            }
        }
        catch (IllegalArgumentException ex2) {
            throw c(ex2);
        }
        try {
            if (this.myAncestorModel.checkForProblems(ocMemberInfo) == 2) {
                return ocSymbol.getNameWithKindUppercase() + " will be inaccessible in the moved code";
            }
        }
        catch (IllegalArgumentException ex3) {
            throw c(ex3);
        }
        try {
            if (this.myInheritorModel.checkForProblems(ocMemberInfo) == 2) {
                return "Moved code will be inaccessible from the " + ocSymbol.getNameWithKindLowercase();
            }
        }
        catch (IllegalArgumentException ex4) {
            throw c(ex4);
        }
        try {
            assert false;
        }
        catch (IllegalArgumentException ex5) {
            throw c(ex5);
        }
        return null;
    }
    
    static {
        boolean $assertionsDisabled2 = false;
        Label_0017: {
            try {
                if (!OCMoveMembersDialog.class.desiredAssertionStatus()) {
                    $assertionsDisabled2 = true;
                    break Label_0017;
                }
            }
            catch (IllegalArgumentException ex) {
                throw c(ex);
            }
            $assertionsDisabled2 = false;
        }
        $assertionsDisabled = $assertionsDisabled2;
    }
    
    private static Exception c(final Exception ex) {
        return ex;
    }
    
    private enum TargetRelation
    {
        SAME_CLASS, 
        INHERITOR, 
        ANCESTOR, 
        UNRELATED;
    }
}
