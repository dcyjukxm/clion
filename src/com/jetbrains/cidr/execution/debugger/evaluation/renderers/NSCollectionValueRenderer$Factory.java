// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.execution.debugger.evaluation.renderers;

import org.jetbrains.annotations.Nullable;
import com.jetbrains.cidr.execution.debugger.backend.DebuggerCommandException;
import java.util.regex.Matcher;
import com.jetbrains.cidr.execution.debugger.backend.LLValue;
import java.util.regex.Pattern;
import com.intellij.execution.ExecutionException;
import org.jetbrains.annotations.NotNull;
import com.jetbrains.cidr.execution.debugger.evaluation.ValueRendererFactory;

public static class Factory implements ValueRendererFactory
{
    @Nullable
    @Override
    public ValueRenderer createRenderer(@NotNull final FactoryContext factoryContext) throws ExecutionException, DebuggerCommandException {
        try {
            if (factoryContext == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "context", "com/jetbrains/cidr/execution/debugger/evaluation/renderers/NSCollectionValueRenderer$Factory", "createRenderer"));
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
        final Kind nsCollectionKind = NSCollectionValueRenderer.getNSCollectionKind(factoryContext);
        try {
            if (nsCollectionKind == null) {
                return null;
            }
        }
        catch (ExecutionException ex3) {
            throw b(ex3);
        }
        Label_0111: {
            try {
                if (!factoryContext.getLLValueData().isSynthetic()) {
                    break Label_0111;
                }
                final Pattern pattern = NSCollectionValueRenderer.access$000();
                final FactoryContext factoryContext2 = factoryContext;
                final LLValue llValue = factoryContext2.getLLValue();
                final String s = llValue.getType();
                final Matcher matcher = pattern.matcher(s);
                final boolean b = matcher.matches();
                if (b) {
                    break Label_0111;
                }
                return new NSCollectionValueRenderer(factoryContext.getPhysicalValue(), nsCollectionKind);
            }
            catch (ExecutionException ex4) {
                throw b(ex4);
            }
            try {
                final Pattern pattern = NSCollectionValueRenderer.access$000();
                final FactoryContext factoryContext2 = factoryContext;
                final LLValue llValue = factoryContext2.getLLValue();
                final String s = llValue.getType();
                final Matcher matcher = pattern.matcher(s);
                final boolean b = matcher.matches();
                if (b) {
                    return new NSEnumerableCollectionValueRenderer(factoryContext.getPhysicalValue(), nsCollectionKind);
                }
            }
            catch (ExecutionException ex5) {
                throw b(ex5);
            }
        }
        return new NSCollectionValueRenderer(factoryContext.getPhysicalValue(), nsCollectionKind);
    }
    
    private static ExecutionException b(final ExecutionException ex) {
        return ex;
    }
}
