// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.cpp.cmake.actions;

import org.jetbrains.annotations.NotNull;
import com.intellij.openapi.actionSystem.AnActionEvent;
import com.jetbrains.cidr.cpp.CPPBundle;
import com.intellij.ide.actions.ShowFilePathAction;
import org.jetbrains.annotations.Nullable;
import java.io.File;
import com.intellij.openapi.project.DumbAwareAction;

public class ShowCMakeGeneratedDirAction extends DumbAwareAction
{
    @Nullable
    private final File myGenerationDir;
    
    public ShowCMakeGeneratedDirAction(@Nullable final File myGenerationDir) {
        super(CPPBundle.message("cmake.action.showGeneratedFiles", ShowFilePathAction.getFileManagerName()));
        this.myGenerationDir = myGenerationDir;
    }
    
    public void update(@NotNull final AnActionEvent p0) {
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
        //    18: ldc             "e"
        //    20: aastore        
        //    21: dup            
        //    22: ldc             1
        //    24: ldc             "com/jetbrains/cidr/cpp/cmake/actions/ShowCMakeGeneratedDirAction"
        //    26: aastore        
        //    27: dup            
        //    28: ldc             2
        //    30: ldc             "update"
        //    32: aastore        
        //    33: invokestatic    java/lang/String.format:(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
        //    36: invokespecial   java/lang/IllegalArgumentException.<init>:(Ljava/lang/String;)V
        //    39: athrow         
        //    40: invokestatic    com/jetbrains/cidr/cpp/cmake/actions/ShowCMakeGeneratedDirAction.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //    43: athrow         
        //    44: aload_0        
        //    45: aload_1        
        //    46: invokespecial   com/intellij/openapi/project/DumbAwareAction.update:(Lcom/intellij/openapi/actionSystem/AnActionEvent;)V
        //    49: aload_1        
        //    50: invokevirtual   com/intellij/openapi/actionSystem/AnActionEvent.getPresentation:()Lcom/intellij/openapi/actionSystem/Presentation;
        //    53: aload_1        
        //    54: invokestatic    com/jetbrains/cidr/cpp/cmake/actions/ShowCMakeGeneratedDirAction.getEventProject:(Lcom/intellij/openapi/actionSystem/AnActionEvent;)Lcom/intellij/openapi/project/Project;
        //    57: ifnull          99
        //    60: aload_0        
        //    61: getfield        com/jetbrains/cidr/cpp/cmake/actions/ShowCMakeGeneratedDirAction.myGenerationDir:Ljava/io/File;
        //    64: ifnull          99
        //    67: goto            74
        //    70: invokestatic    com/jetbrains/cidr/cpp/cmake/actions/ShowCMakeGeneratedDirAction.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //    73: athrow         
        //    74: aload_0        
        //    75: getfield        com/jetbrains/cidr/cpp/cmake/actions/ShowCMakeGeneratedDirAction.myGenerationDir:Ljava/io/File;
        //    78: invokevirtual   java/io/File.exists:()Z
        //    81: ifeq            99
        //    84: goto            91
        //    87: invokestatic    com/jetbrains/cidr/cpp/cmake/actions/ShowCMakeGeneratedDirAction.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //    90: athrow         
        //    91: iconst_1       
        //    92: goto            100
        //    95: invokestatic    com/jetbrains/cidr/cpp/cmake/actions/ShowCMakeGeneratedDirAction.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //    98: athrow         
        //    99: iconst_0       
        //   100: invokevirtual   com/intellij/openapi/actionSystem/Presentation.setEnabled:(Z)V
        //   103: return         
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                                
        //  -----  -----  -----  -----  ------------------------------------
        //  0      40     40     44     Ljava/lang/IllegalArgumentException;
        //  44     67     70     74     Ljava/lang/IllegalArgumentException;
        //  60     84     87     91     Ljava/lang/IllegalArgumentException;
        //  74     95     95     99     Ljava/lang/IllegalArgumentException;
        // 
        // The error that occurred was:
        // 
        // java.lang.IllegalStateException: Expression is linked from several locations: Label_0074:
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
    
    public void actionPerformed(@NotNull final AnActionEvent anActionEvent) {
        try {
            if (anActionEvent == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "e", "com/jetbrains/cidr/cpp/cmake/actions/ShowCMakeGeneratedDirAction", "actionPerformed"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        try {
            if (this.myGenerationDir == null) {
                return;
            }
        }
        catch (IllegalArgumentException ex2) {
            throw a(ex2);
        }
        ShowFilePathAction.openFile(this.myGenerationDir);
    }
    
    private static IllegalArgumentException a(final IllegalArgumentException ex) {
        return ex;
    }
}
