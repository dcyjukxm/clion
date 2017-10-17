// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.execution.debugger.evaluation;

import com.intellij.xdebugger.frame.XValueModifier;
import com.jetbrains.cidr.execution.debugger.CidrStackFrame;
import org.jetbrains.annotations.Nullable;
import com.intellij.xdebugger.XSourcePosition;
import com.jetbrains.cidr.execution.debugger.CidrDebugProcess;
import org.jetbrains.annotations.NotNull;
import com.jetbrains.cidr.execution.debugger.backend.LLValue;

public class CidrLocalValue extends CidrPhysicalValue
{
    public CidrLocalValue(@NotNull final LLValue llValue, @NotNull final CidrDebugProcess cidrDebugProcess, @Nullable final XSourcePosition xSourcePosition, @NotNull final CidrStackFrame cidrStackFrame) {
        if (llValue == null) {
            throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "var", "com/jetbrains/cidr/execution/debugger/evaluation/CidrLocalValue", "<init>"));
        }
        if (cidrDebugProcess == null) {
            throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "process", "com/jetbrains/cidr/execution/debugger/evaluation/CidrLocalValue", "<init>"));
        }
        if (cidrStackFrame == null) {
            throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "frame", "com/jetbrains/cidr/execution/debugger/evaluation/CidrLocalValue", "<init>"));
        }
        super(llValue, cidrDebugProcess, xSourcePosition, cidrStackFrame);
    }
    
    @Nullable
    @Override
    protected XSourcePosition doComputePosition(@NotNull final XSourcePosition xSourcePosition) {
        try {
            if (xSourcePosition == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "position", "com/jetbrains/cidr/execution/debugger/evaluation/CidrLocalValue", "doComputePosition"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        return this.getTypesHelper().computeSourcePosition(xSourcePosition, this.getVar());
    }
    
    public XValueModifier getModifier() {
        return new CidrValueModifier(this);
    }
    
    private static IllegalArgumentException a(final IllegalArgumentException ex) {
        return ex;
    }
}
