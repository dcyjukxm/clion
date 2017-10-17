// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.lang.inspections;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nls;

abstract static class GeneralCpp extends Cpp
{
    @Nls
    @NotNull
    @Override
    public String getGroupDisplayName() {
        String s;
        try {
            s = "General";
            if (s == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/inspections/OCInspections$GeneralCpp", "getGroupDisplayName"));
            }
        }
        catch (IllegalStateException ex) {
            throw c(ex);
        }
        return s;
    }
    
    private static IllegalStateException c(final IllegalStateException ex) {
        return ex;
    }
}
