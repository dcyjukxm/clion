// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.execution.debugger;

import com.intellij.openapi.diagnostic.Logger;

public class CidrDebuggerLog
{
    public static final Logger LOG;
    
    static {
        LOG = Logger.getInstance("#" + CidrDebuggerLog.class.getPackage().getName());
    }
}
