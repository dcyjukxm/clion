// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.cpp.cmake.refactoring;

import com.intellij.psi.PsiFile;
import com.intellij.openapi.editor.Editor;
import org.jetbrains.annotations.NotNull;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.actionSystem.DataContext;
import com.intellij.refactoring.rename.PsiElementRenameHandler;

public class CMakeRenameHandler extends PsiElementRenameHandler
{
    @Override
    public boolean isAvailableOnDataContext(final DataContext p0) {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     0: aload_0        
        //     1: aload_1        
        //     2: invokespecial   com/intellij/refactoring/rename/PsiElementRenameHandler.isAvailableOnDataContext:(Lcom/intellij/openapi/actionSystem/DataContext;)Z
        //     5: ifeq            71
        //     8: getstatic       com/intellij/openapi/actionSystem/CommonDataKeys.PSI_ELEMENT:Lcom/intellij/openapi/actionSystem/DataKey;
        //    11: aload_1        
        //    12: invokevirtual   com/intellij/openapi/actionSystem/DataKey.getData:(Lcom/intellij/openapi/actionSystem/DataContext;)Ljava/lang/Object;
        //    15: checkcast       Lcom/intellij/psi/PsiElement;
        //    18: astore_2       
        //    19: aload_2        
        //    20: ifnull          71
        //    23: aload_2        
        //    24: instanceof      Lcom/jetbrains/cidr/cpp/cmake/psi/CMakeArgument;
        //    27: ifne            65
        //    30: goto            37
        //    33: invokestatic    com/jetbrains/cidr/cpp/cmake/refactoring/CMakeRenameHandler.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //    36: athrow         
        //    37: aload_2        
        //    38: instanceof      Lcom/jetbrains/cidr/cpp/cmake/psi/CMakeLiteral;
        //    41: ifne            65
        //    44: goto            51
        //    47: invokestatic    com/jetbrains/cidr/cpp/cmake/refactoring/CMakeRenameHandler.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //    50: athrow         
        //    51: aload_2        
        //    52: instanceof      Lcom/jetbrains/cidr/cpp/cmake/psi/CMakeCommandNameMixin;
        //    55: ifeq            71
        //    58: goto            65
        //    61: invokestatic    com/jetbrains/cidr/cpp/cmake/refactoring/CMakeRenameHandler.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //    64: athrow         
        //    65: iconst_1       
        //    66: ireturn        
        //    67: invokestatic    com/jetbrains/cidr/cpp/cmake/refactoring/CMakeRenameHandler.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //    70: athrow         
        //    71: iconst_0       
        //    72: ireturn        
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                                
        //  -----  -----  -----  -----  ------------------------------------
        //  19     30     33     37     Ljava/lang/IllegalArgumentException;
        //  23     44     47     51     Ljava/lang/IllegalArgumentException;
        //  37     58     61     65     Ljava/lang/IllegalArgumentException;
        //  51     67     67     71     Ljava/lang/IllegalArgumentException;
        // 
        // The error that occurred was:
        // 
        // java.lang.IllegalStateException: Expression is linked from several locations: Label_0037:
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
    public void invoke(@NotNull final Project project, final Editor editor, final PsiFile psiFile, final DataContext dataContext) {
        try {
            if (project == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "project", "com/jetbrains/cidr/cpp/cmake/refactoring/CMakeRenameHandler", "invoke"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        try {
            if (shouldInvokeRefactoring(dataContext)) {
                super.invoke(project, editor, psiFile, dataContext);
            }
        }
        catch (IllegalArgumentException ex2) {
            throw a(ex2);
        }
    }
    
    public static boolean shouldInvokeRefactoring(final DataContext p0) {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     0: getstatic       com/intellij/openapi/actionSystem/CommonDataKeys.PSI_ELEMENT:Lcom/intellij/openapi/actionSystem/DataKey;
        //     3: aload_0        
        //     4: invokevirtual   com/intellij/openapi/actionSystem/DataKey.getData:(Lcom/intellij/openapi/actionSystem/DataContext;)Ljava/lang/Object;
        //     7: checkcast       Lcom/intellij/psi/PsiElement;
        //    10: astore_1       
        //    11: aconst_null    
        //    12: astore_2       
        //    13: aload_1        
        //    14: instanceof      Lcom/jetbrains/cidr/cpp/cmake/psi/CMakeLiteral;
        //    17: ifeq            33
        //    20: aload_1        
        //    21: checkcast       Lcom/jetbrains/cidr/cpp/cmake/psi/CMakeLiteral;
        //    24: invokeinterface com/jetbrains/cidr/cpp/cmake/psi/CMakeLiteral.getArgument:()Lcom/jetbrains/cidr/cpp/cmake/psi/CMakeArgument;
        //    29: astore_2       
        //    30: goto            45
        //    33: aload_1        
        //    34: instanceof      Lcom/jetbrains/cidr/cpp/cmake/psi/CMakeArgument;
        //    37: ifeq            45
        //    40: aload_1        
        //    41: checkcast       Lcom/jetbrains/cidr/cpp/cmake/psi/CMakeArgument;
        //    44: astore_2       
        //    45: aload_2        
        //    46: ifnull          87
        //    49: aload_2        
        //    50: invokeinterface com/jetbrains/cidr/cpp/cmake/psi/CMakeArgument.isCommandDefinitionName:()Z
        //    55: ifne            81
        //    58: goto            65
        //    61: invokestatic    com/jetbrains/cidr/cpp/cmake/refactoring/CMakeRenameHandler.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //    64: athrow         
        //    65: aload_2        
        //    66: invokeinterface com/jetbrains/cidr/cpp/cmake/psi/CMakeArgument.isEndCommandDefinitionName:()Z
        //    71: ifeq            87
        //    74: goto            81
        //    77: invokestatic    com/jetbrains/cidr/cpp/cmake/refactoring/CMakeRenameHandler.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //    80: athrow         
        //    81: iconst_1       
        //    82: ireturn        
        //    83: invokestatic    com/jetbrains/cidr/cpp/cmake/refactoring/CMakeRenameHandler.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //    86: athrow         
        //    87: aload_1        
        //    88: instanceof      Lcom/jetbrains/cidr/cpp/cmake/psi/CMakeCommandName;
        //    91: ifeq            121
        //    94: aload_1        
        //    95: invokeinterface com/intellij/psi/PsiElement.getText:()Ljava/lang/String;
        //   100: invokestatic    com/jetbrains/cidr/cpp/cmake/completion/contributors/providers/CMakeCommandProvider.isStandardCommand:(Ljava/lang/String;)Z
        //   103: ifeq            119
        //   106: goto            113
        //   109: invokestatic    com/jetbrains/cidr/cpp/cmake/refactoring/CMakeRenameHandler.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   112: athrow         
        //   113: iconst_0       
        //   114: ireturn        
        //   115: invokestatic    com/jetbrains/cidr/cpp/cmake/refactoring/CMakeRenameHandler.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   118: athrow         
        //   119: iconst_1       
        //   120: ireturn        
        //   121: iconst_0       
        //   122: ireturn        
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                                
        //  -----  -----  -----  -----  ------------------------------------
        //  45     58     61     65     Ljava/lang/IllegalArgumentException;
        //  49     74     77     81     Ljava/lang/IllegalArgumentException;
        //  65     83     83     87     Ljava/lang/IllegalArgumentException;
        //  87     106    109    113    Ljava/lang/IllegalArgumentException;
        //  94     115    115    119    Ljava/lang/IllegalArgumentException;
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
    
    private static IllegalArgumentException a(final IllegalArgumentException ex) {
        return ex;
    }
}
