// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.lang.generate.handlers;

import com.intellij.util.ui.UIUtil;
import com.jetbrains.cidr.lang.OCBundle;
import com.jetbrains.cidr.lang.inspections.OCNotReleasedIvarInspection;
import com.jetbrains.cidr.lang.symbols.objc.OCInterfaceSymbol;
import java.util.Map;
import java.util.Set;
import com.jetbrains.cidr.lang.symbols.symtable.OCMembersContainer;
import com.jetbrains.cidr.lang.generate.actions.OCActionContext;
import com.intellij.util.containers.ContainerUtil;
import com.jetbrains.cidr.lang.symbols.objc.OCMemberSymbol;
import com.intellij.util.Processor;
import com.jetbrains.cidr.lang.symbols.objc.OCSynthesizeSymbol;
import java.util.HashSet;
import com.jetbrains.cidr.lang.generate.OCMemberChooserObject;
import java.util.Collection;
import com.jetbrains.cidr.lang.generate.OCMemberChooser;
import javax.swing.JComponent;
import com.jetbrains.cidr.lang.symbols.objc.OCPropertySymbol;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.util.Condition;
import com.intellij.psi.PsiElement;
import com.jetbrains.cidr.lang.workspace.compiler.OCCompilerFeatures;
import com.jetbrains.cidr.lang.settings.OCOption;
import com.intellij.openapi.util.Pair;
import java.util.List;
import org.jetbrains.annotations.Nullable;
import com.jetbrains.cidr.lang.settings.OCCodeStyleSettings;
import com.intellij.openapi.editor.Editor;
import com.intellij.psi.PsiFile;
import com.jetbrains.cidr.lang.psi.OCClassDeclaration;
import com.jetbrains.cidr.lang.psi.OCSymbolDeclarator;
import org.jetbrains.annotations.NotNull;
import com.jetbrains.cidr.lang.settings.OCBooleanOption;
import com.jetbrains.cidr.lang.generate.actions.OCGenerateFromIvarsActionContext;
import com.jetbrains.cidr.lang.symbols.objc.OCInstanceVariableSymbol;
import com.jetbrains.cidr.lang.symbols.objc.OCClassSymbol;

public class OCGeneratePropertiesHandler extends OCClassActionHandlerBase<OCClassSymbol, OCInstanceVariableSymbol, OCGenerateFromIvarsActionContext>
{
    private static final OCBooleanOption SHOW_SYNTHESIZED;
    private static final OCBooleanOption CONVERT_USAGES;
    
    @Override
    protected String getMembersChooserTitle() {
        return "Select Instance Variables to make Properties";
    }
    
    @Override
    protected String getActionTitle() {
        return "Generate Properties from Instance Variables";
    }
    
    @Override
    protected String getNoMembersMessage(@NotNull final OCGenerateFromIvarsActionContext ocGenerateFromIvarsActionContext) {
        try {
            if (ocGenerateFromIvarsActionContext == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "context", "com/jetbrains/cidr/lang/generate/handlers/OCGeneratePropertiesHandler", "getNoMembersMessage"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw b(ex);
        }
        return ocGenerateFromIvarsActionContext.getParentNameUppercase() + " has no instance variables to generate properties from";
    }
    
    @Override
    protected Class<? extends OCSymbolDeclarator> getParentClass() {
        return OCClassDeclaration.class;
    }
    
    @Override
    protected void loadOptions(final PsiFile psiFile, final Editor editor, @NotNull final OCGenerateFromIvarsActionContext ocGenerateFromIvarsActionContext, @Nullable final OCCodeStyleSettings ocCodeStyleSettings, @NotNull final List<Pair<OCOption, Object>> list) {
        try {
            if (ocGenerateFromIvarsActionContext == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "actionContext", "com/jetbrains/cidr/lang/generate/handlers/OCGeneratePropertiesHandler", "loadOptions"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw b(ex);
        }
        try {
            if (list == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "options", "com/jetbrains/cidr/lang/generate/handlers/OCGeneratePropertiesHandler", "loadOptions"));
            }
        }
        catch (IllegalArgumentException ex2) {
            throw b(ex2);
        }
        try {
            list.add((Pair<OCOption, Object>)new Pair((Object)OCGeneratePropertiesHandler.SHOW_SYNTHESIZED, (Object)true));
            if (OCCompilerFeatures.supportsAutosynthesis(psiFile)) {
                list.add((Pair<OCOption, Object>)new Pair((Object)OCGeneratePropertiesHandler.CONVERT_USAGES, (Object)true));
            }
        }
        catch (IllegalArgumentException ex3) {
            throw b(ex3);
        }
        super.loadOptions(psiFile, editor, ocGenerateFromIvarsActionContext, ocCodeStyleSettings, list);
    }
    
    @NotNull
    @Override
    protected OCGenerateFromIvarsActionContext evaluateActionContext(final OCClassSymbol ocClassSymbol, final PsiElement psiElement) {
        OCGenerateFromIvarsActionContext ocGenerateFromIvarsActionContext;
        try {
            ocGenerateFromIvarsActionContext = new OCGenerateFromIvarsActionContext(ocClassSymbol, ocClassSymbol.getResolvedType(true), psiElement);
            if (ocGenerateFromIvarsActionContext == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/generate/handlers/OCGeneratePropertiesHandler", "evaluateActionContext"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw b(ex);
        }
        return ocGenerateFromIvarsActionContext;
    }
    
    @NotNull
    @Override
    protected Condition<OCInstanceVariableSymbol> getCandidatesFilter(@NotNull final OCGenerateFromIvarsActionContext ocGenerateFromIvarsActionContext) {
        try {
            if (ocGenerateFromIvarsActionContext == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "actionContext", "com/jetbrains/cidr/lang/generate/handlers/OCGeneratePropertiesHandler", "getCandidatesFilter"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw b(ex);
        }
        Condition condition;
        try {
            condition = (ocInstanceVariableSymbol -> {
                try {
                    if (ocInstanceVariableSymbol.getGeneratedFromProperty() == null) {
                        return true;
                    }
                }
                catch (IllegalArgumentException ex) {
                    throw b(ex);
                }
                return false;
            });
            if (condition == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/generate/handlers/OCGeneratePropertiesHandler", "getCandidatesFilter"));
            }
        }
        catch (IllegalArgumentException ex2) {
            throw b(ex2);
        }
        return (Condition<OCInstanceVariableSymbol>)condition;
    }
    
    @Override
    protected void performAction(@NotNull final Project project, @Nullable final Editor editor, @NotNull final PsiFile psiFile, @NotNull final OCGenerateFromIvarsActionContext ocGenerateFromIvarsActionContext, @NotNull final List<OCInstanceVariableSymbol> list) {
        try {
            if (project == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "project", "com/jetbrains/cidr/lang/generate/handlers/OCGeneratePropertiesHandler", "performAction"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw b(ex);
        }
        try {
            if (psiFile == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "file", "com/jetbrains/cidr/lang/generate/handlers/OCGeneratePropertiesHandler", "performAction"));
            }
        }
        catch (IllegalArgumentException ex2) {
            throw b(ex2);
        }
        try {
            if (ocGenerateFromIvarsActionContext == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "context", "com/jetbrains/cidr/lang/generate/handlers/OCGeneratePropertiesHandler", "performAction"));
            }
        }
        catch (IllegalArgumentException ex3) {
            throw b(ex3);
        }
        try {
            if (list == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "ivars", "com/jetbrains/cidr/lang/generate/handlers/OCGeneratePropertiesHandler", "performAction"));
            }
        }
        catch (IllegalArgumentException ex4) {
            throw b(ex4);
        }
        this.performAction(psiFile, ocGenerateFromIvarsActionContext, list, null, null, false, this.convertUsages(ocGenerateFromIvarsActionContext), false);
    }
    
    protected boolean convertUsages(final OCGenerateFromIvarsActionContext ocGenerateFromIvarsActionContext) {
        final Boolean b = ((OCClassActionHandlerBase<P, M, OCGenerateFromIvarsActionContext>)this).getOption(ocGenerateFromIvarsActionContext, (OCOption<Boolean, JComponent>)OCGeneratePropertiesHandler.CONVERT_USAGES);
        try {
            if (b != null) {
                return b;
            }
        }
        catch (IllegalArgumentException ex) {
            throw b(ex);
        }
        return true;
    }
    
    protected void performAction(final PsiFile p0, final OCGenerateFromIvarsActionContext p1, final List<OCInstanceVariableSymbol> p2, @Nullable final String p3, @Nullable final OCPropertySymbol.PropertySemantics p4, final boolean p5, final boolean p6, final boolean p7) {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     0: invokestatic    com/intellij/featureStatistics/FeatureUsageTracker.getInstance:()Lcom/intellij/featureStatistics/FeatureUsageTracker;
        //     3: ldc             "codeassists.altInsert"
        //     5: invokevirtual   com/intellij/featureStatistics/FeatureUsageTracker.triggerFeatureUsed:(Ljava/lang/String;)V
        //     8: aload_2        
        //     9: invokevirtual   com/jetbrains/cidr/lang/generate/actions/OCGenerateFromIvarsActionContext.getInterfaceSymbol:()Lcom/jetbrains/cidr/lang/symbols/objc/OCClassSymbol;
        //    12: checkcast       Lcom/jetbrains/cidr/lang/symbols/objc/OCInterfaceSymbol;
        //    15: astore          9
        //    17: new             Ljava/util/HashMap;
        //    20: dup            
        //    21: invokespecial   java/util/HashMap.<init>:()V
        //    24: astore          10
        //    26: new             Ljava/util/HashMap;
        //    29: dup            
        //    30: invokespecial   java/util/HashMap.<init>:()V
        //    33: astore          11
        //    35: new             Ljava/util/HashMap;
        //    38: dup            
        //    39: invokespecial   java/util/HashMap.<init>:()V
        //    42: astore          12
        //    44: new             Ljava/util/ArrayList;
        //    47: dup            
        //    48: invokespecial   java/util/ArrayList.<init>:()V
        //    51: astore          13
        //    53: aload_0        
        //    54: invokevirtual   com/jetbrains/cidr/lang/generate/handlers/OCGeneratePropertiesHandler.replaceAllIvars:()Z
        //    57: istore          14
        //    59: aload_2        
        //    60: invokevirtual   com/jetbrains/cidr/lang/generate/actions/OCGenerateFromIvarsActionContext.getContext:()Lcom/intellij/psi/PsiElement;
        //    63: invokeinterface com/intellij/psi/PsiElement.isValid:()Z
        //    68: ifeq            82
        //    71: aload_2        
        //    72: invokevirtual   com/jetbrains/cidr/lang/generate/actions/OCGenerateFromIvarsActionContext.getContext:()Lcom/intellij/psi/PsiElement;
        //    75: goto            83
        //    78: invokestatic    com/jetbrains/cidr/lang/generate/handlers/OCGeneratePropertiesHandler.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //    81: athrow         
        //    82: aload_1        
        //    83: astore          15
        //    85: new             Ljava/util/ArrayList;
        //    88: dup            
        //    89: invokespecial   java/util/ArrayList.<init>:()V
        //    92: astore          16
        //    94: aload_1        
        //    95: invokestatic    com/jetbrains/cidr/lang/inspections/OCNotReleasedIvarInspection.startInspection:(Lcom/intellij/psi/PsiFile;)Lcom/jetbrains/cidr/lang/inspections/OCNotReleasedIvarInspection$IvarsInfo;
        //    98: astore          17
        //   100: invokestatic    com/intellij/openapi/application/ApplicationManager.getApplication:()Lcom/intellij/openapi/application/Application;
        //   103: aload_3        
        //   104: aload           4
        //   106: aload           10
        //   108: iload           7
        //   110: aload           9
        //   112: aload           5
        //   114: aload_1        
        //   115: aload           15
        //   117: iload           6
        //   119: aload           11
        //   121: aload           12
        //   123: iload           14
        //   125: aload           13
        //   127: aload           17
        //   129: aload           16
        //   131: invokedynamic   run:(Ljava/util/List;Ljava/lang/String;Ljava/util/Map;ZLcom/jetbrains/cidr/lang/symbols/objc/OCInterfaceSymbol;Lcom/jetbrains/cidr/lang/symbols/objc/OCPropertySymbol$PropertySemantics;Lcom/intellij/psi/PsiFile;Lcom/intellij/psi/PsiElement;ZLjava/util/Map;Ljava/util/Map;ZLjava/util/List;Lcom/jetbrains/cidr/lang/inspections/OCNotReleasedIvarInspection$IvarsInfo;Ljava/util/List;)Ljava/lang/Runnable;
        //   136: invokeinterface com/intellij/openapi/application/Application.runWriteAction:(Ljava/lang/Runnable;)V
        //   141: aload_1        
        //   142: invokestatic    com/jetbrains/cidr/lang/workspace/compiler/OCCompilerFeatures.isArcDisabled:(Lcom/intellij/psi/PsiFile;)Z
        //   145: ifeq            181
        //   148: aload           16
        //   150: invokeinterface java/util/List.isEmpty:()Z
        //   155: ifne            181
        //   158: goto            165
        //   161: invokestatic    com/jetbrains/cidr/lang/generate/handlers/OCGeneratePropertiesHandler.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   164: athrow         
        //   165: new             Lcom/jetbrains/cidr/lang/quickfixes/OCReleaseVariablesIntentionAction;
        //   168: dup            
        //   169: aload           16
        //   171: invokespecial   com/jetbrains/cidr/lang/quickfixes/OCReleaseVariablesIntentionAction.<init>:(Ljava/util/List;)V
        //   174: goto            182
        //   177: invokestatic    com/jetbrains/cidr/lang/generate/handlers/OCGeneratePropertiesHandler.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   180: athrow         
        //   181: aconst_null    
        //   182: astore          18
        //   184: new             Lcom/jetbrains/cidr/lang/generate/handlers/OCGeneratePropertiesHandler$1;
        //   187: dup            
        //   188: aload_0        
        //   189: aload           9
        //   191: aload_3        
        //   192: aload           10
        //   194: aload           11
        //   196: aload           12
        //   198: iload           14
        //   200: ifeq            211
        //   203: aconst_null    
        //   204: goto            213
        //   207: invokestatic    com/jetbrains/cidr/lang/generate/handlers/OCGeneratePropertiesHandler.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   210: athrow         
        //   211: aload           13
        //   213: aload           18
        //   215: getstatic       com/jetbrains/cidr/lang/parser/OCTokenTypes.DOT:Lcom/jetbrains/cidr/lang/parser/OCPunctuatorElementType;
        //   218: aload_0        
        //   219: invokevirtual   com/jetbrains/cidr/lang/generate/handlers/OCGeneratePropertiesHandler.getActionTitle:()Ljava/lang/String;
        //   222: iload           7
        //   224: iload           8
        //   226: invokespecial   com/jetbrains/cidr/lang/generate/handlers/OCGeneratePropertiesHandler$1.<init>:(Lcom/jetbrains/cidr/lang/generate/handlers/OCGeneratePropertiesHandler;Lcom/jetbrains/cidr/lang/symbols/objc/OCClassSymbol;Ljava/util/List;Ljava/util/Map;Ljava/util/Map;Ljava/util/Map;Ljava/util/List;Lcom/jetbrains/cidr/lang/quickfixes/OCReleaseVariablesIntentionAction;Lcom/jetbrains/cidr/lang/parser/OCElementType;Ljava/lang/String;ZZ)V
        //   229: astore          19
        //   231: aload           19
        //   233: invokevirtual   com/jetbrains/cidr/lang/refactoring/OCConvertMemberRefactoringProcessor.run:()V
        //   236: return         
        //    Signature:
        //  (Lcom/intellij/psi/PsiFile;Lcom/jetbrains/cidr/lang/generate/actions/OCGenerateFromIvarsActionContext;Ljava/util/List<Lcom/jetbrains/cidr/lang/symbols/objc/OCInstanceVariableSymbol;>;Ljava/lang/String;Lcom/jetbrains/cidr/lang/symbols/objc/OCPropertySymbol$PropertySemantics;ZZZ)V
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                                
        //  -----  -----  -----  -----  ------------------------------------
        //  59     78     78     82     Ljava/lang/IllegalArgumentException;
        //  100    158    161    165    Ljava/lang/IllegalArgumentException;
        //  148    177    177    181    Ljava/lang/IllegalArgumentException;
        //  184    207    207    211    Ljava/lang/IllegalArgumentException;
        // 
        // The error that occurred was:
        // 
        // java.lang.IllegalStateException: Expression is linked from several locations: Label_0274_1:
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
    
    protected void passConflictsToTest(final List<String> list) {
    }
    
    protected boolean replaceAllIvars() {
        return false;
    }
    
    @Override
    protected OCMemberChooserObject[] getChooserNodes(final OCMemberChooser ocMemberChooser, final Collection<OCInstanceVariableSymbol> collection, final OCGenerateFromIvarsActionContext ocGenerateFromIvarsActionContext, final int n) {
        try {
            if (OCClassActionHandlerBase.getOption(ocMemberChooser.getOptionSelections(), (OCOption<Boolean, JComponent>)OCGeneratePropertiesHandler.SHOW_SYNTHESIZED)) {
                return super.getChooserNodes(ocMemberChooser, collection, ocGenerateFromIvarsActionContext, n);
            }
        }
        catch (IllegalArgumentException ex) {
            throw b(ex);
        }
        final HashSet set = new HashSet();
        try {
            ocGenerateFromIvarsActionContext.getType().processMembers(OCSynthesizeSymbol.class, (com.intellij.util.Processor<? super OCSynthesizeSymbol>)(ocSynthesizeSymbol -> {
                try {
                    if (ocSynthesizeSymbol.isSynthesize()) {
                        set.add(ocSynthesizeSymbol.getIvarName());
                    }
                }
                catch (IllegalArgumentException ex) {
                    throw b(ex);
                }
                return true;
            }));
            if (OCCompilerFeatures.supportsAutosynthesis(ocGenerateFromIvarsActionContext.getContext().getContainingFile())) {
                ocGenerateFromIvarsActionContext.getInterfaceSymbol().processMembersInAllCategories((String)null, (Class<? extends OCMemberSymbol>)OCPropertySymbol.class, (com.intellij.util.Processor<? super OCMemberSymbol>)(ocPropertySymbol -> {
                    final OCInstanceVariableSymbol associatedIvar = ocPropertySymbol.getAssociatedIvar();
                    try {
                        if (associatedIvar != null) {
                            set.add(associatedIvar.getName());
                        }
                    }
                    catch (IllegalArgumentException ex) {
                        throw b(ex);
                    }
                    return true;
                }), false);
            }
        }
        catch (IllegalArgumentException ex2) {
            throw b(ex2);
        }
        return super.getChooserNodes(ocMemberChooser, ContainerUtil.filter((Collection)collection, ocInstanceVariableSymbol -> {
            try {
                if (!set.contains(ocInstanceVariableSymbol.getName())) {
                    return true;
                }
            }
            catch (IllegalArgumentException ex) {
                throw b(ex);
            }
            return false;
        }), ocGenerateFromIvarsActionContext, n);
    }
    
    @Override
    protected void optionValueChanged(final OCMemberChooser ocMemberChooser, final Collection<OCInstanceVariableSymbol> collection, final OCOption ocOption, final OCGenerateFromIvarsActionContext ocGenerateFromIvarsActionContext) {
        try {
            if (OCGeneratePropertiesHandler.SHOW_SYNTHESIZED.equals(ocOption)) {
                ocMemberChooser.resetElements(this.getChooserNodes(ocMemberChooser, collection, ocGenerateFromIvarsActionContext, 0));
            }
        }
        catch (IllegalArgumentException ex) {
            throw b(ex);
        }
    }
    
    static {
        SHOW_SYNTHESIZED = new OCBooleanOption("Show ivars with synthesized properties");
        CONVERT_USAGES = new OCBooleanOption(UIUtil.removeMnemonic(OCBundle.message("refactoring.convertIvarUsagesToProperties", new Object[0])));
    }
    
    private static IllegalArgumentException b(final IllegalArgumentException ex) {
        return ex;
    }
}
