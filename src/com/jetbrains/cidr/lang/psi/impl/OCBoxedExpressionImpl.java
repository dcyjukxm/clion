// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.lang.psi.impl;

import com.jetbrains.cidr.lang.types.OCPointerType;
import com.jetbrains.cidr.lang.types.OCReferenceType;
import com.intellij.psi.PsiElement;
import com.jetbrains.cidr.lang.types.OCUnknownType;
import com.jetbrains.cidr.lang.types.OCType;
import com.jetbrains.cidr.lang.symbols.OCResolveContext;
import com.jetbrains.cidr.lang.psi.visitors.OCVisitor;
import com.jetbrains.cidr.lang.parser.OCElementTypes;
import com.jetbrains.cidr.lang.psi.OCExpression;
import org.jetbrains.annotations.NotNull;
import com.intellij.lang.ASTNode;
import com.jetbrains.cidr.lang.psi.OCBoxedExpression;

public class OCBoxedExpressionImpl extends OCExpressionBase implements OCBoxedExpression
{
    public OCBoxedExpressionImpl(@NotNull final ASTNode astNode) {
        if (astNode == null) {
            throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "node", "com/jetbrains/cidr/lang/psi/impl/OCBoxedExpressionImpl", "<init>"));
        }
        super(astNode);
    }
    
    @Override
    public OCExpression getOperand() {
        return this.findChildByType(OCElementTypes.EXPRESSIONS);
    }
    
    @Override
    public void accept(@NotNull final OCVisitor ocVisitor) {
        try {
            if (ocVisitor == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "visitor", "com/jetbrains/cidr/lang/psi/impl/OCBoxedExpressionImpl", "accept"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        ocVisitor.visitBoxedExpression(this);
    }
    
    @NotNull
    @Override
    public OCType getType(@NotNull final OCResolveContext ocResolveContext) {
        try {
            if (ocResolveContext == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "context", "com/jetbrains/cidr/lang/psi/impl/OCBoxedExpressionImpl", "getType"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        final OCExpression operand = this.getOperand();
        Label_0102: {
            OCUnknownType ocUnknownType = null;
            Label_0067: {
                try {
                    if (operand != null) {
                        break Label_0102;
                    }
                    ocUnknownType = OCUnknownType.INSTANCE;
                    if (ocUnknownType == null) {
                        break Label_0067;
                    }
                    return ocUnknownType;
                }
                catch (IllegalArgumentException ex2) {
                    throw a(ex2);
                }
                try {
                    ocUnknownType = OCUnknownType.INSTANCE;
                    if (ocUnknownType == null) {
                        throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/psi/impl/OCBoxedExpressionImpl", "getType"));
                    }
                }
                catch (IllegalArgumentException ex3) {
                    throw a(ex3);
                }
            }
            return ocUnknownType;
        }
        final OCType resolvedType = operand.getResolvedType(ocResolveContext);
        OCUnknownType instance = null;
        Label_0233: {
            OCPointerType ocPointerType2 = null;
            Label_0198: {
                Label_0172: {
                    OCPointerType ocPointerType = null;
                    Label_0137: {
                        try {
                            if (!resolvedType.isNumberCompatible((PsiElement)this)) {
                                break Label_0172;
                            }
                            final String s = "NSNumber";
                            final OCReferenceType ocReferenceType = OCReferenceType.fromText(s);
                            ocPointerType = OCPointerType.to(ocReferenceType);
                            if (ocPointerType == null) {
                                break Label_0137;
                            }
                            return ocPointerType;
                        }
                        catch (IllegalArgumentException ex4) {
                            throw a(ex4);
                        }
                        try {
                            final String s = "NSNumber";
                            final OCReferenceType ocReferenceType = OCReferenceType.fromText(s);
                            ocPointerType = OCPointerType.to(ocReferenceType);
                            if (ocPointerType == null) {
                                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/psi/impl/OCBoxedExpressionImpl", "getType"));
                            }
                        }
                        catch (IllegalArgumentException ex5) {
                            throw a(ex5);
                        }
                    }
                    return ocPointerType;
                    try {
                        if (!resolvedType.isPointerToChar()) {
                            break Label_0233;
                        }
                        final String s2 = "NSString";
                        final OCReferenceType ocReferenceType2 = OCReferenceType.fromText(s2);
                        ocPointerType2 = OCPointerType.to(ocReferenceType2);
                        if (ocPointerType2 == null) {
                            break Label_0198;
                        }
                        return ocPointerType2;
                    }
                    catch (IllegalArgumentException ex6) {
                        throw a(ex6);
                    }
                }
                try {
                    final String s2 = "NSString";
                    final OCReferenceType ocReferenceType2 = OCReferenceType.fromText(s2);
                    ocPointerType2 = OCPointerType.to(ocReferenceType2);
                    if (ocPointerType2 == null) {
                        throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/psi/impl/OCBoxedExpressionImpl", "getType"));
                    }
                }
                catch (IllegalArgumentException ex7) {
                    throw a(ex7);
                }
            }
            return ocPointerType2;
            try {
                instance = OCUnknownType.INSTANCE;
                if (instance == null) {
                    throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/psi/impl/OCBoxedExpressionImpl", "getType"));
                }
            }
            catch (IllegalArgumentException ex8) {
                throw a(ex8);
            }
        }
        return instance;
    }
    
    private static IllegalArgumentException a(final IllegalArgumentException ex) {
        return ex;
    }
}
