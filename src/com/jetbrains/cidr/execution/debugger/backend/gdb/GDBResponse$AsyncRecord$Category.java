// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.execution.debugger.backend.gdb;

import org.jetbrains.annotations.NotNull;

public enum Category implements RecordCategory
{
    exec("*"), 
    notify("="), 
    status("+");
    
    private final String myPrefix;
    
    private Category(final String myPrefix) {
        this.myPrefix = myPrefix;
    }
    
    @NotNull
    @Override
    public String getPrefix() {
        String myPrefix;
        try {
            myPrefix = this.myPrefix;
            if (myPrefix == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/execution/debugger/backend/gdb/GDBResponse$AsyncRecord$Category", "getPrefix"));
            }
        }
        catch (IllegalStateException ex) {
            throw a(ex);
        }
        return myPrefix;
    }
    
    @Override
    public String toString() {
        return this.myPrefix;
    }
    
    static Category forPrefix(@NotNull final String s) {
        try {
            if (s == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "prefix", "com/jetbrains/cidr/execution/debugger/backend/gdb/GDBResponse$AsyncRecord$Category", "forPrefix"));
            }
        }
        catch (IllegalStateException ex) {
            throw a(ex);
        }
        return RecordCategory.forPrefix(values(), s);
    }
    
    private static IllegalStateException a(final IllegalStateException ex) {
        return ex;
    }
}
