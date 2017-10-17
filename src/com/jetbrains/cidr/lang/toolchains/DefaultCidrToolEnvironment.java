// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.lang.toolchains;

import org.jetbrains.annotations.NotNull;
import com.intellij.openapi.util.io.FileUtil;
import com.intellij.openapi.util.text.StringUtil;
import org.jetbrains.annotations.Nullable;
import java.io.File;

public class DefaultCidrToolEnvironment extends CidrToolEnvironment
{
    public static final char[] UNIX_PATH_SEPARATORS;
    public static final char[] WINDOWS_UNIX_PATH_SEPARATORS;
    
    @Override
    public String toLocalPath(@Nullable final File file, @Nullable String canonicalPath) {
        try {
            if (canonicalPath == null) {
                return null;
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        try {
            if (this.isPlatformAbsolute(canonicalPath) || file == null) {
                return canonicalPath;
            }
        }
        catch (IllegalArgumentException ex2) {
            throw a(ex2);
        }
        canonicalPath = FileUtil.toCanonicalPath(StringUtil.trimEnd(file.getPath(), "/") + "/" + canonicalPath);
        return canonicalPath;
    }
    
    protected boolean isPlatformAbsolute(@NotNull final String s) {
        try {
            if (s == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "absoluteOrRelativeEnvPath", "com/jetbrains/cidr/lang/toolchains/DefaultCidrToolEnvironment", "isPlatformAbsolute"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        return FileUtil.isAbsolute(s);
    }
    
    @Override
    public String toEnvPath(@Nullable final String s) {
        return s;
    }
    
    @NotNull
    @Override
    public char[] getSupportedFileSeparators() {
        char[] unix_PATH_SEPARATORS;
        try {
            unix_PATH_SEPARATORS = DefaultCidrToolEnvironment.UNIX_PATH_SEPARATORS;
            if (unix_PATH_SEPARATORS == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/toolchains/DefaultCidrToolEnvironment", "getSupportedFileSeparators"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        return unix_PATH_SEPARATORS;
    }
    
    static {
        UNIX_PATH_SEPARATORS = new char[] { '/' };
        WINDOWS_UNIX_PATH_SEPARATORS = new char[] { '/', '\\' };
    }
    
    private static IllegalArgumentException a(final IllegalArgumentException ex) {
        return ex;
    }
}
