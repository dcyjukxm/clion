// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.cpp.toolchains;

import org.jetbrains.annotations.NotNull;

private static class CacheKey
{
    @NotNull
    private final String path;
    @NotNull
    private final String cygwinHome;
    private final int hashCode;
    
    public CacheKey(@NotNull final String path, @NotNull final String cygwinHome) {
        if (path == null) {
            throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "path", "com/jetbrains/cidr/cpp/toolchains/Cygwin$CacheKey", "<init>"));
        }
        if (cygwinHome == null) {
            throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "cygwinHome", "com/jetbrains/cidr/cpp/toolchains/Cygwin$CacheKey", "<init>"));
        }
        this.path = path;
        this.cygwinHome = cygwinHome;
        this.hashCode = this.calculateHashCode();
    }
    
    public int calculateHashCode() {
        return 31 * this.path.hashCode() + this.cygwinHome.hashCode();
    }
    
    @Override
    public String toString() {
        return this.path + '@' + this.cygwinHome;
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
                final CacheKey cacheKey = this;
                final Class<? extends CacheKey> clazz = cacheKey.getClass();
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
                final CacheKey cacheKey = this;
                final Class<? extends CacheKey> clazz = cacheKey.getClass();
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
        final CacheKey cacheKey2 = (CacheKey)o;
        try {
            if (this.hashCode != cacheKey2.hashCode) {
                return false;
            }
        }
        catch (IllegalArgumentException ex4) {
            throw a(ex4);
        }
        try {
            if (!this.path.equals(cacheKey2.path)) {
                return false;
            }
        }
        catch (IllegalArgumentException ex5) {
            throw a(ex5);
        }
        try {
            if (!this.cygwinHome.equals(cacheKey2.cygwinHome)) {
                return false;
            }
        }
        catch (IllegalArgumentException ex6) {
            throw a(ex6);
        }
        return true;
    }
    
    @Override
    public int hashCode() {
        return this.hashCode;
    }
    
    private static IllegalArgumentException a(final IllegalArgumentException ex) {
        return ex;
    }
}
