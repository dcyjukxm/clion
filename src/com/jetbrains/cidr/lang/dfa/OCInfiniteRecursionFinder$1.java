// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.lang.dfa;

import com.jetbrains.cidr.lang.psi.OCBlockExpression;
import com.intellij.psi.PsiReference;
import com.jetbrains.cidr.lang.types.OCType;
import com.intellij.openapi.util.Ref;
import com.jetbrains.cidr.lang.parser.OCPunctuatorElementType;
import com.intellij.util.Processor;
import com.jetbrains.cidr.lang.refactoring.OCNameSuggester;
import com.jetbrains.cidr.lang.parser.OCElementTypes;
import com.jetbrains.cidr.lang.symbols.objc.OCMethodSymbol;
import com.jetbrains.cidr.lang.psi.OCSendMessageExpression;
import com.jetbrains.cidr.lang.psi.OCExpression;
import com.jetbrains.cidr.lang.psi.OCQualifiedExpression;
import com.jetbrains.cidr.lang.psi.OCReferenceExpression;
import com.jetbrains.cidr.lang.symbols.cpp.OCFunctionSymbol;
import com.jetbrains.cidr.lang.psi.OCCallExpression;
import com.intellij.psi.PsiElement;
import com.jetbrains.cidr.lang.symbols.OCSymbol;
import com.jetbrains.cidr.lang.psi.visitors.OCRecursiveVisitor;

class OCInfiniteRecursionFinder$1 extends OCRecursiveVisitor {
    final /* synthetic */ OCSymbol val$symbol;
    
    @Override
    public void visitCallExpression(final OCCallExpression ocCallExpression) {
        super.visitCallExpression(ocCallExpression);
        final OCExpression functionReferenceExpression = ocCallExpression.getFunctionReferenceExpression();
        if (this.val$symbol instanceof OCFunctionSymbol) {
            OCSymbol ocSymbol = null;
            if (functionReferenceExpression instanceof OCReferenceExpression) {
                ocSymbol = ((OCReferenceExpression)functionReferenceExpression).resolveToSymbol();
            }
            else if (functionReferenceExpression instanceof OCQualifiedExpression) {
                final OCQualifiedExpression ocQualifiedExpression = (OCQualifiedExpression)functionReferenceExpression;
                if (ocQualifiedExpression.getQualifier() instanceof OCReferenceExpression && ((OCReferenceExpression)ocQualifiedExpression.getQualifier()).isCppThis()) {
                    ocSymbol = ocQualifiedExpression.resolveToSymbol();
                }
            }
            if (this.val$symbol.equals(ocSymbol) || (ocSymbol != null && this.val$symbol.equals(ocSymbol.getAssociatedSymbol()))) {
                OCInfiniteRecursionFinder.access$000(OCInfiniteRecursionFinder.this).add(ocCallExpression);
            }
        }
    }
    
    @Override
    public void visitSendMessageExpression(final OCSendMessageExpression ocSendMessageExpression) {
        super.visitSendMessageExpression(ocSendMessageExpression);
        final OCExpression receiverExpression = ocSendMessageExpression.getReceiverExpression();
        if (this.val$symbol instanceof OCMethodSymbol && ocSendMessageExpression.getMessageSelector().equals(this.val$symbol.getName()) && (((OCMethodSymbol)this.val$symbol).isStatic() || (receiverExpression instanceof OCReferenceExpression && ((OCReferenceExpression)receiverExpression).getSelfSuperToken() == OCElementTypes.SelfSuperToken.SELF))) {
            final OCMethodSymbol knownResponder = ocSendMessageExpression.getProbableResponders().getKnownResponder();
            if (this.val$symbol.equals(knownResponder) || (knownResponder != null && this.val$symbol.equals(knownResponder.getAssociatedSymbol()))) {
                OCInfiniteRecursionFinder.access$000(OCInfiniteRecursionFinder.this).add(ocSendMessageExpression);
            }
        }
    }
    
    @Override
    public void visitQualifiedExpression(final OCQualifiedExpression ocQualifiedExpression) {
        super.visitQualifiedExpression(ocQualifiedExpression);
        final OCExpression qualifier = ocQualifiedExpression.getQualifier();
        if (this.val$symbol instanceof OCMethodSymbol) {
            final String symbolName = ocQualifiedExpression.getSymbolName();
            final PsiReference reference = ocQualifiedExpression.getReference();
            if ((symbolName.equals(this.val$symbol.getName()) || OCNameSuggester.getObjCSetterFromGetter(symbolName).equals(this.val$symbol.getName())) && (((OCMethodSymbol)this.val$symbol).isStatic() || (qualifier instanceof OCReferenceExpression && ((OCReferenceExpression)qualifier).getSelfSuperToken() == OCElementTypes.SelfSuperToken.SELF)) && reference != null && !ocQualifiedExpression.processTargets(symbolName, (Processor<OCSymbol>)(ocSymbol2 -> !ocSymbol.equals(ocSymbol2) || !reference.isReferenceTo(psiElement)), true, null, false, false, null)) {
                OCInfiniteRecursionFinder.access$000(OCInfiniteRecursionFinder.this).add(ocQualifiedExpression);
            }
        }
    }
    
    @Override
    public void visitBlockExpression(final OCBlockExpression ocBlockExpression) {
    }
}