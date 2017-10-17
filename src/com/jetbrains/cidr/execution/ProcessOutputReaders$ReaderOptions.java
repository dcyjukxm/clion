// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.execution;

import com.intellij.util.io.BaseDataReader;
import com.intellij.util.io.BaseOutputReader;

public static class ReaderOptions extends BaseOutputReader.Options
{
    private final BaseDataReader.SleepingPolicy myPolicy;
    
    public ReaderOptions(final BaseDataReader.SleepingPolicy myPolicy) {
        this.myPolicy = myPolicy;
    }
    
    public BaseDataReader.SleepingPolicy policy() {
        return this.myPolicy;
    }
    
    public boolean withSeparators() {
        return true;
    }
    
    public boolean splitToLines() {
        return false;
    }
    
    public boolean sendIncompleteLines() {
        return true;
    }
}
