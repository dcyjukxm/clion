// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.doxygen.spellchecker;

import com.intellij.spellchecker.quickfixes.AcceptWordAsCorrect;
import com.intellij.spellchecker.quickfixes.SpellCheckerQuickFix;
import com.intellij.openapi.util.TextRange;
import org.jetbrains.annotations.NotNull;
import com.intellij.spellchecker.tokenizer.Tokenizer;
import com.intellij.psi.PsiElement;
import com.intellij.spellchecker.tokenizer.SpellcheckingStrategy;

public class DxSpellCheckingStrategy extends SpellcheckingStrategy
{
    @NotNull
    @Override
    public Tokenizer getTokenizer(final PsiElement p0) {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     0: aload_1        
        //     1: invokeinterface com/intellij/psi/PsiElement.getNode:()Lcom/intellij/lang/ASTNode;
        //     6: astore_2       
        //     7: aload_2        
        //     8: ifnull          79
        //    11: aload_2        
        //    12: invokeinterface com/intellij/lang/ASTNode.getElementType:()Lcom/intellij/psi/tree/IElementType;
        //    17: getstatic       com/jetbrains/cidr/doxygen/psi/DxTypes.DOC_COMMENT_DATA:Lcom/intellij/psi/tree/IElementType;
        //    20: if_acmpne       79
        //    23: goto            30
        //    26: invokestatic    com/jetbrains/cidr/doxygen/spellchecker/DxSpellCheckingStrategy.b:(Ljava/lang/IllegalStateException;)Ljava/lang/IllegalStateException;
        //    29: athrow         
        //    30: getstatic       com/jetbrains/cidr/doxygen/spellchecker/DxSpellCheckingStrategy.TEXT_TOKENIZER:Lcom/intellij/spellchecker/tokenizer/Tokenizer;
        //    33: dup            
        //    34: ifnonnull       78
        //    37: goto            44
        //    40: invokestatic    com/jetbrains/cidr/doxygen/spellchecker/DxSpellCheckingStrategy.b:(Ljava/lang/IllegalStateException;)Ljava/lang/IllegalStateException;
        //    43: athrow         
        //    44: new             Ljava/lang/IllegalStateException;
        //    47: dup            
        //    48: ldc             "@NotNull method %s.%s must not return null"
        //    50: ldc             2
        //    52: anewarray       Ljava/lang/Object;
        //    55: dup            
        //    56: ldc             0
        //    58: ldc             "com/jetbrains/cidr/doxygen/spellchecker/DxSpellCheckingStrategy"
        //    60: aastore        
        //    61: dup            
        //    62: ldc             1
        //    64: ldc             "getTokenizer"
        //    66: aastore        
        //    67: invokestatic    java/lang/String.format:(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
        //    70: invokespecial   java/lang/IllegalStateException.<init>:(Ljava/lang/String;)V
        //    73: athrow         
        //    74: invokestatic    com/jetbrains/cidr/doxygen/spellchecker/DxSpellCheckingStrategy.b:(Ljava/lang/IllegalStateException;)Ljava/lang/IllegalStateException;
        //    77: athrow         
        //    78: areturn        
        //    79: aload_0        
        //    80: aload_1        
        //    81: invokespecial   com/intellij/spellchecker/tokenizer/SpellcheckingStrategy.getTokenizer:(Lcom/intellij/psi/PsiElement;)Lcom/intellij/spellchecker/tokenizer/Tokenizer;
        //    84: dup            
        //    85: ifnonnull       122
        //    88: new             Ljava/lang/IllegalStateException;
        //    91: dup            
        //    92: ldc             "@NotNull method %s.%s must not return null"
        //    94: ldc             2
        //    96: anewarray       Ljava/lang/Object;
        //    99: dup            
        //   100: ldc             0
        //   102: ldc             "com/jetbrains/cidr/doxygen/spellchecker/DxSpellCheckingStrategy"
        //   104: aastore        
        //   105: dup            
        //   106: ldc             1
        //   108: ldc             "getTokenizer"
        //   110: aastore        
        //   111: invokestatic    java/lang/String.format:(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
        //   114: invokespecial   java/lang/IllegalStateException.<init>:(Ljava/lang/String;)V
        //   117: athrow         
        //   118: invokestatic    com/jetbrains/cidr/doxygen/spellchecker/DxSpellCheckingStrategy.b:(Ljava/lang/IllegalStateException;)Ljava/lang/IllegalStateException;
        //   121: athrow         
        //   122: areturn        
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                             
        //  -----  -----  -----  -----  ---------------------------------
        //  7      23     26     30     Ljava/lang/IllegalStateException;
        //  11     37     40     44     Ljava/lang/IllegalStateException;
        //  30     74     74     78     Ljava/lang/IllegalStateException;
        //  79     118    118    122    Ljava/lang/IllegalStateException;
        // 
        // The error that occurred was:
        // 
        // java.lang.IllegalStateException: Expression is linked from several locations: Label_0030:
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
    public SpellCheckerQuickFix[] getRegularFixes(final PsiElement psiElement, final int n, @NotNull final TextRange textRange, final boolean b, final String s) {
        try {
            if (textRange == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "textRange", "com/jetbrains/cidr/doxygen/spellchecker/DxSpellCheckingStrategy", "getRegularFixes"));
            }
        }
        catch (IllegalStateException ex) {
            throw b(ex);
        }
        return new SpellCheckerQuickFix[] { new AcceptWordAsCorrect(s) };
    }
    
    private static IllegalStateException b(final IllegalStateException ex) {
        return ex;
    }
}
