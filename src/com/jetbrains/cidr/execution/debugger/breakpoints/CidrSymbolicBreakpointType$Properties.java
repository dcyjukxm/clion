// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.execution.debugger.breakpoints;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import com.intellij.xdebugger.breakpoints.XBreakpointProperties;

public static class Properties extends XBreakpointProperties<Properties>
{
    @Nullable
    private String mySymbolPattern;
    @Nullable
    private String myModuleName;
    
    public Properties() {
    }
    
    public Properties(@Nullable final String mySymbolPattern, @Nullable final String myModuleName) {
        this.mySymbolPattern = mySymbolPattern;
        this.myModuleName = myModuleName;
    }
    
    @Nullable
    public String getSymbolPattern() {
        return this.mySymbolPattern;
    }
    
    public void setSymbolPattern(@Nullable final String mySymbolPattern) {
        this.mySymbolPattern = mySymbolPattern;
    }
    
    @Nullable
    public String getModuleName() {
        return this.myModuleName;
    }
    
    public void setModuleName(@Nullable final String myModuleName) {
        this.myModuleName = myModuleName;
    }
    
    @NotNull
    public Properties getState() {
        try {
            if (this == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/execution/debugger/breakpoints/CidrSymbolicBreakpointType$Properties", "getState"));
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
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "state", "com/jetbrains/cidr/execution/debugger/breakpoints/CidrSymbolicBreakpointType$Properties", "loadState"));
            }
        }
        catch (IllegalStateException ex) {
            throw a(ex);
        }
        this.mySymbolPattern = properties.mySymbolPattern;
        this.myModuleName = properties.myModuleName;
    }
    
    private static IllegalStateException a(final IllegalStateException ex) {
        return ex;
    }
}
