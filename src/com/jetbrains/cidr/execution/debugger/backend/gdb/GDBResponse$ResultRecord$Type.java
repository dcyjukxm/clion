// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.execution.debugger.backend.gdb;

public enum Type implements RecordType
{
    done, 
    running, 
    connected, 
    error, 
    exit, 
    stepping, 
    continuing, 
    result, 
    tuple_value, 
    list_value, 
    str_value;
}
