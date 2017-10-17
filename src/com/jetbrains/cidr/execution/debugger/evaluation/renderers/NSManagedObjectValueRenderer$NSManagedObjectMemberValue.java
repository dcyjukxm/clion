// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.execution.debugger.evaluation.renderers;

import org.jetbrains.annotations.NotNull;
import com.intellij.xdebugger.XSourcePosition;
import com.jetbrains.cidr.execution.debugger.backend.LLValue;
import com.jetbrains.cidr.execution.debugger.evaluation.CidrMemberValue;

private abstract class NSManagedObjectMemberValue extends CidrMemberValue
{
    private final String myClassName;
    
    public NSManagedObjectMemberValue(final LLValue llValue, final String myClassName) {
        super(llValue, NSManagedObjectValueRenderer.this.myValue, true);
        this.myClassName = myClassName;
    }
    
    public NSManagedObjectMemberValue(final LLValue llValue, final String s, final String myClassName) {
        super(llValue, s, NSManagedObjectValueRenderer.this.myValue, true);
        this.myClassName = myClassName;
    }
    
    @Override
    protected XSourcePosition doComputePosition(@NotNull final XSourcePosition xSourcePosition) {
        try {
            if (xSourcePosition == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "position", "com/jetbrains/cidr/execution/debugger/evaluation/renderers/NSManagedObjectValueRenderer$NSManagedObjectMemberValue", "doComputePosition"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw b(ex);
        }
        try {
            if (NSManagedObjectValueRenderer.this.isManagedObjectClass(this.myClassName)) {
                return null;
            }
        }
        catch (IllegalArgumentException ex2) {
            throw b(ex2);
        }
        return this.getTypesHelper().resolveProperty(this, this.myClassName);
    }
    
    private static IllegalArgumentException b(final IllegalArgumentException ex) {
        return ex;
    }
}
