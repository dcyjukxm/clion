// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.execution.debugger.backend.gdb;

import com.intellij.execution.ExecutionFinishedException;
import java.io.IOException;
import com.intellij.util.ThrowableRunnable;
import java.util.concurrent.TimeUnit;
import com.intellij.openapi.util.SystemInfo;
import com.intellij.openapi.diagnostic.Logger;
import com.intellij.util.ObjectUtils;
import com.intellij.openapi.util.text.StringUtil;
import com.jetbrains.cidr.execution.debugger.CidrDebuggerLog;
import org.jetbrains.annotations.Nullable;
import com.intellij.execution.ExecutionException;
import com.jetbrains.cidr.execution.debugger.backend.DebuggerDriver;
import com.intellij.util.ThrowableConvertor;
import org.jetbrains.annotations.NotNull;

protected class Communication
{
    @NotNull
    public final String command;
    public final boolean suppressOutputEvent;
    public final boolean suppressRunningEvent;
    public final boolean suppressTargetFinishedEvent;
    public final boolean suppressRunningResult;
    @NotNull
    public final ThrowableConvertor<StopPlace, Boolean, ExecutionException> onSteppingFinished;
    @NotNull
    public final StringBuffer consoleOutput;
    public int receivedSignalCount;
    
    public Communication(@NotNull final GDBDriver gdbDriver, final String s) {
        if (s == null) {
            throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "command", "com/jetbrains/cidr/execution/debugger/backend/gdb/GDBDriver$Communication", "<init>"));
        }
        this(s, false, false, false, false, null);
    }
    
    public Communication(final String command, final boolean suppressOutputEvent, final boolean suppressRunningEvent, final boolean suppressTargetFinishedEvent, @Nullable final boolean suppressRunningResult, final ThrowableConvertor<StopPlace, Boolean, ExecutionException> throwableConvertor) {
        if (command == null) {
            throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "command", "com/jetbrains/cidr/execution/debugger/backend/gdb/GDBDriver$Communication", "<init>"));
        }
        this.consoleOutput = new StringBuffer();
        this.command = command;
        final Logger log = CidrDebuggerLog.LOG;
        while (true) {
            if (StringUtil.containsLineBreak((CharSequence)command)) {
                boolean b = false;
                Label_0091: {
                    try {
                        if (this.isMultilineCommand()) {
                            b = true;
                            break Label_0091;
                        }
                    }
                    catch (IllegalArgumentException ex) {
                        throw b(ex);
                    }
                    b = false;
                }
                log.assertTrue(b, (Object)("MI command must not contain unescaped newlines: " + command));
                this.suppressOutputEvent = suppressOutputEvent;
                this.suppressRunningEvent = suppressRunningEvent;
                this.suppressTargetFinishedEvent = suppressTargetFinishedEvent;
                this.suppressRunningResult = suppressRunningResult;
                this.onSteppingFinished = (ThrowableConvertor<StopPlace, Boolean, ExecutionException>)ObjectUtils.notNull((Object)throwableConvertor, (Object)(stopPlace -> true));
                return;
            }
            continue;
        }
    }
    
    protected boolean isMultilineCommand() {
        return this.command.endsWith("\nend");
    }
    
    protected boolean useExtraLineFeedWorkaround() {
        Label_0031: {
            try {
                if (!SystemInfo.isLinux) {
                    break Label_0031;
                }
                final Communication communication = this;
                final String s = communication.command;
                final String s2 = "-target-attach";
                final boolean b = s.startsWith(s2);
                if (b) {
                    return true;
                }
                break Label_0031;
            }
            catch (IllegalArgumentException ex) {
                throw b(ex);
            }
            try {
                final Communication communication = this;
                final String s = communication.command;
                final String s2 = "-target-attach";
                final boolean b = s.startsWith(s2);
                if (b) {
                    return true;
                }
            }
            catch (IllegalArgumentException ex2) {
                throw b(ex2);
            }
            try {
                if (!SystemInfo.isWindows) {
                    return false;
                }
                final Communication communication2 = this;
                final boolean b2 = communication2.isMultilineCommand();
                if (b2) {
                    return true;
                }
                return false;
            }
            catch (IllegalArgumentException ex3) {
                throw b(ex3);
            }
        }
        try {
            final Communication communication2 = this;
            final boolean b2 = communication2.isMultilineCommand();
            if (b2) {
                return true;
            }
        }
        catch (IllegalArgumentException ex4) {
            throw b(ex4);
        }
        return false;
    }
    
    protected void consumeResponseToExtraLineFeed() {
        try {
            GDBDriver.access$1700(GDBDriver.this).poll(3000L, TimeUnit.MILLISECONDS);
        }
        catch (InterruptedException ex) {
            CidrDebuggerLog.LOG.debug((Throwable)ex);
        }
    }
    
    public void initiate() throws ExecutionException {
        GDBDriver.this.sendRequestOrSpecialCommunication(this.command, (ThrowableRunnable<ExecutionException>)(() -> {
            try {
                GDBDriver.access$1900(GDBDriver.this).write((this.command + "\n").getBytes(GDBDriver.access$1800(GDBDriver.this).getCharset()));
                GDBDriver.access$1900(GDBDriver.this).flush();
            }
            catch (IOException ex) {
                try {
                    if (GDBDriver.access$2100(GDBDriver.this)) {
                        throw new ExecutionFinishedException((Throwable)ex);
                    }
                }
                catch (IOException ex2) {
                    throw b(ex2);
                }
                throw new ExecutionException("Cannot send request", (Throwable)ex);
            }
        }));
    }
    
    public Response waitFor(@NotNull final GDBResponse.ResultRecord.Type... array) throws ExecutionException, GDBCommandException {
        try {
            if (array == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "expectedTypes", "com/jetbrains/cidr/execution/debugger/backend/gdb/GDBDriver$Communication", "waitFor"));
            }
        }
        catch (IOException ex) {
            throw b(ex);
        }
        final boolean useExtraLineFeedWorkaround = this.useExtraLineFeedWorkaround();
        try {
            try {
                if (useExtraLineFeedWorkaround) {
                    GDBDriver.access$1900(GDBDriver.this).write("\n".getBytes(GDBDriver.access$1800(GDBDriver.this).getCharset()));
                    GDBDriver.access$1900(GDBDriver.this).flush();
                }
            }
            catch (IOException ex2) {
                throw b(ex2);
            }
            return GDBDriver.access$2000(GDBDriver.this, this.command, array);
        }
        catch (IOException ex3) {
            try {
                if (GDBDriver.access$2100(GDBDriver.this)) {
                    throw new ExecutionFinishedException((Throwable)ex3);
                }
            }
            catch (IOException ex4) {
                throw b(ex4);
            }
            throw new ExecutionException("Cannot send request", (Throwable)ex3);
        }
        finally {
            try {
                if (useExtraLineFeedWorkaround) {
                    this.consumeResponseToExtraLineFeed();
                }
            }
            catch (IOException ex5) {
                throw b(ex5);
            }
        }
    }
    
    public Response createResponse(@NotNull final GDBResponse.Record record) {
        try {
            if (record == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "record", "com/jetbrains/cidr/execution/debugger/backend/gdb/GDBDriver$Communication", "createResponse"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw b(ex);
        }
        return new ResultResponse(record, this.consoleOutput.toString(), this.receivedSignalCount);
    }
    
    private static Exception b(final Exception ex) {
        return ex;
    }
}
