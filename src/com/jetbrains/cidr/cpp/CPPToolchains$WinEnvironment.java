// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.cpp;

import com.jetbrains.cidr.cpp.toolchains.MinGW;
import com.jetbrains.cidr.cpp.toolchains.Cygwin;
import com.jetbrains.cidr.cpp.toolchains.MSVC;
import com.jetbrains.cidr.toolchains.CidrToolSet;
import java.io.File;
import org.jetbrains.annotations.NotNull;

public enum WinEnvironment
{
    MSVC(CPPBundle.message("msvc", new Object[0])), 
    MINGW(CPPBundle.message("mingw", new Object[0])), 
    CYGWIN(CPPBundle.message("cygwin", new Object[0]));
    
    private String myDisplayName;
    
    private WinEnvironment(final String myDisplayName) {
        this.myDisplayName = myDisplayName;
    }
    
    @NotNull
    public String getDisplayName() {
        String myDisplayName;
        try {
            myDisplayName = this.myDisplayName;
            if (myDisplayName == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/cpp/CPPToolchains$WinEnvironment", "getDisplayName"));
            }
        }
        catch (RuntimeException ex) {
            throw b(ex);
        }
        return myDisplayName;
    }
    
    public CidrToolSet create(@NotNull final File file) {
        try {
            if (file == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "home", "com/jetbrains/cidr/cpp/CPPToolchains$WinEnvironment", "create"));
            }
        }
        catch (RuntimeException ex) {
            throw b(ex);
        }
        try {
            switch (this) {
                case MSVC: {
                    return new MSVC(file);
                }
                case MINGW: {
                    break;
                }
                case CYGWIN: {
                    return new Cygwin(file);
                }
                default: {
                    throw new RuntimeException("Add creation support for " + this);
                }
            }
        }
        catch (RuntimeException ex2) {
            throw b(ex2);
        }
        return new MinGW(file);
    }
    
    private static RuntimeException b(final RuntimeException ex) {
        return ex;
    }
}
