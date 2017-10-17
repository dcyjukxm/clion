// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.lang.psi.impl;

import com.intellij.psi.PsiReference;
import java.util.Arrays;
import java.util.List;
import com.jetbrains.cidr.lang.types.OCMagicType;
import java.util.Iterator;
import com.intellij.psi.PsiElement;
import com.jetbrains.cidr.lang.types.OCUnknownType;
import com.jetbrains.cidr.lang.symbols.cpp.OCFunctionSymbol;
import com.jetbrains.cidr.lang.symbols.OCSymbol;
import com.jetbrains.cidr.lang.types.OCType;
import com.jetbrains.cidr.lang.symbols.OCResolveContext;
import com.jetbrains.cidr.lang.psi.visitors.OCVisitor;
import com.jetbrains.cidr.lang.parser.OCElementType;
import com.intellij.psi.tree.TokenSet;
import com.jetbrains.cidr.lang.parser.OCPunctuatorElementType;
import com.intellij.psi.tree.IElementType;
import com.jetbrains.cidr.lang.parser.OCElementTypes;
import org.jetbrains.annotations.Nullable;
import com.jetbrains.cidr.lang.psi.OCElement;
import com.jetbrains.cidr.lang.psi.OCExpression;
import org.jetbrains.annotations.NotNull;
import com.intellij.lang.ASTNode;
import com.jetbrains.cidr.lang.psi.OCBinaryExpression;
import com.jetbrains.cidr.lang.resolve.references.OCOperatorReference;

public class OCBinaryExpressionImpl extends OCExpressionWithReferenceBase<OCOperatorReference> implements OCBinaryExpression
{
    public OCBinaryExpressionImpl(@NotNull final ASTNode astNode) {
        if (astNode == null) {
            throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "node", "com/jetbrains/cidr/lang/psi/impl/OCBinaryExpressionImpl", "<init>"));
        }
        super(astNode);
    }
    
    @Nullable
    @Override
    protected OCOperatorReference createReference() {
        final String name = this.getOperationSign().getName();
        final OCExpression left = this.getLeft();
        final OCExpression right = this.getRight();
        Label_0033: {
            try {
                if (left == null) {
                    return null;
                }
                final OCExpression ocExpression = right;
                if (ocExpression != null) {
                    break Label_0033;
                }
                return null;
            }
            catch (IllegalArgumentException ex) {
                throw a(ex);
            }
            try {
                final OCExpression ocExpression = right;
                if (ocExpression != null) {
                    return new OCOperatorReference(this, name, OCOperatorReference.OperatorPlacement.INFIX, this.getOperationSignNode().getPsi(), new OCExpression[] { left, right });
                }
            }
            catch (IllegalArgumentException ex2) {
                throw a(ex2);
            }
        }
        return null;
    }
    
    @Nullable
    @Override
    public OCExpression getLeft() {
        for (ASTNode astNode = this.getNode().getFirstChildNode(); astNode != null; astNode = astNode.getTreeNext()) {
            final IElementType elementType = astNode.getElementType();
            try {
                if (OCElementTypes.EXPRESSIONS.contains(elementType)) {
                    return (OCExpression)astNode.getPsi();
                }
            }
            catch (IllegalArgumentException ex) {
                throw a(ex);
            }
        }
        return null;
    }
    
    @Override
    public OCExpression getRight() {
        boolean b = false;
        for (ASTNode astNode = this.getNode().getFirstChildNode(); astNode != null; astNode = astNode.getTreeNext()) {
            final IElementType elementType = astNode.getElementType();
            if (elementType instanceof OCPunctuatorElementType) {
                b = true;
            }
            else {
                Label_0056: {
                    try {
                        if (!b) {
                            continue;
                        }
                        final TokenSet set = OCElementTypes.EXPRESSIONS;
                        final IElementType elementType2 = elementType;
                        final boolean b2 = set.contains(elementType2);
                        if (b2) {
                            break Label_0056;
                        }
                        continue;
                    }
                    catch (IllegalArgumentException ex) {
                        throw a(ex);
                    }
                    try {
                        final TokenSet set = OCElementTypes.EXPRESSIONS;
                        final IElementType elementType2 = elementType;
                        final boolean b2 = set.contains(elementType2);
                        if (b2) {
                            return (OCExpression)astNode.getPsi();
                        }
                    }
                    catch (IllegalArgumentException ex2) {
                        throw a(ex2);
                    }
                }
            }
        }
        return null;
    }
    
    @NotNull
    @Override
    public ASTNode getOperationSignNode() {
        for (ASTNode astNode = this.getNode().getFirstChildNode(); astNode != null; astNode = astNode.getTreeNext()) {
            final IElementType elementType = astNode.getElementType();
            ASTNode astNode2 = null;
            Label_0040: {
                try {
                    if (!(elementType instanceof OCPunctuatorElementType)) {
                        continue;
                    }
                    astNode2 = astNode;
                    if (astNode2 == null) {
                        break Label_0040;
                    }
                    return astNode2;
                }
                catch (IllegalArgumentException ex) {
                    throw a(ex);
                }
                try {
                    astNode2 = astNode;
                    if (astNode2 == null) {
                        throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/psi/impl/OCBinaryExpressionImpl", "getOperationSignNode"));
                    }
                }
                catch (IllegalArgumentException ex2) {
                    throw a(ex2);
                }
            }
            return astNode2;
        }
        throw new AssertionError((Object)("Cannot find operation sign in binary expression: " + this.getText()));
    }
    
    @NotNull
    @Override
    public OCElementType getOperationSign() {
        OCElementType ocElementType;
        try {
            ocElementType = (OCElementType)this.getOperationSignNode().getElementType();
            if (ocElementType == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/psi/impl/OCBinaryExpressionImpl", "getOperationSign"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        return ocElementType;
    }
    
    @Override
    public void accept(@NotNull final OCVisitor ocVisitor) {
        try {
            if (ocVisitor == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "visitor", "com/jetbrains/cidr/lang/psi/impl/OCBinaryExpressionImpl", "accept"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        ocVisitor.visitBinaryExpression(this);
    }
    
    @NotNull
    @Override
    public OCType getType(@NotNull final OCResolveContext ocResolveContext) {
        try {
            if (ocResolveContext == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "context", "com/jetbrains/cidr/lang/psi/impl/OCBinaryExpressionImpl", "getType"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
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
                        throw a(ex2);
                    }
                    try {
                        final OCSymbol ocSymbol2 = ocSymbol;
                        ocType = ocSymbol2.getEffectiveType();
                        if (ocType == null) {
                            throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/psi/impl/OCBinaryExpressionImpl", "getType"));
                        }
                    }
                    catch (IllegalArgumentException ex3) {
                        throw a(ex3);
                    }
                }
                return ocType;
            }
        }
        final OCExpression left = this.getLeft();
        final OCExpression right = this.getRight();
        Label_0293: {
            OCType ocType4 = null;
            Label_0258: {
                Label_0235: {
                    OCType ocType2 = null;
                    Label_0196: {
                        Label_0178: {
                            try {
                                if (left != null) {
                                    break Label_0235;
                                }
                                final OCExpression ocExpression = right;
                                if (ocExpression != null) {
                                    break Label_0178;
                                }
                                break Label_0178;
                            }
                            catch (IllegalArgumentException ex4) {
                                throw a(ex4);
                            }
                            try {
                                final OCExpression ocExpression = right;
                                if (ocExpression != null) {
                                    final OCType ocType3;
                                    ocType2 = (ocType3 = right.getType(ocResolveContext));
                                    break Label_0196;
                                }
                            }
                            catch (IllegalArgumentException ex5) {
                                throw a(ex5);
                            }
                        }
                        OCType ocType3;
                        ocType2 = (ocType3 = OCUnknownType.INSTANCE);
                        try {
                            if (ocType3 == null) {
                                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/psi/impl/OCBinaryExpressionImpl", "getType"));
                            }
                        }
                        catch (IllegalArgumentException ex6) {
                            throw a(ex6);
                        }
                    }
                    return ocType2;
                    try {
                        if (right != null) {
                            break Label_0293;
                        }
                        final OCExpression ocExpression2 = left;
                        final OCResolveContext ocResolveContext2 = ocResolveContext;
                        ocType4 = ocExpression2.getType(ocResolveContext2);
                        if (ocType4 == null) {
                            break Label_0258;
                        }
                        return ocType4;
                    }
                    catch (IllegalArgumentException ex7) {
                        throw a(ex7);
                    }
                }
                try {
                    final OCExpression ocExpression2 = left;
                    final OCResolveContext ocResolveContext2 = ocResolveContext;
                    ocType4 = ocExpression2.getType(ocResolveContext2);
                    if (ocType4 == null) {
                        throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/psi/impl/OCBinaryExpressionImpl", "getType"));
                    }
                }
                catch (IllegalArgumentException ex8) {
                    throw a(ex8);
                }
            }
            return ocType4;
        }
        final OCElementType operationSign = this.getOperationSign();
        final OCType resolvedType = left.getResolvedType(ocResolveContext);
        final OCType resolvedType2 = right.getResolvedType(ocResolveContext);
        OCType binaryExprType;
        try {
            binaryExprType = getBinaryExprType(operationSign, resolvedType, resolvedType2, (PsiElement)this);
            if (binaryExprType == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/psi/impl/OCBinaryExpressionImpl", "getType"));
            }
        }
        catch (IllegalArgumentException ex9) {
            throw a(ex9);
        }
        return binaryExprType;
    }
    
    public static OCType getBinaryExprType(final OCElementType p0, final OCType p1, final OCType p2, @Nullable final PsiElement p3) {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     0: new             Lcom/jetbrains/cidr/lang/symbols/OCResolveContext;
        //     3: dup            
        //     4: aload_3        
        //     5: invokespecial   com/jetbrains/cidr/lang/symbols/OCResolveContext.<init>:(Lcom/intellij/psi/PsiElement;)V
        //     8: astore          5
        //    10: aload_1        
        //    11: aload           5
        //    13: invokevirtual   com/jetbrains/cidr/lang/types/OCType.isUnresolved:(Lcom/jetbrains/cidr/lang/symbols/OCResolveContext;)Z
        //    16: ifne            35
        //    19: aload_2        
        //    20: aload           5
        //    22: invokevirtual   com/jetbrains/cidr/lang/types/OCType.isUnresolved:(Lcom/jetbrains/cidr/lang/symbols/OCResolveContext;)Z
        //    25: ifeq            43
        //    28: goto            35
        //    31: invokestatic    com/jetbrains/cidr/lang/psi/impl/OCBinaryExpressionImpl.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //    34: athrow         
        //    35: getstatic       com/jetbrains/cidr/lang/types/OCUnknownType.INSTANCE:Lcom/jetbrains/cidr/lang/types/OCUnknownType;
        //    38: astore          4
        //    40: goto            519
        //    43: getstatic       com/jetbrains/cidr/lang/parser/OCTokenTypes.LOGIC_OPERATIONS:Lcom/intellij/psi/tree/TokenSet;
        //    46: aload_0        
        //    47: invokevirtual   com/intellij/psi/tree/TokenSet.contains:(Lcom/intellij/psi/tree/IElementType;)Z
        //    50: ifne            70
        //    53: getstatic       com/jetbrains/cidr/lang/parser/OCTokenTypes.COMPARISON_OPERATIONS:Lcom/intellij/psi/tree/TokenSet;
        //    56: aload_0        
        //    57: invokevirtual   com/intellij/psi/tree/TokenSet.contains:(Lcom/intellij/psi/tree/IElementType;)Z
        //    60: ifeq            82
        //    63: goto            70
        //    66: invokestatic    com/jetbrains/cidr/lang/psi/impl/OCBinaryExpressionImpl.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //    69: athrow         
        //    70: aload_3        
        //    71: checkcast       Lcom/jetbrains/cidr/lang/psi/OCElement;
        //    74: invokestatic    com/jetbrains/cidr/lang/types/OCIntType.getAppropriateBool:(Lcom/jetbrains/cidr/lang/psi/OCElement;)Lcom/jetbrains/cidr/lang/types/OCIntType;
        //    77: astore          4
        //    79: goto            519
        //    82: aload_1        
        //    83: aload_3        
        //    84: invokevirtual   com/jetbrains/cidr/lang/types/OCType.isSubclassOfMagic:(Lcom/intellij/psi/PsiElement;)Z
        //    87: ifne            105
        //    90: aload_2        
        //    91: aload_3        
        //    92: invokevirtual   com/jetbrains/cidr/lang/types/OCType.isSubclassOfMagic:(Lcom/intellij/psi/PsiElement;)Z
        //    95: ifeq            118
        //    98: goto            105
        //   101: invokestatic    com/jetbrains/cidr/lang/psi/impl/OCBinaryExpressionImpl.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   104: athrow         
        //   105: new             Lcom/jetbrains/cidr/lang/types/OCMagicType;
        //   108: dup            
        //   109: aload_1        
        //   110: invokespecial   com/jetbrains/cidr/lang/types/OCMagicType.<init>:(Lcom/jetbrains/cidr/lang/types/OCType;)V
        //   113: astore          4
        //   115: goto            519
        //   118: aload_0        
        //   119: getstatic       com/jetbrains/cidr/lang/parser/OCTokenTypes.DOT_MUL:Lcom/jetbrains/cidr/lang/parser/OCPunctuatorElementType;
        //   122: if_acmpeq       139
        //   125: aload_0        
        //   126: getstatic       com/jetbrains/cidr/lang/parser/OCTokenTypes.DEREF_MUL:Lcom/jetbrains/cidr/lang/parser/OCPunctuatorElementType;
        //   129: if_acmpne       192
        //   132: goto            139
        //   135: invokestatic    com/jetbrains/cidr/lang/psi/impl/OCBinaryExpressionImpl.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   138: athrow         
        //   139: aload_2        
        //   140: instanceof      Lcom/jetbrains/cidr/lang/types/OCCppReferenceType;
        //   143: ifeq            161
        //   146: goto            153
        //   149: invokestatic    com/jetbrains/cidr/lang/psi/impl/OCBinaryExpressionImpl.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   152: athrow         
        //   153: aload_2        
        //   154: checkcast       Lcom/jetbrains/cidr/lang/types/OCCppReferenceType;
        //   157: invokevirtual   com/jetbrains/cidr/lang/types/OCCppReferenceType.getRefType:()Lcom/jetbrains/cidr/lang/types/OCType;
        //   160: astore_2       
        //   161: aload_2        
        //   162: instanceof      Lcom/jetbrains/cidr/lang/types/OCPointerType;
        //   165: ifeq            180
        //   168: aload_2        
        //   169: checkcast       Lcom/jetbrains/cidr/lang/types/OCPointerType;
        //   172: invokevirtual   com/jetbrains/cidr/lang/types/OCPointerType.getRefType:()Lcom/jetbrains/cidr/lang/types/OCType;
        //   175: astore          4
        //   177: goto            519
        //   180: aload_1        
        //   181: aload_2        
        //   182: aload           5
        //   184: invokestatic    com/jetbrains/cidr/lang/psi/impl/OCBinaryExpressionImpl.a:(Lcom/jetbrains/cidr/lang/types/OCType;Lcom/jetbrains/cidr/lang/types/OCType;Lcom/jetbrains/cidr/lang/symbols/OCResolveContext;)Lcom/jetbrains/cidr/lang/types/OCType;
        //   187: astore          4
        //   189: goto            519
        //   192: aload_1        
        //   193: instanceof      Lcom/jetbrains/cidr/lang/types/OCCppReferenceType;
        //   196: ifeq            207
        //   199: aload_1        
        //   200: checkcast       Lcom/jetbrains/cidr/lang/types/OCCppReferenceType;
        //   203: invokevirtual   com/jetbrains/cidr/lang/types/OCCppReferenceType.getRefType:()Lcom/jetbrains/cidr/lang/types/OCType;
        //   206: astore_1       
        //   207: aload_2        
        //   208: instanceof      Lcom/jetbrains/cidr/lang/types/OCCppReferenceType;
        //   211: ifeq            222
        //   214: aload_2        
        //   215: checkcast       Lcom/jetbrains/cidr/lang/types/OCCppReferenceType;
        //   218: invokevirtual   com/jetbrains/cidr/lang/types/OCCppReferenceType.getRefType:()Lcom/jetbrains/cidr/lang/types/OCType;
        //   221: astore_2       
        //   222: aload_1        
        //   223: instanceof      Lcom/jetbrains/cidr/lang/types/OCNumericType;
        //   226: ifeq            366
        //   229: aload_2        
        //   230: instanceof      Lcom/jetbrains/cidr/lang/types/OCNumericType;
        //   233: ifeq            366
        //   236: goto            243
        //   239: invokestatic    com/jetbrains/cidr/lang/psi/impl/OCBinaryExpressionImpl.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   242: athrow         
        //   243: aload_0        
        //   244: getstatic       com/jetbrains/cidr/lang/parser/OCTokenTypes.PLUS:Lcom/jetbrains/cidr/lang/parser/OCPunctuatorElementType;
        //   247: if_acmpeq       355
        //   250: goto            257
        //   253: invokestatic    com/jetbrains/cidr/lang/psi/impl/OCBinaryExpressionImpl.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   256: athrow         
        //   257: aload_0        
        //   258: getstatic       com/jetbrains/cidr/lang/parser/OCTokenTypes.MINUS:Lcom/jetbrains/cidr/lang/parser/OCPunctuatorElementType;
        //   261: if_acmpeq       355
        //   264: goto            271
        //   267: invokestatic    com/jetbrains/cidr/lang/psi/impl/OCBinaryExpressionImpl.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   270: athrow         
        //   271: aload_0        
        //   272: getstatic       com/jetbrains/cidr/lang/parser/OCTokenTypes.DIV:Lcom/jetbrains/cidr/lang/parser/OCPunctuatorElementType;
        //   275: if_acmpeq       355
        //   278: goto            285
        //   281: invokestatic    com/jetbrains/cidr/lang/psi/impl/OCBinaryExpressionImpl.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   284: athrow         
        //   285: aload_0        
        //   286: getstatic       com/jetbrains/cidr/lang/parser/OCTokenTypes.PERC:Lcom/jetbrains/cidr/lang/parser/OCPunctuatorElementType;
        //   289: if_acmpeq       355
        //   292: goto            299
        //   295: invokestatic    com/jetbrains/cidr/lang/psi/impl/OCBinaryExpressionImpl.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   298: athrow         
        //   299: aload_0        
        //   300: getstatic       com/jetbrains/cidr/lang/parser/OCTokenTypes.MUL:Lcom/jetbrains/cidr/lang/parser/OCPunctuatorElementType;
        //   303: if_acmpeq       355
        //   306: goto            313
        //   309: invokestatic    com/jetbrains/cidr/lang/psi/impl/OCBinaryExpressionImpl.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   312: athrow         
        //   313: aload_0        
        //   314: getstatic       com/jetbrains/cidr/lang/parser/OCTokenTypes.AND:Lcom/jetbrains/cidr/lang/parser/OCPunctuatorElementType;
        //   317: if_acmpeq       355
        //   320: goto            327
        //   323: invokestatic    com/jetbrains/cidr/lang/psi/impl/OCBinaryExpressionImpl.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   326: athrow         
        //   327: aload_0        
        //   328: getstatic       com/jetbrains/cidr/lang/parser/OCTokenTypes.OR:Lcom/jetbrains/cidr/lang/parser/OCPunctuatorElementType;
        //   331: if_acmpeq       355
        //   334: goto            341
        //   337: invokestatic    com/jetbrains/cidr/lang/psi/impl/OCBinaryExpressionImpl.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   340: athrow         
        //   341: aload_0        
        //   342: getstatic       com/jetbrains/cidr/lang/parser/OCTokenTypes.XOR:Lcom/jetbrains/cidr/lang/parser/OCPunctuatorElementType;
        //   345: if_acmpne       366
        //   348: goto            355
        //   351: invokestatic    com/jetbrains/cidr/lang/psi/impl/OCBinaryExpressionImpl.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   354: athrow         
        //   355: aload_1        
        //   356: aload_2        
        //   357: aload_3        
        //   358: invokevirtual   com/jetbrains/cidr/lang/types/OCType.getLeastCommonType:(Lcom/jetbrains/cidr/lang/types/OCType;Lcom/intellij/psi/PsiElement;)Lcom/jetbrains/cidr/lang/types/OCType;
        //   361: astore          4
        //   363: goto            519
        //   366: aload_1        
        //   367: instanceof      Lcom/jetbrains/cidr/lang/types/OCPointerType;
        //   370: ifeq            395
        //   373: aload_2        
        //   374: instanceof      Lcom/jetbrains/cidr/lang/types/OCPointerType;
        //   377: ifeq            395
        //   380: goto            387
        //   383: invokestatic    com/jetbrains/cidr/lang/psi/impl/OCBinaryExpressionImpl.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   386: athrow         
        //   387: getstatic       com/jetbrains/cidr/lang/types/OCIntType.ULONG:Lcom/jetbrains/cidr/lang/types/OCIntType;
        //   390: astore          4
        //   392: goto            519
        //   395: aload_1        
        //   396: instanceof      Lcom/jetbrains/cidr/lang/types/OCPointerType;
        //   399: ifeq            423
        //   402: aload_2        
        //   403: aload_3        
        //   404: invokevirtual   com/jetbrains/cidr/lang/types/OCType.isIntegerCompatible:(Lcom/intellij/psi/PsiElement;)Z
        //   407: ifeq            423
        //   410: goto            417
        //   413: invokestatic    com/jetbrains/cidr/lang/psi/impl/OCBinaryExpressionImpl.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   416: athrow         
        //   417: aload_1        
        //   418: astore          4
        //   420: goto            519
        //   423: aload_2        
        //   424: instanceof      Lcom/jetbrains/cidr/lang/types/OCPointerType;
        //   427: ifeq            451
        //   430: aload_1        
        //   431: aload_3        
        //   432: invokevirtual   com/jetbrains/cidr/lang/types/OCType.isIntegerCompatible:(Lcom/intellij/psi/PsiElement;)Z
        //   435: ifeq            451
        //   438: goto            445
        //   441: invokestatic    com/jetbrains/cidr/lang/psi/impl/OCBinaryExpressionImpl.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   444: athrow         
        //   445: aload_2        
        //   446: astore          4
        //   448: goto            519
        //   451: aload_1        
        //   452: aload_3        
        //   453: invokevirtual   com/jetbrains/cidr/lang/types/OCType.isNumberCompatible:(Lcom/intellij/psi/PsiElement;)Z
        //   456: ifne            474
        //   459: aload_1        
        //   460: aload_3        
        //   461: invokevirtual   com/jetbrains/cidr/lang/types/OCType.isPointerCompatible:(Lcom/intellij/psi/PsiElement;)Z
        //   464: ifeq            510
        //   467: goto            474
        //   470: invokestatic    com/jetbrains/cidr/lang/psi/impl/OCBinaryExpressionImpl.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   473: athrow         
        //   474: aload_2        
        //   475: aload_3        
        //   476: invokevirtual   com/jetbrains/cidr/lang/types/OCType.isNumberCompatible:(Lcom/intellij/psi/PsiElement;)Z
        //   479: ifne            504
        //   482: goto            489
        //   485: invokestatic    com/jetbrains/cidr/lang/psi/impl/OCBinaryExpressionImpl.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   488: athrow         
        //   489: aload_2        
        //   490: aload_3        
        //   491: invokevirtual   com/jetbrains/cidr/lang/types/OCType.isPointerCompatible:(Lcom/intellij/psi/PsiElement;)Z
        //   494: ifeq            510
        //   497: goto            504
        //   500: invokestatic    com/jetbrains/cidr/lang/psi/impl/OCBinaryExpressionImpl.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   503: athrow         
        //   504: aload_1        
        //   505: astore          4
        //   507: goto            519
        //   510: aload_1        
        //   511: aload_2        
        //   512: aload           5
        //   514: invokestatic    com/jetbrains/cidr/lang/psi/impl/OCBinaryExpressionImpl.a:(Lcom/jetbrains/cidr/lang/types/OCType;Lcom/jetbrains/cidr/lang/types/OCType;Lcom/jetbrains/cidr/lang/symbols/OCResolveContext;)Lcom/jetbrains/cidr/lang/types/OCType;
        //   517: astore          4
        //   519: aload           4
        //   521: instanceof      Lcom/jetbrains/cidr/lang/types/OCNumericType;
        //   524: ifeq            559
        //   527: aload           4
        //   529: aload_3        
        //   530: ifnull          553
        //   533: goto            540
        //   536: invokestatic    com/jetbrains/cidr/lang/psi/impl/OCBinaryExpressionImpl.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   539: athrow         
        //   540: aload_3        
        //   541: invokeinterface com/intellij/psi/PsiElement.getProject:()Lcom/intellij/openapi/project/Project;
        //   546: goto            554
        //   549: invokestatic    com/jetbrains/cidr/lang/psi/impl/OCBinaryExpressionImpl.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   552: athrow         
        //   553: aconst_null    
        //   554: invokevirtual   com/jetbrains/cidr/lang/types/OCType.cloneWithoutCVQualifiers:(Lcom/intellij/openapi/project/Project;)Lcom/jetbrains/cidr/lang/types/OCType;
        //   557: astore          4
        //   559: aload           4
        //   561: areturn        
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                                
        //  -----  -----  -----  -----  ------------------------------------
        //  10     28     31     35     Ljava/lang/IllegalArgumentException;
        //  43     63     66     70     Ljava/lang/IllegalArgumentException;
        //  82     98     101    105    Ljava/lang/IllegalArgumentException;
        //  118    132    135    139    Ljava/lang/IllegalArgumentException;
        //  125    146    149    153    Ljava/lang/IllegalArgumentException;
        //  222    236    239    243    Ljava/lang/IllegalArgumentException;
        //  229    250    253    257    Ljava/lang/IllegalArgumentException;
        //  243    264    267    271    Ljava/lang/IllegalArgumentException;
        //  257    278    281    285    Ljava/lang/IllegalArgumentException;
        //  271    292    295    299    Ljava/lang/IllegalArgumentException;
        //  285    306    309    313    Ljava/lang/IllegalArgumentException;
        //  299    320    323    327    Ljava/lang/IllegalArgumentException;
        //  313    334    337    341    Ljava/lang/IllegalArgumentException;
        //  327    348    351    355    Ljava/lang/IllegalArgumentException;
        //  366    380    383    387    Ljava/lang/IllegalArgumentException;
        //  395    410    413    417    Ljava/lang/IllegalArgumentException;
        //  423    438    441    445    Ljava/lang/IllegalArgumentException;
        //  451    467    470    474    Ljava/lang/IllegalArgumentException;
        //  459    482    485    489    Ljava/lang/IllegalArgumentException;
        //  474    497    500    504    Ljava/lang/IllegalArgumentException;
        //  519    533    536    540    Ljava/lang/IllegalArgumentException;
        //  527    549    549    553    Ljava/lang/IllegalArgumentException;
        // 
        // The error that occurred was:
        // 
        // java.lang.IllegalStateException: Expression is linked from several locations: Label_0243:
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
    
    private static OCType a(final OCType ocType, final OCType ocType2, @NotNull final OCResolveContext ocResolveContext) {
        try {
            if (ocResolveContext == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "context", "com/jetbrains/cidr/lang/psi/impl/OCBinaryExpressionImpl", "unknownOrMagic"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        Label_0067: {
            try {
                if (ocType.isMagicInside(ocResolveContext)) {
                    break Label_0067;
                }
                final OCType ocType3 = ocType2;
                final OCResolveContext ocResolveContext2 = ocResolveContext;
                final boolean b = ocType3.isMagicInside(ocResolveContext2);
                if (b) {
                    break Label_0067;
                }
                return OCUnknownType.INSTANCE;
            }
            catch (IllegalArgumentException ex2) {
                throw a(ex2);
            }
            try {
                final OCType ocType3 = ocType2;
                final OCResolveContext ocResolveContext2 = ocResolveContext;
                final boolean b = ocType3.isMagicInside(ocResolveContext2);
                if (b) {
                    return new OCMagicType(ocType);
                }
            }
            catch (IllegalArgumentException ex3) {
                throw a(ex3);
            }
        }
        return OCUnknownType.INSTANCE;
    }
    
    @Override
    protected List<OCExpression> getDependentExpressions() {
        return Arrays.asList(this.getLeft(), this.getRight());
    }
    
    private static IllegalArgumentException a(final IllegalArgumentException ex) {
        return ex;
    }
}
