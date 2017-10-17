// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.execution;

import com.intellij.openapi.application.ApplicationManager;
import java.util.concurrent.Future;
import org.jetbrains.annotations.NotNull;
import java.nio.charset.Charset;
import java.io.InputStream;
import com.intellij.util.io.BaseOutputReader;

class GLogOutputReaders$MyLogReader$1 extends BaseOutputReader {
    final /* synthetic */ String val$presentableName;
    
    {
        this.start(this.val$presentableName);
    }
    
    protected void onTextAvailable(@NotNull final String s) {
        try {
            if (s == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "text", "com/jetbrains/cidr/execution/GLogOutputReaders$MyLogReader$1", "onTextAvailable"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        MyLogReader.this.this$0.onTextAvailable(s, MyLogReader.access$400(MyLogReader.this));
    }
    
    @NotNull
    protected Future<?> executeOnPooledThread(@NotNull final Runnable runnable) {
        try {
            if (runnable == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "runnable", "com/jetbrains/cidr/execution/GLogOutputReaders$MyLogReader$1", "executeOnPooledThread"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        Future executeOnPooledThread;
        try {
            executeOnPooledThread = ApplicationManager.getApplication().executeOnPooledThread(runnable);
            if (executeOnPooledThread == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/execution/GLogOutputReaders$MyLogReader$1", "executeOnPooledThread"));
            }
        }
        catch (IllegalArgumentException ex2) {
            throw a(ex2);
        }
        return (Future<?>)executeOnPooledThread;
    }
    
    private static IllegalArgumentException a(final IllegalArgumentException ex) {
        return ex;
    }
}