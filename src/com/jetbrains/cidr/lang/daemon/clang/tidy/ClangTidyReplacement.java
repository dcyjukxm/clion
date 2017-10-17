// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.lang.daemon.clang.tidy;

import org.jetbrains.annotations.NotNull;

public class ClangTidyReplacement
{
    @NotNull
    private final String myFilePath;
    @NotNull
    private final String myReplacementText;
    private final int myBeginOffset;
    private final int myEndOffset;
    
    public ClangTidyReplacement(@NotNull final String myFilePath, @NotNull final String myReplacementText, final int myBeginOffset, final int myEndOffset) {
        if (myFilePath == null) {
            throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "filePath", "com/jetbrains/cidr/lang/daemon/clang/tidy/ClangTidyReplacement", "<init>"));
        }
        if (myReplacementText == null) {
            throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "replacementText", "com/jetbrains/cidr/lang/daemon/clang/tidy/ClangTidyReplacement", "<init>"));
        }
        this.myFilePath = myFilePath;
        this.myReplacementText = myReplacementText;
        this.myBeginOffset = myBeginOffset;
        this.myEndOffset = myEndOffset;
    }
    
    @NotNull
    public String getFilePath() {
        String myFilePath;
        try {
            myFilePath = this.myFilePath;
            if (myFilePath == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/daemon/clang/tidy/ClangTidyReplacement", "getFilePath"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        return myFilePath;
    }
    
    @NotNull
    public String getReplacementText() {
        String myReplacementText;
        try {
            myReplacementText = this.myReplacementText;
            if (myReplacementText == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/daemon/clang/tidy/ClangTidyReplacement", "getReplacementText"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        return myReplacementText;
    }
    
    public int getBeginOffset() {
        return this.myBeginOffset;
    }
    
    public int getEndOffset() {
        return this.myEndOffset;
    }
    
    private static IllegalArgumentException a(final IllegalArgumentException ex) {
        return ex;
    }
}
