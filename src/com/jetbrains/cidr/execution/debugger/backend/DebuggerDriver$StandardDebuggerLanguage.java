// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.execution.debugger.backend;

import org.jetbrains.annotations.NotNull;

public enum StandardDebuggerLanguage implements DebuggerLanguage
{
    C("C"), 
    C_PLUS_PLUS("C++"), 
    OBJC("Objective-C"), 
    OBJC_PLUS_PLUS("Objective-C++"), 
    SWIFT("Swift");
    
    private final String myName;
    
    private StandardDebuggerLanguage(final String myName) {
        if (myName == null) {
            throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "name", "com/jetbrains/cidr/execution/debugger/backend/DebuggerDriver$StandardDebuggerLanguage", "<init>"));
        }
        super(s, n);
        this.myName = myName;
    }
    
    @NotNull
    @Override
    public String toString() {
        String myName;
        try {
            myName = this.myName;
            if (myName == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/execution/debugger/backend/DebuggerDriver$StandardDebuggerLanguage", "toString"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        return myName;
    }
    
    private static IllegalArgumentException a(final IllegalArgumentException ex) {
        return ex;
    }
}
