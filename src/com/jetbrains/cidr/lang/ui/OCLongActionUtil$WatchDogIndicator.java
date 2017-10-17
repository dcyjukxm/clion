// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.lang.ui;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import com.intellij.openapi.progress.util.PotemkinProgress;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.progress.util.PingProgress;
import com.intellij.openapi.progress.util.AbstractProgressIndicatorExBase;

static class WatchDogIndicator extends AbstractProgressIndicatorExBase implements PingProgress
{
    private final long myStartTimeMs;
    private final long myTimeOutMs;
    private final Project myProject;
    private final String myDescriptorIdForIndicator;
    private PotemkinProgress myProgress;
    
    public WatchDogIndicator(final long myTimeOutMs, @Nullable final Project myProject, @NotNull final String myDescriptorIdForIndicator) {
        if (myDescriptorIdForIndicator == null) {
            throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "descriptorIdForIndicator", "com/jetbrains/cidr/lang/ui/OCLongActionUtil$WatchDogIndicator", "<init>"));
        }
        this.myTimeOutMs = myTimeOutMs;
        this.myDescriptorIdForIndicator = myDescriptorIdForIndicator;
        this.myStartTimeMs = System.currentTimeMillis();
        this.myProject = myProject;
        this.setIndeterminate(true);
    }
    
    @Override
    public boolean isCanceled() {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     0: invokestatic    java/lang/System.currentTimeMillis:()J
        //     3: aload_0        
        //     4: getfield        com/jetbrains/cidr/lang/ui/OCLongActionUtil$WatchDogIndicator.myStartTimeMs:J
        //     7: lsub           
        //     8: aload_0        
        //     9: getfield        com/jetbrains/cidr/lang/ui/OCLongActionUtil$WatchDogIndicator.myTimeOutMs:J
        //    12: lcmp           
        //    13: iflt            224
        //    16: getstatic       com/jetbrains/cidr/lang/ui/OCLongActionUtil.ourAutoCancelLongAction:Z
        //    19: ifeq            40
        //    22: goto            29
        //    25: invokestatic    com/jetbrains/cidr/lang/ui/OCLongActionUtil$WatchDogIndicator.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //    28: athrow         
        //    29: aload_0        
        //    30: invokevirtual   com/jetbrains/cidr/lang/ui/OCLongActionUtil$WatchDogIndicator.cancel:()V
        //    33: goto            224
        //    36: invokestatic    com/jetbrains/cidr/lang/ui/OCLongActionUtil$WatchDogIndicator.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //    39: athrow         
        //    40: aload_0        
        //    41: getfield        com/jetbrains/cidr/lang/ui/OCLongActionUtil$WatchDogIndicator.myProgress:Lcom/intellij/openapi/progress/util/PotemkinProgress;
        //    44: ifnonnull       224
        //    47: invokestatic    com/intellij/openapi/application/ApplicationManager.getApplication:()Lcom/intellij/openapi/application/Application;
        //    50: invokeinterface com/intellij/openapi/application/Application.isDispatchThread:()Z
        //    55: ifeq            224
        //    58: goto            65
        //    61: invokestatic    com/jetbrains/cidr/lang/ui/OCLongActionUtil$WatchDogIndicator.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //    64: athrow         
        //    65: invokestatic    com/intellij/openapi/application/ApplicationManager.getApplication:()Lcom/intellij/openapi/application/Application;
        //    68: invokeinterface com/intellij/openapi/application/Application.isHeadlessEnvironment:()Z
        //    73: ifne            224
        //    76: goto            83
        //    79: invokestatic    com/jetbrains/cidr/lang/ui/OCLongActionUtil$WatchDogIndicator.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //    82: athrow         
        //    83: invokestatic    com/intellij/openapi/application/ApplicationManager.getApplication:()Lcom/intellij/openapi/application/Application;
        //    86: invokeinterface com/intellij/openapi/application/Application.isUnitTestMode:()Z
        //    91: ifne            224
        //    94: goto            101
        //    97: invokestatic    com/jetbrains/cidr/lang/ui/OCLongActionUtil$WatchDogIndicator.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   100: athrow         
        //   101: aload_0        
        //   102: getfield        com/jetbrains/cidr/lang/ui/OCLongActionUtil$WatchDogIndicator.myDescriptorIdForIndicator:Ljava/lang/String;
        //   105: iconst_0       
        //   106: anewarray       Ljava/lang/Object;
        //   109: invokestatic    com/jetbrains/cidr/lang/OCBundle.message:(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
        //   112: ldc             "\n"
        //   114: invokestatic    com/intellij/openapi/util/text/StringUtil.split:(Ljava/lang/String;Ljava/lang/String;)Ljava/util/List;
        //   117: astore_1       
        //   118: aload_1        
        //   119: invokeinterface java/util/List.size:()I
        //   124: iconst_1       
        //   125: if_icmple       149
        //   128: aload_0        
        //   129: aload_1        
        //   130: iconst_1       
        //   131: invokeinterface java/util/List.get:(I)Ljava/lang/Object;
        //   136: checkcast       Ljava/lang/String;
        //   139: invokevirtual   com/jetbrains/cidr/lang/ui/OCLongActionUtil$WatchDogIndicator.setText:(Ljava/lang/String;)V
        //   142: goto            149
        //   145: invokestatic    com/jetbrains/cidr/lang/ui/OCLongActionUtil$WatchDogIndicator.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   148: athrow         
        //   149: aload_1        
        //   150: invokeinterface java/util/List.size:()I
        //   155: iconst_2       
        //   156: if_icmple       180
        //   159: aload_0        
        //   160: aload_1        
        //   161: iconst_2       
        //   162: invokeinterface java/util/List.get:(I)Ljava/lang/Object;
        //   167: checkcast       Ljava/lang/String;
        //   170: invokevirtual   com/jetbrains/cidr/lang/ui/OCLongActionUtil$WatchDogIndicator.setText2:(Ljava/lang/String;)V
        //   173: goto            180
        //   176: invokestatic    com/jetbrains/cidr/lang/ui/OCLongActionUtil$WatchDogIndicator.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   179: athrow         
        //   180: aload_0        
        //   181: new             Lcom/intellij/openapi/progress/util/PotemkinProgress;
        //   184: dup            
        //   185: aload_1        
        //   186: iconst_0       
        //   187: invokeinterface java/util/List.get:(I)Ljava/lang/Object;
        //   192: checkcast       Ljava/lang/String;
        //   195: aload_0        
        //   196: getfield        com/jetbrains/cidr/lang/ui/OCLongActionUtil$WatchDogIndicator.myProject:Lcom/intellij/openapi/project/Project;
        //   199: aconst_null    
        //   200: invokestatic    com/intellij/CommonBundle.getCancelButtonText:()Ljava/lang/String;
        //   203: invokespecial   com/intellij/openapi/progress/util/PotemkinProgress.<init>:(Ljava/lang/String;Lcom/intellij/openapi/project/Project;Ljavax/swing/JComponent;Ljava/lang/String;)V
        //   206: putfield        com/jetbrains/cidr/lang/ui/OCLongActionUtil$WatchDogIndicator.myProgress:Lcom/intellij/openapi/progress/util/PotemkinProgress;
        //   209: aload_0        
        //   210: getfield        com/jetbrains/cidr/lang/ui/OCLongActionUtil$WatchDogIndicator.myProgress:Lcom/intellij/openapi/progress/util/PotemkinProgress;
        //   213: invokevirtual   com/intellij/openapi/progress/util/PotemkinProgress.start:()V
        //   216: aload_0        
        //   217: aload_0        
        //   218: getfield        com/jetbrains/cidr/lang/ui/OCLongActionUtil$WatchDogIndicator.myProgress:Lcom/intellij/openapi/progress/util/PotemkinProgress;
        //   221: invokevirtual   com/jetbrains/cidr/lang/ui/OCLongActionUtil$WatchDogIndicator.addStateDelegate:(Lcom/intellij/openapi/wm/ex/ProgressIndicatorEx;)V
        //   224: aload_0        
        //   225: invokespecial   com/intellij/openapi/progress/util/AbstractProgressIndicatorExBase.isCanceled:()Z
        //   228: ireturn        
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                                
        //  -----  -----  -----  -----  ------------------------------------
        //  0      22     25     29     Ljava/lang/IllegalArgumentException;
        //  16     36     36     40     Ljava/lang/IllegalArgumentException;
        //  40     58     61     65     Ljava/lang/IllegalArgumentException;
        //  47     76     79     83     Ljava/lang/IllegalArgumentException;
        //  65     94     97     101    Ljava/lang/IllegalArgumentException;
        //  118    142    145    149    Ljava/lang/IllegalArgumentException;
        //  149    173    176    180    Ljava/lang/IllegalArgumentException;
        // 
        // The error that occurred was:
        // 
        // java.lang.IllegalStateException: Expression is linked from several locations: Label_0065:
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
    public void interact() {
        try {
            if (this.myProgress != null) {
                this.myProgress.interact();
            }
        }
        catch (IllegalArgumentException ex) {
            throw b(ex);
        }
    }
    
    private static IllegalArgumentException b(final IllegalArgumentException ex) {
        return ex;
    }
}
