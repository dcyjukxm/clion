// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.lang.asm;

import com.intellij.lang.Language;

public class AsmLanguage extends Language
{
    public static final AsmLanguage INSTANCE;
    
    private AsmLanguage() {
        super("ASM");
    }
    
    static {
        INSTANCE = new AsmLanguage();
    }
}
