// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.cpp.actions.newFile;

import java.util.Collection;
import org.jetbrains.annotations.NotNull;
import com.intellij.util.containers.MultiMap;

private static class VarDependencies
{
    final MultiMap<String, CommandInfo> myMap;
    
    public VarDependencies() {
        this.myMap = (MultiMap<String, CommandInfo>)MultiMap.createSet();
    }
    
    public VarDependencies(@NotNull final VarDependencies varDependencies) {
        if (varDependencies == null) {
            throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "original", "com/jetbrains/cidr/cpp/actions/newFile/CMakeNewFileHandler$VarDependencies", "<init>"));
        }
        this();
        this.myMap.putAllValues((MultiMap)varDependencies.myMap);
    }
    
    @NotNull
    public Collection<CommandInfo> get(@NotNull final String s) {
        try {
            if (s == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "varRef", "com/jetbrains/cidr/cpp/actions/newFile/CMakeNewFileHandler$VarDependencies", "get"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        Collection value;
        try {
            value = this.myMap.get((Object)s);
            if (value == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/cpp/actions/newFile/CMakeNewFileHandler$VarDependencies", "get"));
            }
        }
        catch (IllegalArgumentException ex2) {
            throw a(ex2);
        }
        return (Collection<CommandInfo>)value;
    }
    
    public void set(@NotNull final String s, @NotNull final Collection<CommandInfo> collection) {
        try {
            if (s == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "varRef", "com/jetbrains/cidr/cpp/actions/newFile/CMakeNewFileHandler$VarDependencies", "set"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        try {
            if (collection == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "deps", "com/jetbrains/cidr/cpp/actions/newFile/CMakeNewFileHandler$VarDependencies", "set"));
            }
        }
        catch (IllegalArgumentException ex2) {
            throw a(ex2);
        }
        this.myMap.remove((Object)s);
        this.myMap.put((Object)s, (Collection)collection);
    }
    
    public void setAll(@NotNull final VarDependencies varDependencies) {
        try {
            if (varDependencies == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "original", "com/jetbrains/cidr/cpp/actions/newFile/CMakeNewFileHandler$VarDependencies", "setAll"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        this.myMap.clear();
        this.addAll(varDependencies);
    }
    
    public void addAll(@NotNull final VarDependencies varDependencies) {
        try {
            if (varDependencies == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "original", "com/jetbrains/cidr/cpp/actions/newFile/CMakeNewFileHandler$VarDependencies", "addAll"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        this.myMap.putAllValues((MultiMap)varDependencies.myMap);
    }
    
    private static IllegalArgumentException a(final IllegalArgumentException ex) {
        return ex;
    }
}
