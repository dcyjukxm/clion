// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.execution.testing;

import com.intellij.openapi.diagnostic.Logger;

public class CidrTestLog
{
    public static final Logger LOG;
    
    static {
        LOG = Logger.getInstance("#" + CidrTestLog.class.getPackage().getName());
    }
}
