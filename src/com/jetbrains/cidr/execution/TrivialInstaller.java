// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.execution;

import org.jetbrains.annotations.Nullable;
import java.io.File;
import org.jetbrains.annotations.NotNull;
import com.intellij.execution.ExecutionException;
import com.intellij.execution.configurations.GeneralCommandLine;

public class TrivialInstaller implements Installer
{
    private final GeneralCommandLine myCl;
    
    public TrivialInstaller(final GeneralCommandLine myCl) {
        this.myCl = myCl;
    }
    
    @NotNull
    @Override
    public GeneralCommandLine install() throws ExecutionException {
        GeneralCommandLine myCl;
        try {
            myCl = this.myCl;
            if (myCl == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/execution/TrivialInstaller", "install"));
            }
        }
        catch (ExecutionException ex) {
            throw b((Exception)ex);
        }
        return myCl;
    }
    
    @NotNull
    @Override
    public File getExecutableFile() {
        File file;
        try {
            file = new File(this.myCl.getExePath());
            if (file == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/execution/TrivialInstaller", "getExecutableFile"));
            }
        }
        catch (IllegalStateException ex) {
            throw b(ex);
        }
        return file;
    }
    
    @Nullable
    @Override
    public File getAppWorkingDir() {
        return this.myCl.getWorkDirectory();
    }
    
    private static Exception b(final Exception ex) {
        return ex;
    }
}
