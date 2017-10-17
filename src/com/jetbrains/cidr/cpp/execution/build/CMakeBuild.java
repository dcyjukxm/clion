// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.cpp.execution.build;

import com.intellij.openapi.diagnostic.Logger;
import com.jetbrains.cidr.cpp.CPPLog;
import com.jetbrains.cidr.cpp.cmake.CMakeException;
import com.intellij.execution.process.ProcessHandler;
import com.intellij.openapi.wm.ToolWindow;
import com.intellij.execution.ui.ConsoleViewContentType;
import com.intellij.openapi.ui.Messages;
import com.intellij.ui.content.ContentManagerListener;
import com.intellij.ui.content.ContentManagerEvent;
import com.intellij.ui.content.ContentManager;
import com.intellij.ui.content.ContentManagerAdapter;
import com.intellij.openapi.Disposable;
import com.intellij.openapi.util.Disposer;
import com.intellij.util.ContentsUtil;
import com.intellij.ui.content.ContentFactory;
import com.jetbrains.cidr.lang.toolchains.CidrToolEnvironment;
import com.jetbrains.cidr.cpp.cmake.console.CMakeConsoleBuilder;
import com.intellij.execution.ui.ConsoleView;
import com.intellij.ui.content.Content;
import com.intellij.openapi.wm.ToolWindowManager;
import com.jetbrains.cidr.cpp.toolchains.CPPEnvironment;
import com.jetbrains.cidr.cpp.cmake.CMakeEnvironment;
import java.util.Iterator;
import com.intellij.execution.runners.ExecutionUtil;
import com.intellij.openapi.wm.ToolWindowId;
import java.util.List;
import com.jetbrains.cidr.execution.build.BuildListener;
import com.intellij.util.ui.UIUtil;
import java.io.File;
import com.intellij.openapi.vfs.VfsUtilCore;
import com.intellij.openapi.roots.ProjectRootManager;
import java.util.Collection;
import com.intellij.util.containers.ContainerUtil;
import com.jetbrains.cidr.cpp.cmake.model.CMakeTarget;
import com.intellij.util.SmartList;
import com.jetbrains.cidr.cpp.cmake.workspace.CMakeWorkspace;
import com.jetbrains.cidr.execution.build.CidrBuild;
import com.jetbrains.cidr.cpp.cmake.model.CMakeConfiguration;
import com.intellij.execution.ExecutionException;
import com.intellij.util.ThrowableConvertor;
import com.jetbrains.cidr.CidrBundle;
import org.jetbrains.annotations.Nullable;
import com.intellij.execution.process.ProcessListener;
import com.jetbrains.cidr.execution.ExecutionResult;
import com.jetbrains.cidr.cpp.execution.CMakeAppRunConfiguration;
import org.jetbrains.annotations.NotNull;
import com.intellij.openapi.project.Project;
import com.intellij.notification.NotificationGroup;

public class CMakeBuild
{
    private static final NotificationGroup LOG_NOTIFICATION_GROUP;
    
    public static ExecutionResult<Boolean> build(@NotNull final Project project, @NotNull final CMakeAppRunConfiguration.BuildAndRunConfigurations buildAndRunConfigurations) {
        try {
            if (project == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "project", "com/jetbrains/cidr/cpp/execution/build/CMakeBuild", "build"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        try {
            if (buildAndRunConfigurations == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "configurations", "com/jetbrains/cidr/cpp/execution/build/CMakeBuild", "build"));
            }
        }
        catch (IllegalArgumentException ex2) {
            throw a(ex2);
        }
        return build(project, buildAndRunConfigurations, null);
    }
    
    public static ExecutionResult<Boolean> build(@NotNull final Project project, @NotNull final CMakeAppRunConfiguration.BuildAndRunConfigurations buildAndRunConfigurations, @Nullable final ProcessListener processListener) {
        try {
            if (project == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "project", "com/jetbrains/cidr/cpp/execution/build/CMakeBuild", "build"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        try {
            if (buildAndRunConfigurations == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "configurations", "com/jetbrains/cidr/cpp/execution/build/CMakeBuild", "build"));
            }
        }
        catch (IllegalArgumentException ex2) {
            throw a(ex2);
        }
        return a(project, CidrBundle.message("build", new Object[0]), CidrBundle.message("build.progress", new Object[0]), buildAndRunConfigurations, processListener, false, (ThrowableConvertor<Boolean, Boolean, ExecutionException>)(b -> {
            try {
                if (buildAndRunConfigurations == null) {
                    throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "configurations", "com/jetbrains/cidr/cpp/execution/build/CMakeBuild", "lambda$build$0"));
                }
            }
            catch (ExecutionException ex) {
                throw a((Throwable)ex);
            }
            try {
                if (project == null) {
                    throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "project", "com/jetbrains/cidr/cpp/execution/build/CMakeBuild", "lambda$build$0"));
                }
            }
            catch (ExecutionException ex2) {
                throw a((Throwable)ex2);
            }
            Label_0167: {
                Label_0107: {
                    Logger log;
                    try {
                        log = CPPLog.LOG;
                        if (buildAndRunConfigurations.runConfiguration != null) {
                            final boolean b2 = true;
                            break Label_0107;
                        }
                    }
                    catch (ExecutionException ex3) {
                        throw a((Throwable)ex3);
                    }
                    final boolean b2 = false;
                    try {
                        log.assertTrue(b2);
                        if (!b) {
                            break Label_0167;
                        }
                        final Project project2 = project;
                        final String s = "build";
                        final int n = 0;
                        final Object[] array = new Object[n];
                        final String s2 = CidrBundle.message(s, array);
                        final String s3 = "build.progress";
                        final int n2 = 0;
                        final Object[] array2 = new Object[n2];
                        final String s4 = CidrBundle.message(s3, array2);
                        final CMakeAppRunConfiguration.BuildAndRunConfigurations buildAndRunConfigurations2 = buildAndRunConfigurations;
                        final CMakeConfiguration cMakeConfiguration = buildAndRunConfigurations2.runConfiguration;
                        final String s5 = null;
                        final ProcessListener processListener2 = processListener;
                        final boolean b3 = false;
                        final boolean b4 = false;
                        final ExecutionResult<Boolean> executionResult = execute(project2, s2, s4, cMakeConfiguration, s5, processListener2, b3, b4);
                        final Boolean b5 = executionResult.get();
                        final Boolean b6 = b5;
                        final boolean b7 = b6;
                        if (b7) {
                            break Label_0167;
                        }
                        break Label_0167;
                    }
                    catch (ExecutionException ex4) {
                        throw a((Throwable)ex4);
                    }
                }
                try {
                    final Project project2 = project;
                    final String s = "build";
                    final int n = 0;
                    final Object[] array = new Object[n];
                    final String s2 = CidrBundle.message(s, array);
                    final String s3 = "build.progress";
                    final int n2 = 0;
                    final Object[] array2 = new Object[n2];
                    final String s4 = CidrBundle.message(s3, array2);
                    final CMakeAppRunConfiguration.BuildAndRunConfigurations buildAndRunConfigurations2 = buildAndRunConfigurations;
                    final CMakeConfiguration cMakeConfiguration = buildAndRunConfigurations2.runConfiguration;
                    final String s5 = null;
                    final ProcessListener processListener2 = processListener;
                    final boolean b3 = false;
                    final boolean b4 = false;
                    final ExecutionResult<Boolean> executionResult = execute(project2, s2, s4, cMakeConfiguration, s5, processListener2, b3, b4);
                    final Boolean b5 = executionResult.get();
                    final Boolean b6 = b5;
                    final boolean b7 = b6;
                    if (b7) {
                        return true;
                    }
                }
                catch (ExecutionException ex5) {
                    throw a((Throwable)ex5);
                }
            }
            return false;
        }));
    }
    
    public static ExecutionResult<Boolean> clean(@NotNull final Project project, @NotNull final CMakeAppRunConfiguration.BuildAndRunConfigurations buildAndRunConfigurations) {
        try {
            if (project == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "project", "com/jetbrains/cidr/cpp/execution/build/CMakeBuild", "clean"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        try {
            if (buildAndRunConfigurations == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "buildConfiguration", "com/jetbrains/cidr/cpp/execution/build/CMakeBuild", "clean"));
            }
        }
        catch (IllegalArgumentException ex2) {
            throw a(ex2);
        }
        return clean(project, buildAndRunConfigurations, null);
    }
    
    public static ExecutionResult<Boolean> clean(@NotNull final Project project, @NotNull final CMakeAppRunConfiguration.BuildAndRunConfigurations buildAndRunConfigurations, @Nullable final ProcessListener processListener) {
        try {
            if (project == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "project", "com/jetbrains/cidr/cpp/execution/build/CMakeBuild", "clean"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        try {
            if (buildAndRunConfigurations == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "configurations", "com/jetbrains/cidr/cpp/execution/build/CMakeBuild", "clean"));
            }
        }
        catch (IllegalArgumentException ex2) {
            throw a(ex2);
        }
        return a(project, CidrBundle.message("build.clean", new Object[0]), CidrBundle.message("build.clean.progress", new Object[0]), buildAndRunConfigurations, processListener, true, null);
    }
    
    private static ExecutionResult<Boolean> a(@NotNull final Project p0, @NotNull final String p1, @NotNull final String p2, @NotNull final CMakeAppRunConfiguration.BuildAndRunConfigurations p3, @Nullable final ProcessListener p4, final boolean p5, @Nullable final ThrowableConvertor<Boolean, Boolean, ExecutionException> p6) {
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
        //    18: ldc             "project"
        //    20: aastore        
        //    21: dup            
        //    22: ldc             1
        //    24: ldc             "com/jetbrains/cidr/cpp/execution/build/CMakeBuild"
        //    26: aastore        
        //    27: dup            
        //    28: ldc             2
        //    30: ldc             "execute"
        //    32: aastore        
        //    33: invokestatic    java/lang/String.format:(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
        //    36: invokespecial   java/lang/IllegalArgumentException.<init>:(Ljava/lang/String;)V
        //    39: athrow         
        //    40: invokestatic    com/jetbrains/cidr/cpp/execution/build/CMakeBuild.a:(Ljava/lang/Throwable;)Ljava/lang/Throwable;
        //    43: athrow         
        //    44: aload_1        
        //    45: ifnonnull       88
        //    48: new             Ljava/lang/IllegalArgumentException;
        //    51: dup            
        //    52: ldc             "Argument for @NotNull parameter '%s' of %s.%s must not be null"
        //    54: ldc             3
        //    56: anewarray       Ljava/lang/Object;
        //    59: dup            
        //    60: ldc             0
        //    62: ldc             "taskName"
        //    64: aastore        
        //    65: dup            
        //    66: ldc             1
        //    68: ldc             "com/jetbrains/cidr/cpp/execution/build/CMakeBuild"
        //    70: aastore        
        //    71: dup            
        //    72: ldc             2
        //    74: ldc             "execute"
        //    76: aastore        
        //    77: invokestatic    java/lang/String.format:(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
        //    80: invokespecial   java/lang/IllegalArgumentException.<init>:(Ljava/lang/String;)V
        //    83: athrow         
        //    84: invokestatic    com/jetbrains/cidr/cpp/execution/build/CMakeBuild.a:(Ljava/lang/Throwable;)Ljava/lang/Throwable;
        //    87: athrow         
        //    88: aload_2        
        //    89: ifnonnull       132
        //    92: new             Ljava/lang/IllegalArgumentException;
        //    95: dup            
        //    96: ldc             "Argument for @NotNull parameter '%s' of %s.%s must not be null"
        //    98: ldc             3
        //   100: anewarray       Ljava/lang/Object;
        //   103: dup            
        //   104: ldc             0
        //   106: ldc             "progressTitle"
        //   108: aastore        
        //   109: dup            
        //   110: ldc             1
        //   112: ldc             "com/jetbrains/cidr/cpp/execution/build/CMakeBuild"
        //   114: aastore        
        //   115: dup            
        //   116: ldc             2
        //   118: ldc             "execute"
        //   120: aastore        
        //   121: invokestatic    java/lang/String.format:(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
        //   124: invokespecial   java/lang/IllegalArgumentException.<init>:(Ljava/lang/String;)V
        //   127: athrow         
        //   128: invokestatic    com/jetbrains/cidr/cpp/execution/build/CMakeBuild.a:(Ljava/lang/Throwable;)Ljava/lang/Throwable;
        //   131: athrow         
        //   132: aload_3        
        //   133: ifnonnull       176
        //   136: new             Ljava/lang/IllegalArgumentException;
        //   139: dup            
        //   140: ldc             "Argument for @NotNull parameter '%s' of %s.%s must not be null"
        //   142: ldc             3
        //   144: anewarray       Ljava/lang/Object;
        //   147: dup            
        //   148: ldc             0
        //   150: ldc             "configurations"
        //   152: aastore        
        //   153: dup            
        //   154: ldc             1
        //   156: ldc             "com/jetbrains/cidr/cpp/execution/build/CMakeBuild"
        //   158: aastore        
        //   159: dup            
        //   160: ldc             2
        //   162: ldc             "execute"
        //   164: aastore        
        //   165: invokestatic    java/lang/String.format:(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
        //   168: invokespecial   java/lang/IllegalArgumentException.<init>:(Ljava/lang/String;)V
        //   171: athrow         
        //   172: invokestatic    com/jetbrains/cidr/cpp/execution/build/CMakeBuild.a:(Ljava/lang/Throwable;)Ljava/lang/Throwable;
        //   175: athrow         
        //   176: aload_0        
        //   177: aload_1        
        //   178: aload_2        
        //   179: aload_3        
        //   180: getfield        com/jetbrains/cidr/cpp/execution/CMakeAppRunConfiguration$BuildAndRunConfigurations.buildConfiguration:Lcom/jetbrains/cidr/cpp/cmake/model/CMakeConfiguration;
        //   183: aload_3        
        //   184: getfield        com/jetbrains/cidr/cpp/execution/CMakeAppRunConfiguration$BuildAndRunConfigurations.explicitBuildTargetName:Ljava/lang/String;
        //   187: aload           4
        //   189: iload           5
        //   191: iconst_1       
        //   192: invokestatic    com/jetbrains/cidr/cpp/execution/build/CMakeBuild.execute:(Lcom/intellij/openapi/project/Project;Ljava/lang/String;Ljava/lang/String;Lcom/jetbrains/cidr/cpp/cmake/model/CMakeConfiguration;Ljava/lang/String;Lcom/intellij/execution/process/ProcessListener;ZZ)Lcom/jetbrains/cidr/execution/ExecutionResult;
        //   195: astore          7
        //   197: aload_3        
        //   198: getfield        com/jetbrains/cidr/cpp/execution/CMakeAppRunConfiguration$BuildAndRunConfigurations.runConfiguration:Lcom/jetbrains/cidr/cpp/cmake/model/CMakeConfiguration;
        //   201: ifnull          230
        //   204: aload_3        
        //   205: getfield        com/jetbrains/cidr/cpp/execution/CMakeAppRunConfiguration$BuildAndRunConfigurations.explicitBuildTargetName:Ljava/lang/String;
        //   208: ifnonnull       230
        //   211: goto            218
        //   214: invokestatic    com/jetbrains/cidr/cpp/execution/build/CMakeBuild.a:(Ljava/lang/Throwable;)Ljava/lang/Throwable;
        //   217: athrow         
        //   218: aload           6
        //   220: ifnonnull       237
        //   223: goto            230
        //   226: invokestatic    com/jetbrains/cidr/cpp/execution/build/CMakeBuild.a:(Ljava/lang/Throwable;)Ljava/lang/Throwable;
        //   229: athrow         
        //   230: aload           7
        //   232: areturn        
        //   233: invokestatic    com/jetbrains/cidr/cpp/execution/build/CMakeBuild.a:(Ljava/lang/Throwable;)Ljava/lang/Throwable;
        //   236: athrow         
        //   237: new             Lcom/jetbrains/cidr/execution/ExecutionResult;
        //   240: dup            
        //   241: invokespecial   com/jetbrains/cidr/execution/ExecutionResult.<init>:()V
        //   244: astore          8
        //   246: invokestatic    com/intellij/openapi/application/ApplicationManager.getApplication:()Lcom/intellij/openapi/application/Application;
        //   249: aload           8
        //   251: aload           6
        //   253: aload           7
        //   255: invokedynamic   run:(Lcom/jetbrains/cidr/execution/ExecutionResult;Lcom/intellij/util/ThrowableConvertor;Lcom/jetbrains/cidr/execution/ExecutionResult;)Ljava/lang/Runnable;
        //   260: invokeinterface com/intellij/openapi/application/Application.executeOnPooledThread:(Ljava/lang/Runnable;)Ljava/util/concurrent/Future;
        //   265: pop            
        //   266: aload           8
        //   268: areturn        
        //    Signature:
        //  (Lcom/intellij/openapi/project/Project;Ljava/lang/String;Ljava/lang/String;Lcom/jetbrains/cidr/cpp/execution/CMakeAppRunConfiguration$BuildAndRunConfigurations;Lcom/intellij/execution/process/ProcessListener;ZLcom/intellij/util/ThrowableConvertor<Ljava/lang/Boolean;Ljava/lang/Boolean;Lcom/intellij/execution/ExecutionException;>;)Lcom/jetbrains/cidr/execution/ExecutionResult<Ljava/lang/Boolean;>;
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                                
        //  -----  -----  -----  -----  ------------------------------------
        //  0      40     40     44     Ljava/lang/IllegalArgumentException;
        //  44     84     84     88     Ljava/lang/IllegalArgumentException;
        //  88     128    128    132    Ljava/lang/IllegalArgumentException;
        //  132    172    172    176    Ljava/lang/IllegalArgumentException;
        //  197    211    214    218    Ljava/lang/IllegalArgumentException;
        //  204    223    226    230    Ljava/lang/IllegalArgumentException;
        //  218    233    233    237    Ljava/lang/IllegalArgumentException;
        // 
        // The error that occurred was:
        // 
        // java.lang.IllegalStateException: Expression is linked from several locations: Label_0218:
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
    
    public static ExecutionResult<Boolean> execute(@NotNull final Project project, @NotNull final String s, @NotNull final String s2, @NotNull final CMakeConfiguration cMakeConfiguration, @Nullable final String s3, @Nullable final ProcessListener processListener, final boolean b, final boolean b2) {
        try {
            if (project == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "project", "com/jetbrains/cidr/cpp/execution/build/CMakeBuild", "execute"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        try {
            if (s == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "taskName", "com/jetbrains/cidr/cpp/execution/build/CMakeBuild", "execute"));
            }
        }
        catch (IllegalArgumentException ex2) {
            throw a(ex2);
        }
        try {
            if (s2 == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "progressTitle", "com/jetbrains/cidr/cpp/execution/build/CMakeBuild", "execute"));
            }
        }
        catch (IllegalArgumentException ex3) {
            throw a(ex3);
        }
        try {
            if (cMakeConfiguration == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "buildConfiguration", "com/jetbrains/cidr/cpp/execution/build/CMakeBuild", "execute"));
            }
        }
        catch (IllegalArgumentException ex4) {
            throw a(ex4);
        }
        final IllegalArgumentException ex5;
        final IllegalArgumentException ex7;
        final IllegalArgumentException ex9;
        final CMakeWorkspace cMakeWorkspace;
        final IllegalArgumentException ex11;
        final IllegalArgumentException ex12;
        final IllegalArgumentException ex13;
        final CMakeWorkspace cMakeWorkspace2;
        SmartList list;
        final File file;
        final Iterator<CMakeTarget> iterator;
        final Iterator<CMakeConfiguration> iterator2;
        final Iterator<String> iterator3;
        final CidrBuild.BuildContext buildContext;
        final CMakeEnvironment cMakeEnvironment;
        final String s4;
        ExecutionException ex14;
        final IllegalArgumentException ex15;
        final CPPEnvironment cppEnvironment;
        final File file2;
        final CidrBuild.BuildContext buildContext2;
        return CidrBuild.execute(project, new CidrBuild.BuildContext(project), s, s2, () -> {
            try {
                if (project == null) {
                    new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "project", "com/jetbrains/cidr/cpp/execution/build/CMakeBuild", "lambda$execute$4"));
                    throw ex5;
                }
            }
            catch (IllegalArgumentException ex6) {
                throw a(ex6);
            }
            try {
                if (cMakeConfiguration == null) {
                    new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "buildConfiguration", "com/jetbrains/cidr/cpp/execution/build/CMakeBuild", "lambda$execute$4"));
                    throw ex7;
                }
            }
            catch (IllegalArgumentException ex8) {
                throw a(ex8);
            }
            try {
                if (s == null) {
                    new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "taskName", "com/jetbrains/cidr/cpp/execution/build/CMakeBuild", "lambda$execute$4"));
                    throw ex9;
                }
            }
            catch (IllegalArgumentException ex10) {
                throw a(ex10);
            }
            CMakeWorkspace.getInstance(project);
            cMakeWorkspace.lockModelDuring(() -> {
                try {
                    if (cMakeConfiguration == null) {
                        new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "buildConfiguration", "com/jetbrains/cidr/cpp/execution/build/CMakeBuild", "lambda$null$3"));
                        throw ex11;
                    }
                }
                catch (Throwable t) {
                    throw a(t);
                }
                try {
                    if (project == null) {
                        new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "project", "com/jetbrains/cidr/cpp/execution/build/CMakeBuild", "lambda$null$3"));
                        throw ex12;
                    }
                }
                catch (Throwable t2) {
                    throw a(t2);
                }
                try {
                    if (s == null) {
                        new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "taskName", "com/jetbrains/cidr/cpp/execution/build/CMakeBuild", "lambda$null$3"));
                        throw ex13;
                    }
                }
                catch (Throwable t3) {
                    throw a(t3);
                }
                try {
                    cMakeWorkspace2.getEnvironmentFor(cMakeConfiguration);
                    cMakeConfiguration.getConfigurationGenerationDir();
                    cMakeWorkspace2.getModelProjectDir();
                    list = new SmartList();
                    list.add((Object)file);
                    cMakeWorkspace2.getModelTargets().iterator();
                    while (iterator.hasNext()) {
                        iterator.next().getBuildConfigurations().iterator();
                        while (iterator2.hasNext()) {
                            ContainerUtil.addIfNotNull((Collection)list, (Object)iterator2.next().getProductFile());
                        }
                    }
                    ProjectRootManager.getInstance(project).getContentRootUrls().iterator();
                    while (iterator3.hasNext()) {
                        ContainerUtil.addIfNotNull((Collection)list, (Object)new File(VfsUtilCore.urlToPath((String)iterator3.next())));
                    }
                    try {
                        Label_0342_1_1: {
                            try {
                                if (b) {
                                    break Label_0342_1_1;
                                }
                            }
                            catch (Throwable t4) {
                                throw a(t4);
                            }
                            try {
                                if (s3 != null) {
                                    break Label_0342_1_1;
                                }
                            }
                            catch (Throwable t5) {
                                throw a(t5);
                            }
                            cMakeConfiguration.getTarget().getName();
                        }
                        buildContext.processHandler = a(cMakeEnvironment, cMakeWorkspace2.getSettings().getEffectiveBuildOptionsList(), file, s4);
                    }
                    catch (Throwable t6) {
                        try {
                            if (t6 instanceof ExecutionException) {
                                ex14 = (ExecutionException)t6;
                                throw ex14;
                            }
                        }
                        catch (Throwable t7) {
                            throw a(t7);
                        }
                        // new(com.intellij.execution.ExecutionException.class)
                        new ExecutionException(t6);
                        throw ex14;
                    }
                    try {
                        if (processListener != null) {
                            buildContext.processHandler.addProcessListener(processListener);
                        }
                    }
                    catch (Throwable t8) {
                        throw a(t8);
                    }
                    UIUtil.invokeAndWaitIfNeeded(() -> {
                        try {
                            if (project == null) {
                                new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "project", "com/jetbrains/cidr/cpp/execution/build/CMakeBuild", "lambda$null$2"));
                                throw ex15;
                            }
                        }
                        catch (IllegalArgumentException ex16) {
                            throw a(ex16);
                        }
                        createBuildListenerAndConsole(project, cppEnvironment, file2, buildContext2, b2);
                        return;
                    });
                    CidrBuild.startProcess(project, CMakeBuild.LOG_NOTIFICATION_GROUP, s, buildContext, null, (List<File>)list);
                    buildContext.processHandler.waitFor();
                }
                catch (ExecutionException ex17) {
                    buildContext.error((Throwable)ex17);
                    ExecutionUtil.handleExecutionError(project, ToolWindowId.MESSAGES_WINDOW, s, (Throwable)ex17);
                }
                catch (Throwable t9) {
                    buildContext.error(t9);
                }
                return null;
            });
        });
    }
    
    public static void createBuildListenerAndConsole(@NotNull final Project project, @NotNull final CPPEnvironment cppEnvironment, @Nullable final File file, @NotNull final CidrBuild.BuildContext buildContext, final boolean b) {
        try {
            if (project == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "project", "com/jetbrains/cidr/cpp/execution/build/CMakeBuild", "createBuildListenerAndConsole"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        try {
            if (cppEnvironment == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "environment", "com/jetbrains/cidr/cpp/execution/build/CMakeBuild", "createBuildListenerAndConsole"));
            }
        }
        catch (IllegalArgumentException ex2) {
            throw a(ex2);
        }
        try {
            if (buildContext == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "context", "com/jetbrains/cidr/cpp/execution/build/CMakeBuild", "createBuildListenerAndConsole"));
            }
        }
        catch (IllegalArgumentException ex3) {
            throw a(ex3);
        }
        final ToolWindow toolWindow = ToolWindowManager.getInstance(project).getToolWindow(ToolWindowId.MESSAGES_WINDOW);
        final ContentManager contentManager = toolWindow.getContentManager();
        final Content content = (Content)ContainerUtil.find((Object[])contentManager.getContents(), content -> content.getDisplayName().equals(CidrBundle.message("build.logToolWindowName", new Object[0])));
        ConsoleView console = null;
        Label_0314: {
            Label_0290: {
                Label_0190: {
                    try {
                        if (b) {
                            break Label_0190;
                        }
                        final Content content2 = content;
                        if (content2 != null) {
                            break Label_0190;
                        }
                        break Label_0190;
                    }
                    catch (IllegalArgumentException ex4) {
                        throw a(ex4);
                    }
                    try {
                        final Content content2 = content;
                        if (content2 != null) {
                            if (content.getComponent() instanceof ConsoleView) {
                                break Label_0290;
                            }
                        }
                    }
                    catch (IllegalArgumentException ex5) {
                        throw a(ex5);
                    }
                }
                console = new CMakeConsoleBuilder(project, cppEnvironment, file).getConsole();
                final Content content3 = ContentFactory.SERVICE.getInstance().createContent(console.getComponent(), CidrBundle.message("build.logToolWindowName", new Object[0]), true);
                ContentsUtil.addOrReplaceContent(contentManager, content3, true);
                Disposer.register((Disposable)content3, (Disposable)console);
                contentManager.addContentManagerListener((ContentManagerListener)new ContentManagerAdapter() {
                    public void contentRemoved(final ContentManagerEvent contentManagerEvent) {
                        if (contentManagerEvent.getContent() != content3) {
                            return;
                        }
                        contentManager.removeContentManagerListener((ContentManagerListener)this);
                    }
                    
                    public void contentRemoveQuery(final ContentManagerEvent contentManagerEvent) {
                        if (contentManagerEvent.getContent() != content3) {
                            return;
                        }
                        if (!buildContext.indicator.isCanceled() && !buildContext.processHandler.isProcessTerminated() && !buildContext.processHandler.isProcessTerminating()) {
                            if (Messages.showYesNoDialog(project, CidrBundle.message("build.inProgress.cancelQuestion", new Object[0]), CidrBundle.message("build.inProgress", new Object[0]), Messages.getQuestionIcon()) == 0) {
                                buildContext.indicator.cancel();
                            }
                            else {
                                contentManagerEvent.consume();
                            }
                        }
                    }
                });
                break Label_0314;
            }
            console = (ConsoleView)content.getComponent();
            console.print("\n", ConsoleViewContentType.NORMAL_OUTPUT);
        }
        console.attachToProcess(buildContext.processHandler);
        toolWindow.activate((Runnable)null, false);
    }
    
    private static ProcessHandler a(@NotNull final CMakeEnvironment p0, @NotNull final List<String> p1, @NotNull final File p2, @NotNull final String p3) throws CMakeException, ExecutionException {
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
        //    24: ldc             "com/jetbrains/cidr/cpp/execution/build/CMakeBuild"
        //    26: aastore        
        //    27: dup            
        //    28: ldc             2
        //    30: ldc             "createBuildProcess"
        //    32: aastore        
        //    33: invokestatic    java/lang/String.format:(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
        //    36: invokespecial   java/lang/IllegalArgumentException.<init>:(Ljava/lang/String;)V
        //    39: athrow         
        //    40: invokestatic    com/jetbrains/cidr/cpp/execution/build/CMakeBuild.a:(Ljava/lang/Throwable;)Ljava/lang/Throwable;
        //    43: athrow         
        //    44: aload_1        
        //    45: ifnonnull       88
        //    48: new             Ljava/lang/IllegalArgumentException;
        //    51: dup            
        //    52: ldc             "Argument for @NotNull parameter '%s' of %s.%s must not be null"
        //    54: ldc             3
        //    56: anewarray       Ljava/lang/Object;
        //    59: dup            
        //    60: ldc             0
        //    62: ldc             "buildOptions"
        //    64: aastore        
        //    65: dup            
        //    66: ldc             1
        //    68: ldc             "com/jetbrains/cidr/cpp/execution/build/CMakeBuild"
        //    70: aastore        
        //    71: dup            
        //    72: ldc             2
        //    74: ldc             "createBuildProcess"
        //    76: aastore        
        //    77: invokestatic    java/lang/String.format:(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
        //    80: invokespecial   java/lang/IllegalArgumentException.<init>:(Ljava/lang/String;)V
        //    83: athrow         
        //    84: invokestatic    com/jetbrains/cidr/cpp/execution/build/CMakeBuild.a:(Ljava/lang/Throwable;)Ljava/lang/Throwable;
        //    87: athrow         
        //    88: aload_2        
        //    89: ifnonnull       132
        //    92: new             Ljava/lang/IllegalArgumentException;
        //    95: dup            
        //    96: ldc             "Argument for @NotNull parameter '%s' of %s.%s must not be null"
        //    98: ldc             3
        //   100: anewarray       Ljava/lang/Object;
        //   103: dup            
        //   104: ldc             0
        //   106: ldc             "generatedDir"
        //   108: aastore        
        //   109: dup            
        //   110: ldc             1
        //   112: ldc             "com/jetbrains/cidr/cpp/execution/build/CMakeBuild"
        //   114: aastore        
        //   115: dup            
        //   116: ldc             2
        //   118: ldc             "createBuildProcess"
        //   120: aastore        
        //   121: invokestatic    java/lang/String.format:(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
        //   124: invokespecial   java/lang/IllegalArgumentException.<init>:(Ljava/lang/String;)V
        //   127: athrow         
        //   128: invokestatic    com/jetbrains/cidr/cpp/execution/build/CMakeBuild.a:(Ljava/lang/Throwable;)Ljava/lang/Throwable;
        //   131: athrow         
        //   132: aload_3        
        //   133: ifnonnull       176
        //   136: new             Ljava/lang/IllegalArgumentException;
        //   139: dup            
        //   140: ldc             "Argument for @NotNull parameter '%s' of %s.%s must not be null"
        //   142: ldc             3
        //   144: anewarray       Ljava/lang/Object;
        //   147: dup            
        //   148: ldc             0
        //   150: ldc             "target"
        //   152: aastore        
        //   153: dup            
        //   154: ldc             1
        //   156: ldc             "com/jetbrains/cidr/cpp/execution/build/CMakeBuild"
        //   158: aastore        
        //   159: dup            
        //   160: ldc             2
        //   162: ldc             "createBuildProcess"
        //   164: aastore        
        //   165: invokestatic    java/lang/String.format:(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
        //   168: invokespecial   java/lang/IllegalArgumentException.<init>:(Ljava/lang/String;)V
        //   171: athrow         
        //   172: invokestatic    com/jetbrains/cidr/cpp/execution/build/CMakeBuild.a:(Ljava/lang/Throwable;)Ljava/lang/Throwable;
        //   175: athrow         
        //   176: new             Lcom/jetbrains/cidr/cpp/cmake/CMakeRunner;
        //   179: dup            
        //   180: aload_0        
        //   181: invokespecial   com/jetbrains/cidr/cpp/cmake/CMakeRunner.<init>:(Lcom/jetbrains/cidr/cpp/cmake/CMakeEnvironment;)V
        //   184: astore          4
        //   186: aload_0        
        //   187: invokevirtual   com/jetbrains/cidr/cpp/cmake/CMakeEnvironment.getToolSet:()Lcom/jetbrains/cidr/toolchains/CidrToolSet;
        //   190: astore          5
        //   192: getstatic       com/intellij/openapi/util/SystemInfo.isWindows:Z
        //   195: ifeq            225
        //   198: aload           5
        //   200: ifnull          282
        //   203: goto            210
        //   206: invokestatic    com/jetbrains/cidr/cpp/execution/build/CMakeBuild.a:(Ljava/lang/Throwable;)Ljava/lang/Throwable;
        //   209: athrow         
        //   210: aload           5
        //   212: invokevirtual   com/jetbrains/cidr/toolchains/CidrToolSet.isCygwin:()Z
        //   215: ifeq            282
        //   218: goto            225
        //   221: invokestatic    com/jetbrains/cidr/cpp/execution/build/CMakeBuild.a:(Ljava/lang/Throwable;)Ljava/lang/Throwable;
        //   224: athrow         
        //   225: invokestatic    com/intellij/execution/configurations/PtyCommandLine.isEnabled:()Z
        //   228: ifne            274
        //   231: goto            238
        //   234: invokestatic    com/jetbrains/cidr/cpp/execution/build/CMakeBuild.a:(Ljava/lang/Throwable;)Ljava/lang/Throwable;
        //   237: athrow         
        //   238: invokestatic    com/intellij/openapi/application/ApplicationManager.getApplication:()Lcom/intellij/openapi/application/Application;
        //   241: invokeinterface com/intellij/openapi/application/Application.isInternal:()Z
        //   246: ifne            274
        //   249: goto            256
        //   252: invokestatic    com/jetbrains/cidr/cpp/execution/build/CMakeBuild.a:(Ljava/lang/Throwable;)Ljava/lang/Throwable;
        //   255: athrow         
        //   256: invokestatic    com/intellij/openapi/application/ApplicationManager.getApplication:()Lcom/intellij/openapi/application/Application;
        //   259: invokeinterface com/intellij/openapi/application/Application.isUnitTestMode:()Z
        //   264: ifeq            282
        //   267: goto            274
        //   270: invokestatic    com/jetbrains/cidr/cpp/execution/build/CMakeBuild.a:(Ljava/lang/Throwable;)Ljava/lang/Throwable;
        //   273: athrow         
        //   274: iconst_1       
        //   275: goto            283
        //   278: invokestatic    com/jetbrains/cidr/cpp/execution/build/CMakeBuild.a:(Ljava/lang/Throwable;)Ljava/lang/Throwable;
        //   281: athrow         
        //   282: iconst_0       
        //   283: istore          6
        //   285: aload           4
        //   287: aload_2        
        //   288: iconst_4       
        //   289: anewarray       Ljava/lang/String;
        //   292: dup            
        //   293: iconst_0       
        //   294: ldc             "--build"
        //   296: aastore        
        //   297: dup            
        //   298: iconst_1       
        //   299: aload_2        
        //   300: invokevirtual   java/io/File.getPath:()Ljava/lang/String;
        //   303: aastore        
        //   304: dup            
        //   305: iconst_2       
        //   306: ldc             "--target"
        //   308: aastore        
        //   309: dup            
        //   310: iconst_3       
        //   311: aload_3        
        //   312: aastore        
        //   313: invokestatic    java/util/Arrays.asList:([Ljava/lang/Object;)Ljava/util/List;
        //   316: iconst_1       
        //   317: iload           6
        //   319: invokevirtual   com/jetbrains/cidr/cpp/cmake/CMakeRunner.buildCommandLine:(Ljava/io/File;Ljava/util/List;ZZ)Lcom/intellij/execution/configurations/GeneralCommandLine;
        //   322: astore          7
        //   324: aload           7
        //   326: ldc             "--"
        //   328: invokevirtual   com/intellij/execution/configurations/GeneralCommandLine.addParameter:(Ljava/lang/String;)V
        //   331: aload           7
        //   333: aload_1        
        //   334: invokevirtual   com/intellij/execution/configurations/GeneralCommandLine.addParameters:(Ljava/util/List;)V
        //   337: getstatic       com/jetbrains/cidr/cpp/CPPLog.LOG:Lcom/intellij/openapi/diagnostic/Logger;
        //   340: new             Ljava/lang/StringBuilder;
        //   343: dup            
        //   344: invokespecial   java/lang/StringBuilder.<init>:()V
        //   347: ldc             "Building: "
        //   349: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   352: aload           7
        //   354: invokevirtual   com/intellij/execution/configurations/GeneralCommandLine.getCommandLineString:()Ljava/lang/String;
        //   357: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   360: invokevirtual   java/lang/StringBuilder.toString:()Ljava/lang/String;
        //   363: invokevirtual   com/intellij/openapi/diagnostic/Logger.info:(Ljava/lang/String;)V
        //   366: aload           7
        //   368: invokevirtual   com/intellij/execution/configurations/GeneralCommandLine.getEnvironment:()Ljava/util/Map;
        //   371: ldc             "TERM"
        //   373: ldc             "xterm"
        //   375: invokeinterface java/util/Map.put:(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
        //   380: pop            
        //   381: aload           7
        //   383: invokevirtual   com/intellij/execution/configurations/GeneralCommandLine.getEnvironment:()Ljava/util/Map;
        //   386: ldc             "GCC_COLORS"
        //   388: ldc             "error=01;31:warning=01;35:note=01;36:caret=01;32:locus=01:quote=01"
        //   390: invokeinterface java/util/Map.put:(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
        //   395: pop            
        //   396: iload           6
        //   398: ifeq            427
        //   401: new             Lcom/intellij/execution/process/ColoredProcessHandler;
        //   404: dup            
        //   405: aload           7
        //   407: invokespecial   com/intellij/execution/process/ColoredProcessHandler.<init>:(Lcom/intellij/execution/configurations/GeneralCommandLine;)V
        //   410: astore          8
        //   412: aload           8
        //   414: iconst_1       
        //   415: invokevirtual   com/intellij/execution/process/OSProcessHandler.setHasPty:(Z)V
        //   418: aload           8
        //   420: iconst_0       
        //   421: invokevirtual   com/intellij/execution/process/OSProcessHandler.setShouldDestroyProcessRecursively:(Z)V
        //   424: goto            438
        //   427: new             Lcom/intellij/execution/process/ColoredProcessHandler;
        //   430: dup            
        //   431: aload           7
        //   433: invokespecial   com/intellij/execution/process/ColoredProcessHandler.<init>:(Lcom/intellij/execution/configurations/GeneralCommandLine;)V
        //   436: astore          8
        //   438: aload           8
        //   440: areturn        
        //    Exceptions:
        //  throws com.jetbrains.cidr.cpp.cmake.CMakeException
        //  throws com.intellij.execution.ExecutionException
        //    Signature:
        //  (Lcom/jetbrains/cidr/cpp/cmake/CMakeEnvironment;Ljava/util/List<Ljava/lang/String;>;Ljava/io/File;Ljava/lang/String;)Lcom/intellij/execution/process/ProcessHandler;
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                                         
        //  -----  -----  -----  -----  ---------------------------------------------
        //  0      40     40     44     Lcom/jetbrains/cidr/cpp/cmake/CMakeException;
        //  44     84     84     88     Lcom/jetbrains/cidr/cpp/cmake/CMakeException;
        //  88     128    128    132    Lcom/jetbrains/cidr/cpp/cmake/CMakeException;
        //  132    172    172    176    Lcom/jetbrains/cidr/cpp/cmake/CMakeException;
        //  192    203    206    210    Lcom/jetbrains/cidr/cpp/cmake/CMakeException;
        //  198    218    221    225    Lcom/jetbrains/cidr/cpp/cmake/CMakeException;
        //  210    231    234    238    Lcom/jetbrains/cidr/cpp/cmake/CMakeException;
        //  225    249    252    256    Lcom/jetbrains/cidr/cpp/cmake/CMakeException;
        //  238    267    270    274    Lcom/jetbrains/cidr/cpp/cmake/CMakeException;
        //  256    278    278    282    Lcom/jetbrains/cidr/cpp/cmake/CMakeException;
        // 
        // The error that occurred was:
        // 
        // java.lang.IllegalStateException: Expression is linked from several locations: Label_0210:
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
    
    static {
        LOG_NOTIFICATION_GROUP = NotificationGroup.logOnlyGroup("CMake Build Log");
    }
    
    private static Throwable a(final Throwable t) {
        return t;
    }
}
