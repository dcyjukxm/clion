// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.cpp.execution;

import java.io.File;
import com.intellij.execution.configurations.ConfigurationType;
import com.intellij.execution.configurations.RunProfileState;
import com.jetbrains.cidr.execution.CidrBuildConfigurationHelper;
import com.intellij.openapi.util.InvalidDataException;
import org.jdom.Element;
import com.jetbrains.cidr.execution.testing.CidrLauncher;
import com.intellij.execution.ExecutionException;
import com.jetbrains.cidr.execution.CidrCommandLineState;
import com.intellij.execution.runners.ExecutionEnvironment;
import com.intellij.execution.Executor;
import com.intellij.execution.configurations.RunConfiguration;
import com.intellij.openapi.options.SettingsEditor;
import com.jetbrains.cidr.execution.BuildTargetData;
import com.intellij.openapi.application.ReadAction;
import com.jetbrains.cidr.execution.BuildTargetAndConfigurationData;
import com.intellij.execution.configurations.RuntimeConfigurationError;
import com.intellij.execution.configurations.RuntimeConfigurationException;
import com.jetbrains.cidr.cpp.cmake.workspace.CMakeResolveConfiguration;
import com.jetbrains.cidr.cpp.cmake.workspace.CMakeWorkspace;
import com.jetbrains.cidr.lang.workspace.OCResolveConfiguration;
import com.jetbrains.cidr.execution.BuildConfigurationProblems;
import org.jetbrains.annotations.NotNull;
import com.intellij.openapi.util.WriteExternalException;
import com.intellij.execution.configurations.ConfigurationFactory;
import com.intellij.openapi.project.Project;
import org.jetbrains.annotations.Nullable;
import com.jetbrains.cidr.execution.ExecutableData;
import com.jetbrains.cidr.execution.CidrExecutableDataHolder;
import com.jetbrains.cidr.cpp.cmake.model.CMakeTarget;
import com.jetbrains.cidr.cpp.cmake.model.CMakeConfiguration;
import com.jetbrains.cidr.execution.CidrRunConfiguration;

public class CMakeAppRunConfiguration extends CidrRunConfiguration<CMakeConfiguration, CMakeTarget> implements CidrExecutableDataHolder
{
    @Nullable
    private ExecutableData myExecutableData;
    @Nullable
    private String myExplicitBuildTargetName;
    public static final String ALL_TARGET = "all";
    
    protected CMakeAppRunConfiguration(final Project project, final ConfigurationFactory configurationFactory, final String s) {
        super(project, configurationFactory, s);
    }
    
    @NotNull
    public CMakeRunConfigurationType getType() {
        CMakeRunConfigurationType cMakeRunConfigurationType;
        try {
            cMakeRunConfigurationType = (CMakeRunConfigurationType)super.getType();
            if (cMakeRunConfigurationType == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/cpp/execution/CMakeAppRunConfiguration", "getType"));
            }
        }
        catch (WriteExternalException ex) {
            throw b((Exception)ex);
        }
        return cMakeRunConfigurationType;
    }
    
    @Nullable
    public BuildAndRunConfigurations getBuildAndRunConfigurations() {
        return this.getBuildAndRunConfigurations(null, false);
    }
    
    @Nullable
    public OCResolveConfiguration getResolveConfiguration() {
        final BuildAndRunConfigurations buildAndRunConfigurations = this.getBuildAndRunConfigurations();
        try {
            if (buildAndRunConfigurations == null) {
                return null;
            }
        }
        catch (WriteExternalException ex) {
            throw b((Exception)ex);
        }
        return CMakeWorkspace.getInstance(this.getProject()).getResolveConfigurationFor(buildAndRunConfigurations.buildConfiguration);
    }
    
    public void checkConfiguration() throws RuntimeConfigurationException {
        super.checkConfiguration();
        this.a(false);
    }
    
    public void checkSettingsBeforeRun() throws RuntimeConfigurationException {
        super.checkSettingsBeforeRun();
        this.a(true);
    }
    
    private void a(final boolean b) throws RuntimeConfigurationError {
        final BuildConfigurationProblems buildConfigurationProblems = new BuildConfigurationProblems();
        try {
            this.getBuildAndRunConfigurations(buildConfigurationProblems, b);
            if (buildConfigurationProblems.hasProblems()) {
                throw new RuntimeConfigurationError(buildConfigurationProblems.getHtmlProblems());
            }
        }
        catch (RuntimeConfigurationError runtimeConfigurationError) {
            throw b((Exception)runtimeConfigurationError);
        }
    }
    
    @Nullable
    public BuildAndRunConfigurations getBuildAndRunConfigurations(@Nullable final BuildConfigurationProblems buildConfigurationProblems, final boolean b) {
        final BuildTargetAndConfigurationData targetAndConfigurationData = this.getTargetAndConfigurationData();
        Label_0023: {
            BuildTargetAndConfigurationData buildTargetAndConfigurationData;
            try {
                buildTargetAndConfigurationData = targetAndConfigurationData;
                if (this.myExplicitBuildTargetName != null) {
                    final boolean b2 = true;
                    break Label_0023;
                }
            }
            catch (WriteExternalException ex) {
                throw b((Exception)ex);
            }
            final boolean b2 = false;
            try {
                if (!BuildTargetAndConfigurationData.checkData(buildTargetAndConfigurationData, buildConfigurationProblems, b2)) {
                    return null;
                }
            }
            catch (WriteExternalException ex2) {
                throw b((Exception)ex2);
            }
        }
        return (BuildAndRunConfigurations)ReadAction.compute(() -> {
            // 
            // This method could not be decompiled.
            // 
            // Original Bytecode:
            // 
            //     0: aload_0        
            //     1: invokevirtual   com/jetbrains/cidr/cpp/execution/CMakeAppRunConfiguration.getProject:()Lcom/intellij/openapi/project/Project;
            //     4: invokeinterface com/intellij/openapi/project/Project.isDisposed:()Z
            //     9: ifeq            18
            //    12: aconst_null    
            //    13: areturn        
            //    14: invokestatic    com/jetbrains/cidr/cpp/execution/CMakeAppRunConfiguration.b:(Ljava/lang/Exception;)Ljava/lang/Exception;
            //    17: athrow         
            //    18: aconst_null    
            //    19: astore          4
            //    21: aconst_null    
            //    22: astore          5
            //    24: aload_0        
            //    25: invokevirtual   com/jetbrains/cidr/cpp/execution/CMakeAppRunConfiguration.getExecutableData:()Lcom/jetbrains/cidr/execution/ExecutableData;
            //    28: astore          7
            //    30: aload           7
            //    32: ifnonnull       97
            //    35: iload_1        
            //    36: ifeq            97
            //    39: goto            46
            //    42: invokestatic    com/jetbrains/cidr/cpp/execution/CMakeAppRunConfiguration.b:(Ljava/lang/Exception;)Ljava/lang/Exception;
            //    45: athrow         
            //    46: aload_2        
            //    47: ifnull          95
            //    50: goto            57
            //    53: invokestatic    com/jetbrains/cidr/cpp/execution/CMakeAppRunConfiguration.b:(Ljava/lang/Exception;)Ljava/lang/Exception;
            //    56: athrow         
            //    57: aload_2        
            //    58: getfield        com/jetbrains/cidr/execution/BuildConfigurationProblems.problems:Ljava/util/List;
            //    61: ldc             "build.configuration.parameterNotSelected"
            //    63: iconst_1       
            //    64: anewarray       Ljava/lang/Object;
            //    67: dup            
            //    68: iconst_0       
            //    69: ldc             "build.configuration.executable"
            //    71: iconst_0       
            //    72: anewarray       Ljava/lang/Object;
            //    75: invokestatic    com/jetbrains/cidr/CidrBundle.message:(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
            //    78: aastore        
            //    79: invokestatic    com/jetbrains/cidr/CidrBundle.message:(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
            //    82: invokeinterface java/util/List.add:(Ljava/lang/Object;)Z
            //    87: pop            
            //    88: goto            95
            //    91: invokestatic    com/jetbrains/cidr/cpp/execution/CMakeAppRunConfiguration.b:(Ljava/lang/Exception;)Ljava/lang/Exception;
            //    94: athrow         
            //    95: aconst_null    
            //    96: areturn        
            //    97: aload           7
            //    99: ifnull          206
            //   102: aload           7
            //   104: getfield        com/jetbrains/cidr/execution/ExecutableData.target:Lcom/jetbrains/cidr/execution/BuildTargetData;
            //   107: ifnull          168
            //   110: goto            117
            //   113: invokestatic    com/jetbrains/cidr/cpp/execution/CMakeAppRunConfiguration.b:(Ljava/lang/Exception;)Ljava/lang/Exception;
            //   116: athrow         
            //   117: aload           7
            //   119: getfield        com/jetbrains/cidr/execution/ExecutableData.target:Lcom/jetbrains/cidr/execution/BuildTargetData;
            //   122: aload_3        
            //   123: getfield        com/jetbrains/cidr/execution/BuildTargetAndConfigurationData.target:Lcom/jetbrains/cidr/execution/BuildTargetData;
            //   126: invokevirtual   com/jetbrains/cidr/execution/BuildTargetData.equals:(Ljava/lang/Object;)Z
            //   129: ifne            206
            //   132: goto            139
            //   135: invokestatic    com/jetbrains/cidr/cpp/execution/CMakeAppRunConfiguration.b:(Ljava/lang/Exception;)Ljava/lang/Exception;
            //   138: athrow         
            //   139: aload_0        
            //   140: aload_2        
            //   141: aload           7
            //   143: getfield        com/jetbrains/cidr/execution/ExecutableData.target:Lcom/jetbrains/cidr/execution/BuildTargetData;
            //   146: aload_3        
            //   147: getfield        com/jetbrains/cidr/execution/BuildTargetAndConfigurationData.configurationName:Ljava/lang/String;
            //   150: iconst_1       
            //   151: iconst_1       
            //   152: invokespecial   com/jetbrains/cidr/cpp/execution/CMakeAppRunConfiguration.a:(Lcom/jetbrains/cidr/execution/BuildConfigurationProblems;Lcom/jetbrains/cidr/execution/BuildTargetData;Ljava/lang/String;ZZ)Lcom/jetbrains/cidr/cpp/cmake/model/CMakeConfiguration;
            //   155: astore          5
            //   157: aload           5
            //   159: ifnonnull       206
            //   162: aconst_null    
            //   163: areturn        
            //   164: invokestatic    com/jetbrains/cidr/cpp/execution/CMakeAppRunConfiguration.b:(Ljava/lang/Exception;)Ljava/lang/Exception;
            //   167: athrow         
            //   168: getstatic       com/jetbrains/cidr/cpp/CPPLog.LOG:Lcom/intellij/openapi/diagnostic/Logger;
            //   171: aload           7
            //   173: getfield        com/jetbrains/cidr/execution/ExecutableData.path:Ljava/lang/String;
            //   176: ifnull          187
            //   179: iconst_1       
            //   180: goto            188
            //   183: invokestatic    com/jetbrains/cidr/cpp/execution/CMakeAppRunConfiguration.b:(Ljava/lang/Exception;)Ljava/lang/Exception;
            //   186: athrow         
            //   187: iconst_0       
            //   188: invokevirtual   com/intellij/openapi/diagnostic/Logger.assertTrue:(Z)Z
            //   191: pop            
            //   192: new             Ljava/io/File;
            //   195: dup            
            //   196: aload           7
            //   198: getfield        com/jetbrains/cidr/execution/ExecutableData.path:Ljava/lang/String;
            //   201: invokespecial   java/io/File.<init>:(Ljava/lang/String;)V
            //   204: astore          4
            //   206: aload_0        
            //   207: getfield        com/jetbrains/cidr/cpp/execution/CMakeAppRunConfiguration.myExplicitBuildTargetName:Ljava/lang/String;
            //   210: ifnull          360
            //   213: aload_0        
            //   214: invokevirtual   com/jetbrains/cidr/cpp/execution/CMakeAppRunConfiguration.getProject:()Lcom/intellij/openapi/project/Project;
            //   217: invokestatic    com/jetbrains/cidr/cpp/execution/CMakeRunConfigurationType.getHelper:(Lcom/intellij/openapi/project/Project;)Lcom/jetbrains/cidr/cpp/execution/CMakeBuildConfigurationHelper;
            //   220: astore          8
            //   222: aconst_null    
            //   223: astore          6
            //   225: aload_3        
            //   226: getfield        com/jetbrains/cidr/execution/BuildTargetAndConfigurationData.configurationName:Ljava/lang/String;
            //   229: ifnull          294
            //   232: aload           8
            //   234: invokevirtual   com/jetbrains/cidr/cpp/execution/CMakeBuildConfigurationHelper.getTargets:()Ljava/util/List;
            //   237: invokeinterface java/util/List.iterator:()Ljava/util/Iterator;
            //   242: astore          9
            //   244: aload           9
            //   246: invokeinterface java/util/Iterator.hasNext:()Z
            //   251: ifeq            294
            //   254: aload           9
            //   256: invokeinterface java/util/Iterator.next:()Ljava/lang/Object;
            //   261: checkcast       Lcom/jetbrains/cidr/cpp/cmake/model/CMakeTarget;
            //   264: astore          10
            //   266: aload           8
            //   268: aload           10
            //   270: aload_3        
            //   271: getfield        com/jetbrains/cidr/execution/BuildTargetAndConfigurationData.configurationName:Ljava/lang/String;
            //   274: invokevirtual   com/jetbrains/cidr/cpp/execution/CMakeBuildConfigurationHelper.findConfiguration:(Lcom/jetbrains/cidr/cpp/cmake/model/CMakeTarget;Ljava/lang/String;)Lcom/jetbrains/cidr/cpp/cmake/model/CMakeConfiguration;
            //   277: astore          6
            //   279: aload           6
            //   281: ifnull          291
            //   284: goto            294
            //   287: invokestatic    com/jetbrains/cidr/cpp/execution/CMakeAppRunConfiguration.b:(Ljava/lang/Exception;)Ljava/lang/Exception;
            //   290: athrow         
            //   291: goto            244
            //   294: aload           6
            //   296: ifnonnull       357
            //   299: aload_2        
            //   300: ifnull          355
            //   303: goto            310
            //   306: invokestatic    com/jetbrains/cidr/cpp/execution/CMakeAppRunConfiguration.b:(Ljava/lang/Exception;)Ljava/lang/Exception;
            //   309: athrow         
            //   310: aload_2        
            //   311: getfield        com/jetbrains/cidr/execution/BuildConfigurationProblems.problems:Ljava/util/List;
            //   314: ldc             "build.configuration.parameterNotFound"
            //   316: iconst_2       
            //   317: anewarray       Ljava/lang/Object;
            //   320: dup            
            //   321: iconst_0       
            //   322: ldc             "build.configuration.configuration"
            //   324: iconst_0       
            //   325: anewarray       Ljava/lang/Object;
            //   328: invokestatic    com/jetbrains/cidr/CidrBundle.message:(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
            //   331: aastore        
            //   332: dup            
            //   333: iconst_1       
            //   334: aload_3        
            //   335: getfield        com/jetbrains/cidr/execution/BuildTargetAndConfigurationData.configurationName:Ljava/lang/String;
            //   338: aastore        
            //   339: invokestatic    com/jetbrains/cidr/CidrBundle.message:(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
            //   342: invokeinterface java/util/List.add:(Ljava/lang/Object;)Z
            //   347: pop            
            //   348: goto            355
            //   351: invokestatic    com/jetbrains/cidr/cpp/execution/CMakeAppRunConfiguration.b:(Ljava/lang/Exception;)Ljava/lang/Exception;
            //   354: athrow         
            //   355: aconst_null    
            //   356: areturn        
            //   357: goto            436
            //   360: getstatic       com/jetbrains/cidr/cpp/CPPLog.LOG:Lcom/intellij/openapi/diagnostic/Logger;
            //   363: aload_3        
            //   364: getfield        com/jetbrains/cidr/execution/BuildTargetAndConfigurationData.target:Lcom/jetbrains/cidr/execution/BuildTargetData;
            //   367: ifnull          378
            //   370: iconst_1       
            //   371: goto            379
            //   374: invokestatic    com/jetbrains/cidr/cpp/execution/CMakeAppRunConfiguration.b:(Ljava/lang/Exception;)Ljava/lang/Exception;
            //   377: athrow         
            //   378: iconst_0       
            //   379: invokevirtual   com/intellij/openapi/diagnostic/Logger.assertTrue:(Z)Z
            //   382: pop            
            //   383: aload_0        
            //   384: aload_2        
            //   385: aload_3        
            //   386: getfield        com/jetbrains/cidr/execution/BuildTargetAndConfigurationData.target:Lcom/jetbrains/cidr/execution/BuildTargetData;
            //   389: aload_3        
            //   390: getfield        com/jetbrains/cidr/execution/BuildTargetAndConfigurationData.configurationName:Ljava/lang/String;
            //   393: iconst_0       
            //   394: aload           5
            //   396: ifnonnull       419
            //   399: aload           4
            //   401: ifnonnull       419
            //   404: goto            411
            //   407: invokestatic    com/jetbrains/cidr/cpp/execution/CMakeAppRunConfiguration.b:(Ljava/lang/Exception;)Ljava/lang/Exception;
            //   410: athrow         
            //   411: iconst_1       
            //   412: goto            420
            //   415: invokestatic    com/jetbrains/cidr/cpp/execution/CMakeAppRunConfiguration.b:(Ljava/lang/Exception;)Ljava/lang/Exception;
            //   418: athrow         
            //   419: iconst_0       
            //   420: invokespecial   com/jetbrains/cidr/cpp/execution/CMakeAppRunConfiguration.a:(Lcom/jetbrains/cidr/execution/BuildConfigurationProblems;Lcom/jetbrains/cidr/execution/BuildTargetData;Ljava/lang/String;ZZ)Lcom/jetbrains/cidr/cpp/cmake/model/CMakeConfiguration;
            //   423: astore          6
            //   425: aload           6
            //   427: ifnonnull       436
            //   430: aconst_null    
            //   431: areturn        
            //   432: invokestatic    com/jetbrains/cidr/cpp/execution/CMakeAppRunConfiguration.b:(Ljava/lang/Exception;)Ljava/lang/Exception;
            //   435: athrow         
            //   436: new             Lcom/jetbrains/cidr/cpp/execution/CMakeAppRunConfiguration$BuildAndRunConfigurations;
            //   439: dup            
            //   440: aload           6
            //   442: aload           5
            //   444: aload           4
            //   446: aload_0        
            //   447: getfield        com/jetbrains/cidr/cpp/execution/CMakeAppRunConfiguration.myExplicitBuildTargetName:Ljava/lang/String;
            //   450: invokespecial   com/jetbrains/cidr/cpp/execution/CMakeAppRunConfiguration$BuildAndRunConfigurations.<init>:(Lcom/jetbrains/cidr/cpp/cmake/model/CMakeConfiguration;Lcom/jetbrains/cidr/cpp/cmake/model/CMakeConfiguration;Ljava/io/File;Ljava/lang/String;)V
            //   453: areturn        
            //    Exceptions:
            //  throws java.lang.RuntimeException
            //    Exceptions:
            //  Try           Handler
            //  Start  End    Start  End    Type                        
            //  -----  -----  -----  -----  ----------------------------
            //  0      14     14     18     Ljava/lang/RuntimeException;
            //  30     39     42     46     Ljava/lang/RuntimeException;
            //  35     50     53     57     Ljava/lang/RuntimeException;
            //  46     88     91     95     Ljava/lang/RuntimeException;
            //  97     110    113    117    Ljava/lang/RuntimeException;
            //  102    132    135    139    Ljava/lang/RuntimeException;
            //  157    164    164    168    Ljava/lang/RuntimeException;
            //  168    183    183    187    Ljava/lang/RuntimeException;
            //  279    287    287    291    Ljava/lang/RuntimeException;
            //  294    303    306    310    Ljava/lang/RuntimeException;
            //  299    348    351    355    Ljava/lang/RuntimeException;
            //  360    374    374    378    Ljava/lang/RuntimeException;
            //  379    404    407    411    Ljava/lang/RuntimeException;
            //  399    415    415    419    Ljava/lang/RuntimeException;
            //  425    432    432    436    Ljava/lang/RuntimeException;
            // 
            // The error that occurred was:
            // 
            // java.lang.IllegalStateException: Expression is linked from several locations: Label_0046:
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
        });
    }
    
    @Nullable
    private CMakeConfiguration a(@Nullable final BuildConfigurationProblems p0, @NotNull final BuildTargetData p1, @Nullable final String p2, final boolean p3, final boolean p4) {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     0: aload_2        
        //     1: ifnonnull       44
        //     4: new             Ljava/lang/IllegalArgumentException;
        //     7: dup            
        //     8: ldc             "Argument for @NotNull parameter '%s' of %s.%s must not be null"
        //    10: ldc             3
        //    12: anewarray       Ljava/lang/Object;
        //    15: dup            
        //    16: ldc             0
        //    18: ldc             "dataTarget"
        //    20: aastore        
        //    21: dup            
        //    22: ldc             1
        //    24: ldc             "com/jetbrains/cidr/cpp/execution/CMakeAppRunConfiguration"
        //    26: aastore        
        //    27: dup            
        //    28: ldc             2
        //    30: ldc             "getConfiguration"
        //    32: aastore        
        //    33: invokestatic    java/lang/String.format:(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
        //    36: invokespecial   java/lang/IllegalArgumentException.<init>:(Ljava/lang/String;)V
        //    39: athrow         
        //    40: invokestatic    com/jetbrains/cidr/cpp/execution/CMakeAppRunConfiguration.b:(Ljava/lang/Exception;)Ljava/lang/Exception;
        //    43: athrow         
        //    44: aload_0        
        //    45: invokevirtual   com/jetbrains/cidr/cpp/execution/CMakeAppRunConfiguration.getProject:()Lcom/intellij/openapi/project/Project;
        //    48: invokestatic    com/jetbrains/cidr/cpp/execution/CMakeRunConfigurationType.getHelper:(Lcom/intellij/openapi/project/Project;)Lcom/jetbrains/cidr/cpp/execution/CMakeBuildConfigurationHelper;
        //    51: astore          6
        //    53: aload           6
        //    55: aload_2        
        //    56: invokevirtual   com/jetbrains/cidr/cpp/execution/CMakeBuildConfigurationHelper.findTarget:(Lcom/jetbrains/cidr/execution/BuildTargetData;)Lcom/jetbrains/cidr/cpp/cmake/model/CMakeTarget;
        //    59: astore          8
        //    61: aload           8
        //    63: ifnull          93
        //    66: iload           4
        //    68: ifeq            151
        //    71: goto            78
        //    74: invokestatic    com/jetbrains/cidr/cpp/execution/CMakeAppRunConfiguration.b:(Ljava/lang/Exception;)Ljava/lang/Exception;
        //    77: athrow         
        //    78: aload           8
        //    80: invokevirtual   com/jetbrains/cidr/cpp/cmake/model/CMakeTarget.isExecutable:()Z
        //    83: ifne            151
        //    86: goto            93
        //    89: invokestatic    com/jetbrains/cidr/cpp/execution/CMakeAppRunConfiguration.b:(Ljava/lang/Exception;)Ljava/lang/Exception;
        //    92: athrow         
        //    93: aload_1        
        //    94: ifnull          149
        //    97: goto            104
        //   100: invokestatic    com/jetbrains/cidr/cpp/execution/CMakeAppRunConfiguration.b:(Ljava/lang/Exception;)Ljava/lang/Exception;
        //   103: athrow         
        //   104: aload_1        
        //   105: getfield        com/jetbrains/cidr/execution/BuildConfigurationProblems.problems:Ljava/util/List;
        //   108: ldc             "build.configuration.parameterNotFound"
        //   110: iconst_2       
        //   111: anewarray       Ljava/lang/Object;
        //   114: dup            
        //   115: iconst_0       
        //   116: ldc             "build.configuration.target"
        //   118: iconst_0       
        //   119: anewarray       Ljava/lang/Object;
        //   122: invokestatic    com/jetbrains/cidr/CidrBundle.message:(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
        //   125: aastore        
        //   126: dup            
        //   127: iconst_1       
        //   128: aload_2        
        //   129: invokevirtual   com/jetbrains/cidr/execution/BuildTargetData.getDisplayString:()Ljava/lang/String;
        //   132: aastore        
        //   133: invokestatic    com/jetbrains/cidr/CidrBundle.message:(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
        //   136: invokeinterface java/util/List.add:(Ljava/lang/Object;)Z
        //   141: pop            
        //   142: goto            149
        //   145: invokestatic    com/jetbrains/cidr/cpp/execution/CMakeAppRunConfiguration.b:(Ljava/lang/Exception;)Ljava/lang/Exception;
        //   148: athrow         
        //   149: aconst_null    
        //   150: areturn        
        //   151: aload           6
        //   153: aload           8
        //   155: aload_3        
        //   156: invokevirtual   com/jetbrains/cidr/cpp/execution/CMakeBuildConfigurationHelper.findConfiguration:(Lcom/jetbrains/cidr/cpp/cmake/model/CMakeTarget;Ljava/lang/String;)Lcom/jetbrains/cidr/cpp/cmake/model/CMakeConfiguration;
        //   159: astore          7
        //   161: aload           7
        //   163: ifnonnull       228
        //   166: aload_1        
        //   167: ifnull          226
        //   170: goto            177
        //   173: invokestatic    com/jetbrains/cidr/cpp/execution/CMakeAppRunConfiguration.b:(Ljava/lang/Exception;)Ljava/lang/Exception;
        //   176: athrow         
        //   177: aload_1        
        //   178: getfield        com/jetbrains/cidr/execution/BuildConfigurationProblems.problems:Ljava/util/List;
        //   181: ldc             "build.configuration.parameterNotFoundForTarget"
        //   183: iconst_3       
        //   184: anewarray       Ljava/lang/Object;
        //   187: dup            
        //   188: iconst_0       
        //   189: ldc             "build.configuration.configuration"
        //   191: iconst_0       
        //   192: anewarray       Ljava/lang/Object;
        //   195: invokestatic    com/jetbrains/cidr/CidrBundle.message:(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
        //   198: aastore        
        //   199: dup            
        //   200: iconst_1       
        //   201: aload_3        
        //   202: aastore        
        //   203: dup            
        //   204: iconst_2       
        //   205: aload_2        
        //   206: invokevirtual   com/jetbrains/cidr/execution/BuildTargetData.getDisplayString:()Ljava/lang/String;
        //   209: aastore        
        //   210: invokestatic    com/jetbrains/cidr/CidrBundle.message:(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
        //   213: invokeinterface java/util/List.add:(Ljava/lang/Object;)Z
        //   218: pop            
        //   219: goto            226
        //   222: invokestatic    com/jetbrains/cidr/cpp/execution/CMakeAppRunConfiguration.b:(Ljava/lang/Exception;)Ljava/lang/Exception;
        //   225: athrow         
        //   226: aconst_null    
        //   227: areturn        
        //   228: iload           5
        //   230: ifeq            298
        //   233: aload           7
        //   235: invokevirtual   com/jetbrains/cidr/cpp/cmake/model/CMakeConfiguration.getProductFile:()Ljava/io/File;
        //   238: ifnonnull       298
        //   241: goto            248
        //   244: invokestatic    com/jetbrains/cidr/cpp/execution/CMakeAppRunConfiguration.b:(Ljava/lang/Exception;)Ljava/lang/Exception;
        //   247: athrow         
        //   248: aload_1        
        //   249: ifnull          296
        //   252: goto            259
        //   255: invokestatic    com/jetbrains/cidr/cpp/execution/CMakeAppRunConfiguration.b:(Ljava/lang/Exception;)Ljava/lang/Exception;
        //   258: athrow         
        //   259: aload_1        
        //   260: getfield        com/jetbrains/cidr/execution/BuildConfigurationProblems.problems:Ljava/util/List;
        //   263: ldc             "build.configuration.productNotFound"
        //   265: iconst_2       
        //   266: anewarray       Ljava/lang/Object;
        //   269: dup            
        //   270: iconst_0       
        //   271: aload_2        
        //   272: invokevirtual   com/jetbrains/cidr/execution/BuildTargetData.getDisplayString:()Ljava/lang/String;
        //   275: aastore        
        //   276: dup            
        //   277: iconst_1       
        //   278: aload_3        
        //   279: aastore        
        //   280: invokestatic    com/jetbrains/cidr/CidrBundle.message:(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
        //   283: invokeinterface java/util/List.add:(Ljava/lang/Object;)Z
        //   288: pop            
        //   289: goto            296
        //   292: invokestatic    com/jetbrains/cidr/cpp/execution/CMakeAppRunConfiguration.b:(Ljava/lang/Exception;)Ljava/lang/Exception;
        //   295: athrow         
        //   296: aconst_null    
        //   297: areturn        
        //   298: aload           7
        //   300: areturn        
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                                              
        //  -----  -----  -----  -----  --------------------------------------------------
        //  0      40     40     44     Lcom/intellij/openapi/util/WriteExternalException;
        //  61     71     74     78     Lcom/intellij/openapi/util/WriteExternalException;
        //  66     86     89     93     Lcom/intellij/openapi/util/WriteExternalException;
        //  78     97     100    104    Lcom/intellij/openapi/util/WriteExternalException;
        //  93     142    145    149    Lcom/intellij/openapi/util/WriteExternalException;
        //  161    170    173    177    Lcom/intellij/openapi/util/WriteExternalException;
        //  166    219    222    226    Lcom/intellij/openapi/util/WriteExternalException;
        //  228    241    244    248    Lcom/intellij/openapi/util/WriteExternalException;
        //  233    252    255    259    Lcom/intellij/openapi/util/WriteExternalException;
        //  248    289    292    296    Lcom/intellij/openapi/util/WriteExternalException;
        // 
        // The error that occurred was:
        // 
        // java.lang.IllegalStateException: Expression is linked from several locations: Label_0078:
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
    public SettingsEditor<? extends RunConfiguration> getConfigurationEditor() {
        SettingsEditor<? extends CMakeAppRunConfiguration> editor;
        try {
            editor = this.getType().createEditor(this.getProject());
            if (editor == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/cpp/execution/CMakeAppRunConfiguration", "getConfigurationEditor"));
            }
        }
        catch (WriteExternalException ex) {
            throw b((Exception)ex);
        }
        return (SettingsEditor<? extends RunConfiguration>)editor;
    }
    
    @Nullable
    @Override
    public CidrCommandLineState getState(@NotNull final Executor executor, @NotNull final ExecutionEnvironment executionEnvironment) throws ExecutionException {
        try {
            if (executor == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "executor", "com/jetbrains/cidr/cpp/execution/CMakeAppRunConfiguration", "getState"));
            }
        }
        catch (ExecutionException ex) {
            throw b((Exception)ex);
        }
        try {
            if (executionEnvironment == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "env", "com/jetbrains/cidr/cpp/execution/CMakeAppRunConfiguration", "getState"));
            }
        }
        catch (ExecutionException ex2) {
            throw b((Exception)ex2);
        }
        return new CidrCommandLineState(executionEnvironment, new CMakeLauncher(this));
    }
    
    @Override
    public void writeExternal(final Element element) throws WriteExternalException {
        try {
            super.writeExternal(element);
            if (this.myExecutableData != null) {
                this.myExecutableData.writeExternal(element);
            }
        }
        catch (WriteExternalException ex) {
            throw b((Exception)ex);
        }
        try {
            if (this.myExplicitBuildTargetName != null) {
                element.setAttribute("EXPLICIT_BUILD_TARGET_NAME", this.myExplicitBuildTargetName);
                element.removeAttribute("PROJECT_NAME");
                element.removeAttribute("TARGET_NAME");
            }
        }
        catch (WriteExternalException ex2) {
            throw b((Exception)ex2);
        }
    }
    
    @Override
    public void readExternal(final Element element) throws InvalidDataException {
        super.readExternal(element);
        this.myExecutableData = ExecutableData.loadExternal(element);
        this.myExplicitBuildTargetName = element.getAttributeValue("EXPLICIT_BUILD_TARGET_NAME");
    }
    
    @Nullable
    @Override
    public ExecutableData getExecutableData() {
        return this.myExecutableData;
    }
    
    @Override
    public void setExecutableData(@Nullable final ExecutableData myExecutableData) {
        this.myExecutableData = myExecutableData;
    }
    
    public boolean isBuildAllTargets() {
        return "all".equals(this.myExplicitBuildTargetName);
    }
    
    public void setExplicitBuildTargetName(@Nullable final String myExplicitBuildTargetName) {
        this.myExplicitBuildTargetName = myExplicitBuildTargetName;
    }
    
    @Override
    public RunConfiguration clone() {
        final CMakeAppRunConfiguration cMakeAppRunConfiguration = (CMakeAppRunConfiguration)super.clone();
        cMakeAppRunConfiguration.myExecutableData = this.myExecutableData;
        cMakeAppRunConfiguration.myExplicitBuildTargetName = this.myExplicitBuildTargetName;
        return (RunConfiguration)cMakeAppRunConfiguration;
    }
    
    @Override
    public void setupDefaultTargetAndExecutable() {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     0: aload_0        
        //     1: invokespecial   com/jetbrains/cidr/execution/CidrRunConfiguration.setupDefaultTargetAndExecutable:()V
        //     4: aload_0        
        //     5: invokevirtual   com/jetbrains/cidr/cpp/execution/CMakeAppRunConfiguration.getTargetAndConfigurationData:()Lcom/jetbrains/cidr/execution/BuildTargetAndConfigurationData;
        //     8: astore_1       
        //     9: aload_1        
        //    10: ifnull          70
        //    13: aload_1        
        //    14: getfield        com/jetbrains/cidr/execution/BuildTargetAndConfigurationData.target:Lcom/jetbrains/cidr/execution/BuildTargetData;
        //    17: ifnull          70
        //    20: goto            27
        //    23: invokestatic    com/jetbrains/cidr/cpp/execution/CMakeAppRunConfiguration.b:(Ljava/lang/Exception;)Ljava/lang/Exception;
        //    26: athrow         
        //    27: aload_0        
        //    28: invokevirtual   com/jetbrains/cidr/cpp/execution/CMakeAppRunConfiguration.getHelper:()Lcom/jetbrains/cidr/cpp/execution/CMakeBuildConfigurationHelper;
        //    31: aload_1        
        //    32: getfield        com/jetbrains/cidr/execution/BuildTargetAndConfigurationData.target:Lcom/jetbrains/cidr/execution/BuildTargetData;
        //    35: invokevirtual   com/jetbrains/cidr/cpp/execution/CMakeBuildConfigurationHelper.findRunTarget:(Lcom/jetbrains/cidr/execution/BuildTargetData;)Lcom/jetbrains/cidr/cpp/cmake/model/CMakeTarget;
        //    38: ifnull          70
        //    41: goto            48
        //    44: invokestatic    com/jetbrains/cidr/cpp/execution/CMakeAppRunConfiguration.b:(Ljava/lang/Exception;)Ljava/lang/Exception;
        //    47: athrow         
        //    48: aload_0        
        //    49: new             Lcom/jetbrains/cidr/execution/ExecutableData;
        //    52: dup            
        //    53: aload_1        
        //    54: getfield        com/jetbrains/cidr/execution/BuildTargetAndConfigurationData.target:Lcom/jetbrains/cidr/execution/BuildTargetData;
        //    57: invokespecial   com/jetbrains/cidr/execution/ExecutableData.<init>:(Lcom/jetbrains/cidr/execution/BuildTargetData;)V
        //    60: invokevirtual   com/jetbrains/cidr/cpp/execution/CMakeAppRunConfiguration.setExecutableData:(Lcom/jetbrains/cidr/execution/ExecutableData;)V
        //    63: goto            70
        //    66: invokestatic    com/jetbrains/cidr/cpp/execution/CMakeAppRunConfiguration.b:(Ljava/lang/Exception;)Ljava/lang/Exception;
        //    69: athrow         
        //    70: return         
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                                              
        //  -----  -----  -----  -----  --------------------------------------------------
        //  9      20     23     27     Lcom/intellij/openapi/util/WriteExternalException;
        //  13     41     44     48     Lcom/intellij/openapi/util/WriteExternalException;
        //  27     63     66     70     Lcom/intellij/openapi/util/WriteExternalException;
        // 
        // The error that occurred was:
        // 
        // java.lang.IllegalStateException: Expression is linked from several locations: Label_0027:
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
    @Override
    public CMakeBuildConfigurationHelper getHelper() {
        CMakeBuildConfigurationHelper helper;
        try {
            helper = CMakeRunConfigurationType.getHelper(this.getProject());
            if (helper == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/cpp/execution/CMakeAppRunConfiguration", "getHelper"));
            }
        }
        catch (WriteExternalException ex) {
            throw b((Exception)ex);
        }
        return helper;
    }
    
    private static Exception b(final Exception ex) {
        return ex;
    }
    
    public static class BuildAndRunConfigurations
    {
        @NotNull
        public final CMakeConfiguration buildConfiguration;
        @Nullable
        public final CMakeConfiguration runConfiguration;
        @Nullable
        public final File runExecutable;
        @Nullable
        public final String explicitBuildTargetName;
        
        public BuildAndRunConfigurations(@NotNull final CMakeConfiguration buildConfiguration, @Nullable final CMakeConfiguration runConfiguration, @Nullable final File runExecutable, @Nullable final String explicitBuildTargetName) {
            if (buildConfiguration == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "buildConfiguration", "com/jetbrains/cidr/cpp/execution/CMakeAppRunConfiguration$BuildAndRunConfigurations", "<init>"));
            }
            this.buildConfiguration = buildConfiguration;
            this.runConfiguration = runConfiguration;
            this.runExecutable = runExecutable;
            this.explicitBuildTargetName = explicitBuildTargetName;
        }
        
        public BuildAndRunConfigurations(@NotNull final CMakeConfiguration cMakeConfiguration) {
            if (cMakeConfiguration == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "buildConfiguration", "com/jetbrains/cidr/cpp/execution/CMakeAppRunConfiguration$BuildAndRunConfigurations", "<init>"));
            }
            this(cMakeConfiguration, null, null, null);
        }
        
        @NotNull
        public CMakeConfiguration getRunConfiguration() {
            CMakeConfiguration cMakeConfiguration = null;
            Label_0022: {
                try {
                    if (this.runConfiguration != null) {
                        final CMakeConfiguration cMakeConfiguration2;
                        cMakeConfiguration = (cMakeConfiguration2 = this.runConfiguration);
                        break Label_0022;
                    }
                }
                catch (IllegalArgumentException ex) {
                    throw a(ex);
                }
                CMakeConfiguration cMakeConfiguration2;
                cMakeConfiguration = (cMakeConfiguration2 = this.buildConfiguration);
                try {
                    if (cMakeConfiguration2 == null) {
                        throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/cpp/execution/CMakeAppRunConfiguration$BuildAndRunConfigurations", "getRunConfiguration"));
                    }
                }
                catch (IllegalArgumentException ex2) {
                    throw a(ex2);
                }
            }
            return cMakeConfiguration;
        }
        
        @Nullable
        public File getRunFile() {
            try {
                if (this.runExecutable != null) {
                    return this.runExecutable;
                }
            }
            catch (IllegalArgumentException ex) {
                throw a(ex);
            }
            return this.getRunConfiguration().getProductFile();
        }
        
        private static IllegalArgumentException a(final IllegalArgumentException ex) {
            return ex;
        }
    }
}
