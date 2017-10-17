// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.execution.debugger;

import org.jetbrains.annotations.NotNull;
import com.intellij.openapi.application.Result;
import java.util.LinkedHashSet;
import com.intellij.openapi.util.Ref;
import com.intellij.xdebugger.XSourcePosition;
import com.intellij.xdebugger.XExpression;
import com.intellij.openapi.application.WriteAction;

class OCEvaluatorHelper$1 extends WriteAction {
    protected void run(@NotNull final Result p0) throws Throwable {
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
        //    18: ldc             "result"
        //    20: aastore        
        //    21: dup            
        //    22: ldc             1
        //    24: ldc             "com/jetbrains/cidr/execution/debugger/OCEvaluatorHelper$1"
        //    26: aastore        
        //    27: dup            
        //    28: ldc             2
        //    30: ldc             "run"
        //    32: aastore        
        //    33: invokestatic    java/lang/String.format:(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
        //    36: invokespecial   java/lang/IllegalArgumentException.<init>:(Ljava/lang/String;)V
        //    39: athrow         
        //    40: invokestatic    com/jetbrains/cidr/execution/debugger/OCEvaluatorHelper$1.b:(Lcom/jetbrains/cidr/execution/debugger/CidrEvaluatorHelper$ConversionException;)Lcom/jetbrains/cidr/execution/debugger/CidrEvaluatorHelper$ConversionException;
        //    43: athrow         
        //    44: aload_0        
        //    45: getfield        com/jetbrains/cidr/execution/debugger/OCEvaluatorHelper$1.val$process:Lcom/jetbrains/cidr/execution/debugger/CidrDebugProcess;
        //    48: aload_0        
        //    49: getfield        com/jetbrains/cidr/execution/debugger/OCEvaluatorHelper$1.val$expression:Lcom/intellij/xdebugger/XExpression;
        //    52: invokeinterface com/intellij/xdebugger/XExpression.getExpression:()Ljava/lang/String;
        //    57: aload_0        
        //    58: getfield        com/jetbrains/cidr/execution/debugger/OCEvaluatorHelper$1.val$sourcePosition:Lcom/intellij/xdebugger/XSourcePosition;
        //    61: invokestatic    com/jetbrains/cidr/execution/debugger/OCEvaluatorHelper.access$200:(Lcom/jetbrains/cidr/execution/debugger/CidrDebugProcess;Ljava/lang/String;Lcom/intellij/xdebugger/XSourcePosition;)Lcom/intellij/openapi/util/Pair;
        //    64: astore_2       
        //    65: aload_0        
        //    66: getfield        com/jetbrains/cidr/execution/debugger/OCEvaluatorHelper$1.val$convertedExpression:Lcom/intellij/openapi/util/Ref;
        //    69: aload_2        
        //    70: getfield        com/intellij/openapi/util/Pair.second:Ljava/lang/Object;
        //    73: invokevirtual   com/intellij/openapi/util/Ref.set:(Ljava/lang/Object;)V
        //    76: aload_0        
        //    77: getfield        com/jetbrains/cidr/execution/debugger/OCEvaluatorHelper$1.val$resolvedType:Lcom/intellij/openapi/util/Ref;
        //    80: aload_2        
        //    81: getfield        com/intellij/openapi/util/Pair.first:Ljava/lang/Object;
        //    84: invokevirtual   com/intellij/openapi/util/Ref.set:(Ljava/lang/Object;)V
        //    87: goto            107
        //    90: astore_2       
        //    91: aload_0        
        //    92: getfield        com/jetbrains/cidr/execution/debugger/OCEvaluatorHelper$1.val$lastException:Lcom/intellij/openapi/util/Ref;
        //    95: new             Lcom/jetbrains/cidr/execution/debugger/backend/DebuggerCommandException;
        //    98: dup            
        //    99: aload_2        
        //   100: invokespecial   com/jetbrains/cidr/execution/debugger/backend/DebuggerCommandException.<init>:(Ljava/lang/Throwable;)V
        //   103: invokevirtual   com/intellij/openapi/util/Ref.set:(Ljava/lang/Object;)V
        //   106: return         
        //   107: aload_0        
        //   108: getfield        com/jetbrains/cidr/execution/debugger/OCEvaluatorHelper$1.val$expressions:Ljava/util/LinkedHashSet;
        //   111: aload_0        
        //   112: getfield        com/jetbrains/cidr/execution/debugger/OCEvaluatorHelper$1.val$convertedExpression:Lcom/intellij/openapi/util/Ref;
        //   115: invokevirtual   com/intellij/openapi/util/Ref.get:()Ljava/lang/Object;
        //   118: invokevirtual   java/util/LinkedHashSet.add:(Ljava/lang/Object;)Z
        //   121: pop            
        //   122: aload_0        
        //   123: getfield        com/jetbrains/cidr/execution/debugger/OCEvaluatorHelper$1.val$resolvedType:Lcom/intellij/openapi/util/Ref;
        //   126: invokevirtual   com/intellij/openapi/util/Ref.get:()Ljava/lang/Object;
        //   129: checkcast       Lcom/jetbrains/cidr/lang/types/OCType;
        //   132: astore_2       
        //   133: aload_0        
        //   134: getfield        com/jetbrains/cidr/execution/debugger/OCEvaluatorHelper$1.val$process:Lcom/jetbrains/cidr/execution/debugger/CidrDebugProcess;
        //   137: aload_0        
        //   138: getfield        com/jetbrains/cidr/execution/debugger/OCEvaluatorHelper$1.val$sourcePosition:Lcom/intellij/xdebugger/XSourcePosition;
        //   141: invokevirtual   com/jetbrains/cidr/execution/debugger/CidrDebugProcess.getDebuggerContext:(Lcom/intellij/xdebugger/XSourcePosition;)Lcom/intellij/psi/PsiElement;
        //   144: astore_3       
        //   145: aload_0        
        //   146: getfield        com/jetbrains/cidr/execution/debugger/OCEvaluatorHelper$1.val$resolvedType:Lcom/intellij/openapi/util/Ref;
        //   149: invokevirtual   com/intellij/openapi/util/Ref.isNull:()Z
        //   152: ifne            533
        //   155: aload_2        
        //   156: invokevirtual   com/jetbrains/cidr/lang/types/OCType.isUnknown:()Z
        //   159: ifne            533
        //   162: goto            169
        //   165: invokestatic    com/jetbrains/cidr/execution/debugger/OCEvaluatorHelper$1.b:(Lcom/jetbrains/cidr/execution/debugger/CidrEvaluatorHelper$ConversionException;)Lcom/jetbrains/cidr/execution/debugger/CidrEvaluatorHelper$ConversionException;
        //   168: athrow         
        //   169: aload_3        
        //   170: ifnull          193
        //   173: goto            180
        //   176: invokestatic    com/jetbrains/cidr/execution/debugger/OCEvaluatorHelper$1.b:(Lcom/jetbrains/cidr/execution/debugger/CidrEvaluatorHelper$ConversionException;)Lcom/jetbrains/cidr/execution/debugger/CidrEvaluatorHelper$ConversionException;
        //   179: athrow         
        //   180: aload_3        
        //   181: invokeinterface com/intellij/psi/PsiElement.getContainingFile:()Lcom/intellij/psi/PsiFile;
        //   186: goto            194
        //   189: invokestatic    com/jetbrains/cidr/execution/debugger/OCEvaluatorHelper$1.b:(Lcom/jetbrains/cidr/execution/debugger/CidrEvaluatorHelper$ConversionException;)Lcom/jetbrains/cidr/execution/debugger/CidrEvaluatorHelper$ConversionException;
        //   192: athrow         
        //   193: aconst_null    
        //   194: astore          4
        //   196: aload           4
        //   198: ifnull          433
        //   201: aload_0        
        //   202: getfield        com/jetbrains/cidr/execution/debugger/OCEvaluatorHelper$1.val$process:Lcom/jetbrains/cidr/execution/debugger/CidrDebugProcess;
        //   205: invokevirtual   com/jetbrains/cidr/execution/debugger/CidrDebugProcess.driverSupportsArrayEvaluation:()Z
        //   208: ifeq            244
        //   211: goto            218
        //   214: invokestatic    com/jetbrains/cidr/execution/debugger/OCEvaluatorHelper$1.b:(Lcom/jetbrains/cidr/execution/debugger/CidrEvaluatorHelper$ConversionException;)Lcom/jetbrains/cidr/execution/debugger/CidrEvaluatorHelper$ConversionException;
        //   217: athrow         
        //   218: aload_2        
        //   219: instanceof      Lcom/jetbrains/cidr/lang/types/OCArrayType;
        //   222: ifeq            244
        //   225: goto            232
        //   228: invokestatic    com/jetbrains/cidr/execution/debugger/OCEvaluatorHelper$1.b:(Lcom/jetbrains/cidr/execution/debugger/CidrEvaluatorHelper$ConversionException;)Lcom/jetbrains/cidr/execution/debugger/CidrEvaluatorHelper$ConversionException;
        //   231: athrow         
        //   232: aload_2        
        //   233: aload           4
        //   235: invokeinterface com/intellij/psi/PsiFile.getProject:()Lcom/intellij/openapi/project/Project;
        //   240: invokestatic    com/jetbrains/cidr/lang/types/OCTypeUtils.decayType:(Lcom/jetbrains/cidr/lang/types/OCType;Lcom/intellij/openapi/project/Project;)Lcom/jetbrains/cidr/lang/types/OCType;
        //   243: astore_2       
        //   244: aload_2        
        //   245: instanceof      Lcom/jetbrains/cidr/lang/types/OCCppReferenceType;
        //   248: ifeq            276
        //   251: aload_2        
        //   252: checkcast       Lcom/jetbrains/cidr/lang/types/OCCppReferenceType;
        //   255: invokevirtual   com/jetbrains/cidr/lang/types/OCCppReferenceType.isRvalueRef:()Z
        //   258: ifeq            276
        //   261: goto            268
        //   264: invokestatic    com/jetbrains/cidr/execution/debugger/OCEvaluatorHelper$1.b:(Lcom/jetbrains/cidr/execution/debugger/CidrEvaluatorHelper$ConversionException;)Lcom/jetbrains/cidr/execution/debugger/CidrEvaluatorHelper$ConversionException;
        //   267: athrow         
        //   268: aload_2        
        //   269: checkcast       Lcom/jetbrains/cidr/lang/types/OCCppReferenceType;
        //   272: invokevirtual   com/jetbrains/cidr/lang/types/OCCppReferenceType.getRefType:()Lcom/jetbrains/cidr/lang/types/OCType;
        //   275: astore_2       
        //   276: aload_2        
        //   277: instanceof      Lcom/jetbrains/cidr/lang/types/OCArrayType;
        //   280: ifne            375
        //   283: aload_2        
        //   284: astore          5
        //   286: aload           5
        //   288: instanceof      Lcom/jetbrains/cidr/lang/types/OCPointerType;
        //   291: ifeq            307
        //   294: aload           5
        //   296: checkcast       Lcom/jetbrains/cidr/lang/types/OCPointerType;
        //   299: invokevirtual   com/jetbrains/cidr/lang/types/OCPointerType.getRefType:()Lcom/jetbrains/cidr/lang/types/OCType;
        //   302: astore          5
        //   304: goto            286
        //   307: aload           5
        //   309: instanceof      Lcom/jetbrains/cidr/lang/types/OCStructType;
        //   312: ifeq            375
        //   315: aload_0        
        //   316: getfield        com/jetbrains/cidr/execution/debugger/OCEvaluatorHelper$1.val$expressions:Ljava/util/LinkedHashSet;
        //   319: new             Ljava/lang/StringBuilder;
        //   322: dup            
        //   323: invokespecial   java/lang/StringBuilder.<init>:()V
        //   326: ldc             "(("
        //   328: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   331: aload_2        
        //   332: aload           4
        //   334: invokevirtual   com/jetbrains/cidr/lang/types/OCType.getCanonicalName:(Lcom/intellij/psi/PsiElement;)Ljava/lang/String;
        //   337: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   340: ldc             ")("
        //   342: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   345: aload_0        
        //   346: getfield        com/jetbrains/cidr/execution/debugger/OCEvaluatorHelper$1.val$convertedExpression:Lcom/intellij/openapi/util/Ref;
        //   349: invokevirtual   com/intellij/openapi/util/Ref.get:()Ljava/lang/Object;
        //   352: checkcast       Ljava/lang/String;
        //   355: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   358: ldc             "))"
        //   360: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   363: invokevirtual   java/lang/StringBuilder.toString:()Ljava/lang/String;
        //   366: invokevirtual   java/util/LinkedHashSet.add:(Ljava/lang/Object;)Z
        //   369: pop            
        //   370: return         
        //   371: invokestatic    com/jetbrains/cidr/execution/debugger/OCEvaluatorHelper$1.b:(Lcom/jetbrains/cidr/execution/debugger/CidrEvaluatorHelper$ConversionException;)Lcom/jetbrains/cidr/execution/debugger/CidrEvaluatorHelper$ConversionException;
        //   374: athrow         
        //   375: aload_0        
        //   376: getfield        com/jetbrains/cidr/execution/debugger/OCEvaluatorHelper$1.val$expressions:Ljava/util/LinkedHashSet;
        //   379: new             Ljava/lang/StringBuilder;
        //   382: dup            
        //   383: invokespecial   java/lang/StringBuilder.<init>:()V
        //   386: ldc             "(("
        //   388: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   391: aload_2        
        //   392: aload           4
        //   394: invokevirtual   com/jetbrains/cidr/lang/types/OCType.getCanonicalName:(Lcom/intellij/psi/PsiElement;)Ljava/lang/String;
        //   397: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   400: ldc             ")("
        //   402: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   405: aload_0        
        //   406: getfield        com/jetbrains/cidr/execution/debugger/OCEvaluatorHelper$1.val$convertedExpression:Lcom/intellij/openapi/util/Ref;
        //   409: invokevirtual   com/intellij/openapi/util/Ref.get:()Ljava/lang/Object;
        //   412: checkcast       Ljava/lang/String;
        //   415: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   418: ldc             "))"
        //   420: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   423: invokevirtual   java/lang/StringBuilder.toString:()Ljava/lang/String;
        //   426: invokevirtual   java/util/LinkedHashSet.add:(Ljava/lang/Object;)Z
        //   429: pop            
        //   430: goto            480
        //   433: getstatic       com/jetbrains/cidr/execution/debugger/CidrDebuggerLog.LOG:Lcom/intellij/openapi/diagnostic/Logger;
        //   436: new             Ljava/lang/StringBuilder;
        //   439: dup            
        //   440: invokespecial   java/lang/StringBuilder.<init>:()V
        //   443: ldc             "Cannot get PsiFile for "
        //   445: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   448: aload_0        
        //   449: getfield        com/jetbrains/cidr/execution/debugger/OCEvaluatorHelper$1.val$sourcePosition:Lcom/intellij/xdebugger/XSourcePosition;
        //   452: ifnull          469
        //   455: aload_0        
        //   456: getfield        com/jetbrains/cidr/execution/debugger/OCEvaluatorHelper$1.val$sourcePosition:Lcom/intellij/xdebugger/XSourcePosition;
        //   459: invokevirtual   java/lang/Object.toString:()Ljava/lang/String;
        //   462: goto            471
        //   465: invokestatic    com/jetbrains/cidr/execution/debugger/OCEvaluatorHelper$1.b:(Lcom/jetbrains/cidr/execution/debugger/CidrEvaluatorHelper$ConversionException;)Lcom/jetbrains/cidr/execution/debugger/CidrEvaluatorHelper$ConversionException;
        //   468: athrow         
        //   469: ldc             "UNKNOWN POSITION"
        //   471: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   474: invokevirtual   java/lang/StringBuilder.toString:()Ljava/lang/String;
        //   477: invokevirtual   com/intellij/openapi/diagnostic/Logger.warn:(Ljava/lang/String;)V
        //   480: aload_0        
        //   481: getfield        com/jetbrains/cidr/execution/debugger/OCEvaluatorHelper$1.val$expressions:Ljava/util/LinkedHashSet;
        //   484: new             Ljava/lang/StringBuilder;
        //   487: dup            
        //   488: invokespecial   java/lang/StringBuilder.<init>:()V
        //   491: ldc             "(("
        //   493: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   496: aload_2        
        //   497: invokevirtual   com/jetbrains/cidr/lang/types/OCType.getName:()Ljava/lang/String;
        //   500: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   503: ldc             ")("
        //   505: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   508: aload_0        
        //   509: getfield        com/jetbrains/cidr/execution/debugger/OCEvaluatorHelper$1.val$convertedExpression:Lcom/intellij/openapi/util/Ref;
        //   512: invokevirtual   com/intellij/openapi/util/Ref.get:()Ljava/lang/Object;
        //   515: checkcast       Ljava/lang/String;
        //   518: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   521: ldc             "))"
        //   523: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   526: invokevirtual   java/lang/StringBuilder.toString:()Ljava/lang/String;
        //   529: invokevirtual   java/util/LinkedHashSet.add:(Ljava/lang/Object;)Z
        //   532: pop            
        //   533: return         
        //    Exceptions:
        //  throws java.lang.Throwable
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                                                                           
        //  -----  -----  -----  -----  -------------------------------------------------------------------------------
        //  0      40     40     44     Lcom/jetbrains/cidr/execution/debugger/CidrEvaluatorHelper$ConversionException;
        //  44     87     90     107    Lcom/jetbrains/cidr/execution/debugger/CidrEvaluatorHelper$ConversionException;
        //  145    162    165    169    Lcom/jetbrains/cidr/execution/debugger/CidrEvaluatorHelper$ConversionException;
        //  155    173    176    180    Lcom/jetbrains/cidr/execution/debugger/CidrEvaluatorHelper$ConversionException;
        //  169    189    189    193    Lcom/jetbrains/cidr/execution/debugger/CidrEvaluatorHelper$ConversionException;
        //  196    211    214    218    Lcom/jetbrains/cidr/execution/debugger/CidrEvaluatorHelper$ConversionException;
        //  201    225    228    232    Lcom/jetbrains/cidr/execution/debugger/CidrEvaluatorHelper$ConversionException;
        //  244    261    264    268    Lcom/jetbrains/cidr/execution/debugger/CidrEvaluatorHelper$ConversionException;
        //  307    371    371    375    Lcom/jetbrains/cidr/execution/debugger/CidrEvaluatorHelper$ConversionException;
        //  433    465    465    469    Lcom/jetbrains/cidr/execution/debugger/CidrEvaluatorHelper$ConversionException;
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
    
    private static ConversionException b(final ConversionException ex) {
        return ex;
    }
}