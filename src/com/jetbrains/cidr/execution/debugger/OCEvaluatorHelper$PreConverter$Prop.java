// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.execution.debugger;

import org.jetbrains.annotations.Nullable;
import org.jetbrains.annotations.NotNull;

private static class Prop
{
    @NotNull
    public final String receiver;
    @Nullable
    public final String getter;
    @Nullable
    public final String setter;
    
    private Prop(@NotNull final String receiver, @Nullable final String getter, @Nullable final String setter) {
        if (receiver == null) {
            throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "receiver", "com/jetbrains/cidr/execution/debugger/OCEvaluatorHelper$PreConverter$Prop", "<init>"));
        }
        this.receiver = receiver;
        this.getter = getter;
        this.setter = setter;
    }
    
    public boolean isReadWrite() {
        Label_0021: {
            try {
                if (this.getter == null) {
                    return false;
                }
                final Prop prop = this;
                final String s = prop.setter;
                if (s != null) {
                    break Label_0021;
                }
                return false;
            }
            catch (IllegalArgumentException ex) {
                throw a(ex);
            }
            try {
                final Prop prop = this;
                final String s = prop.setter;
                if (s != null) {
                    return true;
                }
            }
            catch (IllegalArgumentException ex2) {
                throw a(ex2);
            }
        }
        return false;
    }
    
    private static IllegalArgumentException a(final IllegalArgumentException ex) {
        return ex;
    }
}
