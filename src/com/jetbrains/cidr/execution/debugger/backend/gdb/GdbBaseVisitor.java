// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.execution.debugger.backend.gdb;

import org.antlr.v4.runtime.tree.RuleNode;
import org.antlr.v4.runtime.misc.NotNull;
import org.antlr.v4.runtime.tree.AbstractParseTreeVisitor;

public class GdbBaseVisitor<T> extends AbstractParseTreeVisitor<T> implements GdbVisitor<T>
{
    public T visitResult(@NotNull final GdbParser.ResultContext resultContext) {
        return (T)this.visitChildren((RuleNode)resultContext);
    }
    
    public T visitTuple(@NotNull final GdbParser.TupleContext tupleContext) {
        return (T)this.visitChildren((RuleNode)tupleContext);
    }
    
    public T visitOutOfBandRecord(@NotNull final GdbParser.OutOfBandRecordContext outOfBandRecordContext) {
        return (T)this.visitChildren((RuleNode)outOfBandRecordContext);
    }
    
    public T visitCnst(@NotNull final GdbParser.CnstContext cnstContext) {
        return (T)this.visitChildren((RuleNode)cnstContext);
    }
    
    public T visitResponse(@NotNull final GdbParser.ResponseContext responseContext) {
        return (T)this.visitChildren((RuleNode)responseContext);
    }
    
    public T visitStreamRecord(@NotNull final GdbParser.StreamRecordContext streamRecordContext) {
        return (T)this.visitChildren((RuleNode)streamRecordContext);
    }
    
    public T visitCString(@NotNull final GdbParser.CStringContext cStringContext) {
        return (T)this.visitChildren((RuleNode)cStringContext);
    }
    
    public T visitResultRecord(@NotNull final GdbParser.ResultRecordContext resultRecordContext) {
        return (T)this.visitChildren((RuleNode)resultRecordContext);
    }
    
    public T visitList(@NotNull final GdbParser.ListContext listContext) {
        return (T)this.visitChildren((RuleNode)listContext);
    }
    
    public T visitValue(@NotNull final GdbParser.ValueContext valueContext) {
        return (T)this.visitChildren((RuleNode)valueContext);
    }
    
    public T visitAsyncRecord(@NotNull final GdbParser.AsyncRecordContext asyncRecordContext) {
        return (T)this.visitChildren((RuleNode)asyncRecordContext);
    }
}
