// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.cpp.cmake.completion.contributors.providers;

import com.intellij.codeInsight.completion.PrefixMatcher;
import org.jetbrains.annotations.NotNull;
import com.intellij.codeInsight.completion.impl.CamelHumpMatcher;

private static class FirstLetterCaseMatcher extends CamelHumpMatcher
{
    private final int myIndexOfFirstVariableChar;
    
    public FirstLetterCaseMatcher(@NotNull final String s, final int myIndexOfFirstVariableChar) {
        if (s == null) {
            throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "prefix", "com/jetbrains/cidr/cpp/cmake/completion/contributors/providers/CMakeVariableProvider$FirstLetterCaseMatcher", "<init>"));
        }
        super(s);
        this.myIndexOfFirstVariableChar = myIndexOfFirstVariableChar;
    }
    
    @Override
    public boolean prefixMatches(@NotNull final String p0) {
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
        //    18: ldc             "name"
        //    20: aastore        
        //    21: dup            
        //    22: ldc             1
        //    24: ldc             "com/jetbrains/cidr/cpp/cmake/completion/contributors/providers/CMakeVariableProvider$FirstLetterCaseMatcher"
        //    26: aastore        
        //    27: dup            
        //    28: ldc             2
        //    30: ldc             "prefixMatches"
        //    32: aastore        
        //    33: invokestatic    java/lang/String.format:(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
        //    36: invokespecial   java/lang/IllegalArgumentException.<init>:(Ljava/lang/String;)V
        //    39: athrow         
        //    40: invokestatic    com/jetbrains/cidr/cpp/cmake/completion/contributors/providers/CMakeVariableProvider$FirstLetterCaseMatcher.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //    43: athrow         
        //    44: aload_0        
        //    45: getfield        com/jetbrains/cidr/cpp/cmake/completion/contributors/providers/CMakeVariableProvider$FirstLetterCaseMatcher.myPrefix:Ljava/lang/String;
        //    48: invokevirtual   java/lang/String.length:()I
        //    51: ifle            75
        //    54: aload_0        
        //    55: getfield        com/jetbrains/cidr/cpp/cmake/completion/contributors/providers/CMakeVariableProvider$FirstLetterCaseMatcher.myPrefix:Ljava/lang/String;
        //    58: iconst_0       
        //    59: invokevirtual   java/lang/String.charAt:(I)C
        //    62: invokestatic    java/lang/Character.isUpperCase:(C)Z
        //    65: ifne            92
        //    68: goto            75
        //    71: invokestatic    com/jetbrains/cidr/cpp/cmake/completion/contributors/providers/CMakeVariableProvider$FirstLetterCaseMatcher.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //    74: athrow         
        //    75: aload_0        
        //    76: getfield        com/jetbrains/cidr/cpp/cmake/completion/contributors/providers/CMakeVariableProvider$FirstLetterCaseMatcher.myPrefix:Ljava/lang/String;
        //    79: invokevirtual   java/lang/String.length:()I
        //    82: ifne            115
        //    85: goto            92
        //    88: invokestatic    com/jetbrains/cidr/cpp/cmake/completion/contributors/providers/CMakeVariableProvider$FirstLetterCaseMatcher.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //    91: athrow         
        //    92: aload_0        
        //    93: aload_1        
        //    94: invokespecial   com/intellij/codeInsight/completion/impl/CamelHumpMatcher.prefixMatches:(Ljava/lang/String;)Z
        //    97: ifeq            115
        //   100: goto            107
        //   103: invokestatic    com/jetbrains/cidr/cpp/cmake/completion/contributors/providers/CMakeVariableProvider$FirstLetterCaseMatcher.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   106: athrow         
        //   107: iconst_1       
        //   108: goto            116
        //   111: invokestatic    com/jetbrains/cidr/cpp/cmake/completion/contributors/providers/CMakeVariableProvider$FirstLetterCaseMatcher.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   114: athrow         
        //   115: iconst_0       
        //   116: ireturn        
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                                
        //  -----  -----  -----  -----  ------------------------------------
        //  0      40     40     44     Ljava/lang/IllegalArgumentException;
        //  44     68     71     75     Ljava/lang/IllegalArgumentException;
        //  54     85     88     92     Ljava/lang/IllegalArgumentException;
        //  75     100    103    107    Ljava/lang/IllegalArgumentException;
        //  92     111    111    115    Ljava/lang/IllegalArgumentException;
        // 
        // The error that occurred was:
        // 
        // java.lang.IllegalStateException: Expression is linked from several locations: Label_0075:
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
    
    @NotNull
    @Override
    public PrefixMatcher cloneWithPrefix(@NotNull final String s) {
        try {
            if (s == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "prefix", "com/jetbrains/cidr/cpp/cmake/completion/contributors/providers/CMakeVariableProvider$FirstLetterCaseMatcher", "cloneWithPrefix"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw b(ex);
        }
        FirstLetterCaseMatcher firstLetterCaseMatcher;
        try {
            firstLetterCaseMatcher = new FirstLetterCaseMatcher(s, this.myIndexOfFirstVariableChar);
            if (firstLetterCaseMatcher == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/cpp/cmake/completion/contributors/providers/CMakeVariableProvider$FirstLetterCaseMatcher", "cloneWithPrefix"));
            }
        }
        catch (IllegalArgumentException ex2) {
            throw b(ex2);
        }
        return firstLetterCaseMatcher;
    }
    
    private static IllegalArgumentException b(final IllegalArgumentException ex) {
        return ex;
    }
}
