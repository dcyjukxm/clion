// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.execution.debugger.backend.gdb;

import org.antlr.v4.runtime.tree.RuleNode;
import org.antlr.v4.runtime.tree.ParseTreeVisitor;
import org.antlr.v4.runtime.tree.TerminalNode;
import org.antlr.v4.runtime.Token;
import org.antlr.v4.runtime.ParserRuleContext;

public static class ResultContext extends ParserRuleContext
{
    public Token variable;
    
    public TerminalNode ID() {
        return this.getToken(19, 0);
    }
    
    public ValueContext value() {
        return (ValueContext)this.getRuleContext((Class)ValueContext.class, 0);
    }
    
    public ResultContext(final ParserRuleContext parserRuleContext, final int n) {
        super(parserRuleContext, n);
    }
    
    public int getRuleIndex() {
        return 4;
    }
    
    public <T> T accept(final ParseTreeVisitor<? extends T> parseTreeVisitor) {
        if (parseTreeVisitor instanceof GdbVisitor) {
            return ((GdbVisitor<T>)parseTreeVisitor).visitResult(this);
        }
        return (T)parseTreeVisitor.visitChildren((RuleNode)this);
    }
}
