// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.execution.ipcUtils;

import com.intellij.openapi.util.SystemInfo;
import java.io.IOException;
import java.io.File;
import com.sun.jna.Pointer;

public class POSIXLibHandle
{
    public static final int RTLD_LAZY = 1;
    public static final int RTLD_LOCAL;
    public static final int RTLD_GLOBAL;
    private Pointer handle;
    private final File myLib;
    
    public POSIXLibHandle(final File myLib) {
        this.handle = Pointer.NULL;
        this.myLib = myLib;
    }
    
    public boolean load() {
        if (this.handle == Pointer.NULL) {
            try {
                this.handle = POSIX.INSTANCE.dlopen(this.myLib.getCanonicalPath(), 0x1 | POSIXLibHandle.RTLD_GLOBAL);
                if (this.handle == Pointer.NULL) {
                    return false;
                }
            }
            catch (IOException ex) {
                return false;
            }
        }
        return true;
    }
    
    public static String dlerror() {
        return POSIX.INSTANCE.dlerror();
    }
    
    static {
        RTLD_LOCAL = (SystemInfo.isMac ? 4 : 0);
        RTLD_GLOBAL = (SystemInfo.isMac ? 8 : 256);
    }
}
