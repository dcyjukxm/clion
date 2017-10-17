// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.lang.inspections;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nls;

public static class EqualityInConditionalOperatorWithSelf extends GeneralObjC
{
    @Nls
    @NotNull
    @Override
    public String getDisplayName() {
        String s;
        try {
            s = "'=' in conditional expression with \"self\"";
            if (s == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/inspections/OCInspections$EqualityInConditionalOperatorWithSelf", "getDisplayName"));
            }
        }
        catch (IllegalStateException ex) {
            throw d(ex);
        }
        return s;
    }
    
    @Override
    public boolean isEnabledByDefault() {
        return false;
    }
    
    private static IllegalStateException d(final IllegalStateException ex) {
        return ex;
    }
}
