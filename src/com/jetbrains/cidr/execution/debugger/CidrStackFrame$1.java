// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.execution.debugger;

import com.intellij.xdebugger.frame.XValuePlace;
import org.jetbrains.annotations.NotNull;
import com.intellij.xdebugger.frame.XValueNode;
import com.intellij.xdebugger.frame.XNamedValue;

class CidrStackFrame$1 extends XNamedValue {
    public void computePresentation(@NotNull final XValueNode xValueNode, @NotNull final XValuePlace xValuePlace) {
        try {
            if (xValueNode == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "node", "com/jetbrains/cidr/execution/debugger/CidrStackFrame$1", "computePresentation"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        try {
            if (xValuePlace == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "place", "com/jetbrains/cidr/execution/debugger/CidrStackFrame$1", "computePresentation"));
            }
        }
        catch (IllegalArgumentException ex2) {
            throw a(ex2);
        }
        xValueNode.setPresentation(CidrStackFrame.access$000(CidrStackFrame.this).icon, (String)null, CidrStackFrame.access$000(CidrStackFrame.this).reason, false);
    }
    
    private static IllegalArgumentException a(final IllegalArgumentException ex) {
        return ex;
    }
}