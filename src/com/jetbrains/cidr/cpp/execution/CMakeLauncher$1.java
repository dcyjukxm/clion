// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.cpp.execution;

import com.jetbrains.cidr.lang.toolchains.CidrToolEnvironment;
import com.jetbrains.cidr.execution.CidrCommandLineConfigurator;
import com.intellij.execution.configurations.PtyCommandLine;
import java.io.File;
import com.jetbrains.cidr.cpp.cmake.CMakeEnvironment;
import com.intellij.execution.ExecutionException;
import com.intellij.execution.configurations.GeneralCommandLine;
import com.intellij.openapi.util.ThrowableComputable;

class CMakeLauncher$1 implements ThrowableComputable<GeneralCommandLine, ExecutionException> {
    final /* synthetic */ boolean val$usePty;
    final /* synthetic */ CMakeEnvironment val$environment;
    final /* synthetic */ File val$runFile;
    
    public GeneralCommandLine compute() throws ExecutionException {
        Object o;
        if (this.val$usePty) {
            o = new PtyCommandLine();
            ((PtyCommandLine)o).setUseCygwinLaunch(this.val$environment.isCygwin());
        }
        else {
            o = new GeneralCommandLine();
        }
        ((GeneralCommandLine)o).setExePath(this.val$runFile.getPath());
        new CidrCommandLineConfigurator(CMakeLauncher.access$000(CMakeLauncher.this).getProject(), CMakeLauncher.access$100(CMakeLauncher.this, this.val$runFile.getParent())).configureCommandLine((GeneralCommandLine)o);
        this.val$environment.prepare((GeneralCommandLine)o, CidrToolEnvironment.PrepareFor.RUN);
        return (GeneralCommandLine)o;
    }
}