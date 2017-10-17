// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.lang.psi.impl;

import com.jetbrains.cidr.lang.psi.OCIncludeDirective;
import java.util.List;
import com.jetbrains.cidr.lang.psi.visitors.OCRecursiveVisitor;

class OCFileImpl$5 extends OCRecursiveVisitor {
    final /* synthetic */ List val$answer;
    
    @Override
    public void visitImportDirective(final OCIncludeDirective ocIncludeDirective) {
        this.val$answer.add(ocIncludeDirective);
    }
}