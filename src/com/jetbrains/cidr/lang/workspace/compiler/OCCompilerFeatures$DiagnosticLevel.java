// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.lang.workspace.compiler;

public enum DiagnosticLevel
{
    DISABLED, 
    WARNING, 
    ERROR;
    
    public boolean isEnabled() {
        return this != DiagnosticLevel.DISABLED;
    }
}
