// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.lang.intentions;

import com.jetbrains.cidr.lang.parser.OCTokenTypes;
import com.jetbrains.cidr.lang.psi.OCParenthesizedExpression;
import com.jetbrains.cidr.lang.util.OCElementFactory;
import com.jetbrains.cidr.lang.util.OCParenthesesUtils;
import com.jetbrains.cidr.lang.psi.OCUnaryExpression;
import org.jetbrains.annotations.Nullable;
import com.jetbrains.cidr.lang.util.OCComparisonUtils;
import com.jetbrains.cidr.lang.parser.OCElementType;
import com.jetbrains.cidr.lang.psi.OCExpression;
import com.intellij.psi.util.PsiTreeUtil;
import com.jetbrains.cidr.lang.psi.OCBinaryExpression;
import com.intellij.util.IncorrectOperationException;
import com.intellij.psi.PsiElement;
import com.intellij.openapi.editor.Editor;
import org.jetbrains.annotations.NotNull;
import com.intellij.openapi.project.Project;
import com.intellij.codeInsight.intention.PsiElementBaseIntentionAction;

public class OCDeMorganIntentionAction extends PsiElementBaseIntentionAction
{
    public boolean isAvailable(@NotNull final Project p0, final Editor p1, @NotNull final PsiElement p2) {
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
        //    18: ldc             "project"
        //    20: aastore        
        //    21: dup            
        //    22: ldc             1
        //    24: ldc             "com/jetbrains/cidr/lang/intentions/OCDeMorganIntentionAction"
        //    26: aastore        
        //    27: dup            
        //    28: ldc             2
        //    30: ldc             "isAvailable"
        //    32: aastore        
        //    33: invokestatic    java/lang/String.format:(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
        //    36: invokespecial   java/lang/IllegalArgumentException.<init>:(Ljava/lang/String;)V
        //    39: athrow         
        //    40: invokestatic    com/jetbrains/cidr/lang/intentions/OCDeMorganIntentionAction.a:(Lcom/intellij/util/IncorrectOperationException;)Lcom/intellij/util/IncorrectOperationException;
        //    43: athrow         
        //    44: aload_3        
        //    45: ifnonnull       88
        //    48: new             Ljava/lang/IllegalArgumentException;
        //    51: dup            
        //    52: ldc             "Argument for @NotNull parameter '%s' of %s.%s must not be null"
        //    54: ldc             3
        //    56: anewarray       Ljava/lang/Object;
        //    59: dup            
        //    60: ldc             0
        //    62: ldc             "element"
        //    64: aastore        
        //    65: dup            
        //    66: ldc             1
        //    68: ldc             "com/jetbrains/cidr/lang/intentions/OCDeMorganIntentionAction"
        //    70: aastore        
        //    71: dup            
        //    72: ldc             2
        //    74: ldc             "isAvailable"
        //    76: aastore        
        //    77: invokestatic    java/lang/String.format:(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
        //    80: invokespecial   java/lang/IllegalArgumentException.<init>:(Ljava/lang/String;)V
        //    83: athrow         
        //    84: invokestatic    com/jetbrains/cidr/lang/intentions/OCDeMorganIntentionAction.a:(Lcom/intellij/util/IncorrectOperationException;)Lcom/intellij/util/IncorrectOperationException;
        //    87: athrow         
        //    88: aload_3        
        //    89: ldc             Lcom/jetbrains/cidr/lang/psi/OCBinaryExpression;.class
        //    91: invokestatic    com/intellij/psi/util/PsiTreeUtil.getParentOfType:(Lcom/intellij/psi/PsiElement;Ljava/lang/Class;)Lcom/intellij/psi/PsiElement;
        //    94: checkcast       Lcom/jetbrains/cidr/lang/psi/OCBinaryExpression;
        //    97: astore          4
        //    99: aload           4
        //   101: ifnull          178
        //   104: aload           4
        //   106: invokeinterface com/jetbrains/cidr/lang/psi/OCBinaryExpression.getRight:()Lcom/jetbrains/cidr/lang/psi/OCExpression;
        //   111: ifnull          178
        //   114: goto            121
        //   117: invokestatic    com/jetbrains/cidr/lang/intentions/OCDeMorganIntentionAction.a:(Lcom/intellij/util/IncorrectOperationException;)Lcom/intellij/util/IncorrectOperationException;
        //   120: athrow         
        //   121: aload           4
        //   123: invokeinterface com/jetbrains/cidr/lang/psi/OCBinaryExpression.getOperationSign:()Lcom/jetbrains/cidr/lang/parser/OCElementType;
        //   128: getstatic       com/jetbrains/cidr/lang/parser/OCTokenTypes.ANDAND:Lcom/jetbrains/cidr/lang/parser/OCPunctuatorElementType;
        //   131: if_acmpne       153
        //   134: goto            141
        //   137: invokestatic    com/jetbrains/cidr/lang/intentions/OCDeMorganIntentionAction.a:(Lcom/intellij/util/IncorrectOperationException;)Lcom/intellij/util/IncorrectOperationException;
        //   140: athrow         
        //   141: aload_0        
        //   142: ldc             "Replace '&&' with '||'"
        //   144: invokevirtual   com/jetbrains/cidr/lang/intentions/OCDeMorganIntentionAction.setText:(Ljava/lang/String;)V
        //   147: iconst_1       
        //   148: ireturn        
        //   149: invokestatic    com/jetbrains/cidr/lang/intentions/OCDeMorganIntentionAction.a:(Lcom/intellij/util/IncorrectOperationException;)Lcom/intellij/util/IncorrectOperationException;
        //   152: athrow         
        //   153: aload           4
        //   155: invokeinterface com/jetbrains/cidr/lang/psi/OCBinaryExpression.getOperationSign:()Lcom/jetbrains/cidr/lang/parser/OCElementType;
        //   160: getstatic       com/jetbrains/cidr/lang/parser/OCTokenTypes.OROR:Lcom/jetbrains/cidr/lang/parser/OCPunctuatorElementType;
        //   163: if_acmpne       178
        //   166: aload_0        
        //   167: ldc             "Replace '||' with '&&'"
        //   169: invokevirtual   com/jetbrains/cidr/lang/intentions/OCDeMorganIntentionAction.setText:(Ljava/lang/String;)V
        //   172: iconst_1       
        //   173: ireturn        
        //   174: invokestatic    com/jetbrains/cidr/lang/intentions/OCDeMorganIntentionAction.a:(Lcom/intellij/util/IncorrectOperationException;)Lcom/intellij/util/IncorrectOperationException;
        //   177: athrow         
        //   178: iconst_0       
        //   179: ireturn        
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                                           
        //  -----  -----  -----  -----  -----------------------------------------------
        //  0      40     40     44     Lcom/intellij/util/IncorrectOperationException;
        //  44     84     84     88     Lcom/intellij/util/IncorrectOperationException;
        //  99     114    117    121    Lcom/intellij/util/IncorrectOperationException;
        //  104    134    137    141    Lcom/intellij/util/IncorrectOperationException;
        //  121    149    149    153    Lcom/intellij/util/IncorrectOperationException;
        //  153    174    174    178    Lcom/intellij/util/IncorrectOperationException;
        // 
        // The error that occurred was:
        // 
        // java.lang.IllegalStateException: Expression is linked from several locations: Label_0121:
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
    
    @NotNull
    public String getFamilyName() {
        String s;
        try {
            s = "DeMorgan Law";
            if (s == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/intentions/OCDeMorganIntentionAction", "getFamilyName"));
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
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "project", "com/jetbrains/cidr/lang/intentions/OCDeMorganIntentionAction", "invoke"));
            }
        }
        catch (IncorrectOperationException ex) {
            throw a(ex);
        }
        try {
            if (psiElement == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "element", "com/jetbrains/cidr/lang/intentions/OCDeMorganIntentionAction", "invoke"));
            }
        }
        catch (IncorrectOperationException ex2) {
            throw a(ex2);
        }
        OCBinaryExpression ocBinaryExpression = (OCBinaryExpression)PsiTreeUtil.getParentOfType(psiElement, (Class)OCBinaryExpression.class);
        if (ocBinaryExpression != null) {
            final OCElementType operationSign = ocBinaryExpression.getOperationSign();
            for (PsiElement psiElement2 = ocBinaryExpression.getParent(); a(psiElement2, operationSign); psiElement2 = ocBinaryExpression.getParent()) {
                ocBinaryExpression = (OCBinaryExpression)psiElement2;
            }
            final String a = a(ocBinaryExpression, operationSign);
            try {
                if (a != null) {
                    a(a, ocBinaryExpression);
                }
            }
            catch (IncorrectOperationException ex3) {
                throw a(ex3);
            }
        }
    }
    
    @Nullable
    private static String a(@NotNull final OCBinaryExpression ocBinaryExpression, @NotNull final OCElementType ocElementType) {
        try {
            if (ocBinaryExpression == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "expression", "com/jetbrains/cidr/lang/intentions/OCDeMorganIntentionAction", "convertConjunctionExpression"));
            }
        }
        catch (IncorrectOperationException ex) {
            throw a(ex);
        }
        try {
            if (ocElementType == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "sign", "com/jetbrains/cidr/lang/intentions/OCDeMorganIntentionAction", "convertConjunctionExpression"));
            }
        }
        catch (IncorrectOperationException ex2) {
            throw a(ex2);
        }
        final String a = a(ocBinaryExpression.getLeft(), ocElementType);
        final String a2 = a(ocBinaryExpression.getRight(), ocElementType);
        Label_0125: {
            try {
                if (a == null) {
                    break Label_0125;
                }
                final String s = a2;
                if (s == null) {
                    break Label_0125;
                }
                return a + OCComparisonUtils.getInversedOperator(ocElementType) + a2;
            }
            catch (IncorrectOperationException ex3) {
                throw a(ex3);
            }
            try {
                final String s = a2;
                if (s == null) {
                    return null;
                }
            }
            catch (IncorrectOperationException ex4) {
                throw a(ex4);
            }
        }
        return a + OCComparisonUtils.getInversedOperator(ocElementType) + a2;
    }
    
    private static String a(@Nullable final OCExpression ocExpression, @NotNull final OCElementType ocElementType) {
        try {
            if (ocElementType == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "sign", "com/jetbrains/cidr/lang/intentions/OCDeMorganIntentionAction", "convertExpressionPart"));
            }
        }
        catch (IncorrectOperationException ex) {
            throw a(ex);
        }
        try {
            if (ocExpression == null) {
                return null;
            }
        }
        catch (IncorrectOperationException ex2) {
            throw a(ex2);
        }
        try {
            if (a((PsiElement)ocExpression, ocElementType)) {
                return a((OCBinaryExpression)ocExpression, ocElementType);
            }
        }
        catch (IncorrectOperationException ex3) {
            throw a(ex3);
        }
        return c(ocExpression);
    }
    
    @Nullable
    private static String c(@NotNull final OCExpression ocExpression) {
        try {
            if (ocExpression == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "condition", "com/jetbrains/cidr/lang/intentions/OCDeMorganIntentionAction", "convertLeafExpression"));
            }
        }
        catch (IncorrectOperationException ex) {
            throw a(ex);
        }
        if (b(ocExpression)) {
            final OCExpression a = a((OCUnaryExpression)ocExpression);
            try {
                if (a == null) {
                    return "";
                }
            }
            catch (IncorrectOperationException ex2) {
                throw a(ex2);
            }
            try {
                if (OCParenthesesUtils.getPrecedence(a, false) > 14) {
                    return '(' + a.getText() + ')';
                }
            }
            catch (IncorrectOperationException ex3) {
                throw a(ex3);
            }
            return a.getText();
        }
        if (OCComparisonUtils.isComparison(ocExpression)) {
            final OCBinaryExpression ocBinaryExpression = (OCBinaryExpression)ocExpression;
            final String negatedComparison = OCComparisonUtils.getNegatedComparison(ocBinaryExpression.getOperationSign());
            final OCExpression left = ocBinaryExpression.getLeft();
            final OCExpression right = ocBinaryExpression.getRight();
            Label_0178: {
                try {
                    if (left == null) {
                        return null;
                    }
                    final OCExpression ocExpression2 = right;
                    if (ocExpression2 != null) {
                        break Label_0178;
                    }
                    return null;
                }
                catch (IncorrectOperationException ex4) {
                    throw a(ex4);
                }
                try {
                    final OCExpression ocExpression2 = right;
                    if (ocExpression2 != null) {
                        return left.getText() + negatedComparison + right.getText();
                    }
                }
                catch (IncorrectOperationException ex5) {
                    throw a(ex5);
                }
            }
            return null;
        }
        try {
            if (OCParenthesesUtils.getPrecedence(ocExpression, false) > 3) {
                return "!(" + ocExpression.getText() + ')';
            }
        }
        catch (IncorrectOperationException ex6) {
            throw a(ex6);
        }
        return '!' + ocExpression.getText();
    }
    
    private static boolean a(@Nullable final PsiElement psiElement, @NotNull final OCElementType ocElementType) {
        try {
            if (ocElementType == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "sign", "com/jetbrains/cidr/lang/intentions/OCDeMorganIntentionAction", "isConjunctionExpression"));
            }
        }
        catch (IncorrectOperationException ex) {
            throw a(ex);
        }
        return psiElement instanceof OCBinaryExpression && ((OCBinaryExpression)psiElement).getOperationSign().equals(ocElementType);
    }
    
    private static void a(@NotNull String string, @NotNull final OCExpression ocExpression) throws IncorrectOperationException {
        try {
            if (string == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "newExpressionStr", "com/jetbrains/cidr/lang/intentions/OCDeMorganIntentionAction", "replaceExpression"));
            }
        }
        catch (IncorrectOperationException ex) {
            throw a(ex);
        }
        try {
            if (ocExpression == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "oldExpression", "com/jetbrains/cidr/lang/intentions/OCDeMorganIntentionAction", "replaceExpression"));
            }
        }
        catch (IncorrectOperationException ex2) {
            throw a(ex2);
        }
        final OCExpression a = a(ocExpression);
        OCExpression ocExpression2 = null;
        Label_0106: {
            try {
                if (a != null) {
                    ocExpression2 = a;
                    break Label_0106;
                }
            }
            catch (IncorrectOperationException ex3) {
                throw a(ex3);
            }
            ocExpression2 = ocExpression;
        }
        final OCExpression ocExpression3 = ocExpression2;
        if (a == null) {
            string = "!(" + string + ")";
        }
        final OCExpression expressionFromText = OCElementFactory.expressionFromText(string, (PsiElement)ocExpression);
        try {
            if (expressionFromText == null) {
                return;
            }
        }
        catch (IncorrectOperationException ex4) {
            throw a(ex4);
        }
        final boolean a2 = a(expressionFromText, ocExpression3);
        OCExpression ocExpression4 = null;
        OCExpression appendParentheses = null;
        Label_0181: {
            try {
                ocExpression4 = ocExpression3;
                if (a2) {
                    appendParentheses = OCParenthesesUtils.appendParentheses(expressionFromText);
                    break Label_0181;
                }
            }
            catch (IncorrectOperationException ex5) {
                throw a(ex5);
            }
            appendParentheses = expressionFromText;
        }
        ocExpression4.replace((PsiElement)appendParentheses);
    }
    
    @Nullable
    private static OCExpression a(@NotNull final OCExpression ocExpression) {
        try {
            if (ocExpression == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "expression", "com/jetbrains/cidr/lang/intentions/OCDeMorganIntentionAction", "findFirstNegatedParent"));
            }
        }
        catch (IncorrectOperationException ex) {
            throw a(ex);
        }
        OCExpression ocExpression2;
        for (ocExpression2 = ocExpression; ocExpression2.getParent() instanceof OCParenthesizedExpression; ocExpression2 = (OCExpression)ocExpression2.getParent()) {}
        if (ocExpression2.getParent() instanceof OCUnaryExpression) {
            final OCUnaryExpression ocUnaryExpression = (OCUnaryExpression)ocExpression2.getParent();
            try {
                if (OCTokenTypes.EXCL == ocUnaryExpression.getOperationSign()) {
                    return ocUnaryExpression;
                }
            }
            catch (IncorrectOperationException ex2) {
                throw a(ex2);
            }
        }
        return null;
    }
    
    private static boolean b(@Nullable final OCExpression ocExpression) {
        if (ocExpression instanceof OCUnaryExpression) {
            final OCUnaryExpression ocUnaryExpression = (OCUnaryExpression)ocExpression;
            try {
                if (ocUnaryExpression.getOperationSign() == OCTokenTypes.EXCL) {
                    return true;
                }
            }
            catch (IncorrectOperationException ex) {
                throw a(ex);
            }
            return false;
        }
        return false;
    }
    
    @Nullable
    private static OCExpression a(@NotNull final OCUnaryExpression ocUnaryExpression) {
        try {
            if (ocUnaryExpression == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "expression", "com/jetbrains/cidr/lang/intentions/OCDeMorganIntentionAction", "stripNegation"));
            }
        }
        catch (IncorrectOperationException ex) {
            throw a(ex);
        }
        final OCExpression operand = ocUnaryExpression.getOperand();
        try {
            if (operand != null) {
                return OCParenthesesUtils.diveIntoParentheses(operand);
            }
        }
        catch (IncorrectOperationException ex2) {
            throw a(ex2);
        }
        return null;
    }
    
    private static boolean a(@NotNull final OCExpression ocExpression, @NotNull final OCExpression ocExpression2) {
        try {
            if (ocExpression == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "newExpression", "com/jetbrains/cidr/lang/intentions/OCDeMorganIntentionAction", "parenthesesAreRequired"));
            }
        }
        catch (IncorrectOperationException ex) {
            throw a(ex);
        }
        try {
            if (ocExpression2 == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "sourceExpression", "com/jetbrains/cidr/lang/intentions/OCDeMorganIntentionAction", "parenthesesAreRequired"));
            }
        }
        catch (IncorrectOperationException ex2) {
            throw a(ex2);
        }
        try {
            if (ocExpression instanceof OCParenthesizedExpression) {
                return false;
            }
        }
        catch (IncorrectOperationException ex3) {
            throw a(ex3);
        }
        final PsiElement parent = ocExpression2.getParent();
        try {
            if (!(parent instanceof OCExpression)) {
                return false;
            }
        }
        catch (IncorrectOperationException ex4) {
            throw a(ex4);
        }
        final OCExpression ocExpression3 = (OCExpression)parent;
        final int precedence = OCParenthesesUtils.getPrecedence(ocExpression, false);
        final int precedence2 = OCParenthesesUtils.getPrecedence(ocExpression3, false);
        Label_0163: {
            try {
                if (precedence <= 0) {
                    return false;
                }
                final int n = precedence2;
                if (n <= 0) {
                    return false;
                }
                break Label_0163;
            }
            catch (IncorrectOperationException ex5) {
                throw a(ex5);
            }
            try {
                final int n = precedence2;
                if (n <= 0) {
                    return false;
                }
            }
            catch (IncorrectOperationException ex6) {
                throw a(ex6);
            }
            try {
                if (precedence > precedence2) {
                    return true;
                }
            }
            catch (IncorrectOperationException ex7) {
                throw a(ex7);
            }
        }
        return false;
    }
    
    private static IncorrectOperationException a(final IncorrectOperationException ex) {
        return ex;
    }
}
