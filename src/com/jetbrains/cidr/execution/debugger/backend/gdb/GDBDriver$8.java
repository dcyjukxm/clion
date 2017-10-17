// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.execution.debugger.backend.gdb;

import org.jetbrains.annotations.NotNull;
import com.jetbrains.cidr.execution.debugger.backend.LLValue;

class GDBDriver$8 extends StructChildrenVisitor {
    final /* synthetic */ int[] val$childrenCount;
    
    public boolean visitFakeChild(@NotNull final LLValue llValue, final int n) {
        try {
            if (llValue == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "fakeChild", "com/jetbrains/cidr/execution/debugger/backend/gdb/GDBDriver$8", "visitFakeChild"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        final int[] val$childrenCount = this.val$childrenCount;
        final int n2 = 0;
        --val$childrenCount[n2];
        final int[] val$childrenCount2 = this.val$childrenCount;
        final int n3 = 0;
        val$childrenCount2[n3] += n;
        return true;
    }
    
    private static IllegalArgumentException a(final IllegalArgumentException ex) {
        return ex;
    }
}