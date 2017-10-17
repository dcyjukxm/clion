// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.cpp.cmake;

import com.intellij.openapi.util.registry.Registry;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.Nullable;
import com.intellij.execution.util.ExecUtil;
import com.jetbrains.cidr.cpp.toolchains.CMake;
import java.util.Iterator;
import com.jetbrains.cidr.toolchains.CidrToolSet;
import com.jetbrains.cidr.PredefinedVariables;
import com.jetbrains.cidr.lang.toolchains.CidrToolEnvironment;
import com.intellij.execution.configurations.PtyCommandLine;
import com.intellij.openapi.diagnostic.Logger;
import com.intellij.openapi.progress.ProgressIndicator;
import com.intellij.execution.configurations.GeneralCommandLine;
import com.intellij.openapi.progress.ProgressIndicatorProvider;
import com.intellij.openapi.application.ApplicationManager;
import com.intellij.execution.process.ProcessHandler;
import com.intellij.openapi.util.ShutDownTracker;
import com.intellij.execution.process.OSProcessUtil;
import com.intellij.execution.process.CapturingProcessHandler;
import com.jetbrains.cidr.cpp.CPPLog;
import com.intellij.execution.ExecutionException;
import com.intellij.execution.process.ProcessOutput;
import java.util.Map;
import java.util.List;
import java.io.File;
import org.jetbrains.annotations.NotNull;

public class CMakeRunner
{
    private static final String FIX_CYGWIN_PERMISSIONS_KEY = "cidr.cygwin.cmakePermissionsFix";
    private static final boolean FIX_CYGWIN_PERMISSIONS;
    private static volatile boolean ourCygwinPermissionsFixed;
    private static volatile boolean ourEmulateCMakeExecutionErrorInTests;
    @NotNull
    private final CMakeEnvironment myEnvironment;
    
    public static void setEmulateCMakeExecutionErrorInTests(final boolean ourEmulateCMakeExecutionErrorInTests) {
        CMakeRunner.ourEmulateCMakeExecutionErrorInTests = ourEmulateCMakeExecutionErrorInTests;
    }
    
    public CMakeRunner(@NotNull final CMakeEnvironment myEnvironment) {
        if (myEnvironment == null) {
            throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "environment", "com/jetbrains/cidr/cpp/cmake/CMakeRunner", "<init>"));
        }
        this.myEnvironment = myEnvironment;
    }
    
    @NotNull
    public CMakeEnvironment getEnvironment() {
        CMakeEnvironment myEnvironment;
        try {
            myEnvironment = this.myEnvironment;
            if (myEnvironment == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/cpp/cmake/CMakeRunner", "getEnvironment"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw b(ex);
        }
        return myEnvironment;
    }
    
    @NotNull
    public ProcessOutput runCMake(@NotNull final File file, @NotNull final File file2, @NotNull final List<String> list, final boolean b, @NotNull final Map<String, String> map, @NotNull final Listener listener) throws CMakeException {
        try {
            if (file == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "projectDir", "com/jetbrains/cidr/cpp/cmake/CMakeRunner", "runCMake"));
            }
        }
        catch (ExecutionException ex) {
            throw b((Exception)ex);
        }
        try {
            if (file2 == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "outputDir", "com/jetbrains/cidr/cpp/cmake/CMakeRunner", "runCMake"));
            }
        }
        catch (ExecutionException ex2) {
            throw b((Exception)ex2);
        }
        try {
            if (list == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "parameters", "com/jetbrains/cidr/cpp/cmake/CMakeRunner", "runCMake"));
            }
        }
        catch (ExecutionException ex3) {
            throw b((Exception)ex3);
        }
        try {
            if (map == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "additionalEnvironment", "com/jetbrains/cidr/cpp/cmake/CMakeRunner", "runCMake"));
            }
        }
        catch (ExecutionException ex4) {
            throw b((Exception)ex4);
        }
        try {
            if (listener == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "listener", "com/jetbrains/cidr/cpp/cmake/CMakeRunner", "runCMake"));
            }
        }
        catch (ExecutionException ex5) {
            throw b((Exception)ex5);
        }
        final GeneralCommandLine buildCommandLine = this.buildCommandLine(file2, list, b);
        buildCommandLine.addParameter(this.toEnvPath(file.getPath()));
        buildCommandLine.getEnvironment().putAll(map);
        buildCommandLine.setRedirectErrorStream(true);
        ProcessOutput processOutput = null;
        try {
            try {
                if (CMakeRunner.ourEmulateCMakeExecutionErrorInTests) {
                    throw new CMakeException("Emulated CMake error");
                }
            }
            catch (ExecutionException ex6) {
                throw b((Exception)ex6);
            }
            CPPLog.LOG.info("Executing CMake: " + buildCommandLine.getCommandLineString());
            CapturingProcessHandler capturingProcessHandler;
            try {
                capturingProcessHandler = new CapturingProcessHandler(buildCommandLine);
            }
            catch (ExecutionException ex7) {
                throw new CMakeException((Throwable)ex7);
            }
            final Runnable runnable = new Runnable() {
                @Override
                public void run() {
                    OSProcessUtil.killProcessTree(capturingProcessHandler.getProcess());
                }
            };
            ShutDownTracker.getInstance().registerShutdownTask((Runnable)runnable);
            try {
                listener.processStarted((ProcessHandler)capturingProcessHandler);
                final ProgressIndicator progressIndicator = (ApplicationManager.getApplication() == null) ? null : ProgressIndicatorProvider.getGlobalProgressIndicator();
                ProcessOutput processOutput2 = null;
                Label_0409: {
                    try {
                        if (progressIndicator == null) {
                            processOutput2 = capturingProcessHandler.runProcess();
                            break Label_0409;
                        }
                    }
                    catch (ExecutionException ex8) {
                        throw b((Exception)ex8);
                    }
                    processOutput2 = capturingProcessHandler.runProcessWithProgressIndicator(progressIndicator);
                }
                processOutput = processOutput2;
            }
            finally {
                ShutDownTracker.getInstance().unregisterShutdownTask((Runnable)runnable);
                runnable.run();
            }
        }
        finally {
            Logger log = null;
            StringBuilder sb = null;
            String string = null;
            Label_0570: {
                try {
                    log = CPPLog.LOG;
                    sb = new StringBuilder();
                    if (processOutput != null) {
                        string = "CMake exit code: " + processOutput.getExitCode();
                        break Label_0570;
                    }
                }
                catch (ExecutionException ex9) {
                    throw b((Exception)ex9);
                }
                string = "No output";
            }
            log.info(sb.append(string).append(" for command: ").append(buildCommandLine.getCommandLineString()).toString());
        }
        ProcessOutput processOutput3;
        try {
            processOutput3 = processOutput;
            if (processOutput3 == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/cpp/cmake/CMakeRunner", "runCMake"));
            }
        }
        catch (ExecutionException ex10) {
            throw b((Exception)ex10);
        }
        return processOutput3;
    }
    
    @NotNull
    public GeneralCommandLine buildCommandLine(@NotNull final File file, @NotNull final List<String> list, final boolean b) throws CMakeException {
        try {
            if (file == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "outputDir", "com/jetbrains/cidr/cpp/cmake/CMakeRunner", "buildCommandLine"));
            }
        }
        catch (CMakeException ex) {
            throw b(ex);
        }
        try {
            if (list == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "parameters", "com/jetbrains/cidr/cpp/cmake/CMakeRunner", "buildCommandLine"));
            }
        }
        catch (CMakeException ex2) {
            throw b(ex2);
        }
        GeneralCommandLine buildCommandLine;
        try {
            buildCommandLine = this.buildCommandLine(file, list, b, false);
            if (buildCommandLine == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/cpp/cmake/CMakeRunner", "buildCommandLine"));
            }
        }
        catch (CMakeException ex3) {
            throw b(ex3);
        }
        return buildCommandLine;
    }
    
    @NotNull
    public GeneralCommandLine buildCommandLine(@NotNull final File workDirectory, @NotNull final List<String> list, final boolean b, final boolean b2) throws CMakeException {
        try {
            if (workDirectory == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "outputDir", "com/jetbrains/cidr/cpp/cmake/CMakeRunner", "buildCommandLine"));
            }
        }
        catch (ExecutionException ex) {
            throw b((Exception)ex);
        }
        try {
            if (list == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "parameters", "com/jetbrains/cidr/cpp/cmake/CMakeRunner", "buildCommandLine"));
            }
        }
        catch (ExecutionException ex2) {
            throw b((Exception)ex2);
        }
        Object o;
        if (b2) {
            o = new PtyCommandLine();
            ((PtyCommandLine)o).setConsoleMode(false);
            final CidrToolSet toolSet = this.myEnvironment.getToolSet();
            Label_0140: {
                try {
                    if (toolSet == null) {
                        break Label_0140;
                    }
                    final CidrToolSet set = toolSet;
                    final boolean b3 = set.isCygwin();
                    if (b3) {
                        break Label_0140;
                    }
                    break Label_0140;
                }
                catch (ExecutionException ex3) {
                    throw b((Exception)ex3);
                }
                try {
                    final CidrToolSet set = toolSet;
                    final boolean b3 = set.isCygwin();
                    if (b3) {
                        ((PtyCommandLine)o).setUseCygwinLaunch(true);
                    }
                }
                catch (ExecutionException ex4) {
                    throw b((Exception)ex4);
                }
            }
        }
        else {
            o = new GeneralCommandLine();
        }
        PtyCommandLine ptyCommandLine = null;
        GeneralCommandLine.ParentEnvironmentType parentEnvironmentType = null;
        Label_0187: {
            try {
                ptyCommandLine = (PtyCommandLine)o;
                if (b) {
                    parentEnvironmentType = GeneralCommandLine.ParentEnvironmentType.CONSOLE;
                    break Label_0187;
                }
            }
            catch (ExecutionException ex5) {
                throw b((Exception)ex5);
            }
            parentEnvironmentType = GeneralCommandLine.ParentEnvironmentType.NONE;
        }
        ((GeneralCommandLine)ptyCommandLine).withParentEnvironmentType(parentEnvironmentType);
        ((GeneralCommandLine)o).setExePath(a(this.myEnvironment).getExecutablePath());
        try {
            this.myEnvironment.prepare((GeneralCommandLine)o, CidrToolEnvironment.PrepareFor.BUILD);
        }
        catch (ExecutionException ex6) {
            throw new CMakeException((Throwable)ex6);
        }
        final Iterator<String> iterator = PredefinedVariables.getIDEVariables().iterator();
        while (iterator.hasNext()) {
            ((GeneralCommandLine)o).withEnvironment((String)iterator.next(), "TRUE");
        }
        GeneralCommandLine generalCommandLine;
        try {
            ((GeneralCommandLine)o).setWorkDirectory(workDirectory);
            ((GeneralCommandLine)o).addParameters((List)list);
            generalCommandLine = (GeneralCommandLine)o;
            if (generalCommandLine == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/cpp/cmake/CMakeRunner", "buildCommandLine"));
            }
        }
        catch (ExecutionException ex7) {
            throw b((Exception)ex7);
        }
        return generalCommandLine;
    }
    
    @NotNull
    private static synchronized CMake a(@NotNull final CMakeEnvironment p0) throws CMakeException {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     0: aload_0        
        //     1: ifnonnull       44
        //     4: new             Ljava/lang/IllegalArgumentException;
        //     7: dup            
        //     8: ldc             "Argument for @NotNull parameter '%s' of %s.%s must not be null"
        //    10: ldc             3
        //    12: anewarray       Ljava/lang/Object;
        //    15: dup            
        //    16: ldc             0
        //    18: ldc             "environment"
        //    20: aastore        
        //    21: dup            
        //    22: ldc             1
        //    24: ldc             "com/jetbrains/cidr/cpp/cmake/CMakeRunner"
        //    26: aastore        
        //    27: dup            
        //    28: ldc             2
        //    30: ldc             "getCMakeExecutableWithFixedCygwinPermissions"
        //    32: aastore        
        //    33: invokestatic    java/lang/String.format:(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
        //    36: invokespecial   java/lang/IllegalArgumentException.<init>:(Ljava/lang/String;)V
        //    39: athrow         
        //    40: invokestatic    com/jetbrains/cidr/cpp/cmake/CMakeRunner.b:(Ljava/lang/Exception;)Ljava/lang/Exception;
        //    43: athrow         
        //    44: aload_0        
        //    45: invokevirtual   com/jetbrains/cidr/cpp/cmake/CMakeEnvironment.getCMake:()Lcom/jetbrains/cidr/cpp/toolchains/CMake;
        //    48: astore_1       
        //    49: aload_0        
        //    50: invokevirtual   com/jetbrains/cidr/cpp/cmake/CMakeEnvironment.getToolSet:()Lcom/jetbrains/cidr/toolchains/CidrToolSet;
        //    53: astore_2       
        //    54: aload_2        
        //    55: ifnull          85
        //    58: aload_2        
        //    59: invokevirtual   com/jetbrains/cidr/toolchains/CidrToolSet.isCygwin:()Z
        //    62: ifeq            85
        //    65: goto            72
        //    68: invokestatic    com/jetbrains/cidr/cpp/cmake/CMakeRunner.b:(Ljava/lang/Exception;)Ljava/lang/Exception;
        //    71: athrow         
        //    72: getstatic       com/jetbrains/cidr/cpp/cmake/CMakeRunner.FIX_CYGWIN_PERMISSIONS:Z
        //    75: ifne            132
        //    78: goto            85
        //    81: invokestatic    com/jetbrains/cidr/cpp/cmake/CMakeRunner.b:(Ljava/lang/Exception;)Ljava/lang/Exception;
        //    84: athrow         
        //    85: aload_1        
        //    86: dup            
        //    87: ifnonnull       131
        //    90: goto            97
        //    93: invokestatic    com/jetbrains/cidr/cpp/cmake/CMakeRunner.b:(Ljava/lang/Exception;)Ljava/lang/Exception;
        //    96: athrow         
        //    97: new             Ljava/lang/IllegalStateException;
        //   100: dup            
        //   101: ldc             "@NotNull method %s.%s must not return null"
        //   103: ldc             2
        //   105: anewarray       Ljava/lang/Object;
        //   108: dup            
        //   109: ldc             0
        //   111: ldc             "com/jetbrains/cidr/cpp/cmake/CMakeRunner"
        //   113: aastore        
        //   114: dup            
        //   115: ldc             1
        //   117: ldc             "getCMakeExecutableWithFixedCygwinPermissions"
        //   119: aastore        
        //   120: invokestatic    java/lang/String.format:(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
        //   123: invokespecial   java/lang/IllegalStateException.<init>:(Ljava/lang/String;)V
        //   126: athrow         
        //   127: invokestatic    com/jetbrains/cidr/cpp/cmake/CMakeRunner.b:(Ljava/lang/Exception;)Ljava/lang/Exception;
        //   130: athrow         
        //   131: areturn        
        //   132: aload_1        
        //   133: invokevirtual   com/jetbrains/cidr/cpp/toolchains/CMake.getExecutable:()Ljava/io/File;
        //   136: astore_3       
        //   137: aload_3        
        //   138: iconst_1       
        //   139: invokestatic    com/jetbrains/cidr/cpp/CPPPathManager.getBundledCMakeBinary:(Z)Ljava/io/File;
        //   142: invokestatic    com/intellij/openapi/util/io/FileUtil.filesEqual:(Ljava/io/File;Ljava/io/File;)Z
        //   145: istore          4
        //   147: iload           4
        //   149: ifne            199
        //   152: aload_1        
        //   153: dup            
        //   154: ifnonnull       198
        //   157: goto            164
        //   160: invokestatic    com/jetbrains/cidr/cpp/cmake/CMakeRunner.b:(Ljava/lang/Exception;)Ljava/lang/Exception;
        //   163: athrow         
        //   164: new             Ljava/lang/IllegalStateException;
        //   167: dup            
        //   168: ldc             "@NotNull method %s.%s must not return null"
        //   170: ldc             2
        //   172: anewarray       Ljava/lang/Object;
        //   175: dup            
        //   176: ldc             0
        //   178: ldc             "com/jetbrains/cidr/cpp/cmake/CMakeRunner"
        //   180: aastore        
        //   181: dup            
        //   182: ldc             1
        //   184: ldc             "getCMakeExecutableWithFixedCygwinPermissions"
        //   186: aastore        
        //   187: invokestatic    java/lang/String.format:(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
        //   190: invokespecial   java/lang/IllegalStateException.<init>:(Ljava/lang/String;)V
        //   193: athrow         
        //   194: invokestatic    com/jetbrains/cidr/cpp/cmake/CMakeRunner.b:(Ljava/lang/Exception;)Ljava/lang/Exception;
        //   197: athrow         
        //   198: areturn        
        //   199: new             Ljava/io/File;
        //   202: dup            
        //   203: invokestatic    com/intellij/openapi/application/PathManager.getSystemPath:()Ljava/lang/String;
        //   206: ldc             "cygwin_cmake"
        //   208: invokespecial   java/io/File.<init>:(Ljava/lang/String;Ljava/lang/String;)V
        //   211: astore          5
        //   213: new             Lcom/jetbrains/cidr/cpp/toolchains/CMake;
        //   216: dup            
        //   217: new             Ljava/io/File;
        //   220: dup            
        //   221: aload           5
        //   223: new             Ljava/lang/StringBuilder;
        //   226: dup            
        //   227: invokespecial   java/lang/StringBuilder.<init>:()V
        //   230: ldc             "bin/"
        //   232: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   235: aload_3        
        //   236: invokevirtual   java/io/File.getName:()Ljava/lang/String;
        //   239: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   242: invokevirtual   java/lang/StringBuilder.toString:()Ljava/lang/String;
        //   245: invokespecial   java/io/File.<init>:(Ljava/io/File;Ljava/lang/String;)V
        //   248: aload_2        
        //   249: invokespecial   com/jetbrains/cidr/cpp/toolchains/CMake.<init>:(Ljava/io/File;Lcom/jetbrains/cidr/toolchains/CidrToolSet;)V
        //   252: astore          6
        //   254: getstatic       com/jetbrains/cidr/cpp/cmake/CMakeRunner.ourCygwinPermissionsFixed:Z
        //   257: ifeq            308
        //   260: aload           6
        //   262: dup            
        //   263: ifnonnull       307
        //   266: goto            273
        //   269: invokestatic    com/jetbrains/cidr/cpp/cmake/CMakeRunner.b:(Ljava/lang/Exception;)Ljava/lang/Exception;
        //   272: athrow         
        //   273: new             Ljava/lang/IllegalStateException;
        //   276: dup            
        //   277: ldc             "@NotNull method %s.%s must not return null"
        //   279: ldc             2
        //   281: anewarray       Ljava/lang/Object;
        //   284: dup            
        //   285: ldc             0
        //   287: ldc             "com/jetbrains/cidr/cpp/cmake/CMakeRunner"
        //   289: aastore        
        //   290: dup            
        //   291: ldc             1
        //   293: ldc             "getCMakeExecutableWithFixedCygwinPermissions"
        //   295: aastore        
        //   296: invokestatic    java/lang/String.format:(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
        //   299: invokespecial   java/lang/IllegalStateException.<init>:(Ljava/lang/String;)V
        //   302: athrow         
        //   303: invokestatic    com/jetbrains/cidr/cpp/cmake/CMakeRunner.b:(Ljava/lang/Exception;)Ljava/lang/Exception;
        //   306: athrow         
        //   307: areturn        
        //   308: aload_1        
        //   309: invokevirtual   com/jetbrains/cidr/cpp/toolchains/CMake.readVersion:()Ljava/lang/String;
        //   312: astore          7
        //   314: aload           7
        //   316: ifnonnull       366
        //   319: aload_1        
        //   320: dup            
        //   321: ifnonnull       365
        //   324: goto            331
        //   327: invokestatic    com/jetbrains/cidr/cpp/cmake/CMakeRunner.b:(Ljava/lang/Exception;)Ljava/lang/Exception;
        //   330: athrow         
        //   331: new             Ljava/lang/IllegalStateException;
        //   334: dup            
        //   335: ldc             "@NotNull method %s.%s must not return null"
        //   337: ldc             2
        //   339: anewarray       Ljava/lang/Object;
        //   342: dup            
        //   343: ldc             0
        //   345: ldc             "com/jetbrains/cidr/cpp/cmake/CMakeRunner"
        //   347: aastore        
        //   348: dup            
        //   349: ldc             1
        //   351: ldc             "getCMakeExecutableWithFixedCygwinPermissions"
        //   353: aastore        
        //   354: invokestatic    java/lang/String.format:(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
        //   357: invokespecial   java/lang/IllegalStateException.<init>:(Ljava/lang/String;)V
        //   360: athrow         
        //   361: invokestatic    com/jetbrains/cidr/cpp/cmake/CMakeRunner.b:(Ljava/lang/Exception;)Ljava/lang/Exception;
        //   364: athrow         
        //   365: areturn        
        //   366: aload           7
        //   368: aload           6
        //   370: invokevirtual   com/jetbrains/cidr/cpp/toolchains/CMake.readVersion:()Ljava/lang/String;
        //   373: invokevirtual   java/lang/String.equals:(Ljava/lang/Object;)Z
        //   376: ifne            546
        //   379: getstatic       com/jetbrains/cidr/cpp/CPPLog.LOG:Lcom/intellij/openapi/diagnostic/Logger;
        //   382: new             Ljava/lang/StringBuilder;
        //   385: dup            
        //   386: invokespecial   java/lang/StringBuilder.<init>:()V
        //   389: ldc             "Updating Cygwin CMake in "
        //   391: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   394: aload           5
        //   396: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/Object;)Ljava/lang/StringBuilder;
        //   399: invokevirtual   java/lang/StringBuilder.toString:()Ljava/lang/String;
        //   402: invokevirtual   com/intellij/openapi/diagnostic/Logger.info:(Ljava/lang/String;)V
        //   405: aload_1        
        //   406: invokevirtual   com/jetbrains/cidr/cpp/toolchains/CMake.getExecutable:()Ljava/io/File;
        //   409: invokevirtual   java/io/File.getParentFile:()Ljava/io/File;
        //   412: invokevirtual   java/io/File.getParentFile:()Ljava/io/File;
        //   415: astore          8
        //   417: aload           5
        //   419: invokestatic    com/intellij/openapi/util/io/FileUtil.delete:(Ljava/io/File;)Z
        //   422: ifne            457
        //   425: new             Ljava/io/IOException;
        //   428: dup            
        //   429: new             Ljava/lang/StringBuilder;
        //   432: dup            
        //   433: invokespecial   java/lang/StringBuilder.<init>:()V
        //   436: ldc             "Cannot delete directory "
        //   438: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   441: aload           5
        //   443: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/Object;)Ljava/lang/StringBuilder;
        //   446: invokevirtual   java/lang/StringBuilder.toString:()Ljava/lang/String;
        //   449: invokespecial   java/io/IOException.<init>:(Ljava/lang/String;)V
        //   452: athrow         
        //   453: invokestatic    com/jetbrains/cidr/cpp/cmake/CMakeRunner.b:(Ljava/lang/Exception;)Ljava/lang/Exception;
        //   456: athrow         
        //   457: aload           8
        //   459: aload           5
        //   461: invokestatic    com/intellij/openapi/util/io/FileUtil.copyDir:(Ljava/io/File;Ljava/io/File;)V
        //   464: aload           7
        //   466: aload           6
        //   468: invokevirtual   com/jetbrains/cidr/cpp/toolchains/CMake.readVersion:()Ljava/lang/String;
        //   471: invokevirtual   java/lang/String.equals:(Ljava/lang/Object;)Z
        //   474: ifne            512
        //   477: new             Ljava/io/IOException;
        //   480: dup            
        //   481: new             Ljava/lang/StringBuilder;
        //   484: dup            
        //   485: invokespecial   java/lang/StringBuilder.<init>:()V
        //   488: ldc             "Cannot execute CMake: "
        //   490: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   493: aload           6
        //   495: invokevirtual   com/jetbrains/cidr/cpp/toolchains/CMake.getExecutable:()Ljava/io/File;
        //   498: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/Object;)Ljava/lang/StringBuilder;
        //   501: invokevirtual   java/lang/StringBuilder.toString:()Ljava/lang/String;
        //   504: invokespecial   java/io/IOException.<init>:(Ljava/lang/String;)V
        //   507: athrow         
        //   508: invokestatic    com/jetbrains/cidr/cpp/cmake/CMakeRunner.b:(Ljava/lang/Exception;)Ljava/lang/Exception;
        //   511: athrow         
        //   512: aload_0        
        //   513: aload_2        
        //   514: aload           5
        //   516: invokestatic    com/jetbrains/cidr/cpp/cmake/CMakeRunner.a:(Lcom/jetbrains/cidr/cpp/cmake/CMakeEnvironment;Lcom/jetbrains/cidr/toolchains/CidrToolSet;Ljava/io/File;)V
        //   519: goto            546
        //   522: astore          9
        //   524: aload           5
        //   526: aload           8
        //   528: aload           9
        //   530: invokestatic    com/jetbrains/cidr/cpp/cmake/CMakeRunner.a:(Ljava/io/File;Ljava/io/File;Ljava/lang/Exception;)Lcom/jetbrains/cidr/cpp/cmake/CMakeException;
        //   533: athrow         
        //   534: astore          9
        //   536: aload           5
        //   538: aload           8
        //   540: aload           9
        //   542: invokestatic    com/jetbrains/cidr/cpp/cmake/CMakeRunner.a:(Ljava/io/File;Ljava/io/File;Ljava/lang/Exception;)Lcom/jetbrains/cidr/cpp/cmake/CMakeException;
        //   545: athrow         
        //   546: iconst_1       
        //   547: putstatic       com/jetbrains/cidr/cpp/cmake/CMakeRunner.ourCygwinPermissionsFixed:Z
        //   550: aload           6
        //   552: dup            
        //   553: ifnonnull       590
        //   556: new             Ljava/lang/IllegalStateException;
        //   559: dup            
        //   560: ldc             "@NotNull method %s.%s must not return null"
        //   562: ldc             2
        //   564: anewarray       Ljava/lang/Object;
        //   567: dup            
        //   568: ldc             0
        //   570: ldc             "com/jetbrains/cidr/cpp/cmake/CMakeRunner"
        //   572: aastore        
        //   573: dup            
        //   574: ldc             1
        //   576: ldc             "getCMakeExecutableWithFixedCygwinPermissions"
        //   578: aastore        
        //   579: invokestatic    java/lang/String.format:(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
        //   582: invokespecial   java/lang/IllegalStateException.<init>:(Ljava/lang/String;)V
        //   585: athrow         
        //   586: invokestatic    com/jetbrains/cidr/cpp/cmake/CMakeRunner.b:(Ljava/lang/Exception;)Ljava/lang/Exception;
        //   589: athrow         
        //   590: areturn        
        //    Exceptions:
        //  throws com.jetbrains.cidr.cpp.cmake.CMakeException
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                                       
        //  -----  -----  -----  -----  -------------------------------------------
        //  457    508    508    512    Ljava/io/IOException;
        //  417    453    453    457    Ljava/io/IOException;
        //  319    361    361    365    Ljava/io/IOException;
        //  314    324    327    331    Ljava/io/IOException;
        //  260    303    303    307    Ljava/io/IOException;
        //  254    266    269    273    Ljava/io/IOException;
        //  152    194    194    198    Ljava/io/IOException;
        //  147    157    160    164    Ljava/io/IOException;
        //  85     127    127    131    Ljava/io/IOException;
        //  72     90     93     97     Ljava/io/IOException;
        //  58     78     81     85     Ljava/io/IOException;
        //  54     65     68     72     Ljava/io/IOException;
        //  0      40     40     44     Ljava/io/IOException;
        //  417    519    522    534    Ljava/io/IOException;
        //  417    519    534    546    Lcom/intellij/execution/ExecutionException;
        //  546    586    586    590    Ljava/io/IOException;
        // 
        // The error that occurred was:
        // 
        // java.lang.IllegalStateException: Expression is linked from several locations: Label_0072:
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
    
    private static void a(@NotNull final CMakeEnvironment cMakeEnvironment, @NotNull final CidrToolSet set, @NotNull final File file) throws ExecutionException {
        try {
            if (cMakeEnvironment == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "environment", "com/jetbrains/cidr/cpp/cmake/CMakeRunner", "updateCMakePermissions"));
            }
        }
        catch (ExecutionException ex) {
            throw b((Exception)ex);
        }
        try {
            if (set == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "toolset", "com/jetbrains/cidr/cpp/cmake/CMakeRunner", "updateCMakePermissions"));
            }
        }
        catch (ExecutionException ex2) {
            throw b((Exception)ex2);
        }
        try {
            if (file == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "cmakeHome", "com/jetbrains/cidr/cpp/cmake/CMakeRunner", "updateCMakePermissions"));
            }
        }
        catch (ExecutionException ex3) {
            throw b((Exception)ex3);
        }
        final GeneralCommandLine generalCommandLine = new GeneralCommandLine();
        cMakeEnvironment.prepare(generalCommandLine, CidrToolEnvironment.PrepareFor.BUILD);
        generalCommandLine.setExePath(set.getSubFilePath("bin/chmod.exe"));
        generalCommandLine.addParameters(new String[] { "-R", "u+rwx", cMakeEnvironment.toEnvPath(file.getPath()) });
        CPPLog.LOG.info("Fixing CMake permissions for Cygwin: " + generalCommandLine.getCommandLineString());
        final ProcessOutput execAndGetOutput = ExecUtil.execAndGetOutput(generalCommandLine);
        try {
            if (execAndGetOutput.getExitCode() != 0) {
                throw new ExecutionException(execAndGetOutput.getStderr() + "\nProcess exited with code " + execAndGetOutput.getExitCode());
            }
        }
        catch (ExecutionException ex4) {
            throw b((Exception)ex4);
        }
    }
    
    private static CMakeException a(@NotNull final File file, @NotNull final File file2, @NotNull final Exception ex) throws CMakeException {
        try {
            if (file == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "cmakeHome", "com/jetbrains/cidr/cpp/cmake/CMakeRunner", "newPermissionsException"));
            }
        }
        catch (CMakeException ex2) {
            throw b(ex2);
        }
        try {
            if (file2 == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "originalCMakeHome", "com/jetbrains/cidr/cpp/cmake/CMakeRunner", "newPermissionsException"));
            }
        }
        catch (CMakeException ex3) {
            throw b(ex3);
        }
        try {
            if (ex == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "cause", "com/jetbrains/cidr/cpp/cmake/CMakeRunner", "newPermissionsException"));
            }
        }
        catch (CMakeException ex4) {
            throw b(ex4);
        }
        return new CMakeException("Failed to update files permissions inside " + file + "\n \nCygwin requires correct permissions for the CMake files, otherwise CMake will fail to generate the project.\nIDE tried to work this around, but failed; you can do this workaround manually:\n  1) copy " + file2 + " to " + file + "\n  2) in Cygwin console execute 'chmod -R u+rwx " + file + "'\nFor more information check http://trentm.com/2005/03/cygwin-ntfs-permissions-badness.html\n \nTo disable this workaround in future, set '" + "cidr.cygwin.cmakePermissionsFix" + "' to 'false' in Registry (use Find Action).\n \n\nFailure reason: " + ex.getMessage(), ex);
    }
    
    @Contract("null -> null")
    public String toEnvPath(@Nullable final String s) {
        return this.myEnvironment.toEnvPath(s);
    }
    
    static {
        FIX_CYGWIN_PERMISSIONS = Registry.is("cidr.cygwin.cmakePermissionsFix");
        CMakeRunner.ourCygwinPermissionsFixed = false;
        CMakeRunner.ourEmulateCMakeExecutionErrorInTests = false;
    }
    
    private static Exception b(final Exception ex) {
        return ex;
    }
    
    public interface Listener
    {
        default void processStarted(@NotNull final ProcessHandler processHandler) {
            try {
                if (processHandler == null) {
                    throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "handler", "com/jetbrains/cidr/cpp/cmake/CMakeRunner$Listener", "processStarted"));
                }
            }
            catch (IllegalArgumentException ex) {
                throw a(ex);
            }
        }
        
        default IllegalArgumentException a(final IllegalArgumentException ex) {
            return ex;
        }
    }
}
