// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.execution.debugger.evaluation.renderers;

import org.jetbrains.annotations.Nullable;
import com.jetbrains.cidr.execution.debugger.backend.DebuggerCommandException;
import com.intellij.execution.ExecutionException;
import org.jetbrains.annotations.NotNull;
import com.jetbrains.cidr.execution.debugger.evaluation.ValueRendererFactory;

public class NSBasicValueRendererFactory implements ValueRendererFactory
{
    @Nullable
    @Override
    public ValueRenderer createRenderer(@NotNull final FactoryContext factoryContext) throws ExecutionException, DebuggerCommandException {
        try {
            if (factoryContext == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "context", "com/jetbrains/cidr/execution/debugger/evaluation/renderers/NSBasicValueRendererFactory", "createRenderer"));
            }
        }
        catch (ExecutionException ex) {
            throw b(ex);
        }
        try {
            if (!factoryContext.getSettings().COCOA_RENDERERS_ENABLED) {
                return null;
            }
        }
        catch (ExecutionException ex2) {
            throw b(ex2);
        }
        return SelectorValueRenderer.createIfSelector(factoryContext);
    }
    
    private static ExecutionException b(final ExecutionException ex) {
        return ex;
    }
}
