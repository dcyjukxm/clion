// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.cpp.cmake.psi.util;

import com.jetbrains.cidr.cpp.cmake.psi.CMakeTokenTypes;
import org.jetbrains.annotations.NotNull;
import com.intellij.psi.tree.IElementType;

public class CMakeLiteralUtils
{
    private static final IElementType[] QUOTE_CHECK_TOKENS;
    
    public static boolean shouldBeInsideQuotes(@NotNull final String p0) {
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
        //    18: ldc             "literalText"
        //    20: aastore        
        //    21: dup            
        //    22: ldc             1
        //    24: ldc             "com/jetbrains/cidr/cpp/cmake/psi/util/CMakeLiteralUtils"
        //    26: aastore        
        //    27: dup            
        //    28: ldc             2
        //    30: ldc             "shouldBeInsideQuotes"
        //    32: aastore        
        //    33: invokestatic    java/lang/String.format:(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
        //    36: invokespecial   java/lang/IllegalArgumentException.<init>:(Ljava/lang/String;)V
        //    39: athrow         
        //    40: invokestatic    com/jetbrains/cidr/cpp/cmake/psi/util/CMakeLiteralUtils.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //    43: athrow         
        //    44: aload_0        
        //    45: ldc             ";"
        //    47: invokevirtual   java/lang/String.contains:(Ljava/lang/CharSequence;)Z
        //    50: ifeq            59
        //    53: iconst_1       
        //    54: ireturn        
        //    55: invokestatic    com/jetbrains/cidr/cpp/cmake/psi/util/CMakeLiteralUtils.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //    58: athrow         
        //    59: new             Ljava/lang/StringBuilder;
        //    62: dup            
        //    63: invokespecial   java/lang/StringBuilder.<init>:()V
        //    66: ldc             "foo("
        //    68: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //    71: aload_0        
        //    72: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //    75: ldc             ")"
        //    77: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //    80: invokevirtual   java/lang/StringBuilder.toString:()Ljava/lang/String;
        //    83: astore_1       
        //    84: new             Lcom/jetbrains/cidr/cpp/cmake/psi/CMakeLexer;
        //    87: dup            
        //    88: invokespecial   com/jetbrains/cidr/cpp/cmake/psi/CMakeLexer.<init>:()V
        //    91: astore_2       
        //    92: aload_2        
        //    93: aload_1        
        //    94: iconst_0       
        //    95: aload_1        
        //    96: invokevirtual   java/lang/String.length:()I
        //    99: invokevirtual   com/jetbrains/cidr/cpp/cmake/psi/CMakeLexer.start:(Ljava/lang/CharSequence;II)V
        //   102: iconst_0       
        //   103: istore_3       
        //   104: aload_2        
        //   105: invokevirtual   com/jetbrains/cidr/cpp/cmake/psi/CMakeLexer.getTokenType:()Lcom/intellij/psi/tree/IElementType;
        //   108: ifnull          161
        //   111: getstatic       com/jetbrains/cidr/cpp/cmake/psi/util/CMakeLiteralUtils.QUOTE_CHECK_TOKENS:[Lcom/intellij/psi/tree/IElementType;
        //   114: arraylength    
        //   115: iload_3        
        //   116: if_icmple       145
        //   119: goto            126
        //   122: invokestatic    com/jetbrains/cidr/cpp/cmake/psi/util/CMakeLiteralUtils.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   125: athrow         
        //   126: getstatic       com/jetbrains/cidr/cpp/cmake/psi/util/CMakeLiteralUtils.QUOTE_CHECK_TOKENS:[Lcom/intellij/psi/tree/IElementType;
        //   129: iload_3        
        //   130: aaload         
        //   131: aload_2        
        //   132: invokevirtual   com/jetbrains/cidr/cpp/cmake/psi/CMakeLexer.getTokenType:()Lcom/intellij/psi/tree/IElementType;
        //   135: if_acmpeq       151
        //   138: goto            145
        //   141: invokestatic    com/jetbrains/cidr/cpp/cmake/psi/util/CMakeLiteralUtils.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   144: athrow         
        //   145: iconst_1       
        //   146: ireturn        
        //   147: invokestatic    com/jetbrains/cidr/cpp/cmake/psi/util/CMakeLiteralUtils.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   150: athrow         
        //   151: aload_2        
        //   152: invokevirtual   com/jetbrains/cidr/cpp/cmake/psi/CMakeLexer.advance:()V
        //   155: iinc            3, 1
        //   158: goto            104
        //   161: iload_3        
        //   162: getstatic       com/jetbrains/cidr/cpp/cmake/psi/util/CMakeLiteralUtils.QUOTE_CHECK_TOKENS:[Lcom/intellij/psi/tree/IElementType;
        //   165: arraylength    
        //   166: iconst_1       
        //   167: iadd           
        //   168: if_icmpne       179
        //   171: iconst_1       
        //   172: goto            180
        //   175: invokestatic    com/jetbrains/cidr/cpp/cmake/psi/util/CMakeLiteralUtils.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   178: athrow         
        //   179: iconst_0       
        //   180: ireturn        
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                                
        //  -----  -----  -----  -----  ------------------------------------
        //  0      40     40     44     Ljava/lang/IllegalArgumentException;
        //  44     55     55     59     Ljava/lang/IllegalArgumentException;
        //  104    119    122    126    Ljava/lang/IllegalArgumentException;
        //  111    138    141    145    Ljava/lang/IllegalArgumentException;
        //  126    147    147    151    Ljava/lang/IllegalArgumentException;
        //  161    175    175    179    Ljava/lang/IllegalArgumentException;
        // 
        // The error that occurred was:
        // 
        // java.lang.IllegalStateException: Expression is linked from several locations: Label_0126:
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
    
    static {
        QUOTE_CHECK_TOKENS = new IElementType[] { CMakeTokenTypes.ID, CMakeTokenTypes.LPAR, CMakeTokenTypes.LITERAL, CMakeTokenTypes.RPAR, CMakeTokenTypes.EOL };
    }
    
    private static IllegalArgumentException a(final IllegalArgumentException ex) {
        return ex;
    }
}
