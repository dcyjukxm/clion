// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.cpp.execution;

import java.util.Arrays;
import com.intellij.execution.impl.RunnerAndConfigurationSettingsImpl;
import com.intellij.execution.configurations.ConfigurationType;
import gnu.trove.THashSet;
import java.util.List;
import com.intellij.openapi.util.Comparing;
import com.intellij.openapi.util.Pair;
import java.util.ArrayList;
import gnu.trove.TObjectIntHashMap;
import org.jetbrains.annotations.Nullable;
import com.intellij.execution.configurations.RunConfiguration;
import com.intellij.execution.configurations.ConfigurationFactory;
import java.util.Iterator;
import com.intellij.openapi.util.JDOMUtil;
import com.intellij.openapi.util.Ref;
import com.intellij.openapi.util.text.StringUtil;
import java.util.Map;
import com.intellij.util.containers.MultiMap;
import com.jetbrains.cidr.execution.ExecutableData;
import com.jetbrains.cidr.execution.BuildTargetAndConfigurationData;
import java.util.Collection;
import com.intellij.util.containers.ContainerUtil;
import com.intellij.util.Processor;
import com.intellij.execution.RunnerAndConfigurationSettings;
import com.intellij.execution.RunManagerEx;
import com.intellij.execution.impl.RunManagerImpl;
import com.intellij.openapi.application.Application;
import com.intellij.util.messages.Topic;
import com.jetbrains.cidr.cpp.cmake.workspace.CMakeWorkspaceListener;
import com.intellij.openapi.Disposable;
import com.intellij.openapi.application.ApplicationManager;
import java.util.Collections;
import com.intellij.openapi.project.Project;
import org.jetbrains.annotations.NotNull;
import com.jetbrains.cidr.execution.BuildTargetData;
import java.util.Set;
import com.intellij.openapi.components.Storage;
import com.intellij.openapi.components.State;
import org.jdom.Element;
import com.intellij.openapi.components.PersistentStateComponent;
import com.intellij.openapi.components.AbstractProjectComponent;

@State(name = "CMakeRunConfigurationManager", storages = { @Storage("$WORKSPACE_FILE$") })
public class CMakeRunConfigurationManager extends AbstractProjectComponent implements PersistentStateComponent<Element>
{
    private static volatile boolean ourEnableInTests;
    private volatile boolean myShouldGenerateConfigs;
    private volatile boolean myShouldDeleteObsoleteConfigs;
    private volatile boolean myBuildAllGenerated;
    @NotNull
    private volatile Set<BuildTargetData> myGeneratedConfigs;
    
    public static void setEnableInTests(final boolean ourEnableInTests) {
        CMakeRunConfigurationManager.ourEnableInTests = ourEnableInTests;
    }
    
    @NotNull
    public static CMakeRunConfigurationManager getInstance(@NotNull final Project project) {
        try {
            if (project == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "project", "com/jetbrains/cidr/cpp/execution/CMakeRunConfigurationManager", "getInstance"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        CMakeRunConfigurationManager cMakeRunConfigurationManager;
        try {
            cMakeRunConfigurationManager = (CMakeRunConfigurationManager)project.getComponent((Class)CMakeRunConfigurationManager.class);
            if (cMakeRunConfigurationManager == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/cpp/execution/CMakeRunConfigurationManager", "getInstance"));
            }
        }
        catch (IllegalArgumentException ex2) {
            throw a(ex2);
        }
        return cMakeRunConfigurationManager;
    }
    
    protected CMakeRunConfigurationManager(final Project project) {
        super(project);
        this.myShouldGenerateConfigs = true;
        this.myShouldDeleteObsoleteConfigs = true;
        this.myBuildAllGenerated = false;
        this.myGeneratedConfigs = Collections.emptySet();
    }
    
    public void initComponent() {
        Label_0033: {
            try {
                super.initComponent();
                if (CMakeRunConfigurationManager.ourEnableInTests) {
                    break Label_0033;
                }
                final Application application = ApplicationManager.getApplication();
                final boolean b = application.isUnitTestMode();
                if (b) {
                    return;
                }
                break Label_0033;
            }
            catch (IllegalArgumentException ex) {
                throw a(ex);
            }
            try {
                final Application application = ApplicationManager.getApplication();
                final boolean b = application.isUnitTestMode();
                if (b) {
                    return;
                }
            }
            catch (IllegalArgumentException ex2) {
                throw a(ex2);
            }
        }
        this.myProject.getMessageBus().connect((Disposable)this.myProject).subscribe((Topic)CMakeWorkspaceListener.TOPIC, (Object)new CMakeWorkspaceListener() {
            @Override
            public void reloadingFinished(final boolean b) {
                if (b) {
                    return;
                }
                if (CMakeRunConfigurationManager.this.myShouldGenerateConfigs) {
                    CMakeRunConfigurationManager.this.a();
                }
            }
        });
    }
    
    private void a() {
        ApplicationManager.getApplication().assertWriteAccessAllowed();
        this.myGeneratedConfigs = Collections.unmodifiableSet((Set<? extends BuildTargetData>)this.a(this.myGeneratedConfigs, false));
    }
    
    public void recreateRunConfigurations() {
        ApplicationManager.getApplication().assertWriteAccessAllowed();
        this.myGeneratedConfigs = Collections.unmodifiableSet((Set<? extends BuildTargetData>)this.a(Collections.emptySet(), true));
    }
    
    @NotNull
    private Set<BuildTargetData> a(final Set<BuildTargetData> set, final boolean b) {
        final RunManagerImpl runManagerImpl = (RunManagerImpl)RunManagerEx.getInstanceEx(this.myProject);
        runManagerImpl.fireBeginUpdate();
        try {
            while (true) {
                if (runManagerImpl.getSelectedConfiguration() == null) {
                    boolean b2 = false;
                    Label_0046: {
                        try {
                            if (b(runManagerImpl.getAllSettings(), (Processor<CMakeAppRunConfiguration>)(cMakeAppRunConfiguration -> false))) {
                                b2 = true;
                                break Label_0046;
                            }
                        }
                        catch (IllegalArgumentException ex) {
                            throw a(ex);
                        }
                        b2 = false;
                    }
                    final boolean b3 = b2;
                    final Set<BuildTargetData> a = this.a(runManagerImpl, set, b);
                    Label_0076: {
                        try {
                            if (!b3) {
                                break Label_0076;
                            }
                            final RunManagerImpl runManagerImpl2 = runManagerImpl;
                            final RunnerAndConfigurationSettings runnerAndConfigurationSettings = runManagerImpl2.getSelectedConfiguration();
                            if (runnerAndConfigurationSettings == null) {
                                break Label_0076;
                            }
                            break Label_0076;
                        }
                        catch (IllegalArgumentException ex2) {
                            throw a(ex2);
                        }
                        try {
                            final RunManagerImpl runManagerImpl2 = runManagerImpl;
                            final RunnerAndConfigurationSettings runnerAndConfigurationSettings = runManagerImpl2.getSelectedConfiguration();
                            if (runnerAndConfigurationSettings == null) {
                                a(runManagerImpl);
                            }
                        }
                        catch (IllegalArgumentException ex3) {
                            throw a(ex3);
                        }
                    }
                    final Set<BuildTargetData> set2 = a;
                    Set<BuildTargetData> set3;
                    try {
                        runManagerImpl.fireEndUpdate();
                        set3 = set2;
                        if (set3 == null) {
                            throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/cpp/execution/CMakeRunConfigurationManager", "doUpdateRunConfigurations"));
                        }
                    }
                    catch (IllegalArgumentException ex4) {
                        throw a(ex4);
                    }
                    return set3;
                }
                continue;
            }
        }
        finally {
            runManagerImpl.fireEndUpdate();
        }
    }
    
    @NotNull
    private Set<BuildTargetData> a(final RunManagerImpl p0, final Set<BuildTargetData> p1, final boolean p2) {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     0: aload_1        
        //     1: invokevirtual   com/intellij/execution/impl/RunManagerImpl.getAllSettings:()Ljava/util/List;
        //     4: astore          4
        //     6: aload_0        
        //     7: getfield        com/jetbrains/cidr/cpp/execution/CMakeRunConfigurationManager.myProject:Lcom/intellij/openapi/project/Project;
        //    10: invokestatic    com/jetbrains/cidr/cpp/execution/CMakeRunConfigurationType.getHelper:(Lcom/intellij/openapi/project/Project;)Lcom/jetbrains/cidr/cpp/execution/CMakeBuildConfigurationHelper;
        //    13: astore          5
        //    15: iload_3        
        //    16: ifne            33
        //    19: aload_0        
        //    20: getfield        com/jetbrains/cidr/cpp/execution/CMakeRunConfigurationManager.myBuildAllGenerated:Z
        //    23: ifne            69
        //    26: goto            33
        //    29: invokestatic    com/jetbrains/cidr/cpp/execution/CMakeRunConfigurationManager.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //    32: athrow         
        //    33: aload           5
        //    35: invokevirtual   com/jetbrains/cidr/cpp/execution/CMakeBuildConfigurationHelper.getTargets:()Ljava/util/List;
        //    38: invokeinterface java/util/List.isEmpty:()Z
        //    43: ifne            69
        //    46: goto            53
        //    49: invokestatic    com/jetbrains/cidr/cpp/execution/CMakeRunConfigurationManager.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //    52: athrow         
        //    53: aload_1        
        //    54: invokestatic    com/jetbrains/cidr/cpp/execution/CMakeRunConfigurationManager.b:(Lcom/intellij/execution/impl/RunManagerImpl;)V
        //    57: aload_0        
        //    58: iconst_1       
        //    59: putfield        com/jetbrains/cidr/cpp/execution/CMakeRunConfigurationManager.myBuildAllGenerated:Z
        //    62: goto            69
        //    65: invokestatic    com/jetbrains/cidr/cpp/execution/CMakeRunConfigurationManager.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //    68: athrow         
        //    69: aload_1        
        //    70: invokevirtual   com/intellij/execution/impl/RunManagerImpl.getConfigurationFactoriesWithoutUnknown:()Ljava/util/List;
        //    73: invokedynamic   fun:()Lcom/intellij/util/Function;
        //    78: invokestatic    com/intellij/util/containers/ContainerUtil.map:(Ljava/util/Collection;Lcom/intellij/util/Function;)Ljava/util/List;
        //    81: invokestatic    com/intellij/util/containers/ContainerUtil.flatten:(Ljava/lang/Iterable;)Ljava/util/List;
        //    84: astore          6
        //    86: aload           6
        //    88: aload_1        
        //    89: invokedynamic   fun:(Lcom/intellij/execution/impl/RunManagerImpl;)Lcom/intellij/util/Function;
        //    94: invokestatic    com/intellij/util/containers/ContainerUtil.map:(Ljava/util/Collection;Lcom/intellij/util/Function;)Ljava/util/List;
        //    97: aload_1        
        //    98: invokevirtual   com/intellij/execution/impl/RunManagerImpl.getAllSettings:()Ljava/util/List;
        //   101: invokestatic    com/intellij/util/containers/ContainerUtil.concat:(Ljava/util/List;Ljava/util/List;)Ljava/util/List;
        //   104: astore          7
        //   106: aload           7
        //   108: aload           5
        //   110: invokedynamic   process:(Lcom/jetbrains/cidr/cpp/execution/CMakeBuildConfigurationHelper;)Lcom/intellij/util/Processor;
        //   115: invokestatic    com/jetbrains/cidr/cpp/execution/CMakeRunConfigurationManager.b:(Ljava/lang/Iterable;Lcom/intellij/util/Processor;)Z
        //   118: pop            
        //   119: aload           5
        //   121: aload_2        
        //   122: invokestatic    com/jetbrains/cidr/cpp/execution/CMakeRunConfigurationManager.a:(Lcom/jetbrains/cidr/cpp/execution/CMakeBuildConfigurationHelper;Ljava/util/Set;)Ljava/util/Set;
        //   125: astore_2       
        //   126: new             Ljava/util/HashSet;
        //   129: dup            
        //   130: invokespecial   java/util/HashSet.<init>:()V
        //   133: astore          8
        //   135: new             Ljava/util/HashSet;
        //   138: dup            
        //   139: aload_2        
        //   140: aload           8
        //   142: invokedynamic   fun:(Ljava/util/Set;)Lcom/intellij/util/Function;
        //   147: invokestatic    com/intellij/util/containers/ContainerUtil.map:(Ljava/util/Collection;Lcom/intellij/util/Function;)Ljava/util/List;
        //   150: invokespecial   java/util/HashSet.<init>:(Ljava/util/Collection;)V
        //   153: astore          9
        //   155: new             Lcom/intellij/util/containers/MultiMap;
        //   158: dup            
        //   159: invokespecial   com/intellij/util/containers/MultiMap.<init>:()V
        //   162: astore          10
        //   164: new             Lgnu/trove/THashSet;
        //   167: dup            
        //   168: invokespecial   gnu/trove/THashSet.<init>:()V
        //   171: astore          11
        //   173: aload_1        
        //   174: invokevirtual   com/intellij/execution/impl/RunManagerImpl.getAllSettings:()Ljava/util/List;
        //   177: aload           11
        //   179: aload           8
        //   181: aload           9
        //   183: aload           10
        //   185: invokedynamic   process:(Ljava/util/Set;Ljava/util/Set;Ljava/util/Set;Lcom/intellij/util/containers/MultiMap;)Lcom/intellij/util/Processor;
        //   190: invokestatic    com/jetbrains/cidr/cpp/execution/CMakeRunConfigurationManager.a:(Ljava/lang/Iterable;Lcom/intellij/util/Processor;)Z
        //   193: pop            
        //   194: aload           5
        //   196: invokevirtual   com/jetbrains/cidr/cpp/execution/CMakeBuildConfigurationHelper.getTargets:()Ljava/util/List;
        //   199: invokeinterface java/util/List.iterator:()Ljava/util/Iterator;
        //   204: astore          12
        //   206: aload           12
        //   208: invokeinterface java/util/Iterator.hasNext:()Z
        //   213: ifeq            387
        //   216: aload           12
        //   218: invokeinterface java/util/Iterator.next:()Ljava/lang/Object;
        //   223: checkcast       Lcom/jetbrains/cidr/cpp/cmake/model/CMakeTarget;
        //   226: astore          13
        //   228: new             Lcom/jetbrains/cidr/execution/BuildTargetData;
        //   231: dup            
        //   232: aload           13
        //   234: invokespecial   com/jetbrains/cidr/execution/BuildTargetData.<init>:(Lcom/jetbrains/cidr/execution/CidrBuildTarget;)V
        //   237: astore          14
        //   239: aload           10
        //   241: aload           14
        //   243: invokevirtual   com/intellij/util/containers/MultiMap.remove:(Ljava/lang/Object;)Ljava/util/Collection;
        //   246: pop            
        //   247: aload           13
        //   249: invokevirtual   com/jetbrains/cidr/cpp/cmake/model/CMakeTarget.getName:()Ljava/lang/String;
        //   252: astore          15
        //   254: aload           9
        //   256: aload           15
        //   258: invokeinterface java/util/Set.contains:(Ljava/lang/Object;)Z
        //   263: ifeq            273
        //   266: goto            206
        //   269: invokestatic    com/jetbrains/cidr/cpp/execution/CMakeRunConfigurationManager.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   272: athrow         
        //   273: aload           11
        //   275: aload           15
        //   277: invokeinterface java/util/Set.add:(Ljava/lang/Object;)Z
        //   282: ifne            292
        //   285: goto            206
        //   288: invokestatic    com/jetbrains/cidr/cpp/execution/CMakeRunConfigurationManager.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   291: athrow         
        //   292: aload           5
        //   294: aload           13
        //   296: invokevirtual   com/jetbrains/cidr/cpp/execution/CMakeBuildConfigurationHelper.getDefaultConfiguration:(Lcom/jetbrains/cidr/cpp/cmake/model/CMakeTarget;)Lcom/jetbrains/cidr/cpp/cmake/model/CMakeConfiguration;
        //   299: astore          16
        //   301: aload_1        
        //   302: aload           5
        //   304: aload           13
        //   306: invokevirtual   com/jetbrains/cidr/cpp/execution/CMakeBuildConfigurationHelper.getDefaultTargetType:(Lcom/jetbrains/cidr/cpp/cmake/model/CMakeTarget;)Lcom/jetbrains/cidr/cpp/execution/CMakeRunConfigurationType;
        //   309: invokevirtual   com/jetbrains/cidr/cpp/execution/CMakeRunConfigurationType.getFactory:()Lcom/intellij/execution/configurations/ConfigurationFactory;
        //   312: aload           15
        //   314: new             Lcom/jetbrains/cidr/execution/BuildTargetAndConfigurationData;
        //   317: dup            
        //   318: aload           14
        //   320: aload           16
        //   322: ifnonnull       333
        //   325: aconst_null    
        //   326: goto            338
        //   329: invokestatic    com/jetbrains/cidr/cpp/execution/CMakeRunConfigurationManager.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   332: athrow         
        //   333: aload           16
        //   335: invokevirtual   com/jetbrains/cidr/cpp/cmake/model/CMakeConfiguration.getName:()Ljava/lang/String;
        //   338: invokespecial   com/jetbrains/cidr/execution/BuildTargetAndConfigurationData.<init>:(Lcom/jetbrains/cidr/execution/BuildTargetData;Ljava/lang/String;)V
        //   341: aload           13
        //   343: invokevirtual   com/jetbrains/cidr/cpp/cmake/model/CMakeTarget.isExecutable:()Z
        //   346: ifeq            364
        //   349: new             Lcom/jetbrains/cidr/execution/ExecutableData;
        //   352: dup            
        //   353: aload           14
        //   355: invokespecial   com/jetbrains/cidr/execution/ExecutableData.<init>:(Lcom/jetbrains/cidr/execution/BuildTargetData;)V
        //   358: invokestatic    com/intellij/openapi/util/Ref.create:(Ljava/lang/Object;)Lcom/intellij/openapi/util/Ref;
        //   361: goto            365
        //   364: aconst_null    
        //   365: invokestatic    com/jetbrains/cidr/cpp/execution/CMakeRunConfigurationManager.createRunConfiguration:(Lcom/intellij/execution/RunManagerEx;Lcom/intellij/execution/configurations/ConfigurationFactory;Ljava/lang/String;Lcom/jetbrains/cidr/execution/BuildTargetAndConfigurationData;Lcom/intellij/openapi/util/Ref;)Lcom/intellij/execution/RunnerAndConfigurationSettings;
        //   368: astore          17
        //   370: aload_1        
        //   371: aload           17
        //   373: iconst_0       
        //   374: invokevirtual   com/intellij/execution/impl/RunManagerImpl.addConfiguration:(Lcom/intellij/execution/RunnerAndConfigurationSettings;Z)V
        //   377: aload           8
        //   379: aload           14
        //   381: invokestatic    com/intellij/util/containers/ContainerUtil.addIfNotNull:(Ljava/util/Collection;Ljava/lang/Object;)V
        //   384: goto            206
        //   387: aload_0        
        //   388: getfield        com/jetbrains/cidr/cpp/execution/CMakeRunConfigurationManager.myShouldDeleteObsoleteConfigs:Z
        //   391: ifeq            410
        //   394: aload_1        
        //   395: aload_2        
        //   396: aload           10
        //   398: aload           8
        //   400: invokestatic    com/jetbrains/cidr/cpp/execution/CMakeRunConfigurationManager.a:(Lcom/intellij/execution/RunManagerEx;Ljava/util/Set;Lcom/intellij/util/containers/MultiMap;Ljava/util/Set;)V
        //   403: goto            410
        //   406: invokestatic    com/jetbrains/cidr/cpp/execution/CMakeRunConfigurationManager.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   409: athrow         
        //   410: aload           4
        //   412: invokedynamic   fun:()Lcom/intellij/util/Function;
        //   417: invokestatic    com/intellij/util/containers/ContainerUtil.mapNotNull:(Ljava/util/Collection;Lcom/intellij/util/Function;)Ljava/util/List;
        //   420: astore          12
        //   422: aload           12
        //   424: aload           5
        //   426: invokevirtual   com/jetbrains/cidr/cpp/execution/CMakeBuildConfigurationHelper.getTargets:()Ljava/util/List;
        //   429: invokedynamic   fun:()Lcom/intellij/util/Function;
        //   434: invokestatic    com/intellij/util/containers/ContainerUtil.map:(Ljava/util/Collection;Lcom/intellij/util/Function;)Ljava/util/List;
        //   437: iconst_0       
        //   438: invokestatic    com/jetbrains/cidr/execution/CidrOrderMerger.mergeWithOriginalOrderPrecedence:(Ljava/util/List;Ljava/util/List;Z)Ljava/util/List;
        //   441: astore          13
        //   443: new             Lgnu/trove/TObjectIntHashMap;
        //   446: dup            
        //   447: invokespecial   gnu/trove/TObjectIntHashMap.<init>:()V
        //   450: astore          14
        //   452: iconst_0       
        //   453: istore          15
        //   455: aload           13
        //   457: invokeinterface java/util/List.iterator:()Ljava/util/Iterator;
        //   462: astore          16
        //   464: aload           16
        //   466: invokeinterface java/util/Iterator.hasNext:()Z
        //   471: ifeq            502
        //   474: aload           16
        //   476: invokeinterface java/util/Iterator.next:()Ljava/lang/Object;
        //   481: checkcast       Ljava/lang/String;
        //   484: astore          17
        //   486: aload           14
        //   488: aload           17
        //   490: iload           15
        //   492: iinc            15, 1
        //   495: invokevirtual   gnu/trove/TObjectIntHashMap.put:(Ljava/lang/Object;I)I
        //   498: pop            
        //   499: goto            464
        //   502: aload_1        
        //   503: aload           14
        //   505: invokedynamic   compare:(Lgnu/trove/TObjectIntHashMap;)Ljava/util/Comparator;
        //   510: invokevirtual   com/intellij/execution/impl/RunManagerImpl.setOrder:(Ljava/util/Comparator;)V
        //   513: aload           8
        //   515: dup            
        //   516: ifnonnull       553
        //   519: new             Ljava/lang/IllegalStateException;
        //   522: dup            
        //   523: ldc             "@NotNull method %s.%s must not return null"
        //   525: ldc             2
        //   527: anewarray       Ljava/lang/Object;
        //   530: dup            
        //   531: ldc             0
        //   533: ldc             "com/jetbrains/cidr/cpp/execution/CMakeRunConfigurationManager"
        //   535: aastore        
        //   536: dup            
        //   537: ldc             1
        //   539: ldc             "doUpdateRunConfigurations"
        //   541: aastore        
        //   542: invokestatic    java/lang/String.format:(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
        //   545: invokespecial   java/lang/IllegalStateException.<init>:(Ljava/lang/String;)V
        //   548: athrow         
        //   549: invokestatic    com/jetbrains/cidr/cpp/execution/CMakeRunConfigurationManager.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   552: athrow         
        //   553: areturn        
        //    Signature:
        //  (Lcom/intellij/execution/impl/RunManagerImpl;Ljava/util/Set<Lcom/jetbrains/cidr/execution/BuildTargetData;>;Z)Ljava/util/Set<Lcom/jetbrains/cidr/execution/BuildTargetData;>;
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                                
        //  -----  -----  -----  -----  ------------------------------------
        //  15     26     29     33     Ljava/lang/IllegalArgumentException;
        //  19     46     49     53     Ljava/lang/IllegalArgumentException;
        //  33     62     65     69     Ljava/lang/IllegalArgumentException;
        //  254    269    269    273    Ljava/lang/IllegalArgumentException;
        //  273    288    288    292    Ljava/lang/IllegalArgumentException;
        //  301    329    329    333    Ljava/lang/IllegalArgumentException;
        //  387    403    406    410    Ljava/lang/IllegalArgumentException;
        //  502    549    549    553    Ljava/lang/IllegalArgumentException;
        // 
        // The error that occurred was:
        // 
        // java.lang.IllegalStateException: Expression is linked from several locations: Label_0033:
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
    
    private static void b(@NotNull final CMakeBuildConfigurationHelper p0, @NotNull final CMakeAppRunConfiguration p1) {
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
        //    18: ldc             "helper"
        //    20: aastore        
        //    21: dup            
        //    22: ldc             1
        //    24: ldc             "com/jetbrains/cidr/cpp/execution/CMakeRunConfigurationManager"
        //    26: aastore        
        //    27: dup            
        //    28: ldc             2
        //    30: ldc             "updateBuildConfiguration"
        //    32: aastore        
        //    33: invokestatic    java/lang/String.format:(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
        //    36: invokespecial   java/lang/IllegalArgumentException.<init>:(Ljava/lang/String;)V
        //    39: athrow         
        //    40: invokestatic    com/jetbrains/cidr/cpp/execution/CMakeRunConfigurationManager.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
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
        //    62: ldc             "config"
        //    64: aastore        
        //    65: dup            
        //    66: ldc             1
        //    68: ldc             "com/jetbrains/cidr/cpp/execution/CMakeRunConfigurationManager"
        //    70: aastore        
        //    71: dup            
        //    72: ldc             2
        //    74: ldc             "updateBuildConfiguration"
        //    76: aastore        
        //    77: invokestatic    java/lang/String.format:(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
        //    80: invokespecial   java/lang/IllegalArgumentException.<init>:(Ljava/lang/String;)V
        //    83: athrow         
        //    84: invokestatic    com/jetbrains/cidr/cpp/execution/CMakeRunConfigurationManager.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //    87: athrow         
        //    88: aload_1        
        //    89: invokevirtual   com/jetbrains/cidr/cpp/execution/CMakeAppRunConfiguration.getTargetAndConfigurationData:()Lcom/jetbrains/cidr/execution/BuildTargetAndConfigurationData;
        //    92: astore_2       
        //    93: aload_2        
        //    94: ifnonnull       102
        //    97: return         
        //    98: invokestatic    com/jetbrains/cidr/cpp/execution/CMakeRunConfigurationManager.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   101: athrow         
        //   102: aload_1        
        //   103: invokevirtual   com/jetbrains/cidr/cpp/execution/CMakeAppRunConfiguration.isBuildAllTargets:()Z
        //   106: istore_3       
        //   107: iload_3        
        //   108: ifeq            128
        //   111: aload_0        
        //   112: invokevirtual   com/jetbrains/cidr/cpp/execution/CMakeBuildConfigurationHelper.getTargets:()Ljava/util/List;
        //   115: invokestatic    com/intellij/util/containers/ContainerUtil.getFirstItem:(Ljava/util/List;)Ljava/lang/Object;
        //   118: checkcast       Lcom/jetbrains/cidr/cpp/cmake/model/CMakeTarget;
        //   121: goto            136
        //   124: invokestatic    com/jetbrains/cidr/cpp/execution/CMakeRunConfigurationManager.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   127: athrow         
        //   128: aload_0        
        //   129: aload_2        
        //   130: getfield        com/jetbrains/cidr/execution/BuildTargetAndConfigurationData.target:Lcom/jetbrains/cidr/execution/BuildTargetData;
        //   133: invokevirtual   com/jetbrains/cidr/cpp/execution/CMakeBuildConfigurationHelper.findTarget:(Lcom/jetbrains/cidr/execution/BuildTargetData;)Lcom/jetbrains/cidr/cpp/cmake/model/CMakeTarget;
        //   136: astore          4
        //   138: aload           4
        //   140: ifnull          201
        //   143: aload_0        
        //   144: aload           4
        //   146: aload_2        
        //   147: getfield        com/jetbrains/cidr/execution/BuildTargetAndConfigurationData.configurationName:Ljava/lang/String;
        //   150: invokevirtual   com/jetbrains/cidr/cpp/execution/CMakeBuildConfigurationHelper.findConfiguration:(Lcom/jetbrains/cidr/cpp/cmake/model/CMakeTarget;Ljava/lang/String;)Lcom/jetbrains/cidr/cpp/cmake/model/CMakeConfiguration;
        //   153: ifnonnull       201
        //   156: goto            163
        //   159: invokestatic    com/jetbrains/cidr/cpp/execution/CMakeRunConfigurationManager.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   162: athrow         
        //   163: aload_1        
        //   164: new             Lcom/jetbrains/cidr/execution/BuildTargetAndConfigurationData;
        //   167: dup            
        //   168: iload_3        
        //   169: ifeq            187
        //   172: goto            179
        //   175: invokestatic    com/jetbrains/cidr/cpp/execution/CMakeRunConfigurationManager.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   178: athrow         
        //   179: aconst_null    
        //   180: goto            189
        //   183: invokestatic    com/jetbrains/cidr/cpp/execution/CMakeRunConfigurationManager.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   186: athrow         
        //   187: aload           4
        //   189: aload_0        
        //   190: aload           4
        //   192: invokevirtual   com/jetbrains/cidr/cpp/execution/CMakeBuildConfigurationHelper.getDefaultConfiguration:(Lcom/jetbrains/cidr/cpp/cmake/model/CMakeTarget;)Lcom/jetbrains/cidr/cpp/cmake/model/CMakeConfiguration;
        //   195: invokespecial   com/jetbrains/cidr/execution/BuildTargetAndConfigurationData.<init>:(Lcom/jetbrains/cidr/execution/CidrBuildTarget;Lcom/jetbrains/cidr/execution/CidrBuildConfiguration;)V
        //   198: invokevirtual   com/jetbrains/cidr/cpp/execution/CMakeAppRunConfiguration.setTargetAndConfigurationData:(Lcom/jetbrains/cidr/execution/BuildTargetAndConfigurationData;)V
        //   201: return         
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                                
        //  -----  -----  -----  -----  ------------------------------------
        //  0      40     40     44     Ljava/lang/IllegalArgumentException;
        //  44     84     84     88     Ljava/lang/IllegalArgumentException;
        //  93     98     98     102    Ljava/lang/IllegalArgumentException;
        //  107    124    124    128    Ljava/lang/IllegalArgumentException;
        //  138    156    159    163    Ljava/lang/IllegalArgumentException;
        //  143    172    175    179    Ljava/lang/IllegalArgumentException;
        //  163    183    183    187    Ljava/lang/IllegalArgumentException;
        // 
        // The error that occurred was:
        // 
        // java.lang.IllegalStateException: Expression is linked from several locations: Label_0163:
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
    
    private static Set<BuildTargetData> a(@NotNull final CMakeBuildConfigurationHelper cMakeBuildConfigurationHelper, @NotNull final Set<BuildTargetData> set) {
        try {
            if (cMakeBuildConfigurationHelper == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "helper", "com/jetbrains/cidr/cpp/execution/CMakeRunConfigurationManager", "updateProjectName"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        try {
            if (set == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "original", "com/jetbrains/cidr/cpp/execution/CMakeRunConfigurationManager", "updateProjectName"));
            }
        }
        catch (IllegalArgumentException ex2) {
            throw a(ex2);
        }
        return (Set<BuildTargetData>)ContainerUtil.map2Set((Collection)set, buildTargetData -> new BuildTargetData(s, buildTargetData.targetName));
    }
    
    private static void c(@NotNull final CMakeBuildConfigurationHelper cMakeBuildConfigurationHelper, @NotNull final CMakeAppRunConfiguration cMakeAppRunConfiguration) {
        try {
            if (cMakeBuildConfigurationHelper == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "helper", "com/jetbrains/cidr/cpp/execution/CMakeRunConfigurationManager", "updateProjectName"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        try {
            if (cMakeAppRunConfiguration == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "config", "com/jetbrains/cidr/cpp/execution/CMakeRunConfigurationManager", "updateProjectName"));
            }
        }
        catch (IllegalArgumentException ex2) {
            throw a(ex2);
        }
        final String projectName = cMakeBuildConfigurationHelper.getProjectName();
        final BuildTargetAndConfigurationData targetAndConfigurationData = cMakeAppRunConfiguration.getTargetAndConfigurationData();
        Label_0116: {
            try {
                if (targetAndConfigurationData == null) {
                    break Label_0116;
                }
                final BuildTargetAndConfigurationData buildTargetAndConfigurationData = targetAndConfigurationData;
                final BuildTargetData buildTargetData = buildTargetAndConfigurationData.target;
                if (buildTargetData != null) {
                    break Label_0116;
                }
                break Label_0116;
            }
            catch (IllegalArgumentException ex3) {
                throw a(ex3);
            }
            try {
                final BuildTargetAndConfigurationData buildTargetAndConfigurationData = targetAndConfigurationData;
                final BuildTargetData buildTargetData = buildTargetAndConfigurationData.target;
                if (buildTargetData != null) {
                    cMakeAppRunConfiguration.setTargetAndConfigurationData(new BuildTargetAndConfigurationData(projectName, targetAndConfigurationData.target.targetName, targetAndConfigurationData.configurationName));
                }
            }
            catch (IllegalArgumentException ex4) {
                throw a(ex4);
            }
        }
        final ExecutableData executableData = cMakeAppRunConfiguration.getExecutableData();
        Label_0172: {
            try {
                if (executableData == null) {
                    return;
                }
                final ExecutableData executableData2 = executableData;
                final BuildTargetData buildTargetData2 = executableData2.target;
                if (buildTargetData2 != null) {
                    break Label_0172;
                }
                return;
            }
            catch (IllegalArgumentException ex5) {
                throw a(ex5);
            }
            try {
                final ExecutableData executableData2 = executableData;
                final BuildTargetData buildTargetData2 = executableData2.target;
                if (buildTargetData2 != null) {
                    cMakeAppRunConfiguration.setExecutableData(new ExecutableData(new BuildTargetData(projectName, executableData.target.targetName)));
                }
            }
            catch (IllegalArgumentException ex6) {
                throw a(ex6);
            }
        }
    }
    
    private static void a(@NotNull final RunManagerEx runManagerEx, @NotNull final Set<BuildTargetData> set, @NotNull final MultiMap<BuildTargetData, RunnerAndConfigurationSettings> multiMap, @NotNull final Set<BuildTargetData> set2) {
        try {
            if (runManagerEx == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "runManager", "com/jetbrains/cidr/cpp/execution/CMakeRunConfigurationManager", "removeConfigurationsForDeletedTargets"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        try {
            if (set == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "previouslyGenerated", "com/jetbrains/cidr/cpp/execution/CMakeRunConfigurationManager", "removeConfigurationsForDeletedTargets"));
            }
        }
        catch (IllegalArgumentException ex2) {
            throw a(ex2);
        }
        try {
            if (multiMap == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "deletedTargets", "com/jetbrains/cidr/cpp/execution/CMakeRunConfigurationManager", "removeConfigurationsForDeletedTargets"));
            }
        }
        catch (IllegalArgumentException ex3) {
            throw a(ex3);
        }
        try {
            if (set2 == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "generated", "com/jetbrains/cidr/cpp/execution/CMakeRunConfigurationManager", "removeConfigurationsForDeletedTargets"));
            }
        }
        catch (IllegalArgumentException ex4) {
            throw a(ex4);
        }
        for (final Map.Entry<BuildTargetData, V> entry : multiMap.entrySet()) {
            final BuildTargetData buildTargetData = entry.getKey();
            set2.remove(buildTargetData);
            if (set.contains(buildTargetData)) {
                for (final RunnerAndConfigurationSettings runnerAndConfigurationSettings : (Collection)entry.getValue()) {
                    try {
                        if (!StringUtil.equals((CharSequence)runnerAndConfigurationSettings.getName(), (CharSequence)buildTargetData.targetName)) {
                            continue;
                        }
                    }
                    catch (IllegalArgumentException ex5) {
                        throw a(ex5);
                    }
                    final CMakeAppRunConfiguration cMakeAppRunConfiguration = (CMakeAppRunConfiguration)runnerAndConfigurationSettings.getConfiguration();
                    try {
                        if (cMakeAppRunConfiguration == null) {
                            continue;
                        }
                    }
                    catch (IllegalArgumentException ex6) {
                        throw a(ex6);
                    }
                    final BuildTargetAndConfigurationData targetAndConfigurationData = cMakeAppRunConfiguration.getTargetAndConfigurationData();
                    ConfigurationFactory factory = null;
                    String targetName = null;
                    BuildTargetData buildTargetData2 = null;
                    String configurationName = null;
                    Label_0371: {
                        try {
                            factory = cMakeAppRunConfiguration.getFactory();
                            targetName = buildTargetData.targetName;
                            buildTargetData2 = buildTargetData;
                            if (targetAndConfigurationData == null) {
                                configurationName = null;
                                break Label_0371;
                            }
                        }
                        catch (IllegalArgumentException ex7) {
                            throw a(ex7);
                        }
                        configurationName = targetAndConfigurationData.configurationName;
                    }
                    final RunConfiguration configuration = createRunConfiguration(runManagerEx, factory, targetName, new BuildTargetAndConfigurationData(buildTargetData2, configurationName), (Ref<ExecutableData>)Ref.create((Object)cMakeAppRunConfiguration.getExecutableData())).getConfiguration();
                    try {
                        if (configuration == null) {
                            continue;
                        }
                    }
                    catch (IllegalArgumentException ex8) {
                        throw a(ex8);
                    }
                    final Element element = new Element("state");
                    cMakeAppRunConfiguration.writeExternal(element);
                    final Element element2 = new Element("state");
                    try {
                        configuration.writeExternal(element2);
                        if (!JDOMUtil.writeElement(element).equals(JDOMUtil.writeElement(element2))) {
                            continue;
                        }
                    }
                    catch (IllegalArgumentException ex9) {
                        throw a(ex9);
                    }
                    runManagerEx.removeConfiguration(runnerAndConfigurationSettings);
                }
            }
        }
    }
    
    @NotNull
    static RunnerAndConfigurationSettings createRunConfiguration(@NotNull final RunManagerEx runManagerEx, @NotNull final ConfigurationFactory configurationFactory, @NotNull final String s, @NotNull final BuildTargetAndConfigurationData targetAndConfigurationData, @Nullable final Ref<ExecutableData> ref) {
        try {
            if (runManagerEx == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "runManager", "com/jetbrains/cidr/cpp/execution/CMakeRunConfigurationManager", "createRunConfiguration"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        try {
            if (configurationFactory == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "factory", "com/jetbrains/cidr/cpp/execution/CMakeRunConfigurationManager", "createRunConfiguration"));
            }
        }
        catch (IllegalArgumentException ex2) {
            throw a(ex2);
        }
        try {
            if (s == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "name", "com/jetbrains/cidr/cpp/execution/CMakeRunConfigurationManager", "createRunConfiguration"));
            }
        }
        catch (IllegalArgumentException ex3) {
            throw a(ex3);
        }
        try {
            if (targetAndConfigurationData == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "data", "com/jetbrains/cidr/cpp/execution/CMakeRunConfigurationManager", "createRunConfiguration"));
            }
        }
        catch (IllegalArgumentException ex4) {
            throw a(ex4);
        }
        final RunnerAndConfigurationSettings runConfiguration = runManagerEx.createRunConfiguration(s, configurationFactory);
        final CMakeAppRunConfiguration cMakeAppRunConfiguration = (CMakeAppRunConfiguration)runConfiguration.getConfiguration();
        try {
            cMakeAppRunConfiguration.setTargetAndConfigurationData(targetAndConfigurationData);
            if (ref != null) {
                cMakeAppRunConfiguration.setExecutableData((ExecutableData)ref.get());
            }
        }
        catch (IllegalArgumentException ex5) {
            throw a(ex5);
        }
        RunnerAndConfigurationSettings runnerAndConfigurationSettings;
        try {
            runnerAndConfigurationSettings = runConfiguration;
            if (runnerAndConfigurationSettings == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/cpp/execution/CMakeRunConfigurationManager", "createRunConfiguration"));
            }
        }
        catch (IllegalArgumentException ex6) {
            throw a(ex6);
        }
        return runnerAndConfigurationSettings;
    }
    
    private static void b(final RunManagerImpl runManagerImpl) {
        try {
            if (!b(runManagerImpl.getAllSettings(), (Processor<CMakeAppRunConfiguration>)(cMakeAppRunConfiguration -> {
                try {
                    if (!cMakeAppRunConfiguration.isBuildAllTargets()) {
                        return true;
                    }
                }
                catch (IllegalArgumentException ex) {
                    throw a(ex);
                }
                return false;
            }))) {
                return;
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        final RunnerAndConfigurationSettings runConfiguration = runManagerImpl.createRunConfiguration("Build All", CMakeAppRunConfigurationType.getInstance().getFactory());
        final CMakeAppRunConfiguration cMakeAppRunConfiguration = (CMakeAppRunConfiguration)runConfiguration.getConfiguration();
        cMakeAppRunConfiguration.setExplicitBuildTargetName("all");
        final BuildTargetAndConfigurationData targetAndConfigurationData = cMakeAppRunConfiguration.getTargetAndConfigurationData();
        CMakeAppRunConfiguration cMakeAppRunConfiguration2 = null;
        String s = null;
        String s2 = null;
        String configurationName = null;
        Label_0082: {
            try {
                cMakeAppRunConfiguration2 = cMakeAppRunConfiguration;
                s = null;
                s2 = null;
                if (targetAndConfigurationData != null) {
                    configurationName = targetAndConfigurationData.configurationName;
                    break Label_0082;
                }
            }
            catch (IllegalArgumentException ex2) {
                throw a(ex2);
            }
            configurationName = null;
        }
        cMakeAppRunConfiguration2.setTargetAndConfigurationData(new BuildTargetAndConfigurationData(s, s2, configurationName));
        cMakeAppRunConfiguration.setExecutableData(null);
        runManagerImpl.addConfiguration(runConfiguration, false);
        final TObjectIntHashMap tObjectIntHashMap = new TObjectIntHashMap();
        int n = 0;
        final Iterator<RunnerAndConfigurationSettings> iterator = runManagerImpl.getAllSettings().iterator();
        while (iterator.hasNext()) {
            tObjectIntHashMap.put((Object)iterator.next(), n++);
        }
        final RunnerAndConfigurationSettings runnerAndConfigurationSettings3;
        final TObjectIntHashMap tObjectIntHashMap2;
        runManagerImpl.setOrder((runnerAndConfigurationSettings, runnerAndConfigurationSettings2) -> {
            try {
                if (runnerAndConfigurationSettings == runnerAndConfigurationSettings3) {
                    return -1;
                }
            }
            catch (IllegalArgumentException ex3) {
                throw a(ex3);
            }
            try {
                if (runnerAndConfigurationSettings2 == runnerAndConfigurationSettings3) {
                    return 1;
                }
            }
            catch (IllegalArgumentException ex4) {
                throw a(ex4);
            }
            return tObjectIntHashMap2.get((Object)runnerAndConfigurationSettings) - tObjectIntHashMap2.get((Object)runnerAndConfigurationSettings2);
        });
    }
    
    private static void a(@NotNull final RunManagerImpl runManagerImpl) {
        try {
            if (runManagerImpl == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "runManager", "com/jetbrains/cidr/cpp/execution/CMakeRunConfigurationManager", "selectCMakeConfiguration"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        final ArrayList<Pair> list = new ArrayList<Pair>();
        final List flatten = ContainerUtil.flatten((Iterable)ContainerUtil.map((Object[])runManagerImpl.getConfigurationFactories(), configurationType -> {
            try {
                if (runManagerImpl == null) {
                    throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "runManager", "com/jetbrains/cidr/cpp/execution/CMakeRunConfigurationManager", "lambda$selectCMakeConfiguration$11"));
                }
            }
            catch (IllegalArgumentException ex) {
                throw a(ex);
            }
            return runManagerImpl.getConfigurationSettingsList(configurationType);
        }));
        try {
            a(flatten, (Processor<Pair<RunnerAndConfigurationSettings, CMakeAppRunConfiguration>>)(pair -> {
                list.add(pair);
                return true;
            }));
            final boolean b;
            final boolean b2;
            final int n;
            list.sort((pair, pair2) -> {
                Label_0022_1: {
                    try {
                        if (((CMakeAppRunConfiguration)pair.second).getExecutableData() != null) {
                            break Label_0022_1;
                        }
                    }
                    catch (IllegalArgumentException ex2) {
                        throw a(ex2);
                    }
                    try {
                        if (((CMakeAppRunConfiguration)pair2.second).getExecutableData() != null) {
                            break Label_0022_1;
                        }
                    }
                    catch (IllegalArgumentException ex3) {
                        throw a(ex3);
                    }
                }
                n = -Comparing.compare(b, b2);
                try {
                    if (n != 0) {
                        return n;
                    }
                }
                catch (IllegalArgumentException ex4) {
                    throw a(ex4);
                }
                return Comparing.compare(((CMakeAppRunConfiguration)pair.second).isBuildAllTargets(), ((CMakeAppRunConfiguration)pair2.second).isBuildAllTargets());
            });
            if (!list.isEmpty()) {
                runManagerImpl.setSelectedConfiguration((RunnerAndConfigurationSettings)((Pair)list.get(0)).first);
            }
        }
        catch (IllegalArgumentException ex5) {
            throw a(ex5);
        }
    }
    
    private static boolean b(final Iterable<RunnerAndConfigurationSettings> iterable, final Processor<CMakeAppRunConfiguration> processor) {
        return a(iterable, (Processor<Pair<RunnerAndConfigurationSettings, CMakeAppRunConfiguration>>)(pair -> processor.process(pair.second)));
    }
    
    private static boolean a(final Iterable<RunnerAndConfigurationSettings> iterable, final Processor<Pair<RunnerAndConfigurationSettings, CMakeAppRunConfiguration>> processor) {
        for (final RunnerAndConfigurationSettings runnerAndConfigurationSettings : iterable) {
            final RunConfiguration configuration = runnerAndConfigurationSettings.getConfiguration();
            try {
                if (!(configuration instanceof CMakeAppRunConfiguration)) {
                    continue;
                }
                final Processor<Pair<RunnerAndConfigurationSettings, CMakeAppRunConfiguration>> processor2 = processor;
                final RunnerAndConfigurationSettings runnerAndConfigurationSettings2 = runnerAndConfigurationSettings;
                final RunConfiguration runConfiguration = configuration;
                final CMakeAppRunConfiguration cMakeAppRunConfiguration = (CMakeAppRunConfiguration)runConfiguration;
                final Pair pair = Pair.create((Object)runnerAndConfigurationSettings2, (Object)cMakeAppRunConfiguration);
                final boolean b = processor2.process((Object)pair);
                if (!b) {
                    return false;
                }
                continue;
            }
            catch (IllegalArgumentException ex) {
                throw a(ex);
            }
            try {
                final Processor<Pair<RunnerAndConfigurationSettings, CMakeAppRunConfiguration>> processor2 = processor;
                final RunnerAndConfigurationSettings runnerAndConfigurationSettings2 = runnerAndConfigurationSettings;
                final RunConfiguration runConfiguration = configuration;
                final CMakeAppRunConfiguration cMakeAppRunConfiguration = (CMakeAppRunConfiguration)runConfiguration;
                final Pair pair = Pair.create((Object)runnerAndConfigurationSettings2, (Object)cMakeAppRunConfiguration);
                final boolean b = processor2.process((Object)pair);
                if (!b) {
                    return false;
                }
                continue;
            }
            catch (IllegalArgumentException ex2) {
                throw a(ex2);
            }
        }
        return true;
    }
    
    public boolean shouldGenerateConfigurations() {
        return this.myShouldGenerateConfigs;
    }
    
    public void setShouldGenerateConfigurations(final boolean myShouldGenerateConfigs) {
        this.myShouldGenerateConfigs = myShouldGenerateConfigs;
    }
    
    public boolean shouldDeleteObsoleteConfigurations() {
        return this.myShouldDeleteObsoleteConfigs;
    }
    
    public void setShouldDeleteObsoleteConfigurations(final boolean myShouldDeleteObsoleteConfigs) {
        this.myShouldDeleteObsoleteConfigs = myShouldDeleteObsoleteConfigs;
    }
    
    @NotNull
    public Element getState() {
        final Element element = new Element("state");
        element.setAttribute("shouldGenerate", Boolean.toString(this.myShouldGenerateConfigs));
        element.setAttribute("shouldDeleteObsolete", Boolean.toString(this.myShouldDeleteObsoleteConfigs));
        element.setAttribute("buildAllGenerated", Boolean.toString(this.myBuildAllGenerated));
        final Element element2 = new Element("generated");
        element.addContent(element2);
        for (final BuildTargetData buildTargetData : this.myGeneratedConfigs) {
            final Element element3 = new Element("config");
            element2.addContent(element3);
            element3.setAttribute("projectName", buildTargetData.projectName);
            element3.setAttribute("targetName", buildTargetData.targetName);
        }
        Element element4;
        try {
            element4 = element;
            if (element4 == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/cpp/execution/CMakeRunConfigurationManager", "getState"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        return element4;
    }
    
    public void loadState(@NotNull final Element element) {
        try {
            if (element == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "state", "com/jetbrains/cidr/cpp/execution/CMakeRunConfigurationManager", "loadState"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        this.myShouldGenerateConfigs = this.getBoolean(element.getAttributeValue("shouldGenerate"), true);
        this.myShouldDeleteObsoleteConfigs = this.getBoolean(element.getAttributeValue("shouldDeleteObsolete"), true);
        this.myBuildAllGenerated = this.getBoolean(element.getAttributeValue("buildAllGenerated"), false);
        final THashSet set = new THashSet();
        final Element child = element.getChild("generated");
        if (child != null) {
            for (final Element element2 : child.getChildren("config")) {
                final String attributeValue = element2.getAttributeValue("projectName");
                final String attributeValue2 = element2.getAttributeValue("targetName");
                Label_0178: {
                    try {
                        if (attributeValue == null) {
                            continue;
                        }
                        final String s = attributeValue2;
                        if (s != null) {
                            break Label_0178;
                        }
                        continue;
                    }
                    catch (IllegalArgumentException ex2) {
                        throw a(ex2);
                    }
                    try {
                        final String s = attributeValue2;
                        if (s == null) {
                            continue;
                        }
                        ((Set<BuildTargetData>)set).add(new BuildTargetData(attributeValue, attributeValue2));
                    }
                    catch (IllegalArgumentException ex3) {
                        throw a(ex3);
                    }
                }
            }
        }
        this.myGeneratedConfigs = Collections.unmodifiableSet((Set<? extends BuildTargetData>)set);
    }
    
    public boolean getBoolean(@Nullable final String s, final boolean b) {
        try {
            if (s == null) {
                return b;
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        return Boolean.parseBoolean(s);
    }
    
    static {
        CMakeRunConfigurationManager.ourEnableInTests = false;
    }
    
    private static IllegalArgumentException a(final IllegalArgumentException ex) {
        return ex;
    }
}
