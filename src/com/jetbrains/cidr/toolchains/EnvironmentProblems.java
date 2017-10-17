// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.toolchains;

import java.util.Collection;
import com.intellij.openapi.util.text.StringUtil;
import com.intellij.execution.ExecutionException;
import java.util.Collections;
import com.intellij.util.SmartList;
import org.jetbrains.annotations.NotNull;
import java.util.List;

public class EnvironmentProblems
{
    @NotNull
    private final List<String> myProblems;
    
    public EnvironmentProblems() {
        this.myProblems = (List<String>)new SmartList();
    }
    
    public void addProblem(@NotNull final String s) {
        try {
            if (s == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "problem", "com/jetbrains/cidr/toolchains/EnvironmentProblems", "addProblem"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw b(ex);
        }
        this.myProblems.add(s);
    }
    
    public boolean hasProblems() {
        try {
            if (!this.myProblems.isEmpty()) {
                return true;
            }
        }
        catch (IllegalArgumentException ex) {
            throw b(ex);
        }
        return false;
    }
    
    @NotNull
    public List<String> getProblems() {
        List<Object> unmodifiableList;
        try {
            unmodifiableList = (List<Object>)Collections.unmodifiableList((List<? extends String>)this.myProblems);
            if (unmodifiableList == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/toolchains/EnvironmentProblems", "getProblems"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw b(ex);
        }
        return (List<String>)unmodifiableList;
    }
    
    public void throwAsExecutionException() throws ExecutionException {
        try {
            if (this.hasProblems()) {
                throw new ExecutionException(this.a());
            }
        }
        catch (ExecutionException ex) {
            throw b((Exception)ex);
        }
    }
    
    @NotNull
    private String a() {
        String join;
        try {
            join = StringUtil.join((Collection)this.myProblems, "<br>");
            if (join == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/toolchains/EnvironmentProblems", "formatProblems"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw b(ex);
        }
        return join;
    }
    
    private static Exception b(final Exception ex) {
        return ex;
    }
}
