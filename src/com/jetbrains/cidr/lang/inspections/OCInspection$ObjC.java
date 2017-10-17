// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.lang.inspections;

import org.jetbrains.annotations.NotNull;

public abstract static class ObjC extends OCInspection
{
    @NotNull
    public String[] getGroupPath() {
        String[] array;
        try {
            array = new String[] { "Objective-C", this.getGroupDisplayName() };
            if (array == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/inspections/OCInspection$ObjC", "getGroupPath"));
            }
        }
        catch (IllegalStateException ex) {
            throw b(ex);
        }
        return array;
    }
    
    private static IllegalStateException b(final IllegalStateException ex) {
        return ex;
    }
}
