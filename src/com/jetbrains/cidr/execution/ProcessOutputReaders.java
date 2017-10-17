// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.execution;

import com.jetbrains.cidr.execution.debugger.CidrDebuggerLog;
import com.intellij.openapi.application.ApplicationManager;
import java.util.concurrent.Future;
import com.intellij.openapi.util.text.StringUtil;
import org.jetbrains.annotations.Nullable;
import com.intellij.execution.CommandLineUtil;
import java.io.InputStream;
import com.intellij.util.io.BaseDataReader;
import java.io.FileInputStream;
import com.intellij.util.io.BaseOutputReader;
import com.intellij.openapi.util.Key;
import java.util.concurrent.TimeoutException;
import java.util.concurrent.TimeUnit;
import java.nio.charset.Charset;
import com.intellij.execution.ExecutionException;
import com.intellij.openapi.util.io.FileUtil;
import com.intellij.execution.process.ProcessOutputTypes;
import java.io.IOException;
import org.jetbrains.annotations.NotNull;
import com.intellij.execution.configurations.GeneralCommandLine;
import com.pty4j.unix.Pty;
import java.io.File;
import java.util.concurrent.atomic.AtomicReference;

public abstract class ProcessOutputReaders
{
    private final AtomicReference<MyOutputReader[]> myReaders;
    private volatile File myOutFile;
    private volatile File myErrFile;
    private volatile Pty myOutPty;
    private volatile Pty myErrPty;
    
    public ProcessOutputReaders() {
        this.myReaders = new AtomicReference<MyOutputReader[]>(new MyOutputReader[2]);
    }
    
    public void init(@NotNull final GeneralCommandLine generalCommandLine, final boolean b) throws ExecutionException {
        try {
            if (generalCommandLine == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "commandLine", "com/jetbrains/cidr/execution/ProcessOutputReaders", "init"));
            }
        }
        catch (IOException ex) {
            throw b(ex);
        }
        try {
            final Charset charset = generalCommandLine.getCharset();
            try {
                if (b) {
                    this.myOutPty = new Pty(true);
                    this.myErrPty = new Pty(true);
                    this.getReaders()[0] = new MyOutputReader(this.myOutPty, charset, generalCommandLine, ProcessOutputTypes.STDOUT);
                    this.getReaders()[1] = new MyOutputReader(this.myErrPty, charset, generalCommandLine, ProcessOutputTypes.STDERR);
                    return;
                }
            }
            catch (IOException ex2) {
                throw b(ex2);
            }
            this.myOutFile = FileUtil.createTempFile(this.getClass().getSimpleName(), "out");
            this.myErrFile = FileUtil.createTempFile(this.getClass().getSimpleName(), "err");
            this.getReaders()[0] = new MyOutputReader(this.myOutFile, charset, generalCommandLine, ProcessOutputTypes.STDOUT);
            this.getReaders()[1] = new MyOutputReader(this.myErrFile, charset, generalCommandLine, ProcessOutputTypes.STDERR);
        }
        catch (IOException ex3) {
            throw new ExecutionException("Cannot create output file", (Throwable)ex3);
        }
    }
    
    public String getOutFileAbsolutePath() {
        try {
            if (this.myOutPty != null) {
                return this.myOutPty.getSlaveName();
            }
        }
        catch (IllegalArgumentException ex) {
            throw b(ex);
        }
        return this.myOutFile.getAbsolutePath();
    }
    
    public String getErrFileAbsolutePath() {
        try {
            if (this.myErrPty != null) {
                return this.myErrPty.getSlaveName();
            }
        }
        catch (IllegalArgumentException ex) {
            throw b(ex);
        }
        return this.myErrFile.getAbsolutePath();
    }
    
    protected MyOutputReader[] getReaders() {
        return this.myReaders.get();
    }
    
    protected boolean doWaitFor(final MyOutputReader[] array) {
        for (final MyOutputReader myOutputReader : array) {
            try {
                myOutputReader.waitFor();
            }
            catch (InterruptedException ex) {
                Thread.interrupted();
                return false;
            }
        }
        return true;
    }
    
    protected boolean doWaitFor(final MyOutputReader[] array, final long n, final TimeUnit timeUnit) {
        for (final MyOutputReader myOutputReader : array) {
            try {
                myOutputReader.waitFor(n, timeUnit);
            }
            catch (InterruptedException ex) {
                Thread.interrupted();
                return false;
            }
            catch (TimeoutException ex2) {
                return false;
            }
        }
        return true;
    }
    
    public boolean waitFor() {
        return this.doWaitFor(this.getReaders());
    }
    
    public boolean waitFor(final long n, final TimeUnit timeUnit) {
        return this.doWaitFor(this.getReaders(), n, timeUnit);
    }
    
    public void close() {
        final MyOutputReader[] array2;
        final MyOutputReader[] array = array2 = this.myReaders.getAndSet(new MyOutputReader[0]);
        for (int length = array2.length, i = 0; i < length; ++i) {
            array2[i].stop();
        }
        this.doWaitFor(array);
        final MyOutputReader[] array3 = array;
        for (int length2 = array3.length, j = 0; j < length2; ++j) {
            final File file = array3[j].getFile();
            try {
                if (file != null) {
                    FileUtil.delete(file);
                }
            }
            catch (IllegalArgumentException ex) {
                throw b(ex);
            }
        }
    }
    
    protected abstract void onTextAvailable(@NotNull final String p0, @NotNull final Key p1);
    
    private static Exception b(final Exception ex) {
        return ex;
    }
    
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
    
    public static class ReaderOptions extends BaseOutputReader.Options
    {
        private final BaseDataReader.SleepingPolicy myPolicy;
        
        public ReaderOptions(final BaseDataReader.SleepingPolicy myPolicy) {
            this.myPolicy = myPolicy;
        }
        
        public BaseDataReader.SleepingPolicy policy() {
            return this.myPolicy;
        }
        
        public boolean withSeparators() {
            return true;
        }
        
        public boolean splitToLines() {
            return false;
        }
        
        public boolean sendIncompleteLines() {
            return true;
        }
    }
}
