// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.execution.build;

import java.io.OutputStream;
import com.intellij.execution.process.ProcessHandler;

class ShowBuildConsoleInternalAction$1 extends ProcessHandler {
    protected void destroyProcessImpl() {
    }
    
    protected void detachProcessImpl() {
    }
    
    public boolean detachIsDefault() {
        return false;
    }
    
    public OutputStream getProcessInput() {
        return null;
    }
}