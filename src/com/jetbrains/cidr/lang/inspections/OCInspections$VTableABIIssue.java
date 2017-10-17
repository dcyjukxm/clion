// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.lang.inspections;

import org.jetbrains.annotations.NotNull;

public static class VTableABIIssue extends ClangCompilerIssues implements Hidden
{
    @NotNull
    @Override
    public String getDisplayName() {
        String s;
        try {
            s = "VTable ABI Issue";
            if (s == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/inspections/OCInspections$VTableABIIssue", "getDisplayName"));
            }
        }
        catch (IllegalStateException ex) {
            throw d(ex);
        }
        return s;
    }
    
    private static IllegalStateException d(final IllegalStateException ex) {
        return ex;
    }
}
