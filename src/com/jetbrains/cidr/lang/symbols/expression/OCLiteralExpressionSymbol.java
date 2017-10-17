// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.lang.symbols.expression;

import com.jetbrains.cidr.lang.types.OCExpressionTypeArgument;
import com.jetbrains.cidr.lang.types.OCTypeArgument;
import com.intellij.psi.PsiFile;
import com.jetbrains.cidr.lang.psi.impl.OCLiteralExpressionImpl;
import com.jetbrains.cidr.lang.types.OCType;
import com.jetbrains.cidr.lang.symbols.OCResolveContext;
import com.jetbrains.cidr.lang.util.OCExpressionEvaluator;
import com.intellij.openapi.util.Comparing;
import com.jetbrains.cidr.lang.symbols.DeepEqual;
import com.intellij.openapi.vfs.VirtualFile;
import com.intellij.openapi.project.Project;
import org.jetbrains.annotations.Nullable;
import com.intellij.psi.tree.IElementType;
import org.jetbrains.annotations.NotNull;

public class OCLiteralExpressionSymbol extends OCExpressionSymbol
{
    @NotNull
    private Object myValue;
    private IElementType myTokenType;
    private String myText;
    
    public OCLiteralExpressionSymbol() {
    }
    
    public OCLiteralExpressionSymbol(@Nullable final String s, @NotNull final Object myValue, final IElementType myTokenType, final String myText) {
        if (myValue == null) {
            throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "value", "com/jetbrains/cidr/lang/symbols/expression/OCLiteralExpressionSymbol", "<init>"));
        }
        super(null, null, 0L, s);
        this.myValue = myValue;
        this.myTokenType = myTokenType;
        this.myText = myText;
    }
    
    @NotNull
    public Object getValue() {
        Object myValue;
        try {
            myValue = this.myValue;
            if (myValue == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/symbols/expression/OCLiteralExpressionSymbol", "getValue"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw b(ex);
        }
        return myValue;
    }
    
    @Override
    public boolean deepEqualStep(@NotNull final DeepEqual.Comparator comparator, @NotNull final Object o, @NotNull final Object o2) {
        try {
            if (comparator == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "c", "com/jetbrains/cidr/lang/symbols/expression/OCLiteralExpressionSymbol", "deepEqualStep"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw b(ex);
        }
        try {
            if (o == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "first", "com/jetbrains/cidr/lang/symbols/expression/OCLiteralExpressionSymbol", "deepEqualStep"));
            }
        }
        catch (IllegalArgumentException ex2) {
            throw b(ex2);
        }
        try {
            if (o2 == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "second", "com/jetbrains/cidr/lang/symbols/expression/OCLiteralExpressionSymbol", "deepEqualStep"));
            }
        }
        catch (IllegalArgumentException ex3) {
            throw b(ex3);
        }
        try {
            if (!super.deepEqualStep(comparator, o, o2)) {
                return false;
            }
        }
        catch (IllegalArgumentException ex4) {
            throw b(ex4);
        }
        final OCLiteralExpressionSymbol ocLiteralExpressionSymbol = (OCLiteralExpressionSymbol)o;
        final OCLiteralExpressionSymbol ocLiteralExpressionSymbol2 = (OCLiteralExpressionSymbol)o2;
        try {
            if (!comparator.equalObjects(ocLiteralExpressionSymbol.myValue, ocLiteralExpressionSymbol2.myValue)) {
                return false;
            }
        }
        catch (IllegalArgumentException ex5) {
            throw b(ex5);
        }
        try {
            if (!Comparing.equal(ocLiteralExpressionSymbol.myText, ocLiteralExpressionSymbol2.myText)) {
                return false;
            }
        }
        catch (IllegalArgumentException ex6) {
            throw b(ex6);
        }
        try {
            if (ocLiteralExpressionSymbol.myTokenType != ocLiteralExpressionSymbol2.myTokenType) {
                return false;
            }
        }
        catch (IllegalArgumentException ex7) {
            throw b(ex7);
        }
        return true;
    }
    
    @Nullable
    @Override
    public <T> T evaluate(@NotNull final OCExpressionEvaluator.CachingEvaluator<T> cachingEvaluator) {
        try {
            if (cachingEvaluator == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "evaluator", "com/jetbrains/cidr/lang/symbols/expression/OCLiteralExpressionSymbol", "evaluate"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw b(ex);
        }
        try {
            if (this.myValue instanceof Boolean) {
                return cachingEvaluator.evalBool((Boolean)this.myValue);
            }
        }
        catch (IllegalArgumentException ex2) {
            throw b(ex2);
        }
        try {
            if (this.myValue instanceof Number) {
                return cachingEvaluator.evalInteger((Number)this.myValue);
            }
        }
        catch (IllegalArgumentException ex3) {
            throw b(ex3);
        }
        return null;
    }
    
    @Nullable
    @Override
    public OCType getResolvedType(@NotNull final OCResolveContext ocResolveContext) {
        try {
            if (ocResolveContext == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "context", "com/jetbrains/cidr/lang/symbols/expression/OCLiteralExpressionSymbol", "getResolvedType"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw b(ex);
        }
        final PsiFile file = ocResolveContext.getFile();
        try {
            if (file != null) {
                return OCLiteralExpressionImpl.getLiteralType(this.myTokenType, this.myText, file);
            }
        }
        catch (IllegalArgumentException ex2) {
            throw b(ex2);
        }
        return null;
    }
    
    @Nullable
    @Override
    public OCTypeArgument evaluateToTypeArgument(@NotNull final OCResolveContext ocResolveContext) {
        try {
            if (ocResolveContext == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "resolver", "com/jetbrains/cidr/lang/symbols/expression/OCLiteralExpressionSymbol", "evaluateToTypeArgument"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw b(ex);
        }
        return new OCExpressionTypeArgument(this);
    }
    
    @NotNull
    public IElementType getTokenType() {
        IElementType myTokenType;
        try {
            myTokenType = this.myTokenType;
            if (myTokenType == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/symbols/expression/OCLiteralExpressionSymbol", "getTokenType"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw b(ex);
        }
        return myTokenType;
    }
    
    private static IllegalArgumentException b(final IllegalArgumentException ex) {
        return ex;
    }
}
