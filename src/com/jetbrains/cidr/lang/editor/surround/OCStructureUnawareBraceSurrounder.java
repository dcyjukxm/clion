// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.lang.editor.surround;

import com.intellij.psi.PsiFile;
import com.intellij.openapi.editor.Document;
import com.intellij.psi.codeStyle.CodeStyleManager;
import com.intellij.util.text.CharArrayUtil;
import com.intellij.util.IncorrectOperationException;
import com.intellij.openapi.util.TextRange;
import com.intellij.openapi.editor.Editor;
import com.intellij.openapi.project.Project;
import org.jetbrains.annotations.NotNull;
import com.intellij.psi.PsiElement;

public class OCStructureUnawareBraceSurrounder extends OCSurrounder
{
    public String getTemplateDescription() {
        return "{ }";
    }
    
    @Override
    public boolean isApplicable(@NotNull final PsiElement[] p0) {
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
        //    18: ldc             "elements"
        //    20: aastore        
        //    21: dup            
        //    22: ldc             1
        //    24: ldc             "com/jetbrains/cidr/lang/editor/surround/OCStructureUnawareBraceSurrounder"
        //    26: aastore        
        //    27: dup            
        //    28: ldc             2
        //    30: ldc             "isApplicable"
        //    32: aastore        
        //    33: invokestatic    java/lang/String.format:(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
        //    36: invokespecial   java/lang/IllegalArgumentException.<init>:(Ljava/lang/String;)V
        //    39: athrow         
        //    40: invokestatic    com/jetbrains/cidr/lang/editor/surround/OCStructureUnawareBraceSurrounder.a:(Lcom/intellij/util/IncorrectOperationException;)Lcom/intellij/util/IncorrectOperationException;
        //    43: athrow         
        //    44: aload_0        
        //    45: aload_1        
        //    46: invokespecial   com/jetbrains/cidr/lang/editor/surround/OCSurrounder.isApplicable:([Lcom/intellij/psi/PsiElement;)Z
        //    49: ifne            58
        //    52: iconst_0       
        //    53: ireturn        
        //    54: invokestatic    com/jetbrains/cidr/lang/editor/surround/OCStructureUnawareBraceSurrounder.a:(Lcom/intellij/util/IncorrectOperationException;)Lcom/intellij/util/IncorrectOperationException;
        //    57: athrow         
        //    58: aload_1        
        //    59: arraylength    
        //    60: iconst_2       
        //    61: if_icmpeq       70
        //    64: iconst_0       
        //    65: ireturn        
        //    66: invokestatic    com/jetbrains/cidr/lang/editor/surround/OCStructureUnawareBraceSurrounder.a:(Lcom/intellij/util/IncorrectOperationException;)Lcom/intellij/util/IncorrectOperationException;
        //    69: athrow         
        //    70: aload_1        
        //    71: iconst_0       
        //    72: aaload         
        //    73: ldc             Lcom/jetbrains/cidr/lang/psi/OCStatement;.class
        //    75: invokestatic    com/intellij/psi/util/PsiTreeUtil.getParentOfType:(Lcom/intellij/psi/PsiElement;Ljava/lang/Class;)Lcom/intellij/psi/PsiElement;
        //    78: checkcast       Lcom/jetbrains/cidr/lang/psi/OCStatement;
        //    81: astore_2       
        //    82: aload_1        
        //    83: iconst_1       
        //    84: aaload         
        //    85: ldc             Lcom/jetbrains/cidr/lang/psi/OCStatement;.class
        //    87: invokestatic    com/intellij/psi/util/PsiTreeUtil.getParentOfType:(Lcom/intellij/psi/PsiElement;Ljava/lang/Class;)Lcom/intellij/psi/PsiElement;
        //    90: checkcast       Lcom/jetbrains/cidr/lang/psi/OCStatement;
        //    93: astore_3       
        //    94: aload_2        
        //    95: ifnull          131
        //    98: aload_3        
        //    99: ifnull          131
        //   102: goto            109
        //   105: invokestatic    com/jetbrains/cidr/lang/editor/surround/OCStructureUnawareBraceSurrounder.a:(Lcom/intellij/util/IncorrectOperationException;)Lcom/intellij/util/IncorrectOperationException;
        //   108: athrow         
        //   109: aload_2        
        //   110: invokeinterface com/jetbrains/cidr/lang/psi/OCStatement.getParent:()Lcom/intellij/psi/PsiElement;
        //   115: aload_3        
        //   116: invokeinterface com/jetbrains/cidr/lang/psi/OCStatement.getParent:()Lcom/intellij/psi/PsiElement;
        //   121: if_acmpeq       139
        //   124: goto            131
        //   127: invokestatic    com/jetbrains/cidr/lang/editor/surround/OCStructureUnawareBraceSurrounder.a:(Lcom/intellij/util/IncorrectOperationException;)Lcom/intellij/util/IncorrectOperationException;
        //   130: athrow         
        //   131: iconst_1       
        //   132: goto            140
        //   135: invokestatic    com/jetbrains/cidr/lang/editor/surround/OCStructureUnawareBraceSurrounder.a:(Lcom/intellij/util/IncorrectOperationException;)Lcom/intellij/util/IncorrectOperationException;
        //   138: athrow         
        //   139: iconst_0       
        //   140: ireturn        
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                                           
        //  -----  -----  -----  -----  -----------------------------------------------
        //  0      40     40     44     Lcom/intellij/util/IncorrectOperationException;
        //  44     54     54     58     Lcom/intellij/util/IncorrectOperationException;
        //  58     66     66     70     Lcom/intellij/util/IncorrectOperationException;
        //  94     102    105    109    Lcom/intellij/util/IncorrectOperationException;
        //  98     124    127    131    Lcom/intellij/util/IncorrectOperationException;
        //  109    135    135    139    Lcom/intellij/util/IncorrectOperationException;
        // 
        // The error that occurred was:
        // 
        // java.lang.IllegalStateException: Expression is linked from several locations: Label_0109:
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
    
    public TextRange surroundElements(@NotNull final Project project, @NotNull final Editor editor, @NotNull final PsiElement[] array) throws IncorrectOperationException {
        try {
            if (project == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "project", "com/jetbrains/cidr/lang/editor/surround/OCStructureUnawareBraceSurrounder", "surroundElements"));
            }
        }
        catch (IncorrectOperationException ex) {
            throw a(ex);
        }
        try {
            if (editor == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "editor", "com/jetbrains/cidr/lang/editor/surround/OCStructureUnawareBraceSurrounder", "surroundElements"));
            }
        }
        catch (IncorrectOperationException ex2) {
            throw a(ex2);
        }
        try {
            if (array == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "elements", "com/jetbrains/cidr/lang/editor/surround/OCStructureUnawareBraceSurrounder", "surroundElements"));
            }
        }
        catch (IncorrectOperationException ex3) {
            throw a(ex3);
        }
        final int startOffset = array[0].getTextRange().getStartOffset();
        final int endOffset = array[array.length - 1].getTextRange().getEndOffset();
        final Document document = editor.getDocument();
        final int n = CharArrayUtil.shiftBackward(document.getCharsSequence(), startOffset - 1, " \t") + 1;
        document.insertString(endOffset, (CharSequence)"\n}");
        document.insertString(n, (CharSequence)"{\n");
        final CodeStyleManager instance = CodeStyleManager.getInstance(project);
        final PsiFile containingFile = array[0].getContainingFile();
        instance.reformatText(containingFile, n, n + 1);
        instance.reformatText(containingFile, endOffset - 1, endOffset);
        instance.adjustLineIndent(containingFile, TextRange.from(n, endOffset));
        final int n2 = n + "{".length();
        return TextRange.create(n2, n2);
    }
    
    private static IncorrectOperationException a(final IncorrectOperationException ex) {
        return ex;
    }
}
