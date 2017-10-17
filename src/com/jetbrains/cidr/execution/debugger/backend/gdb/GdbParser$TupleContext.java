// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.execution.debugger.backend.gdb;

import org.antlr.v4.runtime.tree.RuleNode;
import org.antlr.v4.runtime.tree.ParseTreeVisitor;
import java.util.List;
import org.antlr.v4.runtime.ParserRuleContext;

public static class TupleContext extends ParserRuleContext
{
    public List<ResultContext> result() {
        return (List<ResultContext>)this.getRuleContexts((Class)ResultContext.class);
    }
    
    public ResultContext result(final int n) {
        return (ResultContext)this.getRuleContext((Class)ResultContext.class, n);
    }
    
    public TupleContext(final ParserRuleContext parserRuleContext, final int n) {
        super(parserRuleContext, n);
    }
    
    public int getRuleIndex() {
        return 7;
    }
    
    public <T> T accept(final ParseTreeVisitor<? extends T> parseTreeVisitor) {
        if (parseTreeVisitor instanceof GdbVisitor) {
            return ((GdbVisitor<T>)parseTreeVisitor).visitTuple(this);
        }
        return (T)parseTreeVisitor.visitChildren((RuleNode)this);
    }
}
