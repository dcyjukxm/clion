// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.lang.generate.handlers;

import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import javax.swing.JLabel;
import javax.swing.ListCellRenderer;
import javax.swing.JList;
import com.intellij.ui.ListCellRendererWrapper;
import java.awt.Component;
import java.awt.LayoutManager;
import java.awt.BorderLayout;
import javax.swing.JComboBox;
import javax.swing.JPanel;
import java.util.Set;
import com.intellij.codeInsight.generation.MemberChooserObject;
import com.jetbrains.cidr.lang.symbols.symtable.OCMembersContainer;
import com.jetbrains.cidr.lang.generate.actions.OCActionContext;
import com.intellij.util.containers.ContainerUtil;
import com.jetbrains.cidr.lang.refactoring.util.OCChangeUtil;
import com.intellij.psi.PsiFile;
import com.intellij.openapi.editor.Editor;
import com.intellij.util.Processor;
import com.jetbrains.cidr.lang.symbols.OCSymbol;
import com.jetbrains.cidr.lang.workspace.compiler.OCCompilerFeatures;
import com.jetbrains.cidr.lang.symbols.objc.OCInstanceVariableSymbol;
import com.jetbrains.cidr.lang.symbols.objc.OCPropertySymbol;
import java.util.Map;
import com.jetbrains.cidr.lang.generate.OCCheckboxMemberChooser;
import com.jetbrains.cidr.lang.generate.OCMemberChooserObject;
import java.util.Collection;
import com.jetbrains.cidr.lang.generate.OCMemberChooser;
import com.jetbrains.cidr.lang.settings.OCOption;
import com.intellij.openapi.util.Pair;
import java.util.List;
import com.intellij.openapi.project.Project;
import org.jetbrains.annotations.Nullable;
import com.jetbrains.cidr.lang.types.OCObjectType;
import com.jetbrains.cidr.lang.symbols.objc.OCImplementationSymbol;
import com.jetbrains.cidr.lang.psi.OCClassDeclarationBase;
import com.intellij.psi.PsiElement;
import com.jetbrains.cidr.lang.psi.OCClassDeclaration;
import com.jetbrains.cidr.lang.psi.OCSymbolDeclarator;
import org.jetbrains.annotations.NotNull;
import com.jetbrains.cidr.lang.symbols.objc.OCMemberSymbol;
import com.jetbrains.cidr.lang.symbols.objc.OCClassSymbol;

public class OCDeclareMembersHandler extends OCClassActionHandlerBase<OCClassSymbol, OCMemberSymbol, OCDeclareActionContext>
{
    @Override
    protected String getActionTitle() {
        return "Declare Members";
    }
    
    @Override
    protected String getMembersChooserTitle() {
        return "Select Members to Declare";
    }
    
    @Override
    protected String getNoMembersMessage(@NotNull final OCDeclareActionContext ocDeclareActionContext) {
        try {
            if (ocDeclareActionContext == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "context", "com/jetbrains/cidr/lang/generate/handlers/OCDeclareMembersHandler", "getNoMembersMessage"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw b(ex);
        }
        return ocDeclareActionContext.getParentNameUppercase() + " has no members to declare";
    }
    
    @Override
    protected Class<? extends OCSymbolDeclarator> getParentClass() {
        return OCClassDeclaration.class;
    }
    
    @Nullable
    @Override
    protected OCDeclareActionContext evaluateActionContext(final OCClassSymbol ocClassSymbol, final PsiElement psiElement) {
        final OCObjectType resolvedType = ocClassSymbol.getResolvedType(true);
        final OCClassDeclarationBase ocClassDeclarationBase = ocClassSymbol.locateDefinition();
        try {
            if (!(ocClassDeclarationBase instanceof OCClassDeclaration)) {
                return null;
            }
        }
        catch (IllegalArgumentException ex) {
            throw b(ex);
        }
        final OCDeclareActionContext ocDeclareActionContext = new OCDeclareActionContext(ocClassSymbol, (OCClassDeclaration)ocClassDeclarationBase, resolvedType);
        OCDeclareActionContext.Target target = null;
        Label_0111: {
            Label_0089: {
                try {
                    if (!(ocClassSymbol instanceof OCImplementationSymbol)) {
                        if (!"".equals(ocClassSymbol.getCategoryName())) {
                            break Label_0089;
                        }
                    }
                }
                catch (IllegalArgumentException ex2) {
                    throw b(ex2);
                }
                target = OCDeclareActionContext.Target.INTERFACE;
                break Label_0111;
            }
            if (ocClassSymbol.getCategoryName() != null) {
                target = OCDeclareActionContext.Target.IMPLEMENTATION;
            }
            else {
                target = OCDeclareActionContext.Target.PRIVATE_CATEGORY;
            }
        }
        ocDeclareActionContext.setTarget(target);
        return ocDeclareActionContext;
    }
    
    @Override
    protected OCMemberChooser createMemberChooser(final Project project, final List<Pair<OCOption, Object>> list, final OCDeclareActionContext ocDeclareActionContext, final List<OCMemberSymbol> list2) {
        final Map<OCSymbol, OCSymbol> parentsMap = ocDeclareActionContext.createParentsMap(list2);
        final MemberChooserHeaderPanel memberChooserHeaderPanel = new MemberChooserHeaderPanel(ocDeclareActionContext.getAvailableTargets(), ocDeclareActionContext.getTarget());
        final OCCheckboxMemberChooser ocCheckboxMemberChooser = new OCCheckboxMemberChooser(new OCMemberChooserObject[0], list, project, ((OCClassActionHandlerBase<P, M, OCDeclareActionContext>)this).allowEmptySelection(ocDeclareActionContext), ((OCClassActionHandlerBase<P, M, OCDeclareActionContext>)this).allowMultiSelection(ocDeclareActionContext), memberChooserHeaderPanel) {
            @Override
            protected boolean isMemberEnabled(final OCMemberChooserObject ocMemberChooserObject) {
                final OCDeclareActionContext.Target target = ocDeclareActionContext.getTarget();
                final OCClassSymbol targetSymbol = ocDeclareActionContext.getTargetSymbol();
                final OCSymbol symbol = ocMemberChooserObject.getSymbol();
                return (targetSymbol == null || (!targetSymbol.equals(symbol) && !targetSymbol.equals(parentsMap.get(symbol)))) && (target != OCDeclareActionContext.Target.IMPLEMENTATION || !(symbol instanceof OCPropertySymbol)) && (target != OCDeclareActionContext.Target.PRIVATE_CATEGORY || !(symbol instanceof OCInstanceVariableSymbol) || OCCompilerFeatures.supportsIvarsInCategories());
            }
        };
        memberChooserHeaderPanel.addTargetChangeListener((Processor<OCDeclareActionContext.Target>)(target -> {
            ocDeclareActionContext.setTarget(target);
            ocCheckboxMemberChooser.refreshChosenMembers();
            ocCheckboxMemberChooser.repaint();
            return true;
        }));
        ocCheckboxMemberChooser.refreshChosenMembers();
        return ocCheckboxMemberChooser;
    }
    
    @Override
    protected void performAction(@NotNull final Project p0, @Nullable final Editor p1, @NotNull final PsiFile p2, @NotNull final OCDeclareActionContext p3, @NotNull final List<OCMemberSymbol> p4) {
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
        //    24: ldc             "com/jetbrains/cidr/lang/generate/handlers/OCDeclareMembersHandler"
        //    26: aastore        
        //    27: dup            
        //    28: ldc             2
        //    30: ldc             "performAction"
        //    32: aastore        
        //    33: invokestatic    java/lang/String.format:(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
        //    36: invokespecial   java/lang/IllegalArgumentException.<init>:(Ljava/lang/String;)V
        //    39: athrow         
        //    40: invokestatic    com/jetbrains/cidr/lang/generate/handlers/OCDeclareMembersHandler.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //    43: athrow         
        //    44: aload_3        
        //    45: ifnonnull       88
        //    48: new             Ljava/lang/IllegalArgumentException;
        //    51: dup            
        //    52: ldc             "Argument for @NotNull parameter '%s' of %s.%s must not be null"
        //    54: ldc             3
        //    56: anewarray       Ljava/lang/Object;
        //    59: dup            
        //    60: ldc             0
        //    62: ldc             "file"
        //    64: aastore        
        //    65: dup            
        //    66: ldc             1
        //    68: ldc             "com/jetbrains/cidr/lang/generate/handlers/OCDeclareMembersHandler"
        //    70: aastore        
        //    71: dup            
        //    72: ldc             2
        //    74: ldc             "performAction"
        //    76: aastore        
        //    77: invokestatic    java/lang/String.format:(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
        //    80: invokespecial   java/lang/IllegalArgumentException.<init>:(Ljava/lang/String;)V
        //    83: athrow         
        //    84: invokestatic    com/jetbrains/cidr/lang/generate/handlers/OCDeclareMembersHandler.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //    87: athrow         
        //    88: aload           4
        //    90: ifnonnull       133
        //    93: new             Ljava/lang/IllegalArgumentException;
        //    96: dup            
        //    97: ldc             "Argument for @NotNull parameter '%s' of %s.%s must not be null"
        //    99: ldc             3
        //   101: anewarray       Ljava/lang/Object;
        //   104: dup            
        //   105: ldc             0
        //   107: ldc             "actionContext"
        //   109: aastore        
        //   110: dup            
        //   111: ldc             1
        //   113: ldc             "com/jetbrains/cidr/lang/generate/handlers/OCDeclareMembersHandler"
        //   115: aastore        
        //   116: dup            
        //   117: ldc             2
        //   119: ldc             "performAction"
        //   121: aastore        
        //   122: invokestatic    java/lang/String.format:(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
        //   125: invokespecial   java/lang/IllegalArgumentException.<init>:(Ljava/lang/String;)V
        //   128: athrow         
        //   129: invokestatic    com/jetbrains/cidr/lang/generate/handlers/OCDeclareMembersHandler.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   132: athrow         
        //   133: aload           5
        //   135: ifnonnull       178
        //   138: new             Ljava/lang/IllegalArgumentException;
        //   141: dup            
        //   142: ldc             "Argument for @NotNull parameter '%s' of %s.%s must not be null"
        //   144: ldc             3
        //   146: anewarray       Ljava/lang/Object;
        //   149: dup            
        //   150: ldc             0
        //   152: ldc             "chosenCandidates"
        //   154: aastore        
        //   155: dup            
        //   156: ldc             1
        //   158: ldc             "com/jetbrains/cidr/lang/generate/handlers/OCDeclareMembersHandler"
        //   160: aastore        
        //   161: dup            
        //   162: ldc             2
        //   164: ldc             "performAction"
        //   166: aastore        
        //   167: invokestatic    java/lang/String.format:(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
        //   170: invokespecial   java/lang/IllegalArgumentException.<init>:(Ljava/lang/String;)V
        //   173: athrow         
        //   174: invokestatic    com/jetbrains/cidr/lang/generate/handlers/OCDeclareMembersHandler.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   177: athrow         
        //   178: invokestatic    com/intellij/featureStatistics/FeatureUsageTracker.getInstance:()Lcom/intellij/featureStatistics/FeatureUsageTracker;
        //   181: ldc             "codeassists.altInsert"
        //   183: invokevirtual   com/intellij/featureStatistics/FeatureUsageTracker.triggerFeatureUsed:(Ljava/lang/String;)V
        //   186: new             Ljava/util/ArrayList;
        //   189: dup            
        //   190: invokespecial   java/util/ArrayList.<init>:()V
        //   193: astore          6
        //   195: new             Ljava/util/ArrayList;
        //   198: dup            
        //   199: invokespecial   java/util/ArrayList.<init>:()V
        //   202: astore          7
        //   204: aload           4
        //   206: invokevirtual   com/jetbrains/cidr/lang/generate/handlers/OCDeclareActionContext.getTargetSymbol:()Lcom/jetbrains/cidr/lang/symbols/objc/OCClassSymbol;
        //   209: astore          8
        //   211: aload           8
        //   213: ifnull          233
        //   216: aload           8
        //   218: invokeinterface com/jetbrains/cidr/lang/symbols/objc/OCClassSymbol.locateDefinition:()Lcom/intellij/psi/PsiElement;
        //   223: checkcast       Lcom/jetbrains/cidr/lang/psi/OCClassDeclaration;
        //   226: goto            234
        //   229: invokestatic    com/jetbrains/cidr/lang/generate/handlers/OCDeclareMembersHandler.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   232: athrow         
        //   233: aconst_null    
        //   234: astore          9
        //   236: aload           4
        //   238: invokevirtual   com/jetbrains/cidr/lang/generate/handlers/OCDeclareActionContext.getPrivateCategory:()Lcom/jetbrains/cidr/lang/symbols/objc/OCInterfaceSymbol;
        //   241: astore          10
        //   243: new             Ljava/util/HashSet;
        //   246: dup            
        //   247: invokespecial   java/util/HashSet.<init>:()V
        //   250: astore          11
        //   252: aload           10
        //   254: ifnull          281
        //   257: aload           10
        //   259: ldc             Lcom/jetbrains/cidr/lang/symbols/objc/OCMemberSymbol;.class
        //   261: aload           11
        //   263: invokedynamic   process:(Ljava/util/Set;)Lcom/intellij/util/Processor;
        //   268: invokeinterface com/jetbrains/cidr/lang/symbols/objc/OCInterfaceSymbol.processMembers:(Ljava/lang/Class;Lcom/intellij/util/Processor;)Z
        //   273: pop            
        //   274: goto            281
        //   277: invokestatic    com/jetbrains/cidr/lang/generate/handlers/OCDeclareMembersHandler.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   280: athrow         
        //   281: aload           11
        //   283: invokeinterface java/util/Set.isEmpty:()Z
        //   288: istore          12
        //   290: invokestatic    com/intellij/openapi/application/ApplicationManager.getApplication:()Lcom/intellij/openapi/application/Application;
        //   293: aload_0        
        //   294: aload           5
        //   296: aload           11
        //   298: aload           8
        //   300: aload           4
        //   302: aload           6
        //   304: aload           9
        //   306: aload           7
        //   308: invokedynamic   run:(Lcom/jetbrains/cidr/lang/generate/handlers/OCDeclareMembersHandler;Ljava/util/List;Ljava/util/Set;Lcom/jetbrains/cidr/lang/symbols/objc/OCClassSymbol;Lcom/jetbrains/cidr/lang/generate/handlers/OCDeclareActionContext;Ljava/util/List;Lcom/jetbrains/cidr/lang/psi/OCClassDeclaration;Ljava/util/List;)Ljava/lang/Runnable;
        //   313: invokeinterface com/intellij/openapi/application/Application.runWriteAction:(Ljava/lang/Runnable;)V
        //   318: aload           10
        //   320: ifnull          442
        //   323: aload           8
        //   325: aload           10
        //   327: if_acmpeq       442
        //   330: goto            337
        //   333: invokestatic    com/jetbrains/cidr/lang/generate/handlers/OCDeclareMembersHandler.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   336: athrow         
        //   337: aload           11
        //   339: invokeinterface java/util/Set.isEmpty:()Z
        //   344: ifeq            442
        //   347: goto            354
        //   350: invokestatic    com/jetbrains/cidr/lang/generate/handlers/OCDeclareMembersHandler.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   353: athrow         
        //   354: iload           12
        //   356: ifne            442
        //   359: goto            366
        //   362: invokestatic    com/jetbrains/cidr/lang/generate/handlers/OCDeclareMembersHandler.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   365: athrow         
        //   366: invokestatic    com/intellij/openapi/application/ApplicationManager.getApplication:()Lcom/intellij/openapi/application/Application;
        //   369: invokeinterface com/intellij/openapi/application/Application.isUnitTestMode:()Z
        //   374: ifeq            392
        //   377: goto            384
        //   380: invokestatic    com/jetbrains/cidr/lang/generate/handlers/OCDeclareMembersHandler.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   383: athrow         
        //   384: iconst_0       
        //   385: goto            402
        //   388: invokestatic    com/jetbrains/cidr/lang/generate/handlers/OCDeclareMembersHandler.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   391: athrow         
        //   392: ldc             "Do you want to remove empty Private Category?"
        //   394: ldc             "Remove Private Category"
        //   396: invokestatic    com/intellij/openapi/ui/Messages.getQuestionIcon:()Ljavax/swing/Icon;
        //   399: invokestatic    com/intellij/openapi/ui/Messages.showYesNoCancelDialog:(Ljava/lang/String;Ljava/lang/String;Ljavax/swing/Icon;)I
        //   402: istore          13
        //   404: iload           13
        //   406: iconst_2       
        //   407: if_icmpne       415
        //   410: return         
        //   411: invokestatic    com/jetbrains/cidr/lang/generate/handlers/OCDeclareMembersHandler.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   414: athrow         
        //   415: iload           13
        //   417: ifne            442
        //   420: aload           6
        //   422: aload           10
        //   424: invokeinterface com/jetbrains/cidr/lang/symbols/objc/OCInterfaceSymbol.locateDefinition:()Lcom/intellij/psi/PsiElement;
        //   429: invokeinterface java/util/List.add:(Ljava/lang/Object;)Z
        //   434: pop            
        //   435: goto            442
        //   438: invokestatic    com/jetbrains/cidr/lang/generate/handlers/OCDeclareMembersHandler.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   441: athrow         
        //   442: new             Lcom/jetbrains/cidr/lang/generate/handlers/OCDeclareMembersHandler$2;
        //   445: dup            
        //   446: aload_0        
        //   447: aload_1        
        //   448: aload_0        
        //   449: invokevirtual   com/jetbrains/cidr/lang/generate/handlers/OCDeclareMembersHandler.getActionTitle:()Ljava/lang/String;
        //   452: iconst_0       
        //   453: anewarray       Lcom/intellij/psi/PsiFile;
        //   456: aload           9
        //   458: aload           4
        //   460: aload           8
        //   462: aload           7
        //   464: aload           6
        //   466: invokespecial   com/jetbrains/cidr/lang/generate/handlers/OCDeclareMembersHandler$2.<init>:(Lcom/jetbrains/cidr/lang/generate/handlers/OCDeclareMembersHandler;Lcom/intellij/openapi/project/Project;Ljava/lang/String;[Lcom/intellij/psi/PsiFile;Lcom/jetbrains/cidr/lang/psi/OCClassDeclaration;Lcom/jetbrains/cidr/lang/generate/handlers/OCDeclareActionContext;Lcom/jetbrains/cidr/lang/symbols/objc/OCClassSymbol;Ljava/util/List;Ljava/util/List;)V
        //   469: invokevirtual   com/jetbrains/cidr/lang/generate/handlers/OCDeclareMembersHandler$2.execute:()Lcom/intellij/openapi/application/RunResult;
        //   472: pop            
        //   473: return         
        //    Signature:
        //  (Lcom/intellij/openapi/project/Project;Lcom/intellij/openapi/editor/Editor;Lcom/intellij/psi/PsiFile;Lcom/jetbrains/cidr/lang/generate/handlers/OCDeclareActionContext;Ljava/util/List<Lcom/jetbrains/cidr/lang/symbols/objc/OCMemberSymbol;>;)V
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                                
        //  -----  -----  -----  -----  ------------------------------------
        //  0      40     40     44     Ljava/lang/IllegalArgumentException;
        //  44     84     84     88     Ljava/lang/IllegalArgumentException;
        //  88     129    129    133    Ljava/lang/IllegalArgumentException;
        //  133    174    174    178    Ljava/lang/IllegalArgumentException;
        //  211    229    229    233    Ljava/lang/IllegalArgumentException;
        //  252    274    277    281    Ljava/lang/IllegalArgumentException;
        //  290    330    333    337    Ljava/lang/IllegalArgumentException;
        //  323    347    350    354    Ljava/lang/IllegalArgumentException;
        //  337    359    362    366    Ljava/lang/IllegalArgumentException;
        //  354    377    380    384    Ljava/lang/IllegalArgumentException;
        //  366    388    388    392    Ljava/lang/IllegalArgumentException;
        //  404    411    411    415    Ljava/lang/IllegalArgumentException;
        //  415    435    438    442    Ljava/lang/IllegalArgumentException;
        // 
        // The error that occurred was:
        // 
        // java.lang.IllegalStateException: Expression is linked from several locations: Label_0337:
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
    
    protected PsiElement moveDeclaration(final PsiElement psiElement, final PsiElement psiElement2) {
        final PsiElement add = OCChangeUtil.add(psiElement, psiElement2);
        OCChangeUtil.delete(psiElement2);
        return add;
    }
    
    @Override
    protected OCMemberChooserObject[] getChooserNodes(final OCMemberChooser ocMemberChooser, final Collection<OCMemberSymbol> collection, final OCDeclareActionContext ocDeclareActionContext, final int n) {
        return (OCMemberChooserObject[])ContainerUtil.map2Array((Collection)collection, (Class)OCMemberChooserObject.class, ocMemberSymbol -> new OCMemberChooserObject(ocMemberSymbol, map) {
            final /* synthetic */ Map val$parentsMap;
            final /* synthetic */ OCMemberSymbol val$symbol;
            
            @Nullable
            @Override
            public MemberChooserObject getParentNodeDelegate() {
                final OCClassSymbol ocClassSymbol = this.val$parentsMap.get(this.val$symbol);
                OCDeclareActionContext.Target target;
                if (ocClassSymbol instanceof OCImplementationSymbol) {
                    target = OCDeclareActionContext.Target.IMPLEMENTATION;
                }
                else if ("".equals(ocClassSymbol.getCategoryName())) {
                    target = OCDeclareActionContext.Target.PRIVATE_CATEGORY;
                }
                else {
                    target = OCDeclareActionContext.Target.INTERFACE;
                }
                return (MemberChooserObject)new OCMemberChooserObject(ocClassSymbol, (target == OCDeclareActionContext.Target.IMPLEMENTATION) ? "Implementation (undeclared)" : target.getName(), ocClassSymbol.getIcon());
            }
        });
    }
    
    private static IllegalArgumentException b(final IllegalArgumentException ex) {
        return ex;
    }
    
    public static class MemberChooserHeaderPanel extends JPanel
    {
        private final JComboBox comboBox;
        
        public MemberChooserHeaderPanel(final OCDeclareActionContext.Target[] array, final OCDeclareActionContext.Target selectedItem) {
            super(new BorderLayout());
            this.add(this.comboBox = new JComboBox((E[])array), "Center");
            this.comboBox.setRenderer((ListCellRenderer)new ListCellRendererWrapper<OCDeclareActionContext.Target>() {
                public void customize(final JList list, final OCDeclareActionContext.Target target, final int n, final boolean b, final boolean b2) {
                    this.setText(target.getName());
                    this.setIcon(target.getIcon());
                }
            });
            this.comboBox.setSelectedItem(selectedItem);
            final JLabel label = new JLabel("Declare in: ");
            label.setDisplayedMnemonic('T');
            label.setLabelFor(this.comboBox);
            this.add(label, "West");
        }
        
        protected void addTargetChangeListener(final Processor<OCDeclareActionContext.Target> processor) {
            this.comboBox.addItemListener(new ItemListener() {
                @Override
                public void itemStateChanged(final ItemEvent itemEvent) {
                    processor.process((Object)MemberChooserHeaderPanel.this.comboBox.getSelectedItem());
                }
            });
        }
    }
}
