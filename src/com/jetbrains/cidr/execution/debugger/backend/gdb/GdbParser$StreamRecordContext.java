// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.execution.debugger.backend.gdb;

import org.antlr.v4.runtime.tree.RuleNode;
import org.antlr.v4.runtime.tree.ParseTreeVisitor;
import org.antlr.v4.runtime.Token;
import org.antlr.v4.runtime.ParserRuleContext;

public static class StreamRecordContext extends ParserRuleContext
{
    public Token prefix;
    
    public CStringContext cString() {
        return (CStringContext)this.getRuleContext((Class)CStringContext.class, 0);
    }
    
    public StreamRecordContext(final ParserRuleContext parserRuleContext, final int n) {
        super(parserRuleContext, n);
    }
    
    public int getRuleIndex() {
        return 9;
    }
    
    public <T> T accept(final ParseTreeVisitor<? extends T> parseTreeVisitor) {
        if (parseTreeVisitor instanceof GdbVisitor) {
            return ((GdbVisitor<T>)parseTreeVisitor).visitStreamRecord(this);
        }
        return (T)parseTreeVisitor.visitChildren((RuleNode)this);
    }
}
