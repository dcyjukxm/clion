// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.lang.dfa.contextSensitive;

import com.jetbrains.cidr.lang.psi.OCGotoStatement;
import com.intellij.openapi.util.Ref;
import com.jetbrains.cidr.lang.psi.visitors.OCRecursiveVisitor;

static final class OCDFAUtils$2 extends OCRecursiveVisitor {
    final /* synthetic */ Ref val$hasGoto;
    
    @Override
    public void visitGotoStatement(final OCGotoStatement ocGotoStatement) {
        this.val$hasGoto.set((Object)true);
    }
}