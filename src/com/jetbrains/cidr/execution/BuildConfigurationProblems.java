// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.execution;

import java.util.Iterator;
import org.jetbrains.annotations.Nullable;
import java.util.Collection;
import com.intellij.openapi.util.text.StringUtil;
import com.intellij.util.SmartList;
import java.util.List;
import org.jetbrains.annotations.NotNull;

public class BuildConfigurationProblems
{
    @NotNull
    public String title;
    @NotNull
    public final List<String> problems;
    @NotNull
    public final List<Fix> fixes;
    
    public BuildConfigurationProblems() {
        this.problems = (List<String>)new SmartList();
        this.fixes = (List<Fix>)new SmartList();
    }
    
    @NotNull
    public String getTitle() {
        String title;
        try {
            title = this.title;
            if (title == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/execution/BuildConfigurationProblems", "getTitle"));
            }
        }
        catch (IllegalStateException ex) {
            throw a(ex);
        }
        return title;
    }
    
    @NotNull
    public List<String> getProblems() {
        List<String> problems;
        try {
            problems = this.problems;
            if (problems == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/execution/BuildConfigurationProblems", "getProblems"));
            }
        }
        catch (IllegalStateException ex) {
            throw a(ex);
        }
        return problems;
    }
    
    public boolean hasProblems() {
        try {
            if (!this.problems.isEmpty()) {
                return true;
            }
        }
        catch (IllegalStateException ex) {
            throw a(ex);
        }
        return false;
    }
    
    @NotNull
    public String getHtmlProblems() {
        String join;
        try {
            join = StringUtil.join((Collection)this.problems, "<br>");
            if (join == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/execution/BuildConfigurationProblems", "getHtmlProblems"));
            }
        }
        catch (IllegalStateException ex) {
            throw a(ex);
        }
        return join;
    }
    
    @NotNull
    public String getTextProblems() {
        String join;
        try {
            join = StringUtil.join((Collection)this.problems, "\n");
            if (join == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/execution/BuildConfigurationProblems", "getTextProblems"));
            }
        }
        catch (IllegalStateException ex) {
            throw a(ex);
        }
        return join;
    }
    
    @NotNull
    public String getText() {
        String s = this.getTitle();
        if (this.hasProblems()) {
            s = s + "\n" + this.getTextProblems();
        }
        String s2;
        try {
            s2 = s;
            if (s2 == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/execution/BuildConfigurationProblems", "getText"));
            }
        }
        catch (IllegalStateException ex) {
            throw a(ex);
        }
        return s2;
    }
    
    @NotNull
    public List<Fix> getFixes() {
        List<Fix> fixes;
        try {
            fixes = this.fixes;
            if (fixes == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/execution/BuildConfigurationProblems", "getFixes"));
            }
        }
        catch (IllegalStateException ex) {
            throw a(ex);
        }
        return fixes;
    }
    
    @Nullable
    public Fix findFix(@Nullable final String s) {
        for (final Fix fix : this.fixes) {
            try {
                if (fix.name.equals(s)) {
                    return fix;
                }
                continue;
            }
            catch (IllegalStateException ex) {
                throw a(ex);
            }
        }
        return null;
    }
    
    private static IllegalStateException a(final IllegalStateException ex) {
        return ex;
    }
    
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
}
