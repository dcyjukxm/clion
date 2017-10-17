// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.lang.util;

import com.jetbrains.cidr.lang.types.OCTypeArgument;
import com.jetbrains.cidr.lang.symbols.expression.OCExpressionSymbol;
import com.jetbrains.cidr.lang.preprocessor.OCInclusionContext;
import com.jetbrains.cidr.lang.symbols.OCTypeParameterSymbol;
import com.jetbrains.cidr.lang.types.OCExpansionPackType;
import com.jetbrains.cidr.lang.symbols.expression.OCSizeofExpressionSymbol;
import com.jetbrains.cidr.lang.symbols.cpp.OCTypeParameterValueSymbol;
import com.jetbrains.cidr.lang.symbols.OCSymbol;
import com.jetbrains.cidr.lang.symbols.cpp.OCDeclaratorSymbol;
import com.jetbrains.cidr.lang.symbols.OCSymbolKind;
import com.jetbrains.cidr.lang.resolve.OCResolveUtil;
import com.jetbrains.cidr.lang.symbols.expression.OCReferenceExpressionSymbol;
import com.jetbrains.cidr.lang.symbols.expression.OCCallExpressionSymbol;
import com.jetbrains.cidr.lang.types.OCType;
import org.jetbrains.annotations.Nullable;
import com.jetbrains.cidr.lang.parser.OCElementType;
import com.intellij.util.Producer;
import com.jetbrains.cidr.lang.psi.OCExpression;
import org.jetbrains.annotations.NotNull;
import com.jetbrains.cidr.lang.symbols.OCResolveContext;
import com.intellij.psi.PsiElement;

public static class ValueEvaluator extends CachingEvaluator<Object>
{
    public ValueEvaluator(final PsiElement psiElement) {
        super(new OCResolveContext(psiElement));
    }
    
    public ValueEvaluator(@NotNull final OCResolveContext ocResolveContext) {
        if (ocResolveContext == null) {
            throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "context", "com/jetbrains/cidr/lang/util/OCExpressionEvaluator$ValueEvaluator", "<init>"));
        }
        super(ocResolveContext);
    }
    
    public ValueEvaluator(@NotNull final ValueEvaluator valueEvaluator, final OCResolveContext ocResolveContext) {
        if (valueEvaluator == null) {
            throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "clone", "com/jetbrains/cidr/lang/util/OCExpressionEvaluator$ValueEvaluator", "<init>"));
        }
        super(valueEvaluator, ocResolveContext);
    }
    
    @Override
    public Number evalDefault(final OCExpression ocExpression) {
        return null;
    }
    
    @Override
    public Object evalConditional(final Object p0, final Producer<Object> p1, final Producer<Object> p2) {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     0: aload_1        
        //     1: instanceof      Ljava/lang/Number;
        //     4: ifne            21
        //     7: aload_1        
        //     8: instanceof      Ljava/lang/Boolean;
        //    11: ifeq            55
        //    14: goto            21
        //    17: invokestatic    com/jetbrains/cidr/lang/util/OCExpressionEvaluator$ValueEvaluator.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //    20: athrow         
        //    21: aload_1        
        //    22: invokestatic    com/jetbrains/cidr/lang/util/OCExpressionEvaluator.singAsInC:(Ljava/lang/Object;)I
        //    25: ifne            48
        //    28: goto            35
        //    31: invokestatic    com/jetbrains/cidr/lang/util/OCExpressionEvaluator$ValueEvaluator.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //    34: athrow         
        //    35: aload_3        
        //    36: invokeinterface com/intellij/util/Producer.produce:()Ljava/lang/Object;
        //    41: goto            54
        //    44: invokestatic    com/jetbrains/cidr/lang/util/OCExpressionEvaluator$ValueEvaluator.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //    47: athrow         
        //    48: aload_2        
        //    49: invokeinterface com/intellij/util/Producer.produce:()Ljava/lang/Object;
        //    54: areturn        
        //    55: aconst_null    
        //    56: areturn        
        //    Signature:
        //  (Ljava/lang/Object;Lcom/intellij/util/Producer<Ljava/lang/Object;>;Lcom/intellij/util/Producer<Ljava/lang/Object;>;)Ljava/lang/Object;
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                                
        //  -----  -----  -----  -----  ------------------------------------
        //  0      14     17     21     Ljava/lang/IllegalArgumentException;
        //  7      28     31     35     Ljava/lang/IllegalArgumentException;
        //  21     44     44     48     Ljava/lang/IllegalArgumentException;
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
    
    @Override
    public Object evalInteger(final Number n) {
        return n;
    }
    
    @Override
    public Object evalBool(final Boolean b) {
        return b;
    }
    
    @Nullable
    @Override
    public Object evalBinary(final OCElementType p0, final Object p1, final Object p2) {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     0: aload_1        
        //     1: getstatic       com/jetbrains/cidr/lang/parser/OCTokenTypes.OROR:Lcom/jetbrains/cidr/lang/parser/OCPunctuatorElementType;
        //     4: if_acmpne       32
        //     7: aload_2        
        //     8: ifnull          32
        //    11: goto            18
        //    14: invokestatic    com/jetbrains/cidr/lang/util/OCExpressionEvaluator$ValueEvaluator.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //    17: athrow         
        //    18: aload_2        
        //    19: invokestatic    com/jetbrains/cidr/lang/util/OCExpressionEvaluator.singAsInC:(Ljava/lang/Object;)I
        //    22: ifne            71
        //    25: goto            32
        //    28: invokestatic    com/jetbrains/cidr/lang/util/OCExpressionEvaluator$ValueEvaluator.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //    31: athrow         
        //    32: aload_1        
        //    33: getstatic       com/jetbrains/cidr/lang/parser/OCTokenTypes.ANDAND:Lcom/jetbrains/cidr/lang/parser/OCPunctuatorElementType;
        //    36: if_acmpne       99
        //    39: goto            46
        //    42: invokestatic    com/jetbrains/cidr/lang/util/OCExpressionEvaluator$ValueEvaluator.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //    45: athrow         
        //    46: aload_2        
        //    47: ifnull          99
        //    50: goto            57
        //    53: invokestatic    com/jetbrains/cidr/lang/util/OCExpressionEvaluator$ValueEvaluator.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //    56: athrow         
        //    57: aload_2        
        //    58: invokestatic    com/jetbrains/cidr/lang/util/OCExpressionEvaluator.singAsInC:(Ljava/lang/Object;)I
        //    61: ifne            99
        //    64: goto            71
        //    67: invokestatic    com/jetbrains/cidr/lang/util/OCExpressionEvaluator$ValueEvaluator.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //    70: athrow         
        //    71: aload_2        
        //    72: invokestatic    com/jetbrains/cidr/lang/util/OCExpressionEvaluator.singAsInC:(Ljava/lang/Object;)I
        //    75: ifeq            95
        //    78: goto            85
        //    81: invokestatic    com/jetbrains/cidr/lang/util/OCExpressionEvaluator$ValueEvaluator.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //    84: athrow         
        //    85: getstatic       java/lang/Boolean.TRUE:Ljava/lang/Boolean;
        //    88: goto            98
        //    91: invokestatic    com/jetbrains/cidr/lang/util/OCExpressionEvaluator$ValueEvaluator.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //    94: athrow         
        //    95: getstatic       java/lang/Boolean.FALSE:Ljava/lang/Boolean;
        //    98: areturn        
        //    99: aload_1        
        //   100: getstatic       com/jetbrains/cidr/lang/parser/OCTokenTypes.OROR:Lcom/jetbrains/cidr/lang/parser/OCPunctuatorElementType;
        //   103: if_acmpne       131
        //   106: aload_3        
        //   107: ifnull          131
        //   110: goto            117
        //   113: invokestatic    com/jetbrains/cidr/lang/util/OCExpressionEvaluator$ValueEvaluator.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   116: athrow         
        //   117: aload_3        
        //   118: invokestatic    com/jetbrains/cidr/lang/util/OCExpressionEvaluator.singAsInC:(Ljava/lang/Object;)I
        //   121: ifne            170
        //   124: goto            131
        //   127: invokestatic    com/jetbrains/cidr/lang/util/OCExpressionEvaluator$ValueEvaluator.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   130: athrow         
        //   131: aload_1        
        //   132: getstatic       com/jetbrains/cidr/lang/parser/OCTokenTypes.ANDAND:Lcom/jetbrains/cidr/lang/parser/OCPunctuatorElementType;
        //   135: if_acmpne       198
        //   138: goto            145
        //   141: invokestatic    com/jetbrains/cidr/lang/util/OCExpressionEvaluator$ValueEvaluator.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   144: athrow         
        //   145: aload_3        
        //   146: ifnull          198
        //   149: goto            156
        //   152: invokestatic    com/jetbrains/cidr/lang/util/OCExpressionEvaluator$ValueEvaluator.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   155: athrow         
        //   156: aload_3        
        //   157: invokestatic    com/jetbrains/cidr/lang/util/OCExpressionEvaluator.singAsInC:(Ljava/lang/Object;)I
        //   160: ifne            198
        //   163: goto            170
        //   166: invokestatic    com/jetbrains/cidr/lang/util/OCExpressionEvaluator$ValueEvaluator.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   169: athrow         
        //   170: aload_3        
        //   171: invokestatic    com/jetbrains/cidr/lang/util/OCExpressionEvaluator.singAsInC:(Ljava/lang/Object;)I
        //   174: ifeq            194
        //   177: goto            184
        //   180: invokestatic    com/jetbrains/cidr/lang/util/OCExpressionEvaluator$ValueEvaluator.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   183: athrow         
        //   184: getstatic       java/lang/Boolean.TRUE:Ljava/lang/Boolean;
        //   187: goto            197
        //   190: invokestatic    com/jetbrains/cidr/lang/util/OCExpressionEvaluator$ValueEvaluator.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   193: athrow         
        //   194: getstatic       java/lang/Boolean.FALSE:Ljava/lang/Boolean;
        //   197: areturn        
        //   198: aload_2        
        //   199: ifnull          213
        //   202: aload_3        
        //   203: ifnonnull       219
        //   206: goto            213
        //   209: invokestatic    com/jetbrains/cidr/lang/util/OCExpressionEvaluator$ValueEvaluator.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   212: athrow         
        //   213: aconst_null    
        //   214: areturn        
        //   215: invokestatic    com/jetbrains/cidr/lang/util/OCExpressionEvaluator$ValueEvaluator.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   218: athrow         
        //   219: aload_2        
        //   220: instanceof      Ljava/lang/Boolean;
        //   223: ifeq            430
        //   226: aload_3        
        //   227: instanceof      Ljava/lang/Boolean;
        //   230: ifeq            430
        //   233: goto            240
        //   236: invokestatic    com/jetbrains/cidr/lang/util/OCExpressionEvaluator$ValueEvaluator.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   239: athrow         
        //   240: aload_2        
        //   241: checkcast       Ljava/lang/Boolean;
        //   244: astore          4
        //   246: aload_3        
        //   247: checkcast       Ljava/lang/Boolean;
        //   250: astore          5
        //   252: aload_1        
        //   253: getstatic       com/jetbrains/cidr/lang/parser/OCTokenTypes.OR:Lcom/jetbrains/cidr/lang/parser/OCPunctuatorElementType;
        //   256: if_acmpne       278
        //   259: aload           4
        //   261: invokevirtual   java/lang/Boolean.booleanValue:()Z
        //   264: aload           5
        //   266: invokevirtual   java/lang/Boolean.booleanValue:()Z
        //   269: ior            
        //   270: invokestatic    java/lang/Boolean.valueOf:(Z)Ljava/lang/Boolean;
        //   273: areturn        
        //   274: invokestatic    com/jetbrains/cidr/lang/util/OCExpressionEvaluator$ValueEvaluator.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   277: athrow         
        //   278: aload_1        
        //   279: getstatic       com/jetbrains/cidr/lang/parser/OCTokenTypes.XOR:Lcom/jetbrains/cidr/lang/parser/OCPunctuatorElementType;
        //   282: if_acmpne       304
        //   285: aload           4
        //   287: invokevirtual   java/lang/Boolean.booleanValue:()Z
        //   290: aload           5
        //   292: invokevirtual   java/lang/Boolean.booleanValue:()Z
        //   295: ixor           
        //   296: invokestatic    java/lang/Boolean.valueOf:(Z)Ljava/lang/Boolean;
        //   299: areturn        
        //   300: invokestatic    com/jetbrains/cidr/lang/util/OCExpressionEvaluator$ValueEvaluator.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   303: athrow         
        //   304: aload_1        
        //   305: getstatic       com/jetbrains/cidr/lang/parser/OCTokenTypes.AND:Lcom/jetbrains/cidr/lang/parser/OCPunctuatorElementType;
        //   308: if_acmpne       330
        //   311: aload           4
        //   313: invokevirtual   java/lang/Boolean.booleanValue:()Z
        //   316: aload           5
        //   318: invokevirtual   java/lang/Boolean.booleanValue:()Z
        //   321: iand           
        //   322: invokestatic    java/lang/Boolean.valueOf:(Z)Ljava/lang/Boolean;
        //   325: areturn        
        //   326: invokestatic    com/jetbrains/cidr/lang/util/OCExpressionEvaluator$ValueEvaluator.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   329: athrow         
        //   330: aload_1        
        //   331: getstatic       com/jetbrains/cidr/lang/parser/OCTokenTypes.OROR:Lcom/jetbrains/cidr/lang/parser/OCPunctuatorElementType;
        //   334: if_acmpne       380
        //   337: aload           4
        //   339: invokevirtual   java/lang/Boolean.booleanValue:()Z
        //   342: ifne            367
        //   345: goto            352
        //   348: invokestatic    com/jetbrains/cidr/lang/util/OCExpressionEvaluator$ValueEvaluator.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   351: athrow         
        //   352: aload           5
        //   354: invokevirtual   java/lang/Boolean.booleanValue:()Z
        //   357: ifeq            375
        //   360: goto            367
        //   363: invokestatic    com/jetbrains/cidr/lang/util/OCExpressionEvaluator$ValueEvaluator.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   366: athrow         
        //   367: iconst_1       
        //   368: goto            376
        //   371: invokestatic    com/jetbrains/cidr/lang/util/OCExpressionEvaluator$ValueEvaluator.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   374: athrow         
        //   375: iconst_0       
        //   376: invokestatic    java/lang/Boolean.valueOf:(Z)Ljava/lang/Boolean;
        //   379: areturn        
        //   380: aload_1        
        //   381: getstatic       com/jetbrains/cidr/lang/parser/OCTokenTypes.ANDAND:Lcom/jetbrains/cidr/lang/parser/OCPunctuatorElementType;
        //   384: if_acmpne       430
        //   387: aload           4
        //   389: invokevirtual   java/lang/Boolean.booleanValue:()Z
        //   392: ifeq            425
        //   395: goto            402
        //   398: invokestatic    com/jetbrains/cidr/lang/util/OCExpressionEvaluator$ValueEvaluator.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   401: athrow         
        //   402: aload           5
        //   404: invokevirtual   java/lang/Boolean.booleanValue:()Z
        //   407: ifeq            425
        //   410: goto            417
        //   413: invokestatic    com/jetbrains/cidr/lang/util/OCExpressionEvaluator$ValueEvaluator.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   416: athrow         
        //   417: iconst_1       
        //   418: goto            426
        //   421: invokestatic    com/jetbrains/cidr/lang/util/OCExpressionEvaluator$ValueEvaluator.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   424: athrow         
        //   425: iconst_0       
        //   426: invokestatic    java/lang/Boolean.valueOf:(Z)Ljava/lang/Boolean;
        //   429: areturn        
        //   430: aload_2        
        //   431: instanceof      Ljava/lang/Boolean;
        //   434: ifeq            448
        //   437: aload_2        
        //   438: checkcast       Ljava/lang/Boolean;
        //   441: invokevirtual   java/lang/Boolean.booleanValue:()Z
        //   444: invokestatic    com/jetbrains/cidr/lang/util/OCExpressionEvaluator.access$300:(Z)Ljava/lang/Long;
        //   447: astore_2       
        //   448: aload_3        
        //   449: instanceof      Ljava/lang/Boolean;
        //   452: ifeq            466
        //   455: aload_3        
        //   456: checkcast       Ljava/lang/Boolean;
        //   459: invokevirtual   java/lang/Boolean.booleanValue:()Z
        //   462: invokestatic    com/jetbrains/cidr/lang/util/OCExpressionEvaluator.access$300:(Z)Ljava/lang/Long;
        //   465: astore_3       
        //   466: aload_2        
        //   467: instanceof      Ljava/lang/Integer;
        //   470: ifeq            484
        //   473: aload_2        
        //   474: checkcast       Ljava/lang/Number;
        //   477: invokevirtual   java/lang/Number.longValue:()J
        //   480: invokestatic    java/lang/Long.valueOf:(J)Ljava/lang/Long;
        //   483: astore_2       
        //   484: aload_3        
        //   485: instanceof      Ljava/lang/Integer;
        //   488: ifeq            502
        //   491: aload_3        
        //   492: checkcast       Ljava/lang/Number;
        //   495: invokevirtual   java/lang/Number.longValue:()J
        //   498: invokestatic    java/lang/Long.valueOf:(J)Ljava/lang/Long;
        //   501: astore_3       
        //   502: aload_2        
        //   503: instanceof      Ljava/lang/Long;
        //   506: ifeq            1195
        //   509: aload_3        
        //   510: instanceof      Ljava/lang/Long;
        //   513: ifeq            1195
        //   516: goto            523
        //   519: invokestatic    com/jetbrains/cidr/lang/util/OCExpressionEvaluator$ValueEvaluator.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   522: athrow         
        //   523: aload_2        
        //   524: checkcast       Ljava/lang/Long;
        //   527: astore          4
        //   529: aload_3        
        //   530: checkcast       Ljava/lang/Long;
        //   533: astore          5
        //   535: aload_1        
        //   536: getstatic       com/jetbrains/cidr/lang/parser/OCTokenTypes.PLUS:Lcom/jetbrains/cidr/lang/parser/OCPunctuatorElementType;
        //   539: if_acmpne       561
        //   542: aload           4
        //   544: invokevirtual   java/lang/Long.longValue:()J
        //   547: aload           5
        //   549: invokevirtual   java/lang/Long.longValue:()J
        //   552: ladd           
        //   553: invokestatic    java/lang/Long.valueOf:(J)Ljava/lang/Long;
        //   556: areturn        
        //   557: invokestatic    com/jetbrains/cidr/lang/util/OCExpressionEvaluator$ValueEvaluator.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   560: athrow         
        //   561: aload_1        
        //   562: getstatic       com/jetbrains/cidr/lang/parser/OCTokenTypes.MINUS:Lcom/jetbrains/cidr/lang/parser/OCPunctuatorElementType;
        //   565: if_acmpne       587
        //   568: aload           4
        //   570: invokevirtual   java/lang/Long.longValue:()J
        //   573: aload           5
        //   575: invokevirtual   java/lang/Long.longValue:()J
        //   578: lsub           
        //   579: invokestatic    java/lang/Long.valueOf:(J)Ljava/lang/Long;
        //   582: areturn        
        //   583: invokestatic    com/jetbrains/cidr/lang/util/OCExpressionEvaluator$ValueEvaluator.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   586: athrow         
        //   587: aload_1        
        //   588: getstatic       com/jetbrains/cidr/lang/parser/OCTokenTypes.MUL:Lcom/jetbrains/cidr/lang/parser/OCPunctuatorElementType;
        //   591: if_acmpne       613
        //   594: aload           4
        //   596: invokevirtual   java/lang/Long.longValue:()J
        //   599: aload           5
        //   601: invokevirtual   java/lang/Long.longValue:()J
        //   604: lmul           
        //   605: invokestatic    java/lang/Long.valueOf:(J)Ljava/lang/Long;
        //   608: areturn        
        //   609: invokestatic    com/jetbrains/cidr/lang/util/OCExpressionEvaluator$ValueEvaluator.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   612: athrow         
        //   613: aload_1        
        //   614: getstatic       com/jetbrains/cidr/lang/parser/OCTokenTypes.DIV:Lcom/jetbrains/cidr/lang/parser/OCPunctuatorElementType;
        //   617: if_acmpne       660
        //   620: aload           5
        //   622: invokevirtual   java/lang/Long.longValue:()J
        //   625: lconst_0       
        //   626: lcmp           
        //   627: ifeq            658
        //   630: goto            637
        //   633: invokestatic    com/jetbrains/cidr/lang/util/OCExpressionEvaluator$ValueEvaluator.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   636: athrow         
        //   637: aload           4
        //   639: invokevirtual   java/lang/Long.longValue:()J
        //   642: aload           5
        //   644: invokevirtual   java/lang/Long.longValue:()J
        //   647: ldiv           
        //   648: invokestatic    java/lang/Long.valueOf:(J)Ljava/lang/Long;
        //   651: goto            659
        //   654: invokestatic    com/jetbrains/cidr/lang/util/OCExpressionEvaluator$ValueEvaluator.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   657: athrow         
        //   658: aconst_null    
        //   659: areturn        
        //   660: aload_1        
        //   661: getstatic       com/jetbrains/cidr/lang/parser/OCTokenTypes.PERC:Lcom/jetbrains/cidr/lang/parser/OCPunctuatorElementType;
        //   664: if_acmpne       707
        //   667: aload           5
        //   669: invokevirtual   java/lang/Long.longValue:()J
        //   672: lconst_0       
        //   673: lcmp           
        //   674: ifeq            705
        //   677: goto            684
        //   680: invokestatic    com/jetbrains/cidr/lang/util/OCExpressionEvaluator$ValueEvaluator.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   683: athrow         
        //   684: aload           4
        //   686: invokevirtual   java/lang/Long.longValue:()J
        //   689: aload           5
        //   691: invokevirtual   java/lang/Long.longValue:()J
        //   694: lrem           
        //   695: invokestatic    java/lang/Long.valueOf:(J)Ljava/lang/Long;
        //   698: goto            706
        //   701: invokestatic    com/jetbrains/cidr/lang/util/OCExpressionEvaluator$ValueEvaluator.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   704: athrow         
        //   705: aconst_null    
        //   706: areturn        
        //   707: aload_1        
        //   708: getstatic       com/jetbrains/cidr/lang/parser/OCTokenTypes.OR:Lcom/jetbrains/cidr/lang/parser/OCPunctuatorElementType;
        //   711: if_acmpne       733
        //   714: aload           4
        //   716: invokevirtual   java/lang/Long.longValue:()J
        //   719: aload           5
        //   721: invokevirtual   java/lang/Long.longValue:()J
        //   724: lor            
        //   725: invokestatic    java/lang/Long.valueOf:(J)Ljava/lang/Long;
        //   728: areturn        
        //   729: invokestatic    com/jetbrains/cidr/lang/util/OCExpressionEvaluator$ValueEvaluator.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   732: athrow         
        //   733: aload_1        
        //   734: getstatic       com/jetbrains/cidr/lang/parser/OCTokenTypes.XOR:Lcom/jetbrains/cidr/lang/parser/OCPunctuatorElementType;
        //   737: if_acmpne       759
        //   740: aload           4
        //   742: invokevirtual   java/lang/Long.longValue:()J
        //   745: aload           5
        //   747: invokevirtual   java/lang/Long.longValue:()J
        //   750: lxor           
        //   751: invokestatic    java/lang/Long.valueOf:(J)Ljava/lang/Long;
        //   754: areturn        
        //   755: invokestatic    com/jetbrains/cidr/lang/util/OCExpressionEvaluator$ValueEvaluator.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   758: athrow         
        //   759: aload_1        
        //   760: getstatic       com/jetbrains/cidr/lang/parser/OCTokenTypes.AND:Lcom/jetbrains/cidr/lang/parser/OCPunctuatorElementType;
        //   763: if_acmpne       785
        //   766: aload           4
        //   768: invokevirtual   java/lang/Long.longValue:()J
        //   771: aload           5
        //   773: invokevirtual   java/lang/Long.longValue:()J
        //   776: land           
        //   777: invokestatic    java/lang/Long.valueOf:(J)Ljava/lang/Long;
        //   780: areturn        
        //   781: invokestatic    com/jetbrains/cidr/lang/util/OCExpressionEvaluator$ValueEvaluator.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   784: athrow         
        //   785: aload_1        
        //   786: getstatic       com/jetbrains/cidr/lang/parser/OCTokenTypes.LTLT:Lcom/jetbrains/cidr/lang/parser/OCPunctuatorElementType;
        //   789: if_acmpne       812
        //   792: aload           4
        //   794: invokevirtual   java/lang/Long.longValue:()J
        //   797: aload           5
        //   799: invokevirtual   java/lang/Long.longValue:()J
        //   802: l2i            
        //   803: lshl           
        //   804: invokestatic    java/lang/Long.valueOf:(J)Ljava/lang/Long;
        //   807: areturn        
        //   808: invokestatic    com/jetbrains/cidr/lang/util/OCExpressionEvaluator$ValueEvaluator.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   811: athrow         
        //   812: aload_1        
        //   813: getstatic       com/jetbrains/cidr/lang/parser/OCTokenTypes.GTGT:Lcom/jetbrains/cidr/lang/parser/OCPunctuatorElementType;
        //   816: if_acmpne       839
        //   819: aload           4
        //   821: invokevirtual   java/lang/Long.longValue:()J
        //   824: aload           5
        //   826: invokevirtual   java/lang/Long.longValue:()J
        //   829: l2i            
        //   830: lshr           
        //   831: invokestatic    java/lang/Long.valueOf:(J)Ljava/lang/Long;
        //   834: areturn        
        //   835: invokestatic    com/jetbrains/cidr/lang/util/OCExpressionEvaluator$ValueEvaluator.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   838: athrow         
        //   839: aload_1        
        //   840: getstatic       com/jetbrains/cidr/lang/parser/OCTokenTypes.OROR:Lcom/jetbrains/cidr/lang/parser/OCPunctuatorElementType;
        //   843: if_acmpne       894
        //   846: aload           4
        //   848: invokevirtual   java/lang/Long.longValue:()J
        //   851: lconst_0       
        //   852: lcmp           
        //   853: ifne            880
        //   856: goto            863
        //   859: invokestatic    com/jetbrains/cidr/lang/util/OCExpressionEvaluator$ValueEvaluator.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   862: athrow         
        //   863: aload           5
        //   865: invokevirtual   java/lang/Long.longValue:()J
        //   868: lconst_0       
        //   869: lcmp           
        //   870: ifeq            890
        //   873: goto            880
        //   876: invokestatic    com/jetbrains/cidr/lang/util/OCExpressionEvaluator$ValueEvaluator.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   879: athrow         
        //   880: getstatic       java/lang/Boolean.TRUE:Ljava/lang/Boolean;
        //   883: goto            893
        //   886: invokestatic    com/jetbrains/cidr/lang/util/OCExpressionEvaluator$ValueEvaluator.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   889: athrow         
        //   890: getstatic       java/lang/Boolean.FALSE:Ljava/lang/Boolean;
        //   893: areturn        
        //   894: aload_1        
        //   895: getstatic       com/jetbrains/cidr/lang/parser/OCTokenTypes.ANDAND:Lcom/jetbrains/cidr/lang/parser/OCPunctuatorElementType;
        //   898: if_acmpne       949
        //   901: aload           4
        //   903: invokevirtual   java/lang/Long.longValue:()J
        //   906: lconst_0       
        //   907: lcmp           
        //   908: ifeq            945
        //   911: goto            918
        //   914: invokestatic    com/jetbrains/cidr/lang/util/OCExpressionEvaluator$ValueEvaluator.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   917: athrow         
        //   918: aload           5
        //   920: invokevirtual   java/lang/Long.longValue:()J
        //   923: lconst_0       
        //   924: lcmp           
        //   925: ifeq            945
        //   928: goto            935
        //   931: invokestatic    com/jetbrains/cidr/lang/util/OCExpressionEvaluator$ValueEvaluator.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   934: athrow         
        //   935: getstatic       java/lang/Boolean.TRUE:Ljava/lang/Boolean;
        //   938: goto            948
        //   941: invokestatic    com/jetbrains/cidr/lang/util/OCExpressionEvaluator$ValueEvaluator.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   944: athrow         
        //   945: getstatic       java/lang/Boolean.FALSE:Ljava/lang/Boolean;
        //   948: areturn        
        //   949: aload_1        
        //   950: getstatic       com/jetbrains/cidr/lang/parser/OCTokenTypes.LT:Lcom/jetbrains/cidr/lang/parser/OCPunctuatorElementType;
        //   953: if_acmpne       990
        //   956: aload           4
        //   958: invokevirtual   java/lang/Long.longValue:()J
        //   961: aload           5
        //   963: invokevirtual   java/lang/Long.longValue:()J
        //   966: lcmp           
        //   967: ifge            985
        //   970: goto            977
        //   973: invokestatic    com/jetbrains/cidr/lang/util/OCExpressionEvaluator$ValueEvaluator.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   976: athrow         
        //   977: iconst_1       
        //   978: goto            986
        //   981: invokestatic    com/jetbrains/cidr/lang/util/OCExpressionEvaluator$ValueEvaluator.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   984: athrow         
        //   985: iconst_0       
        //   986: invokestatic    java/lang/Boolean.valueOf:(Z)Ljava/lang/Boolean;
        //   989: areturn        
        //   990: aload_1        
        //   991: getstatic       com/jetbrains/cidr/lang/parser/OCTokenTypes.LTEQ:Lcom/jetbrains/cidr/lang/parser/OCPunctuatorElementType;
        //   994: if_acmpne       1031
        //   997: aload           4
        //   999: invokevirtual   java/lang/Long.longValue:()J
        //  1002: aload           5
        //  1004: invokevirtual   java/lang/Long.longValue:()J
        //  1007: lcmp           
        //  1008: ifgt            1026
        //  1011: goto            1018
        //  1014: invokestatic    com/jetbrains/cidr/lang/util/OCExpressionEvaluator$ValueEvaluator.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //  1017: athrow         
        //  1018: iconst_1       
        //  1019: goto            1027
        //  1022: invokestatic    com/jetbrains/cidr/lang/util/OCExpressionEvaluator$ValueEvaluator.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //  1025: athrow         
        //  1026: iconst_0       
        //  1027: invokestatic    java/lang/Boolean.valueOf:(Z)Ljava/lang/Boolean;
        //  1030: areturn        
        //  1031: aload_1        
        //  1032: getstatic       com/jetbrains/cidr/lang/parser/OCTokenTypes.GT:Lcom/jetbrains/cidr/lang/parser/OCPunctuatorElementType;
        //  1035: if_acmpne       1072
        //  1038: aload           4
        //  1040: invokevirtual   java/lang/Long.longValue:()J
        //  1043: aload           5
        //  1045: invokevirtual   java/lang/Long.longValue:()J
        //  1048: lcmp           
        //  1049: ifle            1067
        //  1052: goto            1059
        //  1055: invokestatic    com/jetbrains/cidr/lang/util/OCExpressionEvaluator$ValueEvaluator.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //  1058: athrow         
        //  1059: iconst_1       
        //  1060: goto            1068
        //  1063: invokestatic    com/jetbrains/cidr/lang/util/OCExpressionEvaluator$ValueEvaluator.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //  1066: athrow         
        //  1067: iconst_0       
        //  1068: invokestatic    java/lang/Boolean.valueOf:(Z)Ljava/lang/Boolean;
        //  1071: areturn        
        //  1072: aload_1        
        //  1073: getstatic       com/jetbrains/cidr/lang/parser/OCTokenTypes.GTEQ:Lcom/jetbrains/cidr/lang/parser/OCPunctuatorElementType;
        //  1076: if_acmpne       1113
        //  1079: aload           4
        //  1081: invokevirtual   java/lang/Long.longValue:()J
        //  1084: aload           5
        //  1086: invokevirtual   java/lang/Long.longValue:()J
        //  1089: lcmp           
        //  1090: iflt            1108
        //  1093: goto            1100
        //  1096: invokestatic    com/jetbrains/cidr/lang/util/OCExpressionEvaluator$ValueEvaluator.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //  1099: athrow         
        //  1100: iconst_1       
        //  1101: goto            1109
        //  1104: invokestatic    com/jetbrains/cidr/lang/util/OCExpressionEvaluator$ValueEvaluator.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //  1107: athrow         
        //  1108: iconst_0       
        //  1109: invokestatic    java/lang/Boolean.valueOf:(Z)Ljava/lang/Boolean;
        //  1112: areturn        
        //  1113: aload_1        
        //  1114: getstatic       com/jetbrains/cidr/lang/parser/OCTokenTypes.EQEQ:Lcom/jetbrains/cidr/lang/parser/OCPunctuatorElementType;
        //  1117: if_acmpne       1154
        //  1120: aload           4
        //  1122: invokevirtual   java/lang/Long.longValue:()J
        //  1125: aload           5
        //  1127: invokevirtual   java/lang/Long.longValue:()J
        //  1130: lcmp           
        //  1131: ifne            1149
        //  1134: goto            1141
        //  1137: invokestatic    com/jetbrains/cidr/lang/util/OCExpressionEvaluator$ValueEvaluator.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //  1140: athrow         
        //  1141: iconst_1       
        //  1142: goto            1150
        //  1145: invokestatic    com/jetbrains/cidr/lang/util/OCExpressionEvaluator$ValueEvaluator.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //  1148: athrow         
        //  1149: iconst_0       
        //  1150: invokestatic    java/lang/Boolean.valueOf:(Z)Ljava/lang/Boolean;
        //  1153: areturn        
        //  1154: aload_1        
        //  1155: getstatic       com/jetbrains/cidr/lang/parser/OCTokenTypes.EXCLEQ:Lcom/jetbrains/cidr/lang/parser/OCPunctuatorElementType;
        //  1158: if_acmpne       1195
        //  1161: aload           4
        //  1163: invokevirtual   java/lang/Long.longValue:()J
        //  1166: aload           5
        //  1168: invokevirtual   java/lang/Long.longValue:()J
        //  1171: lcmp           
        //  1172: ifeq            1190
        //  1175: goto            1182
        //  1178: invokestatic    com/jetbrains/cidr/lang/util/OCExpressionEvaluator$ValueEvaluator.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //  1181: athrow         
        //  1182: iconst_1       
        //  1183: goto            1191
        //  1186: invokestatic    com/jetbrains/cidr/lang/util/OCExpressionEvaluator$ValueEvaluator.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //  1189: athrow         
        //  1190: iconst_0       
        //  1191: invokestatic    java/lang/Boolean.valueOf:(Z)Ljava/lang/Boolean;
        //  1194: areturn        
        //  1195: aload_2        
        //  1196: instanceof      Ljava/lang/Long;
        //  1199: ifeq            1213
        //  1202: aload_2        
        //  1203: checkcast       Ljava/lang/Number;
        //  1206: invokevirtual   java/lang/Number.longValue:()J
        //  1209: invokestatic    com/jetbrains/cidr/lang/util/OCNumber.valueOf:(J)Lcom/jetbrains/cidr/lang/util/OCNumber;
        //  1212: astore_2       
        //  1213: aload_3        
        //  1214: instanceof      Ljava/lang/Long;
        //  1217: ifeq            1231
        //  1220: aload_3        
        //  1221: checkcast       Ljava/lang/Number;
        //  1224: invokevirtual   java/lang/Number.longValue:()J
        //  1227: invokestatic    com/jetbrains/cidr/lang/util/OCNumber.valueOf:(J)Lcom/jetbrains/cidr/lang/util/OCNumber;
        //  1230: astore_3       
        //  1231: aload_2        
        //  1232: instanceof      Lcom/jetbrains/cidr/lang/util/OCNumber;
        //  1235: ifeq            1881
        //  1238: aload_3        
        //  1239: instanceof      Lcom/jetbrains/cidr/lang/util/OCNumber;
        //  1242: ifeq            1881
        //  1245: goto            1252
        //  1248: invokestatic    com/jetbrains/cidr/lang/util/OCExpressionEvaluator$ValueEvaluator.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //  1251: athrow         
        //  1252: aload_2        
        //  1253: checkcast       Lcom/jetbrains/cidr/lang/util/OCNumber;
        //  1256: astore          4
        //  1258: aload_3        
        //  1259: checkcast       Lcom/jetbrains/cidr/lang/util/OCNumber;
        //  1262: astore          5
        //  1264: aload_1        
        //  1265: getstatic       com/jetbrains/cidr/lang/parser/OCTokenTypes.PLUS:Lcom/jetbrains/cidr/lang/parser/OCPunctuatorElementType;
        //  1268: if_acmpne       1283
        //  1271: aload           4
        //  1273: aload           5
        //  1275: invokevirtual   com/jetbrains/cidr/lang/util/OCNumber.add:(Ljava/math/BigInteger;)Lcom/jetbrains/cidr/lang/util/OCNumber;
        //  1278: areturn        
        //  1279: invokestatic    com/jetbrains/cidr/lang/util/OCExpressionEvaluator$ValueEvaluator.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //  1282: athrow         
        //  1283: aload_1        
        //  1284: getstatic       com/jetbrains/cidr/lang/parser/OCTokenTypes.MINUS:Lcom/jetbrains/cidr/lang/parser/OCPunctuatorElementType;
        //  1287: if_acmpne       1302
        //  1290: aload           4
        //  1292: aload           5
        //  1294: invokevirtual   com/jetbrains/cidr/lang/util/OCNumber.subtract:(Ljava/math/BigInteger;)Lcom/jetbrains/cidr/lang/util/OCNumber;
        //  1297: areturn        
        //  1298: invokestatic    com/jetbrains/cidr/lang/util/OCExpressionEvaluator$ValueEvaluator.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //  1301: athrow         
        //  1302: aload_1        
        //  1303: getstatic       com/jetbrains/cidr/lang/parser/OCTokenTypes.MUL:Lcom/jetbrains/cidr/lang/parser/OCPunctuatorElementType;
        //  1306: if_acmpne       1321
        //  1309: aload           4
        //  1311: aload           5
        //  1313: invokevirtual   com/jetbrains/cidr/lang/util/OCNumber.multiply:(Ljava/math/BigInteger;)Lcom/jetbrains/cidr/lang/util/OCNumber;
        //  1316: areturn        
        //  1317: invokestatic    com/jetbrains/cidr/lang/util/OCExpressionEvaluator$ValueEvaluator.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //  1320: athrow         
        //  1321: aload_1        
        //  1322: getstatic       com/jetbrains/cidr/lang/parser/OCTokenTypes.DIV:Lcom/jetbrains/cidr/lang/parser/OCPunctuatorElementType;
        //  1325: if_acmpne       1362
        //  1328: aload           5
        //  1330: getstatic       java/math/BigInteger.ZERO:Ljava/math/BigInteger;
        //  1333: invokevirtual   com/jetbrains/cidr/lang/util/OCNumber.equals:(Ljava/lang/Object;)Z
        //  1336: ifeq            1354
        //  1339: goto            1346
        //  1342: invokestatic    com/jetbrains/cidr/lang/util/OCExpressionEvaluator$ValueEvaluator.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //  1345: athrow         
        //  1346: aconst_null    
        //  1347: goto            1361
        //  1350: invokestatic    com/jetbrains/cidr/lang/util/OCExpressionEvaluator$ValueEvaluator.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //  1353: athrow         
        //  1354: aload           4
        //  1356: aload           5
        //  1358: invokevirtual   com/jetbrains/cidr/lang/util/OCNumber.divide:(Ljava/math/BigInteger;)Lcom/jetbrains/cidr/lang/util/OCNumber;
        //  1361: areturn        
        //  1362: aload_1        
        //  1363: getstatic       com/jetbrains/cidr/lang/parser/OCTokenTypes.PERC:Lcom/jetbrains/cidr/lang/parser/OCPunctuatorElementType;
        //  1366: if_acmpne       1450
        //  1369: aload           5
        //  1371: getstatic       java/math/BigInteger.ZERO:Ljava/math/BigInteger;
        //  1374: invokevirtual   com/jetbrains/cidr/lang/util/OCNumber.equals:(Ljava/lang/Object;)Z
        //  1377: ifeq            1393
        //  1380: goto            1387
        //  1383: invokestatic    com/jetbrains/cidr/lang/util/OCExpressionEvaluator$ValueEvaluator.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //  1386: athrow         
        //  1387: aconst_null    
        //  1388: areturn        
        //  1389: invokestatic    com/jetbrains/cidr/lang/util/OCExpressionEvaluator$ValueEvaluator.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //  1392: athrow         
        //  1393: aload           4
        //  1395: getstatic       java/math/BigInteger.ZERO:Ljava/math/BigInteger;
        //  1398: invokevirtual   com/jetbrains/cidr/lang/util/OCNumber.compareTo:(Ljava/math/BigInteger;)I
        //  1401: iflt            1412
        //  1404: iconst_1       
        //  1405: goto            1413
        //  1408: invokestatic    com/jetbrains/cidr/lang/util/OCExpressionEvaluator$ValueEvaluator.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //  1411: athrow         
        //  1412: iconst_0       
        //  1413: istore          6
        //  1415: aload           4
        //  1417: invokevirtual   com/jetbrains/cidr/lang/util/OCNumber.abs:()Ljava/math/BigInteger;
        //  1420: aload           5
        //  1422: invokevirtual   com/jetbrains/cidr/lang/util/OCNumber.abs:()Ljava/math/BigInteger;
        //  1425: invokevirtual   java/math/BigInteger.mod:(Ljava/math/BigInteger;)Ljava/math/BigInteger;
        //  1428: astore          7
        //  1430: iload           6
        //  1432: ifeq            1444
        //  1435: aload           7
        //  1437: goto            1449
        //  1440: invokestatic    com/jetbrains/cidr/lang/util/OCExpressionEvaluator$ValueEvaluator.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //  1443: athrow         
        //  1444: aload           7
        //  1446: invokevirtual   java/math/BigInteger.negate:()Ljava/math/BigInteger;
        //  1449: areturn        
        //  1450: aload_1        
        //  1451: getstatic       com/jetbrains/cidr/lang/parser/OCTokenTypes.OR:Lcom/jetbrains/cidr/lang/parser/OCPunctuatorElementType;
        //  1454: if_acmpne       1469
        //  1457: aload           4
        //  1459: aload           5
        //  1461: invokevirtual   com/jetbrains/cidr/lang/util/OCNumber.or:(Ljava/math/BigInteger;)Lcom/jetbrains/cidr/lang/util/OCNumber;
        //  1464: areturn        
        //  1465: invokestatic    com/jetbrains/cidr/lang/util/OCExpressionEvaluator$ValueEvaluator.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //  1468: athrow         
        //  1469: aload_1        
        //  1470: getstatic       com/jetbrains/cidr/lang/parser/OCTokenTypes.XOR:Lcom/jetbrains/cidr/lang/parser/OCPunctuatorElementType;
        //  1473: if_acmpne       1488
        //  1476: aload           4
        //  1478: aload           5
        //  1480: invokevirtual   com/jetbrains/cidr/lang/util/OCNumber.xor:(Ljava/math/BigInteger;)Lcom/jetbrains/cidr/lang/util/OCNumber;
        //  1483: areturn        
        //  1484: invokestatic    com/jetbrains/cidr/lang/util/OCExpressionEvaluator$ValueEvaluator.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //  1487: athrow         
        //  1488: aload_1        
        //  1489: getstatic       com/jetbrains/cidr/lang/parser/OCTokenTypes.AND:Lcom/jetbrains/cidr/lang/parser/OCPunctuatorElementType;
        //  1492: if_acmpne       1507
        //  1495: aload           4
        //  1497: aload           5
        //  1499: invokevirtual   com/jetbrains/cidr/lang/util/OCNumber.and:(Ljava/math/BigInteger;)Lcom/jetbrains/cidr/lang/util/OCNumber;
        //  1502: areturn        
        //  1503: invokestatic    com/jetbrains/cidr/lang/util/OCExpressionEvaluator$ValueEvaluator.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //  1506: athrow         
        //  1507: aload_1        
        //  1508: getstatic       com/jetbrains/cidr/lang/parser/OCTokenTypes.LTLT:Lcom/jetbrains/cidr/lang/parser/OCPunctuatorElementType;
        //  1511: if_acmpne       1526
        //  1514: aload           4
        //  1516: aload           5
        //  1518: invokevirtual   com/jetbrains/cidr/lang/util/OCNumber.shiftLeft:(Ljava/math/BigInteger;)Lcom/jetbrains/cidr/lang/util/OCNumber;
        //  1521: areturn        
        //  1522: invokestatic    com/jetbrains/cidr/lang/util/OCExpressionEvaluator$ValueEvaluator.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //  1525: athrow         
        //  1526: aload_1        
        //  1527: getstatic       com/jetbrains/cidr/lang/parser/OCTokenTypes.GTGT:Lcom/jetbrains/cidr/lang/parser/OCPunctuatorElementType;
        //  1530: if_acmpne       1545
        //  1533: aload           4
        //  1535: aload           5
        //  1537: invokevirtual   com/jetbrains/cidr/lang/util/OCNumber.shiftRight:(Ljava/math/BigInteger;)Lcom/jetbrains/cidr/lang/util/OCNumber;
        //  1540: areturn        
        //  1541: invokestatic    com/jetbrains/cidr/lang/util/OCExpressionEvaluator$ValueEvaluator.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //  1544: athrow         
        //  1545: aload_1        
        //  1546: getstatic       com/jetbrains/cidr/lang/parser/OCTokenTypes.OROR:Lcom/jetbrains/cidr/lang/parser/OCPunctuatorElementType;
        //  1549: if_acmpne       1602
        //  1552: aload           4
        //  1554: getstatic       java/math/BigInteger.ZERO:Ljava/math/BigInteger;
        //  1557: invokevirtual   com/jetbrains/cidr/lang/util/OCNumber.equals:(Ljava/lang/Object;)Z
        //  1560: ifeq            1588
        //  1563: goto            1570
        //  1566: invokestatic    com/jetbrains/cidr/lang/util/OCExpressionEvaluator$ValueEvaluator.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //  1569: athrow         
        //  1570: aload           5
        //  1572: getstatic       java/math/BigInteger.ZERO:Ljava/math/BigInteger;
        //  1575: invokevirtual   com/jetbrains/cidr/lang/util/OCNumber.equals:(Ljava/lang/Object;)Z
        //  1578: ifne            1598
        //  1581: goto            1588
        //  1584: invokestatic    com/jetbrains/cidr/lang/util/OCExpressionEvaluator$ValueEvaluator.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //  1587: athrow         
        //  1588: getstatic       java/lang/Boolean.TRUE:Ljava/lang/Boolean;
        //  1591: goto            1601
        //  1594: invokestatic    com/jetbrains/cidr/lang/util/OCExpressionEvaluator$ValueEvaluator.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //  1597: athrow         
        //  1598: getstatic       java/lang/Boolean.FALSE:Ljava/lang/Boolean;
        //  1601: areturn        
        //  1602: aload_1        
        //  1603: getstatic       com/jetbrains/cidr/lang/parser/OCTokenTypes.ANDAND:Lcom/jetbrains/cidr/lang/parser/OCPunctuatorElementType;
        //  1606: if_acmpne       1659
        //  1609: aload           4
        //  1611: getstatic       java/math/BigInteger.ZERO:Ljava/math/BigInteger;
        //  1614: invokevirtual   com/jetbrains/cidr/lang/util/OCNumber.equals:(Ljava/lang/Object;)Z
        //  1617: ifne            1655
        //  1620: goto            1627
        //  1623: invokestatic    com/jetbrains/cidr/lang/util/OCExpressionEvaluator$ValueEvaluator.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //  1626: athrow         
        //  1627: aload           5
        //  1629: getstatic       java/math/BigInteger.ZERO:Ljava/math/BigInteger;
        //  1632: invokevirtual   com/jetbrains/cidr/lang/util/OCNumber.equals:(Ljava/lang/Object;)Z
        //  1635: ifne            1655
        //  1638: goto            1645
        //  1641: invokestatic    com/jetbrains/cidr/lang/util/OCExpressionEvaluator$ValueEvaluator.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //  1644: athrow         
        //  1645: getstatic       java/lang/Boolean.TRUE:Ljava/lang/Boolean;
        //  1648: goto            1658
        //  1651: invokestatic    com/jetbrains/cidr/lang/util/OCExpressionEvaluator$ValueEvaluator.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //  1654: athrow         
        //  1655: getstatic       java/lang/Boolean.FALSE:Ljava/lang/Boolean;
        //  1658: areturn        
        //  1659: aload_1        
        //  1660: getstatic       com/jetbrains/cidr/lang/parser/OCTokenTypes.LT:Lcom/jetbrains/cidr/lang/parser/OCPunctuatorElementType;
        //  1663: if_acmpne       1696
        //  1666: aload           4
        //  1668: aload           5
        //  1670: invokevirtual   com/jetbrains/cidr/lang/util/OCNumber.compareTo:(Ljava/math/BigInteger;)I
        //  1673: ifge            1691
        //  1676: goto            1683
        //  1679: invokestatic    com/jetbrains/cidr/lang/util/OCExpressionEvaluator$ValueEvaluator.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //  1682: athrow         
        //  1683: iconst_1       
        //  1684: goto            1692
        //  1687: invokestatic    com/jetbrains/cidr/lang/util/OCExpressionEvaluator$ValueEvaluator.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //  1690: athrow         
        //  1691: iconst_0       
        //  1692: invokestatic    java/lang/Boolean.valueOf:(Z)Ljava/lang/Boolean;
        //  1695: areturn        
        //  1696: aload_1        
        //  1697: getstatic       com/jetbrains/cidr/lang/parser/OCTokenTypes.LTEQ:Lcom/jetbrains/cidr/lang/parser/OCPunctuatorElementType;
        //  1700: if_acmpne       1733
        //  1703: aload           4
        //  1705: aload           5
        //  1707: invokevirtual   com/jetbrains/cidr/lang/util/OCNumber.compareTo:(Ljava/math/BigInteger;)I
        //  1710: ifgt            1728
        //  1713: goto            1720
        //  1716: invokestatic    com/jetbrains/cidr/lang/util/OCExpressionEvaluator$ValueEvaluator.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //  1719: athrow         
        //  1720: iconst_1       
        //  1721: goto            1729
        //  1724: invokestatic    com/jetbrains/cidr/lang/util/OCExpressionEvaluator$ValueEvaluator.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //  1727: athrow         
        //  1728: iconst_0       
        //  1729: invokestatic    java/lang/Boolean.valueOf:(Z)Ljava/lang/Boolean;
        //  1732: areturn        
        //  1733: aload_1        
        //  1734: getstatic       com/jetbrains/cidr/lang/parser/OCTokenTypes.GT:Lcom/jetbrains/cidr/lang/parser/OCPunctuatorElementType;
        //  1737: if_acmpne       1770
        //  1740: aload           4
        //  1742: aload           5
        //  1744: invokevirtual   com/jetbrains/cidr/lang/util/OCNumber.compareTo:(Ljava/math/BigInteger;)I
        //  1747: ifle            1765
        //  1750: goto            1757
        //  1753: invokestatic    com/jetbrains/cidr/lang/util/OCExpressionEvaluator$ValueEvaluator.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //  1756: athrow         
        //  1757: iconst_1       
        //  1758: goto            1766
        //  1761: invokestatic    com/jetbrains/cidr/lang/util/OCExpressionEvaluator$ValueEvaluator.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //  1764: athrow         
        //  1765: iconst_0       
        //  1766: invokestatic    java/lang/Boolean.valueOf:(Z)Ljava/lang/Boolean;
        //  1769: areturn        
        //  1770: aload_1        
        //  1771: getstatic       com/jetbrains/cidr/lang/parser/OCTokenTypes.GTEQ:Lcom/jetbrains/cidr/lang/parser/OCPunctuatorElementType;
        //  1774: if_acmpne       1807
        //  1777: aload           4
        //  1779: aload           5
        //  1781: invokevirtual   com/jetbrains/cidr/lang/util/OCNumber.compareTo:(Ljava/math/BigInteger;)I
        //  1784: iflt            1802
        //  1787: goto            1794
        //  1790: invokestatic    com/jetbrains/cidr/lang/util/OCExpressionEvaluator$ValueEvaluator.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //  1793: athrow         
        //  1794: iconst_1       
        //  1795: goto            1803
        //  1798: invokestatic    com/jetbrains/cidr/lang/util/OCExpressionEvaluator$ValueEvaluator.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //  1801: athrow         
        //  1802: iconst_0       
        //  1803: invokestatic    java/lang/Boolean.valueOf:(Z)Ljava/lang/Boolean;
        //  1806: areturn        
        //  1807: aload_1        
        //  1808: getstatic       com/jetbrains/cidr/lang/parser/OCTokenTypes.EQEQ:Lcom/jetbrains/cidr/lang/parser/OCPunctuatorElementType;
        //  1811: if_acmpne       1844
        //  1814: aload           4
        //  1816: aload           5
        //  1818: invokevirtual   com/jetbrains/cidr/lang/util/OCNumber.compareTo:(Ljava/math/BigInteger;)I
        //  1821: ifne            1839
        //  1824: goto            1831
        //  1827: invokestatic    com/jetbrains/cidr/lang/util/OCExpressionEvaluator$ValueEvaluator.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //  1830: athrow         
        //  1831: iconst_1       
        //  1832: goto            1840
        //  1835: invokestatic    com/jetbrains/cidr/lang/util/OCExpressionEvaluator$ValueEvaluator.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //  1838: athrow         
        //  1839: iconst_0       
        //  1840: invokestatic    java/lang/Boolean.valueOf:(Z)Ljava/lang/Boolean;
        //  1843: areturn        
        //  1844: aload_1        
        //  1845: getstatic       com/jetbrains/cidr/lang/parser/OCTokenTypes.EXCLEQ:Lcom/jetbrains/cidr/lang/parser/OCPunctuatorElementType;
        //  1848: if_acmpne       1881
        //  1851: aload           4
        //  1853: aload           5
        //  1855: invokevirtual   com/jetbrains/cidr/lang/util/OCNumber.compareTo:(Ljava/math/BigInteger;)I
        //  1858: ifeq            1876
        //  1861: goto            1868
        //  1864: invokestatic    com/jetbrains/cidr/lang/util/OCExpressionEvaluator$ValueEvaluator.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //  1867: athrow         
        //  1868: iconst_1       
        //  1869: goto            1877
        //  1872: invokestatic    com/jetbrains/cidr/lang/util/OCExpressionEvaluator$ValueEvaluator.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //  1875: athrow         
        //  1876: iconst_0       
        //  1877: invokestatic    java/lang/Boolean.valueOf:(Z)Ljava/lang/Boolean;
        //  1880: areturn        
        //  1881: aload_2        
        //  1882: instanceof      Ljava/lang/Number;
        //  1885: ifeq            1908
        //  1888: aload_3        
        //  1889: instanceof      Ljava/lang/Number;
        //  1892: ifeq            1908
        //  1895: goto            1902
        //  1898: invokestatic    com/jetbrains/cidr/lang/util/OCExpressionEvaluator$ValueEvaluator.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //  1901: athrow         
        //  1902: aconst_null    
        //  1903: areturn        
        //  1904: invokestatic    com/jetbrains/cidr/lang/util/OCExpressionEvaluator$ValueEvaluator.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //  1907: athrow         
        //  1908: invokestatic    com/jetbrains/cidr/lang/util/OCExpressionEvaluator.access$400:()Lcom/intellij/openapi/diagnostic/Logger;
        //  1911: new             Ljava/lang/StringBuilder;
        //  1914: dup            
        //  1915: invokespecial   java/lang/StringBuilder.<init>:()V
        //  1918: ldc             "Unexpected binary operation: "
        //  1920: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //  1923: aload_1        
        //  1924: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/Object;)Ljava/lang/StringBuilder;
        //  1927: invokevirtual   java/lang/StringBuilder.toString:()Ljava/lang/String;
        //  1930: invokevirtual   com/intellij/openapi/diagnostic/Logger.error:(Ljava/lang/String;)V
        //  1933: aconst_null    
        //  1934: areturn        
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                                
        //  -----  -----  -----  -----  ------------------------------------
        //  0      11     14     18     Ljava/lang/IllegalArgumentException;
        //  7      25     28     32     Ljava/lang/IllegalArgumentException;
        //  18     39     42     46     Ljava/lang/IllegalArgumentException;
        //  32     50     53     57     Ljava/lang/IllegalArgumentException;
        //  46     64     67     71     Ljava/lang/IllegalArgumentException;
        //  57     78     81     85     Ljava/lang/IllegalArgumentException;
        //  71     91     91     95     Ljava/lang/IllegalArgumentException;
        //  99     110    113    117    Ljava/lang/IllegalArgumentException;
        //  106    124    127    131    Ljava/lang/IllegalArgumentException;
        //  117    138    141    145    Ljava/lang/IllegalArgumentException;
        //  131    149    152    156    Ljava/lang/IllegalArgumentException;
        //  145    163    166    170    Ljava/lang/IllegalArgumentException;
        //  156    177    180    184    Ljava/lang/IllegalArgumentException;
        //  170    190    190    194    Ljava/lang/IllegalArgumentException;
        //  198    206    209    213    Ljava/lang/IllegalArgumentException;
        //  202    215    215    219    Ljava/lang/IllegalArgumentException;
        //  219    233    236    240    Ljava/lang/IllegalArgumentException;
        //  252    274    274    278    Ljava/lang/IllegalArgumentException;
        //  278    300    300    304    Ljava/lang/IllegalArgumentException;
        //  304    326    326    330    Ljava/lang/IllegalArgumentException;
        //  330    345    348    352    Ljava/lang/IllegalArgumentException;
        //  337    360    363    367    Ljava/lang/IllegalArgumentException;
        //  352    371    371    375    Ljava/lang/IllegalArgumentException;
        //  380    395    398    402    Ljava/lang/IllegalArgumentException;
        //  387    410    413    417    Ljava/lang/IllegalArgumentException;
        //  402    421    421    425    Ljava/lang/IllegalArgumentException;
        //  502    516    519    523    Ljava/lang/IllegalArgumentException;
        //  535    557    557    561    Ljava/lang/IllegalArgumentException;
        //  561    583    583    587    Ljava/lang/IllegalArgumentException;
        //  587    609    609    613    Ljava/lang/IllegalArgumentException;
        //  613    630    633    637    Ljava/lang/IllegalArgumentException;
        //  620    654    654    658    Ljava/lang/IllegalArgumentException;
        //  660    677    680    684    Ljava/lang/IllegalArgumentException;
        //  667    701    701    705    Ljava/lang/IllegalArgumentException;
        //  707    729    729    733    Ljava/lang/IllegalArgumentException;
        //  733    755    755    759    Ljava/lang/IllegalArgumentException;
        //  759    781    781    785    Ljava/lang/IllegalArgumentException;
        //  785    808    808    812    Ljava/lang/IllegalArgumentException;
        //  812    835    835    839    Ljava/lang/IllegalArgumentException;
        //  839    856    859    863    Ljava/lang/IllegalArgumentException;
        //  846    873    876    880    Ljava/lang/IllegalArgumentException;
        //  863    886    886    890    Ljava/lang/IllegalArgumentException;
        //  894    911    914    918    Ljava/lang/IllegalArgumentException;
        //  901    928    931    935    Ljava/lang/IllegalArgumentException;
        //  918    941    941    945    Ljava/lang/IllegalArgumentException;
        //  949    970    973    977    Ljava/lang/IllegalArgumentException;
        //  956    981    981    985    Ljava/lang/IllegalArgumentException;
        //  990    1011   1014   1018   Ljava/lang/IllegalArgumentException;
        //  997    1022   1022   1026   Ljava/lang/IllegalArgumentException;
        //  1031   1052   1055   1059   Ljava/lang/IllegalArgumentException;
        //  1038   1063   1063   1067   Ljava/lang/IllegalArgumentException;
        //  1072   1093   1096   1100   Ljava/lang/IllegalArgumentException;
        //  1079   1104   1104   1108   Ljava/lang/IllegalArgumentException;
        //  1113   1134   1137   1141   Ljava/lang/IllegalArgumentException;
        //  1120   1145   1145   1149   Ljava/lang/IllegalArgumentException;
        //  1154   1175   1178   1182   Ljava/lang/IllegalArgumentException;
        //  1161   1186   1186   1190   Ljava/lang/IllegalArgumentException;
        //  1231   1245   1248   1252   Ljava/lang/IllegalArgumentException;
        //  1264   1279   1279   1283   Ljava/lang/IllegalArgumentException;
        //  1283   1298   1298   1302   Ljava/lang/IllegalArgumentException;
        //  1302   1317   1317   1321   Ljava/lang/IllegalArgumentException;
        //  1321   1339   1342   1346   Ljava/lang/IllegalArgumentException;
        //  1328   1350   1350   1354   Ljava/lang/IllegalArgumentException;
        //  1362   1380   1383   1387   Ljava/lang/IllegalArgumentException;
        //  1369   1389   1389   1393   Ljava/lang/IllegalArgumentException;
        //  1393   1408   1408   1412   Ljava/lang/IllegalArgumentException;
        //  1430   1440   1440   1444   Ljava/lang/IllegalArgumentException;
        //  1450   1465   1465   1469   Ljava/lang/IllegalArgumentException;
        //  1469   1484   1484   1488   Ljava/lang/IllegalArgumentException;
        //  1488   1503   1503   1507   Ljava/lang/IllegalArgumentException;
        //  1507   1522   1522   1526   Ljava/lang/IllegalArgumentException;
        //  1526   1541   1541   1545   Ljava/lang/IllegalArgumentException;
        //  1545   1563   1566   1570   Ljava/lang/IllegalArgumentException;
        //  1552   1581   1584   1588   Ljava/lang/IllegalArgumentException;
        //  1570   1594   1594   1598   Ljava/lang/IllegalArgumentException;
        //  1602   1620   1623   1627   Ljava/lang/IllegalArgumentException;
        //  1609   1638   1641   1645   Ljava/lang/IllegalArgumentException;
        //  1627   1651   1651   1655   Ljava/lang/IllegalArgumentException;
        //  1659   1676   1679   1683   Ljava/lang/IllegalArgumentException;
        //  1666   1687   1687   1691   Ljava/lang/IllegalArgumentException;
        //  1696   1713   1716   1720   Ljava/lang/IllegalArgumentException;
        //  1703   1724   1724   1728   Ljava/lang/IllegalArgumentException;
        //  1733   1750   1753   1757   Ljava/lang/IllegalArgumentException;
        //  1740   1761   1761   1765   Ljava/lang/IllegalArgumentException;
        //  1770   1787   1790   1794   Ljava/lang/IllegalArgumentException;
        //  1777   1798   1798   1802   Ljava/lang/IllegalArgumentException;
        //  1807   1824   1827   1831   Ljava/lang/IllegalArgumentException;
        //  1814   1835   1835   1839   Ljava/lang/IllegalArgumentException;
        //  1844   1861   1864   1868   Ljava/lang/IllegalArgumentException;
        //  1851   1872   1872   1876   Ljava/lang/IllegalArgumentException;
        //  1881   1895   1898   1902   Ljava/lang/IllegalArgumentException;
        //  1888   1904   1904   1908   Ljava/lang/IllegalArgumentException;
        // 
        // The error that occurred was:
        // 
        // java.lang.IllegalStateException: Expression is linked from several locations: Label_0018:
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
    public Object evalUnary(final OCElementType p0, final Object p1) {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     0: aload_2        
        //     1: ifnonnull       10
        //     4: aconst_null    
        //     5: areturn        
        //     6: invokestatic    com/jetbrains/cidr/lang/util/OCExpressionEvaluator$ValueEvaluator.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //     9: athrow         
        //    10: aload_2        
        //    11: instanceof      Ljava/lang/Integer;
        //    14: ifeq            28
        //    17: aload_2        
        //    18: checkcast       Ljava/lang/Integer;
        //    21: invokevirtual   java/lang/Integer.longValue:()J
        //    24: invokestatic    java/lang/Long.valueOf:(J)Ljava/lang/Long;
        //    27: astore_2       
        //    28: aload_1        
        //    29: getstatic       com/jetbrains/cidr/lang/parser/OCTokenTypes.EXCL:Lcom/jetbrains/cidr/lang/parser/OCPunctuatorElementType;
        //    32: if_acmpne       90
        //    35: aload_2        
        //    36: instanceof      Ljava/lang/Number;
        //    39: ifne            63
        //    42: goto            49
        //    45: invokestatic    com/jetbrains/cidr/lang/util/OCExpressionEvaluator$ValueEvaluator.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //    48: athrow         
        //    49: aload_2        
        //    50: instanceof      Ljava/lang/Boolean;
        //    53: ifeq            90
        //    56: goto            63
        //    59: invokestatic    com/jetbrains/cidr/lang/util/OCExpressionEvaluator$ValueEvaluator.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //    62: athrow         
        //    63: aload_2        
        //    64: invokestatic    com/jetbrains/cidr/lang/util/OCExpressionEvaluator.singAsInC:(Ljava/lang/Object;)I
        //    67: ifne            85
        //    70: goto            77
        //    73: invokestatic    com/jetbrains/cidr/lang/util/OCExpressionEvaluator$ValueEvaluator.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //    76: athrow         
        //    77: iconst_1       
        //    78: goto            86
        //    81: invokestatic    com/jetbrains/cidr/lang/util/OCExpressionEvaluator$ValueEvaluator.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //    84: athrow         
        //    85: iconst_0       
        //    86: invokestatic    com/jetbrains/cidr/lang/util/OCExpressionEvaluator.access$300:(Z)Ljava/lang/Long;
        //    89: areturn        
        //    90: aload_1        
        //    91: getstatic       com/jetbrains/cidr/lang/parser/OCTokenTypes.PLUS:Lcom/jetbrains/cidr/lang/parser/OCPunctuatorElementType;
        //    94: if_acmpne       117
        //    97: aload_2        
        //    98: instanceof      Ljava/lang/Number;
        //   101: ifeq            117
        //   104: goto            111
        //   107: invokestatic    com/jetbrains/cidr/lang/util/OCExpressionEvaluator$ValueEvaluator.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   110: athrow         
        //   111: aload_2        
        //   112: areturn        
        //   113: invokestatic    com/jetbrains/cidr/lang/util/OCExpressionEvaluator$ValueEvaluator.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   116: athrow         
        //   117: aload_1        
        //   118: getstatic       com/jetbrains/cidr/lang/parser/OCTokenTypes.MINUS:Lcom/jetbrains/cidr/lang/parser/OCPunctuatorElementType;
        //   121: if_acmpne       173
        //   124: aload_2        
        //   125: instanceof      Ljava/lang/Long;
        //   128: ifeq            154
        //   131: goto            138
        //   134: invokestatic    com/jetbrains/cidr/lang/util/OCExpressionEvaluator$ValueEvaluator.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   137: athrow         
        //   138: aload_2        
        //   139: checkcast       Ljava/lang/Long;
        //   142: invokevirtual   java/lang/Long.longValue:()J
        //   145: lneg           
        //   146: invokestatic    java/lang/Long.valueOf:(J)Ljava/lang/Long;
        //   149: areturn        
        //   150: invokestatic    com/jetbrains/cidr/lang/util/OCExpressionEvaluator$ValueEvaluator.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   153: athrow         
        //   154: aload_2        
        //   155: instanceof      Lcom/jetbrains/cidr/lang/util/OCNumber;
        //   158: ifeq            173
        //   161: aload_2        
        //   162: checkcast       Lcom/jetbrains/cidr/lang/util/OCNumber;
        //   165: invokevirtual   com/jetbrains/cidr/lang/util/OCNumber.negate:()Lcom/jetbrains/cidr/lang/util/OCNumber;
        //   168: areturn        
        //   169: invokestatic    com/jetbrains/cidr/lang/util/OCExpressionEvaluator$ValueEvaluator.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   172: athrow         
        //   173: aload_1        
        //   174: getstatic       com/jetbrains/cidr/lang/parser/OCTokenTypes.TILDE:Lcom/jetbrains/cidr/lang/parser/OCPunctuatorElementType;
        //   177: if_acmpne       232
        //   180: aload_2        
        //   181: instanceof      Ljava/lang/Long;
        //   184: ifeq            213
        //   187: goto            194
        //   190: invokestatic    com/jetbrains/cidr/lang/util/OCExpressionEvaluator$ValueEvaluator.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   193: athrow         
        //   194: aload_2        
        //   195: checkcast       Ljava/lang/Long;
        //   198: invokevirtual   java/lang/Long.longValue:()J
        //   201: ldc2_w          -1
        //   204: lxor           
        //   205: invokestatic    java/lang/Long.valueOf:(J)Ljava/lang/Long;
        //   208: areturn        
        //   209: invokestatic    com/jetbrains/cidr/lang/util/OCExpressionEvaluator$ValueEvaluator.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   212: athrow         
        //   213: aload_2        
        //   214: instanceof      Lcom/jetbrains/cidr/lang/util/OCNumber;
        //   217: ifeq            232
        //   220: aload_2        
        //   221: checkcast       Lcom/jetbrains/cidr/lang/util/OCNumber;
        //   224: invokevirtual   com/jetbrains/cidr/lang/util/OCNumber.inverse:()Lcom/jetbrains/cidr/lang/util/OCNumber;
        //   227: areturn        
        //   228: invokestatic    com/jetbrains/cidr/lang/util/OCExpressionEvaluator$ValueEvaluator.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   231: athrow         
        //   232: aconst_null    
        //   233: areturn        
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                                
        //  -----  -----  -----  -----  ------------------------------------
        //  0      6      6      10     Ljava/lang/IllegalArgumentException;
        //  28     42     45     49     Ljava/lang/IllegalArgumentException;
        //  35     56     59     63     Ljava/lang/IllegalArgumentException;
        //  49     70     73     77     Ljava/lang/IllegalArgumentException;
        //  63     81     81     85     Ljava/lang/IllegalArgumentException;
        //  90     104    107    111    Ljava/lang/IllegalArgumentException;
        //  97     113    113    117    Ljava/lang/IllegalArgumentException;
        //  117    131    134    138    Ljava/lang/IllegalArgumentException;
        //  124    150    150    154    Ljava/lang/IllegalArgumentException;
        //  154    169    169    173    Ljava/lang/IllegalArgumentException;
        //  173    187    190    194    Ljava/lang/IllegalArgumentException;
        //  180    209    209    213    Ljava/lang/IllegalArgumentException;
        //  213    228    228    232    Ljava/lang/IllegalArgumentException;
        // 
        // The error that occurred was:
        // 
        // java.lang.IllegalStateException: Expression is linked from several locations: Label_0049:
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
    public Object evalCast(@NotNull final OCType p0, @Nullable final Object p1) {
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
        //    18: ldc             "type"
        //    20: aastore        
        //    21: dup            
        //    22: ldc             1
        //    24: ldc             "com/jetbrains/cidr/lang/util/OCExpressionEvaluator$ValueEvaluator"
        //    26: aastore        
        //    27: dup            
        //    28: ldc             2
        //    30: ldc             "evalCast"
        //    32: aastore        
        //    33: invokestatic    java/lang/String.format:(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
        //    36: invokespecial   java/lang/IllegalArgumentException.<init>:(Ljava/lang/String;)V
        //    39: athrow         
        //    40: invokestatic    com/jetbrains/cidr/lang/util/OCExpressionEvaluator$ValueEvaluator.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //    43: athrow         
        //    44: aload_1        
        //    45: aload_0        
        //    46: invokevirtual   com/jetbrains/cidr/lang/util/OCExpressionEvaluator$ValueEvaluator.getContext:()Lcom/jetbrains/cidr/lang/symbols/OCResolveContext;
        //    49: invokevirtual   com/jetbrains/cidr/lang/types/OCType.resolve:(Lcom/jetbrains/cidr/lang/symbols/OCResolveContext;)Lcom/jetbrains/cidr/lang/types/OCType;
        //    52: aload_0        
        //    53: invokevirtual   com/jetbrains/cidr/lang/util/OCExpressionEvaluator$ValueEvaluator.getContext:()Lcom/jetbrains/cidr/lang/symbols/OCResolveContext;
        //    56: invokevirtual   com/jetbrains/cidr/lang/symbols/OCResolveContext.getElement:()Lcom/intellij/psi/PsiElement;
        //    59: invokestatic    com/jetbrains/cidr/lang/types/OCIntType.isBool:(Lcom/jetbrains/cidr/lang/types/OCType;Lcom/intellij/psi/PsiElement;)Z
        //    62: ifeq            110
        //    65: aload_2        
        //    66: instanceof      Ljava/lang/Number;
        //    69: ifeq            110
        //    72: goto            79
        //    75: invokestatic    com/jetbrains/cidr/lang/util/OCExpressionEvaluator$ValueEvaluator.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //    78: athrow         
        //    79: aload_0        
        //    80: aload_2        
        //    81: invokestatic    com/jetbrains/cidr/lang/util/OCExpressionEvaluator.singAsInC:(Ljava/lang/Object;)I
        //    84: ifeq            102
        //    87: goto            94
        //    90: invokestatic    com/jetbrains/cidr/lang/util/OCExpressionEvaluator$ValueEvaluator.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //    93: athrow         
        //    94: iconst_1       
        //    95: goto            103
        //    98: invokestatic    com/jetbrains/cidr/lang/util/OCExpressionEvaluator$ValueEvaluator.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   101: athrow         
        //   102: iconst_0       
        //   103: invokestatic    java/lang/Boolean.valueOf:(Z)Ljava/lang/Boolean;
        //   106: invokevirtual   com/jetbrains/cidr/lang/util/OCExpressionEvaluator$ValueEvaluator.evalBool:(Ljava/lang/Boolean;)Ljava/lang/Object;
        //   109: areturn        
        //   110: aload_2        
        //   111: areturn        
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                                
        //  -----  -----  -----  -----  ------------------------------------
        //  0      40     40     44     Ljava/lang/IllegalArgumentException;
        //  44     72     75     79     Ljava/lang/IllegalArgumentException;
        //  65     87     90     94     Ljava/lang/IllegalArgumentException;
        //  79     98     98     102    Ljava/lang/IllegalArgumentException;
        // 
        // The error that occurred was:
        // 
        // java.lang.IllegalStateException: Expression is linked from several locations: Label_0079:
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
    public Object evalCall(@NotNull final OCCallExpressionSymbol p0) {
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
        //    24: ldc             "com/jetbrains/cidr/lang/util/OCExpressionEvaluator$ValueEvaluator"
        //    26: aastore        
        //    27: dup            
        //    28: ldc             2
        //    30: ldc             "evalCall"
        //    32: aastore        
        //    33: invokestatic    java/lang/String.format:(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
        //    36: invokespecial   java/lang/IllegalArgumentException.<init>:(Ljava/lang/String;)V
        //    39: athrow         
        //    40: invokestatic    com/jetbrains/cidr/lang/util/OCExpressionEvaluator$ValueEvaluator.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //    43: athrow         
        //    44: aload_1        
        //    45: invokevirtual   com/jetbrains/cidr/lang/symbols/expression/OCCallExpressionSymbol.getCalleeSymbol:()Lcom/jetbrains/cidr/lang/symbols/expression/OCExpressionSymbol;
        //    48: astore_2       
        //    49: aload_1        
        //    50: invokevirtual   com/jetbrains/cidr/lang/symbols/expression/OCCallExpressionSymbol.getArguments:()Ljava/util/List;
        //    53: astore_3       
        //    54: aload_2        
        //    55: instanceof      Lcom/jetbrains/cidr/lang/symbols/expression/OCReferenceExpressionSymbol;
        //    58: ifeq            263
        //    61: aload_2        
        //    62: checkcast       Lcom/jetbrains/cidr/lang/symbols/expression/OCReferenceExpressionSymbol;
        //    65: aload_0        
        //    66: getfield        com/jetbrains/cidr/lang/util/OCExpressionEvaluator$ValueEvaluator.myContext:Lcom/jetbrains/cidr/lang/symbols/OCResolveContext;
        //    69: invokevirtual   com/jetbrains/cidr/lang/symbols/expression/OCReferenceExpressionSymbol.resolveToSymbol:(Lcom/jetbrains/cidr/lang/symbols/OCResolveContext;)Lcom/jetbrains/cidr/lang/symbols/OCSymbol;
        //    72: astore          4
        //    74: aload_3        
        //    75: invokeinterface java/util/List.size:()I
        //    80: iconst_1       
        //    81: if_icmpne       208
        //    84: aload           4
        //    86: ifnull          166
        //    89: goto            96
        //    92: invokestatic    com/jetbrains/cidr/lang/util/OCExpressionEvaluator$ValueEvaluator.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //    95: athrow         
        //    96: aload           4
        //    98: invokeinterface com/jetbrains/cidr/lang/symbols/OCSymbol.getKind:()Lcom/jetbrains/cidr/lang/symbols/OCSymbolKind;
        //   103: getstatic       com/jetbrains/cidr/lang/symbols/OCSymbolKind.TYPEDEF:Lcom/jetbrains/cidr/lang/symbols/OCSymbolKind;
        //   106: if_acmpeq       136
        //   109: goto            116
        //   112: invokestatic    com/jetbrains/cidr/lang/util/OCExpressionEvaluator$ValueEvaluator.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   115: athrow         
        //   116: aload           4
        //   118: invokeinterface com/jetbrains/cidr/lang/symbols/OCSymbol.getKind:()Lcom/jetbrains/cidr/lang/symbols/OCSymbolKind;
        //   123: getstatic       com/jetbrains/cidr/lang/symbols/OCSymbolKind.USING_SYMBOL_ALIAS:Lcom/jetbrains/cidr/lang/symbols/OCSymbolKind;
        //   126: if_acmpne       166
        //   129: goto            136
        //   132: invokestatic    com/jetbrains/cidr/lang/util/OCExpressionEvaluator$ValueEvaluator.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   135: athrow         
        //   136: aload_0        
        //   137: aload           4
        //   139: invokeinterface com/jetbrains/cidr/lang/symbols/OCSymbol.getType:()Lcom/jetbrains/cidr/lang/types/OCType;
        //   144: aload_3        
        //   145: iconst_0       
        //   146: invokeinterface java/util/List.get:(I)Ljava/lang/Object;
        //   151: checkcast       Lcom/jetbrains/cidr/lang/symbols/expression/OCExpressionSymbol;
        //   154: aload_0        
        //   155: invokevirtual   com/jetbrains/cidr/lang/symbols/expression/OCExpressionSymbol.evaluate:(Lcom/jetbrains/cidr/lang/util/OCExpressionEvaluator$CachingEvaluator;)Ljava/lang/Object;
        //   158: invokevirtual   com/jetbrains/cidr/lang/util/OCExpressionEvaluator$ValueEvaluator.evalCast:(Lcom/jetbrains/cidr/lang/types/OCType;Ljava/lang/Object;)Ljava/lang/Object;
        //   161: areturn        
        //   162: invokestatic    com/jetbrains/cidr/lang/util/OCExpressionEvaluator$ValueEvaluator.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   165: athrow         
        //   166: aload_3        
        //   167: iconst_0       
        //   168: invokeinterface java/util/List.get:(I)Ljava/lang/Object;
        //   173: instanceof      Lcom/jetbrains/cidr/lang/symbols/expression/OCReferenceExpressionSymbol;
        //   176: ifeq            208
        //   179: aload_0        
        //   180: getfield        com/jetbrains/cidr/lang/util/OCExpressionEvaluator$ValueEvaluator.myContext:Lcom/jetbrains/cidr/lang/symbols/OCResolveContext;
        //   183: aload_2        
        //   184: invokevirtual   com/jetbrains/cidr/lang/symbols/expression/OCExpressionSymbol.getName:()Ljava/lang/String;
        //   187: aload_3        
        //   188: iconst_0       
        //   189: invokeinterface java/util/List.get:(I)Ljava/lang/Object;
        //   194: checkcast       Lcom/jetbrains/cidr/lang/symbols/expression/OCReferenceExpressionSymbol;
        //   197: invokevirtual   com/jetbrains/cidr/lang/symbols/expression/OCReferenceExpressionSymbol.getReference:()Lcom/jetbrains/cidr/lang/symbols/OCSymbolReference;
        //   200: invokestatic    com/jetbrains/cidr/lang/util/OCExpressionEvaluator.evaluateGNUBuiltInTrait:(Lcom/jetbrains/cidr/lang/symbols/OCResolveContext;Ljava/lang/String;Lcom/jetbrains/cidr/lang/symbols/OCSymbolReference;)Ljava/lang/Boolean;
        //   203: areturn        
        //   204: invokestatic    com/jetbrains/cidr/lang/util/OCExpressionEvaluator$ValueEvaluator.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   207: athrow         
        //   208: aload_3        
        //   209: invokeinterface java/util/List.stream:()Ljava/util/stream/Stream;
        //   214: aload_0        
        //   215: invokedynamic   apply:(Lcom/jetbrains/cidr/lang/util/OCExpressionEvaluator$ValueEvaluator;)Ljava/util/function/Function;
        //   220: invokeinterface java/util/stream/Stream.map:(Ljava/util/function/Function;)Ljava/util/stream/Stream;
        //   225: invokestatic    java/util/stream/Collectors.toList:()Ljava/util/stream/Collector;
        //   228: invokeinterface java/util/stream/Stream.collect:(Ljava/util/stream/Collector;)Ljava/lang/Object;
        //   233: checkcast       Ljava/util/List;
        //   236: astore          5
        //   238: aload           4
        //   240: aload_0        
        //   241: getfield        com/jetbrains/cidr/lang/util/OCExpressionEvaluator$ValueEvaluator.myContext:Lcom/jetbrains/cidr/lang/symbols/OCResolveContext;
        //   244: aload           5
        //   246: invokestatic    com/jetbrains/cidr/lang/util/OCExpressionEvaluator.access$200:(Lcom/jetbrains/cidr/lang/symbols/OCSymbol;Lcom/jetbrains/cidr/lang/symbols/OCResolveContext;Ljava/util/List;)Ljava/lang/Object;
        //   249: astore          6
        //   251: aload           6
        //   253: ifnull          263
        //   256: aload           6
        //   258: areturn        
        //   259: invokestatic    com/jetbrains/cidr/lang/util/OCExpressionEvaluator$ValueEvaluator.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   262: athrow         
        //   263: aconst_null    
        //   264: areturn        
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                                
        //  -----  -----  -----  -----  ------------------------------------
        //  0      40     40     44     Ljava/lang/IllegalArgumentException;
        //  74     89     92     96     Ljava/lang/IllegalArgumentException;
        //  84     109    112    116    Ljava/lang/IllegalArgumentException;
        //  96     129    132    136    Ljava/lang/IllegalArgumentException;
        //  116    162    162    166    Ljava/lang/IllegalArgumentException;
        //  166    204    204    208    Ljava/lang/IllegalArgumentException;
        //  251    259    259    263    Ljava/lang/IllegalArgumentException;
        // 
        // The error that occurred was:
        // 
        // java.lang.IllegalStateException: Expression is linked from several locations: Label_0096:
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
    public Object evalReference(@NotNull final OCReferenceExpressionSymbol ocReferenceExpressionSymbol) {
        try {
            if (ocReferenceExpressionSymbol == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "symbol", "com/jetbrains/cidr/lang/util/OCExpressionEvaluator$ValueEvaluator", "evalReference"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw b(ex);
        }
        try {
            if (OCResolveUtil.isDependentCode(ocReferenceExpressionSymbol.getReference().getQualifiedName(), this.myContext)) {
                return null;
            }
        }
        catch (IllegalArgumentException ex2) {
            throw b(ex2);
        }
        OCSymbol resolveToSymbol = ocReferenceExpressionSymbol.resolveToSymbol(this.myContext);
        Label_0117: {
            try {
                if (resolveToSymbol == null || resolveToSymbol.getKind() != OCSymbolKind.PARAMETER) {
                    break Label_0117;
                }
            }
            catch (IllegalArgumentException ex3) {
                throw b(ex3);
            }
            final OCTypeParameterValueSymbol autoParamValueMapping = this.myContext.getAutoParamValueMapping((OCDeclaratorSymbol)resolveToSymbol);
            if (autoParamValueMapping != null) {
                resolveToSymbol = autoParamValueMapping;
            }
            try {
                if (resolveToSymbol != null) {
                    return OCExpressionEvaluator.evaluate(resolveToSymbol, (CachingEvaluator<Object>)this);
                }
            }
            catch (IllegalArgumentException ex4) {
                throw b(ex4);
            }
        }
        return null;
    }
    
    @Nullable
    @Override
    public Object evalSizeof(@NotNull final OCSizeofExpressionSymbol ocSizeofExpressionSymbol) {
        try {
            if (ocSizeofExpressionSymbol == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "symbol", "com/jetbrains/cidr/lang/util/OCExpressionEvaluator$ValueEvaluator", "evalSizeof"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw b(ex);
        }
        final OCExpressionSymbol expressionOperand = ocSizeofExpressionSymbol.getExpressionOperand();
        OCType ocType = null;
        Label_0079: {
            try {
                if (expressionOperand != null) {
                    ocType = expressionOperand.getResolvedType(this.myContext);
                    break Label_0079;
                }
            }
            catch (IllegalArgumentException ex2) {
                throw b(ex2);
            }
            ocType = ocSizeofExpressionSymbol.getTypeOperand().resolve(this.myContext);
        }
        final OCType ocType2 = ocType;
        int sizeInBytes = 0;
        Label_0221: {
            Label_0197: {
                Label_0101: {
                    try {
                        if (!ocSizeofExpressionSymbol.isVariadic()) {
                            break Label_0197;
                        }
                        final OCType ocType3 = ocType2;
                        final boolean b = ocType3 instanceof OCExpansionPackType;
                        if (b) {
                            break Label_0101;
                        }
                        break Label_0101;
                    }
                    catch (IllegalArgumentException ex3) {
                        throw b(ex3);
                    }
                    try {
                        final OCType ocType3 = ocType2;
                        final boolean b = ocType3 instanceof OCExpansionPackType;
                        if (b) {
                            return ((OCExpansionPackType)ocType2).getExpansions().size();
                        }
                    }
                    catch (IllegalArgumentException ex4) {
                        throw b(ex4);
                    }
                }
                if (expressionOperand instanceof OCReferenceExpressionSymbol) {
                    final OCSymbol resolveToSymbol = ((OCReferenceExpressionSymbol)expressionOperand).resolveToSymbol(this.myContext);
                    if (resolveToSymbol instanceof OCTypeParameterValueSymbol) {
                        final OCTypeArgument substitution = this.myContext.getSubstitution().getSubstitutionFor((OCTypeParameterSymbol)resolveToSymbol);
                        try {
                            if (substitution instanceof OCExpansionPackType) {
                                return ((OCExpansionPackType)substitution).getExpansions().size();
                            }
                        }
                        catch (IllegalArgumentException ex5) {
                            throw b(ex5);
                        }
                    }
                }
                return null;
                try {
                    if (ocType2 != null) {
                        sizeInBytes = ocType2.getSizeInBytes(this.myContext.getFile(), null);
                        break Label_0221;
                    }
                }
                catch (IllegalArgumentException ex6) {
                    throw b(ex6);
                }
            }
            sizeInBytes = -1;
        }
        final int n = sizeInBytes;
        try {
            if (n != -1) {
                return this.evalInteger(n);
            }
        }
        catch (IllegalArgumentException ex7) {
            throw b(ex7);
        }
        return null;
    }
    
    private static IllegalArgumentException b(final IllegalArgumentException ex) {
        return ex;
    }
}
