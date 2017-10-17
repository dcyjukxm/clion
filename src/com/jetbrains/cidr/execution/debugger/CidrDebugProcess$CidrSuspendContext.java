// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.execution.debugger;

import java.util.Iterator;
import com.intellij.execution.ExecutionException;
import java.util.List;
import com.jetbrains.cidr.execution.debugger.backend.LLFrame;
import com.jetbrains.cidr.execution.debugger.backend.DebuggerCommandException;
import com.jetbrains.cidr.execution.debugger.backend.LLThread;
import java.util.ArrayList;
import com.intellij.openapi.util.UserDataHolder;
import org.jetbrains.annotations.Nullable;
import com.jetbrains.cidr.execution.debugger.backend.DebuggerDriver;
import org.jetbrains.annotations.NotNull;
import com.intellij.xdebugger.frame.XExecutionStack;
import com.intellij.xdebugger.frame.XSuspendContext;

private class CidrSuspendContext extends XSuspendContext
{
    @NotNull
    private final XExecutionStack myActiveStack;
    private final long myActiveStackFrameId;
    
    public CidrSuspendContext(@Nullable final DebuggerDriver.StopPlace stopPlace, final CidrSuspensionCause cidrSuspensionCause) {
        if (stopPlace == null) {
            throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "stopPlace", "com/jetbrains/cidr/execution/debugger/CidrDebugProcess$CidrSuspendContext", "<init>"));
        }
        this.myActiveStack = CidrDebugProcess.this.newExecutionStack(stopPlace.thread, stopPlace.frame, true, cidrSuspensionCause);
        this.myActiveStackFrameId = stopPlace.thread.getId();
    }
    
    @Nullable
    public XExecutionStack getActiveExecutionStack() {
        return this.myActiveStack;
    }
    
    public void computeExecutionStacks(final XSuspendContext.XExecutionStackContainer xExecutionStackContainer) {
        try {
            if (CidrDebugProcess.viewsUpdatesDisabledInTests(xExecutionStackContainer)) {
                return;
            }
        }
        catch (IllegalArgumentException ex) {
            throw b(ex);
        }
        final List<LLThread> list2;
        ArrayList list;
        final Iterator<LLThread> iterator;
        LLThread llThread;
        XExecutionStack myActiveStack;
        final List<XExecutionStack> list3;
        CidrDebugProcess.this.postCommand(debuggerDriver -> {
            try {
                ThrowInTest.doThrow((UserDataHolder)CidrDebugProcess.this, CidrDebugProcess.THROW_ON_THREAD_COLLECTION);
                debuggerDriver.getThreads();
                list = new ArrayList(list2.size());
                list2.iterator();
                while (iterator.hasNext()) {
                    llThread = iterator.next();
                    Label_0098_1: {
                        try {
                            if (llThread.getId() == this.myActiveStackFrameId) {
                                myActiveStack = this.myActiveStack;
                                break Label_0098_1;
                            }
                        }
                        catch (DebuggerCommandException ex2) {
                            throw b(ex2);
                        }
                        CidrDebugProcess.this.newExecutionStack(llThread, null, false, null);
                    }
                    list3.add(myActiveStack);
                }
                xExecutionStackContainer.addExecutionStack((List)list, true);
            }
            catch (DebuggerCommandException ex3) {
                xExecutionStackContainer.errorOccurred(ex3.getMessage());
            }
            catch (ExecutionException ex4) {
                xExecutionStackContainer.errorOccurred(CidrDebuggerUtil.getExceptionMessage((Exception)ex4));
                throw ex4;
            }
        });
    }
    
    private static Exception b(final Exception ex) {
        return ex;
    }
}
