// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.lang.psi.impl;

import com.jetbrains.cidr.lang.types.OCType;
import com.jetbrains.cidr.lang.symbols.OCResolveContext;
import com.jetbrains.cidr.lang.psi.visitors.OCVisitor;
import com.intellij.psi.PsiElement;
import com.intellij.psi.tree.IElementType;
import com.jetbrains.cidr.lang.parser.OCElementTypes;
import com.jetbrains.cidr.lang.psi.OCExpression;
import org.jetbrains.annotations.NotNull;
import com.intellij.lang.ASTNode;
import com.jetbrains.cidr.lang.psi.OCCommaExpression;

public class OCCommaExpressionImpl extends OCExpressionBase implements OCCommaExpression
{
    public OCCommaExpressionImpl(@NotNull final ASTNode astNode) {
        if (astNode == null) {
            throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "node", "com/jetbrains/cidr/lang/psi/impl/OCCommaExpressionImpl", "<init>"));
        }
        super(astNode);
    }
    
    @NotNull
    @Override
    public OCExpression getHeadExpression() {
        for (ASTNode astNode = this.getNode().getFirstChildNode(); astNode != null; astNode = astNode.getTreeNext()) {
            final IElementType elementType = astNode.getElementType();
            OCExpression ocExpression = null;
            Label_0051: {
                try {
                    if (!OCElementTypes.EXPRESSIONS.contains(elementType)) {
                        continue;
                    }
                    final ASTNode astNode2 = astNode;
                    final PsiElement psiElement = astNode2.getPsi();
                    ocExpression = (OCExpression)psiElement;
                    if (ocExpression == null) {
                        break Label_0051;
                    }
                    return ocExpression;
                }
                catch (IllegalArgumentException ex) {
                    throw a(ex);
                }
                try {
                    final ASTNode astNode2 = astNode;
                    final PsiElement psiElement = astNode2.getPsi();
                    ocExpression = (OCExpression)psiElement;
                    if (ocExpression == null) {
                        throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/psi/impl/OCCommaExpressionImpl", "getHeadExpression"));
                    }
                }
                catch (IllegalArgumentException ex2) {
                    throw a(ex2);
                }
            }
            return ocExpression;
        }
        throw new AssertionError((Object)"Can't find single expression in the list");
    }
    
    @NotNull
    @Override
    public OCExpression getTailExpression() {
        for (ASTNode astNode = this.getNode().getLastChildNode(); astNode != null; astNode = astNode.getTreePrev()) {
            final IElementType elementType = astNode.getElementType();
            OCExpression ocExpression = null;
            Label_0051: {
                try {
                    if (!OCElementTypes.EXPRESSIONS.contains(elementType)) {
                        continue;
                    }
                    final ASTNode astNode2 = astNode;
                    final PsiElement psiElement = astNode2.getPsi();
                    ocExpression = (OCExpression)psiElement;
                    if (ocExpression == null) {
                        break Label_0051;
                    }
                    return ocExpression;
                }
                catch (IllegalArgumentException ex) {
                    throw a(ex);
                }
                try {
                    final ASTNode astNode2 = astNode;
                    final PsiElement psiElement = astNode2.getPsi();
                    ocExpression = (OCExpression)psiElement;
                    if (ocExpression == null) {
                        throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/psi/impl/OCCommaExpressionImpl", "getTailExpression"));
                    }
                }
                catch (IllegalArgumentException ex2) {
                    throw a(ex2);
                }
            }
            return ocExpression;
        }
        throw new AssertionError((Object)"Can't find single expression in the list");
    }
    
    @Override
    public void accept(@NotNull final OCVisitor ocVisitor) {
        try {
            if (ocVisitor == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "visitor", "com/jetbrains/cidr/lang/psi/impl/OCCommaExpressionImpl", "accept"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        ocVisitor.visitCommaExpression(this);
    }
    
    @NotNull
    @Override
    public OCType getType(@NotNull final OCResolveContext ocResolveContext) {
        try {
            if (ocResolveContext == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "context", "com/jetbrains/cidr/lang/psi/impl/OCCommaExpressionImpl", "getType"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        OCType type;
        try {
            type = this.getTailExpression().getType(ocResolveContext);
            if (type == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/psi/impl/OCCommaExpressionImpl", "getType"));
            }
        }
        catch (IllegalArgumentException ex2) {
            throw a(ex2);
        }
        return type;
    }
    
    @NotNull
    @Override
    public OCType getResolvedType(@NotNull final OCResolveContext p0) {
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
        //    24: ldc             "com/jetbrains/cidr/lang/psi/impl/OCCommaExpressionImpl"
        //    26: aastore        
        //    27: dup            
        //    28: ldc             2
        //    30: ldc             "getResolvedType"
        //    32: aastore        
        //    33: invokestatic    java/lang/String.format:(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
        //    36: invokespecial   java/lang/IllegalArgumentException.<init>:(Ljava/lang/String;)V
        //    39: athrow         
        //    40: invokestatic    com/jetbrains/cidr/lang/psi/impl/OCCommaExpressionImpl.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //    43: athrow         
        //    44: aload_1        
        //    45: invokevirtual   com/jetbrains/cidr/lang/symbols/OCResolveContext.isInSFINAE:()Z
        //    48: ifeq            124
        //    51: aload_0        
        //    52: invokevirtual   com/jetbrains/cidr/lang/psi/impl/OCCommaExpressionImpl.getHeadExpression:()Lcom/jetbrains/cidr/lang/psi/OCExpression;
        //    55: aload_1        
        //    56: invokeinterface com/jetbrains/cidr/lang/psi/OCExpression.getResolvedType:(Lcom/jetbrains/cidr/lang/symbols/OCResolveContext;)Lcom/jetbrains/cidr/lang/types/OCType;
        //    61: aload_1        
        //    62: invokevirtual   com/jetbrains/cidr/lang/types/OCType.isUnresolved:(Lcom/jetbrains/cidr/lang/symbols/OCResolveContext;)Z
        //    65: ifeq            124
        //    68: goto            75
        //    71: invokestatic    com/jetbrains/cidr/lang/psi/impl/OCCommaExpressionImpl.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //    74: athrow         
        //    75: getstatic       com/jetbrains/cidr/lang/types/OCUnknownType.INSTANCE:Lcom/jetbrains/cidr/lang/types/OCUnknownType;
        //    78: dup            
        //    79: ifnonnull       123
        //    82: goto            89
        //    85: invokestatic    com/jetbrains/cidr/lang/psi/impl/OCCommaExpressionImpl.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //    88: athrow         
        //    89: new             Ljava/lang/IllegalStateException;
        //    92: dup            
        //    93: ldc             "@NotNull method %s.%s must not return null"
        //    95: ldc             2
        //    97: anewarray       Ljava/lang/Object;
        //   100: dup            
        //   101: ldc             0
        //   103: ldc             "com/jetbrains/cidr/lang/psi/impl/OCCommaExpressionImpl"
        //   105: aastore        
        //   106: dup            
        //   107: ldc             1
        //   109: ldc             "getResolvedType"
        //   111: aastore        
        //   112: invokestatic    java/lang/String.format:(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
        //   115: invokespecial   java/lang/IllegalStateException.<init>:(Ljava/lang/String;)V
        //   118: athrow         
        //   119: invokestatic    com/jetbrains/cidr/lang/psi/impl/OCCommaExpressionImpl.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   122: athrow         
        //   123: areturn        
        //   124: aload_0        
        //   125: invokevirtual   com/jetbrains/cidr/lang/psi/impl/OCCommaExpressionImpl.getTailExpression:()Lcom/jetbrains/cidr/lang/psi/OCExpression;
        //   128: aload_1        
        //   129: invokeinterface com/jetbrains/cidr/lang/psi/OCExpression.getResolvedType:(Lcom/jetbrains/cidr/lang/symbols/OCResolveContext;)Lcom/jetbrains/cidr/lang/types/OCType;
        //   134: dup            
        //   135: ifnonnull       172
        //   138: new             Ljava/lang/IllegalStateException;
        //   141: dup            
        //   142: ldc             "@NotNull method %s.%s must not return null"
        //   144: ldc             2
        //   146: anewarray       Ljava/lang/Object;
        //   149: dup            
        //   150: ldc             0
        //   152: ldc             "com/jetbrains/cidr/lang/psi/impl/OCCommaExpressionImpl"
        //   154: aastore        
        //   155: dup            
        //   156: ldc             1
        //   158: ldc             "getResolvedType"
        //   160: aastore        
        //   161: invokestatic    java/lang/String.format:(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
        //   164: invokespecial   java/lang/IllegalStateException.<init>:(Ljava/lang/String;)V
        //   167: athrow         
        //   168: invokestatic    com/jetbrains/cidr/lang/psi/impl/OCCommaExpressionImpl.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   171: athrow         
        //   172: areturn        
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                                
        //  -----  -----  -----  -----  ------------------------------------
        //  0      40     40     44     Ljava/lang/IllegalArgumentException;
        //  44     68     71     75     Ljava/lang/IllegalArgumentException;
        //  51     82     85     89     Ljava/lang/IllegalArgumentException;
        //  75     119    119    123    Ljava/lang/IllegalArgumentException;
        //  124    168    168    172    Ljava/lang/IllegalArgumentException;
        // 
        // The error that occurred was:
        // 
        // java.lang.IllegalStateException: Expression is linked from several locations: Label_0075:
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
