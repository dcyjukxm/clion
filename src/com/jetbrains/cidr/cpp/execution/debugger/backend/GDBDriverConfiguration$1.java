// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.cpp.execution.debugger.backend;

import com.jetbrains.cidr.execution.debugger.backend.DebuggerCommandException;
import com.jetbrains.cidr.execution.debugger.backend.LLValue;
import com.intellij.openapi.util.Pair;
import org.jetbrains.annotations.NotNull;
import com.jetbrains.cidr.execution.debugger.backend.LLValueData;
import com.jetbrains.cidr.execution.debugger.CidrStackFrame;
import com.intellij.openapi.util.Expirable;
import com.jetbrains.cidr.execution.debugger.backend.DebuggerDriver;
import com.jetbrains.cidr.execution.debugger.evaluation.EvaluationContext;

class GDBDriverConfiguration$1 extends EvaluationContext {
    @NotNull
    @Override
    public String convertToRValue(@NotNull final LLValueData llValueData, @NotNull final Pair<LLValue, String> pair) throws DebuggerCommandException {
        try {
            if (llValueData == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "rValue", "com/jetbrains/cidr/cpp/execution/debugger/backend/GDBDriverConfiguration$1", "convertToRValue"));
            }
        }
        catch (DebuggerCommandException ex) {
            throw b(ex);
        }
        try {
            if (pair == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "valuePair", "com/jetbrains/cidr/cpp/execution/debugger/backend/GDBDriverConfiguration$1", "convertToRValue"));
            }
        }
        catch (DebuggerCommandException ex2) {
            throw b(ex2);
        }
        String cast;
        try {
            cast = EvaluationContext.cast((String)pair.getSecond(), ((LLValue)pair.getFirst()).getType());
            if (cast == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/cpp/execution/debugger/backend/GDBDriverConfiguration$1", "convertToRValue"));
            }
        }
        catch (DebuggerCommandException ex3) {
            throw b(ex3);
        }
        return cast;
    }
    
    private static DebuggerCommandException b(final DebuggerCommandException ex) {
        return ex;
    }
}