// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.execution.debugger.backend.gdb;

import org.antlr.v4.runtime.tree.RuleNode;
import org.antlr.v4.runtime.tree.ParseTreeVisitor;
import org.antlr.v4.runtime.ParserRuleContext;

public static class ResponseContext extends ParserRuleContext
{
    public OutOfBandRecordContext outOfBandRecord() {
        return (OutOfBandRecordContext)this.getRuleContext((Class)OutOfBandRecordContext.class, 0);
    }
    
    public ResultRecordContext resultRecord() {
        return (ResultRecordContext)this.getRuleContext((Class)ResultRecordContext.class, 0);
    }
    
    public ResponseContext(final ParserRuleContext parserRuleContext, final int n) {
        super(parserRuleContext, n);
    }
    
    public int getRuleIndex() {
        return 0;
    }
    
    public <T> T accept(final ParseTreeVisitor<? extends T> parseTreeVisitor) {
        if (parseTreeVisitor instanceof GdbVisitor) {
            return ((GdbVisitor<T>)parseTreeVisitor).visitResponse(this);
        }
        return (T)parseTreeVisitor.visitChildren((RuleNode)this);
    }
}
