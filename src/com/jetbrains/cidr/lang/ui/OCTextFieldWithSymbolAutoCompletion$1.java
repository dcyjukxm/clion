// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.lang.ui;

import com.intellij.codeInsight.completion.PrefixMatcher;
import org.jetbrains.annotations.NotNull;
import com.intellij.openapi.progress.ProgressIndicator;
import com.intellij.openapi.application.ApplicationManager;
import java.util.concurrent.TimeUnit;
import com.intellij.openapi.progress.ProgressManager;
import com.intellij.openapi.util.Ref;
import java.util.Collection;
import com.intellij.openapi.util.Computable;
import com.intellij.codeInsight.completion.CompletionParameters;
import org.jetbrains.annotations.Nullable;
import com.intellij.openapi.util.Condition;
import com.intellij.openapi.project.Project;
import java.util.concurrent.CountDownLatch;

static final class OCTextFieldWithSymbolAutoCompletion$1 extends OCTextFieldCompletionProvider<SUPPLEMENTED> {
    private static final int THRESHOLD_LENGTH = 2;
    private int startedEventsCount;
    final /* synthetic */ CountDownLatch val$collectionFinished;
    
    @NotNull
    private Collection<SUPPLEMENTED> a(@Nullable final String s, final CompletionParameters completionParameters, final Computable<Collection<SUPPLEMENTED>> computable) {
        final Ref create = Ref.create();
    Label_0054_Outer:
        while (true) {
            Label_0051: {
                try {
                    if (s == null || s.length() <= 2) {
                        break Label_0051;
                    }
                }
                catch (InterruptedException ex) {
                    throw c(ex);
                }
                while (true) {
                    Label_0024: {
                        break Label_0024;
                        Collection<SUPPLEMENTED> collection = null;
                        while (true) {
                            Label_0119: {
                                Label_0084: {
                                    try {
                                        ProgressManager.checkCanceled();
                                        if (create.isNull()) {
                                            break Label_0119;
                                        }
                                        final Ref ref = create;
                                        final Object o = ref.get();
                                        collection = (Collection<SUPPLEMENTED>)o;
                                        if (collection == null) {
                                            break Label_0084;
                                        }
                                        break;
                                    }
                                    catch (InterruptedException ex2) {
                                        throw c(ex2);
                                    }
                                    try {
                                        final Ref ref = create;
                                        final Object o = ref.get();
                                        collection = (Collection<SUPPLEMENTED>)o;
                                        if (collection == null) {
                                            throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/ui/OCTextFieldWithSymbolAutoCompletion$1", "collectItemsForPrefix"));
                                        }
                                    }
                                    catch (InterruptedException ex3) {
                                        throw c(ex3);
                                    }
                                }
                                break;
                                try {
                                    Label_0147: {
                                        final ProgressIndicator access$000;
                                        try {
                                            if (!this.val$collectionFinished.await(100L, TimeUnit.MILLISECONDS)) {
                                                continue Label_0054_Outer;
                                            }
                                            final ProgressIndicator progressIndicator = access$000;
                                            if (progressIndicator != null) {
                                                break Label_0147;
                                            }
                                            break Label_0147;
                                        }
                                        catch (InterruptedException ex4) {
                                            throw c(ex4);
                                        }
                                        try {
                                            final ProgressIndicator progressIndicator = access$000;
                                            if (progressIndicator != null) {
                                                access$000.cancel();
                                            }
                                        }
                                        catch (InterruptedException ex5) {
                                            throw c(ex5);
                                        }
                                    }
                                    final Collection<SUPPLEMENTED> items = super.getItems(s, false, completionParameters);
                                    if (items == null) {
                                        throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/ui/OCTextFieldWithSymbolAutoCompletion$1", "collectItemsForPrefix"));
                                    }
                                    return items;
                                }
                                catch (InterruptedException ex6) {}
                            }
                        }
                        return collection;
                    }
                    final ProgressIndicator access$000 = OCTextFieldWithSymbolAutoCompletion.access$000();
                    ApplicationManager.getApplication().executeOnPooledThread(() -> create.set((Object)ProgressManager.getInstance().runProcess((Computable)computable, access$000)));
                    continue;
                }
            }
            final ProgressIndicator access$000 = null;
            continue;
        }
    }
    
    @Nullable
    @Override
    public String getPrefix(@NotNull final String s, final int n) {
        try {
            if (s == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "text", "com/jetbrains/cidr/lang/ui/OCTextFieldWithSymbolAutoCompletion$1", "getPrefix"));
            }
        }
        catch (IllegalStateException ex) {
            throw c(ex);
        }
        return this.myAdapter.getPrefix(s, n);
    }
    
    @NotNull
    @Override
    public Collection<SUPPLEMENTED> getItems(final String p0, final boolean p1, final CompletionParameters p2) {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     0: aload_0        
        //     1: aload_1        
        //     2: iload_2        
        //     3: aload_3        
        //     4: invokespecial   com/jetbrains/cidr/lang/ui/OCTextFieldCompletionProvider.getItems:(Ljava/lang/String;ZLcom/intellij/codeInsight/completion/CompletionParameters;)Ljava/util/Collection;
        //     7: astore          4
        //     9: aload           4
        //    11: invokeinterface java/util/Collection.isEmpty:()Z
        //    16: ifeq            30
        //    19: iload_2        
        //    20: ifeq            78
        //    23: goto            30
        //    26: invokestatic    com/jetbrains/cidr/lang/ui/OCTextFieldWithSymbolAutoCompletion$1.c:(Ljava/lang/Exception;)Ljava/lang/Exception;
        //    29: athrow         
        //    30: aload           4
        //    32: dup            
        //    33: ifnonnull       77
        //    36: goto            43
        //    39: invokestatic    com/jetbrains/cidr/lang/ui/OCTextFieldWithSymbolAutoCompletion$1.c:(Ljava/lang/Exception;)Ljava/lang/Exception;
        //    42: athrow         
        //    43: new             Ljava/lang/IllegalStateException;
        //    46: dup            
        //    47: ldc             "@NotNull method %s.%s must not return null"
        //    49: ldc             2
        //    51: anewarray       Ljava/lang/Object;
        //    54: dup            
        //    55: ldc             0
        //    57: ldc             "com/jetbrains/cidr/lang/ui/OCTextFieldWithSymbolAutoCompletion$1"
        //    59: aastore        
        //    60: dup            
        //    61: ldc             1
        //    63: ldc             "getItems"
        //    65: aastore        
        //    66: invokestatic    java/lang/String.format:(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
        //    69: invokespecial   java/lang/IllegalStateException.<init>:(Ljava/lang/String;)V
        //    72: athrow         
        //    73: invokestatic    com/jetbrains/cidr/lang/ui/OCTextFieldWithSymbolAutoCompletion$1.c:(Ljava/lang/Exception;)Ljava/lang/Exception;
        //    76: athrow         
        //    77: areturn        
        //    78: aload_0        
        //    79: getfield        com/jetbrains/cidr/lang/ui/OCTextFieldWithSymbolAutoCompletion$1.val$processListener:Lcom/jetbrains/cidr/lang/ui/OCTextFieldWithSymbolAutoCompletion$ProcessListener;
        //    82: ifnull          105
        //    85: aload_0        
        //    86: aload_0        
        //    87: getfield        com/jetbrains/cidr/lang/ui/OCTextFieldWithSymbolAutoCompletion$1.val$processListener:Lcom/jetbrains/cidr/lang/ui/OCTextFieldWithSymbolAutoCompletion$ProcessListener;
        //    90: invokedynamic   run:(Lcom/jetbrains/cidr/lang/ui/OCTextFieldWithSymbolAutoCompletion$1;Lcom/jetbrains/cidr/lang/ui/OCTextFieldWithSymbolAutoCompletion$ProcessListener;)Ljava/lang/Runnable;
        //    95: invokestatic    com/intellij/util/ui/UIUtil.invokeAndWaitIfNeeded:(Ljava/lang/Runnable;)V
        //    98: goto            105
        //   101: invokestatic    com/jetbrains/cidr/lang/ui/OCTextFieldWithSymbolAutoCompletion$1.c:(Ljava/lang/Exception;)Ljava/lang/Exception;
        //   104: athrow         
        //   105: aload_0        
        //   106: aload_1        
        //   107: aload_3        
        //   108: aload_0        
        //   109: aload_1        
        //   110: aload_0        
        //   111: getfield        com/jetbrains/cidr/lang/ui/OCTextFieldWithSymbolAutoCompletion$1.val$project:Lcom/intellij/openapi/project/Project;
        //   114: aload_0        
        //   115: getfield        com/jetbrains/cidr/lang/ui/OCTextFieldWithSymbolAutoCompletion$1.val$adapter:Lcom/jetbrains/cidr/lang/ui/OCFieldAdapter;
        //   118: aload_0        
        //   119: getfield        com/jetbrains/cidr/lang/ui/OCTextFieldWithSymbolAutoCompletion$1.val$condition:Lcom/intellij/openapi/util/Condition;
        //   122: invokedynamic   compute:(Lcom/jetbrains/cidr/lang/ui/OCTextFieldWithSymbolAutoCompletion$1;Ljava/lang/String;Lcom/intellij/openapi/project/Project;Lcom/jetbrains/cidr/lang/ui/OCFieldAdapter;Lcom/intellij/openapi/util/Condition;)Lcom/intellij/openapi/util/Computable;
        //   127: invokespecial   com/jetbrains/cidr/lang/ui/OCTextFieldWithSymbolAutoCompletion$1.a:(Ljava/lang/String;Lcom/intellij/codeInsight/completion/CompletionParameters;Lcom/intellij/openapi/util/Computable;)Ljava/util/Collection;
        //   130: astore          5
        //   132: aload_0        
        //   133: getfield        com/jetbrains/cidr/lang/ui/OCTextFieldWithSymbolAutoCompletion$1.val$processListener:Lcom/jetbrains/cidr/lang/ui/OCTextFieldWithSymbolAutoCompletion$ProcessListener;
        //   136: ifnull          159
        //   139: aload_0        
        //   140: aload_0        
        //   141: getfield        com/jetbrains/cidr/lang/ui/OCTextFieldWithSymbolAutoCompletion$1.val$processListener:Lcom/jetbrains/cidr/lang/ui/OCTextFieldWithSymbolAutoCompletion$ProcessListener;
        //   144: invokedynamic   run:(Lcom/jetbrains/cidr/lang/ui/OCTextFieldWithSymbolAutoCompletion$1;Lcom/jetbrains/cidr/lang/ui/OCTextFieldWithSymbolAutoCompletion$ProcessListener;)Ljava/lang/Runnable;
        //   149: invokestatic    com/intellij/util/ui/UIUtil.invokeLaterIfNeeded:(Ljava/lang/Runnable;)V
        //   152: goto            159
        //   155: invokestatic    com/jetbrains/cidr/lang/ui/OCTextFieldWithSymbolAutoCompletion$1.c:(Ljava/lang/Exception;)Ljava/lang/Exception;
        //   158: athrow         
        //   159: aload           5
        //   161: dup            
        //   162: ifnonnull       199
        //   165: new             Ljava/lang/IllegalStateException;
        //   168: dup            
        //   169: ldc             "@NotNull method %s.%s must not return null"
        //   171: ldc             2
        //   173: anewarray       Ljava/lang/Object;
        //   176: dup            
        //   177: ldc             0
        //   179: ldc             "com/jetbrains/cidr/lang/ui/OCTextFieldWithSymbolAutoCompletion$1"
        //   181: aastore        
        //   182: dup            
        //   183: ldc             1
        //   185: ldc             "getItems"
        //   187: aastore        
        //   188: invokestatic    java/lang/String.format:(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
        //   191: invokespecial   java/lang/IllegalStateException.<init>:(Ljava/lang/String;)V
        //   194: athrow         
        //   195: invokestatic    com/jetbrains/cidr/lang/ui/OCTextFieldWithSymbolAutoCompletion$1.c:(Ljava/lang/Exception;)Ljava/lang/Exception;
        //   198: athrow         
        //   199: areturn        
        //   200: astore          6
        //   202: aload_0        
        //   203: getfield        com/jetbrains/cidr/lang/ui/OCTextFieldWithSymbolAutoCompletion$1.val$processListener:Lcom/jetbrains/cidr/lang/ui/OCTextFieldWithSymbolAutoCompletion$ProcessListener;
        //   206: ifnull          229
        //   209: aload_0        
        //   210: aload_0        
        //   211: getfield        com/jetbrains/cidr/lang/ui/OCTextFieldWithSymbolAutoCompletion$1.val$processListener:Lcom/jetbrains/cidr/lang/ui/OCTextFieldWithSymbolAutoCompletion$ProcessListener;
        //   214: invokedynamic   run:(Lcom/jetbrains/cidr/lang/ui/OCTextFieldWithSymbolAutoCompletion$1;Lcom/jetbrains/cidr/lang/ui/OCTextFieldWithSymbolAutoCompletion$ProcessListener;)Ljava/lang/Runnable;
        //   219: invokestatic    com/intellij/util/ui/UIUtil.invokeLaterIfNeeded:(Ljava/lang/Runnable;)V
        //   222: goto            229
        //   225: invokestatic    com/jetbrains/cidr/lang/ui/OCTextFieldWithSymbolAutoCompletion$1.c:(Ljava/lang/Exception;)Ljava/lang/Exception;
        //   228: athrow         
        //   229: aload           6
        //   231: athrow         
        //    Signature:
        //  (Ljava/lang/String;ZLcom/intellij/codeInsight/completion/CompletionParameters;)Ljava/util/Collection<TSUPPLEMENTED;>;
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                             
        //  -----  -----  -----  -----  ---------------------------------
        //  78     98     101    105    Ljava/lang/IllegalStateException;
        //  30     73     73     77     Ljava/lang/IllegalStateException;
        //  19     36     39     43     Ljava/lang/IllegalStateException;
        //  9      23     26     30     Ljava/lang/IllegalStateException;
        //  105    132    200    232    Any
        //  159    195    195    199    Ljava/lang/IllegalStateException;
        //  132    152    155    159    Ljava/lang/IllegalStateException;
        //  200    202    200    232    Any
        //  202    222    225    229    Ljava/lang/IllegalStateException;
        // 
        // The error that occurred was:
        // 
        // java.lang.IllegalStateException: Expression is linked from several locations: Label_0030:
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
    
    private static Exception c(final Exception ex) {
        return ex;
    }
}