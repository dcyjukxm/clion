// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.execution.debugger;

import com.intellij.openapi.components.ServiceManager;
import com.jetbrains.cidr.execution.debugger.backend.DebuggerCommandException;
import com.intellij.execution.ExecutionException;
import com.jetbrains.cidr.execution.debugger.backend.LLValue;
import com.intellij.openapi.util.Pair;
import com.intellij.xdebugger.XExpression;
import com.jetbrains.cidr.execution.debugger.backend.DebuggerDriver;
import org.jetbrains.annotations.Nullable;
import com.intellij.xdebugger.XSourcePosition;
import org.jetbrains.annotations.NotNull;

public abstract class CidrEvaluatorHelper
{
    public abstract String convertExpression(@NotNull final CidrDebugProcess p0, final String p1, @Nullable final XSourcePosition p2) throws ConversionException;
    
    public abstract Pair<LLValue, String> convertAndEvaluate(@NotNull final CidrDebugProcess p0, @NotNull final DebuggerDriver p1, @NotNull final XExpression p2, final XSourcePosition p3, final long p4, final int p5) throws ExecutionException, DebuggerCommandException;
    
    public static CidrEvaluatorHelper getInstance() {
        return (CidrEvaluatorHelper)ServiceManager.getService((Class)CidrEvaluatorHelper.class);
    }
    
    public static class ConversionException extends Exception
    {
        public ConversionException(final String s) {
            super(s);
        }
    }
}
