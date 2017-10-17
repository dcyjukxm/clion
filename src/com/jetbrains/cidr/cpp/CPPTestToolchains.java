// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.cpp;

import java.util.Map;
import java.util.Iterator;
import gnu.trove.THashMap;
import com.intellij.openapi.util.text.StringUtil;
import com.jetbrains.cidr.cpp.toolchains.GDB;
import org.jetbrains.annotations.NotNull;
import com.intellij.openapi.util.SystemInfo;
import com.jetbrains.cidr.toolchains.CidrToolSet;
import com.jetbrains.cidr.cpp.toolchains.Cygwin;
import com.jetbrains.cidr.cpp.toolchains.MinGW;
import org.jetbrains.annotations.Nullable;
import java.io.File;
import com.jetbrains.cidr.cpp.toolchains.MSVC;

public class CPPTestToolchains extends CPPToolchains
{
    private static volatile boolean ourUseLLDB;
    
    public CPPTestToolchains(final boolean b, final boolean b2) {
        super(b, b2, false);
    }
    
    @Nullable
    @Override
    public MSVC getMSVC() {
        final File a = a(WinEnvironment.MSVC);
        try {
            if (a == null) {
                return null;
            }
        }
        catch (RuntimeException ex) {
            throw b(ex);
        }
        return new MSVC(a);
    }
    
    @Nullable
    @Override
    public MinGW getMinGW() {
        final File a = a(WinEnvironment.MINGW);
        try {
            if (a == null) {
                return null;
            }
        }
        catch (RuntimeException ex) {
            throw b(ex);
        }
        return new MinGW(a);
    }
    
    @Nullable
    @Override
    public Cygwin getCygwin() {
        final File a = a(WinEnvironment.CYGWIN);
        try {
            if (a == null) {
                return null;
            }
        }
        catch (RuntimeException ex) {
            throw b(ex);
        }
        return new Cygwin(a, true);
    }
    
    @Nullable
    @Override
    public CidrToolSet getToolSet() {
        final CidrToolSet toolSet = super.getToolSet();
        Label_0022: {
            try {
                if (toolSet != null) {
                    return toolSet;
                }
                final boolean b = SystemInfo.isWindows;
                if (b) {
                    break Label_0022;
                }
                return toolSet;
            }
            catch (RuntimeException ex) {
                throw b(ex);
            }
            try {
                final boolean b = SystemInfo.isWindows;
                if (b) {
                    throw new RuntimeException("Environment is not specified.\nUse -Dcpp.test.<msvc/mingw/cygwin>.home to set test environment on Windows");
                }
            }
            catch (RuntimeException ex2) {
                throw b(ex2);
            }
        }
        return toolSet;
    }
    
    @Override
    protected boolean isUseBundledGDB(@NotNull final Settings settings) {
        try {
            if (settings == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "settings", "com/jetbrains/cidr/cpp/CPPTestToolchains", "isUseBundledGDB"));
            }
        }
        catch (RuntimeException ex) {
            throw b(ex);
        }
        return true;
    }
    
    @Override
    public boolean isUseLLDB() {
        return CPPTestToolchains.ourUseLLDB;
    }
    
    public static void setUseLLDB(final boolean ourUseLLDB) {
        CPPTestToolchains.ourUseLLDB = ourUseLLDB;
    }
    
    @Nullable
    @Override
    public GDB getGDB() {
        final String nullize = StringUtil.nullize(System.getProperty("cpp.test.gdb.path"));
        try {
            if (nullize == null) {
                return super.getGDB();
            }
        }
        catch (RuntimeException ex) {
            throw b(ex);
        }
        final File file = new File(nullize);
        try {
            if (!file.exists()) {
                CPPLog.LOG.error("GDB not found at: " + nullize);
                return null;
            }
        }
        catch (RuntimeException ex2) {
            throw b(ex2);
        }
        return new GDB(file, this.getToolSet());
    }
    
    @Nullable
    private static File a(@NotNull final WinEnvironment winEnvironment) {
        try {
            if (winEnvironment == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "environment", "com/jetbrains/cidr/cpp/CPPTestToolchains", "getTestToolHome"));
            }
        }
        catch (RuntimeException ex) {
            throw b(ex);
        }
        final THashMap tHashMap = new THashMap();
        ((Map<WinEnvironment, String>)tHashMap).put(WinEnvironment.MSVC, "cpp.test.msvc.home");
        ((Map<WinEnvironment, String>)tHashMap).put(WinEnvironment.MINGW, "cpp.test.mingw.home");
        ((Map<WinEnvironment, String>)tHashMap).put(WinEnvironment.CYGWIN, "cpp.test.cygwin.home");
        final String s = ((Map<WinEnvironment, String>)tHashMap).get(winEnvironment);
        try {
            if (s == null) {
                throw new RuntimeException("Please add testing support for " + winEnvironment);
            }
        }
        catch (RuntimeException ex2) {
            throw b(ex2);
        }
        final String nullize = StringUtil.nullize(System.getProperty(s));
        try {
            if (nullize == null) {
                return null;
            }
        }
        catch (RuntimeException ex3) {
            throw b(ex3);
        }
        for (final String s2 : ((Map<WinEnvironment, String>)tHashMap).values()) {
            try {
                if (s2.equals(s)) {
                    continue;
                }
            }
            catch (RuntimeException ex4) {
                throw b(ex4);
            }
            try {
                if (System.getProperty(s2) != null) {
                    throw new RuntimeException("Only one testing environment should be specified. Got " + s + " and " + s2);
                }
                continue;
            }
            catch (RuntimeException ex5) {
                throw b(ex5);
            }
        }
        final File file = new File(nullize);
        CPPLog.LOG.assertTrue(file.exists(), (Object)(winEnvironment + " not found at: " + file));
        return file;
    }
    
    static {
        CPPTestToolchains.ourUseLLDB = false;
    }
    
    private static RuntimeException b(final RuntimeException ex) {
        return ex;
    }
}
