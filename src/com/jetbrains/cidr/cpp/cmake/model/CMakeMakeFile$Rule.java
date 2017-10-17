// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.cpp.cmake.model;

import org.jetbrains.annotations.NotNull;

public static class Rule
{
    @NotNull
    private final String myTarget;
    @NotNull
    private final String myDependencies;
    @NotNull
    private final String myRecipe;
    
    public Rule(@NotNull final String myTarget, @NotNull final String s, @NotNull final String myRecipe) {
        if (myTarget == null) {
            throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "target", "com/jetbrains/cidr/cpp/cmake/model/CMakeMakeFile$Rule", "<init>"));
        }
        if (s == null) {
            throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "dependencies", "com/jetbrains/cidr/cpp/cmake/model/CMakeMakeFile$Rule", "<init>"));
        }
        if (myRecipe == null) {
            throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "recipe", "com/jetbrains/cidr/cpp/cmake/model/CMakeMakeFile$Rule", "<init>"));
        }
        this.myTarget = myTarget;
        this.myDependencies = s.trim();
        this.myRecipe = myRecipe;
    }
    
    @NotNull
    public String getTarget() {
        String myTarget;
        try {
            myTarget = this.myTarget;
            if (myTarget == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/cpp/cmake/model/CMakeMakeFile$Rule", "getTarget"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        return myTarget;
    }
    
    @NotNull
    public String getDependencies() {
        String myDependencies;
        try {
            myDependencies = this.myDependencies;
            if (myDependencies == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/cpp/cmake/model/CMakeMakeFile$Rule", "getDependencies"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        return myDependencies;
    }
    
    @NotNull
    public String getRecipe() {
        String myRecipe;
        try {
            myRecipe = this.myRecipe;
            if (myRecipe == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/cpp/cmake/model/CMakeMakeFile$Rule", "getRecipe"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        return myRecipe;
    }
    
    @Override
    public String toString() {
        StringBuilder append;
        try {
            append = new StringBuilder().append(this.myTarget).append(": ").append(this.myDependencies);
            if (this.myRecipe.isEmpty()) {
                final String string = "";
                return append.append(string).toString();
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        final String string = "\n" + this.myRecipe;
        return append.append(string).toString();
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
                final Rule rule = this;
                final Class<? extends Rule> clazz = rule.getClass();
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
                final Rule rule = this;
                final Class<? extends Rule> clazz = rule.getClass();
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
        final Rule rule2 = (Rule)o;
        try {
            if (!this.myTarget.equals(rule2.myTarget)) {
                return false;
            }
        }
        catch (IllegalArgumentException ex4) {
            throw a(ex4);
        }
        try {
            if (!this.myDependencies.equals(rule2.myDependencies)) {
                return false;
            }
        }
        catch (IllegalArgumentException ex5) {
            throw a(ex5);
        }
        try {
            if (!this.myRecipe.equals(rule2.myRecipe)) {
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
        return 31 * (31 * this.myTarget.hashCode() + this.myDependencies.hashCode()) + this.myRecipe.hashCode();
    }
    
    private static IllegalArgumentException a(final IllegalArgumentException ex) {
        return ex;
    }
}
