// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.execution.debugger.backend.gdb;

import org.jetbrains.annotations.NotNull;

public static class ResultRecord extends Record<Category, Type>
{
    public ResultRecord(@NotNull final Category category, @NotNull final Type type, @NotNull final GDBTuple gdbTuple) {
        if (category == null) {
            throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "kind", "com/jetbrains/cidr/execution/debugger/backend/gdb/GDBResponse$ResultRecord", "<init>"));
        }
        if (type == null) {
            throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "type", "com/jetbrains/cidr/execution/debugger/backend/gdb/GDBResponse$ResultRecord", "<init>"));
        }
        if (gdbTuple == null) {
            throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "resultList", "com/jetbrains/cidr/execution/debugger/backend/gdb/GDBResponse$ResultRecord", "<init>"));
        }
        super(category, type, gdbTuple);
    }
    
    public enum Category implements RecordCategory
    {
        result("^");
        
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
                    throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/execution/debugger/backend/gdb/GDBResponse$ResultRecord$Category", "getPrefix"));
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
        
        private static IllegalStateException a(final IllegalStateException ex) {
            return ex;
        }
    }
    
    public enum Type implements RecordType
    {
        done, 
        running, 
        connected, 
        error, 
        exit, 
        stepping, 
        continuing, 
        result, 
        tuple_value, 
        list_value, 
        str_value;
    }
}
