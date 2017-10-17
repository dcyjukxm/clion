// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.execution.debugger.breakpoints;

import com.intellij.util.PlatformUtils;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import com.intellij.util.xmlb.annotations.Attribute;
import com.intellij.xdebugger.breakpoints.XBreakpointProperties;

public static class Properties extends XBreakpointProperties<Properties>
{
    @Attribute("thrown")
    @Nullable
    private Type myWhenThrown;
    @Attribute("caught")
    @Nullable
    private Type myWhenCaught;
    
    public Properties() {
        this(Type.ANY_EXCEPTION, null);
    }
    
    public Properties(@Nullable final Type myWhenThrown, @Nullable final Type myWhenCaught) {
        this.myWhenThrown = myWhenThrown;
        this.myWhenCaught = myWhenCaught;
    }
    
    @Nullable
    public Type getWhenThrown() {
        return this.myWhenThrown;
    }
    
    public void setWhenThrown(@Nullable final Type myWhenThrown) {
        this.myWhenThrown = myWhenThrown;
    }
    
    @Nullable
    public Type getWhenCaught() {
        return this.myWhenCaught;
    }
    
    public void setWhenCaught(@Nullable final Type myWhenCaught) {
        this.myWhenCaught = myWhenCaught;
    }
    
    @NotNull
    public Properties getState() {
        try {
            if (this == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/execution/debugger/breakpoints/CidrExceptionBreakpointType$Properties", "getState"));
            }
        }
        catch (IllegalStateException ex) {
            throw a(ex);
        }
        return this;
    }
    
    public void loadState(@NotNull final Properties properties) {
        try {
            if (properties == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "state", "com/jetbrains/cidr/execution/debugger/breakpoints/CidrExceptionBreakpointType$Properties", "loadState"));
            }
        }
        catch (IllegalStateException ex) {
            throw a(ex);
        }
        this.myWhenThrown = properties.myWhenThrown;
        this.myWhenCaught = properties.myWhenCaught;
    }
    
    private static IllegalStateException a(final IllegalStateException ex) {
        return ex;
    }
    
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
}
