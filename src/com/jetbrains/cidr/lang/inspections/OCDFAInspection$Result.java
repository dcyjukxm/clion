// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.lang.inspections;

import com.jetbrains.cidr.lang.dfa.contextSensitive.OCContextSensitiveControlFlowBuilder;
import com.jetbrains.sourceglider.ManagerInstancesProvider;

private static class Result
{
    private ManagerInstancesProvider myProvider;
    private OCContextSensitiveControlFlowBuilder myFlowBuilder;
    private boolean myFinishedSuccessfully;
    
    public Result(final ManagerInstancesProvider myProvider, final OCContextSensitiveControlFlowBuilder myFlowBuilder, final boolean myFinishedSuccessfully) {
        this.myProvider = myProvider;
        this.myFlowBuilder = myFlowBuilder;
        this.myFinishedSuccessfully = myFinishedSuccessfully;
    }
    
    public ManagerInstancesProvider getProvider() {
        return this.myProvider;
    }
    
    public OCContextSensitiveControlFlowBuilder getFlowBuilder() {
        return this.myFlowBuilder;
    }
    
    public boolean isFinishedSuccessfully() {
        return this.myFinishedSuccessfully;
    }
}
