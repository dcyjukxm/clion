// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.execution.debugger.evaluation;

import com.intellij.xdebugger.frame.presentation.XValuePresentation;
import com.intellij.xdebugger.frame.presentation.XErrorValuePresentation;
import com.intellij.xdebugger.impl.ui.XDebuggerUIConstants;
import com.intellij.xdebugger.frame.XValuePlace;
import com.intellij.xdebugger.frame.XValueNode;
import org.jetbrains.annotations.NotNull;
import com.intellij.xdebugger.frame.XNamedValue;

public class CidrErrorValue extends XNamedValue
{
    @NotNull
    private final String myErrorMessage;
    
    public CidrErrorValue(@NotNull final String myErrorMessage) {
        if (myErrorMessage == null) {
            throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "errorMessage", "com/jetbrains/cidr/execution/debugger/evaluation/CidrErrorValue", "<init>"));
        }
        super("error");
        this.myErrorMessage = myErrorMessage;
    }
    
    public void computePresentation(@NotNull final XValueNode xValueNode, @NotNull final XValuePlace xValuePlace) {
        try {
            if (xValueNode == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "node", "com/jetbrains/cidr/execution/debugger/evaluation/CidrErrorValue", "computePresentation"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        try {
            if (xValuePlace == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "place", "com/jetbrains/cidr/execution/debugger/evaluation/CidrErrorValue", "computePresentation"));
            }
        }
        catch (IllegalArgumentException ex2) {
            throw a(ex2);
        }
        doComputePresentation(xValueNode, this.myErrorMessage, false);
    }
    
    public static void doComputePresentation(@NotNull final XValueNode xValueNode, @NotNull final String s, final boolean b) {
        try {
            if (xValueNode == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "node", "com/jetbrains/cidr/execution/debugger/evaluation/CidrErrorValue", "doComputePresentation"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        try {
            if (s == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "errorMessage", "com/jetbrains/cidr/execution/debugger/evaluation/CidrErrorValue", "doComputePresentation"));
            }
        }
        catch (IllegalArgumentException ex2) {
            throw a(ex2);
        }
        xValueNode.setPresentation(XDebuggerUIConstants.ERROR_MESSAGE_ICON, (XValuePresentation)new XErrorValuePresentation(s), b);
    }
    
    private static IllegalArgumentException a(final IllegalArgumentException ex) {
        return ex;
    }
}
