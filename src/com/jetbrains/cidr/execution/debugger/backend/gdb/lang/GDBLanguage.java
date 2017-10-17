// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.execution.debugger.backend.gdb.lang;

import com.intellij.lang.Language;

public class GDBLanguage extends Language
{
    public static final GDBLanguage INSTANCE;
    
    public GDBLanguage() {
        super("GDB");
    }
    
    static {
        INSTANCE = new GDBLanguage();
    }
}
