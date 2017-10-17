// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.execution.testing;

import java.io.ObjectInputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.Collections;
import com.intellij.openapi.diagnostic.Logger;
import java.util.regex.Matcher;
import com.intellij.openapi.util.text.StringUtil;
import com.intellij.execution.process.ProcessOutputTypes;
import java.util.Iterator;
import java.text.ParseException;
import com.intellij.execution.testframework.sm.ServiceMessageBuilder;
import java.util.List;
import jetbrains.buildServer.messages.serviceMessages.ServiceMessageVisitor;
import com.intellij.openapi.util.Key;
import com.intellij.execution.testframework.TestConsoleProperties;
import org.jetbrains.annotations.NotNull;
import java.util.regex.Pattern;
import org.jetbrains.annotations.Nullable;
import com.intellij.execution.testframework.sm.runner.OutputToGeneralTestEventsConverter;

public abstract class CidrOutputToGeneralTestEventsConverter extends OutputToGeneralTestEventsConverter
{
    @Nullable
    protected String myPotentiallyFinishedSuite;
    @Nullable
    protected String myRunningTest;
    @Nullable
    protected CidrTestEventProcessor myEventProcessor;
    protected StringBuilder myAssertionOutput;
    public static final Pattern PROCESS_FINISHED_PATTERN;
    public static final Pattern TEST_STARTED_PATTERN;
    private static final String KILLED_EXIT_CODE = "137";
    private boolean myTestFrameworkAttached;
    
    public CidrOutputToGeneralTestEventsConverter(@NotNull final String s, @NotNull final TestConsoleProperties testConsoleProperties) {
        if (s == null) {
            throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "testFrameworkName", "com/jetbrains/cidr/execution/testing/CidrOutputToGeneralTestEventsConverter", "<init>"));
        }
        if (testConsoleProperties == null) {
            throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "consoleProperties", "com/jetbrains/cidr/execution/testing/CidrOutputToGeneralTestEventsConverter", "<init>"));
        }
        super(s, testConsoleProperties);
        this.myPotentiallyFinishedSuite = null;
        this.myAssertionOutput = new StringBuilder();
    }
    
    protected void doProcessMessages(@NotNull final Key key, @NotNull final ServiceMessageVisitor serviceMessageVisitor, @NotNull final List<ServiceMessageBuilder> list) throws ParseException {
        try {
            if (key == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "outputType", "com/jetbrains/cidr/execution/testing/CidrOutputToGeneralTestEventsConverter", "doProcessMessages"));
            }
        }
        catch (ParseException ex) {
            throw c(ex);
        }
        try {
            if (serviceMessageVisitor == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "visitor", "com/jetbrains/cidr/execution/testing/CidrOutputToGeneralTestEventsConverter", "doProcessMessages"));
            }
        }
        catch (ParseException ex2) {
            throw c(ex2);
        }
        try {
            if (list == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "messages", "com/jetbrains/cidr/execution/testing/CidrOutputToGeneralTestEventsConverter", "doProcessMessages"));
            }
        }
        catch (ParseException ex3) {
            throw c(ex3);
        }
        final Iterator<ServiceMessageBuilder> iterator = list.iterator();
        while (iterator.hasNext()) {
            super.processServiceMessages(iterator.next().toString(), key, serviceMessageVisitor);
        }
    }
    
    public static void logOutput(final String s, final Key key) {
        String s2 = ">";
        if (key == ProcessOutputTypes.SYSTEM) {
            s2 = "~";
        }
        if (key == ProcessOutputTypes.STDERR) {
            s2 = "!";
        }
        CidrTestLog.LOG.debug(s2 + StringUtil.trimEnd(s, "\n"));
    }
    
    protected void reopenSuiteIfAppropriate(final Key p0, final ServiceMessageVisitor p1, @Nullable final String p2, @Nullable final String p3) throws ParseException {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     0: aload_3        
        //     1: ifnull          30
        //     4: aload_3        
        //     5: aload_0        
        //     6: getfield        com/jetbrains/cidr/execution/testing/CidrOutputToGeneralTestEventsConverter.myPotentiallyFinishedSuite:Ljava/lang/String;
        //     9: invokevirtual   java/lang/String.equals:(Ljava/lang/Object;)Z
        //    12: ifne            30
        //    15: goto            22
        //    18: invokestatic    com/jetbrains/cidr/execution/testing/CidrOutputToGeneralTestEventsConverter.c:(Ljava/lang/Exception;)Ljava/lang/Exception;
        //    21: athrow         
        //    22: iconst_1       
        //    23: goto            31
        //    26: invokestatic    com/jetbrains/cidr/execution/testing/CidrOutputToGeneralTestEventsConverter.c:(Ljava/lang/Exception;)Ljava/lang/Exception;
        //    29: athrow         
        //    30: iconst_0       
        //    31: istore          5
        //    33: aload_0        
        //    34: getfield        com/jetbrains/cidr/execution/testing/CidrOutputToGeneralTestEventsConverter.myPotentiallyFinishedSuite:Ljava/lang/String;
        //    37: ifnull          80
        //    40: iload           5
        //    42: ifne            63
        //    45: goto            52
        //    48: invokestatic    com/jetbrains/cidr/execution/testing/CidrOutputToGeneralTestEventsConverter.c:(Ljava/lang/Exception;)Ljava/lang/Exception;
        //    51: athrow         
        //    52: aload_3        
        //    53: ifnonnull       80
        //    56: goto            63
        //    59: invokestatic    com/jetbrains/cidr/execution/testing/CidrOutputToGeneralTestEventsConverter.c:(Ljava/lang/Exception;)Ljava/lang/Exception;
        //    62: athrow         
        //    63: aload_0        
        //    64: aload_1        
        //    65: aload_2        
        //    66: aload_0        
        //    67: getfield        com/jetbrains/cidr/execution/testing/CidrOutputToGeneralTestEventsConverter.myPotentiallyFinishedSuite:Ljava/lang/String;
        //    70: invokevirtual   com/jetbrains/cidr/execution/testing/CidrOutputToGeneralTestEventsConverter.suiteFinished:(Lcom/intellij/openapi/util/Key;Ljetbrains/buildServer/messages/serviceMessages/ServiceMessageVisitor;Ljava/lang/String;)V
        //    73: goto            80
        //    76: invokestatic    com/jetbrains/cidr/execution/testing/CidrOutputToGeneralTestEventsConverter.c:(Ljava/lang/Exception;)Ljava/lang/Exception;
        //    79: athrow         
        //    80: iload           5
        //    82: ifeq            101
        //    85: aload_0        
        //    86: aload_1        
        //    87: aload_2        
        //    88: aload_3        
        //    89: aload           4
        //    91: invokevirtual   com/jetbrains/cidr/execution/testing/CidrOutputToGeneralTestEventsConverter.suiteStarted:(Lcom/intellij/openapi/util/Key;Ljetbrains/buildServer/messages/serviceMessages/ServiceMessageVisitor;Ljava/lang/String;Ljava/lang/String;)V
        //    94: goto            101
        //    97: invokestatic    com/jetbrains/cidr/execution/testing/CidrOutputToGeneralTestEventsConverter.c:(Ljava/lang/Exception;)Ljava/lang/Exception;
        //   100: athrow         
        //   101: aload_0        
        //   102: aconst_null    
        //   103: putfield        com/jetbrains/cidr/execution/testing/CidrOutputToGeneralTestEventsConverter.myPotentiallyFinishedSuite:Ljava/lang/String;
        //   106: return         
        //    Exceptions:
        //  throws java.text.ParseException
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                      
        //  -----  -----  -----  -----  --------------------------
        //  0      15     18     22     Ljava/text/ParseException;
        //  4      26     26     30     Ljava/text/ParseException;
        //  33     45     48     52     Ljava/text/ParseException;
        //  40     56     59     63     Ljava/text/ParseException;
        //  52     73     76     80     Ljava/text/ParseException;
        //  80     94     97     101    Ljava/text/ParseException;
        // 
        // The error that occurred was:
        // 
        // java.lang.IllegalStateException: Expression is linked from several locations: Label_0052:
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
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.addType(AstBuilder.java:105)
        //     at com.strobel.decompiler.languages.java.JavaLanguage.buildAst(JavaLanguage.java:71)
        //     at com.strobel.decompiler.languages.java.JavaLanguage.decompileType(JavaLanguage.java:59)
        //     at com.strobel.decompiler.DecompilerDriver.decompileType(DecompilerDriver.java:317)
        //     at com.strobel.decompiler.DecompilerDriver.main(DecompilerDriver.java:141)
        // 
        throw new IllegalStateException("An error occurred while decompiling this method.");
    }
    
    protected abstract void suiteStarted(final Key p0, final ServiceMessageVisitor p1, final String p2, final String p3) throws ParseException;
    
    protected abstract void suiteFinished(final Key p0, final ServiceMessageVisitor p1, final String p2) throws ParseException;
    
    protected Boolean processAssertion(final Key key, final ServiceMessageVisitor serviceMessageVisitor, final String s, final Pattern pattern) throws ParseException {
        try {
            if (this.myRunningTest == null || this.myEventProcessor == null) {
                return null;
            }
        }
        catch (ParseException ex) {
            throw c(ex);
        }
        final Matcher matcher2;
        final Matcher matcher = matcher2 = pattern.matcher(s);
        Label_0116: {
            Label_0053: {
                try {
                    if (!matcher.find()) {
                        break Label_0116;
                    }
                    final CidrOutputToGeneralTestEventsConverter cidrOutputToGeneralTestEventsConverter = this;
                    final StringBuilder sb = cidrOutputToGeneralTestEventsConverter.myAssertionOutput;
                    final int n = sb.length();
                    if (n > 0) {
                        break Label_0053;
                    }
                    break Label_0053;
                }
                catch (ParseException ex2) {
                    throw c(ex2);
                }
                try {
                    final CidrOutputToGeneralTestEventsConverter cidrOutputToGeneralTestEventsConverter = this;
                    final StringBuilder sb = cidrOutputToGeneralTestEventsConverter.myAssertionOutput;
                    final int n = sb.length();
                    if (n > 0) {
                        this.doProcessMessages(key, serviceMessageVisitor, this.myEventProcessor.testErrOut(this.myRunningTest, this.myAssertionOutput.toString()));
                        this.myAssertionOutput.setLength(0);
                    }
                }
                catch (ParseException ex3) {
                    throw c(ex3);
                }
            }
            this.myAssertionOutput.append(matcher2.group(0));
            return this.shouldNotPrint(matcher2);
            try {
                if (this.myAssertionOutput.length() > 0) {
                    this.myAssertionOutput.append(s);
                    return true;
                }
            }
            catch (ParseException ex4) {
                throw c(ex4);
            }
        }
        return null;
    }
    
    protected boolean shouldNotPrint(final Matcher matcher) {
        try {
            if (matcher.start() == 0) {
                return true;
            }
        }
        catch (IllegalArgumentException ex) {
            throw c(ex);
        }
        return false;
    }
    
    protected void checkForTermination(final String s, final Key key, final ServiceMessageVisitor serviceMessageVisitor) throws ParseException {
        final Matcher matcher = CidrOutputToGeneralTestEventsConverter.PROCESS_FINISHED_PATTERN.matcher(s);
        try {
            if (!matcher.matches() || this.myRunningTest == null) {
                return;
            }
        }
        catch (ParseException ex) {
            throw c(ex);
        }
        final boolean equals = "137".equals(matcher.group(1));
        Logger logger = null;
        boolean b = false;
        Label_0085: {
            Label_0076: {
                try {
                    this.processCollectedAssertionOutput(key, serviceMessageVisitor, this.myRunningTest, true);
                    if (equals) {
                        return;
                    }
                    logger = CidrTestLog.LOG;
                    final CidrOutputToGeneralTestEventsConverter cidrOutputToGeneralTestEventsConverter = this;
                    final CidrTestEventProcessor cidrTestEventProcessor = cidrOutputToGeneralTestEventsConverter.myEventProcessor;
                    if (cidrTestEventProcessor != null) {
                        break Label_0076;
                    }
                    break Label_0076;
                }
                catch (ParseException ex2) {
                    throw c(ex2);
                }
                try {
                    logger = CidrTestLog.LOG;
                    final CidrOutputToGeneralTestEventsConverter cidrOutputToGeneralTestEventsConverter = this;
                    final CidrTestEventProcessor cidrTestEventProcessor = cidrOutputToGeneralTestEventsConverter.myEventProcessor;
                    if (cidrTestEventProcessor != null) {
                        b = true;
                        break Label_0085;
                    }
                }
                catch (ParseException ex3) {
                    throw c(ex3);
                }
            }
            b = false;
        }
        logger.assertTrue(b, (Object)"Event processor was not initialized");
        this.doProcessMessages(key, serviceMessageVisitor, this.myEventProcessor.testFinished(this.myRunningTest, "0", false));
    }
    
    protected abstract void processCollectedAssertionOutput(final Key p0, final ServiceMessageVisitor p1, @NotNull final String p2, final boolean p3) throws ParseException;
    
    protected void attachTestFramework(final Key key, final ServiceMessageVisitor serviceMessageVisitor) throws ParseException {
        try {
            if (!this.myTestFrameworkAttached) {
                this.doProcessMessages(key, serviceMessageVisitor, Collections.singletonList(new ServiceMessageBuilder("enteredTheMatrix")));
                this.myTestFrameworkAttached = true;
            }
        }
        catch (ParseException ex) {
            throw c(ex);
        }
    }
    
    static {
        PROCESS_FINISHED_PATTERN = Pattern.compile("Process finished with exit code ([-]?\\d+)( \\([^\\(\\)\\n]*\\))?\\n?");
        TEST_STARTED_PATTERN = Pattern.compile("Testing started at [^\\n]*");
    }
    
    private static Exception c(final Exception ex) {
        return ex;
    }
}
