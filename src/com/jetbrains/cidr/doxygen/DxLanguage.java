// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.doxygen;

import com.intellij.lang.Language;

public class DxLanguage extends Language
{
    public static final DxLanguage INSTANCE;
    
    private DxLanguage() {
        super("Doxygen");
    }
    
    public boolean isCaseSensitive() {
        return true;
    }
    
    static {
        INSTANCE = new DxLanguage();
    }
}
