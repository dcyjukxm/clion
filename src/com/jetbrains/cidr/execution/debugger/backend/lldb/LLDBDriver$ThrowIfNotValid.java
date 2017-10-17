// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.execution.debugger.backend.lldb;

import java.util.Iterator;
import com.intellij.openapi.util.text.StringUtil;
import com.jetbrains.cidr.execution.debugger.backend.lldb.auto_generated.Protocol;
import com.intellij.util.Consumer;
import com.google.protobuf.GeneratedMessage;

protected static class ThrowIfNotValid<T extends GeneratedMessage> implements Consumer<T>
{
    private String myMessage;
    private boolean myIsValid;
    
    public ThrowIfNotValid(final String myMessage) {
        this.myIsValid = false;
        this.myMessage = myMessage;
    }
    
    public void consume(final T t) {
        for (final Protocol.CommonResponse next : t.getAllFields().values()) {
            if (next instanceof Protocol.CommonResponse) {
                final Protocol.CommonResponse commonResponse = next;
                this.myIsValid = commonResponse.getIsValid();
                if (this.myIsValid || !commonResponse.hasErrorMessage()) {
                    continue;
                }
                final String errorMessage = commonResponse.getErrorMessage();
                if (StringUtil.isEmptyOrSpaces(errorMessage)) {
                    continue;
                }
                this.myMessage = errorMessage;
            }
        }
    }
    
    public void throwIfNeeded() throws LLDBDriverException {
        try {
            if (!this.myIsValid) {
                throw new LLDBDriverException(this.myMessage);
            }
        }
        catch (LLDBDriverException ex) {
            throw b(ex);
        }
    }
    
    public String getMessage() {
        return this.myMessage;
    }
    
    public boolean isValid() {
        return this.myIsValid;
    }
    
    private static LLDBDriverException b(final LLDBDriverException ex) {
        return ex;
    }
}
