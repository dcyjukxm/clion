// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.lang.generate.handlers;

import com.jetbrains.cidr.lang.OCBundle;
import com.intellij.ui.components.JBList;
import com.jetbrains.cidr.lang.psi.OCFunctionDeclaration;
import com.intellij.usageView.UsageInfo;
import com.intellij.usages.UsageInfo2UsageAdapter;
import com.intellij.usages.Usage;
import com.jetbrains.cidr.lang.refactoring.util.OCChangeUtil;
import com.jetbrains.cidr.lang.generate.actions.OCActionContext;
import com.jetbrains.cidr.lang.psi.OCSymbolDeclarator;
import org.jetbrains.annotations.Contract;
import com.intellij.codeInsight.FileModificationService;
import com.intellij.openapi.application.WriteAction;
import com.intellij.openapi.command.CommandProcessor;
import com.jetbrains.cidr.lang.OCLog;
import java.util.Collections;
import com.jetbrains.cidr.lang.symbols.cpp.OCFunctionSymbol;
import com.intellij.util.containers.ContainerUtil;
import com.intellij.openapi.project.Project;
import com.jetbrains.cidr.lang.generate.OCGenerateUtil;
import com.intellij.psi.PsiElement;
import com.jetbrains.cidr.lang.util.OCCodeInsightUtil;
import javax.swing.JComponent;
import com.jetbrains.cidr.lang.generate.OCCppDefinitionsUtil;
import com.jetbrains.cidr.lang.generate.OCCaretLocation;
import com.intellij.featureStatistics.FeatureUsageTracker;
import java.util.Collection;
import com.jetbrains.cidr.lang.generate.OCMemberChooser;
import com.jetbrains.cidr.lang.settings.OCOption;
import com.intellij.openapi.util.Pair;
import java.util.List;
import com.jetbrains.cidr.lang.settings.OCCodeStyleSettings;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import com.intellij.openapi.editor.Editor;
import com.intellij.psi.PsiFile;
import com.jetbrains.cidr.lang.settings.OCBooleanOption;
import com.jetbrains.cidr.lang.generate.actions.OCCppActionContext;
import com.jetbrains.cidr.lang.symbols.cpp.OCSymbolWithQualifiedName;
import com.jetbrains.cidr.lang.symbols.symtable.OCMembersContainer;

public abstract class OCCCppGenerateHandlerBase<P extends OCMembersContainer, M extends OCSymbolWithQualifiedName<?>, C extends OCCppActionContext<P, M>> extends OCClassActionHandlerBase<P, M, C>
{
    private static final OCBooleanOption INLINE_OPTION;
    
    @Override
    protected void loadOptions(final PsiFile psiFile, @Nullable final Editor editor, @NotNull final C c, @Nullable final OCCodeStyleSettings ocCodeStyleSettings, @NotNull final List<Pair<OCOption, Object>> list) {
        try {
            if (c == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "actionContext", "com/jetbrains/cidr/lang/generate/handlers/OCCCppGenerateHandlerBase", "loadOptions"));
            }
        }
        catch (UnsupportedOperationException ex) {
            throw b(ex);
        }
        try {
            if (list == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "options", "com/jetbrains/cidr/lang/generate/handlers/OCCCppGenerateHandlerBase", "loadOptions"));
            }
        }
        catch (UnsupportedOperationException ex2) {
            throw b(ex2);
        }
        list.add((Pair<OCOption, Object>)new Pair((Object)OCCCppGenerateHandlerBase.INLINE_OPTION, (Object)this.defaultInlineOption(psiFile, editor, c)));
        super.loadOptions(psiFile, editor, c, ocCodeStyleSettings, list);
    }
    
    @Override
    protected void optionValueChanged(final OCMemberChooser ocMemberChooser, final Collection<M> collection, final OCOption ocOption, final C c) {
        try {
            super.optionValueChanged(ocMemberChooser, collection, ocOption, c);
            if (OCCCppGenerateHandlerBase.INLINE_OPTION.equals(ocOption)) {
                FeatureUsageTracker.getInstance().triggerFeatureUsed("cidr.generate.inline.changed");
            }
        }
        catch (UnsupportedOperationException ex) {
            throw b(ex);
        }
    }
    
    protected boolean defaultInlineOption(final PsiFile psiFile, @Nullable final Editor editor, @NotNull final C c) {
        try {
            if (c == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "actionContext", "com/jetbrains/cidr/lang/generate/handlers/OCCCppGenerateHandlerBase", "defaultInlineOption"));
            }
        }
        catch (UnsupportedOperationException ex) {
            throw b(ex);
        }
        return OCCppDefinitionsUtil.shouldInlineNewDefinitions(c.getParent(), OCCaretLocation.byEditor(psiFile, editor));
    }
    
    protected OCCppDefinitionsUtil.InlinePolicy getInlinePolicy(@NotNull final C c) {
        try {
            if (c == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "actionContext", "com/jetbrains/cidr/lang/generate/handlers/OCCCppGenerateHandlerBase", "getInlinePolicy"));
            }
        }
        catch (UnsupportedOperationException ex) {
            throw b(ex);
        }
        final Boolean b = this.getOption(c, (OCOption<Boolean, JComponent>)OCCCppGenerateHandlerBase.INLINE_OPTION);
        try {
            if (b != null) {
                return OCCppDefinitionsUtil.InlinePolicy.get(b);
            }
        }
        catch (UnsupportedOperationException ex2) {
            throw b(ex2);
        }
        return OCCppDefinitionsUtil.InlinePolicy.PREFERRED;
    }
    
    @Override
    public boolean isValidFor(final Editor editor, final PsiFile psiFile) {
        return OCCodeInsightUtil.isValid((PsiElement)psiFile);
    }
    
    @NotNull
    protected abstract List<OCGenerateUtil.Replacement> getReplacements(@NotNull final OCCaretLocation p0, @NotNull final C p1, @NotNull final List<M> p2);
    
    @NotNull
    protected List<? extends P> getParents(@NotNull final PsiElement p0) {
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
        //    18: ldc             "at"
        //    20: aastore        
        //    21: dup            
        //    22: ldc             1
        //    24: ldc             "com/jetbrains/cidr/lang/generate/handlers/OCCCppGenerateHandlerBase"
        //    26: aastore        
        //    27: dup            
        //    28: ldc             2
        //    30: ldc             "getParents"
        //    32: aastore        
        //    33: invokestatic    java/lang/String.format:(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
        //    36: invokespecial   java/lang/IllegalArgumentException.<init>:(Ljava/lang/String;)V
        //    39: athrow         
        //    40: invokestatic    com/jetbrains/cidr/lang/generate/handlers/OCCCppGenerateHandlerBase.b:(Ljava/lang/RuntimeException;)Ljava/lang/RuntimeException;
        //    43: athrow         
        //    44: aload_1        
        //    45: iconst_2       
        //    46: anewarray       Ljava/lang/Class;
        //    49: dup            
        //    50: iconst_0       
        //    51: ldc             Lcom/jetbrains/cidr/lang/psi/OCStruct;.class
        //    53: aastore        
        //    54: dup            
        //    55: iconst_1       
        //    56: ldc             Lcom/jetbrains/cidr/lang/psi/OCUnion;.class
        //    58: aastore        
        //    59: invokestatic    com/intellij/psi/util/PsiTreeUtil.getContextOfType:(Lcom/intellij/psi/PsiElement;[Ljava/lang/Class;)Lcom/intellij/psi/PsiElement;
        //    62: checkcast       Lcom/jetbrains/cidr/lang/psi/OCStructLike;
        //    65: astore_2       
        //    66: aload_0        
        //    67: invokevirtual   com/jetbrains/cidr/lang/generate/handlers/OCCCppGenerateHandlerBase.allowUnions:()Z
        //    70: ifne            136
        //    73: aload_2        
        //    74: instanceof      Lcom/jetbrains/cidr/lang/psi/OCUnion;
        //    77: ifeq            136
        //    80: goto            87
        //    83: invokestatic    com/jetbrains/cidr/lang/generate/handlers/OCCCppGenerateHandlerBase.b:(Ljava/lang/RuntimeException;)Ljava/lang/RuntimeException;
        //    86: athrow         
        //    87: invokestatic    java/util/Collections.emptyList:()Ljava/util/List;
        //    90: dup            
        //    91: ifnonnull       135
        //    94: goto            101
        //    97: invokestatic    com/jetbrains/cidr/lang/generate/handlers/OCCCppGenerateHandlerBase.b:(Ljava/lang/RuntimeException;)Ljava/lang/RuntimeException;
        //   100: athrow         
        //   101: new             Ljava/lang/IllegalStateException;
        //   104: dup            
        //   105: ldc             "@NotNull method %s.%s must not return null"
        //   107: ldc             2
        //   109: anewarray       Ljava/lang/Object;
        //   112: dup            
        //   113: ldc             0
        //   115: ldc             "com/jetbrains/cidr/lang/generate/handlers/OCCCppGenerateHandlerBase"
        //   117: aastore        
        //   118: dup            
        //   119: ldc             1
        //   121: ldc             "getParents"
        //   123: aastore        
        //   124: invokestatic    java/lang/String.format:(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
        //   127: invokespecial   java/lang/IllegalStateException.<init>:(Ljava/lang/String;)V
        //   130: athrow         
        //   131: invokestatic    com/jetbrains/cidr/lang/generate/handlers/OCCCppGenerateHandlerBase.b:(Ljava/lang/RuntimeException;)Ljava/lang/RuntimeException;
        //   134: athrow         
        //   135: areturn        
        //   136: new             Ljava/util/ArrayList;
        //   139: dup            
        //   140: invokespecial   java/util/ArrayList.<init>:()V
        //   143: astore_3       
        //   144: aload_2        
        //   145: ifnull          198
        //   148: aload_2        
        //   149: invokeinterface com/jetbrains/cidr/lang/psi/OCStructLike.getSymbol:()Lcom/jetbrains/cidr/lang/symbols/OCSymbol;
        //   154: checkcast       Lcom/jetbrains/cidr/lang/symbols/cpp/OCStructSymbol;
        //   157: astore          4
        //   159: aload           4
        //   161: ifnull          195
        //   164: aload           4
        //   166: invokevirtual   com/jetbrains/cidr/lang/symbols/cpp/OCStructSymbol.isPredeclaration:()Z
        //   169: ifne            195
        //   172: goto            179
        //   175: invokestatic    com/jetbrains/cidr/lang/generate/handlers/OCCCppGenerateHandlerBase.b:(Ljava/lang/RuntimeException;)Ljava/lang/RuntimeException;
        //   178: athrow         
        //   179: aload_3        
        //   180: aload           4
        //   182: invokeinterface java/util/List.add:(Ljava/lang/Object;)Z
        //   187: pop            
        //   188: goto            195
        //   191: invokestatic    com/jetbrains/cidr/lang/generate/handlers/OCCCppGenerateHandlerBase.b:(Ljava/lang/RuntimeException;)Ljava/lang/RuntimeException;
        //   194: athrow         
        //   195: goto            510
        //   198: new             Lcom/jetbrains/cidr/lang/generate/handlers/OCCCppGenerateHandlerBase$1;
        //   201: dup            
        //   202: aload_0        
        //   203: invokespecial   com/jetbrains/cidr/lang/generate/handlers/OCCCppGenerateHandlerBase$1.<init>:(Lcom/jetbrains/cidr/lang/generate/handlers/OCCCppGenerateHandlerBase;)V
        //   206: astore          4
        //   208: new             Lcom/jetbrains/cidr/lang/util/OCCommonProcessors$TypeFilteredProcessor;
        //   211: dup            
        //   212: aload           4
        //   214: ldc             Lcom/jetbrains/cidr/lang/symbols/cpp/OCNamespaceSymbol;.class
        //   216: invokespecial   com/jetbrains/cidr/lang/util/OCCommonProcessors$TypeFilteredProcessor.<init>:(Lcom/intellij/util/Processor;Ljava/lang/Class;)V
        //   219: astore          5
        //   221: aload_1        
        //   222: invokeinterface com/intellij/psi/PsiElement.getContainingFile:()Lcom/intellij/psi/PsiFile;
        //   227: checkcast       Lcom/jetbrains/cidr/lang/psi/OCFile;
        //   230: astore          6
        //   232: aload           6
        //   234: aload           5
        //   236: invokeinterface com/jetbrains/cidr/lang/psi/OCFile.processSymbolsRecursively:(Lcom/intellij/util/Processor;)Z
        //   241: pop            
        //   242: aconst_null    
        //   243: astore          7
        //   245: aload           6
        //   247: invokeinterface com/jetbrains/cidr/lang/psi/OCFile.isHeader:()Z
        //   252: ifne            286
        //   255: aload           6
        //   257: invokeinterface com/jetbrains/cidr/lang/psi/OCFile.getAssociatedFile:()Lcom/jetbrains/cidr/lang/psi/OCFile;
        //   262: astore          7
        //   264: aload           7
        //   266: ifnull          286
        //   269: aload           7
        //   271: aload           5
        //   273: invokeinterface com/jetbrains/cidr/lang/psi/OCFile.processSymbolsRecursively:(Lcom/intellij/util/Processor;)Z
        //   278: pop            
        //   279: goto            286
        //   282: invokestatic    com/jetbrains/cidr/lang/generate/handlers/OCCCppGenerateHandlerBase.b:(Ljava/lang/RuntimeException;)Ljava/lang/RuntimeException;
        //   285: athrow         
        //   286: aload_1        
        //   287: ldc             Lcom/jetbrains/cidr/lang/psi/OCCppNamespace;.class
        //   289: invokestatic    com/intellij/psi/util/PsiTreeUtil.getParentOfType:(Lcom/intellij/psi/PsiElement;Ljava/lang/Class;)Lcom/intellij/psi/PsiElement;
        //   292: checkcast       Lcom/jetbrains/cidr/lang/psi/OCCppNamespace;
        //   295: astore          8
        //   297: aload           8
        //   299: ifnull          319
        //   302: aload           8
        //   304: invokeinterface com/jetbrains/cidr/lang/psi/OCCppNamespace.getSymbol:()Lcom/jetbrains/cidr/lang/symbols/OCSymbol;
        //   309: checkcast       Lcom/jetbrains/cidr/lang/symbols/cpp/OCNamespaceSymbol;
        //   312: goto            320
        //   315: invokestatic    com/jetbrains/cidr/lang/generate/handlers/OCCCppGenerateHandlerBase.b:(Ljava/lang/RuntimeException;)Ljava/lang/RuntimeException;
        //   318: athrow         
        //   319: aconst_null    
        //   320: astore          9
        //   322: aload           4
        //   324: invokevirtual   com/intellij/util/CommonProcessors$CollectProcessor.getResults:()Ljava/util/Collection;
        //   327: invokeinterface java/util/Collection.iterator:()Ljava/util/Iterator;
        //   332: astore          10
        //   334: aload           10
        //   336: invokeinterface java/util/Iterator.hasNext:()Z
        //   341: ifeq            442
        //   344: aload           10
        //   346: invokeinterface java/util/Iterator.next:()Ljava/lang/Object;
        //   351: checkcast       Lcom/jetbrains/cidr/lang/symbols/cpp/OCNamespaceSymbol;
        //   354: astore          11
        //   356: aload           9
        //   358: ifnonnull       377
        //   361: aload_3        
        //   362: aload           11
        //   364: invokeinterface java/util/List.add:(Ljava/lang/Object;)Z
        //   369: pop            
        //   370: goto            439
        //   373: invokestatic    com/jetbrains/cidr/lang/generate/handlers/OCCCppGenerateHandlerBase.b:(Ljava/lang/RuntimeException;)Ljava/lang/RuntimeException;
        //   376: athrow         
        //   377: aload           11
        //   379: astore          12
        //   381: aload           9
        //   383: aload           12
        //   385: invokevirtual   com/jetbrains/cidr/lang/symbols/cpp/OCNamespaceSymbol.isSameSymbol:(Lcom/jetbrains/cidr/lang/symbols/OCSymbol;)Z
        //   388: ifeq            407
        //   391: aload_3        
        //   392: aload           11
        //   394: invokeinterface java/util/List.add:(Ljava/lang/Object;)Z
        //   399: pop            
        //   400: goto            439
        //   403: invokestatic    com/jetbrains/cidr/lang/generate/handlers/OCCCppGenerateHandlerBase.b:(Ljava/lang/RuntimeException;)Ljava/lang/RuntimeException;
        //   406: athrow         
        //   407: aload           12
        //   409: invokevirtual   com/jetbrains/cidr/lang/symbols/cpp/OCNamespaceSymbol.getParent:()Lcom/jetbrains/cidr/lang/symbols/cpp/OCSymbolWithQualifiedName;
        //   412: astore          13
        //   414: aload           13
        //   416: instanceof      Lcom/jetbrains/cidr/lang/symbols/cpp/OCNamespaceSymbol;
        //   419: ifne            429
        //   422: goto            439
        //   425: invokestatic    com/jetbrains/cidr/lang/generate/handlers/OCCCppGenerateHandlerBase.b:(Ljava/lang/RuntimeException;)Ljava/lang/RuntimeException;
        //   428: athrow         
        //   429: aload           13
        //   431: checkcast       Lcom/jetbrains/cidr/lang/symbols/cpp/OCNamespaceSymbol;
        //   434: astore          12
        //   436: goto            381
        //   439: goto            334
        //   442: aload           9
        //   444: ifnonnull       510
        //   447: aload_0        
        //   448: invokevirtual   com/jetbrains/cidr/lang/generate/handlers/OCCCppGenerateHandlerBase.allowNamespacesAndTopLevel:()Z
        //   451: ifeq            510
        //   454: goto            461
        //   457: invokestatic    com/jetbrains/cidr/lang/generate/handlers/OCCCppGenerateHandlerBase.b:(Ljava/lang/RuntimeException;)Ljava/lang/RuntimeException;
        //   460: athrow         
        //   461: aload           7
        //   463: ifnull          495
        //   466: goto            473
        //   469: invokestatic    com/jetbrains/cidr/lang/generate/handlers/OCCCppGenerateHandlerBase.b:(Ljava/lang/RuntimeException;)Ljava/lang/RuntimeException;
        //   472: athrow         
        //   473: aload_3        
        //   474: aload           7
        //   476: iconst_0       
        //   477: invokeinterface com/jetbrains/cidr/lang/psi/OCFile.getMembersContainer:(Z)Lcom/jetbrains/cidr/lang/symbols/cpp/OCNamespaceLikeSymbol;
        //   482: invokeinterface java/util/List.add:(Ljava/lang/Object;)Z
        //   487: pop            
        //   488: goto            495
        //   491: invokestatic    com/jetbrains/cidr/lang/generate/handlers/OCCCppGenerateHandlerBase.b:(Ljava/lang/RuntimeException;)Ljava/lang/RuntimeException;
        //   494: athrow         
        //   495: aload_3        
        //   496: aload           6
        //   498: iconst_0       
        //   499: invokeinterface com/jetbrains/cidr/lang/psi/OCFile.getMembersContainer:(Z)Lcom/jetbrains/cidr/lang/symbols/cpp/OCNamespaceLikeSymbol;
        //   504: invokeinterface java/util/List.add:(Ljava/lang/Object;)Z
        //   509: pop            
        //   510: aload_3        
        //   511: dup            
        //   512: ifnonnull       549
        //   515: new             Ljava/lang/IllegalStateException;
        //   518: dup            
        //   519: ldc             "@NotNull method %s.%s must not return null"
        //   521: ldc             2
        //   523: anewarray       Ljava/lang/Object;
        //   526: dup            
        //   527: ldc             0
        //   529: ldc             "com/jetbrains/cidr/lang/generate/handlers/OCCCppGenerateHandlerBase"
        //   531: aastore        
        //   532: dup            
        //   533: ldc             1
        //   535: ldc             "getParents"
        //   537: aastore        
        //   538: invokestatic    java/lang/String.format:(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
        //   541: invokespecial   java/lang/IllegalStateException.<init>:(Ljava/lang/String;)V
        //   544: athrow         
        //   545: invokestatic    com/jetbrains/cidr/lang/generate/handlers/OCCCppGenerateHandlerBase.b:(Ljava/lang/RuntimeException;)Ljava/lang/RuntimeException;
        //   548: athrow         
        //   549: areturn        
        //    Signature:
        //  (Lcom/intellij/psi/PsiElement;)Ljava/util/List<+TP;>;
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                                     
        //  -----  -----  -----  -----  -----------------------------------------
        //  0      40     40     44     Ljava/lang/UnsupportedOperationException;
        //  66     80     83     87     Ljava/lang/UnsupportedOperationException;
        //  73     94     97     101    Ljava/lang/UnsupportedOperationException;
        //  87     131    131    135    Ljava/lang/UnsupportedOperationException;
        //  159    172    175    179    Ljava/lang/UnsupportedOperationException;
        //  164    188    191    195    Ljava/lang/UnsupportedOperationException;
        //  264    279    282    286    Ljava/lang/UnsupportedOperationException;
        //  297    315    315    319    Ljava/lang/UnsupportedOperationException;
        //  356    373    373    377    Ljava/lang/UnsupportedOperationException;
        //  381    403    403    407    Ljava/lang/UnsupportedOperationException;
        //  414    425    425    429    Ljava/lang/UnsupportedOperationException;
        //  442    454    457    461    Ljava/lang/UnsupportedOperationException;
        //  447    466    469    473    Ljava/lang/UnsupportedOperationException;
        //  461    488    491    495    Ljava/lang/UnsupportedOperationException;
        //  510    545    545    549    Ljava/lang/UnsupportedOperationException;
        // 
        // The error that occurred was:
        // 
        // java.lang.IllegalStateException: Expression is linked from several locations: Label_0087:
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
    
    protected boolean allowNamespacesAndTopLevel() {
        return false;
    }
    
    protected boolean allowUnions() {
        return false;
    }
    
    protected boolean continueTillNoParentsLeft() {
        return false;
    }
    
    @NotNull
    protected String getNoParentsMessage() {
        String string;
        try {
            string = "No classes to " + this.getActionTitle().toLowerCase();
            if (string == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/generate/handlers/OCCCppGenerateHandlerBase", "getNoParentsMessage"));
            }
        }
        catch (UnsupportedOperationException ex) {
            throw b(ex);
        }
        return string;
    }
    
    @NotNull
    protected String getParentChooserTitle() {
        String s;
        try {
            s = "Choose Destination";
            if (s == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/generate/handlers/OCCCppGenerateHandlerBase", "getParentChooserTitle"));
            }
        }
        catch (UnsupportedOperationException ex) {
            throw b(ex);
        }
        return s;
    }
    
    @NotNull
    protected String getFeatureId() {
        String s;
        try {
            s = "codeassists.altInsert";
            if (s == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/generate/handlers/OCCCppGenerateHandlerBase", "getFeatureId"));
            }
        }
        catch (UnsupportedOperationException ex) {
            throw b(ex);
        }
        return s;
    }
    
    @Override
    public void invoke(@NotNull final Project p0, @Nullable final Editor p1, @NotNull final PsiFile p2) {
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
        //    24: ldc             "com/jetbrains/cidr/lang/generate/handlers/OCCCppGenerateHandlerBase"
        //    26: aastore        
        //    27: dup            
        //    28: ldc             2
        //    30: ldc             "invoke"
        //    32: aastore        
        //    33: invokestatic    java/lang/String.format:(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
        //    36: invokespecial   java/lang/IllegalArgumentException.<init>:(Ljava/lang/String;)V
        //    39: athrow         
        //    40: invokestatic    com/jetbrains/cidr/lang/generate/handlers/OCCCppGenerateHandlerBase.b:(Ljava/lang/RuntimeException;)Ljava/lang/RuntimeException;
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
        //    68: ldc             "com/jetbrains/cidr/lang/generate/handlers/OCCCppGenerateHandlerBase"
        //    70: aastore        
        //    71: dup            
        //    72: ldc             2
        //    74: ldc             "invoke"
        //    76: aastore        
        //    77: invokestatic    java/lang/String.format:(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
        //    80: invokespecial   java/lang/IllegalArgumentException.<init>:(Ljava/lang/String;)V
        //    83: athrow         
        //    84: invokestatic    com/jetbrains/cidr/lang/generate/handlers/OCCCppGenerateHandlerBase.b:(Ljava/lang/RuntimeException;)Ljava/lang/RuntimeException;
        //    87: athrow         
        //    88: aload_1        
        //    89: invokestatic    com/intellij/psi/PsiDocumentManager.getInstance:(Lcom/intellij/openapi/project/Project;)Lcom/intellij/psi/PsiDocumentManager;
        //    92: invokevirtual   com/intellij/psi/PsiDocumentManager.commitAllDocuments:()V
        //    95: aload_0        
        //    96: aload_2        
        //    97: aload_3        
        //    98: invokespecial   com/jetbrains/cidr/lang/generate/handlers/OCCCppGenerateHandlerBase.a:(Lcom/intellij/openapi/editor/Editor;Lcom/intellij/psi/PsiFile;)Ljava/util/List;
        //   101: astore          4
        //   103: aload           4
        //   105: invokeinterface java/util/List.size:()I
        //   110: iconst_1       
        //   111: if_icmple       234
        //   114: aload_2        
        //   115: ifnull          234
        //   118: goto            125
        //   121: invokestatic    com/jetbrains/cidr/lang/generate/handlers/OCCCppGenerateHandlerBase.b:(Ljava/lang/RuntimeException;)Ljava/lang/RuntimeException;
        //   124: athrow         
        //   125: invokestatic    com/intellij/openapi/application/ApplicationManager.getApplication:()Lcom/intellij/openapi/application/Application;
        //   128: invokeinterface com/intellij/openapi/application/Application.isUnitTestMode:()Z
        //   133: ifne            234
        //   136: goto            143
        //   139: invokestatic    com/jetbrains/cidr/lang/generate/handlers/OCCCppGenerateHandlerBase.b:(Ljava/lang/RuntimeException;)Ljava/lang/RuntimeException;
        //   142: athrow         
        //   143: new             Lcom/intellij/ui/components/JBList;
        //   146: dup            
        //   147: aload           4
        //   149: invokespecial   com/intellij/ui/components/JBList.<init>:(Ljava/util/Collection;)V
        //   152: astore          5
        //   154: aload           5
        //   156: new             Lcom/jetbrains/cidr/lang/generate/handlers/OCCCppGenerateHandlerBase$2;
        //   159: dup            
        //   160: aload_0        
        //   161: invokespecial   com/jetbrains/cidr/lang/generate/handlers/OCCCppGenerateHandlerBase$2.<init>:(Lcom/jetbrains/cidr/lang/generate/handlers/OCCCppGenerateHandlerBase;)V
        //   164: invokevirtual   com/intellij/ui/components/JBList.setCellRenderer:(Ljavax/swing/ListCellRenderer;)V
        //   167: aload           5
        //   169: iconst_0       
        //   170: invokevirtual   com/intellij/ui/components/JBList.setSelectionMode:(I)V
        //   173: invokestatic    com/intellij/openapi/ui/popup/JBPopupFactory.getInstance:()Lcom/intellij/openapi/ui/popup/JBPopupFactory;
        //   176: aload           5
        //   178: invokevirtual   com/intellij/openapi/ui/popup/JBPopupFactory.createListPopupBuilder:(Ljavax/swing/JList;)Lcom/intellij/openapi/ui/popup/PopupChooserBuilder;
        //   181: aload_0        
        //   182: invokevirtual   com/jetbrains/cidr/lang/generate/handlers/OCCCppGenerateHandlerBase.getParentChooserTitle:()Ljava/lang/String;
        //   185: invokevirtual   com/intellij/openapi/ui/popup/PopupChooserBuilder.setTitle:(Ljava/lang/String;)Lcom/intellij/openapi/ui/popup/PopupChooserBuilder;
        //   188: iconst_0       
        //   189: invokevirtual   com/intellij/openapi/ui/popup/PopupChooserBuilder.setMovable:(Z)Lcom/intellij/openapi/ui/popup/PopupChooserBuilder;
        //   192: iconst_0       
        //   193: invokevirtual   com/intellij/openapi/ui/popup/PopupChooserBuilder.setResizable:(Z)Lcom/intellij/openapi/ui/popup/PopupChooserBuilder;
        //   196: iconst_1       
        //   197: invokevirtual   com/intellij/openapi/ui/popup/PopupChooserBuilder.setRequestFocus:(Z)Lcom/intellij/openapi/ui/popup/PopupChooserBuilder;
        //   200: aload_0        
        //   201: aload           5
        //   203: aload_1        
        //   204: aload_2        
        //   205: aload_3        
        //   206: invokedynamic   run:(Lcom/jetbrains/cidr/lang/generate/handlers/OCCCppGenerateHandlerBase;Lcom/intellij/ui/components/JBList;Lcom/intellij/openapi/project/Project;Lcom/intellij/openapi/editor/Editor;Lcom/intellij/psi/PsiFile;)Ljava/lang/Runnable;
        //   211: invokevirtual   com/intellij/openapi/ui/popup/PopupChooserBuilder.setItemChoosenCallback:(Ljava/lang/Runnable;)Lcom/intellij/openapi/ui/popup/PopupChooserBuilder;
        //   214: invokedynamic   fun:()Lcom/intellij/util/Function;
        //   219: invokevirtual   com/intellij/openapi/ui/popup/PopupChooserBuilder.setFilteringEnabled:(Lcom/intellij/util/Function;)Lcom/intellij/openapi/ui/popup/PopupChooserBuilder;
        //   222: invokevirtual   com/intellij/openapi/ui/popup/PopupChooserBuilder.createPopup:()Lcom/intellij/openapi/ui/popup/JBPopup;
        //   225: aload_2        
        //   226: invokeinterface com/intellij/openapi/ui/popup/JBPopup.showInBestPositionFor:(Lcom/intellij/openapi/editor/Editor;)V
        //   231: goto            348
        //   234: aload           4
        //   236: invokeinterface java/util/List.isEmpty:()Z
        //   241: ifne            334
        //   244: aload           4
        //   246: aload           4
        //   248: invokeinterface java/util/List.size:()I
        //   253: iconst_1       
        //   254: isub           
        //   255: invokeinterface java/util/List.get:(I)Ljava/lang/Object;
        //   260: checkcast       Lcom/jetbrains/cidr/lang/generate/actions/OCCppActionContext;
        //   263: astore          5
        //   265: aload_0        
        //   266: aload_1        
        //   267: aload_2        
        //   268: aload           5
        //   270: invokevirtual   com/jetbrains/cidr/lang/generate/handlers/OCCCppGenerateHandlerBase.checkContext:(Lcom/intellij/openapi/project/Project;Lcom/intellij/openapi/editor/Editor;Lcom/jetbrains/cidr/lang/generate/actions/OCActionContext;)Z
        //   273: ifeq            331
        //   276: aload_0        
        //   277: aload_1        
        //   278: aload_2        
        //   279: aload_3        
        //   280: aload           5
        //   282: invokevirtual   com/jetbrains/cidr/lang/generate/handlers/OCCCppGenerateHandlerBase.invoke:(Lcom/intellij/openapi/project/Project;Lcom/intellij/openapi/editor/Editor;Lcom/intellij/psi/PsiFile;Lcom/jetbrains/cidr/lang/generate/actions/OCActionContext;)V
        //   285: aload           4
        //   287: invokeinterface java/util/List.size:()I
        //   292: iconst_1       
        //   293: if_icmple       331
        //   296: goto            303
        //   299: invokestatic    com/jetbrains/cidr/lang/generate/handlers/OCCCppGenerateHandlerBase.b:(Ljava/lang/RuntimeException;)Ljava/lang/RuntimeException;
        //   302: athrow         
        //   303: aload_0        
        //   304: invokevirtual   com/jetbrains/cidr/lang/generate/handlers/OCCCppGenerateHandlerBase.continueTillNoParentsLeft:()Z
        //   307: ifeq            331
        //   310: goto            317
        //   313: invokestatic    com/jetbrains/cidr/lang/generate/handlers/OCCCppGenerateHandlerBase.b:(Ljava/lang/RuntimeException;)Ljava/lang/RuntimeException;
        //   316: athrow         
        //   317: aload_0        
        //   318: aload_1        
        //   319: aload_2        
        //   320: aload_3        
        //   321: invokevirtual   com/jetbrains/cidr/lang/generate/handlers/OCCCppGenerateHandlerBase.invoke:(Lcom/intellij/openapi/project/Project;Lcom/intellij/openapi/editor/Editor;Lcom/intellij/psi/PsiFile;)V
        //   324: goto            331
        //   327: invokestatic    com/jetbrains/cidr/lang/generate/handlers/OCCCppGenerateHandlerBase.b:(Ljava/lang/RuntimeException;)Ljava/lang/RuntimeException;
        //   330: athrow         
        //   331: goto            348
        //   334: aload_1        
        //   335: aload_2        
        //   336: aload_0        
        //   337: invokevirtual   com/jetbrains/cidr/lang/generate/handlers/OCCCppGenerateHandlerBase.getNoParentsMessage:()Ljava/lang/String;
        //   340: aload_0        
        //   341: invokevirtual   com/jetbrains/cidr/lang/generate/handlers/OCCCppGenerateHandlerBase.getActionTitle:()Ljava/lang/String;
        //   344: aconst_null    
        //   345: invokestatic    com/intellij/refactoring/util/CommonRefactoringUtil.showErrorHint:(Lcom/intellij/openapi/project/Project;Lcom/intellij/openapi/editor/Editor;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)V
        //   348: return         
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                                     
        //  -----  -----  -----  -----  -----------------------------------------
        //  0      40     40     44     Ljava/lang/UnsupportedOperationException;
        //  44     84     84     88     Ljava/lang/UnsupportedOperationException;
        //  103    118    121    125    Ljava/lang/UnsupportedOperationException;
        //  114    136    139    143    Ljava/lang/UnsupportedOperationException;
        //  265    296    299    303    Ljava/lang/UnsupportedOperationException;
        //  276    310    313    317    Ljava/lang/UnsupportedOperationException;
        //  303    324    327    331    Ljava/lang/UnsupportedOperationException;
        // 
        // The error that occurred was:
        // 
        // java.lang.IllegalStateException: Expression is linked from several locations: Label_0303:
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
    private List<? extends C> a(@Nullable final Editor editor, @NotNull final PsiFile psiFile) {
        try {
            if (psiFile == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "psiFile", "com/jetbrains/cidr/lang/generate/handlers/OCCCppGenerateHandlerBase", "getContexts"));
            }
        }
        catch (UnsupportedOperationException ex) {
            throw b(ex);
        }
        final PsiElement element = OCGenerateUtil.getElementAt(editor, psiFile);
        List mapNotNull;
        try {
            mapNotNull = ContainerUtil.mapNotNull((Collection)this.getParents(element), p1 -> {
                // 
                // This method could not be decompiled.
                // 
                // Original Bytecode:
                // 
                //     0: aload_0        
                //     1: aload_2        
                //     2: aload_1        
                //     3: invokevirtual   com/jetbrains/cidr/lang/generate/handlers/OCCCppGenerateHandlerBase.evaluateActionContext:(Lcom/jetbrains/cidr/lang/symbols/symtable/OCMembersContainer;Lcom/intellij/psi/PsiElement;)Lcom/jetbrains/cidr/lang/generate/actions/OCActionContext;
                //     6: checkcast       Lcom/jetbrains/cidr/lang/generate/actions/OCCppActionContext;
                //     9: astore_3       
                //    10: aload_3        
                //    11: ifnull          70
                //    14: aload_3        
                //    15: invokevirtual   com/jetbrains/cidr/lang/generate/actions/OCCppActionContext.isValid:()Z
                //    18: ifeq            70
                //    21: goto            28
                //    24: invokestatic    com/jetbrains/cidr/lang/generate/handlers/OCCCppGenerateHandlerBase.b:(Ljava/lang/RuntimeException;)Ljava/lang/RuntimeException;
                //    27: athrow         
                //    28: aload_0        
                //    29: aload_3        
                //    30: invokevirtual   com/jetbrains/cidr/lang/generate/handlers/OCCCppGenerateHandlerBase.allowEmptySelection:(Lcom/jetbrains/cidr/lang/generate/actions/OCActionContext;)Z
                //    33: ifne            62
                //    36: goto            43
                //    39: invokestatic    com/jetbrains/cidr/lang/generate/handlers/OCCCppGenerateHandlerBase.b:(Ljava/lang/RuntimeException;)Ljava/lang/RuntimeException;
                //    42: athrow         
                //    43: aload_3        
                //    44: invokevirtual   com/jetbrains/cidr/lang/generate/actions/OCCppActionContext.getMemberCandidates:()Ljava/util/Collection;
                //    47: invokeinterface java/util/Collection.isEmpty:()Z
                //    52: ifne            70
                //    55: goto            62
                //    58: invokestatic    com/jetbrains/cidr/lang/generate/handlers/OCCCppGenerateHandlerBase.b:(Ljava/lang/RuntimeException;)Ljava/lang/RuntimeException;
                //    61: athrow         
                //    62: aload_3        
                //    63: goto            71
                //    66: invokestatic    com/jetbrains/cidr/lang/generate/handlers/OCCCppGenerateHandlerBase.b:(Ljava/lang/RuntimeException;)Ljava/lang/RuntimeException;
                //    69: athrow         
                //    70: aconst_null    
                //    71: areturn        
                //    Exceptions:
                //  Try           Handler
                //  Start  End    Start  End    Type                                     
                //  -----  -----  -----  -----  -----------------------------------------
                //  10     21     24     28     Ljava/lang/UnsupportedOperationException;
                //  14     36     39     43     Ljava/lang/UnsupportedOperationException;
                //  28     55     58     62     Ljava/lang/UnsupportedOperationException;
                //  43     66     66     70     Ljava/lang/UnsupportedOperationException;
                // 
                // The error that occurred was:
                // 
                // java.lang.IllegalStateException: Expression is linked from several locations: Label_0028:
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
            });
            if (mapNotNull == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/generate/handlers/OCCCppGenerateHandlerBase", "getContexts"));
            }
        }
        catch (UnsupportedOperationException ex2) {
            throw b(ex2);
        }
        return (List<? extends C>)mapNotNull;
    }
    
    protected boolean generateMissingInTests() {
        return true;
    }
    
    @NotNull
    protected Collection<OCFunctionSymbol> checkExistingFunctions(@NotNull final Project project, @NotNull final OCCaretLocation ocCaretLocation, @NotNull final C c) {
        try {
            if (project == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "project", "com/jetbrains/cidr/lang/generate/handlers/OCCCppGenerateHandlerBase", "checkExistingFunctions"));
            }
        }
        catch (UnsupportedOperationException ex) {
            throw b(ex);
        }
        try {
            if (ocCaretLocation == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "location", "com/jetbrains/cidr/lang/generate/handlers/OCCCppGenerateHandlerBase", "checkExistingFunctions"));
            }
        }
        catch (UnsupportedOperationException ex2) {
            throw b(ex2);
        }
        try {
            if (c == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "context", "com/jetbrains/cidr/lang/generate/handlers/OCCCppGenerateHandlerBase", "checkExistingFunctions"));
            }
        }
        catch (UnsupportedOperationException ex3) {
            throw b(ex3);
        }
        List<OCFunctionSymbol> emptyList;
        try {
            emptyList = Collections.emptyList();
            if (emptyList == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/generate/handlers/OCCCppGenerateHandlerBase", "checkExistingFunctions"));
            }
        }
        catch (UnsupportedOperationException ex4) {
            throw b(ex4);
        }
        return emptyList;
    }
    
    @Override
    protected final void performAction(@NotNull final Project p0, @Nullable final Editor p1, @NotNull final PsiFile p2, @NotNull final C p3, @NotNull final List<M> p4) {
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
        //    24: ldc             "com/jetbrains/cidr/lang/generate/handlers/OCCCppGenerateHandlerBase"
        //    26: aastore        
        //    27: dup            
        //    28: ldc             2
        //    30: ldc             "performAction"
        //    32: aastore        
        //    33: invokestatic    java/lang/String.format:(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
        //    36: invokespecial   java/lang/IllegalArgumentException.<init>:(Ljava/lang/String;)V
        //    39: athrow         
        //    40: invokestatic    com/jetbrains/cidr/lang/generate/handlers/OCCCppGenerateHandlerBase.b:(Ljava/lang/RuntimeException;)Ljava/lang/RuntimeException;
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
        //    68: ldc             "com/jetbrains/cidr/lang/generate/handlers/OCCCppGenerateHandlerBase"
        //    70: aastore        
        //    71: dup            
        //    72: ldc             2
        //    74: ldc             "performAction"
        //    76: aastore        
        //    77: invokestatic    java/lang/String.format:(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
        //    80: invokespecial   java/lang/IllegalArgumentException.<init>:(Ljava/lang/String;)V
        //    83: athrow         
        //    84: invokestatic    com/jetbrains/cidr/lang/generate/handlers/OCCCppGenerateHandlerBase.b:(Ljava/lang/RuntimeException;)Ljava/lang/RuntimeException;
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
        //   113: ldc             "com/jetbrains/cidr/lang/generate/handlers/OCCCppGenerateHandlerBase"
        //   115: aastore        
        //   116: dup            
        //   117: ldc             2
        //   119: ldc             "performAction"
        //   121: aastore        
        //   122: invokestatic    java/lang/String.format:(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
        //   125: invokespecial   java/lang/IllegalArgumentException.<init>:(Ljava/lang/String;)V
        //   128: athrow         
        //   129: invokestatic    com/jetbrains/cidr/lang/generate/handlers/OCCCppGenerateHandlerBase.b:(Ljava/lang/RuntimeException;)Ljava/lang/RuntimeException;
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
        //   158: ldc             "com/jetbrains/cidr/lang/generate/handlers/OCCCppGenerateHandlerBase"
        //   160: aastore        
        //   161: dup            
        //   162: ldc             2
        //   164: ldc             "performAction"
        //   166: aastore        
        //   167: invokestatic    java/lang/String.format:(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
        //   170: invokespecial   java/lang/IllegalArgumentException.<init>:(Ljava/lang/String;)V
        //   173: athrow         
        //   174: invokestatic    com/jetbrains/cidr/lang/generate/handlers/OCCCppGenerateHandlerBase.b:(Ljava/lang/RuntimeException;)Ljava/lang/RuntimeException;
        //   177: athrow         
        //   178: invokestatic    com/intellij/featureStatistics/FeatureUsageTracker.getInstance:()Lcom/intellij/featureStatistics/FeatureUsageTracker;
        //   181: aload_0        
        //   182: invokevirtual   com/jetbrains/cidr/lang/generate/handlers/OCCCppGenerateHandlerBase.getFeatureId:()Ljava/lang/String;
        //   185: invokevirtual   com/intellij/featureStatistics/FeatureUsageTracker.triggerFeatureUsed:(Ljava/lang/String;)V
        //   188: aload           4
        //   190: aload_2        
        //   191: aload_3        
        //   192: invokevirtual   com/jetbrains/cidr/lang/generate/actions/OCCppActionContext.storeEditor:(Lcom/intellij/openapi/editor/Editor;Lcom/intellij/psi/PsiFile;)V
        //   195: aload_3        
        //   196: aload_2        
        //   197: invokestatic    com/jetbrains/cidr/lang/generate/OCCaretLocation.byEditor:(Lcom/intellij/psi/PsiFile;Lcom/intellij/openapi/editor/Editor;)Lcom/jetbrains/cidr/lang/generate/OCCaretLocation;
        //   200: astore          6
        //   202: aload_0        
        //   203: aload_1        
        //   204: aload           6
        //   206: aload           4
        //   208: invokevirtual   com/jetbrains/cidr/lang/generate/handlers/OCCCppGenerateHandlerBase.checkExistingFunctions:(Lcom/intellij/openapi/project/Project;Lcom/jetbrains/cidr/lang/generate/OCCaretLocation;Lcom/jetbrains/cidr/lang/generate/actions/OCCppActionContext;)Ljava/util/Collection;
        //   211: astore          7
        //   213: aload_0        
        //   214: aload           4
        //   216: aload_1        
        //   217: aload           6
        //   219: aload           5
        //   221: invokedynamic   run:(Lcom/jetbrains/cidr/lang/generate/handlers/OCCCppGenerateHandlerBase;Lcom/jetbrains/cidr/lang/generate/actions/OCCppActionContext;Lcom/intellij/openapi/project/Project;Lcom/jetbrains/cidr/lang/generate/OCCaretLocation;Ljava/util/List;)Ljava/lang/Runnable;
        //   226: astore          8
        //   228: aload_0        
        //   229: aload           4
        //   231: aload           7
        //   233: aload_1        
        //   234: aload           6
        //   236: aload           5
        //   238: invokedynamic   run:(Lcom/jetbrains/cidr/lang/generate/handlers/OCCCppGenerateHandlerBase;Lcom/jetbrains/cidr/lang/generate/actions/OCCppActionContext;Ljava/util/Collection;Lcom/intellij/openapi/project/Project;Lcom/jetbrains/cidr/lang/generate/OCCaretLocation;Ljava/util/List;)Ljava/lang/Runnable;
        //   243: astore          9
        //   245: aload           7
        //   247: invokeinterface java/util/Collection.isEmpty:()Z
        //   252: ifne            871
        //   255: ldc             "generate.cpp.show.existing.button"
        //   257: iconst_0       
        //   258: anewarray       Ljava/lang/Object;
        //   261: invokestatic    com/jetbrains/cidr/lang/OCBundle.message:(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
        //   264: astore          10
        //   266: ldc             "generate.cpp.replace.button"
        //   268: iconst_0       
        //   269: anewarray       Ljava/lang/Object;
        //   272: invokestatic    com/jetbrains/cidr/lang/OCBundle.message:(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
        //   275: astore          11
        //   277: ldc             "generate.cpp.add.missing.button"
        //   279: iconst_0       
        //   280: anewarray       Ljava/lang/Object;
        //   283: invokestatic    com/jetbrains/cidr/lang/OCBundle.message:(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
        //   286: astore          12
        //   288: aload           4
        //   290: invokevirtual   com/jetbrains/cidr/lang/generate/actions/OCCppActionContext.allFunctionsExist:()Z
        //   293: ifeq            307
        //   296: aload_0        
        //   297: invokevirtual   com/jetbrains/cidr/lang/generate/handlers/OCCCppGenerateHandlerBase.getAllDefinedText:()Ljava/lang/String;
        //   300: goto            311
        //   303: invokestatic    com/jetbrains/cidr/lang/generate/handlers/OCCCppGenerateHandlerBase.b:(Ljava/lang/RuntimeException;)Ljava/lang/RuntimeException;
        //   306: athrow         
        //   307: aload_0        
        //   308: invokevirtual   com/jetbrains/cidr/lang/generate/handlers/OCCCppGenerateHandlerBase.getSomeDefinedText:()Ljava/lang/String;
        //   311: astore          13
        //   313: bipush          -100
        //   315: istore          14
        //   317: getstatic       com/intellij/openapi/util/SystemInfo.isMac:Z
        //   320: ifeq            437
        //   323: aload           4
        //   325: invokevirtual   com/jetbrains/cidr/lang/generate/actions/OCCppActionContext.allFunctionsExist:()Z
        //   328: ifeq            365
        //   331: goto            338
        //   334: invokestatic    com/jetbrains/cidr/lang/generate/handlers/OCCCppGenerateHandlerBase.b:(Ljava/lang/RuntimeException;)Ljava/lang/RuntimeException;
        //   337: athrow         
        //   338: iconst_3       
        //   339: anewarray       Ljava/lang/String;
        //   342: dup            
        //   343: iconst_0       
        //   344: aload           11
        //   346: aastore        
        //   347: dup            
        //   348: iconst_1       
        //   349: getstatic       com/intellij/openapi/ui/Messages.CANCEL_BUTTON:Ljava/lang/String;
        //   352: aastore        
        //   353: dup            
        //   354: iconst_2       
        //   355: aload           10
        //   357: aastore        
        //   358: goto            390
        //   361: invokestatic    com/jetbrains/cidr/lang/generate/handlers/OCCCppGenerateHandlerBase.b:(Ljava/lang/RuntimeException;)Ljava/lang/RuntimeException;
        //   364: athrow         
        //   365: iconst_4       
        //   366: anewarray       Ljava/lang/String;
        //   369: dup            
        //   370: iconst_0       
        //   371: aload           11
        //   373: aastore        
        //   374: dup            
        //   375: iconst_1       
        //   376: getstatic       com/intellij/openapi/ui/Messages.CANCEL_BUTTON:Ljava/lang/String;
        //   379: aastore        
        //   380: dup            
        //   381: iconst_2       
        //   382: aload           12
        //   384: aastore        
        //   385: dup            
        //   386: iconst_3       
        //   387: aload           10
        //   389: aastore        
        //   390: astore          15
        //   392: iconst_0       
        //   393: istore          17
        //   395: aload           4
        //   397: invokevirtual   com/jetbrains/cidr/lang/generate/actions/OCCppActionContext.allFunctionsExist:()Z
        //   400: ifeq            412
        //   403: bipush          -100
        //   405: goto            413
        //   408: invokestatic    com/jetbrains/cidr/lang/generate/handlers/OCCCppGenerateHandlerBase.b:(Ljava/lang/RuntimeException;)Ljava/lang/RuntimeException;
        //   411: athrow         
        //   412: iconst_2       
        //   413: istore          18
        //   415: aload           4
        //   417: invokevirtual   com/jetbrains/cidr/lang/generate/actions/OCCppActionContext.allFunctionsExist:()Z
        //   420: ifeq            431
        //   423: iconst_2       
        //   424: goto            432
        //   427: invokestatic    com/jetbrains/cidr/lang/generate/handlers/OCCCppGenerateHandlerBase.b:(Ljava/lang/RuntimeException;)Ljava/lang/RuntimeException;
        //   430: athrow         
        //   431: iconst_3       
        //   432: istore          16
        //   434: goto            541
        //   437: aload           4
        //   439: invokevirtual   com/jetbrains/cidr/lang/generate/actions/OCCppActionContext.allFunctionsExist:()Z
        //   442: ifeq            472
        //   445: iconst_3       
        //   446: anewarray       Ljava/lang/String;
        //   449: dup            
        //   450: iconst_0       
        //   451: aload           11
        //   453: aastore        
        //   454: dup            
        //   455: iconst_1       
        //   456: aload           10
        //   458: aastore        
        //   459: dup            
        //   460: iconst_2       
        //   461: getstatic       com/intellij/openapi/ui/Messages.CANCEL_BUTTON:Ljava/lang/String;
        //   464: aastore        
        //   465: goto            497
        //   468: invokestatic    com/jetbrains/cidr/lang/generate/handlers/OCCCppGenerateHandlerBase.b:(Ljava/lang/RuntimeException;)Ljava/lang/RuntimeException;
        //   471: athrow         
        //   472: iconst_4       
        //   473: anewarray       Ljava/lang/String;
        //   476: dup            
        //   477: iconst_0       
        //   478: aload           11
        //   480: aastore        
        //   481: dup            
        //   482: iconst_1       
        //   483: aload           12
        //   485: aastore        
        //   486: dup            
        //   487: iconst_2       
        //   488: aload           10
        //   490: aastore        
        //   491: dup            
        //   492: iconst_3       
        //   493: getstatic       com/intellij/openapi/ui/Messages.CANCEL_BUTTON:Ljava/lang/String;
        //   496: aastore        
        //   497: astore          15
        //   499: iconst_0       
        //   500: istore          17
        //   502: aload           4
        //   504: invokevirtual   com/jetbrains/cidr/lang/generate/actions/OCCppActionContext.allFunctionsExist:()Z
        //   507: ifeq            519
        //   510: bipush          -100
        //   512: goto            520
        //   515: invokestatic    com/jetbrains/cidr/lang/generate/handlers/OCCCppGenerateHandlerBase.b:(Ljava/lang/RuntimeException;)Ljava/lang/RuntimeException;
        //   518: athrow         
        //   519: iconst_1       
        //   520: istore          18
        //   522: aload           4
        //   524: invokevirtual   com/jetbrains/cidr/lang/generate/actions/OCCppActionContext.allFunctionsExist:()Z
        //   527: ifeq            538
        //   530: iconst_1       
        //   531: goto            539
        //   534: invokestatic    com/jetbrains/cidr/lang/generate/handlers/OCCCppGenerateHandlerBase.b:(Ljava/lang/RuntimeException;)Ljava/lang/RuntimeException;
        //   537: athrow         
        //   538: iconst_2       
        //   539: istore          16
        //   541: invokestatic    com/intellij/openapi/application/ApplicationManager.getApplication:()Lcom/intellij/openapi/application/Application;
        //   544: invokeinterface com/intellij/openapi/application/Application.isUnitTestMode:()Z
        //   549: ifeq            595
        //   552: aload_0        
        //   553: invokevirtual   com/jetbrains/cidr/lang/generate/handlers/OCCCppGenerateHandlerBase.generateMissingInTests:()Z
        //   556: ifeq            590
        //   559: goto            566
        //   562: invokestatic    com/jetbrains/cidr/lang/generate/handlers/OCCCppGenerateHandlerBase.b:(Ljava/lang/RuntimeException;)Ljava/lang/RuntimeException;
        //   565: athrow         
        //   566: aload           4
        //   568: invokevirtual   com/jetbrains/cidr/lang/generate/actions/OCCppActionContext.allFunctionsExist:()Z
        //   571: ifne            590
        //   574: goto            581
        //   577: invokestatic    com/jetbrains/cidr/lang/generate/handlers/OCCCppGenerateHandlerBase.b:(Ljava/lang/RuntimeException;)Ljava/lang/RuntimeException;
        //   580: athrow         
        //   581: iload           18
        //   583: goto            610
        //   586: invokestatic    com/jetbrains/cidr/lang/generate/handlers/OCCCppGenerateHandlerBase.b:(Ljava/lang/RuntimeException;)Ljava/lang/RuntimeException;
        //   589: athrow         
        //   590: iload           17
        //   592: goto            610
        //   595: aload_1        
        //   596: aload           13
        //   598: aload_0        
        //   599: invokevirtual   com/jetbrains/cidr/lang/generate/handlers/OCCCppGenerateHandlerBase.getActionTitle:()Ljava/lang/String;
        //   602: aload           15
        //   604: iload           17
        //   606: aconst_null    
        //   607: invokestatic    com/intellij/openapi/ui/Messages.showDialog:(Lcom/intellij/openapi/project/Project;Ljava/lang/String;Ljava/lang/String;[Ljava/lang/String;ILjavax/swing/Icon;)I
        //   610: istore          19
        //   612: iload           19
        //   614: iload           16
        //   616: if_icmpne       825
        //   619: new             Lcom/intellij/usages/UsageViewPresentation;
        //   622: dup            
        //   623: invokespecial   com/intellij/usages/UsageViewPresentation.<init>:()V
        //   626: astore          20
        //   628: ldc             "generate.cpp.usages.string"
        //   630: iconst_0       
        //   631: anewarray       Ljava/lang/Object;
        //   634: invokestatic    com/jetbrains/cidr/lang/OCBundle.message:(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
        //   637: astore          21
        //   639: aload           20
        //   641: aload           21
        //   643: invokevirtual   com/intellij/usages/UsageViewPresentation.setCodeUsagesString:(Ljava/lang/String;)V
        //   646: aload           20
        //   648: aload_0        
        //   649: invokevirtual   com/jetbrains/cidr/lang/generate/handlers/OCCCppGenerateHandlerBase.getExistingTabName:()Ljava/lang/String;
        //   652: invokevirtual   com/intellij/usages/UsageViewPresentation.setTabName:(Ljava/lang/String;)V
        //   655: aload           20
        //   657: aload_0        
        //   658: invokevirtual   com/jetbrains/cidr/lang/generate/handlers/OCCCppGenerateHandlerBase.getExistingTabName:()Ljava/lang/String;
        //   661: invokevirtual   com/intellij/usages/UsageViewPresentation.setTabText:(Ljava/lang/String;)V
        //   664: aload           20
        //   666: iconst_0       
        //   667: invokevirtual   com/intellij/usages/UsageViewPresentation.setOpenInNewTab:(Z)V
        //   670: aload           20
        //   672: iconst_1       
        //   673: invokevirtual   com/intellij/usages/UsageViewPresentation.setShowCancelButton:(Z)V
        //   676: aload           20
        //   678: iconst_0       
        //   679: invokevirtual   com/intellij/usages/UsageViewPresentation.setUsageTypeFilteringAvailable:(Z)V
        //   682: aload           20
        //   684: iconst_0       
        //   685: invokevirtual   com/intellij/usages/UsageViewPresentation.setMergeDupLinesAvailable:(Z)V
        //   688: aload           20
        //   690: iconst_0       
        //   691: invokevirtual   com/intellij/usages/UsageViewPresentation.setExcludeAvailable:(Z)V
        //   694: aload           20
        //   696: ldc             "generate.cpp.usages.word"
        //   698: iconst_0       
        //   699: anewarray       Ljava/lang/Object;
        //   702: invokestatic    com/jetbrains/cidr/lang/OCBundle.message:(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
        //   705: invokevirtual   com/intellij/usages/UsageViewPresentation.setUsagesWord:(Ljava/lang/String;)V
        //   708: aload           7
        //   710: invokeinterface java/util/Collection.stream:()Ljava/util/stream/Stream;
        //   715: invokedynamic   apply:()Ljava/util/function/Function;
        //   720: invokeinterface java/util/stream/Stream.map:(Ljava/util/function/Function;)Ljava/util/stream/Stream;
        //   725: invokedynamic   test:()Ljava/util/function/Predicate;
        //   730: invokeinterface java/util/stream/Stream.filter:(Ljava/util/function/Predicate;)Ljava/util/stream/Stream;
        //   735: invokedynamic   apply:()Ljava/util/function/IntFunction;
        //   740: invokeinterface java/util/stream/Stream.toArray:(Ljava/util/function/IntFunction;)[Ljava/lang/Object;
        //   745: checkcast       [Lcom/intellij/usages/Usage;
        //   748: astore          22
        //   750: aload_1        
        //   751: invokestatic    com/intellij/usages/UsageViewManager.getInstance:(Lcom/intellij/openapi/project/Project;)Lcom/intellij/usages/UsageViewManager;
        //   754: getstatic       com/intellij/usages/UsageTarget.EMPTY_ARRAY:[Lcom/intellij/usages/UsageTarget;
        //   757: aload           22
        //   759: aload           20
        //   761: invokevirtual   com/intellij/usages/UsageViewManager.showUsages:([Lcom/intellij/usages/UsageTarget;[Lcom/intellij/usages/Usage;Lcom/intellij/usages/UsageViewPresentation;)Lcom/intellij/usages/UsageView;
        //   764: astore          23
        //   766: ldc             "generate.cpp.usages.cannot.make"
        //   768: iconst_0       
        //   769: anewarray       Ljava/lang/Object;
        //   772: invokestatic    com/jetbrains/cidr/lang/OCBundle.message:(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
        //   775: astore          24
        //   777: aload           23
        //   779: aload           9
        //   781: aload           11
        //   783: aload           24
        //   785: aload           11
        //   787: invokeinterface com/intellij/usages/UsageView.addPerformOperationAction:(Ljava/lang/Runnable;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)V
        //   792: aload           4
        //   794: invokevirtual   com/jetbrains/cidr/lang/generate/actions/OCCppActionContext.allFunctionsExist:()Z
        //   797: ifne            822
        //   800: aload           23
        //   802: aload           8
        //   804: aload           12
        //   806: aload           24
        //   808: aload           12
        //   810: invokeinterface com/intellij/usages/UsageView.addPerformOperationAction:(Ljava/lang/Runnable;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)V
        //   815: goto            822
        //   818: invokestatic    com/jetbrains/cidr/lang/generate/handlers/OCCCppGenerateHandlerBase.b:(Ljava/lang/RuntimeException;)Ljava/lang/RuntimeException;
        //   821: athrow         
        //   822: goto            868
        //   825: iload           19
        //   827: iload           17
        //   829: if_icmpne       846
        //   832: aload           9
        //   834: invokeinterface java/lang/Runnable.run:()V
        //   839: goto            868
        //   842: invokestatic    com/jetbrains/cidr/lang/generate/handlers/OCCCppGenerateHandlerBase.b:(Ljava/lang/RuntimeException;)Ljava/lang/RuntimeException;
        //   845: athrow         
        //   846: iload           19
        //   848: iload           18
        //   850: if_icmpne       867
        //   853: aload           8
        //   855: invokeinterface java/lang/Runnable.run:()V
        //   860: goto            868
        //   863: invokestatic    com/jetbrains/cidr/lang/generate/handlers/OCCCppGenerateHandlerBase.b:(Ljava/lang/RuntimeException;)Ljava/lang/RuntimeException;
        //   866: athrow         
        //   867: return         
        //   868: goto            878
        //   871: aload           8
        //   873: invokeinterface java/lang/Runnable.run:()V
        //   878: return         
        //    Signature:
        //  (Lcom/intellij/openapi/project/Project;Lcom/intellij/openapi/editor/Editor;Lcom/intellij/psi/PsiFile;TC;Ljava/util/List<TM;>;)V
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                                     
        //  -----  -----  -----  -----  -----------------------------------------
        //  0      40     40     44     Ljava/lang/UnsupportedOperationException;
        //  44     84     84     88     Ljava/lang/UnsupportedOperationException;
        //  88     129    129    133    Ljava/lang/UnsupportedOperationException;
        //  133    174    174    178    Ljava/lang/UnsupportedOperationException;
        //  288    303    303    307    Ljava/lang/UnsupportedOperationException;
        //  317    331    334    338    Ljava/lang/UnsupportedOperationException;
        //  323    361    361    365    Ljava/lang/UnsupportedOperationException;
        //  395    408    408    412    Ljava/lang/UnsupportedOperationException;
        //  415    427    427    431    Ljava/lang/UnsupportedOperationException;
        //  437    468    468    472    Ljava/lang/UnsupportedOperationException;
        //  502    515    515    519    Ljava/lang/UnsupportedOperationException;
        //  522    534    534    538    Ljava/lang/UnsupportedOperationException;
        //  541    559    562    566    Ljava/lang/UnsupportedOperationException;
        //  552    574    577    581    Ljava/lang/UnsupportedOperationException;
        //  566    586    586    590    Ljava/lang/UnsupportedOperationException;
        //  777    815    818    822    Ljava/lang/UnsupportedOperationException;
        //  825    842    842    846    Ljava/lang/UnsupportedOperationException;
        //  846    863    863    867    Ljava/lang/UnsupportedOperationException;
        // 
        // The error that occurred was:
        // 
        // java.lang.IllegalStateException: Expression is linked from several locations: Label_0566:
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
    protected String getSomeDefinedText() {
        String s;
        try {
            OCLog.LOG.error("No text for action " + this.getActionTitle());
            s = "Some are defined";
            if (s == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/generate/handlers/OCCCppGenerateHandlerBase", "getSomeDefinedText"));
            }
        }
        catch (UnsupportedOperationException ex) {
            throw b(ex);
        }
        return s;
    }
    
    @NotNull
    protected String getAllDefinedText() {
        String s;
        try {
            OCLog.LOG.error("No text for action " + this.getActionTitle());
            s = "All are defined";
            if (s == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/generate/handlers/OCCCppGenerateHandlerBase", "getAllDefinedText"));
            }
        }
        catch (UnsupportedOperationException ex) {
            throw b(ex);
        }
        return s;
    }
    
    @NotNull
    protected String getExistingTabName() {
        String s;
        try {
            OCLog.LOG.error("No text for action " + this.getActionTitle());
            s = "Existing";
            if (s == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/generate/handlers/OCCCppGenerateHandlerBase", "getExistingTabName"));
            }
        }
        catch (UnsupportedOperationException ex) {
            throw b(ex);
        }
        return s;
    }
    
    protected void doPerformAction(@NotNull final Project project, @NotNull final OCCaretLocation ocCaretLocation, @NotNull final C c, @NotNull final List<M> list) {
        try {
            if (project == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "project", "com/jetbrains/cidr/lang/generate/handlers/OCCCppGenerateHandlerBase", "doPerformAction"));
            }
        }
        catch (UnsupportedOperationException ex) {
            throw b(ex);
        }
        try {
            if (ocCaretLocation == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "location", "com/jetbrains/cidr/lang/generate/handlers/OCCCppGenerateHandlerBase", "doPerformAction"));
            }
        }
        catch (UnsupportedOperationException ex2) {
            throw b(ex2);
        }
        try {
            if (c == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "actionContext", "com/jetbrains/cidr/lang/generate/handlers/OCCCppGenerateHandlerBase", "doPerformAction"));
            }
        }
        catch (UnsupportedOperationException ex3) {
            throw b(ex3);
        }
        try {
            if (list == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "chosenCandidates", "com/jetbrains/cidr/lang/generate/handlers/OCCCppGenerateHandlerBase", "doPerformAction"));
            }
        }
        catch (UnsupportedOperationException ex4) {
            throw b(ex4);
        }
        final IllegalArgumentException ex5;
        final IllegalArgumentException ex7;
        final IllegalArgumentException ex9;
        final IllegalArgumentException ex11;
        CommandProcessor.getInstance().executeCommand(project, () -> {
            try {
                if (project == null) {
                    new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "project", "com/jetbrains/cidr/lang/generate/handlers/OCCCppGenerateHandlerBase", "lambda$doPerformAction$8"));
                    throw ex5;
                }
            }
            catch (UnsupportedOperationException ex6) {
                throw b(ex6);
            }
            try {
                if (ocCaretLocation == null) {
                    new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "location", "com/jetbrains/cidr/lang/generate/handlers/OCCCppGenerateHandlerBase", "lambda$doPerformAction$8"));
                    throw ex7;
                }
            }
            catch (UnsupportedOperationException ex8) {
                throw b(ex8);
            }
            try {
                if (c == null) {
                    new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "actionContext", "com/jetbrains/cidr/lang/generate/handlers/OCCCppGenerateHandlerBase", "lambda$doPerformAction$8"));
                    throw ex9;
                }
            }
            catch (UnsupportedOperationException ex10) {
                throw b(ex10);
            }
            try {
                if (list == null) {
                    new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "chosenCandidates", "com/jetbrains/cidr/lang/generate/handlers/OCCCppGenerateHandlerBase", "lambda$doPerformAction$8"));
                    throw ex11;
                }
            }
            catch (UnsupportedOperationException ex12) {
                throw b(ex12);
            }
            this.doPerformActionInCommand(project, ocCaretLocation, c, list);
        }, this.getActionTitle(), (Object)null);
    }
    
    protected void doPerformActionInCommand(@NotNull final Project project, @NotNull final OCCaretLocation ocCaretLocation, @NotNull final C c, @NotNull final List<M> list) {
        try {
            if (project == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "project", "com/jetbrains/cidr/lang/generate/handlers/OCCCppGenerateHandlerBase", "doPerformActionInCommand"));
            }
        }
        catch (UnsupportedOperationException ex) {
            throw b(ex);
        }
        try {
            if (ocCaretLocation == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "location", "com/jetbrains/cidr/lang/generate/handlers/OCCCppGenerateHandlerBase", "doPerformActionInCommand"));
            }
        }
        catch (UnsupportedOperationException ex2) {
            throw b(ex2);
        }
        try {
            if (c == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "actionContext", "com/jetbrains/cidr/lang/generate/handlers/OCCCppGenerateHandlerBase", "doPerformActionInCommand"));
            }
        }
        catch (UnsupportedOperationException ex3) {
            throw b(ex3);
        }
        try {
            if (list == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "chosenCandidates", "com/jetbrains/cidr/lang/generate/handlers/OCCCppGenerateHandlerBase", "doPerformActionInCommand"));
            }
        }
        catch (UnsupportedOperationException ex4) {
            throw b(ex4);
        }
        final List<OCGenerateUtil.Replacement> replacements = this.getReplacements((OCCaretLocation)WriteAction.compute(() -> {
            try {
                if (c == null) {
                    throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "actionContext", "com/jetbrains/cidr/lang/generate/handlers/OCCCppGenerateHandlerBase", "lambda$doPerformActionInCommand$9"));
                }
            }
            catch (RuntimeException ex) {
                throw b(ex);
            }
            try {
                if (ocCaretLocation == null) {
                    throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "location", "com/jetbrains/cidr/lang/generate/handlers/OCCCppGenerateHandlerBase", "lambda$doPerformActionInCommand$9"));
                }
            }
            catch (RuntimeException ex2) {
                throw b(ex2);
            }
            try {
                if (c.isReplaceExisting()) {
                    return OCChangeUtil.removeFunctions(c.getExisting(), ocCaretLocation);
                }
            }
            catch (RuntimeException ex3) {
                throw b(ex3);
            }
            return ocCaretLocation;
        }), c, list);
        try {
            if (FileModificationService.getInstance().preparePsiElementsForWrite((Collection)OCGenerateUtil.getAffectedFiles(replacements))) {
                WriteAction.run(() -> {
                    try {
                        if (project == null) {
                            throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "project", "com/jetbrains/cidr/lang/generate/handlers/OCCCppGenerateHandlerBase", "lambda$doPerformActionInCommand$10"));
                        }
                    }
                    catch (RuntimeException ex) {
                        throw b(ex);
                    }
                    OCGenerateUtil.applyReplacements(project, replacements, this.shouldAutoFixImports());
                });
            }
        }
        catch (UnsupportedOperationException ex5) {
            throw b(ex5);
        }
    }
    
    protected boolean shouldAutoFixImports() {
        return true;
    }
    
    @Contract("_, _, _ -> fail")
    @Nullable
    @Override
    protected final P getParent(@NotNull final Project project, @Nullable final Editor editor, @NotNull final PsiFile psiFile) {
        try {
            if (project == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "project", "com/jetbrains/cidr/lang/generate/handlers/OCCCppGenerateHandlerBase", "getParent"));
            }
        }
        catch (UnsupportedOperationException ex) {
            throw b(ex);
        }
        try {
            if (psiFile == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "file", "com/jetbrains/cidr/lang/generate/handlers/OCCCppGenerateHandlerBase", "getParent"));
            }
        }
        catch (UnsupportedOperationException ex2) {
            throw b(ex2);
        }
        throw new UnsupportedOperationException("CPP generate handler: Use getParents() instead");
    }
    
    @Contract("_, _, _ -> fail")
    @Nullable
    @Override
    protected final C evaluateActionContext(final Project project, @Nullable final Editor editor, final PsiFile psiFile) {
        throw new UnsupportedOperationException("CPP generate handler: return single parent from getParents() instead");
    }
    
    @Contract(" -> fail")
    @Override
    protected final Class<? extends OCSymbolDeclarator> getParentClass() {
        throw new UnsupportedOperationException("CPP generate handler: Can't have single parent, don't use getParentClass");
    }
    
    static {
        INLINE_OPTION = new OCBooleanOption(OCBundle.message("generate.inplace.option", new Object[0]));
    }
    
    private static RuntimeException b(final RuntimeException ex) {
        return ex;
    }
}
