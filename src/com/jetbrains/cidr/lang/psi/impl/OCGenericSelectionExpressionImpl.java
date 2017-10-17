// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.lang.psi.impl;

import com.jetbrains.cidr.lang.types.OCType;
import com.jetbrains.cidr.lang.symbols.OCResolveContext;
import com.intellij.psi.PsiElement;
import com.intellij.psi.tree.IElementType;
import com.jetbrains.cidr.lang.parser.OCElementTypes;
import com.jetbrains.cidr.lang.psi.OCGenericSelectionAssociation;
import java.util.List;
import org.jetbrains.annotations.Nullable;
import com.jetbrains.cidr.lang.psi.OCExpression;
import com.jetbrains.cidr.lang.psi.visitors.OCVisitor;
import org.jetbrains.annotations.NotNull;
import com.intellij.lang.ASTNode;
import com.jetbrains.cidr.lang.psi.OCGenericSelectionExpression;

public class OCGenericSelectionExpressionImpl extends OCExpressionBase implements OCGenericSelectionExpression
{
    public OCGenericSelectionExpressionImpl(@NotNull final ASTNode astNode) {
        if (astNode == null) {
            throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "node", "com/jetbrains/cidr/lang/psi/impl/OCGenericSelectionExpressionImpl", "<init>"));
        }
        super(astNode);
    }
    
    @Override
    public void accept(@NotNull final OCVisitor ocVisitor) {
        try {
            if (ocVisitor == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "visitor", "com/jetbrains/cidr/lang/psi/impl/OCGenericSelectionExpressionImpl", "accept"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        ocVisitor.visitGenericSelectionExpression(this);
    }
    
    @Nullable
    @Override
    public OCExpression getControllingExpression() {
        return this.findChildByClass(OCExpression.class);
    }
    
    @NotNull
    @Override
    public List<OCGenericSelectionAssociation> getAssociations() {
        List<PsiElement> childrenByType;
        try {
            childrenByType = (List<PsiElement>)this.findChildrenByType(OCElementTypes.GENERIC_SELECTION_ASSOCIATION);
            if (childrenByType == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/psi/impl/OCGenericSelectionExpressionImpl", "getAssociations"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        return (List<OCGenericSelectionAssociation>)childrenByType;
    }
    
    @NotNull
    @Override
    public OCType getType(@NotNull final OCResolveContext ocResolveContext) {
        try {
            if (ocResolveContext == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "context", "com/jetbrains/cidr/lang/psi/impl/OCGenericSelectionExpressionImpl", "getType"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        final OCExpression controllingExpression = this.getControllingExpression();
        if (controllingExpression != null) {
            final OCGenericSelectionAssociation associationByType = this.getAssociationByType(controllingExpression.getResolvedType(ocResolveContext));
            if (associationByType != null) {
                final OCType resolvedType = associationByType.getResolvedType(ocResolveContext);
                OCType ocType = null;
                Label_0102: {
                    try {
                        if (resolvedType != null) {
                            final OCType type;
                            ocType = (type = resolvedType);
                            break Label_0102;
                        }
                    }
                    catch (IllegalArgumentException ex2) {
                        throw a(ex2);
                    }
                    OCType type;
                    ocType = (type = super.getType(ocResolveContext));
                    try {
                        if (type == null) {
                            throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/psi/impl/OCGenericSelectionExpressionImpl", "getType"));
                        }
                    }
                    catch (IllegalArgumentException ex3) {
                        throw a(ex3);
                    }
                }
                return ocType;
            }
        }
        OCType type2;
        try {
            type2 = super.getType(ocResolveContext);
            if (type2 == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/psi/impl/OCGenericSelectionExpressionImpl", "getType"));
            }
        }
        catch (IllegalArgumentException ex4) {
            throw a(ex4);
        }
        return type2;
    }
    
    @Nullable
    @Override
    public OCGenericSelectionAssociation getAssociationByType(@NotNull final OCType p0) {
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
        //    24: ldc             "com/jetbrains/cidr/lang/psi/impl/OCGenericSelectionExpressionImpl"
        //    26: aastore        
        //    27: dup            
        //    28: ldc             2
        //    30: ldc             "getAssociationByType"
        //    32: aastore        
        //    33: invokestatic    java/lang/String.format:(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
        //    36: invokespecial   java/lang/IllegalArgumentException.<init>:(Ljava/lang/String;)V
        //    39: athrow         
        //    40: invokestatic    com/jetbrains/cidr/lang/psi/impl/OCGenericSelectionExpressionImpl.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //    43: athrow         
        //    44: aload_1        
        //    45: aload_0        
        //    46: invokevirtual   com/jetbrains/cidr/lang/psi/impl/OCGenericSelectionExpressionImpl.getProject:()Lcom/intellij/openapi/project/Project;
        //    49: invokestatic    com/jetbrains/cidr/lang/types/OCTypeUtils.decayType:(Lcom/jetbrains/cidr/lang/types/OCType;Lcom/intellij/openapi/project/Project;)Lcom/jetbrains/cidr/lang/types/OCType;
        //    52: astore_1       
        //    53: aload_1        
        //    54: instanceof      Lcom/jetbrains/cidr/lang/types/OCFunctionType;
        //    57: ifeq            65
        //    60: aload_1        
        //    61: invokestatic    com/jetbrains/cidr/lang/types/OCPointerType.to:(Lcom/jetbrains/cidr/lang/types/OCType;)Lcom/jetbrains/cidr/lang/types/OCPointerType;
        //    64: astore_1       
        //    65: aconst_null    
        //    66: astore_2       
        //    67: aload_0        
        //    68: invokevirtual   com/jetbrains/cidr/lang/psi/impl/OCGenericSelectionExpressionImpl.getAssociations:()Ljava/util/List;
        //    71: invokeinterface java/util/List.iterator:()Ljava/util/Iterator;
        //    76: astore_3       
        //    77: aload_3        
        //    78: invokeinterface java/util/Iterator.hasNext:()Z
        //    83: ifeq            207
        //    86: aload_3        
        //    87: invokeinterface java/util/Iterator.next:()Ljava/lang/Object;
        //    92: checkcast       Lcom/jetbrains/cidr/lang/psi/OCGenericSelectionAssociation;
        //    95: astore          4
        //    97: aload           4
        //    99: invokeinterface com/jetbrains/cidr/lang/psi/OCGenericSelectionAssociation.isDefault:()Z
        //   104: ifeq            113
        //   107: aload           4
        //   109: astore_2       
        //   110: goto            204
        //   113: aload           4
        //   115: invokeinterface com/jetbrains/cidr/lang/psi/OCGenericSelectionAssociation.getAssociationResolvedType:()Lcom/jetbrains/cidr/lang/types/OCType;
        //   120: astore          5
        //   122: aload_1        
        //   123: instanceof      Lcom/jetbrains/cidr/lang/types/OCStructType;
        //   126: ifeq            187
        //   129: aload_1        
        //   130: checkcast       Lcom/jetbrains/cidr/lang/types/OCStructType;
        //   133: invokevirtual   com/jetbrains/cidr/lang/types/OCStructType.isEnumClass:()Z
        //   136: ifne            187
        //   139: goto            146
        //   142: invokestatic    com/jetbrains/cidr/lang/psi/impl/OCGenericSelectionExpressionImpl.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   145: athrow         
        //   146: aload_1        
        //   147: aload_0        
        //   148: invokevirtual   com/jetbrains/cidr/lang/types/OCType.isIntegerCompatible:(Lcom/intellij/psi/PsiElement;)Z
        //   151: ifeq            187
        //   154: goto            161
        //   157: invokestatic    com/jetbrains/cidr/lang/psi/impl/OCGenericSelectionExpressionImpl.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   160: athrow         
        //   161: getstatic       com/jetbrains/cidr/lang/types/OCIntType.INT:Lcom/jetbrains/cidr/lang/types/OCIntType;
        //   164: aload           5
        //   166: aload_0        
        //   167: invokevirtual   com/jetbrains/cidr/lang/types/OCIntType.equals:(Ljava/lang/Object;Lcom/intellij/psi/PsiElement;)Z
        //   170: ifeq            204
        //   173: goto            180
        //   176: invokestatic    com/jetbrains/cidr/lang/psi/impl/OCGenericSelectionExpressionImpl.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   179: athrow         
        //   180: aload           4
        //   182: areturn        
        //   183: invokestatic    com/jetbrains/cidr/lang/psi/impl/OCGenericSelectionExpressionImpl.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   186: athrow         
        //   187: aload_1        
        //   188: aload           5
        //   190: aload_0        
        //   191: invokevirtual   com/jetbrains/cidr/lang/types/OCType.equals:(Ljava/lang/Object;Lcom/intellij/psi/PsiElement;)Z
        //   194: ifeq            204
        //   197: aload           4
        //   199: areturn        
        //   200: invokestatic    com/jetbrains/cidr/lang/psi/impl/OCGenericSelectionExpressionImpl.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   203: athrow         
        //   204: goto            77
        //   207: aload_2        
        //   208: areturn        
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                                
        //  -----  -----  -----  -----  ------------------------------------
        //  0      40     40     44     Ljava/lang/IllegalArgumentException;
        //  122    139    142    146    Ljava/lang/IllegalArgumentException;
        //  129    154    157    161    Ljava/lang/IllegalArgumentException;
        //  146    173    176    180    Ljava/lang/IllegalArgumentException;
        //  161    183    183    187    Ljava/lang/IllegalArgumentException;
        //  187    200    200    204    Ljava/lang/IllegalArgumentException;
        // 
        // The error that occurred was:
        // 
        // java.lang.IllegalStateException: Expression is linked from several locations: Label_0146:
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
