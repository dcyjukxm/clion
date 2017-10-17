// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.lang;

import org.jetbrains.annotations.Nullable;
import org.jetbrains.annotations.NotNull;
import com.intellij.openapi.actionSystem.AnActionEvent;

public class OCActionUtil
{
    public static boolean updateSmartAction(@NotNull final AnActionEvent p0, @Nullable final OCLanguageKind p1) {
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
        //    18: ldc             "event"
        //    20: aastore        
        //    21: dup            
        //    22: ldc             1
        //    24: ldc             "com/jetbrains/cidr/lang/OCActionUtil"
        //    26: aastore        
        //    27: dup            
        //    28: ldc             2
        //    30: ldc             "updateSmartAction"
        //    32: aastore        
        //    33: invokestatic    java/lang/String.format:(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
        //    36: invokespecial   java/lang/IllegalArgumentException.<init>:(Ljava/lang/String;)V
        //    39: athrow         
        //    40: invokestatic    com/jetbrains/cidr/lang/OCActionUtil.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //    43: athrow         
        //    44: aload_0        
        //    45: invokevirtual   com/intellij/openapi/actionSystem/AnActionEvent.getPresentation:()Lcom/intellij/openapi/actionSystem/Presentation;
        //    48: iconst_1       
        //    49: invokevirtual   com/intellij/openapi/actionSystem/Presentation.setVisible:(Z)V
        //    52: getstatic       com/intellij/openapi/actionSystem/CommonDataKeys.PSI_FILE:Lcom/intellij/openapi/actionSystem/DataKey;
        //    55: aload_0        
        //    56: invokevirtual   com/intellij/openapi/actionSystem/AnActionEvent.getDataContext:()Lcom/intellij/openapi/actionSystem/DataContext;
        //    59: invokevirtual   com/intellij/openapi/actionSystem/DataKey.getData:(Lcom/intellij/openapi/actionSystem/DataContext;)Ljava/lang/Object;
        //    62: checkcast       Lcom/intellij/psi/PsiFile;
        //    65: astore_2       
        //    66: aload_2        
        //    67: instanceof      Lcom/jetbrains/cidr/lang/psi/OCFile;
        //    70: ifeq            131
        //    73: aload_2        
        //    74: invokeinterface com/intellij/psi/PsiFile.getProject:()Lcom/intellij/openapi/project/Project;
        //    79: invokestatic    com/intellij/openapi/project/DumbService.getInstance:(Lcom/intellij/openapi/project/Project;)Lcom/intellij/openapi/project/DumbService;
        //    82: invokevirtual   com/intellij/openapi/project/DumbService.isDumb:()Z
        //    85: ifne            131
        //    88: goto            95
        //    91: invokestatic    com/jetbrains/cidr/lang/OCActionUtil.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //    94: athrow         
        //    95: aload_1        
        //    96: ifnull          141
        //    99: goto            106
        //   102: invokestatic    com/jetbrains/cidr/lang/OCActionUtil.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   105: athrow         
        //   106: aload_2        
        //   107: checkcast       Lcom/jetbrains/cidr/lang/psi/OCFile;
        //   110: invokeinterface com/jetbrains/cidr/lang/psi/OCFile.getKind:()Lcom/jetbrains/cidr/lang/OCLanguageKind;
        //   115: aload_1        
        //   116: invokeinterface com/jetbrains/cidr/lang/OCLanguageKind.conforms:(Lcom/jetbrains/cidr/lang/OCLanguageKind;)Z
        //   121: ifne            141
        //   124: goto            131
        //   127: invokestatic    com/jetbrains/cidr/lang/OCActionUtil.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   130: athrow         
        //   131: aload_0        
        //   132: invokestatic    com/jetbrains/cidr/lang/OCActionUtil.hideAction:(Lcom/intellij/openapi/actionSystem/AnActionEvent;)V
        //   135: iconst_0       
        //   136: ireturn        
        //   137: invokestatic    com/jetbrains/cidr/lang/OCActionUtil.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   140: athrow         
        //   141: iconst_1       
        //   142: ireturn        
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                                
        //  -----  -----  -----  -----  ------------------------------------
        //  0      40     40     44     Ljava/lang/IllegalArgumentException;
        //  66     88     91     95     Ljava/lang/IllegalArgumentException;
        //  73     99     102    106    Ljava/lang/IllegalArgumentException;
        //  95     124    127    131    Ljava/lang/IllegalArgumentException;
        //  106    137    137    141    Ljava/lang/IllegalArgumentException;
        // 
        // The error that occurred was:
        // 
        // java.lang.IllegalStateException: Expression is linked from several locations: Label_0095:
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
    
    public static void hideAction(@NotNull final AnActionEvent anActionEvent) {
        try {
            if (anActionEvent == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "event", "com/jetbrains/cidr/lang/OCActionUtil", "hideAction"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        anActionEvent.getPresentation().setEnabledAndVisible(false);
    }
    
    private static IllegalArgumentException a(final IllegalArgumentException ex) {
        return ex;
    }
}
