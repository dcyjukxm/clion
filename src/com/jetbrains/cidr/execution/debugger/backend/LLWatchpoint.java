// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.execution.debugger.backend;

import org.jetbrains.annotations.NotNull;

public class LLWatchpoint extends LLCodepoint
{
    @NotNull
    private final String myExpression;
    
    public LLWatchpoint(final int n, @NotNull final String myExpression) {
        if (myExpression == null) {
            throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "expression", "com/jetbrains/cidr/execution/debugger/backend/LLWatchpoint", "<init>"));
        }
        super(n);
        this.myExpression = myExpression;
    }
    
    @NotNull
    public String getExpression() {
        String myExpression;
        try {
            myExpression = this.myExpression;
            if (myExpression == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/execution/debugger/backend/LLWatchpoint", "getExpression"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        return myExpression;
    }
    
    @Override
    public String toString() {
        return "Watchpoint-" + this.getId() + "@" + this.myExpression;
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
                final LLWatchpoint llWatchpoint = this;
                final Class<? extends LLWatchpoint> clazz = llWatchpoint.getClass();
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
                final LLWatchpoint llWatchpoint = this;
                final Class<? extends LLWatchpoint> clazz = llWatchpoint.getClass();
                final Object o2 = o;
                final Class<?> clazz2 = o2.getClass();
                if (clazz != clazz2) {
                    return false;
                }
            }
            catch (IllegalArgumentException ex3) {
                throw a(ex3);
            }
            try {
                if (!super.equals(o)) {
                    return false;
                }
            }
            catch (IllegalArgumentException ex4) {
                throw a(ex4);
            }
        }
        final LLWatchpoint llWatchpoint2 = (LLWatchpoint)o;
        try {
            if (!this.myExpression.equals(llWatchpoint2.myExpression)) {
                return false;
            }
        }
        catch (IllegalArgumentException ex5) {
            throw a(ex5);
        }
        return true;
    }
    
    @Override
    public int hashCode() {
        return 31 * super.hashCode() + this.myExpression.hashCode();
    }
    
    private static IllegalArgumentException a(final IllegalArgumentException ex) {
        return ex;
    }
    
    public enum AccessType
    {
        READ("-r", "Read", "hw-rwpt"), 
        WRITE("", "Write", "wpt"), 
        ANY("-a", "Any", "hw-awpt");
        
        private String myParam;
        private String myDisplayText;
        private String myTupleKey;
        
        private AccessType(final String myParam, final String myDisplayText, final String myTupleKey) {
            this.myParam = myParam;
            this.myDisplayText = myDisplayText;
            this.myTupleKey = myTupleKey;
        }
        
        @NotNull
        public String getParamString() {
            String myParam;
            try {
                myParam = this.myParam;
                if (myParam == null) {
                    throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/execution/debugger/backend/LLWatchpoint$AccessType", "getParamString"));
                }
            }
            catch (IllegalStateException ex) {
                throw a(ex);
            }
            return myParam;
        }
        
        @Override
        public String toString() {
            return this.myDisplayText;
        }
        
        public String getTupleKey() {
            return this.myTupleKey;
        }
        
        private static IllegalStateException a(final IllegalStateException ex) {
            return ex;
        }
    }
    
    public enum Lifetime
    {
        STACK_FRAME("Stack Frame"), 
        PERSISTENT("Persistent");
        
        private String myDisplayText;
        
        private Lifetime(final String myDisplayText) {
            this.myDisplayText = myDisplayText;
        }
        
        @Override
        public String toString() {
            return this.myDisplayText;
        }
    }
}
