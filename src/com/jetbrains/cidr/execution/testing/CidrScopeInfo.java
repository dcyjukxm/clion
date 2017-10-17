// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.execution.testing;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class CidrScopeInfo
{
    private final String mySuite;
    private final String myTest;
    private final String myPattern;
    private final String myName;
    private final Mode myMode;
    
    public CidrScopeInfo(@Nullable final String mySuite, @Nullable final String myTest, @Nullable final String myName, @Nullable final String myPattern) {
        this.mySuite = mySuite;
        this.myTest = myTest;
        this.myPattern = myPattern;
        this.myName = myName;
        this.myMode = ((myPattern == null) ? Mode.SUITE_TEST : Mode.PATTERN);
    }
    
    @Nullable
    public String getSuite() {
        return this.mySuite;
    }
    
    @Nullable
    public String getTest() {
        return this.myTest;
    }
    
    @Nullable
    public String getPattern() {
        return this.myPattern;
    }
    
    @Nullable
    public String getName() {
        return this.myName;
    }
    
    @NotNull
    public Mode getMode() {
        Mode myMode;
        try {
            myMode = this.myMode;
            if (myMode == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/execution/testing/CidrScopeInfo", "getMode"));
            }
        }
        catch (IllegalStateException ex) {
            throw a(ex);
        }
        return myMode;
    }
    
    private static IllegalStateException a(final IllegalStateException ex) {
        return ex;
    }
    
    public enum Mode
    {
        SUITE_TEST, 
        PATTERN;
    }
}
