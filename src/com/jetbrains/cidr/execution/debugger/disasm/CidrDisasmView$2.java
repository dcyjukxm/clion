// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.execution.debugger.disasm;

import com.intellij.execution.ExecutionException;
import com.jetbrains.cidr.execution.debugger.CidrDebuggerLog;
import com.jetbrains.cidr.execution.debugger.memory.AddressSpace;
import com.jetbrains.cidr.execution.debugger.backend.DebuggerCommandException;
import org.jetbrains.annotations.NotNull;
import com.jetbrains.cidr.execution.debugger.backend.DebuggerDriver;
import com.jetbrains.cidr.execution.debugger.memory.AddressRange;
import com.jetbrains.cidr.execution.debugger.CidrDebugProcess;

class CidrDisasmView$2 implements CidrDebugProcess.DebuggerCommand {
    final /* synthetic */ AddressRange val$range;
    
    @Override
    public void run(@NotNull final DebuggerDriver debuggerDriver) throws ExecutionException {
        try {
            if (debuggerDriver == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "driver", "com/jetbrains/cidr/execution/debugger/disasm/CidrDisasmView$2", "run"));
            }
        }
        catch (DebuggerCommandException ex) {
            throw b(ex);
        }
        try {
            CidrDisasmView.access$400(CidrDisasmView.this, () -> CidrDisasmView.access$300(CidrDisasmView.this).save((AddressSpace.Region)new CidrDisasmRegion(this.val$range, debuggerDriver.disassemble(this.val$range))));
        }
        catch (DebuggerCommandException ex2) {
            CidrDebuggerLog.LOG.warn((Throwable)ex2);
            CidrDisasmView.access$400(CidrDisasmView.this, () -> CidrDisasmView.access$300(CidrDisasmView.this).unallocate(this.val$range));
        }
    }
    
    @Override
    public void rejected(@NotNull final String s) {
        try {
            if (s == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "reason", "com/jetbrains/cidr/execution/debugger/disasm/CidrDisasmView$2", "rejected"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw b(ex);
        }
        CidrDebuggerLog.LOG.warn(s);
        CidrDisasmView.access$400(CidrDisasmView.this, () -> CidrDisasmView.access$300(CidrDisasmView.this).unallocate(this.val$range));
    }
    
    private static Exception b(final Exception ex) {
        return ex;
    }
}