// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.execution;

import java.util.concurrent.TimeoutException;
import com.jetbrains.cidr.execution.debugger.CidrDebuggerLog;
import java.util.concurrent.TimeUnit;
import com.intellij.openapi.application.ApplicationManager;
import java.util.concurrent.Future;
import com.intellij.openapi.util.text.StringUtil;
import org.jetbrains.annotations.Nullable;
import com.pty4j.unix.Pty;
import java.io.IOException;
import com.intellij.execution.CommandLineUtil;
import java.io.InputStream;
import com.intellij.util.io.BaseDataReader;
import java.io.FileInputStream;
import com.intellij.execution.configurations.GeneralCommandLine;
import java.nio.charset.Charset;
import org.jetbrains.annotations.NotNull;
import com.intellij.openapi.util.Key;
import java.io.File;
import com.intellij.util.io.BaseOutputReader;

protected class MyOutputReader extends BaseOutputReader
{
    private final File myFile;
    private final Key myType;
    
    private MyOutputReader(@NotNull final File myFile, @NotNull final Charset charset, @NotNull final GeneralCommandLine generalCommandLine, final Key myType) throws IOException {
        if (myFile == null) {
            throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "file", "com/jetbrains/cidr/execution/ProcessOutputReaders$MyOutputReader", "<init>"));
        }
        if (charset == null) {
            throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "encoding", "com/jetbrains/cidr/execution/ProcessOutputReaders$MyOutputReader", "<init>"));
        }
        if (generalCommandLine == null) {
            throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "commandLine", "com/jetbrains/cidr/execution/ProcessOutputReaders$MyOutputReader", "<init>"));
        }
        if (myType == null) {
            throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "type", "com/jetbrains/cidr/execution/ProcessOutputReaders$MyOutputReader", "<init>"));
        }
        super((InputStream)new FileInputStream(myFile), charset, (BaseOutputReader.Options)new ReaderOptions(BaseDataReader.SleepingPolicy.SIMPLE));
        this.myFile = myFile;
        this.myType = myType;
        this.start(CommandLineUtil.extractPresentableName(generalCommandLine.getCommandLineString()));
    }
    
    private MyOutputReader(@NotNull final Pty pty, @NotNull final Charset charset, @NotNull final GeneralCommandLine generalCommandLine, final Key myType) {
        if (pty == null) {
            throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "pty", "com/jetbrains/cidr/execution/ProcessOutputReaders$MyOutputReader", "<init>"));
        }
        if (charset == null) {
            throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "encoding", "com/jetbrains/cidr/execution/ProcessOutputReaders$MyOutputReader", "<init>"));
        }
        if (generalCommandLine == null) {
            throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "commandLine", "com/jetbrains/cidr/execution/ProcessOutputReaders$MyOutputReader", "<init>"));
        }
        if (myType == null) {
            throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "type", "com/jetbrains/cidr/execution/ProcessOutputReaders$MyOutputReader", "<init>"));
        }
        super((InputStream)pty.getInputStream(), charset, (BaseOutputReader.Options)new ReaderOptions(BaseDataReader.SleepingPolicy.BLOCKING));
        this.myFile = null;
        this.myType = myType;
        this.start(CommandLineUtil.extractPresentableName(generalCommandLine.getCommandLineString()));
    }
    
    @Nullable
    public File getFile() {
        return this.myFile;
    }
    
    protected void onTextAvailable(@NotNull String s) {
        try {
            if (s == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "text", "com/jetbrains/cidr/execution/ProcessOutputReaders$MyOutputReader", "onTextAvailable"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw b(ex);
        }
        s = StringUtil.trimEnd(s, "\r");
        s = StringUtil.convertLineSeparators(s);
        ProcessOutputReaders.this.onTextAvailable(s, this.myType);
    }
    
    @NotNull
    protected Future<?> executeOnPooledThread(@NotNull final Runnable runnable) {
        try {
            if (runnable == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "runnable", "com/jetbrains/cidr/execution/ProcessOutputReaders$MyOutputReader", "executeOnPooledThread"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw b(ex);
        }
        Future executeOnPooledThread;
        try {
            executeOnPooledThread = ApplicationManager.getApplication().executeOnPooledThread(runnable);
            if (executeOnPooledThread == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/execution/ProcessOutputReaders$MyOutputReader", "executeOnPooledThread"));
            }
        }
        catch (IllegalArgumentException ex2) {
            throw b(ex2);
        }
        return (Future<?>)executeOnPooledThread;
    }
    
    public void stop() {
        try {
            super.stop();
            if (this.mySleepingPolicy == BaseDataReader.SleepingPolicy.BLOCKING) {
                final MyOutputReader myOutputReader = this;
                final MyOutputReader myOutputReader2 = this;
                final BaseDataReader.SleepingPolicy sleepingPolicy = myOutputReader2.mySleepingPolicy;
                final boolean b = false;
                final int n = sleepingPolicy.getTimeToSleep(b);
                final long n2 = n;
                final TimeUnit timeUnit = TimeUnit.MILLISECONDS;
                myOutputReader.waitFor(n2, timeUnit);
            }
            return;
        }
        catch (IOException ex) {
            throw b(ex);
        }
        try {
            final MyOutputReader myOutputReader = this;
            final MyOutputReader myOutputReader2 = this;
            final BaseDataReader.SleepingPolicy sleepingPolicy = myOutputReader2.mySleepingPolicy;
            final boolean b = false;
            final int n = sleepingPolicy.getTimeToSleep(b);
            final long n2 = n;
            final TimeUnit timeUnit = TimeUnit.MILLISECONDS;
            myOutputReader.waitFor(n2, timeUnit);
        }
        catch (InterruptedException | TimeoutException ex3) {
            try {
                this.close();
            }
            catch (IOException ex2) {
                CidrDebuggerLog.LOG.error((Throwable)ex2);
            }
        }
    }
    
    private static Exception b(final Exception ex) {
        return ex;
    }
}
