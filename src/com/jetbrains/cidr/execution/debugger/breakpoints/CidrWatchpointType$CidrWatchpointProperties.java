// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.execution.debugger.breakpoints;

import org.jetbrains.annotations.Nullable;
import com.jetbrains.cidr.execution.debugger.CidrDebugProcess;
import com.intellij.xdebugger.XSourcePosition;
import com.jetbrains.cidr.execution.debugger.backend.LLValue;
import com.jetbrains.cidr.execution.debugger.backend.LLWatchpoint;
import com.intellij.xdebugger.breakpoints.XBreakpointProperties;

public static class CidrWatchpointProperties extends XBreakpointProperties<CidrWatchpointState>
{
    private LLWatchpoint.AccessType myAccessType;
    private LLWatchpoint.Lifetime myLifetime;
    private LLValue myLLValue;
    private String myExpr;
    private XSourcePosition myOriginalPosition;
    private CidrDebugProcess myDebugProcess;
    @Nullable
    private String myError;
    
    public LLWatchpoint.AccessType getAccessType() {
        return this.myAccessType;
    }
    
    public void setAccessType(final LLWatchpoint.AccessType myAccessType) {
        this.myAccessType = myAccessType;
    }
    
    @Nullable
    public LLWatchpoint.Lifetime getLifetime() {
        return this.myLifetime;
    }
    
    public void setLifetime(@Nullable final LLWatchpoint.Lifetime myLifetime) {
        this.myLifetime = myLifetime;
    }
    
    public XSourcePosition getSourcePosition() {
        return this.myOriginalPosition;
    }
    
    public CidrWatchpointState getState() {
        return new CidrWatchpointState();
    }
    
    public void loadState(final CidrWatchpointState cidrWatchpointState) {
    }
    
    public void setDebugProcess(@Nullable final CidrDebugProcess myDebugProcess) {
        this.myDebugProcess = myDebugProcess;
    }
    
    public CidrDebugProcess getDebugProcess() {
        return this.myDebugProcess;
    }
    
    public void setOriginalPosition(final XSourcePosition myOriginalPosition) {
        this.myOriginalPosition = myOriginalPosition;
    }
    
    @Nullable
    public String getError() {
        return this.myError;
    }
    
    public void setError(@Nullable final String myError) {
        this.myError = myError;
    }
    
    public LLValue getLLValue() {
        return this.myLLValue;
    }
    
    public void setLLValue(final LLValue myLLValue) {
        this.myLLValue = myLLValue;
    }
    
    public String getExpr() {
        return this.myExpr;
    }
    
    public void setExpr(final String myExpr) {
        this.myExpr = myExpr;
    }
    
    public static class CidrWatchpointState
    {
    }
}
