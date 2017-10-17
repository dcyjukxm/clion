// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.cpp;

import com.jetbrains.cidr.cpp.toolchains.CMake;
import com.jetbrains.cidr.toolchains.CidrToolSet;
import java.io.File;

public class CPPToolchainsCheckSettings
{
    public CPPToolchains.WinEnvironment environment;
    public File toolSetHome;
    public boolean useBundledCMake;
    public File cmakeFile;
    public boolean useBundledLLDB;
    public boolean useBundledGDB;
    public File gdbFile;
    public boolean invalidToolSet;
    public CidrToolSet toolSet;
    public CMake cmake;
}
