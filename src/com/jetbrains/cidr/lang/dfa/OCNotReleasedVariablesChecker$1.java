// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.lang.dfa;

import com.jetbrains.cidr.lang.symbols.OCSymbol;
import com.jetbrains.cidr.lang.psi.OCExpression;
import java.util.Iterator;
import com.intellij.openapi.util.Pair;
import com.intellij.psi.util.PsiTreeUtil;
import com.jetbrains.cidr.lang.psi.OCCallable;
import com.jetbrains.cidr.lang.psi.OCReturnStatement;
import com.jetbrains.cidr.lang.inspections.OCNotReleasedIvarInspection;
import com.jetbrains.cidr.lang.psi.OCReferenceExpression;
import com.intellij.psi.PsiFile;
import com.jetbrains.cidr.lang.symbols.objc.OCMethodSymbol;
import com.intellij.psi.PsiElement;
import com.jetbrains.cidr.lang.util.OCElementUtil;
import com.jetbrains.cidr.lang.psi.OCSendMessageExpression;
import java.util.List;
import com.jetbrains.cidr.lang.psi.visitors.OCRecursiveVisitor;

static final class OCNotReleasedVariablesChecker$1 extends OCRecursiveVisitor {
    final /* synthetic */ List val$result;
    
    @Override
    public void visitSendMessageExpression(final OCSendMessageExpression ocSendMessageExpression) {
        super.visitSendMessageExpression(ocSendMessageExpression);
        if (!OCElementUtil.isRetainCall((PsiElement)ocSendMessageExpression, false)) {
            return;
        }
        final List<OCMethodSymbol> allResponders = ocSendMessageExpression.getProbableResponders().getAllResponders();
        if (allResponders.isEmpty()) {
            return;
        }
        final Iterator<OCMethodSymbol> iterator = allResponders.iterator();
        while (iterator.hasNext()) {
            if (!iterator.next().getReturnType().resolve((PsiFile)ocSendMessageExpression.getContainingOCFile()).isPointerToObjectCompatible()) {
                return;
            }
        }
        final OCExpression receiverExpression = ocSendMessageExpression.getReceiverExpression();
        if ("retain".equals(ocSendMessageExpression.getMessageSelector())) {
            if (receiverExpression instanceof OCReferenceExpression) {
                final OCSymbol resolveToSymbol = ((OCReferenceExpression)receiverExpression).resolveToSymbol();
                if (resolveToSymbol != null && resolveToSymbol.getKind().isVariable()) {
                    return;
                }
            }
            else if (OCNotReleasedIvarInspection.getReceiverIvar(receiverExpression) != null) {
                return;
            }
        }
        final PsiElement access$000 = OCNotReleasedVariablesChecker.access$000(ocSendMessageExpression);
        if (access$000 != null) {
            if (access$000.getParent() instanceof OCReturnStatement && OCElementUtil.isRetainMethod((OCCallable)PsiTreeUtil.getParentOfType(access$000, (Class)OCCallable.class))) {
                return;
            }
            this.val$result.add(Pair.create((Object)ocSendMessageExpression, (Object)access$000));
        }
    }
}