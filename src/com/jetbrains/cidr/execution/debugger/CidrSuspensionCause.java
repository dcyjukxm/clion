// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.execution.debugger;

import com.intellij.icons.AllIcons;
import javax.swing.Icon;
import org.jetbrains.annotations.NotNull;

public class CidrSuspensionCause
{
    @NotNull
    public final String type;
    @NotNull
    public final String reason;
    @NotNull
    public final Icon icon;
    
    public CidrSuspensionCause(@NotNull final String type, @NotNull final String reason) {
        if (type == null) {
            throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "type", "com/jetbrains/cidr/execution/debugger/CidrSuspensionCause", "<init>"));
        }
        if (reason == null) {
            throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "reason", "com/jetbrains/cidr/execution/debugger/CidrSuspensionCause", "<init>"));
        }
        this.icon = AllIcons.Debugger.Db_exception_breakpoint;
        this.type = type;
        this.reason = reason;
    }
    
    @NotNull
    public String getDisplayString() {
        String string;
        try {
            string = this.type + ": " + this.reason;
            if (string == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/execution/debugger/CidrSuspensionCause", "getDisplayString"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        return string;
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
                final CidrSuspensionCause cidrSuspensionCause = this;
                final Class<? extends CidrSuspensionCause> clazz = cidrSuspensionCause.getClass();
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
                final CidrSuspensionCause cidrSuspensionCause = this;
                final Class<? extends CidrSuspensionCause> clazz = cidrSuspensionCause.getClass();
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
        final CidrSuspensionCause cidrSuspensionCause2 = (CidrSuspensionCause)o;
        try {
            if (!this.reason.equals(cidrSuspensionCause2.reason)) {
                return false;
            }
        }
        catch (IllegalArgumentException ex4) {
            throw a(ex4);
        }
        try {
            if (!this.type.equals(cidrSuspensionCause2.type)) {
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
        return 31 * this.type.hashCode() + this.reason.hashCode();
    }
    
    private static IllegalArgumentException a(final IllegalArgumentException ex) {
        return ex;
    }
}
