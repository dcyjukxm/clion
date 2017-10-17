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
import com.jetbrains.cidr.lang.psi.OCUnaryExpression;
import com.jetbrains.cidr.lang.parser.OCElementType;
import com.jetbrains.cidr.lang.psi.OCBinaryExpression;
import com.jetbrains.cidr.lang.psi.OCStatement;
import com.jetbrains.cidr.lang.util.OCExpressionEvaluator;
import com.jetbrains.cidr.lang.util.OCElementUtil;
import com.jetbrains.cidr.lang.psi.OCExpression;
import com.jetbrains.cidr.lang.psi.OCDeclarationOrExpression;
import com.jetbrains.cidr.lang.psi.OCLoopStatement;
import org.jetbrains.annotations.Nullable;
import com.intellij.psi.PsiElement;
import org.jetbrains.annotations.NotNull;
import com.jetbrains.cidr.lang.psi.OCIfStatement;

private static class Simplifier
{
    private boolean equalToYesNoUsed;
    private boolean ifWithConstBranchUsed;
    
    public boolean isWarningDisabled(final OCSimplifyInspection p0) {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     0: invokestatic    com/intellij/openapi/application/ApplicationManager.getApplication:()Lcom/intellij/openapi/application/Application;
        //     3: invokeinterface com/intellij/openapi/application/Application.isUnitTestMode:()Z
        //     8: ifeq            17
        //    11: iconst_0       
        //    12: ireturn        
        //    13: invokestatic    com/jetbrains/cidr/lang/inspections/OCSimplifyInspection$Simplifier.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //    16: athrow         
        //    17: aload_0        
        //    18: getfield        com/jetbrains/cidr/lang/inspections/OCSimplifyInspection$Simplifier.equalToYesNoUsed:Z
        //    21: ifeq            38
        //    24: aload_1        
        //    25: getfield        com/jetbrains/cidr/lang/inspections/OCSimplifyInspection.enableSimplifyEqualToYesNo:Z
        //    28: ifeq            66
        //    31: goto            38
        //    34: invokestatic    com/jetbrains/cidr/lang/inspections/OCSimplifyInspection$Simplifier.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //    37: athrow         
        //    38: aload_0        
        //    39: getfield        com/jetbrains/cidr/lang/inspections/OCSimplifyInspection$Simplifier.ifWithConstBranchUsed:Z
        //    42: ifeq            74
        //    45: goto            52
        //    48: invokestatic    com/jetbrains/cidr/lang/inspections/OCSimplifyInspection$Simplifier.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //    51: athrow         
        //    52: aload_1        
        //    53: getfield        com/jetbrains/cidr/lang/inspections/OCSimplifyInspection.enableSimplifyIfWithConstantBranch:Z
        //    56: ifne            74
        //    59: goto            66
        //    62: invokestatic    com/jetbrains/cidr/lang/inspections/OCSimplifyInspection$Simplifier.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //    65: athrow         
        //    66: iconst_1       
        //    67: goto            75
        //    70: invokestatic    com/jetbrains/cidr/lang/inspections/OCSimplifyInspection$Simplifier.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //    73: athrow         
        //    74: iconst_0       
        //    75: ireturn        
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                                
        //  -----  -----  -----  -----  ------------------------------------
        //  0      13     13     17     Ljava/lang/IllegalArgumentException;
        //  17     31     34     38     Ljava/lang/IllegalArgumentException;
        //  24     45     48     52     Ljava/lang/IllegalArgumentException;
        //  38     59     62     66     Ljava/lang/IllegalArgumentException;
        //  52     70     70     74     Ljava/lang/IllegalArgumentException;
        // 
        // The error that occurred was:
        // 
        // java.lang.IllegalStateException: Expression is linked from several locations: Label_0038:
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
    private PsiElement a(@NotNull final OCIfStatement p0) {
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
        //    18: ldc             "statement"
        //    20: aastore        
        //    21: dup            
        //    22: ldc             1
        //    24: ldc             "com/jetbrains/cidr/lang/inspections/OCSimplifyInspection$Simplifier"
        //    26: aastore        
        //    27: dup            
        //    28: ldc             2
        //    30: ldc             "trySimplifyIfStatement"
        //    32: aastore        
        //    33: invokestatic    java/lang/String.format:(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
        //    36: invokespecial   java/lang/IllegalArgumentException.<init>:(Ljava/lang/String;)V
        //    39: athrow         
        //    40: invokestatic    com/jetbrains/cidr/lang/inspections/OCSimplifyInspection$Simplifier.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //    43: athrow         
        //    44: aload_1        
        //    45: invokeinterface com/jetbrains/cidr/lang/psi/OCIfStatement.getCondition:()Lcom/jetbrains/cidr/lang/psi/OCDeclarationOrExpression;
        //    50: astore_2       
        //    51: aload_2        
        //    52: ifnull          68
        //    55: aload_2        
        //    56: invokeinterface com/jetbrains/cidr/lang/psi/OCDeclarationOrExpression.getExpression:()Lcom/jetbrains/cidr/lang/psi/OCExpression;
        //    61: goto            69
        //    64: invokestatic    com/jetbrains/cidr/lang/inspections/OCSimplifyInspection$Simplifier.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //    67: athrow         
        //    68: aconst_null    
        //    69: astore_3       
        //    70: aload_3        
        //    71: ifnull          88
        //    74: aload_3        
        //    75: invokestatic    com/jetbrains/cidr/lang/util/OCElementUtil.isPartOfMacroSubstitution:(Lcom/intellij/psi/PsiElement;)Z
        //    78: ifeq            94
        //    81: goto            88
        //    84: invokestatic    com/jetbrains/cidr/lang/inspections/OCSimplifyInspection$Simplifier.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //    87: athrow         
        //    88: aconst_null    
        //    89: areturn        
        //    90: invokestatic    com/jetbrains/cidr/lang/inspections/OCSimplifyInspection$Simplifier.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //    93: athrow         
        //    94: aload_1        
        //    95: invokeinterface com/jetbrains/cidr/lang/psi/OCIfStatement.getThenBranch:()Lcom/jetbrains/cidr/lang/psi/OCStatement;
        //   100: astore          4
        //   102: aload_1        
        //   103: invokeinterface com/jetbrains/cidr/lang/psi/OCIfStatement.getElseBranch:()Lcom/jetbrains/cidr/lang/psi/OCStatement;
        //   108: astore          5
        //   110: aload_3        
        //   111: invokestatic    com/jetbrains/cidr/lang/util/OCExpressionEvaluator.evaluate:(Lcom/jetbrains/cidr/lang/psi/OCExpression;)Ljava/lang/Number;
        //   114: aload_2        
        //   115: invokestatic    com/jetbrains/cidr/lang/inspections/OCSimplifyInspection.access$900:(Ljava/lang/Object;Lcom/intellij/psi/PsiElement;)Ljava/lang/Object;
        //   118: checkcast       Ljava/lang/Number;
        //   121: astore          6
        //   123: aload           6
        //   125: ifnull          157
        //   128: aload           6
        //   130: invokestatic    com/jetbrains/cidr/lang/util/OCExpressionEvaluator.singAsInC:(Ljava/lang/Object;)I
        //   133: ifeq            151
        //   136: goto            143
        //   139: invokestatic    com/jetbrains/cidr/lang/inspections/OCSimplifyInspection$Simplifier.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   142: athrow         
        //   143: iconst_1       
        //   144: goto            152
        //   147: invokestatic    com/jetbrains/cidr/lang/inspections/OCSimplifyInspection$Simplifier.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   150: athrow         
        //   151: iconst_0       
        //   152: aload_1        
        //   153: invokestatic    com/jetbrains/cidr/lang/inspections/OCSimplifyInspection.simplifyStatementWithConstCondition:(ZLcom/jetbrains/cidr/lang/psi/OCStatement;)Lcom/jetbrains/cidr/lang/psi/OCStatement;
        //   156: areturn        
        //   157: aload           4
        //   159: ifnull          221
        //   162: aload           5
        //   164: ifnull          221
        //   167: goto            174
        //   170: invokestatic    com/jetbrains/cidr/lang/inspections/OCSimplifyInspection$Simplifier.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   173: athrow         
        //   174: aload           4
        //   176: aload           5
        //   178: iconst_0       
        //   179: new             Lcom/jetbrains/cidr/lang/symbols/OCResolveContext;
        //   182: dup            
        //   183: aload_1        
        //   184: invokespecial   com/jetbrains/cidr/lang/symbols/OCResolveContext.<init>:(Lcom/intellij/psi/PsiElement;)V
        //   187: invokestatic    com/jetbrains/cidr/lang/util/OCElementUtil.areElementsEquivalent:(Lcom/intellij/psi/PsiElement;Lcom/intellij/psi/PsiElement;ZLcom/jetbrains/cidr/lang/symbols/OCResolveContext;)Z
        //   190: ifeq            221
        //   193: goto            200
        //   196: invokestatic    com/jetbrains/cidr/lang/inspections/OCSimplifyInspection$Simplifier.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   199: athrow         
        //   200: aload_2        
        //   201: invokestatic    com/jetbrains/cidr/lang/util/OCCodeInsightUtil.hasSideEffects:(Lcom/jetbrains/cidr/lang/psi/OCElement;)Z
        //   204: ifne            221
        //   207: goto            214
        //   210: invokestatic    com/jetbrains/cidr/lang/inspections/OCSimplifyInspection$Simplifier.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   213: athrow         
        //   214: aload           4
        //   216: areturn        
        //   217: invokestatic    com/jetbrains/cidr/lang/inspections/OCSimplifyInspection$Simplifier.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   220: athrow         
        //   221: new             Lcom/jetbrains/cidr/lang/intentions/OCConvertIfToTernaryIntentionAction$Converter;
        //   224: dup            
        //   225: new             Lcom/jetbrains/cidr/lang/intentions/OCConvertIfToTernaryIntentionAction;
        //   228: dup            
        //   229: invokespecial   com/jetbrains/cidr/lang/intentions/OCConvertIfToTernaryIntentionAction.<init>:()V
        //   232: dup            
        //   233: invokevirtual   java/lang/Object.getClass:()Ljava/lang/Class;
        //   236: pop            
        //   237: aload_2        
        //   238: invokespecial   com/jetbrains/cidr/lang/intentions/OCConvertIfToTernaryIntentionAction$Converter.<init>:(Lcom/jetbrains/cidr/lang/intentions/OCConvertIfToTernaryIntentionAction;Lcom/intellij/psi/PsiElement;)V
        //   241: astore          7
        //   243: aload           7
        //   245: invokevirtual   com/jetbrains/cidr/lang/intentions/OCConvertIfToTernaryIntentionAction$Converter.isAvailable:()Z
        //   248: ifne            257
        //   251: aconst_null    
        //   252: areturn        
        //   253: invokestatic    com/jetbrains/cidr/lang/inspections/OCSimplifyInspection$Simplifier.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   256: athrow         
        //   257: aload_0        
        //   258: aload           7
        //   260: invokevirtual   com/jetbrains/cidr/lang/intentions/OCConvertIfToTernaryIntentionAction$Converter.getCondition:()Lcom/jetbrains/cidr/lang/psi/OCExpression;
        //   263: aload           7
        //   265: invokevirtual   com/jetbrains/cidr/lang/intentions/OCConvertIfToTernaryIntentionAction$Converter.getThenExpression:()Lcom/jetbrains/cidr/lang/psi/OCExpression;
        //   268: aload           7
        //   270: invokevirtual   com/jetbrains/cidr/lang/intentions/OCConvertIfToTernaryIntentionAction$Converter.getElseExpression:()Lcom/jetbrains/cidr/lang/psi/OCExpression;
        //   273: invokespecial   com/jetbrains/cidr/lang/inspections/OCSimplifyInspection$Simplifier.a:(Lcom/jetbrains/cidr/lang/psi/OCExpression;Lcom/jetbrains/cidr/lang/psi/OCExpression;Lcom/jetbrains/cidr/lang/psi/OCExpression;)Lcom/jetbrains/cidr/lang/psi/OCExpression;
        //   276: areturn        
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                                
        //  -----  -----  -----  -----  ------------------------------------
        //  0      40     40     44     Ljava/lang/IllegalArgumentException;
        //  51     64     64     68     Ljava/lang/IllegalArgumentException;
        //  70     81     84     88     Ljava/lang/IllegalArgumentException;
        //  74     90     90     94     Ljava/lang/IllegalArgumentException;
        //  123    136    139    143    Ljava/lang/IllegalArgumentException;
        //  128    147    147    151    Ljava/lang/IllegalArgumentException;
        //  157    167    170    174    Ljava/lang/IllegalArgumentException;
        //  162    193    196    200    Ljava/lang/IllegalArgumentException;
        //  174    207    210    214    Ljava/lang/IllegalArgumentException;
        //  200    217    217    221    Ljava/lang/IllegalArgumentException;
        //  243    253    253    257    Ljava/lang/IllegalArgumentException;
        // 
        // The error that occurred was:
        // 
        // java.lang.IllegalStateException: Expression is linked from several locations: Label_0174:
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
    
    private static PsiElement a(@NotNull final OCLoopStatement ocLoopStatement) {
        try {
            if (ocLoopStatement == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "statement", "com/jetbrains/cidr/lang/inspections/OCSimplifyInspection$Simplifier", "trySimplifyLoopStatement"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        Object o = ocLoopStatement.getCondition();
        if (o instanceof OCDeclarationOrExpression) {
            o = ((OCDeclarationOrExpression)o).getExpression();
        }
        Label_0089: {
            try {
                if (!(o instanceof OCExpression)) {
                    break Label_0089;
                }
                final OCDeclarationOrExpression ocDeclarationOrExpression = (OCDeclarationOrExpression)o;
                final boolean b = OCElementUtil.isPartOfMacroSubstitution((PsiElement)ocDeclarationOrExpression);
                if (b) {
                    break Label_0089;
                }
                break Label_0089;
            }
            catch (IllegalArgumentException ex2) {
                throw a(ex2);
            }
            try {
                final OCDeclarationOrExpression ocDeclarationOrExpression = (OCDeclarationOrExpression)o;
                final boolean b = OCElementUtil.isPartOfMacroSubstitution((PsiElement)ocDeclarationOrExpression);
                if (b) {
                    return null;
                }
            }
            catch (IllegalArgumentException ex3) {
                throw a(ex3);
            }
        }
        final Number n = (Number)OCSimplifyInspection.access$900(OCExpressionEvaluator.evaluate((OCExpression)o), (PsiElement)o);
        Label_0128: {
            try {
                if (n == null) {
                    return null;
                }
                final Number n2 = n;
                final int n3 = OCExpressionEvaluator.singAsInC(n2);
                if (n3 > 0) {
                    break Label_0128;
                }
                break Label_0128;
            }
            catch (IllegalArgumentException ex4) {
                throw a(ex4);
            }
            try {
                final Number n2 = n;
                final int n3 = OCExpressionEvaluator.singAsInC(n2);
                if (n3 > 0) {
                    final boolean b2 = true;
                    return (PsiElement)OCSimplifyInspection.simplifyStatementWithConstCondition(b2, ocLoopStatement);
                }
            }
            catch (IllegalArgumentException ex5) {
                throw a(ex5);
            }
        }
        final boolean b2 = false;
        return (PsiElement)OCSimplifyInspection.simplifyStatementWithConstCondition(b2, ocLoopStatement);
    }
    
    @Nullable
    private OCExpression a(@Nullable final OCExpression p0, @Nullable final OCExpression p1, @Nullable final OCExpression p2) {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     0: aload_1        
        //     1: ifnull          26
        //     4: aload_2        
        //     5: ifnull          26
        //     8: goto            15
        //    11: invokestatic    com/jetbrains/cidr/lang/inspections/OCSimplifyInspection$Simplifier.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //    14: athrow         
        //    15: aload_3        
        //    16: ifnonnull       32
        //    19: goto            26
        //    22: invokestatic    com/jetbrains/cidr/lang/inspections/OCSimplifyInspection$Simplifier.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //    25: athrow         
        //    26: aconst_null    
        //    27: areturn        
        //    28: invokestatic    com/jetbrains/cidr/lang/inspections/OCSimplifyInspection$Simplifier.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //    31: athrow         
        //    32: aload_1        
        //    33: invokestatic    com/jetbrains/cidr/lang/util/OCExpressionEvaluator.evaluate:(Lcom/jetbrains/cidr/lang/psi/OCExpression;)Ljava/lang/Number;
        //    36: aload_1        
        //    37: invokestatic    com/jetbrains/cidr/lang/inspections/OCSimplifyInspection.access$900:(Ljava/lang/Object;Lcom/intellij/psi/PsiElement;)Ljava/lang/Object;
        //    40: checkcast       Ljava/lang/Number;
        //    43: astore          4
        //    45: aload           4
        //    47: ifnull          78
        //    50: aload           4
        //    52: invokestatic    com/jetbrains/cidr/lang/util/OCExpressionEvaluator.singAsInC:(Ljava/lang/Object;)I
        //    55: ifeq            73
        //    58: goto            65
        //    61: invokestatic    com/jetbrains/cidr/lang/inspections/OCSimplifyInspection$Simplifier.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //    64: athrow         
        //    65: aload_2        
        //    66: goto            74
        //    69: invokestatic    com/jetbrains/cidr/lang/inspections/OCSimplifyInspection$Simplifier.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //    72: athrow         
        //    73: aload_3        
        //    74: invokestatic    com/jetbrains/cidr/lang/inspections/OCSimplifyInspection.access$1000:(Lcom/jetbrains/cidr/lang/psi/OCExpression;)Lcom/jetbrains/cidr/lang/psi/OCExpression;
        //    77: areturn        
        //    78: new             Lcom/jetbrains/cidr/lang/symbols/OCResolveContext;
        //    81: dup            
        //    82: aload_1        
        //    83: invokespecial   com/jetbrains/cidr/lang/symbols/OCResolveContext.<init>:(Lcom/intellij/psi/PsiElement;)V
        //    86: astore          5
        //    88: aload_2        
        //    89: aload_3        
        //    90: iconst_0       
        //    91: aload           5
        //    93: invokestatic    com/jetbrains/cidr/lang/util/OCParenthesesUtils.areExpressionsEquivalent:(Lcom/jetbrains/cidr/lang/psi/OCExpression;Lcom/jetbrains/cidr/lang/psi/OCExpression;ZLcom/jetbrains/cidr/lang/symbols/OCResolveContext;)Z
        //    96: ifeq            122
        //    99: aload_1        
        //   100: invokestatic    com/jetbrains/cidr/lang/util/OCCodeInsightUtil.hasSideEffects:(Lcom/jetbrains/cidr/lang/psi/OCElement;)Z
        //   103: ifne            122
        //   106: goto            113
        //   109: invokestatic    com/jetbrains/cidr/lang/inspections/OCSimplifyInspection$Simplifier.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   112: athrow         
        //   113: aload_2        
        //   114: invokestatic    com/jetbrains/cidr/lang/inspections/OCSimplifyInspection.access$1000:(Lcom/jetbrains/cidr/lang/psi/OCExpression;)Lcom/jetbrains/cidr/lang/psi/OCExpression;
        //   117: areturn        
        //   118: invokestatic    com/jetbrains/cidr/lang/inspections/OCSimplifyInspection$Simplifier.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   121: athrow         
        //   122: aload_2        
        //   123: aload_3        
        //   124: iconst_0       
        //   125: aload           5
        //   127: invokestatic    com/jetbrains/cidr/lang/util/OCParenthesesUtils.areExpressionsOpposite:(Lcom/jetbrains/cidr/lang/psi/OCExpression;Lcom/jetbrains/cidr/lang/psi/OCExpression;ZLcom/jetbrains/cidr/lang/symbols/OCResolveContext;)Z
        //   130: ifeq            149
        //   133: aload_1        
        //   134: invokestatic    com/jetbrains/cidr/lang/inspections/OCSimplifyInspection$Simplifier.a:(Lcom/jetbrains/cidr/lang/psi/OCExpression;)Lcom/jetbrains/cidr/lang/psi/OCExpression;
        //   137: aload_2        
        //   138: getstatic       com/jetbrains/cidr/lang/parser/OCTokenTypes.EQEQ:Lcom/jetbrains/cidr/lang/parser/OCPunctuatorElementType;
        //   141: invokestatic    com/jetbrains/cidr/lang/inspections/OCSimplifyInspection$Simplifier.a:(Lcom/jetbrains/cidr/lang/psi/OCExpression;Lcom/jetbrains/cidr/lang/psi/OCExpression;Lcom/jetbrains/cidr/lang/parser/OCElementType;)Lcom/jetbrains/cidr/lang/psi/OCExpression;
        //   144: areturn        
        //   145: invokestatic    com/jetbrains/cidr/lang/inspections/OCSimplifyInspection$Simplifier.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   148: athrow         
        //   149: aload_2        
        //   150: aload           5
        //   152: iconst_2       
        //   153: anewarray       Ljava/lang/Class;
        //   156: dup            
        //   157: iconst_0       
        //   158: ldc             Lcom/jetbrains/cidr/lang/types/OCMagicType;.class
        //   160: aastore        
        //   161: dup            
        //   162: iconst_1       
        //   163: ldc             Lcom/jetbrains/cidr/lang/types/OCStructType;.class
        //   165: aastore        
        //   166: invokestatic    com/jetbrains/cidr/lang/types/OCTypeUtils.isInstanceOfType:(Lcom/jetbrains/cidr/lang/psi/OCExpression;Lcom/jetbrains/cidr/lang/symbols/OCResolveContext;[Ljava/lang/Class;)Z
        //   169: ifne            202
        //   172: aload_3        
        //   173: aload           5
        //   175: iconst_2       
        //   176: anewarray       Ljava/lang/Class;
        //   179: dup            
        //   180: iconst_0       
        //   181: ldc             Lcom/jetbrains/cidr/lang/types/OCMagicType;.class
        //   183: aastore        
        //   184: dup            
        //   185: iconst_1       
        //   186: ldc             Lcom/jetbrains/cidr/lang/types/OCStructType;.class
        //   188: aastore        
        //   189: invokestatic    com/jetbrains/cidr/lang/types/OCTypeUtils.isInstanceOfType:(Lcom/jetbrains/cidr/lang/psi/OCExpression;Lcom/jetbrains/cidr/lang/symbols/OCResolveContext;[Ljava/lang/Class;)Z
        //   192: ifeq            208
        //   195: goto            202
        //   198: invokestatic    com/jetbrains/cidr/lang/inspections/OCSimplifyInspection$Simplifier.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   201: athrow         
        //   202: aconst_null    
        //   203: areturn        
        //   204: invokestatic    com/jetbrains/cidr/lang/inspections/OCSimplifyInspection$Simplifier.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   207: athrow         
        //   208: aload_2        
        //   209: invokestatic    com/jetbrains/cidr/lang/util/OCExpressionEvaluator.evaluateToIntOrBoolean:(Lcom/jetbrains/cidr/lang/psi/OCExpression;)Ljava/lang/Object;
        //   212: aload_2        
        //   213: invokestatic    com/jetbrains/cidr/lang/inspections/OCSimplifyInspection.access$900:(Ljava/lang/Object;Lcom/intellij/psi/PsiElement;)Ljava/lang/Object;
        //   216: astore          6
        //   218: aload_3        
        //   219: invokestatic    com/jetbrains/cidr/lang/util/OCExpressionEvaluator.evaluateToIntOrBoolean:(Lcom/jetbrains/cidr/lang/psi/OCExpression;)Ljava/lang/Object;
        //   222: aload_3        
        //   223: invokestatic    com/jetbrains/cidr/lang/inspections/OCSimplifyInspection.access$900:(Ljava/lang/Object;Lcom/intellij/psi/PsiElement;)Ljava/lang/Object;
        //   226: astore          7
        //   228: aload           6
        //   230: instanceof      Ljava/lang/Boolean;
        //   233: ifeq            306
        //   236: aload_0        
        //   237: aload           7
        //   239: instanceof      Ljava/lang/Boolean;
        //   242: ifne            260
        //   245: goto            252
        //   248: invokestatic    com/jetbrains/cidr/lang/inspections/OCSimplifyInspection$Simplifier.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   251: athrow         
        //   252: iconst_1       
        //   253: goto            261
        //   256: invokestatic    com/jetbrains/cidr/lang/inspections/OCSimplifyInspection$Simplifier.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   259: athrow         
        //   260: iconst_0       
        //   261: putfield        com/jetbrains/cidr/lang/inspections/OCSimplifyInspection$Simplifier.ifWithConstBranchUsed:Z
        //   264: aload           6
        //   266: checkcast       Ljava/lang/Boolean;
        //   269: invokevirtual   java/lang/Boolean.booleanValue:()Z
        //   272: ifeq            291
        //   275: aload_1        
        //   276: invokestatic    com/jetbrains/cidr/lang/inspections/OCSimplifyInspection$Simplifier.a:(Lcom/jetbrains/cidr/lang/psi/OCExpression;)Lcom/jetbrains/cidr/lang/psi/OCExpression;
        //   279: aload_3        
        //   280: getstatic       com/jetbrains/cidr/lang/parser/OCTokenTypes.OROR:Lcom/jetbrains/cidr/lang/parser/OCPunctuatorElementType;
        //   283: invokestatic    com/jetbrains/cidr/lang/inspections/OCSimplifyInspection$Simplifier.a:(Lcom/jetbrains/cidr/lang/psi/OCExpression;Lcom/jetbrains/cidr/lang/psi/OCExpression;Lcom/jetbrains/cidr/lang/parser/OCElementType;)Lcom/jetbrains/cidr/lang/psi/OCExpression;
        //   286: areturn        
        //   287: invokestatic    com/jetbrains/cidr/lang/inspections/OCSimplifyInspection$Simplifier.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   290: athrow         
        //   291: aload_1        
        //   292: invokestatic    com/jetbrains/cidr/lang/inspections/OCSimplifyInspection$Simplifier.a:(Lcom/jetbrains/cidr/lang/psi/OCExpression;)Lcom/jetbrains/cidr/lang/psi/OCExpression;
        //   295: invokestatic    com/jetbrains/cidr/lang/inspections/OCSimplifyInspection$Simplifier.b:(Lcom/jetbrains/cidr/lang/psi/OCExpression;)Lcom/jetbrains/cidr/lang/psi/OCExpression;
        //   298: aload_3        
        //   299: getstatic       com/jetbrains/cidr/lang/parser/OCTokenTypes.ANDAND:Lcom/jetbrains/cidr/lang/parser/OCPunctuatorElementType;
        //   302: invokestatic    com/jetbrains/cidr/lang/inspections/OCSimplifyInspection$Simplifier.a:(Lcom/jetbrains/cidr/lang/psi/OCExpression;Lcom/jetbrains/cidr/lang/psi/OCExpression;Lcom/jetbrains/cidr/lang/parser/OCElementType;)Lcom/jetbrains/cidr/lang/psi/OCExpression;
        //   305: areturn        
        //   306: aload           7
        //   308: instanceof      Ljava/lang/Boolean;
        //   311: ifeq            368
        //   314: aload_0        
        //   315: iconst_1       
        //   316: putfield        com/jetbrains/cidr/lang/inspections/OCSimplifyInspection$Simplifier.ifWithConstBranchUsed:Z
        //   319: aload           7
        //   321: checkcast       Ljava/lang/Boolean;
        //   324: invokevirtual   java/lang/Boolean.booleanValue:()Z
        //   327: ifeq            356
        //   330: goto            337
        //   333: invokestatic    com/jetbrains/cidr/lang/inspections/OCSimplifyInspection$Simplifier.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   336: athrow         
        //   337: aload_1        
        //   338: invokestatic    com/jetbrains/cidr/lang/inspections/OCSimplifyInspection$Simplifier.a:(Lcom/jetbrains/cidr/lang/psi/OCExpression;)Lcom/jetbrains/cidr/lang/psi/OCExpression;
        //   341: invokestatic    com/jetbrains/cidr/lang/inspections/OCSimplifyInspection$Simplifier.b:(Lcom/jetbrains/cidr/lang/psi/OCExpression;)Lcom/jetbrains/cidr/lang/psi/OCExpression;
        //   344: aload_2        
        //   345: getstatic       com/jetbrains/cidr/lang/parser/OCTokenTypes.OROR:Lcom/jetbrains/cidr/lang/parser/OCPunctuatorElementType;
        //   348: invokestatic    com/jetbrains/cidr/lang/inspections/OCSimplifyInspection$Simplifier.a:(Lcom/jetbrains/cidr/lang/psi/OCExpression;Lcom/jetbrains/cidr/lang/psi/OCExpression;Lcom/jetbrains/cidr/lang/parser/OCElementType;)Lcom/jetbrains/cidr/lang/psi/OCExpression;
        //   351: areturn        
        //   352: invokestatic    com/jetbrains/cidr/lang/inspections/OCSimplifyInspection$Simplifier.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   355: athrow         
        //   356: aload_1        
        //   357: invokestatic    com/jetbrains/cidr/lang/inspections/OCSimplifyInspection$Simplifier.a:(Lcom/jetbrains/cidr/lang/psi/OCExpression;)Lcom/jetbrains/cidr/lang/psi/OCExpression;
        //   360: aload_2        
        //   361: getstatic       com/jetbrains/cidr/lang/parser/OCTokenTypes.ANDAND:Lcom/jetbrains/cidr/lang/parser/OCPunctuatorElementType;
        //   364: invokestatic    com/jetbrains/cidr/lang/inspections/OCSimplifyInspection$Simplifier.a:(Lcom/jetbrains/cidr/lang/psi/OCExpression;Lcom/jetbrains/cidr/lang/psi/OCExpression;Lcom/jetbrains/cidr/lang/parser/OCElementType;)Lcom/jetbrains/cidr/lang/psi/OCExpression;
        //   367: areturn        
        //   368: aconst_null    
        //   369: areturn        
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                                
        //  -----  -----  -----  -----  ------------------------------------
        //  0      8      11     15     Ljava/lang/IllegalArgumentException;
        //  4      19     22     26     Ljava/lang/IllegalArgumentException;
        //  15     28     28     32     Ljava/lang/IllegalArgumentException;
        //  45     58     61     65     Ljava/lang/IllegalArgumentException;
        //  50     69     69     73     Ljava/lang/IllegalArgumentException;
        //  88     106    109    113    Ljava/lang/IllegalArgumentException;
        //  99     118    118    122    Ljava/lang/IllegalArgumentException;
        //  122    145    145    149    Ljava/lang/IllegalArgumentException;
        //  149    195    198    202    Ljava/lang/IllegalArgumentException;
        //  172    204    204    208    Ljava/lang/IllegalArgumentException;
        //  228    245    248    252    Ljava/lang/IllegalArgumentException;
        //  236    256    256    260    Ljava/lang/IllegalArgumentException;
        //  261    287    287    291    Ljava/lang/IllegalArgumentException;
        //  306    330    333    337    Ljava/lang/IllegalArgumentException;
        //  314    352    352    356    Ljava/lang/IllegalArgumentException;
        // 
        // The error that occurred was:
        // 
        // java.lang.IllegalStateException: Expression is linked from several locations: Label_0015:
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
    private OCExpression a(@NotNull final OCBinaryExpression p0) {
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
        //    24: ldc             "com/jetbrains/cidr/lang/inspections/OCSimplifyInspection$Simplifier"
        //    26: aastore        
        //    27: dup            
        //    28: ldc             2
        //    30: ldc             "trySimplifyBinaryExpr"
        //    32: aastore        
        //    33: invokestatic    java/lang/String.format:(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
        //    36: invokespecial   java/lang/IllegalArgumentException.<init>:(Ljava/lang/String;)V
        //    39: athrow         
        //    40: invokestatic    com/jetbrains/cidr/lang/inspections/OCSimplifyInspection$Simplifier.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //    43: athrow         
        //    44: aload_1        
        //    45: invokeinterface com/jetbrains/cidr/lang/psi/OCBinaryExpression.getLeft:()Lcom/jetbrains/cidr/lang/psi/OCExpression;
        //    50: astore_2       
        //    51: aload_1        
        //    52: invokeinterface com/jetbrains/cidr/lang/psi/OCBinaryExpression.getRight:()Lcom/jetbrains/cidr/lang/psi/OCExpression;
        //    57: astore_3       
        //    58: aload_1        
        //    59: invokeinterface com/jetbrains/cidr/lang/psi/OCBinaryExpression.getOperationSign:()Lcom/jetbrains/cidr/lang/parser/OCElementType;
        //    64: astore          4
        //    66: aload_2        
        //    67: ifnull          81
        //    70: aload_3        
        //    71: ifnonnull       87
        //    74: goto            81
        //    77: invokestatic    com/jetbrains/cidr/lang/inspections/OCSimplifyInspection$Simplifier.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //    80: athrow         
        //    81: aconst_null    
        //    82: areturn        
        //    83: invokestatic    com/jetbrains/cidr/lang/inspections/OCSimplifyInspection$Simplifier.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //    86: athrow         
        //    87: new             Lcom/jetbrains/cidr/lang/symbols/OCResolveContext;
        //    90: dup            
        //    91: aload_2        
        //    92: invokeinterface com/jetbrains/cidr/lang/psi/OCExpression.getContext:()Lcom/intellij/psi/PsiElement;
        //    97: invokespecial   com/jetbrains/cidr/lang/symbols/OCResolveContext.<init>:(Lcom/intellij/psi/PsiElement;)V
        //   100: astore          5
        //   102: aload_2        
        //   103: aload           5
        //   105: invokestatic    com/jetbrains/cidr/lang/types/OCTypeUtils.getResolvedCppReferencedType:(Lcom/jetbrains/cidr/lang/psi/OCExpression;Lcom/jetbrains/cidr/lang/symbols/OCResolveContext;)Lcom/jetbrains/cidr/lang/types/OCType;
        //   108: astore          6
        //   110: aload_3        
        //   111: aload           5
        //   113: invokestatic    com/jetbrains/cidr/lang/types/OCTypeUtils.getResolvedCppReferencedType:(Lcom/jetbrains/cidr/lang/psi/OCExpression;Lcom/jetbrains/cidr/lang/symbols/OCResolveContext;)Lcom/jetbrains/cidr/lang/types/OCType;
        //   116: astore          7
        //   118: aload           6
        //   120: iconst_2       
        //   121: anewarray       Ljava/lang/Class;
        //   124: dup            
        //   125: iconst_0       
        //   126: ldc             Lcom/jetbrains/cidr/lang/types/OCMagicType;.class
        //   128: aastore        
        //   129: dup            
        //   130: iconst_1       
        //   131: ldc             Lcom/jetbrains/cidr/lang/types/OCStructType;.class
        //   133: aastore        
        //   134: invokestatic    com/jetbrains/cidr/lang/types/OCTypeUtils.isInstanceOfType:(Lcom/jetbrains/cidr/lang/types/OCType;[Ljava/lang/Class;)Z
        //   137: ifne            169
        //   140: aload           7
        //   142: iconst_2       
        //   143: anewarray       Ljava/lang/Class;
        //   146: dup            
        //   147: iconst_0       
        //   148: ldc             Lcom/jetbrains/cidr/lang/types/OCMagicType;.class
        //   150: aastore        
        //   151: dup            
        //   152: iconst_1       
        //   153: ldc             Lcom/jetbrains/cidr/lang/types/OCStructType;.class
        //   155: aastore        
        //   156: invokestatic    com/jetbrains/cidr/lang/types/OCTypeUtils.isInstanceOfType:(Lcom/jetbrains/cidr/lang/types/OCType;[Ljava/lang/Class;)Z
        //   159: ifeq            175
        //   162: goto            169
        //   165: invokestatic    com/jetbrains/cidr/lang/inspections/OCSimplifyInspection$Simplifier.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   168: athrow         
        //   169: aconst_null    
        //   170: areturn        
        //   171: invokestatic    com/jetbrains/cidr/lang/inspections/OCSimplifyInspection$Simplifier.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   174: athrow         
        //   175: aload_2        
        //   176: aload_3        
        //   177: iconst_0       
        //   178: aload           5
        //   180: invokestatic    com/jetbrains/cidr/lang/util/OCParenthesesUtils.areExpressionsEquivalent:(Lcom/jetbrains/cidr/lang/psi/OCExpression;Lcom/jetbrains/cidr/lang/psi/OCExpression;ZLcom/jetbrains/cidr/lang/symbols/OCResolveContext;)Z
        //   183: ifeq            338
        //   186: aload_2        
        //   187: invokestatic    com/jetbrains/cidr/lang/util/OCCodeInsightUtil.hasSideEffects:(Lcom/jetbrains/cidr/lang/psi/OCElement;)Z
        //   190: ifne            338
        //   193: goto            200
        //   196: invokestatic    com/jetbrains/cidr/lang/inspections/OCSimplifyInspection$Simplifier.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   199: athrow         
        //   200: aload           6
        //   202: instanceof      Lcom/jetbrains/cidr/lang/types/OCRealType;
        //   205: ifne            338
        //   208: goto            215
        //   211: invokestatic    com/jetbrains/cidr/lang/inspections/OCSimplifyInspection$Simplifier.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   214: athrow         
        //   215: aload           4
        //   217: getstatic       com/jetbrains/cidr/lang/parser/OCTokenTypes.ANDAND:Lcom/jetbrains/cidr/lang/parser/OCPunctuatorElementType;
        //   220: if_acmpeq       245
        //   223: goto            230
        //   226: invokestatic    com/jetbrains/cidr/lang/inspections/OCSimplifyInspection$Simplifier.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   229: athrow         
        //   230: aload           4
        //   232: getstatic       com/jetbrains/cidr/lang/parser/OCTokenTypes.OROR:Lcom/jetbrains/cidr/lang/parser/OCPunctuatorElementType;
        //   235: if_acmpne       257
        //   238: goto            245
        //   241: invokestatic    com/jetbrains/cidr/lang/inspections/OCSimplifyInspection$Simplifier.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   244: athrow         
        //   245: aload_2        
        //   246: invokestatic    com/jetbrains/cidr/lang/inspections/OCSimplifyInspection.access$1000:(Lcom/jetbrains/cidr/lang/psi/OCExpression;)Lcom/jetbrains/cidr/lang/psi/OCExpression;
        //   249: invokestatic    com/jetbrains/cidr/lang/inspections/OCSimplifyInspection$Simplifier.a:(Lcom/jetbrains/cidr/lang/psi/OCExpression;)Lcom/jetbrains/cidr/lang/psi/OCExpression;
        //   252: areturn        
        //   253: invokestatic    com/jetbrains/cidr/lang/inspections/OCSimplifyInspection$Simplifier.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   256: athrow         
        //   257: aload           4
        //   259: getstatic       com/jetbrains/cidr/lang/parser/OCTokenTypes.AND:Lcom/jetbrains/cidr/lang/parser/OCPunctuatorElementType;
        //   262: if_acmpeq       280
        //   265: aload           4
        //   267: getstatic       com/jetbrains/cidr/lang/parser/OCTokenTypes.OR:Lcom/jetbrains/cidr/lang/parser/OCPunctuatorElementType;
        //   270: if_acmpne       289
        //   273: goto            280
        //   276: invokestatic    com/jetbrains/cidr/lang/inspections/OCSimplifyInspection$Simplifier.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   279: athrow         
        //   280: aload_2        
        //   281: invokestatic    com/jetbrains/cidr/lang/inspections/OCSimplifyInspection.access$1000:(Lcom/jetbrains/cidr/lang/psi/OCExpression;)Lcom/jetbrains/cidr/lang/psi/OCExpression;
        //   284: areturn        
        //   285: invokestatic    com/jetbrains/cidr/lang/inspections/OCSimplifyInspection$Simplifier.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   288: athrow         
        //   289: aload           4
        //   291: getstatic       com/jetbrains/cidr/lang/parser/OCTokenTypes.XOR:Lcom/jetbrains/cidr/lang/parser/OCPunctuatorElementType;
        //   294: if_acmpeq       312
        //   297: aload           4
        //   299: getstatic       com/jetbrains/cidr/lang/parser/OCTokenTypes.EXCLEQ:Lcom/jetbrains/cidr/lang/parser/OCPunctuatorElementType;
        //   302: if_acmpne       321
        //   305: goto            312
        //   308: invokestatic    com/jetbrains/cidr/lang/inspections/OCSimplifyInspection$Simplifier.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   311: athrow         
        //   312: aload_2        
        //   313: invokestatic    com/jetbrains/cidr/lang/inspections/OCSimplifyInspection$Simplifier.a:(Lcom/jetbrains/cidr/lang/psi/OCElement;)Lcom/jetbrains/cidr/lang/psi/OCExpression;
        //   316: areturn        
        //   317: invokestatic    com/jetbrains/cidr/lang/inspections/OCSimplifyInspection$Simplifier.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   320: athrow         
        //   321: aload           4
        //   323: getstatic       com/jetbrains/cidr/lang/parser/OCTokenTypes.EQEQ:Lcom/jetbrains/cidr/lang/parser/OCPunctuatorElementType;
        //   326: if_acmpne       338
        //   329: aload_2        
        //   330: invokestatic    com/jetbrains/cidr/lang/inspections/OCSimplifyInspection$Simplifier.b:(Lcom/jetbrains/cidr/lang/psi/OCElement;)Lcom/jetbrains/cidr/lang/psi/OCExpression;
        //   333: areturn        
        //   334: invokestatic    com/jetbrains/cidr/lang/inspections/OCSimplifyInspection$Simplifier.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   337: athrow         
        //   338: aload_2        
        //   339: aload_3        
        //   340: iconst_0       
        //   341: aload           5
        //   343: invokestatic    com/jetbrains/cidr/lang/util/OCParenthesesUtils.areExpressionsOpposite:(Lcom/jetbrains/cidr/lang/psi/OCExpression;Lcom/jetbrains/cidr/lang/psi/OCExpression;ZLcom/jetbrains/cidr/lang/symbols/OCResolveContext;)Z
        //   346: ifeq            449
        //   349: aload_2        
        //   350: invokestatic    com/jetbrains/cidr/lang/util/OCCodeInsightUtil.hasSideEffects:(Lcom/jetbrains/cidr/lang/psi/OCElement;)Z
        //   353: ifne            449
        //   356: goto            363
        //   359: invokestatic    com/jetbrains/cidr/lang/inspections/OCSimplifyInspection$Simplifier.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   362: athrow         
        //   363: aload           4
        //   365: getstatic       com/jetbrains/cidr/lang/parser/OCTokenTypes.OROR:Lcom/jetbrains/cidr/lang/parser/OCPunctuatorElementType;
        //   368: if_acmpeq       408
        //   371: goto            378
        //   374: invokestatic    com/jetbrains/cidr/lang/inspections/OCSimplifyInspection$Simplifier.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   377: athrow         
        //   378: aload           4
        //   380: getstatic       com/jetbrains/cidr/lang/parser/OCTokenTypes.XOR:Lcom/jetbrains/cidr/lang/parser/OCPunctuatorElementType;
        //   383: if_acmpeq       408
        //   386: goto            393
        //   389: invokestatic    com/jetbrains/cidr/lang/inspections/OCSimplifyInspection$Simplifier.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   392: athrow         
        //   393: aload           4
        //   395: getstatic       com/jetbrains/cidr/lang/parser/OCTokenTypes.EXCLEQ:Lcom/jetbrains/cidr/lang/parser/OCPunctuatorElementType;
        //   398: if_acmpne       417
        //   401: goto            408
        //   404: invokestatic    com/jetbrains/cidr/lang/inspections/OCSimplifyInspection$Simplifier.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   407: athrow         
        //   408: aload_2        
        //   409: invokestatic    com/jetbrains/cidr/lang/inspections/OCSimplifyInspection$Simplifier.b:(Lcom/jetbrains/cidr/lang/psi/OCElement;)Lcom/jetbrains/cidr/lang/psi/OCExpression;
        //   412: areturn        
        //   413: invokestatic    com/jetbrains/cidr/lang/inspections/OCSimplifyInspection$Simplifier.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   416: athrow         
        //   417: aload           4
        //   419: getstatic       com/jetbrains/cidr/lang/parser/OCTokenTypes.ANDAND:Lcom/jetbrains/cidr/lang/parser/OCPunctuatorElementType;
        //   422: if_acmpeq       440
        //   425: aload           4
        //   427: getstatic       com/jetbrains/cidr/lang/parser/OCTokenTypes.EQEQ:Lcom/jetbrains/cidr/lang/parser/OCPunctuatorElementType;
        //   430: if_acmpne       449
        //   433: goto            440
        //   436: invokestatic    com/jetbrains/cidr/lang/inspections/OCSimplifyInspection$Simplifier.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   439: athrow         
        //   440: aload_2        
        //   441: invokestatic    com/jetbrains/cidr/lang/inspections/OCSimplifyInspection$Simplifier.a:(Lcom/jetbrains/cidr/lang/psi/OCElement;)Lcom/jetbrains/cidr/lang/psi/OCExpression;
        //   444: areturn        
        //   445: invokestatic    com/jetbrains/cidr/lang/inspections/OCSimplifyInspection$Simplifier.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   448: athrow         
        //   449: aload_2        
        //   450: invokestatic    com/jetbrains/cidr/lang/util/OCExpressionEvaluator.evaluateToIntOrBoolean:(Lcom/jetbrains/cidr/lang/psi/OCExpression;)Ljava/lang/Object;
        //   453: aload_2        
        //   454: invokestatic    com/jetbrains/cidr/lang/inspections/OCSimplifyInspection.access$900:(Ljava/lang/Object;Lcom/intellij/psi/PsiElement;)Ljava/lang/Object;
        //   457: astore          8
        //   459: aload_3        
        //   460: invokestatic    com/jetbrains/cidr/lang/util/OCExpressionEvaluator.evaluateToIntOrBoolean:(Lcom/jetbrains/cidr/lang/psi/OCExpression;)Ljava/lang/Object;
        //   463: aload_3        
        //   464: invokestatic    com/jetbrains/cidr/lang/inspections/OCSimplifyInspection.access$900:(Ljava/lang/Object;Lcom/intellij/psi/PsiElement;)Ljava/lang/Object;
        //   467: astore          9
        //   469: aload           8
        //   471: instanceof      Ljava/lang/Boolean;
        //   474: ifeq            493
        //   477: aload           8
        //   479: checkcast       Ljava/lang/Boolean;
        //   482: invokevirtual   java/lang/Boolean.booleanValue:()Z
        //   485: istore          11
        //   487: aload_3        
        //   488: astore          10
        //   490: goto            635
        //   493: aload           9
        //   495: instanceof      Ljava/lang/Boolean;
        //   498: ifeq            517
        //   501: aload           9
        //   503: checkcast       Ljava/lang/Boolean;
        //   506: invokevirtual   java/lang/Boolean.booleanValue:()Z
        //   509: istore          11
        //   511: aload_2        
        //   512: astore          10
        //   514: goto            635
        //   517: aload           8
        //   519: instanceof      Ljava/lang/Number;
        //   522: ifeq            575
        //   525: getstatic       com/jetbrains/cidr/lang/parser/OCTokenTypes.LOGIC_OPERATIONS:Lcom/intellij/psi/tree/TokenSet;
        //   528: aload           4
        //   530: invokevirtual   com/intellij/psi/tree/TokenSet.contains:(Lcom/intellij/psi/tree/IElementType;)Z
        //   533: ifeq            575
        //   536: goto            543
        //   539: invokestatic    com/jetbrains/cidr/lang/inspections/OCSimplifyInspection$Simplifier.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   542: athrow         
        //   543: aload           8
        //   545: invokestatic    com/jetbrains/cidr/lang/util/OCExpressionEvaluator.singAsInC:(Ljava/lang/Object;)I
        //   548: ifeq            566
        //   551: goto            558
        //   554: invokestatic    com/jetbrains/cidr/lang/inspections/OCSimplifyInspection$Simplifier.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   557: athrow         
        //   558: iconst_1       
        //   559: goto            567
        //   562: invokestatic    com/jetbrains/cidr/lang/inspections/OCSimplifyInspection$Simplifier.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   565: athrow         
        //   566: iconst_0       
        //   567: istore          11
        //   569: aload_3        
        //   570: astore          10
        //   572: goto            635
        //   575: aload           9
        //   577: instanceof      Ljava/lang/Number;
        //   580: ifeq            633
        //   583: getstatic       com/jetbrains/cidr/lang/parser/OCTokenTypes.LOGIC_OPERATIONS:Lcom/intellij/psi/tree/TokenSet;
        //   586: aload           4
        //   588: invokevirtual   com/intellij/psi/tree/TokenSet.contains:(Lcom/intellij/psi/tree/IElementType;)Z
        //   591: ifeq            633
        //   594: goto            601
        //   597: invokestatic    com/jetbrains/cidr/lang/inspections/OCSimplifyInspection$Simplifier.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   600: athrow         
        //   601: aload           9
        //   603: invokestatic    com/jetbrains/cidr/lang/util/OCExpressionEvaluator.singAsInC:(Ljava/lang/Object;)I
        //   606: ifeq            624
        //   609: goto            616
        //   612: invokestatic    com/jetbrains/cidr/lang/inspections/OCSimplifyInspection$Simplifier.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   615: athrow         
        //   616: iconst_1       
        //   617: goto            625
        //   620: invokestatic    com/jetbrains/cidr/lang/inspections/OCSimplifyInspection$Simplifier.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   623: athrow         
        //   624: iconst_0       
        //   625: istore          11
        //   627: aload_2        
        //   628: astore          10
        //   630: goto            635
        //   633: aconst_null    
        //   634: areturn        
        //   635: aload           4
        //   637: getstatic       com/jetbrains/cidr/lang/parser/OCTokenTypes.EQEQ:Lcom/jetbrains/cidr/lang/parser/OCPunctuatorElementType;
        //   640: if_acmpeq       658
        //   643: aload           4
        //   645: getstatic       com/jetbrains/cidr/lang/parser/OCTokenTypes.EXCLEQ:Lcom/jetbrains/cidr/lang/parser/OCPunctuatorElementType;
        //   648: if_acmpne       670
        //   651: goto            658
        //   654: invokestatic    com/jetbrains/cidr/lang/inspections/OCSimplifyInspection$Simplifier.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   657: athrow         
        //   658: aload_0        
        //   659: iconst_1       
        //   660: putfield        com/jetbrains/cidr/lang/inspections/OCSimplifyInspection$Simplifier.equalToYesNoUsed:Z
        //   663: goto            670
        //   666: invokestatic    com/jetbrains/cidr/lang/inspections/OCSimplifyInspection$Simplifier.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   669: athrow         
        //   670: iload           11
        //   672: aload_2        
        //   673: aload           10
        //   675: aload           4
        //   677: invokestatic    com/jetbrains/cidr/lang/inspections/OCSimplifyInspection$Simplifier.a:(ZLcom/jetbrains/cidr/lang/psi/OCExpression;Lcom/jetbrains/cidr/lang/psi/OCExpression;Lcom/jetbrains/cidr/lang/parser/OCElementType;)Lcom/jetbrains/cidr/lang/psi/OCExpression;
        //   680: areturn        
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                                
        //  -----  -----  -----  -----  ------------------------------------
        //  0      40     40     44     Ljava/lang/IllegalArgumentException;
        //  66     74     77     81     Ljava/lang/IllegalArgumentException;
        //  70     83     83     87     Ljava/lang/IllegalArgumentException;
        //  118    162    165    169    Ljava/lang/IllegalArgumentException;
        //  140    171    171    175    Ljava/lang/IllegalArgumentException;
        //  175    193    196    200    Ljava/lang/IllegalArgumentException;
        //  186    208    211    215    Ljava/lang/IllegalArgumentException;
        //  200    223    226    230    Ljava/lang/IllegalArgumentException;
        //  215    238    241    245    Ljava/lang/IllegalArgumentException;
        //  230    253    253    257    Ljava/lang/IllegalArgumentException;
        //  257    273    276    280    Ljava/lang/IllegalArgumentException;
        //  265    285    285    289    Ljava/lang/IllegalArgumentException;
        //  289    305    308    312    Ljava/lang/IllegalArgumentException;
        //  297    317    317    321    Ljava/lang/IllegalArgumentException;
        //  321    334    334    338    Ljava/lang/IllegalArgumentException;
        //  338    356    359    363    Ljava/lang/IllegalArgumentException;
        //  349    371    374    378    Ljava/lang/IllegalArgumentException;
        //  363    386    389    393    Ljava/lang/IllegalArgumentException;
        //  378    401    404    408    Ljava/lang/IllegalArgumentException;
        //  393    413    413    417    Ljava/lang/IllegalArgumentException;
        //  417    433    436    440    Ljava/lang/IllegalArgumentException;
        //  425    445    445    449    Ljava/lang/IllegalArgumentException;
        //  517    536    539    543    Ljava/lang/IllegalArgumentException;
        //  525    551    554    558    Ljava/lang/IllegalArgumentException;
        //  543    562    562    566    Ljava/lang/IllegalArgumentException;
        //  575    594    597    601    Ljava/lang/IllegalArgumentException;
        //  583    609    612    616    Ljava/lang/IllegalArgumentException;
        //  601    620    620    624    Ljava/lang/IllegalArgumentException;
        //  635    651    654    658    Ljava/lang/IllegalArgumentException;
        //  643    663    666    670    Ljava/lang/IllegalArgumentException;
        // 
        // The error that occurred was:
        // 
        // java.lang.IllegalStateException: Expression is linked from several locations: Label_0200:
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
    
    private static OCExpression a(final boolean p0, final OCExpression p1, final OCExpression p2, final OCElementType p3) {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     0: new             Lcom/jetbrains/cidr/lang/symbols/OCResolveContext;
        //     3: dup            
        //     4: aload_2        
        //     5: invokespecial   com/jetbrains/cidr/lang/symbols/OCResolveContext.<init>:(Lcom/intellij/psi/PsiElement;)V
        //     8: astore          4
        //    10: aload_2        
        //    11: aload           4
        //    13: iconst_2       
        //    14: anewarray       Ljava/lang/Class;
        //    17: dup            
        //    18: iconst_0       
        //    19: ldc             Lcom/jetbrains/cidr/lang/types/OCMagicType;.class
        //    21: aastore        
        //    22: dup            
        //    23: iconst_1       
        //    24: ldc             Lcom/jetbrains/cidr/lang/types/OCStructType;.class
        //    26: aastore        
        //    27: invokestatic    com/jetbrains/cidr/lang/types/OCTypeUtils.isInstanceOfType:(Lcom/jetbrains/cidr/lang/psi/OCExpression;Lcom/jetbrains/cidr/lang/symbols/OCResolveContext;[Ljava/lang/Class;)Z
        //    30: ifeq            39
        //    33: aconst_null    
        //    34: areturn        
        //    35: invokestatic    com/jetbrains/cidr/lang/inspections/OCSimplifyInspection$Simplifier.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //    38: athrow         
        //    39: iload_0        
        //    40: ifeq            186
        //    43: aload_3        
        //    44: getstatic       com/jetbrains/cidr/lang/parser/OCTokenTypes.ANDAND:Lcom/jetbrains/cidr/lang/parser/OCPunctuatorElementType;
        //    47: if_acmpeq       85
        //    50: goto            57
        //    53: invokestatic    com/jetbrains/cidr/lang/inspections/OCSimplifyInspection$Simplifier.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //    56: athrow         
        //    57: aload_3        
        //    58: getstatic       com/jetbrains/cidr/lang/parser/OCTokenTypes.AND:Lcom/jetbrains/cidr/lang/parser/OCPunctuatorElementType;
        //    61: if_acmpeq       85
        //    64: goto            71
        //    67: invokestatic    com/jetbrains/cidr/lang/inspections/OCSimplifyInspection$Simplifier.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //    70: athrow         
        //    71: aload_3        
        //    72: getstatic       com/jetbrains/cidr/lang/parser/OCTokenTypes.EQEQ:Lcom/jetbrains/cidr/lang/parser/OCPunctuatorElementType;
        //    75: if_acmpne       97
        //    78: goto            85
        //    81: invokestatic    com/jetbrains/cidr/lang/inspections/OCSimplifyInspection$Simplifier.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //    84: athrow         
        //    85: aload_2        
        //    86: invokestatic    com/jetbrains/cidr/lang/inspections/OCSimplifyInspection.access$1000:(Lcom/jetbrains/cidr/lang/psi/OCExpression;)Lcom/jetbrains/cidr/lang/psi/OCExpression;
        //    89: invokestatic    com/jetbrains/cidr/lang/inspections/OCSimplifyInspection$Simplifier.a:(Lcom/jetbrains/cidr/lang/psi/OCExpression;)Lcom/jetbrains/cidr/lang/psi/OCExpression;
        //    92: areturn        
        //    93: invokestatic    com/jetbrains/cidr/lang/inspections/OCSimplifyInspection$Simplifier.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //    96: athrow         
        //    97: aload_3        
        //    98: getstatic       com/jetbrains/cidr/lang/parser/OCTokenTypes.OROR:Lcom/jetbrains/cidr/lang/parser/OCPunctuatorElementType;
        //   101: if_acmpeq       118
        //   104: aload_3        
        //   105: getstatic       com/jetbrains/cidr/lang/parser/OCTokenTypes.OR:Lcom/jetbrains/cidr/lang/parser/OCPunctuatorElementType;
        //   108: if_acmpne       153
        //   111: goto            118
        //   114: invokestatic    com/jetbrains/cidr/lang/inspections/OCSimplifyInspection$Simplifier.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   117: athrow         
        //   118: aload_2        
        //   119: aload_1        
        //   120: if_acmpne       144
        //   123: goto            130
        //   126: invokestatic    com/jetbrains/cidr/lang/inspections/OCSimplifyInspection$Simplifier.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   129: athrow         
        //   130: aload_1        
        //   131: invokestatic    com/jetbrains/cidr/lang/util/OCCodeInsightUtil.hasSideEffects:(Lcom/jetbrains/cidr/lang/psi/OCElement;)Z
        //   134: ifne            153
        //   137: goto            144
        //   140: invokestatic    com/jetbrains/cidr/lang/inspections/OCSimplifyInspection$Simplifier.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   143: athrow         
        //   144: aload_1        
        //   145: invokestatic    com/jetbrains/cidr/lang/inspections/OCSimplifyInspection$Simplifier.b:(Lcom/jetbrains/cidr/lang/psi/OCElement;)Lcom/jetbrains/cidr/lang/psi/OCExpression;
        //   148: areturn        
        //   149: invokestatic    com/jetbrains/cidr/lang/inspections/OCSimplifyInspection$Simplifier.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   152: athrow         
        //   153: aload_3        
        //   154: getstatic       com/jetbrains/cidr/lang/parser/OCTokenTypes.XOR:Lcom/jetbrains/cidr/lang/parser/OCPunctuatorElementType;
        //   157: if_acmpeq       174
        //   160: aload_3        
        //   161: getstatic       com/jetbrains/cidr/lang/parser/OCTokenTypes.EXCLEQ:Lcom/jetbrains/cidr/lang/parser/OCPunctuatorElementType;
        //   164: if_acmpne       308
        //   167: goto            174
        //   170: invokestatic    com/jetbrains/cidr/lang/inspections/OCSimplifyInspection$Simplifier.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   173: athrow         
        //   174: aload_2        
        //   175: invokestatic    com/jetbrains/cidr/lang/inspections/OCSimplifyInspection$Simplifier.a:(Lcom/jetbrains/cidr/lang/psi/OCExpression;)Lcom/jetbrains/cidr/lang/psi/OCExpression;
        //   178: invokestatic    com/jetbrains/cidr/lang/inspections/OCSimplifyInspection$Simplifier.b:(Lcom/jetbrains/cidr/lang/psi/OCExpression;)Lcom/jetbrains/cidr/lang/psi/OCExpression;
        //   181: areturn        
        //   182: invokestatic    com/jetbrains/cidr/lang/inspections/OCSimplifyInspection$Simplifier.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   185: athrow         
        //   186: aload_3        
        //   187: getstatic       com/jetbrains/cidr/lang/parser/OCTokenTypes.ANDAND:Lcom/jetbrains/cidr/lang/parser/OCPunctuatorElementType;
        //   190: if_acmpeq       207
        //   193: aload_3        
        //   194: getstatic       com/jetbrains/cidr/lang/parser/OCTokenTypes.AND:Lcom/jetbrains/cidr/lang/parser/OCPunctuatorElementType;
        //   197: if_acmpne       242
        //   200: goto            207
        //   203: invokestatic    com/jetbrains/cidr/lang/inspections/OCSimplifyInspection$Simplifier.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   206: athrow         
        //   207: aload_2        
        //   208: aload_1        
        //   209: if_acmpne       233
        //   212: goto            219
        //   215: invokestatic    com/jetbrains/cidr/lang/inspections/OCSimplifyInspection$Simplifier.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   218: athrow         
        //   219: aload_1        
        //   220: invokestatic    com/jetbrains/cidr/lang/util/OCCodeInsightUtil.hasSideEffects:(Lcom/jetbrains/cidr/lang/psi/OCElement;)Z
        //   223: ifne            242
        //   226: goto            233
        //   229: invokestatic    com/jetbrains/cidr/lang/inspections/OCSimplifyInspection$Simplifier.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   232: athrow         
        //   233: aload_1        
        //   234: invokestatic    com/jetbrains/cidr/lang/inspections/OCSimplifyInspection$Simplifier.a:(Lcom/jetbrains/cidr/lang/psi/OCElement;)Lcom/jetbrains/cidr/lang/psi/OCExpression;
        //   237: areturn        
        //   238: invokestatic    com/jetbrains/cidr/lang/inspections/OCSimplifyInspection$Simplifier.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   241: athrow         
        //   242: aload_3        
        //   243: getstatic       com/jetbrains/cidr/lang/parser/OCTokenTypes.OROR:Lcom/jetbrains/cidr/lang/parser/OCPunctuatorElementType;
        //   246: if_acmpeq       277
        //   249: aload_3        
        //   250: getstatic       com/jetbrains/cidr/lang/parser/OCTokenTypes.XOR:Lcom/jetbrains/cidr/lang/parser/OCPunctuatorElementType;
        //   253: if_acmpeq       277
        //   256: goto            263
        //   259: invokestatic    com/jetbrains/cidr/lang/inspections/OCSimplifyInspection$Simplifier.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   262: athrow         
        //   263: aload_3        
        //   264: getstatic       com/jetbrains/cidr/lang/parser/OCTokenTypes.EXCLEQ:Lcom/jetbrains/cidr/lang/parser/OCPunctuatorElementType;
        //   267: if_acmpne       289
        //   270: goto            277
        //   273: invokestatic    com/jetbrains/cidr/lang/inspections/OCSimplifyInspection$Simplifier.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   276: athrow         
        //   277: aload_2        
        //   278: invokestatic    com/jetbrains/cidr/lang/inspections/OCSimplifyInspection.access$1000:(Lcom/jetbrains/cidr/lang/psi/OCExpression;)Lcom/jetbrains/cidr/lang/psi/OCExpression;
        //   281: invokestatic    com/jetbrains/cidr/lang/inspections/OCSimplifyInspection$Simplifier.a:(Lcom/jetbrains/cidr/lang/psi/OCExpression;)Lcom/jetbrains/cidr/lang/psi/OCExpression;
        //   284: areturn        
        //   285: invokestatic    com/jetbrains/cidr/lang/inspections/OCSimplifyInspection$Simplifier.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   288: athrow         
        //   289: aload_3        
        //   290: getstatic       com/jetbrains/cidr/lang/parser/OCTokenTypes.EQEQ:Lcom/jetbrains/cidr/lang/parser/OCPunctuatorElementType;
        //   293: if_acmpne       308
        //   296: aload_2        
        //   297: invokestatic    com/jetbrains/cidr/lang/inspections/OCSimplifyInspection$Simplifier.a:(Lcom/jetbrains/cidr/lang/psi/OCExpression;)Lcom/jetbrains/cidr/lang/psi/OCExpression;
        //   300: invokestatic    com/jetbrains/cidr/lang/inspections/OCSimplifyInspection$Simplifier.b:(Lcom/jetbrains/cidr/lang/psi/OCExpression;)Lcom/jetbrains/cidr/lang/psi/OCExpression;
        //   303: areturn        
        //   304: invokestatic    com/jetbrains/cidr/lang/inspections/OCSimplifyInspection$Simplifier.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   307: athrow         
        //   308: aconst_null    
        //   309: areturn        
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                                
        //  -----  -----  -----  -----  ------------------------------------
        //  10     35     35     39     Ljava/lang/IllegalArgumentException;
        //  39     50     53     57     Ljava/lang/IllegalArgumentException;
        //  43     64     67     71     Ljava/lang/IllegalArgumentException;
        //  57     78     81     85     Ljava/lang/IllegalArgumentException;
        //  71     93     93     97     Ljava/lang/IllegalArgumentException;
        //  97     111    114    118    Ljava/lang/IllegalArgumentException;
        //  104    123    126    130    Ljava/lang/IllegalArgumentException;
        //  118    137    140    144    Ljava/lang/IllegalArgumentException;
        //  130    149    149    153    Ljava/lang/IllegalArgumentException;
        //  153    167    170    174    Ljava/lang/IllegalArgumentException;
        //  160    182    182    186    Ljava/lang/IllegalArgumentException;
        //  186    200    203    207    Ljava/lang/IllegalArgumentException;
        //  193    212    215    219    Ljava/lang/IllegalArgumentException;
        //  207    226    229    233    Ljava/lang/IllegalArgumentException;
        //  219    238    238    242    Ljava/lang/IllegalArgumentException;
        //  242    256    259    263    Ljava/lang/IllegalArgumentException;
        //  249    270    273    277    Ljava/lang/IllegalArgumentException;
        //  263    285    285    289    Ljava/lang/IllegalArgumentException;
        //  289    304    304    308    Ljava/lang/IllegalArgumentException;
        // 
        // The error that occurred was:
        // 
        // java.lang.IllegalStateException: Expression is linked from several locations: Label_0057:
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
    private static OCExpression a(@NotNull final OCUnaryExpression ocUnaryExpression) {
        try {
            if (ocUnaryExpression == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "expression", "com/jetbrains/cidr/lang/inspections/OCSimplifyInspection$Simplifier", "trySimplifyUnaryExpr"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        final OCExpression operand = ocUnaryExpression.getOperand();
        Label_0074: {
            try {
                if (operand == null) {
                    break Label_0074;
                }
                final OCUnaryExpression ocUnaryExpression2 = ocUnaryExpression;
                final OCElementType ocElementType = ocUnaryExpression2.getOperationSign();
                final OCPunctuatorElementType ocPunctuatorElementType = OCTokenTypes.EXCL;
                if (ocElementType != ocPunctuatorElementType) {
                    break Label_0074;
                }
                break Label_0074;
            }
            catch (IllegalArgumentException ex2) {
                throw a(ex2);
            }
            try {
                final OCUnaryExpression ocUnaryExpression2 = ocUnaryExpression;
                final OCElementType ocElementType = ocUnaryExpression2.getOperationSign();
                final OCPunctuatorElementType ocPunctuatorElementType = OCTokenTypes.EXCL;
                if (ocElementType != ocPunctuatorElementType) {
                    return null;
                }
            }
            catch (IllegalArgumentException ex3) {
                throw a(ex3);
            }
        }
        final Number n = (Number)OCSimplifyInspection.access$900(OCExpressionEvaluator.evaluate(operand), (PsiElement)operand);
        Label_0126: {
            Label_0110: {
                try {
                    if (n == null) {
                        break Label_0126;
                    }
                    final Number n2 = n;
                    final int n3 = OCExpressionEvaluator.singAsInC(n2);
                    if (n3 != 0) {
                        break Label_0110;
                    }
                    return b((OCElement)operand);
                }
                catch (IllegalArgumentException ex4) {
                    throw a(ex4);
                }
                try {
                    final Number n2 = n;
                    final int n3 = OCExpressionEvaluator.singAsInC(n2);
                    if (n3 != 0) {
                        return a((OCElement)operand);
                    }
                }
                catch (IllegalArgumentException ex5) {
                    throw a(ex5);
                }
            }
            return b((OCElement)operand);
        }
        final OCExpression diveIntoParentheses = OCParenthesesUtils.diveIntoParentheses(operand);
        Label_0149: {
            try {
                if (diveIntoParentheses == null) {
                    break Label_0149;
                }
                final OCExpression ocExpression2 = diveIntoParentheses;
                final boolean b = OCElementUtil.isPartOfMacroSubstitution((PsiElement)ocExpression2);
                if (b) {
                    break Label_0149;
                }
                break Label_0149;
            }
            catch (IllegalArgumentException ex6) {
                throw a(ex6);
            }
            try {
                final OCExpression ocExpression2 = diveIntoParentheses;
                final boolean b = OCElementUtil.isPartOfMacroSubstitution((PsiElement)ocExpression2);
                if (b) {
                    return null;
                }
            }
            catch (IllegalArgumentException ex7) {
                throw a(ex7);
            }
        }
        final OCResolveContext ocResolveContext = new OCResolveContext((PsiElement)diveIntoParentheses);
        if (diveIntoParentheses instanceof OCUnaryExpression) {
            final OCUnaryExpression ocUnaryExpression3 = (OCUnaryExpression)diveIntoParentheses;
            final boolean instanceOfType = OCTypeUtils.isInstanceOfType(ocUnaryExpression3.getOperand(), ocResolveContext, OCMagicType.class, OCStructType.class);
            Label_0229: {
                try {
                    if (ocUnaryExpression3.getOperationSign() != OCTokenTypes.EXCL) {
                        return null;
                    }
                    final boolean b2 = instanceOfType;
                    if (!b2) {
                        break Label_0229;
                    }
                    return null;
                }
                catch (IllegalArgumentException ex8) {
                    throw a(ex8);
                }
                try {
                    final boolean b2 = instanceOfType;
                    if (!b2) {
                        return OCSimplifyInspection.access$1000(a(ocUnaryExpression3.getOperand()));
                    }
                }
                catch (IllegalArgumentException ex9) {
                    throw a(ex9);
                }
            }
        }
        else {
            try {
                if (!(diveIntoParentheses instanceof OCBinaryExpression) || OCSimplifyInspection.access$400(diveIntoParentheses)) {
                    return null;
                }
            }
            catch (IllegalArgumentException ex10) {
                throw a(ex10);
            }
            final OCBinaryExpression ocBinaryExpression = (OCBinaryExpression)diveIntoParentheses;
            boolean b4 = false;
            Label_0349: {
                Label_0340: {
                    try {
                        if (OCTypeUtils.isInstanceOfType(ocBinaryExpression.getLeft(), ocResolveContext, OCMagicType.class, OCStructType.class)) {
                            break Label_0340;
                        }
                        final OCBinaryExpression ocBinaryExpression2 = ocBinaryExpression;
                        final OCExpression ocExpression3 = ocBinaryExpression2.getRight();
                        final OCResolveContext ocResolveContext2 = ocResolveContext;
                        final int n4 = 2;
                        final Class[] array = new Class[n4];
                        final int n5 = 0;
                        final Class<OCMagicType> clazz = OCMagicType.class;
                        array[n5] = clazz;
                        final int n6 = 1;
                        final Class<OCStructType> clazz2 = OCStructType.class;
                        array[n6] = clazz2;
                        final boolean b3 = OCTypeUtils.isInstanceOfType(ocExpression3, ocResolveContext2, (Class<? extends OCType>[])array);
                        if (b3) {
                            break Label_0340;
                        }
                        break Label_0340;
                    }
                    catch (IllegalArgumentException ex11) {
                        throw a(ex11);
                    }
                    try {
                        final OCBinaryExpression ocBinaryExpression2 = ocBinaryExpression;
                        final OCExpression ocExpression3 = ocBinaryExpression2.getRight();
                        final OCResolveContext ocResolveContext2 = ocResolveContext;
                        final int n4 = 2;
                        final Class[] array = new Class[n4];
                        final int n5 = 0;
                        final Class<OCMagicType> clazz = OCMagicType.class;
                        array[n5] = clazz;
                        final int n6 = 1;
                        final Class<OCStructType> clazz2 = OCStructType.class;
                        array[n6] = clazz2;
                        final boolean b3 = OCTypeUtils.isInstanceOfType(ocExpression3, ocResolveContext2, (Class<? extends OCType>[])array);
                        if (b3) {
                            b4 = true;
                            break Label_0349;
                        }
                    }
                    catch (IllegalArgumentException ex12) {
                        throw a(ex12);
                    }
                }
                b4 = false;
            }
            final boolean b5 = b4;
            final OCElementType oppositeOperator = OCCodeInsightUtil.getOppositeOperator(ocBinaryExpression.getOperationSign());
            Label_0380: {
                try {
                    if (oppositeOperator == null) {
                        return null;
                    }
                    final boolean b6 = b5;
                    if (!b6) {
                        break Label_0380;
                    }
                    return null;
                }
                catch (IllegalArgumentException ex13) {
                    throw a(ex13);
                }
                try {
                    final boolean b6 = b5;
                    if (!b6) {
                        return a(ocBinaryExpression.getLeft(), ocBinaryExpression.getRight(), oppositeOperator);
                    }
                }
                catch (IllegalArgumentException ex14) {
                    throw a(ex14);
                }
            }
        }
        return null;
    }
    
    @Nullable
    private static OCExpression a(OCExpression access$1000, final int n) {
        Label_0024: {
            try {
                if (access$1000 == null) {
                    return access$1000;
                }
                final OCExpression ocExpression = access$1000;
                final boolean b = OCElementUtil.isPartOfMacroSubstitution((PsiElement)ocExpression);
                if (b) {
                    return access$1000;
                }
                break Label_0024;
            }
            catch (IllegalArgumentException ex) {
                throw a(ex);
            }
            try {
                final OCExpression ocExpression = access$1000;
                final boolean b = OCElementUtil.isPartOfMacroSubstitution((PsiElement)ocExpression);
                if (b) {
                    return access$1000;
                }
            }
            catch (IllegalArgumentException ex2) {
                throw a(ex2);
            }
        }
        access$1000 = OCSimplifyInspection.access$1000(access$1000);
        Label_0049: {
            try {
                if (access$1000 == null) {
                    return access$1000;
                }
                final OCExpression ocExpression2 = access$1000;
                final boolean b2 = false;
                final int n2 = OCParenthesesUtils.getPrecedence(ocExpression2, b2);
                final int n3 = n;
                if (n2 > n3) {
                    break Label_0049;
                }
                return access$1000;
            }
            catch (IllegalArgumentException ex3) {
                throw a(ex3);
            }
            try {
                final OCExpression ocExpression2 = access$1000;
                final boolean b2 = false;
                final int n2 = OCParenthesesUtils.getPrecedence(ocExpression2, b2);
                final int n3 = n;
                if (n2 > n3) {
                    return OCParenthesesUtils.appendParentheses(access$1000);
                }
            }
            catch (IllegalArgumentException ex4) {
                throw a(ex4);
            }
        }
        return access$1000;
    }
    
    @Nullable
    private static OCExpression a(@Nullable OCExpression a, @Nullable OCExpression a2, @Nullable final OCElementType ocElementType) {
        Label_0015: {
            try {
                if (a == null) {
                    break Label_0015;
                }
                final OCExpression ocExpression = a2;
                if (ocExpression == null) {
                    break Label_0015;
                }
                break Label_0015;
            }
            catch (IllegalArgumentException ex) {
                throw a(ex);
            }
            try {
                final OCExpression ocExpression = a2;
                if (ocExpression == null) {
                    return null;
                }
            }
            catch (IllegalArgumentException ex2) {
                throw a(ex2);
            }
        }
        final int precedence = OCParenthesesUtils.getPrecedence(ocElementType);
        a = a(a, precedence);
        a2 = a(a2, precedence);
        return OCSimplifyInspection.access$1000(OCElementFactory.binaryExpression((PsiElement)a, (PsiElement)a2, ocElementType));
    }
    
    @Nullable
    private static OCExpression b(@Nullable OCExpression a) {
        try {
            if (a == null) {
                return null;
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        a = a(a, OCParenthesesUtils.getPrecedence(OCTokenTypes.EXCL));
        return OCSimplifyInspection.access$1000(OCElementFactory.unaryExpression((PsiElement)a, OCTokenTypes.EXCL));
    }
    
    private static OCExpression b(final OCElement ocElement) {
        return OCElementFactory.booleanConstant(true, (PsiElement)ocElement);
    }
    
    private static OCExpression a(final OCElement ocElement) {
        return OCElementFactory.booleanConstant(false, (PsiElement)ocElement);
    }
    
    @Nullable
    private static OCExpression a(final OCExpression p0) {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     0: aload_0        
        //     1: ifnonnull       10
        //     4: aconst_null    
        //     5: areturn        
        //     6: invokestatic    com/jetbrains/cidr/lang/inspections/OCSimplifyInspection$Simplifier.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //     9: athrow         
        //    10: aload_0        
        //    11: invokeinterface com/jetbrains/cidr/lang/psi/OCExpression.getResolvedType:()Lcom/jetbrains/cidr/lang/types/OCType;
        //    16: astore_1       
        //    17: aload_1        
        //    18: aload_0        
        //    19: invokestatic    com/jetbrains/cidr/lang/types/OCIntType.isBool:(Lcom/jetbrains/cidr/lang/types/OCType;Lcom/intellij/psi/PsiElement;)Z
        //    22: ifeq            31
        //    25: aload_0        
        //    26: areturn        
        //    27: invokestatic    com/jetbrains/cidr/lang/inspections/OCSimplifyInspection$Simplifier.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //    30: athrow         
        //    31: aload_1        
        //    32: instanceof      Lcom/jetbrains/cidr/lang/types/OCStructType;
        //    35: ifne            66
        //    38: aload_1        
        //    39: instanceof      Lcom/jetbrains/cidr/lang/types/OCCppReferenceType;
        //    42: ifne            66
        //    45: goto            52
        //    48: invokestatic    com/jetbrains/cidr/lang/inspections/OCSimplifyInspection$Simplifier.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //    51: athrow         
        //    52: aload_1        
        //    53: instanceof      Lcom/jetbrains/cidr/lang/types/OCBlockPointerType;
        //    56: ifeq            72
        //    59: goto            66
        //    62: invokestatic    com/jetbrains/cidr/lang/inspections/OCSimplifyInspection$Simplifier.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //    65: athrow         
        //    66: aconst_null    
        //    67: areturn        
        //    68: invokestatic    com/jetbrains/cidr/lang/inspections/OCSimplifyInspection$Simplifier.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //    71: athrow         
        //    72: aload_0        
        //    73: aload_1        
        //    74: aload_0        
        //    75: invokevirtual   com/jetbrains/cidr/lang/types/OCType.getDefaultValue:(Lcom/intellij/psi/PsiElement;)Ljava/lang/String;
        //    78: aload_0        
        //    79: iconst_0       
        //    80: invokestatic    com/jetbrains/cidr/lang/util/OCElementFactory.expressionFromText:(Ljava/lang/String;Lcom/intellij/psi/PsiElement;Z)Lcom/jetbrains/cidr/lang/psi/OCExpression;
        //    83: getstatic       com/jetbrains/cidr/lang/parser/OCTokenTypes.EXCLEQ:Lcom/jetbrains/cidr/lang/parser/OCPunctuatorElementType;
        //    86: invokestatic    com/jetbrains/cidr/lang/inspections/OCSimplifyInspection$Simplifier.a:(Lcom/jetbrains/cidr/lang/psi/OCExpression;Lcom/jetbrains/cidr/lang/psi/OCExpression;Lcom/jetbrains/cidr/lang/parser/OCElementType;)Lcom/jetbrains/cidr/lang/psi/OCExpression;
        //    89: areturn        
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                                
        //  -----  -----  -----  -----  ------------------------------------
        //  0      6      6      10     Ljava/lang/IllegalArgumentException;
        //  17     27     27     31     Ljava/lang/IllegalArgumentException;
        //  31     45     48     52     Ljava/lang/IllegalArgumentException;
        //  38     59     62     66     Ljava/lang/IllegalArgumentException;
        //  52     68     68     72     Ljava/lang/IllegalArgumentException;
        // 
        // The error that occurred was:
        // 
        // java.lang.IllegalStateException: Expression is linked from several locations: Label_0052:
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
