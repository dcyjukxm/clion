// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.lang.refactoring.move;

import org.jetbrains.annotations.Nullable;
import com.intellij.lang.ASTNode;
import com.intellij.codeInsight.editorActions.moveUpDown.LineRange;
import com.intellij.openapi.util.Pair;
import com.jetbrains.cidr.lang.util.OCElementsRange;
import com.intellij.psi.PsiElement;
import com.jetbrains.cidr.lang.psi.OCFile;
import com.intellij.codeInsight.editorActions.moveUpDown.StatementUpDownMover;
import com.intellij.psi.PsiFile;
import org.jetbrains.annotations.NotNull;
import com.intellij.openapi.editor.Editor;
import com.intellij.codeInsight.editorActions.moveUpDown.LineMover;

public abstract class OCUpDownMover extends LineMover
{
    public boolean checkAvailable(@NotNull final Editor editor, @NotNull final PsiFile psiFile, @NotNull final StatementUpDownMover.MoveInfo moveInfo, final boolean b) {
        try {
            if (editor == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "editor", "com/jetbrains/cidr/lang/refactoring/move/OCUpDownMover", "checkAvailable"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        try {
            if (psiFile == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "file", "com/jetbrains/cidr/lang/refactoring/move/OCUpDownMover", "checkAvailable"));
            }
        }
        catch (IllegalArgumentException ex2) {
            throw a(ex2);
        }
        try {
            if (moveInfo == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "info", "com/jetbrains/cidr/lang/refactoring/move/OCUpDownMover", "checkAvailable"));
            }
        }
        catch (IllegalArgumentException ex3) {
            throw a(ex3);
        }
        try {
            if (!(psiFile instanceof OCFile)) {
                return false;
            }
        }
        catch (IllegalArgumentException ex4) {
            throw a(ex4);
        }
        try {
            if (!super.checkAvailable(editor, psiFile, moveInfo, b)) {
                return false;
            }
        }
        catch (IllegalArgumentException ex5) {
            throw a(ex5);
        }
        final Pair elementRange = getElementRange(editor, psiFile, moveInfo.toMove);
        try {
            if (elementRange == null) {
                return false;
            }
        }
        catch (IllegalArgumentException ex6) {
            throw a(ex6);
        }
        final OCElementsRange ocElementsRange = new OCElementsRange(this.firstNonMacroWhiteElement(editor, moveInfo.toMove.startLine, (PsiElement)elementRange.first, true, false), this.firstNonMacroWhiteElement(editor, moveInfo.toMove.endLine - 1, (PsiElement)elementRange.second, false, false));
        try {
            if (ocElementsRange.getFirstElement() == null) {
                return false;
            }
            final OCElementsRange ocElementsRange2 = ocElementsRange;
            final PsiElement psiElement = ocElementsRange2.getLastElement();
            if (psiElement == null) {
                return false;
            }
            return this.checkAvailable(editor, moveInfo, ocElementsRange, b);
        }
        catch (IllegalArgumentException ex7) {
            throw a(ex7);
        }
        try {
            final OCElementsRange ocElementsRange2 = ocElementsRange;
            final PsiElement psiElement = ocElementsRange2.getLastElement();
            if (psiElement == null) {
                return false;
            }
        }
        catch (IllegalArgumentException ex8) {
            throw a(ex8);
        }
        return this.checkAvailable(editor, moveInfo, ocElementsRange, b);
    }
    
    public abstract boolean checkAvailable(@NotNull final Editor p0, @NotNull final StatementUpDownMover.MoveInfo p1, @NotNull final OCElementsRange p2, final boolean p3);
    
    protected static int elementStartLine(final Editor editor, final PsiElement psiElement) {
        return editor.offsetToLogicalPosition(psiElement.getTextRange().getStartOffset()).line;
    }
    
    protected static int elementEndLine(final Editor editor, final PsiElement psiElement) {
        return editor.offsetToLogicalPosition(psiElement.getTextRange().getEndOffset()).line;
    }
    
    protected static LineRange calcDestRange(final boolean b, final int n, final int n2) {
        try {
            if (b) {
                return new LineRange(n, n2);
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        try {
            if (n < n2 + 1) {
                return new LineRange(n, n2 + 1);
            }
        }
        catch (IllegalArgumentException ex2) {
            throw a(ex2);
        }
        return new LineRange(n2, n + 1);
    }
    
    @Nullable
    protected PsiElement firstNonMacroWhiteElement(@NotNull final Editor editor, final int n, final int n2, @NotNull final PsiFile psiFile, final boolean b, final boolean b2) {
        try {
            if (editor == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "editor", "com/jetbrains/cidr/lang/refactoring/move/OCUpDownMover", "firstNonMacroWhiteElement"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        try {
            if (psiFile == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "file", "com/jetbrains/cidr/lang/refactoring/move/OCUpDownMover", "firstNonMacroWhiteElement"));
            }
        }
        catch (IllegalArgumentException ex2) {
            throw a(ex2);
        }
        final ASTNode leafElement = psiFile.getNode().findLeafElementAt(n);
        try {
            if (leafElement == null) {
                return null;
            }
        }
        catch (IllegalArgumentException ex3) {
            throw a(ex3);
        }
        return this.firstNonMacroWhiteElement(editor, n2, leafElement.getPsi(), b, b2);
    }
    
    @Nullable
    protected PsiElement firstNonMacroWhiteElement(@NotNull final Editor p0, final int p1, final PsiElement p2, final boolean p3, final boolean p4) {
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
        //    18: ldc             "editor"
        //    20: aastore        
        //    21: dup            
        //    22: ldc             1
        //    24: ldc             "com/jetbrains/cidr/lang/refactoring/move/OCUpDownMover"
        //    26: aastore        
        //    27: dup            
        //    28: ldc             2
        //    30: ldc             "firstNonMacroWhiteElement"
        //    32: aastore        
        //    33: invokestatic    java/lang/String.format:(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
        //    36: invokespecial   java/lang/IllegalArgumentException.<init>:(Ljava/lang/String;)V
        //    39: athrow         
        //    40: invokestatic    com/jetbrains/cidr/lang/refactoring/move/OCUpDownMover.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //    43: athrow         
        //    44: aload_3        
        //    45: ifnull          195
        //    48: aload_0        
        //    49: aload_3        
        //    50: iload           5
        //    52: invokevirtual   com/jetbrains/cidr/lang/refactoring/move/OCUpDownMover.isMacroWhiteElement:(Lcom/intellij/psi/PsiElement;Z)Z
        //    55: ifeq            195
        //    58: goto            65
        //    61: invokestatic    com/jetbrains/cidr/lang/refactoring/move/OCUpDownMover.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //    64: athrow         
        //    65: iload           4
        //    67: ifeq            90
        //    70: goto            77
        //    73: invokestatic    com/jetbrains/cidr/lang/refactoring/move/OCUpDownMover.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //    76: athrow         
        //    77: aload_3        
        //    78: invokeinterface com/intellij/psi/PsiElement.getNextSibling:()Lcom/intellij/psi/PsiElement;
        //    83: goto            96
        //    86: invokestatic    com/jetbrains/cidr/lang/refactoring/move/OCUpDownMover.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //    89: athrow         
        //    90: aload_3        
        //    91: invokeinterface com/intellij/psi/PsiElement.getPrevSibling:()Lcom/intellij/psi/PsiElement;
        //    96: astore          6
        //    98: aload           6
        //   100: ifnonnull       110
        //   103: goto            195
        //   106: invokestatic    com/jetbrains/cidr/lang/refactoring/move/OCUpDownMover.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   109: athrow         
        //   110: aload           6
        //   112: invokestatic    com/jetbrains/cidr/lang/util/OCElementUtil.getElementType:(Lcom/intellij/psi/PsiElement;)Lcom/intellij/psi/tree/IElementType;
        //   115: getstatic       com/intellij/psi/TokenType.WHITE_SPACE:Lcom/intellij/psi/tree/IElementType;
        //   118: if_acmpne       157
        //   121: iload           4
        //   123: ifeq            146
        //   126: goto            133
        //   129: invokestatic    com/jetbrains/cidr/lang/refactoring/move/OCUpDownMover.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   132: athrow         
        //   133: aload_1        
        //   134: aload           6
        //   136: invokestatic    com/jetbrains/cidr/lang/refactoring/move/OCUpDownMover.elementEndLine:(Lcom/intellij/openapi/editor/Editor;Lcom/intellij/psi/PsiElement;)I
        //   139: goto            152
        //   142: invokestatic    com/jetbrains/cidr/lang/refactoring/move/OCUpDownMover.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   145: athrow         
        //   146: aload_1        
        //   147: aload           6
        //   149: invokestatic    com/jetbrains/cidr/lang/refactoring/move/OCUpDownMover.elementStartLine:(Lcom/intellij/openapi/editor/Editor;Lcom/intellij/psi/PsiElement;)I
        //   152: istore          7
        //   154: goto            183
        //   157: iload           4
        //   159: ifeq            175
        //   162: aload_1        
        //   163: aload           6
        //   165: invokestatic    com/jetbrains/cidr/lang/refactoring/move/OCUpDownMover.elementStartLine:(Lcom/intellij/openapi/editor/Editor;Lcom/intellij/psi/PsiElement;)I
        //   168: goto            181
        //   171: invokestatic    com/jetbrains/cidr/lang/refactoring/move/OCUpDownMover.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   174: athrow         
        //   175: aload_1        
        //   176: aload           6
        //   178: invokestatic    com/jetbrains/cidr/lang/refactoring/move/OCUpDownMover.elementEndLine:(Lcom/intellij/openapi/editor/Editor;Lcom/intellij/psi/PsiElement;)I
        //   181: istore          7
        //   183: iload_2        
        //   184: iload           7
        //   186: if_icmpne       195
        //   189: aload           6
        //   191: astore_3       
        //   192: goto            44
        //   195: aload_3        
        //   196: areturn        
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                                
        //  -----  -----  -----  -----  ------------------------------------
        //  0      40     40     44     Ljava/lang/IllegalArgumentException;
        //  44     58     61     65     Ljava/lang/IllegalArgumentException;
        //  48     70     73     77     Ljava/lang/IllegalArgumentException;
        //  65     86     86     90     Ljava/lang/IllegalArgumentException;
        //  98     106    106    110    Ljava/lang/IllegalArgumentException;
        //  110    126    129    133    Ljava/lang/IllegalArgumentException;
        //  121    142    142    146    Ljava/lang/IllegalArgumentException;
        //  157    171    171    175    Ljava/lang/IllegalArgumentException;
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
    
    protected boolean isMacroWhiteElement(final PsiElement p0, final boolean p1) {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     0: iload_2        
        //     1: ifeq            18
        //     4: aload_1        
        //     5: instanceof      Lcom/jetbrains/cidr/lang/psi/OCMacroCall;
        //     8: ifne            52
        //    11: goto            18
        //    14: invokestatic    com/jetbrains/cidr/lang/refactoring/move/OCUpDownMover.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //    17: athrow         
        //    18: aload_1        
        //    19: invokestatic    com/jetbrains/cidr/lang/util/OCElementUtil.isElementEmpty:(Lcom/intellij/psi/PsiElement;)Z
        //    22: ifeq            60
        //    25: goto            32
        //    28: invokestatic    com/jetbrains/cidr/lang/refactoring/move/OCUpDownMover.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //    31: athrow         
        //    32: getstatic       com/jetbrains/cidr/lang/parser/OCTokenTypes.DIRECTIVES:Lcom/intellij/psi/tree/TokenSet;
        //    35: aload_1        
        //    36: invokestatic    com/jetbrains/cidr/lang/util/OCElementUtil.getElementType:(Lcom/intellij/psi/PsiElement;)Lcom/intellij/psi/tree/IElementType;
        //    39: invokevirtual   com/intellij/psi/tree/TokenSet.contains:(Lcom/intellij/psi/tree/IElementType;)Z
        //    42: ifne            60
        //    45: goto            52
        //    48: invokestatic    com/jetbrains/cidr/lang/refactoring/move/OCUpDownMover.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //    51: athrow         
        //    52: iconst_1       
        //    53: goto            61
        //    56: invokestatic    com/jetbrains/cidr/lang/refactoring/move/OCUpDownMover.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //    59: athrow         
        //    60: iconst_0       
        //    61: ireturn        
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                                
        //  -----  -----  -----  -----  ------------------------------------
        //  0      11     14     18     Ljava/lang/IllegalArgumentException;
        //  4      25     28     32     Ljava/lang/IllegalArgumentException;
        //  18     45     48     52     Ljava/lang/IllegalArgumentException;
        //  32     56     56     60     Ljava/lang/IllegalArgumentException;
        // 
        // The error that occurred was:
        // 
        // java.lang.IllegalStateException: Expression is linked from several locations: Label_0018:
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
