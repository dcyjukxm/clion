// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.cpp.cmake.model;

import java.util.Iterator;
import org.jetbrains.annotations.Nullable;
import java.util.Collections;
import org.jetbrains.annotations.NotNull;
import java.util.Map;
import java.io.Serializable;

public class CMakeData<T> implements Serializable
{
    @NotNull
    private final Map<String, T> myForConfiguration;
    
    public CMakeData(@NotNull final Map<String, T> map) {
        if (map == null) {
            throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "forConfigurations", "com/jetbrains/cidr/cpp/cmake/model/CMakeData", "<init>"));
        }
        this.myForConfiguration = Collections.unmodifiableMap((Map<? extends String, ? extends T>)map);
    }
    
    @Nullable
    public T getFor(@NotNull final String s) {
        try {
            if (s == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "configName", "com/jetbrains/cidr/cpp/cmake/model/CMakeData", "getFor"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        return this.myForConfiguration.get(s);
    }
    
    @NotNull
    public Map<String, T> getAll() {
        Map<String, T> myForConfiguration;
        try {
            myForConfiguration = this.myForConfiguration;
            if (myForConfiguration == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/cpp/cmake/model/CMakeData", "getAll"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        return myForConfiguration;
    }
    
    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder(this.getClass().getSimpleName() + ":");
        for (final Map.Entry<String, T> entry : this.myForConfiguration.entrySet()) {
            sb.append("\n").append(entry.getKey()).append("=").append(entry.getValue());
        }
        return sb.toString();
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
                final CMakeData cMakeData = this;
                final Class<? extends CMakeData> clazz = cMakeData.getClass();
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
                final CMakeData cMakeData = this;
                final Class<? extends CMakeData> clazz = cMakeData.getClass();
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
        final CMakeData cMakeData2 = (CMakeData)o;
        try {
            if (!this.myForConfiguration.equals(cMakeData2.myForConfiguration)) {
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
        return this.myForConfiguration.hashCode();
    }
    
    private static IllegalArgumentException a(final IllegalArgumentException ex) {
        return ex;
    }
}
