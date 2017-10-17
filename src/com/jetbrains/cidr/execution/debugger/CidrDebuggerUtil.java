// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.execution.debugger;

import org.jetbrains.annotations.NotNull;
import com.intellij.openapi.util.text.StringUtil;

public class CidrDebuggerUtil
{
    @NotNull
    public static String getExceptionMessage(final Exception ex) {
        String notNullize;
        try {
            notNullize = StringUtil.notNullize(ex.getMessage(), ex.getClass().getSimpleName());
            if (notNullize == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/execution/debugger/CidrDebuggerUtil", "getExceptionMessage"));
            }
        }
        catch (IllegalStateException ex2) {
            throw a(ex2);
        }
        return notNullize;
    }
    
    private static IllegalStateException a(final IllegalStateException ex) {
        return ex;
    }
}
