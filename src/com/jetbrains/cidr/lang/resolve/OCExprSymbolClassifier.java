// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.lang.resolve;

import com.jetbrains.cidr.lang.parser.OCTokenTypes;
import com.jetbrains.cidr.lang.resolve.references.OCOperatorReference;
import com.jetbrains.cidr.lang.types.OCFunctionType;
import com.jetbrains.cidr.lang.types.OCPointerType;
import com.jetbrains.cidr.lang.OCLog;
import com.jetbrains.cidr.lang.symbols.expression.OCArrayIndexExpressionSymbol;
import com.jetbrains.cidr.lang.symbols.expression.OCVariadicPackExpressionSymbol;
import com.jetbrains.cidr.lang.symbols.expression.OCUnknownExpressionSymbol;
import com.jetbrains.cidr.lang.symbols.expression.OCSizeofExpressionSymbol;
import com.jetbrains.cidr.lang.symbols.expression.OCReferenceExpressionSymbol;
import com.jetbrains.cidr.lang.symbols.expression.OCQualifiedExpressionSymbol;
import com.jetbrains.cidr.lang.symbols.expression.OCUnaryExpressionSymbolBase;
import com.jetbrains.cidr.lang.symbols.expression.OCNewExpressionSymbol;
import com.jetbrains.cidr.lang.symbols.expression.OCLiteralExpressionSymbol;
import com.jetbrains.cidr.lang.symbols.expression.OCLambdaExpressionSymbol;
import com.jetbrains.cidr.lang.symbols.expression.OCInitializerListExpressionSymbol;
import com.jetbrains.cidr.lang.symbols.expression.OCConditionalExpressionSymbol;
import com.jetbrains.cidr.lang.symbols.expression.OCCommaExpressionSymbol;
import com.jetbrains.cidr.lang.symbols.expression.OCCastExpressionSymbol;
import com.jetbrains.cidr.lang.symbols.expression.OCCallExpressionSymbol;
import com.jetbrains.cidr.lang.symbols.expression.OCBinaryExpressionSymbol;
import com.jetbrains.cidr.lang.symbols.expression.OCExpressionSymbol;
import com.jetbrains.cidr.lang.parser.OCElementType;
import com.jetbrains.cidr.lang.types.OCCppReferenceType;
import org.jetbrains.annotations.NotNull;
import com.jetbrains.cidr.lang.types.OCType;
import com.jetbrains.cidr.lang.symbols.OCResolveContext;

public class OCExprSymbolClassifier
{
    private final OCResolveContext myContext;
    
    public OCExprSymbolClassifier(final OCResolveContext myContext) {
        this.myContext = myContext;
    }
    
    @NotNull
    private static OCExprValueCategory a(@NotNull final OCType ocType) {
        try {
            if (ocType == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "type", "com/jetbrains/cidr/lang/resolve/OCExprSymbolClassifier", "byReturnType"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        OCExprValueCategory prValue = null;
        Label_0120: {
            OCExprValueCategory ocExprValueCategory = null;
            Label_0081: {
                Label_0068: {
                    try {
                        if (!(ocType instanceof OCCppReferenceType)) {
                            break Label_0120;
                        }
                        final OCType ocType2 = ocType;
                        final OCCppReferenceType ocCppReferenceType = (OCCppReferenceType)ocType2;
                        final boolean b = ocCppReferenceType.isRvalueRef();
                        if (b) {
                            break Label_0068;
                        }
                        break Label_0068;
                    }
                    catch (IllegalArgumentException ex2) {
                        throw a(ex2);
                    }
                    try {
                        final OCType ocType2 = ocType;
                        final OCCppReferenceType ocCppReferenceType = (OCCppReferenceType)ocType2;
                        final boolean b = ocCppReferenceType.isRvalueRef();
                        if (b) {
                            final OCExprValueCategory ocExprValueCategory2;
                            ocExprValueCategory = (ocExprValueCategory2 = OCExprValueCategory.XValue);
                            break Label_0081;
                        }
                    }
                    catch (IllegalArgumentException ex3) {
                        throw a(ex3);
                    }
                }
                OCExprValueCategory ocExprValueCategory2;
                ocExprValueCategory = (ocExprValueCategory2 = OCExprValueCategory.LValue);
                try {
                    if (ocExprValueCategory2 == null) {
                        throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/resolve/OCExprSymbolClassifier", "byReturnType"));
                    }
                }
                catch (IllegalArgumentException ex4) {
                    throw a(ex4);
                }
            }
            return ocExprValueCategory;
            try {
                prValue = OCExprValueCategory.PRValue;
                if (prValue == null) {
                    throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/resolve/OCExprSymbolClassifier", "byReturnType"));
                }
            }
            catch (IllegalArgumentException ex5) {
                throw a(ex5);
            }
        }
        return prValue;
    }
    
    @NotNull
    private static OCExprValueCategory a(@NotNull final OCElementType p0, final boolean p1) {
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
        //    18: ldc             "sign"
        //    20: aastore        
        //    21: dup            
        //    22: ldc             1
        //    24: ldc             "com/jetbrains/cidr/lang/resolve/OCExprSymbolClassifier"
        //    26: aastore        
        //    27: dup            
        //    28: ldc             2
        //    30: ldc             "byBuiltInUnaryOperationSign"
        //    32: aastore        
        //    33: invokestatic    java/lang/String.format:(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
        //    36: invokespecial   java/lang/IllegalArgumentException.<init>:(Ljava/lang/String;)V
        //    39: athrow         
        //    40: invokestatic    com/jetbrains/cidr/lang/resolve/OCExprSymbolClassifier.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //    43: athrow         
        //    44: aload_0        
        //    45: getstatic       com/jetbrains/cidr/lang/parser/OCTokenTypes.MUL:Lcom/jetbrains/cidr/lang/parser/OCPunctuatorElementType;
        //    48: if_acmpeq       90
        //    51: iload_1        
        //    52: ifeq            139
        //    55: goto            62
        //    58: invokestatic    com/jetbrains/cidr/lang/resolve/OCExprSymbolClassifier.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //    61: athrow         
        //    62: aload_0        
        //    63: getstatic       com/jetbrains/cidr/lang/parser/OCTokenTypes.PLUSPLUS:Lcom/jetbrains/cidr/lang/parser/OCPunctuatorElementType;
        //    66: if_acmpeq       90
        //    69: goto            76
        //    72: invokestatic    com/jetbrains/cidr/lang/resolve/OCExprSymbolClassifier.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //    75: athrow         
        //    76: aload_0        
        //    77: getstatic       com/jetbrains/cidr/lang/parser/OCTokenTypes.MINUSMINUS:Lcom/jetbrains/cidr/lang/parser/OCPunctuatorElementType;
        //    80: if_acmpne       139
        //    83: goto            90
        //    86: invokestatic    com/jetbrains/cidr/lang/resolve/OCExprSymbolClassifier.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //    89: athrow         
        //    90: getstatic       com/jetbrains/cidr/lang/resolve/OCExprValueCategory.LValue:Lcom/jetbrains/cidr/lang/resolve/OCExprValueCategory;
        //    93: dup            
        //    94: ifnonnull       138
        //    97: goto            104
        //   100: invokestatic    com/jetbrains/cidr/lang/resolve/OCExprSymbolClassifier.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   103: athrow         
        //   104: new             Ljava/lang/IllegalStateException;
        //   107: dup            
        //   108: ldc             "@NotNull method %s.%s must not return null"
        //   110: ldc             2
        //   112: anewarray       Ljava/lang/Object;
        //   115: dup            
        //   116: ldc             0
        //   118: ldc             "com/jetbrains/cidr/lang/resolve/OCExprSymbolClassifier"
        //   120: aastore        
        //   121: dup            
        //   122: ldc             1
        //   124: ldc             "byBuiltInUnaryOperationSign"
        //   126: aastore        
        //   127: invokestatic    java/lang/String.format:(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
        //   130: invokespecial   java/lang/IllegalStateException.<init>:(Ljava/lang/String;)V
        //   133: athrow         
        //   134: invokestatic    com/jetbrains/cidr/lang/resolve/OCExprSymbolClassifier.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   137: athrow         
        //   138: areturn        
        //   139: getstatic       com/jetbrains/cidr/lang/resolve/OCExprValueCategory.PRValue:Lcom/jetbrains/cidr/lang/resolve/OCExprValueCategory;
        //   142: dup            
        //   143: ifnonnull       180
        //   146: new             Ljava/lang/IllegalStateException;
        //   149: dup            
        //   150: ldc             "@NotNull method %s.%s must not return null"
        //   152: ldc             2
        //   154: anewarray       Ljava/lang/Object;
        //   157: dup            
        //   158: ldc             0
        //   160: ldc             "com/jetbrains/cidr/lang/resolve/OCExprSymbolClassifier"
        //   162: aastore        
        //   163: dup            
        //   164: ldc             1
        //   166: ldc             "byBuiltInUnaryOperationSign"
        //   168: aastore        
        //   169: invokestatic    java/lang/String.format:(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
        //   172: invokespecial   java/lang/IllegalStateException.<init>:(Ljava/lang/String;)V
        //   175: athrow         
        //   176: invokestatic    com/jetbrains/cidr/lang/resolve/OCExprSymbolClassifier.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   179: athrow         
        //   180: areturn        
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                                
        //  -----  -----  -----  -----  ------------------------------------
        //  0      40     40     44     Ljava/lang/IllegalArgumentException;
        //  44     55     58     62     Ljava/lang/IllegalArgumentException;
        //  51     69     72     76     Ljava/lang/IllegalArgumentException;
        //  62     83     86     90     Ljava/lang/IllegalArgumentException;
        //  76     97     100    104    Ljava/lang/IllegalArgumentException;
        //  90     134    134    138    Ljava/lang/IllegalArgumentException;
        //  139    176    176    180    Ljava/lang/IllegalArgumentException;
        // 
        // The error that occurred was:
        // 
        // java.lang.IllegalStateException: Expression is linked from several locations: Label_0062:
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
    public OCExprValueCategory classify(final OCExpressionSymbol ocExpressionSymbol) {
        OCExprValueCategory lValue = null;
        Label_0976: {
            OCExprValueCategory ocExprValueCategory16 = null;
            Label_0941: {
                Label_0915: {
                    OCExprValueCategory ocExprValueCategory15 = null;
                    Label_0880: {
                        Label_0854: {
                            OCExprValueCategory ocExprValueCategory14 = null;
                            Label_0819: {
                                Label_0793: {
                                    OCExprValueCategory ocExprValueCategory13 = null;
                                    Label_0758: {
                                        Label_0732: {
                                            OCExprValueCategory ocExprValueCategory12 = null;
                                            Label_0697: {
                                                Label_0671: {
                                                    OCExprValueCategory ocExprValueCategory11 = null;
                                                    Label_0636: {
                                                        Label_0610: {
                                                            OCExprValueCategory ocExprValueCategory10 = null;
                                                            Label_0575: {
                                                                Label_0549: {
                                                                    OCExprValueCategory ocExprValueCategory9 = null;
                                                                    Label_0514: {
                                                                        Label_0488: {
                                                                            OCExprValueCategory ocExprValueCategory8 = null;
                                                                            Label_0453: {
                                                                                Label_0427: {
                                                                                    OCExprValueCategory ocExprValueCategory7 = null;
                                                                                    Label_0392: {
                                                                                        Label_0366: {
                                                                                            OCExprValueCategory ocExprValueCategory6 = null;
                                                                                            Label_0331: {
                                                                                                Label_0305: {
                                                                                                    OCExprValueCategory ocExprValueCategory5 = null;
                                                                                                    Label_0270: {
                                                                                                        Label_0244: {
                                                                                                            OCExprValueCategory ocExprValueCategory4 = null;
                                                                                                            Label_0209: {
                                                                                                                Label_0183: {
                                                                                                                    OCExprValueCategory ocExprValueCategory3 = null;
                                                                                                                    Label_0148: {
                                                                                                                        Label_0122: {
                                                                                                                            OCExprValueCategory ocExprValueCategory2 = null;
                                                                                                                            Label_0087: {
                                                                                                                                Label_0061: {
                                                                                                                                    OCExprValueCategory ocExprValueCategory = null;
                                                                                                                                    Label_0026: {
                                                                                                                                        try {
                                                                                                                                            if (!(ocExpressionSymbol instanceof OCBinaryExpressionSymbol)) {
                                                                                                                                                break Label_0061;
                                                                                                                                            }
                                                                                                                                            final OCExprSymbolClassifier ocExprSymbolClassifier = this;
                                                                                                                                            final OCExpressionSymbol ocExpressionSymbol2 = ocExpressionSymbol;
                                                                                                                                            final OCBinaryExpressionSymbol ocBinaryExpressionSymbol = (OCBinaryExpressionSymbol)ocExpressionSymbol2;
                                                                                                                                            ocExprValueCategory = ocExprSymbolClassifier.classifyBinaryExpression(ocBinaryExpressionSymbol);
                                                                                                                                            if (ocExprValueCategory == null) {
                                                                                                                                                break Label_0026;
                                                                                                                                            }
                                                                                                                                            return ocExprValueCategory;
                                                                                                                                        }
                                                                                                                                        catch (IllegalArgumentException ex) {
                                                                                                                                            throw a(ex);
                                                                                                                                        }
                                                                                                                                        try {
                                                                                                                                            final OCExprSymbolClassifier ocExprSymbolClassifier = this;
                                                                                                                                            final OCExpressionSymbol ocExpressionSymbol2 = ocExpressionSymbol;
                                                                                                                                            final OCBinaryExpressionSymbol ocBinaryExpressionSymbol = (OCBinaryExpressionSymbol)ocExpressionSymbol2;
                                                                                                                                            ocExprValueCategory = ocExprSymbolClassifier.classifyBinaryExpression(ocBinaryExpressionSymbol);
                                                                                                                                            if (ocExprValueCategory == null) {
                                                                                                                                                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/resolve/OCExprSymbolClassifier", "classify"));
                                                                                                                                            }
                                                                                                                                        }
                                                                                                                                        catch (IllegalArgumentException ex2) {
                                                                                                                                            throw a(ex2);
                                                                                                                                        }
                                                                                                                                    }
                                                                                                                                    return ocExprValueCategory;
                                                                                                                                    try {
                                                                                                                                        if (!(ocExpressionSymbol instanceof OCCallExpressionSymbol)) {
                                                                                                                                            break Label_0122;
                                                                                                                                        }
                                                                                                                                        final OCExprSymbolClassifier ocExprSymbolClassifier2 = this;
                                                                                                                                        final OCExpressionSymbol ocExpressionSymbol3 = ocExpressionSymbol;
                                                                                                                                        final OCCallExpressionSymbol ocCallExpressionSymbol = (OCCallExpressionSymbol)ocExpressionSymbol3;
                                                                                                                                        ocExprValueCategory2 = ocExprSymbolClassifier2.classifyCallExpression(ocCallExpressionSymbol);
                                                                                                                                        if (ocExprValueCategory2 == null) {
                                                                                                                                            break Label_0087;
                                                                                                                                        }
                                                                                                                                        return ocExprValueCategory2;
                                                                                                                                    }
                                                                                                                                    catch (IllegalArgumentException ex3) {
                                                                                                                                        throw a(ex3);
                                                                                                                                    }
                                                                                                                                }
                                                                                                                                try {
                                                                                                                                    final OCExprSymbolClassifier ocExprSymbolClassifier2 = this;
                                                                                                                                    final OCExpressionSymbol ocExpressionSymbol3 = ocExpressionSymbol;
                                                                                                                                    final OCCallExpressionSymbol ocCallExpressionSymbol = (OCCallExpressionSymbol)ocExpressionSymbol3;
                                                                                                                                    ocExprValueCategory2 = ocExprSymbolClassifier2.classifyCallExpression(ocCallExpressionSymbol);
                                                                                                                                    if (ocExprValueCategory2 == null) {
                                                                                                                                        throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/resolve/OCExprSymbolClassifier", "classify"));
                                                                                                                                    }
                                                                                                                                }
                                                                                                                                catch (IllegalArgumentException ex4) {
                                                                                                                                    throw a(ex4);
                                                                                                                                }
                                                                                                                            }
                                                                                                                            return ocExprValueCategory2;
                                                                                                                            try {
                                                                                                                                if (!(ocExpressionSymbol instanceof OCCastExpressionSymbol)) {
                                                                                                                                    break Label_0183;
                                                                                                                                }
                                                                                                                                final OCExprSymbolClassifier ocExprSymbolClassifier3 = this;
                                                                                                                                final OCExpressionSymbol ocExpressionSymbol4 = ocExpressionSymbol;
                                                                                                                                final OCCastExpressionSymbol ocCastExpressionSymbol = (OCCastExpressionSymbol)ocExpressionSymbol4;
                                                                                                                                ocExprValueCategory3 = ocExprSymbolClassifier3.classifyCastExpression(ocCastExpressionSymbol);
                                                                                                                                if (ocExprValueCategory3 == null) {
                                                                                                                                    break Label_0148;
                                                                                                                                }
                                                                                                                                return ocExprValueCategory3;
                                                                                                                            }
                                                                                                                            catch (IllegalArgumentException ex5) {
                                                                                                                                throw a(ex5);
                                                                                                                            }
                                                                                                                        }
                                                                                                                        try {
                                                                                                                            final OCExprSymbolClassifier ocExprSymbolClassifier3 = this;
                                                                                                                            final OCExpressionSymbol ocExpressionSymbol4 = ocExpressionSymbol;
                                                                                                                            final OCCastExpressionSymbol ocCastExpressionSymbol = (OCCastExpressionSymbol)ocExpressionSymbol4;
                                                                                                                            ocExprValueCategory3 = ocExprSymbolClassifier3.classifyCastExpression(ocCastExpressionSymbol);
                                                                                                                            if (ocExprValueCategory3 == null) {
                                                                                                                                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/resolve/OCExprSymbolClassifier", "classify"));
                                                                                                                            }
                                                                                                                        }
                                                                                                                        catch (IllegalArgumentException ex6) {
                                                                                                                            throw a(ex6);
                                                                                                                        }
                                                                                                                    }
                                                                                                                    return ocExprValueCategory3;
                                                                                                                    try {
                                                                                                                        if (!(ocExpressionSymbol instanceof OCCommaExpressionSymbol)) {
                                                                                                                            break Label_0244;
                                                                                                                        }
                                                                                                                        final OCExprSymbolClassifier ocExprSymbolClassifier4 = this;
                                                                                                                        final OCExpressionSymbol ocExpressionSymbol5 = ocExpressionSymbol;
                                                                                                                        final OCCommaExpressionSymbol ocCommaExpressionSymbol = (OCCommaExpressionSymbol)ocExpressionSymbol5;
                                                                                                                        ocExprValueCategory4 = ocExprSymbolClassifier4.classifyCommaExpression(ocCommaExpressionSymbol);
                                                                                                                        if (ocExprValueCategory4 == null) {
                                                                                                                            break Label_0209;
                                                                                                                        }
                                                                                                                        return ocExprValueCategory4;
                                                                                                                    }
                                                                                                                    catch (IllegalArgumentException ex7) {
                                                                                                                        throw a(ex7);
                                                                                                                    }
                                                                                                                }
                                                                                                                try {
                                                                                                                    final OCExprSymbolClassifier ocExprSymbolClassifier4 = this;
                                                                                                                    final OCExpressionSymbol ocExpressionSymbol5 = ocExpressionSymbol;
                                                                                                                    final OCCommaExpressionSymbol ocCommaExpressionSymbol = (OCCommaExpressionSymbol)ocExpressionSymbol5;
                                                                                                                    ocExprValueCategory4 = ocExprSymbolClassifier4.classifyCommaExpression(ocCommaExpressionSymbol);
                                                                                                                    if (ocExprValueCategory4 == null) {
                                                                                                                        throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/resolve/OCExprSymbolClassifier", "classify"));
                                                                                                                    }
                                                                                                                }
                                                                                                                catch (IllegalArgumentException ex8) {
                                                                                                                    throw a(ex8);
                                                                                                                }
                                                                                                            }
                                                                                                            return ocExprValueCategory4;
                                                                                                            try {
                                                                                                                if (!(ocExpressionSymbol instanceof OCConditionalExpressionSymbol)) {
                                                                                                                    break Label_0305;
                                                                                                                }
                                                                                                                final OCExprSymbolClassifier ocExprSymbolClassifier5 = this;
                                                                                                                final OCExpressionSymbol ocExpressionSymbol6 = ocExpressionSymbol;
                                                                                                                final OCConditionalExpressionSymbol ocConditionalExpressionSymbol = (OCConditionalExpressionSymbol)ocExpressionSymbol6;
                                                                                                                ocExprValueCategory5 = ocExprSymbolClassifier5.classifyConditionalExpression(ocConditionalExpressionSymbol);
                                                                                                                if (ocExprValueCategory5 == null) {
                                                                                                                    break Label_0270;
                                                                                                                }
                                                                                                                return ocExprValueCategory5;
                                                                                                            }
                                                                                                            catch (IllegalArgumentException ex9) {
                                                                                                                throw a(ex9);
                                                                                                            }
                                                                                                        }
                                                                                                        try {
                                                                                                            final OCExprSymbolClassifier ocExprSymbolClassifier5 = this;
                                                                                                            final OCExpressionSymbol ocExpressionSymbol6 = ocExpressionSymbol;
                                                                                                            final OCConditionalExpressionSymbol ocConditionalExpressionSymbol = (OCConditionalExpressionSymbol)ocExpressionSymbol6;
                                                                                                            ocExprValueCategory5 = ocExprSymbolClassifier5.classifyConditionalExpression(ocConditionalExpressionSymbol);
                                                                                                            if (ocExprValueCategory5 == null) {
                                                                                                                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/resolve/OCExprSymbolClassifier", "classify"));
                                                                                                            }
                                                                                                        }
                                                                                                        catch (IllegalArgumentException ex10) {
                                                                                                            throw a(ex10);
                                                                                                        }
                                                                                                    }
                                                                                                    return ocExprValueCategory5;
                                                                                                    try {
                                                                                                        if (!(ocExpressionSymbol instanceof OCInitializerListExpressionSymbol)) {
                                                                                                            break Label_0366;
                                                                                                        }
                                                                                                        final OCExprSymbolClassifier ocExprSymbolClassifier6 = this;
                                                                                                        final OCExpressionSymbol ocExpressionSymbol7 = ocExpressionSymbol;
                                                                                                        final OCInitializerListExpressionSymbol ocInitializerListExpressionSymbol = (OCInitializerListExpressionSymbol)ocExpressionSymbol7;
                                                                                                        ocExprValueCategory6 = ocExprSymbolClassifier6.classifyInitializerListExpressionSymbol(ocInitializerListExpressionSymbol);
                                                                                                        if (ocExprValueCategory6 == null) {
                                                                                                            break Label_0331;
                                                                                                        }
                                                                                                        return ocExprValueCategory6;
                                                                                                    }
                                                                                                    catch (IllegalArgumentException ex11) {
                                                                                                        throw a(ex11);
                                                                                                    }
                                                                                                }
                                                                                                try {
                                                                                                    final OCExprSymbolClassifier ocExprSymbolClassifier6 = this;
                                                                                                    final OCExpressionSymbol ocExpressionSymbol7 = ocExpressionSymbol;
                                                                                                    final OCInitializerListExpressionSymbol ocInitializerListExpressionSymbol = (OCInitializerListExpressionSymbol)ocExpressionSymbol7;
                                                                                                    ocExprValueCategory6 = ocExprSymbolClassifier6.classifyInitializerListExpressionSymbol(ocInitializerListExpressionSymbol);
                                                                                                    if (ocExprValueCategory6 == null) {
                                                                                                        throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/resolve/OCExprSymbolClassifier", "classify"));
                                                                                                    }
                                                                                                }
                                                                                                catch (IllegalArgumentException ex12) {
                                                                                                    throw a(ex12);
                                                                                                }
                                                                                            }
                                                                                            return ocExprValueCategory6;
                                                                                            try {
                                                                                                if (!(ocExpressionSymbol instanceof OCLambdaExpressionSymbol)) {
                                                                                                    break Label_0427;
                                                                                                }
                                                                                                final OCExprSymbolClassifier ocExprSymbolClassifier7 = this;
                                                                                                final OCExpressionSymbol ocExpressionSymbol8 = ocExpressionSymbol;
                                                                                                final OCLambdaExpressionSymbol ocLambdaExpressionSymbol = (OCLambdaExpressionSymbol)ocExpressionSymbol8;
                                                                                                ocExprValueCategory7 = ocExprSymbolClassifier7.classifyLambdaExpression(ocLambdaExpressionSymbol);
                                                                                                if (ocExprValueCategory7 == null) {
                                                                                                    break Label_0392;
                                                                                                }
                                                                                                return ocExprValueCategory7;
                                                                                            }
                                                                                            catch (IllegalArgumentException ex13) {
                                                                                                throw a(ex13);
                                                                                            }
                                                                                        }
                                                                                        try {
                                                                                            final OCExprSymbolClassifier ocExprSymbolClassifier7 = this;
                                                                                            final OCExpressionSymbol ocExpressionSymbol8 = ocExpressionSymbol;
                                                                                            final OCLambdaExpressionSymbol ocLambdaExpressionSymbol = (OCLambdaExpressionSymbol)ocExpressionSymbol8;
                                                                                            ocExprValueCategory7 = ocExprSymbolClassifier7.classifyLambdaExpression(ocLambdaExpressionSymbol);
                                                                                            if (ocExprValueCategory7 == null) {
                                                                                                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/resolve/OCExprSymbolClassifier", "classify"));
                                                                                            }
                                                                                        }
                                                                                        catch (IllegalArgumentException ex14) {
                                                                                            throw a(ex14);
                                                                                        }
                                                                                    }
                                                                                    return ocExprValueCategory7;
                                                                                    try {
                                                                                        if (!(ocExpressionSymbol instanceof OCLiteralExpressionSymbol)) {
                                                                                            break Label_0488;
                                                                                        }
                                                                                        final OCExprSymbolClassifier ocExprSymbolClassifier8 = this;
                                                                                        final OCExpressionSymbol ocExpressionSymbol9 = ocExpressionSymbol;
                                                                                        final OCLiteralExpressionSymbol ocLiteralExpressionSymbol = (OCLiteralExpressionSymbol)ocExpressionSymbol9;
                                                                                        ocExprValueCategory8 = ocExprSymbolClassifier8.classifyLiteralExpression(ocLiteralExpressionSymbol);
                                                                                        if (ocExprValueCategory8 == null) {
                                                                                            break Label_0453;
                                                                                        }
                                                                                        return ocExprValueCategory8;
                                                                                    }
                                                                                    catch (IllegalArgumentException ex15) {
                                                                                        throw a(ex15);
                                                                                    }
                                                                                }
                                                                                try {
                                                                                    final OCExprSymbolClassifier ocExprSymbolClassifier8 = this;
                                                                                    final OCExpressionSymbol ocExpressionSymbol9 = ocExpressionSymbol;
                                                                                    final OCLiteralExpressionSymbol ocLiteralExpressionSymbol = (OCLiteralExpressionSymbol)ocExpressionSymbol9;
                                                                                    ocExprValueCategory8 = ocExprSymbolClassifier8.classifyLiteralExpression(ocLiteralExpressionSymbol);
                                                                                    if (ocExprValueCategory8 == null) {
                                                                                        throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/resolve/OCExprSymbolClassifier", "classify"));
                                                                                    }
                                                                                }
                                                                                catch (IllegalArgumentException ex16) {
                                                                                    throw a(ex16);
                                                                                }
                                                                            }
                                                                            return ocExprValueCategory8;
                                                                            try {
                                                                                if (!(ocExpressionSymbol instanceof OCNewExpressionSymbol)) {
                                                                                    break Label_0549;
                                                                                }
                                                                                final OCExprSymbolClassifier ocExprSymbolClassifier9 = this;
                                                                                final OCExpressionSymbol ocExpressionSymbol10 = ocExpressionSymbol;
                                                                                final OCNewExpressionSymbol ocNewExpressionSymbol = (OCNewExpressionSymbol)ocExpressionSymbol10;
                                                                                ocExprValueCategory9 = ocExprSymbolClassifier9.classifyCppNewExpression(ocNewExpressionSymbol);
                                                                                if (ocExprValueCategory9 == null) {
                                                                                    break Label_0514;
                                                                                }
                                                                                return ocExprValueCategory9;
                                                                            }
                                                                            catch (IllegalArgumentException ex17) {
                                                                                throw a(ex17);
                                                                            }
                                                                        }
                                                                        try {
                                                                            final OCExprSymbolClassifier ocExprSymbolClassifier9 = this;
                                                                            final OCExpressionSymbol ocExpressionSymbol10 = ocExpressionSymbol;
                                                                            final OCNewExpressionSymbol ocNewExpressionSymbol = (OCNewExpressionSymbol)ocExpressionSymbol10;
                                                                            ocExprValueCategory9 = ocExprSymbolClassifier9.classifyCppNewExpression(ocNewExpressionSymbol);
                                                                            if (ocExprValueCategory9 == null) {
                                                                                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/resolve/OCExprSymbolClassifier", "classify"));
                                                                            }
                                                                        }
                                                                        catch (IllegalArgumentException ex18) {
                                                                            throw a(ex18);
                                                                        }
                                                                    }
                                                                    return ocExprValueCategory9;
                                                                    try {
                                                                        if (!(ocExpressionSymbol instanceof OCUnaryExpressionSymbolBase)) {
                                                                            break Label_0610;
                                                                        }
                                                                        final OCExprSymbolClassifier ocExprSymbolClassifier10 = this;
                                                                        final OCExpressionSymbol ocExpressionSymbol11 = ocExpressionSymbol;
                                                                        final OCUnaryExpressionSymbolBase ocUnaryExpressionSymbolBase = (OCUnaryExpressionSymbolBase)ocExpressionSymbol11;
                                                                        ocExprValueCategory10 = ocExprSymbolClassifier10.classifyUnaryExpression(ocUnaryExpressionSymbolBase);
                                                                        if (ocExprValueCategory10 == null) {
                                                                            break Label_0575;
                                                                        }
                                                                        return ocExprValueCategory10;
                                                                    }
                                                                    catch (IllegalArgumentException ex19) {
                                                                        throw a(ex19);
                                                                    }
                                                                }
                                                                try {
                                                                    final OCExprSymbolClassifier ocExprSymbolClassifier10 = this;
                                                                    final OCExpressionSymbol ocExpressionSymbol11 = ocExpressionSymbol;
                                                                    final OCUnaryExpressionSymbolBase ocUnaryExpressionSymbolBase = (OCUnaryExpressionSymbolBase)ocExpressionSymbol11;
                                                                    ocExprValueCategory10 = ocExprSymbolClassifier10.classifyUnaryExpression(ocUnaryExpressionSymbolBase);
                                                                    if (ocExprValueCategory10 == null) {
                                                                        throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/resolve/OCExprSymbolClassifier", "classify"));
                                                                    }
                                                                }
                                                                catch (IllegalArgumentException ex20) {
                                                                    throw a(ex20);
                                                                }
                                                            }
                                                            return ocExprValueCategory10;
                                                            try {
                                                                if (!(ocExpressionSymbol instanceof OCQualifiedExpressionSymbol)) {
                                                                    break Label_0671;
                                                                }
                                                                final OCExprSymbolClassifier ocExprSymbolClassifier11 = this;
                                                                final OCExpressionSymbol ocExpressionSymbol12 = ocExpressionSymbol;
                                                                final OCQualifiedExpressionSymbol ocQualifiedExpressionSymbol = (OCQualifiedExpressionSymbol)ocExpressionSymbol12;
                                                                ocExprValueCategory11 = ocExprSymbolClassifier11.classifyQualifiedExpression(ocQualifiedExpressionSymbol);
                                                                if (ocExprValueCategory11 == null) {
                                                                    break Label_0636;
                                                                }
                                                                return ocExprValueCategory11;
                                                            }
                                                            catch (IllegalArgumentException ex21) {
                                                                throw a(ex21);
                                                            }
                                                        }
                                                        try {
                                                            final OCExprSymbolClassifier ocExprSymbolClassifier11 = this;
                                                            final OCExpressionSymbol ocExpressionSymbol12 = ocExpressionSymbol;
                                                            final OCQualifiedExpressionSymbol ocQualifiedExpressionSymbol = (OCQualifiedExpressionSymbol)ocExpressionSymbol12;
                                                            ocExprValueCategory11 = ocExprSymbolClassifier11.classifyQualifiedExpression(ocQualifiedExpressionSymbol);
                                                            if (ocExprValueCategory11 == null) {
                                                                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/resolve/OCExprSymbolClassifier", "classify"));
                                                            }
                                                        }
                                                        catch (IllegalArgumentException ex22) {
                                                            throw a(ex22);
                                                        }
                                                    }
                                                    return ocExprValueCategory11;
                                                    try {
                                                        if (!(ocExpressionSymbol instanceof OCReferenceExpressionSymbol)) {
                                                            break Label_0732;
                                                        }
                                                        final OCExprSymbolClassifier ocExprSymbolClassifier12 = this;
                                                        final OCExpressionSymbol ocExpressionSymbol13 = ocExpressionSymbol;
                                                        final OCReferenceExpressionSymbol ocReferenceExpressionSymbol = (OCReferenceExpressionSymbol)ocExpressionSymbol13;
                                                        ocExprValueCategory12 = ocExprSymbolClassifier12.classifyReferenceExpression(ocReferenceExpressionSymbol);
                                                        if (ocExprValueCategory12 == null) {
                                                            break Label_0697;
                                                        }
                                                        return ocExprValueCategory12;
                                                    }
                                                    catch (IllegalArgumentException ex23) {
                                                        throw a(ex23);
                                                    }
                                                }
                                                try {
                                                    final OCExprSymbolClassifier ocExprSymbolClassifier12 = this;
                                                    final OCExpressionSymbol ocExpressionSymbol13 = ocExpressionSymbol;
                                                    final OCReferenceExpressionSymbol ocReferenceExpressionSymbol = (OCReferenceExpressionSymbol)ocExpressionSymbol13;
                                                    ocExprValueCategory12 = ocExprSymbolClassifier12.classifyReferenceExpression(ocReferenceExpressionSymbol);
                                                    if (ocExprValueCategory12 == null) {
                                                        throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/resolve/OCExprSymbolClassifier", "classify"));
                                                    }
                                                }
                                                catch (IllegalArgumentException ex24) {
                                                    throw a(ex24);
                                                }
                                            }
                                            return ocExprValueCategory12;
                                            try {
                                                if (!(ocExpressionSymbol instanceof OCSizeofExpressionSymbol)) {
                                                    break Label_0793;
                                                }
                                                final OCExprSymbolClassifier ocExprSymbolClassifier13 = this;
                                                final OCExpressionSymbol ocExpressionSymbol14 = ocExpressionSymbol;
                                                final OCSizeofExpressionSymbol ocSizeofExpressionSymbol = (OCSizeofExpressionSymbol)ocExpressionSymbol14;
                                                ocExprValueCategory13 = ocExprSymbolClassifier13.classifySizeofExpression(ocSizeofExpressionSymbol);
                                                if (ocExprValueCategory13 == null) {
                                                    break Label_0758;
                                                }
                                                return ocExprValueCategory13;
                                            }
                                            catch (IllegalArgumentException ex25) {
                                                throw a(ex25);
                                            }
                                        }
                                        try {
                                            final OCExprSymbolClassifier ocExprSymbolClassifier13 = this;
                                            final OCExpressionSymbol ocExpressionSymbol14 = ocExpressionSymbol;
                                            final OCSizeofExpressionSymbol ocSizeofExpressionSymbol = (OCSizeofExpressionSymbol)ocExpressionSymbol14;
                                            ocExprValueCategory13 = ocExprSymbolClassifier13.classifySizeofExpression(ocSizeofExpressionSymbol);
                                            if (ocExprValueCategory13 == null) {
                                                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/resolve/OCExprSymbolClassifier", "classify"));
                                            }
                                        }
                                        catch (IllegalArgumentException ex26) {
                                            throw a(ex26);
                                        }
                                    }
                                    return ocExprValueCategory13;
                                    try {
                                        if (!(ocExpressionSymbol instanceof OCUnknownExpressionSymbol)) {
                                            break Label_0854;
                                        }
                                        final OCExprSymbolClassifier ocExprSymbolClassifier14 = this;
                                        final OCExpressionSymbol ocExpressionSymbol15 = ocExpressionSymbol;
                                        final OCUnknownExpressionSymbol ocUnknownExpressionSymbol = (OCUnknownExpressionSymbol)ocExpressionSymbol15;
                                        ocExprValueCategory14 = ocExprSymbolClassifier14.classifyUnknownExpression(ocUnknownExpressionSymbol);
                                        if (ocExprValueCategory14 == null) {
                                            break Label_0819;
                                        }
                                        return ocExprValueCategory14;
                                    }
                                    catch (IllegalArgumentException ex27) {
                                        throw a(ex27);
                                    }
                                }
                                try {
                                    final OCExprSymbolClassifier ocExprSymbolClassifier14 = this;
                                    final OCExpressionSymbol ocExpressionSymbol15 = ocExpressionSymbol;
                                    final OCUnknownExpressionSymbol ocUnknownExpressionSymbol = (OCUnknownExpressionSymbol)ocExpressionSymbol15;
                                    ocExprValueCategory14 = ocExprSymbolClassifier14.classifyUnknownExpression(ocUnknownExpressionSymbol);
                                    if (ocExprValueCategory14 == null) {
                                        throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/resolve/OCExprSymbolClassifier", "classify"));
                                    }
                                }
                                catch (IllegalArgumentException ex28) {
                                    throw a(ex28);
                                }
                            }
                            return ocExprValueCategory14;
                            try {
                                if (!(ocExpressionSymbol instanceof OCVariadicPackExpressionSymbol)) {
                                    break Label_0915;
                                }
                                final OCExprSymbolClassifier ocExprSymbolClassifier15 = this;
                                final OCExpressionSymbol ocExpressionSymbol16 = ocExpressionSymbol;
                                final OCVariadicPackExpressionSymbol ocVariadicPackExpressionSymbol = (OCVariadicPackExpressionSymbol)ocExpressionSymbol16;
                                ocExprValueCategory15 = ocExprSymbolClassifier15.classifyVariadicPackExpression(ocVariadicPackExpressionSymbol);
                                if (ocExprValueCategory15 == null) {
                                    break Label_0880;
                                }
                                return ocExprValueCategory15;
                            }
                            catch (IllegalArgumentException ex29) {
                                throw a(ex29);
                            }
                        }
                        try {
                            final OCExprSymbolClassifier ocExprSymbolClassifier15 = this;
                            final OCExpressionSymbol ocExpressionSymbol16 = ocExpressionSymbol;
                            final OCVariadicPackExpressionSymbol ocVariadicPackExpressionSymbol = (OCVariadicPackExpressionSymbol)ocExpressionSymbol16;
                            ocExprValueCategory15 = ocExprSymbolClassifier15.classifyVariadicPackExpression(ocVariadicPackExpressionSymbol);
                            if (ocExprValueCategory15 == null) {
                                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/resolve/OCExprSymbolClassifier", "classify"));
                            }
                        }
                        catch (IllegalArgumentException ex30) {
                            throw a(ex30);
                        }
                    }
                    return ocExprValueCategory15;
                    try {
                        if (!(ocExpressionSymbol instanceof OCArrayIndexExpressionSymbol)) {
                            break Label_0976;
                        }
                        final OCExprSymbolClassifier ocExprSymbolClassifier16 = this;
                        final OCExpressionSymbol ocExpressionSymbol17 = ocExpressionSymbol;
                        final OCArrayIndexExpressionSymbol ocArrayIndexExpressionSymbol = (OCArrayIndexExpressionSymbol)ocExpressionSymbol17;
                        ocExprValueCategory16 = ocExprSymbolClassifier16.a(ocArrayIndexExpressionSymbol);
                        if (ocExprValueCategory16 == null) {
                            break Label_0941;
                        }
                        return ocExprValueCategory16;
                    }
                    catch (IllegalArgumentException ex31) {
                        throw a(ex31);
                    }
                }
                try {
                    final OCExprSymbolClassifier ocExprSymbolClassifier16 = this;
                    final OCExpressionSymbol ocExpressionSymbol17 = ocExpressionSymbol;
                    final OCArrayIndexExpressionSymbol ocArrayIndexExpressionSymbol = (OCArrayIndexExpressionSymbol)ocExpressionSymbol17;
                    ocExprValueCategory16 = ocExprSymbolClassifier16.a(ocArrayIndexExpressionSymbol);
                    if (ocExprValueCategory16 == null) {
                        throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/resolve/OCExprSymbolClassifier", "classify"));
                    }
                }
                catch (IllegalArgumentException ex32) {
                    throw a(ex32);
                }
            }
            return ocExprValueCategory16;
            try {
                OCLog.LOG.error("Unknown expression " + ocExpressionSymbol);
                lValue = OCExprValueCategory.LValue;
                if (lValue == null) {
                    throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/resolve/OCExprSymbolClassifier", "classify"));
                }
            }
            catch (IllegalArgumentException ex33) {
                throw a(ex33);
            }
        }
        return lValue;
    }
    
    @NotNull
    public OCExprValueCategory classifyUnknownExpression(@NotNull final OCUnknownExpressionSymbol ocUnknownExpressionSymbol) {
        try {
            if (ocUnknownExpressionSymbol == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "symbol", "com/jetbrains/cidr/lang/resolve/OCExprSymbolClassifier", "classifyUnknownExpression"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        OCExprValueCategory lValue;
        try {
            lValue = OCExprValueCategory.LValue;
            if (lValue == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/resolve/OCExprSymbolClassifier", "classifyUnknownExpression"));
            }
        }
        catch (IllegalArgumentException ex2) {
            throw a(ex2);
        }
        return lValue;
    }
    
    @NotNull
    public OCExprValueCategory classifyInitializerListExpressionSymbol(@NotNull final OCInitializerListExpressionSymbol ocInitializerListExpressionSymbol) {
        try {
            if (ocInitializerListExpressionSymbol == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "symbol", "com/jetbrains/cidr/lang/resolve/OCExprSymbolClassifier", "classifyInitializerListExpressionSymbol"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        OCExprValueCategory prValue;
        try {
            prValue = OCExprValueCategory.PRValue;
            if (prValue == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/resolve/OCExprSymbolClassifier", "classifyInitializerListExpressionSymbol"));
            }
        }
        catch (IllegalArgumentException ex2) {
            throw a(ex2);
        }
        return prValue;
    }
    
    public OCExprValueCategory classifyCommaExpression(final OCCommaExpressionSymbol ocCommaExpressionSymbol) {
        try {
            if (this.classify(ocCommaExpressionSymbol.getTailExpression()) == OCExprValueCategory.LValue) {
                return OCExprValueCategory.LValue;
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        return OCExprValueCategory.PRValue;
    }
    
    @NotNull
    public OCExprValueCategory classifyConditionalExpression(final OCConditionalExpressionSymbol p0) {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     0: aload_1        
        //     1: invokevirtual   com/jetbrains/cidr/lang/symbols/expression/OCConditionalExpressionSymbol.getLeftOperandOrCondition:()Lcom/jetbrains/cidr/lang/symbols/expression/OCExpressionSymbol;
        //     4: astore_2       
        //     5: aload_1        
        //     6: invokevirtual   com/jetbrains/cidr/lang/symbols/expression/OCConditionalExpressionSymbol.getRightOperand:()Lcom/jetbrains/cidr/lang/symbols/expression/OCExpressionSymbol;
        //     9: astore_3       
        //    10: aload_2        
        //    11: ifnonnull       74
        //    14: aload_3        
        //    15: ifnonnull       74
        //    18: goto            25
        //    21: invokestatic    com/jetbrains/cidr/lang/resolve/OCExprSymbolClassifier.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //    24: athrow         
        //    25: getstatic       com/jetbrains/cidr/lang/resolve/OCExprValueCategory.PRValue:Lcom/jetbrains/cidr/lang/resolve/OCExprValueCategory;
        //    28: dup            
        //    29: ifnonnull       73
        //    32: goto            39
        //    35: invokestatic    com/jetbrains/cidr/lang/resolve/OCExprSymbolClassifier.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //    38: athrow         
        //    39: new             Ljava/lang/IllegalStateException;
        //    42: dup            
        //    43: ldc             "@NotNull method %s.%s must not return null"
        //    45: ldc             2
        //    47: anewarray       Ljava/lang/Object;
        //    50: dup            
        //    51: ldc             0
        //    53: ldc             "com/jetbrains/cidr/lang/resolve/OCExprSymbolClassifier"
        //    55: aastore        
        //    56: dup            
        //    57: ldc             1
        //    59: ldc             "classifyConditionalExpression"
        //    61: aastore        
        //    62: invokestatic    java/lang/String.format:(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
        //    65: invokespecial   java/lang/IllegalStateException.<init>:(Ljava/lang/String;)V
        //    68: athrow         
        //    69: invokestatic    com/jetbrains/cidr/lang/resolve/OCExprSymbolClassifier.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //    72: athrow         
        //    73: areturn        
        //    74: aload_2        
        //    75: ifnonnull       129
        //    78: aload_0        
        //    79: aload_3        
        //    80: invokevirtual   com/jetbrains/cidr/lang/resolve/OCExprSymbolClassifier.classify:(Lcom/jetbrains/cidr/lang/symbols/expression/OCExpressionSymbol;)Lcom/jetbrains/cidr/lang/resolve/OCExprValueCategory;
        //    83: dup            
        //    84: ifnonnull       128
        //    87: goto            94
        //    90: invokestatic    com/jetbrains/cidr/lang/resolve/OCExprSymbolClassifier.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //    93: athrow         
        //    94: new             Ljava/lang/IllegalStateException;
        //    97: dup            
        //    98: ldc             "@NotNull method %s.%s must not return null"
        //   100: ldc             2
        //   102: anewarray       Ljava/lang/Object;
        //   105: dup            
        //   106: ldc             0
        //   108: ldc             "com/jetbrains/cidr/lang/resolve/OCExprSymbolClassifier"
        //   110: aastore        
        //   111: dup            
        //   112: ldc             1
        //   114: ldc             "classifyConditionalExpression"
        //   116: aastore        
        //   117: invokestatic    java/lang/String.format:(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
        //   120: invokespecial   java/lang/IllegalStateException.<init>:(Ljava/lang/String;)V
        //   123: athrow         
        //   124: invokestatic    com/jetbrains/cidr/lang/resolve/OCExprSymbolClassifier.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   127: athrow         
        //   128: areturn        
        //   129: aload_3        
        //   130: ifnonnull       184
        //   133: aload_0        
        //   134: aload_2        
        //   135: invokevirtual   com/jetbrains/cidr/lang/resolve/OCExprSymbolClassifier.classify:(Lcom/jetbrains/cidr/lang/symbols/expression/OCExpressionSymbol;)Lcom/jetbrains/cidr/lang/resolve/OCExprValueCategory;
        //   138: dup            
        //   139: ifnonnull       183
        //   142: goto            149
        //   145: invokestatic    com/jetbrains/cidr/lang/resolve/OCExprSymbolClassifier.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   148: athrow         
        //   149: new             Ljava/lang/IllegalStateException;
        //   152: dup            
        //   153: ldc             "@NotNull method %s.%s must not return null"
        //   155: ldc             2
        //   157: anewarray       Ljava/lang/Object;
        //   160: dup            
        //   161: ldc             0
        //   163: ldc             "com/jetbrains/cidr/lang/resolve/OCExprSymbolClassifier"
        //   165: aastore        
        //   166: dup            
        //   167: ldc             1
        //   169: ldc             "classifyConditionalExpression"
        //   171: aastore        
        //   172: invokestatic    java/lang/String.format:(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
        //   175: invokespecial   java/lang/IllegalStateException.<init>:(Ljava/lang/String;)V
        //   178: athrow         
        //   179: invokestatic    com/jetbrains/cidr/lang/resolve/OCExprSymbolClassifier.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   182: athrow         
        //   183: areturn        
        //   184: aload_0        
        //   185: aload_2        
        //   186: invokevirtual   com/jetbrains/cidr/lang/resolve/OCExprSymbolClassifier.classify:(Lcom/jetbrains/cidr/lang/symbols/expression/OCExpressionSymbol;)Lcom/jetbrains/cidr/lang/resolve/OCExprValueCategory;
        //   189: astore          4
        //   191: aload_0        
        //   192: aload_3        
        //   193: invokevirtual   com/jetbrains/cidr/lang/resolve/OCExprSymbolClassifier.classify:(Lcom/jetbrains/cidr/lang/symbols/expression/OCExpressionSymbol;)Lcom/jetbrains/cidr/lang/resolve/OCExprValueCategory;
        //   196: astore          5
        //   198: aload           4
        //   200: aload           5
        //   202: if_acmpne       314
        //   205: aload           4
        //   207: getstatic       com/jetbrains/cidr/lang/resolve/OCExprValueCategory.PRValue:Lcom/jetbrains/cidr/lang/resolve/OCExprValueCategory;
        //   210: if_acmpeq       314
        //   213: goto            220
        //   216: invokestatic    com/jetbrains/cidr/lang/resolve/OCExprSymbolClassifier.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   219: athrow         
        //   220: aload_2        
        //   221: aload_0        
        //   222: getfield        com/jetbrains/cidr/lang/resolve/OCExprSymbolClassifier.myContext:Lcom/jetbrains/cidr/lang/symbols/OCResolveContext;
        //   225: invokevirtual   com/jetbrains/cidr/lang/symbols/expression/OCExpressionSymbol.getResolvedType:(Lcom/jetbrains/cidr/lang/symbols/OCResolveContext;)Lcom/jetbrains/cidr/lang/types/OCType;
        //   228: astore          6
        //   230: aload_3        
        //   231: aload_0        
        //   232: getfield        com/jetbrains/cidr/lang/resolve/OCExprSymbolClassifier.myContext:Lcom/jetbrains/cidr/lang/symbols/OCResolveContext;
        //   235: invokevirtual   com/jetbrains/cidr/lang/symbols/expression/OCExpressionSymbol.getResolvedType:(Lcom/jetbrains/cidr/lang/symbols/OCResolveContext;)Lcom/jetbrains/cidr/lang/types/OCType;
        //   238: astore          7
        //   240: aload           6
        //   242: ifnull          314
        //   245: aload           6
        //   247: aload           7
        //   249: aload_0        
        //   250: getfield        com/jetbrains/cidr/lang/resolve/OCExprSymbolClassifier.myContext:Lcom/jetbrains/cidr/lang/symbols/OCResolveContext;
        //   253: invokevirtual   com/jetbrains/cidr/lang/types/OCType.equals:(Ljava/lang/Object;Lcom/jetbrains/cidr/lang/symbols/OCResolveContext;)Z
        //   256: ifeq            314
        //   259: goto            266
        //   262: invokestatic    com/jetbrains/cidr/lang/resolve/OCExprSymbolClassifier.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   265: athrow         
        //   266: aload           4
        //   268: dup            
        //   269: ifnonnull       313
        //   272: goto            279
        //   275: invokestatic    com/jetbrains/cidr/lang/resolve/OCExprSymbolClassifier.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   278: athrow         
        //   279: new             Ljava/lang/IllegalStateException;
        //   282: dup            
        //   283: ldc             "@NotNull method %s.%s must not return null"
        //   285: ldc             2
        //   287: anewarray       Ljava/lang/Object;
        //   290: dup            
        //   291: ldc             0
        //   293: ldc             "com/jetbrains/cidr/lang/resolve/OCExprSymbolClassifier"
        //   295: aastore        
        //   296: dup            
        //   297: ldc             1
        //   299: ldc             "classifyConditionalExpression"
        //   301: aastore        
        //   302: invokestatic    java/lang/String.format:(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
        //   305: invokespecial   java/lang/IllegalStateException.<init>:(Ljava/lang/String;)V
        //   308: athrow         
        //   309: invokestatic    com/jetbrains/cidr/lang/resolve/OCExprSymbolClassifier.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   312: athrow         
        //   313: areturn        
        //   314: getstatic       com/jetbrains/cidr/lang/resolve/OCExprValueCategory.PRValue:Lcom/jetbrains/cidr/lang/resolve/OCExprValueCategory;
        //   317: dup            
        //   318: ifnonnull       355
        //   321: new             Ljava/lang/IllegalStateException;
        //   324: dup            
        //   325: ldc             "@NotNull method %s.%s must not return null"
        //   327: ldc             2
        //   329: anewarray       Ljava/lang/Object;
        //   332: dup            
        //   333: ldc             0
        //   335: ldc             "com/jetbrains/cidr/lang/resolve/OCExprSymbolClassifier"
        //   337: aastore        
        //   338: dup            
        //   339: ldc             1
        //   341: ldc             "classifyConditionalExpression"
        //   343: aastore        
        //   344: invokestatic    java/lang/String.format:(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
        //   347: invokespecial   java/lang/IllegalStateException.<init>:(Ljava/lang/String;)V
        //   350: athrow         
        //   351: invokestatic    com/jetbrains/cidr/lang/resolve/OCExprSymbolClassifier.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   354: athrow         
        //   355: areturn        
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                                
        //  -----  -----  -----  -----  ------------------------------------
        //  10     18     21     25     Ljava/lang/IllegalArgumentException;
        //  14     32     35     39     Ljava/lang/IllegalArgumentException;
        //  25     69     69     73     Ljava/lang/IllegalArgumentException;
        //  74     87     90     94     Ljava/lang/IllegalArgumentException;
        //  78     124    124    128    Ljava/lang/IllegalArgumentException;
        //  129    142    145    149    Ljava/lang/IllegalArgumentException;
        //  133    179    179    183    Ljava/lang/IllegalArgumentException;
        //  198    213    216    220    Ljava/lang/IllegalArgumentException;
        //  240    259    262    266    Ljava/lang/IllegalArgumentException;
        //  245    272    275    279    Ljava/lang/IllegalArgumentException;
        //  266    309    309    313    Ljava/lang/IllegalArgumentException;
        //  314    351    351    355    Ljava/lang/IllegalArgumentException;
        // 
        // The error that occurred was:
        // 
        // java.lang.IllegalStateException: Expression is linked from several locations: Label_0025:
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
    public OCExprValueCategory classifyBinaryExpression(@NotNull final OCBinaryExpressionSymbol p0) {
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
        //    18: ldc             "e"
        //    20: aastore        
        //    21: dup            
        //    22: ldc             1
        //    24: ldc             "com/jetbrains/cidr/lang/resolve/OCExprSymbolClassifier"
        //    26: aastore        
        //    27: dup            
        //    28: ldc             2
        //    30: ldc             "classifyBinaryExpression"
        //    32: aastore        
        //    33: invokestatic    java/lang/String.format:(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
        //    36: invokespecial   java/lang/IllegalArgumentException.<init>:(Ljava/lang/String;)V
        //    39: athrow         
        //    40: invokestatic    com/jetbrains/cidr/lang/resolve/OCExprSymbolClassifier.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //    43: athrow         
        //    44: aload_1        
        //    45: aload_0        
        //    46: getfield        com/jetbrains/cidr/lang/resolve/OCExprSymbolClassifier.myContext:Lcom/jetbrains/cidr/lang/symbols/OCResolveContext;
        //    49: invokevirtual   com/jetbrains/cidr/lang/symbols/expression/OCBinaryExpressionSymbol.getCustomReturnType:(Lcom/jetbrains/cidr/lang/symbols/OCResolveContext;)Lcom/jetbrains/cidr/lang/types/OCType;
        //    52: astore_2       
        //    53: aload_2        
        //    54: ifnull          107
        //    57: aload_2        
        //    58: invokestatic    com/jetbrains/cidr/lang/resolve/OCExprSymbolClassifier.a:(Lcom/jetbrains/cidr/lang/types/OCType;)Lcom/jetbrains/cidr/lang/resolve/OCExprValueCategory;
        //    61: dup            
        //    62: ifnonnull       106
        //    65: goto            72
        //    68: invokestatic    com/jetbrains/cidr/lang/resolve/OCExprSymbolClassifier.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //    71: athrow         
        //    72: new             Ljava/lang/IllegalStateException;
        //    75: dup            
        //    76: ldc             "@NotNull method %s.%s must not return null"
        //    78: ldc             2
        //    80: anewarray       Ljava/lang/Object;
        //    83: dup            
        //    84: ldc             0
        //    86: ldc             "com/jetbrains/cidr/lang/resolve/OCExprSymbolClassifier"
        //    88: aastore        
        //    89: dup            
        //    90: ldc             1
        //    92: ldc             "classifyBinaryExpression"
        //    94: aastore        
        //    95: invokestatic    java/lang/String.format:(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
        //    98: invokespecial   java/lang/IllegalStateException.<init>:(Ljava/lang/String;)V
        //   101: athrow         
        //   102: invokestatic    com/jetbrains/cidr/lang/resolve/OCExprSymbolClassifier.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   105: athrow         
        //   106: areturn        
        //   107: aload_1        
        //   108: invokevirtual   com/jetbrains/cidr/lang/symbols/expression/OCBinaryExpressionSymbol.getOperator:()Lcom/jetbrains/cidr/lang/parser/OCElementType;
        //   111: astore_3       
        //   112: aload_3        
        //   113: getstatic       com/jetbrains/cidr/lang/parser/OCTokenTypes.DOT_MUL:Lcom/jetbrains/cidr/lang/parser/OCPunctuatorElementType;
        //   116: if_acmpne       191
        //   119: aload_0        
        //   120: aload_1        
        //   121: invokevirtual   com/jetbrains/cidr/lang/symbols/expression/OCBinaryExpressionSymbol.getRightOperand:()Lcom/jetbrains/cidr/lang/symbols/expression/OCExpressionSymbol;
        //   124: invokespecial   com/jetbrains/cidr/lang/resolve/OCExprSymbolClassifier.a:(Lcom/jetbrains/cidr/lang/symbols/expression/OCExpressionSymbol;)Z
        //   127: ifeq            191
        //   130: goto            137
        //   133: invokestatic    com/jetbrains/cidr/lang/resolve/OCExprSymbolClassifier.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   136: athrow         
        //   137: aload_0        
        //   138: aload_1        
        //   139: invokevirtual   com/jetbrains/cidr/lang/symbols/expression/OCBinaryExpressionSymbol.getLeftOperand:()Lcom/jetbrains/cidr/lang/symbols/expression/OCExpressionSymbol;
        //   142: invokevirtual   com/jetbrains/cidr/lang/resolve/OCExprSymbolClassifier.classify:(Lcom/jetbrains/cidr/lang/symbols/expression/OCExpressionSymbol;)Lcom/jetbrains/cidr/lang/resolve/OCExprValueCategory;
        //   145: dup            
        //   146: ifnonnull       190
        //   149: goto            156
        //   152: invokestatic    com/jetbrains/cidr/lang/resolve/OCExprSymbolClassifier.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   155: athrow         
        //   156: new             Ljava/lang/IllegalStateException;
        //   159: dup            
        //   160: ldc             "@NotNull method %s.%s must not return null"
        //   162: ldc             2
        //   164: anewarray       Ljava/lang/Object;
        //   167: dup            
        //   168: ldc             0
        //   170: ldc             "com/jetbrains/cidr/lang/resolve/OCExprSymbolClassifier"
        //   172: aastore        
        //   173: dup            
        //   174: ldc             1
        //   176: ldc             "classifyBinaryExpression"
        //   178: aastore        
        //   179: invokestatic    java/lang/String.format:(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
        //   182: invokespecial   java/lang/IllegalStateException.<init>:(Ljava/lang/String;)V
        //   185: athrow         
        //   186: invokestatic    com/jetbrains/cidr/lang/resolve/OCExprSymbolClassifier.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   189: athrow         
        //   190: areturn        
        //   191: aload_3        
        //   192: getstatic       com/jetbrains/cidr/lang/parser/OCTokenTypes.DEREF_MUL:Lcom/jetbrains/cidr/lang/parser/OCPunctuatorElementType;
        //   195: if_acmpne       265
        //   198: aload_0        
        //   199: aload_1        
        //   200: invokevirtual   com/jetbrains/cidr/lang/symbols/expression/OCBinaryExpressionSymbol.getRightOperand:()Lcom/jetbrains/cidr/lang/symbols/expression/OCExpressionSymbol;
        //   203: invokespecial   com/jetbrains/cidr/lang/resolve/OCExprSymbolClassifier.a:(Lcom/jetbrains/cidr/lang/symbols/expression/OCExpressionSymbol;)Z
        //   206: ifeq            265
        //   209: goto            216
        //   212: invokestatic    com/jetbrains/cidr/lang/resolve/OCExprSymbolClassifier.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   215: athrow         
        //   216: getstatic       com/jetbrains/cidr/lang/resolve/OCExprValueCategory.LValue:Lcom/jetbrains/cidr/lang/resolve/OCExprValueCategory;
        //   219: dup            
        //   220: ifnonnull       264
        //   223: goto            230
        //   226: invokestatic    com/jetbrains/cidr/lang/resolve/OCExprSymbolClassifier.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   229: athrow         
        //   230: new             Ljava/lang/IllegalStateException;
        //   233: dup            
        //   234: ldc             "@NotNull method %s.%s must not return null"
        //   236: ldc             2
        //   238: anewarray       Ljava/lang/Object;
        //   241: dup            
        //   242: ldc             0
        //   244: ldc             "com/jetbrains/cidr/lang/resolve/OCExprSymbolClassifier"
        //   246: aastore        
        //   247: dup            
        //   248: ldc             1
        //   250: ldc             "classifyBinaryExpression"
        //   252: aastore        
        //   253: invokestatic    java/lang/String.format:(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
        //   256: invokespecial   java/lang/IllegalStateException.<init>:(Ljava/lang/String;)V
        //   259: athrow         
        //   260: invokestatic    com/jetbrains/cidr/lang/resolve/OCExprSymbolClassifier.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   263: athrow         
        //   264: areturn        
        //   265: getstatic       com/jetbrains/cidr/lang/resolve/OCExprValueCategory.PRValue:Lcom/jetbrains/cidr/lang/resolve/OCExprValueCategory;
        //   268: dup            
        //   269: ifnonnull       306
        //   272: new             Ljava/lang/IllegalStateException;
        //   275: dup            
        //   276: ldc             "@NotNull method %s.%s must not return null"
        //   278: ldc             2
        //   280: anewarray       Ljava/lang/Object;
        //   283: dup            
        //   284: ldc             0
        //   286: ldc             "com/jetbrains/cidr/lang/resolve/OCExprSymbolClassifier"
        //   288: aastore        
        //   289: dup            
        //   290: ldc             1
        //   292: ldc             "classifyBinaryExpression"
        //   294: aastore        
        //   295: invokestatic    java/lang/String.format:(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
        //   298: invokespecial   java/lang/IllegalStateException.<init>:(Ljava/lang/String;)V
        //   301: athrow         
        //   302: invokestatic    com/jetbrains/cidr/lang/resolve/OCExprSymbolClassifier.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   305: athrow         
        //   306: areturn        
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                                
        //  -----  -----  -----  -----  ------------------------------------
        //  0      40     40     44     Ljava/lang/IllegalArgumentException;
        //  53     65     68     72     Ljava/lang/IllegalArgumentException;
        //  57     102    102    106    Ljava/lang/IllegalArgumentException;
        //  112    130    133    137    Ljava/lang/IllegalArgumentException;
        //  119    149    152    156    Ljava/lang/IllegalArgumentException;
        //  137    186    186    190    Ljava/lang/IllegalArgumentException;
        //  191    209    212    216    Ljava/lang/IllegalArgumentException;
        //  198    223    226    230    Ljava/lang/IllegalArgumentException;
        //  216    260    260    264    Ljava/lang/IllegalArgumentException;
        //  265    302    302    306    Ljava/lang/IllegalArgumentException;
        // 
        // The error that occurred was:
        // 
        // java.lang.IllegalStateException: Expression is linked from several locations: Label_0137:
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
    
    private boolean a(@NotNull final OCExpressionSymbol ocExpressionSymbol) {
        try {
            if (ocExpressionSymbol == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "symbol", "com/jetbrains/cidr/lang/resolve/OCExprSymbolClassifier", "isPointerToDataMember"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        final OCType resolvedType = ocExpressionSymbol.getResolvedType(this.myContext);
        try {
            if (!(resolvedType instanceof OCPointerType)) {
                return true;
            }
        }
        catch (IllegalArgumentException ex2) {
            throw a(ex2);
        }
        try {
            if (!(((OCPointerType)resolvedType).getRefType() instanceof OCFunctionType)) {
                return true;
            }
        }
        catch (IllegalArgumentException ex3) {
            throw a(ex3);
        }
        return false;
    }
    
    @NotNull
    public OCExprValueCategory classifyCastExpression(final OCCastExpressionSymbol p0) {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     0: aload_0        
        //     1: getfield        com/jetbrains/cidr/lang/resolve/OCExprSymbolClassifier.myContext:Lcom/jetbrains/cidr/lang/symbols/OCResolveContext;
        //     4: invokevirtual   com/jetbrains/cidr/lang/symbols/OCResolveContext.isCpp:()Z
        //     7: ifne            76
        //    10: aload_1        
        //    11: invokevirtual   com/jetbrains/cidr/lang/symbols/expression/OCCastExpressionSymbol.getOperand:()Lcom/jetbrains/cidr/lang/symbols/expression/OCExpressionSymbol;
        //    14: instanceof      Lcom/jetbrains/cidr/lang/symbols/expression/OCInitializerListExpressionSymbol;
        //    17: ifeq            76
        //    20: goto            27
        //    23: invokestatic    com/jetbrains/cidr/lang/resolve/OCExprSymbolClassifier.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //    26: athrow         
        //    27: getstatic       com/jetbrains/cidr/lang/resolve/OCExprValueCategory.LValue:Lcom/jetbrains/cidr/lang/resolve/OCExprValueCategory;
        //    30: dup            
        //    31: ifnonnull       75
        //    34: goto            41
        //    37: invokestatic    com/jetbrains/cidr/lang/resolve/OCExprSymbolClassifier.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //    40: athrow         
        //    41: new             Ljava/lang/IllegalStateException;
        //    44: dup            
        //    45: ldc             "@NotNull method %s.%s must not return null"
        //    47: ldc             2
        //    49: anewarray       Ljava/lang/Object;
        //    52: dup            
        //    53: ldc             0
        //    55: ldc             "com/jetbrains/cidr/lang/resolve/OCExprSymbolClassifier"
        //    57: aastore        
        //    58: dup            
        //    59: ldc             1
        //    61: ldc             "classifyCastExpression"
        //    63: aastore        
        //    64: invokestatic    java/lang/String.format:(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
        //    67: invokespecial   java/lang/IllegalStateException.<init>:(Ljava/lang/String;)V
        //    70: athrow         
        //    71: invokestatic    com/jetbrains/cidr/lang/resolve/OCExprSymbolClassifier.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //    74: athrow         
        //    75: areturn        
        //    76: aload_1        
        //    77: invokevirtual   com/jetbrains/cidr/lang/symbols/expression/OCCastExpressionSymbol.getCastType:()Lcom/jetbrains/cidr/lang/types/OCType;
        //    80: aload_0        
        //    81: getfield        com/jetbrains/cidr/lang/resolve/OCExprSymbolClassifier.myContext:Lcom/jetbrains/cidr/lang/symbols/OCResolveContext;
        //    84: invokevirtual   com/jetbrains/cidr/lang/types/OCType.resolve:(Lcom/jetbrains/cidr/lang/symbols/OCResolveContext;)Lcom/jetbrains/cidr/lang/types/OCType;
        //    87: astore_2       
        //    88: aload_2        
        //    89: instanceof      Lcom/jetbrains/cidr/lang/types/OCCppReferenceType;
        //    92: ifeq            198
        //    95: aload_2        
        //    96: checkcast       Lcom/jetbrains/cidr/lang/types/OCCppReferenceType;
        //    99: astore_3       
        //   100: aload_3        
        //   101: invokevirtual   com/jetbrains/cidr/lang/types/OCCppReferenceType.isRvalueRef:()Z
        //   104: ifeq            156
        //   107: getstatic       com/jetbrains/cidr/lang/resolve/OCExprValueCategory.XValue:Lcom/jetbrains/cidr/lang/resolve/OCExprValueCategory;
        //   110: dup            
        //   111: ifnonnull       155
        //   114: goto            121
        //   117: invokestatic    com/jetbrains/cidr/lang/resolve/OCExprSymbolClassifier.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   120: athrow         
        //   121: new             Ljava/lang/IllegalStateException;
        //   124: dup            
        //   125: ldc             "@NotNull method %s.%s must not return null"
        //   127: ldc             2
        //   129: anewarray       Ljava/lang/Object;
        //   132: dup            
        //   133: ldc             0
        //   135: ldc             "com/jetbrains/cidr/lang/resolve/OCExprSymbolClassifier"
        //   137: aastore        
        //   138: dup            
        //   139: ldc             1
        //   141: ldc             "classifyCastExpression"
        //   143: aastore        
        //   144: invokestatic    java/lang/String.format:(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
        //   147: invokespecial   java/lang/IllegalStateException.<init>:(Ljava/lang/String;)V
        //   150: athrow         
        //   151: invokestatic    com/jetbrains/cidr/lang/resolve/OCExprSymbolClassifier.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   154: athrow         
        //   155: areturn        
        //   156: getstatic       com/jetbrains/cidr/lang/resolve/OCExprValueCategory.LValue:Lcom/jetbrains/cidr/lang/resolve/OCExprValueCategory;
        //   159: dup            
        //   160: ifnonnull       197
        //   163: new             Ljava/lang/IllegalStateException;
        //   166: dup            
        //   167: ldc             "@NotNull method %s.%s must not return null"
        //   169: ldc             2
        //   171: anewarray       Ljava/lang/Object;
        //   174: dup            
        //   175: ldc             0
        //   177: ldc             "com/jetbrains/cidr/lang/resolve/OCExprSymbolClassifier"
        //   179: aastore        
        //   180: dup            
        //   181: ldc             1
        //   183: ldc             "classifyCastExpression"
        //   185: aastore        
        //   186: invokestatic    java/lang/String.format:(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
        //   189: invokespecial   java/lang/IllegalStateException.<init>:(Ljava/lang/String;)V
        //   192: athrow         
        //   193: invokestatic    com/jetbrains/cidr/lang/resolve/OCExprSymbolClassifier.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   196: athrow         
        //   197: areturn        
        //   198: getstatic       com/jetbrains/cidr/lang/resolve/OCExprValueCategory.PRValue:Lcom/jetbrains/cidr/lang/resolve/OCExprValueCategory;
        //   201: dup            
        //   202: ifnonnull       239
        //   205: new             Ljava/lang/IllegalStateException;
        //   208: dup            
        //   209: ldc             "@NotNull method %s.%s must not return null"
        //   211: ldc             2
        //   213: anewarray       Ljava/lang/Object;
        //   216: dup            
        //   217: ldc             0
        //   219: ldc             "com/jetbrains/cidr/lang/resolve/OCExprSymbolClassifier"
        //   221: aastore        
        //   222: dup            
        //   223: ldc             1
        //   225: ldc             "classifyCastExpression"
        //   227: aastore        
        //   228: invokestatic    java/lang/String.format:(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
        //   231: invokespecial   java/lang/IllegalStateException.<init>:(Ljava/lang/String;)V
        //   234: athrow         
        //   235: invokestatic    com/jetbrains/cidr/lang/resolve/OCExprSymbolClassifier.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   238: athrow         
        //   239: areturn        
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                                
        //  -----  -----  -----  -----  ------------------------------------
        //  0      20     23     27     Ljava/lang/IllegalArgumentException;
        //  10     34     37     41     Ljava/lang/IllegalArgumentException;
        //  27     71     71     75     Ljava/lang/IllegalArgumentException;
        //  100    114    117    121    Ljava/lang/IllegalArgumentException;
        //  107    151    151    155    Ljava/lang/IllegalArgumentException;
        //  156    193    193    197    Ljava/lang/IllegalArgumentException;
        //  198    235    235    239    Ljava/lang/IllegalArgumentException;
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
    
    @NotNull
    public OCExprValueCategory classifySizeofExpression(final OCSizeofExpressionSymbol ocSizeofExpressionSymbol) {
        OCExprValueCategory prValue;
        try {
            prValue = OCExprValueCategory.PRValue;
            if (prValue == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/resolve/OCExprSymbolClassifier", "classifySizeofExpression"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        return prValue;
    }
    
    @NotNull
    public OCExprValueCategory classifyUnaryExpression(final OCUnaryExpressionSymbolBase ocUnaryExpressionSymbolBase) {
        final OCType customReturnType = ocUnaryExpressionSymbolBase.getCustomReturnType(this.myContext);
        OCExprValueCategory a = null;
        Label_0086: {
            OCElementType operator = null;
            Label_0063: {
                OCExprValueCategory ocExprValueCategory = null;
                Label_0028: {
                    try {
                        if (customReturnType == null) {
                            break Label_0063;
                        }
                        final OCType ocType = customReturnType;
                        ocExprValueCategory = a(ocType);
                        if (ocExprValueCategory == null) {
                            break Label_0028;
                        }
                        return ocExprValueCategory;
                    }
                    catch (IllegalArgumentException ex) {
                        throw a(ex);
                    }
                    try {
                        final OCType ocType = customReturnType;
                        ocExprValueCategory = a(ocType);
                        if (ocExprValueCategory == null) {
                            throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/resolve/OCExprSymbolClassifier", "classifyUnaryExpression"));
                        }
                    }
                    catch (IllegalArgumentException ex2) {
                        throw a(ex2);
                    }
                }
                return ocExprValueCategory;
                try {
                    operator = ocUnaryExpressionSymbolBase.getOperator();
                    if (ocUnaryExpressionSymbolBase.getOperatorPlacement() == OCOperatorReference.OperatorPlacement.PREFIX) {
                        final boolean b = true;
                        break Label_0086;
                    }
                }
                catch (IllegalArgumentException ex3) {
                    throw a(ex3);
                }
            }
            final boolean b = false;
            try {
                a = a(operator, b);
                if (a == null) {
                    throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/resolve/OCExprSymbolClassifier", "classifyUnaryExpression"));
                }
            }
            catch (IllegalArgumentException ex4) {
                throw a(ex4);
            }
        }
        return a;
    }
    
    @NotNull
    public OCExprValueCategory classifyCallExpression(final OCCallExpressionSymbol p0) {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     0: aload_1        
        //     1: aload_0        
        //     2: getfield        com/jetbrains/cidr/lang/resolve/OCExprSymbolClassifier.myContext:Lcom/jetbrains/cidr/lang/symbols/OCResolveContext;
        //     5: invokevirtual   com/jetbrains/cidr/lang/symbols/expression/OCCallExpressionSymbol.getResolvedType:(Lcom/jetbrains/cidr/lang/symbols/OCResolveContext;)Lcom/jetbrains/cidr/lang/types/OCType;
        //     8: astore_2       
        //     9: aload_2        
        //    10: ifnull          27
        //    13: aload_2        
        //    14: invokevirtual   com/jetbrains/cidr/lang/types/OCType.isUnknown:()Z
        //    17: ifeq            76
        //    20: goto            27
        //    23: invokestatic    com/jetbrains/cidr/lang/resolve/OCExprSymbolClassifier.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //    26: athrow         
        //    27: getstatic       com/jetbrains/cidr/lang/resolve/OCExprValueCategory.LValue:Lcom/jetbrains/cidr/lang/resolve/OCExprValueCategory;
        //    30: dup            
        //    31: ifnonnull       75
        //    34: goto            41
        //    37: invokestatic    com/jetbrains/cidr/lang/resolve/OCExprSymbolClassifier.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //    40: athrow         
        //    41: new             Ljava/lang/IllegalStateException;
        //    44: dup            
        //    45: ldc             "@NotNull method %s.%s must not return null"
        //    47: ldc             2
        //    49: anewarray       Ljava/lang/Object;
        //    52: dup            
        //    53: ldc             0
        //    55: ldc             "com/jetbrains/cidr/lang/resolve/OCExprSymbolClassifier"
        //    57: aastore        
        //    58: dup            
        //    59: ldc             1
        //    61: ldc             "classifyCallExpression"
        //    63: aastore        
        //    64: invokestatic    java/lang/String.format:(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
        //    67: invokespecial   java/lang/IllegalStateException.<init>:(Ljava/lang/String;)V
        //    70: athrow         
        //    71: invokestatic    com/jetbrains/cidr/lang/resolve/OCExprSymbolClassifier.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //    74: athrow         
        //    75: areturn        
        //    76: aload_2        
        //    77: invokestatic    com/jetbrains/cidr/lang/resolve/OCExprSymbolClassifier.a:(Lcom/jetbrains/cidr/lang/types/OCType;)Lcom/jetbrains/cidr/lang/resolve/OCExprValueCategory;
        //    80: dup            
        //    81: ifnonnull       118
        //    84: new             Ljava/lang/IllegalStateException;
        //    87: dup            
        //    88: ldc             "@NotNull method %s.%s must not return null"
        //    90: ldc             2
        //    92: anewarray       Ljava/lang/Object;
        //    95: dup            
        //    96: ldc             0
        //    98: ldc             "com/jetbrains/cidr/lang/resolve/OCExprSymbolClassifier"
        //   100: aastore        
        //   101: dup            
        //   102: ldc             1
        //   104: ldc             "classifyCallExpression"
        //   106: aastore        
        //   107: invokestatic    java/lang/String.format:(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
        //   110: invokespecial   java/lang/IllegalStateException.<init>:(Ljava/lang/String;)V
        //   113: athrow         
        //   114: invokestatic    com/jetbrains/cidr/lang/resolve/OCExprSymbolClassifier.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   117: athrow         
        //   118: areturn        
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                                
        //  -----  -----  -----  -----  ------------------------------------
        //  9      20     23     27     Ljava/lang/IllegalArgumentException;
        //  13     34     37     41     Ljava/lang/IllegalArgumentException;
        //  27     71     71     75     Ljava/lang/IllegalArgumentException;
        //  76     114    114    118    Ljava/lang/IllegalArgumentException;
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
    
    @NotNull
    public OCExprValueCategory classifyQualifiedExpression(final OCQualifiedExpressionSymbol ocQualifiedExpressionSymbol) {
        OCExprValueCategory classify = null;
        Label_0056: {
            OCExprValueCategory ocExprValueCategory = null;
            Label_0021: {
                try {
                    if (!ocQualifiedExpressionSymbol.isDeref()) {
                        break Label_0056;
                    }
                    ocExprValueCategory = OCExprValueCategory.LValue;
                    if (ocExprValueCategory == null) {
                        break Label_0021;
                    }
                    return ocExprValueCategory;
                }
                catch (IllegalArgumentException ex) {
                    throw a(ex);
                }
                try {
                    ocExprValueCategory = OCExprValueCategory.LValue;
                    if (ocExprValueCategory == null) {
                        throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/resolve/OCExprSymbolClassifier", "classifyQualifiedExpression"));
                    }
                }
                catch (IllegalArgumentException ex2) {
                    throw a(ex2);
                }
            }
            return ocExprValueCategory;
            try {
                classify = this.classify(ocQualifiedExpressionSymbol.getQualifier());
                if (classify == null) {
                    throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/resolve/OCExprSymbolClassifier", "classifyQualifiedExpression"));
                }
            }
            catch (IllegalArgumentException ex3) {
                throw a(ex3);
            }
        }
        return classify;
    }
    
    @NotNull
    public OCExprValueCategory classifyReferenceExpression(final OCReferenceExpressionSymbol ocReferenceExpressionSymbol) {
        OCExprValueCategory lValue;
        try {
            lValue = OCExprValueCategory.LValue;
            if (lValue == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/resolve/OCExprSymbolClassifier", "classifyReferenceExpression"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        return lValue;
    }
    
    @NotNull
    public OCExprValueCategory classifyLiteralExpression(final OCLiteralExpressionSymbol ocLiteralExpressionSymbol) {
        OCExprValueCategory prValue = null;
        Label_0059: {
            OCExprValueCategory ocExprValueCategory = null;
            Label_0024: {
                try {
                    if (ocLiteralExpressionSymbol.getTokenType() != OCTokenTypes.STRING_LITERAL) {
                        break Label_0059;
                    }
                    ocExprValueCategory = OCExprValueCategory.LValue;
                    if (ocExprValueCategory == null) {
                        break Label_0024;
                    }
                    return ocExprValueCategory;
                }
                catch (IllegalArgumentException ex) {
                    throw a(ex);
                }
                try {
                    ocExprValueCategory = OCExprValueCategory.LValue;
                    if (ocExprValueCategory == null) {
                        throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/resolve/OCExprSymbolClassifier", "classifyLiteralExpression"));
                    }
                }
                catch (IllegalArgumentException ex2) {
                    throw a(ex2);
                }
            }
            return ocExprValueCategory;
            try {
                prValue = OCExprValueCategory.PRValue;
                if (prValue == null) {
                    throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/resolve/OCExprSymbolClassifier", "classifyLiteralExpression"));
                }
            }
            catch (IllegalArgumentException ex3) {
                throw a(ex3);
            }
        }
        return prValue;
    }
    
    @NotNull
    public OCExprValueCategory classifyLambdaExpression(final OCLambdaExpressionSymbol ocLambdaExpressionSymbol) {
        OCExprValueCategory prValue;
        try {
            prValue = OCExprValueCategory.PRValue;
            if (prValue == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/resolve/OCExprSymbolClassifier", "classifyLambdaExpression"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        return prValue;
    }
    
    @NotNull
    public OCExprValueCategory classifyCppNewExpression(final OCNewExpressionSymbol ocNewExpressionSymbol) {
        OCExprValueCategory prValue;
        try {
            prValue = OCExprValueCategory.PRValue;
            if (prValue == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/resolve/OCExprSymbolClassifier", "classifyCppNewExpression"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        return prValue;
    }
    
    @NotNull
    public OCExprValueCategory classifyVariadicPackExpression(final OCVariadicPackExpressionSymbol ocVariadicPackExpressionSymbol) {
        OCExprValueCategory classify;
        try {
            classify = this.classify(ocVariadicPackExpressionSymbol.getExpression());
            if (classify == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/resolve/OCExprSymbolClassifier", "classifyVariadicPackExpression"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        return classify;
    }
    
    @NotNull
    private OCExprValueCategory a(@NotNull final OCArrayIndexExpressionSymbol p0) {
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
        //    18: ldc             "symbol"
        //    20: aastore        
        //    21: dup            
        //    22: ldc             1
        //    24: ldc             "com/jetbrains/cidr/lang/resolve/OCExprSymbolClassifier"
        //    26: aastore        
        //    27: dup            
        //    28: ldc             2
        //    30: ldc             "classifyArrayIndexExpression"
        //    32: aastore        
        //    33: invokestatic    java/lang/String.format:(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
        //    36: invokespecial   java/lang/IllegalArgumentException.<init>:(Ljava/lang/String;)V
        //    39: athrow         
        //    40: invokestatic    com/jetbrains/cidr/lang/resolve/OCExprSymbolClassifier.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //    43: athrow         
        //    44: aload_1        
        //    45: invokevirtual   com/jetbrains/cidr/lang/symbols/expression/OCArrayIndexExpressionSymbol.getIndexSymbol:()Lcom/jetbrains/cidr/lang/symbols/expression/OCExpressionSymbol;
        //    48: ifnonnull       100
        //    51: getstatic       com/jetbrains/cidr/lang/resolve/OCExprValueCategory.LValue:Lcom/jetbrains/cidr/lang/resolve/OCExprValueCategory;
        //    54: dup            
        //    55: ifnonnull       99
        //    58: goto            65
        //    61: invokestatic    com/jetbrains/cidr/lang/resolve/OCExprSymbolClassifier.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //    64: athrow         
        //    65: new             Ljava/lang/IllegalStateException;
        //    68: dup            
        //    69: ldc             "@NotNull method %s.%s must not return null"
        //    71: ldc             2
        //    73: anewarray       Ljava/lang/Object;
        //    76: dup            
        //    77: ldc             0
        //    79: ldc             "com/jetbrains/cidr/lang/resolve/OCExprSymbolClassifier"
        //    81: aastore        
        //    82: dup            
        //    83: ldc             1
        //    85: ldc             "classifyArrayIndexExpression"
        //    87: aastore        
        //    88: invokestatic    java/lang/String.format:(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
        //    91: invokespecial   java/lang/IllegalStateException.<init>:(Ljava/lang/String;)V
        //    94: athrow         
        //    95: invokestatic    com/jetbrains/cidr/lang/resolve/OCExprSymbolClassifier.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //    98: athrow         
        //    99: areturn        
        //   100: aload_1        
        //   101: aload_0        
        //   102: getfield        com/jetbrains/cidr/lang/resolve/OCExprSymbolClassifier.myContext:Lcom/jetbrains/cidr/lang/symbols/OCResolveContext;
        //   105: invokevirtual   com/jetbrains/cidr/lang/symbols/expression/OCArrayIndexExpressionSymbol.getResolvedTypeWithKind:(Lcom/jetbrains/cidr/lang/symbols/OCResolveContext;)Lcom/jetbrains/cidr/lang/symbols/expression/OCArrayIndexExpressionSymbol$ResolveResult;
        //   108: astore_2       
        //   109: aload_2        
        //   110: getfield        com/jetbrains/cidr/lang/symbols/expression/OCArrayIndexExpressionSymbol$ResolveResult.kind:Lcom/jetbrains/cidr/lang/symbols/expression/OCArrayIndexExpressionSymbol$Kind;
        //   113: getstatic       com/jetbrains/cidr/lang/symbols/expression/OCArrayIndexExpressionSymbol$Kind.Builtin:Lcom/jetbrains/cidr/lang/symbols/expression/OCArrayIndexExpressionSymbol$Kind;
        //   116: if_acmpeq       133
        //   119: aload_2        
        //   120: getfield        com/jetbrains/cidr/lang/symbols/expression/OCArrayIndexExpressionSymbol$ResolveResult.type:Lcom/jetbrains/cidr/lang/types/OCType;
        //   123: ifnonnull       182
        //   126: goto            133
        //   129: invokestatic    com/jetbrains/cidr/lang/resolve/OCExprSymbolClassifier.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   132: athrow         
        //   133: getstatic       com/jetbrains/cidr/lang/resolve/OCExprValueCategory.LValue:Lcom/jetbrains/cidr/lang/resolve/OCExprValueCategory;
        //   136: dup            
        //   137: ifnonnull       181
        //   140: goto            147
        //   143: invokestatic    com/jetbrains/cidr/lang/resolve/OCExprSymbolClassifier.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   146: athrow         
        //   147: new             Ljava/lang/IllegalStateException;
        //   150: dup            
        //   151: ldc             "@NotNull method %s.%s must not return null"
        //   153: ldc             2
        //   155: anewarray       Ljava/lang/Object;
        //   158: dup            
        //   159: ldc             0
        //   161: ldc             "com/jetbrains/cidr/lang/resolve/OCExprSymbolClassifier"
        //   163: aastore        
        //   164: dup            
        //   165: ldc             1
        //   167: ldc             "classifyArrayIndexExpression"
        //   169: aastore        
        //   170: invokestatic    java/lang/String.format:(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
        //   173: invokespecial   java/lang/IllegalStateException.<init>:(Ljava/lang/String;)V
        //   176: athrow         
        //   177: invokestatic    com/jetbrains/cidr/lang/resolve/OCExprSymbolClassifier.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   180: athrow         
        //   181: areturn        
        //   182: aload_2        
        //   183: getfield        com/jetbrains/cidr/lang/symbols/expression/OCArrayIndexExpressionSymbol$ResolveResult.type:Lcom/jetbrains/cidr/lang/types/OCType;
        //   186: invokestatic    com/jetbrains/cidr/lang/resolve/OCExprSymbolClassifier.a:(Lcom/jetbrains/cidr/lang/types/OCType;)Lcom/jetbrains/cidr/lang/resolve/OCExprValueCategory;
        //   189: dup            
        //   190: ifnonnull       227
        //   193: new             Ljava/lang/IllegalStateException;
        //   196: dup            
        //   197: ldc             "@NotNull method %s.%s must not return null"
        //   199: ldc             2
        //   201: anewarray       Ljava/lang/Object;
        //   204: dup            
        //   205: ldc             0
        //   207: ldc             "com/jetbrains/cidr/lang/resolve/OCExprSymbolClassifier"
        //   209: aastore        
        //   210: dup            
        //   211: ldc             1
        //   213: ldc             "classifyArrayIndexExpression"
        //   215: aastore        
        //   216: invokestatic    java/lang/String.format:(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
        //   219: invokespecial   java/lang/IllegalStateException.<init>:(Ljava/lang/String;)V
        //   222: athrow         
        //   223: invokestatic    com/jetbrains/cidr/lang/resolve/OCExprSymbolClassifier.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   226: athrow         
        //   227: areturn        
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                                
        //  -----  -----  -----  -----  ------------------------------------
        //  0      40     40     44     Ljava/lang/IllegalArgumentException;
        //  44     58     61     65     Ljava/lang/IllegalArgumentException;
        //  51     95     95     99     Ljava/lang/IllegalArgumentException;
        //  109    126    129    133    Ljava/lang/IllegalArgumentException;
        //  119    140    143    147    Ljava/lang/IllegalArgumentException;
        //  133    177    177    181    Ljava/lang/IllegalArgumentException;
        //  182    223    223    227    Ljava/lang/IllegalArgumentException;
        // 
        // The error that occurred was:
        // 
        // java.lang.IllegalStateException: Expression is linked from several locations: Label_0133:
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
