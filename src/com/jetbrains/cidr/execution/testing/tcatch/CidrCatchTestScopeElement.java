// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.execution.testing.tcatch;

import org.jetbrains.annotations.Nullable;
import com.jetbrains.cidr.execution.testing.CidrTestScopeElement;

public class CidrCatchTestScopeElement extends CidrTestScopeElement
{
    public CidrCatchTestScopeElement(@Nullable final String s, @Nullable final String s2) {
        super(s, s2);
    }
    
    @Override
    public String toString() {
        return this.getTestName();
    }
}
