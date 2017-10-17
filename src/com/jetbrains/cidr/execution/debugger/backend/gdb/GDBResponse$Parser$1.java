// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.execution.debugger.backend.gdb;

import com.jetbrains.cidr.execution.debugger.CidrDebuggerLog;
import org.antlr.v4.runtime.atn.ATNConfigSet;
import java.util.BitSet;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.misc.NotNull;
import org.antlr.v4.runtime.Parser;
import org.antlr.v4.runtime.RecognitionException;
import org.antlr.v4.runtime.misc.Nullable;
import org.antlr.v4.runtime.Recognizer;
import org.antlr.v4.runtime.ANTLRErrorListener;

class GDBResponse$Parser$1 implements ANTLRErrorListener {
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
}