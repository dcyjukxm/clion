// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.execution.debugger.evaluation.renderers;

import com.jetbrains.cidr.execution.debugger.evaluation.CidrPhysicalValue;
import org.jetbrains.annotations.NotNull;
import com.intellij.util.containers.ContainerUtil;
import org.jetbrains.annotations.Nullable;
import com.jetbrains.cidr.execution.debugger.backend.DebuggerDriver;
import com.intellij.openapi.extensions.ExtensionPointName;

public abstract class ValueRendererExtension
{
    protected static final ExtensionPointName<ValueRendererExtension> EP_NAME;
    
    @Nullable
    public static ValueRendererExtension getExtension(@Nullable final DebuggerDriver.DebuggerLanguage debuggerLanguage) {
        if (debuggerLanguage == null) {
            return null;
        }
        return (ValueRendererExtension)ContainerUtil.find(ValueRendererExtension.EP_NAME.getExtensions(), valueRendererExtension -> valueRendererExtension.handlesLanguage(debuggerLanguage));
    }
    
    public abstract boolean handlesLanguage(@NotNull final DebuggerDriver.DebuggerLanguage p0);
    
    @NotNull
    public abstract String getChildEvaluationExpression(@NotNull final CidrPhysicalValue p0, @NotNull final String p1, @NotNull final CidrPhysicalValue p2);
    
    static {
        EP_NAME = ExtensionPointName.create("cidr.debugger.valueRendererExtension");
    }
}
