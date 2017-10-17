// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.lang.dfa.contextSensitive;

import java.util.Iterator;
import com.jetbrains.sourceglider.atttributes.Attribute;
import org.jetbrains.annotations.NotNull;
import com.jetbrains.cidr.lang.dfa.OCNode;
import com.jetbrains.cidr.lang.dfa.OCControlFlowGraph;
import com.jetbrains.cidr.lang.dfa.OCUnreachableCodeFinder;

class OCContextSensitiveControlFlowBuilder$1 extends OCUnreachableCodeFinder {
    @Override
    protected void processDeadEnd(@NotNull final OCNode ocNode) {
        try {
            if (ocNode == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "node", "com/jetbrains/cidr/lang/dfa/contextSensitive/OCContextSensitiveControlFlowBuilder$1", "processDeadEnd"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw c(ex);
        }
        final Iterator<Integer> iterator = OCContextSensitiveControlFlowBuilder.access$000(OCContextSensitiveControlFlowBuilder.this).get((Object)ocNode).iterator();
        while (iterator.hasNext()) {
            OCContextSensitiveControlFlowBuilder.access$400(OCContextSensitiveControlFlowBuilder.this).addTuple(OCContextSensitiveControlFlowBuilder.access$100(OCContextSensitiveControlFlowBuilder.this), OCContextSensitiveControlFlowBuilder.access$200(OCContextSensitiveControlFlowBuilder.this, iterator.next()), OCContextSensitiveControlFlowBuilder.access$300(OCContextSensitiveControlFlowBuilder.this));
        }
    }
    
    private static IllegalArgumentException c(final IllegalArgumentException ex) {
        return ex;
    }
}