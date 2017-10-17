// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.lang.resolve.references;

import org.jetbrains.annotations.NotNull;
import com.intellij.openapi.vfs.VirtualFile;
import com.intellij.util.Processor;
import com.intellij.openapi.vfs.VirtualFileVisitor;

class OCFileResourceReference$3 extends VirtualFileVisitor {
    @NotNull
    public VirtualFileVisitor.Result visitFileEx(@NotNull final VirtualFile p0) {
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
        //    18: ldc             "child"
        //    20: aastore        
        //    21: dup            
        //    22: ldc             1
        //    24: ldc             "com/jetbrains/cidr/lang/resolve/references/OCFileResourceReference$3"
        //    26: aastore        
        //    27: dup            
        //    28: ldc             2
        //    30: ldc             "visitFileEx"
        //    32: aastore        
        //    33: invokestatic    java/lang/String.format:(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
        //    36: invokespecial   java/lang/IllegalArgumentException.<init>:(Ljava/lang/String;)V
        //    39: athrow         
        //    40: invokestatic    com/jetbrains/cidr/lang/resolve/references/OCFileResourceReference$3.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //    43: athrow         
        //    44: aload_1        
        //    45: invokevirtual   com/intellij/openapi/vfs/VirtualFile.isDirectory:()Z
        //    48: aload_0        
        //    49: getfield        com/jetbrains/cidr/lang/resolve/references/OCFileResourceReference$3.this$0:Lcom/jetbrains/cidr/lang/resolve/references/OCFileResourceReference;
        //    52: invokestatic    com/jetbrains/cidr/lang/resolve/references/OCFileResourceReference.access$000:(Lcom/jetbrains/cidr/lang/resolve/references/OCFileResourceReference;)Z
        //    55: if_icmpne       146
        //    58: aload_0        
        //    59: aload_1        
        //    60: invokespecial   com/jetbrains/cidr/lang/resolve/references/OCFileResourceReference$3.a:(Lcom/intellij/openapi/vfs/VirtualFile;)Z
        //    63: ifeq            146
        //    66: goto            73
        //    69: invokestatic    com/jetbrains/cidr/lang/resolve/references/OCFileResourceReference$3.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //    72: athrow         
        //    73: aload_0        
        //    74: getfield        com/jetbrains/cidr/lang/resolve/references/OCFileResourceReference$3.val$processor:Lcom/intellij/util/Processor;
        //    77: aload_1        
        //    78: invokeinterface com/intellij/util/Processor.process:(Ljava/lang/Object;)Z
        //    83: ifne            146
        //    86: goto            93
        //    89: invokestatic    com/jetbrains/cidr/lang/resolve/references/OCFileResourceReference$3.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //    92: athrow         
        //    93: aload_0        
        //    94: getfield        com/jetbrains/cidr/lang/resolve/references/OCFileResourceReference$3.val$file:Lcom/intellij/openapi/vfs/VirtualFile;
        //    97: invokestatic    com/jetbrains/cidr/lang/resolve/references/OCFileResourceReference$3.skipTo:(Lcom/intellij/openapi/vfs/VirtualFile;)Lcom/intellij/openapi/vfs/VirtualFileVisitor$Result;
        //   100: dup            
        //   101: ifnonnull       145
        //   104: goto            111
        //   107: invokestatic    com/jetbrains/cidr/lang/resolve/references/OCFileResourceReference$3.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   110: athrow         
        //   111: new             Ljava/lang/IllegalStateException;
        //   114: dup            
        //   115: ldc             "@NotNull method %s.%s must not return null"
        //   117: ldc             2
        //   119: anewarray       Ljava/lang/Object;
        //   122: dup            
        //   123: ldc             0
        //   125: ldc             "com/jetbrains/cidr/lang/resolve/references/OCFileResourceReference$3"
        //   127: aastore        
        //   128: dup            
        //   129: ldc             1
        //   131: ldc             "visitFileEx"
        //   133: aastore        
        //   134: invokestatic    java/lang/String.format:(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
        //   137: invokespecial   java/lang/IllegalStateException.<init>:(Ljava/lang/String;)V
        //   140: athrow         
        //   141: invokestatic    com/jetbrains/cidr/lang/resolve/references/OCFileResourceReference$3.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   144: athrow         
        //   145: areturn        
        //   146: getstatic       com/jetbrains/cidr/lang/resolve/references/OCFileResourceReference$3.CONTINUE:Lcom/intellij/openapi/vfs/VirtualFileVisitor$Result;
        //   149: dup            
        //   150: ifnonnull       187
        //   153: new             Ljava/lang/IllegalStateException;
        //   156: dup            
        //   157: ldc             "@NotNull method %s.%s must not return null"
        //   159: ldc             2
        //   161: anewarray       Ljava/lang/Object;
        //   164: dup            
        //   165: ldc             0
        //   167: ldc             "com/jetbrains/cidr/lang/resolve/references/OCFileResourceReference$3"
        //   169: aastore        
        //   170: dup            
        //   171: ldc             1
        //   173: ldc             "visitFileEx"
        //   175: aastore        
        //   176: invokestatic    java/lang/String.format:(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
        //   179: invokespecial   java/lang/IllegalStateException.<init>:(Ljava/lang/String;)V
        //   182: athrow         
        //   183: invokestatic    com/jetbrains/cidr/lang/resolve/references/OCFileResourceReference$3.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   186: athrow         
        //   187: areturn        
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                                
        //  -----  -----  -----  -----  ------------------------------------
        //  0      40     40     44     Ljava/lang/IllegalArgumentException;
        //  44     66     69     73     Ljava/lang/IllegalArgumentException;
        //  58     86     89     93     Ljava/lang/IllegalArgumentException;
        //  73     104    107    111    Ljava/lang/IllegalArgumentException;
        //  93     141    141    145    Ljava/lang/IllegalArgumentException;
        //  146    183    183    187    Ljava/lang/IllegalArgumentException;
        // 
        // The error that occurred was:
        // 
        // java.lang.IllegalStateException: Expression is linked from several locations: Label_0073:
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
    
    private boolean a(final VirtualFile p0) {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     0: aload_1        
        //     1: invokevirtual   com/intellij/openapi/vfs/VirtualFile.getExtension:()Ljava/lang/String;
        //     4: astore_2       
        //     5: aload_0        
        //     6: getfield        com/jetbrains/cidr/lang/resolve/references/OCFileResourceReference$3.this$0:Lcom/jetbrains/cidr/lang/resolve/references/OCFileResourceReference;
        //     9: invokestatic    com/jetbrains/cidr/lang/resolve/references/OCFileResourceReference.access$100:(Lcom/jetbrains/cidr/lang/resolve/references/OCFileResourceReference;)Ljava/util/Set;
        //    12: ifnull          58
        //    15: aload_2        
        //    16: ifnull          52
        //    19: goto            26
        //    22: invokestatic    com/jetbrains/cidr/lang/resolve/references/OCFileResourceReference$3.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //    25: athrow         
        //    26: aload_0        
        //    27: getfield        com/jetbrains/cidr/lang/resolve/references/OCFileResourceReference$3.this$0:Lcom/jetbrains/cidr/lang/resolve/references/OCFileResourceReference;
        //    30: invokestatic    com/jetbrains/cidr/lang/resolve/references/OCFileResourceReference.access$100:(Lcom/jetbrains/cidr/lang/resolve/references/OCFileResourceReference;)Ljava/util/Set;
        //    33: aload_2        
        //    34: invokevirtual   java/lang/String.toLowerCase:()Ljava/lang/String;
        //    37: invokeinterface java/util/Set.contains:(Ljava/lang/Object;)Z
        //    42: ifne            58
        //    45: goto            52
        //    48: invokestatic    com/jetbrains/cidr/lang/resolve/references/OCFileResourceReference$3.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //    51: athrow         
        //    52: iconst_0       
        //    53: ireturn        
        //    54: invokestatic    com/jetbrains/cidr/lang/resolve/references/OCFileResourceReference$3.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //    57: athrow         
        //    58: aload_0        
        //    59: getfield        com/jetbrains/cidr/lang/resolve/references/OCFileResourceReference$3.val$fileName:Ljava/lang/String;
        //    62: ifnonnull       71
        //    65: iconst_1       
        //    66: ireturn        
        //    67: invokestatic    com/jetbrains/cidr/lang/resolve/references/OCFileResourceReference$3.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //    70: athrow         
        //    71: aload_1        
        //    72: invokevirtual   com/intellij/openapi/vfs/VirtualFile.getNameWithoutExtension:()Ljava/lang/String;
        //    75: aload_0        
        //    76: getfield        com/jetbrains/cidr/lang/resolve/references/OCFileResourceReference$3.val$fileName:Ljava/lang/String;
        //    79: invokevirtual   java/lang/String.equals:(Ljava/lang/Object;)Z
        //    82: ifeq            91
        //    85: iconst_1       
        //    86: ireturn        
        //    87: invokestatic    com/jetbrains/cidr/lang/resolve/references/OCFileResourceReference$3.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //    90: athrow         
        //    91: aload_0        
        //    92: getfield        com/jetbrains/cidr/lang/resolve/references/OCFileResourceReference$3.this$0:Lcom/jetbrains/cidr/lang/resolve/references/OCFileResourceReference;
        //    95: invokestatic    com/jetbrains/cidr/lang/resolve/references/OCFileResourceReference.access$100:(Lcom/jetbrains/cidr/lang/resolve/references/OCFileResourceReference;)Ljava/util/Set;
        //    98: ifnull          140
        //   101: aload_1        
        //   102: invokestatic    com/jetbrains/cidr/lang/OCGroupedFileNamings.getGroupedFileNaming:(Lcom/intellij/openapi/vfs/VirtualFile;)Lcom/jetbrains/cidr/lang/OCGroupedFileNaming;
        //   105: astore_3       
        //   106: aload_3        
        //   107: ifnull          140
        //   110: aload_3        
        //   111: aload_1        
        //   112: invokevirtual   com/intellij/openapi/vfs/VirtualFile.getNameWithoutExtension:()Ljava/lang/String;
        //   115: invokeinterface com/jetbrains/cidr/lang/OCGroupedFileNaming.getBaseName:(Ljava/lang/String;)Ljava/lang/String;
        //   120: astore          4
        //   122: aload           4
        //   124: aload_0        
        //   125: getfield        com/jetbrains/cidr/lang/resolve/references/OCFileResourceReference$3.val$fileName:Ljava/lang/String;
        //   128: invokevirtual   java/lang/String.equals:(Ljava/lang/Object;)Z
        //   131: ifeq            140
        //   134: iconst_1       
        //   135: ireturn        
        //   136: invokestatic    com/jetbrains/cidr/lang/resolve/references/OCFileResourceReference$3.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   139: athrow         
        //   140: iconst_0       
        //   141: ireturn        
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                                
        //  -----  -----  -----  -----  ------------------------------------
        //  5      19     22     26     Ljava/lang/IllegalArgumentException;
        //  15     45     48     52     Ljava/lang/IllegalArgumentException;
        //  26     54     54     58     Ljava/lang/IllegalArgumentException;
        //  58     67     67     71     Ljava/lang/IllegalArgumentException;
        //  71     87     87     91     Ljava/lang/IllegalArgumentException;
        //  122    136    136    140    Ljava/lang/IllegalArgumentException;
        // 
        // The error that occurred was:
        // 
        // java.lang.IllegalStateException: Expression is linked from several locations: Label_0026:
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