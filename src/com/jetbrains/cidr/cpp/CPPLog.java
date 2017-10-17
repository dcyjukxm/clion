// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.cpp;

import org.jetbrains.annotations.Nullable;
import java.io.File;
import org.jetbrains.annotations.NotNull;
import com.intellij.openapi.diagnostic.Logger;

public class CPPLog
{
    public static final Logger LOG;
    
    public static void logReadVersion(@NotNull final String s, @Nullable final File file) {
        try {
            if (s == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "tool", "com/jetbrains/cidr/cpp/CPPLog", "logReadVersion"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        try {
            if (file == null) {
                CPPLog.LOG.debug(s + ".readVersion() file: null");
                return;
            }
        }
        catch (IllegalArgumentException ex2) {
            throw a(ex2);
        }
        Logger log = null;
        StringBuilder append = null;
        String s2 = null;
        Label_0126: {
            try {
                log = CPPLog.LOG;
                append = new StringBuilder().append(s).append(".readVersion() file: '").append(file).append("' not ");
                if (!file.isFile()) {
                    s2 = "file";
                    break Label_0126;
                }
            }
            catch (IllegalArgumentException ex3) {
                throw a(ex3);
            }
            s2 = "executable";
        }
        log.debug(append.append(s2).toString());
    }
    
    public static void logReadVersion(@NotNull final String s, @Nullable final File file, @Nullable final String s2) {
        try {
            if (s == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "tool", "com/jetbrains/cidr/cpp/CPPLog", "logReadVersion"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        CPPLog.LOG.debug(s + ".readVersion() file: '" + file + "' output: " + s2);
    }
    
    static {
        LOG = Logger.getInstance("#" + CPPLog.class.getPackage().getName());
    }
    
    private static IllegalArgumentException a(final IllegalArgumentException ex) {
        return ex;
    }
}
