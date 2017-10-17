// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.lang;

import com.jetbrains.cidr.lang.settings.OCCustomOptionCpp;
import com.jetbrains.cidr.lang.settings.OCCustomOptionObjC;
import com.jetbrains.cidr.lang.settings.OCCustomOption;

private static class LazyOptionHolder
{
    public static final OCCustomOption INSTANCE;
    
    static {
        INSTANCE = (OCLanguage.access$000() ? new OCCustomOptionObjC() : new OCCustomOptionCpp());
    }
}
