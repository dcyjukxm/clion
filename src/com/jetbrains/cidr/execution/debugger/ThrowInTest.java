// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.execution.debugger;

import com.jetbrains.cidr.execution.debugger.backend.DebuggerCommandException;
import com.intellij.execution.ExecutionException;
import com.intellij.openapi.util.UserDataHolder;
import com.intellij.openapi.util.Key;

public class ThrowInTest
{
    public static void throwOn(final Object o, final Key key, final boolean b) {
        ((UserDataHolder)o).putUserData(key, (Object)b);
    }
    
    public static void doThrow(final UserDataHolder userDataHolder, final Key key) throws ExecutionException, DebuggerCommandException {
        final Boolean b = (Boolean)userDataHolder.getUserData(key);
        try {
            if (b == null) {
                return;
            }
        }
        catch (ExecutionException ex) {
            throw b(ex);
        }
        try {
            if (b == Boolean.TRUE) {
                throw new TestDebuggerException();
            }
        }
        catch (ExecutionException ex2) {
            throw b(ex2);
        }
        throw new TestExecutionException();
    }
    
    private static ExecutionException b(final ExecutionException ex) {
        return ex;
    }
    
    public static class TestDebuggerException extends DebuggerCommandException
    {
        public TestDebuggerException() {
            super("user exception");
        }
    }
    
    public static class TestExecutionException extends ExecutionException
    {
        public TestExecutionException() {
            super("execution exception");
        }
    }
}
