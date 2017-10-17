// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.lang.psi.impl;

import com.jetbrains.cidr.lang.types.OCPointerType;
import com.jetbrains.cidr.lang.types.OCBlockPointerType;
import com.jetbrains.cidr.lang.types.OCCppReferenceType;
import com.jetbrains.cidr.lang.types.OCUnknownType;
import com.intellij.psi.PsiElement;
import com.jetbrains.cidr.lang.types.OCTypeOwner;
import com.jetbrains.cidr.lang.util.OCExpressionEvaluator;
import com.jetbrains.cidr.lang.types.OCType;
import com.jetbrains.cidr.lang.symbols.OCResolveContext;
import com.jetbrains.cidr.lang.psi.visitors.OCVisitor;
import org.jetbrains.annotations.Nullable;
import com.intellij.psi.tree.IElementType;
import com.jetbrains.cidr.lang.parser.OCTokenTypes;
import com.jetbrains.cidr.lang.parser.OCElementTypes;
import com.jetbrains.cidr.lang.psi.OCExpression;
import org.jetbrains.annotations.NotNull;
import com.intellij.lang.ASTNode;
import com.jetbrains.cidr.lang.psi.OCConditionalExpression;

public class OCConditionalExpressionImpl extends OCExpressionBase implements OCConditionalExpression
{
    static final /* synthetic */ boolean $assertionsDisabled;
    
    public OCConditionalExpressionImpl(@NotNull final ASTNode astNode) {
        if (astNode == null) {
            throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "node", "com/jetbrains/cidr/lang/psi/impl/OCConditionalExpressionImpl", "<init>"));
        }
        super(astNode);
    }
    
    @NotNull
    @Override
    public OCExpression getCondition() {
        final OCExpression a = this.a(0);
        OCExpression ocExpression2 = null;
        Label_0035: {
            Label_0023: {
                try {
                    if (OCConditionalExpressionImpl.$assertionsDisabled) {
                        break Label_0035;
                    }
                    final OCExpression ocExpression = a;
                    if (ocExpression == null) {
                        break Label_0023;
                    }
                    break Label_0035;
                }
                catch (IllegalArgumentException ex) {
                    throw a(ex);
                }
                try {
                    final OCExpression ocExpression = a;
                    if (ocExpression == null) {
                        throw new AssertionError();
                    }
                }
                catch (IllegalArgumentException ex2) {
                    throw a(ex2);
                }
            }
            try {
                ocExpression2 = a;
                if (ocExpression2 == null) {
                    throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/psi/impl/OCConditionalExpressionImpl", "getCondition"));
                }
            }
            catch (IllegalArgumentException ex3) {
                throw a(ex3);
            }
        }
        return ocExpression2;
    }
    
    @Override
    public OCExpression getPositiveExpression(final boolean b) {
        final OCExpression a = this.a(1);
        Label_0021: {
            try {
                if (a != null) {
                    break Label_0021;
                }
                final boolean b2 = b;
                if (!b2) {
                    break Label_0021;
                }
                return this.getCondition();
            }
            catch (IllegalArgumentException ex) {
                throw a(ex);
            }
            try {
                final boolean b2 = b;
                if (!b2) {
                    return a;
                }
            }
            catch (IllegalArgumentException ex2) {
                throw a(ex2);
            }
        }
        return this.getCondition();
    }
    
    @Override
    public OCExpression getNegativeExpression() {
        return this.a(2);
    }
    
    @Nullable
    private OCExpression a(final int n) {
        int n2 = 0;
        for (ASTNode astNode = this.getNode().getFirstChildNode(); astNode != null; astNode = astNode.getTreeNext()) {
            final IElementType elementType = astNode.getElementType();
            Label_0085: {
                Label_0061: {
                    Label_0047: {
                        try {
                            if (!OCElementTypes.EXPRESSIONS.contains(elementType)) {
                                break Label_0061;
                            }
                            final int n3 = n2;
                            final int n4 = n;
                            if (n3 == n4) {
                                break Label_0047;
                            }
                            break Label_0061;
                        }
                        catch (IllegalArgumentException ex) {
                            throw a(ex);
                        }
                        try {
                            final int n3 = n2;
                            final int n4 = n;
                            if (n3 == n4) {
                                return (OCExpression)astNode.getPsi();
                            }
                        }
                        catch (IllegalArgumentException ex2) {
                            throw a(ex2);
                        }
                    }
                    try {
                        if (n2 != 0 || elementType != OCTokenTypes.QUEST) {
                            break Label_0085;
                        }
                    }
                    catch (IllegalArgumentException ex3) {
                        throw a(ex3);
                    }
                }
                n2 = 1;
                continue;
                try {
                    if (n2 != 1 || elementType != OCTokenTypes.COLON) {
                        continue;
                    }
                }
                catch (IllegalArgumentException ex4) {
                    throw a(ex4);
                }
            }
            n2 = 2;
        }
        return null;
    }
    
    @Override
    public void accept(@NotNull final OCVisitor ocVisitor) {
        try {
            if (ocVisitor == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "visitor", "com/jetbrains/cidr/lang/psi/impl/OCConditionalExpressionImpl", "accept"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        ocVisitor.visitConditionalExpression(this);
    }
    
    @NotNull
    @Override
    public OCType getType(@NotNull final OCResolveContext ocResolveContext) {
        try {
            if (ocResolveContext == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "context", "com/jetbrains/cidr/lang/psi/impl/OCConditionalExpressionImpl", "getType"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        final OCExpression positiveExpression = this.getPositiveExpression(true);
        final OCExpression negativeExpression = this.getNegativeExpression();
        OCUnknownType instance = null;
        Label_0145: {
            try {
                if (positiveExpression == null || negativeExpression == null) {
                    break Label_0145;
                }
            }
            catch (IllegalArgumentException ex2) {
                throw a(ex2);
            }
            final OCType resolvedType = positiveExpression.getResolvedType(ocResolveContext);
            final OCType resolvedType2 = negativeExpression.getResolvedType(ocResolveContext);
            OCType conditionalExprType;
            try {
                conditionalExprType = getConditionalExprType(resolvedType, resolvedType2, OCExpressionEvaluator.isLikeNil(positiveExpression, ocResolveContext), OCExpressionEvaluator.isLikeNil(negativeExpression, ocResolveContext), (PsiElement)this);
                if (conditionalExprType == null) {
                    throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/psi/impl/OCConditionalExpressionImpl", "getType"));
                }
            }
            catch (IllegalArgumentException ex3) {
                throw a(ex3);
            }
            return conditionalExprType;
            try {
                instance = OCUnknownType.INSTANCE;
                if (instance == null) {
                    throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/psi/impl/OCConditionalExpressionImpl", "getType"));
                }
            }
            catch (IllegalArgumentException ex4) {
                throw a(ex4);
            }
        }
        return instance;
    }
    
    public static OCType getConditionalExprType(OCType ocType, OCType ocType2, final boolean b, final boolean b2, @Nullable final PsiElement psiElement) {
        boolean b4 = false;
        Label_0030: {
            Label_0021: {
                try {
                    if (!(ocType instanceof OCCppReferenceType)) {
                        break Label_0021;
                    }
                    final OCType ocType3 = ocType2;
                    final boolean b3 = ocType3 instanceof OCCppReferenceType;
                    if (b3) {
                        break Label_0021;
                    }
                    break Label_0021;
                }
                catch (IllegalArgumentException ex) {
                    throw a(ex);
                }
                try {
                    final OCType ocType3 = ocType2;
                    final boolean b3 = ocType3 instanceof OCCppReferenceType;
                    if (b3) {
                        b4 = true;
                        break Label_0030;
                    }
                }
                catch (IllegalArgumentException ex2) {
                    throw a(ex2);
                }
            }
            b4 = false;
        }
        final boolean b5 = b4;
        if (ocType instanceof OCCppReferenceType) {
            ocType = ((OCCppReferenceType)ocType).getRefType();
        }
        if (ocType2 instanceof OCCppReferenceType) {
            ocType2 = ((OCCppReferenceType)ocType2).getRefType();
        }
        Label_0190: {
            Label_0158: {
                Label_0126: {
                    Label_0094: {
                        try {
                            if (!b2) {
                                break Label_0126;
                            }
                            if (!(ocType instanceof OCBlockPointerType)) {
                                break Label_0094;
                            }
                        }
                        catch (IllegalArgumentException ex3) {
                            throw a(ex3);
                        }
                        ocType2 = OCBlockPointerType.blockPtr(((OCBlockPointerType)ocType).getRefType());
                        break Label_0126;
                    }
                    if (ocType instanceof OCPointerType) {
                        final OCPointerType ocPointerType = (OCPointerType)ocType;
                        ocType2 = OCPointerType.to(ocPointerType.getRefType(), ocPointerType.getARCAttribute(), ocPointerType.getClassQualifier());
                    }
                    try {
                        if (!b) {
                            break Label_0190;
                        }
                        if (!(ocType2 instanceof OCBlockPointerType)) {
                            break Label_0158;
                        }
                    }
                    catch (IllegalArgumentException ex4) {
                        throw a(ex4);
                    }
                }
                ocType = OCBlockPointerType.blockPtr(((OCBlockPointerType)ocType2).getRefType());
                break Label_0190;
            }
            if (ocType2 instanceof OCPointerType) {
                final OCPointerType ocPointerType2 = (OCPointerType)ocType2;
                ocType = OCPointerType.to(ocPointerType2.getRefType(), ocPointerType2.getARCAttribute(), ocPointerType2.getClassQualifier());
            }
        }
        OCType leastCommonType = ocType.getLeastCommonType(ocType2, psiElement);
        Label_0243: {
            Label_0230: {
                try {
                    if (!(leastCommonType instanceof OCUnknownType)) {
                        break Label_0243;
                    }
                    if (!ocType.isCompatible(ocType2, psiElement)) {
                        break Label_0230;
                    }
                }
                catch (IllegalArgumentException ex5) {
                    throw a(ex5);
                }
                leastCommonType = ocType;
                break Label_0243;
            }
            if (ocType2.isCompatible(ocType, psiElement)) {
                leastCommonType = ocType2;
            }
            try {
                if (b5) {
                    return OCCppReferenceType.to(leastCommonType);
                }
            }
            catch (IllegalArgumentException ex6) {
                throw a(ex6);
            }
        }
        return leastCommonType;
    }
    
    static {
        boolean $assertionsDisabled2 = false;
        Label_0017: {
            try {
                if (!OCConditionalExpressionImpl.class.desiredAssertionStatus()) {
                    $assertionsDisabled2 = true;
                    break Label_0017;
                }
            }
            catch (IllegalArgumentException ex) {
                throw a(ex);
            }
            $assertionsDisabled2 = false;
        }
        $assertionsDisabled = $assertionsDisabled2;
    }
    
    private static IllegalArgumentException a(final IllegalArgumentException ex) {
        return ex;
    }
}
