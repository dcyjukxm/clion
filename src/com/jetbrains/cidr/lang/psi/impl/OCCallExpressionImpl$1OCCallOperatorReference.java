// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.lang.psi.impl;

import org.jetbrains.annotations.NotNull;
import com.jetbrains.cidr.lang.psi.OCArgumentList;
import com.jetbrains.cidr.lang.util.OCElementUtil;
import java.util.ArrayList;
import com.intellij.openapi.util.TextRange;
import com.jetbrains.cidr.lang.types.OCTypeOwner;
import com.intellij.psi.PsiElement;
import com.jetbrains.cidr.lang.types.OCType;
import com.jetbrains.cidr.lang.psi.OCExpression;
import java.util.List;
import com.jetbrains.cidr.lang.psi.OCElement;
import com.intellij.psi.MultiRangeReference;
import com.jetbrains.cidr.lang.resolve.references.OCOperatorReference;

class 1OCCallOperatorReference extends OCOperatorReference implements MultiRangeReference
{
    final /* synthetic */ OCCallExpressionImpl this$0;
    
    1OCCallOperatorReference(final OCElement ocElement, final List<OCExpression> list, final List<OCType> list2) {
        super(ocElement, "()", OperatorPlacement.POSTFIX, null, list, list2);
    }
    
    @Override
    public boolean isValid() {
        Label_0040: {
            try {
                if (!super.isValid()) {
                    return false;
                }
                final 1OCCallOperatorReference 1ocCallOperatorReference = this;
                final List<? extends OCTypeOwner> list = 1ocCallOperatorReference.myArguments;
                final int n = list.size();
                final 1OCCallOperatorReference 1ocCallOperatorReference2 = this;
                final OCCallExpressionImpl ocCallExpressionImpl = 1ocCallOperatorReference2.this$0;
                final List<OCExpression> list2 = ocCallExpressionImpl.getArguments();
                final int n2 = list2.size();
                final int n3 = 1;
                final int n4 = n2 + n3;
                if (n == n4) {
                    break Label_0040;
                }
                return false;
            }
            catch (IllegalStateException ex) {
                throw a(ex);
            }
            try {
                final 1OCCallOperatorReference 1ocCallOperatorReference = this;
                final List<? extends OCTypeOwner> list = 1ocCallOperatorReference.myArguments;
                final int n = list.size();
                final 1OCCallOperatorReference 1ocCallOperatorReference2 = this;
                final OCCallExpressionImpl ocCallExpressionImpl = 1ocCallOperatorReference2.this$0;
                final List<OCExpression> list2 = ocCallExpressionImpl.getArguments();
                final int n2 = list2.size();
                final int n3 = 1;
                final int n4 = n2 + n3;
                if (n == n4) {
                    return true;
                }
            }
            catch (IllegalStateException ex2) {
                throw a(ex2);
            }
        }
        return false;
    }
    
    @NotNull
    public List<TextRange> getRanges() {
        final ArrayList<TextRange> list = new ArrayList<TextRange>();
        final OCArgumentList argumentList = OCCallExpressionImpl.this.getArgumentList();
        final TextRange rangeInParent = OCElementUtil.getRangeInParent((PsiElement)argumentList);
        final PsiElement leftPar = argumentList.getLeftPar();
        try {
            if (leftPar != null) {
                list.add(OCElementUtil.getRangeInParent(leftPar).shiftRight(rangeInParent.getStartOffset()));
            }
        }
        catch (IllegalStateException ex) {
            throw a(ex);
        }
        final PsiElement rightPar = argumentList.getRightPar();
        try {
            if (rightPar != null) {
                list.add(OCElementUtil.getRangeInParent(rightPar).shiftRight(rangeInParent.getStartOffset()));
            }
        }
        catch (IllegalStateException ex2) {
            throw a(ex2);
        }
        ArrayList<TextRange> list2;
        try {
            list2 = list;
            if (list2 == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/psi/impl/OCCallExpressionImpl$1OCCallOperatorReference", "getRanges"));
            }
        }
        catch (IllegalStateException ex3) {
            throw a(ex3);
        }
        return list2;
    }
    
    private static IllegalStateException a(final IllegalStateException ex) {
        return ex;
    }
}
