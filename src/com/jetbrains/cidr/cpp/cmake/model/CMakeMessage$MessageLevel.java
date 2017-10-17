// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.cpp.cmake.model;

public enum MessageLevel
{
    WARNING, 
    AUTHOR_WARNING, 
    ERROR, 
    FATAL_ERROR;
    
    public boolean isWarning() {
        return this.ordinal() >= MessageLevel.WARNING.ordinal() && this.ordinal() <= MessageLevel.AUTHOR_WARNING.ordinal();
    }
    
    public boolean isError() {
        return this.ordinal() >= MessageLevel.ERROR.ordinal();
    }
}
