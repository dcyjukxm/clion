// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.lang.editor.surround;

import com.jetbrains.cidr.lang.symbols.OCSymbolWithParent;
import com.jetbrains.cidr.lang.symbols.objc.OCMethodSymbol;
import com.jetbrains.cidr.lang.symbols.objc.OCClassSymbol;
import com.jetbrains.cidr.lang.psi.OCSendMessageExpression;
import java.util.List;
import com.jetbrains.cidr.lang.psi.visitors.OCRecursiveVisitor;

static final class OCIfRespondsToSurrounder$2 extends OCRecursiveVisitor {
    final /* synthetic */ List val$result;
    
    @Override
    public void visitSendMessageExpression(final OCSendMessageExpression ocSendMessageExpression) {
        final OCMethodSymbol knownResponder = ocSendMessageExpression.getProbableResponders().getKnownResponder();
        if (knownResponder == null || knownResponder.isOptional() || !((OCSymbolWithParent<T, OCClassSymbol>)knownResponder).getParent().getName().equals("NSObject") || ((OCSymbolWithParent<T, OCClassSymbol>)knownResponder).getParent().getCategoryName() != null) {
            this.val$result.add(ocSendMessageExpression);
        }
        super.visitSendMessageExpression(ocSendMessageExpression);
    }
}