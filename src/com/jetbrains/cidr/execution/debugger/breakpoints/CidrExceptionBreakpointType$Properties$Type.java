// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.execution.debugger.breakpoints;

import com.intellij.util.PlatformUtils;
import org.jetbrains.annotations.NotNull;

public enum Type
{
    ANY_EXCEPTION("Any"), 
    OBJC_EXCEPTION("Objective-C") {
        @Override
        public boolean isAvailable() {
            return PlatformUtils.isAppCode() || PlatformUtils.isRubyMine();
        }
    };
    
    private final String myDisplayString;
    
    private Type(final String myDisplayString) {
        this.myDisplayString = myDisplayString;
    }
    
    @NotNull
    public String getDisplayString() {
        String myDisplayString;
        try {
            myDisplayString = this.myDisplayString;
            if (myDisplayString == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/execution/debugger/breakpoints/CidrExceptionBreakpointType$Properties$Type", "getDisplayString"));
            }
        }
        catch (IllegalStateException ex) {
            throw a(ex);
        }
        return myDisplayString;
    }
    
    public boolean isAvailable() {
        return true;
    }
    
    private static IllegalStateException a(final IllegalStateException ex) {
        return ex;
    }
}
