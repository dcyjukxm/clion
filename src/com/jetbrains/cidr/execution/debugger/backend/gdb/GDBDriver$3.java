// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.execution.debugger.backend.gdb;

import com.intellij.execution.process.OSProcessUtil;
import com.intellij.execution.configurations.GeneralCommandLine;
import com.intellij.execution.process.OSProcessHandler;

class GDBDriver$3 extends OSProcessHandler {
    protected void doDestroyProcess() {
        OSProcessUtil.killProcess(this.myProcess);
    }
}