// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.lang.symbols.symtable;

import com.intellij.ide.util.DelegatingProgressIndicator;
import com.jetbrains.cidr.lang.OCLanguageKind;
import com.intellij.psi.PsiFile;
import com.jetbrains.cidr.lang.CLanguageKind;
import com.jetbrains.cidr.lang.psi.OCConfigurationOwner;
import com.intellij.psi.PsiManager;
import java.util.Collections;
import com.intellij.util.containers.MultiMap;
import com.intellij.testFramework.LightVirtualFile;
import com.jetbrains.cidr.modulemap.symbols.ModuleMapCacheBuilder;
import com.intellij.openapi.diagnostic.Logger;
import com.intellij.openapi.util.Disposer;
import com.intellij.openapi.Disposable;
import com.intellij.openapi.project.DumbModeTask;
import com.intellij.openapi.application.Application;
import java.util.concurrent.Semaphore;
import com.intellij.openapi.application.ApplicationListener;
import com.intellij.openapi.application.ApplicationManager;
import com.intellij.openapi.application.ApplicationAdapter;
import com.intellij.openapi.progress.ProgressManager;
import com.intellij.concurrency.SensitiveProgressWrapper;
import com.intellij.util.Consumer;
import com.intellij.util.Producer;
import java.util.concurrent.ExecutionException;
import com.intellij.openapi.util.Condition;
import com.intellij.openapi.util.Ref;
import com.jetbrains.cidr.lang.preprocessor.OCInclusionContextUtil;
import com.jetbrains.cidr.lang.preprocessor.OCImportGraph;
import com.jetbrains.cidr.lang.preprocessor.OCInclusionContext;
import com.jetbrains.cidr.lang.workspace.OCResolveConfiguration;
import com.jetbrains.cidr.lang.workspace.OCWorkspaceManager;
import java.util.Iterator;
import com.intellij.util.containers.HashSet;
import com.intellij.util.containers.ContainerUtil;
import java.util.ArrayList;
import com.intellij.openapi.application.ex.ApplicationManagerEx;
import java.util.concurrent.Future;
import com.intellij.openapi.application.ReadAction;
import com.jetbrains.cidr.modulemap.symbols.ModuleMapCache;
import com.intellij.openapi.vfs.VirtualFile;
import com.intellij.openapi.fileTypes.FileType;
import com.intellij.util.Processor;
import com.intellij.openapi.project.DumbService;
import com.jetbrains.cidr.lang.OCLog;
import java.util.Collection;
import com.intellij.openapi.util.text.StringUtil;
import com.intellij.openapi.util.UserDataHolder;
import com.intellij.openapi.progress.ProcessCanceledException;
import com.intellij.openapi.components.ServiceManager;
import java.util.concurrent.atomic.AtomicInteger;
import com.intellij.openapi.util.SimpleModificationTracker;
import com.intellij.openapi.project.Project;
import java.util.List;
import com.intellij.openapi.util.NotNullLazyKey;
import org.jetbrains.annotations.NotNull;
import java.util.Set;
import org.jetbrains.annotations.Nullable;
import com.intellij.openapi.progress.ProgressIndicator;
import com.intellij.util.NotNullProducer;

public class OCSymbolTablesBuildingActivity
{
    @Nullable
    private static NotNullProducer<ProgressIndicator> ourIndicatorFactory;
    @NotNull
    private static final Set<Thread> mySymbolBuildingThreads;
    public static final NotNullLazyKey<List<String>, Project> ACTIVITY_LOG;
    @NotNull
    private final Project project;
    private final SimpleModificationTracker myModificationTracker;
    @NotNull
    private final AtomicInteger ourRequestCount;
    private volatile int ourLastBuild;
    
    public static OCSymbolTablesBuildingActivity getInstance(final Project project) {
        return (OCSymbolTablesBuildingActivity)ServiceManager.getService(project, (Class)OCSymbolTablesBuildingActivity.class);
    }
    
    public OCSymbolTablesBuildingActivity(@NotNull final Project project) {
        if (project == null) {
            throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "project", "com/jetbrains/cidr/lang/symbols/symtable/OCSymbolTablesBuildingActivity", "<init>"));
        }
        this.myModificationTracker = new SimpleModificationTracker();
        this.ourRequestCount = new AtomicInteger();
        this.project = project;
    }
    
    @NotNull
    public SimpleModificationTracker getModificationTracker() {
        SimpleModificationTracker myModificationTracker;
        try {
            myModificationTracker = this.myModificationTracker;
            if (myModificationTracker == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/symbols/symtable/OCSymbolTablesBuildingActivity", "getModificationTracker"));
            }
        }
        catch (ProcessCanceledException ex) {
            throw b((Exception)ex);
        }
        return myModificationTracker;
    }
    
    public void assertParsingAndSymbolBuildingAllowed() {
        this.assertParsingAndSymbolBuildingAllowed(Thread.currentThread());
    }
    
    void assertParsingAndSymbolBuildingAllowed(final Thread thread) {
        boolean b2 = false;
        Label_0038: {
            Label_0029: {
                try {
                    if (FileSymbolTablesCache.areSymbolsLoaded(this.project)) {
                        break Label_0029;
                    }
                    final Set<Thread> set = OCSymbolTablesBuildingActivity.mySymbolBuildingThreads;
                    final Thread thread2 = thread;
                    final boolean b = set.contains(thread2);
                    if (b) {
                        break Label_0029;
                    }
                    break Label_0029;
                }
                catch (ProcessCanceledException ex) {
                    throw b((Exception)ex);
                }
                try {
                    final Set<Thread> set = OCSymbolTablesBuildingActivity.mySymbolBuildingThreads;
                    final Thread thread2 = thread;
                    final boolean b = set.contains(thread2);
                    if (b) {
                        b2 = true;
                        break Label_0038;
                    }
                }
                catch (ProcessCanceledException ex2) {
                    throw b((Exception)ex2);
                }
            }
            b2 = false;
        }
        if (!b2) {
            final List list = (List)OCSymbolTablesBuildingActivity.ACTIVITY_LOG.getValue((UserDataHolder)this.project);
            final String join;
            synchronized (list) {
                join = StringUtil.join((Collection)list, "\n");
            }
            OCLog.LOG.error("Symbol building is not allowed: " + System.currentTimeMillis() + " . \nLoaded: " + FileSymbolTablesCache.areSymbolsLoaded(this.project) + "\nDumb: " + DumbService.isDumb(this.project) + "\nPrevious activities: \n" + join);
        }
    }
    
    public static void setIndicatorFactory(@Nullable final NotNullProducer<ProgressIndicator> ourIndicatorFactory) {
        OCSymbolTablesBuildingActivity.ourIndicatorFactory = ourIndicatorFactory;
    }
    
    public void rebuildSymbols() {
        this.rebuildSymbols(Mode.FAST);
    }
    
    public void rebuildSymbols(@NotNull final Mode mode) {
        try {
            if (mode == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "mode", "com/jetbrains/cidr/lang/symbols/symtable/OCSymbolTablesBuildingActivity", "rebuildSymbols"));
            }
        }
        catch (ProcessCanceledException ex) {
            throw b((Exception)ex);
        }
        this.a("rebuildSymbols", (Processor<MyProgressIndicator>)(myProgressIndicator -> {
            try {
                if (mode == null) {
                    throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "mode", "com/jetbrains/cidr/lang/symbols/symtable/OCSymbolTablesBuildingActivity", "lambda$rebuildSymbols$1"));
                }
            }
            catch (ProcessCanceledException ex) {
                throw b((Exception)ex);
            }
            try {
                if (n <= this.ourLastBuild) {
                    return false;
                }
            }
            catch (ProcessCanceledException ex2) {
                throw b((Exception)ex2);
            }
            this.ourLastBuild = this.ourRequestCount.get();
            this.a(myProgressIndicator, mode);
            return true;
        }));
    }
    
    public void rebuildSwiftModules(final FileType fileType) {
        this.a("rebuildSwiftModules", (Processor<MyProgressIndicator>)(myProgressIndicator -> {
            this.b(ContainerUtil.createMaybeSingletonList((Object)new LightVirtualFile("", fileType, (CharSequence)"")), myProgressIndicator);
            return true;
        }));
    }
    
    public void buildSymbolsForFiles(@NotNull final Collection<VirtualFile> collection) {
        try {
            if (collection == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "files", "com/jetbrains/cidr/lang/symbols/symtable/OCSymbolTablesBuildingActivity", "buildSymbolsForFiles"));
            }
        }
        catch (ProcessCanceledException ex) {
            throw b((Exception)ex);
        }
        this.a("buildSymbolsForFiles", (Processor<MyProgressIndicator>)(myProgressIndicator -> {
            try {
                if (collection == null) {
                    throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "files", "com/jetbrains/cidr/lang/symbols/symtable/OCSymbolTablesBuildingActivity", "lambda$buildSymbolsForFiles$3"));
                }
            }
            catch (ProcessCanceledException ex) {
                throw b((Exception)ex);
            }
            this.a(myProgressIndicator, collection);
            return true;
        }));
    }
    
    public void rebuildModuleMaps() {
        try {
            if (!ModuleMapCache.shouldBuildCache()) {
                return;
            }
        }
        catch (ProcessCanceledException ex) {
            throw b((Exception)ex);
        }
        this.a("rebuildModuleMaps", (Processor<MyProgressIndicator>)(myProgressIndicator -> {
            this.c(myProgressIndicator);
            return true;
        }));
    }
    
    private void c(@NotNull final MyProgressIndicator myProgressIndicator) {
        try {
            if (myProgressIndicator == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "indicator", "com/jetbrains/cidr/lang/symbols/symtable/OCSymbolTablesBuildingActivity", "buildModuleMapsInternal"));
            }
        }
        catch (ProcessCanceledException ex) {
            throw b((Exception)ex);
        }
        ModuleMapCache.getInstance(this.project).reset();
        final TaskProvider taskProvider = (TaskProvider)ReadAction.compute(() -> {
            try {
                if (myProgressIndicator == null) {
                    throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "indicator", "com/jetbrains/cidr/lang/symbols/symtable/OCSymbolTablesBuildingActivity", "lambda$buildModuleMapsInternal$5"));
                }
            }
            catch (RuntimeException ex) {
                throw b(ex);
            }
            return ModuleMapCacheBuilder.getInstance(this.project).getTask((ProgressIndicator)myProgressIndicator);
        });
        try {
            if (taskProvider == null) {
                return;
            }
        }
        catch (ProcessCanceledException ex2) {
            throw b((Exception)ex2);
        }
        myProgressIndicator.setText("Building Module Maps...");
        myProgressIndicator.startTiming("Building Module Maps");
        myProgressIndicator.setIndeterminate(false);
        myProgressIndicator.setInterval(0.0, 1.0);
        myProgressIndicator.setFraction(0.0);
        a(a((TaskProvider<Object>)taskProvider, (ProgressIndicator)myProgressIndicator));
        myProgressIndicator.logTiming();
    }
    
    private void a(@NotNull final String p0, @NotNull final Processor<MyProgressIndicator> p1) {
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
        //    18: ldc             "activityName"
        //    20: aastore        
        //    21: dup            
        //    22: ldc             1
        //    24: ldc             "com/jetbrains/cidr/lang/symbols/symtable/OCSymbolTablesBuildingActivity"
        //    26: aastore        
        //    27: dup            
        //    28: ldc             2
        //    30: ldc             "runSymbolActivity"
        //    32: aastore        
        //    33: invokestatic    java/lang/String.format:(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
        //    36: invokespecial   java/lang/IllegalArgumentException.<init>:(Ljava/lang/String;)V
        //    39: athrow         
        //    40: invokestatic    com/jetbrains/cidr/lang/symbols/symtable/OCSymbolTablesBuildingActivity.b:(Ljava/lang/Exception;)Ljava/lang/Exception;
        //    43: athrow         
        //    44: aload_2        
        //    45: ifnonnull       88
        //    48: new             Ljava/lang/IllegalArgumentException;
        //    51: dup            
        //    52: ldc             "Argument for @NotNull parameter '%s' of %s.%s must not be null"
        //    54: ldc             3
        //    56: anewarray       Ljava/lang/Object;
        //    59: dup            
        //    60: ldc             0
        //    62: ldc             "activity"
        //    64: aastore        
        //    65: dup            
        //    66: ldc             1
        //    68: ldc             "com/jetbrains/cidr/lang/symbols/symtable/OCSymbolTablesBuildingActivity"
        //    70: aastore        
        //    71: dup            
        //    72: ldc             2
        //    74: ldc             "runSymbolActivity"
        //    76: aastore        
        //    77: invokestatic    java/lang/String.format:(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
        //    80: invokespecial   java/lang/IllegalArgumentException.<init>:(Ljava/lang/String;)V
        //    83: athrow         
        //    84: invokestatic    com/jetbrains/cidr/lang/symbols/symtable/OCSymbolTablesBuildingActivity.b:(Ljava/lang/Exception;)Ljava/lang/Exception;
        //    87: athrow         
        //    88: aload_0        
        //    89: aload_1        
        //    90: aload_2        
        //    91: invokedynamic   run:(Lcom/jetbrains/cidr/lang/symbols/symtable/OCSymbolTablesBuildingActivity;Ljava/lang/String;Lcom/intellij/util/Processor;)Ljava/lang/Runnable;
        //    96: astore_3       
        //    97: invokestatic    com/intellij/openapi/application/ex/ApplicationManagerEx.getApplicationEx:()Lcom/intellij/openapi/application/ex/ApplicationEx;
        //   100: astore          4
        //   102: aload           4
        //   104: invokeinterface com/intellij/openapi/application/ex/ApplicationEx.isUnitTestMode:()Z
        //   109: ifeq            154
        //   112: aload           4
        //   114: invokeinterface com/intellij/openapi/application/ex/ApplicationEx.isWriteAccessAllowed:()Z
        //   119: ifeq            154
        //   122: goto            129
        //   125: invokestatic    com/jetbrains/cidr/lang/symbols/symtable/OCSymbolTablesBuildingActivity.b:(Ljava/lang/Exception;)Ljava/lang/Exception;
        //   128: athrow         
        //   129: new             Lcom/jetbrains/cidr/lang/symbols/symtable/OCSymbolTablesBuildingActivity$2;
        //   132: dup            
        //   133: aload_0        
        //   134: aload           4
        //   136: aload_3        
        //   137: invokespecial   com/jetbrains/cidr/lang/symbols/symtable/OCSymbolTablesBuildingActivity$2.<init>:(Lcom/jetbrains/cidr/lang/symbols/symtable/OCSymbolTablesBuildingActivity;Lcom/intellij/openapi/application/ex/ApplicationEx;Ljava/lang/Runnable;)V
        //   140: astore          5
        //   142: aload           4
        //   144: aload           5
        //   146: invokeinterface com/intellij/openapi/application/ex/ApplicationEx.addApplicationListener:(Lcom/intellij/openapi/application/ApplicationListener;)V
        //   151: goto            160
        //   154: aload_3        
        //   155: invokeinterface java/lang/Runnable.run:()V
        //   160: return         
        //    Signature:
        //  (Ljava/lang/String;Lcom/intellij/util/Processor<Lcom/jetbrains/cidr/lang/symbols/symtable/OCSymbolTablesBuildingActivity$MyProgressIndicator;>;)V
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                                                    
        //  -----  -----  -----  -----  --------------------------------------------------------
        //  0      40     40     44     Lcom/intellij/openapi/progress/ProcessCanceledException;
        //  44     84     84     88     Lcom/intellij/openapi/progress/ProcessCanceledException;
        //  102    122    125    129    Lcom/intellij/openapi/progress/ProcessCanceledException;
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
    
    private void a(@NotNull final MyProgressIndicator myProgressIndicator, @NotNull final Collection<VirtualFile> collection) {
        try {
            if (myProgressIndicator == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "indicator", "com/jetbrains/cidr/lang/symbols/symtable/OCSymbolTablesBuildingActivity", "buildSymbolsForFilesInternal"));
            }
        }
        catch (ProcessCanceledException ex) {
            throw b((Exception)ex);
        }
        try {
            if (collection == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "sourceFiles", "com/jetbrains/cidr/lang/symbols/symtable/OCSymbolTablesBuildingActivity", "buildSymbolsForFilesInternal"));
            }
        }
        catch (ProcessCanceledException ex2) {
            throw b((Exception)ex2);
        }
        final FileSymbolTablesCache instance = FileSymbolTablesCache.getInstance(this.project);
        try {
            if (!instance.shouldBuildTables()) {
                return;
            }
        }
        catch (ProcessCanceledException ex3) {
            throw b((Exception)ex3);
        }
        final IllegalArgumentException ex4;
        final FileSymbolTablesCache fileSymbolTablesCache;
        a((ProgressIndicator)myProgressIndicator, () -> {
            try {
                if (myProgressIndicator == null) {
                    new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "indicator", "com/jetbrains/cidr/lang/symbols/symtable/OCSymbolTablesBuildingActivity", "lambda$buildSymbolsForFilesInternal$7"));
                    throw ex4;
                }
            }
            catch (ProcessCanceledException ex5) {
                throw b((Exception)ex5);
            }
            try {
                if (myProgressIndicator.isCanceled()) {
                    return;
                }
            }
            catch (ProcessCanceledException ex6) {
                throw b((Exception)ex6);
            }
            fileSymbolTablesCache.notifySymbolsUnloaded();
            fileSymbolTablesCache.reparseCachedPsiFiles();
            return;
        });
        myProgressIndicator.checkCanceled();
        myProgressIndicator.setText("Updating symbols...");
        myProgressIndicator.setIndeterminate(false);
        myProgressIndicator.setFraction(0.0);
        myProgressIndicator.setInterval(myProgressIndicator.getFraction(), 1.0);
        new OCSymbolTableBuilder(this.project, (ProgressIndicator)myProgressIndicator, collection).processBuildFiles();
        this.a(instance, myProgressIndicator);
    }
    
    private void a(@NotNull final MyProgressIndicator myProgressIndicator, @NotNull final Mode mode) {
        try {
            if (myProgressIndicator == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "indicator", "com/jetbrains/cidr/lang/symbols/symtable/OCSymbolTablesBuildingActivity", "buildSymbolsInternal"));
            }
        }
        catch (ProcessCanceledException ex) {
            throw b((Exception)ex);
        }
        try {
            if (mode == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "mode", "com/jetbrains/cidr/lang/symbols/symtable/OCSymbolTablesBuildingActivity", "buildSymbolsInternal"));
            }
        }
        catch (ProcessCanceledException ex2) {
            throw b((Exception)ex2);
        }
        final FileSymbolTablesCache instance = FileSymbolTablesCache.getInstance(this.project);
        try {
            if (!instance.shouldBuildTables()) {
                return;
            }
        }
        catch (ProcessCanceledException ex3) {
            throw b((Exception)ex3);
        }
        try {
            if (ApplicationManagerEx.getApplicationEx().isWriteAccessAllowed()) {
                OCLog.LOG.error("Symbols building must not be initiated from write action, otherwise deadlock will occur");
                return;
            }
        }
        catch (ProcessCanceledException ex4) {
            throw b((Exception)ex4);
        }
        myProgressIndicator.checkCanceled();
        myProgressIndicator.setText("Building symbols...");
        myProgressIndicator.setText2("");
        myProgressIndicator.setIndeterminate(true);
        myProgressIndicator.startTiming("Clearing symbols");
        final String[] array = { null };
        final IllegalArgumentException ex5;
        final FileSymbolTablesCache fileSymbolTablesCache;
        final Object o;
        a((ProgressIndicator)myProgressIndicator, () -> {
            try {
                if (myProgressIndicator == null) {
                    new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "indicator", "com/jetbrains/cidr/lang/symbols/symtable/OCSymbolTablesBuildingActivity", "lambda$buildSymbolsInternal$8"));
                    throw ex5;
                }
            }
            catch (ProcessCanceledException ex6) {
                throw b((Exception)ex6);
            }
            try {
                if (myProgressIndicator.isCanceled()) {
                    return;
                }
            }
            catch (ProcessCanceledException ex7) {
                throw b((Exception)ex7);
            }
            fileSymbolTablesCache.notifySymbolsUnloaded();
            fileSymbolTablesCache.clearAllTables();
            fileSymbolTablesCache.reparseCachedPsiFiles();
            this.a();
            o[0] = this.project.getLocationHash();
            return;
        });
        myProgressIndicator.logTiming();
        myProgressIndicator.checkCanceled();
        myProgressIndicator.setText("Loading symbols...");
        myProgressIndicator.startTiming("Loading symbols");
        myProgressIndicator.setIndeterminate(false);
        myProgressIndicator.setFraction(0.0);
        final Collection<VirtualFile> a = a((ProgressIndicator)myProgressIndicator, instance);
        final ArrayList<Object> list = (ArrayList<Object>)new ArrayList<VirtualFile>();
        b((ProgressIndicator)myProgressIndicator, () -> list.addAll(ContainerUtil.findAll((Collection)a, virtualFile -> {
            try {
                if (!OCInclusionContextUtil.isNeedToFindRoot(virtualFile, this.project)) {
                    return true;
                }
            }
            catch (ProcessCanceledException ex) {
                throw b((Exception)ex);
            }
            return false;
        })));
        OCLog.LOG.info("Building symbols in " + mode.name() + " mode, " + list.size() + " source files from total " + a.size() + " project files");
        long deserializeTables = 0L;
        if (mode != Mode.FULL) {
            deserializeTables = instance.deserializeTables(array[0], a, (ProgressIndicator)myProgressIndicator, 0.25);
        }
        final double fraction = myProgressIndicator.getFraction();
        final double n = 1.0 - 0.1 * (a.size() - deserializeTables) / Math.max(1, a.size());
        myProgressIndicator.logTiming();
        final HashSet set = new HashSet((Collection)instance.getCachedFiles());
        String text = null;
        Label_0468: {
            try {
                OCLog.LOG.info("Loaded " + a(instance) + " tables for " + ((Set)set).size() + " files (" + deserializeTables + " project files)");
                myProgressIndicator.checkCanceled();
                if (((Set)set).isEmpty()) {
                    text = "Building symbols...";
                    break Label_0468;
                }
            }
            catch (ProcessCanceledException ex8) {
                throw b((Exception)ex8);
            }
            text = "Updating symbols...";
        }
        myProgressIndicator.setText(text);
        myProgressIndicator.startTiming("Building symbols");
        final List all = ContainerUtil.findAll((Collection)list, virtualFile -> {
            try {
                if (!((Set)set).contains(virtualFile)) {
                    return true;
                }
            }
            catch (ProcessCanceledException ex) {
                throw b((Exception)ex);
            }
            return false;
        });
        List<Object> list2 = null;
        Label_0509: {
            try {
                if (mode == Mode.COMPACT) {
                    list2 = list;
                    break Label_0509;
                }
            }
            catch (ProcessCanceledException ex9) {
                throw b((Exception)ex9);
            }
            list2 = (List<Object>)all;
        }
        final List<Object> list3 = list2;
        OCLog.LOG.info("Building symbols for " + list3.size() + " source files");
        final double n2 = list3.size();
        myProgressIndicator.setInterval(fraction, fraction + (n - fraction) * (n2 / Math.max(n2 + (a.size() - list.size()) * 0.2, 1.0)));
        new OCSymbolTableBuilder(this.project, (ProgressIndicator)myProgressIndicator, (Collection<VirtualFile>)list3).processBuildFiles();
        myProgressIndicator.checkCanceled();
        this.b(a, myProgressIndicator);
        final HashSet set2 = new HashSet((Collection)a);
        Label_0691: {
            try {
                if (mode == Mode.COMPACT) {
                    ((Collection)set2).removeAll(instance.getFilesWithUsedTables());
                    break Label_0691;
                }
            }
            catch (ProcessCanceledException ex10) {
                throw b((Exception)ex10);
            }
            ((Collection)set2).removeAll(instance.getCachedFiles());
            try {
                OCLog.LOG.info("Building symbols for " + ((Collection)set2).size() + " unused headers");
                myProgressIndicator.setInterval(myProgressIndicator.getFraction(), n);
                new OCSymbolTableBuilder(this.project, (ProgressIndicator)myProgressIndicator, (Collection<VirtualFile>)set2).processBuildFiles();
                myProgressIndicator.logTiming();
                if (mode == Mode.COMPACT) {
                    instance.removeUnusedTables();
                }
            }
            catch (ProcessCanceledException ex11) {
                throw b((Exception)ex11);
            }
        }
        final HashSet set3 = new HashSet((Collection)instance.getFilesWithChangedTables());
        this.a(instance, myProgressIndicator);
        myProgressIndicator.checkCanceled();
        myProgressIndicator.setText("Saving symbols...");
        myProgressIndicator.startTiming("Saving symbols");
        myProgressIndicator.setInterval(n, 1.0);
        OCLog.LOG.info("Saving modified symbols for " + ((Set)set3).size() + " files (" + a(instance, (Set<VirtualFile>)set3) + " tables of total " + a(instance) + ")");
        instance.serializeTables(array[0], (Set<VirtualFile>)set3, (ProgressIndicator)myProgressIndicator);
        myProgressIndicator.logTiming();
        FileSymbolTable.reportStats(this.project);
    }
    
    private static int a(@NotNull final FileSymbolTablesCache fileSymbolTablesCache) {
        try {
            if (fileSymbolTablesCache == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "cache", "com/jetbrains/cidr/lang/symbols/symtable/OCSymbolTablesBuildingActivity", "getAllTablesCount"));
            }
        }
        catch (ProcessCanceledException ex) {
            throw b((Exception)ex);
        }
        return a(fileSymbolTablesCache, fileSymbolTablesCache.getCachedFiles());
    }
    
    private static int a(@NotNull final FileSymbolTablesCache fileSymbolTablesCache, @NotNull final Set<VirtualFile> set) {
        try {
            if (fileSymbolTablesCache == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "cache", "com/jetbrains/cidr/lang/symbols/symtable/OCSymbolTablesBuildingActivity", "getAllTablesCount"));
            }
        }
        catch (ProcessCanceledException ex) {
            throw b((Exception)ex);
        }
        try {
            if (set == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "files", "com/jetbrains/cidr/lang/symbols/symtable/OCSymbolTablesBuildingActivity", "getAllTablesCount"));
            }
        }
        catch (ProcessCanceledException ex2) {
            throw b((Exception)ex2);
        }
        int n = 0;
        final Iterator<VirtualFile> iterator = set.iterator();
        while (iterator.hasNext()) {
            n += fileSymbolTablesCache.allTablesForFileCount(iterator.next());
        }
        return n;
    }
    
    private void b(@NotNull final Collection<VirtualFile> collection, @NotNull final MyProgressIndicator myProgressIndicator) {
        try {
            if (collection == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "allFiles", "com/jetbrains/cidr/lang/symbols/symtable/OCSymbolTablesBuildingActivity", "processSwiftModules"));
            }
        }
        catch (ProcessCanceledException ex) {
            throw b((Exception)ex);
        }
        try {
            if (myProgressIndicator == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "indicator", "com/jetbrains/cidr/lang/symbols/symtable/OCSymbolTablesBuildingActivity", "processSwiftModules"));
            }
        }
        catch (ProcessCanceledException ex2) {
            throw b((Exception)ex2);
        }
        final ArrayList arrayList = ContainerUtil.newArrayList();
        final SymbolTableProvider[] array = (SymbolTableProvider[])SymbolTableProvider.INSTANCE.getExtensions();
        for (int length = array.length, i = 0; i < length; ++i) {
            final TaskProvider itemProviderAndWorkerForAdditionalSymbolLoading = array[i].getItemProviderAndWorkerForAdditionalSymbolLoading(this.project, (ProgressIndicator)myProgressIndicator, collection);
            try {
                if (itemProviderAndWorkerForAdditionalSymbolLoading != null) {
                    arrayList.addAll(a(itemProviderAndWorkerForAdditionalSymbolLoading, (ProgressIndicator)myProgressIndicator));
                }
            }
            catch (ProcessCanceledException ex3) {
                throw b((Exception)ex3);
            }
        }
        try {
            if (!arrayList.isEmpty()) {
                myProgressIndicator.setText("Processing Swift modules...");
                myProgressIndicator.setInterval(0.0, 1.0);
                myProgressIndicator.setFraction(0.0);
                a(arrayList);
            }
        }
        catch (ProcessCanceledException ex4) {
            throw b((Exception)ex4);
        }
    }
    
    private void a(@NotNull final FileSymbolTablesCache p0, @NotNull final MyProgressIndicator p1) {
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
        //    18: ldc             "cache"
        //    20: aastore        
        //    21: dup            
        //    22: ldc             1
        //    24: ldc             "com/jetbrains/cidr/lang/symbols/symtable/OCSymbolTablesBuildingActivity"
        //    26: aastore        
        //    27: dup            
        //    28: ldc             2
        //    30: ldc             "notifySymbolsAreLoadedAndReparseCachedFiles"
        //    32: aastore        
        //    33: invokestatic    java/lang/String.format:(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
        //    36: invokespecial   java/lang/IllegalArgumentException.<init>:(Ljava/lang/String;)V
        //    39: athrow         
        //    40: invokestatic    com/jetbrains/cidr/lang/symbols/symtable/OCSymbolTablesBuildingActivity.b:(Ljava/lang/Exception;)Ljava/lang/Exception;
        //    43: athrow         
        //    44: aload_2        
        //    45: ifnonnull       88
        //    48: new             Ljava/lang/IllegalArgumentException;
        //    51: dup            
        //    52: ldc             "Argument for @NotNull parameter '%s' of %s.%s must not be null"
        //    54: ldc             3
        //    56: anewarray       Ljava/lang/Object;
        //    59: dup            
        //    60: ldc             0
        //    62: ldc             "indicator"
        //    64: aastore        
        //    65: dup            
        //    66: ldc             1
        //    68: ldc             "com/jetbrains/cidr/lang/symbols/symtable/OCSymbolTablesBuildingActivity"
        //    70: aastore        
        //    71: dup            
        //    72: ldc             2
        //    74: ldc             "notifySymbolsAreLoadedAndReparseCachedFiles"
        //    76: aastore        
        //    77: invokestatic    java/lang/String.format:(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
        //    80: invokespecial   java/lang/IllegalArgumentException.<init>:(Ljava/lang/String;)V
        //    83: athrow         
        //    84: invokestatic    com/jetbrains/cidr/lang/symbols/symtable/OCSymbolTablesBuildingActivity.b:(Ljava/lang/Exception;)Ljava/lang/Exception;
        //    87: athrow         
        //    88: aload_2        
        //    89: invokevirtual   com/jetbrains/cidr/lang/symbols/symtable/OCSymbolTablesBuildingActivity$MyProgressIndicator.checkCanceled:()V
        //    92: aload_2        
        //    93: aload_2        
        //    94: aload_1        
        //    95: invokedynamic   run:(Lcom/jetbrains/cidr/lang/symbols/symtable/OCSymbolTablesBuildingActivity$MyProgressIndicator;Lcom/jetbrains/cidr/lang/symbols/symtable/FileSymbolTablesCache;)Ljava/lang/Runnable;
        //   100: invokestatic    com/jetbrains/cidr/lang/symbols/symtable/OCSymbolTablesBuildingActivity.b:(Lcom/intellij/openapi/progress/ProgressIndicator;Ljava/lang/Runnable;)V
        //   103: aload_2        
        //   104: invokevirtual   com/jetbrains/cidr/lang/symbols/symtable/OCSymbolTablesBuildingActivity$MyProgressIndicator.checkCanceled:()V
        //   107: aload_0        
        //   108: aload_2        
        //   109: aload_1        
        //   110: invokedynamic   run:(Lcom/jetbrains/cidr/lang/symbols/symtable/OCSymbolTablesBuildingActivity;Lcom/jetbrains/cidr/lang/symbols/symtable/OCSymbolTablesBuildingActivity$MyProgressIndicator;Lcom/jetbrains/cidr/lang/symbols/symtable/FileSymbolTablesCache;)Ljava/lang/Runnable;
        //   115: astore_3       
        //   116: invokestatic    com/intellij/openapi/application/ApplicationManager.getApplication:()Lcom/intellij/openapi/application/Application;
        //   119: invokeinterface com/intellij/openapi/application/Application.isUnitTestMode:()Z
        //   124: ifeq            141
        //   127: invokestatic    com/intellij/openapi/application/TransactionGuard.getInstance:()Lcom/intellij/openapi/application/TransactionGuard;
        //   130: aload_3        
        //   131: invokevirtual   com/intellij/openapi/application/TransactionGuard.submitTransactionAndWait:(Ljava/lang/Runnable;)V
        //   134: goto            149
        //   137: invokestatic    com/jetbrains/cidr/lang/symbols/symtable/OCSymbolTablesBuildingActivity.b:(Ljava/lang/Exception;)Ljava/lang/Exception;
        //   140: athrow         
        //   141: aload_0        
        //   142: getfield        com/jetbrains/cidr/lang/symbols/symtable/OCSymbolTablesBuildingActivity.project:Lcom/intellij/openapi/project/Project;
        //   145: aload_3        
        //   146: invokestatic    com/intellij/openapi/application/TransactionGuard.submitTransaction:(Lcom/intellij/openapi/Disposable;Ljava/lang/Runnable;)V
        //   149: return         
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                                                    
        //  -----  -----  -----  -----  --------------------------------------------------------
        //  0      40     40     44     Lcom/intellij/openapi/progress/ProcessCanceledException;
        //  44     84     84     88     Lcom/intellij/openapi/progress/ProcessCanceledException;
        //  116    137    137    141    Lcom/intellij/openapi/progress/ProcessCanceledException;
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
    
    private void a() {
        this.myModificationTracker.incModificationCount();
        for (final OCResolveConfiguration ocResolveConfiguration : OCWorkspaceManager.getWorkspace(this.project).getConfigurations()) {
            OCInclusionContext.onPrecompiledContextChange(ocResolveConfiguration);
            OCInclusionContext.clearSymbolTableConformanceCache(ocResolveConfiguration);
            OCImportGraph.invalidateRootHeadersCache(ocResolveConfiguration);
        }
        OCInclusionContextUtil.invalidateHeaderRootAndActiveConfigurationForAllExcept(null, this.project);
    }
    
    @NotNull
    private static Collection<VirtualFile> a(@NotNull final ProgressIndicator progressIndicator, @NotNull final FileSymbolTablesCache fileSymbolTablesCache) {
        try {
            if (progressIndicator == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "indicator", "com/jetbrains/cidr/lang/symbols/symtable/OCSymbolTablesBuildingActivity", "getFilesToBuildCachesForSafely"));
            }
        }
        catch (ProcessCanceledException ex) {
            throw b((Exception)ex);
        }
        try {
            if (fileSymbolTablesCache == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "cache", "com/jetbrains/cidr/lang/symbols/symtable/OCSymbolTablesBuildingActivity", "getFilesToBuildCachesForSafely"));
            }
        }
        catch (ProcessCanceledException ex2) {
            throw b((Exception)ex2);
        }
        final Ref ref = new Ref();
        Collection collection;
        try {
            final IllegalArgumentException ex3;
            final Ref ref2;
            b(progressIndicator, () -> {
                try {
                    if (fileSymbolTablesCache == null) {
                        new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "cache", "com/jetbrains/cidr/lang/symbols/symtable/OCSymbolTablesBuildingActivity", "lambda$getFilesToBuildCachesForSafely$14"));
                        throw ex3;
                    }
                }
                catch (ProcessCanceledException ex4) {
                    throw b((Exception)ex4);
                }
                ref2.set((Object)fileSymbolTablesCache.getFilesToBuildTablesFor());
                return;
            });
            collection = (Collection)ref.get();
            if (collection == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/symbols/symtable/OCSymbolTablesBuildingActivity", "getFilesToBuildCachesForSafely"));
            }
        }
        catch (ProcessCanceledException ex5) {
            throw b((Exception)ex5);
        }
        return (Collection<VirtualFile>)collection;
    }
    
    private static void b(@NotNull final ProgressIndicator progressIndicator, @NotNull final Runnable runnable) {
        try {
            if (progressIndicator == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "indicator", "com/jetbrains/cidr/lang/symbols/symtable/OCSymbolTablesBuildingActivity", "runReadActionSafely"));
            }
        }
        catch (ProcessCanceledException ex) {
            throw b((Exception)ex);
        }
        try {
            if (runnable == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "runnable", "com/jetbrains/cidr/lang/symbols/symtable/OCSymbolTablesBuildingActivity", "runReadActionSafely"));
            }
        }
        catch (ProcessCanceledException ex2) {
            throw b((Exception)ex2);
        }
        a(progressIndicator, o -> {
            try {
                if (runnable == null) {
                    throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "runnable", "com/jetbrains/cidr/lang/symbols/symtable/OCSymbolTablesBuildingActivity", "lambda$runReadActionSafely$15"));
                }
            }
            catch (ProcessCanceledException ex) {
                throw b((Exception)ex);
            }
            return ApplicationManagerEx.getApplicationEx().tryRunReadAction(runnable);
        });
    }
    
    private static void a(@NotNull final ProgressIndicator p0, @NotNull final Runnable p1) {
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
        //    18: ldc             "indicator"
        //    20: aastore        
        //    21: dup            
        //    22: ldc             1
        //    24: ldc             "com/jetbrains/cidr/lang/symbols/symtable/OCSymbolTablesBuildingActivity"
        //    26: aastore        
        //    27: dup            
        //    28: ldc             2
        //    30: ldc             "invokeAndWaitSafely"
        //    32: aastore        
        //    33: invokestatic    java/lang/String.format:(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
        //    36: invokespecial   java/lang/IllegalArgumentException.<init>:(Ljava/lang/String;)V
        //    39: athrow         
        //    40: invokestatic    com/jetbrains/cidr/lang/symbols/symtable/OCSymbolTablesBuildingActivity.b:(Ljava/lang/Exception;)Ljava/lang/Exception;
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
        //    62: ldc             "runnable"
        //    64: aastore        
        //    65: dup            
        //    66: ldc             1
        //    68: ldc             "com/jetbrains/cidr/lang/symbols/symtable/OCSymbolTablesBuildingActivity"
        //    70: aastore        
        //    71: dup            
        //    72: ldc             2
        //    74: ldc             "invokeAndWaitSafely"
        //    76: aastore        
        //    77: invokestatic    java/lang/String.format:(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
        //    80: invokespecial   java/lang/IllegalArgumentException.<init>:(Ljava/lang/String;)V
        //    83: athrow         
        //    84: invokestatic    com/jetbrains/cidr/lang/symbols/symtable/OCSymbolTablesBuildingActivity.b:(Ljava/lang/Exception;)Ljava/lang/Exception;
        //    87: athrow         
        //    88: invokestatic    com/intellij/openapi/application/ApplicationManager.getApplication:()Lcom/intellij/openapi/application/Application;
        //    91: astore_2       
        //    92: aload_2        
        //    93: invokeinterface com/intellij/openapi/application/Application.isDispatchThread:()Z
        //    98: ifeq            113
        //   101: aload_2        
        //   102: aload_1        
        //   103: invokeinterface com/intellij/openapi/application/Application.runWriteAction:(Ljava/lang/Runnable;)V
        //   108: return         
        //   109: invokestatic    com/jetbrains/cidr/lang/symbols/symtable/OCSymbolTablesBuildingActivity.b:(Ljava/lang/Exception;)Ljava/lang/Exception;
        //   112: athrow         
        //   113: new             Ljava/util/concurrent/Semaphore;
        //   116: dup            
        //   117: iconst_0       
        //   118: invokespecial   java/util/concurrent/Semaphore.<init>:(I)V
        //   121: astore_3       
        //   122: aload_2        
        //   123: aload_2        
        //   124: aload_1        
        //   125: aload_3        
        //   126: invokedynamic   run:(Lcom/intellij/openapi/application/Application;Ljava/lang/Runnable;Ljava/util/concurrent/Semaphore;)Ljava/lang/Runnable;
        //   131: invokestatic    com/intellij/openapi/application/TransactionGuard.submitTransaction:(Lcom/intellij/openapi/Disposable;Ljava/lang/Runnable;)V
        //   134: aload_0        
        //   135: aload_3        
        //   136: invokedynamic   value:(Ljava/util/concurrent/Semaphore;)Lcom/intellij/openapi/util/Condition;
        //   141: invokestatic    com/jetbrains/cidr/lang/symbols/symtable/OCSymbolTablesBuildingActivity.a:(Lcom/intellij/openapi/progress/ProgressIndicator;Lcom/intellij/openapi/util/Condition;)V
        //   144: return         
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                                                    
        //  -----  -----  -----  -----  --------------------------------------------------------
        //  0      40     40     44     Lcom/intellij/openapi/progress/ProcessCanceledException;
        //  44     84     84     88     Lcom/intellij/openapi/progress/ProcessCanceledException;
        //  92     109    109    113    Lcom/intellij/openapi/progress/ProcessCanceledException;
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
    
    private static void a(@NotNull final ProgressIndicator progressIndicator, @NotNull final Condition condition) {
        try {
            if (progressIndicator == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "indicator", "com/jetbrains/cidr/lang/symbols/symtable/OCSymbolTablesBuildingActivity", "waitForCondition"));
            }
        }
        catch (InterruptedException ex) {
            throw b(ex);
        }
        try {
            if (condition == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "condition", "com/jetbrains/cidr/lang/symbols/symtable/OCSymbolTablesBuildingActivity", "waitForCondition"));
            }
        }
        catch (InterruptedException ex2) {
            throw b(ex2);
        }
        long min = 1L;
        while (true) {
            try {
                progressIndicator.checkCanceled();
                if (condition.value((Object)null)) {
                    break;
                }
            }
            catch (InterruptedException ex3) {
                throw b(ex3);
            }
            try {
                Thread.sleep(min);
                min = Math.min(min * 2L, 100L);
                continue;
            }
            catch (InterruptedException ex4) {
                throw new ProcessCanceledException();
            }
            break;
        }
    }
    
    private static void a(@NotNull final Iterable<Future<?>> iterable) {
        try {
            if (iterable == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "tasks", "com/jetbrains/cidr/lang/symbols/symtable/OCSymbolTablesBuildingActivity", "processFutures"));
            }
        }
        catch (InterruptedException ex) {
            throw b(ex);
        }
        for (final Future<?> future : iterable) {
            try {
                future.get();
            }
            catch (InterruptedException ex3) {}
            catch (ExecutionException ex2) {
                OCLog.LOG.error(ex2.getMessage());
            }
        }
    }
    
    @NotNull
    private static <T> List<Future<?>> a(@NotNull final TaskProvider<T> p0, @NotNull final ProgressIndicator p1) {
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
        //    18: ldc             "taskProvider"
        //    20: aastore        
        //    21: dup            
        //    22: ldc             1
        //    24: ldc             "com/jetbrains/cidr/lang/symbols/symtable/OCSymbolTablesBuildingActivity"
        //    26: aastore        
        //    27: dup            
        //    28: ldc             2
        //    30: ldc             "createTasks"
        //    32: aastore        
        //    33: invokestatic    java/lang/String.format:(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
        //    36: invokespecial   java/lang/IllegalArgumentException.<init>:(Ljava/lang/String;)V
        //    39: athrow         
        //    40: invokestatic    com/jetbrains/cidr/lang/symbols/symtable/OCSymbolTablesBuildingActivity.b:(Ljava/lang/Exception;)Ljava/lang/Exception;
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
        //    62: ldc             "indicator"
        //    64: aastore        
        //    65: dup            
        //    66: ldc             1
        //    68: ldc             "com/jetbrains/cidr/lang/symbols/symtable/OCSymbolTablesBuildingActivity"
        //    70: aastore        
        //    71: dup            
        //    72: ldc             2
        //    74: ldc             "createTasks"
        //    76: aastore        
        //    77: invokestatic    java/lang/String.format:(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
        //    80: invokespecial   java/lang/IllegalArgumentException.<init>:(Ljava/lang/String;)V
        //    83: athrow         
        //    84: invokestatic    com/jetbrains/cidr/lang/symbols/symtable/OCSymbolTablesBuildingActivity.b:(Ljava/lang/Exception;)Ljava/lang/Exception;
        //    87: athrow         
        //    88: invokestatic    java/lang/Runtime.getRuntime:()Ljava/lang/Runtime;
        //    91: invokevirtual   java/lang/Runtime.availableProcessors:()I
        //    94: aconst_null    
        //    95: invokestatic    java/util/Collections.nCopies:(ILjava/lang/Object;)Ljava/util/List;
        //    98: invokeinterface java/util/List.stream:()Ljava/util/stream/Stream;
        //   103: aload_0        
        //   104: aload_1        
        //   105: invokedynamic   apply:(Lcom/jetbrains/cidr/lang/symbols/symtable/OCSymbolTablesBuildingActivity$TaskProvider;Lcom/intellij/openapi/progress/ProgressIndicator;)Ljava/util/function/Function;
        //   110: invokeinterface java/util/stream/Stream.map:(Ljava/util/function/Function;)Ljava/util/stream/Stream;
        //   115: invokestatic    java/util/stream/Collectors.toList:()Ljava/util/stream/Collector;
        //   118: invokeinterface java/util/stream/Stream.collect:(Ljava/util/stream/Collector;)Ljava/lang/Object;
        //   123: checkcast       Ljava/util/List;
        //   126: dup            
        //   127: ifnonnull       164
        //   130: new             Ljava/lang/IllegalStateException;
        //   133: dup            
        //   134: ldc             "@NotNull method %s.%s must not return null"
        //   136: ldc             2
        //   138: anewarray       Ljava/lang/Object;
        //   141: dup            
        //   142: ldc             0
        //   144: ldc             "com/jetbrains/cidr/lang/symbols/symtable/OCSymbolTablesBuildingActivity"
        //   146: aastore        
        //   147: dup            
        //   148: ldc             1
        //   150: ldc             "createTasks"
        //   152: aastore        
        //   153: invokestatic    java/lang/String.format:(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
        //   156: invokespecial   java/lang/IllegalStateException.<init>:(Ljava/lang/String;)V
        //   159: athrow         
        //   160: invokestatic    com/jetbrains/cidr/lang/symbols/symtable/OCSymbolTablesBuildingActivity.b:(Ljava/lang/Exception;)Ljava/lang/Exception;
        //   163: athrow         
        //   164: areturn        
        //    Signature:
        //  <T:Ljava/lang/Object;>(Lcom/jetbrains/cidr/lang/symbols/symtable/OCSymbolTablesBuildingActivity$TaskProvider<TT;>;Lcom/intellij/openapi/progress/ProgressIndicator;)Ljava/util/List<Ljava/util/concurrent/Future<*>;>;
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                                                    
        //  -----  -----  -----  -----  --------------------------------------------------------
        //  0      40     40     44     Lcom/intellij/openapi/progress/ProcessCanceledException;
        //  44     84     84     88     Lcom/intellij/openapi/progress/ProcessCanceledException;
        //  88     160    160    164    Lcom/intellij/openapi/progress/ProcessCanceledException;
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
    
    private static <T> void a(@NotNull final Producer<T> producer, @NotNull final Consumer<T> consumer, @NotNull final ProgressIndicator progressIndicator) {
        try {
            if (producer == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "itemProvider", "com/jetbrains/cidr/lang/symbols/symtable/OCSymbolTablesBuildingActivity", "processItemsInReadAction"));
            }
        }
        catch (ProcessCanceledException ex) {
            throw b((Exception)ex);
        }
        try {
            if (consumer == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "worker", "com/jetbrains/cidr/lang/symbols/symtable/OCSymbolTablesBuildingActivity", "processItemsInReadAction"));
            }
        }
        catch (ProcessCanceledException ex2) {
            throw b((Exception)ex2);
        }
        try {
            if (progressIndicator == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "globalIndicator", "com/jetbrains/cidr/lang/symbols/symtable/OCSymbolTablesBuildingActivity", "processItemsInReadAction"));
            }
        }
        catch (ProcessCanceledException ex3) {
            throw b((Exception)ex3);
        }
        Object o;
        if (OCSymbolTablesBuildingActivity.ourIndicatorFactory != null) {
            o = OCSymbolTablesBuildingActivity.ourIndicatorFactory.produce();
        }
        else {
            o = new SensitiveProgressWrapper(progressIndicator) {
                @Override
                protected boolean isReuseable() {
                    return true;
                }
            };
        }
        final IllegalArgumentException ex4;
        final IllegalArgumentException ex6;
        final IllegalArgumentException ex8;
        final ProgressIndicator progressIndicator2;
        final ApplicationAdapter applicationAdapter;
        Ref ref;
        final Object o2;
        final IllegalArgumentException ex12;
        final IllegalArgumentException ex14;
        final Ref ref2;
        final Object o3;
        ProgressManager.getInstance().runProcess(() -> {
            try {
                if (progressIndicator == null) {
                    new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "globalIndicator", "com/jetbrains/cidr/lang/symbols/symtable/OCSymbolTablesBuildingActivity", "lambda$processItemsInReadAction$22"));
                    throw ex4;
                }
            }
            catch (ProcessCanceledException ex5) {
                throw b((Exception)ex5);
            }
            try {
                if (producer == null) {
                    new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "itemProvider", "com/jetbrains/cidr/lang/symbols/symtable/OCSymbolTablesBuildingActivity", "lambda$processItemsInReadAction$22"));
                    throw ex6;
                }
            }
            catch (ProcessCanceledException ex7) {
                throw b((Exception)ex7);
            }
            try {
                if (consumer == null) {
                    new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "worker", "com/jetbrains/cidr/lang/symbols/symtable/OCSymbolTablesBuildingActivity", "lambda$processItemsInReadAction$22"));
                    throw ex8;
                }
            }
            catch (ProcessCanceledException ex9) {
                throw b((Exception)ex9);
            }
            applicationAdapter = new ApplicationAdapter() {
                final /* synthetic */ ProgressIndicator val$localProgress;
                
                public void beforeWriteActionStart(@NotNull final Object o) {
                    try {
                        if (o == null) {
                            throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "action", "com/jetbrains/cidr/lang/symbols/symtable/OCSymbolTablesBuildingActivity$4", "beforeWriteActionStart"));
                        }
                    }
                    catch (IllegalArgumentException ex) {
                        throw a(ex);
                    }
                    this.val$localProgress.cancel();
                }
                
                public void writeActionFinished(@NotNull final Object o) {
                    try {
                        if (o == null) {
                            throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "action", "com/jetbrains/cidr/lang/symbols/symtable/OCSymbolTablesBuildingActivity$4", "writeActionFinished"));
                        }
                    }
                    catch (IllegalArgumentException ex) {
                        throw a(ex);
                    }
                    try {
                        if (this.val$localProgress.isRunning()) {
                            this.val$localProgress.stop();
                        }
                    }
                    catch (IllegalArgumentException ex2) {
                        throw a(ex2);
                    }
                    this.val$localProgress.start();
                }
                
                private static IllegalArgumentException a(final IllegalArgumentException ex) {
                    return ex;
                }
            };
            ApplicationManager.getApplication().addApplicationListener((ApplicationListener)applicationAdapter);
            try {
                ref = new Ref();
                while (true) {
                    Label_0212_1: {
                        try {
                            if (!progressIndicator.isCanceled()) {
                                if (ref.get() != null) {
                                    break Label_0212_1;
                                }
                            }
                            else {
                                break;
                            }
                        }
                        catch (ProcessCanceledException ex10) {
                            throw b((Exception)ex10);
                        }
                        producer.produce();
                        try {
                            if (o2 == null) {
                                break;
                            }
                        }
                        catch (ProcessCanceledException ex11) {
                            throw b((Exception)ex11);
                        }
                        ref.set(o2);
                    }
                    b(progressIndicator, () -> {
                        try {
                            if (progressIndicator == null) {
                                new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "globalIndicator", "com/jetbrains/cidr/lang/symbols/symtable/OCSymbolTablesBuildingActivity", "lambda$null$21"));
                                throw ex12;
                            }
                        }
                        catch (ProcessCanceledException ex13) {
                            throw b((Exception)ex13);
                        }
                        try {
                            if (consumer == null) {
                                new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "worker", "com/jetbrains/cidr/lang/symbols/symtable/OCSymbolTablesBuildingActivity", "lambda$null$21"));
                                throw ex14;
                            }
                        }
                        catch (ProcessCanceledException ex15) {
                            throw b((Exception)ex15);
                        }
                        try {
                            if (progressIndicator.isCanceled()) {
                                return;
                            }
                        }
                        catch (ProcessCanceledException ex16) {
                            throw b((Exception)ex16);
                        }
                        ref2.get();
                        ref2.set((Object)null);
                        try {
                            consumer.consume(o3);
                        }
                        catch (ProcessCanceledException ex17) {
                            ref2.set(o3);
                        }
                        catch (Throwable t) {
                            OCLog.LOG.error(t);
                        }
                    });
                }
            }
            finally {
                ApplicationManager.getApplication().removeApplicationListener((ApplicationListener)applicationAdapter);
            }
        }, (ProgressIndicator)o);
    }
    
    static {
        boolean $assertionsDisabled2 = false;
        Label_0017: {
            try {
                if (!OCSymbolTablesBuildingActivity.class.desiredAssertionStatus()) {
                    $assertionsDisabled2 = true;
                    break Label_0017;
                }
            }
            catch (ProcessCanceledException ex) {
                throw b((Exception)ex);
            }
            $assertionsDisabled2 = false;
        }
        $assertionsDisabled = $assertionsDisabled2;
        mySymbolBuildingThreads = ContainerUtil.newConcurrentSet();
        ACTIVITY_LOG = NotNullLazyKey.create("SYMBOL ACTIVITY LOG", project -> new ArrayList());
    }
    
    private static Exception b(final Exception ex) {
        return ex;
    }
    
    public enum Mode
    {
        FAST, 
        COMPACT, 
        FULL;
    }
    
    private static class OCSymbolTableBuilder
    {
        private final ProgressIndicator myIndicator;
        private final MultiMap<OCBuildFileCategory, OCBuildFileDescriptor> myClusterization;
        
        public OCSymbolTableBuilder(@NotNull final Project project, @NotNull final ProgressIndicator myIndicator, @NotNull final Collection<VirtualFile> collection) {
            if (project == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "project", "com/jetbrains/cidr/lang/symbols/symtable/OCSymbolTablesBuildingActivity$OCSymbolTableBuilder", "<init>"));
            }
            if (myIndicator == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "progressIndicator", "com/jetbrains/cidr/lang/symbols/symtable/OCSymbolTablesBuildingActivity$OCSymbolTableBuilder", "<init>"));
            }
            if (collection == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "files", "com/jetbrains/cidr/lang/symbols/symtable/OCSymbolTablesBuildingActivity$OCSymbolTableBuilder", "<init>"));
            }
            this.myClusterization = (MultiMap<OCBuildFileCategory, OCBuildFileDescriptor>)new MultiMap();
            this.myIndicator = myIndicator;
            a((com.intellij.util.Producer<Object>)(() -> {
                try {
                    if (iterator.hasNext()) {
                        return iterator.next();
                    }
                }
                catch (IllegalArgumentException ex) {
                    throw a(ex);
                }
                return null;
            }), (com.intellij.util.Consumer<Object>)(virtualFile -> {
                try {
                    if (project == null) {
                        throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "project", "com/jetbrains/cidr/lang/symbols/symtable/OCSymbolTablesBuildingActivity$OCSymbolTableBuilder", "lambda$new$1"));
                    }
                }
                catch (IllegalArgumentException ex) {
                    throw a(ex);
                }
                try {
                    if (!virtualFile.isValid()) {
                        return;
                    }
                }
                catch (IllegalArgumentException ex2) {
                    throw a(ex2);
                }
                final PsiFile file = PsiManager.getInstance(project).findFile(virtualFile);
                OCLanguageKind ocLanguageKind = null;
                Label_0106: {
                    Label_0095: {
                        try {
                            if (!(file instanceof OCConfigurationOwner)) {
                                return;
                            }
                            if (!OCInclusionContextUtil.isNeedToFindRoot(file)) {
                                break Label_0095;
                            }
                        }
                        catch (IllegalArgumentException ex3) {
                            throw a(ex3);
                        }
                        ocLanguageKind = CLanguageKind.maxLanguage(project);
                        break Label_0106;
                    }
                    ocLanguageKind = ((OCConfigurationOwner)file).getKind();
                }
                for (final OCResolveConfiguration ocResolveConfiguration : OCInclusionContextUtil.getAllBuildConfigurationsForIndexing(virtualFile, project)) {
                    this.myClusterization.putValue((Object)new OCBuildFileCategory(ocResolveConfiguration.getIndexingCluster(), ocLanguageKind), (Object)new OCBuildFileDescriptor(ocResolveConfiguration, virtualFile, ocLanguageKind));
                }
            }), myIndicator);
        }
        
        public void processBuildFiles() {
            final int size = this.myClusterization.values().size();
            final Set keySet = this.myClusterization.keySet();
            final ArrayList list = new ArrayList(keySet.size());
            final AtomicInteger atomicInteger = new AtomicInteger(0);
            final ArrayList<a> list2 = new ArrayList<a>();
            final HashSet set = new HashSet();
            final Iterator<OCBuildFileCategory> iterator = keySet.iterator();
            while (iterator.hasNext()) {
                final Collection<Object> unmodifiableCollection = Collections.unmodifiableCollection((Collection<?>)this.myClusterization.get((Object)iterator.next()));
                ((Set)set).addAll(unmodifiableCollection);
                list2.add(() -> {
                    while (iterator.hasNext()) {
                        final OCBuildFileDescriptor ocBuildFileDescriptor = iterator.next();
                        synchronized (set) {
                            if (((Set)set).contains(ocBuildFileDescriptor)) {
                                ((Set)set).remove(ocBuildFileDescriptor);
                                return ocBuildFileDescriptor;
                            }
                            continue;
                        }
                    }
                    return null;
                });
            }
            list.addAll(a((TaskProvider<Object>)new TaskProvider<OCBuildFileDescriptor>() {
                final /* synthetic */ a val$remainingProvider = () -> {
                    synchronized (set) {
                        if (!((Set)set).isEmpty()) {
                            final OCBuildFileDescriptor ocBuildFileDescriptor = ((Set<OCBuildFileDescriptor>)set).iterator().next();
                            ((Set)set).remove(ocBuildFileDescriptor);
                            return ocBuildFileDescriptor;
                        }
                    }
                    return null;
                };
                
                @Override
                public Producer<OCBuildFileDescriptor> getItemProvider() {
                    return (Producer<OCBuildFileDescriptor>)new PrioritizedBuildFileProvider((Collection)list2, this.val$remainingProvider);
                }
                
                @Override
                public Consumer<OCBuildFileDescriptor> getWorker() {
                    return (Consumer<OCBuildFileDescriptor>)(ocBuildFileDescriptor -> {
                        OCImportGraph.buildSymbolAndRootHeaderCache(ocBuildFileDescriptor.myConfiguration, ocBuildFileDescriptor.myFile, ocBuildFileDescriptor.myLanguageKind, OCSymbolTableBuilder.this.myIndicator);
                        OCSymbolTableBuilder.this.myIndicator.setFraction(atomicInteger.incrementAndGet() / n);
                    });
                }
            }, this.myIndicator));
            a(list);
        }
        
        private static IllegalArgumentException a(final IllegalArgumentException ex) {
            return ex;
        }
        
        private static final class OCBuildFileCategory
        {
            private final Object myConfigurationCluster;
            private final OCLanguageKind myKind;
            
            private OCBuildFileCategory(@Nullable final Object myConfigurationCluster, @Nullable final OCLanguageKind myKind) {
                this.myConfigurationCluster = myConfigurationCluster;
                this.myKind = myKind;
            }
            
            @Override
            public boolean equals(final Object o) {
                if (this == o) {
                    return true;
                }
                if (o == null || this.getClass() != o.getClass()) {
                    return false;
                }
                final OCBuildFileCategory ocBuildFileCategory = (OCBuildFileCategory)o;
                if (this.myKind != ocBuildFileCategory.myKind) {
                    return false;
                }
                if (this.myConfigurationCluster != null) {
                    if (this.myConfigurationCluster.equals(ocBuildFileCategory.myConfigurationCluster)) {
                        return true;
                    }
                }
                else if (ocBuildFileCategory.myConfigurationCluster == null) {
                    return true;
                }
                return false;
            }
            
            @Override
            public int hashCode() {
                return 31 * ((this.myConfigurationCluster != null) ? this.myConfigurationCluster.hashCode() : 0) + ((this.myKind != null) ? this.myKind.hashCode() : 0);
            }
        }
        
        private static final class OCBuildFileDescriptor
        {
            private final VirtualFile myFile;
            private final OCResolveConfiguration myConfiguration;
            private final OCLanguageKind myLanguageKind;
            
            private OCBuildFileDescriptor(final OCResolveConfiguration myConfiguration, final VirtualFile myFile, final OCLanguageKind myLanguageKind) {
                this.myConfiguration = myConfiguration;
                this.myFile = myFile;
                this.myLanguageKind = myLanguageKind;
            }
        }
        
        private static class PrioritizedBuildFileProvider implements a
        {
            private boolean myUseClusters;
            private Producer<OCBuildFileDescriptor> myCurrentProvider;
            @NotNull
            private final Collection<a> myProviders;
            @NotNull
            private final a myBottomProvider;
            
            private PrioritizedBuildFileProvider(@NotNull final Collection<a> myProviders, @NotNull final a myBottomProvider) {
                if (myProviders == null) {
                    throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "providers", "com/jetbrains/cidr/lang/symbols/symtable/OCSymbolTablesBuildingActivity$OCSymbolTableBuilder$PrioritizedBuildFileProvider", "<init>"));
                }
                if (myBottomProvider == null) {
                    throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "bottomProvider", "com/jetbrains/cidr/lang/symbols/symtable/OCSymbolTablesBuildingActivity$OCSymbolTableBuilder$PrioritizedBuildFileProvider", "<init>"));
                }
                this.myUseClusters = true;
                this.myProviders = myProviders;
                this.myBottomProvider = myBottomProvider;
            }
            
            @Nullable
            public OCBuildFileDescriptor produce() {
                final OCBuildFileDescriptor a = this.a();
                try {
                    if (a != null) {
                        return a;
                    }
                }
                catch (IllegalArgumentException ex) {
                    throw a(ex);
                }
                return (OCBuildFileDescriptor)this.myBottomProvider.produce();
            }
            
            private OCBuildFileDescriptor a() {
                try {
                    if (!this.myUseClusters) {
                        return null;
                    }
                }
                catch (IllegalArgumentException ex) {
                    throw a(ex);
                }
                while (true) {
                    if (this.myCurrentProvider != null) {
                        final Object myCurrentProvider = this.myCurrentProvider.produce();
                        try {
                            if (myCurrentProvider != null) {
                                return (OCBuildFileDescriptor)myCurrentProvider;
                            }
                        }
                        catch (IllegalArgumentException ex2) {
                            throw a(ex2);
                        }
                    }
                    Object myCurrentProvider;
                    synchronized (this.myProviders) {
                        final Iterator<a> iterator = this.myProviders.iterator();
                        if (!iterator.hasNext()) {
                            break;
                        }
                        myCurrentProvider = iterator.next();
                        iterator.remove();
                    }
                    this.myCurrentProvider = (Producer<OCBuildFileDescriptor>)myCurrentProvider;
                }
                this.myUseClusters = false;
                return null;
            }
            
            private static IllegalArgumentException a(final IllegalArgumentException ex) {
                return ex;
            }
        }
        
        @FunctionalInterface
        private interface a extends Producer<OCBuildFileDescriptor>
        {
        }
    }
    
    private static class MyProgressIndicator extends DelegatingProgressIndicator
    {
        private double myFromFraction;
        private double myToFraction;
        private String lastActivity;
        private long activityStarted;
        
        public MyProgressIndicator(@NotNull final ProgressIndicator progressIndicator) {
            if (progressIndicator == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "indicator", "com/jetbrains/cidr/lang/symbols/symtable/OCSymbolTablesBuildingActivity$MyProgressIndicator", "<init>"));
            }
            super(progressIndicator);
            this.myToFraction = 1.0;
        }
        
        public void startTiming(final String lastActivity) {
            this.activityStarted = System.currentTimeMillis();
            this.lastActivity = lastActivity;
        }
        
        public void setText(final String text) {
            super.setText(text);
        }
        
        public void logTiming() {
            OCLog.LOG.info(this.lastActivity + " finished in " + (System.currentTimeMillis() - this.activityStarted) / 1000L + " s.");
        }
        
        public void setInterval(final double myFromFraction, final double myToFraction) {
            this.myFromFraction = myFromFraction;
            this.myToFraction = myToFraction;
        }
        
        public void setFraction(final double n) {
            super.setFraction(this.myFromFraction + (this.myToFraction - this.myFromFraction) * n);
        }
    }
    
    public interface TaskProvider<T>
    {
        Producer<T> getItemProvider();
        
        Consumer<T> getWorker();
    }
}
