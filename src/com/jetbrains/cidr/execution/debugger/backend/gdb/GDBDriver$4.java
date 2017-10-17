// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.execution.debugger.backend.gdb;

import com.jetbrains.cidr.execution.debugger.CidrDebuggerLog;
import com.intellij.execution.process.ProcessOutputTypes;
import com.intellij.openapi.util.Key;
import com.intellij.execution.process.ProcessEvent;
import com.intellij.execution.process.ProcessAdapter;

class GDBDriver$4 extends ProcessAdapter {
    StringBuilder myBuffer = new StringBuilder();
    
    public void onTextAvailable(final ProcessEvent processEvent, final Key key) {
        if (key == ProcessOutputTypes.STDOUT) {
            this.myBuffer.append(processEvent.getText());
            String s = this.myBuffer.toString();
            boolean b = false;
            int index;
            while ((index = s.indexOf("\n")) != -1) {
                String s2 = s.substring(0, index).trim();
                s = s.substring(index + 1);
                b = true;
                final MIResponseFilter access$2900 = GDBDriver.access$2900(GDBDriver.this);
                if (access$2900 != null) {
                    s2 = access$2900.apply(GDBDriver.access$1600(GDBDriver.this).command, s2);
                }
                GDBDriver.access$3000(GDBDriver.this, s2);
            }
            if (b) {
                this.myBuffer = new StringBuilder(s);
            }
        }
        else {
            final String text = processEvent.getText();
            if (text != null && CidrDebuggerLog.LOG.isDebugEnabled()) {
                CidrDebuggerLog.LOG.debug("<" + text);
            }
            if (key == ProcessOutputTypes.STDERR) {
                GDBDriver.access$3100(GDBDriver.this, processEvent.getText(), key);
            }
        }
    }
    
    public void processTerminated(final ProcessEvent processEvent) {
        CidrDebuggerLog.LOG.info("Debugger exited with code " + processEvent.getExitCode());
        CidrDebuggerLog.LOG.debug("<[terminated]");
        GDBDriver.access$3200(GDBDriver.this);
        GDBDriver.access$3300(GDBDriver.this, processEvent.getExitCode());
    }
}