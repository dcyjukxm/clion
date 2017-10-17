// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.lang.editor.surround;

import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiFile;
import org.jetbrains.annotations.NotNull;
import com.intellij.lang.surroundWith.Surrounder;
import com.intellij.lang.surroundWith.SurroundDescriptor;

public class OCPrepSurroundDescriptor implements SurroundDescriptor
{
    private static final Surrounder[] SURROUNDERS;
    
    @NotNull
    public Surrounder[] getSurrounders() {
        Surrounder[] surrounders;
        try {
            surrounders = OCPrepSurroundDescriptor.SURROUNDERS;
            if (surrounders == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/editor/surround/OCPrepSurroundDescriptor", "getSurrounders"));
            }
        }
        catch (IllegalStateException ex) {
            throw a(ex);
        }
        return surrounders;
    }
    
    public boolean isExclusive() {
        return false;
    }
    
    @NotNull
    public PsiElement[] getElementsToSurround(final PsiFile p0, final int p1, final int p2) {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     0: aload_1        
        //     1: iload_2        
        //     2: invokeinterface com/intellij/psi/PsiFile.findElementAt:(I)Lcom/intellij/psi/PsiElement;
        //     7: astore          4
        //     9: aload_1        
        //    10: iload_3        
        //    11: iconst_1       
        //    12: isub           
        //    13: invokeinterface com/intellij/psi/PsiFile.findElementAt:(I)Lcom/intellij/psi/PsiElement;
        //    18: astore          5
        //    20: aload           4
        //    22: instanceof      Lcom/intellij/psi/PsiWhiteSpace;
        //    25: ifeq            48
        //    28: aload           4
        //    30: invokeinterface com/intellij/psi/PsiElement.getTextRange:()Lcom/intellij/openapi/util/TextRange;
        //    35: invokevirtual   com/intellij/openapi/util/TextRange.getEndOffset:()I
        //    38: istore_2       
        //    39: aload_1        
        //    40: iload_2        
        //    41: invokeinterface com/intellij/psi/PsiFile.findElementAt:(I)Lcom/intellij/psi/PsiElement;
        //    46: astore          4
        //    48: aload           5
        //    50: instanceof      Lcom/intellij/psi/PsiWhiteSpace;
        //    53: ifeq            78
        //    56: aload           5
        //    58: invokeinterface com/intellij/psi/PsiElement.getTextRange:()Lcom/intellij/openapi/util/TextRange;
        //    63: invokevirtual   com/intellij/openapi/util/TextRange.getStartOffset:()I
        //    66: istore_3       
        //    67: aload_1        
        //    68: iload_3        
        //    69: iconst_1       
        //    70: isub           
        //    71: invokeinterface com/intellij/psi/PsiFile.findElementAt:(I)Lcom/intellij/psi/PsiElement;
        //    76: astore          5
        //    78: aload           4
        //    80: ifnull          95
        //    83: aload           5
        //    85: ifnonnull       144
        //    88: goto            95
        //    91: invokestatic    com/jetbrains/cidr/lang/editor/surround/OCPrepSurroundDescriptor.a:(Ljava/lang/IllegalStateException;)Ljava/lang/IllegalStateException;
        //    94: athrow         
        //    95: getstatic       com/intellij/psi/PsiElement.EMPTY_ARRAY:[Lcom/intellij/psi/PsiElement;
        //    98: dup            
        //    99: ifnonnull       143
        //   102: goto            109
        //   105: invokestatic    com/jetbrains/cidr/lang/editor/surround/OCPrepSurroundDescriptor.a:(Ljava/lang/IllegalStateException;)Ljava/lang/IllegalStateException;
        //   108: athrow         
        //   109: new             Ljava/lang/IllegalStateException;
        //   112: dup            
        //   113: ldc             "@NotNull method %s.%s must not return null"
        //   115: ldc             2
        //   117: anewarray       Ljava/lang/Object;
        //   120: dup            
        //   121: ldc             0
        //   123: ldc             "com/jetbrains/cidr/lang/editor/surround/OCPrepSurroundDescriptor"
        //   125: aastore        
        //   126: dup            
        //   127: ldc             1
        //   129: ldc             "getElementsToSurround"
        //   131: aastore        
        //   132: invokestatic    java/lang/String.format:(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
        //   135: invokespecial   java/lang/IllegalStateException.<init>:(Ljava/lang/String;)V
        //   138: athrow         
        //   139: invokestatic    com/jetbrains/cidr/lang/editor/surround/OCPrepSurroundDescriptor.a:(Ljava/lang/IllegalStateException;)Ljava/lang/IllegalStateException;
        //   142: athrow         
        //   143: areturn        
        //   144: iconst_2       
        //   145: anewarray       Lcom/intellij/psi/PsiElement;
        //   148: dup            
        //   149: iconst_0       
        //   150: aload           4
        //   152: aastore        
        //   153: dup            
        //   154: iconst_1       
        //   155: aload           5
        //   157: aastore        
        //   158: dup            
        //   159: ifnonnull       196
        //   162: new             Ljava/lang/IllegalStateException;
        //   165: dup            
        //   166: ldc             "@NotNull method %s.%s must not return null"
        //   168: ldc             2
        //   170: anewarray       Ljava/lang/Object;
        //   173: dup            
        //   174: ldc             0
        //   176: ldc             "com/jetbrains/cidr/lang/editor/surround/OCPrepSurroundDescriptor"
        //   178: aastore        
        //   179: dup            
        //   180: ldc             1
        //   182: ldc             "getElementsToSurround"
        //   184: aastore        
        //   185: invokestatic    java/lang/String.format:(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
        //   188: invokespecial   java/lang/IllegalStateException.<init>:(Ljava/lang/String;)V
        //   191: athrow         
        //   192: invokestatic    com/jetbrains/cidr/lang/editor/surround/OCPrepSurroundDescriptor.a:(Ljava/lang/IllegalStateException;)Ljava/lang/IllegalStateException;
        //   195: athrow         
        //   196: areturn        
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                             
        //  -----  -----  -----  -----  ---------------------------------
        //  78     88     91     95     Ljava/lang/IllegalStateException;
        //  83     102    105    109    Ljava/lang/IllegalStateException;
        //  95     139    139    143    Ljava/lang/IllegalStateException;
        //  144    192    192    196    Ljava/lang/IllegalStateException;
        // 
        // The error that occurred was:
        // 
        // java.lang.IllegalStateException: Expression is linked from several locations: Label_0095:
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
        SURROUNDERS = new Surrounder[] { new OCCondCompilationSurrounder(), new OCStructureUnawareBraceSurrounder() };
    }
    
    private static IllegalStateException a(final IllegalStateException ex) {
        return ex;
    }
}
