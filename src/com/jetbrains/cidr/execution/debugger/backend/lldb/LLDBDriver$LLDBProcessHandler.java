// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.execution.debugger.backend.lldb;

import com.intellij.util.Consumer;
import com.jetbrains.cidr.execution.debugger.backend.lldb.auto_generated.Protocol;
import com.jetbrains.cidr.execution.ExecutionResult;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;
import com.google.protobuf.GeneratedMessage;
import com.jetbrains.cidr.execution.ipcUtils.ProtobufServer;
import com.intellij.util.io.BaseOutputReader;
import com.intellij.execution.ExecutionException;
import com.intellij.execution.configurations.GeneralCommandLine;
import org.jetbrains.annotations.NotNull;
import com.intellij.execution.process.OSProcessHandler;

private class LLDBProcessHandler extends OSProcessHandler
{
    final /* synthetic */ LLDBDriver this$0;
    
    public LLDBProcessHandler(final GeneralCommandLine generalCommandLine) throws ExecutionException {
        if (generalCommandLine == null) {
            throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "commandLine", "com/jetbrains/cidr/execution/debugger/backend/lldb/LLDBDriver$LLDBProcessHandler", "<init>"));
        }
        super(generalCommandLine);
    }
    
    @NotNull
    protected BaseOutputReader.Options readerOptions() {
        BaseOutputReader.Options blocking;
        try {
            blocking = BaseOutputReader.Options.BLOCKING;
            if (blocking == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/execution/debugger/backend/lldb/LLDBDriver$LLDBProcessHandler", "readerOptions"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw b(ex);
        }
        return blocking;
    }
    
    protected void doDestroyProcess() {
        Label_0042: {
            try {
                if (LLDBDriver.access$000(LLDBDriver.this).isDone()) {
                    final LLDBProcessHandler lldbProcessHandler = this;
                    final LLDBDriver lldbDriver = lldbProcessHandler.this$0;
                    final ExecutionResult executionResult = LLDBDriver.access$000(lldbDriver);
                    final ProtobufServer protobufServer = executionResult.get();
                    final ProtobufServer protobufServer2 = protobufServer;
                    final Protocol.CompositeRequest compositeRequest = ProtobufMessageFactory.exit();
                    final Class clazz = null;
                    final Consumer consumer = null;
                    protobufServer2.sendMessage(compositeRequest, clazz, consumer);
                }
                break Label_0042;
            }
            catch (TimeoutException ex) {
                throw b(ex);
            }
            try {
                final LLDBProcessHandler lldbProcessHandler = this;
                final LLDBDriver lldbDriver = lldbProcessHandler.this$0;
                final ExecutionResult executionResult = LLDBDriver.access$000(lldbDriver);
                final ProtobufServer protobufServer = executionResult.get();
                final ProtobufServer protobufServer2 = protobufServer;
                final Protocol.CompositeRequest compositeRequest = ProtobufMessageFactory.exit();
                final Class clazz = null;
                final Consumer consumer = null;
                protobufServer2.sendMessage(compositeRequest, clazz, consumer);
            }
            catch (ExecutionException ex2) {}
        }
        LLDBDriver.access$900(LLDBDriver.this).tearDown();
        try {
            this.executeOnPooledThread(() -> {
                try {
                    this.getProcess().waitFor();
                }
                catch (InterruptedException ex3) {}
                return;
            }).get(4L, TimeUnit.SECONDS);
        }
        catch (TimeoutException ex4) {}
        catch (InterruptedException ex5) {}
        catch (java.util.concurrent.ExecutionException ex6) {}
        super.doDestroyProcess();
    }
    
    private static Exception b(final Exception ex) {
        return ex;
    }
}
