// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.lang.workspace.headerRoots;

import java.util.Collections;
import org.jetbrains.annotations.NotNull;
import java.util.List;

public class HeaderRoots
{
    public static final HeaderRoots EMPTY;
    @NotNull
    private final List<HeadersSearchRoot> myRoots;
    
    public HeaderRoots(@NotNull final List<HeadersSearchRoot> myRoots) {
        if (myRoots == null) {
            throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "roots", "com/jetbrains/cidr/lang/workspace/headerRoots/HeaderRoots", "<init>"));
        }
        this.myRoots = myRoots;
    }
    
    @NotNull
    public List<HeadersSearchRoot> getRoots() {
        List<HeadersSearchRoot> myRoots;
        try {
            myRoots = this.myRoots;
            if (myRoots == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/workspace/headerRoots/HeaderRoots", "getRoots"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        return myRoots;
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
                final HeaderRoots headerRoots = this;
                final Class<? extends HeaderRoots> clazz = headerRoots.getClass();
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
                final HeaderRoots headerRoots = this;
                final Class<? extends HeaderRoots> clazz = headerRoots.getClass();
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
        final HeaderRoots headerRoots2 = (HeaderRoots)o;
        try {
            if (!this.myRoots.equals(headerRoots2.myRoots)) {
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
        return this.myRoots.hashCode();
    }
    
    static {
        EMPTY = new HeaderRoots(Collections.emptyList());
    }
    
    private static IllegalArgumentException a(final IllegalArgumentException ex) {
        return ex;
    }
}
