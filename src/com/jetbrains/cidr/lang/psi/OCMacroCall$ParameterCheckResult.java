// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.lang.psi;

public static class ParameterCheckResult
{
    private final int myActual;
    private final int myAllowed;
    
    public ParameterCheckResult(final int myActual, final int myAllowed) {
        this.myActual = myActual;
        this.myAllowed = myAllowed;
    }
    
    public int getActualCount() {
        return this.myActual;
    }
    
    public int getAllowedCount() {
        return this.myAllowed;
    }
}
