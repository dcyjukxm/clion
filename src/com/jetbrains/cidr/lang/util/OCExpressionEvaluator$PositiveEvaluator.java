// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.lang.util;

import com.jetbrains.cidr.lang.symbols.expression.OCSizeofExpressionSymbol;
import com.jetbrains.cidr.lang.symbols.expression.OCReferenceExpressionSymbol;
import com.jetbrains.cidr.lang.symbols.expression.OCCallExpressionSymbol;
import com.intellij.util.Producer;
import com.jetbrains.cidr.lang.parser.OCElementType;
import org.jetbrains.annotations.Nullable;
import com.jetbrains.cidr.lang.types.OCType;
import com.jetbrains.cidr.lang.types.OCIntType;
import com.jetbrains.cidr.lang.psi.OCExpression;
import org.jetbrains.annotations.NotNull;
import com.jetbrains.cidr.lang.symbols.OCResolveContext;
import com.intellij.openapi.util.Pair;

public static class PositiveEvaluator extends CachingEvaluator<Pair<Boolean, Number>>
{
    private ValueEvaluator valueEvaluator;
    
    public PositiveEvaluator(@NotNull final OCResolveContext ocResolveContext) {
        if (ocResolveContext == null) {
            throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "context", "com/jetbrains/cidr/lang/util/OCExpressionEvaluator$PositiveEvaluator", "<init>"));
        }
        super(ocResolveContext);
        this.valueEvaluator = new ValueEvaluator(ocResolveContext);
    }
    
    @Nullable
    @Override
    public Pair<Boolean, Number> evalDefault(@NotNull final OCExpression ocExpression) {
        try {
            if (ocExpression == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "psi", "com/jetbrains/cidr/lang/util/OCExpressionEvaluator$PositiveEvaluator", "evalDefault"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw b(ex);
        }
        final OCType resolvedType = ocExpression.getResolvedType(this.valueEvaluator.getContext());
        Label_0082: {
            try {
                if (!(resolvedType instanceof OCIntType)) {
                    return null;
                }
                final OCIntType ocIntType = (OCIntType)resolvedType;
                final OCIntType ocIntType2 = ocIntType;
                final boolean b = ocIntType2.isSigned();
                if (!b) {
                    break Label_0082;
                }
                return null;
            }
            catch (IllegalArgumentException ex2) {
                throw b(ex2);
            }
            try {
                final OCIntType ocIntType = (OCIntType)resolvedType;
                final OCIntType ocIntType2 = ocIntType;
                final boolean b = ocIntType2.isSigned();
                if (!b) {
                    return (Pair<Boolean, Number>)new Pair((Object)true, (Object)null);
                }
            }
            catch (IllegalArgumentException ex3) {
                throw b(ex3);
            }
        }
        return null;
    }
    
    @NotNull
    @Override
    public Pair<Boolean, Number> evalInteger(@NotNull final Number n) {
        try {
            if (n == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "value", "com/jetbrains/cidr/lang/util/OCExpressionEvaluator$PositiveEvaluator", "evalInteger"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw b(ex);
        }
        boolean b = false;
        Label_0064: {
            try {
                if (OCExpressionEvaluator.singAsInC(n) >= 0) {
                    b = true;
                    break Label_0064;
                }
            }
            catch (IllegalArgumentException ex2) {
                throw b(ex2);
            }
            b = false;
        }
        final Pair pair = new Pair((Object)b, (Object)n);
        if (pair == null) {
            throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/util/OCExpressionEvaluator$PositiveEvaluator", "evalInteger"));
        }
        return (Pair<Boolean, Number>)pair;
    }
    
    @Override
    public Pair<Boolean, Number> evalBool(final Boolean b) {
        return null;
    }
    
    @Nullable
    @Override
    public Pair<Boolean, Number> evalUnary(final OCElementType ocElementType, final Pair<Boolean, Number> pair) {
        try {
            if (pair == null) {
                return null;
            }
        }
        catch (IllegalArgumentException ex) {
            throw b(ex);
        }
        final Object evalUnary = this.valueEvaluator.evalUnary(ocElementType, pair.getSecond());
        Label_0057: {
            try {
                if (!(evalUnary instanceof Number) || ((Number)evalUnary).longValue() < 0L) {
                    break Label_0057;
                }
            }
            catch (IllegalArgumentException ex2) {
                throw b(ex2);
            }
            final boolean b = true;
            return (Pair<Boolean, Number>)new Pair((Object)b, (Object)((evalUnary instanceof Number) ? ((Number)evalUnary) : null));
        }
        final boolean b = false;
        return (Pair<Boolean, Number>)new Pair((Object)b, (Object)((evalUnary instanceof Number) ? ((Number)evalUnary) : null));
    }
    
    @Nullable
    @Override
    public Pair<Boolean, Number> evalBinary(final OCElementType p0, final Pair<Boolean, Number> p1, final Pair<Boolean, Number> p2) {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     0: aload_2        
        //     1: ifnull          15
        //     4: aload_3        
        //     5: ifnonnull       21
        //     8: goto            15
        //    11: invokestatic    com/jetbrains/cidr/lang/util/OCExpressionEvaluator$PositiveEvaluator.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //    14: athrow         
        //    15: aconst_null    
        //    16: areturn        
        //    17: invokestatic    com/jetbrains/cidr/lang/util/OCExpressionEvaluator$PositiveEvaluator.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //    20: athrow         
        //    21: aload_0        
        //    22: getfield        com/jetbrains/cidr/lang/util/OCExpressionEvaluator$PositiveEvaluator.valueEvaluator:Lcom/jetbrains/cidr/lang/util/OCExpressionEvaluator$ValueEvaluator;
        //    25: aload_1        
        //    26: aload_2        
        //    27: invokevirtual   com/intellij/openapi/util/Pair.getSecond:()Ljava/lang/Object;
        //    30: aload_3        
        //    31: invokevirtual   com/intellij/openapi/util/Pair.getSecond:()Ljava/lang/Object;
        //    34: invokevirtual   com/jetbrains/cidr/lang/util/OCExpressionEvaluator$ValueEvaluator.evalBinary:(Lcom/jetbrains/cidr/lang/parser/OCElementType;Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
        //    37: astore          4
        //    39: aload           4
        //    41: instanceof      Ljava/lang/Number;
        //    44: ifeq            62
        //    47: aload           4
        //    49: invokestatic    com/jetbrains/cidr/lang/util/OCExpressionEvaluator.singAsInC:(Ljava/lang/Object;)I
        //    52: ifge            116
        //    55: goto            62
        //    58: invokestatic    com/jetbrains/cidr/lang/util/OCExpressionEvaluator$PositiveEvaluator.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //    61: athrow         
        //    62: aload_1        
        //    63: getstatic       com/jetbrains/cidr/lang/parser/OCTokenTypes.PLUS:Lcom/jetbrains/cidr/lang/parser/OCPunctuatorElementType;
        //    66: if_acmpne       124
        //    69: goto            76
        //    72: invokestatic    com/jetbrains/cidr/lang/util/OCExpressionEvaluator$PositiveEvaluator.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //    75: athrow         
        //    76: aload_2        
        //    77: invokevirtual   com/intellij/openapi/util/Pair.getFirst:()Ljava/lang/Object;
        //    80: checkcast       Ljava/lang/Boolean;
        //    83: invokevirtual   java/lang/Boolean.booleanValue:()Z
        //    86: ifeq            124
        //    89: goto            96
        //    92: invokestatic    com/jetbrains/cidr/lang/util/OCExpressionEvaluator$PositiveEvaluator.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //    95: athrow         
        //    96: aload_3        
        //    97: invokevirtual   com/intellij/openapi/util/Pair.getFirst:()Ljava/lang/Object;
        //   100: checkcast       Ljava/lang/Boolean;
        //   103: invokevirtual   java/lang/Boolean.booleanValue:()Z
        //   106: ifeq            124
        //   109: goto            116
        //   112: invokestatic    com/jetbrains/cidr/lang/util/OCExpressionEvaluator$PositiveEvaluator.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   115: athrow         
        //   116: iconst_1       
        //   117: goto            125
        //   120: invokestatic    com/jetbrains/cidr/lang/util/OCExpressionEvaluator$PositiveEvaluator.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   123: athrow         
        //   124: iconst_0       
        //   125: istore          5
        //   127: new             Lcom/intellij/openapi/util/Pair;
        //   130: dup            
        //   131: iload           5
        //   133: invokestatic    java/lang/Boolean.valueOf:(Z)Ljava/lang/Boolean;
        //   136: aload           4
        //   138: instanceof      Ljava/lang/Number;
        //   141: ifeq            156
        //   144: aload           4
        //   146: checkcast       Ljava/lang/Number;
        //   149: goto            157
        //   152: invokestatic    com/jetbrains/cidr/lang/util/OCExpressionEvaluator$PositiveEvaluator.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   155: athrow         
        //   156: aconst_null    
        //   157: invokespecial   com/intellij/openapi/util/Pair.<init>:(Ljava/lang/Object;Ljava/lang/Object;)V
        //   160: areturn        
        //    Signature:
        //  (Lcom/jetbrains/cidr/lang/parser/OCElementType;Lcom/intellij/openapi/util/Pair<Ljava/lang/Boolean;Ljava/lang/Number;>;Lcom/intellij/openapi/util/Pair<Ljava/lang/Boolean;Ljava/lang/Number;>;)Lcom/intellij/openapi/util/Pair<Ljava/lang/Boolean;Ljava/lang/Number;>;
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                                
        //  -----  -----  -----  -----  ------------------------------------
        //  0      8      11     15     Ljava/lang/IllegalArgumentException;
        //  4      17     17     21     Ljava/lang/IllegalArgumentException;
        //  39     55     58     62     Ljava/lang/IllegalArgumentException;
        //  47     69     72     76     Ljava/lang/IllegalArgumentException;
        //  62     89     92     96     Ljava/lang/IllegalArgumentException;
        //  76     109    112    116    Ljava/lang/IllegalArgumentException;
        //  96     120    120    124    Ljava/lang/IllegalArgumentException;
        //  127    152    152    156    Ljava/lang/IllegalArgumentException;
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
    
    @Nullable
    @Override
    public Pair<Boolean, Number> evalConditional(final Pair<Boolean, Number> p0, final Producer<Pair<Boolean, Number>> p1, final Producer<Pair<Boolean, Number>> p2) {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     0: aload_2        
        //     1: ifnull          20
        //     4: aload_2        
        //     5: invokeinterface com/intellij/util/Producer.produce:()Ljava/lang/Object;
        //    10: checkcast       Lcom/intellij/openapi/util/Pair;
        //    13: goto            21
        //    16: invokestatic    com/jetbrains/cidr/lang/util/OCExpressionEvaluator$PositiveEvaluator.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //    19: athrow         
        //    20: aconst_null    
        //    21: astore          4
        //    23: aload_3        
        //    24: ifnull          43
        //    27: aload_3        
        //    28: invokeinterface com/intellij/util/Producer.produce:()Ljava/lang/Object;
        //    33: checkcast       Lcom/intellij/openapi/util/Pair;
        //    36: goto            44
        //    39: invokestatic    com/jetbrains/cidr/lang/util/OCExpressionEvaluator$PositiveEvaluator.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //    42: athrow         
        //    43: aconst_null    
        //    44: astore          5
        //    46: aload           4
        //    48: ifnull          124
        //    51: aload           5
        //    53: ifnull          124
        //    56: goto            63
        //    59: invokestatic    com/jetbrains/cidr/lang/util/OCExpressionEvaluator$PositiveEvaluator.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //    62: athrow         
        //    63: new             Lcom/intellij/openapi/util/Pair;
        //    66: dup            
        //    67: aload           4
        //    69: invokevirtual   com/intellij/openapi/util/Pair.getFirst:()Ljava/lang/Object;
        //    72: checkcast       Ljava/lang/Boolean;
        //    75: invokevirtual   java/lang/Boolean.booleanValue:()Z
        //    78: ifeq            113
        //    81: goto            88
        //    84: invokestatic    com/jetbrains/cidr/lang/util/OCExpressionEvaluator$PositiveEvaluator.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //    87: athrow         
        //    88: aload           5
        //    90: invokevirtual   com/intellij/openapi/util/Pair.getFirst:()Ljava/lang/Object;
        //    93: checkcast       Ljava/lang/Boolean;
        //    96: invokevirtual   java/lang/Boolean.booleanValue:()Z
        //    99: ifeq            113
        //   102: goto            109
        //   105: invokestatic    com/jetbrains/cidr/lang/util/OCExpressionEvaluator$PositiveEvaluator.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   108: athrow         
        //   109: iconst_1       
        //   110: goto            114
        //   113: iconst_0       
        //   114: invokestatic    java/lang/Boolean.valueOf:(Z)Ljava/lang/Boolean;
        //   117: aconst_null    
        //   118: invokespecial   com/intellij/openapi/util/Pair.<init>:(Ljava/lang/Object;Ljava/lang/Object;)V
        //   121: goto            125
        //   124: aconst_null    
        //   125: areturn        
        //    Signature:
        //  (Lcom/intellij/openapi/util/Pair<Ljava/lang/Boolean;Ljava/lang/Number;>;Lcom/intellij/util/Producer<Lcom/intellij/openapi/util/Pair<Ljava/lang/Boolean;Ljava/lang/Number;>;>;Lcom/intellij/util/Producer<Lcom/intellij/openapi/util/Pair<Ljava/lang/Boolean;Ljava/lang/Number;>;>;)Lcom/intellij/openapi/util/Pair<Ljava/lang/Boolean;Ljava/lang/Number;>;
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                                
        //  -----  -----  -----  -----  ------------------------------------
        //  0      16     16     20     Ljava/lang/IllegalArgumentException;
        //  23     39     39     43     Ljava/lang/IllegalArgumentException;
        //  46     56     59     63     Ljava/lang/IllegalArgumentException;
        //  51     81     84     88     Ljava/lang/IllegalArgumentException;
        //  63     102    105    109    Ljava/lang/IllegalArgumentException;
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
    
    @Nullable
    @Override
    public Pair<Boolean, Number> evalCast(final OCType ocType, final Pair<Boolean, Number> pair) {
        return null;
    }
    
    @Nullable
    @Override
    public Pair<Boolean, Number> evalCall(final OCCallExpressionSymbol ocCallExpressionSymbol) {
        return null;
    }
    
    @Nullable
    @Override
    public Pair<Boolean, Number> evalReference(final OCReferenceExpressionSymbol ocReferenceExpressionSymbol) {
        return null;
    }
    
    @Nullable
    @Override
    public Pair<Boolean, Number> evalSizeof(final OCSizeofExpressionSymbol ocSizeofExpressionSymbol) {
        return null;
    }
    
    private static IllegalArgumentException b(final IllegalArgumentException ex) {
        return ex;
    }
}
