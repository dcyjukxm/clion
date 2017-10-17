// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.cpp;

import com.intellij.openapi.util.io.FileUtil;
import java.util.ArrayList;
import com.intellij.openapi.util.SystemInfo;
import org.jetbrains.annotations.NotNull;
import java.util.List;
import org.jetbrains.annotations.Nullable;

public static class Settings
{
    private int myDetectedVersion;
    private boolean myDoNotNotify;
    private boolean myIsWindows;
    private boolean myIsMac;
    @Nullable
    private WinEnvironment myWinEnvironment;
    @Nullable
    private String myMSVCHomePath;
    @Nullable
    private String myMinGWHomePath;
    @Nullable
    private String myCygwinHomePath;
    private boolean myUseBundledCMake;
    @Nullable
    private String mySpecifiedCMakeExecutablePath;
    private boolean myUseBundledLLDB;
    private boolean myUseBundledGDB;
    @Nullable
    private String mySpecifiedGDBExecutablePath;
    @NotNull
    private List<String> myDebuggers;
    
    public Settings() {
        this(SystemInfo.isWindows, SystemInfo.isMac);
    }
    
    public Settings(final boolean myIsWindows, final boolean myIsMac) {
        this.myUseBundledCMake = true;
        this.myDebuggers = new ArrayList<String>();
        this.myIsMac = myIsMac;
        this.myIsWindows = myIsWindows;
        this.myUseBundledLLDB = this.myIsMac;
        this.myUseBundledGDB = !this.myUseBundledLLDB;
    }
    
    public int getDetectedVersion() {
        return this.myDetectedVersion;
    }
    
    public void setDetectedVersion(final int myDetectedVersion) {
        this.myDetectedVersion = myDetectedVersion;
    }
    
    private void b(final boolean myDoNotNotify) {
        this.myDoNotNotify = myDoNotNotify;
    }
    
    private void a(final boolean b) {
        Label_0018: {
            try {
                if (!b) {
                    return;
                }
                final Settings settings = this;
                final boolean b2 = settings.myDoNotNotify;
                if (!b2) {
                    break Label_0018;
                }
                return;
            }
            catch (IllegalStateException ex) {
                throw a(ex);
            }
            try {
                final Settings settings = this;
                final boolean b2 = settings.myDoNotNotify;
                if (!b2) {
                    CPPToolchains.notifyEnvironmentChanged();
                }
            }
            catch (IllegalStateException ex2) {
                throw a(ex2);
            }
        }
    }
    
    @Nullable
    public WinEnvironment getWinEnvironment() {
        return this.myWinEnvironment;
    }
    
    public void setWinEnvironment(@Nullable final WinEnvironment myWinEnvironment) {
        boolean b = false;
        Label_0017: {
            try {
                if (this.myWinEnvironment != myWinEnvironment) {
                    b = true;
                    break Label_0017;
                }
            }
            catch (IllegalStateException ex) {
                throw a(ex);
            }
            b = false;
        }
        final boolean b2 = b;
        this.myWinEnvironment = myWinEnvironment;
        this.a(b2);
    }
    
    @Nullable
    public String getMSVCHomePath() {
        return CPPToolchains.preparePath(this.myMSVCHomePath);
    }
    
    public void setMSVCHomePath(@Nullable String preparePath) {
        preparePath = CPPToolchains.preparePath(preparePath);
        boolean b = false;
        Label_0025: {
            try {
                if (!FileUtil.pathsEqual(this.myMSVCHomePath, preparePath)) {
                    b = true;
                    break Label_0025;
                }
            }
            catch (IllegalStateException ex) {
                throw a(ex);
            }
            b = false;
        }
        final boolean b2 = b;
        this.myMSVCHomePath = preparePath;
        this.a(b2);
    }
    
    @Nullable
    public String getMinGWHomePath() {
        return CPPToolchains.preparePath(this.myMinGWHomePath);
    }
    
    public void setMinGWHomePath(@Nullable String preparePath) {
        preparePath = CPPToolchains.preparePath(preparePath);
        boolean b = false;
        Label_0025: {
            try {
                if (!FileUtil.pathsEqual(this.myMinGWHomePath, preparePath)) {
                    b = true;
                    break Label_0025;
                }
            }
            catch (IllegalStateException ex) {
                throw a(ex);
            }
            b = false;
        }
        final boolean b2 = b;
        this.myMinGWHomePath = preparePath;
        this.a(b2);
    }
    
    @Nullable
    public String getCygwinHomePath() {
        return CPPToolchains.preparePath(this.myCygwinHomePath);
    }
    
    public void setCygwinHomePath(@Nullable String preparePath) {
        preparePath = CPPToolchains.preparePath(preparePath);
        boolean b = false;
        Label_0025: {
            try {
                if (!FileUtil.pathsEqual(this.myCygwinHomePath, preparePath)) {
                    b = true;
                    break Label_0025;
                }
            }
            catch (IllegalStateException ex) {
                throw a(ex);
            }
            b = false;
        }
        final boolean b2 = b;
        this.myCygwinHomePath = preparePath;
        this.a(b2);
    }
    
    public boolean isUseBundledCMake() {
        return this.myUseBundledCMake;
    }
    
    public void setUseBundledCMake(final boolean myUseBundledCMake) {
        boolean b = false;
        Label_0017: {
            try {
                if (this.myUseBundledCMake != myUseBundledCMake) {
                    b = true;
                    break Label_0017;
                }
            }
            catch (IllegalStateException ex) {
                throw a(ex);
            }
            b = false;
        }
        final boolean b2 = b;
        this.myUseBundledCMake = myUseBundledCMake;
        this.a(b2);
    }
    
    @Nullable
    public String getSpecifiedCMakeExecutablePath() {
        return CPPToolchains.preparePath(this.mySpecifiedCMakeExecutablePath);
    }
    
    public void setSpecifiedCMakeExecutablePath(@Nullable String preparePath) {
        preparePath = CPPToolchains.preparePath(preparePath);
        boolean b = false;
        Label_0025: {
            try {
                if (!FileUtil.pathsEqual(this.mySpecifiedCMakeExecutablePath, preparePath)) {
                    b = true;
                    break Label_0025;
                }
            }
            catch (IllegalStateException ex) {
                throw a(ex);
            }
            b = false;
        }
        final boolean b2 = b;
        this.mySpecifiedCMakeExecutablePath = preparePath;
        this.a(b2);
    }
    
    public boolean isUseBundledLLDB() {
        return this.myUseBundledLLDB;
    }
    
    public void setUseBundledLLDB(final boolean myUseBundledLLDB) {
        try {
            if (!CPPToolchains.access$100(this.myIsWindows)) {
                this.myUseBundledLLDB = false;
                this.myUseBundledGDB = true;
                return;
            }
        }
        catch (IllegalStateException ex) {
            throw a(ex);
        }
        try {
            if (this.myUseBundledLLDB = myUseBundledLLDB) {
                this.myUseBundledGDB = false;
            }
        }
        catch (IllegalStateException ex2) {
            throw a(ex2);
        }
    }
    
    public boolean isUseBundledGDB() {
        return this.myUseBundledGDB;
    }
    
    public void setUseBundledGDB(final boolean myUseBundledGDB) {
        try {
            this.myUseBundledGDB = myUseBundledGDB;
            if (myUseBundledGDB) {
                this.myUseBundledLLDB = false;
            }
        }
        catch (IllegalStateException ex) {
            throw a(ex);
        }
    }
    
    @Nullable
    public String getSpecifiedGDBExecutablePath() {
        return CPPToolchains.preparePath(this.mySpecifiedGDBExecutablePath);
    }
    
    public void setSpecifiedGDBExecutablePath(@Nullable final String s) {
        this.mySpecifiedGDBExecutablePath = CPPToolchains.preparePath(s);
    }
    
    @NotNull
    public List<String> getDebuggers() {
        List<String> myDebuggers;
        try {
            myDebuggers = this.myDebuggers;
            if (myDebuggers == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/cpp/CPPToolchains$Settings", "getDebuggers"));
            }
        }
        catch (IllegalStateException ex) {
            throw a(ex);
        }
        return myDebuggers;
    }
    
    public void setDebuggers(@NotNull final List<String> myDebuggers) {
        try {
            if (myDebuggers == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "debuggers", "com/jetbrains/cidr/cpp/CPPToolchains$Settings", "setDebuggers"));
            }
        }
        catch (IllegalStateException ex) {
            throw a(ex);
        }
        this.myDebuggers = myDebuggers;
    }
    
    private static IllegalStateException a(final IllegalStateException ex) {
        return ex;
    }
}
