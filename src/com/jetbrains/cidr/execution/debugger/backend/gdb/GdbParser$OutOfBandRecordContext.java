// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.execution.debugger.backend.gdb;

import org.antlr.v4.runtime.tree.RuleNode;
import org.antlr.v4.runtime.tree.ParseTreeVisitor;
import org.antlr.v4.runtime.ParserRuleContext;

public static class OutOfBandRecordContext extends ParserRuleContext
{
    public AsyncRecordContext asyncRecord() {
        return (AsyncRecordContext)this.getRuleContext((Class)AsyncRecordContext.class, 0);
    }
    
    public StreamRecordContext streamRecord() {
        return (StreamRecordContext)this.getRuleContext((Class)StreamRecordContext.class, 0);
    }
    
    public OutOfBandRecordContext(final ParserRuleContext parserRuleContext, final int n) {
        super(parserRuleContext, n);
    }
    
    public int getRuleIndex() {
        return 2;
    }
    
    public <T> T accept(final ParseTreeVisitor<? extends T> parseTreeVisitor) {
        if (parseTreeVisitor instanceof GdbVisitor) {
            return ((GdbVisitor<T>)parseTreeVisitor).visitOutOfBandRecord(this);
        }
        return (T)parseTreeVisitor.visitChildren((RuleNode)this);
    }
}
