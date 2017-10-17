// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.execution.debugger.evaluation;

import com.intellij.xdebugger.Obsolescent;
import com.intellij.openapi.util.Expirable;

public class XValueNodeExpirable implements Expirable
{
    private final Obsolescent myNode;
    
    public XValueNodeExpirable(final Obsolescent myNode) {
        this.myNode = myNode;
    }
    
    public boolean isExpired() {
        return this.myNode.isObsolete();
    }
}
