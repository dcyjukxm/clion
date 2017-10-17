// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.cpp.cmake;

import com.intellij.execution.process.OSProcessUtil;
import com.intellij.execution.process.CapturingProcessHandler;

class CMakeRunner$1 implements Runnable {
    final /* synthetic */ CapturingProcessHandler val$handler;
    
    @Override
    public void run() {
        OSProcessUtil.killProcessTree(this.val$handler.getProcess());
    }
}