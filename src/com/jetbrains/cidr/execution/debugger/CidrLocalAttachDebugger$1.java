// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.execution.debugger;

import com.intellij.execution.filters.TextConsoleBuilderFactory;
import com.intellij.execution.ExecutionException;
import com.intellij.xdebugger.XDebugProcess;
import org.jetbrains.annotations.NotNull;
import com.intellij.xdebugger.XDebugSession;
import com.intellij.execution.process.ProcessInfo;
import com.intellij.openapi.project.Project;
import com.intellij.xdebugger.XDebugSessionListener;
import com.intellij.xdebugger.XDebugProcessStarter;

class CidrLocalAttachDebugger$1 extends XDebugProcessStarter {
    final /* synthetic */ XDebugSessionListener[] val$listeners;
    final /* synthetic */ Project val$project;
    final /* synthetic */ ProcessInfo val$processInfo;
    
    @NotNull
    public XDebugProcess start(@NotNull final XDebugSession xDebugSession) throws ExecutionException {
        try {
            if (xDebugSession == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "session", "com/jetbrains/cidr/execution/debugger/CidrLocalAttachDebugger$1", "start"));
            }
        }
        catch (ExecutionException ex) {
            throw b(ex);
        }
        final XDebugSessionListener[] val$listeners = this.val$listeners;
        for (int length = val$listeners.length, i = 0; i < length; ++i) {
            xDebugSession.addSessionListener(val$listeners[i]);
        }
        final CidrLocalAttachedDebugProcess cidrLocalAttachedDebugProcess = new CidrLocalAttachedDebugProcess(CidrLocalAttachDebugger.access$000(CidrLocalAttachDebugger.this), this.val$processInfo, xDebugSession, TextConsoleBuilderFactory.getInstance().createBuilder(this.val$project));
        CidrLocalAttachedDebugProcess cidrLocalAttachedDebugProcess2;
        try {
            cidrLocalAttachedDebugProcess.start();
            cidrLocalAttachedDebugProcess2 = cidrLocalAttachedDebugProcess;
            if (cidrLocalAttachedDebugProcess2 == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/execution/debugger/CidrLocalAttachDebugger$1", "start"));
            }
        }
        catch (ExecutionException ex2) {
            throw b(ex2);
        }
        return cidrLocalAttachedDebugProcess2;
    }
    
    private static ExecutionException b(final ExecutionException ex) {
        return ex;
    }
}