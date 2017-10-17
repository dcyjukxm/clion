// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.lang.dfa.contextSensitive;

import com.intellij.util.Alarm;
import com.intellij.openapi.Disposable;

public static class WorkingTimeMeasurer implements Disposable
{
    private final Alarm myAlarm;
    private volatile boolean myTimedOut;
    
    public WorkingTimeMeasurer(final long n) {
        this.myTimedOut = false;
        (this.myAlarm = new Alarm(Alarm.ThreadToUse.POOLED_THREAD, (Disposable)this)).addRequest(() -> this.myTimedOut = true, n);
    }
    
    public boolean isTimeOver() {
        return this.myTimedOut;
    }
    
    public void dispose() {
    }
}
