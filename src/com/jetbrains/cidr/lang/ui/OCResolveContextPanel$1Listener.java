// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.lang.ui;

import com.jetbrains.cidr.lang.workspace.OCResolveRootAndConfiguration;
import org.jetbrains.annotations.NotNull;
import com.intellij.openapi.vfs.VirtualFile;
import com.jetbrains.cidr.lang.preprocessor.OCInclusionContextListener;
import com.intellij.openapi.project.DumbService;

class 1Listener implements DumbService.DumbModeListener, OCInclusionContextListener
{
    volatile boolean isDumbMode;
    
    public void enteredDumbMode() {
        this.isDumbMode = true;
        OCResolveContextPanel.this.scheduleUpdate();
    }
    
    public void exitDumbMode() {
        this.isDumbMode = false;
        OCResolveContextPanel.this.scheduleUpdate();
    }
    
    public void resolveRootAndActiveConfigurationChanged(@NotNull final VirtualFile p0, @NotNull final OCResolveRootAndConfiguration p1) {
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
        //    18: ldc             "file"
        //    20: aastore        
        //    21: dup            
        //    22: ldc             1
        //    24: ldc             "com/jetbrains/cidr/lang/ui/OCResolveContextPanel$1Listener"
        //    26: aastore        
        //    27: dup            
        //    28: ldc             2
        //    30: ldc             "resolveRootAndActiveConfigurationChanged"
        //    32: aastore        
        //    33: invokestatic    java/lang/String.format:(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
        //    36: invokespecial   java/lang/IllegalArgumentException.<init>:(Ljava/lang/String;)V
        //    39: athrow         
        //    40: invokestatic    com/jetbrains/cidr/lang/ui/OCResolveContextPanel$1Listener.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //    43: athrow         
        //    44: aload_2        
        //    45: ifnonnull       88
        //    48: new             Ljava/lang/IllegalArgumentException;
        //    51: dup            
        //    52: ldc             "Argument for @NotNull parameter '%s' of %s.%s must not be null"
        //    54: ldc             3
        //    56: anewarray       Ljava/lang/Object;
        //    59: dup            
        //    60: ldc             0
        //    62: ldc             "rootAndConfiguration"
        //    64: aastore        
        //    65: dup            
        //    66: ldc             1
        //    68: ldc             "com/jetbrains/cidr/lang/ui/OCResolveContextPanel$1Listener"
        //    70: aastore        
        //    71: dup            
        //    72: ldc             2
        //    74: ldc             "resolveRootAndActiveConfigurationChanged"
        //    76: aastore        
        //    77: invokestatic    java/lang/String.format:(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
        //    80: invokespecial   java/lang/IllegalArgumentException.<init>:(Ljava/lang/String;)V
        //    83: athrow         
        //    84: invokestatic    com/jetbrains/cidr/lang/ui/OCResolveContextPanel$1Listener.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //    87: athrow         
        //    88: aload_0        
        //    89: getfield        com/jetbrains/cidr/lang/ui/OCResolveContextPanel$1Listener.this$0:Lcom/jetbrains/cidr/lang/ui/OCResolveContextPanel;
        //    92: invokestatic    com/jetbrains/cidr/lang/ui/OCResolveContextPanel.access$400:(Lcom/jetbrains/cidr/lang/ui/OCResolveContextPanel;)Z
        //    95: ifne            147
        //    98: aload_0        
        //    99: getfield        com/jetbrains/cidr/lang/ui/OCResolveContextPanel$1Listener.isDumbMode:Z
        //   102: ifne            147
        //   105: goto            112
        //   108: invokestatic    com/jetbrains/cidr/lang/ui/OCResolveContextPanel$1Listener.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   111: athrow         
        //   112: aload_1        
        //   113: aload_0        
        //   114: getfield        com/jetbrains/cidr/lang/ui/OCResolveContextPanel$1Listener.this$0:Lcom/jetbrains/cidr/lang/ui/OCResolveContextPanel;
        //   117: invokestatic    com/jetbrains/cidr/lang/ui/OCResolveContextPanel.access$500:(Lcom/jetbrains/cidr/lang/ui/OCResolveContextPanel;)Lcom/intellij/openapi/vfs/VirtualFile;
        //   120: invokevirtual   java/lang/Object.equals:(Ljava/lang/Object;)Z
        //   123: ifeq            147
        //   126: goto            133
        //   129: invokestatic    com/jetbrains/cidr/lang/ui/OCResolveContextPanel$1Listener.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   132: athrow         
        //   133: aload_0        
        //   134: getfield        com/jetbrains/cidr/lang/ui/OCResolveContextPanel$1Listener.this$0:Lcom/jetbrains/cidr/lang/ui/OCResolveContextPanel;
        //   137: invokevirtual   com/jetbrains/cidr/lang/ui/OCResolveContextPanel.scheduleUpdate:()V
        //   140: goto            147
        //   143: invokestatic    com/jetbrains/cidr/lang/ui/OCResolveContextPanel$1Listener.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   146: athrow         
        //   147: return         
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                                
        //  -----  -----  -----  -----  ------------------------------------
        //  0      40     40     44     Ljava/lang/IllegalArgumentException;
        //  44     84     84     88     Ljava/lang/IllegalArgumentException;
        //  88     105    108    112    Ljava/lang/IllegalArgumentException;
        //  98     126    129    133    Ljava/lang/IllegalArgumentException;
        //  112    140    143    147    Ljava/lang/IllegalArgumentException;
        // 
        // The error that occurred was:
        // 
        // java.lang.IllegalStateException: Expression is linked from several locations: Label_0112:
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
    
    private static IllegalArgumentException b(final IllegalArgumentException ex) {
        return ex;
    }
}
