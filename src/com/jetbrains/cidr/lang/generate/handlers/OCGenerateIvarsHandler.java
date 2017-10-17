// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.lang.generate.handlers;

import com.jetbrains.cidr.lang.symbols.OCSymbolWithParent;
import com.jetbrains.cidr.lang.inspections.OCNotReleasedIvarInspection;
import com.jetbrains.cidr.lang.symbols.objc.OCImplementationSymbol;
import com.jetbrains.cidr.lang.symbols.objc.OCInterfaceSymbol;
import com.jetbrains.cidr.lang.symbols.objc.OCMethodSymbol;
import com.jetbrains.cidr.lang.symbols.objc.OCSynthesizeSymbol;
import java.util.Map;
import com.jetbrains.cidr.lang.symbols.symtable.OCMembersContainer;
import com.jetbrains.cidr.lang.generate.actions.OCActionContext;
import com.intellij.util.Processor;
import com.jetbrains.cidr.lang.symbols.objc.OCMemberSymbol;
import java.util.HashMap;
import com.intellij.openapi.util.Condition;
import com.intellij.util.containers.ContainerUtil;
import javax.swing.JComponent;
import com.jetbrains.cidr.lang.generate.OCMemberChooserObject;
import java.util.Collection;
import com.jetbrains.cidr.lang.generate.OCMemberChooser;
import com.intellij.psi.codeStyle.CodeStyleSettingsManager;
import com.intellij.openapi.util.Ref;
import com.intellij.openapi.project.Project;
import com.jetbrains.cidr.lang.types.OCType;
import com.jetbrains.cidr.lang.psi.OCFile;
import com.jetbrains.cidr.lang.types.OCObjectType;
import com.jetbrains.cidr.lang.symbols.objc.OCProtocolSymbol;
import com.intellij.psi.PsiElement;
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
import com.jetbrains.cidr.lang.generate.actions.OCGenerateIvarsActionContext;
import com.jetbrains.cidr.lang.symbols.objc.OCPropertySymbol;
import com.jetbrains.cidr.lang.symbols.objc.OCClassSymbol;

public class OCGenerateIvarsHandler extends OCClassActionHandlerBase<OCClassSymbol, OCPropertySymbol, OCGenerateIvarsActionContext>
{
    private static final OCBooleanOption SHOW_SYNTHESIZED;
    
    @Override
    protected String getMembersChooserTitle() {
        return "Select Properties to make Instance Variables";
    }
    
    @Override
    protected String getActionTitle() {
        return "Generate Instance Variables from Properties";
    }
    
    @Override
    protected String getNoMembersMessage(@NotNull final OCGenerateIvarsActionContext ocGenerateIvarsActionContext) {
        try {
            if (ocGenerateIvarsActionContext == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "context", "com/jetbrains/cidr/lang/generate/handlers/OCGenerateIvarsHandler", "getNoMembersMessage"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw b(ex);
        }
        return ocGenerateIvarsActionContext.getParentNameUppercase() + " has no properties to generate instance variables from";
    }
    
    @Override
    protected Class<? extends OCSymbolDeclarator> getParentClass() {
        return OCClassDeclaration.class;
    }
    
    @Override
    protected void loadOptions(final PsiFile psiFile, final Editor editor, @NotNull final OCGenerateIvarsActionContext ocGenerateIvarsActionContext, @Nullable final OCCodeStyleSettings ocCodeStyleSettings, @NotNull final List<Pair<OCOption, Object>> list) {
        try {
            if (ocGenerateIvarsActionContext == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "actionContext", "com/jetbrains/cidr/lang/generate/handlers/OCGenerateIvarsHandler", "loadOptions"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw b(ex);
        }
        try {
            if (list == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "options", "com/jetbrains/cidr/lang/generate/handlers/OCGenerateIvarsHandler", "loadOptions"));
            }
        }
        catch (IllegalArgumentException ex2) {
            throw b(ex2);
        }
        list.add((Pair<OCOption, Object>)new Pair((Object)OCGenerateIvarsHandler.SHOW_SYNTHESIZED, (Object)true));
        super.loadOptions(psiFile, editor, ocGenerateIvarsActionContext, ocCodeStyleSettings, list);
    }
    
    @NotNull
    @Override
    protected OCGenerateIvarsActionContext evaluateActionContext(final OCClassSymbol ocClassSymbol, final PsiElement psiElement) {
        OCGenerateIvarsActionContext ocGenerateIvarsActionContext;
        try {
            ocGenerateIvarsActionContext = new OCGenerateIvarsActionContext(ocClassSymbol, ocClassSymbol.getResolvedType(true), psiElement);
            if (ocGenerateIvarsActionContext == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/generate/handlers/OCGenerateIvarsHandler", "evaluateActionContext"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw b(ex);
        }
        return ocGenerateIvarsActionContext;
    }
    
    public static boolean isPropertyDefective(final OCPropertySymbol ocPropertySymbol) {
        final OCClassSymbol ocClassSymbol = ((OCSymbolWithParent<T, OCClassSymbol>)ocPropertySymbol).getParent();
        try {
            if (ocClassSymbol instanceof OCProtocolSymbol) {
                return false;
            }
        }
        catch (IllegalArgumentException ex) {
            throw b(ex);
        }
        final OCFile containingOCFile = ocClassSymbol.getContainingOCFile();
        final OCType resolve = ocClassSymbol.getType().resolve((PsiFile)containingOCFile, true);
        return resolve instanceof OCObjectType && a(new OCGenerateIvarsActionContext(ocClassSymbol, (OCObjectType)resolve, (PsiElement)containingOCFile)).value((Object)ocPropertySymbol);
    }
    
    @Override
    protected void performAction(@NotNull final Project p0, @Nullable final Editor p1, @NotNull final PsiFile p2, @NotNull final OCGenerateIvarsActionContext p3, @NotNull final List<OCPropertySymbol> p4) {
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
        //    24: ldc             "com/jetbrains/cidr/lang/generate/handlers/OCGenerateIvarsHandler"
        //    26: aastore        
        //    27: dup            
        //    28: ldc             2
        //    30: ldc             "performAction"
        //    32: aastore        
        //    33: invokestatic    java/lang/String.format:(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
        //    36: invokespecial   java/lang/IllegalArgumentException.<init>:(Ljava/lang/String;)V
        //    39: athrow         
        //    40: invokestatic    com/jetbrains/cidr/lang/generate/handlers/OCGenerateIvarsHandler.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
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
        //    68: ldc             "com/jetbrains/cidr/lang/generate/handlers/OCGenerateIvarsHandler"
        //    70: aastore        
        //    71: dup            
        //    72: ldc             2
        //    74: ldc             "performAction"
        //    76: aastore        
        //    77: invokestatic    java/lang/String.format:(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
        //    80: invokespecial   java/lang/IllegalArgumentException.<init>:(Ljava/lang/String;)V
        //    83: athrow         
        //    84: invokestatic    com/jetbrains/cidr/lang/generate/handlers/OCGenerateIvarsHandler.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
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
        //   107: ldc             "context"
        //   109: aastore        
        //   110: dup            
        //   111: ldc             1
        //   113: ldc             "com/jetbrains/cidr/lang/generate/handlers/OCGenerateIvarsHandler"
        //   115: aastore        
        //   116: dup            
        //   117: ldc             2
        //   119: ldc             "performAction"
        //   121: aastore        
        //   122: invokestatic    java/lang/String.format:(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
        //   125: invokespecial   java/lang/IllegalArgumentException.<init>:(Ljava/lang/String;)V
        //   128: athrow         
        //   129: invokestatic    com/jetbrains/cidr/lang/generate/handlers/OCGenerateIvarsHandler.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
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
        //   152: ldc             "properties"
        //   154: aastore        
        //   155: dup            
        //   156: ldc             1
        //   158: ldc             "com/jetbrains/cidr/lang/generate/handlers/OCGenerateIvarsHandler"
        //   160: aastore        
        //   161: dup            
        //   162: ldc             2
        //   164: ldc             "performAction"
        //   166: aastore        
        //   167: invokestatic    java/lang/String.format:(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
        //   170: invokespecial   java/lang/IllegalArgumentException.<init>:(Ljava/lang/String;)V
        //   173: athrow         
        //   174: invokestatic    com/jetbrains/cidr/lang/generate/handlers/OCGenerateIvarsHandler.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   177: athrow         
        //   178: aload_3        
        //   179: invokeinterface com/intellij/psi/PsiFile.getProject:()Lcom/intellij/openapi/project/Project;
        //   184: invokestatic    com/intellij/psi/codeStyle/CodeStyleSettingsManager.getSettings:(Lcom/intellij/openapi/project/Project;)Lcom/intellij/psi/codeStyle/CodeStyleSettings;
        //   187: ldc             Lcom/jetbrains/cidr/lang/settings/OCCodeStyleSettings;.class
        //   189: invokevirtual   com/intellij/psi/codeStyle/CodeStyleSettings.getCustomSettings:(Ljava/lang/Class;)Lcom/intellij/psi/codeStyle/CustomCodeStyleSettings;
        //   192: checkcast       Lcom/jetbrains/cidr/lang/settings/OCCodeStyleSettings;
        //   195: astore          6
        //   197: invokestatic    com/jetbrains/cidr/lang/workspace/compiler/OCCompilerFeatures.supportsIvarsInImplementation:()Z
        //   200: ifeq            238
        //   203: aload           6
        //   205: ifnull          230
        //   208: goto            215
        //   211: invokestatic    com/jetbrains/cidr/lang/generate/handlers/OCGenerateIvarsHandler.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   214: athrow         
        //   215: aload           6
        //   217: getfield        com/jetbrains/cidr/lang/settings/OCCodeStyleSettings.PUT_IVARS_TO_IMPLEMENTATION:Z
        //   220: ifeq            238
        //   223: goto            230
        //   226: invokestatic    com/jetbrains/cidr/lang/generate/handlers/OCGenerateIvarsHandler.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   229: athrow         
        //   230: iconst_1       
        //   231: goto            239
        //   234: invokestatic    com/jetbrains/cidr/lang/generate/handlers/OCGenerateIvarsHandler.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   237: athrow         
        //   238: iconst_0       
        //   239: istore          7
        //   241: aload_0        
        //   242: aload_3        
        //   243: aload           4
        //   245: aload           5
        //   247: aconst_null    
        //   248: iload           7
        //   250: invokevirtual   com/jetbrains/cidr/lang/generate/handlers/OCGenerateIvarsHandler.performAction:(Lcom/intellij/psi/PsiFile;Lcom/jetbrains/cidr/lang/generate/actions/OCGenerateIvarsActionContext;Ljava/util/List;Ljava/lang/String;Z)V
        //   253: return         
        //    Signature:
        //  (Lcom/intellij/openapi/project/Project;Lcom/intellij/openapi/editor/Editor;Lcom/intellij/psi/PsiFile;Lcom/jetbrains/cidr/lang/generate/actions/OCGenerateIvarsActionContext;Ljava/util/List<Lcom/jetbrains/cidr/lang/symbols/objc/OCPropertySymbol;>;)V
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                                
        //  -----  -----  -----  -----  ------------------------------------
        //  0      40     40     44     Ljava/lang/IllegalArgumentException;
        //  44     84     84     88     Ljava/lang/IllegalArgumentException;
        //  88     129    129    133    Ljava/lang/IllegalArgumentException;
        //  133    174    174    178    Ljava/lang/IllegalArgumentException;
        //  197    208    211    215    Ljava/lang/IllegalArgumentException;
        //  203    223    226    230    Ljava/lang/IllegalArgumentException;
        //  215    234    234    238    Ljava/lang/IllegalArgumentException;
        // 
        // The error that occurred was:
        // 
        // java.lang.IllegalStateException: Expression is linked from several locations: Label_0215:
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
    
    public void performAction(final PsiFile p0, final OCGenerateIvarsActionContext p1, final List<OCPropertySymbol> p2, final String p3, final boolean p4) {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     0: invokestatic    com/intellij/featureStatistics/FeatureUsageTracker.getInstance:()Lcom/intellij/featureStatistics/FeatureUsageTracker;
        //     3: ldc             "codeassists.altInsert"
        //     5: invokevirtual   com/intellij/featureStatistics/FeatureUsageTracker.triggerFeatureUsed:(Ljava/lang/String;)V
        //     8: aload_2        
        //     9: invokevirtual   com/jetbrains/cidr/lang/generate/actions/OCGenerateIvarsActionContext.getInterfaceSymbol:()Lcom/jetbrains/cidr/lang/symbols/objc/OCClassSymbol;
        //    12: checkcast       Lcom/jetbrains/cidr/lang/symbols/objc/OCInterfaceSymbol;
        //    15: astore          6
        //    17: aload_2        
        //    18: invokevirtual   com/jetbrains/cidr/lang/generate/actions/OCGenerateIvarsActionContext.getImplementationSymbol:()Lcom/jetbrains/cidr/lang/symbols/objc/OCImplementationSymbol;
        //    21: astore          7
        //    23: new             Ljava/util/HashMap;
        //    26: dup            
        //    27: invokespecial   java/util/HashMap.<init>:()V
        //    30: astore          8
        //    32: new             Ljava/util/ArrayList;
        //    35: dup            
        //    36: invokespecial   java/util/ArrayList.<init>:()V
        //    39: astore          9
        //    41: new             Ljava/util/HashMap;
        //    44: dup            
        //    45: invokespecial   java/util/HashMap.<init>:()V
        //    48: astore          10
        //    50: new             Ljava/util/HashMap;
        //    53: dup            
        //    54: invokespecial   java/util/HashMap.<init>:()V
        //    57: astore          11
        //    59: new             Ljava/util/ArrayList;
        //    62: dup            
        //    63: invokespecial   java/util/ArrayList.<init>:()V
        //    66: astore          12
        //    68: aload_0        
        //    69: invokevirtual   com/jetbrains/cidr/lang/generate/handlers/OCGenerateIvarsHandler.replaceAllProperties:()Z
        //    72: istore          13
        //    74: aload_2        
        //    75: invokestatic    com/jetbrains/cidr/lang/generate/handlers/OCGenerateIvarsHandler.a:(Lcom/jetbrains/cidr/lang/generate/actions/OCGenerateIvarsActionContext;)Lcom/intellij/openapi/util/Condition;
        //    78: astore          14
        //    80: aload_2        
        //    81: invokevirtual   com/jetbrains/cidr/lang/generate/actions/OCGenerateIvarsActionContext.getContext:()Lcom/intellij/psi/PsiElement;
        //    84: invokeinterface com/intellij/psi/PsiElement.isValid:()Z
        //    89: ifeq            103
        //    92: aload_2        
        //    93: invokevirtual   com/jetbrains/cidr/lang/generate/actions/OCGenerateIvarsActionContext.getContext:()Lcom/intellij/psi/PsiElement;
        //    96: goto            104
        //    99: invokestatic    com/jetbrains/cidr/lang/generate/handlers/OCGenerateIvarsHandler.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   102: athrow         
        //   103: aload_1        
        //   104: astore          15
        //   106: new             Ljava/util/ArrayList;
        //   109: dup            
        //   110: invokespecial   java/util/ArrayList.<init>:()V
        //   113: astore          16
        //   115: aload_1        
        //   116: invokestatic    com/jetbrains/cidr/lang/inspections/OCNotReleasedIvarInspection.startInspection:(Lcom/intellij/psi/PsiFile;)Lcom/jetbrains/cidr/lang/inspections/OCNotReleasedIvarInspection$IvarsInfo;
        //   119: astore          17
        //   121: invokestatic    com/intellij/openapi/application/ApplicationManager.getApplication:()Lcom/intellij/openapi/application/Application;
        //   124: aload_0        
        //   125: aload_3        
        //   126: iload           13
        //   128: aload           14
        //   130: aload           4
        //   132: aload           8
        //   134: aload           6
        //   136: aload           7
        //   138: aload_1        
        //   139: aload           10
        //   141: aload           15
        //   143: aload           11
        //   145: aload           12
        //   147: aload           17
        //   149: aload           16
        //   151: aload           9
        //   153: invokedynamic   run:(Lcom/jetbrains/cidr/lang/generate/handlers/OCGenerateIvarsHandler;Ljava/util/List;ZLcom/intellij/openapi/util/Condition;Ljava/lang/String;Ljava/util/Map;Lcom/jetbrains/cidr/lang/symbols/objc/OCInterfaceSymbol;Lcom/jetbrains/cidr/lang/symbols/objc/OCImplementationSymbol;Lcom/intellij/psi/PsiFile;Ljava/util/Map;Lcom/intellij/psi/PsiElement;Ljava/util/Map;Ljava/util/List;Lcom/jetbrains/cidr/lang/inspections/OCNotReleasedIvarInspection$IvarsInfo;Ljava/util/List;Ljava/util/List;)Ljava/lang/Runnable;
        //   158: invokeinterface com/intellij/openapi/application/Application.runWriteAction:(Ljava/lang/Runnable;)V
        //   163: aload           10
        //   165: invokeinterface java/util/Map.isEmpty:()Z
        //   170: ifne            217
        //   173: aload_0        
        //   174: aload_1        
        //   175: invokeinterface com/intellij/psi/PsiFile.getProject:()Lcom/intellij/openapi/project/Project;
        //   180: invokevirtual   com/jetbrains/cidr/lang/generate/handlers/OCGenerateIvarsHandler.generateIvars:(Lcom/intellij/openapi/project/Project;)Ljava/lang/Boolean;
        //   183: astore          18
        //   185: aload           18
        //   187: ifnonnull       195
        //   190: return         
        //   191: invokestatic    com/jetbrains/cidr/lang/generate/handlers/OCGenerateIvarsHandler.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   194: athrow         
        //   195: aload           18
        //   197: invokevirtual   java/lang/Boolean.booleanValue:()Z
        //   200: ifne            217
        //   203: aload           10
        //   205: invokeinterface java/util/Map.clear:()V
        //   210: goto            217
        //   213: invokestatic    com/jetbrains/cidr/lang/generate/handlers/OCGenerateIvarsHandler.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   216: athrow         
        //   217: aconst_null    
        //   218: astore          18
        //   220: aload_1        
        //   221: invokestatic    com/jetbrains/cidr/lang/workspace/compiler/OCCompilerFeatures.isArcDisabled:(Lcom/intellij/psi/PsiFile;)Z
        //   224: ifeq            282
        //   227: aload           16
        //   229: invokeinterface java/util/List.isEmpty:()Z
        //   234: ifne            282
        //   237: goto            244
        //   240: invokestatic    com/jetbrains/cidr/lang/generate/handlers/OCGenerateIvarsHandler.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   243: athrow         
        //   244: aload_3        
        //   245: iconst_0       
        //   246: invokeinterface java/util/List.get:(I)Ljava/lang/Object;
        //   251: checkcast       Lcom/jetbrains/cidr/lang/symbols/objc/OCPropertySymbol;
        //   254: invokeinterface com/jetbrains/cidr/lang/symbols/objc/OCPropertySymbol.getParent:()Lcom/jetbrains/cidr/lang/symbols/OCSymbol;
        //   259: checkcast       Lcom/jetbrains/cidr/lang/symbols/objc/OCClassSymbol;
        //   262: astore          19
        //   264: new             Lcom/jetbrains/cidr/lang/quickfixes/OCReleaseVariablesIntentionAction;
        //   267: dup            
        //   268: getstatic       com/jetbrains/cidr/lang/symbols/OCSymbolKind.INSTANCE_VARIABLE:Lcom/jetbrains/cidr/lang/symbols/OCSymbolKind;
        //   271: aload           19
        //   273: aload           16
        //   275: aload           9
        //   277: invokespecial   com/jetbrains/cidr/lang/quickfixes/OCReleaseVariablesIntentionAction.<init>:(Lcom/jetbrains/cidr/lang/symbols/OCSymbolKind;Lcom/jetbrains/cidr/lang/symbols/objc/OCClassSymbol;Ljava/util/List;Ljava/util/List;)V
        //   280: astore          18
        //   282: iload           5
        //   284: ifeq            298
        //   287: aload_2        
        //   288: invokevirtual   com/jetbrains/cidr/lang/generate/actions/OCGenerateIvarsActionContext.getImplementationSymbol:()Lcom/jetbrains/cidr/lang/symbols/objc/OCImplementationSymbol;
        //   291: goto            302
        //   294: invokestatic    com/jetbrains/cidr/lang/generate/handlers/OCGenerateIvarsHandler.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   297: athrow         
        //   298: aload_2        
        //   299: invokevirtual   com/jetbrains/cidr/lang/generate/actions/OCGenerateIvarsActionContext.getInterfaceSymbol:()Lcom/jetbrains/cidr/lang/symbols/objc/OCClassSymbol;
        //   302: astore          19
        //   304: new             Lcom/jetbrains/cidr/lang/generate/handlers/OCGenerateIvarsHandler$2;
        //   307: dup            
        //   308: aload_0        
        //   309: aload           19
        //   311: aload_3        
        //   312: aload           8
        //   314: aload           10
        //   316: aload           11
        //   318: iload           13
        //   320: ifeq            331
        //   323: aconst_null    
        //   324: goto            333
        //   327: invokestatic    com/jetbrains/cidr/lang/generate/handlers/OCGenerateIvarsHandler.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   330: athrow         
        //   331: aload           12
        //   333: aload           18
        //   335: getstatic       com/jetbrains/cidr/lang/parser/OCTokenTypes.DEREF:Lcom/jetbrains/cidr/lang/parser/OCPunctuatorElementType;
        //   338: aload_0        
        //   339: invokevirtual   com/jetbrains/cidr/lang/generate/handlers/OCGenerateIvarsHandler.getActionTitle:()Ljava/lang/String;
        //   342: iload           13
        //   344: invokespecial   com/jetbrains/cidr/lang/generate/handlers/OCGenerateIvarsHandler$2.<init>:(Lcom/jetbrains/cidr/lang/generate/handlers/OCGenerateIvarsHandler;Lcom/jetbrains/cidr/lang/symbols/objc/OCClassSymbol;Ljava/util/List;Ljava/util/Map;Ljava/util/Map;Ljava/util/Map;Ljava/util/List;Lcom/jetbrains/cidr/lang/quickfixes/OCReleaseVariablesIntentionAction;Lcom/jetbrains/cidr/lang/parser/OCElementType;Ljava/lang/String;Z)V
        //   347: astore          20
        //   349: aload           20
        //   351: invokevirtual   com/jetbrains/cidr/lang/refactoring/OCConvertMemberRefactoringProcessor.run:()V
        //   354: return         
        //    Signature:
        //  (Lcom/intellij/psi/PsiFile;Lcom/jetbrains/cidr/lang/generate/actions/OCGenerateIvarsActionContext;Ljava/util/List<Lcom/jetbrains/cidr/lang/symbols/objc/OCPropertySymbol;>;Ljava/lang/String;Z)V
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                                
        //  -----  -----  -----  -----  ------------------------------------
        //  80     99     99     103    Ljava/lang/IllegalArgumentException;
        //  185    191    191    195    Ljava/lang/IllegalArgumentException;
        //  195    210    213    217    Ljava/lang/IllegalArgumentException;
        //  220    237    240    244    Ljava/lang/IllegalArgumentException;
        //  282    294    294    298    Ljava/lang/IllegalArgumentException;
        //  304    327    327    331    Ljava/lang/IllegalArgumentException;
        // 
        // The error that occurred was:
        // 
        // java.lang.IllegalStateException: Expression is linked from several locations: Label_0380_1:
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
    
    protected boolean replaceAllProperties() {
        return false;
    }
    
    @Nullable
    protected static Boolean askGenerateIvars(final Project project) {
        final Ref create = Ref.create();
        final OCCodeStyleSettings ocCodeStyleSettings = (OCCodeStyleSettings)CodeStyleSettingsManager.getSettings(project).getCustomSettings((Class)OCCodeStyleSettings.class);
        final OCCodeStyleSettings.RememberedOption askAndSave = OCCodeStyleSettings.askAndSave(ocCodeStyleSettings.GENERATE_INSTANCE_VARIABLES_FOR_PROPERTIES, "Do you want to generate instance variables for chosen properties?", "Generate instance variables", (Ref<Boolean>)create);
        try {
            if (askAndSave == null) {
                return null;
            }
        }
        catch (IllegalArgumentException ex) {
            throw b(ex);
        }
        ocCodeStyleSettings.GENERATE_INSTANCE_VARIABLES_FOR_PROPERTIES = askAndSave;
        return (Boolean)create.get();
    }
    
    @Nullable
    protected Boolean generateIvars(final Project project) {
        try {
            if (this.replaceAllProperties()) {
                return true;
            }
        }
        catch (IllegalArgumentException ex) {
            throw b(ex);
        }
        return askGenerateIvars(project);
    }
    
    @Override
    protected OCMemberChooserObject[] getChooserNodes(final OCMemberChooser ocMemberChooser, final Collection<OCPropertySymbol> collection, final OCGenerateIvarsActionContext ocGenerateIvarsActionContext, final int n) {
        try {
            if (OCClassActionHandlerBase.getOption(ocMemberChooser.getOptionSelections(), (OCOption<Boolean, JComponent>)OCGenerateIvarsHandler.SHOW_SYNTHESIZED)) {
                return super.getChooserNodes(ocMemberChooser, collection, ocGenerateIvarsActionContext, n);
            }
        }
        catch (IllegalArgumentException ex) {
            throw b(ex);
        }
        return super.getChooserNodes(ocMemberChooser, ContainerUtil.filter((Collection)collection, (Condition)a(ocGenerateIvarsActionContext)), ocGenerateIvarsActionContext, n);
    }
    
    private static Condition<OCPropertySymbol> a(final OCGenerateIvarsActionContext ocGenerateIvarsActionContext) {
        final HashMap hashMap = new HashMap();
        ocGenerateIvarsActionContext.getType().processMembers(OCMemberSymbol.class, (com.intellij.util.Processor<? super OCMemberSymbol>)(ocMemberSymbol -> {
            try {
                if (ocMemberSymbol instanceof OCSynthesizeSymbol) {
                    hashMap.put(ocMemberSymbol.getName(), 10);
                    return true;
                }
            }
            catch (IllegalArgumentException ex) {
                throw b(ex);
            }
            try {
                if (!(ocMemberSymbol instanceof OCMethodSymbol) || !ocMemberSymbol.isDefinition()) {
                    return true;
                }
            }
            catch (IllegalArgumentException ex2) {
                throw b(ex2);
            }
            final OCMethodSymbol ocMethodSymbol = (OCMethodSymbol)ocMemberSymbol.getAssociatedSymbol();
            OCPropertySymbol generatedFromProperty = null;
            Label_0083: {
                try {
                    if (ocMethodSymbol != null) {
                        generatedFromProperty = ocMethodSymbol.getGeneratedFromProperty();
                        break Label_0083;
                    }
                }
                catch (IllegalArgumentException ex3) {
                    throw b(ex3);
                }
                generatedFromProperty = null;
            }
            final OCPropertySymbol ocPropertySymbol = generatedFromProperty;
            if (ocPropertySymbol != null) {
                final Integer n = hashMap.get(ocPropertySymbol.getName());
                String name = null;
                int n2 = 0;
                Label_0132: {
                    try {
                        name = ocPropertySymbol.getName();
                        if (n != null) {
                            n2 = n + 1;
                            break Label_0132;
                        }
                    }
                    catch (IllegalArgumentException ex4) {
                        throw b(ex4);
                    }
                    n2 = 1;
                }
                hashMap.put(name, n2);
            }
            return true;
        }));
        return (Condition<OCPropertySymbol>)(ocPropertySymbol -> {
            final String categoryName = ((OCSymbolWithParent<T, OCClassSymbol>)ocPropertySymbol).getParent().getCategoryName();
            Label_0039: {
                try {
                    if (categoryName == null) {
                        break Label_0039;
                    }
                    final String s = categoryName;
                    final boolean b = s.isEmpty();
                    if (!b) {
                        return false;
                    }
                    break Label_0039;
                }
                catch (IllegalArgumentException ex) {
                    throw b(ex);
                }
                try {
                    final String s = categoryName;
                    final boolean b = s.isEmpty();
                    if (!b) {
                        return false;
                    }
                }
                catch (IllegalArgumentException ex2) {
                    throw b(ex2);
                }
            }
            if (((OCSymbolWithParent<T, OCClassSymbol>)ocPropertySymbol).getParent().equals(ocGenerateIvarsActionContext.getInterfaceSymbol())) {
                final Integer n = hashMap.get(ocPropertySymbol.getName());
                Label_0111: {
                    int n2 = 0;
                    Label_0093: {
                        try {
                            if (ocPropertySymbol.isReadonly()) {
                                n2 = 1;
                                break Label_0093;
                            }
                        }
                        catch (IllegalArgumentException ex3) {
                            throw b(ex3);
                        }
                        n2 = 2;
                        try {
                            if (n != null) {
                                final int intValue = n;
                                break Label_0111;
                            }
                        }
                        catch (IllegalArgumentException ex4) {
                            throw b(ex4);
                        }
                    }
                    final int intValue = 0;
                    try {
                        if (n2 > intValue) {
                            return true;
                        }
                    }
                    catch (IllegalArgumentException ex5) {
                        throw b(ex5);
                    }
                }
                return false;
            }
            try {
                if (ocPropertySymbol.getAssociatedSymbol() == null) {
                    return true;
                }
            }
            catch (IllegalArgumentException ex6) {
                throw b(ex6);
            }
            return false;
        });
    }
    
    @Override
    protected void optionValueChanged(final OCMemberChooser ocMemberChooser, final Collection<OCPropertySymbol> collection, final OCOption ocOption, final OCGenerateIvarsActionContext ocGenerateIvarsActionContext) {
        try {
            if (OCGenerateIvarsHandler.SHOW_SYNTHESIZED.equals(ocOption)) {
                ocMemberChooser.resetElements(this.getChooserNodes(ocMemberChooser, collection, ocGenerateIvarsActionContext, 0));
            }
        }
        catch (IllegalArgumentException ex) {
            throw b(ex);
        }
    }
    
    static {
        SHOW_SYNTHESIZED = new OCBooleanOption("Show synthesized properties");
    }
    
    private static IllegalArgumentException b(final IllegalArgumentException ex) {
        return ex;
    }
}
