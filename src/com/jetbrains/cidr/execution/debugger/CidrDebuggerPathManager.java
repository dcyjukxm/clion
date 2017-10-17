// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.execution.debugger;

import com.intellij.openapi.application.impl.ApplicationImpl;
import org.jetbrains.annotations.Nullable;
import com.intellij.openapi.application.PathManager;
import com.intellij.openapi.util.SystemInfo;
import java.io.File;
import org.jetbrains.annotations.NotNull;

public class CidrDebuggerPathManager
{
    @NotNull
    public static File getLLDBBinFile(@NotNull final String s) {
        try {
            if (s == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "relativePath", "com/jetbrains/cidr/execution/debugger/CidrDebuggerPathManager", "getLLDBBinFile"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        File file2 = null;
        Label_0166: {
            String s2 = null;
            Label_0089: {
                Label_0072: {
                    Label_0063: {
                        try {
                            if (!a()) {
                                break Label_0166;
                            }
                            final boolean b = SystemInfo.isWindows;
                            if (b) {
                                break Label_0063;
                            }
                            break Label_0072;
                        }
                        catch (IllegalArgumentException ex2) {
                            throw a(ex2);
                        }
                        try {
                            final boolean b = SystemInfo.isWindows;
                            if (b) {
                                s2 = "win/";
                                break Label_0089;
                            }
                        }
                        catch (IllegalArgumentException ex3) {
                            throw a(ex3);
                        }
                    }
                    try {
                        if (SystemInfo.isMac) {
                            s2 = "mac/";
                            break Label_0089;
                        }
                    }
                    catch (IllegalArgumentException ex4) {
                        throw a(ex4);
                    }
                }
                s2 = "linux/";
            }
            final String s3 = s2;
            File file;
            try {
                file = new File(new File(PathManager.getHomePath(), "CIDR/cidr-debugger/bin/lldb/" + s3), s);
                if (file == null) {
                    throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/execution/debugger/CidrDebuggerPathManager", "getLLDBBinFile"));
                }
            }
            catch (IllegalArgumentException ex5) {
                throw a(ex5);
            }
            return file;
            try {
                file2 = new File(PathManager.getBinPath(), "lldb/" + s);
                if (file2 == null) {
                    throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/execution/debugger/CidrDebuggerPathManager", "getLLDBBinFile"));
                }
            }
            catch (IllegalArgumentException ex6) {
                throw a(ex6);
            }
        }
        return file2;
    }
    
    @NotNull
    public static File getBundledLLDBSTLPrettyPrinters() {
        File file2 = null;
        Label_0064: {
            File file = null;
            Label_0029: {
                try {
                    if (!a()) {
                        break Label_0064;
                    }
                    final String s = PathManager.getHomePath();
                    final String s2 = "CIDR/cidr-debugger/bin/lldb/renderers";
                    file = new File(s, s2);
                    if (file == null) {
                        break Label_0029;
                    }
                    return file;
                }
                catch (IllegalArgumentException ex) {
                    throw a(ex);
                }
                try {
                    final String s = PathManager.getHomePath();
                    final String s2 = "CIDR/cidr-debugger/bin/lldb/renderers";
                    file = new File(s, s2);
                    if (file == null) {
                        throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/execution/debugger/CidrDebuggerPathManager", "getBundledLLDBSTLPrettyPrinters"));
                    }
                }
                catch (IllegalArgumentException ex2) {
                    throw a(ex2);
                }
            }
            return file;
            try {
                file2 = new File(PathManager.getBinPath(), "lldb/renderers");
                if (file2 == null) {
                    throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/execution/debugger/CidrDebuggerPathManager", "getBundledLLDBSTLPrettyPrinters"));
                }
            }
            catch (IllegalArgumentException ex3) {
                throw a(ex3);
            }
        }
        return file2;
    }
    
    @NotNull
    public static File getBundledGDBBinary() {
        File bundledGDBFile = null;
        Label_0017: {
            try {
                if (SystemInfo.isWindows) {
                    final String s = "bin/gdb.exe";
                    break Label_0017;
                }
            }
            catch (IllegalArgumentException ex) {
                throw a(ex);
            }
            final String s = "bin/gdb";
            try {
                bundledGDBFile = getBundledGDBFile(s);
                if (bundledGDBFile == null) {
                    throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/execution/debugger/CidrDebuggerPathManager", "getBundledGDBBinary"));
                }
            }
            catch (IllegalArgumentException ex2) {
                throw a(ex2);
            }
        }
        return bundledGDBFile;
    }
    
    @NotNull
    public static File getBundledGDBFile(@NotNull final String s) {
        try {
            if (s == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "relativePath", "com/jetbrains/cidr/execution/debugger/CidrDebuggerPathManager", "getBundledGDBFile"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        File file2 = null;
        Label_0171: {
            String s2 = null;
            Label_0089: {
                Label_0072: {
                    Label_0063: {
                        try {
                            if (!a()) {
                                break Label_0171;
                            }
                            final boolean b = SystemInfo.isWindows;
                            if (b) {
                                break Label_0063;
                            }
                            break Label_0072;
                        }
                        catch (IllegalArgumentException ex2) {
                            throw a(ex2);
                        }
                        try {
                            final boolean b = SystemInfo.isWindows;
                            if (b) {
                                s2 = "win/";
                                break Label_0089;
                            }
                        }
                        catch (IllegalArgumentException ex3) {
                            throw a(ex3);
                        }
                    }
                    try {
                        if (SystemInfo.isMac) {
                            s2 = "mac/";
                            break Label_0089;
                        }
                    }
                    catch (IllegalArgumentException ex4) {
                        throw a(ex4);
                    }
                }
                s2 = "linux/";
            }
            final String s3 = s2;
            File file;
            try {
                file = new File(new File(PathManager.getHomePath(), "CIDR/cidr-debugger/bin/gdb/" + s3 + "/"), s);
                if (file == null) {
                    throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/execution/debugger/CidrDebuggerPathManager", "getBundledGDBFile"));
                }
            }
            catch (IllegalArgumentException ex5) {
                throw a(ex5);
            }
            return file;
            try {
                file2 = new File(PathManager.getBinPath(), "gdb/" + s);
                if (file2 == null) {
                    throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/execution/debugger/CidrDebuggerPathManager", "getBundledGDBFile"));
                }
            }
            catch (IllegalArgumentException ex6) {
                throw a(ex6);
            }
        }
        return file2;
    }
    
    @NotNull
    public static File getWinbreakFile(@NotNull final String s) {
        try {
            if (s == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "name", "com/jetbrains/cidr/execution/debugger/CidrDebuggerPathManager", "getWinbreakFile"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        File file3 = null;
        Label_0116: {
            File file2 = null;
            Label_0081: {
                try {
                    if (!a()) {
                        break Label_0116;
                    }
                    final String s2 = PathManager.getHomePath();
                    final String s3 = "CIDR/cidr-debugger/bin/gdb/";
                    final File file = new File(s2, s3);
                    final String s4 = s;
                    file2 = new File(file, s4);
                    if (file2 == null) {
                        break Label_0081;
                    }
                    return file2;
                }
                catch (IllegalArgumentException ex2) {
                    throw a(ex2);
                }
                try {
                    final String s2 = PathManager.getHomePath();
                    final String s3 = "CIDR/cidr-debugger/bin/gdb/";
                    final File file = new File(s2, s3);
                    final String s4 = s;
                    file2 = new File(file, s4);
                    if (file2 == null) {
                        throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/execution/debugger/CidrDebuggerPathManager", "getWinbreakFile"));
                    }
                }
                catch (IllegalArgumentException ex3) {
                    throw a(ex3);
                }
            }
            return file2;
            try {
                file3 = new File(PathManager.getBinPath(), "gdb/" + s);
                if (file3 == null) {
                    throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/execution/debugger/CidrDebuggerPathManager", "getWinbreakFile"));
                }
            }
            catch (IllegalArgumentException ex4) {
                throw a(ex4);
            }
        }
        return file3;
    }
    
    @NotNull
    public static File getBundledGDBSTLPrettyPrinters() {
        File file2 = null;
        Label_0064: {
            File file = null;
            Label_0029: {
                try {
                    if (!a()) {
                        break Label_0064;
                    }
                    final String s = PathManager.getHomePath();
                    final String s2 = "CIDR/cidr-debugger/bin/gdb/renderers";
                    file = new File(s, s2);
                    if (file == null) {
                        break Label_0029;
                    }
                    return file;
                }
                catch (IllegalArgumentException ex) {
                    throw a(ex);
                }
                try {
                    final String s = PathManager.getHomePath();
                    final String s2 = "CIDR/cidr-debugger/bin/gdb/renderers";
                    file = new File(s, s2);
                    if (file == null) {
                        throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/execution/debugger/CidrDebuggerPathManager", "getBundledGDBSTLPrettyPrinters"));
                    }
                }
                catch (IllegalArgumentException ex2) {
                    throw a(ex2);
                }
            }
            return file;
            try {
                file2 = new File(PathManager.getBinPath(), "gdb/renderers");
                if (file2 == null) {
                    throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/execution/debugger/CidrDebuggerPathManager", "getBundledGDBSTLPrettyPrinters"));
                }
            }
            catch (IllegalArgumentException ex3) {
                throw a(ex3);
            }
        }
        return file2;
    }
    
    @Nullable
    public static File getOSXSystemPythonBinDir() {
        final File file = new File("/System/Library/Frameworks/Python.framework/Versions/Current/bin");
        try {
            if (!file.exists()) {
                return null;
            }
            final File file2 = file;
            final boolean b = file2.isDirectory();
            if (b) {
                return file;
            }
            return null;
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        try {
            final File file2 = file;
            final boolean b = file2.isDirectory();
            if (b) {
                return file;
            }
        }
        catch (IllegalArgumentException ex2) {
            throw a(ex2);
        }
        return null;
    }
    
    private static boolean a() {
        return ApplicationImpl.isRunningFromSources();
    }
    
    private static IllegalArgumentException a(final IllegalArgumentException ex) {
        return ex;
    }
}
