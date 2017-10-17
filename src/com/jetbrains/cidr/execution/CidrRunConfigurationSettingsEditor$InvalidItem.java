// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.execution;

import org.jetbrains.annotations.NotNull;

protected static class InvalidItem
{
    @NotNull
    Object myValue;
    
    private InvalidItem(@NotNull final Object myValue) {
        if (myValue == null) {
            throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "value", "com/jetbrains/cidr/execution/CidrRunConfigurationSettingsEditor$InvalidItem", "<init>"));
        }
        this.myValue = myValue;
    }
    
    @Override
    public String toString() {
        return this.myValue.toString();
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
                final InvalidItem invalidItem = this;
                final Class<? extends InvalidItem> clazz = invalidItem.getClass();
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
                final InvalidItem invalidItem = this;
                final Class<? extends InvalidItem> clazz = invalidItem.getClass();
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
        final InvalidItem invalidItem2 = (InvalidItem)o;
        try {
            if (!this.myValue.equals(invalidItem2.myValue)) {
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
