// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.lang.refactoring;

import com.jetbrains.cidr.lang.psi.OCReferenceElement;
import java.util.List;
import com.jetbrains.cidr.lang.psi.visitors.OCRecursiveVisitor;

static final class OCNameSuggester$1 extends OCRecursiveVisitor {
    final /* synthetic */ List val$forbiddenNames;
    
    @Override
    public void visitReferenceElement(final OCReferenceElement ocReferenceElement) {
        if (ocReferenceElement.resolveToSymbol() != null) {
            this.val$forbiddenNames.add(ocReferenceElement.getName());
        }
    }
}