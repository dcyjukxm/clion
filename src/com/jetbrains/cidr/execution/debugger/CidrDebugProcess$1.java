// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.execution.debugger;

import com.intellij.execution.process.ProcessEvent;
import com.intellij.execution.process.ProcessHandler;
import com.intellij.execution.process.ProcessAdapter;

class CidrDebugProcess$1 extends ProcessAdapter {
    final /* synthetic */ ProcessHandler val$driverProcessHandler;
    
    public void startNotified(final ProcessEvent processEvent) {
        this.val$driverProcessHandler.startNotify();
    }
    
    public void processWillTerminate(final ProcessEvent processEvent, final boolean b) {
        CidrDebugProcess.access$200(CidrDebugProcess.this, !b);
    }
}