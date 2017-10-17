// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.execution.debugger.backend.gdb;

import com.jetbrains.cidr.execution.debugger.backend.DebuggerCommandException;
import com.intellij.execution.ExecutionException;
import com.jetbrains.cidr.execution.debugger.backend.LLValue;
import org.jetbrains.annotations.NotNull;

private class CreatedValueLoader implements LLValueLoader
{
    @NotNull
    private final LLValueLoadedData partialLoadedData;
    
    public CreatedValueLoader(final LLValueLoadedData partialLoadedData) {
        if (partialLoadedData == null) {
            throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "partialLoadedData", "com/jetbrains/cidr/execution/debugger/backend/gdb/GDBDriver$CreatedValueLoader", "<init>"));
        }
        this.partialLoadedData = partialLoadedData;
    }
    
    @Override
    public void loadValue(@NotNull final LLValue llValue) throws ExecutionException, DebuggerCommandException {
        try {
            if (llValue == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "value", "com/jetbrains/cidr/execution/debugger/backend/gdb/GDBDriver$CreatedValueLoader", "loadValue"));
            }
        }
        catch (ExecutionException ex) {
            throw b(ex);
        }
        final String id = this.partialLoadedData.id;
        GDBDriver.access$2200(llValue, new LLValueLoadedData(id, this.partialLoadedData.childrenCount, this.partialLoadedData.isDynamic, this.partialLoadedData.hasDynamicChildren, this.partialLoadedData.isMap, GDBDriver.this.sendRequestAndWaitForDone("-var-evaluate-expression %s", GDBDriver.stringify(id)).getResultList().getRequiredString("value")));
    }
    
    private static ExecutionException b(final ExecutionException ex) {
        return ex;
    }
}
