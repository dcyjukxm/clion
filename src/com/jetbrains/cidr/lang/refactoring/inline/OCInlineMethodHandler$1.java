// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.lang.refactoring.inline;

import com.jetbrains.cidr.lang.util.OCElementsRange;
import org.jetbrains.annotations.Nullable;
import org.jetbrains.annotations.NotNull;
import java.util.Iterator;
import com.jetbrains.cidr.lang.dfa.OCInstruction;
import com.jetbrains.cidr.lang.symbols.OCSymbol;
import com.jetbrains.cidr.lang.dfa.OCNode;
import com.jetbrains.cidr.lang.dfa.OCControlFlowGraph;
import com.intellij.openapi.util.Ref;
import com.jetbrains.cidr.lang.dfa.OCDataFlowAlgorithm;

static final class OCInlineMethodHandler$1 extends OCDataFlowAlgorithm {
    final /* synthetic */ Ref val$error;
    
    @Override
    public void process() {
        final Iterator<OCNode> iterator = this.myCfg.getExitNodes().iterator();
        while (iterator.hasNext()) {
            final OCNode nodeAfterReturn = iterator.next().getNodeAfterReturn();
            try {
                if (nodeAfterReturn == null) {
                    continue;
                }
                this.traverse(nodeAfterReturn, null, null, true);
            }
            catch (IllegalArgumentException ex) {
                throw b(ex);
            }
        }
    }
    
    @Override
    protected boolean processNode(@NotNull final OCNode ocNode, final OCSymbol ocSymbol, final boolean b, @Nullable final OCInstruction ocInstruction, final OCInstruction ocInstruction2) {
        try {
            if (ocNode == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "node", "com/jetbrains/cidr/lang/refactoring/inline/OCInlineMethodHandler$1", "processNode"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw b(ex);
        }
        final OCElementsRange range = ocNode.getRange();
        Label_0070: {
            try {
                if (range == null) {
                    return true;
                }
                final OCElementsRange ocElementsRange = range;
                final boolean b2 = ocElementsRange.isEmpty();
                if (!b2) {
                    break Label_0070;
                }
                return true;
            }
            catch (IllegalArgumentException ex2) {
                throw b(ex2);
            }
            try {
                final OCElementsRange ocElementsRange = range;
                final boolean b2 = ocElementsRange.isEmpty();
                if (!b2) {
                    this.val$error.set((Object)"Cannot inline methods with return statements interrupting the execution flow");
                    return false;
                }
            }
            catch (IllegalArgumentException ex3) {
                throw b(ex3);
            }
        }
        return true;
    }
    
    private static IllegalArgumentException b(final IllegalArgumentException ex) {
        return ex;
    }
}