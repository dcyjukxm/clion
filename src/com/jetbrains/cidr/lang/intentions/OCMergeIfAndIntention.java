// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.lang.intentions;

import com.jetbrains.cidr.lang.psi.OCExpression;
import com.jetbrains.cidr.lang.util.OCElementFactory;
import com.jetbrains.cidr.lang.psi.OCBinaryExpression;
import com.jetbrains.cidr.lang.util.OCParenthesesUtils;
import com.jetbrains.cidr.lang.psi.OCIfStatement;
import com.jetbrains.cidr.lang.psi.OCBlockStatement;
import com.intellij.psi.util.PsiTreeUtil;
import com.jetbrains.cidr.lang.psi.OCStatement;
import com.intellij.util.IncorrectOperationException;
import com.intellij.psi.PsiElement;
import com.intellij.openapi.editor.Editor;
import org.jetbrains.annotations.NotNull;
import com.intellij.openapi.project.Project;
import com.intellij.codeInsight.intention.PsiElementBaseIntentionAction;

public class OCMergeIfAndIntention extends PsiElementBaseIntentionAction
{
    public boolean isAvailable(@NotNull final Project project, final Editor editor, @NotNull final PsiElement psiElement) {
        try {
            if (project == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "project", "com/jetbrains/cidr/lang/intentions/OCMergeIfAndIntention", "isAvailable"));
            }
        }
        catch (IncorrectOperationException ex) {
            throw a(ex);
        }
        try {
            if (psiElement == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "element", "com/jetbrains/cidr/lang/intentions/OCMergeIfAndIntention", "isAvailable"));
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
        try {
            if (!OCMergeElseIfIntention.checkCursorInAppropriatePosition(editor, ocStatement)) {
                return false;
            }
        }
        catch (IncorrectOperationException ex4) {
            throw a(ex4);
        }
        try {
            if (this.a(ocStatement)) {
                return true;
            }
        }
        catch (IncorrectOperationException ex5) {
            throw a(ex5);
        }
        OCStatement ocStatement2;
        for (ocStatement2 = (OCStatement)PsiTreeUtil.getParentOfType((PsiElement)ocStatement, (Class)OCStatement.class); ocStatement2 instanceof OCBlockStatement; ocStatement2 = (OCStatement)PsiTreeUtil.getParentOfType((PsiElement)ocStatement2, (Class)OCStatement.class)) {}
        return this.a(ocStatement2);
    }
    
    @NotNull
    public String getFamilyName() {
        String s;
        try {
            s = "Merge Nested Ifs";
            if (s == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/intentions/OCMergeIfAndIntention", "getFamilyName"));
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
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "project", "com/jetbrains/cidr/lang/intentions/OCMergeIfAndIntention", "invoke"));
            }
        }
        catch (IncorrectOperationException ex) {
            throw a(ex);
        }
        try {
            if (psiElement == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "element", "com/jetbrains/cidr/lang/intentions/OCMergeIfAndIntention", "invoke"));
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
        final OCStatement stripBraces = OCParenthesesUtils.stripBraces(ocIfStatement.getThenBranch());
        try {
            if (!(stripBraces instanceof OCIfStatement)) {
                return;
            }
        }
        catch (IncorrectOperationException ex5) {
            throw a(ex5);
        }
        final OCIfStatement ocIfStatement2 = (OCIfStatement)stripBraces;
        final OCExpression expression = ocIfStatement2.getCondition().getExpression();
        final OCExpression expression2 = ocIfStatement.getCondition().getExpression();
        final OCBinaryExpression ocBinaryExpression = (OCBinaryExpression)OCElementFactory.expressionFromText("1 && 2", (PsiElement)expression);
        OCParenthesesUtils.replaceExpressionAndAppendParentheses(ocBinaryExpression.getLeft(), expression2);
        OCParenthesesUtils.replaceExpressionAndAppendParentheses(ocBinaryExpression.getRight(), expression);
        expression2.replace((PsiElement)ocBinaryExpression);
        ocIfStatement.getThenBranch().replace((PsiElement)ocIfStatement2.getThenBranch());
    }
    
    private boolean a(final OCStatement p0) {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     0: aload_1        
        //     1: instanceof      Lcom/jetbrains/cidr/lang/psi/OCIfStatement;
        //     4: ifeq            183
        //     7: aload_0        
        //     8: aload_0        
        //     9: invokevirtual   com/jetbrains/cidr/lang/intentions/OCMergeIfAndIntention.getFamilyName:()Ljava/lang/String;
        //    12: invokevirtual   com/jetbrains/cidr/lang/intentions/OCMergeIfAndIntention.setText:(Ljava/lang/String;)V
        //    15: aload_1        
        //    16: checkcast       Lcom/jetbrains/cidr/lang/psi/OCIfStatement;
        //    19: astore_2       
        //    20: aload_2        
        //    21: invokeinterface com/jetbrains/cidr/lang/psi/OCIfStatement.getThenBranch:()Lcom/jetbrains/cidr/lang/psi/OCStatement;
        //    26: astore_3       
        //    27: aload_3        
        //    28: invokestatic    com/jetbrains/cidr/lang/util/OCParenthesesUtils.stripBraces:(Lcom/jetbrains/cidr/lang/psi/OCStatement;)Lcom/jetbrains/cidr/lang/psi/OCStatement;
        //    31: astore_3       
        //    32: aload_2        
        //    33: invokeinterface com/jetbrains/cidr/lang/psi/OCIfStatement.getElseBranch:()Lcom/jetbrains/cidr/lang/psi/OCStatement;
        //    38: astore          4
        //    40: aload           4
        //    42: invokestatic    com/jetbrains/cidr/lang/util/OCParenthesesUtils.stripBraces:(Lcom/jetbrains/cidr/lang/psi/OCStatement;)Lcom/jetbrains/cidr/lang/psi/OCStatement;
        //    45: astore          4
        //    47: aload_3        
        //    48: ifnull          77
        //    51: aload           4
        //    53: ifnonnull       77
        //    56: goto            63
        //    59: invokestatic    com/jetbrains/cidr/lang/intentions/OCMergeIfAndIntention.a:(Lcom/intellij/util/IncorrectOperationException;)Lcom/intellij/util/IncorrectOperationException;
        //    62: athrow         
        //    63: aload_3        
        //    64: instanceof      Lcom/jetbrains/cidr/lang/psi/OCIfStatement;
        //    67: ifne            83
        //    70: goto            77
        //    73: invokestatic    com/jetbrains/cidr/lang/intentions/OCMergeIfAndIntention.a:(Lcom/intellij/util/IncorrectOperationException;)Lcom/intellij/util/IncorrectOperationException;
        //    76: athrow         
        //    77: iconst_0       
        //    78: ireturn        
        //    79: invokestatic    com/jetbrains/cidr/lang/intentions/OCMergeIfAndIntention.a:(Lcom/intellij/util/IncorrectOperationException;)Lcom/intellij/util/IncorrectOperationException;
        //    82: athrow         
        //    83: aload_2        
        //    84: invokeinterface com/jetbrains/cidr/lang/psi/OCIfStatement.getCondition:()Lcom/jetbrains/cidr/lang/psi/OCDeclarationOrExpression;
        //    89: ifnull          113
        //    92: aload_2        
        //    93: invokeinterface com/jetbrains/cidr/lang/psi/OCIfStatement.getCondition:()Lcom/jetbrains/cidr/lang/psi/OCDeclarationOrExpression;
        //    98: invokeinterface com/jetbrains/cidr/lang/psi/OCDeclarationOrExpression.getExpression:()Lcom/jetbrains/cidr/lang/psi/OCExpression;
        //   103: ifnonnull       119
        //   106: goto            113
        //   109: invokestatic    com/jetbrains/cidr/lang/intentions/OCMergeIfAndIntention.a:(Lcom/intellij/util/IncorrectOperationException;)Lcom/intellij/util/IncorrectOperationException;
        //   112: athrow         
        //   113: iconst_0       
        //   114: ireturn        
        //   115: invokestatic    com/jetbrains/cidr/lang/intentions/OCMergeIfAndIntention.a:(Lcom/intellij/util/IncorrectOperationException;)Lcom/intellij/util/IncorrectOperationException;
        //   118: athrow         
        //   119: aload_3        
        //   120: checkcast       Lcom/jetbrains/cidr/lang/psi/OCIfStatement;
        //   123: astore          5
        //   125: aload           5
        //   127: invokeinterface com/jetbrains/cidr/lang/psi/OCIfStatement.getCondition:()Lcom/jetbrains/cidr/lang/psi/OCDeclarationOrExpression;
        //   132: ifnull          157
        //   135: aload           5
        //   137: invokeinterface com/jetbrains/cidr/lang/psi/OCIfStatement.getCondition:()Lcom/jetbrains/cidr/lang/psi/OCDeclarationOrExpression;
        //   142: invokeinterface com/jetbrains/cidr/lang/psi/OCDeclarationOrExpression.getExpression:()Lcom/jetbrains/cidr/lang/psi/OCExpression;
        //   147: ifnonnull       163
        //   150: goto            157
        //   153: invokestatic    com/jetbrains/cidr/lang/intentions/OCMergeIfAndIntention.a:(Lcom/intellij/util/IncorrectOperationException;)Lcom/intellij/util/IncorrectOperationException;
        //   156: athrow         
        //   157: iconst_0       
        //   158: ireturn        
        //   159: invokestatic    com/jetbrains/cidr/lang/intentions/OCMergeIfAndIntention.a:(Lcom/intellij/util/IncorrectOperationException;)Lcom/intellij/util/IncorrectOperationException;
        //   162: athrow         
        //   163: aload           5
        //   165: invokeinterface com/jetbrains/cidr/lang/psi/OCIfStatement.getElseBranch:()Lcom/jetbrains/cidr/lang/psi/OCStatement;
        //   170: ifnonnull       181
        //   173: iconst_1       
        //   174: goto            182
        //   177: invokestatic    com/jetbrains/cidr/lang/intentions/OCMergeIfAndIntention.a:(Lcom/intellij/util/IncorrectOperationException;)Lcom/intellij/util/IncorrectOperationException;
        //   180: athrow         
        //   181: iconst_0       
        //   182: ireturn        
        //   183: iconst_0       
        //   184: ireturn        
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                                           
        //  -----  -----  -----  -----  -----------------------------------------------
        //  47     56     59     63     Lcom/intellij/util/IncorrectOperationException;
        //  51     70     73     77     Lcom/intellij/util/IncorrectOperationException;
        //  63     79     79     83     Lcom/intellij/util/IncorrectOperationException;
        //  83     106    109    113    Lcom/intellij/util/IncorrectOperationException;
        //  92     115    115    119    Lcom/intellij/util/IncorrectOperationException;
        //  125    150    153    157    Lcom/intellij/util/IncorrectOperationException;
        //  135    159    159    163    Lcom/intellij/util/IncorrectOperationException;
        //  163    177    177    181    Lcom/intellij/util/IncorrectOperationException;
        // 
        // The error that occurred was:
        // 
        // java.lang.IllegalStateException: Expression is linked from several locations: Label_0063:
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
    
    private static IncorrectOperationException a(final IncorrectOperationException ex) {
        return ex;
    }
}
