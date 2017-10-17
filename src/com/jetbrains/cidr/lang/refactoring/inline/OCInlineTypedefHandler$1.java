// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.lang.refactoring.inline;

import com.jetbrains.cidr.lang.types.OCReferenceType;
import com.jetbrains.cidr.lang.types.OCType;
import com.jetbrains.cidr.lang.symbols.OCSymbol;
import com.intellij.psi.PsiFile;
import com.jetbrains.cidr.lang.types.visitors.OCTypeCloneVisitor;

static final class OCInlineTypedefHandler$1 extends OCTypeCloneVisitor {
    final /* synthetic */ PsiFile val$containingFile;
    final /* synthetic */ OCSymbol val$symbol;
    final /* synthetic */ OCType val$elementType;
    
    @Override
    public OCType visitReferenceType(final OCReferenceType ocReferenceType) {
        if (ocReferenceType.getReference(this.val$containingFile).resolveToSymbols(this.val$containingFile).contains(this.val$symbol)) {
            return ocReferenceType.isConst() ? this.val$elementType.cloneWithConstModifier(this.val$symbol.getProject()) : this.val$elementType;
        }
        return super.visitReferenceType(ocReferenceType);
    }
}