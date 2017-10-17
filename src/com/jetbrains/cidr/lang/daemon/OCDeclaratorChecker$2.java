// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.lang.daemon;

import com.jetbrains.cidr.lang.symbols.OCSymbol;
import com.intellij.lang.annotation.Annotation;
import com.intellij.codeInsight.intention.IntentionAction;
import com.jetbrains.cidr.lang.symbols.cpp.OCSymbolWithQualifiedName;
import com.jetbrains.cidr.lang.quickfixes.OCAddTypeModifierIntentionAction;
import com.jetbrains.cidr.lang.parser.OCTokenTypes;
import com.jetbrains.cidr.lang.symbols.cpp.OCDeclaratorSymbol;
import com.jetbrains.cidr.lang.psi.OCReferenceExpression;
import com.jetbrains.cidr.lang.inspections.OCInspection;
import com.intellij.psi.PsiElement;
import com.jetbrains.cidr.lang.inspections.OCInspections;
import com.jetbrains.cidr.lang.psi.OCExpression;

class OCDeclaratorChecker$2 extends OCConstantExpressionVisitor {
    final /* synthetic */ String val$clangID;
    final /* synthetic */ String val$message;
    
    @Override
    protected void nonConstExpression(final OCExpression ocExpression) {
        final Annotation addErrorAnnotation = OCDeclaratorChecker.this.addErrorAnnotation((PsiElement)ocExpression, OCInspections.ConstExpressionRequired.class, this.val$clangID, this.val$message);
        if (ocExpression instanceof OCReferenceExpression) {
            final OCSymbol resolveToSymbol = ((OCReferenceExpression)ocExpression).resolveToSymbol();
            if (resolveToSymbol instanceof OCDeclaratorSymbol) {
                OCDeclaratorChecker.this.registerQuickFix(addErrorAnnotation, (IntentionAction)new OCAddTypeModifierIntentionAction((OCSymbolWithQualifiedName)resolveToSymbol, OCTokenTypes.CONST_KEYWORD));
            }
        }
    }
}