// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.execution.testing.tcatch;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import javax.xml.parsers.ParserConfigurationException;
import org.xml.sax.SAXParseException;
import java.io.InputStream;
import javax.xml.parsers.SAXParserFactory;
import java.io.PipedInputStream;
import com.intellij.execution.testframework.sm.runner.GeneralIdBasedToSMTRunnerEventsConvertor;
import org.xml.sax.SAXException;
import org.xml.sax.Attributes;
import java.util.HashMap;
import java.util.HashSet;
import org.xml.sax.helpers.DefaultHandler;
import com.intellij.util.ui.UIUtil;
import com.intellij.execution.testframework.sm.runner.ui.SMTRunnerConsoleView;
import com.intellij.execution.impl.ConsoleViewImpl;
import com.intellij.openapi.application.ApplicationManager;
import com.intellij.execution.testframework.sm.runner.GeneralTestEventsProcessor;
import java.util.concurrent.ExecutionException;
import com.intellij.openapi.util.text.StringUtil;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.Nullable;
import java.util.regex.Matcher;
import java.io.IOException;
import java.text.ParseException;
import com.intellij.openapi.util.Key;
import java.util.Iterator;
import com.jetbrains.cidr.execution.testing.CidrTestLog;
import com.intellij.execution.process.ProcessOutputTypes;
import com.intellij.execution.testframework.sm.ServiceMessageBuilder;
import java.util.List;
import com.intellij.execution.testframework.TestConsoleProperties;
import org.jetbrains.annotations.NotNull;
import java.util.Stack;
import java.util.regex.Pattern;
import com.intellij.execution.ui.ExecutionConsole;
import com.jetbrains.cidr.execution.testing.CidrTestEventProcessor;
import jetbrains.buildServer.messages.serviceMessages.ServiceMessageVisitor;
import java.io.PipedOutputStream;
import java.util.concurrent.Future;
import com.intellij.execution.testframework.sm.runner.OutputToGeneralTestEventsConverter;

public class CidrCatchOutputToGeneralTestEventsConverter extends OutputToGeneralTestEventsConverter
{
    private static final String CATCH_TAG = "Catch";
    private static final String STDOUT_TAG = "StdOut";
    private static final String STDERR_TAG = "StdErr";
    private static final String ORIGINAL_TAG = "Original";
    private static final String EXPANDED_TAG = "Expanded";
    private static final String GROUP_TAG = "Group";
    private static final String TEST_CASE_TAG = "TestCase";
    private static final String SECTION_TAG = "Section";
    private static final String OVERALL_RESULT_TAG = "OverallResult";
    private static final String OVERALL_RESULTS_TAG = "OverallResults";
    private static final String EXPRESSION_TAG = "Expression";
    private static final String INFO_TAG = "Info";
    private static final String WARNING_TAG = "Warning";
    private static final String FAILURE_TAG = "Failure";
    private static final String FATAL_ERROR_CONDITION_TAG = "FatalErrorCondition";
    private static final String EXCEPTION_TAG = "Exception";
    private static final String NAME_ATTR = "name";
    private static final String TAGS_ATTR = "tags";
    private static final String DURATION_IN_SECONDS_ATTR = "durationInSeconds";
    private static final String SUCCESS_ATTR = "success";
    private static final String SUCCESSES_ATTR = "successes";
    private static final String FAILURES_ATTR = "failures";
    private static final String DESCRIPTION_ATTR = "description";
    private static final String EXPECTED_FAILURES_ATTR = "expectedFailures";
    private static final String FILE_NAME_ATTR = "filename";
    private static final String LINE_ATTR = "line";
    private static final String TYPE_ATTR = "type";
    private static final String OUT_INDENT = "  ";
    private static final String[] ALL_TAGS;
    private static final String[] ALL_ATTRS;
    private volatile Future<Boolean> myXMLParser;
    private volatile PipedOutputStream myPipedOutStream;
    private volatile ServiceMessageVisitor myVisitor;
    private final CidrTestEventProcessor myEventProcessor;
    private volatile boolean myXMLStarted;
    private volatile boolean myXMLParserInAction;
    private volatile String myProcessFinishedMessage;
    private volatile ExecutionConsole myConsole;
    private static final String TAGS;
    private static final Pattern OPEN_TAG;
    private static final Pattern CLOSE_TAG;
    private final Stack<Boolean> myPrintfHolderStack;
    
    public CidrCatchOutputToGeneralTestEventsConverter(@NotNull final String s, @NotNull final TestConsoleProperties testConsoleProperties, @NotNull final ExecutionConsole myConsole) {
        if (s == null) {
            throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "testFrameworkName", "com/jetbrains/cidr/execution/testing/tcatch/CidrCatchOutputToGeneralTestEventsConverter", "<init>"));
        }
        if (testConsoleProperties == null) {
            throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "consoleProperties", "com/jetbrains/cidr/execution/testing/tcatch/CidrCatchOutputToGeneralTestEventsConverter", "<init>"));
        }
        if (myConsole == null) {
            throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "console", "com/jetbrains/cidr/execution/testing/tcatch/CidrCatchOutputToGeneralTestEventsConverter", "<init>"));
        }
        super(s, testConsoleProperties);
        this.myXMLStarted = false;
        this.myXMLParserInAction = true;
        this.myPrintfHolderStack = new Stack<Boolean>();
        this.myConsole = myConsole;
        this.myEventProcessor = new CidrTestEventProcessor("catch");
    }
    
    protected void process(@NotNull final List<ServiceMessageBuilder> list) {
        try {
            if (list == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "messages", "com/jetbrains/cidr/execution/testing/tcatch/CidrCatchOutputToGeneralTestEventsConverter", "process"));
            }
        }
        catch (Exception ex) {
            throw c(ex);
        }
        try {
            final Iterator<ServiceMessageBuilder> iterator = list.iterator();
            while (iterator.hasNext()) {
                super.processServiceMessages(iterator.next().toString(), ProcessOutputTypes.STDOUT, this.myVisitor);
            }
        }
        catch (Exception ex2) {
            CidrTestLog.LOG.error(ex2.getMessage());
        }
    }
    
    @Override
    protected boolean processServiceMessages(final String p0, final Key p1, final ServiceMessageVisitor p2) throws ParseException {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     0: aload_1        
        //     1: aload_2        
        //     2: invokestatic    com/jetbrains/cidr/execution/testing/CidrOutputToGeneralTestEventsConverter.logOutput:(Ljava/lang/String;Lcom/intellij/openapi/util/Key;)V
        //     5: aload_0        
        //     6: aload_3        
        //     7: putfield        com/jetbrains/cidr/execution/testing/tcatch/CidrCatchOutputToGeneralTestEventsConverter.myVisitor:Ljetbrains/buildServer/messages/serviceMessages/ServiceMessageVisitor;
        //    10: aload_0        
        //    11: getfield        com/jetbrains/cidr/execution/testing/tcatch/CidrCatchOutputToGeneralTestEventsConverter.myXMLStarted:Z
        //    14: ifne            43
        //    17: aload_1        
        //    18: invokestatic    com/jetbrains/cidr/execution/testing/tcatch/CidrCatchOutputToGeneralTestEventsConverter.f:(Ljava/lang/String;)Z
        //    21: ifeq            43
        //    24: goto            31
        //    27: invokestatic    com/jetbrains/cidr/execution/testing/tcatch/CidrCatchOutputToGeneralTestEventsConverter.c:(Ljava/lang/Exception;)Ljava/lang/Exception;
        //    30: athrow         
        //    31: aload_0        
        //    32: iconst_1       
        //    33: putfield        com/jetbrains/cidr/execution/testing/tcatch/CidrCatchOutputToGeneralTestEventsConverter.myXMLStarted:Z
        //    36: goto            43
        //    39: invokestatic    com/jetbrains/cidr/execution/testing/tcatch/CidrCatchOutputToGeneralTestEventsConverter.c:(Ljava/lang/Exception;)Ljava/lang/Exception;
        //    42: athrow         
        //    43: aload_2        
        //    44: getstatic       com/intellij/execution/process/ProcessOutputTypes.STDOUT:Lcom/intellij/openapi/util/Key;
        //    47: if_acmpne       154
        //    50: aload_0        
        //    51: getfield        com/jetbrains/cidr/execution/testing/tcatch/CidrCatchOutputToGeneralTestEventsConverter.myXMLStarted:Z
        //    54: ifeq            154
        //    57: goto            64
        //    60: invokestatic    com/jetbrains/cidr/execution/testing/tcatch/CidrCatchOutputToGeneralTestEventsConverter.c:(Ljava/lang/Exception;)Ljava/lang/Exception;
        //    63: athrow         
        //    64: aload_0        
        //    65: getfield        com/jetbrains/cidr/execution/testing/tcatch/CidrCatchOutputToGeneralTestEventsConverter.myXMLParserInAction:Z
        //    68: ifeq            154
        //    71: goto            78
        //    74: invokestatic    com/jetbrains/cidr/execution/testing/tcatch/CidrCatchOutputToGeneralTestEventsConverter.c:(Ljava/lang/Exception;)Ljava/lang/Exception;
        //    77: athrow         
        //    78: aload_0        
        //    79: getfield        com/jetbrains/cidr/execution/testing/tcatch/CidrCatchOutputToGeneralTestEventsConverter.myXMLParser:Ljava/util/concurrent/Future;
        //    82: ifnonnull       129
        //    85: aload_0        
        //    86: new             Ljava/io/PipedOutputStream;
        //    89: dup            
        //    90: invokespecial   java/io/PipedOutputStream.<init>:()V
        //    93: putfield        com/jetbrains/cidr/execution/testing/tcatch/CidrCatchOutputToGeneralTestEventsConverter.myPipedOutStream:Ljava/io/PipedOutputStream;
        //    96: new             Ljava/io/PipedInputStream;
        //    99: dup            
        //   100: aload_0        
        //   101: getfield        com/jetbrains/cidr/execution/testing/tcatch/CidrCatchOutputToGeneralTestEventsConverter.myPipedOutStream:Ljava/io/PipedOutputStream;
        //   104: invokespecial   java/io/PipedInputStream.<init>:(Ljava/io/PipedOutputStream;)V
        //   107: astore          4
        //   109: aload_0        
        //   110: invokestatic    com/intellij/openapi/application/ApplicationManager.getApplication:()Lcom/intellij/openapi/application/Application;
        //   113: aload_0        
        //   114: aload           4
        //   116: invokedynamic   call:(Lcom/jetbrains/cidr/execution/testing/tcatch/CidrCatchOutputToGeneralTestEventsConverter;Ljava/io/PipedInputStream;)Ljava/util/concurrent/Callable;
        //   121: invokeinterface com/intellij/openapi/application/Application.executeOnPooledThread:(Ljava/util/concurrent/Callable;)Ljava/util/concurrent/Future;
        //   126: putfield        com/jetbrains/cidr/execution/testing/tcatch/CidrCatchOutputToGeneralTestEventsConverter.myXMLParser:Ljava/util/concurrent/Future;
        //   129: aload_0        
        //   130: aload_1        
        //   131: invokespecial   com/jetbrains/cidr/execution/testing/tcatch/CidrCatchOutputToGeneralTestEventsConverter.c:(Ljava/lang/String;)V
        //   134: iconst_1       
        //   135: ireturn        
        //   136: astore          4
        //   138: aload_0        
        //   139: iconst_0       
        //   140: putfield        com/jetbrains/cidr/execution/testing/tcatch/CidrCatchOutputToGeneralTestEventsConverter.myXMLParserInAction:Z
        //   143: getstatic       com/jetbrains/cidr/execution/testing/CidrTestLog.LOG:Lcom/intellij/openapi/diagnostic/Logger;
        //   146: aload           4
        //   148: invokevirtual   com/intellij/openapi/diagnostic/Logger.error:(Ljava/lang/Throwable;)V
        //   151: goto            207
        //   154: aload_2        
        //   155: getstatic       com/intellij/execution/process/ProcessOutputTypes.SYSTEM:Lcom/intellij/openapi/util/Key;
        //   158: if_acmpne       207
        //   161: getstatic       com/jetbrains/cidr/execution/testing/CidrOutputToGeneralTestEventsConverter.PROCESS_FINISHED_PATTERN:Ljava/util/regex/Pattern;
        //   164: aload_1        
        //   165: invokevirtual   java/util/regex/Pattern.matcher:(Ljava/lang/CharSequence;)Ljava/util/regex/Matcher;
        //   168: invokevirtual   java/util/regex/Matcher.matches:()Z
        //   171: ifeq            192
        //   174: goto            181
        //   177: invokestatic    com/jetbrains/cidr/execution/testing/tcatch/CidrCatchOutputToGeneralTestEventsConverter.c:(Ljava/lang/Exception;)Ljava/lang/Exception;
        //   180: athrow         
        //   181: aload_0        
        //   182: aload_1        
        //   183: putfield        com/jetbrains/cidr/execution/testing/tcatch/CidrCatchOutputToGeneralTestEventsConverter.myProcessFinishedMessage:Ljava/lang/String;
        //   186: iconst_1       
        //   187: ireturn        
        //   188: invokestatic    com/jetbrains/cidr/execution/testing/tcatch/CidrCatchOutputToGeneralTestEventsConverter.c:(Ljava/lang/Exception;)Ljava/lang/Exception;
        //   191: athrow         
        //   192: aload_1        
        //   193: ldc             "\n"
        //   195: invokevirtual   java/lang/String.equals:(Ljava/lang/Object;)Z
        //   198: ifeq            207
        //   201: iconst_1       
        //   202: ireturn        
        //   203: invokestatic    com/jetbrains/cidr/execution/testing/tcatch/CidrCatchOutputToGeneralTestEventsConverter.c:(Ljava/lang/Exception;)Ljava/lang/Exception;
        //   206: athrow         
        //   207: iconst_0       
        //   208: ireturn        
        //    Exceptions:
        //  throws java.text.ParseException
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                 
        //  -----  -----  -----  -----  ---------------------
        //  50     71     74     78     Ljava/io/IOException;
        //  43     57     60     64     Ljava/io/IOException;
        //  17     36     39     43     Ljava/io/IOException;
        //  0      24     27     31     Ljava/io/IOException;
        //  78     135    136    154    Ljava/io/IOException;
        //  154    174    177    181    Ljava/io/IOException;
        //  161    188    188    192    Ljava/io/IOException;
        //  192    203    203    207    Ljava/io/IOException;
        // 
        // The error that occurred was:
        // 
        // java.lang.IllegalStateException: Could not infer any expression.
        //     at com.strobel.decompiler.ast.TypeAnalysis.runInference(TypeAnalysis.java:374)
        //     at com.strobel.decompiler.ast.TypeAnalysis.run(TypeAnalysis.java:96)
        //     at com.strobel.decompiler.ast.AstOptimizer.optimize(AstOptimizer.java:344)
        //     at com.strobel.decompiler.ast.AstOptimizer.optimize(AstOptimizer.java:42)
        //     at com.strobel.decompiler.languages.java.ast.AstMethodBodyBuilder.createMethodBody(AstMethodBodyBuilder.java:214)
        //     at com.strobel.decompiler.languages.java.ast.AstMethodBodyBuilder.createMethodBody(AstMethodBodyBuilder.java:99)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createMethodBody(AstBuilder.java:757)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createMethod(AstBuilder.java:655)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.addTypeMembers(AstBuilder.java:532)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createTypeCore(AstBuilder.java:499)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createTypeNoCache(AstBuilder.java:141)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createType(AstBuilder.java:130)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.addType(AstBuilder.java:105)
        //     at com.strobel.decompiler.languages.java.JavaLanguage.buildAst(JavaLanguage.java:71)
        //     at com.strobel.decompiler.languages.java.JavaLanguage.decompileType(JavaLanguage.java:59)
        //     at com.strobel.decompiler.DecompilerDriver.decompileType(DecompilerDriver.java:317)
        //     at com.strobel.decompiler.DecompilerDriver.main(DecompilerDriver.java:141)
        // 
        throw new IllegalStateException("An error occurred while decompiling this method.");
    }
    
    private void c(final String s) throws IOException {
        Label_0115: {
            try {
                if (f(s)) {
                    this.e(s);
                    break Label_0115;
                }
            }
            catch (IOException ex) {
                throw c(ex);
            }
            boolean booleanValue = false;
            Label_0050: {
                try {
                    if (this.myPrintfHolderStack.size() == 0) {
                        booleanValue = false;
                        break Label_0050;
                    }
                }
                catch (IOException ex2) {
                    throw c(ex2);
                }
                booleanValue = this.myPrintfHolderStack.peek();
            }
            final boolean b = booleanValue;
            final Matcher matcher = CidrCatchOutputToGeneralTestEventsConverter.OPEN_TAG.matcher(s);
            try {
                if (matcher.matches()) {
                    this.a(b, true, matcher);
                    break Label_0115;
                }
            }
            catch (IOException ex3) {
                throw c(ex3);
            }
            final Matcher matcher2 = CidrCatchOutputToGeneralTestEventsConverter.CLOSE_TAG.matcher(s);
            try {
                if (matcher2.matches()) {
                    this.a(b, false, matcher2);
                    break Label_0115;
                }
            }
            catch (IOException ex4) {
                throw c(ex4);
            }
            this.a(b, s);
        }
        this.myPipedOutStream.flush();
    }
    
    @Contract(pure = true)
    private static boolean f(@Nullable final String s) {
        Label_0020: {
            try {
                if (s == null) {
                    return false;
                }
                final String s2 = s;
                final String s3 = "<?xml version=";
                final boolean b = s2.startsWith(s3);
                if (b) {
                    break Label_0020;
                }
                return false;
            }
            catch (IllegalArgumentException ex) {
                throw c(ex);
            }
            try {
                final String s2 = s;
                final String s3 = "<?xml version=";
                final boolean b = s2.startsWith(s3);
                if (b) {
                    return true;
                }
            }
            catch (IllegalArgumentException ex2) {
                throw c(ex2);
            }
        }
        return false;
    }
    
    private void a(final boolean b, final boolean b2, final Matcher matcher) throws IOException {
        final String group = matcher.group(1);
        final String group2 = matcher.group(2);
        final String group3 = matcher.group(3);
        Label_0066: {
            try {
                this.a(b, group.trim());
                if (b2) {
                    this.myPrintfHolderStack.push(d(group3));
                    break Label_0066;
                }
            }
            catch (IOException ex) {
                throw c(ex);
            }
            this.myPrintfHolderStack.pop();
        }
        this.e(group2);
    }
    
    private void a(final boolean b, @NotNull final String s) throws IOException {
        try {
            if (s == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "freeText", "com/jetbrains/cidr/execution/testing/tcatch/CidrCatchOutputToGeneralTestEventsConverter", "processFreeText"));
            }
        }
        catch (IOException ex) {
            throw c(ex);
        }
        try {
            if (s.length() == 0) {
                return;
            }
        }
        catch (IOException ex2) {
            throw c(ex2);
        }
        String format = null;
        Label_0095: {
            try {
                if (b) {
                    format = String.format("<%s>%s</%s>", "StdOut", StringUtil.escapeXml(s), "StdOut");
                    break Label_0095;
                }
            }
            catch (IOException ex3) {
                throw c(ex3);
            }
            format = s;
        }
        this.e(format);
    }
    
    private static boolean d(@NotNull final String s) {
        try {
            if (s == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "tag", "com/jetbrains/cidr/execution/testing/tcatch/CidrCatchOutputToGeneralTestEventsConverter", "isPrintfHolder"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw c(ex);
        }
        Label_0069: {
            try {
                if ("TestCase".equals(s)) {
                    break Label_0069;
                }
                final String s2 = "Section";
                final String s3 = s;
                final boolean b = s2.equals(s3);
                if (b) {
                    break Label_0069;
                }
                return false;
            }
            catch (IllegalArgumentException ex2) {
                throw c(ex2);
            }
            try {
                final String s2 = "Section";
                final String s3 = s;
                final boolean b = s2.equals(s3);
                if (b) {
                    return true;
                }
            }
            catch (IllegalArgumentException ex3) {
                throw c(ex3);
            }
        }
        return false;
    }
    
    private void e(final String s) throws IOException {
        this.myPipedOutStream.write(s.getBytes("UTF-8"));
    }
    
    @Override
    public void flushBufferBeforeTerminating() {
        CidrTestLog.LOG.debug("flushBufferBeforeTerminating");
        if (this.myPipedOutStream != null) {
            try {
                this.myPipedOutStream.close();
            }
            catch (IOException ex) {
                CidrTestLog.LOG.debug((Throwable)ex);
            }
            finally {
                this.myPipedOutStream = null;
            }
        }
        if (this.myXMLParser != null) {
            try {
                final Boolean b = this.myXMLParser.get();
                try {
                    if (CidrTestLog.LOG.isDebugEnabled()) {
                        CidrTestLog.LOG.debug("parsing finished:" + b.toString());
                    }
                }
                catch (IOException ex2) {
                    throw c(ex2);
                }
                final GeneralTestEventsProcessor processor = this.getProcessor();
                Label_0141: {
                    try {
                        if (processor == null) {
                            break Label_0141;
                        }
                        final CidrCatchOutputToGeneralTestEventsConverter cidrCatchOutputToGeneralTestEventsConverter = this;
                        final String s = cidrCatchOutputToGeneralTestEventsConverter.myProcessFinishedMessage;
                        if (s != null) {
                            break Label_0141;
                        }
                        break Label_0141;
                    }
                    catch (IOException ex3) {
                        throw c(ex3);
                    }
                    try {
                        final CidrCatchOutputToGeneralTestEventsConverter cidrCatchOutputToGeneralTestEventsConverter = this;
                        final String s = cidrCatchOutputToGeneralTestEventsConverter.myProcessFinishedMessage;
                        if (s != null) {
                            processor.onUncapturedOutput(this.myProcessFinishedMessage, ProcessOutputTypes.SYSTEM);
                        }
                    }
                    catch (IOException ex4) {
                        throw c(ex4);
                    }
                }
            }
            catch (InterruptedException ex6) {}
            catch (ExecutionException ex5) {
                CidrTestLog.LOG.error((Throwable)ex5);
            }
            finally {
                this.myXMLParser = null;
                this.myXMLParserInAction = false;
            }
        }
        this.g();
        super.flushBufferBeforeTerminating();
    }
    
    private void g() {
        try {
            if (ApplicationManager.getApplication().isUnitTestMode()) {
                UIUtil.invokeAndWaitIfNeeded(() -> ((ConsoleViewImpl)((SMTRunnerConsoleView)this.myConsole).getConsole()).waitAllRequests());
            }
        }
        catch (IllegalArgumentException ex) {
            throw c(ex);
        }
    }
    
    @NotNull
    @Contract(pure = true)
    private DefaultHandler i() {
        DefaultHandler defaultHandler;
        try {
            defaultHandler = new DefaultHandler() {
                static final String ROOT_NODE_ID = "0";
                final Stack<String> myTestNameStack = new Stack<String>();
                final HashSet<String> myTestIds = new HashSet<String>();
                final HashMap<String, TestEnd> myTestResults = new HashMap<String, TestEnd>();
                final StringBuilder myInfoMessageWithNewLineAndIndent = new StringBuilder();
                String myExpressionPrefix;
                final StringBuilder myText = new StringBuilder();
                boolean mySuccess;
                int myDuration;
                final Stack<String> mySectionsEndIdStack = new Stack<String>();
                static final /* synthetic */ boolean $assertionsDisabled;
                
                @Override
                public void startElement(final String p0, final String p1, final String p2, final Attributes p3) throws SAXException {
                    // 
                    // This method could not be decompiled.
                    // 
                    // Original Bytecode:
                    // 
                    //     0: aload_0        
                    //     1: getfield        com/jetbrains/cidr/execution/testing/tcatch/CidrCatchOutputToGeneralTestEventsConverter$1.this$0:Lcom/jetbrains/cidr/execution/testing/tcatch/CidrCatchOutputToGeneralTestEventsConverter;
                    //     4: invokestatic    com/jetbrains/cidr/execution/testing/tcatch/CidrCatchOutputToGeneralTestEventsConverter.access$000:(Lcom/jetbrains/cidr/execution/testing/tcatch/CidrCatchOutputToGeneralTestEventsConverter;)Lcom/intellij/execution/testframework/sm/runner/GeneralTestEventsProcessor;
                    //     7: astore          5
                    //     9: aload           5
                    //    11: ifnonnull       27
                    //    14: getstatic       com/jetbrains/cidr/execution/testing/CidrTestLog.LOG:Lcom/intellij/openapi/diagnostic/Logger;
                    //    17: ldc             "startElement: processor was disposed"
                    //    19: invokevirtual   com/intellij/openapi/diagnostic/Logger.debug:(Ljava/lang/String;)V
                    //    22: return         
                    //    23: invokestatic    com/jetbrains/cidr/execution/testing/tcatch/CidrCatchOutputToGeneralTestEventsConverter$1.b:(Ljava/lang/Exception;)Ljava/lang/Exception;
                    //    26: athrow         
                    //    27: aload_0        
                    //    28: getfield        com/jetbrains/cidr/execution/testing/tcatch/CidrCatchOutputToGeneralTestEventsConverter$1.myText:Ljava/lang/StringBuilder;
                    //    31: iconst_0       
                    //    32: invokevirtual   java/lang/StringBuilder.setLength:(I)V
                    //    35: getstatic       com/jetbrains/cidr/execution/testing/CidrTestLog.LOG:Lcom/intellij/openapi/diagnostic/Logger;
                    //    38: invokevirtual   com/intellij/openapi/diagnostic/Logger.isDebugEnabled:()Z
                    //    41: ifeq            76
                    //    44: getstatic       com/jetbrains/cidr/execution/testing/CidrTestLog.LOG:Lcom/intellij/openapi/diagnostic/Logger;
                    //    47: new             Ljava/lang/StringBuilder;
                    //    50: dup            
                    //    51: invokespecial   java/lang/StringBuilder.<init>:()V
                    //    54: ldc             "{startElement"
                    //    56: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
                    //    59: aload_3        
                    //    60: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
                    //    63: invokevirtual   java/lang/StringBuilder.toString:()Ljava/lang/String;
                    //    66: invokevirtual   com/intellij/openapi/diagnostic/Logger.debug:(Ljava/lang/String;)V
                    //    69: goto            76
                    //    72: invokestatic    com/jetbrains/cidr/execution/testing/tcatch/CidrCatchOutputToGeneralTestEventsConverter$1.b:(Ljava/lang/Exception;)Ljava/lang/Exception;
                    //    75: athrow         
                    //    76: ldc             "Group"
                    //    78: aload_3        
                    //    79: invokevirtual   java/lang/String.equals:(Ljava/lang/Object;)Z
                    //    82: ifeq            107
                    //    85: aload           5
                    //    87: invokevirtual   com/intellij/execution/testframework/sm/runner/GeneralTestEventsProcessor.onTestsReporterAttached:()V
                    //    90: aload_0        
                    //    91: getfield        com/jetbrains/cidr/execution/testing/tcatch/CidrCatchOutputToGeneralTestEventsConverter$1.myTestIds:Ljava/util/HashSet;
                    //    94: ldc             "0"
                    //    96: invokevirtual   java/util/HashSet.add:(Ljava/lang/Object;)Z
                    //    99: pop            
                    //   100: goto            485
                    //   103: invokestatic    com/jetbrains/cidr/execution/testing/tcatch/CidrCatchOutputToGeneralTestEventsConverter$1.b:(Ljava/lang/Exception;)Ljava/lang/Exception;
                    //   106: athrow         
                    //   107: ldc             "TestCase"
                    //   109: aload_3        
                    //   110: invokevirtual   java/lang/String.equals:(Ljava/lang/Object;)Z
                    //   113: ifne            132
                    //   116: ldc             "Section"
                    //   118: aload_3        
                    //   119: invokevirtual   java/lang/String.equals:(Ljava/lang/Object;)Z
                    //   122: ifeq            147
                    //   125: goto            132
                    //   128: invokestatic    com/jetbrains/cidr/execution/testing/tcatch/CidrCatchOutputToGeneralTestEventsConverter$1.b:(Ljava/lang/Exception;)Ljava/lang/Exception;
                    //   131: athrow         
                    //   132: aload_0        
                    //   133: aload           5
                    //   135: aload           4
                    //   137: invokespecial   com/jetbrains/cidr/execution/testing/tcatch/CidrCatchOutputToGeneralTestEventsConverter$1.a:(Lcom/intellij/execution/testframework/sm/runner/GeneralTestEventsProcessor;Lorg/xml/sax/Attributes;)V
                    //   140: goto            485
                    //   143: invokestatic    com/jetbrains/cidr/execution/testing/tcatch/CidrCatchOutputToGeneralTestEventsConverter$1.b:(Ljava/lang/Exception;)Ljava/lang/Exception;
                    //   146: athrow         
                    //   147: ldc             "OverallResult"
                    //   149: aload_3        
                    //   150: invokevirtual   java/lang/String.equals:(Ljava/lang/Object;)Z
                    //   153: ifeq            217
                    //   156: aload           4
                    //   158: ldc             "durationInSeconds"
                    //   160: invokeinterface org/xml/sax/Attributes.getValue:(Ljava/lang/String;)Ljava/lang/String;
                    //   165: astore          6
                    //   167: aload           6
                    //   169: ifnull          196
                    //   172: aload_0        
                    //   173: aload           6
                    //   175: invokestatic    java/lang/Double.parseDouble:(Ljava/lang/String;)D
                    //   178: ldc2_w          1000.0
                    //   181: dmul           
                    //   182: invokestatic    java/lang/Math.round:(D)J
                    //   185: l2i            
                    //   186: putfield        com/jetbrains/cidr/execution/testing/tcatch/CidrCatchOutputToGeneralTestEventsConverter$1.myDuration:I
                    //   189: goto            196
                    //   192: invokestatic    com/jetbrains/cidr/execution/testing/tcatch/CidrCatchOutputToGeneralTestEventsConverter$1.b:(Ljava/lang/Exception;)Ljava/lang/Exception;
                    //   195: athrow         
                    //   196: aload_0        
                    //   197: ldc             "true"
                    //   199: aload           4
                    //   201: ldc             "success"
                    //   203: invokeinterface org/xml/sax/Attributes.getValue:(Ljava/lang/String;)Ljava/lang/String;
                    //   208: invokevirtual   java/lang/String.equals:(Ljava/lang/Object;)Z
                    //   211: putfield        com/jetbrains/cidr/execution/testing/tcatch/CidrCatchOutputToGeneralTestEventsConverter$1.mySuccess:Z
                    //   214: goto            485
                    //   217: ldc             "OverallResults"
                    //   219: aload_3        
                    //   220: invokevirtual   java/lang/String.equals:(Ljava/lang/Object;)Z
                    //   223: ifeq            344
                    //   226: aload           4
                    //   228: ldc             "durationInSeconds"
                    //   230: invokeinterface org/xml/sax/Attributes.getValue:(Ljava/lang/String;)Ljava/lang/String;
                    //   235: astore          6
                    //   237: aload           6
                    //   239: ifnull          266
                    //   242: aload_0        
                    //   243: aload           6
                    //   245: invokestatic    java/lang/Double.parseDouble:(Ljava/lang/String;)D
                    //   248: ldc2_w          1000.0
                    //   251: dmul           
                    //   252: invokestatic    java/lang/Math.round:(D)J
                    //   255: l2i            
                    //   256: putfield        com/jetbrains/cidr/execution/testing/tcatch/CidrCatchOutputToGeneralTestEventsConverter$1.myDuration:I
                    //   259: goto            266
                    //   262: invokestatic    com/jetbrains/cidr/execution/testing/tcatch/CidrCatchOutputToGeneralTestEventsConverter$1.b:(Ljava/lang/Exception;)Ljava/lang/Exception;
                    //   265: athrow         
                    //   266: aload_0        
                    //   267: aload           4
                    //   269: ldc             "failures"
                    //   271: invokeinterface org/xml/sax/Attributes.getValue:(Ljava/lang/String;)Ljava/lang/String;
                    //   276: ifnull          337
                    //   279: aload           4
                    //   281: ldc             "expectedFailures"
                    //   283: invokeinterface org/xml/sax/Attributes.getValue:(Ljava/lang/String;)Ljava/lang/String;
                    //   288: ifnull          337
                    //   291: goto            298
                    //   294: invokestatic    com/jetbrains/cidr/execution/testing/tcatch/CidrCatchOutputToGeneralTestEventsConverter$1.b:(Ljava/lang/Exception;)Ljava/lang/Exception;
                    //   297: athrow         
                    //   298: aload           4
                    //   300: ldc             "failures"
                    //   302: invokeinterface org/xml/sax/Attributes.getValue:(Ljava/lang/String;)Ljava/lang/String;
                    //   307: aload           4
                    //   309: ldc             "expectedFailures"
                    //   311: invokeinterface org/xml/sax/Attributes.getValue:(Ljava/lang/String;)Ljava/lang/String;
                    //   316: invokevirtual   java/lang/String.equals:(Ljava/lang/Object;)Z
                    //   319: ifeq            337
                    //   322: goto            329
                    //   325: invokestatic    com/jetbrains/cidr/execution/testing/tcatch/CidrCatchOutputToGeneralTestEventsConverter$1.b:(Ljava/lang/Exception;)Ljava/lang/Exception;
                    //   328: athrow         
                    //   329: iconst_1       
                    //   330: goto            338
                    //   333: invokestatic    com/jetbrains/cidr/execution/testing/tcatch/CidrCatchOutputToGeneralTestEventsConverter$1.b:(Ljava/lang/Exception;)Ljava/lang/Exception;
                    //   336: athrow         
                    //   337: iconst_0       
                    //   338: putfield        com/jetbrains/cidr/execution/testing/tcatch/CidrCatchOutputToGeneralTestEventsConverter$1.mySuccess:Z
                    //   341: goto            485
                    //   344: ldc             "Expression"
                    //   346: aload_3        
                    //   347: invokevirtual   java/lang/String.equals:(Ljava/lang/Object;)Z
                    //   350: ifeq            407
                    //   353: ldc             "true"
                    //   355: aload           4
                    //   357: ldc             "success"
                    //   359: invokeinterface org/xml/sax/Attributes.getValue:(Ljava/lang/String;)Ljava/lang/String;
                    //   364: invokevirtual   java/lang/String.equals:(Ljava/lang/Object;)Z
                    //   367: ifne            407
                    //   370: goto            377
                    //   373: invokestatic    com/jetbrains/cidr/execution/testing/tcatch/CidrCatchOutputToGeneralTestEventsConverter$1.b:(Ljava/lang/Exception;)Ljava/lang/Exception;
                    //   376: athrow         
                    //   377: aload_0        
                    //   378: aload           5
                    //   380: aload           4
                    //   382: ldc             ""
                    //   384: invokespecial   com/jetbrains/cidr/execution/testing/tcatch/CidrCatchOutputToGeneralTestEventsConverter$1.a:(Lcom/intellij/execution/testframework/sm/runner/GeneralTestEventsProcessor;Lorg/xml/sax/Attributes;Ljava/lang/String;)V
                    //   387: aload_0        
                    //   388: aload           4
                    //   390: ldc             "type"
                    //   392: invokeinterface org/xml/sax/Attributes.getValue:(Ljava/lang/String;)Ljava/lang/String;
                    //   397: putfield        com/jetbrains/cidr/execution/testing/tcatch/CidrCatchOutputToGeneralTestEventsConverter$1.myExpressionPrefix:Ljava/lang/String;
                    //   400: goto            485
                    //   403: invokestatic    com/jetbrains/cidr/execution/testing/tcatch/CidrCatchOutputToGeneralTestEventsConverter$1.b:(Ljava/lang/Exception;)Ljava/lang/Exception;
                    //   406: athrow         
                    //   407: ldc             "Exception"
                    //   409: aload_3        
                    //   410: invokevirtual   java/lang/String.equals:(Ljava/lang/Object;)Z
                    //   413: ifeq            433
                    //   416: aload_0        
                    //   417: aload           5
                    //   419: aload           4
                    //   421: ldc             "\nunexpected exception"
                    //   423: invokespecial   com/jetbrains/cidr/execution/testing/tcatch/CidrCatchOutputToGeneralTestEventsConverter$1.a:(Lcom/intellij/execution/testframework/sm/runner/GeneralTestEventsProcessor;Lorg/xml/sax/Attributes;Ljava/lang/String;)V
                    //   426: goto            485
                    //   429: invokestatic    com/jetbrains/cidr/execution/testing/tcatch/CidrCatchOutputToGeneralTestEventsConverter$1.b:(Ljava/lang/Exception;)Ljava/lang/Exception;
                    //   432: athrow         
                    //   433: ldc             "FatalErrorCondition"
                    //   435: aload_3        
                    //   436: invokevirtual   java/lang/String.equals:(Ljava/lang/Object;)Z
                    //   439: ifeq            459
                    //   442: aload_0        
                    //   443: aload           5
                    //   445: aload           4
                    //   447: ldc             "\nfatal error"
                    //   449: invokespecial   com/jetbrains/cidr/execution/testing/tcatch/CidrCatchOutputToGeneralTestEventsConverter$1.a:(Lcom/intellij/execution/testframework/sm/runner/GeneralTestEventsProcessor;Lorg/xml/sax/Attributes;Ljava/lang/String;)V
                    //   452: goto            485
                    //   455: invokestatic    com/jetbrains/cidr/execution/testing/tcatch/CidrCatchOutputToGeneralTestEventsConverter$1.b:(Ljava/lang/Exception;)Ljava/lang/Exception;
                    //   458: athrow         
                    //   459: ldc             "Failure"
                    //   461: aload_3        
                    //   462: invokevirtual   java/lang/String.equals:(Ljava/lang/Object;)Z
                    //   465: ifeq            485
                    //   468: aload_0        
                    //   469: aload           5
                    //   471: aload           4
                    //   473: ldc             ""
                    //   475: invokespecial   com/jetbrains/cidr/execution/testing/tcatch/CidrCatchOutputToGeneralTestEventsConverter$1.a:(Lcom/intellij/execution/testframework/sm/runner/GeneralTestEventsProcessor;Lorg/xml/sax/Attributes;Ljava/lang/String;)V
                    //   478: goto            485
                    //   481: invokestatic    com/jetbrains/cidr/execution/testing/tcatch/CidrCatchOutputToGeneralTestEventsConverter$1.b:(Ljava/lang/Exception;)Ljava/lang/Exception;
                    //   484: athrow         
                    //   485: return         
                    //    Exceptions:
                    //  throws org.xml.sax.SAXException
                    //    Exceptions:
                    //  Try           Handler
                    //  Start  End    Start  End    Type                      
                    //  -----  -----  -----  -----  --------------------------
                    //  9      23     23     27     Lorg/xml/sax/SAXException;
                    //  27     69     72     76     Lorg/xml/sax/SAXException;
                    //  76     103    103    107    Lorg/xml/sax/SAXException;
                    //  107    125    128    132    Lorg/xml/sax/SAXException;
                    //  116    143    143    147    Lorg/xml/sax/SAXException;
                    //  167    189    192    196    Lorg/xml/sax/SAXException;
                    //  237    259    262    266    Lorg/xml/sax/SAXException;
                    //  266    291    294    298    Lorg/xml/sax/SAXException;
                    //  279    322    325    329    Lorg/xml/sax/SAXException;
                    //  298    333    333    337    Lorg/xml/sax/SAXException;
                    //  344    370    373    377    Lorg/xml/sax/SAXException;
                    //  353    403    403    407    Lorg/xml/sax/SAXException;
                    //  407    429    429    433    Lorg/xml/sax/SAXException;
                    //  433    455    455    459    Lorg/xml/sax/SAXException;
                    //  459    478    481    485    Lorg/xml/sax/SAXException;
                    // 
                    // The error that occurred was:
                    // 
                    // java.lang.IllegalStateException: Expression is linked from several locations: Label_0298:
                    //     at com.strobel.decompiler.ast.Error.expressionLinkedFromMultipleLocations(Error.java:27)
                    //     at com.strobel.decompiler.ast.AstOptimizer.mergeDisparateObjectInitializations(AstOptimizer.java:2592)
                    //     at com.strobel.decompiler.ast.AstOptimizer.optimize(AstOptimizer.java:235)
                    //     at com.strobel.decompiler.ast.AstOptimizer.optimize(AstOptimizer.java:42)
                    //     at com.strobel.decompiler.languages.java.ast.AstMethodBodyBuilder.createMethodBody(AstMethodBodyBuilder.java:214)
                    //     at com.strobel.decompiler.languages.java.ast.AstMethodBodyBuilder.createMethodBody(AstMethodBodyBuilder.java:99)
                    //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createMethodBody(AstBuilder.java:757)
                    //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createMethod(AstBuilder.java:655)
                    //     at com.strobel.decompiler.languages.java.ast.AstBuilder.addTypeMembers(AstBuilder.java:532)
                    //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createTypeCore(AstBuilder.java:499)
                    //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createTypeNoCache(AstBuilder.java:141)
                    //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createType(AstBuilder.java:130)
                    //     at com.strobel.decompiler.languages.java.ast.AstMethodBodyBuilder.transformCall(AstMethodBodyBuilder.java:1163)
                    //     at com.strobel.decompiler.languages.java.ast.AstMethodBodyBuilder.transformByteCode(AstMethodBodyBuilder.java:1010)
                    //     at com.strobel.decompiler.languages.java.ast.AstMethodBodyBuilder.transformExpression(AstMethodBodyBuilder.java:540)
                    //     at com.strobel.decompiler.languages.java.ast.AstMethodBodyBuilder.transformByteCode(AstMethodBodyBuilder.java:554)
                    //     at com.strobel.decompiler.languages.java.ast.AstMethodBodyBuilder.transformExpression(AstMethodBodyBuilder.java:540)
                    //     at com.strobel.decompiler.languages.java.ast.AstMethodBodyBuilder.transformNode(AstMethodBodyBuilder.java:392)
                    //     at com.strobel.decompiler.languages.java.ast.AstMethodBodyBuilder.transformBlock(AstMethodBodyBuilder.java:333)
                    //     at com.strobel.decompiler.languages.java.ast.AstMethodBodyBuilder.transformNode(AstMethodBodyBuilder.java:494)
                    //     at com.strobel.decompiler.languages.java.ast.AstMethodBodyBuilder.transformBlock(AstMethodBodyBuilder.java:333)
                    //     at com.strobel.decompiler.languages.java.ast.AstMethodBodyBuilder.createMethodBody(AstMethodBodyBuilder.java:294)
                    //     at com.strobel.decompiler.languages.java.ast.AstMethodBodyBuilder.createMethodBody(AstMethodBodyBuilder.java:99)
                    //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createMethodBody(AstBuilder.java:757)
                    //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createMethod(AstBuilder.java:655)
                    //     at com.strobel.decompiler.languages.java.ast.AstBuilder.addTypeMembers(AstBuilder.java:532)
                    //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createTypeCore(AstBuilder.java:499)
                    //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createTypeNoCache(AstBuilder.java:141)
                    //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createType(AstBuilder.java:130)
                    //     at com.strobel.decompiler.languages.java.ast.AstBuilder.addType(AstBuilder.java:105)
                    //     at com.strobel.decompiler.languages.java.JavaLanguage.buildAst(JavaLanguage.java:71)
                    //     at com.strobel.decompiler.languages.java.JavaLanguage.decompileType(JavaLanguage.java:59)
                    //     at com.strobel.decompiler.DecompilerDriver.decompileType(DecompilerDriver.java:317)
                    //     at com.strobel.decompiler.DecompilerDriver.main(DecompilerDriver.java:141)
                    // 
                    throw new IllegalStateException("An error occurred while decompiling this method.");
                }
                
                @Override
                public void characters(final char[] array, final int n, final int n2) throws SAXException {
                    this.myText.append(array, n, n2);
                }
                
                @Override
                public void endElement(final String s, final String s2, final String s3) throws SAXException {
                    final GeneralTestEventsProcessor access$100 = CidrCatchOutputToGeneralTestEventsConverter.this.getProcessor();
                    try {
                        if (access$100 == null) {
                            CidrTestLog.LOG.debug("endElement: processor was disposed");
                            return;
                        }
                    }
                    catch (SAXException ex) {
                        throw b(ex);
                    }
                    try {
                        if (CidrTestLog.LOG.isDebugEnabled()) {
                            CidrTestLog.LOG.debug("}endElement " + s3);
                        }
                    }
                    catch (SAXException ex2) {
                        throw b(ex2);
                    }
                    try {
                        if ("Group".equals(s3)) {
                            return;
                        }
                    }
                    catch (SAXException ex3) {
                        throw b(ex3);
                    }
                    Label_0122: {
                        Label_0109: {
                            try {
                                if ("TestCase".equals(s3)) {
                                    break Label_0109;
                                }
                                final String s4 = "Section";
                                final String s5 = s3;
                                final boolean b = s4.equals(s5);
                                if (b) {
                                    break Label_0109;
                                }
                                break Label_0122;
                            }
                            catch (SAXException ex4) {
                                throw b(ex4);
                            }
                            try {
                                final String s4 = "Section";
                                final String s5 = s3;
                                final boolean b = s4.equals(s5);
                                if (b) {
                                    this.a(access$100);
                                    return;
                                }
                            }
                            catch (SAXException ex5) {
                                throw b(ex5);
                            }
                        }
                        try {
                            if ("Info".equals(s3)) {
                                this.a(this.myText.toString());
                                return;
                            }
                        }
                        catch (SAXException ex6) {
                            throw b(ex6);
                        }
                    }
                    try {
                        if ("Original".equals(s3)) {
                            this.a(access$100, "  " + StringUtil.notNullize(this.myExpressionPrefix) + '(' + this.myText.toString().trim() + ')', false);
                            return;
                        }
                    }
                    catch (SAXException ex7) {
                        throw b(ex7);
                    }
                    try {
                        if ("Warning".equals(s3)) {
                            this.b(access$100, "\nwarning:", true);
                            return;
                        }
                    }
                    catch (SAXException ex8) {
                        throw b(ex8);
                    }
                    if ("Expanded".equals(s3)) {
                        final StringBuilder sb = new StringBuilder();
                        final String trim = this.myText.toString().trim();
                        try {
                            if (trim.length() != 0) {
                                sb.append("\nwith expansion:\n").append("  ").append(trim);
                            }
                        }
                        catch (SAXException ex9) {
                            throw b(ex9);
                        }
                        try {
                            if (this.myInfoMessageWithNewLineAndIndent.length() != 0) {
                                sb.append("\nwith message:").append((CharSequence)this.myInfoMessageWithNewLineAndIndent);
                                this.myInfoMessageWithNewLineAndIndent.setLength(0);
                            }
                        }
                        catch (SAXException ex10) {
                            throw b(ex10);
                        }
                        sb.append('\n');
                        this.a(access$100, sb.toString(), false);
                    }
                    else {
                        try {
                            if ("Failure".equals(s3)) {
                                this.b(access$100, "explicitly with messages:", false);
                                return;
                            }
                        }
                        catch (SAXException ex11) {
                            throw b(ex11);
                        }
                        Label_0421: {
                            try {
                                if ("StdOut".equals(s3)) {
                                    break Label_0421;
                                }
                                final String s6 = "StdErr";
                                final String s7 = s3;
                                final boolean b2 = s6.equals(s7);
                                if (b2) {
                                    break Label_0421;
                                }
                                return;
                            }
                            catch (SAXException ex12) {
                                throw b(ex12);
                            }
                            try {
                                final String s6 = "StdErr";
                                final String s7 = s3;
                                final boolean b2 = s6.equals(s7);
                                if (b2) {
                                    this.a(access$100, this.myText.toString().trim() + "\n", "StdOut".equals(s3));
                                }
                            }
                            catch (SAXException ex13) {
                                throw b(ex13);
                            }
                        }
                    }
                }
                
                private void a(@NotNull final String s) {
                    try {
                        if (s == null) {
                            throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "text", "com/jetbrains/cidr/execution/testing/tcatch/CidrCatchOutputToGeneralTestEventsConverter$1", "addInfo"));
                        }
                    }
                    catch (IllegalArgumentException ex) {
                        throw b(ex);
                    }
                    this.myInfoMessageWithNewLineAndIndent.append("\n").append("  ").append(s.trim());
                }
                
                private void b(final GeneralTestEventsProcessor generalTestEventsProcessor, final String s, final boolean b) {
                    this.a(this.myText.toString());
                    final String string = s + (Object)this.myInfoMessageWithNewLineAndIndent + "\n";
                    this.myInfoMessageWithNewLineAndIndent.setLength(0);
                    this.a(generalTestEventsProcessor, string, b);
                }
                
                private void a(final GeneralTestEventsProcessor generalTestEventsProcessor, final String s, final boolean b) {
                    CidrCatchOutputToGeneralTestEventsConverter this$0;
                    final String s2;
                    final String s3;
                    final List<ServiceMessageBuilder> list;
                    generalTestEventsProcessor.addToInvokeLater(() -> {
                        this.b();
                        this.a();
                        Label_0043_1: {
                            try {
                                this$0 = CidrCatchOutputToGeneralTestEventsConverter.this;
                                if (b) {
                                    CidrCatchOutputToGeneralTestEventsConverter.this.myEventProcessor.testStdOut(s2, s3, s);
                                    break Label_0043_1;
                                }
                            }
                            catch (IllegalArgumentException ex) {
                                throw b(ex);
                            }
                            CidrCatchOutputToGeneralTestEventsConverter.this.myEventProcessor.testErrOut(s2, s3, s);
                        }
                        this$0.process(list);
                    });
                }
                
                private void a(@NotNull final GeneralTestEventsProcessor generalTestEventsProcessor, final Attributes attributes, @NotNull final String s) {
                    try {
                        if (generalTestEventsProcessor == null) {
                            throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "processor", "com/jetbrains/cidr/execution/testing/tcatch/CidrCatchOutputToGeneralTestEventsConverter$1", "reportError"));
                        }
                    }
                    catch (IllegalArgumentException ex) {
                        throw b(ex);
                    }
                    try {
                        if (s == null) {
                            throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "message", "com/jetbrains/cidr/execution/testing/tcatch/CidrCatchOutputToGeneralTestEventsConverter$1", "reportError"));
                        }
                    }
                    catch (IllegalArgumentException ex2) {
                        throw b(ex2);
                    }
                    Label_0108: {
                        try {
                            if (CidrCatchOutputToGeneralTestEventsConverter$1.$assertionsDisabled) {
                                break Label_0108;
                            }
                            final GeneralTestEventsProcessor generalTestEventsProcessor2 = generalTestEventsProcessor;
                            final boolean b = generalTestEventsProcessor2 instanceof GeneralIdBasedToSMTRunnerEventsConvertor;
                            if (!b) {
                                break Label_0108;
                            }
                            break Label_0108;
                        }
                        catch (IllegalArgumentException ex3) {
                            throw b(ex3);
                        }
                        try {
                            final GeneralTestEventsProcessor generalTestEventsProcessor2 = generalTestEventsProcessor;
                            final boolean b = generalTestEventsProcessor2 instanceof GeneralIdBasedToSMTRunnerEventsConvertor;
                            if (!b) {
                                throw new AssertionError();
                            }
                        }
                        catch (IllegalArgumentException ex4) {
                            throw b(ex4);
                        }
                    }
                    final String value = attributes.getValue("filename");
                    final String value2 = attributes.getValue("line");
                    String format = null;
                    Label_0185: {
                        Label_0157: {
                            try {
                                if (value == null) {
                                    break Label_0157;
                                }
                                final String s2 = value2;
                                if (s2 == null) {
                                    break Label_0157;
                                }
                                break Label_0157;
                            }
                            catch (IllegalArgumentException ex5) {
                                throw b(ex5);
                            }
                            try {
                                final String s2 = value2;
                                if (s2 == null) {
                                    format = "Failure:";
                                    break Label_0185;
                                }
                            }
                            catch (IllegalArgumentException ex6) {
                                throw b(ex6);
                            }
                        }
                        format = String.format("%s:%s: Failure:", value, value2);
                    }
                    ((GeneralIdBasedToSMTRunnerEventsConvertor)generalTestEventsProcessor).onError(this.a(), "", format + s, false);
                }
                
                private void a(final GeneralTestEventsProcessor generalTestEventsProcessor, final Attributes attributes) {
                    final String value = attributes.getValue("name");
                    this.a();
                    this.myTestNameStack.push(value);
                    final String a = this.a();
                    Label_0080: {
                        Label_0062: {
                            try {
                                if (this.mySectionsEndIdStack.size() == 0) {
                                    break Label_0080;
                                }
                                final String s = a;
                                final DefaultHandler defaultHandler = this;
                                final Stack<String> stack = defaultHandler.mySectionsEndIdStack;
                                final String s2 = stack.peek();
                                final boolean b = s.equals(s2);
                                if (b) {
                                    break Label_0062;
                                }
                                break Label_0062;
                            }
                            catch (IllegalArgumentException ex) {
                                throw b(ex);
                            }
                            try {
                                final String s = a;
                                final DefaultHandler defaultHandler = this;
                                final Stack<String> stack = defaultHandler.mySectionsEndIdStack;
                                final String s2 = stack.peek();
                                final boolean b = s.equals(s2);
                                if (b) {
                                    this.mySectionsEndIdStack.pop();
                                    return;
                                }
                            }
                            catch (IllegalArgumentException ex2) {
                                throw b(ex2);
                            }
                        }
                        this.b(generalTestEventsProcessor);
                    }
                    final String s3;
                    generalTestEventsProcessor.addToInvokeLater(() -> CidrCatchOutputToGeneralTestEventsConverter.this.process(CidrCatchOutputToGeneralTestEventsConverter.this.myEventProcessor.testStarted(value, s3, a, this.getLocation(attributes))));
                }
                
                private void a(final GeneralTestEventsProcessor generalTestEventsProcessor) {
                    final String a = this.a();
                    final String s = this.myTestNameStack.pop();
                    final TestEnd testEnd = this.myTestResults.get(a);
                    Label_0081: {
                        try {
                            if (testEnd == null) {
                                this.myTestResults.put(a, new TestEnd(s, a, this.myDuration, this.mySuccess));
                                break Label_0081;
                            }
                        }
                        catch (IllegalArgumentException ex) {
                            throw b(ex);
                        }
                        testEnd.update(this.myDuration, this.mySuccess);
                    }
                    this.mySectionsEndIdStack.push(a);
                    final String a2 = this.a();
                    Label_0123: {
                        try {
                            if (!"0".equals(a2)) {
                                return;
                            }
                            final DefaultHandler defaultHandler = this;
                            final Stack<String> stack = defaultHandler.mySectionsEndIdStack;
                            final int n = stack.size();
                            if (n != 0) {
                                break Label_0123;
                            }
                            return;
                        }
                        catch (IllegalArgumentException ex2) {
                            throw b(ex2);
                        }
                        try {
                            final DefaultHandler defaultHandler = this;
                            final Stack<String> stack = defaultHandler.mySectionsEndIdStack;
                            final int n = stack.size();
                            if (n != 0) {
                                this.b(generalTestEventsProcessor);
                            }
                        }
                        catch (IllegalArgumentException ex3) {
                            throw b(ex3);
                        }
                    }
                }
                
                private void b(final GeneralTestEventsProcessor generalTestEventsProcessor) {
                    final TestEnd testEnd;
                    final Object o;
                    this.mySectionsEndIdStack.forEach(s -> {
                        testEnd = this.myTestResults.get(s);
                        Label_0029_1: {
                            try {
                                if (!CidrCatchOutputToGeneralTestEventsConverter$1.$assertionsDisabled) {
                                    if (o == null) {
                                        break Label_0029_1;
                                    }
                                    else {
                                        break Label_0029_1;
                                    }
                                }
                                else {
                                    break Label_0029_1;
                                }
                            }
                            catch (IllegalArgumentException ex) {
                                throw b(ex);
                            }
                            try {
                                if (o == null) {
                                    throw new AssertionError();
                                }
                            }
                            catch (IllegalArgumentException ex2) {
                                throw b(ex2);
                            }
                        }
                        testEnd.processEnd(generalTestEventsProcessor);
                        this.myTestResults.remove(s);
                        return;
                    });
                    this.mySectionsEndIdStack.clear();
                }
                
                @NotNull
                @Contract(pure = true)
                public String getLocation(final Attributes attributes) {
                    String string;
                    try {
                        string = this.c() + ':' + this.a(attributes);
                        if (string == null) {
                            throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/execution/testing/tcatch/CidrCatchOutputToGeneralTestEventsConverter$1", "getLocation"));
                        }
                    }
                    catch (IllegalArgumentException ex) {
                        throw b(ex);
                    }
                    return string;
                }
                
                private String b() {
                    return this.myTestNameStack.peek();
                }
                
                private String c() {
                    return this.myTestNameStack.firstElement();
                }
                
                private int a(final Attributes attributes) {
                    final String value = attributes.getValue("line");
                    try {
                        if (value == null) {
                            return -1;
                        }
                    }
                    catch (IllegalArgumentException ex) {
                        throw b(ex);
                    }
                    return Integer.valueOf(value);
                }
                
                @NotNull
                private String a() {
                    final StringBuilder sb = new StringBuilder();
                    String string;
                    try {
                        sb.append("0");
                        this.myTestNameStack.forEach(s -> sb.append('[').append(s).append(']'));
                        string = sb.toString();
                        if (string == null) {
                            throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/execution/testing/tcatch/CidrCatchOutputToGeneralTestEventsConverter$1", "getCurrentNodeId"));
                        }
                    }
                    catch (IllegalArgumentException ex) {
                        throw b(ex);
                    }
                    return string;
                }
                
                static {
                    boolean $assertionsDisabled2 = false;
                    Label_0017: {
                        try {
                            if (!CidrCatchOutputToGeneralTestEventsConverter.class.desiredAssertionStatus()) {
                                $assertionsDisabled2 = true;
                                break Label_0017;
                            }
                        }
                        catch (IllegalArgumentException ex) {
                            throw b(ex);
                        }
                        $assertionsDisabled2 = false;
                    }
                    $assertionsDisabled = $assertionsDisabled2;
                }
                
                private static Exception b(final Exception ex) {
                    return ex;
                }
                
                class TestEnd
                {
                    String testName;
                    String nodeId;
                    int durationInMs;
                    boolean success;
                    
                    TestEnd(@NotNull final String testName, final String nodeId, final int durationInMs, final boolean success) {
                        if (testName == null) {
                            throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "testName", "com/jetbrains/cidr/execution/testing/tcatch/CidrCatchOutputToGeneralTestEventsConverter$1$TestEnd", "<init>"));
                        }
                        if (nodeId == null) {
                            throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "nodeId", "com/jetbrains/cidr/execution/testing/tcatch/CidrCatchOutputToGeneralTestEventsConverter$1$TestEnd", "<init>"));
                        }
                        this.testName = testName;
                        this.nodeId = nodeId;
                        this.durationInMs = durationInMs;
                        this.success = success;
                    }
                    
                    void update(final int n, final boolean b) {
                        this.durationInMs += n;
                        this.success &= b;
                    }
                    
                    void processEnd(final GeneralTestEventsProcessor generalTestEventsProcessor) {
                        generalTestEventsProcessor.addToInvokeLater(() -> CidrCatchOutputToGeneralTestEventsConverter.this.process(CidrCatchOutputToGeneralTestEventsConverter.this.myEventProcessor.testFinished(this.testName, this.nodeId, Integer.toString(this.durationInMs), this.success)));
                    }
                }
            };
            if (defaultHandler == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/execution/testing/tcatch/CidrCatchOutputToGeneralTestEventsConverter", "createHandler"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw c(ex);
        }
        return defaultHandler;
    }
    
    static {
        ALL_TAGS = new String[] { "Catch", "StdOut", "StdErr", "Original", "Expanded", "Group", "TestCase", "Section", "OverallResult", "OverallResults", "Expression", "Info", "Warning", "Failure", "FatalErrorCondition", "Exception" };
        ALL_ATTRS = new String[] { "name", "description", "tags", "durationInSeconds", "success", "successes", "failures", "expectedFailures", "filename", "line", "type" };
        TAGS = StringUtil.join(CidrCatchOutputToGeneralTestEventsConverter.ALL_TAGS, "|");
        OPEN_TAG = Pattern.compile(String.format("([^\\n]*)(\\s*<(%s)(?: (?:%s)\\=\"[^\"]*\")*\\/?>\\n)", CidrCatchOutputToGeneralTestEventsConverter.TAGS, StringUtil.join(CidrCatchOutputToGeneralTestEventsConverter.ALL_ATTRS, "|")));
        CLOSE_TAG = Pattern.compile(String.format("([^\\n]*)(\\s*</(%s)>\\n)", CidrCatchOutputToGeneralTestEventsConverter.TAGS));
    }
    
    private static Exception c(final Exception ex) {
        return ex;
    }
}
