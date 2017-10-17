// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.execution;

import com.intellij.execution.process.ProcessListener;
import java.util.concurrent.TimeoutException;
import com.intellij.execution.ExecutionException;

public interface ProcessHandlerWithPID
{
    int getPID() throws ExecutionException;
    
    int getPID(final long p0) throws ExecutionException, TimeoutException;
    
    void addProcessListener(final ProcessListener p0);
    
    void startNotify();
    
    void destroyProcess();
    
    void detachProcess();
    
    boolean waitFor();
}
