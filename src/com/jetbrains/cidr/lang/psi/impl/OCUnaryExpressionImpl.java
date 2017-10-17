// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.lang.psi.impl;

import com.intellij.psi.PsiReference;
import java.util.Collections;
import java.util.List;
import com.jetbrains.cidr.lang.types.OCMagicType;
import com.jetbrains.cidr.lang.psi.OCReferenceElement;
import java.util.Iterator;
import com.jetbrains.cidr.lang.psi.OCNamespaceQualifierOwner;
import com.jetbrains.cidr.lang.symbols.OCSymbolReference;
import com.jetbrains.cidr.lang.psi.OCReferenceExpression;
import com.jetbrains.cidr.lang.types.OCUnknownType;
import com.jetbrains.cidr.lang.symbols.cpp.OCFunctionSymbol;
import com.jetbrains.cidr.lang.symbols.OCSymbol;
import com.jetbrains.cidr.lang.types.OCType;
import com.jetbrains.cidr.lang.symbols.OCResolveContext;
import com.jetbrains.cidr.lang.psi.visitors.OCVisitor;
import com.jetbrains.cidr.lang.parser.OCTokenTypes;
import com.jetbrains.cidr.lang.parser.OCElementType;
import com.jetbrains.cidr.lang.parser.OCElementTypes;
import org.jetbrains.annotations.Nullable;
import com.jetbrains.cidr.lang.psi.OCElement;
import com.jetbrains.cidr.lang.psi.OCExpression;
import org.jetbrains.annotations.NotNull;
import com.intellij.lang.ASTNode;
import com.intellij.openapi.diagnostic.Logger;
import com.jetbrains.cidr.lang.psi.OCUnaryExpression;
import com.jetbrains.cidr.lang.resolve.references.OCOperatorReference;

public class OCUnaryExpressionImpl extends OCExpressionWithReferenceBase<OCOperatorReference> implements OCUnaryExpression
{
    private static final Logger LOG;
    
    public OCUnaryExpressionImpl(@NotNull final ASTNode astNode) {
        if (astNode == null) {
            throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "node", "com/jetbrains/cidr/lang/psi/impl/OCUnaryExpressionImpl", "<init>"));
        }
        super(astNode);
    }
    
    @Nullable
    @Override
    protected OCOperatorReference createReference() {
        final OCExpression operand = this.getOperand();
        try {
            if (operand != null) {
                return new OCOperatorReference(this, this.getOperationName(), OCOperatorReference.OperatorPlacement.PREFIX, this.getOperationSignNode().getPsi(), new OCExpression[] { operand });
            }
        }
        catch (IllegalArgumentException ex) {
            throw e(ex);
        }
        return null;
    }
    
    @Override
    public OCExpression getOperand() {
        return this.findChildByType(OCElementTypes.EXPRESSIONS);
    }
    
    @NotNull
    @Override
    public OCElementType getOperationSign() {
        OCElementType ocElementType;
        try {
            ocElementType = (OCElementType)this.getOperationSignNode().getElementType();
            if (ocElementType == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/psi/impl/OCUnaryExpressionImpl", "getOperationSign"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw e(ex);
        }
        return ocElementType;
    }
    
    @NotNull
    @Override
    public ASTNode getOperationSignNode() {
        ASTNode firstChildNode;
        try {
            firstChildNode = this.getNode().getFirstChildNode();
            if (firstChildNode == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/psi/impl/OCUnaryExpressionImpl", "getOperationSignNode"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw e(ex);
        }
        return firstChildNode;
    }
    
    @NotNull
    @Override
    public String getOperationName() {
        String name;
        try {
            name = this.getOperationSign().getName();
            if (name == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/psi/impl/OCUnaryExpressionImpl", "getOperationName"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw e(ex);
        }
        return name;
    }
    
    @Override
    public boolean isGetAddress() {
        try {
            if (this.getOperationSign() == OCTokenTypes.AND) {
                return true;
            }
        }
        catch (IllegalArgumentException ex) {
            throw e(ex);
        }
        return false;
    }
    
    @Override
    public void accept(@NotNull final OCVisitor ocVisitor) {
        try {
            if (ocVisitor == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "visitor", "com/jetbrains/cidr/lang/psi/impl/OCUnaryExpressionImpl", "accept"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw e(ex);
        }
        ocVisitor.visitUnaryExpression(this);
    }
    
    @NotNull
    @Override
    public OCType getType(@NotNull final OCResolveContext ocResolveContext) {
        try {
            if (ocResolveContext == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "context", "com/jetbrains/cidr/lang/psi/impl/OCUnaryExpressionImpl", "getType"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw e(ex);
        }
        final OCOperatorReference ocOperatorReference = this.getReference();
        if (ocOperatorReference != null) {
            for (final OCSymbol ocSymbol : ocOperatorReference.resolveToSymbols(ocResolveContext)) {
                OCType ocType = null;
                Label_0113: {
                    try {
                        if (!(ocSymbol instanceof OCFunctionSymbol)) {
                            continue;
                        }
                        final OCSymbol ocSymbol2 = ocSymbol;
                        ocType = ocSymbol2.getEffectiveType();
                        if (ocType == null) {
                            break Label_0113;
                        }
                        return ocType;
                    }
                    catch (IllegalArgumentException ex2) {
                        throw e(ex2);
                    }
                    try {
                        final OCSymbol ocSymbol2 = ocSymbol;
                        ocType = ocSymbol2.getEffectiveType();
                        if (ocType == null) {
                            throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/psi/impl/OCUnaryExpressionImpl", "getType"));
                        }
                    }
                    catch (IllegalArgumentException ex3) {
                        throw e(ex3);
                    }
                }
                return ocType;
            }
        }
        final OCElementType operationSign = this.getOperationSign();
        final OCExpression operand = this.getOperand();
        Label_0216: {
            OCUnknownType ocUnknownType = null;
            Label_0181: {
                try {
                    if (operand != null) {
                        break Label_0216;
                    }
                    ocUnknownType = OCUnknownType.INSTANCE;
                    if (ocUnknownType == null) {
                        break Label_0181;
                    }
                    return ocUnknownType;
                }
                catch (IllegalArgumentException ex4) {
                    throw e(ex4);
                }
                try {
                    ocUnknownType = OCUnknownType.INSTANCE;
                    if (ocUnknownType == null) {
                        throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/psi/impl/OCUnaryExpressionImpl", "getType"));
                    }
                }
                catch (IllegalArgumentException ex5) {
                    throw e(ex5);
                }
            }
            return ocUnknownType;
        }
        final OCType resolvedType = operand.getResolvedType(ocResolveContext);
        OCSymbolReference localReference = null;
        if (operand instanceof OCReferenceExpression) {
            final OCReferenceElement referenceElement = ((OCReferenceExpression)operand).getReferenceElement();
            if (referenceElement != null) {
                localReference = OCSymbolReference.getLocalReference(referenceElement, OCSymbolReference.SymbolFilter.NONE);
            }
        }
        OCType unaryExprType;
        try {
            unaryExprType = getUnaryExprType(operationSign, resolvedType, localReference, ocResolveContext);
            if (unaryExprType == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/psi/impl/OCUnaryExpressionImpl", "getType"));
            }
        }
        catch (IllegalArgumentException ex6) {
            throw e(ex6);
        }
        return unaryExprType;
    }
    
    public static OCType getUnaryExprType(final OCElementType p0, final OCType p1, @Nullable final OCSymbolReference p2, @NotNull final OCResolveContext p3) {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     0: aload_3        
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
        //    24: ldc             "com/jetbrains/cidr/lang/psi/impl/OCUnaryExpressionImpl"
        //    26: aastore        
        //    27: dup            
        //    28: ldc             2
        //    30: ldc             "getUnaryExprType"
        //    32: aastore        
        //    33: invokestatic    java/lang/String.format:(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
        //    36: invokespecial   java/lang/IllegalArgumentException.<init>:(Ljava/lang/String;)V
        //    39: athrow         
        //    40: invokestatic    com/jetbrains/cidr/lang/psi/impl/OCUnaryExpressionImpl.e:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //    43: athrow         
        //    44: aload_1        
        //    45: aload_3        
        //    46: invokevirtual   com/jetbrains/cidr/lang/types/OCType.isUnresolved:(Lcom/jetbrains/cidr/lang/symbols/OCResolveContext;)Z
        //    49: ifeq            60
        //    52: getstatic       com/jetbrains/cidr/lang/types/OCUnknownType.INSTANCE:Lcom/jetbrains/cidr/lang/types/OCUnknownType;
        //    55: astore          4
        //    57: goto            528
        //    60: aload_0        
        //    61: getstatic       com/jetbrains/cidr/lang/parser/OCTokenTypes.UDL_SUFFIX:Lcom/jetbrains/cidr/lang/parser/OCElementType;
        //    64: if_acmpeq       82
        //    67: aload_1        
        //    68: aload_3        
        //    69: invokevirtual   com/jetbrains/cidr/lang/types/OCType.isSubclassOfMagic:(Lcom/jetbrains/cidr/lang/symbols/OCResolveContext;)Z
        //    72: ifeq            95
        //    75: goto            82
        //    78: invokestatic    com/jetbrains/cidr/lang/psi/impl/OCUnaryExpressionImpl.e:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //    81: athrow         
        //    82: new             Lcom/jetbrains/cidr/lang/types/OCMagicType;
        //    85: dup            
        //    86: aload_1        
        //    87: invokespecial   com/jetbrains/cidr/lang/types/OCMagicType.<init>:(Lcom/jetbrains/cidr/lang/types/OCType;)V
        //    90: astore          4
        //    92: goto            528
        //    95: aload_0        
        //    96: getstatic       com/jetbrains/cidr/lang/parser/OCTokenTypes.MUL:Lcom/jetbrains/cidr/lang/parser/OCPunctuatorElementType;
        //    99: if_acmpne       138
        //   102: aload_1        
        //   103: instanceof      Lcom/jetbrains/cidr/lang/types/OCPointerType;
        //   106: ifeq            128
        //   109: goto            116
        //   112: invokestatic    com/jetbrains/cidr/lang/psi/impl/OCUnaryExpressionImpl.e:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   115: athrow         
        //   116: aload_1        
        //   117: checkcast       Lcom/jetbrains/cidr/lang/types/OCPointerType;
        //   120: invokevirtual   com/jetbrains/cidr/lang/types/OCPointerType.getRefType:()Lcom/jetbrains/cidr/lang/types/OCType;
        //   123: astore          4
        //   125: goto            528
        //   128: aload_1        
        //   129: aload_3        
        //   130: invokestatic    com/jetbrains/cidr/lang/psi/impl/OCUnaryExpressionImpl.a:(Lcom/jetbrains/cidr/lang/types/OCType;Lcom/jetbrains/cidr/lang/symbols/OCResolveContext;)Lcom/jetbrains/cidr/lang/types/OCType;
        //   133: astore          4
        //   135: goto            528
        //   138: aload_0        
        //   139: getstatic       com/jetbrains/cidr/lang/parser/OCTokenTypes.__REAL__KEYWORD:Lcom/jetbrains/cidr/lang/parser/OCElementType;
        //   142: if_acmpeq       159
        //   145: aload_0        
        //   146: getstatic       com/jetbrains/cidr/lang/parser/OCTokenTypes.__IMAG__KEYWORD:Lcom/jetbrains/cidr/lang/parser/OCElementType;
        //   149: if_acmpne       191
        //   152: goto            159
        //   155: invokestatic    com/jetbrains/cidr/lang/psi/impl/OCUnaryExpressionImpl.e:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   158: athrow         
        //   159: aload_1        
        //   160: instanceof      Lcom/jetbrains/cidr/lang/types/OCRealType;
        //   163: ifeq            185
        //   166: goto            173
        //   169: invokestatic    com/jetbrains/cidr/lang/psi/impl/OCUnaryExpressionImpl.e:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   172: athrow         
        //   173: aload_1        
        //   174: checkcast       Lcom/jetbrains/cidr/lang/types/OCRealType;
        //   177: invokevirtual   com/jetbrains/cidr/lang/types/OCRealType.cloneWithoutComplexModifier:()Lcom/jetbrains/cidr/lang/types/OCRealType;
        //   180: astore          4
        //   182: goto            528
        //   185: aload_1        
        //   186: astore          4
        //   188: goto            528
        //   191: aload_0        
        //   192: getstatic       com/jetbrains/cidr/lang/parser/OCTokenTypes.AND:Lcom/jetbrains/cidr/lang/parser/OCPunctuatorElementType;
        //   195: if_acmpne       378
        //   198: aload_1        
        //   199: astore          5
        //   201: aconst_null    
        //   202: astore          6
        //   204: aload           5
        //   206: instanceof      Lcom/jetbrains/cidr/lang/types/OCCppReferenceType;
        //   209: ifeq            222
        //   212: aload           5
        //   214: checkcast       Lcom/jetbrains/cidr/lang/types/OCCppReferenceType;
        //   217: invokevirtual   com/jetbrains/cidr/lang/types/OCCppReferenceType.getRefType:()Lcom/jetbrains/cidr/lang/types/OCType;
        //   220: astore          5
        //   222: aload_2        
        //   223: ifnull          365
        //   226: aload_2        
        //   227: invokevirtual   com/jetbrains/cidr/lang/symbols/OCSymbolReference.getQualifiedName:()Lcom/jetbrains/cidr/lang/symbols/OCQualifiedName;
        //   230: invokevirtual   com/jetbrains/cidr/lang/symbols/OCQualifiedName.getQualifier:()Lcom/jetbrains/cidr/lang/symbols/OCQualifiedName;
        //   233: ifnull          365
        //   236: goto            243
        //   239: invokestatic    com/jetbrains/cidr/lang/psi/impl/OCUnaryExpressionImpl.e:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   242: athrow         
        //   243: aload_2        
        //   244: aload_3        
        //   245: invokevirtual   com/jetbrains/cidr/lang/symbols/OCSymbolReference.resolveToSymbols:(Lcom/jetbrains/cidr/lang/symbols/OCResolveContext;)Ljava/util/List;
        //   248: invokestatic    com/jetbrains/cidr/lang/psi/impl/OCReferenceElementImpl.findPredeclaration:(Ljava/util/Collection;)Lcom/jetbrains/cidr/lang/symbols/OCSymbol;
        //   251: astore          7
        //   253: aload           7
        //   255: instanceof      Lcom/jetbrains/cidr/lang/symbols/cpp/OCFunctionSymbol;
        //   258: ifne            276
        //   261: aload           7
        //   263: instanceof      Lcom/jetbrains/cidr/lang/symbols/cpp/OCDeclaratorSymbol;
        //   266: ifeq            338
        //   269: goto            276
        //   272: invokestatic    com/jetbrains/cidr/lang/psi/impl/OCUnaryExpressionImpl.e:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   275: athrow         
        //   276: aload           7
        //   278: checkcast       Lcom/jetbrains/cidr/lang/symbols/cpp/OCSymbolWithQualifiedName;
        //   281: invokevirtual   com/jetbrains/cidr/lang/symbols/cpp/OCSymbolWithQualifiedName.isFriendOrStatic:()Z
        //   284: ifne            338
        //   287: goto            294
        //   290: invokestatic    com/jetbrains/cidr/lang/psi/impl/OCUnaryExpressionImpl.e:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   293: athrow         
        //   294: aload           7
        //   296: checkcast       Lcom/jetbrains/cidr/lang/symbols/cpp/OCSymbolWithQualifiedName;
        //   299: invokevirtual   com/jetbrains/cidr/lang/symbols/cpp/OCSymbolWithQualifiedName.getParent:()Lcom/jetbrains/cidr/lang/symbols/cpp/OCSymbolWithQualifiedName;
        //   302: instanceof      Lcom/jetbrains/cidr/lang/symbols/cpp/OCStructSymbol;
        //   305: ifeq            338
        //   308: goto            315
        //   311: invokestatic    com/jetbrains/cidr/lang/psi/impl/OCUnaryExpressionImpl.e:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   314: athrow         
        //   315: new             Lcom/jetbrains/cidr/lang/types/OCReferenceTypeBuilder;
        //   318: dup            
        //   319: aload_2        
        //   320: invokevirtual   com/jetbrains/cidr/lang/symbols/OCSymbolReference.getSymbolReferenceToQualifier:()Lcom/jetbrains/cidr/lang/symbols/OCSymbolReference;
        //   323: invokespecial   com/jetbrains/cidr/lang/types/OCReferenceTypeBuilder.<init>:(Lcom/jetbrains/cidr/lang/symbols/OCSymbolReference;)V
        //   326: invokevirtual   com/jetbrains/cidr/lang/types/OCReferenceTypeBuilder.build:()Lcom/jetbrains/cidr/lang/types/OCReferenceType;
        //   329: aload_3        
        //   330: invokevirtual   com/jetbrains/cidr/lang/types/OCReferenceType.resolve:(Lcom/jetbrains/cidr/lang/symbols/OCResolveContext;)Lcom/jetbrains/cidr/lang/types/OCType;
        //   333: astore          6
        //   335: goto            365
        //   338: aload           7
        //   340: instanceof      Lcom/jetbrains/cidr/lang/symbols/OCTypeParameterSymbol;
        //   343: ifeq            365
        //   346: new             Lcom/jetbrains/cidr/lang/types/OCMagicType;
        //   349: dup            
        //   350: aload_2        
        //   351: invokevirtual   com/jetbrains/cidr/lang/symbols/OCSymbolReference.getQualifiedName:()Lcom/jetbrains/cidr/lang/symbols/OCQualifiedName;
        //   354: invokevirtual   com/jetbrains/cidr/lang/symbols/OCQualifiedName.getQualifier:()Lcom/jetbrains/cidr/lang/symbols/OCQualifiedName;
        //   357: invokevirtual   com/jetbrains/cidr/lang/symbols/OCQualifiedName.toString:()Ljava/lang/String;
        //   360: invokespecial   com/jetbrains/cidr/lang/types/OCMagicType.<init>:(Ljava/lang/String;)V
        //   363: astore          6
        //   365: aload           5
        //   367: aconst_null    
        //   368: aload           6
        //   370: invokestatic    com/jetbrains/cidr/lang/types/OCPointerType.to:(Lcom/jetbrains/cidr/lang/types/OCType;Lcom/jetbrains/cidr/lang/types/ARCAttribute;Lcom/jetbrains/cidr/lang/types/OCType;)Lcom/jetbrains/cidr/lang/types/OCPointerType;
        //   373: astore          4
        //   375: goto            528
        //   378: aload_0        
        //   379: getstatic       com/jetbrains/cidr/lang/parser/OCTokenTypes.TILDE:Lcom/jetbrains/cidr/lang/parser/OCPunctuatorElementType;
        //   382: if_acmpeq       399
        //   385: aload_0        
        //   386: getstatic       com/jetbrains/cidr/lang/parser/OCTokenTypes.PLUS:Lcom/jetbrains/cidr/lang/parser/OCPunctuatorElementType;
        //   389: if_acmpne       405
        //   392: goto            399
        //   395: invokestatic    com/jetbrains/cidr/lang/psi/impl/OCUnaryExpressionImpl.e:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   398: athrow         
        //   399: aload_1        
        //   400: astore          4
        //   402: goto            528
        //   405: aload_0        
        //   406: getstatic       com/jetbrains/cidr/lang/parser/OCTokenTypes.MINUS:Lcom/jetbrains/cidr/lang/parser/OCPunctuatorElementType;
        //   409: if_acmpne       465
        //   412: aload_1        
        //   413: instanceof      Lcom/jetbrains/cidr/lang/types/OCIntType;
        //   416: ifeq            438
        //   419: goto            426
        //   422: invokestatic    com/jetbrains/cidr/lang/psi/impl/OCUnaryExpressionImpl.e:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   425: athrow         
        //   426: aload_1        
        //   427: checkcast       Lcom/jetbrains/cidr/lang/types/OCIntType;
        //   430: invokevirtual   com/jetbrains/cidr/lang/types/OCIntType.promoteToSigned:()Lcom/jetbrains/cidr/lang/types/OCIntType;
        //   433: astore          4
        //   435: goto            528
        //   438: aload_1        
        //   439: aload_3        
        //   440: invokevirtual   com/jetbrains/cidr/lang/symbols/OCResolveContext.getElement:()Lcom/intellij/psi/PsiElement;
        //   443: invokevirtual   com/jetbrains/cidr/lang/types/OCType.isNumberCompatible:(Lcom/intellij/psi/PsiElement;)Z
        //   446: ifeq            455
        //   449: aload_1        
        //   450: astore          4
        //   452: goto            528
        //   455: aload_1        
        //   456: aload_3        
        //   457: invokestatic    com/jetbrains/cidr/lang/psi/impl/OCUnaryExpressionImpl.a:(Lcom/jetbrains/cidr/lang/types/OCType;Lcom/jetbrains/cidr/lang/symbols/OCResolveContext;)Lcom/jetbrains/cidr/lang/types/OCType;
        //   460: astore          4
        //   462: goto            528
        //   465: aload_0        
        //   466: getstatic       com/jetbrains/cidr/lang/parser/OCTokenTypes.EXCL:Lcom/jetbrains/cidr/lang/parser/OCPunctuatorElementType;
        //   469: if_acmpne       515
        //   472: aload_1        
        //   473: aload_3        
        //   474: invokevirtual   com/jetbrains/cidr/lang/symbols/OCResolveContext.getElement:()Lcom/intellij/psi/PsiElement;
        //   477: invokevirtual   com/jetbrains/cidr/lang/types/OCType.isNumberCompatible:(Lcom/intellij/psi/PsiElement;)Z
        //   480: ifeq            505
        //   483: goto            490
        //   486: invokestatic    com/jetbrains/cidr/lang/psi/impl/OCUnaryExpressionImpl.e:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   489: athrow         
        //   490: aload_3        
        //   491: invokevirtual   com/jetbrains/cidr/lang/symbols/OCResolveContext.getElement:()Lcom/intellij/psi/PsiElement;
        //   494: checkcast       Lcom/jetbrains/cidr/lang/psi/OCElement;
        //   497: invokestatic    com/jetbrains/cidr/lang/types/OCIntType.getAppropriateBool:(Lcom/jetbrains/cidr/lang/psi/OCElement;)Lcom/jetbrains/cidr/lang/types/OCIntType;
        //   500: astore          4
        //   502: goto            528
        //   505: aload_1        
        //   506: aload_3        
        //   507: invokestatic    com/jetbrains/cidr/lang/psi/impl/OCUnaryExpressionImpl.a:(Lcom/jetbrains/cidr/lang/types/OCType;Lcom/jetbrains/cidr/lang/symbols/OCResolveContext;)Lcom/jetbrains/cidr/lang/types/OCType;
        //   510: astore          4
        //   512: goto            528
        //   515: getstatic       com/jetbrains/cidr/lang/psi/impl/OCUnaryExpressionImpl.LOG:Lcom/intellij/openapi/diagnostic/Logger;
        //   518: ldc             "Unknown operation sign"
        //   520: invokevirtual   com/intellij/openapi/diagnostic/Logger.error:(Ljava/lang/String;)V
        //   523: getstatic       com/jetbrains/cidr/lang/types/OCUnknownType.INSTANCE:Lcom/jetbrains/cidr/lang/types/OCUnknownType;
        //   526: astore          4
        //   528: aload           4
        //   530: instanceof      Lcom/jetbrains/cidr/lang/types/OCNumericType;
        //   533: ifeq            547
        //   536: aload           4
        //   538: aload_3        
        //   539: invokevirtual   com/jetbrains/cidr/lang/symbols/OCResolveContext.getProject:()Lcom/intellij/openapi/project/Project;
        //   542: invokevirtual   com/jetbrains/cidr/lang/types/OCType.cloneWithoutCVQualifiers:(Lcom/intellij/openapi/project/Project;)Lcom/jetbrains/cidr/lang/types/OCType;
        //   545: astore          4
        //   547: aload           4
        //   549: areturn        
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                                
        //  -----  -----  -----  -----  ------------------------------------
        //  0      40     40     44     Ljava/lang/IllegalArgumentException;
        //  60     75     78     82     Ljava/lang/IllegalArgumentException;
        //  95     109    112    116    Ljava/lang/IllegalArgumentException;
        //  138    152    155    159    Ljava/lang/IllegalArgumentException;
        //  145    166    169    173    Ljava/lang/IllegalArgumentException;
        //  222    236    239    243    Ljava/lang/IllegalArgumentException;
        //  253    269    272    276    Ljava/lang/IllegalArgumentException;
        //  261    287    290    294    Ljava/lang/IllegalArgumentException;
        //  276    308    311    315    Ljava/lang/IllegalArgumentException;
        //  378    392    395    399    Ljava/lang/IllegalArgumentException;
        //  405    419    422    426    Ljava/lang/IllegalArgumentException;
        //  465    483    486    490    Ljava/lang/IllegalArgumentException;
        // 
        // The error that occurred was:
        // 
        // java.lang.IllegalStateException: Expression is linked from several locations: Label_0276:
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
    
    private static OCType a(final OCType ocType, @NotNull final OCResolveContext ocResolveContext) {
        try {
            if (ocResolveContext == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "context", "com/jetbrains/cidr/lang/psi/impl/OCUnaryExpressionImpl", "unknownOrMagic"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw e(ex);
        }
        try {
            if (ocType.isMagicInside(ocResolveContext)) {
                return new OCMagicType(ocType);
            }
        }
        catch (IllegalArgumentException ex2) {
            throw e(ex2);
        }
        return OCUnknownType.INSTANCE;
    }
    
    @Override
    protected List<OCExpression> getDependentExpressions() {
        return Collections.singletonList(this.getOperand());
    }
    
    static {
        LOG = Logger.getInstance("#com.jetbrains.cidr.lang.psi.impl.OCUnaryExpressionImpl");
    }
    
    private static IllegalArgumentException e(final IllegalArgumentException ex) {
        return ex;
    }
}
