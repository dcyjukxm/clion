// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.lang.util;

import java.util.stream.Collector;
import java.util.stream.Collectors;
import com.jetbrains.cidr.lang.daemon.OCGetSymbolVisitor;
import com.jetbrains.cidr.lang.psi.OCCallExpression;
import java.util.List;
import com.jetbrains.cidr.lang.psi.OCCompoundInitializer;
import com.intellij.util.Producer;
import com.jetbrains.cidr.lang.psi.OCConditionalExpression;
import com.jetbrains.cidr.lang.psi.OCUnaryExpression;
import com.jetbrains.cidr.lang.parser.OCElementType;
import com.jetbrains.cidr.lang.parser.OCTokenTypes;
import com.jetbrains.cidr.lang.types.OCRealType;
import com.jetbrains.cidr.lang.types.OCStructType;
import com.jetbrains.cidr.lang.types.OCMagicType;
import com.jetbrains.cidr.lang.types.OCTypeUtils;
import com.jetbrains.cidr.lang.psi.OCElement;
import com.jetbrains.cidr.lang.psi.OCBinaryExpression;
import com.jetbrains.cidr.lang.types.OCTypeOwner;
import com.jetbrains.cidr.lang.psi.OCParenthesizedExpression;
import com.jetbrains.cidr.lang.types.OCType;
import com.jetbrains.cidr.lang.psi.OCTypeElement;
import com.jetbrains.cidr.lang.psi.OCSizeofExpression;
import com.jetbrains.cidr.lang.psi.OCCastExpression;
import com.jetbrains.cidr.lang.symbols.OCSymbol;
import com.jetbrains.cidr.lang.symbols.OCResolveContext;
import com.jetbrains.cidr.lang.psi.OCReferenceElement;
import com.jetbrains.cidr.lang.psi.OCExpression;
import com.jetbrains.cidr.lang.resolve.OCResolveUtil;
import com.jetbrains.cidr.lang.symbols.OCSymbolGroupContext;
import com.jetbrains.cidr.lang.psi.OCReferenceExpression;
import com.intellij.lang.ASTNode;
import com.intellij.psi.PsiElement;
import com.jetbrains.cidr.lang.preprocessor.OCInclusionContext;
import com.jetbrains.cidr.lang.psi.OCLiteralExpression;
import org.jetbrains.annotations.Nullable;
import com.jetbrains.cidr.lang.psi.visitors.OCVisitor;

private static class EvaluationVisitor<T> extends OCVisitor
{
    private T myResult;
    private CachingEvaluator<T> myEvaluator;
    
    public EvaluationVisitor(final CachingEvaluator<T> myEvaluator) {
        this.myEvaluator = myEvaluator;
    }
    
    @Nullable
    private T a() {
        return this.myResult;
    }
    
    @Override
    public void visitLiteralExpression(final OCLiteralExpression ocLiteralExpression) {
        final PsiElement firstChild = ocLiteralExpression.getFirstChild();
        if (firstChild == null) {
            return;
        }
        final ASTNode node = firstChild.getNode();
        final Object constValue = OCElementUtil.getConstValue(node.getElementType(), node.getText(), node.getPsi(), null);
        if (constValue instanceof Number) {
            this.myResult = (T)this.myEvaluator.evalInteger((Number)constValue);
        }
        else if (constValue instanceof Boolean) {
            this.myResult = (T)this.myEvaluator.evalBool((Boolean)constValue);
        }
    }
    
    @Override
    public void visitReferenceExpression(final OCReferenceExpression ocReferenceExpression) {
        final OCReferenceElement referenceElement = ocReferenceExpression.getReferenceElement();
        final OCResolveContext context = this.myEvaluator.getContext();
        OCSymbol resolveToSymbol = null;
        if (referenceElement != null) {
            resolveToSymbol = referenceElement.resolveToSymbol(null, context, false, false, false);
        }
        if (!OCResolveUtil.isDependentCode(ocReferenceExpression, context)) {
            this.myResult = OCExpressionEvaluator.evaluate(resolveToSymbol, this.myEvaluator);
        }
        if (this.myResult == null) {
            this.myResult = this.myEvaluator.evalDefault(ocReferenceExpression);
        }
    }
    
    @Override
    public void visitCastExpression(final OCCastExpression ocCastExpression) {
        this.myResult = (T)OCExpressionEvaluator.access$100(ocCastExpression.getOperand(), ocCastExpression.getResolvedType(), this.myEvaluator);
    }
    
    @Override
    public void visitSizeofExpression(final OCSizeofExpression ocSizeofExpression) {
        final OCExpression operand = ocSizeofExpression.getOperand();
        final OCTypeElement typeElement = ocSizeofExpression.getTypeElement();
        if (ocSizeofExpression.isVariadic()) {
            return;
        }
        OCType ocType;
        if (operand != null) {
            ocType = operand.getResolvedType(this.myEvaluator.getContext());
        }
        else {
            if (typeElement == null) {
                return;
            }
            ocType = typeElement.getType().resolve(this.myEvaluator.getContext());
        }
        final int sizeInBytes = ocType.getSizeInBytes(ocSizeofExpression.getContainingFile(), null);
        if (sizeInBytes != -1) {
            this.myResult = (T)this.myEvaluator.evalInteger(sizeInBytes);
        }
    }
    
    @Override
    public void visitParenthesizedExpression(final OCParenthesizedExpression ocParenthesizedExpression) {
        this.myResult = OCExpressionEvaluator.evaluate(ocParenthesizedExpression.getOperand(), this.myEvaluator);
    }
    
    @Override
    public void visitBinaryExpression(final OCBinaryExpression ocBinaryExpression) {
        final OCExpression left = ocBinaryExpression.getLeft();
        final OCExpression right = ocBinaryExpression.getRight();
        final OCElementType operationSign = ocBinaryExpression.getOperationSign();
        this.myResult = this.myEvaluator.evalBinary(operationSign, OCExpressionEvaluator.evaluate(left, this.myEvaluator), OCExpressionEvaluator.evaluate(right, this.myEvaluator));
        if (this.myResult != null || left == null || right == null || OCCodeInsightUtil.hasSideEffects(left) || OCCodeInsightUtil.hasSideEffects(right)) {
            return;
        }
        final OCType resolvedCppReferencedType = OCTypeUtils.getResolvedCppReferencedType(left, this.myEvaluator.getContext());
        final OCType resolvedCppReferencedType2 = OCTypeUtils.getResolvedCppReferencedType(right, this.myEvaluator.getContext());
        if (OCTypeUtils.isInstanceOfType(resolvedCppReferencedType, OCMagicType.class, OCStructType.class) || OCTypeUtils.isInstanceOfType(resolvedCppReferencedType2, OCMagicType.class, OCStructType.class)) {
            return;
        }
        if (OCParenthesesUtils.areExpressionsEquivalent(left, right, true, this.myEvaluator.getContext())) {
            if (resolvedCppReferencedType instanceof OCRealType || resolvedCppReferencedType2 instanceof OCRealType) {
                return;
            }
            if (operationSign == OCTokenTypes.XOR || operationSign == OCTokenTypes.EXCLEQ) {
                this.myResult = this.myEvaluator.evalBool(false);
            }
            else if (operationSign == OCTokenTypes.EQEQ) {
                this.myResult = this.myEvaluator.evalBool(true);
            }
        }
        else if (OCParenthesesUtils.areExpressionsOpposite(left, right, true, this.myEvaluator.getContext())) {
            if (operationSign == OCTokenTypes.OROR || operationSign == OCTokenTypes.XOR || operationSign == OCTokenTypes.EXCLEQ) {
                this.myResult = this.myEvaluator.evalBool(true);
            }
            else if (operationSign == OCTokenTypes.ANDAND || operationSign == OCTokenTypes.EQEQ) {
                this.myResult = this.myEvaluator.evalBool(false);
            }
        }
    }
    
    @Override
    public void visitUnaryExpression(final OCUnaryExpression ocUnaryExpression) {
        this.myResult = this.myEvaluator.evalUnary(ocUnaryExpression.getOperationSign(), OCExpressionEvaluator.evaluate(ocUnaryExpression.getOperand(), this.myEvaluator));
    }
    
    @Override
    public void visitConditionalExpression(final OCConditionalExpression ocConditionalExpression) {
        this.myResult = this.myEvaluator.evalConditional(OCExpressionEvaluator.evaluate(ocConditionalExpression.getCondition(), this.myEvaluator), (com.intellij.util.Producer<T>)(() -> OCExpressionEvaluator.evaluate(ocConditionalExpression.getPositiveExpression(true), this.myEvaluator)), (com.intellij.util.Producer<T>)(() -> OCExpressionEvaluator.evaluate(ocConditionalExpression.getNegativeExpression(), this.myEvaluator)));
    }
    
    @Override
    public void visitCompoundInitializer(final OCCompoundInitializer ocCompoundInitializer) {
        final List<OCExpression> initializerExpressions = ocCompoundInitializer.getInitializerExpressions();
        if (initializerExpressions.size() == 1) {
            this.myResult = OCExpressionEvaluator.evaluate(initializerExpressions.get(0), this.myEvaluator);
        }
    }
    
    @Override
    public void visitCallExpression(final OCCallExpression ocCallExpression) {
        final Boolean evaluateGNUBuiltInTrait = OCExpressionEvaluator.evaluateGNUBuiltInTrait(ocCallExpression, this.myEvaluator.getContext());
        if (evaluateGNUBuiltInTrait != null) {
            this.myResult = (T)this.myEvaluator.evalBool(evaluateGNUBuiltInTrait);
            return;
        }
        final Number number = OCExpressionEvaluator.getNumber(OCExpressionEvaluator.access$200(OCGetSymbolVisitor.getSymbol(ocCallExpression.getFunctionReferenceExpression()), this.myEvaluator.getContext(), ocCallExpression.getArguments().stream().map(ocExpression -> OCExpressionEvaluator.evaluate(ocExpression, this.myEvaluator)).collect((Collector<? super Object, ?, List<? super Object>>)Collectors.toList())));
        this.myResult = (T)((number != null) ? this.myEvaluator.evalInteger(number) : this.myEvaluator.evalDefault(ocCallExpression));
    }
    
    @Override
    public void visitExpression(final OCExpression ocExpression) {
        this.myResult = this.myEvaluator.evalDefault(ocExpression);
    }
}
