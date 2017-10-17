// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.lang.intentions;

import com.jetbrains.cidr.lang.util.OCParenthesesUtils;
import com.jetbrains.cidr.lang.psi.OCBlockStatement;
import com.jetbrains.cidr.lang.psi.OCIfStatement;
import com.intellij.psi.util.PsiTreeUtil;
import com.jetbrains.cidr.lang.psi.OCStatement;
import com.intellij.util.IncorrectOperationException;
import com.intellij.psi.PsiElement;
import com.intellij.openapi.editor.Editor;
import org.jetbrains.annotations.NotNull;
import com.intellij.openapi.project.Project;
import com.intellij.codeInsight.intention.PsiElementBaseIntentionAction;

public class OCMergeElseIfIntention extends PsiElementBaseIntentionAction
{
    public boolean isAvailable(@NotNull final Project project, final Editor editor, @NotNull final PsiElement psiElement) {
        try {
            if (project == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "project", "com/jetbrains/cidr/lang/intentions/OCMergeElseIfIntention", "isAvailable"));
            }
        }
        catch (IncorrectOperationException ex) {
            throw a(ex);
        }
        try {
            if (psiElement == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "element", "com/jetbrains/cidr/lang/intentions/OCMergeElseIfIntention", "isAvailable"));
            }
        }
        catch (IncorrectOperationException ex2) {
            throw a(ex2);
        }
        final OCStatement ocStatement = (OCStatement)PsiTreeUtil.getParentOfType(psiElement, (Class)OCStatement.class);
        try {
            if (ocStatement == null) {
                return false;
            }
        }
        catch (IncorrectOperationException ex3) {
            throw a(ex3);
        }
        Label_0146: {
            try {
                if (!this.a(ocStatement)) {
                    break Label_0146;
                }
                final Editor editor2 = editor;
                final OCStatement ocStatement2 = ocStatement;
                final OCIfStatement ocIfStatement = (OCIfStatement)ocStatement2;
                final boolean b = checkCursorAtElseKeyword(editor2, ocIfStatement);
                if (b) {
                    return true;
                }
                return false;
            }
            catch (IncorrectOperationException ex4) {
                throw a(ex4);
            }
            try {
                final Editor editor2 = editor;
                final OCStatement ocStatement2 = ocStatement;
                final OCIfStatement ocIfStatement = (OCIfStatement)ocStatement2;
                final boolean b = checkCursorAtElseKeyword(editor2, ocIfStatement);
                if (b) {
                    return true;
                }
            }
            catch (IncorrectOperationException ex5) {
                throw a(ex5);
            }
            return false;
        }
        while (true) {
            OCStatement ocStatement3 = (OCStatement)PsiTreeUtil.getParentOfType((PsiElement)ocStatement, (Class)OCStatement.class);
            Label_0221: {
                Label_0196: {
                    while (true) {
                        try {
                            if (!(ocStatement3 instanceof OCBlockStatement)) {
                                return false;
                            }
                            if (!(ocStatement3 instanceof OCBlockStatement)) {
                                break Label_0196;
                            }
                        }
                        catch (IncorrectOperationException ex6) {
                            throw a(ex6);
                        }
                        ocStatement3 = (OCStatement)PsiTreeUtil.getParentOfType((PsiElement)ocStatement3, (Class)OCStatement.class);
                        continue;
                    }
                    try {
                        if (!this.a(ocStatement3)) {
                            return false;
                        }
                        final Editor editor3 = editor;
                        final OCStatement ocStatement4 = ocStatement;
                        final boolean b2 = checkCursorInAppropriatePosition(editor3, ocStatement4);
                        if (b2) {
                            break Label_0221;
                        }
                        return false;
                    }
                    catch (IncorrectOperationException ex7) {
                        throw a(ex7);
                    }
                }
                try {
                    final Editor editor3 = editor;
                    final OCStatement ocStatement4 = ocStatement;
                    final boolean b2 = checkCursorInAppropriatePosition(editor3, ocStatement4);
                    if (b2) {
                        return true;
                    }
                }
                catch (IncorrectOperationException ex8) {
                    throw a(ex8);
                }
            }
            return false;
        }
        return false;
    }
    
    @NotNull
    public String getFamilyName() {
        String s;
        try {
            s = "Merge If Else";
            if (s == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/intentions/OCMergeElseIfIntention", "getFamilyName"));
            }
        }
        catch (IncorrectOperationException ex) {
            throw a(ex);
        }
        return s;
    }
    
    public void invoke(@NotNull final Project project, final Editor editor, @NotNull final PsiElement psiElement) throws IncorrectOperationException {
        try {
            if (project == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "project", "com/jetbrains/cidr/lang/intentions/OCMergeElseIfIntention", "invoke"));
            }
        }
        catch (IncorrectOperationException ex) {
            throw a(ex);
        }
        try {
            if (psiElement == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "element", "com/jetbrains/cidr/lang/intentions/OCMergeElseIfIntention", "invoke"));
            }
        }
        catch (IncorrectOperationException ex2) {
            throw a(ex2);
        }
        OCStatement ocStatement = (OCStatement)PsiTreeUtil.getParentOfType(psiElement, (Class)OCStatement.class);
        try {
            if (ocStatement == null) {
                return;
            }
        }
        catch (IncorrectOperationException ex3) {
            throw a(ex3);
        }
        if (!this.a(ocStatement)) {
            ocStatement = (OCStatement)PsiTreeUtil.getParentOfType((PsiElement)ocStatement, (Class)OCIfStatement.class);
        }
        try {
            if (ocStatement == null) {
                return;
            }
        }
        catch (IncorrectOperationException ex4) {
            throw a(ex4);
        }
        final OCIfStatement ocIfStatement = (OCIfStatement)ocStatement;
        final OCStatement stripBraces = OCParenthesesUtils.stripBraces(ocIfStatement.getElseBranch());
        try {
            if (!(stripBraces instanceof OCIfStatement)) {
                return;
            }
        }
        catch (IncorrectOperationException ex5) {
            throw a(ex5);
        }
        ocIfStatement.getElseBranch().replace((PsiElement)stripBraces);
    }
    
    public static boolean checkCursorInAppropriatePosition(@NotNull final Editor p0, final OCStatement p1) {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     0: aload_0        
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
        //    24: ldc             "com/jetbrains/cidr/lang/intentions/OCMergeElseIfIntention"
        //    26: aastore        
        //    27: dup            
        //    28: ldc             2
        //    30: ldc             "checkCursorInAppropriatePosition"
        //    32: aastore        
        //    33: invokestatic    java/lang/String.format:(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
        //    36: invokespecial   java/lang/IllegalArgumentException.<init>:(Ljava/lang/String;)V
        //    39: athrow         
        //    40: invokestatic    com/jetbrains/cidr/lang/intentions/OCMergeElseIfIntention.a:(Lcom/intellij/util/IncorrectOperationException;)Lcom/intellij/util/IncorrectOperationException;
        //    43: athrow         
        //    44: aload_1        
        //    45: instanceof      Lcom/jetbrains/cidr/lang/psi/OCIfStatement;
        //    48: ifeq            174
        //    51: aload_1        
        //    52: checkcast       Lcom/jetbrains/cidr/lang/psi/OCIfStatement;
        //    55: astore_2       
        //    56: aload_2        
        //    57: invokeinterface com/jetbrains/cidr/lang/psi/OCIfStatement.getRParenth:()Lcom/intellij/lang/ASTNode;
        //    62: astore_3       
        //    63: aload_2        
        //    64: invokeinterface com/jetbrains/cidr/lang/psi/OCIfStatement.getElseKeyword:()Lcom/intellij/lang/ASTNode;
        //    69: astore          4
        //    71: aload_0        
        //    72: invokeinterface com/intellij/openapi/editor/Editor.getCaretModel:()Lcom/intellij/openapi/editor/CaretModel;
        //    77: invokeinterface com/intellij/openapi/editor/CaretModel.getOffset:()I
        //    82: istore          5
        //    84: aload_3        
        //    85: ifnull          106
        //    88: iload           5
        //    90: aload_3        
        //    91: invokeinterface com/intellij/lang/ASTNode.getStartOffset:()I
        //    96: if_icmple       164
        //    99: goto            106
        //   102: invokestatic    com/jetbrains/cidr/lang/intentions/OCMergeElseIfIntention.a:(Lcom/intellij/util/IncorrectOperationException;)Lcom/intellij/util/IncorrectOperationException;
        //   105: athrow         
        //   106: aload           4
        //   108: ifnull          172
        //   111: goto            118
        //   114: invokestatic    com/jetbrains/cidr/lang/intentions/OCMergeElseIfIntention.a:(Lcom/intellij/util/IncorrectOperationException;)Lcom/intellij/util/IncorrectOperationException;
        //   117: athrow         
        //   118: iload           5
        //   120: aload           4
        //   122: invokeinterface com/intellij/lang/ASTNode.getStartOffset:()I
        //   127: if_icmplt       172
        //   130: goto            137
        //   133: invokestatic    com/jetbrains/cidr/lang/intentions/OCMergeElseIfIntention.a:(Lcom/intellij/util/IncorrectOperationException;)Lcom/intellij/util/IncorrectOperationException;
        //   136: athrow         
        //   137: iload           5
        //   139: aload           4
        //   141: invokeinterface com/intellij/lang/ASTNode.getStartOffset:()I
        //   146: aload           4
        //   148: invokeinterface com/intellij/lang/ASTNode.getTextLength:()I
        //   153: iadd           
        //   154: if_icmpgt       172
        //   157: goto            164
        //   160: invokestatic    com/jetbrains/cidr/lang/intentions/OCMergeElseIfIntention.a:(Lcom/intellij/util/IncorrectOperationException;)Lcom/intellij/util/IncorrectOperationException;
        //   163: athrow         
        //   164: iconst_1       
        //   165: goto            173
        //   168: invokestatic    com/jetbrains/cidr/lang/intentions/OCMergeElseIfIntention.a:(Lcom/intellij/util/IncorrectOperationException;)Lcom/intellij/util/IncorrectOperationException;
        //   171: athrow         
        //   172: iconst_0       
        //   173: ireturn        
        //   174: iconst_0       
        //   175: ireturn        
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                                           
        //  -----  -----  -----  -----  -----------------------------------------------
        //  0      40     40     44     Lcom/intellij/util/IncorrectOperationException;
        //  84     99     102    106    Lcom/intellij/util/IncorrectOperationException;
        //  88     111    114    118    Lcom/intellij/util/IncorrectOperationException;
        //  106    130    133    137    Lcom/intellij/util/IncorrectOperationException;
        //  118    157    160    164    Lcom/intellij/util/IncorrectOperationException;
        //  137    168    168    172    Lcom/intellij/util/IncorrectOperationException;
        // 
        // The error that occurred was:
        // 
        // java.lang.IllegalStateException: Expression is linked from several locations: Label_0106:
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
    
    public static boolean checkCursorAtElseKeyword(@NotNull final Editor p0, final OCIfStatement p1) {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     0: aload_0        
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
        //    24: ldc             "com/jetbrains/cidr/lang/intentions/OCMergeElseIfIntention"
        //    26: aastore        
        //    27: dup            
        //    28: ldc             2
        //    30: ldc             "checkCursorAtElseKeyword"
        //    32: aastore        
        //    33: invokestatic    java/lang/String.format:(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
        //    36: invokespecial   java/lang/IllegalArgumentException.<init>:(Ljava/lang/String;)V
        //    39: athrow         
        //    40: invokestatic    com/jetbrains/cidr/lang/intentions/OCMergeElseIfIntention.a:(Lcom/intellij/util/IncorrectOperationException;)Lcom/intellij/util/IncorrectOperationException;
        //    43: athrow         
        //    44: aload_1        
        //    45: invokeinterface com/jetbrains/cidr/lang/psi/OCIfStatement.getElseKeyword:()Lcom/intellij/lang/ASTNode;
        //    50: astore_2       
        //    51: aload_0        
        //    52: invokeinterface com/intellij/openapi/editor/Editor.getCaretModel:()Lcom/intellij/openapi/editor/CaretModel;
        //    57: invokeinterface com/intellij/openapi/editor/CaretModel.getOffset:()I
        //    62: istore_3       
        //    63: aload_2        
        //    64: ifnull          116
        //    67: iload_3        
        //    68: aload_2        
        //    69: invokeinterface com/intellij/lang/ASTNode.getStartOffset:()I
        //    74: if_icmplt       116
        //    77: goto            84
        //    80: invokestatic    com/jetbrains/cidr/lang/intentions/OCMergeElseIfIntention.a:(Lcom/intellij/util/IncorrectOperationException;)Lcom/intellij/util/IncorrectOperationException;
        //    83: athrow         
        //    84: iload_3        
        //    85: aload_2        
        //    86: invokeinterface com/intellij/lang/ASTNode.getStartOffset:()I
        //    91: aload_2        
        //    92: invokeinterface com/intellij/lang/ASTNode.getTextLength:()I
        //    97: iadd           
        //    98: if_icmpgt       116
        //   101: goto            108
        //   104: invokestatic    com/jetbrains/cidr/lang/intentions/OCMergeElseIfIntention.a:(Lcom/intellij/util/IncorrectOperationException;)Lcom/intellij/util/IncorrectOperationException;
        //   107: athrow         
        //   108: iconst_1       
        //   109: goto            117
        //   112: invokestatic    com/jetbrains/cidr/lang/intentions/OCMergeElseIfIntention.a:(Lcom/intellij/util/IncorrectOperationException;)Lcom/intellij/util/IncorrectOperationException;
        //   115: athrow         
        //   116: iconst_0       
        //   117: ireturn        
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                                           
        //  -----  -----  -----  -----  -----------------------------------------------
        //  0      40     40     44     Lcom/intellij/util/IncorrectOperationException;
        //  63     77     80     84     Lcom/intellij/util/IncorrectOperationException;
        //  67     101    104    108    Lcom/intellij/util/IncorrectOperationException;
        //  84     112    112    116    Lcom/intellij/util/IncorrectOperationException;
        // 
        // The error that occurred was:
        // 
        // java.lang.IllegalStateException: Expression is linked from several locations: Label_0084:
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
    
    private boolean a(final OCStatement ocStatement) {
        if (ocStatement instanceof OCIfStatement) {
            this.setText(this.getFamilyName());
            final OCStatement stripBraces = OCParenthesesUtils.stripBraces(((OCIfStatement)ocStatement).getElseBranch());
            Label_0050: {
                try {
                    if (stripBraces == null) {
                        return false;
                    }
                    final OCStatement ocStatement2 = stripBraces;
                    final boolean b = ocStatement2 instanceof OCIfStatement;
                    if (b) {
                        break Label_0050;
                    }
                    return false;
                }
                catch (IncorrectOperationException ex) {
                    throw a(ex);
                }
                try {
                    final OCStatement ocStatement2 = stripBraces;
                    final boolean b = ocStatement2 instanceof OCIfStatement;
                    if (b) {
                        return true;
                    }
                }
                catch (IncorrectOperationException ex2) {
                    throw a(ex2);
                }
            }
            return false;
        }
        return false;
    }
    
    private static IncorrectOperationException a(final IncorrectOperationException ex) {
        return ex;
    }
}
