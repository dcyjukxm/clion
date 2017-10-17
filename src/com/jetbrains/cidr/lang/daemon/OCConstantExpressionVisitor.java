// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.lang.daemon;

import com.intellij.psi.PsiElementVisitor;
import org.jetbrains.annotations.Nullable;
import com.jetbrains.cidr.lang.psi.OCSizeofExpression;
import com.jetbrains.cidr.lang.psi.OCLambdaExpression;
import com.jetbrains.cidr.lang.psi.OCBlockExpression;
import com.jetbrains.cidr.lang.psi.OCBoxedExpression;
import com.jetbrains.cidr.lang.psi.OCLiteralExpression;
import com.jetbrains.cidr.lang.psi.OCFile;
import com.intellij.psi.PsiFile;
import com.jetbrains.cidr.lang.workspace.compiler.OCCompilerFeatures;
import com.jetbrains.cidr.lang.psi.OCCppNewExpression;
import com.jetbrains.cidr.lang.types.OCType;
import com.jetbrains.cidr.lang.psi.OCCastExpression;
import com.intellij.psi.PsiReference;
import com.jetbrains.cidr.lang.util.OCCodeInsightUtil;
import com.jetbrains.cidr.lang.symbols.cpp.OCFunctionSymbol;
import com.jetbrains.cidr.lang.symbols.cpp.OCDeclaratorSymbol;
import com.jetbrains.cidr.lang.psi.OCSymbolDeclarator;
import com.jetbrains.cidr.lang.psi.OCCallExpression;
import com.jetbrains.cidr.lang.symbols.OCSymbol;
import com.intellij.psi.PsiElement;
import com.jetbrains.cidr.lang.symbols.OCSymbolKind;
import com.jetbrains.cidr.lang.symbols.OCTypeParameterSymbol;
import com.jetbrains.cidr.lang.psi.OCReferenceExpression;
import com.jetbrains.cidr.lang.psi.OCArraySelectionExpression;
import com.jetbrains.cidr.lang.psi.OCExpression;
import com.jetbrains.cidr.lang.psi.OCUnaryExpression;
import com.jetbrains.cidr.lang.psi.visitors.OCRecursiveVisitor;

public class OCConstantExpressionVisitor extends OCRecursiveVisitor
{
    private boolean wasAmpersand;
    private boolean myConst;
    
    @Override
    public void visitUnaryExpression(final OCUnaryExpression ocUnaryExpression) {
        if (ocUnaryExpression.isGetAddress()) {
            this.wasAmpersand = true;
        }
        this.visitExpression(ocUnaryExpression);
    }
    
    @Override
    public void visitArraySelectionExpression(final OCArraySelectionExpression ocArraySelectionExpression) {
        if (this.wasAmpersand) {
            this.visitExpression(ocArraySelectionExpression);
        }
        else {
            this.nonConstExpression(ocArraySelectionExpression);
            this.myConst = false;
        }
    }
    
    @Override
    public void visitReferenceExpression(final OCReferenceExpression ocReferenceExpression) {
        if (this.wasAmpersand) {
            this.wasAmpersand = false;
            return;
        }
        final OCSymbol resolveToSymbol = ocReferenceExpression.resolveToSymbol();
        if (resolveToSymbol != null) {
            if (resolveToSymbol instanceof OCTypeParameterSymbol) {
                return;
            }
            if (resolveToSymbol.getKind() == OCSymbolKind.TYPEDEF) {
                if (a(resolveToSymbol.getResolvedType(), (PsiElement)ocReferenceExpression)) {
                    this.nonConstExpression(ocReferenceExpression);
                    this.myConst = false;
                }
            }
            else if (!this.isConstDeclarator(ocReferenceExpression, resolveToSymbol)) {
                this.nonConstExpression(ocReferenceExpression);
                this.myConst = false;
            }
        }
    }
    
    @Override
    public void visitCallExpression(final OCCallExpression ocCallExpression) {
        final OCExpression functionReferenceExpression = ocCallExpression.getFunctionReferenceExpression();
        final PsiReference reference = functionReferenceExpression.getReference();
        if (reference != null) {
            final PsiElement resolve = reference.resolve();
            if (resolve instanceof OCSymbolDeclarator) {
                final OCDeclaratorSymbol symbol = ((OCSymbolDeclarator<OCDeclaratorSymbol>)resolve).getSymbol();
                if ((symbol instanceof OCDeclaratorSymbol && symbol.isConstexpr()) || (symbol instanceof OCFunctionSymbol && ((OCFunctionSymbol)symbol).isConstexpr())) {
                    super.visitCallExpression(ocCallExpression);
                    return;
                }
            }
        }
        if (!(functionReferenceExpression instanceof OCReferenceExpression)) {
            this.nonConstExpression(ocCallExpression);
            this.myConst = false;
        }
        else {
            final OCSymbol resolveToSymbol = ((OCReferenceExpression)functionReferenceExpression).resolveToSymbol();
            if (OCCodeInsightUtil.isInPlainOldC((PsiElement)ocCallExpression) && (resolveToSymbol == null || !resolveToSymbol.getName().startsWith("__builtin"))) {
                this.nonConstExpression(ocCallExpression);
                this.myConst = false;
            }
            else {
                super.visitCallExpression(ocCallExpression);
            }
        }
    }
    
    @Override
    public void visitCastExpression(final OCCastExpression ocCastExpression) {
        if (a(ocCastExpression.getResolvedType(), (PsiElement)ocCastExpression)) {
            this.nonConstExpression(ocCastExpression);
            this.myConst = false;
        }
        else {
            super.visitCastExpression(ocCastExpression);
        }
    }
    
    private static boolean a(final OCType ocType, final PsiElement psiElement) {
        return !OCCodeInsightUtil.isInPlainOldC(psiElement) && !ocType.isScalar() && !ocType.isUnknown();
    }
    
    @Override
    public void visitCppNewExpression(final OCCppNewExpression ocCppNewExpression) {
        this.nonConstExpression(ocCppNewExpression);
        this.myConst = false;
    }
    
    protected boolean isConstDeclarator(final OCReferenceExpression ocReferenceExpression, final OCSymbol ocSymbol) {
        if (!ocSymbol.getKind().isConst() && ocSymbol instanceof OCDeclaratorSymbol) {
            final OCDeclaratorSymbol ocDeclaratorSymbol = (OCDeclaratorSymbol)ocSymbol;
            if (ocDeclaratorSymbol.getArrayLengths().length != 0) {
                return true;
            }
            final OCFile containingOCFile = ocReferenceExpression.getContainingOCFile();
            if (((containingOCFile != null && containingOCFile.isCpp()) || !OCCompilerFeatures.getDiagnosticLevel((PsiFile)containingOCFile, OCCompilerFeatures.Diagnostic.FOLDING_CONSTANT).isEnabled()) && ocDeclaratorSymbol.isConst()) {
                return true;
            }
            if (ocSymbol.getKind() != OCSymbolKind.BUILTIN_SYMBOL) {
                return false;
            }
            this.visitExpression(ocReferenceExpression);
        }
        else if (ocSymbol.getKind().isConstructorOrDestructor()) {
            return false;
        }
        return true;
    }
    
    @Override
    public void visitLiteralExpression(final OCLiteralExpression ocLiteralExpression) {
        final OCType resolvedType = ocLiteralExpression.getResolvedType();
        if (resolvedType.isPointerToObject() && !resolvedType.isPointerToString()) {
            this.myConst = false;
            this.nonConstExpression(ocLiteralExpression);
        }
    }
    
    @Override
    public void visitBoxedExpression(final OCBoxedExpression ocBoxedExpression) {
        this.myConst = false;
        this.nonConstExpression(ocBoxedExpression);
    }
    
    @Override
    public void visitBlockExpression(final OCBlockExpression ocBlockExpression) {
    }
    
    @Override
    public void visitLambdaExpression(final OCLambdaExpression ocLambdaExpression) {
    }
    
    @Override
    public void visitSizeofExpression(final OCSizeofExpression ocSizeofExpression) {
    }
    
    protected void nonConstExpression(final OCExpression ocExpression) {
    }
    
    public boolean isConstant(@Nullable final OCExpression ocExpression) {
        if (ocExpression != null) {
            this.myConst = true;
            ocExpression.accept((PsiElementVisitor)this);
            return this.myConst;
        }
        return false;
    }
}
