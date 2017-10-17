// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.execution.debugger.backend.gdb;

import org.antlr.v4.runtime.misc.NotNull;
import org.antlr.v4.runtime.tree.ParseTreeVisitor;

public interface GdbVisitor<T> extends ParseTreeVisitor<T>
{
    T visitResult(@NotNull final GdbParser.ResultContext p0);
    
    T visitTuple(@NotNull final GdbParser.TupleContext p0);
    
    T visitOutOfBandRecord(@NotNull final GdbParser.OutOfBandRecordContext p0);
    
    T visitCnst(@NotNull final GdbParser.CnstContext p0);
    
    T visitResponse(@NotNull final GdbParser.ResponseContext p0);
    
    T visitStreamRecord(@NotNull final GdbParser.StreamRecordContext p0);
    
    T visitCString(@NotNull final GdbParser.CStringContext p0);
    
    T visitResultRecord(@NotNull final GdbParser.ResultRecordContext p0);
    
    T visitList(@NotNull final GdbParser.ListContext p0);
    
    T visitValue(@NotNull final GdbParser.ValueContext p0);
    
    T visitAsyncRecord(@NotNull final GdbParser.AsyncRecordContext p0);
}
