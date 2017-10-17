// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.execution.debugger.backend.gdb;

import org.jetbrains.annotations.NotNull;

public static class AsyncRecord extends Record<Category, Type>
{
    public AsyncRecord(@NotNull final Category category, @NotNull final String s, @NotNull final GDBTuple gdbTuple) {
        if (category == null) {
            throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "category", "com/jetbrains/cidr/execution/debugger/backend/gdb/GDBResponse$AsyncRecord", "<init>"));
        }
        if (s == null) {
            throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "type", "com/jetbrains/cidr/execution/debugger/backend/gdb/GDBResponse$AsyncRecord", "<init>"));
        }
        if (gdbTuple == null) {
            throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "resultList", "com/jetbrains/cidr/execution/debugger/backend/gdb/GDBResponse$AsyncRecord", "<init>"));
        }
        super(category, new Type(s), gdbTuple);
    }
    
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
    
    public static class Type implements RecordType
    {
        @NotNull
        private final String myValue;
        
        public Type(@NotNull final String myValue) {
            if (myValue == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "value", "com/jetbrains/cidr/execution/debugger/backend/gdb/GDBResponse$AsyncRecord$Type", "<init>"));
            }
            this.myValue = myValue;
        }
        
        @NotNull
        public String getValue() {
            String myValue;
            try {
                myValue = this.myValue;
                if (myValue == null) {
                    throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/execution/debugger/backend/gdb/GDBResponse$AsyncRecord$Type", "getValue"));
                }
            }
            catch (IllegalArgumentException ex) {
                throw a(ex);
            }
            return myValue;
        }
        
        @Override
        public String toString() {
            return this.myValue;
        }
        
        @Override
        public boolean equals(final Object o) {
            try {
                if (this == o) {
                    return true;
                }
            }
            catch (IllegalArgumentException ex) {
                throw a(ex);
            }
            Label_0039: {
                try {
                    if (o == null) {
                        return false;
                    }
                    final Type type = this;
                    final Class<? extends Type> clazz = type.getClass();
                    final Object o2 = o;
                    final Class<?> clazz2 = o2.getClass();
                    if (clazz != clazz2) {
                        return false;
                    }
                    break Label_0039;
                }
                catch (IllegalArgumentException ex2) {
                    throw a(ex2);
                }
                try {
                    final Type type = this;
                    final Class<? extends Type> clazz = type.getClass();
                    final Object o2 = o;
                    final Class<?> clazz2 = o2.getClass();
                    if (clazz != clazz2) {
                        return false;
                    }
                }
                catch (IllegalArgumentException ex3) {
                    throw a(ex3);
                }
            }
            final Type type2 = (Type)o;
            try {
                if (!this.myValue.equals(type2.myValue)) {
                    return false;
                }
            }
            catch (IllegalArgumentException ex4) {
                throw a(ex4);
            }
            return true;
        }
        
        @Override
        public int hashCode() {
            return this.myValue.hashCode();
        }
        
        private static IllegalArgumentException a(final IllegalArgumentException ex) {
            return ex;
        }
    }
}
