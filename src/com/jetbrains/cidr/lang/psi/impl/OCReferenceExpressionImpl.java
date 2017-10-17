// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.lang.psi.impl;

import java.util.function.BiFunction;
import java.util.Map;
import com.jetbrains.cidr.lang.util.OCExpressionEvaluator;
import com.jetbrains.cidr.lang.psi.OCCallExpression;
import java.util.List;
import com.intellij.psi.PsiFile;
import com.jetbrains.cidr.lang.symbols.cpp.OCUsingSymbol;
import com.jetbrains.cidr.lang.symbols.OCSymbolKind;
import com.jetbrains.cidr.lang.types.OCVariadicType;
import com.jetbrains.cidr.lang.types.OCUnknownType;
import com.jetbrains.cidr.lang.types.OCFunctionType;
import java.util.Collections;
import com.jetbrains.cidr.lang.types.OCVoidType;
import com.jetbrains.cidr.lang.symbols.cpp.OCStructSymbol;
import com.jetbrains.cidr.lang.types.OCObjectType;
import com.jetbrains.cidr.lang.types.OCReferenceType;
import com.intellij.psi.util.PsiTreeUtil;
import com.jetbrains.cidr.lang.psi.OCMethod;
import com.jetbrains.cidr.lang.types.OCObjectTypeContext;
import com.jetbrains.cidr.lang.types.OCType;
import com.jetbrains.cidr.lang.psi.OCExpression;
import com.jetbrains.cidr.lang.util.OCElementUtil;
import com.intellij.psi.PsiElement;
import com.jetbrains.cidr.lang.psi.visitors.OCVisitor;
import com.jetbrains.cidr.lang.symbols.OCSymbol;
import com.jetbrains.cidr.lang.symbols.OCResolveContext;
import org.jetbrains.annotations.Nullable;
import com.intellij.psi.tree.IElementType;
import com.jetbrains.cidr.lang.parser.OCElementTypes;
import com.jetbrains.cidr.lang.psi.OCReferenceElement;
import org.jetbrains.annotations.NotNull;
import com.intellij.lang.ASTNode;
import com.jetbrains.cidr.lang.psi.OCReferenceExpression;

public class OCReferenceExpressionImpl extends OCExpressionBase implements OCReferenceExpression
{
    public OCReferenceExpressionImpl(@NotNull final ASTNode astNode) {
        if (astNode == null) {
            throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "node", "com/jetbrains/cidr/lang/psi/impl/OCReferenceExpressionImpl", "<init>"));
        }
        super(astNode);
    }
    
    @Nullable
    @Override
    public OCReferenceElement getReferenceElement() {
        return this.findChildByType(OCElementTypes.REFERENCE_ELEMENT);
    }
    
    @Override
    public OCSymbol resolveToSymbol(@NotNull final OCResolveContext ocResolveContext) {
        try {
            if (ocResolveContext == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "context", "com/jetbrains/cidr/lang/psi/impl/OCReferenceExpressionImpl", "resolveToSymbol"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        final OCReferenceElement referenceElement = this.getReferenceElement();
        try {
            if (referenceElement != null) {
                return referenceElement.resolveToSymbol(ocResolveContext);
            }
        }
        catch (IllegalArgumentException ex2) {
            throw a(ex2);
        }
        return null;
    }
    
    @Override
    public OCSymbol resolveToSymbol() {
        final OCReferenceElement referenceElement = this.getReferenceElement();
        try {
            if (referenceElement != null) {
                return referenceElement.resolveToSymbol();
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        return null;
    }
    
    @Override
    public void accept(@NotNull final OCVisitor ocVisitor) {
        try {
            if (ocVisitor == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "visitor", "com/jetbrains/cidr/lang/psi/impl/OCReferenceExpressionImpl", "accept"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        ocVisitor.visitReferenceExpression(this);
    }
    
    @Nullable
    @Override
    public OCElementTypes.SelfSuperToken getSelfSuperToken() {
        final OCReferenceElement referenceElement = this.getReferenceElement();
        try {
            if (referenceElement != null) {
                return OCElementUtil.getSelfSuperToken(referenceElement.getCanonicalText(), (PsiElement)this, new OCResolveContext((PsiElement)referenceElement));
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        return null;
    }
    
    @Override
    public boolean isCppThis() {
        final OCReferenceElement referenceElement = this.getReferenceElement();
        Label_0025: {
            try {
                if (referenceElement == null) {
                    return false;
                }
                final OCReferenceElement ocReferenceElement = referenceElement;
                final boolean b = ocReferenceElement.isCppThis();
                if (b) {
                    break Label_0025;
                }
                return false;
            }
            catch (IllegalArgumentException ex) {
                throw a(ex);
            }
            try {
                final OCReferenceElement ocReferenceElement = referenceElement;
                final boolean b = ocReferenceElement.isCppThis();
                if (b) {
                    return true;
                }
            }
            catch (IllegalArgumentException ex2) {
                throw a(ex2);
            }
        }
        return false;
    }
    
    @Override
    public boolean isSelfSuperOrThis() {
        Label_0021: {
            try {
                if (this.getSelfSuperToken() != null) {
                    break Label_0021;
                }
                final OCReferenceExpressionImpl ocReferenceExpressionImpl = this;
                final boolean b = ocReferenceExpressionImpl.isCppThis();
                if (b) {
                    break Label_0021;
                }
                return false;
            }
            catch (IllegalArgumentException ex) {
                throw a(ex);
            }
            try {
                final OCReferenceExpressionImpl ocReferenceExpressionImpl = this;
                final boolean b = ocReferenceExpressionImpl.isCppThis();
                if (b) {
                    return true;
                }
            }
            catch (IllegalArgumentException ex2) {
                throw a(ex2);
            }
        }
        return false;
    }
    
    @Override
    public OCObjectTypeContext getTypeContext(final OCExpression ocExpression, final OCType ocType, final OCType ocType2, final boolean b, final boolean b2) {
        final OCElementTypes.SelfSuperToken selfSuperToken = this.getSelfSuperToken();
        try {
            if (selfSuperToken == null) {
                return super.getTypeContext(ocExpression, ocType, ocType2, b, b2);
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        return this.getTypeContext(b, b2);
    }
    
    @Nullable
    @Override
    public OCObjectTypeContext getTypeContext(final boolean b, final boolean b2) {
        final OCElementTypes.SelfSuperToken selfSuperToken = this.getSelfSuperToken();
        try {
            if (selfSuperToken == null) {
                return super.getTypeContext(b, b2);
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        final OCMethod ocMethod = (OCMethod)PsiTreeUtil.getContextOfType((PsiElement)this, new Class[] { OCMethod.class });
        try {
            if (ocMethod == null) {
                return null;
            }
        }
        catch (IllegalArgumentException ex2) {
            throw a(ex2);
        }
        OCType resolvedFromText = OCReferenceType.resolvedFromText(ocMethod.getContainingClass().getName(), this.getContainingFile(), b2);
        OCObjectTypeContext ocObjectTypeContext = null;
        boolean b4 = false;
        Label_0174: {
            Label_0165: {
                Label_0136: {
                    try {
                        if (selfSuperToken != OCElementTypes.SelfSuperToken.SUPER || !(resolvedFromText instanceof OCObjectType)) {
                            break Label_0136;
                        }
                    }
                    catch (IllegalArgumentException ex3) {
                        throw a(ex3);
                    }
                    final OCObjectType superType = ((OCObjectType)resolvedFromText).getSuperType();
                    if (superType != null) {
                        resolvedFromText = superType;
                    }
                    else if (ocMethod.isInstanceMethod()) {
                        resolvedFromText = null;
                    }
                    try {
                        if (!(resolvedFromText instanceof OCObjectType)) {
                            return null;
                        }
                        ocObjectTypeContext = new(com.jetbrains.cidr.lang.types.OCObjectTypeContext.class);
                        final OCMethod ocMethod2 = ocMethod;
                        final boolean b3 = ocMethod2.isInstanceMethod();
                        if (!b3) {
                            break Label_0165;
                        }
                        break Label_0165;
                    }
                    catch (IllegalArgumentException ex4) {
                        throw a(ex4);
                    }
                }
                try {
                    ocObjectTypeContext = new(com.jetbrains.cidr.lang.types.OCObjectTypeContext.class);
                    final OCMethod ocMethod2 = ocMethod;
                    final boolean b3 = ocMethod2.isInstanceMethod();
                    if (!b3) {
                        b4 = true;
                        break Label_0174;
                    }
                }
                catch (IllegalArgumentException ex5) {
                    throw a(ex5);
                }
            }
            b4 = false;
        }
        new OCObjectTypeContext(b4, false, (OCObjectType)resolvedFromText, resolvedFromText);
        return ocObjectTypeContext;
    }
    
    @NotNull
    @Override
    public OCType getType(@NotNull final OCResolveContext ocResolveContext) {
        try {
            if (ocResolveContext == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "context", "com/jetbrains/cidr/lang/psi/impl/OCReferenceExpressionImpl", "getType"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        final OCReferenceElement referenceElement = this.getReferenceElement();
        OCType referenceExpressionType = null;
        Label_0068: {
            try {
                if (referenceElement != null) {
                    final OCSymbol resolveToSymbol = referenceElement.resolveToSymbol(ocResolveContext);
                    break Label_0068;
                }
            }
            catch (IllegalArgumentException ex2) {
                throw a(ex2);
            }
            final OCSymbol resolveToSymbol = null;
            try {
                referenceExpressionType = getReferenceExpressionType(resolveToSymbol);
                if (referenceExpressionType == null) {
                    throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/psi/impl/OCReferenceExpressionImpl", "getType"));
                }
            }
            catch (IllegalArgumentException ex3) {
                throw a(ex3);
            }
        }
        return referenceExpressionType;
    }
    
    public static OCType getReferenceExpressionType(final OCSymbol ocSymbol) {
        try {
            if (ocSymbol instanceof OCStructSymbol) {
                return new OCFunctionType(OCVoidType.instance(), Collections.emptyList());
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        OCType ocType = null;
        Label_0045: {
            try {
                if (ocSymbol != null) {
                    ocType = ocSymbol.getType();
                    break Label_0045;
                }
            }
            catch (IllegalArgumentException ex2) {
                throw a(ex2);
            }
            ocType = OCUnknownType.INSTANCE;
        }
        final OCType ocType2 = ocType;
        try {
            if (ocType2 instanceof OCVariadicType) {
                return ((OCVariadicType)ocType2).getUnderlyingType();
            }
        }
        catch (IllegalArgumentException ex3) {
            throw a(ex3);
        }
        return ocType2;
    }
    
    @Override
    public boolean isExpression() {
        OCSymbol resolveToSymbol = this.resolveToSymbol();
        try {
            if (resolveToSymbol == null) {
                return true;
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        if (resolveToSymbol.getKind() == OCSymbolKind.SYMBOL_USING_SYMBOL) {
            final List<OCSymbol> resolveToSymbols = ((OCUsingSymbol)resolveToSymbol).getSymbolReference().resolveToSymbols((PsiFile)this.getContainingOCFile());
            if (resolveToSymbols.size() != 0) {
                resolveToSymbol = resolveToSymbols.get(0);
            }
            return resolveToSymbol.getKind().isExpression();
        }
        return resolveToSymbol.getKind().isExpression();
    }
    
    @Override
    public boolean isParameterOfBuiltInTrait() {
        OCExpression functionReferenceExpression = null;
        Label_0040: {
            try {
                if (this.getParent().getParent() instanceof OCCallExpression) {
                    functionReferenceExpression = ((OCCallExpression)this.getParent().getParent()).getFunctionReferenceExpression();
                    break Label_0040;
                }
            }
            catch (IllegalArgumentException ex) {
                throw a(ex);
            }
            functionReferenceExpression = null;
        }
        final OCExpression ocExpression = functionReferenceExpression;
        OCReferenceElement referenceElement = null;
        Label_0065: {
            try {
                if (ocExpression instanceof OCReferenceExpression) {
                    referenceElement = ((OCReferenceExpression)ocExpression).getReferenceElement();
                    break Label_0065;
                }
            }
            catch (IllegalArgumentException ex2) {
                throw a(ex2);
            }
            referenceElement = null;
        }
        final OCReferenceElement ocReferenceElement = referenceElement;
        Label_0094: {
            try {
                if (ocReferenceElement == null) {
                    return false;
                }
                final Map<String, BiFunction<OCType, OCResolveContext, Boolean>> map = OCExpressionEvaluator.BUILT_IN_TRAITS;
                final OCReferenceElement ocReferenceElement2 = ocReferenceElement;
                final String s = ocReferenceElement2.getName();
                final boolean b = map.containsKey(s);
                if (b) {
                    break Label_0094;
                }
                return false;
            }
            catch (IllegalArgumentException ex3) {
                throw a(ex3);
            }
            try {
                final Map<String, BiFunction<OCType, OCResolveContext, Boolean>> map = OCExpressionEvaluator.BUILT_IN_TRAITS;
                final OCReferenceElement ocReferenceElement2 = ocReferenceElement;
                final String s = ocReferenceElement2.getName();
                final boolean b = map.containsKey(s);
                if (b) {
                    return true;
                }
            }
            catch (IllegalArgumentException ex4) {
                throw a(ex4);
            }
        }
        return false;
    }
    
    private static IllegalArgumentException a(final IllegalArgumentException ex) {
        return ex;
    }
}
