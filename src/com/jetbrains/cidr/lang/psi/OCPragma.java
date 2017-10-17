// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.lang.psi;

import org.jetbrains.annotations.Nullable;
import com.intellij.openapi.util.Pair;

public interface OCPragma extends OCDirective
{
    @Nullable
    Pair<Mode, String> parsePragma();
    
    public enum Mode
    {
        IGNORE, 
        WARNING, 
        ERROR, 
        FATAL, 
        PUSH, 
        POP;
    }
}
