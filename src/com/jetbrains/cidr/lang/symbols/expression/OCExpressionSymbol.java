// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.lang.symbols.expression;

import com.intellij.psi.tree.IElementType;
import com.jetbrains.cidr.lang.types.OCExpressionTypeArgument;
import com.jetbrains.cidr.lang.util.OCExpressionEvaluator;
import com.jetbrains.cidr.lang.types.OCTypeArgument;
import com.jetbrains.cidr.lang.symbols.OCResolveContext;
import com.intellij.psi.PsiElement;
import com.jetbrains.cidr.lang.symbols.OCSymbolOffsetUtil;
import org.jetbrains.annotations.NotNull;
import com.jetbrains.cidr.lang.symbols.OCSymbolKind;
import com.intellij.util.containers.ContainerUtil;
import com.intellij.openapi.vfs.VirtualFile;
import org.jetbrains.annotations.Nullable;
import com.intellij.openapi.project.Project;
import com.jetbrains.cidr.lang.types.OCTypeOwner;
import com.jetbrains.cidr.lang.psi.OCExpression;
import com.jetbrains.cidr.lang.symbols.OCSymbolImpl;

public abstract class OCExpressionSymbol extends OCSymbolImpl<OCExpression> implements OCTypeOwner
{
    public OCExpressionSymbol() {
    }
    
    public OCExpressionSymbol(@Nullable final Project project, @Nullable final VirtualFile virtualFile, final long n, @Nullable final String s) {
        super(project, virtualFile, n, s, ContainerUtil.emptyList());
    }
    
    @NotNull
    @Override
    public OCSymbolKind getKind() {
        OCSymbolKind expression;
        try {
            expression = OCSymbolKind.EXPRESSION;
            if (expression == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/symbols/expression/OCExpressionSymbol", "getKind"));
            }
        }
        catch (IllegalStateException ex) {
            throw b(ex);
        }
        return expression;
    }
    
    @Nullable
    @Override
    public OCExpression locateDefinition() {
        OCExpression ocExpression = super.locateDefinition();
        try {
            if (ocExpression == null) {
                return null;
            }
        }
        catch (IllegalStateException ex) {
            throw b(ex);
        }
        PsiElement psiElement = ocExpression.getParent();
        while (true) {
            try {
                if (!(psiElement instanceof OCExpression) || OCSymbolOffsetUtil.getVirtualComplexOffset(psiElement) != this.myComplexOffset) {
                    break;
                }
            }
            catch (IllegalStateException ex2) {
                throw b(ex2);
            }
            ocExpression = (OCExpression)psiElement;
            psiElement = ocExpression.getParent();
        }
        return ocExpression;
    }
    
    @Nullable
    public OCTypeArgument evaluateToTypeArgument(@NotNull final OCResolveContext ocResolveContext) {
        try {
            if (ocResolveContext == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "resolver", "com/jetbrains/cidr/lang/symbols/expression/OCExpressionSymbol", "evaluateToTypeArgument"));
            }
        }
        catch (IllegalStateException ex) {
            throw b(ex);
        }
        final Number evaluate = OCExpressionEvaluator.evaluate(this, ocResolveContext);
        try {
            if (evaluate != null) {
                final OCLiteralExpressionSymbol ocLiteralExpressionSymbol;
                final OCExpressionTypeArgument ocExpressionTypeArgument = new OCExpressionTypeArgument(ocLiteralExpressionSymbol);
                ocLiteralExpressionSymbol = new OCLiteralExpressionSymbol(evaluate.toString(), evaluate, null, null);
                return ocExpressionTypeArgument;
            }
        }
        catch (IllegalStateException ex2) {
            throw b(ex2);
        }
        return null;
    }
    
    @Nullable
    public abstract <T> T evaluate(@NotNull final OCExpressionEvaluator.CachingEvaluator<T> p0);
    
    private static IllegalStateException b(final IllegalStateException ex) {
        return ex;
    }
}
