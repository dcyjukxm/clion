// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.execution.debugger.backend.gdb;

import com.jetbrains.cidr.execution.debugger.backend.DebuggerCommandException;
import java.util.Collection;
import com.intellij.execution.ExecutionException;
import org.jetbrains.annotations.NotNull;
import com.jetbrains.cidr.execution.debugger.backend.LLValue;
import java.util.List;

class GDBDriver$7 extends StructChildrenVisitor {
    int currentOffset = this.val$offset;
    int restCount = this.val$count;
    final /* synthetic */ int val$offset;
    final /* synthetic */ int val$count;
    final /* synthetic */ List val$result;
    final /* synthetic */ LLValue val$var;
    
    public boolean visitRealChild(@NotNull final GDBTuple gdbTuple) throws ExecutionException {
        try {
            if (gdbTuple == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "child", "com/jetbrains/cidr/execution/debugger/backend/gdb/GDBDriver$7", "visitRealChild"));
            }
        }
        catch (ExecutionException ex) {
            throw b(ex);
        }
        try {
            if (this.currentOffset >= 1) {
                --this.currentOffset;
                return true;
            }
        }
        catch (ExecutionException ex2) {
            throw b(ex2);
        }
        try {
            this.val$result.add(GDBDriver.access$900(GDBDriver.this, gdbTuple, this.val$var.getReferenceExpression(), null));
            --this.restCount;
            this.currentOffset = Math.max(0, this.currentOffset - 1);
            if (this.restCount != 0) {
                return true;
            }
        }
        catch (ExecutionException ex3) {
            throw b(ex3);
        }
        return false;
    }
    
    public boolean visitFakeChild(@NotNull final LLValue llValue, final int n) throws ExecutionException, DebuggerCommandException {
        try {
            if (llValue == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "fakeChild", "com/jetbrains/cidr/execution/debugger/backend/gdb/GDBDriver$7", "visitFakeChild"));
            }
        }
        catch (ExecutionException ex) {
            throw b(ex);
        }
        try {
            if (this.currentOffset >= n) {
                this.currentOffset -= n;
                return true;
            }
        }
        catch (ExecutionException ex2) {
            throw b(ex2);
        }
        final int min = Math.min(this.currentOffset + this.restCount, n);
        try {
            this.val$result.addAll(GDBDriver.access$1000(GDBDriver.this, llValue, this.currentOffset, min).list);
            this.restCount -= min - this.currentOffset;
            this.currentOffset = 0;
            if (this.restCount != 0) {
                return true;
            }
        }
        catch (ExecutionException ex3) {
            throw b(ex3);
        }
        return false;
    }
    
    private static ExecutionException b(final ExecutionException ex) {
        return ex;
    }
}