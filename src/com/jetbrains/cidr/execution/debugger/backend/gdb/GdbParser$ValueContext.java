// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.execution.debugger.backend.gdb;

import org.antlr.v4.runtime.tree.RuleNode;
import org.antlr.v4.runtime.tree.ParseTreeVisitor;
import org.antlr.v4.runtime.ParserRuleContext;

public static class ValueContext extends ParserRuleContext
{
    public CnstContext cnst() {
        return (CnstContext)this.getRuleContext((Class)CnstContext.class, 0);
    }
    
    public TupleContext tuple() {
        return (TupleContext)this.getRuleContext((Class)TupleContext.class, 0);
    }
    
    public ListContext list() {
        return (ListContext)this.getRuleContext((Class)ListContext.class, 0);
    }
    
    public ValueContext(final ParserRuleContext parserRuleContext, final int n) {
        super(parserRuleContext, n);
    }
    
    public int getRuleIndex() {
        return 5;
    }
    
    public <T> T accept(final ParseTreeVisitor<? extends T> parseTreeVisitor) {
        if (parseTreeVisitor instanceof GdbVisitor) {
            return ((GdbVisitor<T>)parseTreeVisitor).visitValue(this);
        }
        return (T)parseTreeVisitor.visitChildren((RuleNode)this);
    }
}
