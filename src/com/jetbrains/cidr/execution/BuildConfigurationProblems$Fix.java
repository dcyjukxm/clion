// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.execution;

import org.jetbrains.annotations.NotNull;

public abstract static class Fix
{
    @NotNull
    private final String name;
    private final boolean special;
    
    protected Fix(@NotNull final String name, final boolean special) {
        if (name == null) {
            throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "name", "com/jetbrains/cidr/execution/BuildConfigurationProblems$Fix", "<init>"));
        }
        this.name = name;
        this.special = special;
    }
    
    public abstract void apply();
    
    @NotNull
    public String getName() {
        String name;
        try {
            name = this.name;
            if (name == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/execution/BuildConfigurationProblems$Fix", "getName"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        return name;
    }
    
    private static IllegalArgumentException a(final IllegalArgumentException ex) {
        return ex;
    }
}
