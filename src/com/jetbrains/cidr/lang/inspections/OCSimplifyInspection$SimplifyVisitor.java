// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.lang.inspections;

import com.jetbrains.cidr.lang.util.OCElementFactory;
import com.jetbrains.cidr.lang.parser.OCPunctuatorElementType;
import com.jetbrains.cidr.lang.util.OCCodeInsightUtil;
import com.jetbrains.cidr.lang.types.OCType;
import com.jetbrains.cidr.lang.types.OCTypeUtils;
import com.jetbrains.cidr.lang.types.OCStructType;
import com.jetbrains.cidr.lang.types.OCMagicType;
import com.jetbrains.cidr.lang.symbols.OCResolveContext;
import com.jetbrains.cidr.lang.util.OCParenthesesUtils;
import com.jetbrains.cidr.lang.psi.OCElement;
import com.jetbrains.cidr.lang.parser.OCTokenTypes;
import com.jetbrains.cidr.lang.parser.OCElementType;
import com.jetbrains.cidr.lang.psi.OCStatement;
import com.jetbrains.cidr.lang.util.OCExpressionEvaluator;
import com.jetbrains.cidr.lang.psi.OCDeclarationOrExpression;
import org.jetbrains.annotations.Nullable;
import org.jetbrains.annotations.NotNull;
import com.jetbrains.cidr.lang.psi.OCUnaryExpression;
import com.jetbrains.cidr.lang.psi.OCExpression;
import com.jetbrains.cidr.lang.psi.OCBinaryExpression;
import com.jetbrains.cidr.lang.psi.OCForStatement;
import com.jetbrains.cidr.lang.psi.OCDoWhileStatement;
import com.jetbrains.cidr.lang.psi.OCLoopStatement;
import com.jetbrains.cidr.lang.psi.OCWhileStatement;
import com.jetbrains.cidr.lang.psi.OCIfStatement;
import com.intellij.psi.PsiElement;
import com.jetbrains.cidr.lang.util.OCElementUtil;
import com.jetbrains.cidr.lang.psi.OCConditionalExpression;
import com.jetbrains.cidr.lang.psi.visitors.OCVisitor;

private abstract static class SimplifyVisitor extends OCVisitor
{
    @Override
    public void visitConditionalExpression(final OCConditionalExpression ocConditionalExpression) {
        try {
            if (OCElementUtil.insideDirective((PsiElement)ocConditionalExpression)) {
                return;
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        final Simplifier simplifier = new Simplifier();
        this.a((PsiElement)ocConditionalExpression, (PsiElement)simplifier.a(ocConditionalExpression.getCondition(), ocConditionalExpression.getPositiveExpression(true), ocConditionalExpression.getNegativeExpression()), simplifier);
    }
    
    @Override
    public void visitIfStatement(final OCIfStatement ocIfStatement) {
        final Simplifier simplifier = new Simplifier();
        this.a((PsiElement)ocIfStatement, simplifier.a(ocIfStatement), simplifier);
    }
    
    @Override
    public void visitWhileStatement(final OCWhileStatement ocWhileStatement) {
        this.simplify((PsiElement)ocWhileStatement, a(ocWhileStatement), new Simplifier());
    }
    
    @Override
    public void visitDoWhileStatement(final OCDoWhileStatement ocDoWhileStatement) {
        this.simplify((PsiElement)ocDoWhileStatement, a(ocDoWhileStatement), new Simplifier());
    }
    
    @Override
    public void visitForStatement(final OCForStatement ocForStatement) {
        this.simplify((PsiElement)ocForStatement, a(ocForStatement), new Simplifier());
    }
    
    @Override
    public void visitBinaryExpression(final OCBinaryExpression ocBinaryExpression) {
        Label_0026: {
            try {
                if (OCElementUtil.insideDirective((PsiElement)ocBinaryExpression)) {
                    return;
                }
                final OCBinaryExpression ocBinaryExpression2 = ocBinaryExpression;
                final boolean b = OCSimplifyInspection.access$400(ocBinaryExpression2);
                if (b) {
                    return;
                }
                break Label_0026;
            }
            catch (IllegalArgumentException ex) {
                throw a(ex);
            }
            try {
                final OCBinaryExpression ocBinaryExpression2 = ocBinaryExpression;
                final boolean b = OCSimplifyInspection.access$400(ocBinaryExpression2);
                if (b) {
                    return;
                }
            }
            catch (IllegalArgumentException ex2) {
                throw a(ex2);
            }
        }
        final Simplifier simplifier = new Simplifier();
        this.a((PsiElement)ocBinaryExpression, (PsiElement)simplifier.a(ocBinaryExpression), simplifier);
    }
    
    @Override
    public void visitUnaryExpression(final OCUnaryExpression ocUnaryExpression) {
        Label_0026: {
            try {
                if (OCElementUtil.insideDirective((PsiElement)ocUnaryExpression)) {
                    return;
                }
                final OCUnaryExpression ocUnaryExpression2 = ocUnaryExpression;
                final boolean b = OCSimplifyInspection.access$400(ocUnaryExpression2);
                if (b) {
                    return;
                }
                break Label_0026;
            }
            catch (IllegalArgumentException ex) {
                throw a(ex);
            }
            try {
                final OCUnaryExpression ocUnaryExpression2 = ocUnaryExpression;
                final boolean b = OCSimplifyInspection.access$400(ocUnaryExpression2);
                if (b) {
                    return;
                }
            }
            catch (IllegalArgumentException ex2) {
                throw a(ex2);
            }
        }
        this.a((PsiElement)ocUnaryExpression, (PsiElement)a(ocUnaryExpression), new Simplifier());
    }
    
    private void a(@NotNull final PsiElement p0, @Nullable final PsiElement p1, final Simplifier p2) {
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
        //    18: ldc             "element"
        //    20: aastore        
        //    21: dup            
        //    22: ldc             1
        //    24: ldc             "com/jetbrains/cidr/lang/inspections/OCSimplifyInspection$SimplifyVisitor"
        //    26: aastore        
        //    27: dup            
        //    28: ldc             2
        //    30: ldc             "simplifyAndParenthesize"
        //    32: aastore        
        //    33: invokestatic    java/lang/String.format:(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
        //    36: invokespecial   java/lang/IllegalArgumentException.<init>:(Ljava/lang/String;)V
        //    39: athrow         
        //    40: invokestatic    com/jetbrains/cidr/lang/inspections/OCSimplifyInspection$SimplifyVisitor.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //    43: athrow         
        //    44: aload_1        
        //    45: invokeinterface com/intellij/psi/PsiElement.getParent:()Lcom/intellij/psi/PsiElement;
        //    50: astore          4
        //    52: aload           4
        //    54: instanceof      Lcom/jetbrains/cidr/lang/psi/OCParenthesizedExpression;
        //    57: ifeq            111
        //    60: aload_2        
        //    61: instanceof      Lcom/jetbrains/cidr/lang/psi/OCExpression;
        //    64: ifeq            111
        //    67: goto            74
        //    70: invokestatic    com/jetbrains/cidr/lang/inspections/OCSimplifyInspection$SimplifyVisitor.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //    73: athrow         
        //    74: aload           4
        //    76: checkcast       Lcom/jetbrains/cidr/lang/psi/OCExpression;
        //    79: aload_2        
        //    80: checkcast       Lcom/jetbrains/cidr/lang/psi/OCExpression;
        //    83: invokestatic    com/jetbrains/cidr/lang/util/OCParenthesesUtils.isParenthesesNeededInReplacing:(Lcom/jetbrains/cidr/lang/psi/OCExpression;Lcom/jetbrains/cidr/lang/psi/OCExpression;)Z
        //    86: ifne            111
        //    89: goto            96
        //    92: invokestatic    com/jetbrains/cidr/lang/inspections/OCSimplifyInspection$SimplifyVisitor.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //    95: athrow         
        //    96: aload           4
        //    98: astore_1       
        //    99: aload           4
        //   101: invokeinterface com/intellij/psi/PsiElement.getParent:()Lcom/intellij/psi/PsiElement;
        //   106: astore          4
        //   108: goto            52
        //   111: aload_1        
        //   112: instanceof      Lcom/jetbrains/cidr/lang/psi/OCExpression;
        //   115: ifeq            173
        //   118: aload_2        
        //   119: instanceof      Lcom/jetbrains/cidr/lang/psi/OCExpression;
        //   122: ifeq            173
        //   125: goto            132
        //   128: invokestatic    com/jetbrains/cidr/lang/inspections/OCSimplifyInspection$SimplifyVisitor.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   131: athrow         
        //   132: aload_1        
        //   133: checkcast       Lcom/jetbrains/cidr/lang/psi/OCExpression;
        //   136: aload_2        
        //   137: checkcast       Lcom/jetbrains/cidr/lang/psi/OCExpression;
        //   140: invokestatic    com/jetbrains/cidr/lang/util/OCParenthesesUtils.isParenthesesNeededInReplacing:(Lcom/jetbrains/cidr/lang/psi/OCExpression;Lcom/jetbrains/cidr/lang/psi/OCExpression;)Z
        //   143: ifeq            173
        //   146: goto            153
        //   149: invokestatic    com/jetbrains/cidr/lang/inspections/OCSimplifyInspection$SimplifyVisitor.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   152: athrow         
        //   153: aload_0        
        //   154: aload_1        
        //   155: aload_2        
        //   156: checkcast       Lcom/jetbrains/cidr/lang/psi/OCExpression;
        //   159: invokestatic    com/jetbrains/cidr/lang/util/OCParenthesesUtils.appendParentheses:(Lcom/jetbrains/cidr/lang/psi/OCExpression;)Lcom/jetbrains/cidr/lang/psi/OCParenthesizedExpression;
        //   162: aload_3        
        //   163: invokevirtual   com/jetbrains/cidr/lang/inspections/OCSimplifyInspection$SimplifyVisitor.simplify:(Lcom/intellij/psi/PsiElement;Lcom/intellij/psi/PsiElement;Lcom/jetbrains/cidr/lang/inspections/OCSimplifyInspection$Simplifier;)V
        //   166: goto            180
        //   169: invokestatic    com/jetbrains/cidr/lang/inspections/OCSimplifyInspection$SimplifyVisitor.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   172: athrow         
        //   173: aload_0        
        //   174: aload_1        
        //   175: aload_2        
        //   176: aload_3        
        //   177: invokevirtual   com/jetbrains/cidr/lang/inspections/OCSimplifyInspection$SimplifyVisitor.simplify:(Lcom/intellij/psi/PsiElement;Lcom/intellij/psi/PsiElement;Lcom/jetbrains/cidr/lang/inspections/OCSimplifyInspection$Simplifier;)V
        //   180: return         
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                                
        //  -----  -----  -----  -----  ------------------------------------
        //  0      40     40     44     Ljava/lang/IllegalArgumentException;
        //  52     67     70     74     Ljava/lang/IllegalArgumentException;
        //  60     89     92     96     Ljava/lang/IllegalArgumentException;
        //  111    125    128    132    Ljava/lang/IllegalArgumentException;
        //  118    146    149    153    Ljava/lang/IllegalArgumentException;
        //  132    169    169    173    Ljava/lang/IllegalArgumentException;
        // 
        // The error that occurred was:
        // 
        // java.lang.IllegalStateException: Expression is linked from several locations: Label_0132:
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
    
    protected abstract void simplify(@NotNull final PsiElement p0, @Nullable final PsiElement p1, final Simplifier p2);
    
    private static IllegalArgumentException a(final IllegalArgumentException ex) {
        return ex;
    }
}
