// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.execution;

import org.jetbrains.annotations.Nullable;
import java.io.File;
import org.jetbrains.annotations.NotNull;
import com.intellij.execution.ExecutionException;
import com.intellij.execution.configurations.GeneralCommandLine;

public interface Installer
{
    @NotNull
    GeneralCommandLine install() throws ExecutionException;
    
    @NotNull
    File getExecutableFile();
    
    @Nullable
    File getAppWorkingDir();
}
