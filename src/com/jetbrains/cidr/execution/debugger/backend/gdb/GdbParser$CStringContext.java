// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.execution.debugger.backend.gdb;

import org.antlr.v4.runtime.tree.RuleNode;
import org.antlr.v4.runtime.tree.ParseTreeVisitor;
import org.antlr.v4.runtime.tree.TerminalNode;
import org.antlr.v4.runtime.ParserRuleContext;

public static class CStringContext extends ParserRuleContext
{
    public TerminalNode STRING() {
        return this.getToken(18, 0);
    }
    
    public CStringContext(final ParserRuleContext parserRuleContext, final int n) {
        super(parserRuleContext, n);
    }
    
    public int getRuleIndex() {
        return 10;
    }
    
    public <T> T accept(final ParseTreeVisitor<? extends T> parseTreeVisitor) {
        if (parseTreeVisitor instanceof GdbVisitor) {
            return ((GdbVisitor<T>)parseTreeVisitor).visitCString(this);
        }
        return (T)parseTreeVisitor.visitChildren((RuleNode)this);
    }
}
