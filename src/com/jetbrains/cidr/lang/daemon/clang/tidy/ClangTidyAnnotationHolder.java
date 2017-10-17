// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.lang.daemon.clang.tidy;

import org.jetbrains.annotations.NotNull;
import java.util.List;
import java.io.File;

public class ClangTidyAnnotationHolder
{
    private final State myState;
    private final String myFailReason;
    private final File mySourceFile;
    private final List<ClangTidyDiagnostic> myDiagnostics;
    
    private ClangTidyAnnotationHolder(@NotNull final State myState, @NotNull final String myFailReason) {
        if (myState == null) {
            throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "state", "com/jetbrains/cidr/lang/daemon/clang/tidy/ClangTidyAnnotationHolder", "<init>"));
        }
        if (myFailReason == null) {
            throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "failReason", "com/jetbrains/cidr/lang/daemon/clang/tidy/ClangTidyAnnotationHolder", "<init>"));
        }
        this.myState = myState;
        this.myFailReason = myFailReason;
        this.mySourceFile = null;
        this.myDiagnostics = null;
    }
    
    private ClangTidyAnnotationHolder(@NotNull final File mySourceFile, @NotNull final List<ClangTidyDiagnostic> myDiagnostics) {
        if (mySourceFile == null) {
            throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "sourceFile", "com/jetbrains/cidr/lang/daemon/clang/tidy/ClangTidyAnnotationHolder", "<init>"));
        }
        if (myDiagnostics == null) {
            throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "diagnostics", "com/jetbrains/cidr/lang/daemon/clang/tidy/ClangTidyAnnotationHolder", "<init>"));
        }
        this.myState = State.SUCCESS;
        this.myFailReason = null;
        this.mySourceFile = mySourceFile;
        this.myDiagnostics = myDiagnostics;
    }
    
    public File getSourceFile() {
        return this.mySourceFile;
    }
    
    public String getFailReason() {
        return this.myFailReason;
    }
    
    public List<ClangTidyDiagnostic> getDiagnostics() {
        return this.myDiagnostics;
    }
    
    public static ClangTidyAnnotationHolder error(@NotNull final String s) {
        try {
            if (s == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "reason", "com/jetbrains/cidr/lang/daemon/clang/tidy/ClangTidyAnnotationHolder", "error"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        return new ClangTidyAnnotationHolder(State.ERROR, s);
    }
    
    public static ClangTidyAnnotationHolder warning(@NotNull final String s) {
        try {
            if (s == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "reason", "com/jetbrains/cidr/lang/daemon/clang/tidy/ClangTidyAnnotationHolder", "warning"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        return new ClangTidyAnnotationHolder(State.WARNING, s);
    }
    
    public static ClangTidyAnnotationHolder success(@NotNull final File file, @NotNull final List<ClangTidyDiagnostic> list) {
        try {
            if (file == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "sourceFile", "com/jetbrains/cidr/lang/daemon/clang/tidy/ClangTidyAnnotationHolder", "success"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        try {
            if (list == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "diagnostics", "com/jetbrains/cidr/lang/daemon/clang/tidy/ClangTidyAnnotationHolder", "success"));
            }
        }
        catch (IllegalArgumentException ex2) {
            throw a(ex2);
        }
        return new ClangTidyAnnotationHolder(file, list);
    }
    
    public boolean isError() {
        try {
            if (this.myState == State.ERROR) {
                return true;
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        return false;
    }
    
    public boolean isWarning() {
        try {
            if (this.myState == State.WARNING) {
                return true;
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        return false;
    }
    
    public boolean isSuccess() {
        try {
            if (this.myState == State.SUCCESS) {
                return true;
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        return false;
    }
    
    public boolean isFail() {
        try {
            if (this.myState != State.SUCCESS) {
                return true;
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        return false;
    }
    
    private static IllegalArgumentException a(final IllegalArgumentException ex) {
        return ex;
    }
    
    private enum State
    {
        SUCCESS, 
        WARNING, 
        ERROR;
    }
}
