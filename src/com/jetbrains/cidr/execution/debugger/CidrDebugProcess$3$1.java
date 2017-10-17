// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.execution.debugger;

import com.intellij.util.ui.UIUtil;
import com.intellij.openapi.Disposable;
import com.intellij.openapi.util.Disposer;
import com.intellij.xdebugger.XDebugSessionListener;

class CidrDebugProcess$3$1 implements XDebugSessionListener {
    public void sessionPaused() {
        this.b(true);
    }
    
    public void sessionResumed() {
        this.b(false);
    }
    
    public void sessionStopped() {
        this.b(false);
    }
    
    private void b(final boolean editable) {
        UIUtil.invokeAndWaitIfNeeded(() -> {
            if (!XDebugTabLayouter.this.this$0.getProject().isDisposed() && !Disposer.isDisposed((Disposable)CidrDebugProcess.access$300(XDebugTabLayouter.this.this$0))) {
                CidrDebugProcess.access$300(XDebugTabLayouter.this.this$0).setEditable(editable);
            }
        });
    }
}