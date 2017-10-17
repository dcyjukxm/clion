// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.execution.testing.tcatch;

import com.intellij.execution.configurations.GeneralCommandLine;
import com.intellij.execution.testframework.sm.runner.SMTRunnerConsoleProperties;
import com.intellij.execution.ui.ConsoleView;
import com.jetbrains.cidr.execution.CidrConsoleBuilder;
import com.intellij.execution.filters.TextConsoleBuilder;
import java.io.File;
import com.jetbrains.cidr.lang.toolchains.CidrToolEnvironment;
import com.intellij.execution.configurations.CommandLineState;
import com.intellij.openapi.project.Project;
import com.jetbrains.cidr.execution.testing.CidrTestScopeElement;
import com.intellij.openapi.ui.ComponentContainer;
import com.jetbrains.cidr.execution.testing.CidrRerunFailedTestsAction;
import com.intellij.execution.testframework.sm.runner.ui.SMTRunnerConsoleView;
import com.intellij.execution.Executor;
import com.intellij.execution.runners.ExecutionEnvironment;
import com.jetbrains.cidr.execution.testing.CidrLauncher;
import com.intellij.execution.configurations.RunConfiguration;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;
import com.intellij.openapi.util.text.StringUtil;
import org.jetbrains.annotations.Nullable;
import com.jetbrains.cidr.execution.testing.CidrTestScope;
import com.jetbrains.cidr.execution.testing.CidrTestCommandLineState;

public class CidrCatchTestCommandLineState extends CidrTestCommandLineState
{
    public static final CidrTestScope ALL_VISIBLE_TESTS;
    public static final CidrTestScope NO_TESTS;
    
    @NotNull
    @Contract("_ -> !null")
    public static CidrTestScope getFromString(@Nullable final String s) {
        CidrTestScope cidrTestScope;
        try {
            cidrTestScope = new CidrTestScope() {
                @Override
                public String toString() {
                    return StringUtil.notNullize(s);
                }
            };
            if (cidrTestScope == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/execution/testing/tcatch/CidrCatchTestCommandLineState", "getFromString"));
            }
        }
        catch (IllegalStateException ex) {
            throw a(ex);
        }
        return cidrTestScope;
    }
    
    public CidrCatchTestCommandLineState(@NotNull final RunConfiguration runConfiguration, @NotNull final CidrLauncher cidrLauncher, @Nullable final CidrTestScope cidrTestScope, @NotNull final ExecutionEnvironment executionEnvironment, @NotNull final Executor executor) {
        if (runConfiguration == null) {
            throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "configuration", "com/jetbrains/cidr/execution/testing/tcatch/CidrCatchTestCommandLineState", "<init>"));
        }
        if (cidrLauncher == null) {
            throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "launcher", "com/jetbrains/cidr/execution/testing/tcatch/CidrCatchTestCommandLineState", "<init>"));
        }
        if (executionEnvironment == null) {
            throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "env", "com/jetbrains/cidr/execution/testing/tcatch/CidrCatchTestCommandLineState", "<init>"));
        }
        if (executor == null) {
            throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "executor", "com/jetbrains/cidr/execution/testing/tcatch/CidrCatchTestCommandLineState", "<init>"));
        }
        super(runConfiguration, cidrLauncher, executionEnvironment, executor, cidrTestScope);
    }
    
    @NotNull
    @Override
    public CidrTestScope testScope() {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     0: aload_0        
        //     1: getfield        com/jetbrains/cidr/execution/testing/tcatch/CidrCatchTestCommandLineState.myFailedTests:Lcom/jetbrains/cidr/execution/testing/CidrTestScope;
        //     4: ifnull          74
        //     7: aload_0        
        //     8: getfield        com/jetbrains/cidr/execution/testing/tcatch/CidrCatchTestCommandLineState.myFailedTests:Lcom/jetbrains/cidr/execution/testing/CidrTestScope;
        //    11: invokevirtual   com/jetbrains/cidr/execution/testing/CidrTestScope.getElements:()Ljava/util/SortedSet;
        //    14: aload_0        
        //    15: invokedynamic   fun:(Lcom/jetbrains/cidr/execution/testing/tcatch/CidrCatchTestCommandLineState;)Lcom/intellij/util/Function;
        //    20: ldc             ","
        //    22: invokestatic    com/intellij/openapi/util/text/StringUtil.join:(Ljava/util/Collection;Lcom/intellij/util/Function;Ljava/lang/String;)Ljava/lang/String;
        //    25: invokestatic    com/jetbrains/cidr/execution/testing/tcatch/CidrCatchTestCommandLineState.getFromString:(Ljava/lang/String;)Lcom/jetbrains/cidr/execution/testing/CidrTestScope;
        //    28: dup            
        //    29: ifnonnull       73
        //    32: goto            39
        //    35: invokestatic    com/jetbrains/cidr/execution/testing/tcatch/CidrCatchTestCommandLineState.a:(Ljava/lang/IllegalStateException;)Ljava/lang/IllegalStateException;
        //    38: athrow         
        //    39: new             Ljava/lang/IllegalStateException;
        //    42: dup            
        //    43: ldc             "@NotNull method %s.%s must not return null"
        //    45: ldc             2
        //    47: anewarray       Ljava/lang/Object;
        //    50: dup            
        //    51: ldc             0
        //    53: ldc             "com/jetbrains/cidr/execution/testing/tcatch/CidrCatchTestCommandLineState"
        //    55: aastore        
        //    56: dup            
        //    57: ldc             1
        //    59: ldc             "testScope"
        //    61: aastore        
        //    62: invokestatic    java/lang/String.format:(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
        //    65: invokespecial   java/lang/IllegalStateException.<init>:(Ljava/lang/String;)V
        //    68: athrow         
        //    69: invokestatic    com/jetbrains/cidr/execution/testing/tcatch/CidrCatchTestCommandLineState.a:(Ljava/lang/IllegalStateException;)Ljava/lang/IllegalStateException;
        //    72: athrow         
        //    73: areturn        
        //    74: aload_0        
        //    75: getfield        com/jetbrains/cidr/execution/testing/tcatch/CidrCatchTestCommandLineState.myConfiguration:Lcom/intellij/execution/configurations/RunConfiguration;
        //    78: checkcast       Lcom/jetbrains/cidr/execution/testing/CidrTestRunConfiguration;
        //    81: invokeinterface com/jetbrains/cidr/execution/testing/CidrTestRunConfiguration.getTestData:()Lcom/jetbrains/cidr/execution/testing/CidrTestRunConfigurationData;
        //    86: astore_1       
        //    87: aload_1        
        //    88: invokevirtual   com/jetbrains/cidr/execution/testing/CidrTestRunConfigurationData.getTestMode:()Lcom/jetbrains/cidr/execution/testing/CidrScopeInfo$Mode;
        //    91: getstatic       com/jetbrains/cidr/execution/testing/CidrScopeInfo$Mode.PATTERN:Lcom/jetbrains/cidr/execution/testing/CidrScopeInfo$Mode;
        //    94: if_acmpne       150
        //    97: aload_1        
        //    98: invokevirtual   com/jetbrains/cidr/execution/testing/CidrTestRunConfigurationData.getTestPattern:()Ljava/lang/String;
        //   101: invokestatic    com/jetbrains/cidr/execution/testing/tcatch/CidrCatchTestCommandLineState.getFromString:(Ljava/lang/String;)Lcom/jetbrains/cidr/execution/testing/CidrTestScope;
        //   104: dup            
        //   105: ifnonnull       149
        //   108: goto            115
        //   111: invokestatic    com/jetbrains/cidr/execution/testing/tcatch/CidrCatchTestCommandLineState.a:(Ljava/lang/IllegalStateException;)Ljava/lang/IllegalStateException;
        //   114: athrow         
        //   115: new             Ljava/lang/IllegalStateException;
        //   118: dup            
        //   119: ldc             "@NotNull method %s.%s must not return null"
        //   121: ldc             2
        //   123: anewarray       Ljava/lang/Object;
        //   126: dup            
        //   127: ldc             0
        //   129: ldc             "com/jetbrains/cidr/execution/testing/tcatch/CidrCatchTestCommandLineState"
        //   131: aastore        
        //   132: dup            
        //   133: ldc             1
        //   135: ldc             "testScope"
        //   137: aastore        
        //   138: invokestatic    java/lang/String.format:(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
        //   141: invokespecial   java/lang/IllegalStateException.<init>:(Ljava/lang/String;)V
        //   144: athrow         
        //   145: invokestatic    com/jetbrains/cidr/execution/testing/tcatch/CidrCatchTestCommandLineState.a:(Ljava/lang/IllegalStateException;)Ljava/lang/IllegalStateException;
        //   148: athrow         
        //   149: areturn        
        //   150: aload_1        
        //   151: invokevirtual   com/jetbrains/cidr/execution/testing/CidrTestRunConfigurationData.getTestMode:()Lcom/jetbrains/cidr/execution/testing/CidrScopeInfo$Mode;
        //   154: getstatic       com/jetbrains/cidr/execution/testing/CidrScopeInfo$Mode.SUITE_TEST:Lcom/jetbrains/cidr/execution/testing/CidrScopeInfo$Mode;
        //   157: if_acmpne       361
        //   160: aload_1        
        //   161: invokevirtual   com/jetbrains/cidr/execution/testing/CidrTestRunConfigurationData.getTestName:()Ljava/lang/String;
        //   164: ifnonnull       237
        //   167: goto            174
        //   170: invokestatic    com/jetbrains/cidr/execution/testing/tcatch/CidrCatchTestCommandLineState.a:(Ljava/lang/IllegalStateException;)Ljava/lang/IllegalStateException;
        //   173: athrow         
        //   174: aload_1        
        //   175: invokevirtual   com/jetbrains/cidr/execution/testing/CidrTestRunConfigurationData.getTestSuite:()Ljava/lang/String;
        //   178: ifnonnull       237
        //   181: goto            188
        //   184: invokestatic    com/jetbrains/cidr/execution/testing/tcatch/CidrCatchTestCommandLineState.a:(Ljava/lang/IllegalStateException;)Ljava/lang/IllegalStateException;
        //   187: athrow         
        //   188: getstatic       com/jetbrains/cidr/execution/testing/tcatch/CidrCatchTestCommandLineState.ALL_VISIBLE_TESTS:Lcom/jetbrains/cidr/execution/testing/CidrTestScope;
        //   191: dup            
        //   192: ifnonnull       236
        //   195: goto            202
        //   198: invokestatic    com/jetbrains/cidr/execution/testing/tcatch/CidrCatchTestCommandLineState.a:(Ljava/lang/IllegalStateException;)Ljava/lang/IllegalStateException;
        //   201: athrow         
        //   202: new             Ljava/lang/IllegalStateException;
        //   205: dup            
        //   206: ldc             "@NotNull method %s.%s must not return null"
        //   208: ldc             2
        //   210: anewarray       Ljava/lang/Object;
        //   213: dup            
        //   214: ldc             0
        //   216: ldc             "com/jetbrains/cidr/execution/testing/tcatch/CidrCatchTestCommandLineState"
        //   218: aastore        
        //   219: dup            
        //   220: ldc             1
        //   222: ldc             "testScope"
        //   224: aastore        
        //   225: invokestatic    java/lang/String.format:(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
        //   228: invokespecial   java/lang/IllegalStateException.<init>:(Ljava/lang/String;)V
        //   231: athrow         
        //   232: invokestatic    com/jetbrains/cidr/execution/testing/tcatch/CidrCatchTestCommandLineState.a:(Ljava/lang/IllegalStateException;)Ljava/lang/IllegalStateException;
        //   235: athrow         
        //   236: areturn        
        //   237: aload_1        
        //   238: invokevirtual   com/jetbrains/cidr/execution/testing/CidrTestRunConfigurationData.getTestName:()Ljava/lang/String;
        //   241: ifnonnull       311
        //   244: aload_1        
        //   245: invokevirtual   com/jetbrains/cidr/execution/testing/CidrTestRunConfigurationData.getTestSuite:()Ljava/lang/String;
        //   248: ifnull          311
        //   251: goto            258
        //   254: invokestatic    com/jetbrains/cidr/execution/testing/tcatch/CidrCatchTestCommandLineState.a:(Ljava/lang/IllegalStateException;)Ljava/lang/IllegalStateException;
        //   257: athrow         
        //   258: aload_1        
        //   259: invokevirtual   com/jetbrains/cidr/execution/testing/CidrTestRunConfigurationData.getTestSuite:()Ljava/lang/String;
        //   262: invokestatic    com/jetbrains/cidr/execution/testing/tcatch/CidrCatchTestCommandLineState.getFromString:(Ljava/lang/String;)Lcom/jetbrains/cidr/execution/testing/CidrTestScope;
        //   265: dup            
        //   266: ifnonnull       310
        //   269: goto            276
        //   272: invokestatic    com/jetbrains/cidr/execution/testing/tcatch/CidrCatchTestCommandLineState.a:(Ljava/lang/IllegalStateException;)Ljava/lang/IllegalStateException;
        //   275: athrow         
        //   276: new             Ljava/lang/IllegalStateException;
        //   279: dup            
        //   280: ldc             "@NotNull method %s.%s must not return null"
        //   282: ldc             2
        //   284: anewarray       Ljava/lang/Object;
        //   287: dup            
        //   288: ldc             0
        //   290: ldc             "com/jetbrains/cidr/execution/testing/tcatch/CidrCatchTestCommandLineState"
        //   292: aastore        
        //   293: dup            
        //   294: ldc             1
        //   296: ldc             "testScope"
        //   298: aastore        
        //   299: invokestatic    java/lang/String.format:(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
        //   302: invokespecial   java/lang/IllegalStateException.<init>:(Ljava/lang/String;)V
        //   305: athrow         
        //   306: invokestatic    com/jetbrains/cidr/execution/testing/tcatch/CidrCatchTestCommandLineState.a:(Ljava/lang/IllegalStateException;)Ljava/lang/IllegalStateException;
        //   309: athrow         
        //   310: areturn        
        //   311: aload_0        
        //   312: aload_1        
        //   313: invokevirtual   com/jetbrains/cidr/execution/testing/CidrTestRunConfigurationData.getTestName:()Ljava/lang/String;
        //   316: invokevirtual   com/jetbrains/cidr/execution/testing/tcatch/CidrCatchTestCommandLineState.prepareTestNameForScope:(Ljava/lang/String;)Ljava/lang/String;
        //   319: invokestatic    com/jetbrains/cidr/execution/testing/tcatch/CidrCatchTestCommandLineState.getFromString:(Ljava/lang/String;)Lcom/jetbrains/cidr/execution/testing/CidrTestScope;
        //   322: dup            
        //   323: ifnonnull       360
        //   326: new             Ljava/lang/IllegalStateException;
        //   329: dup            
        //   330: ldc             "@NotNull method %s.%s must not return null"
        //   332: ldc             2
        //   334: anewarray       Ljava/lang/Object;
        //   337: dup            
        //   338: ldc             0
        //   340: ldc             "com/jetbrains/cidr/execution/testing/tcatch/CidrCatchTestCommandLineState"
        //   342: aastore        
        //   343: dup            
        //   344: ldc             1
        //   346: ldc             "testScope"
        //   348: aastore        
        //   349: invokestatic    java/lang/String.format:(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
        //   352: invokespecial   java/lang/IllegalStateException.<init>:(Ljava/lang/String;)V
        //   355: athrow         
        //   356: invokestatic    com/jetbrains/cidr/execution/testing/tcatch/CidrCatchTestCommandLineState.a:(Ljava/lang/IllegalStateException;)Ljava/lang/IllegalStateException;
        //   359: athrow         
        //   360: areturn        
        //   361: getstatic       com/jetbrains/cidr/lang/OCLog.LOG:Lcom/intellij/openapi/diagnostic/Logger;
        //   364: new             Ljava/lang/StringBuilder;
        //   367: dup            
        //   368: invokespecial   java/lang/StringBuilder.<init>:()V
        //   371: ldc             "Unknown CidrTestRunConfigurationData.Mode: "
        //   373: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   376: aload_1        
        //   377: invokevirtual   com/jetbrains/cidr/execution/testing/CidrTestRunConfigurationData.getTestMode:()Lcom/jetbrains/cidr/execution/testing/CidrScopeInfo$Mode;
        //   380: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/Object;)Ljava/lang/StringBuilder;
        //   383: invokevirtual   java/lang/StringBuilder.toString:()Ljava/lang/String;
        //   386: invokevirtual   com/intellij/openapi/diagnostic/Logger.error:(Ljava/lang/String;)V
        //   389: getstatic       com/jetbrains/cidr/execution/testing/tcatch/CidrCatchTestCommandLineState.NO_TESTS:Lcom/jetbrains/cidr/execution/testing/CidrTestScope;
        //   392: dup            
        //   393: ifnonnull       430
        //   396: new             Ljava/lang/IllegalStateException;
        //   399: dup            
        //   400: ldc             "@NotNull method %s.%s must not return null"
        //   402: ldc             2
        //   404: anewarray       Ljava/lang/Object;
        //   407: dup            
        //   408: ldc             0
        //   410: ldc             "com/jetbrains/cidr/execution/testing/tcatch/CidrCatchTestCommandLineState"
        //   412: aastore        
        //   413: dup            
        //   414: ldc             1
        //   416: ldc             "testScope"
        //   418: aastore        
        //   419: invokestatic    java/lang/String.format:(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
        //   422: invokespecial   java/lang/IllegalStateException.<init>:(Ljava/lang/String;)V
        //   425: athrow         
        //   426: invokestatic    com/jetbrains/cidr/execution/testing/tcatch/CidrCatchTestCommandLineState.a:(Ljava/lang/IllegalStateException;)Ljava/lang/IllegalStateException;
        //   429: athrow         
        //   430: areturn        
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                             
        //  -----  -----  -----  -----  ---------------------------------
        //  0      32     35     39     Ljava/lang/IllegalStateException;
        //  7      69     69     73     Ljava/lang/IllegalStateException;
        //  87     108    111    115    Ljava/lang/IllegalStateException;
        //  97     145    145    149    Ljava/lang/IllegalStateException;
        //  150    167    170    174    Ljava/lang/IllegalStateException;
        //  160    181    184    188    Ljava/lang/IllegalStateException;
        //  174    195    198    202    Ljava/lang/IllegalStateException;
        //  188    232    232    236    Ljava/lang/IllegalStateException;
        //  237    251    254    258    Ljava/lang/IllegalStateException;
        //  244    269    272    276    Ljava/lang/IllegalStateException;
        //  258    306    306    310    Ljava/lang/IllegalStateException;
        //  311    356    356    360    Ljava/lang/IllegalStateException;
        //  361    426    426    430    Ljava/lang/IllegalStateException;
        // 
        // The error that occurred was:
        // 
        // java.lang.IllegalStateException: Expression is linked from several locations: Label_0174:
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
    
    @NotNull
    protected String prepareTestNameForScope(final String s) {
        String escapeChars;
        try {
            escapeChars = StringUtil.escapeChars(s, new char[] { ',', '[', ']' });
            if (escapeChars == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/execution/testing/tcatch/CidrCatchTestCommandLineState", "prepareTestNameForScope"));
            }
        }
        catch (IllegalStateException ex) {
            throw a(ex);
        }
        return escapeChars;
    }
    
    @NotNull
    @Override
    protected CidrRerunFailedTestsAction doCreateRerunFailedTestsAction(final SMTRunnerConsoleView smtRunnerConsoleView) {
        CidrCatchTestRerunFailedTestsAction cidrCatchTestRerunFailedTestsAction;
        try {
            cidrCatchTestRerunFailedTestsAction = new CidrCatchTestRerunFailedTestsAction((ComponentContainer)smtRunnerConsoleView);
            if (cidrCatchTestRerunFailedTestsAction == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/execution/testing/tcatch/CidrCatchTestCommandLineState", "doCreateRerunFailedTestsAction"));
            }
        }
        catch (IllegalStateException ex) {
            throw a(ex);
        }
        return cidrCatchTestRerunFailedTestsAction;
    }
    
    @NotNull
    @Override
    protected CidrTestScopeElement createTestScopeElement(@Nullable final String s, @Nullable final String s2) {
        CidrCatchTestScopeElement cidrCatchTestScopeElement;
        try {
            cidrCatchTestScopeElement = new CidrCatchTestScopeElement(s, s2);
            if (cidrCatchTestScopeElement == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/execution/testing/tcatch/CidrCatchTestCommandLineState", "createTestScopeElement"));
            }
        }
        catch (IllegalStateException ex) {
            throw a(ex);
        }
        return cidrCatchTestScopeElement;
    }
    
    @Contract(pure = true)
    @NotNull
    public static TextConsoleBuilder createConsoleBuilder(@NotNull final Project project, @NotNull final CommandLineState commandLineState, @NotNull final CidrToolEnvironment cidrToolEnvironment, @Nullable final File file) {
        try {
            if (project == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "project", "com/jetbrains/cidr/execution/testing/tcatch/CidrCatchTestCommandLineState", "createConsoleBuilder"));
            }
        }
        catch (IllegalStateException ex) {
            throw a(ex);
        }
        try {
            if (commandLineState == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "state", "com/jetbrains/cidr/execution/testing/tcatch/CidrCatchTestCommandLineState", "createConsoleBuilder"));
            }
        }
        catch (IllegalStateException ex2) {
            throw a(ex2);
        }
        try {
            if (cidrToolEnvironment == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "environment", "com/jetbrains/cidr/execution/testing/tcatch/CidrCatchTestCommandLineState", "createConsoleBuilder"));
            }
        }
        catch (IllegalStateException ex3) {
            throw a(ex3);
        }
        CidrConsoleBuilder cidrConsoleBuilder;
        try {
            cidrConsoleBuilder = new CidrConsoleBuilder(project, cidrToolEnvironment, file) {
                @NotNull
                @Override
                protected ConsoleView createConsole() {
                    final CidrCatchTestCommandLineState cidrCatchTestCommandLineState = (CidrCatchTestCommandLineState)commandLineState;
                    ConsoleView console;
                    try {
                        console = this.createConsole(cidrCatchTestCommandLineState.getConfiguration().getType(), new CidrCatchTestConsoleProperties(cidrCatchTestCommandLineState.getConfiguration(), cidrCatchTestCommandLineState.getExecutor(), cidrCatchTestCommandLineState.getExecutionTarget()));
                        if (console == null) {
                            throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/execution/testing/tcatch/CidrCatchTestCommandLineState$2", "createConsole"));
                        }
                    }
                    catch (IllegalStateException ex) {
                        throw a(ex);
                    }
                    return console;
                }
                
                private static IllegalStateException a(final IllegalStateException ex) {
                    return ex;
                }
            };
            if (cidrConsoleBuilder == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/execution/testing/tcatch/CidrCatchTestCommandLineState", "createConsoleBuilder"));
            }
        }
        catch (IllegalStateException ex4) {
            throw a(ex4);
        }
        return cidrConsoleBuilder;
    }
    
    @Contract(pure = true)
    public static GeneralCommandLine prepareCommandLine(@NotNull final CommandLineState commandLineState, @NotNull final GeneralCommandLine generalCommandLine) {
        try {
            if (commandLineState == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "state", "com/jetbrains/cidr/execution/testing/tcatch/CidrCatchTestCommandLineState", "prepareCommandLine"));
            }
        }
        catch (IllegalStateException ex) {
            throw a(ex);
        }
        try {
            if (generalCommandLine == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "cl", "com/jetbrains/cidr/execution/testing/tcatch/CidrCatchTestCommandLineState", "prepareCommandLine"));
            }
        }
        catch (IllegalStateException ex2) {
            throw a(ex2);
        }
        generalCommandLine.addParameters(new String[] { "-r", "xml", "-d", "yes" });
        final String string = ((CidrTestCommandLineState)commandLineState).testScope().toString();
        try {
            if (!generalCommandLine.getParametersList().hasParameter("--order")) {
                generalCommandLine.addParameters(new String[] { "--order", "lex" });
            }
        }
        catch (IllegalStateException ex3) {
            throw a(ex3);
        }
        try {
            if (string != null) {
                a(generalCommandLine, string);
                generalCommandLine.addParameter(string);
            }
        }
        catch (IllegalStateException ex4) {
            throw a(ex4);
        }
        return generalCommandLine;
    }
    
    private static void a(@NotNull final GeneralCommandLine generalCommandLine, @NotNull final String s) {
        try {
            if (generalCommandLine == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "cl", "com/jetbrains/cidr/execution/testing/tcatch/CidrCatchTestCommandLineState", "fixtureForAllTestsInFile"));
            }
        }
        catch (IllegalStateException ex) {
            throw a(ex);
        }
        try {
            if (s == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "maybeRunAllInFileScope", "com/jetbrains/cidr/execution/testing/tcatch/CidrCatchTestCommandLineState", "fixtureForAllTestsInFile"));
            }
        }
        catch (IllegalStateException ex2) {
            throw a(ex2);
        }
        Label_0113: {
            try {
                if (!s.startsWith("[#")) {
                    return;
                }
                final String s2 = s;
                final String s3 = "]";
                final boolean b = s2.endsWith(s3);
                if (b) {
                    break Label_0113;
                }
                return;
            }
            catch (IllegalStateException ex3) {
                throw a(ex3);
            }
            try {
                final String s2 = s;
                final String s3 = "]";
                final boolean b = s2.endsWith(s3);
                if (b) {
                    generalCommandLine.addParameter("-#");
                }
            }
            catch (IllegalStateException ex4) {
                throw a(ex4);
            }
        }
    }
    
    static {
        ALL_VISIBLE_TESTS = getFromString(null);
        NO_TESTS = getFromString("*");
    }
    
    private static IllegalStateException a(final IllegalStateException ex) {
        return ex;
    }
}
