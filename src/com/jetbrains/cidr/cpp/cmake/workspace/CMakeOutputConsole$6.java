// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.cpp.cmake.workspace;

import com.intellij.execution.process.ProcessEvent;
import com.intellij.execution.process.ProcessAdapter;

class CMakeOutputConsole$6 extends ProcessAdapter {
    final /* synthetic */ Runnable val$finishOnce;
    
    public void processTerminated(final ProcessEvent processEvent) {
        CMakeOutputConsole.access$200(CMakeOutputConsole.this, this.val$finishOnce);
    }
}