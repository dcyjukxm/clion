// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.cpp;

import com.jetbrains.cidr.cpp.toolchains.CMake;
import org.jetbrains.annotations.NotNull;
import com.intellij.openapi.application.PathManager;
import com.intellij.openapi.util.SystemInfo;
import com.intellij.openapi.application.impl.ApplicationImpl;
import java.io.File;

public class CPPPathManager
{
    @NotNull
    public static File getBundledCMakeBinary(final boolean b) {
        String binPath = null;
        StringBuilder sb = null;
        String s3 = null;
        Label_0168: {
            Label_0139: {
                String s = null;
                Label_0058: {
                    Label_0026: {
                        Label_0017: {
                            try {
                                if (!ApplicationImpl.isRunningFromSources()) {
                                    break Label_0139;
                                }
                                final boolean b2 = b;
                                if (b2) {
                                    break Label_0017;
                                }
                                break Label_0026;
                            }
                            catch (IllegalStateException ex) {
                                throw a(ex);
                            }
                            try {
                                final boolean b2 = b;
                                if (b2) {
                                    s = "cygwin";
                                    break Label_0058;
                                }
                            }
                            catch (IllegalStateException ex2) {
                                throw a(ex2);
                            }
                        }
                        try {
                            if (SystemInfo.isWindows) {
                                s = "win";
                                break Label_0058;
                            }
                        }
                        catch (IllegalStateException ex3) {
                            throw a(ex3);
                        }
                    }
                    try {
                        if (SystemInfo.isMac) {
                            s = "mac";
                            break Label_0058;
                        }
                    }
                    catch (IllegalStateException ex4) {
                        throw a(ex4);
                    }
                    s = "linux";
                }
                final String s2 = s;
                File file;
                try {
                    file = new File(PathManager.getHomePath(), "CIDR/clion/bin/cmake/" + s2 + "/bin/" + a(b));
                    if (file == null) {
                        throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/cpp/CPPPathManager", "getBundledCMakeBinary"));
                    }
                }
                catch (IllegalStateException ex5) {
                    throw a(ex5);
                }
                return file;
                try {
                    binPath = PathManager.getBinPath();
                    sb = new StringBuilder();
                    if (b) {
                        s3 = "cmake_cygwin/bin/";
                        break Label_0168;
                    }
                }
                catch (IllegalStateException ex6) {
                    throw a(ex6);
                }
            }
            s3 = "cmake/bin/";
        }
        final File file2 = new File(binPath, sb.append(s3).append(a(b)).toString());
        if (file2 == null) {
            throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/cpp/CPPPathManager", "getBundledCMakeBinary"));
        }
        return file2;
    }
    
    @NotNull
    private static String a(final boolean b) {
        String s = null;
        Label_0016: {
            try {
                if (b) {
                    final String executable_NAME;
                    s = (executable_NAME = "cmake.exe");
                    break Label_0016;
                }
            }
            catch (IllegalStateException ex) {
                throw a(ex);
            }
            String executable_NAME;
            s = (executable_NAME = CMake.EXECUTABLE_NAME);
            try {
                if (executable_NAME == null) {
                    throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/cpp/CPPPathManager", "getCMakeExecutableName"));
                }
            }
            catch (IllegalStateException ex2) {
                throw a(ex2);
            }
        }
        return s;
    }
    
    private static IllegalStateException a(final IllegalStateException ex) {
        return ex;
    }
}
