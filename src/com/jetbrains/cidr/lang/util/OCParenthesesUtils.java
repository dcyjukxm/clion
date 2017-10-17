// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.lang.util;

import java.util.HashMap;
import com.jetbrains.cidr.lang.parser.OCPunctuatorElementType;
import com.jetbrains.cidr.lang.psi.OCUnaryExpression;
import com.jetbrains.cidr.lang.symbols.OCResolveContext;
import org.jetbrains.annotations.Contract;
import com.jetbrains.cidr.lang.types.OCTypeOwner;
import com.intellij.util.ArrayUtil;
import com.jetbrains.cidr.lang.psi.OCCastKind;
import com.jetbrains.cidr.lang.psi.OCBinaryExpression;
import com.jetbrains.cidr.lang.psi.OCCastExpression;
import com.jetbrains.cidr.lang.psi.OCDeclaration;
import com.jetbrains.cidr.lang.psi.OCDeclarator;
import com.jetbrains.cidr.lang.psi.OCCompoundInitializer;
import com.jetbrains.cidr.lang.refactoring.util.OCChangeUtil;
import com.intellij.psi.PsiElement;
import com.jetbrains.cidr.lang.psi.OCParenthesizedExpression;
import org.jetbrains.annotations.NotNull;
import java.util.List;
import com.jetbrains.cidr.lang.psi.OCBlockStatement;
import org.jetbrains.annotations.Nullable;
import com.jetbrains.cidr.lang.psi.OCStatement;
import com.jetbrains.cidr.lang.psi.OCExpression;
import com.intellij.psi.tree.IElementType;
import com.jetbrains.cidr.lang.parser.OCTokenTypes;
import com.jetbrains.cidr.lang.parser.OCElementType;
import java.util.Map;

public class OCParenthesesUtils
{
    public static final int SEND_MESSAGE_OUTER_PRECEDENCE = 1;
    public static final int PREFIX_INCDEC_PRECEDENCE = 2;
    public static final int UNARY_OP_PRECEDENCE = 3;
    public static final int PM_OP_PRECEDENCE = 4;
    public static final int MULTIPLICATIVE_PRECEDENCE = 5;
    public static final int ADDITIVE_PRECEDENCE = 6;
    public static final int SHIFT_PRECEDENCE = 7;
    public static final int RELATIONAL_PRECEDENCE = 8;
    public static final int EQUALITY_PRECEDENCE = 9;
    public static final int BITWISE_AND_PRECEDENCE = 10;
    public static final int BITWISE_XOR_PRECEDENCE = 11;
    public static final int BITWISE_OR_PRECEDENCE = 12;
    public static final int LOGICAL_AND_PRECEDENCE = 13;
    public static final int LOGICAL_OR_PRECEDENCE = 14;
    public static final int TERNARY_OPERATOR_PRECEDENCE = 15;
    public static final int ASSIGNMENT_PRECEDENCE = 16;
    public static final int COMMA_PRECEDENCE_PRECEDENCE = 17;
    public static final int SEND_MESSAGE_INNER_PRECEDENCE = 18;
    private static final Map<OCElementType, Integer> BINARY_OPERATOR_PRECEDENCE;
    
    public static int getPrecedence(final OCElementType ocElementType) {
        try {
            if (ocElementType == OCTokenTypes.EQ) {
                return 16;
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        try {
            if (OCTokenTypes.UNARY_OPERATIONS.contains((IElementType)ocElementType)) {
                return 3;
            }
        }
        catch (IllegalArgumentException ex2) {
            throw a(ex2);
        }
        try {
            if (OCParenthesesUtils.BINARY_OPERATOR_PRECEDENCE.containsKey(ocElementType)) {
                return OCParenthesesUtils.BINARY_OPERATOR_PRECEDENCE.get(ocElementType);
            }
        }
        catch (IllegalArgumentException ex3) {
            throw a(ex3);
        }
        throw new IllegalArgumentException("Unknown precedence of operator " + ocElementType.getName());
    }
    
    public static int getPrecedence(final OCExpression p0, final boolean p1) {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     0: aload_0        
        //     1: instanceof      Lcom/jetbrains/cidr/lang/psi/OCParenthesizedExpression;
        //     4: ifne            35
        //     7: aload_0        
        //     8: instanceof      Lcom/jetbrains/cidr/lang/psi/OCLiteralExpression;
        //    11: ifne            35
        //    14: goto            21
        //    17: invokestatic    com/jetbrains/cidr/lang/util/OCParenthesesUtils.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //    20: athrow         
        //    21: aload_0        
        //    22: instanceof      Lcom/jetbrains/cidr/lang/psi/OCReferenceExpression;
        //    25: ifeq            41
        //    28: goto            35
        //    31: invokestatic    com/jetbrains/cidr/lang/util/OCParenthesesUtils.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //    34: athrow         
        //    35: iconst_0       
        //    36: ireturn        
        //    37: invokestatic    com/jetbrains/cidr/lang/util/OCParenthesesUtils.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //    40: athrow         
        //    41: aload_0        
        //    42: instanceof      Lcom/jetbrains/cidr/lang/psi/OCQualifiedExpression;
        //    45: ifne            90
        //    48: aload_0        
        //    49: instanceof      Lcom/jetbrains/cidr/lang/psi/OCPrefixExpression;
        //    52: ifne            90
        //    55: goto            62
        //    58: invokestatic    com/jetbrains/cidr/lang/util/OCParenthesesUtils.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //    61: athrow         
        //    62: aload_0        
        //    63: instanceof      Lcom/jetbrains/cidr/lang/psi/OCArraySelectionExpression;
        //    66: ifne            90
        //    69: goto            76
        //    72: invokestatic    com/jetbrains/cidr/lang/util/OCParenthesesUtils.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //    75: athrow         
        //    76: aload_0        
        //    77: instanceof      Lcom/jetbrains/cidr/lang/psi/OCCallExpression;
        //    80: ifeq            96
        //    83: goto            90
        //    86: invokestatic    com/jetbrains/cidr/lang/util/OCParenthesesUtils.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //    89: athrow         
        //    90: iconst_2       
        //    91: ireturn        
        //    92: invokestatic    com/jetbrains/cidr/lang/util/OCParenthesesUtils.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //    95: athrow         
        //    96: aload_0        
        //    97: instanceof      Lcom/jetbrains/cidr/lang/psi/OCUnaryExpression;
        //   100: ifne            131
        //   103: aload_0        
        //   104: instanceof      Lcom/jetbrains/cidr/lang/psi/OCCastExpression;
        //   107: ifne            131
        //   110: goto            117
        //   113: invokestatic    com/jetbrains/cidr/lang/util/OCParenthesesUtils.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   116: athrow         
        //   117: aload_0        
        //   118: instanceof      Lcom/jetbrains/cidr/lang/psi/OCSizeofExpression;
        //   121: ifeq            137
        //   124: goto            131
        //   127: invokestatic    com/jetbrains/cidr/lang/util/OCParenthesesUtils.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   130: athrow         
        //   131: iconst_3       
        //   132: ireturn        
        //   133: invokestatic    com/jetbrains/cidr/lang/util/OCParenthesesUtils.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   136: athrow         
        //   137: aload_0        
        //   138: instanceof      Lcom/jetbrains/cidr/lang/psi/OCBinaryExpression;
        //   141: ifeq            172
        //   144: getstatic       com/jetbrains/cidr/lang/util/OCParenthesesUtils.BINARY_OPERATOR_PRECEDENCE:Ljava/util/Map;
        //   147: aload_0        
        //   148: checkcast       Lcom/jetbrains/cidr/lang/psi/OCBinaryExpression;
        //   151: invokeinterface com/jetbrains/cidr/lang/psi/OCBinaryExpression.getOperationSign:()Lcom/jetbrains/cidr/lang/parser/OCElementType;
        //   156: invokeinterface java/util/Map.get:(Ljava/lang/Object;)Ljava/lang/Object;
        //   161: checkcast       Ljava/lang/Integer;
        //   164: invokevirtual   java/lang/Integer.intValue:()I
        //   167: ireturn        
        //   168: invokestatic    com/jetbrains/cidr/lang/util/OCParenthesesUtils.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   171: athrow         
        //   172: aload_0        
        //   173: instanceof      Lcom/jetbrains/cidr/lang/psi/OCConditionalExpression;
        //   176: ifeq            186
        //   179: bipush          15
        //   181: ireturn        
        //   182: invokestatic    com/jetbrains/cidr/lang/util/OCParenthesesUtils.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   185: athrow         
        //   186: aload_0        
        //   187: instanceof      Lcom/jetbrains/cidr/lang/psi/OCAssignmentExpression;
        //   190: ifeq            200
        //   193: bipush          16
        //   195: ireturn        
        //   196: invokestatic    com/jetbrains/cidr/lang/util/OCParenthesesUtils.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   199: athrow         
        //   200: aload_0        
        //   201: instanceof      Lcom/jetbrains/cidr/lang/psi/OCCommaExpression;
        //   204: ifne            221
        //   207: aload_0        
        //   208: instanceof      Lcom/jetbrains/cidr/lang/psi/OCPostfixExpression;
        //   211: ifeq            228
        //   214: goto            221
        //   217: invokestatic    com/jetbrains/cidr/lang/util/OCParenthesesUtils.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   220: athrow         
        //   221: bipush          17
        //   223: ireturn        
        //   224: invokestatic    com/jetbrains/cidr/lang/util/OCParenthesesUtils.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   227: athrow         
        //   228: aload_0        
        //   229: instanceof      Lcom/jetbrains/cidr/lang/psi/OCSendMessageExpression;
        //   232: ifeq            257
        //   235: iload_1        
        //   236: ifeq            255
        //   239: goto            246
        //   242: invokestatic    com/jetbrains/cidr/lang/util/OCParenthesesUtils.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   245: athrow         
        //   246: bipush          18
        //   248: goto            256
        //   251: invokestatic    com/jetbrains/cidr/lang/util/OCParenthesesUtils.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   254: athrow         
        //   255: iconst_1       
        //   256: ireturn        
        //   257: iconst_m1      
        //   258: ireturn        
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                                
        //  -----  -----  -----  -----  ------------------------------------
        //  0      14     17     21     Ljava/lang/IllegalArgumentException;
        //  7      28     31     35     Ljava/lang/IllegalArgumentException;
        //  21     37     37     41     Ljava/lang/IllegalArgumentException;
        //  41     55     58     62     Ljava/lang/IllegalArgumentException;
        //  48     69     72     76     Ljava/lang/IllegalArgumentException;
        //  62     83     86     90     Ljava/lang/IllegalArgumentException;
        //  76     92     92     96     Ljava/lang/IllegalArgumentException;
        //  96     110    113    117    Ljava/lang/IllegalArgumentException;
        //  103    124    127    131    Ljava/lang/IllegalArgumentException;
        //  117    133    133    137    Ljava/lang/IllegalArgumentException;
        //  137    168    168    172    Ljava/lang/IllegalArgumentException;
        //  172    182    182    186    Ljava/lang/IllegalArgumentException;
        //  186    196    196    200    Ljava/lang/IllegalArgumentException;
        //  200    214    217    221    Ljava/lang/IllegalArgumentException;
        //  207    224    224    228    Ljava/lang/IllegalArgumentException;
        //  228    239    242    246    Ljava/lang/IllegalArgumentException;
        //  235    251    251    255    Ljava/lang/IllegalArgumentException;
        // 
        // The error that occurred was:
        // 
        // java.lang.IllegalStateException: Expression is linked from several locations: Label_0021:
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
    
    @Nullable
    public static OCStatement stripBraces(@Nullable final OCStatement ocStatement) {
        if (ocStatement instanceof OCBlockStatement) {
            final OCBlockStatement ocBlockStatement = (OCBlockStatement)ocStatement;
            final List<OCStatement> statements = ocBlockStatement.getStatements();
            try {
                if (statements.size() == 1) {
                    return statements.get(0);
                }
            }
            catch (IllegalArgumentException ex) {
                throw a(ex);
            }
            return ocBlockStatement;
        }
        return ocStatement;
    }
    
    @NotNull
    public static OCParenthesizedExpression appendParentheses(@NotNull final OCExpression ocExpression) {
        try {
            if (ocExpression == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "expression", "com/jetbrains/cidr/lang/util/OCParenthesesUtils", "appendParentheses"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        final OCParenthesizedExpression ocParenthesizedExpression = (OCParenthesizedExpression)OCElementFactory.expressionFromText("(1)", (PsiElement)ocExpression, false);
        OCParenthesizedExpression ocParenthesizedExpression2;
        try {
            OCChangeUtil.replaceHandlingMacros((PsiElement)ocParenthesizedExpression.getOperand(), (PsiElement)ocExpression);
            ocParenthesizedExpression2 = ocParenthesizedExpression;
            if (ocParenthesizedExpression2 == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/util/OCParenthesesUtils", "appendParentheses"));
            }
        }
        catch (IllegalArgumentException ex2) {
            throw a(ex2);
        }
        return ocParenthesizedExpression2;
    }
    
    public static PsiElement replaceExpressionAndRemoveAppendParentheses(@NotNull OCExpression ocExpression, @NotNull OCExpression diveIntoParenthesesButNotIntoMacroCall) {
        try {
            if (ocExpression == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "oldElement", "com/jetbrains/cidr/lang/util/OCParenthesesUtils", "replaceExpressionAndRemoveAppendParentheses"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        try {
            if (diveIntoParenthesesButNotIntoMacroCall == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "newElement", "com/jetbrains/cidr/lang/util/OCParenthesesUtils", "replaceExpressionAndRemoveAppendParentheses"));
            }
        }
        catch (IllegalArgumentException ex2) {
            throw a(ex2);
        }
        diveIntoParenthesesButNotIntoMacroCall = diveIntoParenthesesButNotIntoMacroCall(diveIntoParenthesesButNotIntoMacroCall);
        while (ocExpression.getParent() instanceof OCParenthesizedExpression) {
            ocExpression = (OCExpression)ocExpression.getParent();
        }
        return replaceExpressionAndAppendParentheses(ocExpression, diveIntoParenthesesButNotIntoMacroCall);
    }
    
    public static PsiElement replaceExpressionAndAppendParentheses(@NotNull final OCExpression ocExpression, @NotNull OCExpression ocExpression2) {
        try {
            if (ocExpression == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "oldElement", "com/jetbrains/cidr/lang/util/OCParenthesesUtils", "replaceExpressionAndAppendParentheses"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        try {
            if (ocExpression2 == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "newElement", "com/jetbrains/cidr/lang/util/OCParenthesesUtils", "replaceExpressionAndAppendParentheses"));
            }
        }
        catch (IllegalArgumentException ex2) {
            throw a(ex2);
        }
        Label_0191: {
            Label_0114: {
                try {
                    if (!(ocExpression2 instanceof OCCompoundInitializer)) {
                        break Label_0191;
                    }
                    final OCExpression ocExpression3 = ocExpression2;
                    final PsiElement psiElement = ocExpression3.getParent();
                    final boolean b = psiElement instanceof OCDeclarator;
                    if (b) {
                        break Label_0114;
                    }
                    break Label_0191;
                }
                catch (IllegalArgumentException ex3) {
                    throw a(ex3);
                }
                try {
                    final OCExpression ocExpression3 = ocExpression2;
                    final PsiElement psiElement = ocExpression3.getParent();
                    final boolean b = psiElement instanceof OCDeclarator;
                    if (!b) {
                        break Label_0191;
                    }
                    if (ocExpression instanceof OCCompoundInitializer) {
                        break Label_0191;
                    }
                }
                catch (IllegalArgumentException ex4) {
                    throw a(ex4);
                }
            }
            final OCDeclaration ocDeclaration = (OCDeclaration)ocExpression2.getParent().getParent();
            if (ocDeclaration.getTypeElement() != null) {
                final OCCastExpression ocCastExpression = (OCCastExpression)OCElementFactory.expressionFromText("(int)1", (PsiElement)ocExpression2);
                OCChangeUtil.replaceHandlingMacros((PsiElement)ocCastExpression.getTypeElement(), (PsiElement)ocDeclaration.getTypeElement());
                OCChangeUtil.replaceHandlingMacros((PsiElement)ocCastExpression.getOperand(), (PsiElement)ocExpression2);
                ocExpression2 = ocCastExpression;
            }
            try {
                if (isParenthesesNeededInReplacing(ocExpression, ocExpression2)) {
                    return OCChangeUtil.replaceHandlingMacros((PsiElement)ocExpression, (PsiElement)appendParentheses(ocExpression2));
                }
            }
            catch (IllegalArgumentException ex5) {
                throw a(ex5);
            }
        }
        return OCChangeUtil.replaceHandlingMacros((PsiElement)ocExpression, (PsiElement)ocExpression2);
    }
    
    public static boolean isParenthesesNeededInReplacing(@NotNull final OCExpression p0, @NotNull final OCExpression p1) {
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
        //    18: ldc             "oldElement"
        //    20: aastore        
        //    21: dup            
        //    22: ldc             1
        //    24: ldc             "com/jetbrains/cidr/lang/util/OCParenthesesUtils"
        //    26: aastore        
        //    27: dup            
        //    28: ldc             2
        //    30: ldc             "isParenthesesNeededInReplacing"
        //    32: aastore        
        //    33: invokestatic    java/lang/String.format:(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
        //    36: invokespecial   java/lang/IllegalArgumentException.<init>:(Ljava/lang/String;)V
        //    39: athrow         
        //    40: invokestatic    com/jetbrains/cidr/lang/util/OCParenthesesUtils.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //    43: athrow         
        //    44: aload_1        
        //    45: ifnonnull       88
        //    48: new             Ljava/lang/IllegalArgumentException;
        //    51: dup            
        //    52: ldc             "Argument for @NotNull parameter '%s' of %s.%s must not be null"
        //    54: ldc             3
        //    56: anewarray       Ljava/lang/Object;
        //    59: dup            
        //    60: ldc             0
        //    62: ldc             "newElement"
        //    64: aastore        
        //    65: dup            
        //    66: ldc             1
        //    68: ldc             "com/jetbrains/cidr/lang/util/OCParenthesesUtils"
        //    70: aastore        
        //    71: dup            
        //    72: ldc             2
        //    74: ldc             "isParenthesesNeededInReplacing"
        //    76: aastore        
        //    77: invokestatic    java/lang/String.format:(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
        //    80: invokespecial   java/lang/IllegalArgumentException.<init>:(Ljava/lang/String;)V
        //    83: athrow         
        //    84: invokestatic    com/jetbrains/cidr/lang/util/OCParenthesesUtils.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //    87: athrow         
        //    88: aload_0        
        //    89: invokeinterface com/jetbrains/cidr/lang/psi/OCExpression.getParent:()Lcom/intellij/psi/PsiElement;
        //    94: astore_2       
        //    95: aload_2        
        //    96: instanceof      Lcom/jetbrains/cidr/lang/psi/OCExpression;
        //    99: ifeq            197
        //   102: aload_2        
        //   103: checkcast       Lcom/jetbrains/cidr/lang/psi/OCExpression;
        //   106: iconst_1       
        //   107: invokestatic    com/jetbrains/cidr/lang/util/OCParenthesesUtils.getPrecedence:(Lcom/jetbrains/cidr/lang/psi/OCExpression;Z)I
        //   110: istore_3       
        //   111: aload_1        
        //   112: iconst_0       
        //   113: invokestatic    com/jetbrains/cidr/lang/util/OCParenthesesUtils.getPrecedence:(Lcom/jetbrains/cidr/lang/psi/OCExpression;Z)I
        //   116: istore          4
        //   118: iload_3        
        //   119: iload           4
        //   121: if_icmple       130
        //   124: iconst_0       
        //   125: ireturn        
        //   126: invokestatic    com/jetbrains/cidr/lang/util/OCParenthesesUtils.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   129: athrow         
        //   130: iload_3        
        //   131: iload           4
        //   133: if_icmpge       142
        //   136: iconst_1       
        //   137: ireturn        
        //   138: invokestatic    com/jetbrains/cidr/lang/util/OCParenthesesUtils.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   141: athrow         
        //   142: aload_2        
        //   143: instanceof      Lcom/jetbrains/cidr/lang/psi/OCBinaryExpression;
        //   146: ifeq            195
        //   149: aload_0        
        //   150: aload_2        
        //   151: checkcast       Lcom/jetbrains/cidr/lang/psi/OCBinaryExpression;
        //   154: invokeinterface com/jetbrains/cidr/lang/psi/OCBinaryExpression.getRight:()Lcom/jetbrains/cidr/lang/psi/OCExpression;
        //   159: if_acmpne       195
        //   162: goto            169
        //   165: invokestatic    com/jetbrains/cidr/lang/util/OCParenthesesUtils.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   168: athrow         
        //   169: aload_2        
        //   170: checkcast       Lcom/jetbrains/cidr/lang/psi/OCBinaryExpression;
        //   173: aload_1        
        //   174: invokestatic    com/jetbrains/cidr/lang/util/OCParenthesesUtils.isParenthesesNeededInRightArgument:(Lcom/jetbrains/cidr/lang/psi/OCBinaryExpression;Lcom/jetbrains/cidr/lang/psi/OCExpression;)Z
        //   177: ifeq            193
        //   180: goto            187
        //   183: invokestatic    com/jetbrains/cidr/lang/util/OCParenthesesUtils.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   186: athrow         
        //   187: iconst_1       
        //   188: ireturn        
        //   189: invokestatic    com/jetbrains/cidr/lang/util/OCParenthesesUtils.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   192: athrow         
        //   193: iconst_0       
        //   194: ireturn        
        //   195: iconst_0       
        //   196: ireturn        
        //   197: iconst_0       
        //   198: ireturn        
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                                
        //  -----  -----  -----  -----  ------------------------------------
        //  0      40     40     44     Ljava/lang/IllegalArgumentException;
        //  44     84     84     88     Ljava/lang/IllegalArgumentException;
        //  118    126    126    130    Ljava/lang/IllegalArgumentException;
        //  130    138    138    142    Ljava/lang/IllegalArgumentException;
        //  142    162    165    169    Ljava/lang/IllegalArgumentException;
        //  149    180    183    187    Ljava/lang/IllegalArgumentException;
        //  169    189    189    193    Ljava/lang/IllegalArgumentException;
        // 
        // The error that occurred was:
        // 
        // java.lang.IllegalStateException: Expression is linked from several locations: Label_0169:
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
    
    public static boolean isParenthesesNeededInRightArgument(final OCBinaryExpression p0, final OCExpression p1) {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     0: aload_1        
        //     1: instanceof      Lcom/jetbrains/cidr/lang/psi/OCBinaryExpression;
        //     4: ifeq            71
        //     7: aload_1        
        //     8: checkcast       Lcom/jetbrains/cidr/lang/psi/OCBinaryExpression;
        //    11: astore_2       
        //    12: aload_0        
        //    13: invokeinterface com/jetbrains/cidr/lang/psi/OCBinaryExpression.getOperationSign:()Lcom/jetbrains/cidr/lang/parser/OCElementType;
        //    18: aload_2        
        //    19: invokeinterface com/jetbrains/cidr/lang/psi/OCBinaryExpression.getOperationSign:()Lcom/jetbrains/cidr/lang/parser/OCElementType;
        //    24: if_acmpne       71
        //    27: aload_2        
        //    28: invokeinterface com/jetbrains/cidr/lang/psi/OCBinaryExpression.getOperationSign:()Lcom/jetbrains/cidr/lang/parser/OCElementType;
        //    33: getstatic       com/jetbrains/cidr/lang/parser/OCTokenTypes.ANDAND:Lcom/jetbrains/cidr/lang/parser/OCPunctuatorElementType;
        //    36: if_acmpeq       65
        //    39: goto            46
        //    42: invokestatic    com/jetbrains/cidr/lang/util/OCParenthesesUtils.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //    45: athrow         
        //    46: aload_2        
        //    47: invokeinterface com/jetbrains/cidr/lang/psi/OCBinaryExpression.getOperationSign:()Lcom/jetbrains/cidr/lang/parser/OCElementType;
        //    52: getstatic       com/jetbrains/cidr/lang/parser/OCTokenTypes.OROR:Lcom/jetbrains/cidr/lang/parser/OCPunctuatorElementType;
        //    55: if_acmpne       71
        //    58: goto            65
        //    61: invokestatic    com/jetbrains/cidr/lang/util/OCParenthesesUtils.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //    64: athrow         
        //    65: iconst_0       
        //    66: ireturn        
        //    67: invokestatic    com/jetbrains/cidr/lang/util/OCParenthesesUtils.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //    70: athrow         
        //    71: iconst_1       
        //    72: ireturn        
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                                
        //  -----  -----  -----  -----  ------------------------------------
        //  12     39     42     46     Ljava/lang/IllegalArgumentException;
        //  27     58     61     65     Ljava/lang/IllegalArgumentException;
        //  46     67     67     71     Ljava/lang/IllegalArgumentException;
        // 
        // The error that occurred was:
        // 
        // java.lang.IllegalStateException: Expression is linked from several locations: Label_0046:
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
    
    @Nullable
    public static OCExpression diveIntoParenthesesAndCasts(@Nullable final OCExpression ocExpression) {
        return diveIntoParenthesesAndCasts(ocExpression, OCCastKind.values());
    }
    
    @Nullable
    public static OCExpression diveIntoParenthesesAndCasts(@Nullable OCExpression ocExpression, @NotNull final OCCastKind[] array) {
        try {
            if (array == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "acceptableCasts", "com/jetbrains/cidr/lang/util/OCParenthesesUtils", "diveIntoParenthesesAndCasts"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        while (true) {
            if (ocExpression instanceof OCParenthesizedExpression) {
                ocExpression = ((OCParenthesizedExpression)ocExpression).getOperand();
            }
            else {
                if (!(ocExpression instanceof OCCastExpression)) {
                    break;
                }
                final OCCastExpression ocCastExpression = (OCCastExpression)ocExpression;
                try {
                    if (ArrayUtil.indexOf((Object[])array, (Object)ocCastExpression.getCastKind()) == -1) {
                        break;
                    }
                }
                catch (IllegalArgumentException ex2) {
                    throw a(ex2);
                }
                ocExpression = ocCastExpression.getOperand();
            }
        }
        return ocExpression;
    }
    
    @Nullable
    public static OCExpression diveIntoParenthesesButNotIntoMacroCall(@Nullable final OCExpression ocExpression) {
        Label_0021: {
            try {
                if (!(ocExpression instanceof OCParenthesizedExpression)) {
                    return ocExpression;
                }
                final OCExpression ocExpression2 = ocExpression;
                final boolean b = OCElementUtil.isPartOfMacroSubstitution((PsiElement)ocExpression2);
                if (!b) {
                    break Label_0021;
                }
                return ocExpression;
            }
            catch (IllegalArgumentException ex) {
                throw a(ex);
            }
            try {
                final OCExpression ocExpression2 = ocExpression;
                final boolean b = OCElementUtil.isPartOfMacroSubstitution((PsiElement)ocExpression2);
                if (!b) {
                    return diveIntoParenthesesButNotIntoMacroCall(((OCParenthesizedExpression)ocExpression).getOperand());
                }
            }
            catch (IllegalArgumentException ex2) {
                throw a(ex2);
            }
        }
        return ocExpression;
    }
    
    @Nullable
    public static OCTypeOwner diveIntoParentheses(@Nullable final OCTypeOwner ocTypeOwner) {
        try {
            if (ocTypeOwner instanceof OCExpression) {
                return diveIntoParentheses((OCExpression)ocTypeOwner);
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        return ocTypeOwner;
    }
    
    @Nullable
    public static OCExpression diveIntoParentheses(@Nullable final OCExpression ocExpression) {
        try {
            if (ocExpression instanceof OCParenthesizedExpression) {
                return diveIntoParentheses(((OCParenthesizedExpression)ocExpression).getOperand());
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        return ocExpression;
    }
    
    @Nullable
    @Contract("null -> null")
    public static PsiElement diveIntoParenthesesAndCasts(@Nullable final PsiElement psiElement) {
        try {
            if (psiElement instanceof OCExpression) {
                return (PsiElement)diveIntoParenthesesAndCasts((OCExpression)psiElement);
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        return psiElement;
    }
    
    public static OCExpression topmostParenthesized(final OCExpression ocExpression) {
        try {
            if (ocExpression.getParent() instanceof OCParenthesizedExpression) {
                return topmostParenthesized((OCExpression)ocExpression.getParent());
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        return ocExpression;
    }
    
    public static boolean areExpressionsEquivalent(@Nullable final OCExpression p0, @Nullable final OCExpression p1, final boolean p2, @NotNull final OCResolveContext p3) {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     0: aload_3        
        //     1: ifnonnull       44
        //     4: new             Ljava/lang/IllegalArgumentException;
        //     7: dup            
        //     8: ldc             "Argument for @NotNull parameter '%s' of %s.%s must not be null"
        //    10: ldc             3
        //    12: anewarray       Ljava/lang/Object;
        //    15: dup            
        //    16: ldc             0
        //    18: ldc             "context"
        //    20: aastore        
        //    21: dup            
        //    22: ldc             1
        //    24: ldc             "com/jetbrains/cidr/lang/util/OCParenthesesUtils"
        //    26: aastore        
        //    27: dup            
        //    28: ldc             2
        //    30: ldc             "areExpressionsEquivalent"
        //    32: aastore        
        //    33: invokestatic    java/lang/String.format:(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
        //    36: invokespecial   java/lang/IllegalArgumentException.<init>:(Ljava/lang/String;)V
        //    39: athrow         
        //    40: invokestatic    com/jetbrains/cidr/lang/util/OCParenthesesUtils.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //    43: athrow         
        //    44: aload_0        
        //    45: invokestatic    com/jetbrains/cidr/lang/util/OCParenthesesUtils.diveIntoParentheses:(Lcom/jetbrains/cidr/lang/psi/OCExpression;)Lcom/jetbrains/cidr/lang/psi/OCExpression;
        //    48: astore_0       
        //    49: aload_1        
        //    50: invokestatic    com/jetbrains/cidr/lang/util/OCParenthesesUtils.diveIntoParentheses:(Lcom/jetbrains/cidr/lang/psi/OCExpression;)Lcom/jetbrains/cidr/lang/psi/OCExpression;
        //    53: astore_1       
        //    54: aload_0        
        //    55: ifnull          94
        //    58: aload_1        
        //    59: ifnull          94
        //    62: goto            69
        //    65: invokestatic    com/jetbrains/cidr/lang/util/OCParenthesesUtils.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //    68: athrow         
        //    69: aload_0        
        //    70: aload_1        
        //    71: iload_2        
        //    72: aload_3        
        //    73: invokestatic    com/jetbrains/cidr/lang/util/OCElementUtil.areElementsEquivalent:(Lcom/intellij/psi/PsiElement;Lcom/intellij/psi/PsiElement;ZLcom/jetbrains/cidr/lang/symbols/OCResolveContext;)Z
        //    76: ifeq            94
        //    79: goto            86
        //    82: invokestatic    com/jetbrains/cidr/lang/util/OCParenthesesUtils.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //    85: athrow         
        //    86: iconst_1       
        //    87: goto            95
        //    90: invokestatic    com/jetbrains/cidr/lang/util/OCParenthesesUtils.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //    93: athrow         
        //    94: iconst_0       
        //    95: ireturn        
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                                
        //  -----  -----  -----  -----  ------------------------------------
        //  0      40     40     44     Ljava/lang/IllegalArgumentException;
        //  54     62     65     69     Ljava/lang/IllegalArgumentException;
        //  58     79     82     86     Ljava/lang/IllegalArgumentException;
        //  69     90     90     94     Ljava/lang/IllegalArgumentException;
        // 
        // The error that occurred was:
        // 
        // java.lang.IllegalStateException: Expression is linked from several locations: Label_0069:
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
    
    public static boolean areExpressionsOpposite(OCExpression diveIntoParentheses, OCExpression diveIntoParentheses2, final boolean b, @NotNull final OCResolveContext ocResolveContext) {
        try {
            if (ocResolveContext == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "context", "com/jetbrains/cidr/lang/util/OCParenthesesUtils", "areExpressionsOpposite"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        diveIntoParentheses = diveIntoParentheses(diveIntoParentheses);
        diveIntoParentheses2 = diveIntoParentheses(diveIntoParentheses2);
        Label_0132: {
            Label_0103: {
                Label_0083: {
                    try {
                        if (!(diveIntoParentheses instanceof OCUnaryExpression)) {
                            break Label_0103;
                        }
                        final OCExpression ocExpression = diveIntoParentheses;
                        final OCUnaryExpression ocUnaryExpression = (OCUnaryExpression)ocExpression;
                        final OCElementType ocElementType = ocUnaryExpression.getOperationSign();
                        final OCPunctuatorElementType ocPunctuatorElementType = OCTokenTypes.EXCL;
                        if (ocElementType == ocPunctuatorElementType) {
                            break Label_0083;
                        }
                        break Label_0103;
                    }
                    catch (IllegalArgumentException ex2) {
                        throw a(ex2);
                    }
                    try {
                        final OCExpression ocExpression = diveIntoParentheses;
                        final OCUnaryExpression ocUnaryExpression = (OCUnaryExpression)ocExpression;
                        final OCElementType ocElementType = ocUnaryExpression.getOperationSign();
                        final OCPunctuatorElementType ocPunctuatorElementType = OCTokenTypes.EXCL;
                        if (ocElementType == ocPunctuatorElementType) {
                            return areExpressionsEquivalent(((OCUnaryExpression)diveIntoParentheses).getOperand(), diveIntoParentheses2, b, ocResolveContext);
                        }
                    }
                    catch (IllegalArgumentException ex3) {
                        throw a(ex3);
                    }
                }
                try {
                    if (!(diveIntoParentheses2 instanceof OCUnaryExpression)) {
                        return false;
                    }
                    final OCExpression ocExpression2 = diveIntoParentheses2;
                    final OCUnaryExpression ocUnaryExpression2 = (OCUnaryExpression)ocExpression2;
                    final OCElementType ocElementType2 = ocUnaryExpression2.getOperationSign();
                    final OCPunctuatorElementType ocPunctuatorElementType2 = OCTokenTypes.EXCL;
                    if (ocElementType2 == ocPunctuatorElementType2) {
                        break Label_0132;
                    }
                    return false;
                }
                catch (IllegalArgumentException ex4) {
                    throw a(ex4);
                }
            }
            try {
                final OCExpression ocExpression2 = diveIntoParentheses2;
                final OCUnaryExpression ocUnaryExpression2 = (OCUnaryExpression)ocExpression2;
                final OCElementType ocElementType2 = ocUnaryExpression2.getOperationSign();
                final OCPunctuatorElementType ocPunctuatorElementType2 = OCTokenTypes.EXCL;
                if (ocElementType2 == ocPunctuatorElementType2) {
                    return areExpressionsEquivalent(((OCUnaryExpression)diveIntoParentheses2).getOperand(), diveIntoParentheses, b, ocResolveContext);
                }
            }
            catch (IllegalArgumentException ex5) {
                throw a(ex5);
            }
        }
        return false;
    }
    
    static {
        (BINARY_OPERATOR_PRECEDENCE = new HashMap<OCElementType, Integer>()).put(OCTokenTypes.DOT_MUL, 4);
        OCParenthesesUtils.BINARY_OPERATOR_PRECEDENCE.put(OCTokenTypes.DEREF_MUL, 4);
        OCParenthesesUtils.BINARY_OPERATOR_PRECEDENCE.put(OCTokenTypes.MUL, 5);
        OCParenthesesUtils.BINARY_OPERATOR_PRECEDENCE.put(OCTokenTypes.DIV, 5);
        OCParenthesesUtils.BINARY_OPERATOR_PRECEDENCE.put(OCTokenTypes.PERC, 5);
        OCParenthesesUtils.BINARY_OPERATOR_PRECEDENCE.put(OCTokenTypes.PLUS, 6);
        OCParenthesesUtils.BINARY_OPERATOR_PRECEDENCE.put(OCTokenTypes.MINUS, 6);
        OCParenthesesUtils.BINARY_OPERATOR_PRECEDENCE.put(OCTokenTypes.LTLT, 7);
        OCParenthesesUtils.BINARY_OPERATOR_PRECEDENCE.put(OCTokenTypes.GTGT, 7);
        OCParenthesesUtils.BINARY_OPERATOR_PRECEDENCE.put(OCTokenTypes.LT, 8);
        OCParenthesesUtils.BINARY_OPERATOR_PRECEDENCE.put(OCTokenTypes.LTEQ, 8);
        OCParenthesesUtils.BINARY_OPERATOR_PRECEDENCE.put(OCTokenTypes.GT, 8);
        OCParenthesesUtils.BINARY_OPERATOR_PRECEDENCE.put(OCTokenTypes.GTEQ, 8);
        OCParenthesesUtils.BINARY_OPERATOR_PRECEDENCE.put(OCTokenTypes.EQEQ, 9);
        OCParenthesesUtils.BINARY_OPERATOR_PRECEDENCE.put(OCTokenTypes.EXCLEQ, 9);
        OCParenthesesUtils.BINARY_OPERATOR_PRECEDENCE.put(OCTokenTypes.AND, 10);
        OCParenthesesUtils.BINARY_OPERATOR_PRECEDENCE.put(OCTokenTypes.XOR, 11);
        OCParenthesesUtils.BINARY_OPERATOR_PRECEDENCE.put(OCTokenTypes.OR, 12);
        OCParenthesesUtils.BINARY_OPERATOR_PRECEDENCE.put(OCTokenTypes.ANDAND, 13);
        OCParenthesesUtils.BINARY_OPERATOR_PRECEDENCE.put(OCTokenTypes.OROR, 14);
    }
    
    private static IllegalArgumentException a(final IllegalArgumentException ex) {
        return ex;
    }
}
