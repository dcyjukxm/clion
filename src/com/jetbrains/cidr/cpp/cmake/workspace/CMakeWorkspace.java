// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.cpp.cmake.workspace;

import com.intellij.openapi.diagnostic.Logger;
import com.intellij.openapi.progress.ProcessCanceledException;
import com.intellij.openapi.fileEditor.FileDocumentManager;
import com.jetbrains.cidr.lang.workspace.OCWorkspaceModificationTrackers;
import com.intellij.openapi.vfs.encoding.EncodingProjectManager;
import com.intellij.execution.process.ProcessHandler;
import com.jetbrains.cidr.cpp.cmake.model.CMakeListener;
import com.intellij.openapi.vfs.VfsUtil;
import com.intellij.execution.ExecutionException;
import com.jetbrains.cidr.cpp.cmake.model.CMakeConfiguration;
import com.jetbrains.cidr.lang.workspace.OCIncludeMap;
import com.jetbrains.cidr.lang.symbols.OCSymbol;
import com.jetbrains.cidr.cpp.cmake.model.CMakeTarget;
import com.intellij.openapi.roots.ModifiableRootModel;
import com.jetbrains.cidr.project.CidrNotInProjectNotification;
import com.intellij.openapi.vcs.changes.ChangeListManager;
import com.intellij.openapi.project.impl.ProjectImpl;
import com.intellij.util.PathUtil;
import java.io.ObjectInputStream;
import java.io.InputStream;
import java.io.BufferedInputStream;
import java.io.FileInputStream;
import com.intellij.openapi.vfs.VfsUtilCore;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.io.BufferedOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import com.intellij.psi.search.PsiElementProcessor;
import com.jetbrains.cidr.lang.workspace.headerRoots.HeadersSearchRootProcessor;
import com.jetbrains.cidr.lang.workspace.compiler.OCCompilerKind;
import com.jetbrains.cidr.lang.workspace.headerRoots.FrameworksSearchRoot;
import com.intellij.psi.PsiFileSystemItem;
import com.jetbrains.cidr.lang.workspace.headerRoots.HeadersSearchRoot;
import com.jetbrains.cidr.project.CidrRootsSynchronizer;
import com.jetbrains.cidr.lang.workspace.headerRoots.HeaderRoots;
import com.jetbrains.cidr.lang.workspace.compiler.CidrCompilerResult;
import java.util.Comparator;
import com.jetbrains.cidr.cpp.cmake.model.CMakeFileSettings;
import com.jetbrains.cidr.lang.OCLanguageKind;
import com.jetbrains.cidr.lang.workspace.OCResolveRootAndConfiguration;
import com.intellij.openapi.progress.ProgressManager;
import com.jetbrains.cidr.lang.CLanguageKind;
import com.jetbrains.cidr.lang.workspace.OCResolveConfiguration;
import com.intellij.openapi.util.MultiValuesMap;
import com.jetbrains.cidr.toolchains.CompilerInfoCache;
import com.jetbrains.cidr.cpp.cmake.psi.CMakeFile;
import com.intellij.util.Function;
import com.intellij.util.NullableFunction;
import java.util.function.BiConsumer;
import java.util.Set;
import com.intellij.util.ui.UIUtil;
import com.jetbrains.cidr.cpp.cmake.model.CMakeModel;
import java.util.Map;
import gnu.trove.THashMap;
import java.nio.charset.Charset;
import com.jetbrains.cidr.cpp.cmake.CMakeSettingsKt;
import com.jetbrains.cidr.cpp.cmake.model.CMakeMessage;
import com.intellij.openapi.application.ReadAction;
import com.intellij.openapi.util.Computable;
import com.intellij.openapi.progress.ProgressIndicator;
import java.util.concurrent.TimeoutException;
import java.util.concurrent.Future;
import java.util.concurrent.Callable;
import com.jetbrains.cidr.cpp.cmake.model.CMakeModelConfigurationData;
import com.intellij.openapi.vfs.LocalFileSystem;
import java.util.Iterator;
import com.jetbrains.cidr.cpp.cmake.model.CMakeGenerator;
import java.util.regex.Matcher;
import com.intellij.util.containers.ContainerUtil;
import com.intellij.openapi.util.text.StringUtil;
import gnu.trove.THashSet;
import java.util.regex.Pattern;
import java.util.Collection;
import java.util.ArrayList;
import java.util.List;
import com.intellij.openapi.project.ProjectUtil;
import java.nio.file.Paths;
import com.intellij.openapi.application.PathManager;
import javax.swing.Icon;
import javax.swing.event.HyperlinkListener;
import com.jetbrains.cidr.cpp.cmake.actions.ChangeCMakeProjectContentRootAction;
import javax.swing.event.HyperlinkEvent;
import com.intellij.ui.HyperlinkAdapter;
import com.jetbrains.cidr.cpp.CPPBundle;
import com.intellij.openapi.ui.MessageType;
import com.intellij.openapi.wm.ToolWindow;
import com.intellij.openapi.util.Disposer;
import com.intellij.ui.content.ContentManager;
import icons.CLionIcons;
import com.intellij.openapi.wm.ToolWindowAnchor;
import com.intellij.openapi.wm.ToolWindowManager;
import com.intellij.util.messages.MessageBusConnection;
import com.jetbrains.cidr.project.CidrRootConfigurationListener;
import com.jetbrains.cidr.project.CidrRootConfiguration;
import com.jetbrains.cidr.cpp.cmake.CMakeSettingsListener;
import com.intellij.util.messages.Topic;
import com.jetbrains.cidr.cpp.CPPToolchainsListener;
import com.intellij.openapi.Disposable;
import com.intellij.openapi.application.ApplicationManager;
import java.util.Collections;
import com.jetbrains.cidr.cpp.cmake.CMakeProjectOpenProcessor;
import com.intellij.openapi.vfs.VirtualFile;
import com.intellij.openapi.project.impl.ProjectManagerImpl;
import com.jetbrains.cidr.cpp.CPPModuleType;
import com.intellij.openapi.module.ModuleType;
import java.nio.file.Path;
import com.jetbrains.cidr.cpp.CPPLog;
import com.intellij.openapi.util.io.FileUtil;
import java.io.File;
import org.jetbrains.annotations.NotNull;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.util.Ref;
import java.util.concurrent.atomic.AtomicInteger;
import com.intellij.openapi.util.Pair;
import com.intellij.util.Consumer;
import com.jetbrains.cidr.cpp.cmake.CMakeEnvironment;
import com.jetbrains.cidr.toolchains.EnvironmentProblems;
import com.jetbrains.cidr.cpp.cmake.CMakeSettings;
import kotlin.jvm.functions.Function3;
import org.jetbrains.annotations.Nullable;
import com.intellij.openapi.util.Key;
import com.intellij.openapi.components.State;
import com.intellij.openapi.components.ProjectComponent;
import org.jdom.Element;
import com.intellij.openapi.components.PersistentStateComponent;
import com.jetbrains.cidr.lang.workspace.OCWorkspace;

@com.intellij.openapi.components.State(name = "CMakeWorkspace")
public class CMakeWorkspace implements OCWorkspace, PersistentStateComponent<Element>, ProjectComponent
{
    private static final Key<Boolean> FORCE_RELOAD_ON_OPENING;
    private static final int SERIALIZATION_VERSION = 106;
    public static final String TOOLWINDOW_ID = "CMake";
    @Nullable
    private static Boolean ourSkipLoadingOnFirstOpeningInTests;
    @Nullable
    private static Boolean ourSkipReloadOnReopenInTests;
    @Nullable
    private static Boolean ourForceUseStandardDirForGenerationInTests;
    @Nullable
    private static BalloonNotifier ourBalloonNotifier;
    @Nullable
    private static Function3<CMakeSettings.Configuration, EnvironmentProblems, Boolean, CMakeEnvironment> ourEnvironmentFactory;
    @Nullable
    private static Consumer<CMakeSettings> ourSettingsConfigurator;
    @Nullable
    private static Pair<Integer, Integer> ourPauseOnReloadInTests;
    private static AtomicInteger ourReloadsCounter;
    private static final Ref<Boolean> ourCheckCachesValidity;
    @NotNull
    private final Project myProject;
    private volatile boolean myStateWasLoaded;
    private volatile boolean myInitialized;
    @Nullable
    private volatile File myProjectDir;
    @NotNull
    private volatile State myState;
    private volatile boolean myClearGeneratedFilesBeforeReload;
    @Nullable
    private volatile File myContentRoot;
    private volatile boolean myReportFilesOutsideRoots;
    private volatile boolean myIgnoreFilesOutsideOfProjectRoot;
    @NotNull
    private final CMakeSettings mySettings;
    @NotNull
    private final MyRootsSynchronizer myRootsSynchronizer;
    @NotNull
    private final CMakeWorkspaceWatcher myWorkspaceWatcher;
    private volatile CMakeOutputConsole myOutputConsole;
    boolean myUpdateContentRootsRecursionProtection;
    static final /* synthetic */ boolean $assertionsDisabled;
    
    public static void setSkipLoadingOnFirstOpeningInTests(@Nullable final Boolean ourSkipLoadingOnFirstOpeningInTests) {
        CMakeWorkspace.ourSkipLoadingOnFirstOpeningInTests = ourSkipLoadingOnFirstOpeningInTests;
    }
    
    public static void setSkipReloadOnReopenInTests(@Nullable final Boolean ourSkipReloadOnReopenInTests) {
        CMakeWorkspace.ourSkipReloadOnReopenInTests = ourSkipReloadOnReopenInTests;
    }
    
    public static void setForceUseStandardDirForGenerationInTests(@Nullable final Boolean ourForceUseStandardDirForGenerationInTests) {
        CMakeWorkspace.ourForceUseStandardDirForGenerationInTests = ourForceUseStandardDirForGenerationInTests;
    }
    
    public static void setBalloonNotifierInTests(@Nullable final BalloonNotifier ourBalloonNotifier) {
        CMakeWorkspace.ourBalloonNotifier = ourBalloonNotifier;
    }
    
    public static void setEnvironmentFactoryInTests(@Nullable final Function3<CMakeSettings.Configuration, EnvironmentProblems, Boolean, CMakeEnvironment> ourEnvironmentFactory) {
        CMakeWorkspace.ourEnvironmentFactory = ourEnvironmentFactory;
    }
    
    public static void setSettingsConfiguratorInTests(@Nullable final Consumer<CMakeSettings> ourSettingsConfigurator) {
        CMakeWorkspace.ourSettingsConfigurator = ourSettingsConfigurator;
    }
    
    @Nullable
    public static Consumer<CMakeSettings> getSettingsConfiguratorInTests() {
        return CMakeWorkspace.ourSettingsConfigurator;
    }
    
    public static void setPauseOnReloadInTests(@Nullable final Pair<Integer, Integer> ourPauseOnReloadInTests) {
        CMakeWorkspace.ourPauseOnReloadInTests = ourPauseOnReloadInTests;
    }
    
    public static int getReloadsCounter() {
        return CMakeWorkspace.ourReloadsCounter.get();
    }
    
    public static void clearTestSettings() {
        CMakeWorkspace.ourSkipReloadOnReopenInTests = null;
        CMakeWorkspace.ourSkipLoadingOnFirstOpeningInTests = null;
        CMakeWorkspace.ourForceUseStandardDirForGenerationInTests = null;
        CMakeWorkspace.ourBalloonNotifier = null;
        CMakeWorkspace.ourEnvironmentFactory = null;
        CMakeWorkspace.ourSettingsConfigurator = null;
        CMakeWorkspace.ourPauseOnReloadInTests = null;
    }
    
    @NotNull
    public static CMakeWorkspace getInstance(@NotNull final Project project) {
        try {
            if (project == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "project", "com/jetbrains/cidr/cpp/cmake/workspace/CMakeWorkspace", "getInstance"));
            }
        }
        catch (NullPointerException ex) {
            throw b(ex);
        }
        CMakeWorkspace cMakeWorkspace;
        try {
            cMakeWorkspace = (CMakeWorkspace)project.getComponent((Class)CMakeWorkspace.class);
            if (cMakeWorkspace == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/cpp/cmake/workspace/CMakeWorkspace", "getInstance"));
            }
        }
        catch (NullPointerException ex2) {
            throw b(ex2);
        }
        return cMakeWorkspace;
    }
    
    public CMakeWorkspace(@NotNull final Project myProject) {
        if (myProject == null) {
            throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "project", "com/jetbrains/cidr/cpp/cmake/workspace/CMakeWorkspace", "<init>"));
        }
        this.myStateWasLoaded = false;
        this.myInitialized = false;
        this.myState = this.b((File)null);
        this.myClearGeneratedFilesBeforeReload = false;
        this.myProject = myProject;
        this.mySettings = CMakeSettings.Companion.getInstance(myProject);
        if (!shouldLoadForProject(myProject)) {
            this.myRootsSynchronizer = null;
            this.myWorkspaceWatcher = null;
            return;
        }
        synchronized (CMakeWorkspace.ourCheckCachesValidity) {
            Label_0178: {
                if (CMakeWorkspace.ourCheckCachesValidity.get()) {
                    Label_0163: {
                        try {
                            CMakeWorkspace.ourCheckCachesValidity.set((Object)false);
                            if (!c().exists()) {
                                break Label_0178;
                            }
                            final Path path = getBaseCacheDir();
                            final File file = path.toFile();
                            final boolean b = FileUtil.delete(file);
                            if (!b) {
                                break Label_0163;
                            }
                            break Label_0178;
                        }
                        catch (NullPointerException ex) {
                            throw b(ex);
                        }
                        try {
                            final Path path = getBaseCacheDir();
                            final File file = path.toFile();
                            final boolean b = FileUtil.delete(file);
                            if (!b) {
                                CPPLog.LOG.warn("Cannot delete invalidated CMake generation dir");
                            }
                        }
                        catch (NullPointerException ex2) {
                            throw b(ex2);
                        }
                    }
                }
            }
        }
        this.myRootsSynchronizer = new MyRootsSynchronizer(myProject);
        this.myWorkspaceWatcher = new CMakeWorkspaceWatcher(myProject, this);
    }
    
    @NotNull
    private static ModuleType i() {
        ModuleType instance;
        try {
            instance = CPPModuleType.getInstance();
            if (instance == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/cpp/cmake/workspace/CMakeWorkspace", "getModuleType"));
            }
        }
        catch (NullPointerException ex) {
            throw b(ex);
        }
        return instance;
    }
    
    static boolean shouldLoadForProject(final Project project) {
        Label_0023: {
            try {
                if (project.isDefault()) {
                    return false;
                }
                final Project project2 = project;
                final boolean b = ProjectManagerImpl.isLight(project2);
                if (!b) {
                    break Label_0023;
                }
                return false;
            }
            catch (NullPointerException ex) {
                throw b(ex);
            }
            try {
                final Project project2 = project;
                final boolean b = ProjectManagerImpl.isLight(project2);
                if (!b) {
                    return true;
                }
            }
            catch (NullPointerException ex2) {
                throw b(ex2);
            }
        }
        return false;
    }
    
    public static void invalidateCaches() {
        FileUtil.createIfDoesntExist(c());
    }
    
    @NotNull
    private static File c() {
        File file;
        try {
            file = getBaseCacheDir().resolve(".broken").toFile();
            if (file == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/cpp/cmake/workspace/CMakeWorkspace", "getBrokenMarkerFile"));
            }
        }
        catch (NullPointerException ex) {
            throw b(ex);
        }
        return file;
    }
    
    public static void setCheckCachesValidity(final boolean b) {
        synchronized (CMakeWorkspace.ourCheckCachesValidity) {
            CMakeWorkspace.ourCheckCachesValidity.set((Object)b);
        }
    }
    
    public static void forceReloadOnOpening(@NotNull final VirtualFile virtualFile) {
        try {
            if (virtualFile == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "baseDir", "com/jetbrains/cidr/cpp/cmake/workspace/CMakeWorkspace", "forceReloadOnOpening"));
            }
        }
        catch (NullPointerException ex) {
            throw b(ex);
        }
        virtualFile.putUserData((Key)CMakeWorkspace.FORCE_RELOAD_ON_OPENING, (Object)true);
    }
    
    public void projectOpened() {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     0: aload_0        
        //     1: getfield        com/jetbrains/cidr/cpp/cmake/workspace/CMakeWorkspace.myProject:Lcom/intellij/openapi/project/Project;
        //     4: invokestatic    com/jetbrains/cidr/cpp/cmake/workspace/CMakeWorkspace.shouldLoadForProject:(Lcom/intellij/openapi/project/Project;)Z
        //     7: ifne            15
        //    10: return         
        //    11: invokestatic    com/jetbrains/cidr/cpp/cmake/workspace/CMakeWorkspace.b:(Ljava/lang/Exception;)Ljava/lang/Exception;
        //    14: athrow         
        //    15: aload_0        
        //    16: getfield        com/jetbrains/cidr/cpp/cmake/workspace/CMakeWorkspace.myRootsSynchronizer:Lcom/jetbrains/cidr/cpp/cmake/workspace/CMakeWorkspace$MyRootsSynchronizer;
        //    19: invokevirtual   com/jetbrains/cidr/cpp/cmake/workspace/CMakeWorkspace$MyRootsSynchronizer.getModule:()Lcom/intellij/openapi/module/Module;
        //    22: ifnonnull       30
        //    25: return         
        //    26: invokestatic    com/jetbrains/cidr/cpp/cmake/workspace/CMakeWorkspace.b:(Ljava/lang/Exception;)Ljava/lang/Exception;
        //    29: athrow         
        //    30: aload_0        
        //    31: getfield        com/jetbrains/cidr/cpp/cmake/workspace/CMakeWorkspace.myRootsSynchronizer:Lcom/jetbrains/cidr/cpp/cmake/workspace/CMakeWorkspace$MyRootsSynchronizer;
        //    34: ldc             "CMake"
        //    36: invokevirtual   com/jetbrains/cidr/cpp/cmake/workspace/CMakeWorkspace$MyRootsSynchronizer.installClasspathStorage:(Ljava/lang/String;)V
        //    39: getstatic       com/jetbrains/cidr/cpp/cmake/workspace/CMakeWorkspace.ourSettingsConfigurator:Lcom/intellij/util/Consumer;
        //    42: ifnull          68
        //    45: getstatic       com/jetbrains/cidr/cpp/cmake/workspace/CMakeWorkspace.ourSettingsConfigurator:Lcom/intellij/util/Consumer;
        //    48: aload_0        
        //    49: invokevirtual   com/jetbrains/cidr/cpp/cmake/workspace/CMakeWorkspace.getSettings:()Lcom/jetbrains/cidr/cpp/cmake/CMakeSettings;
        //    52: invokeinterface com/intellij/util/Consumer.consume:(Ljava/lang/Object;)V
        //    57: aconst_null    
        //    58: putstatic       com/jetbrains/cidr/cpp/cmake/workspace/CMakeWorkspace.ourSettingsConfigurator:Lcom/intellij/util/Consumer;
        //    61: goto            68
        //    64: invokestatic    com/jetbrains/cidr/cpp/cmake/workspace/CMakeWorkspace.b:(Ljava/lang/Exception;)Ljava/lang/Exception;
        //    67: athrow         
        //    68: iconst_0       
        //    69: istore_1       
        //    70: aload_0        
        //    71: getfield        com/jetbrains/cidr/cpp/cmake/workspace/CMakeWorkspace.myProject:Lcom/intellij/openapi/project/Project;
        //    74: invokeinterface com/intellij/openapi/project/Project.getBaseDir:()Lcom/intellij/openapi/vfs/VirtualFile;
        //    79: astore_3       
        //    80: aload_3        
        //    81: ifnonnull       89
        //    84: iconst_1       
        //    85: istore_2       
        //    86: goto            120
        //    89: aload_3        
        //    90: getstatic       com/jetbrains/cidr/cpp/cmake/workspace/CMakeWorkspace.FORCE_RELOAD_ON_OPENING:Lcom/intellij/openapi/util/Key;
        //    93: invokevirtual   com/intellij/openapi/vfs/VirtualFile.getUserData:(Lcom/intellij/openapi/util/Key;)Ljava/lang/Object;
        //    96: getstatic       java/lang/Boolean.TRUE:Ljava/lang/Boolean;
        //    99: if_acmpne       110
        //   102: iconst_1       
        //   103: goto            111
        //   106: invokestatic    com/jetbrains/cidr/cpp/cmake/workspace/CMakeWorkspace.b:(Ljava/lang/Exception;)Ljava/lang/Exception;
        //   109: athrow         
        //   110: iconst_0       
        //   111: istore_2       
        //   112: aload_3        
        //   113: getstatic       com/jetbrains/cidr/cpp/cmake/workspace/CMakeWorkspace.FORCE_RELOAD_ON_OPENING:Lcom/intellij/openapi/util/Key;
        //   116: aconst_null    
        //   117: invokevirtual   com/intellij/openapi/vfs/VirtualFile.putUserData:(Lcom/intellij/openapi/util/Key;Ljava/lang/Object;)V
        //   120: aload_0        
        //   121: getfield        com/jetbrains/cidr/cpp/cmake/workspace/CMakeWorkspace.myProject:Lcom/intellij/openapi/project/Project;
        //   124: invokestatic    com/jetbrains/cidr/cpp/cmake/workspace/CMakeWorkspace.a:(Lcom/intellij/openapi/project/Project;)Lcom/jetbrains/cidr/cpp/cmake/CMakeProjectOpenProcessor$OpenProjectSpec;
        //   127: astore          4
        //   129: aload           4
        //   131: ifnull          163
        //   134: aload_0        
        //   135: aload_0        
        //   136: aload_0        
        //   137: aload           4
        //   139: getfield        com/jetbrains/cidr/cpp/cmake/CMakeProjectOpenProcessor$OpenProjectSpec.sourceDir:Ljava/io/File;
        //   142: dup_x1         
        //   143: putfield        com/jetbrains/cidr/cpp/cmake/workspace/CMakeWorkspace.myProjectDir:Ljava/io/File;
        //   146: invokespecial   com/jetbrains/cidr/cpp/cmake/workspace/CMakeWorkspace.b:(Ljava/io/File;)Lcom/jetbrains/cidr/cpp/cmake/workspace/CMakeWorkspace$State;
        //   149: putfield        com/jetbrains/cidr/cpp/cmake/workspace/CMakeWorkspace.myState:Lcom/jetbrains/cidr/cpp/cmake/workspace/CMakeWorkspace$State;
        //   152: iconst_1       
        //   153: istore_2       
        //   154: aload_0        
        //   155: aload           4
        //   157: invokespecial   com/jetbrains/cidr/cpp/cmake/workspace/CMakeWorkspace.a:(Lcom/jetbrains/cidr/cpp/cmake/CMakeProjectOpenProcessor$OpenProjectSpec;)V
        //   160: goto            253
        //   163: aload_0        
        //   164: getfield        com/jetbrains/cidr/cpp/cmake/workspace/CMakeWorkspace.myStateWasLoaded:Z
        //   167: ifne            234
        //   170: aload_0        
        //   171: getfield        com/jetbrains/cidr/cpp/cmake/workspace/CMakeWorkspace.myState:Lcom/jetbrains/cidr/cpp/cmake/workspace/CMakeWorkspace$State;
        //   174: getfield        com/jetbrains/cidr/cpp/cmake/workspace/CMakeWorkspace$State.projectDir:Ljava/io/File;
        //   177: ifnonnull       229
        //   180: goto            187
        //   183: invokestatic    com/jetbrains/cidr/cpp/cmake/workspace/CMakeWorkspace.b:(Ljava/lang/Exception;)Ljava/lang/Exception;
        //   186: athrow         
        //   187: aload_3        
        //   188: ifnull          229
        //   191: goto            198
        //   194: invokestatic    com/jetbrains/cidr/cpp/cmake/workspace/CMakeWorkspace.b:(Ljava/lang/Exception;)Ljava/lang/Exception;
        //   197: athrow         
        //   198: aload_0        
        //   199: aload_0        
        //   200: aload_0        
        //   201: new             Ljava/io/File;
        //   204: dup            
        //   205: aload_3        
        //   206: invokevirtual   com/intellij/openapi/vfs/VirtualFile.getPath:()Ljava/lang/String;
        //   209: invokespecial   java/io/File.<init>:(Ljava/lang/String;)V
        //   212: dup_x1         
        //   213: putfield        com/jetbrains/cidr/cpp/cmake/workspace/CMakeWorkspace.myProjectDir:Ljava/io/File;
        //   216: invokespecial   com/jetbrains/cidr/cpp/cmake/workspace/CMakeWorkspace.b:(Ljava/io/File;)Lcom/jetbrains/cidr/cpp/cmake/workspace/CMakeWorkspace$State;
        //   219: putfield        com/jetbrains/cidr/cpp/cmake/workspace/CMakeWorkspace.myState:Lcom/jetbrains/cidr/cpp/cmake/workspace/CMakeWorkspace$State;
        //   222: goto            229
        //   225: invokestatic    com/jetbrains/cidr/cpp/cmake/workspace/CMakeWorkspace.b:(Ljava/lang/Exception;)Ljava/lang/Exception;
        //   228: athrow         
        //   229: iconst_1       
        //   230: istore_2       
        //   231: goto            253
        //   234: getstatic       com/jetbrains/cidr/cpp/cmake/workspace/CMakeWorkspace.ourSkipReloadOnReopenInTests:Ljava/lang/Boolean;
        //   237: getstatic       java/lang/Boolean.TRUE:Ljava/lang/Boolean;
        //   240: if_acmpne       251
        //   243: iconst_1       
        //   244: goto            252
        //   247: invokestatic    com/jetbrains/cidr/cpp/cmake/workspace/CMakeWorkspace.b:(Ljava/lang/Exception;)Ljava/lang/Exception;
        //   250: athrow         
        //   251: iconst_0       
        //   252: istore_1       
        //   253: invokestatic    com/intellij/openapi/application/ApplicationManager.getApplication:()Lcom/intellij/openapi/application/Application;
        //   256: aload_0        
        //   257: invokedynamic   run:(Lcom/jetbrains/cidr/cpp/cmake/workspace/CMakeWorkspace;)Ljava/lang/Runnable;
        //   262: invokeinterface com/intellij/openapi/application/Application.runWriteAction:(Ljava/lang/Runnable;)V
        //   267: aload_0        
        //   268: iload_2        
        //   269: ifne            280
        //   272: iconst_1       
        //   273: goto            281
        //   276: invokestatic    com/jetbrains/cidr/cpp/cmake/workspace/CMakeWorkspace.b:(Ljava/lang/Exception;)Ljava/lang/Exception;
        //   279: athrow         
        //   280: iconst_0       
        //   281: invokespecial   com/jetbrains/cidr/cpp/cmake/workspace/CMakeWorkspace.b:(Z)V
        //   284: aload_0        
        //   285: invokespecial   com/jetbrains/cidr/cpp/cmake/workspace/CMakeWorkspace.j:()V
        //   288: aload_0        
        //   289: iconst_1       
        //   290: putfield        com/jetbrains/cidr/cpp/cmake/workspace/CMakeWorkspace.myInitialized:Z
        //   293: iload_1        
        //   294: ifne            325
        //   297: getstatic       com/jetbrains/cidr/cpp/cmake/workspace/CMakeWorkspace.ourSkipLoadingOnFirstOpeningInTests:Ljava/lang/Boolean;
        //   300: getstatic       java/lang/Boolean.TRUE:Ljava/lang/Boolean;
        //   303: if_acmpeq       325
        //   306: goto            313
        //   309: invokestatic    com/jetbrains/cidr/cpp/cmake/workspace/CMakeWorkspace.b:(Ljava/lang/Exception;)Ljava/lang/Exception;
        //   312: athrow         
        //   313: aload_0        
        //   314: iload_2        
        //   315: invokevirtual   com/jetbrains/cidr/cpp/cmake/workspace/CMakeWorkspace.scheduleReload:(Z)V
        //   318: goto            325
        //   321: invokestatic    com/jetbrains/cidr/cpp/cmake/workspace/CMakeWorkspace.b:(Ljava/lang/Exception;)Ljava/lang/Exception;
        //   324: athrow         
        //   325: return         
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                            
        //  -----  -----  -----  -----  --------------------------------
        //  0      11     11     15     Ljava/lang/NullPointerException;
        //  15     26     26     30     Ljava/lang/NullPointerException;
        //  30     61     64     68     Ljava/lang/NullPointerException;
        //  89     106    106    110    Ljava/lang/NullPointerException;
        //  163    180    183    187    Ljava/lang/NullPointerException;
        //  170    191    194    198    Ljava/lang/NullPointerException;
        //  187    222    225    229    Ljava/lang/NullPointerException;
        //  234    247    247    251    Ljava/lang/NullPointerException;
        //  253    276    276    280    Ljava/lang/NullPointerException;
        //  281    306    309    313    Ljava/lang/NullPointerException;
        //  297    318    321    325    Ljava/lang/NullPointerException;
        // 
        // The error that occurred was:
        // 
        // java.lang.IllegalStateException: Expression is linked from several locations: Label_0187:
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
    
    public void projectClosed() {
        try {
            if (!shouldLoadForProject(this.myProject)) {
                return;
            }
        }
        catch (NullPointerException ex) {
            throw b(ex);
        }
        this.cancelActiveReload();
        this.myWorkspaceWatcher.shutdown();
    }
    
    private void a(@NotNull final CMakeProjectOpenProcessor.OpenProjectSpec openProjectSpec) {
        try {
            if (openProjectSpec == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "openSpec", "com/jetbrains/cidr/cpp/cmake/workspace/CMakeWorkspace", "updateSettingsFromOpenSpec"));
            }
        }
        catch (NullPointerException ex) {
            throw b(ex);
        }
        File generationDir = null;
        Label_0085: {
            Label_0065: {
                try {
                    if (openProjectSpec.generationDir == null) {
                        return;
                    }
                    final CMakeProjectOpenProcessor.OpenProjectSpec openProjectSpec2 = openProjectSpec;
                    final boolean b = openProjectSpec2.isInSourceGeneration();
                    if (b) {
                        break Label_0065;
                    }
                    break Label_0065;
                }
                catch (NullPointerException ex2) {
                    throw b(ex2);
                }
                try {
                    final CMakeProjectOpenProcessor.OpenProjectSpec openProjectSpec2 = openProjectSpec;
                    final boolean b = openProjectSpec2.isInSourceGeneration();
                    if (b) {
                        generationDir = new File(".");
                        break Label_0085;
                    }
                }
                catch (NullPointerException ex3) {
                    throw b(ex3);
                }
            }
            generationDir = openProjectSpec.generationDir;
        }
        this.getSettings().setConfigurations(Collections.singletonList(new CMakeSettings.Configuration(openProjectSpec.configuration).withGenerationDir(generationDir)));
    }
    
    @Nullable
    private static CMakeProjectOpenProcessor.OpenProjectSpec a(@NotNull final Project project) {
        try {
            if (project == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "project", "com/jetbrains/cidr/cpp/cmake/workspace/CMakeWorkspace", "getAndClearSourceAndGenerationDirToOpen"));
            }
        }
        catch (NullPointerException ex) {
            throw b(ex);
        }
        return CMakeProjectOpenProcessor.getHelper().getAndClearFileToOpenData(project);
    }
    
    private void j() {
        ApplicationManager.getApplication().getMessageBus().connect((Disposable)this.myProject).subscribe((Topic)CPPToolchainsListener.TOPIC, (Object)new CPPToolchainsListener() {
            @Override
            public void environmentChanged() {
                CMakeWorkspace.this.scheduleReload(true);
            }
        });
        final MessageBusConnection connect = this.myProject.getMessageBus().connect((Disposable)this.myProject);
        connect.subscribe((Topic)CMakeSettingsListener.Companion.getTOPIC(), (Object)new CMakeSettingsListener() {
            @Override
            public void autoReloadChanged() {
                CMakeWorkspace.this.myWorkspaceWatcher.schedulePendingAutoReloads();
            }
            
            @Override
            public void configurationsChanged() {
                ApplicationManager.getApplication().runWriteAction(() -> CMakeWorkspace.this.a(true));
                CMakeWorkspace.this.scheduleReload(false);
            }
        });
        CidrRootConfiguration.setEnabledFor(this.myRootsSynchronizer.getModule());
        connect.subscribe((Topic)CidrRootConfigurationListener.TOPIC, (Object)new CidrRootConfigurationListener() {
            @Override
            public void configurationChanged() {
                CMakeWorkspace.this.d();
            }
        });
        this.myWorkspaceWatcher.listenForChanges();
        this.myOutputConsole.listenForChanges();
    }
    
    private void b(boolean b) {
        final ToolWindow registerToolWindow = ToolWindowManager.getInstance(this.myProject).registerToolWindow("CMake", false, ToolWindowAnchor.BOTTOM, (Disposable)this.myProject, true);
        registerToolWindow.setIcon(CLionIcons.CMakeToolWindow);
        Disposer.register((Disposable)this.myProject, (Disposable)new Disposable() {
            final /* synthetic */ ContentManager val$cm = registerToolWindow.getContentManager();
            
            public void dispose() {
                this.val$cm.removeAllContents(true);
            }
        });
        this.myOutputConsole = new CMakeOutputConsole(this.myProject);
        if (this.myState.model == null) {
            b = false;
        }
        this.myOutputConsole.restoreTabs(this.myProjectDir, this.a(this.getSettings().getConfigurations(), false), b);
    }
    
    private void a() {
        try {
            ApplicationManager.getApplication().assertIsDispatchThread();
            if (!this.myReportFilesOutsideRoots || this.myIgnoreFilesOutsideOfProjectRoot) {
                return;
            }
        }
        catch (NullPointerException ex) {
            throw b(ex);
        }
        this.a(MessageType.WARNING, CPPBundle.message("cmake.filesOutsideOfProject", "change", "ignore"), (HyperlinkListener)new HyperlinkAdapter() {
            protected void hyperlinkActivated(final HyperlinkEvent hyperlinkEvent) {
                if ("change".equals(hyperlinkEvent.getDescription())) {
                    ChangeCMakeProjectContentRootAction.perform(CMakeWorkspace.this.myProject);
                }
                else if ("ignore".equals(hyperlinkEvent.getDescription())) {
                    CMakeWorkspace.this.myIgnoreFilesOutsideOfProjectRoot = true;
                }
            }
        });
    }
    
    @NotNull
    public static ToolWindow getToolWindow(@NotNull final Project project) {
        try {
            if (project == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "project", "com/jetbrains/cidr/cpp/cmake/workspace/CMakeWorkspace", "getToolWindow"));
            }
        }
        catch (NullPointerException ex) {
            throw b(ex);
        }
        ToolWindow toolWindow;
        try {
            toolWindow = ToolWindowManager.getInstance(project).getToolWindow("CMake");
            if (toolWindow == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/cpp/cmake/workspace/CMakeWorkspace", "getToolWindow"));
            }
        }
        catch (NullPointerException ex2) {
            throw b(ex2);
        }
        return toolWindow;
    }
    
    private void a(@NotNull final MessageType messageType, @NotNull final String s, @NotNull final HyperlinkListener hyperlinkListener) {
        try {
            if (messageType == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "type", "com/jetbrains/cidr/cpp/cmake/workspace/CMakeWorkspace", "showBalloonNotification"));
            }
        }
        catch (NullPointerException ex) {
            throw b(ex);
        }
        try {
            if (s == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "message", "com/jetbrains/cidr/cpp/cmake/workspace/CMakeWorkspace", "showBalloonNotification"));
            }
        }
        catch (NullPointerException ex2) {
            throw b(ex2);
        }
        try {
            if (hyperlinkListener == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "listener", "com/jetbrains/cidr/cpp/cmake/workspace/CMakeWorkspace", "showBalloonNotification"));
            }
        }
        catch (NullPointerException ex3) {
            throw b(ex3);
        }
        try {
            if (CMakeWorkspace.ourBalloonNotifier != null) {
                CMakeWorkspace.ourBalloonNotifier.notify(messageType, s, hyperlinkListener);
                return;
            }
        }
        catch (NullPointerException ex4) {
            throw b(ex4);
        }
        ToolWindowManager.getInstance(this.myProject).notifyByBalloon("CMake", messageType, s, (Icon)null, hyperlinkListener);
    }
    
    @NotNull
    public CMakeOutputConsole getConsole() {
        CMakeOutputConsole myOutputConsole;
        try {
            myOutputConsole = this.myOutputConsole;
            if (myOutputConsole == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/cpp/cmake/workspace/CMakeWorkspace", "getConsole"));
            }
        }
        catch (NullPointerException ex) {
            throw b(ex);
        }
        return myOutputConsole;
    }
    
    @NotNull
    public CMakeSettings getSettings() {
        CMakeSettings mySettings;
        try {
            mySettings = this.mySettings;
            if (mySettings == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/cpp/cmake/workspace/CMakeWorkspace", "getSettings"));
            }
        }
        catch (NullPointerException ex) {
            throw b(ex);
        }
        return mySettings;
    }
    
    @NotNull
    public static Path getBaseCacheDir() {
        Path value;
        try {
            value = Paths.get(FileUtil.toCanonicalPath(PathManager.getSystemPath()), "cmake");
            if (value == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/cpp/cmake/workspace/CMakeWorkspace", "getBaseCacheDir"));
            }
        }
        catch (NullPointerException ex) {
            throw b(ex);
        }
        return value;
    }
    
    @NotNull
    public Path getProjectCacheDir() {
        Path projectCachePath;
        try {
            ApplicationManager.getApplication().assertReadAccessAllowed();
            projectCachePath = ProjectUtil.getProjectCachePath(this.myProject, getBaseCacheDir(), false, "-");
            if (projectCachePath == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/cpp/cmake/workspace/CMakeWorkspace", "getProjectCacheDir"));
            }
        }
        catch (NullPointerException ex) {
            throw b(ex);
        }
        return projectCachePath;
    }
    
    @NotNull
    private File f() {
        File file;
        try {
            file = this.getProjectCacheDir().resolve("CMakeModel").toFile();
            if (file == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/cpp/cmake/workspace/CMakeWorkspace", "getProjectCacheModelFile"));
            }
        }
        catch (NullPointerException ex) {
            throw b(ex);
        }
        return file;
    }
    
    @NotNull
    public List<File> getEffectiveConfigurationGenerationDirs(@NotNull final List<Pair<String, File>> p0) {
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
        //    18: ldc             "configurationNamesWithSpecifiedDirs"
        //    20: aastore        
        //    21: dup            
        //    22: ldc             1
        //    24: ldc             "com/jetbrains/cidr/cpp/cmake/workspace/CMakeWorkspace"
        //    26: aastore        
        //    27: dup            
        //    28: ldc             2
        //    30: ldc             "getEffectiveConfigurationGenerationDirs"
        //    32: aastore        
        //    33: invokestatic    java/lang/String.format:(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
        //    36: invokespecial   java/lang/IllegalArgumentException.<init>:(Ljava/lang/String;)V
        //    39: athrow         
        //    40: invokestatic    com/jetbrains/cidr/cpp/cmake/workspace/CMakeWorkspace.b:(Ljava/lang/Exception;)Ljava/lang/Exception;
        //    43: athrow         
        //    44: aload_1        
        //    45: invokedynamic   fun:()Lcom/intellij/util/Function;
        //    50: invokestatic    com/intellij/util/containers/ContainerUtil.map:(Ljava/util/Collection;Lcom/intellij/util/Function;)Ljava/util/List;
        //    53: invokestatic    com/jetbrains/cidr/cpp/cmake/workspace/CMakeWorkspace.getConfigurationGenerationDirNames:(Ljava/util/List;)Ljava/util/List;
        //    56: astore_2       
        //    57: invokestatic    com/intellij/openapi/application/ApplicationManager.getApplication:()Lcom/intellij/openapi/application/Application;
        //    60: invokeinterface com/intellij/openapi/application/Application.isUnitTestMode:()Z
        //    65: ifeq            140
        //    68: getstatic       com/jetbrains/cidr/cpp/cmake/workspace/CMakeWorkspace.ourForceUseStandardDirForGenerationInTests:Ljava/lang/Boolean;
        //    71: getstatic       java/lang/Boolean.TRUE:Ljava/lang/Boolean;
        //    74: if_acmpeq       140
        //    77: goto            84
        //    80: invokestatic    com/jetbrains/cidr/cpp/cmake/workspace/CMakeWorkspace.b:(Ljava/lang/Exception;)Ljava/lang/Exception;
        //    83: athrow         
        //    84: aload_2        
        //    85: aload_0        
        //    86: invokedynamic   fun:(Lcom/jetbrains/cidr/cpp/cmake/workspace/CMakeWorkspace;)Lcom/intellij/util/Function;
        //    91: invokestatic    com/intellij/util/containers/ContainerUtil.map:(Ljava/util/Collection;Lcom/intellij/util/Function;)Ljava/util/List;
        //    94: dup            
        //    95: ifnonnull       139
        //    98: goto            105
        //   101: invokestatic    com/jetbrains/cidr/cpp/cmake/workspace/CMakeWorkspace.b:(Ljava/lang/Exception;)Ljava/lang/Exception;
        //   104: athrow         
        //   105: new             Ljava/lang/IllegalStateException;
        //   108: dup            
        //   109: ldc             "@NotNull method %s.%s must not return null"
        //   111: ldc             2
        //   113: anewarray       Ljava/lang/Object;
        //   116: dup            
        //   117: ldc             0
        //   119: ldc             "com/jetbrains/cidr/cpp/cmake/workspace/CMakeWorkspace"
        //   121: aastore        
        //   122: dup            
        //   123: ldc             1
        //   125: ldc             "getEffectiveConfigurationGenerationDirs"
        //   127: aastore        
        //   128: invokestatic    java/lang/String.format:(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
        //   131: invokespecial   java/lang/IllegalStateException.<init>:(Ljava/lang/String;)V
        //   134: athrow         
        //   135: invokestatic    com/jetbrains/cidr/cpp/cmake/workspace/CMakeWorkspace.b:(Ljava/lang/Exception;)Ljava/lang/Exception;
        //   138: athrow         
        //   139: areturn        
        //   140: aload_1        
        //   141: aload_2        
        //   142: invokestatic    com/intellij/util/containers/ContainerUtil.zip:(Ljava/lang/Iterable;Ljava/lang/Iterable;)Ljava/lang/Iterable;
        //   145: aload_0        
        //   146: invokedynamic   fun:(Lcom/jetbrains/cidr/cpp/cmake/workspace/CMakeWorkspace;)Lcom/intellij/util/Function;
        //   151: invokestatic    com/intellij/util/containers/ContainerUtil.map:(Ljava/lang/Iterable;Lcom/intellij/util/Function;)Ljava/util/List;
        //   154: dup            
        //   155: ifnonnull       192
        //   158: new             Ljava/lang/IllegalStateException;
        //   161: dup            
        //   162: ldc             "@NotNull method %s.%s must not return null"
        //   164: ldc             2
        //   166: anewarray       Ljava/lang/Object;
        //   169: dup            
        //   170: ldc             0
        //   172: ldc             "com/jetbrains/cidr/cpp/cmake/workspace/CMakeWorkspace"
        //   174: aastore        
        //   175: dup            
        //   176: ldc             1
        //   178: ldc             "getEffectiveConfigurationGenerationDirs"
        //   180: aastore        
        //   181: invokestatic    java/lang/String.format:(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
        //   184: invokespecial   java/lang/IllegalStateException.<init>:(Ljava/lang/String;)V
        //   187: athrow         
        //   188: invokestatic    com/jetbrains/cidr/cpp/cmake/workspace/CMakeWorkspace.b:(Ljava/lang/Exception;)Ljava/lang/Exception;
        //   191: athrow         
        //   192: areturn        
        //    Signature:
        //  (Ljava/util/List<Lcom/intellij/openapi/util/Pair<Ljava/lang/String;Ljava/io/File;>;>;)Ljava/util/List<Ljava/io/File;>;
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                            
        //  -----  -----  -----  -----  --------------------------------
        //  0      40     40     44     Ljava/lang/NullPointerException;
        //  57     77     80     84     Ljava/lang/NullPointerException;
        //  68     98     101    105    Ljava/lang/NullPointerException;
        //  84     135    135    139    Ljava/lang/NullPointerException;
        //  140    188    188    192    Ljava/lang/NullPointerException;
        // 
        // The error that occurred was:
        // 
        // java.lang.IllegalStateException: Expression is linked from several locations: Label_0084:
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
    public static List<String> getConfigurationGenerationDirNames(@NotNull final List<String> list) {
        try {
            if (list == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "configurationNames", "com/jetbrains/cidr/cpp/cmake/workspace/CMakeWorkspace", "getConfigurationGenerationDirNames"));
            }
        }
        catch (NullPointerException ex) {
            throw b(ex);
        }
        final ArrayList<Object> list2 = new ArrayList<Object>(list);
        final Pattern compile = Pattern.compile("(.*)-(\\d+)");
        boolean b;
        do {
            b = false;
            final THashSet set = new THashSet();
            for (int i = 0; i < list2.size(); ++i) {
                String group = list2.get(i);
                if (!((Set<String>)set).add(group)) {
                    b = true;
                    int int1 = 0;
                    Label_0186: {
                        try {
                            if (group.length() <= 0 || !Character.isDigit(group.charAt(group.length() - 1))) {
                                break Label_0186;
                            }
                        }
                        catch (NullPointerException ex2) {
                            throw b(ex2);
                        }
                        final Matcher matcher = compile.matcher(group);
                        if (matcher.matches()) {
                            group = matcher.group(1);
                            int1 = StringUtil.parseInt(matcher.group(2), int1);
                        }
                    }
                    list2.set(i, group + "-" + (int1 + 1));
                }
            }
        } while (b);
        List map;
        try {
            map = ContainerUtil.map((Collection)list2, s -> "cmake-build-" + StringUtil.toLowerCase(s));
            if (map == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/cpp/cmake/workspace/CMakeWorkspace", "getConfigurationGenerationDirNames"));
            }
        }
        catch (NullPointerException ex3) {
            throw b(ex3);
        }
        return (List<String>)map;
    }
    
    void clearGeneratedFiles() {
        final ArrayList<Object> list = new ArrayList<Object>();
        list.addAll(this.myState.a());
        list.addAll(this.b(this.getSettings().getConfigurations()));
        ContainerUtil.removeDuplicates((Collection)list);
        final Iterator<File> iterator = list.iterator();
        while (iterator.hasNext()) {
            CMakeGenerator.clearGeneratedCMakeFiles(iterator.next());
        }
        a((List<File>)list, null);
    }
    
    private static void a(final List<File> list, @Nullable final Runnable runnable) {
        try {
            if (!list.isEmpty()) {
                LocalFileSystem.getInstance().refreshIoFiles((Iterable)list, true, true, runnable);
                return;
            }
        }
        catch (NullPointerException ex) {
            throw b(ex);
        }
        try {
            if (runnable != null) {
                runnable.run();
            }
        }
        catch (NullPointerException ex2) {
            throw b(ex2);
        }
    }
    
    @NotNull
    public File getProjectSubDir(@NotNull final File file) {
        try {
            if (file == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "subdir", "com/jetbrains/cidr/cpp/cmake/workspace/CMakeWorkspace", "getProjectSubDir"));
            }
        }
        catch (NullPointerException ex) {
            throw b(ex);
        }
        Label_0098: {
            File file2 = null;
            Label_0063: {
                try {
                    if (!file.isAbsolute()) {
                        break Label_0098;
                    }
                    file2 = file;
                    if (file2 == null) {
                        break Label_0063;
                    }
                    return file2;
                }
                catch (NullPointerException ex2) {
                    throw b(ex2);
                }
                try {
                    file2 = file;
                    if (file2 == null) {
                        throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/cpp/cmake/workspace/CMakeWorkspace", "getProjectSubDir"));
                    }
                }
                catch (NullPointerException ex3) {
                    throw b(ex3);
                }
            }
            return file2;
        }
        final String basePath = this.myProject.getBasePath();
        try {
            if (basePath == null) {
                throw new NullPointerException("Project base path is null. default = " + this.myProject.isDefault());
            }
        }
        catch (NullPointerException ex4) {
            throw b(ex4);
        }
        File absoluteFile;
        try {
            absoluteFile = new File(basePath, file.getPath()).getAbsoluteFile();
            if (absoluteFile == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/cpp/cmake/workspace/CMakeWorkspace", "getProjectSubDir"));
            }
        }
        catch (NullPointerException ex5) {
            throw b(ex5);
        }
        return absoluteFile;
    }
    
    @NotNull
    public List<CMakeModelConfigurationData> getModelConfigurationData() {
        final State myState = this.myState;
        Object o = null;
        Label_0029: {
            try {
                if (myState.model == null) {
                    final Object o2;
                    o = (o2 = Collections.emptyList());
                    break Label_0029;
                }
            }
            catch (NullPointerException ex) {
                throw b(ex);
            }
            Object o2;
            o = (o2 = myState.model.getConfigurationData());
            try {
                if (o2 == null) {
                    throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/cpp/cmake/workspace/CMakeWorkspace", "getModelConfigurationData"));
                }
            }
            catch (NullPointerException ex2) {
                throw b(ex2);
            }
        }
        return (List<CMakeModelConfigurationData>)o;
    }
    
    public <T> Future<T> lockModelDuring(final Callable<T> callable) {
        return this.myWorkspaceWatcher.lockModelDuring(callable);
    }
    
    public void setReloadInBackgroundInTests(final boolean b, final Disposable disposable) {
        this.myWorkspaceWatcher.setReloadInBackgroundInTests(b, disposable);
    }
    
    public void waitForReloadsToFinish() throws TimeoutException {
        this.myWorkspaceWatcher.waitForReloadsToFinish();
    }
    
    public void cancelActiveReload() {
        final ProgressIndicator runningReloadTaskIndicator = this.myWorkspaceWatcher.getRunningReloadTaskIndicator();
        try {
            if (runningReloadTaskIndicator != null) {
                runningReloadTaskIndicator.cancel();
            }
        }
        catch (NullPointerException ex) {
            throw b(ex);
        }
    }
    
    public void stopReloadsOnDocumentChange() {
        this.myWorkspaceWatcher.stopReloadsOnDocumentChange();
    }
    
    public void resumeReloadsOnDocumentChange() {
        this.myWorkspaceWatcher.resumeReloadsOnDocumentChange();
    }
    
    public void scheduleReload(final boolean b) {
        this.myWorkspaceWatcher.scheduleReload(progressIndicator -> this.b(progressIndicator), b);
    }
    
    public void scheduleReloadNewProjectDir(@NotNull final File myProjectDir) {
        try {
            if (myProjectDir == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "newProjectDir", "com/jetbrains/cidr/cpp/cmake/workspace/CMakeWorkspace", "scheduleReloadNewProjectDir"));
            }
        }
        catch (NullPointerException ex) {
            throw b(ex);
        }
        this.myProjectDir = myProjectDir;
        this.scheduleReload(true);
    }
    
    public void scheduleClearGeneratedFilesAndReload() {
        this.scheduleReload(this.myClearGeneratedFilesBeforeReload = true);
    }
    
    private <T> T a(final Computable<T> computable) {
        return (T)ReadAction.compute(() -> {
            try {
                if (this.myProject.isDisposed()) {
                    throw new ProcessCanceledException();
                }
            }
            catch (RuntimeException ex) {
                throw b(ex);
            }
            ProgressManager.checkCanceled();
            return computable.compute();
        });
    }
    
    private void c(final Runnable runnable) {
        this.a((com.intellij.openapi.util.Computable<Object>)(() -> {
            runnable.run();
            return null;
        }));
    }
    
    private void b(@NotNull final ProgressIndicator p0) {
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
        //    18: ldc             "indicator"
        //    20: aastore        
        //    21: dup            
        //    22: ldc             1
        //    24: ldc             "com/jetbrains/cidr/cpp/cmake/workspace/CMakeWorkspace"
        //    26: aastore        
        //    27: dup            
        //    28: ldc             2
        //    30: ldc             "doReload"
        //    32: aastore        
        //    33: invokestatic    java/lang/String.format:(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
        //    36: invokespecial   java/lang/IllegalArgumentException.<init>:(Ljava/lang/String;)V
        //    39: athrow         
        //    40: invokestatic    com/jetbrains/cidr/cpp/cmake/workspace/CMakeWorkspace.b:(Ljava/lang/Exception;)Ljava/lang/Exception;
        //    43: athrow         
        //    44: invokestatic    com/intellij/openapi/application/ApplicationManager.getApplication:()Lcom/intellij/openapi/application/Application;
        //    47: invokeinterface com/intellij/openapi/application/Application.isWriteAccessAllowed:()Z
        //    52: ifeq            69
        //    55: new             Ljava/lang/AssertionError;
        //    58: dup            
        //    59: ldc             "CMake reloading should not be initiated from writeAction. Will cause a deadlock."
        //    61: invokespecial   java/lang/AssertionError.<init>:(Ljava/lang/Object;)V
        //    64: athrow         
        //    65: invokestatic    com/jetbrains/cidr/cpp/cmake/workspace/CMakeWorkspace.b:(Ljava/lang/Exception;)Ljava/lang/Exception;
        //    68: athrow         
        //    69: aload_0        
        //    70: getfield        com/jetbrains/cidr/cpp/cmake/workspace/CMakeWorkspace.myClearGeneratedFilesBeforeReload:Z
        //    73: ifeq            92
        //    76: aload_0        
        //    77: iconst_0       
        //    78: putfield        com/jetbrains/cidr/cpp/cmake/workspace/CMakeWorkspace.myClearGeneratedFilesBeforeReload:Z
        //    81: aload_0        
        //    82: invokevirtual   com/jetbrains/cidr/cpp/cmake/workspace/CMakeWorkspace.clearGeneratedFiles:()V
        //    85: goto            92
        //    88: invokestatic    com/jetbrains/cidr/cpp/cmake/workspace/CMakeWorkspace.b:(Ljava/lang/Exception;)Ljava/lang/Exception;
        //    91: athrow         
        //    92: getstatic       com/jetbrains/cidr/cpp/cmake/workspace/CMakeWorkspace.ourReloadsCounter:Ljava/util/concurrent/atomic/AtomicInteger;
        //    95: invokevirtual   java/util/concurrent/atomic/AtomicInteger.incrementAndGet:()I
        //    98: pop            
        //    99: aload_0        
        //   100: aload_0        
        //   101: invokedynamic   compute:(Lcom/jetbrains/cidr/cpp/cmake/workspace/CMakeWorkspace;)Lcom/intellij/openapi/util/Computable;
        //   106: invokespecial   com/jetbrains/cidr/cpp/cmake/workspace/CMakeWorkspace.a:(Lcom/intellij/openapi/util/Computable;)Ljava/lang/Object;
        //   109: checkcast       Lcom/jetbrains/cidr/cpp/cmake/workspace/CMakeWorkspaceListener;
        //   112: astore_2       
        //   113: getstatic       com/jetbrains/cidr/cpp/cmake/workspace/CMakeWorkspace.ourPauseOnReloadInTests:Lcom/intellij/openapi/util/Pair;
        //   116: ifnull          159
        //   119: getstatic       com/jetbrains/cidr/cpp/cmake/workspace/CMakeWorkspace.ourPauseOnReloadInTests:Lcom/intellij/openapi/util/Pair;
        //   122: getfield        com/intellij/openapi/util/Pair.first:Ljava/lang/Object;
        //   125: ifnull          159
        //   128: goto            135
        //   131: invokestatic    com/jetbrains/cidr/cpp/cmake/workspace/CMakeWorkspace.b:(Ljava/lang/Exception;)Ljava/lang/Exception;
        //   134: athrow         
        //   135: getstatic       com/jetbrains/cidr/cpp/cmake/workspace/CMakeWorkspace.ourPauseOnReloadInTests:Lcom/intellij/openapi/util/Pair;
        //   138: getfield        com/intellij/openapi/util/Pair.first:Ljava/lang/Object;
        //   141: checkcast       Ljava/lang/Integer;
        //   144: invokevirtual   java/lang/Integer.intValue:()I
        //   147: i2l            
        //   148: invokestatic    java/lang/Thread.sleep:(J)V
        //   151: goto            159
        //   154: invokestatic    com/jetbrains/cidr/cpp/cmake/workspace/CMakeWorkspace.b:(Ljava/lang/Exception;)Ljava/lang/Exception;
        //   157: athrow         
        //   158: astore_3       
        //   159: iconst_0       
        //   160: istore_3       
        //   161: invokestatic    com/intellij/openapi/progress/ProgressManager.checkCanceled:()V
        //   164: invokestatic    com/intellij/openapi/application/TransactionGuard.getInstance:()Lcom/intellij/openapi/application/TransactionGuard;
        //   167: aload_0        
        //   168: invokedynamic   run:(Lcom/jetbrains/cidr/cpp/cmake/workspace/CMakeWorkspace;)Ljava/lang/Runnable;
        //   173: invokevirtual   com/intellij/openapi/application/TransactionGuard.submitTransactionAndWait:(Ljava/lang/Runnable;)V
        //   176: invokestatic    com/intellij/openapi/progress/ProgressManager.checkCanceled:()V
        //   179: aload_0        
        //   180: getfield        com/jetbrains/cidr/cpp/cmake/workspace/CMakeWorkspace.myProject:Lcom/intellij/openapi/project/Project;
        //   183: invokeinterface com/intellij/openapi/project/Project.isDisposed:()Z
        //   188: ifeq            203
        //   191: new             Lcom/intellij/openapi/progress/ProcessCanceledException;
        //   194: dup            
        //   195: invokespecial   com/intellij/openapi/progress/ProcessCanceledException.<init>:()V
        //   198: athrow         
        //   199: invokestatic    com/jetbrains/cidr/cpp/cmake/workspace/CMakeWorkspace.b:(Ljava/lang/Exception;)Ljava/lang/Exception;
        //   202: athrow         
        //   203: new             Lcom/intellij/openapi/util/Ref;
        //   206: dup            
        //   207: invokespecial   com/intellij/openapi/util/Ref.<init>:()V
        //   210: astore          5
        //   212: new             Lcom/intellij/openapi/util/Ref;
        //   215: dup            
        //   216: invokespecial   com/intellij/openapi/util/Ref.<init>:()V
        //   219: astore          6
        //   221: aload_0        
        //   222: aload_2        
        //   223: invokedynamic   run:(Lcom/jetbrains/cidr/cpp/cmake/workspace/CMakeWorkspace;Lcom/jetbrains/cidr/cpp/cmake/workspace/CMakeWorkspaceListener;)Ljava/lang/Runnable;
        //   228: astore          7
        //   230: aload_0        
        //   231: aload_2        
        //   232: invokedynamic   run:(Lcom/jetbrains/cidr/cpp/cmake/workspace/CMakeWorkspace;Lcom/jetbrains/cidr/cpp/cmake/workspace/CMakeWorkspaceListener;)Ljava/lang/Runnable;
        //   237: astore          8
        //   239: aload_0        
        //   240: aload_2        
        //   241: invokedynamic   run:(Lcom/jetbrains/cidr/cpp/cmake/workspace/CMakeWorkspace;Lcom/jetbrains/cidr/cpp/cmake/workspace/CMakeWorkspaceListener;)Ljava/lang/Runnable;
        //   246: astore          9
        //   248: aload_0        
        //   249: getfield        com/jetbrains/cidr/cpp/cmake/workspace/CMakeWorkspace.myState:Lcom/jetbrains/cidr/cpp/cmake/workspace/CMakeWorkspace$State;
        //   252: astore          10
        //   254: aload_0        
        //   255: aload_0        
        //   256: getfield        com/jetbrains/cidr/cpp/cmake/workspace/CMakeWorkspace.myProjectDir:Ljava/io/File;
        //   259: aload           10
        //   261: aload_1        
        //   262: aload           7
        //   264: aload           8
        //   266: invokespecial   com/jetbrains/cidr/cpp/cmake/workspace/CMakeWorkspace.a:(Ljava/io/File;Lcom/jetbrains/cidr/cpp/cmake/workspace/CMakeWorkspace$State;Lcom/intellij/openapi/progress/ProgressIndicator;Ljava/lang/Runnable;Ljava/lang/Runnable;)Lcom/jetbrains/cidr/cpp/cmake/workspace/CMakeWorkspace$State;
        //   269: astore          4
        //   271: aload_0        
        //   272: aload_0        
        //   273: aload           5
        //   275: aload           4
        //   277: aload           6
        //   279: invokedynamic   run:(Lcom/jetbrains/cidr/cpp/cmake/workspace/CMakeWorkspace;Lcom/intellij/openapi/util/Ref;Lcom/jetbrains/cidr/cpp/cmake/workspace/CMakeWorkspace$State;Lcom/intellij/openapi/util/Ref;)Ljava/lang/Runnable;
        //   284: invokespecial   com/jetbrains/cidr/cpp/cmake/workspace/CMakeWorkspace.c:(Ljava/lang/Runnable;)V
        //   287: goto            326
        //   290: astore          11
        //   292: aload           9
        //   294: invokeinterface java/lang/Runnable.run:()V
        //   299: goto            311
        //   302: astore          12
        //   304: aload           11
        //   306: aload           12
        //   308: invokevirtual   java/lang/Throwable.addSuppressed:(Ljava/lang/Throwable;)V
        //   311: aload           11
        //   313: invokestatic    com/intellij/util/ExceptionUtil.rethrow:(Ljava/lang/Throwable;)V
        //   316: new             Ljava/lang/RuntimeException;
        //   319: dup            
        //   320: aload           11
        //   322: invokespecial   java/lang/RuntimeException.<init>:(Ljava/lang/Throwable;)V
        //   325: athrow         
        //   326: aload           5
        //   328: invokevirtual   com/intellij/openapi/util/Ref.get:()Ljava/lang/Object;
        //   331: checkcast       Ljava/util/List;
        //   334: aload           9
        //   336: invokedynamic   run:(Ljava/lang/Runnable;)Ljava/lang/Runnable;
        //   341: invokestatic    com/jetbrains/cidr/cpp/cmake/workspace/CMakeWorkspace.a:(Ljava/util/List;Ljava/lang/Runnable;)V
        //   344: aload           4
        //   346: aload           6
        //   348: invokevirtual   com/intellij/openapi/util/Ref.get:()Ljava/lang/Object;
        //   351: checkcast       Ljava/io/File;
        //   354: invokestatic    com/jetbrains/cidr/cpp/cmake/workspace/CMakeWorkspace.d:(Lcom/jetbrains/cidr/cpp/cmake/workspace/CMakeWorkspace$State;Ljava/io/File;)V
        //   357: invokestatic    com/intellij/openapi/application/TransactionGuard.getInstance:()Lcom/intellij/openapi/application/TransactionGuard;
        //   360: aload_0        
        //   361: aload_2        
        //   362: aload           4
        //   364: invokedynamic   run:(Lcom/jetbrains/cidr/cpp/cmake/workspace/CMakeWorkspace;Lcom/jetbrains/cidr/cpp/cmake/workspace/CMakeWorkspaceListener;Lcom/jetbrains/cidr/cpp/cmake/workspace/CMakeWorkspace$State;)Ljava/lang/Runnable;
        //   369: invokevirtual   com/intellij/openapi/application/TransactionGuard.submitTransactionAndWait:(Ljava/lang/Runnable;)V
        //   372: iload_3        
        //   373: istore          4
        //   375: new             Lcom/jetbrains/cidr/cpp/cmake/workspace/CMakeWorkspace$6;
        //   378: dup            
        //   379: aload_0        
        //   380: aload_2        
        //   381: iload           4
        //   383: invokespecial   com/jetbrains/cidr/cpp/cmake/workspace/CMakeWorkspace$6.<init>:(Lcom/jetbrains/cidr/cpp/cmake/workspace/CMakeWorkspace;Lcom/jetbrains/cidr/cpp/cmake/workspace/CMakeWorkspaceListener;Z)V
        //   386: invokevirtual   com/jetbrains/cidr/cpp/cmake/workspace/CMakeWorkspace$6.execute:()Lcom/intellij/openapi/application/RunResult;
        //   389: pop            
        //   390: getstatic       com/jetbrains/cidr/cpp/cmake/workspace/CMakeWorkspace.ourPauseOnReloadInTests:Lcom/intellij/openapi/util/Pair;
        //   393: ifnull          437
        //   396: getstatic       com/jetbrains/cidr/cpp/cmake/workspace/CMakeWorkspace.ourPauseOnReloadInTests:Lcom/intellij/openapi/util/Pair;
        //   399: getfield        com/intellij/openapi/util/Pair.second:Ljava/lang/Object;
        //   402: ifnull          437
        //   405: goto            412
        //   408: invokestatic    com/jetbrains/cidr/cpp/cmake/workspace/CMakeWorkspace.b:(Ljava/lang/Exception;)Ljava/lang/Exception;
        //   411: athrow         
        //   412: getstatic       com/jetbrains/cidr/cpp/cmake/workspace/CMakeWorkspace.ourPauseOnReloadInTests:Lcom/intellij/openapi/util/Pair;
        //   415: getfield        com/intellij/openapi/util/Pair.second:Ljava/lang/Object;
        //   418: checkcast       Ljava/lang/Integer;
        //   421: invokevirtual   java/lang/Integer.intValue:()I
        //   424: i2l            
        //   425: invokestatic    java/lang/Thread.sleep:(J)V
        //   428: goto            437
        //   431: invokestatic    com/jetbrains/cidr/cpp/cmake/workspace/CMakeWorkspace.b:(Ljava/lang/Exception;)Ljava/lang/Exception;
        //   434: athrow         
        //   435: astore          5
        //   437: goto            517
        //   440: astore          4
        //   442: iconst_1       
        //   443: istore_3       
        //   444: aload           4
        //   446: athrow         
        //   447: astore          13
        //   449: iload_3        
        //   450: istore          14
        //   452: new             Lcom/jetbrains/cidr/cpp/cmake/workspace/CMakeWorkspace$6;
        //   455: dup            
        //   456: aload_0        
        //   457: aload_2        
        //   458: iload           14
        //   460: invokespecial   com/jetbrains/cidr/cpp/cmake/workspace/CMakeWorkspace$6.<init>:(Lcom/jetbrains/cidr/cpp/cmake/workspace/CMakeWorkspace;Lcom/jetbrains/cidr/cpp/cmake/workspace/CMakeWorkspaceListener;Z)V
        //   463: invokevirtual   com/jetbrains/cidr/cpp/cmake/workspace/CMakeWorkspace$6.execute:()Lcom/intellij/openapi/application/RunResult;
        //   466: pop            
        //   467: getstatic       com/jetbrains/cidr/cpp/cmake/workspace/CMakeWorkspace.ourPauseOnReloadInTests:Lcom/intellij/openapi/util/Pair;
        //   470: ifnull          514
        //   473: getstatic       com/jetbrains/cidr/cpp/cmake/workspace/CMakeWorkspace.ourPauseOnReloadInTests:Lcom/intellij/openapi/util/Pair;
        //   476: getfield        com/intellij/openapi/util/Pair.second:Ljava/lang/Object;
        //   479: ifnull          514
        //   482: goto            489
        //   485: invokestatic    com/jetbrains/cidr/cpp/cmake/workspace/CMakeWorkspace.b:(Ljava/lang/Exception;)Ljava/lang/Exception;
        //   488: athrow         
        //   489: getstatic       com/jetbrains/cidr/cpp/cmake/workspace/CMakeWorkspace.ourPauseOnReloadInTests:Lcom/intellij/openapi/util/Pair;
        //   492: getfield        com/intellij/openapi/util/Pair.second:Ljava/lang/Object;
        //   495: checkcast       Ljava/lang/Integer;
        //   498: invokevirtual   java/lang/Integer.intValue:()I
        //   501: i2l            
        //   502: invokestatic    java/lang/Thread.sleep:(J)V
        //   505: goto            514
        //   508: invokestatic    com/jetbrains/cidr/cpp/cmake/workspace/CMakeWorkspace.b:(Ljava/lang/Exception;)Ljava/lang/Exception;
        //   511: athrow         
        //   512: astore          15
        //   514: aload           13
        //   516: athrow         
        //   517: return         
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                                                    
        //  -----  -----  -----  -----  --------------------------------------------------------
        //  113    128    131    135    Ljava/lang/InterruptedException;
        //  69     85     88     92     Ljava/lang/InterruptedException;
        //  44     65     65     69     Ljava/lang/InterruptedException;
        //  0      40     40     44     Ljava/lang/InterruptedException;
        //  135    151    158    159    Ljava/lang/InterruptedException;
        //  161    199    199    203    Ljava/lang/InterruptedException;
        //  119    154    154    158    Lcom/intellij/openapi/progress/ProcessCanceledException;
        //  254    287    290    326    Ljava/lang/Throwable;
        //  292    299    302    311    Ljava/lang/Throwable;
        //  412    428    435    437    Ljava/lang/InterruptedException;
        //  161    372    440    447    Lcom/intellij/openapi/progress/ProcessCanceledException;
        //  161    372    447    517    Any
        //  396    431    431    435    Lcom/intellij/openapi/progress/ProcessCanceledException;
        //  375    405    408    412    Ljava/lang/InterruptedException;
        //  489    505    512    514    Ljava/lang/InterruptedException;
        //  440    449    447    517    Any
        //  452    482    485    489    Ljava/lang/InterruptedException;
        //  473    508    508    512    Lcom/intellij/openapi/progress/ProcessCanceledException;
        // 
        // The error that occurred was:
        // 
        // java.lang.IllegalStateException: Expression is linked from several locations: Label_0135:
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
    private State a(@Nullable final File file, @NotNull State state, @NotNull final ProgressIndicator progressIndicator, @NotNull final Runnable runnable, @NotNull final Runnable runnable2) {
        try {
            if (state == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "currentState", "com/jetbrains/cidr/cpp/cmake/workspace/CMakeWorkspace", "doGenerateProject"));
            }
        }
        catch (NullPointerException ex) {
            throw b(ex);
        }
        try {
            if (progressIndicator == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "indicator", "com/jetbrains/cidr/cpp/cmake/workspace/CMakeWorkspace", "doGenerateProject"));
            }
        }
        catch (NullPointerException ex2) {
            throw b(ex2);
        }
        try {
            if (runnable == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "beforeGeneration", "com/jetbrains/cidr/cpp/cmake/workspace/CMakeWorkspace", "doGenerateProject"));
            }
        }
        catch (NullPointerException ex3) {
            throw b(ex3);
        }
        try {
            if (runnable2 == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "afterGeneration", "com/jetbrains/cidr/cpp/cmake/workspace/CMakeWorkspace", "doGenerateProject"));
            }
        }
        catch (NullPointerException ex4) {
            throw b(ex4);
        }
        final ArrayList<CMakeMessage> list = new ArrayList<CMakeMessage>();
        File file2 = null;
        boolean exists = false;
        if (file != null) {
            file2 = new File(file, "CMakeLists.txt");
            exists = file2.exists();
        }
        Label_0324: {
            Label_0295: {
                StringBuilder sb2 = null;
                String string = null;
                Label_0272: {
                    Label_0244: {
                        try {
                            if (exists) {
                                break Label_0295;
                            }
                            final StringBuilder sb = new StringBuilder();
                            final String s = "CMakeLists.txt not found";
                            sb2 = sb.append(s);
                            final File file3 = file;
                            if (file3 != null) {
                                break Label_0244;
                            }
                            break Label_0244;
                        }
                        catch (NullPointerException ex5) {
                            throw b(ex5);
                        }
                        try {
                            final StringBuilder sb = new StringBuilder();
                            final String s = "CMakeLists.txt not found";
                            sb2 = sb.append(s);
                            final File file3 = file;
                            if (file3 != null) {
                                string = " in " + file;
                                break Label_0272;
                            }
                        }
                        catch (NullPointerException ex6) {
                            throw b(ex6);
                        }
                    }
                    string = "";
                }
                list.add(CMakeMessage.fatalError(sb2.append(string).toString(), file2));
                try {
                    if (exists) {
                        if (FileUtil.filesEqual(file, state.projectDir)) {
                            break Label_0324;
                        }
                    }
                }
                catch (NullPointerException ex7) {
                    throw b(ex7);
                }
            }
            state = this.b(file);
        }
        final List<CMakeSettings.Configuration> list2 = this.a((com.intellij.openapi.util.Computable<List<CMakeSettings.Configuration>>)(() -> this.mySettings.getConfigurations()));
        final long calcConfigurationsHash = CMakeSettingsKt.calcConfigurationsHash(list2);
        final List<CMakeConfigurationInfo> list3 = this.a((com.intellij.openapi.util.Computable<List<CMakeConfigurationInfo>>)(() -> this.a(list2, true)));
        this.myOutputConsole.updateTabs(file, list3);
        this.myOutputConsole.reportMessages(-1, list);
        this.myOutputConsole.reloadingStarted(progressIndicator);
        boolean b = false;
        for (int i = 0; i < list3.size(); ++i) {
            final EnvironmentProblems environmentProblems = list3.get(i).getEnvironmentProblems();
            if (environmentProblems.hasProblems()) {
                final Iterator<String> iterator = environmentProblems.getProblems().iterator();
                while (iterator.hasNext()) {
                    this.myOutputConsole.reportMessage(i, new CMakeMessage(CMakeMessage.MessageLevel.FATAL_ERROR, null, null, iterator.next()));
                }
                b = true;
            }
        }
        State state2 = null;
        Label_0655: {
            try {
                if (!exists || b) {
                    break Label_0655;
                }
            }
            catch (NullPointerException ex8) {
                throw b(ex8);
            }
            final Charset charset = this.a((com.intellij.openapi.util.Computable<Charset>)(() -> EncodingProjectManager.getInstance(this.myProject).getDefaultCharset()));
            final int[] array = { 0 };
            final THashMap tHashMap = new THashMap();
            final List mapNotNull = ContainerUtil.mapNotNull((Collection)list2, configuration -> {
                final int n = array[0]++;
                final String configName = configuration.getConfigName();
                final CMakeConfigurationInfo cMakeConfigurationInfo = list3.get(n);
                final File generationDir = cMakeConfigurationInfo.getGenerationDir();
                final CMakeEnvironment environment = cMakeConfigurationInfo.getEnvironment();
                Label_0062: {
                    try {
                        if (CMakeWorkspace.$assertionsDisabled) {
                            break Label_0062;
                        }
                        final CMakeEnvironment cMakeEnvironment = environment;
                        if (cMakeEnvironment == null) {
                            break Label_0062;
                        }
                        break Label_0062;
                    }
                    catch (NullPointerException ex) {
                        throw b(ex);
                    }
                    try {
                        final CMakeEnvironment cMakeEnvironment = environment;
                        if (cMakeEnvironment == null) {
                            throw new AssertionError();
                        }
                    }
                    catch (NullPointerException ex2) {
                        throw b(ex2);
                    }
                }
                ((Map<Integer, CMakeEnvironment>)tHashMap).put(n, environment);
                return new CMakeGenerator.Parameters(n, configName, generationDir, configuration.getGenerationOptionsList(), configuration.getGenerationPassSystemEnvironment(), configuration.getAdditionalGenerationEnvironment(), new CMakeListener() {
                    final /* synthetic */ int val$configIndex;
                    
                    @Override
                    public void message(@NotNull final CMakeMessage cMakeMessage) {
                        try {
                            if (cMakeMessage == null) {
                                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "message", "com/jetbrains/cidr/cpp/cmake/workspace/CMakeWorkspace$7", "message"));
                            }
                        }
                        catch (IllegalArgumentException ex) {
                            throw a(ex);
                        }
                        CMakeWorkspace.this.myOutputConsole.reportMessage(this.val$configIndex, cMakeMessage);
                    }
                    
                    @Override
                    public void attachTo(@NotNull final ProcessHandler processHandler) {
                        try {
                            if (processHandler == null) {
                                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "handler", "com/jetbrains/cidr/cpp/cmake/workspace/CMakeWorkspace$7", "attachTo"));
                            }
                        }
                        catch (IllegalArgumentException ex) {
                            throw a(ex);
                        }
                        CMakeWorkspace.this.myOutputConsole.attachConsoleToProcess(this.val$configIndex, processHandler);
                    }
                    
                    private static IllegalArgumentException a(final IllegalArgumentException ex) {
                        return ex;
                    }
                }, environment);
            });
            runnable.run();
            CMakeModel generate;
            try {
                generate = new CMakeGenerator(this.myProject, file, mapNotNull, this.a(file, charset), charset).generate(state.model, progressIndicator);
            }
            finally {
                runnable2.run();
            }
            state = this.a(calcConfigurationsHash, file, generate, (Map<Integer, CMakeEnvironment>)tHashMap, null, null, state, (n, cMakeMessage) -> this.myOutputConsole.reportMessage(n, cMakeMessage));
            try {
                state2 = state;
                if (state2 == null) {
                    throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/cpp/cmake/workspace/CMakeWorkspace", "doGenerateProject"));
                }
            }
            catch (NullPointerException ex9) {
                throw b(ex9);
            }
        }
        return state2;
    }
    
    @NotNull
    private List<File> b(@NotNull final List<CMakeSettings.Configuration> list) {
        try {
            if (list == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "configurations", "com/jetbrains/cidr/cpp/cmake/workspace/CMakeWorkspace", "collectConfigurationGenerationDirs"));
            }
        }
        catch (NullPointerException ex) {
            throw b(ex);
        }
        List<File> effectiveConfigurationGenerationDirs;
        try {
            effectiveConfigurationGenerationDirs = this.getEffectiveConfigurationGenerationDirs(ContainerUtil.map((Collection)list, configuration -> Pair.create((Object)configuration.getConfigName(), (Object)configuration.getGenerationDir())));
            if (effectiveConfigurationGenerationDirs == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/cpp/cmake/workspace/CMakeWorkspace", "collectConfigurationGenerationDirs"));
            }
        }
        catch (NullPointerException ex2) {
            throw b(ex2);
        }
        return effectiveConfigurationGenerationDirs;
    }
    
    @NotNull
    private List<CMakeConfigurationInfo> a(@NotNull final List<CMakeSettings.Configuration> list, final boolean b) {
        try {
            if (list == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "configurations", "com/jetbrains/cidr/cpp/cmake/workspace/CMakeWorkspace", "collectConfigurationInfos"));
            }
        }
        catch (NullPointerException ex) {
            throw b(ex);
        }
        final List<File> b2 = this.b(list);
        List map;
        try {
            map = ContainerUtil.map(ContainerUtil.zip((Iterable)list, (Iterable)b2), pair -> {
                ProgressManager.checkCanceled();
                final EnvironmentProblems environmentProblems = new EnvironmentProblems();
                try {
                    if (CMakeWorkspace.ourEnvironmentFactory == null) {
                        final CMakeEnvironment create = CMakeEnvironment.create(environmentProblems, b, ((CMakeSettings.Configuration)pair.first).getToolSetOptions());
                        return new CMakeConfigurationInfo(((CMakeSettings.Configuration)pair.first).getConfigName(), (File)pair.second, create, environmentProblems);
                    }
                }
                catch (NullPointerException ex) {
                    throw b(ex);
                }
                final CMakeEnvironment create = (CMakeEnvironment)CMakeWorkspace.ourEnvironmentFactory.invoke(pair.first, (Object)environmentProblems, (Object)b);
                return new CMakeConfigurationInfo(((CMakeSettings.Configuration)pair.first).getConfigName(), (File)pair.second, create, environmentProblems);
            });
            if (map == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/cpp/cmake/workspace/CMakeWorkspace", "collectConfigurationInfos"));
            }
        }
        catch (NullPointerException ex2) {
            throw b(ex2);
        }
        return (List<CMakeConfigurationInfo>)map;
    }
    
    @Nullable
    private String a(@NotNull final File file, @NotNull final Charset charset) {
        try {
            if (file == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "projectDir", "com/jetbrains/cidr/cpp/cmake/workspace/CMakeWorkspace", "determineEncoding"));
            }
        }
        catch (NullPointerException ex) {
            throw b(ex);
        }
        try {
            if (charset == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "defaultCharset", "com/jetbrains/cidr/cpp/cmake/workspace/CMakeWorkspace", "determineEncoding"));
            }
        }
        catch (NullPointerException ex2) {
            throw b(ex2);
        }
        final Charset charset2 = (Charset)UIUtil.invokeAndWaitIfNeeded(() -> {
            try {
                if (file == null) {
                    throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "projectDir", "com/jetbrains/cidr/cpp/cmake/workspace/CMakeWorkspace", "lambda$determineEncoding$26"));
                }
            }
            catch (NullPointerException ex) {
                throw b(ex);
            }
            try {
                if (this.myProject.isDisposed()) {
                    return null;
                }
            }
            catch (NullPointerException ex2) {
                throw b(ex2);
            }
            final VirtualFile fileByIoFile = VfsUtil.findFileByIoFile(new File(file, "CMakeLists.txt"), true);
            try {
                if (fileByIoFile == null) {
                    return null;
                }
            }
            catch (NullPointerException ex3) {
                throw b(ex3);
            }
            return fileByIoFile.getCharset();
        });
        Label_0121: {
            try {
                if (charset2 == null) {
                    break Label_0121;
                }
                final Charset charset3 = charset;
                final Charset charset4 = charset2;
                final boolean b = charset3.equals(charset4);
                if (b) {
                    break Label_0121;
                }
                return charset2.name();
            }
            catch (NullPointerException ex3) {
                throw b(ex3);
            }
            try {
                final Charset charset3 = charset;
                final Charset charset4 = charset2;
                final boolean b = charset3.equals(charset4);
                if (b) {
                    return null;
                }
            }
            catch (NullPointerException ex4) {
                throw b(ex4);
            }
        }
        return charset2.name();
    }
    
    @NotNull
    private State b(@Nullable final File file) {
        File file2 = null;
        Label_0022: {
            try {
                if (file == null) {
                    file2 = null;
                    break Label_0022;
                }
            }
            catch (NullPointerException ex) {
                throw b(ex);
            }
            file2 = new File(file, "CMakeLists.txt");
        }
        final Set maybeSingletonSet = ContainerUtil.createMaybeSingletonSet((Object)file2);
        Map<Object, Object> emptyMap;
        if (maybeSingletonSet.isEmpty()) {
            emptyMap = Collections.emptyMap();
        }
        else {
            emptyMap = this.a((com.intellij.openapi.util.Computable<Map<Object, Object>>)(() -> this.a(maybeSingletonSet, Collections.emptyMap(), (NullableFunction<File, VirtualFile>)(file -> VfsUtil.findFileByIoFile(file, false)))));
        }
        State state;
        try {
            state = new State(file, 0L, (Map<File, FileStamp>)emptyMap);
            if (state == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/cpp/cmake/workspace/CMakeWorkspace", "createInitialState"));
            }
        }
        catch (NullPointerException ex2) {
            throw b(ex2);
        }
        return state;
    }
    
    @NotNull
    private State a(final long p0, @NotNull final File p1, @NotNull final CMakeModel p2, @NotNull final Map<Integer, CMakeEnvironment> p3, @Nullable final Map<File, FileStamp> p4, @Nullable final IOLibraryRootsAndFiles p5, @Nullable final State p6, @Nullable final BiConsumer<Integer, CMakeMessage> p7) {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     0: aload_3        
        //     1: ifnonnull       44
        //     4: new             Ljava/lang/IllegalArgumentException;
        //     7: dup            
        //     8: ldc             "Argument for @NotNull parameter '%s' of %s.%s must not be null"
        //    10: ldc             3
        //    12: anewarray       Ljava/lang/Object;
        //    15: dup            
        //    16: ldc             0
        //    18: ldc             "projectDir"
        //    20: aastore        
        //    21: dup            
        //    22: ldc             1
        //    24: ldc             "com/jetbrains/cidr/cpp/cmake/workspace/CMakeWorkspace"
        //    26: aastore        
        //    27: dup            
        //    28: ldc             2
        //    30: ldc             "createStateWithModel"
        //    32: aastore        
        //    33: invokestatic    java/lang/String.format:(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
        //    36: invokespecial   java/lang/IllegalArgumentException.<init>:(Ljava/lang/String;)V
        //    39: athrow         
        //    40: invokestatic    com/jetbrains/cidr/cpp/cmake/workspace/CMakeWorkspace.b:(Ljava/lang/Exception;)Ljava/lang/Exception;
        //    43: athrow         
        //    44: aload           4
        //    46: ifnonnull       89
        //    49: new             Ljava/lang/IllegalArgumentException;
        //    52: dup            
        //    53: ldc             "Argument for @NotNull parameter '%s' of %s.%s must not be null"
        //    55: ldc             3
        //    57: anewarray       Ljava/lang/Object;
        //    60: dup            
        //    61: ldc             0
        //    63: ldc             "model"
        //    65: aastore        
        //    66: dup            
        //    67: ldc             1
        //    69: ldc             "com/jetbrains/cidr/cpp/cmake/workspace/CMakeWorkspace"
        //    71: aastore        
        //    72: dup            
        //    73: ldc             2
        //    75: ldc             "createStateWithModel"
        //    77: aastore        
        //    78: invokestatic    java/lang/String.format:(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
        //    81: invokespecial   java/lang/IllegalArgumentException.<init>:(Ljava/lang/String;)V
        //    84: athrow         
        //    85: invokestatic    com/jetbrains/cidr/cpp/cmake/workspace/CMakeWorkspace.b:(Ljava/lang/Exception;)Ljava/lang/Exception;
        //    88: athrow         
        //    89: aload           5
        //    91: ifnonnull       134
        //    94: new             Ljava/lang/IllegalArgumentException;
        //    97: dup            
        //    98: ldc             "Argument for @NotNull parameter '%s' of %s.%s must not be null"
        //   100: ldc             3
        //   102: anewarray       Ljava/lang/Object;
        //   105: dup            
        //   106: ldc             0
        //   108: ldc             "configEnvironments"
        //   110: aastore        
        //   111: dup            
        //   112: ldc             1
        //   114: ldc             "com/jetbrains/cidr/cpp/cmake/workspace/CMakeWorkspace"
        //   116: aastore        
        //   117: dup            
        //   118: ldc             2
        //   120: ldc             "createStateWithModel"
        //   122: aastore        
        //   123: invokestatic    java/lang/String.format:(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
        //   126: invokespecial   java/lang/IllegalArgumentException.<init>:(Ljava/lang/String;)V
        //   129: athrow         
        //   130: invokestatic    com/jetbrains/cidr/cpp/cmake/workspace/CMakeWorkspace.b:(Ljava/lang/Exception;)Ljava/lang/Exception;
        //   133: athrow         
        //   134: invokestatic    com/intellij/openapi/vfs/LocalFileSystem.getInstance:()Lcom/intellij/openapi/vfs/LocalFileSystem;
        //   137: astore          10
        //   139: new             Lcom/jetbrains/cidr/cpp/cmake/workspace/CMakeWorkspace$8;
        //   142: dup            
        //   143: aload_0        
        //   144: aload           10
        //   146: invokespecial   com/jetbrains/cidr/cpp/cmake/workspace/CMakeWorkspace$8.<init>:(Lcom/jetbrains/cidr/cpp/cmake/workspace/CMakeWorkspace;Lcom/intellij/openapi/vfs/LocalFileSystem;)V
        //   149: astore          11
        //   151: new             Lcom/jetbrains/cidr/toolchains/CompilerInfoCache;
        //   154: dup            
        //   155: invokespecial   com/jetbrains/cidr/toolchains/CompilerInfoCache.<init>:()V
        //   158: astore          12
        //   160: new             Lgnu/trove/THashSet;
        //   163: dup            
        //   164: getstatic       com/intellij/openapi/util/io/FileUtil.FILE_HASHING_STRATEGY:Lgnu/trove/TObjectHashingStrategy;
        //   167: invokespecial   gnu/trove/THashSet.<init>:(Lgnu/trove/TObjectHashingStrategy;)V
        //   170: astore          13
        //   172: new             Lgnu/trove/THashMap;
        //   175: dup            
        //   176: invokespecial   gnu/trove/THashMap.<init>:()V
        //   179: astore          14
        //   181: new             Ljava/util/ArrayList;
        //   184: dup            
        //   185: invokespecial   java/util/ArrayList.<init>:()V
        //   188: astore          15
        //   190: aload           4
        //   192: invokevirtual   com/jetbrains/cidr/cpp/cmake/model/CMakeModel.getTargets:()Ljava/util/List;
        //   195: invokeinterface java/util/List.iterator:()Ljava/util/Iterator;
        //   200: astore          16
        //   202: aload           16
        //   204: invokeinterface java/util/Iterator.hasNext:()Z
        //   209: ifeq            417
        //   212: aload           16
        //   214: invokeinterface java/util/Iterator.next:()Ljava/lang/Object;
        //   219: checkcast       Lcom/jetbrains/cidr/cpp/cmake/model/CMakeTarget;
        //   222: astore          17
        //   224: aload           17
        //   226: invokevirtual   com/jetbrains/cidr/cpp/cmake/model/CMakeTarget.getBuildConfigurations:()Ljava/util/List;
        //   229: invokeinterface java/util/List.iterator:()Ljava/util/Iterator;
        //   234: astore          18
        //   236: aload           18
        //   238: invokeinterface java/util/Iterator.hasNext:()Z
        //   243: ifeq            414
        //   246: aload           18
        //   248: invokeinterface java/util/Iterator.next:()Ljava/lang/Object;
        //   253: checkcast       Lcom/jetbrains/cidr/cpp/cmake/model/CMakeConfiguration;
        //   256: astore          19
        //   258: aload           5
        //   260: aload           19
        //   262: invokevirtual   com/jetbrains/cidr/cpp/cmake/model/CMakeConfiguration.getId:()I
        //   265: invokestatic    java/lang/Integer.valueOf:(I)Ljava/lang/Integer;
        //   268: invokeinterface java/util/Map.get:(Ljava/lang/Object;)Ljava/lang/Object;
        //   273: checkcast       Lcom/jetbrains/cidr/cpp/cmake/CMakeEnvironment;
        //   276: astore          20
        //   278: aload           19
        //   280: invokevirtual   com/jetbrains/cidr/cpp/cmake/model/CMakeConfiguration.getSources:()Ljava/util/Collection;
        //   283: aload           11
        //   285: invokestatic    com/intellij/util/containers/ContainerUtil.mapNotNull:(Ljava/util/Collection;Lcom/intellij/util/Function;)Ljava/util/List;
        //   288: astore          21
        //   290: new             Lcom/jetbrains/cidr/cpp/cmake/workspace/CMakeResolveConfiguration;
        //   293: dup            
        //   294: aload_0        
        //   295: getfield        com/jetbrains/cidr/cpp/cmake/workspace/CMakeWorkspace.myProject:Lcom/intellij/openapi/project/Project;
        //   298: aload           17
        //   300: aload           19
        //   302: aload           20
        //   304: aload           12
        //   306: aload           21
        //   308: invokespecial   com/jetbrains/cidr/cpp/cmake/workspace/CMakeResolveConfiguration.<init>:(Lcom/intellij/openapi/project/Project;Lcom/jetbrains/cidr/cpp/cmake/model/CMakeTarget;Lcom/jetbrains/cidr/cpp/cmake/model/CMakeConfiguration;Lcom/jetbrains/cidr/lang/toolchains/CidrToolEnvironment;Lcom/jetbrains/cidr/toolchains/CompilerInfoCache;Ljava/util/Collection;)V
        //   311: astore          22
        //   313: aload           13
        //   315: aload           19
        //   317: invokevirtual   com/jetbrains/cidr/cpp/cmake/model/CMakeConfiguration.getSources:()Ljava/util/Collection;
        //   320: invokevirtual   gnu/trove/THashSet.addAll:(Ljava/util/Collection;)Z
        //   323: pop            
        //   324: aload           15
        //   326: aload           22
        //   328: invokevirtual   java/util/ArrayList.add:(Ljava/lang/Object;)Z
        //   331: pop            
        //   332: aload           21
        //   334: invokeinterface java/util/List.iterator:()Ljava/util/Iterator;
        //   339: astore          23
        //   341: aload           23
        //   343: invokeinterface java/util/Iterator.hasNext:()Z
        //   348: ifeq            411
        //   351: aload           23
        //   353: invokeinterface java/util/Iterator.next:()Ljava/lang/Object;
        //   358: checkcast       Lcom/intellij/openapi/vfs/VirtualFile;
        //   361: astore          24
        //   363: aload           14
        //   365: aload           24
        //   367: invokevirtual   gnu/trove/THashMap.get:(Ljava/lang/Object;)Ljava/lang/Object;
        //   370: checkcast       Ljava/util/List;
        //   373: astore          25
        //   375: aload           25
        //   377: ifnonnull       398
        //   380: aload           14
        //   382: aload           24
        //   384: new             Ljava/util/ArrayList;
        //   387: dup            
        //   388: invokespecial   java/util/ArrayList.<init>:()V
        //   391: dup            
        //   392: astore          25
        //   394: invokevirtual   gnu/trove/THashMap.put:(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
        //   397: pop            
        //   398: aload           25
        //   400: aload           22
        //   402: invokeinterface java/util/List.add:(Ljava/lang/Object;)Z
        //   407: pop            
        //   408: goto            341
        //   411: goto            236
        //   414: goto            202
        //   417: aload           15
        //   419: invokevirtual   java/util/ArrayList.trimToSize:()V
        //   422: aload           14
        //   424: invokevirtual   gnu/trove/THashMap.compact:()V
        //   427: aload           14
        //   429: invokevirtual   gnu/trove/THashMap.entrySet:()Ljava/util/Set;
        //   432: invokeinterface java/util/Set.iterator:()Ljava/util/Iterator;
        //   437: astore          16
        //   439: aload           16
        //   441: invokeinterface java/util/Iterator.hasNext:()Z
        //   446: ifeq            497
        //   449: aload           16
        //   451: invokeinterface java/util/Iterator.next:()Ljava/lang/Object;
        //   456: checkcast       Ljava/util/Map$Entry;
        //   459: astore          17
        //   461: aload           17
        //   463: invokeinterface java/util/Map$Entry.getValue:()Ljava/lang/Object;
        //   468: checkcast       Ljava/util/List;
        //   471: astore          18
        //   473: aload           18
        //   475: checkcast       Ljava/util/ArrayList;
        //   478: invokevirtual   java/util/ArrayList.trimToSize:()V
        //   481: aload           17
        //   483: aload           18
        //   485: invokestatic    java/util/Collections.unmodifiableList:(Ljava/util/List;)Ljava/util/List;
        //   488: invokeinterface java/util/Map$Entry.setValue:(Ljava/lang/Object;)Ljava/lang/Object;
        //   493: pop            
        //   494: goto            439
        //   497: new             Ljava/util/ArrayList;
        //   500: dup            
        //   501: invokespecial   java/util/ArrayList.<init>:()V
        //   504: astore          16
        //   506: aload           16
        //   508: aload           4
        //   510: invokevirtual   com/jetbrains/cidr/cpp/cmake/model/CMakeModel.getCMakeFiles:()Ljava/util/Set;
        //   513: invokeinterface java/util/List.addAll:(Ljava/util/Collection;)Z
        //   518: pop            
        //   519: aload           16
        //   521: aload           4
        //   523: invokevirtual   com/jetbrains/cidr/cpp/cmake/model/CMakeModel.getHeaderAndResourceFiles:()Ljava/util/Set;
        //   526: invokeinterface java/util/List.addAll:(Ljava/util/Collection;)Z
        //   531: pop            
        //   532: aload           7
        //   534: ifnonnull       637
        //   537: getstatic       com/jetbrains/cidr/cpp/cmake/workspace/CMakeWorkspace.$assertionsDisabled:Z
        //   540: ifne            574
        //   543: goto            550
        //   546: invokestatic    com/jetbrains/cidr/cpp/cmake/workspace/CMakeWorkspace.b:(Ljava/lang/Exception;)Ljava/lang/Exception;
        //   549: athrow         
        //   550: aload           9
        //   552: ifnonnull       574
        //   555: goto            562
        //   558: invokestatic    com/jetbrains/cidr/cpp/cmake/workspace/CMakeWorkspace.b:(Ljava/lang/Exception;)Ljava/lang/Exception;
        //   561: athrow         
        //   562: new             Ljava/lang/AssertionError;
        //   565: dup            
        //   566: invokespecial   java/lang/AssertionError.<init>:()V
        //   569: athrow         
        //   570: invokestatic    com/jetbrains/cidr/cpp/cmake/workspace/CMakeWorkspace.b:(Ljava/lang/Exception;)Ljava/lang/Exception;
        //   573: athrow         
        //   574: getstatic       com/jetbrains/cidr/cpp/CPPLog.LOG:Lcom/intellij/openapi/diagnostic/Logger;
        //   577: invokestatic    com/intellij/openapi/application/ApplicationManager.getApplication:()Lcom/intellij/openapi/application/Application;
        //   580: invokeinterface com/intellij/openapi/application/Application.isUnitTestMode:()Z
        //   585: ifne            606
        //   588: invokestatic    com/intellij/openapi/application/ApplicationManager.getApplication:()Lcom/intellij/openapi/application/Application;
        //   591: invokeinterface com/intellij/openapi/application/Application.isDispatchThread:()Z
        //   596: ifne            614
        //   599: goto            606
        //   602: invokestatic    com/jetbrains/cidr/cpp/cmake/workspace/CMakeWorkspace.b:(Ljava/lang/Exception;)Ljava/lang/Exception;
        //   605: athrow         
        //   606: iconst_1       
        //   607: goto            615
        //   610: invokestatic    com/jetbrains/cidr/cpp/cmake/workspace/CMakeWorkspace.b:(Ljava/lang/Exception;)Ljava/lang/Exception;
        //   613: athrow         
        //   614: iconst_0       
        //   615: ldc             "This operation takes significant time and must not be called from UI-thread"
        //   617: invokevirtual   com/intellij/openapi/diagnostic/Logger.assertTrue:(ZLjava/lang/Object;)Z
        //   620: pop            
        //   621: aload           15
        //   623: aload           11
        //   625: aload           12
        //   627: aload           9
        //   629: invokestatic    com/jetbrains/cidr/cpp/cmake/workspace/CMakeWorkspace.a:(Ljava/util/List;Lcom/intellij/util/NullableFunction;Lcom/jetbrains/cidr/toolchains/CompilerInfoCache;Ljava/util/function/BiConsumer;)Lcom/jetbrains/cidr/cpp/cmake/workspace/CMakeWorkspace$LibraryRootsAndFiles;
        //   632: astore          17
        //   634: goto            701
        //   637: new             Lcom/jetbrains/cidr/cpp/cmake/workspace/CMakeWorkspace$LibraryRootsAndFiles;
        //   640: dup            
        //   641: aload           7
        //   643: getfield        com/jetbrains/cidr/cpp/cmake/workspace/CMakeWorkspace$IOLibraryRootsAndFiles.systemHeaderRoots:Ljava/util/List;
        //   646: aload           7
        //   648: getfield        com/jetbrains/cidr/cpp/cmake/workspace/CMakeWorkspace$IOLibraryRootsAndFiles.userHeaderRoots:Ljava/util/List;
        //   651: aload           7
        //   653: getfield        com/jetbrains/cidr/cpp/cmake/workspace/CMakeWorkspace$IOLibraryRootsAndFiles.headerExcludeRoots:Ljava/util/List;
        //   656: aload           7
        //   658: getfield        com/jetbrains/cidr/cpp/cmake/workspace/CMakeWorkspace$IOLibraryRootsAndFiles.filesToIndex:Ljava/util/List;
        //   661: aload           11
        //   663: invokestatic    com/intellij/util/containers/ContainerUtil.mapNotNull:(Ljava/util/Collection;Lcom/intellij/util/Function;)Ljava/util/List;
        //   666: aload           7
        //   668: getfield        com/jetbrains/cidr/cpp/cmake/workspace/CMakeWorkspace$IOLibraryRootsAndFiles.config2pchFiles:Ljava/util/Map;
        //   671: aload           11
        //   673: invokestatic    com/jetbrains/cidr/cpp/cmake/workspace/CMakeWorkspace.b:(Ljava/util/Map;Lcom/intellij/util/NullableFunction;)Ljava/util/Map;
        //   676: aload           7
        //   678: getfield        com/jetbrains/cidr/cpp/cmake/workspace/CMakeWorkspace$IOLibraryRootsAndFiles.fileAndLang2pchFiles:Ljava/util/Map;
        //   681: aload           11
        //   683: invokestatic    com/jetbrains/cidr/cpp/cmake/workspace/CMakeWorkspace.a:(Ljava/util/Map;Lcom/intellij/util/NullableFunction;)Ljava/util/Map;
        //   686: aload           7
        //   688: getfield        com/jetbrains/cidr/cpp/cmake/workspace/CMakeWorkspace$IOLibraryRootsAndFiles.config2includeMap:Ljava/util/Map;
        //   691: aload           11
        //   693: invokestatic    com/jetbrains/cidr/cpp/cmake/workspace/PrecompiledIncludeMap.convert:(Ljava/util/Map;Lcom/intellij/util/NullableFunction;)Ljava/util/Map;
        //   696: invokespecial   com/jetbrains/cidr/cpp/cmake/workspace/CMakeWorkspace$LibraryRootsAndFiles.<init>:(Ljava/util/List;Ljava/util/List;Ljava/util/List;Ljava/util/List;Ljava/util/Map;Ljava/util/Map;Ljava/util/Map;)V
        //   699: astore          17
        //   701: aload           6
        //   703: astore          18
        //   705: aload           18
        //   707: ifnonnull       803
        //   710: getstatic       com/jetbrains/cidr/cpp/cmake/workspace/CMakeWorkspace.$assertionsDisabled:Z
        //   713: ifne            747
        //   716: goto            723
        //   719: invokestatic    com/jetbrains/cidr/cpp/cmake/workspace/CMakeWorkspace.b:(Ljava/lang/Exception;)Ljava/lang/Exception;
        //   722: athrow         
        //   723: aload           8
        //   725: ifnonnull       747
        //   728: goto            735
        //   731: invokestatic    com/jetbrains/cidr/cpp/cmake/workspace/CMakeWorkspace.b:(Ljava/lang/Exception;)Ljava/lang/Exception;
        //   734: athrow         
        //   735: new             Ljava/lang/AssertionError;
        //   738: dup            
        //   739: invokespecial   java/lang/AssertionError.<init>:()V
        //   742: athrow         
        //   743: invokestatic    com/jetbrains/cidr/cpp/cmake/workspace/CMakeWorkspace.b:(Ljava/lang/Exception;)Ljava/lang/Exception;
        //   746: athrow         
        //   747: new             Lgnu/trove/THashSet;
        //   750: dup            
        //   751: aload           4
        //   753: invokevirtual   com/jetbrains/cidr/cpp/cmake/model/CMakeModel.getCMakeFiles:()Ljava/util/Set;
        //   756: getstatic       com/intellij/openapi/util/io/FileUtil.FILE_HASHING_STRATEGY:Lgnu/trove/TObjectHashingStrategy;
        //   759: invokespecial   gnu/trove/THashSet.<init>:(Ljava/util/Collection;Lgnu/trove/TObjectHashingStrategy;)V
        //   762: astore          19
        //   764: aload           19
        //   766: new             Ljava/io/File;
        //   769: dup            
        //   770: aload_3        
        //   771: ldc             "CMakeLists.txt"
        //   773: invokespecial   java/io/File.<init>:(Ljava/io/File;Ljava/lang/String;)V
        //   776: invokeinterface java/util/Set.add:(Ljava/lang/Object;)Z
        //   781: pop            
        //   782: aload_0        
        //   783: aload_0        
        //   784: aload           19
        //   786: aload           8
        //   788: aload           11
        //   790: invokedynamic   compute:(Lcom/jetbrains/cidr/cpp/cmake/workspace/CMakeWorkspace;Ljava/util/Set;Lcom/jetbrains/cidr/cpp/cmake/workspace/CMakeWorkspace$State;Lcom/intellij/util/NullableFunction;)Lcom/intellij/openapi/util/Computable;
        //   795: invokespecial   com/jetbrains/cidr/cpp/cmake/workspace/CMakeWorkspace.a:(Lcom/intellij/openapi/util/Computable;)Ljava/lang/Object;
        //   798: checkcast       Ljava/util/Map;
        //   801: astore          18
        //   803: new             Lcom/jetbrains/cidr/cpp/cmake/workspace/CMakeWorkspace$State;
        //   806: dup            
        //   807: aload_3        
        //   808: lload_1        
        //   809: aload           4
        //   811: aload           5
        //   813: aload           18
        //   815: new             Ljava/util/ArrayList;
        //   818: dup            
        //   819: aload           13
        //   821: invokespecial   java/util/ArrayList.<init>:(Ljava/util/Collection;)V
        //   824: aload           16
        //   826: aload           17
        //   828: aload           14
        //   830: aload           15
        //   832: aload           12
        //   834: aconst_null    
        //   835: invokespecial   com/jetbrains/cidr/cpp/cmake/workspace/CMakeWorkspace$State.<init>:(Ljava/io/File;JLcom/jetbrains/cidr/cpp/cmake/model/CMakeModel;Ljava/util/Map;Ljava/util/Map;Ljava/util/List;Ljava/util/List;Lcom/jetbrains/cidr/cpp/cmake/workspace/CMakeWorkspace$LibraryRootsAndFiles;Ljava/util/Map;Ljava/util/List;Lcom/jetbrains/cidr/toolchains/CompilerInfoCache;Lcom/jetbrains/cidr/cpp/cmake/workspace/CMakeWorkspace$1;)V
        //   838: dup            
        //   839: ifnonnull       876
        //   842: new             Ljava/lang/IllegalStateException;
        //   845: dup            
        //   846: ldc             "@NotNull method %s.%s must not return null"
        //   848: ldc             2
        //   850: anewarray       Ljava/lang/Object;
        //   853: dup            
        //   854: ldc             0
        //   856: ldc             "com/jetbrains/cidr/cpp/cmake/workspace/CMakeWorkspace"
        //   858: aastore        
        //   859: dup            
        //   860: ldc             1
        //   862: ldc             "createStateWithModel"
        //   864: aastore        
        //   865: invokestatic    java/lang/String.format:(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
        //   868: invokespecial   java/lang/IllegalStateException.<init>:(Ljava/lang/String;)V
        //   871: athrow         
        //   872: invokestatic    com/jetbrains/cidr/cpp/cmake/workspace/CMakeWorkspace.b:(Ljava/lang/Exception;)Ljava/lang/Exception;
        //   875: athrow         
        //   876: areturn        
        //    Signature:
        //  (JLjava/io/File;Lcom/jetbrains/cidr/cpp/cmake/model/CMakeModel;Ljava/util/Map<Ljava/lang/Integer;Lcom/jetbrains/cidr/cpp/cmake/CMakeEnvironment;>;Ljava/util/Map<Ljava/io/File;Lcom/jetbrains/cidr/cpp/cmake/workspace/FileStamp;>;Lcom/jetbrains/cidr/cpp/cmake/workspace/CMakeWorkspace$IOLibraryRootsAndFiles;Lcom/jetbrains/cidr/cpp/cmake/workspace/CMakeWorkspace$State;Ljava/util/function/BiConsumer<Ljava/lang/Integer;Lcom/jetbrains/cidr/cpp/cmake/model/CMakeMessage;>;)Lcom/jetbrains/cidr/cpp/cmake/workspace/CMakeWorkspace$State;
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                            
        //  -----  -----  -----  -----  --------------------------------
        //  0      40     40     44     Ljava/lang/NullPointerException;
        //  44     85     85     89     Ljava/lang/NullPointerException;
        //  89     130    130    134    Ljava/lang/NullPointerException;
        //  506    543    546    550    Ljava/lang/NullPointerException;
        //  537    555    558    562    Ljava/lang/NullPointerException;
        //  550    570    570    574    Ljava/lang/NullPointerException;
        //  574    599    602    606    Ljava/lang/NullPointerException;
        //  588    610    610    614    Ljava/lang/NullPointerException;
        //  705    716    719    723    Ljava/lang/NullPointerException;
        //  710    728    731    735    Ljava/lang/NullPointerException;
        //  723    743    743    747    Ljava/lang/NullPointerException;
        //  803    872    872    876    Ljava/lang/NullPointerException;
        // 
        // The error that occurred was:
        // 
        // java.lang.IllegalStateException: Expression is linked from several locations: Label_0550:
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
    
    private static Map<String, Set<VirtualFile>> b(@NotNull final Map<String, List<File>> map, @NotNull final NullableFunction<File, VirtualFile> nullableFunction) {
        try {
            if (map == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "files", "com/jetbrains/cidr/cpp/cmake/workspace/CMakeWorkspace", "convertToMapWithSets"));
            }
        }
        catch (NullPointerException ex) {
            throw b(ex);
        }
        try {
            if (nullableFunction == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "unsafeCachedFileMapper", "com/jetbrains/cidr/cpp/cmake/workspace/CMakeWorkspace", "convertToMapWithSets"));
            }
        }
        catch (NullPointerException ex2) {
            throw b(ex2);
        }
        final THashMap tHashMap = new THashMap(map.size());
        for (final Map.Entry<String, List<File>> entry : map.entrySet()) {
            ((Map<String, Set<Object>>)tHashMap).put(entry.getKey(), Collections.unmodifiableSet((Set<?>)ContainerUtil.map2SetNotNull((Collection)entry.getValue(), (Function)nullableFunction)));
        }
        return (Map<String, Set<VirtualFile>>)tHashMap;
    }
    
    private static Map<String, List<VirtualFile>> a(@NotNull final Map<String, List<File>> map, @NotNull final NullableFunction<File, VirtualFile> nullableFunction) {
        try {
            if (map == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "files", "com/jetbrains/cidr/cpp/cmake/workspace/CMakeWorkspace", "convertToMapWithLists"));
            }
        }
        catch (NullPointerException ex) {
            throw b(ex);
        }
        try {
            if (nullableFunction == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "unsafeCachedFileMapper", "com/jetbrains/cidr/cpp/cmake/workspace/CMakeWorkspace", "convertToMapWithLists"));
            }
        }
        catch (NullPointerException ex2) {
            throw b(ex2);
        }
        final THashMap tHashMap = new THashMap(map.size());
        for (final Map.Entry<String, List<File>> entry : map.entrySet()) {
            ((Map<String, List<Object>>)tHashMap).put(entry.getKey(), Collections.unmodifiableList((List<?>)ContainerUtil.mapNotNull((Collection)entry.getValue(), (Function)nullableFunction)));
        }
        return (Map<String, List<VirtualFile>>)tHashMap;
    }
    
    @NotNull
    private THashMap<File, FileStamp> a(@NotNull final Set<File> set, @NotNull final Map<File, FileStamp> map, @NotNull final NullableFunction<File, VirtualFile> nullableFunction) {
        try {
            if (set == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "cmakeFiles", "com/jetbrains/cidr/cpp/cmake/workspace/CMakeWorkspace", "calculateCMakeFileStamps"));
            }
        }
        catch (NullPointerException ex) {
            throw b(ex);
        }
        try {
            if (map == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "alreadyCalculatedStamps", "com/jetbrains/cidr/cpp/cmake/workspace/CMakeWorkspace", "calculateCMakeFileStamps"));
            }
        }
        catch (NullPointerException ex2) {
            throw b(ex2);
        }
        try {
            if (nullableFunction == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "getVirtualFile", "com/jetbrains/cidr/cpp/cmake/workspace/CMakeWorkspace", "calculateCMakeFileStamps"));
            }
        }
        catch (NullPointerException ex3) {
            throw b(ex3);
        }
        final THashMap tHashMap = new THashMap(set.size(), FileUtil.FILE_HASHING_STRATEGY);
        for (final File file : set) {
            tHashMap.put((Object)file, (Object)FileStamp.calcFileStamp(this.myProject, file, new FileStamp.FileStampFunction() {
                @Override
                public FileStamp getAlreadyCalculatedStamp() {
                    return map.get(file);
                }
                
                @Nullable
                @Override
                public CMakeFile getCMakeFile(@NotNull final Project project) {
                    try {
                        if (project == null) {
                            throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "project", "com/jetbrains/cidr/cpp/cmake/workspace/CMakeWorkspace$9", "getCMakeFile"));
                        }
                    }
                    catch (IllegalArgumentException ex) {
                        throw a(ex);
                    }
                    final VirtualFile virtualFile = (VirtualFile)nullableFunction.fun((Object)file);
                    try {
                        if (virtualFile == null) {
                            return null;
                        }
                    }
                    catch (IllegalArgumentException ex2) {
                        throw a(ex2);
                    }
                    return FileStamp.createOrGetCMakeFile(project, virtualFile);
                }
                
                private static IllegalArgumentException a(final IllegalArgumentException ex) {
                    return ex;
                }
            }));
        }
        THashMap tHashMap2;
        try {
            tHashMap2 = tHashMap;
            if (tHashMap2 == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/cpp/cmake/workspace/CMakeWorkspace", "calculateCMakeFileStamps"));
            }
        }
        catch (NullPointerException ex4) {
            throw b(ex4);
        }
        return (THashMap<File, FileStamp>)tHashMap2;
    }
    
    private static LibraryRootsAndFiles a(@NotNull final List<CMakeResolveConfiguration> list, @NotNull final NullableFunction<File, VirtualFile> nullableFunction, @NotNull final CompilerInfoCache compilerInfoCache, @NotNull final BiConsumer<Integer, CMakeMessage> biConsumer) {
        try {
            if (list == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "configurations", "com/jetbrains/cidr/cpp/cmake/workspace/CMakeWorkspace", "collectRootsAndFiles"));
            }
        }
        catch (NullPointerException ex) {
            throw b(ex);
        }
        try {
            if (nullableFunction == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "cachedFileMapper", "com/jetbrains/cidr/cpp/cmake/workspace/CMakeWorkspace", "collectRootsAndFiles"));
            }
        }
        catch (NullPointerException ex2) {
            throw b(ex2);
        }
        try {
            if (compilerInfoCache == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "compilerInfoCache", "com/jetbrains/cidr/cpp/cmake/workspace/CMakeWorkspace", "collectRootsAndFiles"));
            }
        }
        catch (NullPointerException ex3) {
            throw b(ex3);
        }
        try {
            if (biConsumer == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "messagesCollector", "com/jetbrains/cidr/cpp/cmake/workspace/CMakeWorkspace", "collectRootsAndFiles"));
            }
        }
        catch (NullPointerException ex4) {
            throw b(ex4);
        }
        final THashSet set = new THashSet(FileUtil.FILE_HASHING_STRATEGY);
        final THashSet set2 = new THashSet(FileUtil.FILE_HASHING_STRATEGY);
        final THashSet set3 = new THashSet(FileUtil.FILE_HASHING_STRATEGY);
        final THashSet set4 = new THashSet();
        final THashMap tHashMap = new THashMap();
        final THashMap tHashMap2 = new THashMap();
        final THashMap tHashMap3 = new THashMap();
        final MultiValuesMap multiValuesMap = new MultiValuesMap(true);
        final MultiValuesMap multiValuesMap2 = new MultiValuesMap(true);
        for (final CMakeResolveConfiguration cMakeResolveConfiguration : list) {
            final PrecompiledIncludeMapper precompiledIncludeMapper = new PrecompiledIncludeMapper(cMakeResolveConfiguration, compilerInfoCache);
            for (final CLanguageKind cLanguageKind : CLanguageKind.values()) {
                ProgressManager.checkCanceled();
                a(cMakeResolveConfiguration, cLanguageKind, cMakeResolveConfiguration.getLibraryHeadersRootsResult(new OCResolveRootAndConfiguration(cMakeResolveConfiguration, cLanguageKind)), (MultiValuesMap<Integer, String>)multiValuesMap, (THashSet<File>)set, (THashSet<File>)set2, (THashSet<File>)set3, (THashSet<VirtualFile>)set4);
                a((Map<String, Set<VirtualFile>>)tHashMap, (Map<String, List<VirtualFile>>)tHashMap2, cMakeResolveConfiguration, cLanguageKind, null);
                a((MultiValuesMap<Integer, String>)multiValuesMap2, cMakeResolveConfiguration, compilerInfoCache, cLanguageKind, null);
                precompiledIncludeMapper.collect(cLanguageKind, null);
            }
            for (final Map.Entry<File, CMakeFileSettings> entry : cMakeResolveConfiguration.getFilesWithCustomSettings().entrySet()) {
                ProgressManager.checkCanceled();
                final VirtualFile virtualFile = (VirtualFile)nullableFunction.fun((Object)entry.getKey());
                try {
                    if (virtualFile == null) {
                        continue;
                    }
                }
                catch (NullPointerException ex5) {
                    throw b(ex5);
                }
                final OCLanguageKind languageKind = entry.getValue().getLanguageKind();
                a(cMakeResolveConfiguration, languageKind, cMakeResolveConfiguration.getLibraryHeadersRootsResult(new OCResolveRootAndConfiguration(cMakeResolveConfiguration, virtualFile)), (MultiValuesMap<Integer, String>)multiValuesMap, (THashSet<File>)set, (THashSet<File>)set2, (THashSet<File>)set3, (THashSet<VirtualFile>)set4);
                a((Map<String, Set<VirtualFile>>)tHashMap, (Map<String, List<VirtualFile>>)tHashMap2, cMakeResolveConfiguration, languageKind, virtualFile);
                a((MultiValuesMap<Integer, String>)multiValuesMap2, cMakeResolveConfiguration, compilerInfoCache, languageKind, virtualFile);
                precompiledIncludeMapper.collect(languageKind, virtualFile);
            }
            final PrecompiledIncludeMap includeMap = precompiledIncludeMapper.getIncludeMap();
            tHashMap3.put((Object)cMakeResolveConfiguration.getUniqueName(), (Object)includeMap);
            includeMap.fillPCHCollections((Map<String, Set<VirtualFile>>)tHashMap, (Map<String, List<VirtualFile>>)tHashMap2, cMakeResolveConfiguration);
        }
        set.compact();
        set2.compact();
        set4.compact();
        for (final Map.Entry<K, Set> entry2 : tHashMap.entrySet()) {
            final Set<? extends T> set5 = entry2.getValue();
            ((THashSet)set5).compact();
            entry2.setValue(Collections.unmodifiableSet((Set<?>)set5));
        }
        tHashMap.compact();
        tHashMap2.compact();
        tHashMap3.compact();
        a("Cannot get compiler information", CMakeMessage.MessageLevel.ERROR, (MultiValuesMap<Integer, String>)multiValuesMap, biConsumer);
        a("Problems were encountered while collecting compiler information", CMakeMessage.MessageLevel.WARNING, (MultiValuesMap<Integer, String>)multiValuesMap2, biConsumer);
        return new LibraryRootsAndFiles(new ArrayList<File>((Collection<? extends File>)set), new ArrayList<File>((Collection<? extends File>)set2), new ArrayList<File>((Collection<? extends File>)set3), new ArrayList<VirtualFile>((Collection<? extends VirtualFile>)set4), (Map<String, Set<VirtualFile>>)tHashMap, (Map<String, List<VirtualFile>>)tHashMap2, (Map<String, PrecompiledIncludeMap>)tHashMap3);
    }
    
    private static void a(@NotNull final String s, @NotNull final CMakeMessage.MessageLevel messageLevel, @NotNull final MultiValuesMap<Integer, String> multiValuesMap, @NotNull final BiConsumer<Integer, CMakeMessage> biConsumer) {
        try {
            if (s == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "prefix", "com/jetbrains/cidr/cpp/cmake/workspace/CMakeWorkspace", "flushConsoleMessages"));
            }
        }
        catch (NullPointerException ex) {
            throw b(ex);
        }
        try {
            if (messageLevel == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "level", "com/jetbrains/cidr/cpp/cmake/workspace/CMakeWorkspace", "flushConsoleMessages"));
            }
        }
        catch (NullPointerException ex2) {
            throw b(ex2);
        }
        try {
            if (multiValuesMap == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "consoleMessages", "com/jetbrains/cidr/cpp/cmake/workspace/CMakeWorkspace", "flushConsoleMessages"));
            }
        }
        catch (NullPointerException ex3) {
            throw b(ex3);
        }
        try {
            if (biConsumer == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "messagesCollector", "com/jetbrains/cidr/cpp/cmake/workspace/CMakeWorkspace", "flushConsoleMessages"));
            }
        }
        catch (NullPointerException ex4) {
            throw b(ex4);
        }
        for (final Map.Entry<K, Iterable> entry : ContainerUtil.sorted((Collection)multiValuesMap.entrySet(), (Comparator)Comparator.comparingInt(Map.Entry::getKey))) {
            biConsumer.accept((Integer)entry.getKey(), new CMakeMessage(messageLevel, null, null, "\n" + s + ":\n\t" + StringUtil.replace(String.join("\n", entry.getValue()), "\n", "\n\t")));
        }
    }
    
    private static void a(@NotNull final MultiValuesMap<Integer, String> multiValuesMap, @NotNull final CMakeResolveConfiguration cMakeResolveConfiguration, @NotNull final CompilerInfoCache compilerInfoCache, @NotNull final OCLanguageKind ocLanguageKind, @Nullable final VirtualFile virtualFile) {
        try {
            if (multiValuesMap == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "warnings", "com/jetbrains/cidr/cpp/cmake/workspace/CMakeWorkspace", "collectCompilerWarnings"));
            }
        }
        catch (NullPointerException ex) {
            throw b(ex);
        }
        try {
            if (cMakeResolveConfiguration == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "config", "com/jetbrains/cidr/cpp/cmake/workspace/CMakeWorkspace", "collectCompilerWarnings"));
            }
        }
        catch (NullPointerException ex2) {
            throw b(ex2);
        }
        try {
            if (compilerInfoCache == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "compilerInfoCache", "com/jetbrains/cidr/cpp/cmake/workspace/CMakeWorkspace", "collectCompilerWarnings"));
            }
        }
        catch (NullPointerException ex3) {
            throw b(ex3);
        }
        try {
            if (ocLanguageKind == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "lang", "com/jetbrains/cidr/cpp/cmake/workspace/CMakeWorkspace", "collectCompilerWarnings"));
            }
        }
        catch (NullPointerException ex4) {
            throw b(ex4);
        }
        final CompilerInfoCache.Entry entry = compilerInfoCache.getCompilerInfoCache(cMakeResolveConfiguration.getProject(), cMakeResolveConfiguration.getCompilerSettings(), ocLanguageKind, virtualFile).getResult();
        try {
            if (entry != null) {
                multiValuesMap.putAll((Object)cMakeResolveConfiguration.getConfiguration().getId(), (Collection)entry.warnings);
            }
        }
        catch (NullPointerException ex5) {
            throw b(ex5);
        }
    }
    
    private static void a(@NotNull final Map<String, Set<VirtualFile>> map, @NotNull final Map<String, List<VirtualFile>> map2, @NotNull final CMakeResolveConfiguration cMakeResolveConfiguration, @NotNull final OCLanguageKind ocLanguageKind, @Nullable final VirtualFile virtualFile) {
        try {
            if (map == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "config2pchFiles", "com/jetbrains/cidr/cpp/cmake/workspace/CMakeWorkspace", "collectPCHFiles"));
            }
        }
        catch (NullPointerException ex) {
            throw b(ex);
        }
        try {
            if (map2 == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "fileAndLang2pchFiles", "com/jetbrains/cidr/cpp/cmake/workspace/CMakeWorkspace", "collectPCHFiles"));
            }
        }
        catch (NullPointerException ex2) {
            throw b(ex2);
        }
        try {
            if (cMakeResolveConfiguration == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "config", "com/jetbrains/cidr/cpp/cmake/workspace/CMakeWorkspace", "collectPCHFiles"));
            }
        }
        catch (NullPointerException ex3) {
            throw b(ex3);
        }
        try {
            if (ocLanguageKind == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "kind", "com/jetbrains/cidr/cpp/cmake/workspace/CMakeWorkspace", "collectPCHFiles"));
            }
        }
        catch (NullPointerException ex4) {
            throw b(ex4);
        }
        final List<VirtualFile> precompiledHeadersInternal = cMakeResolveConfiguration.getPrecompiledHeadersInternal(ocLanguageKind, virtualFile);
        if (precompiledHeadersInternal != null) {
            final String uniqueName = cMakeResolveConfiguration.getUniqueName();
            Object o = map.get(uniqueName);
            if (o == null) {
                map.put(uniqueName, (Set<VirtualFile>)(o = new THashSet()));
            }
            ((Set<VirtualFile>)o).addAll(precompiledHeadersInternal);
            map2.put(cMakeResolveConfiguration.getCompilerSettings().getCompilerKey(ocLanguageKind, virtualFile), precompiledHeadersInternal);
        }
    }
    
    private static void a(@NotNull final CMakeResolveConfiguration cMakeResolveConfiguration, @NotNull final OCLanguageKind ocLanguageKind, @NotNull final CidrCompilerResult<HeaderRoots> cidrCompilerResult, @NotNull final MultiValuesMap<Integer, String> multiValuesMap, @NotNull final THashSet<File> set, @NotNull final THashSet<File> set2, @NotNull final THashSet<File> set3, @NotNull final THashSet<VirtualFile> set4) {
        try {
            if (cMakeResolveConfiguration == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "configuration", "com/jetbrains/cidr/cpp/cmake/workspace/CMakeWorkspace", "collectFilesFromRoots"));
            }
        }
        catch (NullPointerException ex) {
            throw b(ex);
        }
        try {
            if (ocLanguageKind == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "language", "com/jetbrains/cidr/cpp/cmake/workspace/CMakeWorkspace", "collectFilesFromRoots"));
            }
        }
        catch (NullPointerException ex2) {
            throw b(ex2);
        }
        try {
            if (cidrCompilerResult == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "roots", "com/jetbrains/cidr/cpp/cmake/workspace/CMakeWorkspace", "collectFilesFromRoots"));
            }
        }
        catch (NullPointerException ex3) {
            throw b(ex3);
        }
        try {
            if (multiValuesMap == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "messagesCollector", "com/jetbrains/cidr/cpp/cmake/workspace/CMakeWorkspace", "collectFilesFromRoots"));
            }
        }
        catch (NullPointerException ex4) {
            throw b(ex4);
        }
        try {
            if (set == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "systemHeaderRoots", "com/jetbrains/cidr/cpp/cmake/workspace/CMakeWorkspace", "collectFilesFromRoots"));
            }
        }
        catch (NullPointerException ex5) {
            throw b(ex5);
        }
        try {
            if (set2 == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "userHeaderRoots", "com/jetbrains/cidr/cpp/cmake/workspace/CMakeWorkspace", "collectFilesFromRoots"));
            }
        }
        catch (NullPointerException ex6) {
            throw b(ex6);
        }
        try {
            if (set3 == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "headerExcludeRoots", "com/jetbrains/cidr/cpp/cmake/workspace/CMakeWorkspace", "collectFilesFromRoots"));
            }
        }
        catch (NullPointerException ex7) {
            throw b(ex7);
        }
        try {
            if (set4 == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "filesToIndex", "com/jetbrains/cidr/cpp/cmake/workspace/CMakeWorkspace", "collectFilesFromRoots"));
            }
        }
        catch (NullPointerException ex8) {
            throw b(ex8);
        }
        final HeaderRoots headerRoots = cidrCompilerResult.getResult();
        if (headerRoots != null) {
            final CidrRootsSynchronizer.HeaderSearchRoots headerSearchRoots = new CidrRootsSynchronizer.HeaderSearchRoots();
            CidrRootsSynchronizer.collectHeaderSearchRoots(headerRoots.getRoots(), headerSearchRoots);
            set.addAll((Collection)headerSearchRoots.systemHeaderRoots);
            set2.addAll((Collection)headerSearchRoots.userHeaderRoots);
            set3.addAll((Collection)headerSearchRoots.excludeRoots);
            final OCCompilerKind compiler = cMakeResolveConfiguration.getCompilerSettings().getCompiler(ocLanguageKind);
            for (final PsiFileSystemItem psiFileSystemItem : headerRoots.getRoots()) {
                try {
                    if (psiFileSystemItem instanceof FrameworksSearchRoot) {
                        continue;
                    }
                }
                catch (NullPointerException ex9) {
                    throw b(ex9);
                }
                try {
                    if (compiler == OCCompilerKind.MSVC) {
                        continue;
                    }
                }
                catch (NullPointerException ex10) {
                    throw b(ex10);
                }
                psiFileSystemItem.processChildren((PsiElementProcessor)new HeadersSearchRootProcessor() {
                    @Override
                    public boolean process(@NotNull final VirtualFile virtualFile) {
                        try {
                            if (virtualFile == null) {
                                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "file", "com/jetbrains/cidr/cpp/cmake/workspace/CMakeWorkspace$10", "process"));
                            }
                        }
                        catch (IllegalArgumentException ex) {
                            throw a(ex);
                        }
                        try {
                            if (!virtualFile.isDirectory()) {
                                set4.add((Object)virtualFile);
                            }
                        }
                        catch (IllegalArgumentException ex2) {
                            throw a(ex2);
                        }
                        return true;
                    }
                    
                    private static IllegalArgumentException a(final IllegalArgumentException ex) {
                        return ex;
                    }
                });
            }
        }
        else {
            final Throwable error = cidrCompilerResult.getError();
            try {
                if (error == null) {
                    CPPLog.LOG.error("No error in CidrProcessResult");
                    return;
                }
            }
            catch (NullPointerException ex11) {
                throw b(ex11);
            }
            multiValuesMap.put((Object)cMakeResolveConfiguration.getConfiguration().getId(), (Object)error.getMessage());
        }
    }
    
    private static void d(@NotNull final State state, @NotNull final File file) {
        try {
            if (state == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "state", "com/jetbrains/cidr/cpp/cmake/workspace/CMakeWorkspace", "saveState"));
            }
        }
        catch (IOException ex) {
            throw b(ex);
        }
        try {
            if (file == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "file", "com/jetbrains/cidr/cpp/cmake/workspace/CMakeWorkspace", "saveState"));
            }
        }
        catch (IOException ex2) {
            throw b(ex2);
        }
        try {
            try {
                if (!FileUtil.createParentDirs(file)) {
                    throw new IOException("Cannot create directory: " + file.getParent());
                }
            }
            catch (IOException ex3) {
                throw b(ex3);
            }
            final ObjectOutputStream objectOutputStream = new ObjectOutputStream(new BufferedOutputStream(new FileOutputStream(file)));
            try {
                Label_0181: {
                    ObjectOutputStream objectOutputStream2;
                    try {
                        objectOutputStream.writeInt(106);
                        objectOutputStream.writeInt(105);
                        objectOutputStream2 = objectOutputStream;
                        if (state.projectDir != null) {
                            final boolean b = true;
                            break Label_0181;
                        }
                    }
                    catch (IOException ex4) {
                        throw b(ex4);
                    }
                    final boolean b = false;
                    try {
                        objectOutputStream2.writeBoolean(b);
                        if (state.projectDir != null) {
                            objectOutputStream.writeUTF(state.projectDir.getPath());
                        }
                    }
                    catch (IOException ex5) {
                        throw b(ex5);
                    }
                }
                ObjectOutputStream objectOutputStream3 = null;
                boolean b2 = false;
                Label_0234: {
                    try {
                        objectOutputStream.writeLong(state.settingsHash);
                        objectOutputStream3 = objectOutputStream;
                        if (state.model != null) {
                            b2 = true;
                            break Label_0234;
                        }
                    }
                    catch (IOException ex6) {
                        throw b(ex6);
                    }
                    b2 = false;
                }
                objectOutputStream3.writeBoolean(b2);
                if (state.model != null) {
                    state.model.save(objectOutputStream);
                    objectOutputStream.writeInt(state.configEnvironments.size());
                    for (final Map.Entry<Integer, CMakeEnvironment> entry : state.configEnvironments.entrySet()) {
                        objectOutputStream.writeInt(entry.getKey());
                        objectOutputStream.writeUTF(entry.getValue().getUniqueID());
                    }
                    a(objectOutputStream, state.libraryRootsAndFiles.systemHeaderRoots);
                    a(objectOutputStream, state.libraryRootsAndFiles.userHeaderRoots);
                    a(objectOutputStream, state.libraryRootsAndFiles.headerExcludeRoots);
                    a(objectOutputStream, VfsUtilCore.virtualToIoFiles((Collection)state.libraryRootsAndFiles.filesToIndex));
                    a(objectOutputStream, state.libraryRootsAndFiles.config2pchFiles);
                    a(objectOutputStream, state.libraryRootsAndFiles.fileAndLang2pchFiles);
                    PrecompiledIncludeMap.save(objectOutputStream, state.libraryRootsAndFiles.config2includeMap);
                    b(objectOutputStream, state.cmakeFilesWithStamps);
                }
            }
            finally {
                objectOutputStream.close();
            }
        }
        catch (IOException ex7) {
            CPPLog.LOG.warn("Cannot save CMake model file: " + file, (Throwable)ex7);
        }
    }
    
    private static void a(@NotNull final ObjectOutputStream objectOutputStream, @NotNull final Collection<File> collection) throws IOException {
        try {
            if (objectOutputStream == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "out", "com/jetbrains/cidr/cpp/cmake/workspace/CMakeWorkspace", "writeFilesList"));
            }
        }
        catch (IOException ex) {
            throw b(ex);
        }
        try {
            if (collection == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "files", "com/jetbrains/cidr/cpp/cmake/workspace/CMakeWorkspace", "writeFilesList"));
            }
        }
        catch (IOException ex2) {
            throw b(ex2);
        }
        objectOutputStream.writeInt(collection.size());
        final Iterator<File> iterator = collection.iterator();
        while (iterator.hasNext()) {
            objectOutputStream.writeUTF(iterator.next().getPath());
        }
    }
    
    private static void b(@NotNull final ObjectOutputStream objectOutputStream, @NotNull final Map<File, FileStamp> map) throws IOException {
        try {
            if (objectOutputStream == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "out", "com/jetbrains/cidr/cpp/cmake/workspace/CMakeWorkspace", "writeFilesWithStamps"));
            }
        }
        catch (IOException ex) {
            throw b(ex);
        }
        try {
            if (map == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "filesWithStamps", "com/jetbrains/cidr/cpp/cmake/workspace/CMakeWorkspace", "writeFilesWithStamps"));
            }
        }
        catch (IOException ex2) {
            throw b(ex2);
        }
        objectOutputStream.writeInt(map.size());
        for (final Map.Entry<File, FileStamp> entry : map.entrySet()) {
            objectOutputStream.writeUTF(entry.getKey().getPath());
            objectOutputStream.writeLong(entry.getValue().timestamp);
            objectOutputStream.writeLong(entry.getValue().size);
            objectOutputStream.writeLong(entry.getValue().contentCrc);
        }
    }
    
    private static void a(@NotNull final ObjectOutputStream objectOutputStream, @NotNull final Map<String, ? extends Collection<VirtualFile>> map) throws IOException {
        try {
            if (objectOutputStream == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "out", "com/jetbrains/cidr/cpp/cmake/workspace/CMakeWorkspace", "writeMapWithFiles"));
            }
        }
        catch (IOException ex) {
            throw b(ex);
        }
        try {
            if (map == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "configFiles", "com/jetbrains/cidr/cpp/cmake/workspace/CMakeWorkspace", "writeMapWithFiles"));
            }
        }
        catch (IOException ex2) {
            throw b(ex2);
        }
        objectOutputStream.writeInt(map.size());
        for (final Map.Entry<String, ? extends Collection<VirtualFile>> entry : map.entrySet()) {
            objectOutputStream.writeUTF(entry.getKey());
            a(objectOutputStream, VfsUtilCore.virtualToIoFiles((Collection)entry.getValue()));
        }
    }
    
    @Nullable
    private State a(@NotNull final File file, @NotNull final File file2, @NotNull final List<CMakeConfigurationInfo> list) {
        try {
            if (file == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "currentProjectDir", "com/jetbrains/cidr/cpp/cmake/workspace/CMakeWorkspace", "loadStateIfActual"));
            }
        }
        catch (IOException ex) {
            throw b(ex);
        }
        try {
            if (file2 == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "modelFile", "com/jetbrains/cidr/cpp/cmake/workspace/CMakeWorkspace", "loadStateIfActual"));
            }
        }
        catch (IOException ex2) {
            throw b(ex2);
        }
        try {
            if (list == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "configurationInfos", "com/jetbrains/cidr/cpp/cmake/workspace/CMakeWorkspace", "loadStateIfActual"));
            }
        }
        catch (IOException ex3) {
            throw b(ex3);
        }
        try {
            if (!file2.exists()) {
                return null;
            }
        }
        catch (IOException ex4) {
            throw b(ex4);
        }
        try {
            final ObjectInputStream objectInputStream = new ObjectInputStream(new BufferedInputStream(new FileInputStream(file2)));
            try {
                try {
                    final int int1 = objectInputStream.readInt();
                    final int int2 = objectInputStream.readInt();
                    Label_0215: {
                        try {
                            if (int1 == 106) {
                                if (int2 == 105) {
                                    break Label_0215;
                                }
                            }
                        }
                        catch (IOException ex5) {
                            throw b(ex5);
                        }
                        return null;
                    }
                }
                catch (IOException ex14) {
                    return null;
                }
                String utf = null;
                Label_0252: {
                    try {
                        if (objectInputStream.readBoolean()) {
                            utf = objectInputStream.readUTF();
                            break Label_0252;
                        }
                    }
                    catch (IOException ex6) {
                        throw b(ex6);
                    }
                    utf = null;
                }
                final String s = utf;
                File file3 = null;
                Label_0276: {
                    try {
                        if (s != null) {
                            file3 = new File(s);
                            break Label_0276;
                        }
                    }
                    catch (IOException ex7) {
                        throw b(ex7);
                    }
                    file3 = null;
                }
                final File file4 = file3;
                if (!FileUtil.filesEqual(file4, file)) {
                    return null;
                }
                final long long1 = objectInputStream.readLong();
                State a = null;
                CMakeModel load = null;
                Label_0341: {
                    Label_0328: {
                        try {
                            if (file4 == null) {
                                return a;
                            }
                            final ObjectInputStream objectInputStream2 = objectInputStream;
                            final boolean b = objectInputStream2.readBoolean();
                            if (b) {
                                break Label_0328;
                            }
                            break Label_0328;
                        }
                        catch (IOException ex8) {
                            throw b(ex8);
                        }
                        try {
                            final ObjectInputStream objectInputStream2 = objectInputStream;
                            final boolean b = objectInputStream2.readBoolean();
                            if (b) {
                                load = CMakeModel.load(objectInputStream);
                                break Label_0341;
                            }
                        }
                        catch (IOException ex9) {
                            throw b(ex9);
                        }
                    }
                    load = null;
                }
                final CMakeModel cMakeModel = load;
                if (cMakeModel != null) {
                    int int3 = objectInputStream.readInt();
                    final THashMap tHashMap = new THashMap(int3);
                    while (int3-- > 0) {
                        final int int4 = objectInputStream.readInt();
                        final String utf2 = objectInputStream.readUTF();
                        CMakeConfigurationInfo cMakeConfigurationInfo = null;
                        Label_0418: {
                            try {
                                if (int4 < list.size()) {
                                    cMakeConfigurationInfo = list.get(int4);
                                    break Label_0418;
                                }
                            }
                            catch (IOException ex10) {
                                throw b(ex10);
                            }
                            cMakeConfigurationInfo = null;
                        }
                        final CMakeConfigurationInfo cMakeConfigurationInfo2 = cMakeConfigurationInfo;
                        Label_0474: {
                            Label_0440: {
                                try {
                                    if (cMakeConfigurationInfo2 == null) {
                                        return null;
                                    }
                                    final CMakeConfigurationInfo cMakeConfigurationInfo3 = cMakeConfigurationInfo2;
                                    final CMakeEnvironment cMakeEnvironment = cMakeConfigurationInfo3.getEnvironment();
                                    if (cMakeEnvironment != null) {
                                        break Label_0440;
                                    }
                                    return null;
                                }
                                catch (IOException ex11) {
                                    throw b(ex11);
                                }
                                try {
                                    final CMakeConfigurationInfo cMakeConfigurationInfo3 = cMakeConfigurationInfo2;
                                    final CMakeEnvironment cMakeEnvironment = cMakeConfigurationInfo3.getEnvironment();
                                    if (cMakeEnvironment != null) {
                                        if (utf2.equals(cMakeConfigurationInfo2.getEnvironment().getUniqueID())) {
                                            break Label_0474;
                                        }
                                    }
                                }
                                catch (IOException ex12) {
                                    throw b(ex12);
                                }
                            }
                            return null;
                        }
                        ((Map<Integer, CMakeEnvironment>)tHashMap).put(int4, cMakeConfigurationInfo2.getEnvironment());
                    }
                    final IOLibraryRootsAndFiles ioLibraryRootsAndFiles = new IOLibraryRootsAndFiles();
                    ioLibraryRootsAndFiles.systemHeaderRoots = c(objectInputStream);
                    ioLibraryRootsAndFiles.userHeaderRoots = c(objectInputStream);
                    ioLibraryRootsAndFiles.headerExcludeRoots = c(objectInputStream);
                    ioLibraryRootsAndFiles.filesToIndex = c(objectInputStream);
                    ioLibraryRootsAndFiles.config2pchFiles = b(objectInputStream);
                    ioLibraryRootsAndFiles.fileAndLang2pchFiles = b(objectInputStream);
                    ioLibraryRootsAndFiles.config2includeMap = PrecompiledIncludeMap.load(objectInputStream);
                    a = this.a(long1, file4, cMakeModel, (Map<Integer, CMakeEnvironment>)tHashMap, a(objectInputStream), ioLibraryRootsAndFiles, null, null);
                }
                return a;
            }
            finally {
                objectInputStream.close();
            }
        }
        catch (IOException ex13) {
            CPPLog.LOG.warn("Cannot load CMake model file: " + file2, (Throwable)ex13);
            return null;
        }
    }
    
    private static List<File> c(final ObjectInputStream objectInputStream) throws IOException {
        int int1 = objectInputStream.readInt();
        final ArrayList list = new ArrayList<File>(int1);
        try {
            while (int1-- > 0) {
                list.add(new File(objectInputStream.readUTF()));
            }
        }
        catch (IOException ex) {
            throw b(ex);
        }
        return (List<File>)list;
    }
    
    private static Map<String, List<File>> b(@NotNull final ObjectInputStream objectInputStream) throws IOException {
        try {
            if (objectInputStream == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "in", "com/jetbrains/cidr/cpp/cmake/workspace/CMakeWorkspace", "readMapWithLists"));
            }
        }
        catch (IOException ex) {
            throw b(ex);
        }
        int int1 = objectInputStream.readInt();
        final THashMap tHashMap = new THashMap(int1);
        try {
            while (int1-- > 0) {
                ((Map<String, List<File>>)tHashMap).put(objectInputStream.readUTF(), c(objectInputStream));
            }
        }
        catch (IOException ex2) {
            throw b(ex2);
        }
        return (Map<String, List<File>>)tHashMap;
    }
    
    private static Map<File, FileStamp> a(final ObjectInputStream objectInputStream) throws IOException {
        int int1 = objectInputStream.readInt();
        final THashMap tHashMap = new THashMap(int1, FileUtil.FILE_HASHING_STRATEGY);
        try {
            while (int1-- > 0) {
                ((Map<File, FileStamp>)tHashMap).put(new File(objectInputStream.readUTF()), new FileStamp(objectInputStream.readLong(), objectInputStream.readLong(), objectInputStream.readLong()));
            }
        }
        catch (IOException ex) {
            throw b(ex);
        }
        return (Map<File, FileStamp>)tHashMap;
    }
    
    private void g() {
        String projectName = null;
        final CMakeModel model = this.myState.model;
        if (model != null) {
            projectName = model.getProjectName();
        }
        Label_0052: {
            try {
                if (projectName != null) {
                    if (!"Project".equals(projectName)) {
                        break Label_0052;
                    }
                }
            }
            catch (NullPointerException ex) {
                throw b(ex);
            }
            projectName = PathUtil.getFileName(this.myProject.getBasePath());
        }
        ((ProjectImpl)this.myProject).setProjectName(projectName);
    }
    
    private void d() {
        this.a(false);
    }
    
    private void a(final boolean b) {
        this.a(this.myState, this.myContentRoot, b);
    }
    
    private void a(@NotNull final State state, @Nullable final File file) {
        try {
            if (state == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "state", "com/jetbrains/cidr/cpp/cmake/workspace/CMakeWorkspace", "updateContentRoots"));
            }
        }
        catch (NullPointerException ex) {
            throw b(ex);
        }
        this.a(state, file, false);
    }
    
    private void a(@NotNull final State state, @Nullable File myContentRoot, final boolean b) {
        try {
            if (state == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "state", "com/jetbrains/cidr/cpp/cmake/workspace/CMakeWorkspace", "updateContentRoots"));
            }
        }
        catch (NullPointerException ex) {
            throw b(ex);
        }
        try {
            ApplicationManager.getApplication().assertWriteAccessAllowed();
            if (this.myUpdateContentRootsRecursionProtection) {
                return;
            }
        }
        catch (NullPointerException ex2) {
            throw b(ex2);
        }
        this.myUpdateContentRootsRecursionProtection = true;
        try {
            if (FileUtil.filesEqual(state.projectDir, myContentRoot)) {
                myContentRoot = null;
            }
            this.myContentRoot = myContentRoot;
            final ChangeListManager changeListManager;
            final Object o;
            this.c(state, myContentRoot).forEach(parentFile -> {
                ChangeListManager.getInstance(this.myProject);
                changeListManager.addDirectoryToIgnoreImplicitly(FileUtil.toCanonicalPath(parentFile.getPath()));
                while (true) {
                    parentFile = parentFile.getParentFile();
                    try {
                        if (o != null) {
                            changeListManager.removeImplicitlyIgnoredDirectory(FileUtil.toCanonicalPath(parentFile.getPath()));
                            continue;
                        }
                    }
                    catch (NullPointerException ex3) {
                        throw b(ex3);
                    }
                    break;
                }
                return;
            });
            final UpdateRoots b2 = this.b(state, myContentRoot);
            boolean myReportFilesOutsideRoots = false;
            final Iterator<File> iterator = b2.sourceFiles.iterator();
            while (iterator.hasNext()) {
                if (!CidrRootsSynchronizer.isUnder(b2.roots, iterator.next())) {
                    myReportFilesOutsideRoots = true;
                    break;
                }
            }
            try {
                if (!myReportFilesOutsideRoots) {
                    this.myIgnoreFilesOutsideOfProjectRoot = false;
                }
            }
            catch (NullPointerException ex4) {
                throw b(ex4);
            }
            Project myProject = null;
            boolean b3 = false;
            Label_0237: {
                try {
                    this.myReportFilesOutsideRoots = myReportFilesOutsideRoots;
                    this.myRootsSynchronizer.updateRoots(b2.rootsInfo, b);
                    myProject = this.myProject;
                    if (state.model != null) {
                        b3 = true;
                        break Label_0237;
                    }
                }
                catch (NullPointerException ex5) {
                    throw b(ex5);
                }
                b3 = false;
            }
            CidrNotInProjectNotification.setEnabled(myProject, b3);
        }
        finally {
            this.myUpdateContentRootsRecursionProtection = false;
        }
    }
    
    @NotNull
    List<VirtualFile> getExcludeRoots() {
        List mapNotNull;
        try {
            mapNotNull = ContainerUtil.mapNotNull((Collection)this.c(this.myState, this.myContentRoot), file -> VfsUtil.findFileByIoFile(file, false));
            if (mapNotNull == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/cpp/cmake/workspace/CMakeWorkspace", "getExcludeRoots"));
            }
        }
        catch (NullPointerException ex) {
            throw b(ex);
        }
        return (List<VirtualFile>)mapNotNull;
    }
    
    void updateContentRoots(@NotNull final ModifiableRootModel modifiableRootModel) {
        try {
            if (modifiableRootModel == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "model", "com/jetbrains/cidr/cpp/cmake/workspace/CMakeWorkspace", "updateContentRoots"));
            }
        }
        catch (NullPointerException ex) {
            throw b(ex);
        }
        this.myRootsSynchronizer.updateModuleRoots(modifiableRootModel, this.b(this.myState, this.myContentRoot).rootsInfo);
    }
    
    @NotNull
    private UpdateRoots b(@NotNull final State state, @Nullable final File file) {
        try {
            if (state == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "state", "com/jetbrains/cidr/cpp/cmake/workspace/CMakeWorkspace", "collectUpdateRoots"));
            }
        }
        catch (NullPointerException ex) {
            throw b(ex);
        }
        ApplicationManager.getApplication().assertReadAccessAllowed();
        final ArrayList<File> list = new ArrayList<File>(state.sourceFiles);
        final List<File> a = a(state, file, list);
        for (final File file2 : state.projectResourceFiles) {
            for (final File file3 : a) {
                try {
                    if (VfsUtilCore.isAncestor(file3, file2, true)) {
                        list.add(file2);
                        break;
                    }
                    continue;
                }
                catch (NullPointerException ex2) {
                    throw b(ex2);
                }
            }
        }
        final CidrRootsSynchronizer.RootsInfo rootsInfo = new CidrRootsSynchronizer.RootsInfo();
        rootsInfo.registerSystemHeaderRootUnderContentRootAsLibraries = false;
        rootsInfo.contentRoots.addAll(a);
        rootsInfo.sourceFiles.addAll(list);
        final CidrRootConfiguration instance = CidrRootConfiguration.getInstance(this.myProject);
        UpdateRoots updateRoots;
        try {
            rootsInfo.explicitSourceFolders.addAll(instance.getSourceRoots());
            rootsInfo.explicitLibraryRoots.addAll(instance.getLibraryRoots());
            rootsInfo.explicitExcludeFolders.addAll(instance.getExcludeRoots());
            rootsInfo.headerSearchRoots.systemHeaderRoots.addAll(state.libraryRootsAndFiles.systemHeaderRoots);
            rootsInfo.headerSearchRoots.userHeaderRoots.addAll(state.libraryRootsAndFiles.userHeaderRoots);
            rootsInfo.headerSearchRoots.excludeRoots.addAll(state.libraryRootsAndFiles.headerExcludeRoots);
            updateRoots = new UpdateRoots(a, list, rootsInfo);
            if (updateRoots == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/cpp/cmake/workspace/CMakeWorkspace", "collectUpdateRoots"));
            }
        }
        catch (NullPointerException ex3) {
            throw b(ex3);
        }
        return updateRoots;
    }
    
    @NotNull
    private List<File> c(final State state, final File file2) {
        ApplicationManager.getApplication().assertReadAccessAllowed();
        final List<File> a = a(state, file2, null);
        final ArrayList<File> list = new ArrayList<File>();
        final Collection collection;
        final File file4;
        final List<File> list2;
        this.b(this.getSettings().getConfigurations()).forEach(file3 -> {
            if (collection.stream().anyMatch(file -> FileUtil.isAncestor(file3, file, false))) {
                file4 = new File(file3, "CMakeFiles");
            }
            list2.add(file4);
            return;
        });
        final Iterator<File> iterator = a.iterator();
        while (iterator.hasNext()) {
            final File file5 = new File(iterator.next(), "CMakeFiles");
            try {
                if (!file5.exists()) {
                    continue;
                }
                list.add(file5);
            }
            catch (NullPointerException ex) {
                throw b(ex);
            }
        }
        ArrayList<File> list3;
        try {
            list3 = list;
            if (list3 == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/cpp/cmake/workspace/CMakeWorkspace", "collectExcludeRoots"));
            }
        }
        catch (NullPointerException ex2) {
            throw b(ex2);
        }
        return list3;
    }
    
    @NotNull
    private static List<File> a(@NotNull final State state, @Nullable final File file, @Nullable final List<File> list) {
        try {
            if (state == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "state", "com/jetbrains/cidr/cpp/cmake/workspace/CMakeWorkspace", "getRoots"));
            }
        }
        catch (NullPointerException ex) {
            throw b(ex);
        }
        final ArrayList list2 = new ArrayList<File>();
        ContainerUtil.addIfNotNull((Collection)list2, (Object)file);
        ContainerUtil.addIfNotNull((Collection)list2, (Object)state.projectDir);
        for (final File file2 : state.cmakeFilesWithStamps.keySet()) {
            try {
                if (!"CMakeLists.txt".equalsIgnoreCase(file2.getName())) {
                    continue;
                }
            }
            catch (NullPointerException ex2) {
                throw b(ex2);
            }
            try {
                ContainerUtil.addIfNotNull((Collection)list2, (Object)file2.getParentFile());
                if (list == null) {
                    continue;
                }
                list.add(file2);
            }
            catch (NullPointerException ex3) {
                throw b(ex3);
            }
        }
        ArrayList list3;
        try {
            list3 = list2;
            if (list3 == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/cpp/cmake/workspace/CMakeWorkspace", "getRoots"));
            }
        }
        catch (NullPointerException ex4) {
            throw b(ex4);
        }
        return (List<File>)list3;
    }
    
    public void changeContentRoot(@Nullable final VirtualFile virtualFile) {
        State myState = null;
        File virtualToIoFile = null;
        Label_0026: {
            try {
                this.myIgnoreFilesOutsideOfProjectRoot = false;
                myState = this.myState;
                if (virtualFile == null) {
                    virtualToIoFile = null;
                    break Label_0026;
                }
            }
            catch (NullPointerException ex) {
                throw b(ex);
            }
            virtualToIoFile = VfsUtilCore.virtualToIoFile(virtualFile);
        }
        this.a(myState, virtualToIoFile);
    }
    
    @Nullable
    public VirtualFile getEffectiveContentRoot() {
        final File myContentRoot = this.myContentRoot;
        VirtualFile fileByIoFile = null;
        Label_0024: {
            try {
                if (myContentRoot == null) {
                    fileByIoFile = null;
                    break Label_0024;
                }
            }
            catch (NullPointerException ex) {
                throw b(ex);
            }
            fileByIoFile = LocalFileSystem.getInstance().findFileByIoFile(myContentRoot);
        }
        final VirtualFile virtualFile = fileByIoFile;
        try {
            if (virtualFile != null) {
                return virtualFile;
            }
        }
        catch (NullPointerException ex2) {
            throw b(ex2);
        }
        final File projectDir = this.myState.projectDir;
        try {
            if (projectDir == null) {
                return null;
            }
        }
        catch (NullPointerException ex3) {
            throw b(ex3);
        }
        return LocalFileSystem.getInstance().findFileByIoFile(projectDir);
    }
    
    @Nullable
    File getCurrentContentRootIOFile() {
        return this.myContentRoot;
    }
    
    @Nullable
    public Element getState() {
        return createStateElement(this.myProjectDir, this.myContentRoot, this.myIgnoreFilesOutsideOfProjectRoot);
    }
    
    static Element createStateElement(@Nullable final File file) {
        return createStateElement(file, null);
    }
    
    static Element createStateElement(@Nullable final File file, @Nullable final File file2) {
        return createStateElement(file, file2, false);
    }
    
    static Element createStateElement(@Nullable final File file, @Nullable final File file2, final boolean b) {
        final Element element = new Element("state");
        try {
            if (b) {
                element.setAttribute("IGNORE_OUTSIDE_FILES", "true");
            }
        }
        catch (NullPointerException ex) {
            throw b(ex);
        }
        try {
            if (file != null) {
                element.setAttribute("PROJECT_DIR", FileUtil.toSystemIndependentName(file.getPath()));
            }
        }
        catch (NullPointerException ex2) {
            throw b(ex2);
        }
        if (file2 != null) {
            final Element element2 = new Element("contentRoot");
            element2.setAttribute("DIR", FileUtil.toSystemIndependentName(file2.getPath()));
            element.addContent(element2);
        }
        return element;
    }
    
    public void loadState(final Element p0) {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     0: aload_1        
        //     1: ldc             "PROJECT_DIR"
        //     3: invokevirtual   org/jdom/Element.getAttributeValue:(Ljava/lang/String;)Ljava/lang/String;
        //     6: astore          5
        //     8: new             Ljava/io/File;
        //    11: dup            
        //    12: aload           5
        //    14: ifnonnull       33
        //    17: aload_0        
        //    18: getfield        com/jetbrains/cidr/cpp/cmake/workspace/CMakeWorkspace.myProject:Lcom/intellij/openapi/project/Project;
        //    21: invokeinterface com/intellij/openapi/project/Project.getBasePath:()Ljava/lang/String;
        //    26: goto            35
        //    29: invokestatic    com/jetbrains/cidr/cpp/cmake/workspace/CMakeWorkspace.b:(Ljava/lang/Exception;)Ljava/lang/Exception;
        //    32: athrow         
        //    33: aload           5
        //    35: invokespecial   java/io/File.<init>:(Ljava/lang/String;)V
        //    38: astore          6
        //    40: aload_0        
        //    41: getfield        com/jetbrains/cidr/cpp/cmake/workspace/CMakeWorkspace.myProject:Lcom/intellij/openapi/project/Project;
        //    44: invokestatic    com/jetbrains/cidr/cpp/cmake/workspace/CMakeWorkspace.a:(Lcom/intellij/openapi/project/Project;)Lcom/jetbrains/cidr/cpp/cmake/CMakeProjectOpenProcessor$OpenProjectSpec;
        //    47: astore          7
        //    49: aload           7
        //    51: ifnull          89
        //    54: aload           7
        //    56: getfield        com/jetbrains/cidr/cpp/cmake/CMakeProjectOpenProcessor$OpenProjectSpec.generationDir:Ljava/io/File;
        //    59: ifnonnull       97
        //    62: goto            69
        //    65: invokestatic    com/jetbrains/cidr/cpp/cmake/workspace/CMakeWorkspace.b:(Ljava/lang/Exception;)Ljava/lang/Exception;
        //    68: athrow         
        //    69: aload           6
        //    71: aload           7
        //    73: getfield        com/jetbrains/cidr/cpp/cmake/CMakeProjectOpenProcessor$OpenProjectSpec.sourceDir:Ljava/io/File;
        //    76: invokestatic    com/intellij/openapi/util/io/FileUtil.filesEqual:(Ljava/io/File;Ljava/io/File;)Z
        //    79: ifeq            97
        //    82: goto            89
        //    85: invokestatic    com/jetbrains/cidr/cpp/cmake/workspace/CMakeWorkspace.b:(Ljava/lang/Exception;)Ljava/lang/Exception;
        //    88: athrow         
        //    89: iconst_1       
        //    90: goto            98
        //    93: invokestatic    com/jetbrains/cidr/cpp/cmake/workspace/CMakeWorkspace.b:(Ljava/lang/Exception;)Ljava/lang/Exception;
        //    96: athrow         
        //    97: iconst_0       
        //    98: istore          8
        //   100: iload           8
        //   102: ifeq            175
        //   105: aload           6
        //   107: astore_2       
        //   108: aload_1        
        //   109: ldc             "contentRoot"
        //   111: invokevirtual   org/jdom/Element.getChild:(Ljava/lang/String;)Lorg/jdom/Element;
        //   114: astore          9
        //   116: aload           9
        //   118: ifnonnull       129
        //   121: aconst_null    
        //   122: goto            136
        //   125: invokestatic    com/jetbrains/cidr/cpp/cmake/workspace/CMakeWorkspace.b:(Ljava/lang/Exception;)Ljava/lang/Exception;
        //   128: athrow         
        //   129: aload           9
        //   131: ldc             "DIR"
        //   133: invokevirtual   org/jdom/Element.getAttributeValue:(Ljava/lang/String;)Ljava/lang/String;
        //   136: astore          10
        //   138: aload           10
        //   140: ifnonnull       151
        //   143: aconst_null    
        //   144: goto            160
        //   147: invokestatic    com/jetbrains/cidr/cpp/cmake/workspace/CMakeWorkspace.b:(Ljava/lang/Exception;)Ljava/lang/Exception;
        //   150: athrow         
        //   151: new             Ljava/io/File;
        //   154: dup            
        //   155: aload           10
        //   157: invokespecial   java/io/File.<init>:(Ljava/lang/String;)V
        //   160: astore_3       
        //   161: aload_1        
        //   162: ldc             "IGNORE_OUTSIDE_FILES"
        //   164: invokevirtual   org/jdom/Element.getAttributeValue:(Ljava/lang/String;)Ljava/lang/String;
        //   167: invokestatic    java/lang/Boolean.parseBoolean:(Ljava/lang/String;)Z
        //   170: istore          4
        //   172: goto            192
        //   175: aload           7
        //   177: getfield        com/jetbrains/cidr/cpp/cmake/CMakeProjectOpenProcessor$OpenProjectSpec.sourceDir:Ljava/io/File;
        //   180: astore_2       
        //   181: aload_0        
        //   182: aload           7
        //   184: invokespecial   com/jetbrains/cidr/cpp/cmake/workspace/CMakeWorkspace.a:(Lcom/jetbrains/cidr/cpp/cmake/CMakeProjectOpenProcessor$OpenProjectSpec;)V
        //   187: aconst_null    
        //   188: astore_3       
        //   189: iconst_0       
        //   190: istore          4
        //   192: aload_0        
        //   193: aload_2        
        //   194: putfield        com/jetbrains/cidr/cpp/cmake/workspace/CMakeWorkspace.myProjectDir:Ljava/io/File;
        //   197: aload_0        
        //   198: aload_3        
        //   199: putfield        com/jetbrains/cidr/cpp/cmake/workspace/CMakeWorkspace.myContentRoot:Ljava/io/File;
        //   202: aload_0        
        //   203: iload           4
        //   205: putfield        com/jetbrains/cidr/cpp/cmake/workspace/CMakeWorkspace.myIgnoreFilesOutsideOfProjectRoot:Z
        //   208: aload_0        
        //   209: getfield        com/jetbrains/cidr/cpp/cmake/workspace/CMakeWorkspace.myInitialized:Z
        //   212: ifne            299
        //   215: aconst_null    
        //   216: astore          9
        //   218: iload           8
        //   220: ifeq            264
        //   223: aload_0        
        //   224: aload_0        
        //   225: invokedynamic   compute:(Lcom/jetbrains/cidr/cpp/cmake/workspace/CMakeWorkspace;)Lcom/intellij/openapi/util/Computable;
        //   230: invokespecial   com/jetbrains/cidr/cpp/cmake/workspace/CMakeWorkspace.a:(Lcom/intellij/openapi/util/Computable;)Ljava/lang/Object;
        //   233: checkcast       Ljava/io/File;
        //   236: astore          10
        //   238: aload_0        
        //   239: aload_0        
        //   240: invokedynamic   compute:(Lcom/jetbrains/cidr/cpp/cmake/workspace/CMakeWorkspace;)Lcom/intellij/openapi/util/Computable;
        //   245: invokespecial   com/jetbrains/cidr/cpp/cmake/workspace/CMakeWorkspace.a:(Lcom/intellij/openapi/util/Computable;)Ljava/lang/Object;
        //   248: checkcast       Ljava/util/List;
        //   251: astore          11
        //   253: aload_0        
        //   254: aload_2        
        //   255: aload           10
        //   257: aload           11
        //   259: invokespecial   com/jetbrains/cidr/cpp/cmake/workspace/CMakeWorkspace.a:(Ljava/io/File;Ljava/io/File;Ljava/util/List;)Lcom/jetbrains/cidr/cpp/cmake/workspace/CMakeWorkspace$State;
        //   262: astore          9
        //   264: aload           9
        //   266: ifnull          287
        //   269: aload_0        
        //   270: aload           9
        //   272: putfield        com/jetbrains/cidr/cpp/cmake/workspace/CMakeWorkspace.myState:Lcom/jetbrains/cidr/cpp/cmake/workspace/CMakeWorkspace$State;
        //   275: aload_0        
        //   276: iconst_1       
        //   277: putfield        com/jetbrains/cidr/cpp/cmake/workspace/CMakeWorkspace.myStateWasLoaded:Z
        //   280: goto            296
        //   283: invokestatic    com/jetbrains/cidr/cpp/cmake/workspace/CMakeWorkspace.b:(Ljava/lang/Exception;)Ljava/lang/Exception;
        //   286: athrow         
        //   287: aload_0        
        //   288: aload_0        
        //   289: aload_2        
        //   290: invokespecial   com/jetbrains/cidr/cpp/cmake/workspace/CMakeWorkspace.b:(Ljava/io/File;)Lcom/jetbrains/cidr/cpp/cmake/workspace/CMakeWorkspace$State;
        //   293: putfield        com/jetbrains/cidr/cpp/cmake/workspace/CMakeWorkspace.myState:Lcom/jetbrains/cidr/cpp/cmake/workspace/CMakeWorkspace$State;
        //   296: goto            339
        //   299: aload_0        
        //   300: getfield        com/jetbrains/cidr/cpp/cmake/workspace/CMakeWorkspace.myState:Lcom/jetbrains/cidr/cpp/cmake/workspace/CMakeWorkspace$State;
        //   303: getfield        com/jetbrains/cidr/cpp/cmake/workspace/CMakeWorkspace$State.projectDir:Ljava/io/File;
        //   306: aload_2        
        //   307: invokestatic    com/intellij/openapi/util/io/FileUtil.filesEqual:(Ljava/io/File;Ljava/io/File;)Z
        //   310: ifne            325
        //   313: aload_0        
        //   314: aload_2        
        //   315: invokevirtual   com/jetbrains/cidr/cpp/cmake/workspace/CMakeWorkspace.scheduleReloadNewProjectDir:(Ljava/io/File;)V
        //   318: goto            339
        //   321: invokestatic    com/jetbrains/cidr/cpp/cmake/workspace/CMakeWorkspace.b:(Ljava/lang/Exception;)Ljava/lang/Exception;
        //   324: athrow         
        //   325: invokestatic    com/intellij/openapi/application/ApplicationManager.getApplication:()Lcom/intellij/openapi/application/Application;
        //   328: aload_0        
        //   329: invokedynamic   run:(Lcom/jetbrains/cidr/cpp/cmake/workspace/CMakeWorkspace;)Ljava/lang/Runnable;
        //   334: invokeinterface com/intellij/openapi/application/Application.runWriteAction:(Ljava/lang/Runnable;)V
        //   339: return         
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                            
        //  -----  -----  -----  -----  --------------------------------
        //  8      29     29     33     Ljava/lang/NullPointerException;
        //  49     62     65     69     Ljava/lang/NullPointerException;
        //  54     82     85     89     Ljava/lang/NullPointerException;
        //  69     93     93     97     Ljava/lang/NullPointerException;
        //  116    125    125    129    Ljava/lang/NullPointerException;
        //  138    147    147    151    Ljava/lang/NullPointerException;
        //  264    283    283    287    Ljava/lang/NullPointerException;
        //  299    321    321    325    Ljava/lang/NullPointerException;
        // 
        // The error that occurred was:
        // 
        // java.lang.IllegalStateException: Expression is linked from several locations: Label_0069:
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
    
    @Nullable
    public File getModelProjectDir() {
        return this.myState.projectDir;
    }
    
    @Nullable
    public CMakeModel getModel() {
        return this.myState.model;
    }
    
    long getModelSettingsHash() {
        return this.myState.settingsHash;
    }
    
    @NotNull
    public List<CMakeTarget> getModelTargets() {
        final CMakeModel model = this.myState.model;
        Object o = null;
        Label_0026: {
            try {
                if (model == null) {
                    final Object o2;
                    o = (o2 = Collections.emptyList());
                    break Label_0026;
                }
            }
            catch (NullPointerException ex) {
                throw b(ex);
            }
            Object o2;
            o = (o2 = model.getTargets());
            try {
                if (o2 == null) {
                    throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/cpp/cmake/workspace/CMakeWorkspace", "getModelTargets"));
                }
            }
            catch (NullPointerException ex2) {
                throw b(ex2);
            }
        }
        return (List<CMakeTarget>)o;
    }
    
    @NotNull
    public Set<File> getCMakeFiles() {
        Set<File> keySet;
        try {
            keySet = this.myState.cmakeFilesWithStamps.keySet();
            if (keySet == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/cpp/cmake/workspace/CMakeWorkspace", "getCMakeFiles"));
            }
        }
        catch (NullPointerException ex) {
            throw b(ex);
        }
        return keySet;
    }
    
    @NotNull
    Map<File, FileStamp> getCMakeFilesWithStamps() {
        Map<File, FileStamp> cmakeFilesWithStamps;
        try {
            cmakeFilesWithStamps = this.myState.cmakeFilesWithStamps;
            if (cmakeFilesWithStamps == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/cpp/cmake/workspace/CMakeWorkspace", "getCMakeFilesWithStamps"));
            }
        }
        catch (NullPointerException ex) {
            throw b(ex);
        }
        return cmakeFilesWithStamps;
    }
    
    @NotNull
    public Collection<File> getSourceFiles() {
        List<File> sourceFiles;
        try {
            sourceFiles = this.myState.sourceFiles;
            if (sourceFiles == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/cpp/cmake/workspace/CMakeWorkspace", "getSourceFiles"));
            }
        }
        catch (NullPointerException ex) {
            throw b(ex);
        }
        return sourceFiles;
    }
    
    @NotNull
    public Collection<VirtualFile> getSourceVirtualFiles() {
        Set<VirtualFile> keySet;
        try {
            keySet = this.myState.sourceFilesToConfigurations.keySet();
            if (keySet == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/cpp/cmake/workspace/CMakeWorkspace", "getSourceVirtualFiles"));
            }
        }
        catch (NullPointerException ex) {
            throw b(ex);
        }
        return keySet;
    }
    
    @NotNull
    public Collection<File> getProjectResourceFiles() {
        List<File> projectResourceFiles;
        try {
            projectResourceFiles = this.myState.projectResourceFiles;
            if (projectResourceFiles == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/cpp/cmake/workspace/CMakeWorkspace", "getProjectResourceFiles"));
            }
        }
        catch (NullPointerException ex) {
            throw b(ex);
        }
        return projectResourceFiles;
    }
    
    @NotNull
    public Collection<File> getSystemLibraryRoots() {
        List<File> systemHeaderRoots;
        try {
            systemHeaderRoots = this.myState.libraryRootsAndFiles.systemHeaderRoots;
            if (systemHeaderRoots == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/cpp/cmake/workspace/CMakeWorkspace", "getSystemLibraryRoots"));
            }
        }
        catch (NullPointerException ex) {
            throw b(ex);
        }
        return systemHeaderRoots;
    }
    
    @NotNull
    public Collection<File> getUserLibraryRoots() {
        List<File> userHeaderRoots;
        try {
            userHeaderRoots = this.myState.libraryRootsAndFiles.userHeaderRoots;
            if (userHeaderRoots == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/cpp/cmake/workspace/CMakeWorkspace", "getUserLibraryRoots"));
            }
        }
        catch (NullPointerException ex) {
            throw b(ex);
        }
        return userHeaderRoots;
    }
    
    @NotNull
    @Override
    public Collection<VirtualFile> getLibraryFilesToBuildSymbols() {
        List<VirtualFile> filesToIndex;
        try {
            filesToIndex = this.myState.libraryRootsAndFiles.filesToIndex;
            if (filesToIndex == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/cpp/cmake/workspace/CMakeWorkspace", "getLibraryFilesToBuildSymbols"));
            }
        }
        catch (NullPointerException ex) {
            throw b(ex);
        }
        return filesToIndex;
    }
    
    @Override
    public boolean isInSDK(@Nullable final VirtualFile virtualFile) {
        return false;
    }
    
    @Override
    public boolean isFromWrongSDK(@NotNull final OCSymbol ocSymbol, @Nullable final VirtualFile virtualFile) {
        try {
            if (ocSymbol == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "symbol", "com/jetbrains/cidr/cpp/cmake/workspace/CMakeWorkspace", "isFromWrongSDK"));
            }
        }
        catch (NullPointerException ex) {
            throw b(ex);
        }
        return false;
    }
    
    @Override
    public boolean areFromSameProject(@Nullable final VirtualFile virtualFile, @Nullable final VirtualFile virtualFile2) {
        return false;
    }
    
    @Override
    public boolean areFromSamePackage(@Nullable final VirtualFile virtualFile, @Nullable final VirtualFile virtualFile2) {
        return false;
    }
    
    @NotNull
    @Override
    public List<CMakeResolveConfiguration> getConfigurations() {
        List<CMakeResolveConfiguration> configurations;
        try {
            configurations = this.myState.configurations;
            if (configurations == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/cpp/cmake/workspace/CMakeWorkspace", "getConfigurations"));
            }
        }
        catch (NullPointerException ex) {
            throw b(ex);
        }
        return configurations;
    }
    
    @NotNull
    Set<VirtualFile> getPCHFiles(@NotNull final CMakeResolveConfiguration cMakeResolveConfiguration) {
        try {
            if (cMakeResolveConfiguration == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "config", "com/jetbrains/cidr/cpp/cmake/workspace/CMakeWorkspace", "getPCHFiles"));
            }
        }
        catch (NullPointerException ex) {
            throw b(ex);
        }
        Set notNullize;
        try {
            notNullize = ContainerUtil.notNullize((Set)this.myState.libraryRootsAndFiles.config2pchFiles.get(cMakeResolveConfiguration.getUniqueName()));
            if (notNullize == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/cpp/cmake/workspace/CMakeWorkspace", "getPCHFiles"));
            }
        }
        catch (NullPointerException ex2) {
            throw b(ex2);
        }
        return (Set<VirtualFile>)notNullize;
    }
    
    @NotNull
    List<VirtualFile> getPCHFiles(@NotNull final String s) {
        try {
            if (s == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "compilerKey", "com/jetbrains/cidr/cpp/cmake/workspace/CMakeWorkspace", "getPCHFiles"));
            }
        }
        catch (NullPointerException ex) {
            throw b(ex);
        }
        List notNullize;
        try {
            notNullize = ContainerUtil.notNullize((List)this.myState.libraryRootsAndFiles.fileAndLang2pchFiles.get(s));
            if (notNullize == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/cpp/cmake/workspace/CMakeWorkspace", "getPCHFiles"));
            }
        }
        catch (NullPointerException ex2) {
            throw b(ex2);
        }
        return (List<VirtualFile>)notNullize;
    }
    
    @NotNull
    OCIncludeMap getIncludeMap(@NotNull final CMakeResolveConfiguration cMakeResolveConfiguration) {
        try {
            if (cMakeResolveConfiguration == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "config", "com/jetbrains/cidr/cpp/cmake/workspace/CMakeWorkspace", "getIncludeMap"));
            }
        }
        catch (NullPointerException ex) {
            throw b(ex);
        }
        final PrecompiledIncludeMap precompiledIncludeMap = this.myState.libraryRootsAndFiles.config2includeMap.get(cMakeResolveConfiguration.getUniqueName());
        OCIncludeMap empty = null;
        Label_0118: {
            PrecompiledIncludeMap precompiledIncludeMap2 = null;
            Label_0083: {
                try {
                    if (precompiledIncludeMap == null) {
                        break Label_0118;
                    }
                    precompiledIncludeMap2 = precompiledIncludeMap;
                    if (precompiledIncludeMap2 == null) {
                        break Label_0083;
                    }
                    return precompiledIncludeMap2;
                }
                catch (NullPointerException ex2) {
                    throw b(ex2);
                }
                try {
                    precompiledIncludeMap2 = precompiledIncludeMap;
                    if (precompiledIncludeMap2 == null) {
                        throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/cpp/cmake/workspace/CMakeWorkspace", "getIncludeMap"));
                    }
                }
                catch (NullPointerException ex3) {
                    throw b(ex3);
                }
            }
            return precompiledIncludeMap2;
            try {
                empty = OCIncludeMap.EMPTY;
                if (empty == null) {
                    throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/cpp/cmake/workspace/CMakeWorkspace", "getIncludeMap"));
                }
            }
            catch (NullPointerException ex4) {
                throw b(ex4);
            }
        }
        return empty;
    }
    
    @NotNull
    @Override
    public List<CMakeResolveConfiguration> getConfigurationsForFile(@Nullable final VirtualFile virtualFile) {
        Label_0053: {
            List<CMakeResolveConfiguration> list = null;
            Label_0018: {
                try {
                    if (virtualFile != null) {
                        break Label_0053;
                    }
                    list = Collections.emptyList();
                    if (list == null) {
                        break Label_0018;
                    }
                    return list;
                }
                catch (NullPointerException ex) {
                    throw b(ex);
                }
                try {
                    list = Collections.emptyList();
                    if (list == null) {
                        throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/cpp/cmake/workspace/CMakeWorkspace", "getConfigurationsForFile"));
                    }
                }
                catch (NullPointerException ex2) {
                    throw b(ex2);
                }
            }
            return list;
        }
        final List<CMakeResolveConfiguration> list2 = this.myState.sourceFilesToConfigurations.get(virtualFile);
        Object o = null;
        Label_0085: {
            try {
                if (list2 == null) {
                    final Object emptyList;
                    o = (emptyList = Collections.emptyList());
                    break Label_0085;
                }
            }
            catch (NullPointerException ex3) {
                throw b(ex3);
            }
            Object emptyList;
            o = (emptyList = list2);
            try {
                if (emptyList == null) {
                    throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/cpp/cmake/workspace/CMakeWorkspace", "getConfigurationsForFile"));
                }
            }
            catch (NullPointerException ex4) {
                throw b(ex4);
            }
        }
        return (List<CMakeResolveConfiguration>)o;
    }
    
    @Nullable
    public CMakeResolveConfiguration getResolveConfigurationFor(@Nullable final CMakeConfiguration cMakeConfiguration) {
        try {
            if (cMakeConfiguration == null) {
                return null;
            }
        }
        catch (NullPointerException ex) {
            throw b(ex);
        }
        for (final CMakeResolveConfiguration cMakeResolveConfiguration : this.myState.configurations) {
            try {
                if (cMakeResolveConfiguration.getConfiguration() == cMakeConfiguration) {
                    return cMakeResolveConfiguration;
                }
                continue;
            }
            catch (NullPointerException ex2) {
                throw b(ex2);
            }
        }
        return null;
    }
    
    @NotNull
    public CMakeEnvironment getEnvironmentFor(@NotNull final CMakeConfiguration cMakeConfiguration) throws ExecutionException {
        try {
            if (cMakeConfiguration == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "configuration", "com/jetbrains/cidr/cpp/cmake/workspace/CMakeWorkspace", "getEnvironmentFor"));
            }
        }
        catch (ExecutionException ex) {
            throw b((Exception)ex);
        }
        final CMakeEnvironment cMakeEnvironment = this.myState.configEnvironments.get(cMakeConfiguration.getId());
        try {
            if (cMakeEnvironment == null) {
                throw new ExecutionException("Configuration has been deleted");
            }
        }
        catch (ExecutionException ex2) {
            throw b((Exception)ex2);
        }
        CMakeEnvironment cMakeEnvironment2;
        try {
            cMakeEnvironment2 = cMakeEnvironment;
            if (cMakeEnvironment2 == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/cpp/cmake/workspace/CMakeWorkspace", "getEnvironmentFor"));
            }
        }
        catch (ExecutionException ex3) {
            throw b((Exception)ex3);
        }
        return cMakeEnvironment2;
    }
    
    @NotNull
    public CompilerInfoCache getCompilerCache() {
        CompilerInfoCache compilerInfoCache;
        try {
            compilerInfoCache = this.myState.compilerInfoCache;
            if (compilerInfoCache == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/cpp/cmake/workspace/CMakeWorkspace", "getCompilerCache"));
            }
        }
        catch (NullPointerException ex) {
            throw b(ex);
        }
        return compilerInfoCache;
    }
    
    static {
        boolean $assertionsDisabled2 = false;
        Label_0017: {
            try {
                if (!CMakeWorkspace.class.desiredAssertionStatus()) {
                    $assertionsDisabled2 = true;
                    break Label_0017;
                }
            }
            catch (NullPointerException ex) {
                throw b(ex);
            }
            $assertionsDisabled2 = false;
        }
        $assertionsDisabled = $assertionsDisabled2;
        FORCE_RELOAD_ON_OPENING = Key.create("FORCE_RELOAD_ON_OPENING");
        CMakeWorkspace.ourReloadsCounter = new AtomicInteger(0);
        ourCheckCachesValidity = Ref.create((Object)true);
    }
    
    private static Exception b(final Exception ex) {
        return ex;
    }
    
    private static class UpdateRoots
    {
        @NotNull
        List<File> roots;
        @NotNull
        List<File> sourceFiles;
        @NotNull
        CidrRootsSynchronizer.RootsInfo rootsInfo;
        
        public UpdateRoots(@NotNull final List<File> roots, @NotNull final List<File> sourceFiles, @NotNull final CidrRootsSynchronizer.RootsInfo rootsInfo) {
            if (roots == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "roots", "com/jetbrains/cidr/cpp/cmake/workspace/CMakeWorkspace$UpdateRoots", "<init>"));
            }
            if (sourceFiles == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "sourceFiles", "com/jetbrains/cidr/cpp/cmake/workspace/CMakeWorkspace$UpdateRoots", "<init>"));
            }
            if (rootsInfo == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "rootsInfo", "com/jetbrains/cidr/cpp/cmake/workspace/CMakeWorkspace$UpdateRoots", "<init>"));
            }
            this.roots = roots;
            this.sourceFiles = sourceFiles;
            this.rootsInfo = rootsInfo;
        }
    }
    
    private static class LibraryRootsAndFiles
    {
        @NotNull
        final List<File> systemHeaderRoots;
        @NotNull
        final List<File> userHeaderRoots;
        @NotNull
        final List<File> headerExcludeRoots;
        @NotNull
        final List<VirtualFile> filesToIndex;
        @NotNull
        final Map<String, Set<VirtualFile>> config2pchFiles;
        @NotNull
        final Map<String, List<VirtualFile>> fileAndLang2pchFiles;
        @NotNull
        final Map<String, PrecompiledIncludeMap> config2includeMap;
        
        @NotNull
        public static LibraryRootsAndFiles empty() {
            LibraryRootsAndFiles libraryRootsAndFiles;
            try {
                libraryRootsAndFiles = new LibraryRootsAndFiles(Collections.emptyList(), Collections.emptyList(), Collections.emptyList(), Collections.emptyList(), Collections.emptyMap(), Collections.emptyMap(), Collections.emptyMap());
                if (libraryRootsAndFiles == null) {
                    throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/cpp/cmake/workspace/CMakeWorkspace$LibraryRootsAndFiles", "empty"));
                }
            }
            catch (IllegalStateException ex) {
                throw a(ex);
            }
            return libraryRootsAndFiles;
        }
        
        public LibraryRootsAndFiles(@NotNull final List<File> list, @NotNull final List<File> list2, @NotNull final List<File> list3, @NotNull final List<VirtualFile> list4, @NotNull final Map<String, Set<VirtualFile>> map, @NotNull final Map<String, List<VirtualFile>> map2, @NotNull final Map<String, PrecompiledIncludeMap> map3) {
            if (list == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "systemHeaderRoots", "com/jetbrains/cidr/cpp/cmake/workspace/CMakeWorkspace$LibraryRootsAndFiles", "<init>"));
            }
            if (list2 == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "userHeaderRoots", "com/jetbrains/cidr/cpp/cmake/workspace/CMakeWorkspace$LibraryRootsAndFiles", "<init>"));
            }
            if (list3 == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "headerExcludeRoots", "com/jetbrains/cidr/cpp/cmake/workspace/CMakeWorkspace$LibraryRootsAndFiles", "<init>"));
            }
            if (list4 == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "filesToIndex", "com/jetbrains/cidr/cpp/cmake/workspace/CMakeWorkspace$LibraryRootsAndFiles", "<init>"));
            }
            if (map == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "config2pchFiles", "com/jetbrains/cidr/cpp/cmake/workspace/CMakeWorkspace$LibraryRootsAndFiles", "<init>"));
            }
            if (map2 == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "fileAndLang2pchFiles", "com/jetbrains/cidr/cpp/cmake/workspace/CMakeWorkspace$LibraryRootsAndFiles", "<init>"));
            }
            if (map3 == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "config2includeMap", "com/jetbrains/cidr/cpp/cmake/workspace/CMakeWorkspace$LibraryRootsAndFiles", "<init>"));
            }
            this.systemHeaderRoots = Collections.unmodifiableList((List<? extends File>)list);
            this.userHeaderRoots = Collections.unmodifiableList((List<? extends File>)list2);
            this.headerExcludeRoots = Collections.unmodifiableList((List<? extends File>)list3);
            this.filesToIndex = Collections.unmodifiableList((List<? extends VirtualFile>)list4);
            this.config2pchFiles = Collections.unmodifiableMap((Map<? extends String, ? extends Set<VirtualFile>>)map);
            this.fileAndLang2pchFiles = Collections.unmodifiableMap((Map<? extends String, ? extends List<VirtualFile>>)map2);
            this.config2includeMap = Collections.unmodifiableMap((Map<? extends String, ? extends PrecompiledIncludeMap>)map3);
        }
        
        private static IllegalStateException a(final IllegalStateException ex) {
            return ex;
        }
    }
    
    private static class IOLibraryRootsAndFiles
    {
        List<File> systemHeaderRoots;
        List<File> userHeaderRoots;
        List<File> headerExcludeRoots;
        List<File> filesToIndex;
        Map<String, List<File>> config2pchFiles;
        Map<String, List<File>> fileAndLang2pchFiles;
        Map<String, Map<Pair<String, File>, File>> config2includeMap;
    }
    
    private static class State
    {
        @Nullable
        final File projectDir;
        final long settingsHash;
        @Nullable
        final CMakeModel model;
        @NotNull
        final Map<Integer, CMakeEnvironment> configEnvironments;
        @NotNull
        final Map<File, FileStamp> cmakeFilesWithStamps;
        @NotNull
        final List<File> sourceFiles;
        @NotNull
        final List<File> projectResourceFiles;
        @NotNull
        final LibraryRootsAndFiles libraryRootsAndFiles;
        @NotNull
        final Map<VirtualFile, List<CMakeResolveConfiguration>> sourceFilesToConfigurations;
        @NotNull
        final List<CMakeResolveConfiguration> configurations;
        @NotNull
        final CompilerInfoCache compilerInfoCache;
        
        public State(@Nullable final File file, final long n, @NotNull final Map<File, FileStamp> map) {
            if (map == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "cmakeFilesWithStamps", "com/jetbrains/cidr/cpp/cmake/workspace/CMakeWorkspace$State", "<init>"));
            }
            this(file, n, null, Collections.emptyMap(), map, Collections.emptyList(), Collections.emptyList(), LibraryRootsAndFiles.empty(), Collections.emptyMap(), Collections.emptyList(), new CompilerInfoCache());
        }
        
        private State(@Nullable final File projectDir, final long settingsHash, @Nullable final CMakeModel model, @NotNull final Map<Integer, CMakeEnvironment> map, @NotNull final Map<File, FileStamp> map2, @NotNull final List<File> list, @NotNull final List<File> list2, @NotNull final LibraryRootsAndFiles libraryRootsAndFiles, @NotNull final Map<VirtualFile, List<CMakeResolveConfiguration>> map3, @NotNull final List<CMakeResolveConfiguration> list3, @NotNull final CompilerInfoCache compilerInfoCache) {
            if (map == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "configEnvironments", "com/jetbrains/cidr/cpp/cmake/workspace/CMakeWorkspace$State", "<init>"));
            }
            if (map2 == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "cmakeFilesWithStamps", "com/jetbrains/cidr/cpp/cmake/workspace/CMakeWorkspace$State", "<init>"));
            }
            if (list == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "sourceFiles", "com/jetbrains/cidr/cpp/cmake/workspace/CMakeWorkspace$State", "<init>"));
            }
            if (list2 == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "projectResourceFiles", "com/jetbrains/cidr/cpp/cmake/workspace/CMakeWorkspace$State", "<init>"));
            }
            if (libraryRootsAndFiles == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "libraryRootsAndFiles", "com/jetbrains/cidr/cpp/cmake/workspace/CMakeWorkspace$State", "<init>"));
            }
            if (map3 == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "sourceFilesToConfigurations", "com/jetbrains/cidr/cpp/cmake/workspace/CMakeWorkspace$State", "<init>"));
            }
            if (list3 == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "configurations", "com/jetbrains/cidr/cpp/cmake/workspace/CMakeWorkspace$State", "<init>"));
            }
            if (compilerInfoCache == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "compilerInfoCache", "com/jetbrains/cidr/cpp/cmake/workspace/CMakeWorkspace$State", "<init>"));
            }
            this.projectDir = projectDir;
            this.settingsHash = settingsHash;
            this.model = model;
            this.configEnvironments = Collections.unmodifiableMap((Map<? extends Integer, ? extends CMakeEnvironment>)map);
            final Logger log = CPPLog.LOG;
            while (true) {
                if (projectDir != null) {
                    boolean b = false;
                    Label_0393: {
                        try {
                            if (map2.containsKey(new File(projectDir, "CMakeLists.txt"))) {
                                b = true;
                                break Label_0393;
                            }
                        }
                        catch (IllegalArgumentException ex) {
                            throw a(ex);
                        }
                        b = false;
                    }
                    log.assertTrue(b);
                    this.cmakeFilesWithStamps = Collections.unmodifiableMap((Map<? extends File, ? extends FileStamp>)map2);
                    this.sourceFiles = Collections.unmodifiableList((List<? extends File>)list);
                    this.projectResourceFiles = Collections.unmodifiableList((List<? extends File>)list2);
                    this.libraryRootsAndFiles = libraryRootsAndFiles;
                    this.sourceFilesToConfigurations = Collections.unmodifiableMap((Map<? extends VirtualFile, ? extends List<CMakeResolveConfiguration>>)map3);
                    this.configurations = Collections.unmodifiableList((List<? extends CMakeResolveConfiguration>)list3);
                    this.compilerInfoCache = compilerInfoCache;
                    return;
                }
                continue;
            }
        }
        
        @NotNull
        private List<File> a() {
            List<File> emptyList = null;
            Label_0068: {
                List list2 = null;
                Label_0033: {
                    try {
                        if (this.model == null) {
                            break Label_0068;
                        }
                        final State state = this;
                        final CMakeModel cMakeModel = state.model;
                        final List<CMakeModelConfigurationData> list = cMakeModel.getConfigurationData();
                        final Function function = cMakeModelConfigurationData -> cMakeModelConfigurationData.getGenerationDir();
                        list2 = ContainerUtil.map((Collection)list, function);
                        if (list2 == null) {
                            break Label_0033;
                        }
                        return (List<File>)list2;
                    }
                    catch (IllegalArgumentException ex) {
                        throw a(ex);
                    }
                    try {
                        final State state = this;
                        final CMakeModel cMakeModel = state.model;
                        final List<CMakeModelConfigurationData> list = cMakeModel.getConfigurationData();
                        final Function function = cMakeModelConfigurationData -> cMakeModelConfigurationData.getGenerationDir();
                        list2 = ContainerUtil.map((Collection)list, function);
                        if (list2 == null) {
                            throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/cpp/cmake/workspace/CMakeWorkspace$State", "getAllGenerationDirs"));
                        }
                    }
                    catch (IllegalArgumentException ex2) {
                        throw a(ex2);
                    }
                }
                return (List<File>)list2;
                try {
                    emptyList = Collections.emptyList();
                    if (emptyList == null) {
                        throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/cpp/cmake/workspace/CMakeWorkspace$State", "getAllGenerationDirs"));
                    }
                }
                catch (IllegalArgumentException ex3) {
                    throw a(ex3);
                }
            }
            return emptyList;
        }
        
        private static IllegalArgumentException a(final IllegalArgumentException ex) {
            return ex;
        }
    }
    
    private static class MyRootsSynchronizer extends CidrRootsSynchronizer
    {
        public MyRootsSynchronizer(@NotNull final Project project) {
            if (project == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "project", "com/jetbrains/cidr/cpp/cmake/workspace/CMakeWorkspace$MyRootsSynchronizer", "<init>"));
            }
            super(project, i());
        }
    }
    
    interface BalloonNotifier
    {
        void notify(@NotNull final MessageType p0, @NotNull final String p1, @NotNull final HyperlinkListener p2);
    }
}
