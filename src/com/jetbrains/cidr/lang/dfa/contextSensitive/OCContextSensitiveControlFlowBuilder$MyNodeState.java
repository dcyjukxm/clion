// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.lang.dfa.contextSensitive;

import com.jetbrains.cidr.lang.dfa.OCNode;
import org.jetbrains.annotations.NotNull;
import com.jetbrains.cidr.lang.dfa.OCControlFlowBuilder;

private class MyNodeState implements NodeState
{
    private final int myContextsCnt;
    
    private MyNodeState(final OCNode ocNode) {
        if (ocNode == null) {
            throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "node", "com/jetbrains/cidr/lang/dfa/contextSensitive/OCContextSensitiveControlFlowBuilder$MyNodeState", "<init>"));
        }
        this.myContextsCnt = OCContextSensitiveControlFlowBuilder.access$000(OCContextSensitiveControlFlowBuilder.this).get((Object)ocNode).size();
    }
}
