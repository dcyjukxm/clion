// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.lang.generate;

import com.intellij.psi.PsiElement;
import com.intellij.openapi.util.TextRange;
import org.jetbrains.annotations.NotNull;
import com.intellij.psi.PsiFile;

public static class ReplacePosition
{
    @NotNull
    public final PsiFile file;
    @NotNull
    public final TextRange range;
    @NotNull
    public final PsiElement context;
    public final boolean addNewLineBefore;
    public final boolean addNewLineAfter;
    
    public ReplacePosition(@NotNull final PsiFile psiFile, @NotNull final TextRange textRange, @NotNull final PsiElement psiElement) {
        if (psiFile == null) {
            throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "file", "com/jetbrains/cidr/lang/generate/OCGenerateUtil$ReplacePosition", "<init>"));
        }
        if (textRange == null) {
            throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "range", "com/jetbrains/cidr/lang/generate/OCGenerateUtil$ReplacePosition", "<init>"));
        }
        if (psiElement == null) {
            throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "context", "com/jetbrains/cidr/lang/generate/OCGenerateUtil$ReplacePosition", "<init>"));
        }
        this(psiFile, textRange, psiElement, true);
    }
    
    public ReplacePosition(@NotNull final PsiFile p0, @NotNull final TextRange p1, @NotNull final PsiElement p2, final boolean p3) {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     0: aload_1        
        //     1: ifnonnull       40
        //     4: new             Ljava/lang/IllegalArgumentException;
        //     7: dup            
        //     8: ldc             "Argument for @NotNull parameter '%s' of %s.%s must not be null"
        //    10: ldc             3
        //    12: anewarray       Ljava/lang/Object;
        //    15: dup            
        //    16: ldc             0
        //    18: ldc             "file"
        //    20: aastore        
        //    21: dup            
        //    22: ldc             1
        //    24: ldc             "com/jetbrains/cidr/lang/generate/OCGenerateUtil$ReplacePosition"
        //    26: aastore        
        //    27: dup            
        //    28: ldc             2
        //    30: ldc             "<init>"
        //    32: aastore        
        //    33: invokestatic    java/lang/String.format:(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
        //    36: invokespecial   java/lang/IllegalArgumentException.<init>:(Ljava/lang/String;)V
        //    39: athrow         
        //    40: aload_2        
        //    41: ifnonnull       80
        //    44: new             Ljava/lang/IllegalArgumentException;
        //    47: dup            
        //    48: ldc             "Argument for @NotNull parameter '%s' of %s.%s must not be null"
        //    50: ldc             3
        //    52: anewarray       Ljava/lang/Object;
        //    55: dup            
        //    56: ldc             0
        //    58: ldc             "range"
        //    60: aastore        
        //    61: dup            
        //    62: ldc             1
        //    64: ldc             "com/jetbrains/cidr/lang/generate/OCGenerateUtil$ReplacePosition"
        //    66: aastore        
        //    67: dup            
        //    68: ldc             2
        //    70: ldc             "<init>"
        //    72: aastore        
        //    73: invokestatic    java/lang/String.format:(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
        //    76: invokespecial   java/lang/IllegalArgumentException.<init>:(Ljava/lang/String;)V
        //    79: athrow         
        //    80: aload_3        
        //    81: ifnonnull       120
        //    84: new             Ljava/lang/IllegalArgumentException;
        //    87: dup            
        //    88: ldc             "Argument for @NotNull parameter '%s' of %s.%s must not be null"
        //    90: ldc             3
        //    92: anewarray       Ljava/lang/Object;
        //    95: dup            
        //    96: ldc             0
        //    98: ldc             "context"
        //   100: aastore        
        //   101: dup            
        //   102: ldc             1
        //   104: ldc             "com/jetbrains/cidr/lang/generate/OCGenerateUtil$ReplacePosition"
        //   106: aastore        
        //   107: dup            
        //   108: ldc             2
        //   110: ldc             "<init>"
        //   112: aastore        
        //   113: invokestatic    java/lang/String.format:(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
        //   116: invokespecial   java/lang/IllegalArgumentException.<init>:(Ljava/lang/String;)V
        //   119: athrow         
        //   120: aload_0        
        //   121: invokespecial   java/lang/Object.<init>:()V
        //   124: aload_0        
        //   125: aload_1        
        //   126: putfield        com/jetbrains/cidr/lang/generate/OCGenerateUtil$ReplacePosition.file:Lcom/intellij/psi/PsiFile;
        //   129: aload_0        
        //   130: aload_2        
        //   131: putfield        com/jetbrains/cidr/lang/generate/OCGenerateUtil$ReplacePosition.range:Lcom/intellij/openapi/util/TextRange;
        //   134: aload_0        
        //   135: aload_3        
        //   136: putfield        com/jetbrains/cidr/lang/generate/OCGenerateUtil$ReplacePosition.context:Lcom/intellij/psi/PsiElement;
        //   139: aload_1        
        //   140: invokeinterface com/intellij/psi/PsiFile.getProject:()Lcom/intellij/openapi/project/Project;
        //   145: invokestatic    com/intellij/psi/PsiDocumentManager.getInstance:(Lcom/intellij/openapi/project/Project;)Lcom/intellij/psi/PsiDocumentManager;
        //   148: aload_1        
        //   149: invokevirtual   com/intellij/psi/PsiDocumentManager.getDocument:(Lcom/intellij/psi/PsiFile;)Lcom/intellij/openapi/editor/Document;
        //   152: astore          5
        //   154: aload_0        
        //   155: iload           4
        //   157: ifeq            204
        //   160: aload           5
        //   162: ifnull          196
        //   165: goto            172
        //   168: invokestatic    com/jetbrains/cidr/lang/generate/OCGenerateUtil$ReplacePosition.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   171: athrow         
        //   172: aload           5
        //   174: invokeinterface com/intellij/openapi/editor/Document.getCharsSequence:()Ljava/lang/CharSequence;
        //   179: aload_2        
        //   180: invokevirtual   com/intellij/openapi/util/TextRange.getStartOffset:()I
        //   183: invokestatic    com/jetbrains/cidr/lang/generate/OCGenerateUtil.shouldInsertNewLineBefore:(Ljava/lang/CharSequence;I)Z
        //   186: ifeq            204
        //   189: goto            196
        //   192: invokestatic    com/jetbrains/cidr/lang/generate/OCGenerateUtil$ReplacePosition.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   195: athrow         
        //   196: iconst_1       
        //   197: goto            205
        //   200: invokestatic    com/jetbrains/cidr/lang/generate/OCGenerateUtil$ReplacePosition.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   203: athrow         
        //   204: iconst_0       
        //   205: putfield        com/jetbrains/cidr/lang/generate/OCGenerateUtil$ReplacePosition.addNewLineBefore:Z
        //   208: aload_0        
        //   209: aload           5
        //   211: ifnull          238
        //   214: aload           5
        //   216: invokeinterface com/intellij/openapi/editor/Document.getCharsSequence:()Ljava/lang/CharSequence;
        //   221: aload_2        
        //   222: invokevirtual   com/intellij/openapi/util/TextRange.getEndOffset:()I
        //   225: invokestatic    com/jetbrains/cidr/lang/generate/OCGenerateUtil.shouldInsertNewLineAfter:(Ljava/lang/CharSequence;I)Z
        //   228: ifeq            246
        //   231: goto            238
        //   234: invokestatic    com/jetbrains/cidr/lang/generate/OCGenerateUtil$ReplacePosition.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   237: athrow         
        //   238: iconst_1       
        //   239: goto            247
        //   242: invokestatic    com/jetbrains/cidr/lang/generate/OCGenerateUtil$ReplacePosition.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   245: athrow         
        //   246: iconst_0       
        //   247: putfield        com/jetbrains/cidr/lang/generate/OCGenerateUtil$ReplacePosition.addNewLineAfter:Z
        //   250: return         
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                                
        //  -----  -----  -----  -----  ------------------------------------
        //  154    165    168    172    Ljava/lang/IllegalArgumentException;
        //  160    189    192    196    Ljava/lang/IllegalArgumentException;
        //  172    200    200    204    Ljava/lang/IllegalArgumentException;
        //  205    231    234    238    Ljava/lang/IllegalArgumentException;
        //  214    242    242    246    Ljava/lang/IllegalArgumentException;
        // 
        // The error that occurred was:
        // 
        // java.lang.IllegalStateException: Expression is linked from several locations: Label_0172:
        //     at com.strobel.decompiler.ast.Error.expressionLinkedFromMultipleLocations(Error.java:27)
        //     at com.strobel.decompiler.ast.AstOptimizer.mergeDisparateObjectInitializations(AstOptimizer.java:2592)
        //     at com.strobel.decompiler.ast.AstOptimizer.optimize(AstOptimizer.java:235)
        //     at com.strobel.decompiler.ast.AstOptimizer.optimize(AstOptimizer.java:42)
        //     at com.strobel.decompiler.languages.java.ast.AstMethodBodyBuilder.createMethodBody(AstMethodBodyBuilder.java:214)
        //     at com.strobel.decompiler.languages.java.ast.AstMethodBodyBuilder.createMethodBody(AstMethodBodyBuilder.java:99)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createMethodBody(AstBuilder.java:757)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createConstructor(AstBuilder.java:692)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.addTypeMembers(AstBuilder.java:529)
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
