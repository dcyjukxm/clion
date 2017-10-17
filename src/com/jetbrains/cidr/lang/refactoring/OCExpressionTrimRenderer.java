// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.lang.refactoring;

import com.jetbrains.cidr.lang.parser.OCMacroRange;
import com.jetbrains.cidr.lang.util.OCElementUtil;
import com.intellij.util.Function;
import com.intellij.psi.PsiElement;
import com.jetbrains.cidr.lang.symbols.OCSymbol;
import com.jetbrains.cidr.lang.psi.OCReferenceElement;
import com.jetbrains.cidr.lang.symbols.cpp.OCMacroSymbol;
import com.jetbrains.cidr.lang.psi.OCMacroCall;
import com.jetbrains.cidr.lang.psi.OCArgumentList;
import com.jetbrains.cidr.lang.psi.OCCommaExpression;
import java.util.Iterator;
import com.jetbrains.cidr.lang.psi.OCMessageArgument;
import com.jetbrains.cidr.lang.psi.OCSendMessageExpression;
import com.jetbrains.cidr.lang.psi.OCCallExpression;
import com.jetbrains.cidr.lang.psi.OCAssignmentExpression;
import com.jetbrains.cidr.lang.psi.OCConditionalExpression;
import com.jetbrains.cidr.lang.psi.OCBinaryExpression;
import com.jetbrains.cidr.lang.psi.OCQualifiedExpression;
import com.jetbrains.cidr.lang.psi.OCPostfixExpression;
import com.jetbrains.cidr.lang.psi.OCUnaryExpression;
import com.jetbrains.cidr.lang.psi.OCPrefixExpression;
import com.jetbrains.cidr.lang.psi.OCArraySelectionExpression;
import com.jetbrains.cidr.lang.psi.OCCastExpression;
import com.intellij.psi.PsiElementVisitor;
import com.jetbrains.cidr.lang.psi.OCParenthesizedExpression;
import com.jetbrains.cidr.lang.psi.OCFunctionDeclaration;
import com.jetbrains.cidr.lang.psi.OCFunctionDefinition;
import com.jetbrains.cidr.lang.psi.OCParameterDeclaration;
import com.jetbrains.cidr.lang.psi.OCTypeElement;
import com.jetbrains.cidr.lang.psi.OCDeclaration;
import com.jetbrains.cidr.lang.psi.OCDeclarator;
import com.jetbrains.cidr.lang.psi.OCExpression;
import com.jetbrains.cidr.lang.psi.visitors.OCVisitor;

public class OCExpressionTrimRenderer extends OCVisitor
{
    private final StringBuilder myBuf;
    
    public OCExpressionTrimRenderer(final StringBuilder myBuf) {
        this.myBuf = myBuf;
    }
    
    @Override
    public void visitExpression(final OCExpression ocExpression) {
        this.myBuf.append(ocExpression.getTextWithMacros());
    }
    
    @Override
    public void visitDeclarator(final OCDeclarator ocDeclarator) {
        final OCTypeElement typeElement = ((OCDeclaration)ocDeclarator.getParent()).getTypeElement();
        if (typeElement != null) {
            this.myBuf.append(typeElement.getTextWithMacros()).append(' ');
        }
        this.myBuf.append(ocDeclarator.getName());
    }
    
    @Override
    public void visitParameterDeclaration(final OCParameterDeclaration ocParameterDeclaration) {
        final OCTypeElement typeElement = ocParameterDeclaration.getTypeElement();
        final OCDeclarator declarator = ocParameterDeclaration.getDeclarator();
        if (typeElement != null) {
            this.myBuf.append(typeElement.getTextWithMacros()).append(' ');
        }
        if (declarator != null) {
            this.myBuf.append(declarator.getName());
        }
    }
    
    @Override
    public void visitFunctionDefinition(final OCFunctionDefinition ocFunctionDefinition) {
        this.visitFunctionDeclaration(ocFunctionDefinition);
    }
    
    @Override
    public void visitFunctionDeclaration(final OCFunctionDeclaration ocFunctionDeclaration) {
        this.myBuf.append(ocFunctionDeclaration.getReturnTypeElement().getTextWithMacros()).append(' ');
        final OCDeclarator declarator = ocFunctionDeclaration.getDeclarator();
        if (declarator != null) {
            this.myBuf.append(declarator.getName());
        }
        this.myBuf.append("(...)");
    }
    
    @Override
    public void visitParenthesizedExpression(final OCParenthesizedExpression ocParenthesizedExpression) {
        this.myBuf.append("(");
        final OCExpression operand = ocParenthesizedExpression.getOperand();
        if (operand != null) {
            operand.accept((PsiElementVisitor)this);
        }
        this.myBuf.append(")");
    }
    
    @Override
    public void visitCastExpression(final OCCastExpression ocCastExpression) {
        final OCTypeElement typeElement = ocCastExpression.getTypeElement();
        if (typeElement != null) {
            this.myBuf.append("(").append(typeElement.getText()).append(")");
        }
        final OCExpression operand = ocCastExpression.getOperand();
        if (operand != null) {
            operand.accept((PsiElementVisitor)this);
        }
    }
    
    @Override
    public void visitArraySelectionExpression(final OCArraySelectionExpression ocArraySelectionExpression) {
        ocArraySelectionExpression.getArrayExpression().accept((PsiElementVisitor)this);
        this.myBuf.append("[");
        final OCExpression indexExpression = ocArraySelectionExpression.getIndexExpression();
        if (indexExpression != null) {
            indexExpression.accept((PsiElementVisitor)this);
        }
        this.myBuf.append("]");
    }
    
    @Override
    public void visitPrefixExpression(final OCPrefixExpression ocPrefixExpression) {
        this.myBuf.append(ocPrefixExpression.getOperationSignNode().getText());
        final OCExpression operand = ocPrefixExpression.getOperand();
        if (operand != null) {
            operand.accept((PsiElementVisitor)this);
        }
    }
    
    @Override
    public void visitUnaryExpression(final OCUnaryExpression ocUnaryExpression) {
        this.myBuf.append(ocUnaryExpression.getOperationSignNode().getText());
        final OCExpression operand = ocUnaryExpression.getOperand();
        if (operand != null) {
            operand.accept((PsiElementVisitor)this);
        }
    }
    
    @Override
    public void visitPostfixExpression(final OCPostfixExpression ocPostfixExpression) {
        ocPostfixExpression.getOperand().accept((PsiElementVisitor)this);
        this.myBuf.append(ocPostfixExpression.getOperationSignNode().getText());
    }
    
    @Override
    public void visitQualifiedExpression(final OCQualifiedExpression ocQualifiedExpression) {
        ocQualifiedExpression.getQualifier().accept((PsiElementVisitor)this);
        this.myBuf.append('.').append(ocQualifiedExpression.getName());
    }
    
    @Override
    public void visitBinaryExpression(final OCBinaryExpression ocBinaryExpression) {
        final OCExpression left = ocBinaryExpression.getLeft();
        if (left != null) {
            left.accept((PsiElementVisitor)this);
        }
        this.myBuf.append(" ").append(ocBinaryExpression.getOperationSignNode().getText()).append(" ");
        final OCExpression right = ocBinaryExpression.getRight();
        if (right != null) {
            right.accept((PsiElementVisitor)this);
        }
    }
    
    @Override
    public void visitConditionalExpression(final OCConditionalExpression ocConditionalExpression) {
        ocConditionalExpression.getCondition().accept((PsiElementVisitor)this);
        this.myBuf.append(" ? ");
        final OCExpression positiveExpression = ocConditionalExpression.getPositiveExpression(false);
        if (positiveExpression != null) {
            positiveExpression.accept((PsiElementVisitor)this);
        }
        this.myBuf.append(" : ");
        final OCExpression negativeExpression = ocConditionalExpression.getNegativeExpression();
        if (negativeExpression != null) {
            negativeExpression.accept((PsiElementVisitor)this);
        }
    }
    
    @Override
    public void visitAssignmentExpression(final OCAssignmentExpression ocAssignmentExpression) {
        ocAssignmentExpression.getReceiverExpression().accept((PsiElementVisitor)this);
        this.myBuf.append(ocAssignmentExpression.getOperationSignNode().getText());
        final OCExpression sourceExpression = ocAssignmentExpression.getSourceExpression();
        if (sourceExpression != null) {
            sourceExpression.accept((PsiElementVisitor)this);
        }
    }
    
    @Override
    public void visitCallExpression(final OCCallExpression ocCallExpression) {
        ocCallExpression.getFunctionReferenceExpression().accept((PsiElementVisitor)this);
        ocCallExpression.getArgumentList().accept((PsiElementVisitor)this);
    }
    
    @Override
    public void visitSendMessageExpression(final OCSendMessageExpression ocSendMessageExpression) {
        this.myBuf.append("[");
        final OCExpression receiverExpression = ocSendMessageExpression.getReceiverExpression();
        if (receiverExpression != null) {
            receiverExpression.accept((PsiElementVisitor)this);
            this.myBuf.append(" ");
        }
        for (final OCMessageArgument ocMessageArgument : ocSendMessageExpression.getArguments()) {
            final String selectorName = ocMessageArgument.getArgumentSelector().getSelectorName();
            if (selectorName.length() > 0) {
                this.myBuf.append(selectorName);
                if (ocMessageArgument.getArgumentExpression() == null) {
                    continue;
                }
                this.myBuf.append("... ");
            }
        }
        this.myBuf.append("]");
    }
    
    @Override
    public void visitCommaExpression(final OCCommaExpression ocCommaExpression) {
        this.myBuf.append(ocCommaExpression.getHeadExpression());
        this.myBuf.append(", ");
        this.myBuf.append(ocCommaExpression.getTailExpression());
    }
    
    @Override
    public void visitArgumentList(final OCArgumentList list) {
        if (list.getArguments().size() > 0) {
            this.myBuf.append("(...)");
        }
        else {
            this.myBuf.append("()");
        }
    }
    
    @Override
    public void visitMacroCall(final OCMacroCall ocMacroCall) {
        final OCReferenceElement macroReferenceElement = ocMacroCall.getMacroReferenceElement();
        if (macroReferenceElement == null) {
            this.myBuf.append(ocMacroCall.getText());
            return;
        }
        final OCSymbol resolveToSymbol = macroReferenceElement.resolveToSymbol();
        if (resolveToSymbol instanceof OCMacroSymbol) {
            this.myBuf.append(resolveToSymbol.getName());
            if (((OCMacroSymbol)resolveToSymbol).hasParameterList()) {
                this.myBuf.append("(...)");
            }
        }
        else {
            this.myBuf.append(ocMacroCall.getText());
        }
    }
    
    public static class RenderFunction<E extends PsiElement> implements Function<E, String>
    {
        public String fun(final E e) {
            final StringBuilder sb = new StringBuilder();
            final OCExpressionTrimRenderer ocExpressionTrimRenderer = new OCExpressionTrimRenderer(sb);
            final OCMacroRange rangeInMacroCall = OCElementUtil.getRangeInMacroCall(e);
            if (rangeInMacroCall != null && !rangeInMacroCall.mapsToArguments()) {
                rangeInMacroCall.getMacroCall().accept((PsiElementVisitor)ocExpressionTrimRenderer);
            }
            else {
                e.accept((PsiElementVisitor)ocExpressionTrimRenderer);
            }
            return sb.toString();
        }
    }
}
