// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.execution.ipcUtils;

import com.sun.jna.Native;
import com.sun.jna.Pointer;
import com.sun.jna.Library;

public interface POSIX extends Library
{
    public static final POSIX INSTANCE = (POSIX)Native.loadLibrary((Class)POSIX.class);
    
    int posix_openpt(final int p0);
    
    int grantpt(final int p0);
    
    int unlockpt(final int p0);
    
    String ptsname(final int p0);
    
    void close(final int p0);
    
    int write(final int p0, final byte[] p1, final int p2);
    
    String ttyname(final int p0);
    
    int mkfifo(final String p0, final int p1);
    
    Pointer dlopen(final String p0, final int p1);
    
    String dlerror();
    
    void setenv(final String p0, final String p1, final int p2);
    
    void unsetenv(final String p0);
}
