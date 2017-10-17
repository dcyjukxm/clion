// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.execution.debugger.backend.gdb;

import com.jetbrains.cidr.execution.debugger.backend.DebuggerCommandException;
import com.intellij.execution.ExecutionException;
import com.jetbrains.cidr.execution.debugger.backend.LLValue;
import org.jetbrains.annotations.Nullable;
import org.jetbrains.annotations.NotNull;

private class FrameValueLoader implements LLValueLoader
{
    @NotNull
    private final String myFrameAddr;
    @Nullable
    private final String myAvailableValue;
    
    public FrameValueLoader(@Nullable final String myFrameAddr, final String myAvailableValue) {
        if (myFrameAddr == null) {
            throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "frameAddr", "com/jetbrains/cidr/execution/debugger/backend/gdb/GDBDriver$FrameValueLoader", "<init>"));
        }
        this.myFrameAddr = myFrameAddr;
        this.myAvailableValue = myAvailableValue;
    }
    
    @Override
    public void loadValue(@NotNull final LLValue llValue) throws ExecutionException, DebuggerCommandException {
        try {
            if (llValue == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "value", "com/jetbrains/cidr/execution/debugger/backend/gdb/GDBDriver$FrameValueLoader", "loadValue"));
            }
        }
        catch (ExecutionException ex) {
            throw b(ex);
        }
        final String name = llValue.getName();
        final String access$2300 = GDBDriver.access$2300(this.myFrameAddr, name);
        final String s = (String)GDBDriver.access$2400(GDBDriver.this).get((Object)access$2300);
        LLValueLoadedData access$2302;
        if (s == null) {
            final GDBResponse.Record access$2301 = GDBDriver.access$2500(GDBDriver.this, this.myFrameAddr, name);
            final String requiredString = access$2301.getResultList().getRequiredString("name");
            access$2302 = GDBDriver.access$2600(access$2301.getResultList(), requiredString, access$2301.getResultList().getString("value", ""));
            GDBDriver.access$2400(GDBDriver.this).put((Object)access$2300, (Object)requiredString);
            GDBDriver.access$2700(GDBDriver.this).put(requiredString, access$2302);
        }
        else {
            try {
                if (this.myAvailableValue != null) {
                    GDBDriver.access$2800(GDBDriver.this, s, this.myAvailableValue);
                }
            }
            catch (ExecutionException ex2) {
                throw b(ex2);
            }
            access$2302 = GDBDriver.access$2700(GDBDriver.this).get(s);
        }
        GDBDriver.access$2200(llValue, access$2302);
    }
    
    private static ExecutionException b(final ExecutionException ex) {
        return ex;
    }
}
