// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.cpp.execution;

import com.intellij.execution.CommonProgramRunConfigurationParameters;
import com.intellij.execution.util.ProgramParametersConfigurator;
import com.intellij.execution.configurations.SimpleProgramParameters;
import com.jetbrains.cidr.execution.BuildConfigurationProblems;
import com.jetbrains.cidr.cpp.cmake.model.CMakeConfiguration;
import com.intellij.openapi.diagnostic.Logger;
import com.jetbrains.cidr.execution.CidrCommandLineConfigurator;
import com.intellij.execution.configurations.PtyCommandLine;
import com.intellij.openapi.util.ThrowableComputable;
import com.intellij.openapi.application.ApplicationManager;
import com.jetbrains.cidr.CidrBundle;
import com.jetbrains.cidr.cpp.CPPLog;
import com.jetbrains.cidr.cpp.execution.debugger.backend.GDBDriverConfiguration;
import com.jetbrains.cidr.execution.debugger.backend.LLDBDriverConfiguration;
import com.jetbrains.cidr.cpp.CPPBundle;
import com.jetbrains.cidr.cpp.toolchains.MSVC;
import com.jetbrains.cidr.cpp.CPPToolchains;
import com.jetbrains.cidr.execution.Installer;
import java.util.List;
import com.intellij.util.containers.ContainerUtil;
import com.jetbrains.cidr.execution.debugger.backend.DebuggerDriverConfiguration;
import com.intellij.openapi.extensions.ExtensionPointName;
import com.intellij.openapi.extensions.Extensions;
import com.jetbrains.cidr.execution.TrivialInstaller;
import com.jetbrains.cidr.execution.RunParameters;
import com.intellij.execution.configurations.GeneralCommandLine;
import com.jetbrains.cidr.cpp.cmake.CMakeEnvironment;
import com.jetbrains.cidr.execution.debugger.CidrLocalDebugProcess;
import com.jetbrains.cidr.execution.debugger.CidrDebugProcess;
import com.intellij.xdebugger.XDebugSession;
import com.jetbrains.cidr.execution.CidrConsoleBuilder;
import com.intellij.execution.filters.TextConsoleBuilder;
import com.jetbrains.cidr.lang.toolchains.CidrToolEnvironment;
import com.intellij.execution.ExecutionException;
import com.intellij.execution.process.ProcessHandler;
import com.intellij.execution.configurations.CommandLineState;
import org.jetbrains.annotations.Nullable;
import com.jetbrains.cidr.cpp.cmake.workspace.CMakeWorkspace;
import java.io.File;
import com.intellij.openapi.project.Project;
import org.jetbrains.annotations.NotNull;
import com.jetbrains.cidr.execution.testing.CidrLauncher;

public class CMakeLauncher extends CidrLauncher
{
    private static final String WIN_PTY_COLS = "win.pty.cols";
    private static final int WIN_PTY_CONSOLE_WIDTH = 120;
    @NotNull
    private CMakeAppRunConfiguration myConfiguration;
    
    public CMakeLauncher(@NotNull final CMakeAppRunConfiguration myConfiguration) {
        if (myConfiguration == null) {
            throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "configuration", "com/jetbrains/cidr/cpp/execution/CMakeLauncher", "<init>"));
        }
        this.myConfiguration = myConfiguration;
    }
    
    @NotNull
    @Override
    protected Project getProject() {
        Project project;
        try {
            project = this.myConfiguration.getProject();
            if (project == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/cpp/execution/CMakeLauncher", "getProject"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw c(ex);
        }
        return project;
    }
    
    @Nullable
    private File a() {
        return CMakeWorkspace.getInstance(this.getProject()).getModelProjectDir();
    }
    
    public ProcessHandler createProcess(@NotNull final CommandLineState p0) throws ExecutionException {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     0: aload_1        
        //     1: ifnonnull       44
        //     4: new             Ljava/lang/IllegalArgumentException;
        //     7: dup            
        //     8: ldc             "Argument for @NotNull parameter '%s' of %s.%s must not be null"
        //    10: ldc             3
        //    12: anewarray       Ljava/lang/Object;
        //    15: dup            
        //    16: ldc             0
        //    18: ldc             "state"
        //    20: aastore        
        //    21: dup            
        //    22: ldc             1
        //    24: ldc             "com/jetbrains/cidr/cpp/execution/CMakeLauncher"
        //    26: aastore        
        //    27: dup            
        //    28: ldc             2
        //    30: ldc             "createProcess"
        //    32: aastore        
        //    33: invokestatic    java/lang/String.format:(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
        //    36: invokespecial   java/lang/IllegalArgumentException.<init>:(Ljava/lang/String;)V
        //    39: athrow         
        //    40: invokestatic    com/jetbrains/cidr/cpp/execution/CMakeLauncher.c:(Ljava/lang/Exception;)Ljava/lang/Exception;
        //    43: athrow         
        //    44: aload_0        
        //    45: invokevirtual   com/jetbrains/cidr/cpp/execution/CMakeLauncher.usePty:()Z
        //    48: istore_2       
        //    49: aload_0        
        //    50: invokespecial   com/jetbrains/cidr/cpp/execution/CMakeLauncher.b:()Lcom/jetbrains/cidr/cpp/execution/CMakeAppRunConfiguration$BuildAndRunConfigurations;
        //    53: astore_3       
        //    54: aload_0        
        //    55: aload_3        
        //    56: invokevirtual   com/jetbrains/cidr/cpp/execution/CMakeLauncher.getRunEnvironment:(Lcom/jetbrains/cidr/cpp/execution/CMakeAppRunConfiguration$BuildAndRunConfigurations;)Lcom/jetbrains/cidr/cpp/cmake/CMakeEnvironment;
        //    59: astore          4
        //    61: aload_0        
        //    62: aload_1        
        //    63: aload_3        
        //    64: aload           4
        //    66: iload_2        
        //    67: invokevirtual   com/jetbrains/cidr/cpp/execution/CMakeLauncher.createCommandLine:(Lcom/intellij/execution/configurations/CommandLineState;Lcom/jetbrains/cidr/cpp/execution/CMakeAppRunConfiguration$BuildAndRunConfigurations;Lcom/jetbrains/cidr/cpp/cmake/CMakeEnvironment;Z)Lcom/intellij/execution/configurations/GeneralCommandLine;
        //    70: astore          5
        //    72: aload_1        
        //    73: aload_0        
        //    74: aload_1        
        //    75: aload           4
        //    77: aload_0        
        //    78: invokespecial   com/jetbrains/cidr/cpp/execution/CMakeLauncher.a:()Ljava/io/File;
        //    81: invokevirtual   com/jetbrains/cidr/cpp/execution/CMakeLauncher.createConsoleBuilder:(Lcom/intellij/execution/configurations/CommandLineState;Lcom/jetbrains/cidr/lang/toolchains/CidrToolEnvironment;Ljava/io/File;)Lcom/intellij/execution/filters/TextConsoleBuilder;
        //    84: invokevirtual   com/intellij/execution/configurations/CommandLineState.setConsoleBuilder:(Lcom/intellij/execution/filters/TextConsoleBuilder;)V
        //    87: getstatic       com/intellij/openapi/util/SystemInfo.isWindows:Z
        //    90: ifeq            128
        //    93: iload_2        
        //    94: ifeq            128
        //    97: goto            104
        //   100: invokestatic    com/jetbrains/cidr/cpp/execution/CMakeLauncher.c:(Ljava/lang/Exception;)Ljava/lang/Exception;
        //   103: athrow         
        //   104: invokestatic    com/jetbrains/cidr/cpp/CPPToolchains.getInstance:()Lcom/jetbrains/cidr/cpp/CPPToolchains;
        //   107: invokevirtual   com/jetbrains/cidr/cpp/CPPToolchains.getMinGW:()Lcom/jetbrains/cidr/cpp/toolchains/MinGW;
        //   110: ifnull          128
        //   113: goto            120
        //   116: invokestatic    com/jetbrains/cidr/cpp/execution/CMakeLauncher.c:(Ljava/lang/Exception;)Ljava/lang/Exception;
        //   119: athrow         
        //   120: iconst_1       
        //   121: goto            129
        //   124: invokestatic    com/jetbrains/cidr/cpp/execution/CMakeLauncher.c:(Ljava/lang/Exception;)Ljava/lang/Exception;
        //   127: athrow         
        //   128: iconst_0       
        //   129: istore          6
        //   131: aconst_null    
        //   132: astore          7
        //   134: iload           6
        //   136: ifeq            157
        //   139: ldc             "win.pty.cols"
        //   141: invokestatic    java/lang/System.getProperty:(Ljava/lang/String;)Ljava/lang/String;
        //   144: astore          7
        //   146: ldc             "win.pty.cols"
        //   148: bipush          120
        //   150: invokestatic    java/lang/String.valueOf:(I)Ljava/lang/String;
        //   153: invokestatic    java/lang/System.setProperty:(Ljava/lang/String;Ljava/lang/String;)Ljava/lang/String;
        //   156: pop            
        //   157: new             Lcom/intellij/execution/process/ColoredProcessHandler;
        //   160: dup            
        //   161: aload           5
        //   163: invokespecial   com/intellij/execution/process/ColoredProcessHandler.<init>:(Lcom/intellij/execution/configurations/GeneralCommandLine;)V
        //   166: astore          8
        //   168: iload           6
        //   170: ifeq            252
        //   173: aload           7
        //   175: ifnull          200
        //   178: goto            185
        //   181: invokestatic    com/jetbrains/cidr/cpp/execution/CMakeLauncher.c:(Ljava/lang/Exception;)Ljava/lang/Exception;
        //   184: athrow         
        //   185: ldc             "win.pty.cols"
        //   187: aload           7
        //   189: invokestatic    java/lang/System.setProperty:(Ljava/lang/String;Ljava/lang/String;)Ljava/lang/String;
        //   192: pop            
        //   193: goto            252
        //   196: invokestatic    com/jetbrains/cidr/cpp/execution/CMakeLauncher.c:(Ljava/lang/Exception;)Ljava/lang/Exception;
        //   199: athrow         
        //   200: ldc             "win.pty.cols"
        //   202: invokestatic    java/lang/System.clearProperty:(Ljava/lang/String;)Ljava/lang/String;
        //   205: pop            
        //   206: goto            252
        //   209: astore          9
        //   211: iload           6
        //   213: ifeq            249
        //   216: aload           7
        //   218: ifnull          243
        //   221: goto            228
        //   224: invokestatic    com/jetbrains/cidr/cpp/execution/CMakeLauncher.c:(Ljava/lang/Exception;)Ljava/lang/Exception;
        //   227: athrow         
        //   228: ldc             "win.pty.cols"
        //   230: aload           7
        //   232: invokestatic    java/lang/System.setProperty:(Ljava/lang/String;Ljava/lang/String;)Ljava/lang/String;
        //   235: pop            
        //   236: goto            249
        //   239: invokestatic    com/jetbrains/cidr/cpp/execution/CMakeLauncher.c:(Ljava/lang/Exception;)Ljava/lang/Exception;
        //   242: athrow         
        //   243: ldc             "win.pty.cols"
        //   245: invokestatic    java/lang/System.clearProperty:(Ljava/lang/String;)Ljava/lang/String;
        //   248: pop            
        //   249: aload           9
        //   251: athrow         
        //   252: iload_2        
        //   253: ifeq            275
        //   256: aload           8
        //   258: iconst_1       
        //   259: invokevirtual   com/intellij/execution/process/OSProcessHandler.setHasPty:(Z)V
        //   262: aload           8
        //   264: iconst_0       
        //   265: invokevirtual   com/intellij/execution/process/OSProcessHandler.setShouldDestroyProcessRecursively:(Z)V
        //   268: goto            275
        //   271: invokestatic    com/jetbrains/cidr/cpp/execution/CMakeLauncher.c:(Ljava/lang/Exception;)Ljava/lang/Exception;
        //   274: athrow         
        //   275: aload           8
        //   277: areturn        
        //    Exceptions:
        //  throws com.intellij.execution.ExecutionException
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                                       
        //  -----  -----  -----  -----  -------------------------------------------
        //  104    124    124    128    Lcom/intellij/execution/ExecutionException;
        //  93     113    116    120    Lcom/intellij/execution/ExecutionException;
        //  72     97     100    104    Lcom/intellij/execution/ExecutionException;
        //  0      40     40     44     Lcom/intellij/execution/ExecutionException;
        //  157    168    209    252    Any
        //  173    196    196    200    Lcom/intellij/execution/ExecutionException;
        //  168    178    181    185    Lcom/intellij/execution/ExecutionException;
        //  209    211    209    252    Any
        //  211    221    224    228    Lcom/intellij/execution/ExecutionException;
        //  216    239    239    243    Lcom/intellij/execution/ExecutionException;
        //  252    268    271    275    Lcom/intellij/execution/ExecutionException;
        // 
        // The error that occurred was:
        // 
        // java.lang.IllegalStateException: Expression is linked from several locations: Label_0104:
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
    protected TextConsoleBuilder createConsoleBuilder(@NotNull final CommandLineState commandLineState, @NotNull final CidrToolEnvironment cidrToolEnvironment, @Nullable final File file) {
        try {
            if (commandLineState == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "state", "com/jetbrains/cidr/cpp/execution/CMakeLauncher", "createConsoleBuilder"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw c(ex);
        }
        try {
            if (cidrToolEnvironment == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "environment", "com/jetbrains/cidr/cpp/execution/CMakeLauncher", "createConsoleBuilder"));
            }
        }
        catch (IllegalArgumentException ex2) {
            throw c(ex2);
        }
        CidrConsoleBuilder cidrConsoleBuilder;
        try {
            cidrConsoleBuilder = new CidrConsoleBuilder(this.myConfiguration.getProject(), cidrToolEnvironment, file);
            if (cidrConsoleBuilder == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/cpp/execution/CMakeLauncher", "createConsoleBuilder"));
            }
        }
        catch (IllegalArgumentException ex3) {
            throw c(ex3);
        }
        return cidrConsoleBuilder;
    }
    
    public boolean usePty() {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     0: invokestatic    com/intellij/openapi/application/ApplicationManager.getApplication:()Lcom/intellij/openapi/application/Application;
        //     3: astore_1       
        //     4: invokestatic    com/intellij/execution/configurations/PtyCommandLine.isEnabled:()Z
        //     7: ifne            42
        //    10: aload_1        
        //    11: invokeinterface com/intellij/openapi/application/Application.isInternal:()Z
        //    16: ifne            42
        //    19: goto            26
        //    22: invokestatic    com/jetbrains/cidr/cpp/execution/CMakeLauncher.c:(Ljava/lang/Exception;)Ljava/lang/Exception;
        //    25: athrow         
        //    26: aload_1        
        //    27: invokeinterface com/intellij/openapi/application/Application.isUnitTestMode:()Z
        //    32: ifeq            50
        //    35: goto            42
        //    38: invokestatic    com/jetbrains/cidr/cpp/execution/CMakeLauncher.c:(Ljava/lang/Exception;)Ljava/lang/Exception;
        //    41: athrow         
        //    42: iconst_1       
        //    43: goto            51
        //    46: invokestatic    com/jetbrains/cidr/cpp/execution/CMakeLauncher.c:(Ljava/lang/Exception;)Ljava/lang/Exception;
        //    49: athrow         
        //    50: iconst_0       
        //    51: ireturn        
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                                
        //  -----  -----  -----  -----  ------------------------------------
        //  4      19     22     26     Ljava/lang/IllegalArgumentException;
        //  10     35     38     42     Ljava/lang/IllegalArgumentException;
        //  26     46     46     50     Ljava/lang/IllegalArgumentException;
        // 
        // The error that occurred was:
        // 
        // java.lang.IllegalStateException: Expression is linked from several locations: Label_0026:
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
    public CidrDebugProcess createDebugProcess(@NotNull final CommandLineState commandLineState, @NotNull final XDebugSession xDebugSession) throws ExecutionException {
        try {
            if (commandLineState == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "state", "com/jetbrains/cidr/cpp/execution/CMakeLauncher", "createDebugProcess"));
            }
        }
        catch (ExecutionException ex) {
            throw c((Exception)ex);
        }
        try {
            if (xDebugSession == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "session", "com/jetbrains/cidr/cpp/execution/CMakeLauncher", "createDebugProcess"));
            }
        }
        catch (ExecutionException ex2) {
            throw c((Exception)ex2);
        }
        final CMakeAppRunConfiguration.BuildAndRunConfigurations b = this.b();
        final CMakeEnvironment runEnvironment = this.getRunEnvironment(b);
        final GeneralCommandLine commandLine = this.createCommandLine(commandLineState, b, runEnvironment, false);
        runEnvironment.convertPathVariableToEnv(commandLine);
        commandLineState.setConsoleBuilder(this.createConsoleBuilder(commandLineState, runEnvironment, this.a()));
        final RunParameters a = a(commandLine);
        CidrLocalDebugProcess cidrLocalDebugProcess;
        try {
            cidrLocalDebugProcess = new CidrLocalDebugProcess(a, xDebugSession, commandLineState.getConsoleBuilder());
            if (cidrLocalDebugProcess == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/cpp/execution/CMakeLauncher", "createDebugProcess"));
            }
        }
        catch (ExecutionException ex3) {
            throw c((Exception)ex3);
        }
        return cidrLocalDebugProcess;
    }
    
    @NotNull
    private static RunParameters a(final GeneralCommandLine generalCommandLine) throws ExecutionException {
        final TrivialInstaller trivialInstaller = new TrivialInstaller(generalCommandLine);
        final CLionCustomDebuggerProvider[] array = (CLionCustomDebuggerProvider[])Extensions.getExtensions((ExtensionPointName)CLionCustomDebuggerProvider.EP_NAME);
        for (int length = array.length, i = 0; i < length; ++i) {
            final DebuggerDriverConfiguration debuggerDriverConfiguration = (DebuggerDriverConfiguration)ContainerUtil.getFirstItem((List)array[i].getDebuggerConfigurations());
            try {
                if (debuggerDriverConfiguration == null) {
                    continue;
                }
                final CLionRunParameters cLionRunParameters = new CLionRunParameters(debuggerDriverConfiguration, trivialInstaller);
                if (cLionRunParameters != null) {
                    return cLionRunParameters;
                }
            }
            catch (ExecutionException ex) {
                throw c((Exception)ex);
            }
            throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/cpp/execution/CMakeLauncher", "getDebugParameters"));
        }
        final CPPToolchains instance = CPPToolchains.getInstance();
        try {
            if (MSVC.isDebugSupportDisabled(instance.getToolSet())) {
                throw new ExecutionException(CPPBundle.message("msvc.debug.notSupported", new Object[0]));
            }
        }
        catch (ExecutionException ex2) {
            throw c((Exception)ex2);
        }
        CLionRunParameters cLionRunParameters3 = null;
        Label_0214: {
            CLionRunParameters cLionRunParameters2 = null;
            Label_0179: {
                try {
                    if (!instance.isUseLLDB()) {
                        break Label_0214;
                    }
                    final LLDBDriverConfiguration lldbDriverConfiguration = createLLDBDriverConfiguration();
                    final TrivialInstaller trivialInstaller2 = trivialInstaller;
                    cLionRunParameters2 = new CLionRunParameters(lldbDriverConfiguration, trivialInstaller2);
                    if (cLionRunParameters2 == null) {
                        break Label_0179;
                    }
                    return cLionRunParameters2;
                }
                catch (ExecutionException ex3) {
                    throw c((Exception)ex3);
                }
                try {
                    final LLDBDriverConfiguration lldbDriverConfiguration = createLLDBDriverConfiguration();
                    final TrivialInstaller trivialInstaller2 = trivialInstaller;
                    cLionRunParameters2 = new CLionRunParameters(lldbDriverConfiguration, trivialInstaller2);
                    if (cLionRunParameters2 == null) {
                        throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/cpp/execution/CMakeLauncher", "getDebugParameters"));
                    }
                }
                catch (ExecutionException ex4) {
                    throw c((Exception)ex4);
                }
            }
            return cLionRunParameters2;
            try {
                cLionRunParameters3 = new CLionRunParameters(createGDBDriverConfiguration(), trivialInstaller);
                if (cLionRunParameters3 == null) {
                    throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/cpp/execution/CMakeLauncher", "getDebugParameters"));
                }
            }
            catch (ExecutionException ex5) {
                throw c((Exception)ex5);
            }
        }
        return cLionRunParameters3;
    }
    
    @NotNull
    public static LLDBDriverConfiguration createLLDBDriverConfiguration() {
        LLDBDriverConfiguration lldbDriverConfiguration;
        try {
            lldbDriverConfiguration = new LLDBDriverConfiguration();
            if (lldbDriverConfiguration == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/cpp/execution/CMakeLauncher", "createLLDBDriverConfiguration"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw c(ex);
        }
        return lldbDriverConfiguration;
    }
    
    @NotNull
    public static DebuggerDriverConfiguration createGDBDriverConfiguration() {
        GDBDriverConfiguration gdbDriverConfiguration;
        try {
            gdbDriverConfiguration = new GDBDriverConfiguration();
            if (gdbDriverConfiguration == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/cpp/execution/CMakeLauncher", "createGDBDriverConfiguration"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw c(ex);
        }
        return gdbDriverConfiguration;
    }
    
    @NotNull
    protected GeneralCommandLine createCommandLine(@NotNull final CommandLineState commandLineState, @NotNull final CMakeAppRunConfiguration.BuildAndRunConfigurations buildAndRunConfigurations, @NotNull final CMakeEnvironment cMakeEnvironment, final boolean b) throws ExecutionException {
        try {
            if (commandLineState == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "state", "com/jetbrains/cidr/cpp/execution/CMakeLauncher", "createCommandLine"));
            }
        }
        catch (ExecutionException ex) {
            throw c((Exception)ex);
        }
        try {
            if (buildAndRunConfigurations == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "buildAndRunConfigurations", "com/jetbrains/cidr/cpp/execution/CMakeLauncher", "createCommandLine"));
            }
        }
        catch (ExecutionException ex2) {
            throw c((Exception)ex2);
        }
        try {
            if (cMakeEnvironment == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "environment", "com/jetbrains/cidr/cpp/execution/CMakeLauncher", "createCommandLine"));
            }
        }
        catch (ExecutionException ex3) {
            throw c((Exception)ex3);
        }
        final File runFile = buildAndRunConfigurations.getRunFile();
        Label_0155: {
            Logger log;
            try {
                log = CPPLog.LOG;
                if (runFile != null) {
                    final boolean b2 = true;
                    break Label_0155;
                }
            }
            catch (ExecutionException ex4) {
                throw c((Exception)ex4);
            }
            final boolean b2 = false;
            try {
                log.assertTrue(b2);
                if (!runFile.exists()) {
                    throw new ExecutionException(CidrBundle.message("run.fileNotFound", runFile));
                }
            }
            catch (ExecutionException ex5) {
                throw c((Exception)ex5);
            }
        }
        GeneralCommandLine generalCommandLine;
        try {
            generalCommandLine = (GeneralCommandLine)ApplicationManager.getApplication().runReadAction((ThrowableComputable)new ThrowableComputable<GeneralCommandLine, ExecutionException>() {
                public GeneralCommandLine compute() throws ExecutionException {
                    Object o;
                    if (b) {
                        o = new PtyCommandLine();
                        ((PtyCommandLine)o).setUseCygwinLaunch(cMakeEnvironment.isCygwin());
                    }
                    else {
                        o = new GeneralCommandLine();
                    }
                    ((GeneralCommandLine)o).setExePath(runFile.getPath());
                    new CidrCommandLineConfigurator(CMakeLauncher.this.myConfiguration.getProject(), CMakeLauncher.this.a(runFile.getParent())).configureCommandLine((GeneralCommandLine)o);
                    cMakeEnvironment.prepare((GeneralCommandLine)o, CidrToolEnvironment.PrepareFor.RUN);
                    return (GeneralCommandLine)o;
                }
            });
            if (generalCommandLine == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/cpp/execution/CMakeLauncher", "createCommandLine"));
            }
        }
        catch (ExecutionException ex6) {
            throw c((Exception)ex6);
        }
        return generalCommandLine;
    }
    
    @NotNull
    public CMakeEnvironment getRunEnvironment(@NotNull final CMakeAppRunConfiguration.BuildAndRunConfigurations buildAndRunConfigurations) throws ExecutionException {
        try {
            if (buildAndRunConfigurations == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "buildAndRunConfigurations", "com/jetbrains/cidr/cpp/execution/CMakeLauncher", "getRunEnvironment"));
            }
        }
        catch (ExecutionException ex) {
            throw c((Exception)ex);
        }
        final CMakeConfiguration runConfiguration = buildAndRunConfigurations.getRunConfiguration();
        CMakeEnvironment environment;
        try {
            environment = CMakeWorkspace.getInstance(this.getProject()).getEnvironmentFor(runConfiguration);
            if (environment == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/cpp/execution/CMakeLauncher", "getRunEnvironment"));
            }
        }
        catch (ExecutionException ex2) {
            throw c((Exception)ex2);
        }
        return environment;
    }
    
    @NotNull
    private CMakeAppRunConfiguration.BuildAndRunConfigurations b() throws ExecutionException {
        final BuildConfigurationProblems buildConfigurationProblems = new BuildConfigurationProblems();
        final CMakeAppRunConfiguration.BuildAndRunConfigurations buildAndRunConfigurations = this.myConfiguration.getBuildAndRunConfigurations(buildConfigurationProblems, true);
        try {
            if (buildAndRunConfigurations == null) {
                throw new ExecutionException(buildConfigurationProblems.getText());
            }
        }
        catch (ExecutionException ex) {
            throw c((Exception)ex);
        }
        CMakeAppRunConfiguration.BuildAndRunConfigurations buildAndRunConfigurations2;
        try {
            buildAndRunConfigurations2 = buildAndRunConfigurations;
            if (buildAndRunConfigurations2 == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/cpp/execution/CMakeLauncher", "getBuildAndRunConfigurations"));
            }
        }
        catch (ExecutionException ex2) {
            throw c((Exception)ex2);
        }
        return buildAndRunConfigurations2;
    }
    
    @NotNull
    private SimpleProgramParameters a(@NotNull final String s) {
        try {
            if (s == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "defaultWorkingDir", "com/jetbrains/cidr/cpp/execution/CMakeLauncher", "getParameters"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw c(ex);
        }
        final SimpleProgramParameters simpleProgramParameters = new SimpleProgramParameters();
        final ProgramParametersConfigurator programParametersConfigurator = new ProgramParametersConfigurator() {
            @NotNull
            @Override
            protected String getDefaultWorkingDir(@NotNull final Project project) {
                try {
                    if (project == null) {
                        throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "project", "com/jetbrains/cidr/cpp/execution/CMakeLauncher$2", "getDefaultWorkingDir"));
                    }
                }
                catch (IllegalArgumentException ex) {
                    throw a(ex);
                }
                String val$defaultWorkingDir;
                try {
                    val$defaultWorkingDir = s;
                    if (val$defaultWorkingDir == null) {
                        throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/cpp/execution/CMakeLauncher$2", "getDefaultWorkingDir"));
                    }
                }
                catch (IllegalArgumentException ex2) {
                    throw a(ex2);
                }
                return val$defaultWorkingDir;
            }
            
            private static IllegalArgumentException a(final IllegalArgumentException ex) {
                return ex;
            }
        };
        SimpleProgramParameters simpleProgramParameters2;
        try {
            programParametersConfigurator.configureConfiguration(simpleProgramParameters, this.myConfiguration);
            simpleProgramParameters2 = simpleProgramParameters;
            if (simpleProgramParameters2 == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/cpp/execution/CMakeLauncher", "getParameters"));
            }
        }
        catch (IllegalArgumentException ex2) {
            throw c(ex2);
        }
        return simpleProgramParameters2;
    }
    
    private static Exception c(final Exception ex) {
        return ex;
    }
}
