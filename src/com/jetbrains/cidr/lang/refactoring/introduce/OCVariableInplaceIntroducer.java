// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.lang.refactoring.introduce;

import com.intellij.psi.PsiNameIdentifierOwner;
import com.intellij.openapi.actionSystem.Shortcut;
import com.intellij.openapi.keymap.KeymapManager;
import org.jetbrains.annotations.NotNull;
import com.jetbrains.cidr.lang.util.OCElementFactory;
import org.jetbrains.annotations.Nullable;
import com.jetbrains.cidr.lang.psi.OCExpressionStatement;
import com.jetbrains.cidr.lang.psi.OCAssignmentExpression;
import com.jetbrains.cidr.lang.symbols.OCSymbolKind;
import com.jetbrains.cidr.lang.refactoring.util.OCChangeUtil;
import com.jetbrains.cidr.lang.psi.OCStatement;
import com.jetbrains.cidr.lang.psi.OCDeclarationStatement;
import com.intellij.psi.codeStyle.CodeStyleSettingsManager;
import com.jetbrains.cidr.lang.settings.OCCodeStyleSettings;
import com.intellij.psi.PsiElement;
import java.util.List;
import com.intellij.openapi.editor.Editor;
import com.intellij.openapi.project.Project;
import javax.swing.JCheckBox;
import com.jetbrains.cidr.lang.psi.OCExpression;
import com.jetbrains.cidr.lang.psi.OCDeclarator;

public class OCVariableInplaceIntroducer extends OCBaseExpressionInplaceIntroducer<OCDeclarator, OCExpression>
{
    private JCheckBox myAutoCB;
    private JCheckBox myConstCB;
    private boolean myCreateFromAssignment;
    
    public OCVariableInplaceIntroducer(final Project project, final Editor editor, final OCExpression ocExpression, final List<OCExpression> list, final String s) {
        super(project, editor, (PsiElement)ocExpression, (List<PsiElement>)list, OCDeclarator.class, (Class<PsiElement>)OCExpression.class, s);
    }
    
    @Override
    public void configurePanel() {
        super.configurePanel();
        final OCCodeStyleSettings ocCodeStyleSettings = (OCCodeStyleSettings)CodeStyleSettingsManager.getSettings(this.myProject).getCustomSettings((Class)OCCodeStyleSettings.class);
        try {
            if (this.isAutoAvailable()) {
                this.myAutoCB = (JCheckBox)this.createCheckBox("Declare &auto", ocCodeStyleSettings.INTRODUCE_AUTO_VARS, () -> {
                    ocCodeStyleSettings.INTRODUCE_AUTO_VARS = this.myAutoCB.isSelected();
                    this.g();
                    return;
                });
            }
        }
        catch (IllegalStateException ex) {
            throw b(ex);
        }
        try {
            if (this.isConstAvailable()) {
                this.myConstCB = (JCheckBox)this.createCheckBox("Declare &const", ocCodeStyleSettings.INTRODUCE_CONST_VARS, () -> {
                    ocCodeStyleSettings.INTRODUCE_CONST_VARS = this.myConstCB.isSelected();
                    this.g();
                });
            }
        }
        catch (IllegalStateException ex2) {
            throw b(ex2);
        }
    }
    
    private void g() {
        final OCDeclarator ocDeclarator = ((OCBaseInplaceIntroducer<OCDeclarator, E>)this).getVariable();
        if (ocDeclarator != null) {
            this.myEditor.getCaretModel().moveToOffset(this.rebuildDeclarator(ocDeclarator, new Rebuilder() {
                @Override
                public OCDeclarator rebuild(final OCDeclarator ocDeclarator) {
                    return ((OCDeclarationStatement)OCChangeUtil.replaceHandlingMacros(ocDeclarator.getParent().getParent(), (PsiElement)OCVariableInplaceIntroducer.this.a(ocDeclarator.getName(), ocDeclarator.getInitializer(), (PsiElement)ocDeclarator))).getDeclaration().getDeclarators().get(0);
                }
            }).getNameIdentifier().getTextRange().getStartOffset());
        }
    }
    
    @Override
    protected OCSymbolKind getDeclaratorKind() {
        return OCSymbolKind.LOCAL_VARIABLE;
    }
    
    public boolean isPreviewDisabled() {
        return true;
    }
    
    @Nullable
    private OCExpression a(final OCExpression ocExpression) {
        try {
            if (!this.isCreateFromUsageMode()) {
                return ocExpression;
            }
        }
        catch (IllegalStateException ex) {
            throw b(ex);
        }
        Label_0049: {
            try {
                if (!(ocExpression.getParent() instanceof OCAssignmentExpression)) {
                    return null;
                }
                final OCExpression ocExpression2 = ocExpression;
                final PsiElement psiElement = ocExpression2.getParent();
                final PsiElement psiElement2 = psiElement.getParent();
                final boolean b = psiElement2 instanceof OCExpressionStatement;
                if (b) {
                    break Label_0049;
                }
                return null;
            }
            catch (IllegalStateException ex2) {
                throw b(ex2);
            }
            try {
                final OCExpression ocExpression2 = ocExpression;
                final PsiElement psiElement = ocExpression2.getParent();
                final PsiElement psiElement2 = psiElement.getParent();
                final boolean b = psiElement2 instanceof OCExpressionStatement;
                if (!b) {
                    return null;
                }
                if (ocExpression.getParent().getParent() != this.getAnchor()) {
                    return null;
                }
            }
            catch (IllegalStateException ex3) {
                throw b(ex3);
            }
        }
        final OCAssignmentExpression ocAssignmentExpression = (OCAssignmentExpression)ocExpression.getParent();
        try {
            if (ocAssignmentExpression.getReceiverExpression() == ocExpression) {
                return ocAssignmentExpression.getSourceExpression();
            }
        }
        catch (IllegalStateException ex4) {
            throw b(ex4);
        }
        return null;
    }
    
    private OCStatement a(final String s, final OCExpression ocExpression, final PsiElement psiElement) {
        boolean b2 = false;
        Label_0033: {
            Label_0024: {
                try {
                    if (this.myAutoCB == null) {
                        break Label_0024;
                    }
                    final OCVariableInplaceIntroducer ocVariableInplaceIntroducer = this;
                    final JCheckBox checkBox = ocVariableInplaceIntroducer.myAutoCB;
                    final boolean b = checkBox.isSelected();
                    if (b) {
                        break Label_0024;
                    }
                    break Label_0024;
                }
                catch (IllegalStateException ex) {
                    throw b(ex);
                }
                try {
                    final OCVariableInplaceIntroducer ocVariableInplaceIntroducer = this;
                    final JCheckBox checkBox = ocVariableInplaceIntroducer.myAutoCB;
                    final boolean b = checkBox.isSelected();
                    if (b) {
                        b2 = true;
                        break Label_0033;
                    }
                }
                catch (IllegalStateException ex2) {
                    throw b(ex2);
                }
            }
            b2 = false;
        }
        final boolean b3 = b2;
        Label_0059: {
            try {
                if (this.myConstCB == null) {
                    break Label_0059;
                }
                final OCVariableInplaceIntroducer ocVariableInplaceIntroducer2 = this;
                final JCheckBox checkBox2 = ocVariableInplaceIntroducer2.myConstCB;
                final boolean b4 = checkBox2.isSelected();
                if (b4) {
                    break Label_0059;
                }
                break Label_0059;
            }
            catch (IllegalStateException ex3) {
                throw b(ex3);
            }
            try {
                final OCVariableInplaceIntroducer ocVariableInplaceIntroducer2 = this;
                final JCheckBox checkBox2 = ocVariableInplaceIntroducer2.myConstCB;
                final boolean b4 = checkBox2.isSelected();
                if (b4) {
                    final boolean b5 = true;
                    return OCElementFactory.declarationStatement(s, this.getType((PsiElement)ocExpression, b3, b5), ocExpression, psiElement);
                }
            }
            catch (IllegalStateException ex4) {
                throw b(ex4);
            }
        }
        final boolean b5 = false;
        return OCElementFactory.declarationStatement(s, this.getType((PsiElement)ocExpression, b3, b5), ocExpression, psiElement);
    }
    
    public void introduceForPreview(final String p0) {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     0: aload_0        
        //     1: invokevirtual   com/jetbrains/cidr/lang/refactoring/introduce/OCVariableInplaceIntroducer.getMainExpression:()Lcom/intellij/psi/PsiElement;
        //     4: checkcast       Lcom/jetbrains/cidr/lang/psi/OCExpression;
        //     7: astore_2       
        //     8: aload_0        
        //     9: invokevirtual   com/jetbrains/cidr/lang/refactoring/introduce/OCVariableInplaceIntroducer.getAnchor:()Lcom/intellij/psi/PsiElement;
        //    12: astore_3       
        //    13: aload_0        
        //    14: aload_1        
        //    15: aload_0        
        //    16: aload_2        
        //    17: invokespecial   com/jetbrains/cidr/lang/refactoring/introduce/OCVariableInplaceIntroducer.a:(Lcom/jetbrains/cidr/lang/psi/OCExpression;)Lcom/jetbrains/cidr/lang/psi/OCExpression;
        //    20: aload_3        
        //    21: invokeinterface com/intellij/psi/PsiElement.getParent:()Lcom/intellij/psi/PsiElement;
        //    26: invokespecial   com/jetbrains/cidr/lang/refactoring/introduce/OCVariableInplaceIntroducer.a:(Ljava/lang/String;Lcom/jetbrains/cidr/lang/psi/OCExpression;Lcom/intellij/psi/PsiElement;)Lcom/jetbrains/cidr/lang/psi/OCStatement;
        //    29: astore          4
        //    31: aload_0        
        //    32: aload_3        
        //    33: invokevirtual   com/jetbrains/cidr/lang/refactoring/introduce/OCVariableInplaceIntroducer.ensureParentIsBlockStatement:(Lcom/intellij/psi/PsiElement;)Lcom/intellij/psi/PsiElement;
        //    36: astore_3       
        //    37: aload_0        
        //    38: aload_0        
        //    39: invokevirtual   com/jetbrains/cidr/lang/refactoring/introduce/OCVariableInplaceIntroducer.isCreateFromUsageMode:()Z
        //    42: ifeq            68
        //    45: aload_0        
        //    46: aload_2        
        //    47: invokespecial   com/jetbrains/cidr/lang/refactoring/introduce/OCVariableInplaceIntroducer.a:(Lcom/jetbrains/cidr/lang/psi/OCExpression;)Lcom/jetbrains/cidr/lang/psi/OCExpression;
        //    50: ifnull          68
        //    53: goto            60
        //    56: invokestatic    com/jetbrains/cidr/lang/refactoring/introduce/OCVariableInplaceIntroducer.b:(Ljava/lang/IllegalStateException;)Ljava/lang/IllegalStateException;
        //    59: athrow         
        //    60: iconst_1       
        //    61: goto            69
        //    64: invokestatic    com/jetbrains/cidr/lang/refactoring/introduce/OCVariableInplaceIntroducer.b:(Ljava/lang/IllegalStateException;)Ljava/lang/IllegalStateException;
        //    67: athrow         
        //    68: iconst_0       
        //    69: putfield        com/jetbrains/cidr/lang/refactoring/introduce/OCVariableInplaceIntroducer.myCreateFromAssignment:Z
        //    72: aload_2        
        //    73: invokeinterface com/jetbrains/cidr/lang/psi/OCExpression.getParent:()Lcom/intellij/psi/PsiElement;
        //    78: instanceof      Lcom/jetbrains/cidr/lang/psi/OCExpressionStatement;
        //    81: ifeq            108
        //    84: aload_2        
        //    85: invokeinterface com/jetbrains/cidr/lang/psi/OCExpression.getParent:()Lcom/intellij/psi/PsiElement;
        //    90: invokeinterface com/intellij/psi/PsiElement.getParent:()Lcom/intellij/psi/PsiElement;
        //    95: instanceof      Lcom/jetbrains/cidr/lang/psi/OCMacroCallArgument;
        //    98: ifeq            122
        //   101: goto            108
        //   104: invokestatic    com/jetbrains/cidr/lang/refactoring/introduce/OCVariableInplaceIntroducer.b:(Ljava/lang/IllegalStateException;)Ljava/lang/IllegalStateException;
        //   107: athrow         
        //   108: aload_0        
        //   109: getfield        com/jetbrains/cidr/lang/refactoring/introduce/OCVariableInplaceIntroducer.myCreateFromAssignment:Z
        //   112: ifeq            173
        //   115: goto            122
        //   118: invokestatic    com/jetbrains/cidr/lang/refactoring/introduce/OCVariableInplaceIntroducer.b:(Ljava/lang/IllegalStateException;)Ljava/lang/IllegalStateException;
        //   121: athrow         
        //   122: aload_2        
        //   123: invokeinterface com/jetbrains/cidr/lang/psi/OCExpression.isValid:()Z
        //   128: ifeq            146
        //   131: goto            138
        //   134: invokestatic    com/jetbrains/cidr/lang/refactoring/introduce/OCVariableInplaceIntroducer.b:(Ljava/lang/IllegalStateException;)Ljava/lang/IllegalStateException;
        //   137: athrow         
        //   138: aload_2        
        //   139: goto            147
        //   142: invokestatic    com/jetbrains/cidr/lang/refactoring/introduce/OCVariableInplaceIntroducer.b:(Ljava/lang/IllegalStateException;)Ljava/lang/IllegalStateException;
        //   145: athrow         
        //   146: aload_3        
        //   147: ldc             Lcom/jetbrains/cidr/lang/psi/OCStatement;.class
        //   149: iconst_0       
        //   150: invokestatic    com/intellij/psi/util/PsiTreeUtil.getParentOfType:(Lcom/intellij/psi/PsiElement;Ljava/lang/Class;Z)Lcom/intellij/psi/PsiElement;
        //   153: checkcast       Lcom/jetbrains/cidr/lang/psi/OCStatement;
        //   156: astore          6
        //   158: aload           6
        //   160: aload           4
        //   162: invokestatic    com/jetbrains/cidr/lang/refactoring/util/OCChangeUtil.replaceHandlingMacros:(Lcom/intellij/psi/PsiElement;Lcom/intellij/psi/PsiElement;)Lcom/intellij/psi/PsiElement;
        //   165: checkcast       Lcom/jetbrains/cidr/lang/psi/OCDeclarationStatement;
        //   168: astore          5
        //   170: goto            190
        //   173: aload_3        
        //   174: invokeinterface com/intellij/psi/PsiElement.getParent:()Lcom/intellij/psi/PsiElement;
        //   179: aload           4
        //   181: aload_3        
        //   182: invokestatic    com/jetbrains/cidr/lang/refactoring/util/OCChangeUtil.addBefore:(Lcom/intellij/psi/PsiElement;Lcom/intellij/psi/PsiElement;Lcom/intellij/psi/PsiElement;)Lcom/intellij/psi/PsiElement;
        //   185: checkcast       Lcom/jetbrains/cidr/lang/psi/OCDeclarationStatement;
        //   188: astore          5
        //   190: aload_0        
        //   191: aload           5
        //   193: invokeinterface com/jetbrains/cidr/lang/psi/OCDeclarationStatement.getDeclaration:()Lcom/jetbrains/cidr/lang/psi/OCDeclaration;
        //   198: invokeinterface com/jetbrains/cidr/lang/psi/OCDeclaration.getDeclarators:()Ljava/util/List;
        //   203: iconst_0       
        //   204: invokeinterface java/util/List.get:(I)Ljava/lang/Object;
        //   209: checkcast       Lcom/intellij/psi/PsiNameIdentifierOwner;
        //   212: invokevirtual   com/jetbrains/cidr/lang/refactoring/introduce/OCVariableInplaceIntroducer.setVariable:(Lcom/intellij/psi/PsiNameIdentifierOwner;)V
        //   215: return         
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                             
        //  -----  -----  -----  -----  ---------------------------------
        //  37     53     56     60     Ljava/lang/IllegalStateException;
        //  45     64     64     68     Ljava/lang/IllegalStateException;
        //  69     101    104    108    Ljava/lang/IllegalStateException;
        //  84     115    118    122    Ljava/lang/IllegalStateException;
        //  108    131    134    138    Ljava/lang/IllegalStateException;
        //  122    142    142    146    Ljava/lang/IllegalStateException;
        // 
        // The error that occurred was:
        // 
        // java.lang.IllegalStateException: Expression is linked from several locations: Label_0108:
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
    
    @Override
    protected void introduceForReal(final String p0) {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     0: aload_0        
        //     1: aload_1        
        //     2: invokevirtual   com/jetbrains/cidr/lang/refactoring/introduce/OCVariableInplaceIntroducer.introduceForPreview:(Ljava/lang/String;)V
        //     5: iconst_0       
        //     6: istore_2       
        //     7: aload_0        
        //     8: getfield        com/jetbrains/cidr/lang/refactoring/introduce/OCVariableInplaceIntroducer.myOccurrences:[Lcom/intellij/psi/PsiElement;
        //    11: checkcast       [Lcom/jetbrains/cidr/lang/psi/OCExpression;
        //    14: astore_3       
        //    15: aload_3        
        //    16: arraylength    
        //    17: istore          4
        //    19: iconst_0       
        //    20: istore          5
        //    22: iload           5
        //    24: iload           4
        //    26: if_icmpge       88
        //    29: aload_3        
        //    30: iload           5
        //    32: aaload         
        //    33: astore          6
        //    35: aload_0        
        //    36: aload_1        
        //    37: invokevirtual   com/jetbrains/cidr/lang/refactoring/introduce/OCVariableInplaceIntroducer.changeUsages:(Ljava/lang/String;)Z
        //    40: ifeq            62
        //    43: aload           6
        //    45: aload_1        
        //    46: aload           6
        //    48: invokestatic    com/jetbrains/cidr/lang/util/OCElementFactory.expressionFromText:(Ljava/lang/String;Lcom/intellij/psi/PsiElement;)Lcom/jetbrains/cidr/lang/psi/OCExpression;
        //    51: invokestatic    com/jetbrains/cidr/lang/refactoring/util/OCChangeUtil.replaceHandlingMacros:(Lcom/intellij/psi/PsiElement;Lcom/intellij/psi/PsiElement;)Lcom/intellij/psi/PsiElement;
        //    54: pop            
        //    55: goto            62
        //    58: invokestatic    com/jetbrains/cidr/lang/refactoring/introduce/OCVariableInplaceIntroducer.b:(Ljava/lang/IllegalStateException;)Ljava/lang/IllegalStateException;
        //    61: athrow         
        //    62: new             Lcom/jetbrains/cidr/lang/search/usages/OCReadWriteAccessDetector;
        //    65: dup            
        //    66: invokespecial   com/jetbrains/cidr/lang/search/usages/OCReadWriteAccessDetector.<init>:()V
        //    69: aload           6
        //    71: invokevirtual   com/jetbrains/cidr/lang/search/usages/OCReadWriteAccessDetector.getExpressionAccess:(Lcom/intellij/psi/PsiElement;)Lcom/intellij/codeInsight/highlighting/ReadWriteAccessDetector$Access;
        //    74: getstatic       com/intellij/codeInsight/highlighting/ReadWriteAccessDetector$Access.Write:Lcom/intellij/codeInsight/highlighting/ReadWriteAccessDetector$Access;
        //    77: if_acmpne       82
        //    80: iconst_1       
        //    81: istore_2       
        //    82: iinc            5, 1
        //    85: goto            22
        //    88: aload_0        
        //    89: invokevirtual   com/jetbrains/cidr/lang/refactoring/introduce/OCVariableInplaceIntroducer.getVariable:()Lcom/intellij/psi/PsiNameIdentifierOwner;
        //    92: checkcast       Lcom/jetbrains/cidr/lang/psi/OCDeclarator;
        //    95: astore_3       
        //    96: aload_0        
        //    97: invokevirtual   com/jetbrains/cidr/lang/refactoring/introduce/OCVariableInplaceIntroducer.isCreateFromUsageMode:()Z
        //   100: ifeq            207
        //   103: aload_0        
        //   104: getfield        com/jetbrains/cidr/lang/refactoring/introduce/OCVariableInplaceIntroducer.myCreateFromAssignment:Z
        //   107: ifne            207
        //   110: goto            117
        //   113: invokestatic    com/jetbrains/cidr/lang/refactoring/introduce/OCVariableInplaceIntroducer.b:(Ljava/lang/IllegalStateException;)Ljava/lang/IllegalStateException;
        //   116: athrow         
        //   117: iload_2        
        //   118: ifne            207
        //   121: goto            128
        //   124: invokestatic    com/jetbrains/cidr/lang/refactoring/introduce/OCVariableInplaceIntroducer.b:(Ljava/lang/IllegalStateException;)Ljava/lang/IllegalStateException;
        //   127: athrow         
        //   128: aload_3        
        //   129: ifnull          207
        //   132: goto            139
        //   135: invokestatic    com/jetbrains/cidr/lang/refactoring/introduce/OCVariableInplaceIntroducer.b:(Ljava/lang/IllegalStateException;)Ljava/lang/IllegalStateException;
        //   138: athrow         
        //   139: new             Lcom/jetbrains/cidr/lang/quickfixes/OCAddInitializerIntentionAction;
        //   142: dup            
        //   143: aload_3        
        //   144: aload_3        
        //   145: invokeinterface com/jetbrains/cidr/lang/psi/OCDeclarator.getSymbol:()Lcom/jetbrains/cidr/lang/symbols/OCSymbol;
        //   150: invokespecial   com/jetbrains/cidr/lang/quickfixes/OCAddInitializerIntentionAction.<init>:(Lcom/jetbrains/cidr/lang/psi/OCDeclarator;Lcom/jetbrains/cidr/lang/symbols/OCSymbol;)V
        //   153: astore          4
        //   155: aload           4
        //   157: aload_3        
        //   158: invokeinterface com/jetbrains/cidr/lang/psi/OCDeclarator.getProject:()Lcom/intellij/openapi/project/Project;
        //   163: aload_0        
        //   164: getfield        com/jetbrains/cidr/lang/refactoring/introduce/OCVariableInplaceIntroducer.myEditor:Lcom/intellij/openapi/editor/Editor;
        //   167: aload_3        
        //   168: invokeinterface com/jetbrains/cidr/lang/psi/OCDeclarator.getContainingFile:()Lcom/intellij/psi/PsiFile;
        //   173: invokevirtual   com/jetbrains/cidr/lang/quickfixes/OCAddInitializerIntentionAction.isAvailable:(Lcom/intellij/openapi/project/Project;Lcom/intellij/openapi/editor/Editor;Lcom/intellij/psi/PsiFile;)Z
        //   176: ifeq            207
        //   179: aload           4
        //   181: aload_3        
        //   182: invokeinterface com/jetbrains/cidr/lang/psi/OCDeclarator.getProject:()Lcom/intellij/openapi/project/Project;
        //   187: aload_0        
        //   188: getfield        com/jetbrains/cidr/lang/refactoring/introduce/OCVariableInplaceIntroducer.myEditor:Lcom/intellij/openapi/editor/Editor;
        //   191: aload_3        
        //   192: invokeinterface com/jetbrains/cidr/lang/psi/OCDeclarator.getContainingFile:()Lcom/intellij/psi/PsiFile;
        //   197: invokevirtual   com/jetbrains/cidr/lang/quickfixes/OCAddInitializerIntentionAction.invoke:(Lcom/intellij/openapi/project/Project;Lcom/intellij/openapi/editor/Editor;Lcom/intellij/psi/PsiFile;)V
        //   200: goto            207
        //   203: invokestatic    com/jetbrains/cidr/lang/refactoring/introduce/OCVariableInplaceIntroducer.b:(Ljava/lang/IllegalStateException;)Ljava/lang/IllegalStateException;
        //   206: athrow         
        //   207: return         
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                             
        //  -----  -----  -----  -----  ---------------------------------
        //  35     55     58     62     Ljava/lang/IllegalStateException;
        //  96     110    113    117    Ljava/lang/IllegalStateException;
        //  103    121    124    128    Ljava/lang/IllegalStateException;
        //  117    132    135    139    Ljava/lang/IllegalStateException;
        //  155    200    203    207    Ljava/lang/IllegalStateException;
        // 
        // The error that occurred was:
        // 
        // java.lang.IllegalStateException: Expression is linked from several locations: Label_0117:
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
    protected String getFeatureID() {
        String s;
        try {
            s = "refactoring.appCodeIntroduceVariable";
            if (s == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/refactoring/introduce/OCVariableInplaceIntroducer", "getFeatureID"));
            }
        }
        catch (IllegalStateException ex) {
            throw b(ex);
        }
        return s;
    }
    
    @Override
    protected void moveOffsetAfter(final boolean p0) {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     0: aload_0        
        //     1: iload_1        
        //     2: invokespecial   com/jetbrains/cidr/lang/refactoring/introduce/OCBaseExpressionInplaceIntroducer.moveOffsetAfter:(Z)V
        //     5: aload_0        
        //     6: invokevirtual   com/jetbrains/cidr/lang/refactoring/introduce/OCVariableInplaceIntroducer.getVariable:()Lcom/intellij/psi/PsiNameIdentifierOwner;
        //     9: checkcast       Lcom/jetbrains/cidr/lang/psi/OCDeclarator;
        //    12: astore_2       
        //    13: iload_1        
        //    14: ifeq            89
        //    17: aload_0        
        //    18: invokevirtual   com/jetbrains/cidr/lang/refactoring/introduce/OCVariableInplaceIntroducer.isCreateFromUsageMode:()Z
        //    21: ifeq            89
        //    24: goto            31
        //    27: invokestatic    com/jetbrains/cidr/lang/refactoring/introduce/OCVariableInplaceIntroducer.b:(Ljava/lang/IllegalStateException;)Ljava/lang/IllegalStateException;
        //    30: athrow         
        //    31: aload_2        
        //    32: ifnull          89
        //    35: goto            42
        //    38: invokestatic    com/jetbrains/cidr/lang/refactoring/introduce/OCVariableInplaceIntroducer.b:(Ljava/lang/IllegalStateException;)Ljava/lang/IllegalStateException;
        //    41: athrow         
        //    42: aload_0        
        //    43: getfield        com/jetbrains/cidr/lang/refactoring/introduce/OCVariableInplaceIntroducer.myEditor:Lcom/intellij/openapi/editor/Editor;
        //    46: invokeinterface com/intellij/openapi/editor/Editor.getCaretModel:()Lcom/intellij/openapi/editor/CaretModel;
        //    51: aload_2        
        //    52: invokeinterface com/jetbrains/cidr/lang/psi/OCDeclarator.getTextRange:()Lcom/intellij/openapi/util/TextRange;
        //    57: invokevirtual   com/intellij/openapi/util/TextRange.getEndOffset:()I
        //    60: invokeinterface com/intellij/openapi/editor/CaretModel.moveToOffset:(I)V
        //    65: aload_0        
        //    66: getfield        com/jetbrains/cidr/lang/refactoring/introduce/OCVariableInplaceIntroducer.myEditor:Lcom/intellij/openapi/editor/Editor;
        //    69: invokeinterface com/intellij/openapi/editor/Editor.getScrollingModel:()Lcom/intellij/openapi/editor/ScrollingModel;
        //    74: getstatic       com/intellij/openapi/editor/ScrollType.MAKE_VISIBLE:Lcom/intellij/openapi/editor/ScrollType;
        //    77: invokeinterface com/intellij/openapi/editor/ScrollingModel.scrollToCaret:(Lcom/intellij/openapi/editor/ScrollType;)V
        //    82: goto            89
        //    85: invokestatic    com/jetbrains/cidr/lang/refactoring/introduce/OCVariableInplaceIntroducer.b:(Ljava/lang/IllegalStateException;)Ljava/lang/IllegalStateException;
        //    88: athrow         
        //    89: return         
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                             
        //  -----  -----  -----  -----  ---------------------------------
        //  13     24     27     31     Ljava/lang/IllegalStateException;
        //  17     35     38     42     Ljava/lang/IllegalStateException;
        //  31     82     85     89     Ljava/lang/IllegalStateException;
        // 
        // The error that occurred was:
        // 
        // java.lang.IllegalStateException: Expression is linked from several locations: Label_0031:
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
    private static String a() {
        final Shortcut[] shortcuts = KeymapManager.getInstance().getActiveKeymap().getShortcuts("PreviousTemplateVariable");
        try {
            if (shortcuts.length > 0) {
                return "Press " + shortcuts[0] + " to change type";
            }
        }
        catch (IllegalStateException ex) {
            throw b(ex);
        }
        return null;
    }
    
    @Override
    protected void restoreState(@NotNull final OCDeclarator ocDeclarator) {
        try {
            if (ocDeclarator == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "variable", "com/jetbrains/cidr/lang/refactoring/introduce/OCVariableInplaceIntroducer", "restoreState"));
            }
        }
        catch (IllegalStateException ex) {
            throw b(ex);
        }
        this.myExprType = ocDeclarator.getResolvedType();
        super.restoreState(ocDeclarator);
    }
    
    private static IllegalStateException b(final IllegalStateException ex) {
        return ex;
    }
}
