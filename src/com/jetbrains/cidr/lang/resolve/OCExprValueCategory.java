// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.lang.resolve;

import org.jetbrains.annotations.NotNull;
import com.jetbrains.cidr.lang.types.OCTypeOwner;
import com.intellij.psi.PsiElement;
import com.jetbrains.cidr.lang.symbols.OCResolveContext;
import org.jetbrains.annotations.Nullable;
import com.jetbrains.cidr.lang.psi.OCExpression;

public enum OCExprValueCategory
{
    LValue, 
    PRValue, 
    XValue;
    
    public boolean isLValue() {
        try {
            if (this == OCExprValueCategory.LValue) {
                return true;
            }
        }
        catch (IllegalStateException ex) {
            throw a(ex);
        }
        return false;
    }
    
    public boolean isRValue() {
        Label_0021: {
            try {
                if (this == OCExprValueCategory.PRValue) {
                    break Label_0021;
                }
                final OCExprValueCategory ocExprValueCategory = this;
                final OCExprValueCategory ocExprValueCategory2 = OCExprValueCategory.XValue;
                if (ocExprValueCategory == ocExprValueCategory2) {
                    break Label_0021;
                }
                return false;
            }
            catch (IllegalStateException ex) {
                throw a(ex);
            }
            try {
                final OCExprValueCategory ocExprValueCategory = this;
                final OCExprValueCategory ocExprValueCategory2 = OCExprValueCategory.XValue;
                if (ocExprValueCategory == ocExprValueCategory2) {
                    return true;
                }
            }
            catch (IllegalStateException ex2) {
                throw a(ex2);
            }
        }
        return false;
    }
    
    public boolean isGLValue() {
        Label_0021: {
            try {
                if (this == OCExprValueCategory.LValue) {
                    break Label_0021;
                }
                final OCExprValueCategory ocExprValueCategory = this;
                final OCExprValueCategory ocExprValueCategory2 = OCExprValueCategory.XValue;
                if (ocExprValueCategory == ocExprValueCategory2) {
                    break Label_0021;
                }
                return false;
            }
            catch (IllegalStateException ex) {
                throw a(ex);
            }
            try {
                final OCExprValueCategory ocExprValueCategory = this;
                final OCExprValueCategory ocExprValueCategory2 = OCExprValueCategory.XValue;
                if (ocExprValueCategory == ocExprValueCategory2) {
                    return true;
                }
            }
            catch (IllegalStateException ex2) {
                throw a(ex2);
            }
        }
        return false;
    }
    
    @NotNull
    public static OCExprValueCategory classify(@Nullable final OCExpression ocExpression) {
        OCExprValueCategory classify;
        try {
            classify = classify(ocExpression, new OCResolveContext((PsiElement)ocExpression));
            if (classify == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/resolve/OCExprValueCategory", "classify"));
            }
        }
        catch (IllegalStateException ex) {
            throw a(ex);
        }
        return classify;
    }
    
    @NotNull
    public static OCExprValueCategory classify(@Nullable final OCTypeOwner p0, @NotNull final OCResolveContext p1) {
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
        //    18: ldc             "context"
        //    20: aastore        
        //    21: dup            
        //    22: ldc             1
        //    24: ldc             "com/jetbrains/cidr/lang/resolve/OCExprValueCategory"
        //    26: aastore        
        //    27: dup            
        //    28: ldc             2
        //    30: ldc             "classify"
        //    32: aastore        
        //    33: invokestatic    java/lang/String.format:(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
        //    36: invokespecial   java/lang/IllegalArgumentException.<init>:(Ljava/lang/String;)V
        //    39: athrow         
        //    40: invokestatic    com/jetbrains/cidr/lang/resolve/OCExprValueCategory.a:(Ljava/lang/IllegalStateException;)Ljava/lang/IllegalStateException;
        //    43: athrow         
        //    44: aload_0        
        //    45: instanceof      Lcom/jetbrains/cidr/lang/psi/OCExpression;
        //    48: ifeq            212
        //    51: aload_0        
        //    52: checkcast       Lcom/jetbrains/cidr/lang/psi/OCExpression;
        //    55: ldc             Lcom/jetbrains/cidr/lang/psi/OCForeachStatement;.class
        //    57: invokestatic    com/intellij/psi/util/PsiTreeUtil.getParentOfType:(Lcom/intellij/psi/PsiElement;Ljava/lang/Class;)Lcom/intellij/psi/PsiElement;
        //    60: checkcast       Lcom/jetbrains/cidr/lang/psi/OCForeachStatement;
        //    63: astore_2       
        //    64: aload_2        
        //    65: ifnull          150
        //    68: aload_2        
        //    69: invokeinterface com/jetbrains/cidr/lang/psi/OCForeachStatement.isCpp11Foreach:()Z
        //    74: ifeq            150
        //    77: goto            84
        //    80: invokestatic    com/jetbrains/cidr/lang/resolve/OCExprValueCategory.a:(Ljava/lang/IllegalStateException;)Ljava/lang/IllegalStateException;
        //    83: athrow         
        //    84: aload_0        
        //    85: aload_2        
        //    86: invokeinterface com/jetbrains/cidr/lang/psi/OCForeachStatement.getCollectionExpression:()Lcom/jetbrains/cidr/lang/psi/OCExpression;
        //    91: if_acmpne       150
        //    94: goto            101
        //    97: invokestatic    com/jetbrains/cidr/lang/resolve/OCExprValueCategory.a:(Ljava/lang/IllegalStateException;)Ljava/lang/IllegalStateException;
        //   100: athrow         
        //   101: getstatic       com/jetbrains/cidr/lang/resolve/OCExprValueCategory.LValue:Lcom/jetbrains/cidr/lang/resolve/OCExprValueCategory;
        //   104: dup            
        //   105: ifnonnull       149
        //   108: goto            115
        //   111: invokestatic    com/jetbrains/cidr/lang/resolve/OCExprValueCategory.a:(Ljava/lang/IllegalStateException;)Ljava/lang/IllegalStateException;
        //   114: athrow         
        //   115: new             Ljava/lang/IllegalStateException;
        //   118: dup            
        //   119: ldc             "@NotNull method %s.%s must not return null"
        //   121: ldc             2
        //   123: anewarray       Ljava/lang/Object;
        //   126: dup            
        //   127: ldc             0
        //   129: ldc             "com/jetbrains/cidr/lang/resolve/OCExprValueCategory"
        //   131: aastore        
        //   132: dup            
        //   133: ldc             1
        //   135: ldc             "classify"
        //   137: aastore        
        //   138: invokestatic    java/lang/String.format:(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
        //   141: invokespecial   java/lang/IllegalStateException.<init>:(Ljava/lang/String;)V
        //   144: athrow         
        //   145: invokestatic    com/jetbrains/cidr/lang/resolve/OCExprValueCategory.a:(Ljava/lang/IllegalStateException;)Ljava/lang/IllegalStateException;
        //   148: athrow         
        //   149: areturn        
        //   150: new             Lcom/jetbrains/cidr/lang/resolve/OCPsiExprClassifierVisitor;
        //   153: dup            
        //   154: aload_1        
        //   155: invokespecial   com/jetbrains/cidr/lang/resolve/OCPsiExprClassifierVisitor.<init>:(Lcom/jetbrains/cidr/lang/symbols/OCResolveContext;)V
        //   158: astore_3       
        //   159: aload_0        
        //   160: checkcast       Lcom/jetbrains/cidr/lang/psi/OCExpression;
        //   163: aload_3        
        //   164: invokeinterface com/jetbrains/cidr/lang/psi/OCExpression.accept:(Lcom/intellij/psi/PsiElementVisitor;)V
        //   169: aload_3        
        //   170: invokevirtual   com/jetbrains/cidr/lang/resolve/OCPsiExprClassifierVisitor.getResult:()Lcom/jetbrains/cidr/lang/resolve/OCExprValueCategory;
        //   173: dup            
        //   174: ifnonnull       211
        //   177: new             Ljava/lang/IllegalStateException;
        //   180: dup            
        //   181: ldc             "@NotNull method %s.%s must not return null"
        //   183: ldc             2
        //   185: anewarray       Ljava/lang/Object;
        //   188: dup            
        //   189: ldc             0
        //   191: ldc             "com/jetbrains/cidr/lang/resolve/OCExprValueCategory"
        //   193: aastore        
        //   194: dup            
        //   195: ldc             1
        //   197: ldc             "classify"
        //   199: aastore        
        //   200: invokestatic    java/lang/String.format:(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
        //   203: invokespecial   java/lang/IllegalStateException.<init>:(Ljava/lang/String;)V
        //   206: athrow         
        //   207: invokestatic    com/jetbrains/cidr/lang/resolve/OCExprValueCategory.a:(Ljava/lang/IllegalStateException;)Ljava/lang/IllegalStateException;
        //   210: athrow         
        //   211: areturn        
        //   212: aload_0        
        //   213: instanceof      Lcom/jetbrains/cidr/lang/symbols/expression/OCExpressionSymbol;
        //   216: ifeq            280
        //   219: new             Lcom/jetbrains/cidr/lang/resolve/OCExprSymbolClassifier;
        //   222: dup            
        //   223: aload_1        
        //   224: invokespecial   com/jetbrains/cidr/lang/resolve/OCExprSymbolClassifier.<init>:(Lcom/jetbrains/cidr/lang/symbols/OCResolveContext;)V
        //   227: aload_0        
        //   228: checkcast       Lcom/jetbrains/cidr/lang/symbols/expression/OCExpressionSymbol;
        //   231: invokevirtual   com/jetbrains/cidr/lang/resolve/OCExprSymbolClassifier.classify:(Lcom/jetbrains/cidr/lang/symbols/expression/OCExpressionSymbol;)Lcom/jetbrains/cidr/lang/resolve/OCExprValueCategory;
        //   234: dup            
        //   235: ifnonnull       279
        //   238: goto            245
        //   241: invokestatic    com/jetbrains/cidr/lang/resolve/OCExprValueCategory.a:(Ljava/lang/IllegalStateException;)Ljava/lang/IllegalStateException;
        //   244: athrow         
        //   245: new             Ljava/lang/IllegalStateException;
        //   248: dup            
        //   249: ldc             "@NotNull method %s.%s must not return null"
        //   251: ldc             2
        //   253: anewarray       Ljava/lang/Object;
        //   256: dup            
        //   257: ldc             0
        //   259: ldc             "com/jetbrains/cidr/lang/resolve/OCExprValueCategory"
        //   261: aastore        
        //   262: dup            
        //   263: ldc             1
        //   265: ldc             "classify"
        //   267: aastore        
        //   268: invokestatic    java/lang/String.format:(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
        //   271: invokespecial   java/lang/IllegalStateException.<init>:(Ljava/lang/String;)V
        //   274: athrow         
        //   275: invokestatic    com/jetbrains/cidr/lang/resolve/OCExprValueCategory.a:(Ljava/lang/IllegalStateException;)Ljava/lang/IllegalStateException;
        //   278: athrow         
        //   279: areturn        
        //   280: getstatic       com/jetbrains/cidr/lang/resolve/OCExprValueCategory.PRValue:Lcom/jetbrains/cidr/lang/resolve/OCExprValueCategory;
        //   283: dup            
        //   284: ifnonnull       321
        //   287: new             Ljava/lang/IllegalStateException;
        //   290: dup            
        //   291: ldc             "@NotNull method %s.%s must not return null"
        //   293: ldc             2
        //   295: anewarray       Ljava/lang/Object;
        //   298: dup            
        //   299: ldc             0
        //   301: ldc             "com/jetbrains/cidr/lang/resolve/OCExprValueCategory"
        //   303: aastore        
        //   304: dup            
        //   305: ldc             1
        //   307: ldc             "classify"
        //   309: aastore        
        //   310: invokestatic    java/lang/String.format:(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
        //   313: invokespecial   java/lang/IllegalStateException.<init>:(Ljava/lang/String;)V
        //   316: athrow         
        //   317: invokestatic    com/jetbrains/cidr/lang/resolve/OCExprValueCategory.a:(Ljava/lang/IllegalStateException;)Ljava/lang/IllegalStateException;
        //   320: athrow         
        //   321: areturn        
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                             
        //  -----  -----  -----  -----  ---------------------------------
        //  0      40     40     44     Ljava/lang/IllegalStateException;
        //  64     77     80     84     Ljava/lang/IllegalStateException;
        //  68     94     97     101    Ljava/lang/IllegalStateException;
        //  84     108    111    115    Ljava/lang/IllegalStateException;
        //  101    145    145    149    Ljava/lang/IllegalStateException;
        //  159    207    207    211    Ljava/lang/IllegalStateException;
        //  212    238    241    245    Ljava/lang/IllegalStateException;
        //  219    275    275    279    Ljava/lang/IllegalStateException;
        //  280    317    317    321    Ljava/lang/IllegalStateException;
        // 
        // The error that occurred was:
        // 
        // java.lang.IllegalStateException: Expression is linked from several locations: Label_0084:
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
    
    private static IllegalStateException a(final IllegalStateException ex) {
        return ex;
    }
}
