// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.lang.ui;

import com.jetbrains.cidr.lang.symbols.symtable.OCGlobalProjectSymbolsCache;
import com.intellij.util.Processor;
import com.intellij.util.containers.ContainerUtil;
import com.intellij.openapi.application.ReadAction;
import com.intellij.openapi.progress.ProcessCanceledException;
import com.intellij.openapi.progress.ProgressManager;
import java.util.HashSet;
import java.util.Set;
import com.intellij.util.CommonProcessors;
import com.intellij.codeInsight.completion.impl.CamelHumpMatcher;
import com.intellij.codeInsight.CodeInsightSettings;
import com.intellij.codeInsight.completion.PrefixMatcher;
import com.intellij.openapi.progress.ProgressIndicator;
import com.intellij.util.Function;
import java.util.List;
import com.intellij.openapi.util.Computable;
import java.util.Collections;
import java.util.Collection;
import org.jetbrains.annotations.Nullable;
import com.intellij.openapi.util.Condition;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.vfs.VirtualFile;
import com.jetbrains.cidr.lang.symbols.OCQualifiedName;
import com.jetbrains.cidr.lang.symbols.cpp.OCFunctionSymbol;
import com.jetbrains.cidr.lang.symbols.cpp.OCSymbolWithQualifiedName;
import javax.swing.Icon;
import org.jetbrains.annotations.NotNull;
import com.jetbrains.cidr.lang.symbols.OCSymbol;

public class OCFieldAdapterForSymbolName implements OCFieldAdapter<OCSymbol>
{
    public static final OCFieldAdapterForSymbolName INSTANCE;
    
    @NotNull
    @Override
    public String getName(@NotNull final OCSymbol ocSymbol) {
        try {
            if (ocSymbol == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "symbol", "com/jetbrains/cidr/lang/ui/OCFieldAdapterForSymbolName", "getName"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        String name;
        try {
            name = ocSymbol.getName();
            if (name == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/ui/OCFieldAdapterForSymbolName", "getName"));
            }
        }
        catch (IllegalArgumentException ex2) {
            throw a(ex2);
        }
        return name;
    }
    
    @Override
    public Icon getIcon(@NotNull final OCSymbol ocSymbol) {
        try {
            if (ocSymbol == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "item", "com/jetbrains/cidr/lang/ui/OCFieldAdapterForSymbolName", "getIcon"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        return ocSymbol.getIcon();
    }
    
    @Override
    public String getTypeText(@NotNull final OCSymbol ocSymbol) {
        try {
            if (ocSymbol == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "item", "com/jetbrains/cidr/lang/ui/OCFieldAdapterForSymbolName", "getTypeText"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        if (ocSymbol instanceof OCSymbolWithQualifiedName) {
            OCSymbolWithQualifiedName ocSymbolWithQualifiedName = ((OCSymbolWithQualifiedName)ocSymbol).getResolvedOwner();
            Label_0082: {
                try {
                    if (!(ocSymbol instanceof OCFunctionSymbol) || ocSymbolWithQualifiedName == null) {
                        break Label_0082;
                    }
                }
                catch (IllegalArgumentException ex2) {
                    throw a(ex2);
                }
                ocSymbolWithQualifiedName = ocSymbolWithQualifiedName.getResolvedOwner();
            }
            if (ocSymbolWithQualifiedName != null) {
                final OCQualifiedName resolvedQualifiedName = ocSymbolWithQualifiedName.getResolvedQualifiedName();
                try {
                    if (resolvedQualifiedName != null) {
                        return resolvedQualifiedName.getCanonicalName(true).substring(2);
                    }
                }
                catch (IllegalArgumentException ex3) {
                    throw a(ex3);
                }
            }
        }
        final VirtualFile containingFile = ocSymbol.getContainingFile();
        try {
            if (containingFile != null) {
                return containingFile.getName();
            }
        }
        catch (IllegalArgumentException ex4) {
            throw a(ex4);
        }
        return "";
    }
    
    @NotNull
    @Override
    public String getReadableName(@NotNull final OCSymbol ocSymbol) {
        try {
            if (ocSymbol == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "symbol", "com/jetbrains/cidr/lang/ui/OCFieldAdapterForSymbolName", "getReadableName"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        String presentableName;
        try {
            presentableName = ocSymbol.getPresentableName();
            if (presentableName == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/ui/OCFieldAdapterForSymbolName", "getReadableName"));
            }
        }
        catch (IllegalArgumentException ex2) {
            throw a(ex2);
        }
        return presentableName;
    }
    
    protected boolean isTopLevelOnly() {
        return true;
    }
    
    @NotNull
    @Override
    public Collection<OCSymbol> collectValuesFromProject(@NotNull final Project project, @Nullable final Condition<OCSymbol> condition) {
        try {
            if (project == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "project", "com/jetbrains/cidr/lang/ui/OCFieldAdapterForSymbolName", "collectValuesFromProject"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        Label_0102: {
            List<OCSymbol> list = null;
            Label_0067: {
                try {
                    if (!project.isDisposed()) {
                        break Label_0102;
                    }
                    list = Collections.emptyList();
                    if (list == null) {
                        break Label_0067;
                    }
                    return (Collection<OCSymbol>)list;
                }
                catch (IllegalArgumentException ex2) {
                    throw a(ex2);
                }
                try {
                    list = Collections.emptyList();
                    if (list == null) {
                        throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/ui/OCFieldAdapterForSymbolName", "collectValuesFromProject"));
                    }
                }
                catch (IllegalArgumentException ex3) {
                    throw a(ex3);
                }
            }
            return (Collection<OCSymbol>)list;
        }
        final List<Object> collectAllValues = OCFieldAdapter.collectAllValues(project, Collections.emptyList(), (com.intellij.openapi.util.Computable<List<Object>>)(() -> {
            try {
                if (project == null) {
                    throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "project", "com/jetbrains/cidr/lang/ui/OCFieldAdapterForSymbolName", "lambda$collectValuesFromProject$0"));
                }
            }
            catch (IllegalArgumentException ex) {
                throw a(ex);
            }
            try {
                if (project.isDisposed()) {
                    return Collections.emptyList();
                }
            }
            catch (IllegalArgumentException ex2) {
                throw a(ex2);
            }
            final CommonProcessors.CollectProcessor collectProcessor = new CommonProcessors.CollectProcessor();
            OCGlobalProjectSymbolsCache.processTopLevelAndMemberSymbols(project, (Processor<OCSymbol>)collectProcessor, null, this.isTopLevelOnly());
            return collectProcessor.getResults();
        }));
        Collection<OCSymbol> collection;
        try {
            collection = OCFieldAdapter.computeWithWriteActionPriority((com.intellij.util.Function<ProgressIndicator, Collection<OCSymbol>>)(progressIndicator -> {
                try {
                    if (project == null) {
                        throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "project", "com/jetbrains/cidr/lang/ui/OCFieldAdapterForSymbolName", "lambda$collectValuesFromProject$1"));
                    }
                }
                catch (IllegalArgumentException ex) {
                    throw a(ex);
                }
                final CommonProcessors.CollectProcessor<OCSymbol> collectProcessor = new CommonProcessors.CollectProcessor<OCSymbol>() {
                    final Set uniquePresentationName;
                    final /* synthetic */ ProgressIndicator val$indicator;
                    
                    {
                        this.uniquePresentationName = new HashSet();
                    }
                    
                    protected boolean accept(final OCSymbol ocSymbol) {
                        ProgressManager.checkCanceled();
                        Boolean b = null;
                        while (b == null) {
                            try {
                                b = (Boolean)ProgressManager.getInstance().runProcess(() -> {
                                    try {
                                        if (project == null) {
                                            throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "project", "com/jetbrains/cidr/lang/ui/OCFieldAdapterForSymbolName$1", "lambda$accept$1"));
                                        }
                                    }
                                    catch (ProcessCanceledException ex) {
                                        throw b((RuntimeException)ex);
                                    }
                                    return (Boolean)ReadAction.compute(() -> {
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
                                        //    24: ldc             "com/jetbrains/cidr/lang/ui/OCFieldAdapterForSymbolName$1"
                                        //    26: aastore        
                                        //    27: dup            
                                        //    28: ldc             2
                                        //    30: ldc             "lambda$null$0"
                                        //    32: aastore        
                                        //    33: invokestatic    java/lang/String.format:(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
                                        //    36: invokespecial   java/lang/IllegalArgumentException.<init>:(Ljava/lang/String;)V
                                        //    39: athrow         
                                        //    40: invokestatic    com/jetbrains/cidr/lang/ui/OCFieldAdapterForSymbolName$1.b:(Ljava/lang/RuntimeException;)Ljava/lang/RuntimeException;
                                        //    43: athrow         
                                        //    44: aload_1        
                                        //    45: invokeinterface com/intellij/openapi/project/Project.isDisposed:()Z
                                        //    50: ifeq            62
                                        //    53: iconst_0       
                                        //    54: invokestatic    java/lang/Boolean.valueOf:(Z)Ljava/lang/Boolean;
                                        //    57: areturn        
                                        //    58: invokestatic    com/jetbrains/cidr/lang/ui/OCFieldAdapterForSymbolName$1.b:(Ljava/lang/RuntimeException;)Ljava/lang/RuntimeException;
                                        //    61: athrow         
                                        //    62: aload_0        
                                        //    63: getfield        com/jetbrains/cidr/lang/ui/OCFieldAdapterForSymbolName$1.this$0:Lcom/jetbrains/cidr/lang/ui/OCFieldAdapterForSymbolName;
                                        //    66: aload_2        
                                        //    67: invokevirtual   com/jetbrains/cidr/lang/ui/OCFieldAdapterForSymbolName.getReadableName:(Lcom/jetbrains/cidr/lang/symbols/OCSymbol;)Ljava/lang/String;
                                        //    70: astore          4
                                        //    72: aload_0        
                                        //    73: getfield        com/jetbrains/cidr/lang/ui/OCFieldAdapterForSymbolName$1.uniquePresentationName:Ljava/util/Set;
                                        //    76: aload           4
                                        //    78: invokeinterface java/util/Set.contains:(Ljava/lang/Object;)Z
                                        //    83: ifne            135
                                        //    86: aload_3        
                                        //    87: ifnull          114
                                        //    90: goto            97
                                        //    93: invokestatic    com/jetbrains/cidr/lang/ui/OCFieldAdapterForSymbolName$1.b:(Ljava/lang/RuntimeException;)Ljava/lang/RuntimeException;
                                        //    96: athrow         
                                        //    97: aload_3        
                                        //    98: aload_2        
                                        //    99: invokeinterface com/intellij/openapi/util/Condition.value:(Ljava/lang/Object;)Z
                                        //   104: ifeq            135
                                        //   107: goto            114
                                        //   110: invokestatic    com/jetbrains/cidr/lang/ui/OCFieldAdapterForSymbolName$1.b:(Ljava/lang/RuntimeException;)Ljava/lang/RuntimeException;
                                        //   113: athrow         
                                        //   114: aload_0        
                                        //   115: getfield        com/jetbrains/cidr/lang/ui/OCFieldAdapterForSymbolName$1.uniquePresentationName:Ljava/util/Set;
                                        //   118: aload           4
                                        //   120: invokeinterface java/util/Set.add:(Ljava/lang/Object;)Z
                                        //   125: pop            
                                        //   126: iconst_1       
                                        //   127: invokestatic    java/lang/Boolean.valueOf:(Z)Ljava/lang/Boolean;
                                        //   130: areturn        
                                        //   131: invokestatic    com/jetbrains/cidr/lang/ui/OCFieldAdapterForSymbolName$1.b:(Ljava/lang/RuntimeException;)Ljava/lang/RuntimeException;
                                        //   134: athrow         
                                        //   135: iconst_0       
                                        //   136: invokestatic    java/lang/Boolean.valueOf:(Z)Ljava/lang/Boolean;
                                        //   139: areturn        
                                        //    Exceptions:
                                        //  throws java.lang.RuntimeException
                                        //    Exceptions:
                                        //  Try           Handler
                                        //  Start  End    Start  End    Type                        
                                        //  -----  -----  -----  -----  ----------------------------
                                        //  0      40     40     44     Ljava/lang/RuntimeException;
                                        //  44     58     58     62     Ljava/lang/RuntimeException;
                                        //  72     90     93     97     Ljava/lang/RuntimeException;
                                        //  86     107    110    114    Ljava/lang/RuntimeException;
                                        //  97     131    131    135    Ljava/lang/RuntimeException;
                                        // 
                                        // The error that occurred was:
                                        // 
                                        // java.lang.IllegalStateException: Expression is linked from several locations: Label_0097:
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
                                        //     at com.strobel.decompiler.languages.java.ast.AstMethodBodyBuilder.transformCall(AstMethodBodyBuilder.java:1163)
                                        //     at com.strobel.decompiler.languages.java.ast.AstMethodBodyBuilder.transformByteCode(AstMethodBodyBuilder.java:1010)
                                        //     at com.strobel.decompiler.languages.java.ast.AstMethodBodyBuilder.transformExpression(AstMethodBodyBuilder.java:540)
                                        //     at com.strobel.decompiler.languages.java.ast.AstMethodBodyBuilder.transformByteCode(AstMethodBodyBuilder.java:554)
                                        //     at com.strobel.decompiler.languages.java.ast.AstMethodBodyBuilder.transformExpression(AstMethodBodyBuilder.java:540)
                                        //     at com.strobel.decompiler.languages.java.ast.AstMethodBodyBuilder.transformNode(AstMethodBodyBuilder.java:392)
                                        //     at com.strobel.decompiler.languages.java.ast.AstMethodBodyBuilder.transformBlock(AstMethodBodyBuilder.java:333)
                                        //     at com.strobel.decompiler.languages.java.ast.AstMethodBodyBuilder.createMethodBody(AstMethodBodyBuilder.java:294)
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
                                }, this.val$indicator);
                            }
                            catch (ProcessCanceledException ex) {}
                        }
                        return b;
                    }
                    
                    private static RuntimeException b(final RuntimeException ex) {
                        return ex;
                    }
                };
                ContainerUtil.process((Iterable)collectAllValues, (Processor)collectProcessor);
                return collectProcessor.getResults();
            }));
            if (collection == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/ui/OCFieldAdapterForSymbolName", "collectValuesFromProject"));
            }
        }
        catch (IllegalArgumentException ex4) {
            throw a(ex4);
        }
        return collection;
    }
    
    @NotNull
    @Override
    public PrefixMatcher createPrefixMatcher(@NotNull final String s) {
        try {
            if (s == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "prefix", "com/jetbrains/cidr/lang/ui/OCFieldAdapterForSymbolName", "createPrefixMatcher"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        boolean b = false;
        Label_0080: {
            Label_0071: {
                try {
                    if (CodeInsightSettings.getInstance().COMPLETION_CASE_SENSITIVE == 3) {
                        break Label_0071;
                    }
                    final CodeInsightSettings codeInsightSettings = CodeInsightSettings.getInstance();
                    final int n = codeInsightSettings.COMPLETION_CASE_SENSITIVE;
                    final int n2 = 2;
                    if (n != n2) {
                        break Label_0071;
                    }
                    break Label_0071;
                }
                catch (IllegalArgumentException ex2) {
                    throw a(ex2);
                }
                try {
                    final CodeInsightSettings codeInsightSettings = CodeInsightSettings.getInstance();
                    final int n = codeInsightSettings.COMPLETION_CASE_SENSITIVE;
                    final int n2 = 2;
                    if (n != n2) {
                        b = true;
                        break Label_0080;
                    }
                }
                catch (IllegalArgumentException ex3) {
                    throw a(ex3);
                }
            }
            b = false;
        }
        final boolean b2 = b;
        CamelHumpMatcher camelHumpMatcher;
        try {
            camelHumpMatcher = new CamelHumpMatcher(s, b2);
            if (camelHumpMatcher == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/ui/OCFieldAdapterForSymbolName", "createPrefixMatcher"));
            }
        }
        catch (IllegalArgumentException ex4) {
            throw a(ex4);
        }
        return camelHumpMatcher;
    }
    
    @Override
    public String getPrefix(@NotNull final String s, final int n) {
        try {
            if (s == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "text", "com/jetbrains/cidr/lang/ui/OCFieldAdapterForSymbolName", "getPrefix"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        return s.substring(Math.max(s.lastIndexOf(32, n - 1) + 1, s.lastIndexOf(10, n - 1) + 1), n);
    }
    
    static {
        INSTANCE = new OCFieldAdapterForSymbolName();
    }
    
    private static IllegalArgumentException a(final IllegalArgumentException ex) {
        return ex;
    }
}
