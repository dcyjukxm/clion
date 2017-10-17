// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.execution.build;

import com.intellij.openapi.util.Key;
import java.io.IOException;
import com.jetbrains.cidr.lang.OCLog;
import com.intellij.execution.process.ProcessEvent;
import java.io.FileWriter;
import com.intellij.execution.process.ProcessAdapter;

static final class CidrBuild$4 extends ProcessAdapter {
    final /* synthetic */ FileWriter val$finalWriter;
    
    public void processTerminated(final ProcessEvent processEvent) {
        try {
            this.val$finalWriter.close();
        }
        catch (IOException ex) {
            OCLog.LOG.warn((Throwable)ex);
        }
    }
    
    public void onTextAvailable(final ProcessEvent processEvent, final Key key) {
        try {
            this.val$finalWriter.write(processEvent.getText());
        }
        catch (IOException ex) {
            OCLog.LOG.warn((Throwable)ex);
        }
    }
}