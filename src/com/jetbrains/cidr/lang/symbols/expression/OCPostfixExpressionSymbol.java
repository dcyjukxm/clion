// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.lang.symbols.expression;

import com.jetbrains.cidr.lang.psi.OCFile;
import com.jetbrains.cidr.lang.types.OCUnknownType;
import com.intellij.psi.PsiElement;
import com.jetbrains.cidr.lang.symbols.OCResolveContext;
import com.jetbrains.cidr.lang.types.OCType;
import com.jetbrains.cidr.lang.resolve.references.OCOperatorReference;
import org.jetbrains.annotations.NotNull;
import com.jetbrains.cidr.lang.parser.OCElementType;
import com.intellij.openapi.vfs.VirtualFile;
import org.jetbrains.annotations.Nullable;
import com.intellij.openapi.project.Project;

public class OCPostfixExpressionSymbol extends OCUnaryExpressionSymbolBase
{
    public OCPostfixExpressionSymbol() {
    }
    
    public OCPostfixExpressionSymbol(@Nullable final Project project, @Nullable final VirtualFile virtualFile, final long n, @Nullable final String s, @NotNull final OCElementType ocElementType, @NotNull final OCExpressionSymbol ocExpressionSymbol) {
        if (ocElementType == null) {
            throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "operator", "com/jetbrains/cidr/lang/symbols/expression/OCPostfixExpressionSymbol", "<init>"));
        }
        if (ocExpressionSymbol == null) {
            throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "operand", "com/jetbrains/cidr/lang/symbols/expression/OCPostfixExpressionSymbol", "<init>"));
        }
        super(project, virtualFile, n, s, ocElementType, ocExpressionSymbol);
    }
    
    @NotNull
    @Override
    public OCOperatorReference.OperatorPlacement getOperatorPlacement() {
        OCOperatorReference.OperatorPlacement postfix;
        try {
            postfix = OCOperatorReference.OperatorPlacement.POSTFIX;
            if (postfix == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/symbols/expression/OCPostfixExpressionSymbol", "getOperatorPlacement"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw c(ex);
        }
        return postfix;
    }
    
    @Override
    protected OCType getResolvedType(final OCType ocType, @NotNull final OCResolveContext ocResolveContext) {
        try {
            if (ocResolveContext == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "context", "com/jetbrains/cidr/lang/symbols/expression/OCPostfixExpressionSymbol", "getResolvedType"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw c(ex);
        }
        try {
            if (ocType.isNumberCompatible(ocResolveContext.getElement())) {
                return ocType;
            }
            final OCType ocType2 = ocType;
            final OCPostfixExpressionSymbol ocPostfixExpressionSymbol = this;
            final OCFile ocFile = ocPostfixExpressionSymbol.getContainingOCFile();
            final boolean b = ocType2.isPointerCompatible((PsiElement)ocFile);
            if (b) {
                return ocType;
            }
            return OCUnknownType.INSTANCE;
        }
        catch (IllegalArgumentException ex2) {
            throw c(ex2);
        }
        try {
            final OCType ocType2 = ocType;
            final OCPostfixExpressionSymbol ocPostfixExpressionSymbol = this;
            final OCFile ocFile = ocPostfixExpressionSymbol.getContainingOCFile();
            final boolean b = ocType2.isPointerCompatible((PsiElement)ocFile);
            if (b) {
                return ocType;
            }
        }
        catch (IllegalArgumentException ex3) {
            throw c(ex3);
        }
        return OCUnknownType.INSTANCE;
    }
    
    private static IllegalArgumentException c(final IllegalArgumentException ex) {
        return ex;
    }
}
