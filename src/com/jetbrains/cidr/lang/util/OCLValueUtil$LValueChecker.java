// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.lang.util;

import com.jetbrains.cidr.lang.psi.OCConditionalExpression;
import com.jetbrains.cidr.lang.psi.OCUnaryExpression;
import com.jetbrains.cidr.lang.parser.OCPunctuatorElementType;
import com.jetbrains.cidr.lang.parser.OCElementType;
import com.intellij.psi.PsiElement;
import com.jetbrains.cidr.lang.psi.OCPrefixExpression;
import com.jetbrains.cidr.lang.parser.OCTokenTypes;
import com.jetbrains.cidr.lang.psi.OCPostfixExpression;
import com.jetbrains.cidr.lang.psi.OCAssignmentExpression;
import org.jetbrains.annotations.NotNull;
import com.jetbrains.cidr.lang.psi.OCExpression;

private static class LValueChecker
{
    private boolean myLValue;
    private boolean myAssignmentLHS;
    
    public LValueChecker() {
        this.myLValue = false;
        this.myAssignmentLHS = false;
    }
    
    public boolean isLValue() {
        return this.myLValue;
    }
    
    public boolean isAssignmentLHS() {
        return this.myAssignmentLHS;
    }
    
    public void process(@NotNull OCExpression b) {
        try {
            if (b == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "expression", "com/jetbrains/cidr/lang/util/OCLValueUtil$LValueChecker", "process"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        b = this.b(b);
        final PsiElement parent = b.getParent();
        Label_0205: {
            Label_0100: {
                LValueChecker lValueChecker = null;
                boolean myAssignmentLHS = false;
                Label_0094: {
                    Label_0085: {
                        try {
                            if (!(parent instanceof OCAssignmentExpression)) {
                                break Label_0100;
                            }
                            lValueChecker = this;
                            final PsiElement psiElement = parent;
                            final OCAssignmentExpression ocAssignmentExpression = (OCAssignmentExpression)psiElement;
                            final OCExpression ocExpression = ocAssignmentExpression.getReceiverExpression();
                            final OCExpression ocExpression2 = b;
                            if (ocExpression == ocExpression2) {
                                break Label_0085;
                            }
                            break Label_0085;
                        }
                        catch (IllegalArgumentException ex2) {
                            throw a(ex2);
                        }
                        try {
                            lValueChecker = this;
                            final PsiElement psiElement = parent;
                            final OCAssignmentExpression ocAssignmentExpression = (OCAssignmentExpression)psiElement;
                            final OCExpression ocExpression = ocAssignmentExpression.getReceiverExpression();
                            final OCExpression ocExpression2 = b;
                            if (ocExpression == ocExpression2) {
                                myAssignmentLHS = true;
                                break Label_0094;
                            }
                        }
                        catch (IllegalArgumentException ex3) {
                            throw a(ex3);
                        }
                    }
                    myAssignmentLHS = false;
                }
                lValueChecker.myAssignmentLHS = myAssignmentLHS;
                break Label_0205;
            }
            if (parent instanceof OCPostfixExpression) {
                final OCElementType operationSign = ((OCPostfixExpression)parent).getOperationSign();
                boolean myAssignmentLHS2 = false;
                Label_0148: {
                    Label_0139: {
                        try {
                            if (operationSign == OCTokenTypes.PLUSPLUS) {
                                break Label_0139;
                            }
                            final OCPunctuatorElementType ocPunctuatorElementType = (OCPunctuatorElementType)operationSign;
                            final OCPunctuatorElementType ocPunctuatorElementType2 = OCTokenTypes.MINUSMINUS;
                            if (ocPunctuatorElementType == ocPunctuatorElementType2) {
                                break Label_0139;
                            }
                            break Label_0139;
                        }
                        catch (IllegalArgumentException ex4) {
                            throw a(ex4);
                        }
                        try {
                            final OCPunctuatorElementType ocPunctuatorElementType = (OCPunctuatorElementType)operationSign;
                            final OCPunctuatorElementType ocPunctuatorElementType2 = OCTokenTypes.MINUSMINUS;
                            if (ocPunctuatorElementType == ocPunctuatorElementType2) {
                                myAssignmentLHS2 = true;
                                break Label_0148;
                            }
                        }
                        catch (IllegalArgumentException ex5) {
                            throw a(ex5);
                        }
                    }
                    myAssignmentLHS2 = false;
                }
                this.myAssignmentLHS = myAssignmentLHS2;
            }
            else if (parent instanceof OCPrefixExpression) {
                final OCElementType operationSign2 = ((OCPrefixExpression)parent).getOperationSign();
                boolean myAssignmentLHS3 = false;
                Label_0202: {
                    Label_0193: {
                        try {
                            if (operationSign2 == OCTokenTypes.PLUSPLUS) {
                                break Label_0193;
                            }
                            final OCPunctuatorElementType ocPunctuatorElementType3 = (OCPunctuatorElementType)operationSign2;
                            final OCPunctuatorElementType ocPunctuatorElementType4 = OCTokenTypes.MINUSMINUS;
                            if (ocPunctuatorElementType3 == ocPunctuatorElementType4) {
                                break Label_0193;
                            }
                            break Label_0193;
                        }
                        catch (IllegalArgumentException ex6) {
                            throw a(ex6);
                        }
                        try {
                            final OCPunctuatorElementType ocPunctuatorElementType3 = (OCPunctuatorElementType)operationSign2;
                            final OCPunctuatorElementType ocPunctuatorElementType4 = OCTokenTypes.MINUSMINUS;
                            if (ocPunctuatorElementType3 == ocPunctuatorElementType4) {
                                myAssignmentLHS3 = true;
                                break Label_0202;
                            }
                        }
                        catch (IllegalArgumentException ex7) {
                            throw a(ex7);
                        }
                    }
                    myAssignmentLHS3 = false;
                }
                this.myAssignmentLHS = myAssignmentLHS3;
            }
        }
        this.myLValue |= this.myAssignmentLHS;
    }
    
    @NotNull
    private OCExpression b(@NotNull final OCExpression p0) {
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
        //    18: ldc             "expression"
        //    20: aastore        
        //    21: dup            
        //    22: ldc             1
        //    24: ldc             "com/jetbrains/cidr/lang/util/OCLValueUtil$LValueChecker"
        //    26: aastore        
        //    27: dup            
        //    28: ldc             2
        //    30: ldc             "getTopmostLValueParent"
        //    32: aastore        
        //    33: invokestatic    java/lang/String.format:(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
        //    36: invokespecial   java/lang/IllegalArgumentException.<init>:(Ljava/lang/String;)V
        //    39: athrow         
        //    40: invokestatic    com/jetbrains/cidr/lang/util/OCLValueUtil$LValueChecker.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //    43: athrow         
        //    44: aload_1        
        //    45: invokestatic    com/jetbrains/cidr/lang/util/OCParenthesesUtils.topmostParenthesized:(Lcom/jetbrains/cidr/lang/psi/OCExpression;)Lcom/jetbrains/cidr/lang/psi/OCExpression;
        //    48: astore_2       
        //    49: aload_2        
        //    50: invokeinterface com/jetbrains/cidr/lang/psi/OCExpression.getParent:()Lcom/intellij/psi/PsiElement;
        //    55: astore_3       
        //    56: aload_3        
        //    57: instanceof      Lcom/jetbrains/cidr/lang/psi/OCExpression;
        //    60: ifeq            167
        //    63: aload_3        
        //    64: checkcast       Lcom/jetbrains/cidr/lang/psi/OCExpression;
        //    67: astore          4
        //    69: aload           4
        //    71: invokestatic    com/jetbrains/cidr/lang/util/OCLValueUtil$LValueChecker.c:(Lcom/jetbrains/cidr/lang/psi/OCExpression;)Z
        //    74: ifne            115
        //    77: aload           4
        //    79: invokestatic    com/jetbrains/cidr/lang/util/OCLValueUtil$LValueChecker.a:(Lcom/jetbrains/cidr/lang/psi/OCExpression;)Z
        //    82: ifne            115
        //    85: goto            92
        //    88: invokestatic    com/jetbrains/cidr/lang/util/OCLValueUtil$LValueChecker.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //    91: athrow         
        //    92: aload_2        
        //    93: aload           4
        //    95: invokestatic    com/jetbrains/cidr/lang/util/OCLValueUtil$LValueChecker.a:(Lcom/jetbrains/cidr/lang/psi/OCExpression;Lcom/jetbrains/cidr/lang/psi/OCExpression;)Z
        //    98: ifne            115
        //   101: goto            108
        //   104: invokestatic    com/jetbrains/cidr/lang/util/OCLValueUtil$LValueChecker.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   107: athrow         
        //   108: goto            167
        //   111: invokestatic    com/jetbrains/cidr/lang/util/OCLValueUtil$LValueChecker.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   114: athrow         
        //   115: aload_0        
        //   116: aload           4
        //   118: invokestatic    com/jetbrains/cidr/lang/util/OCLValueUtil$LValueChecker.c:(Lcom/jetbrains/cidr/lang/psi/OCExpression;)Z
        //   121: ifne            139
        //   124: aload           4
        //   126: invokestatic    com/jetbrains/cidr/lang/util/OCLValueUtil$LValueChecker.a:(Lcom/jetbrains/cidr/lang/psi/OCExpression;)Z
        //   129: ifeq            147
        //   132: goto            139
        //   135: invokestatic    com/jetbrains/cidr/lang/util/OCLValueUtil$LValueChecker.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   138: athrow         
        //   139: iconst_1       
        //   140: goto            148
        //   143: invokestatic    com/jetbrains/cidr/lang/util/OCLValueUtil$LValueChecker.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   146: athrow         
        //   147: iconst_0       
        //   148: putfield        com/jetbrains/cidr/lang/util/OCLValueUtil$LValueChecker.myLValue:Z
        //   151: aload           4
        //   153: invokestatic    com/jetbrains/cidr/lang/util/OCParenthesesUtils.topmostParenthesized:(Lcom/jetbrains/cidr/lang/psi/OCExpression;)Lcom/jetbrains/cidr/lang/psi/OCExpression;
        //   156: astore_2       
        //   157: aload_2        
        //   158: invokeinterface com/jetbrains/cidr/lang/psi/OCExpression.getParent:()Lcom/intellij/psi/PsiElement;
        //   163: astore_3       
        //   164: goto            56
        //   167: aload_2        
        //   168: dup            
        //   169: ifnonnull       206
        //   172: new             Ljava/lang/IllegalStateException;
        //   175: dup            
        //   176: ldc             "@NotNull method %s.%s must not return null"
        //   178: ldc             2
        //   180: anewarray       Ljava/lang/Object;
        //   183: dup            
        //   184: ldc             0
        //   186: ldc             "com/jetbrains/cidr/lang/util/OCLValueUtil$LValueChecker"
        //   188: aastore        
        //   189: dup            
        //   190: ldc             1
        //   192: ldc             "getTopmostLValueParent"
        //   194: aastore        
        //   195: invokestatic    java/lang/String.format:(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
        //   198: invokespecial   java/lang/IllegalStateException.<init>:(Ljava/lang/String;)V
        //   201: athrow         
        //   202: invokestatic    com/jetbrains/cidr/lang/util/OCLValueUtil$LValueChecker.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   205: athrow         
        //   206: areturn        
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                                
        //  -----  -----  -----  -----  ------------------------------------
        //  0      40     40     44     Ljava/lang/IllegalArgumentException;
        //  69     85     88     92     Ljava/lang/IllegalArgumentException;
        //  77     101    104    108    Ljava/lang/IllegalArgumentException;
        //  92     111    111    115    Ljava/lang/IllegalArgumentException;
        //  115    132    135    139    Ljava/lang/IllegalArgumentException;
        //  124    143    143    147    Ljava/lang/IllegalArgumentException;
        //  167    202    202    206    Ljava/lang/IllegalArgumentException;
        // 
        // The error that occurred was:
        // 
        // java.lang.IllegalStateException: Expression is linked from several locations: Label_0092:
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
    
    private static boolean c(@NotNull final OCExpression ocExpression) {
        try {
            if (ocExpression == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "expression", "com/jetbrains/cidr/lang/util/OCLValueUtil$LValueChecker", "isAddressOperator"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        Label_0070: {
            try {
                if (!(ocExpression instanceof OCUnaryExpression)) {
                    return false;
                }
                final OCExpression ocExpression2 = ocExpression;
                final OCUnaryExpression ocUnaryExpression = (OCUnaryExpression)ocExpression2;
                final boolean b = ocUnaryExpression.isGetAddress();
                if (b) {
                    break Label_0070;
                }
                return false;
            }
            catch (IllegalArgumentException ex2) {
                throw a(ex2);
            }
            try {
                final OCExpression ocExpression2 = ocExpression;
                final OCUnaryExpression ocUnaryExpression = (OCUnaryExpression)ocExpression2;
                final boolean b = ocUnaryExpression.isGetAddress();
                if (b) {
                    return true;
                }
            }
            catch (IllegalArgumentException ex3) {
                throw a(ex3);
            }
        }
        return false;
    }
    
    private static boolean a(@NotNull final OCExpression ocExpression) {
        try {
            if (ocExpression == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "expression", "com/jetbrains/cidr/lang/util/OCLValueUtil$LValueChecker", "isDereference"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        Label_0073: {
            try {
                if (!(ocExpression instanceof OCUnaryExpression)) {
                    return false;
                }
                final OCExpression ocExpression2 = ocExpression;
                final OCUnaryExpression ocUnaryExpression = (OCUnaryExpression)ocExpression2;
                final OCElementType ocElementType = ocUnaryExpression.getOperationSign();
                final OCPunctuatorElementType ocPunctuatorElementType = OCTokenTypes.MUL;
                if (ocElementType == ocPunctuatorElementType) {
                    break Label_0073;
                }
                return false;
            }
            catch (IllegalArgumentException ex2) {
                throw a(ex2);
            }
            try {
                final OCExpression ocExpression2 = ocExpression;
                final OCUnaryExpression ocUnaryExpression = (OCUnaryExpression)ocExpression2;
                final OCElementType ocElementType = ocUnaryExpression.getOperationSign();
                final OCPunctuatorElementType ocPunctuatorElementType = OCTokenTypes.MUL;
                if (ocElementType == ocPunctuatorElementType) {
                    return true;
                }
            }
            catch (IllegalArgumentException ex3) {
                throw a(ex3);
            }
        }
        return false;
    }
    
    private static boolean a(@NotNull final OCExpression ocExpression, @NotNull final OCExpression ocExpression2) {
        try {
            if (ocExpression == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "expression", "com/jetbrains/cidr/lang/util/OCLValueUtil$LValueChecker", "isBodyOfConditionalExpr"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        try {
            if (ocExpression2 == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "parentExpression", "com/jetbrains/cidr/lang/util/OCLValueUtil$LValueChecker", "isBodyOfConditionalExpr"));
            }
        }
        catch (IllegalArgumentException ex2) {
            throw a(ex2);
        }
        try {
            if (!(ocExpression2 instanceof OCConditionalExpression)) {
                return false;
            }
        }
        catch (IllegalArgumentException ex3) {
            throw a(ex3);
        }
        final OCConditionalExpression ocConditionalExpression = (OCConditionalExpression)ocExpression2;
        Label_0134: {
            try {
                if (ocConditionalExpression.getPositiveExpression(true) == ocExpression) {
                    break Label_0134;
                }
                final OCConditionalExpression ocConditionalExpression2 = ocConditionalExpression;
                final OCExpression ocExpression3 = ocConditionalExpression2.getNegativeExpression();
                final OCExpression ocExpression4 = ocExpression;
                if (ocExpression3 == ocExpression4) {
                    break Label_0134;
                }
                return false;
            }
            catch (IllegalArgumentException ex4) {
                throw a(ex4);
            }
            try {
                final OCConditionalExpression ocConditionalExpression2 = ocConditionalExpression;
                final OCExpression ocExpression3 = ocConditionalExpression2.getNegativeExpression();
                final OCExpression ocExpression4 = ocExpression;
                if (ocExpression3 == ocExpression4) {
                    return true;
                }
            }
            catch (IllegalArgumentException ex5) {
                throw a(ex5);
            }
        }
        return false;
    }
    
    private static IllegalArgumentException a(final IllegalArgumentException ex) {
        return ex;
    }
}
