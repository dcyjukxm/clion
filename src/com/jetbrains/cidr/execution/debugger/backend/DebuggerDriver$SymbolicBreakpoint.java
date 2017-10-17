// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.execution.debugger.backend;

import org.jetbrains.annotations.Nullable;

public static class SymbolicBreakpoint
{
    public static final int INVALID_THREAD_ID = 0;
    private String myPattern;
    private boolean myRegexpPattern;
    @Nullable
    private String myModule;
    @Nullable
    private String myCondition;
    private long myThreadId;
    
    public SymbolicBreakpoint() {
        this.myRegexpPattern = false;
        this.myThreadId = 0L;
    }
    
    public String getPattern() {
        return this.myPattern;
    }
    
    public void setPattern(final String myPattern) {
        this.myPattern = myPattern;
    }
    
    public boolean isRegexpPattern() {
        return this.myRegexpPattern;
    }
    
    public void setRegexpPattern(final boolean myRegexpPattern) {
        this.myRegexpPattern = myRegexpPattern;
    }
    
    @Nullable
    public String getModule() {
        return this.myModule;
    }
    
    public void setModule(@Nullable final String myModule) {
        this.myModule = myModule;
    }
    
    @Nullable
    public String getCondition() {
        return this.myCondition;
    }
    
    public void setCondition(@Nullable final String myCondition) {
        this.myCondition = myCondition;
    }
    
    public long getThreadId() {
        return this.myThreadId;
    }
    
    public void setThreadId(final long myThreadId) {
        this.myThreadId = myThreadId;
    }
}
