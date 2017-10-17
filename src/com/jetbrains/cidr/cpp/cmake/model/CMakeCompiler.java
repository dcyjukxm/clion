// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.cpp.cmake.model;

import com.jetbrains.cidr.lang.workspace.compiler.OCCompilerKind;
import org.jetbrains.annotations.NotNull;
import java.io.Serializable;

public class CMakeCompiler implements Serializable
{
    @NotNull
    private final String myId;
    
    public CMakeCompiler(@NotNull final String myId) {
        if (myId == null) {
            throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "id", "com/jetbrains/cidr/cpp/cmake/model/CMakeCompiler", "<init>"));
        }
        this.myId = myId;
    }
    
    @NotNull
    public OCCompilerKind getCompilerKind() {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     0: ldc             "GNU"
        //     2: aload_0        
        //     3: getfield        com/jetbrains/cidr/cpp/cmake/model/CMakeCompiler.myId:Ljava/lang/String;
        //     6: invokevirtual   java/lang/String.equalsIgnoreCase:(Ljava/lang/String;)Z
        //     9: ifeq            61
        //    12: getstatic       com/jetbrains/cidr/lang/workspace/compiler/OCCompilerKind.GCC:Lcom/jetbrains/cidr/lang/workspace/compiler/OCCompilerKind;
        //    15: dup            
        //    16: ifnonnull       60
        //    19: goto            26
        //    22: invokestatic    com/jetbrains/cidr/cpp/cmake/model/CMakeCompiler.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //    25: athrow         
        //    26: new             Ljava/lang/IllegalStateException;
        //    29: dup            
        //    30: ldc             "@NotNull method %s.%s must not return null"
        //    32: ldc             2
        //    34: anewarray       Ljava/lang/Object;
        //    37: dup            
        //    38: ldc             0
        //    40: ldc             "com/jetbrains/cidr/cpp/cmake/model/CMakeCompiler"
        //    42: aastore        
        //    43: dup            
        //    44: ldc             1
        //    46: ldc             "getCompilerKind"
        //    48: aastore        
        //    49: invokestatic    java/lang/String.format:(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
        //    52: invokespecial   java/lang/IllegalStateException.<init>:(Ljava/lang/String;)V
        //    55: athrow         
        //    56: invokestatic    com/jetbrains/cidr/cpp/cmake/model/CMakeCompiler.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //    59: athrow         
        //    60: areturn        
        //    61: ldc             "Clang"
        //    63: aload_0        
        //    64: getfield        com/jetbrains/cidr/cpp/cmake/model/CMakeCompiler.myId:Ljava/lang/String;
        //    67: invokevirtual   java/lang/String.equalsIgnoreCase:(Ljava/lang/String;)Z
        //    70: ifne            92
        //    73: ldc             "AppleClang"
        //    75: aload_0        
        //    76: getfield        com/jetbrains/cidr/cpp/cmake/model/CMakeCompiler.myId:Ljava/lang/String;
        //    79: invokevirtual   java/lang/String.equalsIgnoreCase:(Ljava/lang/String;)Z
        //    82: ifeq            141
        //    85: goto            92
        //    88: invokestatic    com/jetbrains/cidr/cpp/cmake/model/CMakeCompiler.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //    91: athrow         
        //    92: getstatic       com/jetbrains/cidr/lang/workspace/compiler/OCCompilerKind.CLANG:Lcom/jetbrains/cidr/lang/workspace/compiler/OCCompilerKind;
        //    95: dup            
        //    96: ifnonnull       140
        //    99: goto            106
        //   102: invokestatic    com/jetbrains/cidr/cpp/cmake/model/CMakeCompiler.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   105: athrow         
        //   106: new             Ljava/lang/IllegalStateException;
        //   109: dup            
        //   110: ldc             "@NotNull method %s.%s must not return null"
        //   112: ldc             2
        //   114: anewarray       Ljava/lang/Object;
        //   117: dup            
        //   118: ldc             0
        //   120: ldc             "com/jetbrains/cidr/cpp/cmake/model/CMakeCompiler"
        //   122: aastore        
        //   123: dup            
        //   124: ldc             1
        //   126: ldc             "getCompilerKind"
        //   128: aastore        
        //   129: invokestatic    java/lang/String.format:(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
        //   132: invokespecial   java/lang/IllegalStateException.<init>:(Ljava/lang/String;)V
        //   135: athrow         
        //   136: invokestatic    com/jetbrains/cidr/cpp/cmake/model/CMakeCompiler.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   139: athrow         
        //   140: areturn        
        //   141: ldc             "MSVC"
        //   143: aload_0        
        //   144: getfield        com/jetbrains/cidr/cpp/cmake/model/CMakeCompiler.myId:Ljava/lang/String;
        //   147: invokevirtual   java/lang/String.equalsIgnoreCase:(Ljava/lang/String;)Z
        //   150: ifeq            202
        //   153: getstatic       com/jetbrains/cidr/lang/workspace/compiler/OCCompilerKind.MSVC:Lcom/jetbrains/cidr/lang/workspace/compiler/OCCompilerKind;
        //   156: dup            
        //   157: ifnonnull       201
        //   160: goto            167
        //   163: invokestatic    com/jetbrains/cidr/cpp/cmake/model/CMakeCompiler.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   166: athrow         
        //   167: new             Ljava/lang/IllegalStateException;
        //   170: dup            
        //   171: ldc             "@NotNull method %s.%s must not return null"
        //   173: ldc             2
        //   175: anewarray       Ljava/lang/Object;
        //   178: dup            
        //   179: ldc             0
        //   181: ldc             "com/jetbrains/cidr/cpp/cmake/model/CMakeCompiler"
        //   183: aastore        
        //   184: dup            
        //   185: ldc             1
        //   187: ldc             "getCompilerKind"
        //   189: aastore        
        //   190: invokestatic    java/lang/String.format:(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
        //   193: invokespecial   java/lang/IllegalStateException.<init>:(Ljava/lang/String;)V
        //   196: athrow         
        //   197: invokestatic    com/jetbrains/cidr/cpp/cmake/model/CMakeCompiler.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   200: athrow         
        //   201: areturn        
        //   202: getstatic       com/jetbrains/cidr/lang/workspace/compiler/OCCompilerKind.UNKNOWN:Lcom/jetbrains/cidr/lang/workspace/compiler/OCCompilerKind;
        //   205: dup            
        //   206: ifnonnull       243
        //   209: new             Ljava/lang/IllegalStateException;
        //   212: dup            
        //   213: ldc             "@NotNull method %s.%s must not return null"
        //   215: ldc             2
        //   217: anewarray       Ljava/lang/Object;
        //   220: dup            
        //   221: ldc             0
        //   223: ldc             "com/jetbrains/cidr/cpp/cmake/model/CMakeCompiler"
        //   225: aastore        
        //   226: dup            
        //   227: ldc             1
        //   229: ldc             "getCompilerKind"
        //   231: aastore        
        //   232: invokestatic    java/lang/String.format:(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
        //   235: invokespecial   java/lang/IllegalStateException.<init>:(Ljava/lang/String;)V
        //   238: athrow         
        //   239: invokestatic    com/jetbrains/cidr/cpp/cmake/model/CMakeCompiler.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   242: athrow         
        //   243: areturn        
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                                
        //  -----  -----  -----  -----  ------------------------------------
        //  0      19     22     26     Ljava/lang/IllegalArgumentException;
        //  12     56     56     60     Ljava/lang/IllegalArgumentException;
        //  61     85     88     92     Ljava/lang/IllegalArgumentException;
        //  73     99     102    106    Ljava/lang/IllegalArgumentException;
        //  92     136    136    140    Ljava/lang/IllegalArgumentException;
        //  141    160    163    167    Ljava/lang/IllegalArgumentException;
        //  153    197    197    201    Ljava/lang/IllegalArgumentException;
        //  202    239    239    243    Ljava/lang/IllegalArgumentException;
        // 
        // The error that occurred was:
        // 
        // java.lang.IllegalStateException: Expression is linked from several locations: Label_0092:
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
    public String toString() {
        return this.myId;
    }
    
    @Override
    public boolean equals(final Object o) {
        try {
            if (this == o) {
                return true;
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        Label_0039: {
            try {
                if (o == null) {
                    return false;
                }
                final CMakeCompiler cMakeCompiler = this;
                final Class<? extends CMakeCompiler> clazz = cMakeCompiler.getClass();
                final Object o2 = o;
                final Class<?> clazz2 = o2.getClass();
                if (clazz != clazz2) {
                    return false;
                }
                break Label_0039;
            }
            catch (IllegalArgumentException ex2) {
                throw a(ex2);
            }
            try {
                final CMakeCompiler cMakeCompiler = this;
                final Class<? extends CMakeCompiler> clazz = cMakeCompiler.getClass();
                final Object o2 = o;
                final Class<?> clazz2 = o2.getClass();
                if (clazz != clazz2) {
                    return false;
                }
            }
            catch (IllegalArgumentException ex3) {
                throw a(ex3);
            }
        }
        final CMakeCompiler cMakeCompiler2 = (CMakeCompiler)o;
        try {
            if (!this.myId.equals(cMakeCompiler2.myId)) {
                return false;
            }
        }
        catch (IllegalArgumentException ex4) {
            throw a(ex4);
        }
        return true;
    }
    
    @Override
    public int hashCode() {
        return this.myId.hashCode();
    }
    
    private static IllegalArgumentException a(final IllegalArgumentException ex) {
        return ex;
    }
}
