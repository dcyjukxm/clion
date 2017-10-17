// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.execution.debugger.evaluation.renderers;

import com.intellij.xdebugger.frame.XValueModifier;
import com.jetbrains.cidr.execution.debugger.backend.LLValue;

private class KeyedValue extends NSManagedObjectMemberValue
{
    public KeyedValue(final LLValue llValue, final String s, final String s2) {
        super(llValue, s, s2);
    }
    
    @Override
    public XValueModifier getModifier() {
        return new KeyedValueModifier(this);
    }
}
