// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr;

import com.intellij.openapi.diagnostic.Logger;

public class CidrLog
{
    public static final Logger LOG;
    
    static {
        LOG = Logger.getInstance("#" + CidrLog.class.getPackage().getName());
    }
}
