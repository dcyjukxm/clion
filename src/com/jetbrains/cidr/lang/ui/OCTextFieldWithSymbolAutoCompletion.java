// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.lang.ui;

import java.util.Collection;
import com.intellij.util.ui.UIUtil;
import com.intellij.util.containers.ContainerUtil;
import java.util.concurrent.CountDownLatch;
import com.intellij.openapi.progress.util.AbstractProgressIndicatorBase;
import com.intellij.openapi.progress.util.ProgressIndicatorBase;
import com.intellij.concurrency.SensitiveProgressWrapper;
import com.intellij.openapi.progress.ProgressManager;
import com.intellij.openapi.util.Condition;
import org.jetbrains.annotations.Nullable;
import com.intellij.ui.TextFieldWithAutoCompletionListProvider;
import org.jetbrains.annotations.NotNull;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.progress.ProgressIndicator;
import com.intellij.ui.TextFieldWithAutoCompletion;

public class OCTextFieldWithSymbolAutoCompletion<SUPPLEMENTED> extends TextFieldWithAutoCompletion<SUPPLEMENTED>
{
    private final ProgressIndicator myMainCollectionProgress;
    
    public OCTextFieldWithSymbolAutoCompletion(@NotNull final Project project, @NotNull final TextFieldWithAutoCompletionListProvider<SUPPLEMENTED> textFieldWithAutoCompletionListProvider) {
        if (project == null) {
            throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "project", "com/jetbrains/cidr/lang/ui/OCTextFieldWithSymbolAutoCompletion", "<init>"));
        }
        if (textFieldWithAutoCompletionListProvider == null) {
            throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "provider", "com/jetbrains/cidr/lang/ui/OCTextFieldWithSymbolAutoCompletion", "<init>"));
        }
        this(project, textFieldWithAutoCompletionListProvider, null);
    }
    
    public OCTextFieldWithSymbolAutoCompletion(@NotNull final Project project, @NotNull final TextFieldWithAutoCompletionListProvider<SUPPLEMENTED> textFieldWithAutoCompletionListProvider, @Nullable final ProgressIndicator myMainCollectionProgress) {
        if (project == null) {
            throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "project", "com/jetbrains/cidr/lang/ui/OCTextFieldWithSymbolAutoCompletion", "<init>"));
        }
        if (textFieldWithAutoCompletionListProvider == null) {
            throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "provider", "com/jetbrains/cidr/lang/ui/OCTextFieldWithSymbolAutoCompletion", "<init>"));
        }
        super(project, textFieldWithAutoCompletionListProvider, false, null);
        this.myMainCollectionProgress = myMainCollectionProgress;
    }
    
    public static <SUPPLEMENTED> OCTextFieldWithSymbolAutoCompletion<SUPPLEMENTED> create(@NotNull final Project project, @NotNull final OCFieldAdapter<SUPPLEMENTED> ocFieldAdapter, @Nullable final Condition<SUPPLEMENTED> condition) {
        try {
            if (project == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "project", "com/jetbrains/cidr/lang/ui/OCTextFieldWithSymbolAutoCompletion", "create"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw e(ex);
        }
        try {
            if (ocFieldAdapter == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "adapter", "com/jetbrains/cidr/lang/ui/OCTextFieldWithSymbolAutoCompletion", "create"));
            }
        }
        catch (IllegalArgumentException ex2) {
            throw e(ex2);
        }
        return create(project, ocFieldAdapter, condition, null);
    }
    
    public static <SUPPLEMENTED> OCTextFieldWithSymbolAutoCompletion<SUPPLEMENTED> create(@NotNull final Project p0, @NotNull final OCFieldAdapter<SUPPLEMENTED> p1, @Nullable final Condition<SUPPLEMENTED> p2, @Nullable final ProcessListener p3) {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     0: aload_0        
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
        //    24: ldc             "com/jetbrains/cidr/lang/ui/OCTextFieldWithSymbolAutoCompletion"
        //    26: aastore        
        //    27: dup            
        //    28: ldc             2
        //    30: ldc             "create"
        //    32: aastore        
        //    33: invokestatic    java/lang/String.format:(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
        //    36: invokespecial   java/lang/IllegalArgumentException.<init>:(Ljava/lang/String;)V
        //    39: athrow         
        //    40: invokestatic    com/jetbrains/cidr/lang/ui/OCTextFieldWithSymbolAutoCompletion.e:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //    43: athrow         
        //    44: aload_1        
        //    45: ifnonnull       88
        //    48: new             Ljava/lang/IllegalArgumentException;
        //    51: dup            
        //    52: ldc             "Argument for @NotNull parameter '%s' of %s.%s must not be null"
        //    54: ldc             3
        //    56: anewarray       Ljava/lang/Object;
        //    59: dup            
        //    60: ldc             0
        //    62: ldc             "adapter"
        //    64: aastore        
        //    65: dup            
        //    66: ldc             1
        //    68: ldc             "com/jetbrains/cidr/lang/ui/OCTextFieldWithSymbolAutoCompletion"
        //    70: aastore        
        //    71: dup            
        //    72: ldc             2
        //    74: ldc             "create"
        //    76: aastore        
        //    77: invokestatic    java/lang/String.format:(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
        //    80: invokespecial   java/lang/IllegalArgumentException.<init>:(Ljava/lang/String;)V
        //    83: athrow         
        //    84: invokestatic    com/jetbrains/cidr/lang/ui/OCTextFieldWithSymbolAutoCompletion.e:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //    87: athrow         
        //    88: new             Ljava/util/concurrent/CountDownLatch;
        //    91: dup            
        //    92: iconst_1       
        //    93: invokespecial   java/util/concurrent/CountDownLatch.<init>:(I)V
        //    96: astore          4
        //    98: new             Lcom/jetbrains/cidr/lang/ui/OCTextFieldWithSymbolAutoCompletion$1;
        //   101: dup            
        //   102: aload_1        
        //   103: aload           4
        //   105: aload_3        
        //   106: aload_0        
        //   107: aload_1        
        //   108: aload_2        
        //   109: invokespecial   com/jetbrains/cidr/lang/ui/OCTextFieldWithSymbolAutoCompletion$1.<init>:(Lcom/jetbrains/cidr/lang/ui/OCFieldAdapter;Ljava/util/concurrent/CountDownLatch;Lcom/jetbrains/cidr/lang/ui/OCTextFieldWithSymbolAutoCompletion$ProcessListener;Lcom/intellij/openapi/project/Project;Lcom/jetbrains/cidr/lang/ui/OCFieldAdapter;Lcom/intellij/openapi/util/Condition;)V
        //   112: astore          5
        //   114: invokestatic    com/jetbrains/cidr/lang/ui/OCTextFieldWithSymbolAutoCompletion.a:()Lcom/intellij/openapi/progress/ProgressIndicator;
        //   117: astore          6
        //   119: invokestatic    com/intellij/openapi/application/ApplicationManager.getApplication:()Lcom/intellij/openapi/application/Application;
        //   122: aload           5
        //   124: aload_0        
        //   125: aload_2        
        //   126: aload           4
        //   128: aload           6
        //   130: invokedynamic   run:(Lcom/jetbrains/cidr/lang/ui/OCTextFieldCompletionProvider;Lcom/intellij/openapi/project/Project;Lcom/intellij/openapi/util/Condition;Ljava/util/concurrent/CountDownLatch;Lcom/intellij/openapi/progress/ProgressIndicator;)Ljava/lang/Runnable;
        //   135: invokeinterface com/intellij/openapi/application/Application.executeOnPooledThread:(Ljava/lang/Runnable;)Ljava/util/concurrent/Future;
        //   140: pop            
        //   141: new             Lcom/jetbrains/cidr/lang/ui/OCTextFieldWithSymbolAutoCompletion;
        //   144: dup            
        //   145: aload_0        
        //   146: aload           5
        //   148: aload           6
        //   150: invokespecial   com/jetbrains/cidr/lang/ui/OCTextFieldWithSymbolAutoCompletion.<init>:(Lcom/intellij/openapi/project/Project;Lcom/intellij/ui/TextFieldWithAutoCompletionListProvider;Lcom/intellij/openapi/progress/ProgressIndicator;)V
        //   153: areturn        
        //    Signature:
        //  <SUPPLEMENTED:Ljava/lang/Object;>(Lcom/intellij/openapi/project/Project;Lcom/jetbrains/cidr/lang/ui/OCFieldAdapter<TSUPPLEMENTED;>;Lcom/intellij/openapi/util/Condition<TSUPPLEMENTED;>;Lcom/jetbrains/cidr/lang/ui/OCTextFieldWithSymbolAutoCompletion$ProcessListener;)Lcom/jetbrains/cidr/lang/ui/OCTextFieldWithSymbolAutoCompletion<TSUPPLEMENTED;>;
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                                
        //  -----  -----  -----  -----  ------------------------------------
        //  0      40     40     44     Ljava/lang/IllegalArgumentException;
        //  44     84     84     88     Ljava/lang/IllegalArgumentException;
        // 
        // The error that occurred was:
        // 
        // java.lang.IllegalStateException: Could not infer any expression.
        //     at com.strobel.decompiler.ast.TypeAnalysis.runInference(TypeAnalysis.java:374)
        //     at com.strobel.decompiler.ast.TypeAnalysis.run(TypeAnalysis.java:96)
        //     at com.strobel.decompiler.ast.AstOptimizer.optimize(AstOptimizer.java:344)
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
    
    private static ProgressIndicator a() {
        final ProgressIndicator progressIndicator = ProgressManager.getInstance().getProgressIndicator();
        try {
            if (progressIndicator != null) {
                final AbstractProgressIndicatorBase abstractProgressIndicatorBase = new SensitiveProgressWrapper(progressIndicator);
                return (ProgressIndicator)abstractProgressIndicatorBase;
            }
        }
        catch (IllegalArgumentException ex) {
            throw e(ex);
        }
        final AbstractProgressIndicatorBase abstractProgressIndicatorBase = new ProgressIndicatorBase();
        return (ProgressIndicator)abstractProgressIndicatorBase;
    }
    
    public void cancelRunningTasks() {
        try {
            if (this.myMainCollectionProgress != null) {
                this.myMainCollectionProgress.cancel();
            }
        }
        catch (IllegalArgumentException ex) {
            throw e(ex);
        }
    }
    
    private static IllegalArgumentException e(final IllegalArgumentException ex) {
        return ex;
    }
    
    public interface ProcessListener
    {
        void onStarted();
        
        void onFinished();
    }
}
