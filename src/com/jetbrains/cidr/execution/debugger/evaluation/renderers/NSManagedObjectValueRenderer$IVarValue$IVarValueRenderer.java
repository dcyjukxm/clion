// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.execution.debugger.evaluation.renderers;

import com.jetbrains.cidr.execution.debugger.backend.LLValueData;
import com.jetbrains.cidr.execution.debugger.backend.LLValue;
import com.jetbrains.cidr.execution.debugger.evaluation.CidrPhysicalValue;
import org.jetbrains.annotations.NotNull;

private class IVarValueRenderer extends ValueRenderer
{
    private IVarValueRenderer(final NSManagedObjectMemberValue nsManagedObjectMemberValue) {
        if (nsManagedObjectMemberValue == null) {
            throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "value", "com/jetbrains/cidr/execution/debugger/evaluation/renderers/NSManagedObjectValueRenderer$IVarValue$IVarValueRenderer", "<init>"));
        }
        super(nsManagedObjectMemberValue);
    }
    
    @Override
    protected boolean areChildrenModifiable(@NotNull final LLValue llValue, @NotNull final LLValueData llValueData) {
        try {
            if (llValue == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "var", "com/jetbrains/cidr/execution/debugger/evaluation/renderers/NSManagedObjectValueRenderer$IVarValue$IVarValueRenderer", "areChildrenModifiable"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        try {
            if (llValueData == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "data", "com/jetbrains/cidr/execution/debugger/evaluation/renderers/NSManagedObjectValueRenderer$IVarValue$IVarValueRenderer", "areChildrenModifiable"));
            }
        }
        catch (IllegalArgumentException ex2) {
            throw a(ex2);
        }
        return true;
    }
    
    private static IllegalArgumentException a(final IllegalArgumentException ex) {
        return ex;
    }
}
