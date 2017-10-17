// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.execution.debugger.backend.lldb;

import com.intellij.execution.process.ProcessOutputTypes;
import com.intellij.util.PathUtil;
import com.jetbrains.cidr.execution.debugger.CidrDebuggerLog;
import com.intellij.openapi.util.Key;
import com.intellij.execution.ExecutionFinishedException;
import com.intellij.execution.process.ProcessEvent;
import com.intellij.execution.process.ProcessAdapter;

class LLDBDriver$4 extends ProcessAdapter {
    public void processTerminated(final ProcessEvent processEvent) {
        if (!LLDBDriver.access$000(LLDBDriver.this).isDone()) {
            LLDBDriver.access$000(LLDBDriver.this).setException((Throwable)new ExecutionFinishedException());
        }
        LLDBDriver.access$200(LLDBDriver.this).close();
        LLDBDriver.access$300(LLDBDriver.this, processEvent.getExitCode());
    }
    
    public void onTextAvailable(final ProcessEvent processEvent, final Key key) {
        final String text = processEvent.getText();
        if (text != null && CidrDebuggerLog.LOG.isDebugEnabled()) {
            CidrDebuggerLog.LOG.debug(PathUtil.getFileName(LLDBDriver.access$400(LLDBDriver.this).getExePath()) + ": " + text);
        }
        if (key == ProcessOutputTypes.STDERR) {
            LLDBDriver.access$500(LLDBDriver.this, processEvent.getText(), key);
        }
    }
}