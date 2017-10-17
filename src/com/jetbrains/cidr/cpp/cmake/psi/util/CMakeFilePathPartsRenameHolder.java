// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.cpp.cmake.psi.util;

import com.intellij.openapi.util.TextRange;
import org.jetbrains.annotations.NotNull;
import com.intellij.psi.PsiElement;

public class CMakeFilePathPartsRenameHolder
{
    private String myNewContent;
    private String myOldStart;
    private String myOldEnd;
    
    public CMakeFilePathPartsRenameHolder invoke(@NotNull final PsiElement p0, @NotNull final TextRange p1, final String p2) {
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
        //    18: ldc             "argument"
        //    20: aastore        
        //    21: dup            
        //    22: ldc             1
        //    24: ldc             "com/jetbrains/cidr/cpp/cmake/psi/util/CMakeFilePathPartsRenameHolder"
        //    26: aastore        
        //    27: dup            
        //    28: ldc             2
        //    30: ldc             "invoke"
        //    32: aastore        
        //    33: invokestatic    java/lang/String.format:(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
        //    36: invokespecial   java/lang/IllegalArgumentException.<init>:(Ljava/lang/String;)V
        //    39: athrow         
        //    40: invokestatic    com/jetbrains/cidr/cpp/cmake/psi/util/CMakeFilePathPartsRenameHolder.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //    43: athrow         
        //    44: aload_2        
        //    45: ifnonnull       88
        //    48: new             Ljava/lang/IllegalArgumentException;
        //    51: dup            
        //    52: ldc             "Argument for @NotNull parameter '%s' of %s.%s must not be null"
        //    54: ldc             3
        //    56: anewarray       Ljava/lang/Object;
        //    59: dup            
        //    60: ldc             0
        //    62: ldc             "range"
        //    64: aastore        
        //    65: dup            
        //    66: ldc             1
        //    68: ldc             "com/jetbrains/cidr/cpp/cmake/psi/util/CMakeFilePathPartsRenameHolder"
        //    70: aastore        
        //    71: dup            
        //    72: ldc             2
        //    74: ldc             "invoke"
        //    76: aastore        
        //    77: invokestatic    java/lang/String.format:(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
        //    80: invokespecial   java/lang/IllegalArgumentException.<init>:(Ljava/lang/String;)V
        //    83: athrow         
        //    84: invokestatic    com/jetbrains/cidr/cpp/cmake/psi/util/CMakeFilePathPartsRenameHolder.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //    87: athrow         
        //    88: aload_1        
        //    89: invokeinterface com/intellij/psi/PsiElement.getText:()Ljava/lang/String;
        //    94: astore          4
        //    96: aload_2        
        //    97: invokevirtual   com/intellij/openapi/util/TextRange.getStartOffset:()I
        //   100: istore          5
        //   102: aload_2        
        //   103: invokevirtual   com/intellij/openapi/util/TextRange.getEndOffset:()I
        //   106: istore          6
        //   108: aload_0        
        //   109: aload_3        
        //   110: ldc             "/"
        //   112: invokestatic    com/intellij/openapi/util/text/StringUtil.trimEnd:(Ljava/lang/String;Ljava/lang/String;)Ljava/lang/String;
        //   115: putfield        com/jetbrains/cidr/cpp/cmake/psi/util/CMakeFilePathPartsRenameHolder.myNewContent:Ljava/lang/String;
        //   118: aload_0        
        //   119: getfield        com/jetbrains/cidr/cpp/cmake/psi/util/CMakeFilePathPartsRenameHolder.myNewContent:Ljava/lang/String;
        //   122: invokevirtual   java/lang/String.isEmpty:()Z
        //   125: ifeq            218
        //   128: iload           6
        //   130: iconst_1       
        //   131: iadd           
        //   132: aload           4
        //   134: invokevirtual   java/lang/String.length:()I
        //   137: if_icmpge       176
        //   140: goto            147
        //   143: invokestatic    com/jetbrains/cidr/cpp/cmake/psi/util/CMakeFilePathPartsRenameHolder.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   146: athrow         
        //   147: aload           4
        //   149: iload           6
        //   151: invokevirtual   java/lang/String.charAt:(I)C
        //   154: bipush          47
        //   156: if_icmpne       176
        //   159: goto            166
        //   162: invokestatic    com/jetbrains/cidr/cpp/cmake/psi/util/CMakeFilePathPartsRenameHolder.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   165: athrow         
        //   166: iinc            6, 1
        //   169: goto            218
        //   172: invokestatic    com/jetbrains/cidr/cpp/cmake/psi/util/CMakeFilePathPartsRenameHolder.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   175: athrow         
        //   176: iload           5
        //   178: iconst_1       
        //   179: isub           
        //   180: ifle            212
        //   183: aload           4
        //   185: iload           5
        //   187: invokevirtual   java/lang/String.charAt:(I)C
        //   190: bipush          47
        //   192: if_icmpne       212
        //   195: goto            202
        //   198: invokestatic    com/jetbrains/cidr/cpp/cmake/psi/util/CMakeFilePathPartsRenameHolder.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   201: athrow         
        //   202: iinc            5, -1
        //   205: goto            218
        //   208: invokestatic    com/jetbrains/cidr/cpp/cmake/psi/util/CMakeFilePathPartsRenameHolder.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   211: athrow         
        //   212: aload_0        
        //   213: ldc             "."
        //   215: putfield        com/jetbrains/cidr/cpp/cmake/psi/util/CMakeFilePathPartsRenameHolder.myNewContent:Ljava/lang/String;
        //   218: aload_0        
        //   219: aload           4
        //   221: iconst_0       
        //   222: iload           5
        //   224: invokevirtual   java/lang/String.substring:(II)Ljava/lang/String;
        //   227: putfield        com/jetbrains/cidr/cpp/cmake/psi/util/CMakeFilePathPartsRenameHolder.myOldStart:Ljava/lang/String;
        //   230: aload_0        
        //   231: aload           4
        //   233: iload           6
        //   235: invokevirtual   java/lang/String.substring:(I)Ljava/lang/String;
        //   238: putfield        com/jetbrains/cidr/cpp/cmake/psi/util/CMakeFilePathPartsRenameHolder.myOldEnd:Ljava/lang/String;
        //   241: aload_0        
        //   242: areturn        
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                                
        //  -----  -----  -----  -----  ------------------------------------
        //  0      40     40     44     Ljava/lang/IllegalArgumentException;
        //  44     84     84     88     Ljava/lang/IllegalArgumentException;
        //  108    140    143    147    Ljava/lang/IllegalArgumentException;
        //  128    159    162    166    Ljava/lang/IllegalArgumentException;
        //  147    172    172    176    Ljava/lang/IllegalArgumentException;
        //  176    195    198    202    Ljava/lang/IllegalArgumentException;
        //  183    208    208    212    Ljava/lang/IllegalArgumentException;
        // 
        // The error that occurred was:
        // 
        // java.lang.IllegalStateException: Expression is linked from several locations: Label_0147:
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
    
    public String getNewContent() {
        return this.myNewContent;
    }
    
    public String getOldStart() {
        return this.myOldStart;
    }
    
    public String getOldEnd() {
        return this.myOldEnd;
    }
    
    private static IllegalArgumentException a(final IllegalArgumentException ex) {
        return ex;
    }
}
