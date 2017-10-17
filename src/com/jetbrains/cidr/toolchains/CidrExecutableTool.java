// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.toolchains;

import org.jetbrains.annotations.NotNull;
import java.io.File;

public abstract class CidrExecutableTool extends CidrTool
{
    @NotNull
    protected final File myExecutable;
    
    public CidrExecutableTool(@NotNull final File myExecutable) {
        if (myExecutable == null) {
            throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "executable", "com/jetbrains/cidr/toolchains/CidrExecutableTool", "<init>"));
        }
        this.myExecutable = myExecutable;
    }
    
    @NotNull
    public File getExecutable() {
        File myExecutable;
        try {
            myExecutable = this.myExecutable;
            if (myExecutable == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/toolchains/CidrExecutableTool", "getExecutable"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        return myExecutable;
    }
    
    @NotNull
    public String getExecutablePath() {
        String path;
        try {
            path = this.myExecutable.getPath();
            if (path == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/toolchains/CidrExecutableTool", "getExecutablePath"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        return path;
    }
    
    private static IllegalArgumentException a(final IllegalArgumentException ex) {
        return ex;
    }
}
