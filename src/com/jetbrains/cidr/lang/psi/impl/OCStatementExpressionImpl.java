// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.lang.psi.impl;

import com.jetbrains.cidr.lang.types.OCType;
import com.jetbrains.cidr.lang.symbols.OCResolveContext;
import com.jetbrains.cidr.lang.psi.visitors.OCVisitor;
import org.jetbrains.annotations.Nullable;
import com.jetbrains.cidr.lang.parser.OCElementTypes;
import com.jetbrains.cidr.lang.psi.OCBlockStatement;
import org.jetbrains.annotations.NotNull;
import com.intellij.lang.ASTNode;
import com.jetbrains.cidr.lang.psi.OCStatementExpression;

public class OCStatementExpressionImpl extends OCExpressionBase implements OCStatementExpression
{
    public OCStatementExpressionImpl(@NotNull final ASTNode astNode) {
        if (astNode == null) {
            throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "node", "com/jetbrains/cidr/lang/psi/impl/OCStatementExpressionImpl", "<init>"));
        }
        super(astNode);
    }
    
    @Nullable
    @Override
    public OCBlockStatement getBody() {
        return this.findChildByType(OCElementTypes.BLOCK_STATEMENTS);
    }
    
    @Override
    public void accept(@NotNull final OCVisitor ocVisitor) {
        try {
            if (ocVisitor == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "visitor", "com/jetbrains/cidr/lang/psi/impl/OCStatementExpressionImpl", "accept"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        ocVisitor.visitStatementExpression(this);
    }
    
    @NotNull
    @Override
    public OCType getType(@NotNull final OCResolveContext p0) {
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
        //    24: ldc             "com/jetbrains/cidr/lang/psi/impl/OCStatementExpressionImpl"
        //    26: aastore        
        //    27: dup            
        //    28: ldc             2
        //    30: ldc             "getType"
        //    32: aastore        
        //    33: invokestatic    java/lang/String.format:(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
        //    36: invokespecial   java/lang/IllegalArgumentException.<init>:(Ljava/lang/String;)V
        //    39: athrow         
        //    40: invokestatic    com/jetbrains/cidr/lang/psi/impl/OCStatementExpressionImpl.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //    43: athrow         
        //    44: aload_0        
        //    45: invokevirtual   com/jetbrains/cidr/lang/psi/impl/OCStatementExpressionImpl.getBody:()Lcom/jetbrains/cidr/lang/psi/OCBlockStatement;
        //    48: astore_2       
        //    49: aload_2        
        //    50: ifnonnull       102
        //    53: invokestatic    com/jetbrains/cidr/lang/types/OCVoidType.instance:()Lcom/jetbrains/cidr/lang/types/OCVoidType;
        //    56: dup            
        //    57: ifnonnull       101
        //    60: goto            67
        //    63: invokestatic    com/jetbrains/cidr/lang/psi/impl/OCStatementExpressionImpl.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //    66: athrow         
        //    67: new             Ljava/lang/IllegalStateException;
        //    70: dup            
        //    71: ldc             "@NotNull method %s.%s must not return null"
        //    73: ldc             2
        //    75: anewarray       Ljava/lang/Object;
        //    78: dup            
        //    79: ldc             0
        //    81: ldc             "com/jetbrains/cidr/lang/psi/impl/OCStatementExpressionImpl"
        //    83: aastore        
        //    84: dup            
        //    85: ldc             1
        //    87: ldc             "getType"
        //    89: aastore        
        //    90: invokestatic    java/lang/String.format:(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
        //    93: invokespecial   java/lang/IllegalStateException.<init>:(Ljava/lang/String;)V
        //    96: athrow         
        //    97: invokestatic    com/jetbrains/cidr/lang/psi/impl/OCStatementExpressionImpl.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   100: athrow         
        //   101: areturn        
        //   102: aload_2        
        //   103: invokeinterface com/jetbrains/cidr/lang/psi/OCBlockStatement.getStatements:()Ljava/util/List;
        //   108: astore_3       
        //   109: aload_3        
        //   110: ifnull          129
        //   113: aload_3        
        //   114: invokeinterface java/util/List.isEmpty:()Z
        //   119: ifeq            178
        //   122: goto            129
        //   125: invokestatic    com/jetbrains/cidr/lang/psi/impl/OCStatementExpressionImpl.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   128: athrow         
        //   129: invokestatic    com/jetbrains/cidr/lang/types/OCVoidType.instance:()Lcom/jetbrains/cidr/lang/types/OCVoidType;
        //   132: dup            
        //   133: ifnonnull       177
        //   136: goto            143
        //   139: invokestatic    com/jetbrains/cidr/lang/psi/impl/OCStatementExpressionImpl.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   142: athrow         
        //   143: new             Ljava/lang/IllegalStateException;
        //   146: dup            
        //   147: ldc             "@NotNull method %s.%s must not return null"
        //   149: ldc             2
        //   151: anewarray       Ljava/lang/Object;
        //   154: dup            
        //   155: ldc             0
        //   157: ldc             "com/jetbrains/cidr/lang/psi/impl/OCStatementExpressionImpl"
        //   159: aastore        
        //   160: dup            
        //   161: ldc             1
        //   163: ldc             "getType"
        //   165: aastore        
        //   166: invokestatic    java/lang/String.format:(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
        //   169: invokespecial   java/lang/IllegalStateException.<init>:(Ljava/lang/String;)V
        //   172: athrow         
        //   173: invokestatic    com/jetbrains/cidr/lang/psi/impl/OCStatementExpressionImpl.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   176: athrow         
        //   177: areturn        
        //   178: aload_3        
        //   179: aload_3        
        //   180: invokeinterface java/util/List.size:()I
        //   185: iconst_1       
        //   186: isub           
        //   187: invokeinterface java/util/List.get:(I)Ljava/lang/Object;
        //   192: checkcast       Lcom/jetbrains/cidr/lang/psi/OCStatement;
        //   195: astore          4
        //   197: aload           4
        //   199: instanceof      Lcom/jetbrains/cidr/lang/psi/OCExpressionStatement;
        //   202: ifne            254
        //   205: invokestatic    com/jetbrains/cidr/lang/types/OCVoidType.instance:()Lcom/jetbrains/cidr/lang/types/OCVoidType;
        //   208: dup            
        //   209: ifnonnull       253
        //   212: goto            219
        //   215: invokestatic    com/jetbrains/cidr/lang/psi/impl/OCStatementExpressionImpl.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   218: athrow         
        //   219: new             Ljava/lang/IllegalStateException;
        //   222: dup            
        //   223: ldc             "@NotNull method %s.%s must not return null"
        //   225: ldc             2
        //   227: anewarray       Ljava/lang/Object;
        //   230: dup            
        //   231: ldc             0
        //   233: ldc             "com/jetbrains/cidr/lang/psi/impl/OCStatementExpressionImpl"
        //   235: aastore        
        //   236: dup            
        //   237: ldc             1
        //   239: ldc             "getType"
        //   241: aastore        
        //   242: invokestatic    java/lang/String.format:(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
        //   245: invokespecial   java/lang/IllegalStateException.<init>:(Ljava/lang/String;)V
        //   248: athrow         
        //   249: invokestatic    com/jetbrains/cidr/lang/psi/impl/OCStatementExpressionImpl.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   252: athrow         
        //   253: areturn        
        //   254: aload           4
        //   256: checkcast       Lcom/jetbrains/cidr/lang/psi/OCExpressionStatement;
        //   259: invokeinterface com/jetbrains/cidr/lang/psi/OCExpressionStatement.getExpression:()Lcom/jetbrains/cidr/lang/psi/OCExpression;
        //   264: astore          5
        //   266: aload           5
        //   268: ifnonnull       281
        //   271: invokestatic    com/jetbrains/cidr/lang/types/OCVoidType.instance:()Lcom/jetbrains/cidr/lang/types/OCVoidType;
        //   274: goto            289
        //   277: invokestatic    com/jetbrains/cidr/lang/psi/impl/OCStatementExpressionImpl.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   280: athrow         
        //   281: aload           5
        //   283: aload_1        
        //   284: invokeinterface com/jetbrains/cidr/lang/psi/OCExpression.getType:(Lcom/jetbrains/cidr/lang/symbols/OCResolveContext;)Lcom/jetbrains/cidr/lang/types/OCType;
        //   289: dup            
        //   290: ifnonnull       327
        //   293: new             Ljava/lang/IllegalStateException;
        //   296: dup            
        //   297: ldc             "@NotNull method %s.%s must not return null"
        //   299: ldc             2
        //   301: anewarray       Ljava/lang/Object;
        //   304: dup            
        //   305: ldc             0
        //   307: ldc             "com/jetbrains/cidr/lang/psi/impl/OCStatementExpressionImpl"
        //   309: aastore        
        //   310: dup            
        //   311: ldc             1
        //   313: ldc             "getType"
        //   315: aastore        
        //   316: invokestatic    java/lang/String.format:(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
        //   319: invokespecial   java/lang/IllegalStateException.<init>:(Ljava/lang/String;)V
        //   322: athrow         
        //   323: invokestatic    com/jetbrains/cidr/lang/psi/impl/OCStatementExpressionImpl.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   326: athrow         
        //   327: areturn        
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                                
        //  -----  -----  -----  -----  ------------------------------------
        //  0      40     40     44     Ljava/lang/IllegalArgumentException;
        //  49     60     63     67     Ljava/lang/IllegalArgumentException;
        //  53     97     97     101    Ljava/lang/IllegalArgumentException;
        //  109    122    125    129    Ljava/lang/IllegalArgumentException;
        //  113    136    139    143    Ljava/lang/IllegalArgumentException;
        //  129    173    173    177    Ljava/lang/IllegalArgumentException;
        //  197    212    215    219    Ljava/lang/IllegalArgumentException;
        //  205    249    249    253    Ljava/lang/IllegalArgumentException;
        //  266    277    277    281    Ljava/lang/IllegalArgumentException;
        //  289    323    323    327    Ljava/lang/IllegalArgumentException;
        // 
        // The error that occurred was:
        // 
        // java.lang.IllegalStateException: Expression is linked from several locations: Label_0129:
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
