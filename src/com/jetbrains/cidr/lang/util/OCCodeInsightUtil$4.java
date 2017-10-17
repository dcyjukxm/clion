// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.lang.util;

import com.jetbrains.cidr.lang.psi.OCDeclarator;
import com.intellij.psi.PsiElementVisitor;
import com.jetbrains.cidr.lang.psi.OCElement;
import com.jetbrains.cidr.lang.psi.visitors.OCVisitor;

static final class OCCodeInsightUtil$4 extends OCVisitor {
    final /* synthetic */ String val$name;
    
    @Override
    public void visitOCElement(final OCElement ocElement) {
        ocElement.acceptChildren((PsiElementVisitor)this);
    }
    
    @Override
    public void visitDeclarator(final OCDeclarator ocDeclarator) {
        try {
            if (this.val$name.equals(ocDeclarator.getName())) {
                throw new CancelException(ocDeclarator);
            }
        }
        catch (CancelException ex) {
            throw b(ex);
        }
    }
    
    private static CancelException b(final CancelException ex) {
        return ex;
    }
}