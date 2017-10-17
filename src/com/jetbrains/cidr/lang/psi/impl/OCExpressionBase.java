// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.lang.psi.impl;

import org.jetbrains.annotations.Nullable;
import com.jetbrains.cidr.lang.psi.OCFile;
import com.jetbrains.cidr.lang.types.visitors.OCTypeVisitor;
import com.jetbrains.cidr.lang.types.visitors.OCTypeResolveVisitor;
import com.intellij.psi.PsiFile;
import com.jetbrains.cidr.lang.types.OCObjectTypeContext;
import java.util.Collections;
import java.util.List;
import com.jetbrains.cidr.lang.types.OCTypeArgument;
import com.jetbrains.cidr.lang.types.OCStructType;
import java.util.Iterator;
import com.jetbrains.cidr.lang.types.OCUnknownType;
import com.intellij.psi.PsiElement;
import com.jetbrains.cidr.lang.symbols.OCResolveContext;
import com.jetbrains.cidr.lang.types.OCType;
import org.jetbrains.annotations.NotNull;
import com.intellij.lang.ASTNode;
import com.jetbrains.cidr.lang.psi.OCExpression;

public class OCExpressionBase extends OCElementBase implements OCExpression
{
    public OCExpressionBase(@NotNull final ASTNode astNode) {
        if (astNode == null) {
            throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "node", "com/jetbrains/cidr/lang/psi/impl/OCExpressionBase", "<init>"));
        }
        super(astNode);
    }
    
    @NotNull
    @Override
    public OCType getType() {
        OCType type;
        try {
            type = this.getType(new OCResolveContext((PsiElement)this));
            if (type == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/psi/impl/OCExpressionBase", "getType"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw c(ex);
        }
        return type;
    }
    
    @NotNull
    @Override
    public OCType getType(@NotNull final OCResolveContext ocResolveContext) {
        try {
            if (ocResolveContext == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "context", "com/jetbrains/cidr/lang/psi/impl/OCExpressionBase", "getType"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw c(ex);
        }
        OCUnknownType instance;
        try {
            instance = OCUnknownType.INSTANCE;
            if (instance == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/psi/impl/OCExpressionBase", "getType"));
            }
        }
        catch (IllegalArgumentException ex2) {
            throw c(ex2);
        }
        return instance;
    }
    
    @NotNull
    @Override
    public OCType getResolvedType() {
        OCType resolvedType;
        try {
            resolvedType = this.getResolvedType(new OCResolveContext((PsiElement)this));
            if (resolvedType == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/psi/impl/OCExpressionBase", "getResolvedType"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw c(ex);
        }
        return resolvedType;
    }
    
    @NotNull
    @Override
    public OCType getResolvedType(@NotNull final OCResolveContext ocResolveContext) {
        try {
            if (ocResolveContext == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "context", "com/jetbrains/cidr/lang/psi/impl/OCExpressionBase", "getResolvedType"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw c(ex);
        }
        OCType resolve;
        try {
            resolve = this.getType(ocResolveContext).resolve(ocResolveContext);
            if (resolve == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/psi/impl/OCExpressionBase", "getResolvedType"));
            }
        }
        catch (IllegalArgumentException ex2) {
            throw c(ex2);
        }
        return resolve;
    }
    
    @NotNull
    @Override
    public String findBestTypeName(final OCType ocType) {
        String bestNameInContext = ocType.getBestNameInContext((PsiElement)this);
        String aliasName = ocType.getAliasName();
        final String bestNameInContext2 = ocType.cloneWithAliasName(null).getBestNameInContext((PsiElement)this);
        if (ocType.isBetterAliasName(ocType, bestNameInContext2, bestNameInContext, (PsiElement)this)) {
            bestNameInContext = bestNameInContext2;
        }
        if (aliasName == null) {
            aliasName = bestNameInContext;
        }
        String s = this.a(ocType, aliasName, this.getResolvedType());
        for (final OCExpression ocExpression : this.getDependentExpressions()) {
            if (ocExpression != null) {
                final OCType resolvedType = ocExpression.getResolvedType();
                final String bestTypeName = ocExpression.findBestTypeName(resolvedType);
                if (ocType.isBetterAliasName(resolvedType, bestTypeName, s, (PsiElement)this)) {
                    s = bestTypeName;
                }
                s = this.a(ocType, s, resolvedType);
            }
        }
        String s4 = null;
        Label_0169: {
            Label_0160: {
                try {
                    if (s == ocType.getAliasName()) {
                        break Label_0160;
                    }
                    final String s2 = s;
                    final String s3 = bestNameInContext;
                    if (s2 != s3) {
                        break Label_0160;
                    }
                    break Label_0160;
                }
                catch (IllegalArgumentException ex) {
                    throw c(ex);
                }
                try {
                    final String s2 = s;
                    final String s3 = bestNameInContext;
                    if (s2 != s3) {
                        final String s5;
                        s4 = (s5 = s);
                        break Label_0169;
                    }
                }
                catch (IllegalArgumentException ex2) {
                    throw c(ex2);
                }
            }
            String s5;
            s4 = (s5 = bestNameInContext);
            try {
                if (s5 == null) {
                    throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/psi/impl/OCExpressionBase", "findBestTypeName"));
                }
            }
            catch (IllegalArgumentException ex3) {
                throw c(ex3);
            }
        }
        return s4;
    }
    
    private String a(final OCType ocType, String s, final OCType ocType2) {
        if (ocType2 instanceof OCStructType) {
            for (final OCTypeArgument ocTypeArgument : ((OCStructType)ocType2).getSymbol().getSubstitution().getSubstitutedTypes()) {
                if (ocTypeArgument instanceof OCType) {
                    final String aliasName = ((OCType)ocTypeArgument).getAliasName();
                    Label_0098: {
                        try {
                            if (aliasName == null || !ocType.isBetterAliasName((OCType)ocTypeArgument, aliasName, s, (PsiElement)this)) {
                                break Label_0098;
                            }
                        }
                        catch (IllegalArgumentException ex) {
                            throw c(ex);
                        }
                        s = aliasName;
                    }
                    final String bestNameInContext = ((OCType)ocTypeArgument).getBestNameInContext((PsiElement)this);
                    if (!ocType.isBetterAliasName((OCType)ocTypeArgument, bestNameInContext, s, (PsiElement)this)) {
                        continue;
                    }
                    s = bestNameInContext;
                }
            }
        }
        return s;
    }
    
    public static String getBestTypeName(@NotNull final OCType ocType, @NotNull OCType cloneWithAliasName, @NotNull final PsiElement psiElement) {
        try {
            if (ocType == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "contextType", "com/jetbrains/cidr/lang/psi/impl/OCExpressionBase", "getBestTypeName"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw c(ex);
        }
        try {
            if (cloneWithAliasName == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "targetType", "com/jetbrains/cidr/lang/psi/impl/OCExpressionBase", "getBestTypeName"));
            }
        }
        catch (IllegalArgumentException ex2) {
            throw c(ex2);
        }
        try {
            if (psiElement == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "context", "com/jetbrains/cidr/lang/psi/impl/OCExpressionBase", "getBestTypeName"));
            }
        }
        catch (IllegalArgumentException ex3) {
            throw c(ex3);
        }
        String bestNameInContext = cloneWithAliasName.getBestNameInContext(psiElement);
        cloneWithAliasName = cloneWithAliasName.cloneWithAliasName(null);
        final String bestNameInContext2 = cloneWithAliasName.getBestNameInContext(psiElement);
        if (cloneWithAliasName.isBetterAliasName(cloneWithAliasName, bestNameInContext2, bestNameInContext, psiElement)) {
            bestNameInContext = bestNameInContext2;
        }
        if (ocType instanceof OCStructType) {
            for (final OCTypeArgument ocTypeArgument : ((OCStructType)ocType).getSymbol().getSubstitution().getSubstitutedTypes()) {
                if (ocTypeArgument instanceof OCType) {
                    final String bestNameInContext3 = ((OCType)ocTypeArgument).getBestNameInContext(psiElement);
                    final String aliasName = ((OCType)ocTypeArgument).getAliasName();
                    if (cloneWithAliasName.isBetterAliasName((OCType)ocTypeArgument, bestNameInContext3, bestNameInContext, psiElement)) {
                        bestNameInContext = bestNameInContext3;
                    }
                    try {
                        if (aliasName == null || !cloneWithAliasName.isBetterAliasName((OCType)ocTypeArgument, aliasName, bestNameInContext, psiElement)) {
                            continue;
                        }
                    }
                    catch (IllegalArgumentException ex4) {
                        throw c(ex4);
                    }
                    bestNameInContext = aliasName;
                }
            }
        }
        return bestNameInContext;
    }
    
    protected List<OCExpression> getDependentExpressions() {
        return Collections.emptyList();
    }
    
    @Override
    public OCObjectTypeContext getTypeContext() {
        return this.getTypeContext(false, false);
    }
    
    @Nullable
    @Override
    public OCObjectTypeContext getTypeContext(final boolean b, final boolean b2) {
        final OCFile containingOCFile = this.getContainingOCFile();
        final OCType type = this.getType();
        final OCType resolve = type.resolve((PsiFile)containingOCFile, b2);
        OCType guessedType;
        if (b2) {
            guessedType = resolve.getGuessedType().accept((OCTypeVisitor<OCType>)new OCTypeResolveVisitor.OCObjectTypeReResolver((PsiFile)containingOCFile));
        }
        else {
            guessedType = resolve.getGuessedType();
        }
        return this.getTypeContext(null, guessedType, type, b, b2);
    }
    
    @Nullable
    @Override
    public OCObjectTypeContext getTypeContext(final OCExpression p0, final OCType p1, final OCType p2, final boolean p3, final boolean p4) {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     0: aload_0        
        //     1: invokestatic    com/jetbrains/cidr/lang/util/OCParenthesesUtils.topmostParenthesized:(Lcom/jetbrains/cidr/lang/psi/OCExpression;)Lcom/jetbrains/cidr/lang/psi/OCExpression;
        //     4: invokeinterface com/jetbrains/cidr/lang/psi/OCExpression.getParent:()Lcom/intellij/psi/PsiElement;
        //     9: instanceof      Lcom/jetbrains/cidr/lang/psi/OCQualifiedExpression;
        //    12: istore          6
        //    14: aload_2        
        //    15: invokevirtual   com/jetbrains/cidr/lang/types/OCType.isClassType:()Z
        //    18: ifeq            88
        //    21: aload_1        
        //    22: ifnull          88
        //    25: goto            32
        //    28: invokestatic    com/jetbrains/cidr/lang/psi/impl/OCExpressionBase.c:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //    31: athrow         
        //    32: aload_1        
        //    33: ldc             Lcom/jetbrains/cidr/lang/psi/OCClassDeclaration;.class
        //    35: invokestatic    com/intellij/psi/util/PsiTreeUtil.getParentOfType:(Lcom/intellij/psi/PsiElement;Ljava/lang/Class;)Lcom/intellij/psi/PsiElement;
        //    38: checkcast       Lcom/jetbrains/cidr/lang/psi/OCClassDeclaration;
        //    41: astore          7
        //    43: aload           7
        //    45: ifnull          62
        //    48: aload           7
        //    50: invokeinterface com/jetbrains/cidr/lang/psi/OCClassDeclaration.getType:()Lcom/jetbrains/cidr/lang/types/OCObjectType;
        //    55: goto            63
        //    58: invokestatic    com/jetbrains/cidr/lang/psi/impl/OCExpressionBase.c:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //    61: athrow         
        //    62: aconst_null    
        //    63: astore          8
        //    65: aload           8
        //    67: ifnull          88
        //    70: new             Lcom/jetbrains/cidr/lang/types/OCObjectTypeContext;
        //    73: dup            
        //    74: getstatic       com/jetbrains/cidr/lang/types/OCObjectTypeContext$StaticMode.STATIC:Lcom/jetbrains/cidr/lang/types/OCObjectTypeContext$StaticMode;
        //    77: aload           8
        //    79: aload_2        
        //    80: invokespecial   com/jetbrains/cidr/lang/types/OCObjectTypeContext.<init>:(Lcom/jetbrains/cidr/lang/types/OCObjectTypeContext$StaticMode;Lcom/jetbrains/cidr/lang/types/OCObjectType;Lcom/jetbrains/cidr/lang/types/OCType;)V
        //    83: areturn        
        //    84: invokestatic    com/jetbrains/cidr/lang/psi/impl/OCExpressionBase.c:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //    87: athrow         
        //    88: iload           6
        //    90: ifne            121
        //    93: aload_2        
        //    94: invokevirtual   com/jetbrains/cidr/lang/types/OCType.isClassType:()Z
        //    97: ifne            172
        //   100: goto            107
        //   103: invokestatic    com/jetbrains/cidr/lang/psi/impl/OCExpressionBase.c:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   106: athrow         
        //   107: aload_2        
        //   108: instanceof      Lcom/jetbrains/cidr/lang/types/OCBlockPointerType;
        //   111: ifne            172
        //   114: goto            121
        //   117: invokestatic    com/jetbrains/cidr/lang/psi/impl/OCExpressionBase.c:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   120: athrow         
        //   121: aload_2        
        //   122: invokevirtual   com/jetbrains/cidr/lang/types/OCType.isPointerToID:()Z
        //   125: ifeq            298
        //   128: goto            135
        //   131: invokestatic    com/jetbrains/cidr/lang/psi/impl/OCExpressionBase.c:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   134: athrow         
        //   135: aload_2        
        //   136: invokevirtual   com/jetbrains/cidr/lang/types/OCType.getTerminalType:()Lcom/jetbrains/cidr/lang/types/OCType;
        //   139: checkcast       Lcom/jetbrains/cidr/lang/types/OCObjectType;
        //   142: invokevirtual   com/jetbrains/cidr/lang/types/OCObjectType.getAllProtocols:()Ljava/util/List;
        //   145: invokeinterface java/util/List.isEmpty:()Z
        //   150: ifeq            298
        //   153: goto            160
        //   156: invokestatic    com/jetbrains/cidr/lang/psi/impl/OCExpressionBase.c:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   159: athrow         
        //   160: iload           4
        //   162: ifeq            298
        //   165: goto            172
        //   168: invokestatic    com/jetbrains/cidr/lang/psi/impl/OCExpressionBase.c:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   171: athrow         
        //   172: new             Lcom/jetbrains/cidr/lang/types/OCReferenceTypeBuilder;
        //   175: dup            
        //   176: ldc             "NSObject"
        //   178: invokespecial   com/jetbrains/cidr/lang/types/OCReferenceTypeBuilder.<init>:(Ljava/lang/String;)V
        //   181: astore          7
        //   183: aload_2        
        //   184: invokevirtual   com/jetbrains/cidr/lang/types/OCType.isClassType:()Z
        //   187: ifeq            224
        //   190: aload_3        
        //   191: instanceof      Lcom/jetbrains/cidr/lang/types/OCReferenceType;
        //   194: ifeq            224
        //   197: goto            204
        //   200: invokestatic    com/jetbrains/cidr/lang/psi/impl/OCExpressionBase.c:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   203: athrow         
        //   204: aload           7
        //   206: aload_3        
        //   207: checkcast       Lcom/jetbrains/cidr/lang/types/OCReferenceType;
        //   210: invokevirtual   com/jetbrains/cidr/lang/types/OCReferenceType.getProtocolNames:()Ljava/util/List;
        //   213: invokevirtual   com/jetbrains/cidr/lang/types/OCReferenceTypeBuilder.setProtocolNames:(Ljava/util/List;)Lcom/jetbrains/cidr/lang/types/OCReferenceTypeBuilder;
        //   216: pop            
        //   217: goto            224
        //   220: invokestatic    com/jetbrains/cidr/lang/psi/impl/OCExpressionBase.c:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   223: athrow         
        //   224: aload           7
        //   226: invokevirtual   com/jetbrains/cidr/lang/types/OCReferenceTypeBuilder.build:()Lcom/jetbrains/cidr/lang/types/OCReferenceType;
        //   229: aload_0        
        //   230: invokevirtual   com/jetbrains/cidr/lang/psi/impl/OCExpressionBase.getContainingFile:()Lcom/intellij/psi/PsiFile;
        //   233: iload           5
        //   235: invokevirtual   com/jetbrains/cidr/lang/types/OCReferenceType.resolve:(Lcom/intellij/psi/PsiFile;Z)Lcom/jetbrains/cidr/lang/types/OCType;
        //   238: astore          8
        //   240: aload           8
        //   242: instanceof      Lcom/jetbrains/cidr/lang/types/OCObjectType;
        //   245: ifeq            296
        //   248: new             Lcom/jetbrains/cidr/lang/types/OCObjectTypeContext;
        //   251: dup            
        //   252: iconst_0       
        //   253: aload_2        
        //   254: invokevirtual   com/jetbrains/cidr/lang/types/OCType.isClassType:()Z
        //   257: ifne            281
        //   260: goto            267
        //   263: invokestatic    com/jetbrains/cidr/lang/psi/impl/OCExpressionBase.c:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   266: athrow         
        //   267: aload_2        
        //   268: invokevirtual   com/jetbrains/cidr/lang/types/OCType.isPointerToID:()Z
        //   271: ifeq            285
        //   274: goto            281
        //   277: invokestatic    com/jetbrains/cidr/lang/psi/impl/OCExpressionBase.c:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   280: athrow         
        //   281: iconst_1       
        //   282: goto            286
        //   285: iconst_0       
        //   286: aload           8
        //   288: checkcast       Lcom/jetbrains/cidr/lang/types/OCObjectType;
        //   291: aload_2        
        //   292: invokespecial   com/jetbrains/cidr/lang/types/OCObjectTypeContext.<init>:(ZZLcom/jetbrains/cidr/lang/types/OCObjectType;Lcom/jetbrains/cidr/lang/types/OCType;)V
        //   295: areturn        
        //   296: aconst_null    
        //   297: areturn        
        //   298: aload_2        
        //   299: invokevirtual   com/jetbrains/cidr/lang/types/OCType.isPointerToObject:()Z
        //   302: ifeq            330
        //   305: new             Lcom/jetbrains/cidr/lang/types/OCObjectTypeContext;
        //   308: dup            
        //   309: iconst_0       
        //   310: aload_2        
        //   311: invokevirtual   com/jetbrains/cidr/lang/types/OCType.isPointerToID:()Z
        //   314: aload_2        
        //   315: invokevirtual   com/jetbrains/cidr/lang/types/OCType.getTerminalType:()Lcom/jetbrains/cidr/lang/types/OCType;
        //   318: checkcast       Lcom/jetbrains/cidr/lang/types/OCObjectType;
        //   321: aload_2        
        //   322: invokespecial   com/jetbrains/cidr/lang/types/OCObjectTypeContext.<init>:(ZZLcom/jetbrains/cidr/lang/types/OCObjectType;Lcom/jetbrains/cidr/lang/types/OCType;)V
        //   325: areturn        
        //   326: invokestatic    com/jetbrains/cidr/lang/psi/impl/OCExpressionBase.c:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   329: athrow         
        //   330: aload_2        
        //   331: instanceof      Lcom/jetbrains/cidr/lang/types/OCObjectType;
        //   334: ifeq            357
        //   337: new             Lcom/jetbrains/cidr/lang/types/OCObjectTypeContext;
        //   340: dup            
        //   341: getstatic       com/jetbrains/cidr/lang/types/OCObjectTypeContext$StaticMode.STATIC:Lcom/jetbrains/cidr/lang/types/OCObjectTypeContext$StaticMode;
        //   344: aload_2        
        //   345: checkcast       Lcom/jetbrains/cidr/lang/types/OCObjectType;
        //   348: aload_2        
        //   349: invokespecial   com/jetbrains/cidr/lang/types/OCObjectTypeContext.<init>:(Lcom/jetbrains/cidr/lang/types/OCObjectTypeContext$StaticMode;Lcom/jetbrains/cidr/lang/types/OCObjectType;Lcom/jetbrains/cidr/lang/types/OCType;)V
        //   352: areturn        
        //   353: invokestatic    com/jetbrains/cidr/lang/psi/impl/OCExpressionBase.c:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   356: athrow         
        //   357: aload_2        
        //   358: instanceof      Lcom/jetbrains/cidr/lang/types/OCMagicType;
        //   361: ifeq            393
        //   364: new             Lcom/jetbrains/cidr/lang/types/OCObjectTypeContext;
        //   367: dup            
        //   368: getstatic       com/jetbrains/cidr/lang/types/OCObjectTypeContext$StaticMode.NO_MATTER:Lcom/jetbrains/cidr/lang/types/OCObjectTypeContext$StaticMode;
        //   371: aload_0        
        //   372: invokevirtual   com/jetbrains/cidr/lang/psi/impl/OCExpressionBase.getProject:()Lcom/intellij/openapi/project/Project;
        //   375: invokestatic    com/jetbrains/cidr/lang/types/OCIdType.pointerToID:(Lcom/intellij/openapi/project/Project;)Lcom/jetbrains/cidr/lang/types/OCPointerType;
        //   378: invokevirtual   com/jetbrains/cidr/lang/types/OCPointerType.getRefType:()Lcom/jetbrains/cidr/lang/types/OCType;
        //   381: checkcast       Lcom/jetbrains/cidr/lang/types/OCObjectType;
        //   384: aload_2        
        //   385: invokespecial   com/jetbrains/cidr/lang/types/OCObjectTypeContext.<init>:(Lcom/jetbrains/cidr/lang/types/OCObjectTypeContext$StaticMode;Lcom/jetbrains/cidr/lang/types/OCObjectType;Lcom/jetbrains/cidr/lang/types/OCType;)V
        //   388: areturn        
        //   389: invokestatic    com/jetbrains/cidr/lang/psi/impl/OCExpressionBase.c:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   392: athrow         
        //   393: aconst_null    
        //   394: areturn        
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                                
        //  -----  -----  -----  -----  ------------------------------------
        //  14     25     28     32     Ljava/lang/IllegalArgumentException;
        //  43     58     58     62     Ljava/lang/IllegalArgumentException;
        //  65     84     84     88     Ljava/lang/IllegalArgumentException;
        //  88     100    103    107    Ljava/lang/IllegalArgumentException;
        //  93     114    117    121    Ljava/lang/IllegalArgumentException;
        //  107    128    131    135    Ljava/lang/IllegalArgumentException;
        //  121    153    156    160    Ljava/lang/IllegalArgumentException;
        //  135    165    168    172    Ljava/lang/IllegalArgumentException;
        //  183    197    200    204    Ljava/lang/IllegalArgumentException;
        //  190    217    220    224    Ljava/lang/IllegalArgumentException;
        //  240    260    263    267    Ljava/lang/IllegalArgumentException;
        //  248    274    277    281    Ljava/lang/IllegalArgumentException;
        //  298    326    326    330    Ljava/lang/IllegalArgumentException;
        //  330    353    353    357    Ljava/lang/IllegalArgumentException;
        //  357    389    389    393    Ljava/lang/IllegalArgumentException;
        // 
        // The error that occurred was:
        // 
        // java.lang.IllegalStateException: Expression is linked from several locations: Label_0107:
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
    
    private static IllegalArgumentException c(final IllegalArgumentException ex) {
        return ex;
    }
}
