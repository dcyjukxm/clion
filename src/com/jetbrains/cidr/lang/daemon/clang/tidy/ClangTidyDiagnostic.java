// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.lang.daemon.clang.tidy;

import com.intellij.openapi.util.text.StringUtil;
import java.util.List;
import org.jetbrains.annotations.NotNull;

public class ClangTidyDiagnostic
{
    @NotNull
    private final String myMessage;
    @NotNull
    private final String myDiagnosticName;
    @NotNull
    private final String myFilePath;
    private final int myFileOffset;
    @NotNull
    private final List<ClangTidyReplacement> myReplacements;
    
    public ClangTidyDiagnostic(@NotNull final String s, @NotNull final String myDiagnosticName, @NotNull final String myFilePath, final int myFileOffset, @NotNull final List<ClangTidyReplacement> myReplacements) {
        if (s == null) {
            throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "message", "com/jetbrains/cidr/lang/daemon/clang/tidy/ClangTidyDiagnostic", "<init>"));
        }
        if (myDiagnosticName == null) {
            throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "diagnosticName", "com/jetbrains/cidr/lang/daemon/clang/tidy/ClangTidyDiagnostic", "<init>"));
        }
        if (myFilePath == null) {
            throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "filePath", "com/jetbrains/cidr/lang/daemon/clang/tidy/ClangTidyDiagnostic", "<init>"));
        }
        if (myReplacements == null) {
            throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "replacements", "com/jetbrains/cidr/lang/daemon/clang/tidy/ClangTidyDiagnostic", "<init>"));
        }
        this.myMessage = StringUtil.capitalize(s);
        this.myDiagnosticName = myDiagnosticName;
        this.myFilePath = myFilePath;
        this.myFileOffset = myFileOffset;
        this.myReplacements = myReplacements;
    }
    
    @NotNull
    public String getMessage() {
        String myMessage;
        try {
            myMessage = this.myMessage;
            if (myMessage == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/daemon/clang/tidy/ClangTidyDiagnostic", "getMessage"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        return myMessage;
    }
    
    @NotNull
    public String getDiagnosticName() {
        String myDiagnosticName;
        try {
            myDiagnosticName = this.myDiagnosticName;
            if (myDiagnosticName == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/daemon/clang/tidy/ClangTidyDiagnostic", "getDiagnosticName"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        return myDiagnosticName;
    }
    
    @NotNull
    public String getFilePath() {
        String myFilePath;
        try {
            myFilePath = this.myFilePath;
            if (myFilePath == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/daemon/clang/tidy/ClangTidyDiagnostic", "getFilePath"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        return myFilePath;
    }
    
    public int getFileOffset() {
        return this.myFileOffset;
    }
    
    @NotNull
    public List<ClangTidyReplacement> getReplacements() {
        List<ClangTidyReplacement> myReplacements;
        try {
            myReplacements = this.myReplacements;
            if (myReplacements == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/daemon/clang/tidy/ClangTidyDiagnostic", "getReplacements"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        return myReplacements;
    }
    
    private static IllegalArgumentException a(final IllegalArgumentException ex) {
        return ex;
    }
}
