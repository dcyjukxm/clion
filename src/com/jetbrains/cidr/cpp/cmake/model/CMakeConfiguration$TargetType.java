// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.cpp.cmake.model;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public enum TargetType
{
    EXECUTABLE, 
    STATIC_LIBRARY, 
    SHARED_LIBRARY, 
    UTILITY;
    
    @NotNull
    public static TargetType fromString(@Nullable final String p0) {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     0: aload_0        
        //     1: iconst_0       
        //     2: invokestatic    com/intellij/openapi/util/text/StringUtil.parseInt:(Ljava/lang/String;I)I
        //     5: istore_1       
        //     6: iload_1        
        //     7: tableswitch {
        //                0: 36
        //                1: 36
        //                2: 85
        //                3: 127
        //          default: 169
        //        }
        //    36: getstatic       com/jetbrains/cidr/cpp/cmake/model/CMakeConfiguration$TargetType.EXECUTABLE:Lcom/jetbrains/cidr/cpp/cmake/model/CMakeConfiguration$TargetType;
        //    39: dup            
        //    40: ifnonnull       84
        //    43: goto            50
        //    46: invokestatic    com/jetbrains/cidr/cpp/cmake/model/CMakeConfiguration$TargetType.a:(Ljava/lang/IllegalStateException;)Ljava/lang/IllegalStateException;
        //    49: athrow         
        //    50: new             Ljava/lang/IllegalStateException;
        //    53: dup            
        //    54: ldc             "@NotNull method %s.%s must not return null"
        //    56: ldc             2
        //    58: anewarray       Ljava/lang/Object;
        //    61: dup            
        //    62: ldc             0
        //    64: ldc             "com/jetbrains/cidr/cpp/cmake/model/CMakeConfiguration$TargetType"
        //    66: aastore        
        //    67: dup            
        //    68: ldc             1
        //    70: ldc             "fromString"
        //    72: aastore        
        //    73: invokestatic    java/lang/String.format:(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
        //    76: invokespecial   java/lang/IllegalStateException.<init>:(Ljava/lang/String;)V
        //    79: athrow         
        //    80: invokestatic    com/jetbrains/cidr/cpp/cmake/model/CMakeConfiguration$TargetType.a:(Ljava/lang/IllegalStateException;)Ljava/lang/IllegalStateException;
        //    83: athrow         
        //    84: areturn        
        //    85: getstatic       com/jetbrains/cidr/cpp/cmake/model/CMakeConfiguration$TargetType.STATIC_LIBRARY:Lcom/jetbrains/cidr/cpp/cmake/model/CMakeConfiguration$TargetType;
        //    88: dup            
        //    89: ifnonnull       126
        //    92: new             Ljava/lang/IllegalStateException;
        //    95: dup            
        //    96: ldc             "@NotNull method %s.%s must not return null"
        //    98: ldc             2
        //   100: anewarray       Ljava/lang/Object;
        //   103: dup            
        //   104: ldc             0
        //   106: ldc             "com/jetbrains/cidr/cpp/cmake/model/CMakeConfiguration$TargetType"
        //   108: aastore        
        //   109: dup            
        //   110: ldc             1
        //   112: ldc             "fromString"
        //   114: aastore        
        //   115: invokestatic    java/lang/String.format:(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
        //   118: invokespecial   java/lang/IllegalStateException.<init>:(Ljava/lang/String;)V
        //   121: athrow         
        //   122: invokestatic    com/jetbrains/cidr/cpp/cmake/model/CMakeConfiguration$TargetType.a:(Ljava/lang/IllegalStateException;)Ljava/lang/IllegalStateException;
        //   125: athrow         
        //   126: areturn        
        //   127: getstatic       com/jetbrains/cidr/cpp/cmake/model/CMakeConfiguration$TargetType.SHARED_LIBRARY:Lcom/jetbrains/cidr/cpp/cmake/model/CMakeConfiguration$TargetType;
        //   130: dup            
        //   131: ifnonnull       168
        //   134: new             Ljava/lang/IllegalStateException;
        //   137: dup            
        //   138: ldc             "@NotNull method %s.%s must not return null"
        //   140: ldc             2
        //   142: anewarray       Ljava/lang/Object;
        //   145: dup            
        //   146: ldc             0
        //   148: ldc             "com/jetbrains/cidr/cpp/cmake/model/CMakeConfiguration$TargetType"
        //   150: aastore        
        //   151: dup            
        //   152: ldc             1
        //   154: ldc             "fromString"
        //   156: aastore        
        //   157: invokestatic    java/lang/String.format:(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
        //   160: invokespecial   java/lang/IllegalStateException.<init>:(Ljava/lang/String;)V
        //   163: athrow         
        //   164: invokestatic    com/jetbrains/cidr/cpp/cmake/model/CMakeConfiguration$TargetType.a:(Ljava/lang/IllegalStateException;)Ljava/lang/IllegalStateException;
        //   167: athrow         
        //   168: areturn        
        //   169: getstatic       com/jetbrains/cidr/cpp/cmake/model/CMakeConfiguration$TargetType.UTILITY:Lcom/jetbrains/cidr/cpp/cmake/model/CMakeConfiguration$TargetType;
        //   172: dup            
        //   173: ifnonnull       210
        //   176: new             Ljava/lang/IllegalStateException;
        //   179: dup            
        //   180: ldc             "@NotNull method %s.%s must not return null"
        //   182: ldc             2
        //   184: anewarray       Ljava/lang/Object;
        //   187: dup            
        //   188: ldc             0
        //   190: ldc             "com/jetbrains/cidr/cpp/cmake/model/CMakeConfiguration$TargetType"
        //   192: aastore        
        //   193: dup            
        //   194: ldc             1
        //   196: ldc             "fromString"
        //   198: aastore        
        //   199: invokestatic    java/lang/String.format:(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
        //   202: invokespecial   java/lang/IllegalStateException.<init>:(Ljava/lang/String;)V
        //   205: athrow         
        //   206: invokestatic    com/jetbrains/cidr/cpp/cmake/model/CMakeConfiguration$TargetType.a:(Ljava/lang/IllegalStateException;)Ljava/lang/IllegalStateException;
        //   209: athrow         
        //   210: areturn        
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                             
        //  -----  -----  -----  -----  ---------------------------------
        //  6      43     46     50     Ljava/lang/IllegalStateException;
        //  36     80     80     84     Ljava/lang/IllegalStateException;
        //  85     122    122    126    Ljava/lang/IllegalStateException;
        //  127    164    164    168    Ljava/lang/IllegalStateException;
        //  169    206    206    210    Ljava/lang/IllegalStateException;
        // 
        // The error that occurred was:
        // 
        // java.lang.IllegalStateException: Expression is linked from several locations: Label_0036:
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
    
    private static IllegalStateException a(final IllegalStateException ex) {
        return ex;
    }
}
