// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.execution.debugger.backend.gdb;

import java.util.List;
import org.antlr.v4.runtime.tree.TerminalNode;
import org.antlr.v4.runtime.Token;
import org.antlr.v4.runtime.tree.RuleNode;
import org.antlr.v4.runtime.tree.ParseTreeVisitor;
import org.antlr.v4.runtime.atn.ATNSimulator;
import org.antlr.v4.runtime.NoViableAltException;
import org.antlr.v4.runtime.RecognitionException;
import org.antlr.v4.runtime.ParserRuleContext;
import org.antlr.v4.runtime.atn.ParserATNSimulator;
import org.antlr.v4.runtime.TokenStream;
import org.antlr.v4.runtime.atn.ATN;
import org.antlr.v4.runtime.atn.PredictionContextCache;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.Parser;

public class GdbParser extends Parser
{
    protected static final DFA[] _decisionToDFA;
    protected static final PredictionContextCache _sharedContextCache;
    public static final int T__16 = 1;
    public static final int T__15 = 2;
    public static final int T__14 = 3;
    public static final int T__13 = 4;
    public static final int T__12 = 5;
    public static final int T__11 = 6;
    public static final int T__10 = 7;
    public static final int T__9 = 8;
    public static final int T__8 = 9;
    public static final int T__7 = 10;
    public static final int T__6 = 11;
    public static final int T__5 = 12;
    public static final int T__4 = 13;
    public static final int T__3 = 14;
    public static final int T__2 = 15;
    public static final int T__1 = 16;
    public static final int T__0 = 17;
    public static final int STRING = 18;
    public static final int ID = 19;
    public static final int DIGIT_SEQ = 20;
    public static final int WS = 21;
    public static final String[] tokenNames;
    public static final int RULE_response = 0;
    public static final int RULE_resultRecord = 1;
    public static final int RULE_outOfBandRecord = 2;
    public static final int RULE_asyncRecord = 3;
    public static final int RULE_result = 4;
    public static final int RULE_value = 5;
    public static final int RULE_cnst = 6;
    public static final int RULE_tuple = 7;
    public static final int RULE_list = 8;
    public static final int RULE_streamRecord = 9;
    public static final int RULE_cString = 10;
    public static final String[] ruleNames;
    public static final String _serializedATN = "\u0003\uacf5\uee8c\u4f5d\u8b0d\u4a45\u78bd\u1b2f\u3378\u0003\u0017\u008c\u0004\u0002\t\u0002\u0004\u0003\t\u0003\u0004\u0004\t\u0004\u0004\u0005\t\u0005\u0004\u0006\t\u0006\u0004\u0007\t\u0007\u0004\b\t\b\u0004\t\t\t\u0004\n\t\n\u0004\u000b\t\u000b\u0004\f\t\f\u0003\u0002\u0003\u0002\u0005\u0002\u001b\n\u0002\u0003\u0003\u0005\u0003\u001e\n\u0003\u0003\u0003\u0003\u0003\u0003\u0003\u0003\u0003\u0007\u0003$\n\u0003\f\u0003\u000e\u0003'\u000b\u0003\u0003\u0003\u0005\u0003*\n\u0003\u0003\u0003\u0003\u0003\u0003\u0003\u0005\u0003/\n\u0003\u0003\u0003\u0003\u0003\u0007\u00033\n\u0003\f\u0003\u000e\u00036\u000b\u0003\u0003\u0003\u0005\u00039\n\u0003\u0003\u0004\u0003\u0004\u0005\u0004=\n\u0004\u0003\u0005\u0005\u0005@\n\u0005\u0003\u0005\u0003\u0005\u0003\u0005\u0003\u0005\u0007\u0005F\n\u0005\f\u0005\u000e\u0005I\u000b\u0005\u0003\u0005\u0005\u0005L\n\u0005\u0003\u0005\u0003\u0005\u0003\u0005\u0005\u0005Q\n\u0005\u0003\u0005\u0005\u0005T\n\u0005\u0003\u0005\u0003\u0005\u0007\u0005X\n\u0005\f\u0005\u000e\u0005[\u000b\u0005\u0005\u0005]\n\u0005\u0003\u0006\u0003\u0006\u0003\u0006\u0003\u0006\u0005\u0006c\n\u0006\u0003\u0007\u0003\u0007\u0003\u0007\u0005\u0007h\n\u0007\u0003\b\u0003\b\u0003\t\u0003\t\u0003\t\u0003\t\u0003\t\u0007\tq\n\t\f\t\u000e\tt\u000b\t\u0003\t\u0003\t\u0005\tx\n\t\u0003\n\u0003\n\u0005\n|\n\n\u0003\n\u0003\n\u0007\n\u0080\n\n\f\n\u000e\n\u0083\u000b\n\u0003\n\u0003\n\u0003\u000b\u0003\u000b\u0003\u000b\u0003\f\u0003\f\u0003\f\u0002\r\u0002\u0004\u0006\b\n\f\u000e\u0010\u0012\u0014\u0016\u0002\u0004\u0004\u0002\u0007\u0007\u0011\u0012\u0005\u0002\t\t\f\f\u000f\u000f\u0097\u0002\u001a\u0003\u0002\u0002\u0002\u00048\u0003\u0002\u0002\u0002\u0006<\u0003\u0002\u0002\u0002\b?\u0003\u0002\u0002\u0002\nb\u0003\u0002\u0002\u0002\fg\u0003\u0002\u0002\u0002\u000ei\u0003\u0002\u0002\u0002\u0010w\u0003\u0002\u0002\u0002\u0012y\u0003\u0002\u0002\u0002\u0014\u0086\u0003\u0002\u0002\u0002\u0016\u0089\u0003\u0002\u0002\u0002\u0018\u001b\u0005\u0004\u0003\u0002\u0019\u001b\u0005\u0006\u0004\u0002\u001a\u0018\u0003\u0002\u0002\u0002\u001a\u0019\u0003\u0002\u0002\u0002\u001b\u0003\u0003\u0002\u0002\u0002\u001c\u001e\u0007\u0016\u0002\u0002\u001d\u001c\u0003\u0002\u0002\u0002\u001d\u001e\u0003\u0002\u0002\u0002\u001e\u001f\u0003\u0002\u0002\u0002\u001f \u0007\u000b\u0002\u0002 %\u0007\u0015\u0002\u0002!\"\u0007\u0013\u0002\u0002\"$\u0005\n\u0006\u0002#!\u0003\u0002\u0002\u0002$'\u0003\u0002\u0002\u0002%#\u0003\u0002\u0002\u0002%&\u0003\u0002\u0002\u0002&9\u0003\u0002\u0002\u0002'%\u0003\u0002\u0002\u0002(*\u0007\u0016\u0002\u0002)(\u0003\u0002\u0002\u0002)*\u0003\u0002\u0002\u0002*+\u0003\u0002\u0002\u0002+,\u0007\u000b\u0002\u0002,.\u0007\u0015\u0002\u0002-/\u0005\n\u0006\u0002.-\u0003\u0002\u0002\u0002./\u0003\u0002\u0002\u0002/4\u0003\u0002\u0002\u000201\u0007\u0013\u0002\u000213\u0005\n\u0006\u000220\u0003\u0002\u0002\u000236\u0003\u0002\u0002\u000242\u0003\u0002\u0002\u000245\u0003\u0002\u0002\u000257\u0003\u0002\u0002\u000264\u0003\u0002\u0002\u000279\u0007\b\u0002\u00028\u001d\u0003\u0002\u0002\u00028)\u0003\u0002\u0002\u00029\u0005\u0003\u0002\u0002\u0002:=\u0005\b\u0005\u0002;=\u0005\u0014\u000b\u0002<:\u0003\u0002\u0002\u0002<;\u0003\u0002\u0002\u0002=\u0007\u0003\u0002\u0002\u0002>@\u0007\u0016\u0002\u0002?>\u0003\u0002\u0002\u0002?@\u0003\u0002\u0002\u0002@\\\u0003\u0002\u0002\u0002AB\t\u0002\u0002\u0002BG\u0007\u0015\u0002\u0002CD\u0007\u0013\u0002\u0002DF\u0005\n\u0006\u0002EC\u0003\u0002\u0002\u0002FI\u0003\u0002\u0002\u0002GE\u0003\u0002\u0002\u0002GH\u0003\u0002\u0002\u0002HK\u0003\u0002\u0002\u0002IG\u0003\u0002\u0002\u0002JL\u0007\n\u0002\u0002KJ\u0003\u0002\u0002\u0002KL\u0003\u0002\u0002\u0002L]\u0003\u0002\u0002\u0002MQ\u0007\u000e\u0002\u0002NQ\u0007\u0003\u0002\u0002OQ\u0007\u0010\u0002\u0002PM\u0003\u0002\u0002\u0002PN\u0003\u0002\u0002\u0002PO\u0003\u0002\u0002\u0002QS\u0003\u0002\u0002\u0002RT\u0005\n\u0006\u0002SR\u0003\u0002\u0002\u0002ST\u0003\u0002\u0002\u0002TY\u0003\u0002\u0002\u0002UV\u0007\u0013\u0002\u0002VX\u0005\n\u0006\u0002WU\u0003\u0002\u0002\u0002X[\u0003\u0002\u0002\u0002YW\u0003\u0002\u0002\u0002YZ\u0003\u0002\u0002\u0002Z]\u0003\u0002\u0002\u0002[Y\u0003\u0002\u0002\u0002\\A\u0003\u0002\u0002\u0002\\P\u0003\u0002\u0002\u0002]\t\u0003\u0002\u0002\u0002^_\u0007\u0015\u0002\u0002_`\u0007\u0007\u0002\u0002`c\u0005\f\u0007\u0002ac\u0005\f\u0007\u0002b^\u0003\u0002\u0002\u0002ba\u0003\u0002\u0002\u0002c\u000b\u0003\u0002\u0002\u0002dh\u0005\u000e\b\u0002eh\u0005\u0010\t\u0002fh\u0005\u0012\n\u0002gd\u0003\u0002\u0002\u0002ge\u0003\u0002\u0002\u0002gf\u0003\u0002\u0002\u0002h\r\u0003\u0002\u0002\u0002ij\u0005\u0016\f\u0002j\u000f\u0003\u0002\u0002\u0002kx\u0007\r\u0002\u0002lm\u0007\u0005\u0002\u0002mr\u0005\n\u0006\u0002no\u0007\u0013\u0002\u0002oq\u0005\n\u0006\u0002pn\u0003\u0002\u0002\u0002qt\u0003\u0002\u0002\u0002rp\u0003\u0002\u0002\u0002rs\u0003\u0002\u0002\u0002su\u0003\u0002\u0002\u0002tr\u0003\u0002\u0002\u0002uv\u0007\u0006\u0002\u0002vx\u0003\u0002\u0002\u0002wk\u0003\u0002\u0002\u0002wl\u0003\u0002\u0002\u0002x\u0011\u0003\u0002\u0002\u0002y{\u0007\u0004\u0002\u0002z|\u0005\n\u0006\u0002{z\u0003\u0002\u0002\u0002{|\u0003\u0002\u0002\u0002|\u0081\u0003\u0002\u0002\u0002}~\u0007\u0013\u0002\u0002~\u0080\u0005\n\u0006\u0002\u007f}\u0003\u0002\u0002\u0002\u0080\u0083\u0003\u0002\u0002\u0002\u0081\u007f\u0003\u0002\u0002\u0002\u0081\u0082\u0003\u0002\u0002\u0002\u0082\u0084\u0003\u0002\u0002\u0002\u0083\u0081\u0003\u0002\u0002\u0002\u0084\u0085\u0007\b\u0002\u0002\u0085\u0013\u0003\u0002\u0002\u0002\u0086\u0087\t\u0003\u0002\u0002\u0087\u0088\u0005\u0016\f\u0002\u0088\u0015\u0003\u0002\u0002\u0002\u0089\u008a\u0007\u0014\u0002\u0002\u008a\u0017\u0003\u0002\u0002\u0002\u0017\u001a\u001d%).48<?GKPSY\\bgrw{\u0081";
    public static final ATN _ATN;
    
    public String getGrammarFileName() {
        return "Gdb.g4";
    }
    
    public String[] getTokenNames() {
        return GdbParser.tokenNames;
    }
    
    public String[] getRuleNames() {
        return GdbParser.ruleNames;
    }
    
    public ATN getATN() {
        return GdbParser._ATN;
    }
    
    public GdbParser(final TokenStream tokenStream) {
        super(tokenStream);
        this._interp = (ATNSimulator)new ParserATNSimulator((Parser)this, GdbParser._ATN, GdbParser._decisionToDFA, GdbParser._sharedContextCache);
    }
    
    public final ResponseContext response() throws RecognitionException {
        final ResponseContext responseContext = new ResponseContext(this._ctx, this.getState());
        this.enterRule((ParserRuleContext)responseContext, 0, 0);
        try {
            this.setState(24);
            switch (((ParserATNSimulator)this.getInterpreter()).adaptivePredict(this._input, 0, this._ctx)) {
                case 1: {
                    this.enterOuterAlt((ParserRuleContext)responseContext, 1);
                    this.setState(22);
                    this.resultRecord();
                    break;
                }
                case 2: {
                    this.enterOuterAlt((ParserRuleContext)responseContext, 2);
                    this.setState(23);
                    this.outOfBandRecord();
                    break;
                }
            }
        }
        catch (RecognitionException exception) {
            responseContext.exception = exception;
            this._errHandler.reportError((Parser)this, exception);
            this._errHandler.recover((Parser)this, exception);
        }
        finally {
            this.exitRule();
        }
        return responseContext;
    }
    
    public final ResultRecordContext resultRecord() throws RecognitionException {
        final ResultRecordContext resultRecordContext = new ResultRecordContext(this._ctx, this.getState());
        this.enterRule((ParserRuleContext)resultRecordContext, 2, 1);
        try {
            this.setState(54);
            switch (((ParserATNSimulator)this.getInterpreter()).adaptivePredict(this._input, 6, this._ctx)) {
                case 1: {
                    this.enterOuterAlt((ParserRuleContext)resultRecordContext, 1);
                    this.setState(27);
                    final int la = this._input.LA(1);
                    try {
                        if (la == 20) {
                            this.setState(26);
                            this.match(20);
                        }
                    }
                    catch (RecognitionException ex) {
                        throw b(ex);
                    }
                    this.setState(29);
                    this.match(9);
                    this.setState(30);
                    resultRecordContext.resultClass = this.match(19);
                    this.setState(35);
                    this._errHandler.sync((Parser)this);
                    for (int i = this._input.LA(1); i == 17; i = this._input.LA(1)) {
                        this.setState(31);
                        this.match(17);
                        this.setState(32);
                        this.result();
                        this.setState(37);
                        this._errHandler.sync((Parser)this);
                    }
                    break;
                }
                case 2: {
                    this.enterOuterAlt((ParserRuleContext)resultRecordContext, 2);
                    this.setState(39);
                    final int la2 = this._input.LA(1);
                    try {
                        if (la2 == 20) {
                            this.setState(38);
                            this.match(20);
                        }
                    }
                    catch (RecognitionException ex2) {
                        throw b(ex2);
                    }
                    this.setState(41);
                    this.match(9);
                    this.setState(42);
                    resultRecordContext.resultClass = this.match(19);
                    this.setState(44);
                    final int la3 = this._input.LA(1);
                    Label_0362: {
                        try {
                            if ((la3 & 0xFFFFFFC0) != 0x0) {
                                break Label_0362;
                            }
                            final long n = 1L;
                            final int n2 = la3;
                            final long n3 = n << n2;
                            final long n4 = 788492L;
                            final long n5 = n3 & n4;
                            final long n6 = 0L;
                            final long n7 = lcmp(n5, n6);
                            if (n7 != 0) {
                                break Label_0362;
                            }
                            break Label_0362;
                        }
                        catch (RecognitionException ex3) {
                            throw b(ex3);
                        }
                        try {
                            final long n = 1L;
                            final int n2 = la3;
                            final long n3 = n << n2;
                            final long n4 = 788492L;
                            final long n5 = n3 & n4;
                            final long n6 = 0L;
                            final long n7 = lcmp(n5, n6);
                            if (n7 != 0) {
                                this.setState(43);
                                this.result();
                            }
                        }
                        catch (RecognitionException ex4) {
                            throw b(ex4);
                        }
                    }
                    this.setState(50);
                    this._errHandler.sync((Parser)this);
                    for (int j = this._input.LA(1); j == 17; j = this._input.LA(1)) {
                        this.setState(46);
                        this.match(17);
                        this.setState(47);
                        this.result();
                        this.setState(52);
                        this._errHandler.sync((Parser)this);
                    }
                    this.setState(53);
                    resultRecordContext.listTailToken = this.match(6);
                    break;
                }
            }
        }
        catch (RecognitionException exception) {
            resultRecordContext.exception = exception;
            this._errHandler.reportError((Parser)this, exception);
            this._errHandler.recover((Parser)this, exception);
        }
        finally {
            this.exitRule();
        }
        return resultRecordContext;
    }
    
    public final OutOfBandRecordContext outOfBandRecord() throws RecognitionException {
        final OutOfBandRecordContext outOfBandRecordContext = new OutOfBandRecordContext(this._ctx, this.getState());
        this.enterRule((ParserRuleContext)outOfBandRecordContext, 4, 2);
        try {
            this.setState(58);
            switch (this._input.LA(1)) {
                case 1:
                case 5:
                case 12:
                case 14:
                case 15:
                case 16:
                case 20: {
                    this.enterOuterAlt((ParserRuleContext)outOfBandRecordContext, 1);
                    this.setState(56);
                    this.asyncRecord();
                    break;
                }
                case 7:
                case 10:
                case 13: {
                    this.enterOuterAlt((ParserRuleContext)outOfBandRecordContext, 2);
                    this.setState(57);
                    this.streamRecord();
                    break;
                }
                default: {
                    throw new NoViableAltException((Parser)this);
                }
            }
        }
        catch (RecognitionException exception) {
            outOfBandRecordContext.exception = exception;
            this._errHandler.reportError((Parser)this, exception);
            this._errHandler.recover((Parser)this, exception);
        }
        finally {
            this.exitRule();
        }
        return outOfBandRecordContext;
    }
    
    public final AsyncRecordContext asyncRecord() throws RecognitionException {
        final AsyncRecordContext asyncRecordContext = new AsyncRecordContext(this._ctx, this.getState());
        this.enterRule((ParserRuleContext)asyncRecordContext, 6, 3);
        try {
            this.enterOuterAlt((ParserRuleContext)asyncRecordContext, 1);
            this.setState(61);
            final int la = this._input.LA(1);
            try {
                if (la == 20) {
                    this.setState(60);
                    this.match(20);
                }
            }
            catch (RecognitionException ex) {
                throw b(ex);
            }
            this.setState(90);
            switch (this._input.LA(1)) {
                case 5:
                case 15:
                case 16: {
                    this.setState(63);
                    asyncRecordContext.prefix = this._input.LT(1);
                    final int la2 = this._input.LA(1);
                    Label_0225: {
                        try {
                            if ((la2 & 0xFFFFFFC0) != 0x0) {
                                break Label_0225;
                            }
                            final long n = 1L;
                            final int n2 = la2;
                            final long n3 = n << n2;
                            final long n4 = 98336L;
                            final long n5 = n3 & n4;
                            final long n6 = 0L;
                            final long n7 = lcmp(n5, n6);
                            if (n7 == 0) {
                                break Label_0225;
                            }
                            break Label_0225;
                        }
                        catch (RecognitionException ex2) {
                            throw b(ex2);
                        }
                        try {
                            final long n = 1L;
                            final int n2 = la2;
                            final long n3 = n << n2;
                            final long n4 = 98336L;
                            final long n5 = n3 & n4;
                            final long n6 = 0L;
                            final long n7 = lcmp(n5, n6);
                            if (n7 == 0) {
                                asyncRecordContext.prefix = this._errHandler.recoverInline((Parser)this);
                            }
                        }
                        catch (RecognitionException ex3) {
                            throw b(ex3);
                        }
                    }
                    this.consume();
                    this.setState(64);
                    asyncRecordContext.asyncClass = this.match(19);
                    this.setState(69);
                    this._errHandler.sync((Parser)this);
                    for (int i = this._input.LA(1); i == 17; i = this._input.LA(1)) {
                        this.setState(65);
                        this.match(17);
                        this.setState(66);
                        this.result();
                        this.setState(71);
                        this._errHandler.sync((Parser)this);
                    }
                    this.setState(73);
                    final int la3 = this._input.LA(1);
                    try {
                        if (la3 == 8) {
                            this.setState(72);
                            this.match(8);
                            break;
                        }
                        break;
                    }
                    catch (RecognitionException ex4) {
                        throw b(ex4);
                    }
                }
                case 1:
                case 12:
                case 14: {
                    Label_0517: {
                        Label_0489: {
                            try {
                                this.setState(78);
                                switch (this._input.LA(1)) {
                                    case 12: {
                                        this.setState(75);
                                        asyncRecordContext.quirk = this.match(12);
                                        break Label_0517;
                                    }
                                    case 1: {
                                        break;
                                    }
                                    case 14: {
                                        break Label_0489;
                                    }
                                    default: {
                                        throw new NoViableAltException((Parser)this);
                                    }
                                }
                            }
                            catch (RecognitionException ex5) {
                                throw b(ex5);
                            }
                            this.setState(76);
                            asyncRecordContext.quirk = this.match(1);
                            break Label_0517;
                        }
                        this.setState(77);
                        asyncRecordContext.quirk = this.match(14);
                    }
                    this.setState(81);
                    final int la4 = this._input.LA(1);
                    Label_0560: {
                        try {
                            if ((la4 & 0xFFFFFFC0) != 0x0) {
                                break Label_0560;
                            }
                            final long n8 = 1L;
                            final int n9 = la4;
                            final long n10 = n8 << n9;
                            final long n11 = 788492L;
                            final long n12 = n10 & n11;
                            final long n13 = 0L;
                            final long n14 = lcmp(n12, n13);
                            if (n14 != 0) {
                                break Label_0560;
                            }
                            break Label_0560;
                        }
                        catch (RecognitionException ex6) {
                            throw b(ex6);
                        }
                        try {
                            final long n8 = 1L;
                            final int n9 = la4;
                            final long n10 = n8 << n9;
                            final long n11 = 788492L;
                            final long n12 = n10 & n11;
                            final long n13 = 0L;
                            final long n14 = lcmp(n12, n13);
                            if (n14 != 0) {
                                this.setState(80);
                                this.result();
                            }
                        }
                        catch (RecognitionException ex7) {
                            throw b(ex7);
                        }
                    }
                    this.setState(87);
                    this._errHandler.sync((Parser)this);
                    for (int j = this._input.LA(1); j == 17; j = this._input.LA(1)) {
                        this.setState(83);
                        this.match(17);
                        this.setState(84);
                        this.result();
                        this.setState(89);
                        this._errHandler.sync((Parser)this);
                    }
                    break;
                }
                default: {
                    throw new NoViableAltException((Parser)this);
                }
            }
        }
        catch (RecognitionException exception) {
            asyncRecordContext.exception = exception;
            this._errHandler.reportError((Parser)this, exception);
            this._errHandler.recover((Parser)this, exception);
        }
        finally {
            this.exitRule();
        }
        return asyncRecordContext;
    }
    
    public final ResultContext result() throws RecognitionException {
        final ResultContext resultContext = new ResultContext(this._ctx, this.getState());
        this.enterRule((ParserRuleContext)resultContext, 8, 4);
        try {
            this.setState(96);
            switch (this._input.LA(1)) {
                case 19: {
                    this.enterOuterAlt((ParserRuleContext)resultContext, 1);
                    this.setState(92);
                    resultContext.variable = this.match(19);
                    this.setState(93);
                    this.match(5);
                    this.setState(94);
                    this.value();
                    break;
                }
                case 2:
                case 3:
                case 11:
                case 18: {
                    this.enterOuterAlt((ParserRuleContext)resultContext, 2);
                    this.setState(95);
                    this.value();
                    break;
                }
                default: {
                    throw new NoViableAltException((Parser)this);
                }
            }
        }
        catch (RecognitionException exception) {
            resultContext.exception = exception;
            this._errHandler.reportError((Parser)this, exception);
            this._errHandler.recover((Parser)this, exception);
        }
        finally {
            this.exitRule();
        }
        return resultContext;
    }
    
    public final ValueContext value() throws RecognitionException {
        final ValueContext valueContext = new ValueContext(this._ctx, this.getState());
        this.enterRule((ParserRuleContext)valueContext, 10, 5);
        try {
            this.setState(101);
            switch (this._input.LA(1)) {
                case 18: {
                    this.enterOuterAlt((ParserRuleContext)valueContext, 1);
                    this.setState(98);
                    this.cnst();
                    break;
                }
                case 3:
                case 11: {
                    this.enterOuterAlt((ParserRuleContext)valueContext, 2);
                    this.setState(99);
                    this.tuple();
                    break;
                }
                case 2: {
                    this.enterOuterAlt((ParserRuleContext)valueContext, 3);
                    this.setState(100);
                    this.list();
                    break;
                }
                default: {
                    throw new NoViableAltException((Parser)this);
                }
            }
        }
        catch (RecognitionException exception) {
            valueContext.exception = exception;
            this._errHandler.reportError((Parser)this, exception);
            this._errHandler.recover((Parser)this, exception);
        }
        finally {
            this.exitRule();
        }
        return valueContext;
    }
    
    public final CnstContext cnst() throws RecognitionException {
        final CnstContext cnstContext = new CnstContext(this._ctx, this.getState());
        this.enterRule((ParserRuleContext)cnstContext, 12, 6);
        try {
            this.enterOuterAlt((ParserRuleContext)cnstContext, 1);
            this.setState(103);
            this.cString();
        }
        catch (RecognitionException exception) {
            cnstContext.exception = exception;
            this._errHandler.reportError((Parser)this, exception);
            this._errHandler.recover((Parser)this, exception);
        }
        finally {
            this.exitRule();
        }
        return cnstContext;
    }
    
    public final TupleContext tuple() throws RecognitionException {
        final TupleContext tupleContext = new TupleContext(this._ctx, this.getState());
        this.enterRule((ParserRuleContext)tupleContext, 14, 7);
        try {
            this.setState(117);
            switch (this._input.LA(1)) {
                case 11: {
                    this.enterOuterAlt((ParserRuleContext)tupleContext, 1);
                    this.setState(105);
                    this.match(11);
                    break;
                }
                case 3: {
                    this.enterOuterAlt((ParserRuleContext)tupleContext, 2);
                    this.setState(106);
                    this.match(3);
                    this.setState(107);
                    this.result();
                    this.setState(112);
                    this._errHandler.sync((Parser)this);
                    for (int i = this._input.LA(1); i == 17; i = this._input.LA(1)) {
                        this.setState(108);
                        this.match(17);
                        this.setState(109);
                        this.result();
                        this.setState(114);
                        this._errHandler.sync((Parser)this);
                    }
                    this.setState(115);
                    this.match(4);
                    break;
                }
                default: {
                    throw new NoViableAltException((Parser)this);
                }
            }
        }
        catch (RecognitionException exception) {
            tupleContext.exception = exception;
            this._errHandler.reportError((Parser)this, exception);
            this._errHandler.recover((Parser)this, exception);
        }
        finally {
            this.exitRule();
        }
        return tupleContext;
    }
    
    public final ListContext list() throws RecognitionException {
        final ListContext listContext = new ListContext(this._ctx, this.getState());
        this.enterRule((ParserRuleContext)listContext, 16, 8);
        try {
            this.enterOuterAlt((ParserRuleContext)listContext, 1);
            this.setState(119);
            this.match(2);
            this.setState(121);
            final int la = this._input.LA(1);
            Label_0086: {
                try {
                    if ((la & 0xFFFFFFC0) != 0x0) {
                        break Label_0086;
                    }
                    final long n = 1L;
                    final int n2 = la;
                    final long n3 = n << n2;
                    final long n4 = 788492L;
                    final long n5 = n3 & n4;
                    final long n6 = 0L;
                    final long n7 = lcmp(n5, n6);
                    if (n7 != 0) {
                        break Label_0086;
                    }
                    break Label_0086;
                }
                catch (RecognitionException ex) {
                    throw b(ex);
                }
                try {
                    final long n = 1L;
                    final int n2 = la;
                    final long n3 = n << n2;
                    final long n4 = 788492L;
                    final long n5 = n3 & n4;
                    final long n6 = 0L;
                    final long n7 = lcmp(n5, n6);
                    if (n7 != 0) {
                        this.setState(120);
                        this.result();
                    }
                }
                catch (RecognitionException ex2) {
                    throw b(ex2);
                }
            }
            this.setState(127);
            this._errHandler.sync((Parser)this);
            for (int i = this._input.LA(1); i == 17; i = this._input.LA(1)) {
                this.setState(123);
                this.match(17);
                this.setState(124);
                this.result();
                this.setState(129);
                this._errHandler.sync((Parser)this);
            }
            this.setState(130);
            this.match(6);
        }
        catch (RecognitionException exception) {
            listContext.exception = exception;
            this._errHandler.reportError((Parser)this, exception);
            this._errHandler.recover((Parser)this, exception);
        }
        finally {
            this.exitRule();
        }
        return listContext;
    }
    
    public final StreamRecordContext streamRecord() throws RecognitionException {
        final StreamRecordContext streamRecordContext = new StreamRecordContext(this._ctx, this.getState());
        this.enterRule((ParserRuleContext)streamRecordContext, 18, 9);
        try {
            this.enterOuterAlt((ParserRuleContext)streamRecordContext, 1);
            this.setState(132);
            streamRecordContext.prefix = this._input.LT(1);
            final int la = this._input.LA(1);
            Label_0089: {
                try {
                    if ((la & 0xFFFFFFC0) != 0x0) {
                        break Label_0089;
                    }
                    final long n = 1L;
                    final int n2 = la;
                    final long n3 = n << n2;
                    final long n4 = 9344L;
                    final long n5 = n3 & n4;
                    final long n6 = 0L;
                    final long n7 = lcmp(n5, n6);
                    if (n7 == 0) {
                        break Label_0089;
                    }
                    break Label_0089;
                }
                catch (RecognitionException ex) {
                    throw b(ex);
                }
                try {
                    final long n = 1L;
                    final int n2 = la;
                    final long n3 = n << n2;
                    final long n4 = 9344L;
                    final long n5 = n3 & n4;
                    final long n6 = 0L;
                    final long n7 = lcmp(n5, n6);
                    if (n7 == 0) {
                        streamRecordContext.prefix = this._errHandler.recoverInline((Parser)this);
                    }
                }
                catch (RecognitionException ex2) {
                    throw b(ex2);
                }
            }
            this.consume();
            this.setState(133);
            this.cString();
        }
        catch (RecognitionException exception) {
            streamRecordContext.exception = exception;
            this._errHandler.reportError((Parser)this, exception);
            this._errHandler.recover((Parser)this, exception);
        }
        finally {
            this.exitRule();
        }
        return streamRecordContext;
    }
    
    public final CStringContext cString() throws RecognitionException {
        final CStringContext cStringContext = new CStringContext(this._ctx, this.getState());
        this.enterRule((ParserRuleContext)cStringContext, 20, 10);
        try {
            this.enterOuterAlt((ParserRuleContext)cStringContext, 1);
            this.setState(135);
            this.match(18);
        }
        catch (RecognitionException exception) {
            cStringContext.exception = exception;
            this._errHandler.reportError((Parser)this, exception);
            this._errHandler.recover((Parser)this, exception);
        }
        finally {
            this.exitRule();
        }
        return cStringContext;
    }
    
    static {
        _sharedContextCache = new PredictionContextCache();
        tokenNames = new String[] { "<INVALID>", "'=memory-changed'", "'['", "'{'", "'}'", "'='", "']'", "'~'", "'\\n'", "'^'", "'@'", "'{}'", "'*stopped,changelist=['", "'&'", "'=breakpoint-modified'", "'*'", "'+'", "','", "STRING", "ID", "DIGIT_SEQ", "WS" };
        ruleNames = new String[] { "response", "resultRecord", "outOfBandRecord", "asyncRecord", "result", "value", "cnst", "tuple", "list", "streamRecord", "cString" };
        _ATN = ATNSimulator.deserialize("\u0003\uacf5\uee8c\u4f5d\u8b0d\u4a45\u78bd\u1b2f\u3378\u0003\u0017\u008c\u0004\u0002\t\u0002\u0004\u0003\t\u0003\u0004\u0004\t\u0004\u0004\u0005\t\u0005\u0004\u0006\t\u0006\u0004\u0007\t\u0007\u0004\b\t\b\u0004\t\t\t\u0004\n\t\n\u0004\u000b\t\u000b\u0004\f\t\f\u0003\u0002\u0003\u0002\u0005\u0002\u001b\n\u0002\u0003\u0003\u0005\u0003\u001e\n\u0003\u0003\u0003\u0003\u0003\u0003\u0003\u0003\u0003\u0007\u0003$\n\u0003\f\u0003\u000e\u0003'\u000b\u0003\u0003\u0003\u0005\u0003*\n\u0003\u0003\u0003\u0003\u0003\u0003\u0003\u0005\u0003/\n\u0003\u0003\u0003\u0003\u0003\u0007\u00033\n\u0003\f\u0003\u000e\u00036\u000b\u0003\u0003\u0003\u0005\u00039\n\u0003\u0003\u0004\u0003\u0004\u0005\u0004=\n\u0004\u0003\u0005\u0005\u0005@\n\u0005\u0003\u0005\u0003\u0005\u0003\u0005\u0003\u0005\u0007\u0005F\n\u0005\f\u0005\u000e\u0005I\u000b\u0005\u0003\u0005\u0005\u0005L\n\u0005\u0003\u0005\u0003\u0005\u0003\u0005\u0005\u0005Q\n\u0005\u0003\u0005\u0005\u0005T\n\u0005\u0003\u0005\u0003\u0005\u0007\u0005X\n\u0005\f\u0005\u000e\u0005[\u000b\u0005\u0005\u0005]\n\u0005\u0003\u0006\u0003\u0006\u0003\u0006\u0003\u0006\u0005\u0006c\n\u0006\u0003\u0007\u0003\u0007\u0003\u0007\u0005\u0007h\n\u0007\u0003\b\u0003\b\u0003\t\u0003\t\u0003\t\u0003\t\u0003\t\u0007\tq\n\t\f\t\u000e\tt\u000b\t\u0003\t\u0003\t\u0005\tx\n\t\u0003\n\u0003\n\u0005\n|\n\n\u0003\n\u0003\n\u0007\n\u0080\n\n\f\n\u000e\n\u0083\u000b\n\u0003\n\u0003\n\u0003\u000b\u0003\u000b\u0003\u000b\u0003\f\u0003\f\u0003\f\u0002\r\u0002\u0004\u0006\b\n\f\u000e\u0010\u0012\u0014\u0016\u0002\u0004\u0004\u0002\u0007\u0007\u0011\u0012\u0005\u0002\t\t\f\f\u000f\u000f\u0097\u0002\u001a\u0003\u0002\u0002\u0002\u00048\u0003\u0002\u0002\u0002\u0006<\u0003\u0002\u0002\u0002\b?\u0003\u0002\u0002\u0002\nb\u0003\u0002\u0002\u0002\fg\u0003\u0002\u0002\u0002\u000ei\u0003\u0002\u0002\u0002\u0010w\u0003\u0002\u0002\u0002\u0012y\u0003\u0002\u0002\u0002\u0014\u0086\u0003\u0002\u0002\u0002\u0016\u0089\u0003\u0002\u0002\u0002\u0018\u001b\u0005\u0004\u0003\u0002\u0019\u001b\u0005\u0006\u0004\u0002\u001a\u0018\u0003\u0002\u0002\u0002\u001a\u0019\u0003\u0002\u0002\u0002\u001b\u0003\u0003\u0002\u0002\u0002\u001c\u001e\u0007\u0016\u0002\u0002\u001d\u001c\u0003\u0002\u0002\u0002\u001d\u001e\u0003\u0002\u0002\u0002\u001e\u001f\u0003\u0002\u0002\u0002\u001f \u0007\u000b\u0002\u0002 %\u0007\u0015\u0002\u0002!\"\u0007\u0013\u0002\u0002\"$\u0005\n\u0006\u0002#!\u0003\u0002\u0002\u0002$'\u0003\u0002\u0002\u0002%#\u0003\u0002\u0002\u0002%&\u0003\u0002\u0002\u0002&9\u0003\u0002\u0002\u0002'%\u0003\u0002\u0002\u0002(*\u0007\u0016\u0002\u0002)(\u0003\u0002\u0002\u0002)*\u0003\u0002\u0002\u0002*+\u0003\u0002\u0002\u0002+,\u0007\u000b\u0002\u0002,.\u0007\u0015\u0002\u0002-/\u0005\n\u0006\u0002.-\u0003\u0002\u0002\u0002./\u0003\u0002\u0002\u0002/4\u0003\u0002\u0002\u000201\u0007\u0013\u0002\u000213\u0005\n\u0006\u000220\u0003\u0002\u0002\u000236\u0003\u0002\u0002\u000242\u0003\u0002\u0002\u000245\u0003\u0002\u0002\u000257\u0003\u0002\u0002\u000264\u0003\u0002\u0002\u000279\u0007\b\u0002\u00028\u001d\u0003\u0002\u0002\u00028)\u0003\u0002\u0002\u00029\u0005\u0003\u0002\u0002\u0002:=\u0005\b\u0005\u0002;=\u0005\u0014\u000b\u0002<:\u0003\u0002\u0002\u0002<;\u0003\u0002\u0002\u0002=\u0007\u0003\u0002\u0002\u0002>@\u0007\u0016\u0002\u0002?>\u0003\u0002\u0002\u0002?@\u0003\u0002\u0002\u0002@\\\u0003\u0002\u0002\u0002AB\t\u0002\u0002\u0002BG\u0007\u0015\u0002\u0002CD\u0007\u0013\u0002\u0002DF\u0005\n\u0006\u0002EC\u0003\u0002\u0002\u0002FI\u0003\u0002\u0002\u0002GE\u0003\u0002\u0002\u0002GH\u0003\u0002\u0002\u0002HK\u0003\u0002\u0002\u0002IG\u0003\u0002\u0002\u0002JL\u0007\n\u0002\u0002KJ\u0003\u0002\u0002\u0002KL\u0003\u0002\u0002\u0002L]\u0003\u0002\u0002\u0002MQ\u0007\u000e\u0002\u0002NQ\u0007\u0003\u0002\u0002OQ\u0007\u0010\u0002\u0002PM\u0003\u0002\u0002\u0002PN\u0003\u0002\u0002\u0002PO\u0003\u0002\u0002\u0002QS\u0003\u0002\u0002\u0002RT\u0005\n\u0006\u0002SR\u0003\u0002\u0002\u0002ST\u0003\u0002\u0002\u0002TY\u0003\u0002\u0002\u0002UV\u0007\u0013\u0002\u0002VX\u0005\n\u0006\u0002WU\u0003\u0002\u0002\u0002X[\u0003\u0002\u0002\u0002YW\u0003\u0002\u0002\u0002YZ\u0003\u0002\u0002\u0002Z]\u0003\u0002\u0002\u0002[Y\u0003\u0002\u0002\u0002\\A\u0003\u0002\u0002\u0002\\P\u0003\u0002\u0002\u0002]\t\u0003\u0002\u0002\u0002^_\u0007\u0015\u0002\u0002_`\u0007\u0007\u0002\u0002`c\u0005\f\u0007\u0002ac\u0005\f\u0007\u0002b^\u0003\u0002\u0002\u0002ba\u0003\u0002\u0002\u0002c\u000b\u0003\u0002\u0002\u0002dh\u0005\u000e\b\u0002eh\u0005\u0010\t\u0002fh\u0005\u0012\n\u0002gd\u0003\u0002\u0002\u0002ge\u0003\u0002\u0002\u0002gf\u0003\u0002\u0002\u0002h\r\u0003\u0002\u0002\u0002ij\u0005\u0016\f\u0002j\u000f\u0003\u0002\u0002\u0002kx\u0007\r\u0002\u0002lm\u0007\u0005\u0002\u0002mr\u0005\n\u0006\u0002no\u0007\u0013\u0002\u0002oq\u0005\n\u0006\u0002pn\u0003\u0002\u0002\u0002qt\u0003\u0002\u0002\u0002rp\u0003\u0002\u0002\u0002rs\u0003\u0002\u0002\u0002su\u0003\u0002\u0002\u0002tr\u0003\u0002\u0002\u0002uv\u0007\u0006\u0002\u0002vx\u0003\u0002\u0002\u0002wk\u0003\u0002\u0002\u0002wl\u0003\u0002\u0002\u0002x\u0011\u0003\u0002\u0002\u0002y{\u0007\u0004\u0002\u0002z|\u0005\n\u0006\u0002{z\u0003\u0002\u0002\u0002{|\u0003\u0002\u0002\u0002|\u0081\u0003\u0002\u0002\u0002}~\u0007\u0013\u0002\u0002~\u0080\u0005\n\u0006\u0002\u007f}\u0003\u0002\u0002\u0002\u0080\u0083\u0003\u0002\u0002\u0002\u0081\u007f\u0003\u0002\u0002\u0002\u0081\u0082\u0003\u0002\u0002\u0002\u0082\u0084\u0003\u0002\u0002\u0002\u0083\u0081\u0003\u0002\u0002\u0002\u0084\u0085\u0007\b\u0002\u0002\u0085\u0013\u0003\u0002\u0002\u0002\u0086\u0087\t\u0003\u0002\u0002\u0087\u0088\u0005\u0016\f\u0002\u0088\u0015\u0003\u0002\u0002\u0002\u0089\u008a\u0007\u0014\u0002\u0002\u008a\u0017\u0003\u0002\u0002\u0002\u0017\u001a\u001d%).48<?GKPSY\\bgrw{\u0081".toCharArray());
        _decisionToDFA = new DFA[GdbParser._ATN.getNumberOfDecisions()];
        int i = 0;
        try {
            while (i < GdbParser._ATN.getNumberOfDecisions()) {
                GdbParser._decisionToDFA[i] = new DFA(GdbParser._ATN.getDecisionState(i), i);
                ++i;
            }
        }
        catch (RecognitionException ex) {
            throw b(ex);
        }
    }
    
    private static RecognitionException b(final RecognitionException ex) {
        return ex;
    }
    
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
    
    public static class ResultRecordContext extends ParserRuleContext
    {
        public Token resultClass;
        public Token listTailToken;
        
        public TerminalNode ID() {
            return this.getToken(19, 0);
        }
        
        public List<ResultContext> result() {
            return (List<ResultContext>)this.getRuleContexts((Class)ResultContext.class);
        }
        
        public ResultContext result(final int n) {
            return (ResultContext)this.getRuleContext((Class)ResultContext.class, n);
        }
        
        public TerminalNode DIGIT_SEQ() {
            return this.getToken(20, 0);
        }
        
        public ResultRecordContext(final ParserRuleContext parserRuleContext, final int n) {
            super(parserRuleContext, n);
        }
        
        public int getRuleIndex() {
            return 1;
        }
        
        public <T> T accept(final ParseTreeVisitor<? extends T> parseTreeVisitor) {
            if (parseTreeVisitor instanceof GdbVisitor) {
                return ((GdbVisitor<T>)parseTreeVisitor).visitResultRecord(this);
            }
            return (T)parseTreeVisitor.visitChildren((RuleNode)this);
        }
    }
    
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
    
    public static class AsyncRecordContext extends ParserRuleContext
    {
        public Token prefix;
        public Token asyncClass;
        public Token quirk;
        
        public TerminalNode ID() {
            return this.getToken(19, 0);
        }
        
        public List<ResultContext> result() {
            return (List<ResultContext>)this.getRuleContexts((Class)ResultContext.class);
        }
        
        public ResultContext result(final int n) {
            return (ResultContext)this.getRuleContext((Class)ResultContext.class, n);
        }
        
        public TerminalNode DIGIT_SEQ() {
            return this.getToken(20, 0);
        }
        
        public AsyncRecordContext(final ParserRuleContext parserRuleContext, final int n) {
            super(parserRuleContext, n);
        }
        
        public int getRuleIndex() {
            return 3;
        }
        
        public <T> T accept(final ParseTreeVisitor<? extends T> parseTreeVisitor) {
            if (parseTreeVisitor instanceof GdbVisitor) {
                return ((GdbVisitor<T>)parseTreeVisitor).visitAsyncRecord(this);
            }
            return (T)parseTreeVisitor.visitChildren((RuleNode)this);
        }
    }
    
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
    
    public static class CnstContext extends ParserRuleContext
    {
        public CStringContext cString() {
            return (CStringContext)this.getRuleContext((Class)CStringContext.class, 0);
        }
        
        public CnstContext(final ParserRuleContext parserRuleContext, final int n) {
            super(parserRuleContext, n);
        }
        
        public int getRuleIndex() {
            return 6;
        }
        
        public <T> T accept(final ParseTreeVisitor<? extends T> parseTreeVisitor) {
            if (parseTreeVisitor instanceof GdbVisitor) {
                return ((GdbVisitor<T>)parseTreeVisitor).visitCnst(this);
            }
            return (T)parseTreeVisitor.visitChildren((RuleNode)this);
        }
    }
    
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
    
    public static class ListContext extends ParserRuleContext
    {
        public List<ResultContext> result() {
            return (List<ResultContext>)this.getRuleContexts((Class)ResultContext.class);
        }
        
        public ResultContext result(final int n) {
            return (ResultContext)this.getRuleContext((Class)ResultContext.class, n);
        }
        
        public ListContext(final ParserRuleContext parserRuleContext, final int n) {
            super(parserRuleContext, n);
        }
        
        public int getRuleIndex() {
            return 8;
        }
        
        public <T> T accept(final ParseTreeVisitor<? extends T> parseTreeVisitor) {
            if (parseTreeVisitor instanceof GdbVisitor) {
                return ((GdbVisitor<T>)parseTreeVisitor).visitList(this);
            }
            return (T)parseTreeVisitor.visitChildren((RuleNode)this);
        }
    }
    
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
}
