// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.lang;

import com.intellij.openapi.diagnostic.Logger;

public class OCLog
{
    public static final Logger LOG;
    
    static {
        LOG = Logger.getInstance("#" + OCLog.class.getPackage().getName());
    }
}
