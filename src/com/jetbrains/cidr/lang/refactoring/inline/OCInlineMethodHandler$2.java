// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.lang.refactoring.inline;

import com.intellij.psi.PsiNamedElement;
import com.jetbrains.cidr.lang.psi.OCDeclarator;
import java.util.Map;
import com.jetbrains.cidr.lang.psi.visitors.OCRecursiveVisitor;

class OCInlineMethodHandler$2 extends OCRecursiveVisitor {
    final /* synthetic */ Map val$declaratorUsages;
    
    @Override
    public void visitDeclarator(final OCDeclarator ocDeclarator) {
        super.visitDeclarator(ocDeclarator);
        this.val$declaratorUsages.put(ocDeclarator, OCInlineMethodHandler.access$000((PsiNamedElement)ocDeclarator));
    }
}