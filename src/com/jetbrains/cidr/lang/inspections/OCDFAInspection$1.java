// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.lang.inspections;

import com.jetbrains.cidr.lang.dfa.contextSensitive.OCContextSensitiveControlFlowBuilder;
import com.jetbrains.cidr.lang.psi.OCCallable;
import com.jetbrains.sourceglider.ui.ThreadCallback;
import java.util.Map;
import com.intellij.openapi.util.Ref;
import com.jetbrains.cidr.lang.psi.visitors.OCRecursiveVisitor;

static final class OCDFAInspection$1 extends OCRecursiveVisitor {
    final /* synthetic */ Ref val$flowBuilder;
    final /* synthetic */ Map val$options1;
    final /* synthetic */ ThreadCallback val$threadCallback;
    
    @Override
    public void visitCallable(final OCCallable ocCallable) {
        ((OCContextSensitiveControlFlowBuilder)this.val$flowBuilder.get()).processCallable(ocCallable, this.val$options1, this.val$threadCallback);
    }
}