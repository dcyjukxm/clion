// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.execution.debugger.breakpoints;

import com.intellij.icons.AllIcons;
import com.intellij.xdebugger.breakpoints.XLineBreakpoint;
import com.jetbrains.cidr.execution.debugger.CidrEvaluatorHelper;
import org.jetbrains.annotations.NotNull;
import com.intellij.openapi.application.Result;
import com.intellij.xdebugger.breakpoints.XBreakpoint;
import com.intellij.openapi.application.WriteAction;

class CidrCodePointHandlerBase$2 extends WriteAction<String> {
    final /* synthetic */ XBreakpoint val$breakpoint;
    
    protected void run(@NotNull final Result<String> result) throws Throwable {
        try {
            if (result == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "stringResult", "com/jetbrains/cidr/execution/debugger/breakpoints/CidrCodePointHandlerBase$2", "run"));
            }
        }
        catch (CidrEvaluatorHelper.ConversionException ex) {
            throw b(ex);
        }
        try {
            result.setResult((Object)CidrEvaluatorHelper.getInstance().convertExpression(CidrCodePointHandlerBase.this.myProcess, this.val$breakpoint.getCondition(), this.val$breakpoint.getSourcePosition()));
        }
        catch (CidrEvaluatorHelper.ConversionException ex2) {
            try {
                if (this.val$breakpoint instanceof XLineBreakpoint) {
                    CidrCodePointHandlerBase.this.myProcess.getSession().updateBreakpointPresentation((XLineBreakpoint)this.val$breakpoint, AllIcons.Debugger.Db_invalid_breakpoint, ex2.getMessage());
                }
            }
            catch (CidrEvaluatorHelper.ConversionException ex3) {
                throw b(ex3);
            }
        }
    }
    
    private static CidrEvaluatorHelper.ConversionException b(final CidrEvaluatorHelper.ConversionException ex) {
        return ex;
    }
}