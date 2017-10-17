// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.cpp.cmake.formatting;

import org.jetbrains.annotations.NotNull;
import com.intellij.psi.PsiElement;
import java.util.List;
import com.intellij.openapi.util.TextRange;
import com.intellij.psi.PsiRecursiveElementVisitor;

class CMakePreFormatProcessor$MyFormatter$1 extends PsiRecursiveElementVisitor {
    public void visitElement(@NotNull final PsiElement p0) {
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
        //    18: ldc             "element"
        //    20: aastore        
        //    21: dup            
        //    22: ldc             1
        //    24: ldc             "com/jetbrains/cidr/cpp/cmake/formatting/CMakePreFormatProcessor$MyFormatter$1"
        //    26: aastore        
        //    27: dup            
        //    28: ldc             2
        //    30: ldc             "visitElement"
        //    32: aastore        
        //    33: invokestatic    java/lang/String.format:(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
        //    36: invokespecial   java/lang/IllegalArgumentException.<init>:(Ljava/lang/String;)V
        //    39: athrow         
        //    40: invokestatic    com/jetbrains/cidr/cpp/cmake/formatting/CMakePreFormatProcessor$MyFormatter$1.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //    43: athrow         
        //    44: aload_1        
        //    45: invokeinterface com/intellij/psi/PsiElement.getNode:()Lcom/intellij/lang/ASTNode;
        //    50: astore_2       
        //    51: aload_2        
        //    52: invokeinterface com/intellij/lang/ASTNode.getTextRange:()Lcom/intellij/openapi/util/TextRange;
        //    57: astore_3       
        //    58: aload_0        
        //    59: getfield        com/jetbrains/cidr/cpp/cmake/formatting/CMakePreFormatProcessor$MyFormatter$1.val$range:Lcom/intellij/openapi/util/TextRange;
        //    62: aload_3        
        //    63: invokevirtual   com/intellij/openapi/util/TextRange.intersects:(Lcom/intellij/openapi/util/TextRange;)Z
        //    66: ifeq            152
        //    69: aload_0        
        //    70: getfield        com/jetbrains/cidr/cpp/cmake/formatting/CMakePreFormatProcessor$MyFormatter$1.this$0:Lcom/jetbrains/cidr/cpp/cmake/formatting/CMakePreFormatProcessor$MyFormatter;
        //    73: aload_1        
        //    74: invokestatic    com/jetbrains/cidr/cpp/cmake/formatting/CMakePreFormatProcessor$MyFormatter.access$100:(Lcom/jetbrains/cidr/cpp/cmake/formatting/CMakePreFormatProcessor$MyFormatter;Lcom/intellij/psi/PsiElement;)Z
        //    77: ifeq            152
        //    80: goto            87
        //    83: invokestatic    com/jetbrains/cidr/cpp/cmake/formatting/CMakePreFormatProcessor$MyFormatter$1.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //    86: athrow         
        //    87: aload_2        
        //    88: invokeinterface com/intellij/lang/ASTNode.getElementType:()Lcom/intellij/psi/tree/IElementType;
        //    93: astore          4
        //    95: aload           4
        //    97: getstatic       com/jetbrains/cidr/cpp/cmake/psi/CMakeElementTypes.ID:Lcom/intellij/psi/tree/IElementType;
        //   100: if_acmpeq       134
        //   103: aload_2        
        //   104: aload           4
        //   106: invokestatic    com/jetbrains/cidr/cpp/cmake/formatting/CMakePreFormatProcessor$MyFormatter.access$200:(Lcom/intellij/lang/ASTNode;Lcom/intellij/psi/tree/IElementType;)Z
        //   109: ifne            134
        //   112: goto            119
        //   115: invokestatic    com/jetbrains/cidr/cpp/cmake/formatting/CMakePreFormatProcessor$MyFormatter$1.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   118: athrow         
        //   119: aload           4
        //   121: invokestatic    com/jetbrains/cidr/cpp/cmake/formatting/CMakePreFormatProcessor$MyFormatter.access$300:(Lcom/intellij/psi/tree/IElementType;)Z
        //   124: ifeq            152
        //   127: goto            134
        //   130: invokestatic    com/jetbrains/cidr/cpp/cmake/formatting/CMakePreFormatProcessor$MyFormatter$1.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   133: athrow         
        //   134: aload_0        
        //   135: getfield        com/jetbrains/cidr/cpp/cmake/formatting/CMakePreFormatProcessor$MyFormatter$1.val$functionsToChangeCase:Ljava/util/List;
        //   138: aload_3        
        //   139: invokeinterface java/util/List.add:(Ljava/lang/Object;)Z
        //   144: pop            
        //   145: goto            152
        //   148: invokestatic    com/jetbrains/cidr/cpp/cmake/formatting/CMakePreFormatProcessor$MyFormatter$1.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   151: athrow         
        //   152: aload_0        
        //   153: aload_1        
        //   154: invokespecial   com/intellij/psi/PsiRecursiveElementVisitor.visitElement:(Lcom/intellij/psi/PsiElement;)V
        //   157: return         
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                                
        //  -----  -----  -----  -----  ------------------------------------
        //  0      40     40     44     Ljava/lang/IllegalArgumentException;
        //  58     80     83     87     Ljava/lang/IllegalArgumentException;
        //  95     112    115    119    Ljava/lang/IllegalArgumentException;
        //  103    127    130    134    Ljava/lang/IllegalArgumentException;
        //  119    145    148    152    Ljava/lang/IllegalArgumentException;
        // 
        // The error that occurred was:
        // 
        // java.lang.IllegalStateException: Expression is linked from several locations: Label_0119:
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