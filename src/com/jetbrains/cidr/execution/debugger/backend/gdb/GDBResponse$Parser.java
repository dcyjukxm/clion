// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.execution.debugger.backend.gdb;

import org.antlr.v4.runtime.tree.ParseTree;
import com.jetbrains.cidr.execution.debugger.CidrDebuggerLog;
import org.antlr.v4.runtime.atn.ATNConfigSet;
import java.util.BitSet;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.misc.NotNull;
import org.antlr.v4.runtime.RecognitionException;
import org.antlr.v4.runtime.misc.Nullable;
import org.antlr.v4.runtime.Recognizer;
import org.antlr.v4.runtime.ANTLRErrorListener;
import org.antlr.v4.runtime.TokenStream;
import org.antlr.v4.runtime.TokenSource;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.ANTLRInputStream;

private static class Parser
{
    String text;
    
    private Parser(final String text) {
        this.text = text;
    }
    
    public GDBResponse parse() throws ResponseParseException {
        final GdbParser gdbParser = new GdbParser((TokenStream)new CommonTokenStream((TokenSource)new GdbLexer((CharStream)new ANTLRInputStream(this.text))));
        gdbParser.removeErrorListeners();
        gdbParser.addErrorListener((ANTLRErrorListener)new ANTLRErrorListener() {
            public void syntaxError(final Recognizer<?, ?> recognizer, @Nullable final Object o, final int n, final int n2, final String s, @Nullable final RecognitionException ex) {
                throw new RuntimeException("Syntax error: " + s + " in \"" + Parser.this.text + "\" line " + n + " position " + n2);
            }
            
            public void reportAmbiguity(@NotNull final org.antlr.v4.runtime.Parser parser, @NotNull final DFA dfa, final int n, final int n2, final boolean b, @NotNull final BitSet set, @NotNull final ATNConfigSet set2) {
                CidrDebuggerLog.LOG.warn("Ambiguity in \"" + Parser.this.text + "\" started " + n + " stopped " + n2);
            }
            
            public void reportAttemptingFullContext(@NotNull final org.antlr.v4.runtime.Parser parser, @NotNull final DFA dfa, final int n, final int n2, @Nullable final BitSet set, @NotNull final ATNConfigSet set2) {
                CidrDebuggerLog.LOG.warn("Attempting full context in \"" + Parser.this.text + "\" started " + n + " stopped " + n2);
            }
            
            public void reportContextSensitivity(@NotNull final org.antlr.v4.runtime.Parser parser, @NotNull final DFA dfa, final int n, final int n2, final int n3, @NotNull final ATNConfigSet set) {
                CidrDebuggerLog.LOG.error("Context sensitivity in \"" + Parser.this.text + "\" started " + n + " stopped " + n2);
            }
        });
        try {
            return (GDBResponse)new GDBResponseVisitor().visit((ParseTree)gdbParser.response());
        }
        catch (Throwable t) {
            throw new ResponseParseException(t.getMessage(), t);
        }
    }
}
