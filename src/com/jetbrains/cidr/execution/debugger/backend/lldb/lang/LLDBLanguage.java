// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.execution.debugger.backend.lldb.lang;

import com.intellij.lang.Language;

public class LLDBLanguage extends Language
{
    public static final LLDBLanguage INSTANCE;
    
    public LLDBLanguage() {
        super("LLDB");
    }
    
    static {
        INSTANCE = new LLDBLanguage();
    }
}
