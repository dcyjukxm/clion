// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.execution.debugger.evaluation.renderers;

import org.jetbrains.annotations.Nullable;
import com.intellij.execution.ExecutionException;
import com.jetbrains.cidr.execution.debugger.evaluation.CidrPhysicalValue;
import com.jetbrains.cidr.execution.debugger.CidrDebuggerSettings;
import com.jetbrains.cidr.execution.debugger.backend.DebuggerCommandException;
import com.jetbrains.cidr.execution.debugger.backend.LLValue;
import org.jetbrains.annotations.NotNull;
import com.jetbrains.cidr.execution.debugger.evaluation.ValueRendererFactory;

public static class Factory implements ValueRendererFactory
{
    @Nullable
    @Override
    public ValueRenderer createRenderer(@NotNull final FactoryContext factoryContext) throws ExecutionException, DebuggerCommandException {
        try {
            if (factoryContext == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "context", "com/jetbrains/cidr/execution/debugger/evaluation/renderers/NSManagedObjectValueRenderer$Factory", "createRenderer"));
            }
        }
        catch (NotApplicableException ex) {
            throw b(ex);
        }
        Label_0071: {
            try {
                if (!factoryContext.getSettings().COCOA_RENDERERS_ENABLED) {
                    break Label_0071;
                }
                final FactoryContext factoryContext2 = factoryContext;
                final CidrDebuggerSettings cidrDebuggerSettings = factoryContext2.getSettings();
                final boolean b = cidrDebuggerSettings.CORE_DATA_RENDERERS_ENABLED;
                if (!b) {
                    break Label_0071;
                }
                break Label_0071;
            }
            catch (NotApplicableException ex2) {
                throw b(ex2);
            }
            try {
                final FactoryContext factoryContext2 = factoryContext;
                final CidrDebuggerSettings cidrDebuggerSettings = factoryContext2.getSettings();
                final boolean b = cidrDebuggerSettings.CORE_DATA_RENDERERS_ENABLED;
                if (!b) {
                    return null;
                }
            }
            catch (NotApplicableException ex3) {
                throw b(ex3);
            }
        }
        final CidrPhysicalValue physicalValue = factoryContext.getPhysicalValue();
        try {
            if (physicalValue.getVar().getTypeClass() != LLValue.TypeClass.OBJC_POINTER) {
                return null;
            }
        }
        catch (NotApplicableException ex4) {
            throw b(ex4);
        }
        try {
            if (factoryContext.getLLValueData().isSynthetic()) {
                return null;
            }
        }
        catch (NotApplicableException ex5) {
            throw b(ex5);
        }
        try {
            return new NSManagedObjectValueRenderer(factoryContext.getPhysicalValue(), factoryContext.getEvaluationContext());
        }
        catch (NotApplicableException ex6) {
            return null;
        }
        catch (DebuggerCommandException ex7) {
            return null;
        }
    }
    
    private static NotApplicableException b(final NotApplicableException ex) {
        return ex;
    }
}
