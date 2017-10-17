// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.lang.formatting;

import org.jetbrains.annotations.Nullable;
import com.intellij.openapi.util.TextRange;
import com.intellij.lang.ASTNode;
import com.intellij.openapi.editor.Document;
import com.intellij.formatting.Block;
import com.intellij.psi.formatter.FormattingDocumentModelImpl;
import com.intellij.formatting.FormattingMode;
import com.intellij.psi.codeStyle.CodeStyleSettings;
import org.jetbrains.annotations.NotNull;
import com.intellij.psi.PsiFile;
import com.intellij.formatting.FormattingModelWithShiftIndentInsideDocumentRange;
import com.intellij.psi.formatter.PsiBasedFormattingModel;

public class OCPsiBasedFormattingModel extends PsiBasedFormattingModel implements FormattingModelWithShiftIndentInsideDocumentRange
{
    @NotNull
    private final PsiFile myFile;
    @NotNull
    private final OCMultilineNodeFormatter myMultilineNodeFormatter;
    
    public OCPsiBasedFormattingModel(@NotNull final PsiFile myFile, @NotNull final CodeStyleSettings codeStyleSettings, @NotNull final FormattingMode formattingMode) {
        if (myFile == null) {
            throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "file", "com/jetbrains/cidr/lang/formatting/OCPsiBasedFormattingModel", "<init>"));
        }
        if (codeStyleSettings == null) {
            throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "settings", "com/jetbrains/cidr/lang/formatting/OCPsiBasedFormattingModel", "<init>"));
        }
        if (formattingMode == null) {
            throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "mode", "com/jetbrains/cidr/lang/formatting/OCPsiBasedFormattingModel", "<init>"));
        }
        super(myFile, (Block)new OCCodeBlock(myFile, codeStyleSettings, formattingMode), FormattingDocumentModelImpl.createOn(myFile));
        this.myFile = myFile;
        this.myMultilineNodeFormatter = new OCMultilineNodeFormatter(codeStyleSettings);
    }
    
    public TextRange shiftIndentInsideDocumentRange(final Document document, final ASTNode astNode, final TextRange textRange, final int n) {
        return this.myMultilineNodeFormatter.shiftIndentInsideRangeInDocument(document, astNode, textRange, n);
    }
    
    public String adjustWhiteSpaceInsideDocument(@Nullable final ASTNode p0, final String p1) {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     0: aload_1        
        //     1: invokestatic    com/jetbrains/cidr/lang/util/OCElementUtil.getElementType:(Lcom/intellij/lang/ASTNode;)Lcom/intellij/psi/tree/IElementType;
        //     4: getstatic       com/jetbrains/cidr/lang/parser/OCTokenTypes.CONDITIONALLY_NON_COMPILED_COMMENT:Lcom/jetbrains/cidr/lang/parser/OCElementType;
        //     7: if_acmpne       188
        //    10: getstatic       com/jetbrains/cidr/lang/formatting/OCMultilineNodeFormatter.PREPROCESSOR_INFO:Lcom/intellij/openapi/util/Key;
        //    13: aload_1        
        //    14: invokevirtual   com/intellij/openapi/util/Key.get:(Lcom/intellij/openapi/util/UserDataHolder;)Ljava/lang/Object;
        //    17: ifnull          188
        //    20: goto            27
        //    23: invokestatic    com/jetbrains/cidr/lang/formatting/OCPsiBasedFormattingModel.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //    26: athrow         
        //    27: getstatic       com/jetbrains/cidr/lang/formatting/OCPsiBasedFormattingModel.$assertionsDisabled:Z
        //    30: ifne            63
        //    33: goto            40
        //    36: invokestatic    com/jetbrains/cidr/lang/formatting/OCPsiBasedFormattingModel.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //    39: athrow         
        //    40: aload_1        
        //    41: ifnonnull       63
        //    44: goto            51
        //    47: invokestatic    com/jetbrains/cidr/lang/formatting/OCPsiBasedFormattingModel.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //    50: athrow         
        //    51: new             Ljava/lang/AssertionError;
        //    54: dup            
        //    55: invokespecial   java/lang/AssertionError.<init>:()V
        //    58: athrow         
        //    59: invokestatic    com/jetbrains/cidr/lang/formatting/OCPsiBasedFormattingModel.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //    62: athrow         
        //    63: aload_2        
        //    64: invokestatic    com/jetbrains/cidr/lang/formatting/OCMultilineNodeFormatter.splitIndent:(Ljava/lang/String;)Lcom/intellij/openapi/util/Pair;
        //    67: astore_3       
        //    68: aload_0        
        //    69: getfield        com/jetbrains/cidr/lang/formatting/OCPsiBasedFormattingModel.myMultilineNodeFormatter:Lcom/jetbrains/cidr/lang/formatting/OCMultilineNodeFormatter;
        //    72: aload_3        
        //    73: getfield        com/intellij/openapi/util/Pair.second:Ljava/lang/Object;
        //    76: checkcast       Ljava/lang/String;
        //    79: invokevirtual   com/jetbrains/cidr/lang/formatting/OCMultilineNodeFormatter.indent:(Ljava/lang/String;)I
        //    82: istore          4
        //    84: iload           4
        //    86: bipush          120
        //    88: if_icmplt       188
        //    91: iinc            4, -120
        //    94: getstatic       com/jetbrains/cidr/lang/formatting/OCMultilineNodeFormatter.CODE_INDENT:Lcom/intellij/openapi/util/Key;
        //    97: aload_1        
        //    98: iload           4
        //   100: invokestatic    java/lang/Integer.valueOf:(I)Ljava/lang/Integer;
        //   103: invokevirtual   com/intellij/openapi/util/Key.set:(Lcom/intellij/openapi/util/UserDataHolder;Ljava/lang/Object;)V
        //   106: aload_1        
        //   107: invokeinterface com/intellij/lang/ASTNode.getChars:()Ljava/lang/CharSequence;
        //   112: astore          5
        //   114: aload           5
        //   116: invokeinterface java/lang/CharSequence.length:()I
        //   121: ifeq            155
        //   124: aload           5
        //   126: iconst_0       
        //   127: invokeinterface java/lang/CharSequence.charAt:(I)C
        //   132: bipush          35
        //   134: if_icmpne       155
        //   137: goto            144
        //   140: invokestatic    com/jetbrains/cidr/lang/formatting/OCPsiBasedFormattingModel.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   143: athrow         
        //   144: aload_3        
        //   145: getfield        com/intellij/openapi/util/Pair.first:Ljava/lang/Object;
        //   148: checkcast       Ljava/lang/String;
        //   151: astore_2       
        //   152: goto            188
        //   155: new             Ljava/lang/StringBuilder;
        //   158: dup            
        //   159: invokespecial   java/lang/StringBuilder.<init>:()V
        //   162: aload_3        
        //   163: getfield        com/intellij/openapi/util/Pair.first:Ljava/lang/Object;
        //   166: checkcast       Ljava/lang/String;
        //   169: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   172: aload_0        
        //   173: getfield        com/jetbrains/cidr/lang/formatting/OCPsiBasedFormattingModel.myMultilineNodeFormatter:Lcom/jetbrains/cidr/lang/formatting/OCMultilineNodeFormatter;
        //   176: iload           4
        //   178: invokevirtual   com/jetbrains/cidr/lang/formatting/OCMultilineNodeFormatter.getStringIndent:(I)Ljava/lang/String;
        //   181: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   184: invokevirtual   java/lang/StringBuilder.toString:()Ljava/lang/String;
        //   187: astore_2       
        //   188: aload_2        
        //   189: areturn        
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                                
        //  -----  -----  -----  -----  ------------------------------------
        //  0      20     23     27     Ljava/lang/IllegalArgumentException;
        //  10     33     36     40     Ljava/lang/IllegalArgumentException;
        //  27     44     47     51     Ljava/lang/IllegalArgumentException;
        //  40     59     59     63     Ljava/lang/IllegalArgumentException;
        //  114    137    140    144    Ljava/lang/IllegalArgumentException;
        // 
        // The error that occurred was:
        // 
        // java.lang.IllegalStateException: Expression is linked from several locations: Label_0027:
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
    public TextRange replaceWhiteSpace(final TextRange textRange, final ASTNode astNode, final String s) {
        return super.replaceWhiteSpace(textRange, astNode, this.adjustWhiteSpaceInsideDocument(astNode, s));
    }
    
    @Override
    public TextRange shiftIndentInsideRange(final ASTNode astNode, final TextRange textRange, final int n) {
        final TextRange shiftIndentInsideRangeInPsiTree = this.myMultilineNodeFormatter.shiftIndentInsideRangeInPsiTree(astNode, textRange, n);
        try {
            if (shiftIndentInsideRangeInPsiTree != null) {
                return shiftIndentInsideRangeInPsiTree;
            }
        }
        catch (IllegalArgumentException ex) {
            throw b(ex);
        }
        return super.shiftIndentInsideRange(astNode, textRange, n);
    }
    
    @Nullable
    @Override
    protected ASTNode findElementAt(final int p0) {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     0: invokestatic    com/jetbrains/cidr/lang/documentation/doxygen/api/DoxygenFacade.isDoxygenSupported:()Z
        //     3: ifeq            94
        //     6: getstatic       com/jetbrains/cidr/lang/formatting/OCPsiBasedFormattingModel.$assertionsDisabled:Z
        //     9: ifne            65
        //    12: goto            19
        //    15: invokestatic    com/jetbrains/cidr/lang/formatting/OCPsiBasedFormattingModel.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //    18: athrow         
        //    19: aload_0        
        //    20: getfield        com/jetbrains/cidr/lang/formatting/OCPsiBasedFormattingModel.myFile:Lcom/intellij/psi/PsiFile;
        //    23: invokeinterface com/intellij/psi/PsiFile.getProject:()Lcom/intellij/openapi/project/Project;
        //    28: invokestatic    com/intellij/psi/PsiDocumentManager.getInstance:(Lcom/intellij/openapi/project/Project;)Lcom/intellij/psi/PsiDocumentManager;
        //    31: aload_0        
        //    32: invokevirtual   com/jetbrains/cidr/lang/formatting/OCPsiBasedFormattingModel.getDocumentModel:()Lcom/intellij/formatting/FormattingDocumentModel;
        //    35: invokeinterface com/intellij/formatting/FormattingDocumentModel.getDocument:()Lcom/intellij/openapi/editor/Document;
        //    40: invokevirtual   com/intellij/psi/PsiDocumentManager.isUncommited:(Lcom/intellij/openapi/editor/Document;)Z
        //    43: ifeq            65
        //    46: goto            53
        //    49: invokestatic    com/jetbrains/cidr/lang/formatting/OCPsiBasedFormattingModel.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //    52: athrow         
        //    53: new             Ljava/lang/AssertionError;
        //    56: dup            
        //    57: invokespecial   java/lang/AssertionError.<init>:()V
        //    60: athrow         
        //    61: invokestatic    com/jetbrains/cidr/lang/formatting/OCPsiBasedFormattingModel.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //    64: athrow         
        //    65: aload_0        
        //    66: getfield        com/jetbrains/cidr/lang/formatting/OCPsiBasedFormattingModel.myFile:Lcom/intellij/psi/PsiFile;
        //    69: iload_1        
        //    70: invokeinterface com/intellij/psi/PsiFile.findElementAt:(I)Lcom/intellij/psi/PsiElement;
        //    75: astore_2       
        //    76: aload_2        
        //    77: instanceof      Lcom/intellij/psi/PsiComment;
        //    80: ifeq            94
        //    83: aload_2        
        //    84: invokeinterface com/intellij/psi/PsiElement.getNode:()Lcom/intellij/lang/ASTNode;
        //    89: areturn        
        //    90: invokestatic    com/jetbrains/cidr/lang/formatting/OCPsiBasedFormattingModel.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //    93: athrow         
        //    94: aload_0        
        //    95: iload_1        
        //    96: invokespecial   com/intellij/psi/formatter/PsiBasedFormattingModel.findElementAt:(I)Lcom/intellij/lang/ASTNode;
        //    99: areturn        
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                                
        //  -----  -----  -----  -----  ------------------------------------
        //  0      12     15     19     Ljava/lang/IllegalArgumentException;
        //  6      46     49     53     Ljava/lang/IllegalArgumentException;
        //  19     61     61     65     Ljava/lang/IllegalArgumentException;
        //  76     90     90     94     Ljava/lang/IllegalArgumentException;
        // 
        // The error that occurred was:
        // 
        // java.lang.IllegalStateException: Expression is linked from several locations: Label_0019:
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
        boolean $assertionsDisabled2 = false;
        Label_0017: {
            try {
                if (!OCPsiBasedFormattingModel.class.desiredAssertionStatus()) {
                    $assertionsDisabled2 = true;
                    break Label_0017;
                }
            }
            catch (IllegalArgumentException ex) {
                throw b(ex);
            }
            $assertionsDisabled2 = false;
        }
        $assertionsDisabled = $assertionsDisabled2;
    }
    
    private static IllegalArgumentException b(final IllegalArgumentException ex) {
        return ex;
    }
}
