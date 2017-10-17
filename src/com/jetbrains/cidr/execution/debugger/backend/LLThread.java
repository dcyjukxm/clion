// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.execution.debugger.backend;

import com.intellij.openapi.util.Comparing;
import com.intellij.openapi.util.text.StringUtil;
import org.jetbrains.annotations.Nullable;

public class LLThread
{
    private final long myId;
    @Nullable
    private final String myState;
    @Nullable
    private final String myWorkQueue;
    @Nullable
    private final String myName;
    
    public LLThread(final long n) {
        this(n, null, null, null);
    }
    
    public LLThread(final long n, @Nullable final String s, @Nullable final String s2) {
        this(n, s, s2, null);
    }
    
    public LLThread(final long myId, @Nullable final String s, @Nullable final String s2, @Nullable final String s3) {
        this.myId = myId;
        this.myState = StringUtil.nullize(s);
        this.myWorkQueue = StringUtil.nullize(s2);
        this.myName = StringUtil.nullize(s3);
    }
    
    public long getId() {
        return this.myId;
    }
    
    @Nullable
    public String getState() {
        return this.myState;
    }
    
    @Nullable
    public String getWorkQueue() {
        return this.myWorkQueue;
    }
    
    public String getDisplayName() {
        final StringBuilder sb = new StringBuilder();
        sb.append("Thread-").append(this.myId);
        if (this.myWorkQueue != null) {
            sb.append("-<").append(this.myWorkQueue).append(">");
        }
        if (this.myName != null) {
            sb.append("-[").append(this.myName).append("]");
        }
        return sb.toString();
    }
    
    @Override
    public String toString() {
        return this.getDisplayName();
    }
    
    @Override
    public boolean equals(final Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || this.getClass() != o.getClass()) {
            return false;
        }
        final LLThread llThread = (LLThread)o;
        return this.myId == llThread.myId && Comparing.equal(this.myState, llThread.myState) && Comparing.equal(this.myWorkQueue, llThread.myWorkQueue);
    }
    
    @Override
    public int hashCode() {
        return 31 * (31 * Long.hashCode(this.myId) + ((this.myState != null) ? this.myState.hashCode() : 0)) + ((this.myWorkQueue != null) ? this.myWorkQueue.hashCode() : 0);
    }
}
