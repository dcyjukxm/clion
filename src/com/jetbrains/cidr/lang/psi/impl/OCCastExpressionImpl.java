// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.lang.psi.impl;

import com.jetbrains.cidr.lang.symbols.OCResolveContext;
import com.jetbrains.cidr.lang.psi.visitors.OCVisitor;
import com.intellij.psi.PsiElement;
import com.jetbrains.cidr.lang.parser.OCTokenTypes;
import com.jetbrains.cidr.lang.util.OCElementUtil;
import com.jetbrains.cidr.lang.psi.OCCastKind;
import com.jetbrains.cidr.lang.psi.OCTypeElement;
import com.jetbrains.cidr.lang.types.OCUnknownType;
import com.jetbrains.cidr.lang.types.OCType;
import com.intellij.psi.tree.IElementType;
import com.jetbrains.cidr.lang.psi.OCArgumentList;
import org.jetbrains.annotations.Nullable;
import com.jetbrains.cidr.lang.parser.OCElementTypes;
import com.jetbrains.cidr.lang.psi.OCExpression;
import org.jetbrains.annotations.NotNull;
import com.intellij.lang.ASTNode;
import com.jetbrains.cidr.lang.psi.OCCastExpression;

public class OCCastExpressionImpl extends OCExpressionBase implements OCCastExpression
{
    public OCCastExpressionImpl(@NotNull final ASTNode astNode) {
        if (astNode == null) {
            throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "node", "com/jetbrains/cidr/lang/psi/impl/OCCastExpressionImpl", "<init>"));
        }
        super(astNode);
    }
    
    @Nullable
    @Override
    public OCExpression getOperand() {
        return this.findChildByType(OCElementTypes.EXPRESSIONS);
    }
    
    @Nullable
    @Override
    public OCArgumentList getArgumentList() {
        return this.findChildByType(OCElementTypes.ARGUMENT_LIST);
    }
    
    @NotNull
    @Override
    public OCType getCastType() {
        final OCTypeElement typeElement = this.getTypeElement();
        OCType ocType = null;
        Label_0025: {
            try {
                if (typeElement != null) {
                    final OCType ocType2;
                    ocType = (ocType2 = typeElement.getType());
                    break Label_0025;
                }
            }
            catch (IllegalArgumentException ex) {
                throw a(ex);
            }
            OCType ocType2;
            ocType = (ocType2 = OCUnknownType.INSTANCE);
            try {
                if (ocType2 == null) {
                    throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/psi/impl/OCCastExpressionImpl", "getCastType"));
                }
            }
            catch (IllegalArgumentException ex2) {
                throw a(ex2);
            }
        }
        return ocType;
    }
    
    @NotNull
    @Override
    public OCCastKind getCastKind() {
        for (PsiElement psiElement = this.getFirstChild(); psiElement != null; psiElement = psiElement.getNextSibling()) {
            final IElementType elementType = OCElementUtil.getElementType(psiElement);
            OCCastKind ocCastKind4 = null;
            Label_0203: {
                Label_0182: {
                    OCCastKind ocCastKind3 = null;
                    Label_0147: {
                        Label_0126: {
                            OCCastKind ocCastKind2 = null;
                            Label_0091: {
                                Label_0070: {
                                    OCCastKind ocCastKind = null;
                                    Label_0035: {
                                        try {
                                            if (elementType != OCTokenTypes.STATIC_CAST_CPP_KEYWORD) {
                                                break Label_0070;
                                            }
                                            ocCastKind = OCCastKind.STATIC_CAST;
                                            if (ocCastKind == null) {
                                                break Label_0035;
                                            }
                                            return ocCastKind;
                                        }
                                        catch (IllegalArgumentException ex) {
                                            throw a(ex);
                                        }
                                        try {
                                            ocCastKind = OCCastKind.STATIC_CAST;
                                            if (ocCastKind == null) {
                                                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/psi/impl/OCCastExpressionImpl", "getCastKind"));
                                            }
                                        }
                                        catch (IllegalArgumentException ex2) {
                                            throw a(ex2);
                                        }
                                    }
                                    return ocCastKind;
                                    try {
                                        if (elementType != OCTokenTypes.CONST_CAST_CPP_KEYWORD) {
                                            break Label_0126;
                                        }
                                        ocCastKind2 = OCCastKind.CONST_CAST;
                                        if (ocCastKind2 == null) {
                                            break Label_0091;
                                        }
                                        return ocCastKind2;
                                    }
                                    catch (IllegalArgumentException ex3) {
                                        throw a(ex3);
                                    }
                                }
                                try {
                                    ocCastKind2 = OCCastKind.CONST_CAST;
                                    if (ocCastKind2 == null) {
                                        throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/psi/impl/OCCastExpressionImpl", "getCastKind"));
                                    }
                                }
                                catch (IllegalArgumentException ex4) {
                                    throw a(ex4);
                                }
                            }
                            return ocCastKind2;
                            try {
                                if (elementType != OCTokenTypes.DYNAMIC_CAST_CPP_KEYWORD) {
                                    break Label_0182;
                                }
                                ocCastKind3 = OCCastKind.DYNAMIC_CAST;
                                if (ocCastKind3 == null) {
                                    break Label_0147;
                                }
                                return ocCastKind3;
                            }
                            catch (IllegalArgumentException ex5) {
                                throw a(ex5);
                            }
                        }
                        try {
                            ocCastKind3 = OCCastKind.DYNAMIC_CAST;
                            if (ocCastKind3 == null) {
                                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/psi/impl/OCCastExpressionImpl", "getCastKind"));
                            }
                        }
                        catch (IllegalArgumentException ex6) {
                            throw a(ex6);
                        }
                    }
                    return ocCastKind3;
                    try {
                        if (elementType != OCTokenTypes.REINTERPRET_CAST_CPP_KEYWORD) {
                            continue;
                        }
                        ocCastKind4 = OCCastKind.REINTERPRET_CAST;
                        if (ocCastKind4 == null) {
                            break Label_0203;
                        }
                        return ocCastKind4;
                    }
                    catch (IllegalArgumentException ex7) {
                        throw a(ex7);
                    }
                }
                try {
                    ocCastKind4 = OCCastKind.REINTERPRET_CAST;
                    if (ocCastKind4 == null) {
                        throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/psi/impl/OCCastExpressionImpl", "getCastKind"));
                    }
                }
                catch (IllegalArgumentException ex8) {
                    throw a(ex8);
                }
            }
            return ocCastKind4;
        }
        OCCastKind c_STYLE_CAST;
        try {
            c_STYLE_CAST = OCCastKind.C_STYLE_CAST;
            if (c_STYLE_CAST == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/psi/impl/OCCastExpressionImpl", "getCastKind"));
            }
        }
        catch (IllegalArgumentException ex9) {
            throw a(ex9);
        }
        return c_STYLE_CAST;
    }
    
    @Override
    public PsiElement getBridgeCastModifier() {
        for (PsiElement psiElement = this.getFirstChild(); psiElement != null; psiElement = psiElement.getNextSibling()) {
            final IElementType elementType = OCElementUtil.getElementType(psiElement);
            try {
                if (OCTokenTypes.BRIDGE_CAST_KEYWORDS.contains(elementType)) {
                    return psiElement;
                }
            }
            catch (IllegalArgumentException ex) {
                throw a(ex);
            }
        }
        return null;
    }
    
    @Nullable
    @Override
    public OCTypeElement getTypeElement() {
        return this.findChildByType(OCElementTypes.TYPE_ELEMENT);
    }
    
    @Override
    public void accept(@NotNull final OCVisitor ocVisitor) {
        try {
            if (ocVisitor == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "visitor", "com/jetbrains/cidr/lang/psi/impl/OCCastExpressionImpl", "accept"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        ocVisitor.visitCastExpression(this);
    }
    
    @NotNull
    @Override
    public OCType getType(@NotNull final OCResolveContext ocResolveContext) {
        try {
            if (ocResolveContext == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "context", "com/jetbrains/cidr/lang/psi/impl/OCCastExpressionImpl", "getType"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        OCType castType;
        try {
            castType = this.getCastType();
            if (castType == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/psi/impl/OCCastExpressionImpl", "getType"));
            }
        }
        catch (IllegalArgumentException ex2) {
            throw a(ex2);
        }
        return castType;
    }
    
    private static IllegalArgumentException a(final IllegalArgumentException ex) {
        return ex;
    }
}
