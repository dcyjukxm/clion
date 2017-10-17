// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.execution.debugger.backend.gdb;

import org.jetbrains.annotations.NotNull;
import com.intellij.execution.ExecutionException;

protected interface Response
{
    @NotNull
    GDBResponse.Record getRecord() throws ExecutionException;
    
    @NotNull
    default GDBTuple getResultList() throws ExecutionException {
        GDBTuple resultList;
        try {
            resultList = this.getRecord().getResultList();
            if (resultList == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/execution/debugger/backend/gdb/GDBDriver$Response", "getResultList"));
            }
        }
        catch (ExecutionException ex) {
            throw b(ex);
        }
        return resultList;
    }
    
    @NotNull
    String getOutput() throws ExecutionException;
    
    default void checkError() throws ExecutionException {
    }
    
    int getReceivedSignalCount() throws ExecutionException;
    
    default ExecutionException b(final ExecutionException ex) {
        return ex;
    }
}
