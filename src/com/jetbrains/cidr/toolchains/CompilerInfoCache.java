// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.toolchains;

import com.jetbrains.cidr.lang.workspace.compiler.OCCompilerFeatures;
import com.intellij.openapi.util.Pair;
import java.util.Iterator;
import java.util.List;
import java.util.Collections;
import com.intellij.openapi.vfs.LocalFileSystem;
import java.util.Collection;
import com.intellij.util.containers.ContainerUtil;
import com.jetbrains.cidr.lang.workspace.headerRoots.HeadersSearchRoot;
import java.util.ArrayList;
import com.jetbrains.cidr.lang.workspace.headerRoots.HeadersSearchPath;
import com.jetbrains.cidr.lang.workspace.headerRoots.HeadersSearchPathTree;
import com.intellij.execution.ExecutionException;
import com.jetbrains.cidr.lang.toolchains.CidrCompilerSwitches;
import java.io.File;
import com.jetbrains.cidr.lang.workspace.compiler.OCCompilerKind;
import org.jetbrains.annotations.Nullable;
import com.intellij.openapi.vfs.VirtualFile;
import com.jetbrains.cidr.lang.OCLanguageKind;
import com.jetbrains.cidr.lang.workspace.compiler.OCCompilerSettings;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.progress.ProcessCanceledException;
import com.intellij.util.concurrency.AppExecutorUtil;
import java.util.HashMap;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.Future;
import org.jetbrains.annotations.NotNull;
import com.jetbrains.cidr.lang.workspace.compiler.CidrCompilerResult;
import java.util.Map;
import java.util.concurrent.ExecutorService;

public class CompilerInfoCache
{
    private static final ExecutorService ourExecutor;
    @NotNull
    private final Map<String, CidrCompilerResult<Entry>> myCache;
    @NotNull
    private final Map<String, Future<CidrCompilerResult<Entry>>> myInProcess;
    @NotNull
    private final Object myLock;
    
    public CompilerInfoCache() {
        this.myCache = new ConcurrentHashMap<String, CidrCompilerResult<Entry>>();
        this.myInProcess = new HashMap<String, Future<CidrCompilerResult<Entry>>>();
        this.myLock = new Object();
    }
    
    static int defaultMaxProcesses(final int n) {
        return Math.max(1, (int)Math.floor(n * 0.8));
    }
    
    @NotNull
    static ExecutorService createExecutor(final int n) {
        ExecutorService boundedApplicationPoolExecutor;
        try {
            boundedApplicationPoolExecutor = AppExecutorUtil.createBoundedApplicationPoolExecutor("CompilerInfoCache pool", n);
            if (boundedApplicationPoolExecutor == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/toolchains/CompilerInfoCache", "createExecutor"));
            }
        }
        catch (ProcessCanceledException ex) {
            throw b((Exception)ex);
        }
        return boundedApplicationPoolExecutor;
    }
    
    public boolean isEmpty() {
        return this.myCache.isEmpty();
    }
    
    @NotNull
    public CidrCompilerResult<Entry> getCompilerInfoCache(@NotNull final Project project, @NotNull final OCCompilerSettings ocCompilerSettings, @NotNull final OCLanguageKind ocLanguageKind, @Nullable final VirtualFile virtualFile) {
        try {
            if (project == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "project", "com/jetbrains/cidr/toolchains/CompilerInfoCache", "getCompilerInfoCache"));
            }
        }
        catch (ProcessCanceledException ex) {
            throw b((Exception)ex);
        }
        try {
            if (ocCompilerSettings == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "compilerSettings", "com/jetbrains/cidr/toolchains/CompilerInfoCache", "getCompilerInfoCache"));
            }
        }
        catch (ProcessCanceledException ex2) {
            throw b((Exception)ex2);
        }
        try {
            if (ocLanguageKind == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "lang", "com/jetbrains/cidr/toolchains/CompilerInfoCache", "getCompilerInfoCache"));
            }
        }
        catch (ProcessCanceledException ex3) {
            throw b((Exception)ex3);
        }
        final File compilerExecutable = ocCompilerSettings.getCompilerExecutable(ocLanguageKind);
        CidrCompilerBase cidrCompilerBase = null;
        Label_0248: {
            Label_0198: {
                CidrCompilerResult<Entry> cidrCompilerResult = null;
                Label_0163: {
                    try {
                        if (compilerExecutable != null) {
                            break Label_0198;
                        }
                        final Entry entry = Entry.EMPTY;
                        cidrCompilerResult = CidrCompilerResult.create(entry);
                        if (cidrCompilerResult == null) {
                            break Label_0163;
                        }
                        return cidrCompilerResult;
                    }
                    catch (ProcessCanceledException ex4) {
                        throw b((Exception)ex4);
                    }
                    try {
                        final Entry entry = Entry.EMPTY;
                        cidrCompilerResult = CidrCompilerResult.create(entry);
                        if (cidrCompilerResult == null) {
                            throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/toolchains/CompilerInfoCache", "getCompilerInfoCache"));
                        }
                    }
                    catch (ProcessCanceledException ex5) {
                        throw b((Exception)ex5);
                    }
                }
                return cidrCompilerResult;
                try {
                    if (ocCompilerSettings.getCompiler(ocLanguageKind) == OCCompilerKind.MSVC) {
                        cidrCompilerBase = new MSVCCompiler(compilerExecutable, ocCompilerSettings.getCompilerWorkingDir());
                        break Label_0248;
                    }
                }
                catch (ProcessCanceledException ex6) {
                    throw b((Exception)ex6);
                }
            }
            cidrCompilerBase = new GCCCompiler(compilerExecutable, ocCompilerSettings.getCompilerWorkingDir());
        }
        final CidrCompilerBase cidrCompilerBase2 = cidrCompilerBase;
        CidrCompilerResult<Entry> compilerInfoCache;
        try {
            compilerInfoCache = this.getCompilerInfoCache(project, cidrCompilerBase2, ocCompilerSettings, ocLanguageKind, virtualFile, CompilerInfoCache.ourExecutor);
            if (compilerInfoCache == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/toolchains/CompilerInfoCache", "getCompilerInfoCache"));
            }
        }
        catch (ProcessCanceledException ex7) {
            throw b((Exception)ex7);
        }
        return compilerInfoCache;
    }
    
    @NotNull
    CidrCompilerResult<Entry> getCompilerInfoCache(@NotNull final Project p0, @NotNull final CidrCompiler p1, @NotNull final OCCompilerSettings p2, @NotNull final OCLanguageKind p3, @Nullable final VirtualFile p4, @NotNull final ExecutorService p5) {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
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
        //    24: ldc             "com/jetbrains/cidr/toolchains/CompilerInfoCache"
        //    26: aastore        
        //    27: dup            
        //    28: ldc             2
        //    30: ldc             "getCompilerInfoCache"
        //    32: aastore        
        //    33: invokestatic    java/lang/String.format:(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
        //    36: invokespecial   java/lang/IllegalArgumentException.<init>:(Ljava/lang/String;)V
        //    39: athrow         
        //    40: invokestatic    com/jetbrains/cidr/toolchains/CompilerInfoCache.b:(Ljava/lang/Exception;)Ljava/lang/Exception;
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
        //    62: ldc             "compiler"
        //    64: aastore        
        //    65: dup            
        //    66: ldc             1
        //    68: ldc             "com/jetbrains/cidr/toolchains/CompilerInfoCache"
        //    70: aastore        
        //    71: dup            
        //    72: ldc             2
        //    74: ldc             "getCompilerInfoCache"
        //    76: aastore        
        //    77: invokestatic    java/lang/String.format:(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
        //    80: invokespecial   java/lang/IllegalArgumentException.<init>:(Ljava/lang/String;)V
        //    83: athrow         
        //    84: invokestatic    com/jetbrains/cidr/toolchains/CompilerInfoCache.b:(Ljava/lang/Exception;)Ljava/lang/Exception;
        //    87: athrow         
        //    88: aload_3        
        //    89: ifnonnull       132
        //    92: new             Ljava/lang/IllegalArgumentException;
        //    95: dup            
        //    96: ldc             "Argument for @NotNull parameter '%s' of %s.%s must not be null"
        //    98: ldc             3
        //   100: anewarray       Ljava/lang/Object;
        //   103: dup            
        //   104: ldc             0
        //   106: ldc             "compilerSettings"
        //   108: aastore        
        //   109: dup            
        //   110: ldc             1
        //   112: ldc             "com/jetbrains/cidr/toolchains/CompilerInfoCache"
        //   114: aastore        
        //   115: dup            
        //   116: ldc             2
        //   118: ldc             "getCompilerInfoCache"
        //   120: aastore        
        //   121: invokestatic    java/lang/String.format:(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
        //   124: invokespecial   java/lang/IllegalArgumentException.<init>:(Ljava/lang/String;)V
        //   127: athrow         
        //   128: invokestatic    com/jetbrains/cidr/toolchains/CompilerInfoCache.b:(Ljava/lang/Exception;)Ljava/lang/Exception;
        //   131: athrow         
        //   132: aload           4
        //   134: ifnonnull       177
        //   137: new             Ljava/lang/IllegalArgumentException;
        //   140: dup            
        //   141: ldc             "Argument for @NotNull parameter '%s' of %s.%s must not be null"
        //   143: ldc             3
        //   145: anewarray       Ljava/lang/Object;
        //   148: dup            
        //   149: ldc             0
        //   151: ldc             "lang"
        //   153: aastore        
        //   154: dup            
        //   155: ldc             1
        //   157: ldc             "com/jetbrains/cidr/toolchains/CompilerInfoCache"
        //   159: aastore        
        //   160: dup            
        //   161: ldc             2
        //   163: ldc             "getCompilerInfoCache"
        //   165: aastore        
        //   166: invokestatic    java/lang/String.format:(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
        //   169: invokespecial   java/lang/IllegalArgumentException.<init>:(Ljava/lang/String;)V
        //   172: athrow         
        //   173: invokestatic    com/jetbrains/cidr/toolchains/CompilerInfoCache.b:(Ljava/lang/Exception;)Ljava/lang/Exception;
        //   176: athrow         
        //   177: aload           6
        //   179: ifnonnull       222
        //   182: new             Ljava/lang/IllegalArgumentException;
        //   185: dup            
        //   186: ldc             "Argument for @NotNull parameter '%s' of %s.%s must not be null"
        //   188: ldc             3
        //   190: anewarray       Ljava/lang/Object;
        //   193: dup            
        //   194: ldc             0
        //   196: ldc             "executor"
        //   198: aastore        
        //   199: dup            
        //   200: ldc             1
        //   202: ldc             "com/jetbrains/cidr/toolchains/CompilerInfoCache"
        //   204: aastore        
        //   205: dup            
        //   206: ldc             2
        //   208: ldc             "getCompilerInfoCache"
        //   210: aastore        
        //   211: invokestatic    java/lang/String.format:(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
        //   214: invokespecial   java/lang/IllegalArgumentException.<init>:(Ljava/lang/String;)V
        //   217: athrow         
        //   218: invokestatic    com/jetbrains/cidr/toolchains/CompilerInfoCache.b:(Ljava/lang/Exception;)Ljava/lang/Exception;
        //   221: athrow         
        //   222: aload_3        
        //   223: aload           4
        //   225: aload           5
        //   227: invokeinterface com/jetbrains/cidr/lang/workspace/compiler/OCCompilerSettings.getCompilerKeyAndSwitches:(Lcom/jetbrains/cidr/lang/OCLanguageKind;Lcom/intellij/openapi/vfs/VirtualFile;)Lcom/intellij/openapi/util/Pair;
        //   232: astore          7
        //   234: aload           7
        //   236: getfield        com/intellij/openapi/util/Pair.first:Ljava/lang/Object;
        //   239: checkcast       Ljava/lang/String;
        //   242: astore          8
        //   244: aload_0        
        //   245: getfield        com/jetbrains/cidr/toolchains/CompilerInfoCache.myCache:Ljava/util/Map;
        //   248: aload           8
        //   250: invokeinterface java/util/Map.get:(Ljava/lang/Object;)Ljava/lang/Object;
        //   255: checkcast       Lcom/jetbrains/cidr/lang/workspace/compiler/CidrCompilerResult;
        //   258: astore          9
        //   260: aload           9
        //   262: ifnull          313
        //   265: aload           9
        //   267: dup            
        //   268: ifnonnull       312
        //   271: goto            278
        //   274: invokestatic    com/jetbrains/cidr/toolchains/CompilerInfoCache.b:(Ljava/lang/Exception;)Ljava/lang/Exception;
        //   277: athrow         
        //   278: new             Ljava/lang/IllegalStateException;
        //   281: dup            
        //   282: ldc             "@NotNull method %s.%s must not return null"
        //   284: ldc             2
        //   286: anewarray       Ljava/lang/Object;
        //   289: dup            
        //   290: ldc             0
        //   292: ldc             "com/jetbrains/cidr/toolchains/CompilerInfoCache"
        //   294: aastore        
        //   295: dup            
        //   296: ldc             1
        //   298: ldc             "getCompilerInfoCache"
        //   300: aastore        
        //   301: invokestatic    java/lang/String.format:(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
        //   304: invokespecial   java/lang/IllegalStateException.<init>:(Ljava/lang/String;)V
        //   307: athrow         
        //   308: invokestatic    com/jetbrains/cidr/toolchains/CompilerInfoCache.b:(Ljava/lang/Exception;)Ljava/lang/Exception;
        //   311: athrow         
        //   312: areturn        
        //   313: aload_0        
        //   314: getfield        com/jetbrains/cidr/toolchains/CompilerInfoCache.myLock:Ljava/lang/Object;
        //   317: dup            
        //   318: astore          11
        //   320: monitorenter   
        //   321: aload_0        
        //   322: getfield        com/jetbrains/cidr/toolchains/CompilerInfoCache.myCache:Ljava/util/Map;
        //   325: aload           8
        //   327: invokeinterface java/util/Map.get:(Ljava/lang/Object;)Ljava/lang/Object;
        //   332: checkcast       Lcom/jetbrains/cidr/lang/workspace/compiler/CidrCompilerResult;
        //   335: astore          9
        //   337: aload           9
        //   339: ifnull          382
        //   342: aload           9
        //   344: aload           11
        //   346: monitorexit    
        //   347: dup            
        //   348: ifnonnull       381
        //   351: new             Ljava/lang/IllegalStateException;
        //   354: dup            
        //   355: ldc             "@NotNull method %s.%s must not return null"
        //   357: ldc             2
        //   359: anewarray       Ljava/lang/Object;
        //   362: dup            
        //   363: ldc             0
        //   365: ldc             "com/jetbrains/cidr/toolchains/CompilerInfoCache"
        //   367: aastore        
        //   368: dup            
        //   369: ldc             1
        //   371: ldc             "getCompilerInfoCache"
        //   373: aastore        
        //   374: invokestatic    java/lang/String.format:(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
        //   377: invokespecial   java/lang/IllegalStateException.<init>:(Ljava/lang/String;)V
        //   380: athrow         
        //   381: areturn        
        //   382: aload_0        
        //   383: getfield        com/jetbrains/cidr/toolchains/CompilerInfoCache.myInProcess:Ljava/util/Map;
        //   386: aload           8
        //   388: invokeinterface java/util/Map.get:(Ljava/lang/Object;)Ljava/lang/Object;
        //   393: checkcast       Ljava/util/concurrent/Future;
        //   396: astore          10
        //   398: aload           10
        //   400: ifnonnull       441
        //   403: aload           6
        //   405: aload_0        
        //   406: aload_1        
        //   407: aload_2        
        //   408: aload_3        
        //   409: aload           4
        //   411: aload           7
        //   413: aload           8
        //   415: invokedynamic   call:(Lcom/jetbrains/cidr/toolchains/CompilerInfoCache;Lcom/intellij/openapi/project/Project;Lcom/jetbrains/cidr/toolchains/CidrCompiler;Lcom/jetbrains/cidr/lang/workspace/compiler/OCCompilerSettings;Lcom/jetbrains/cidr/lang/OCLanguageKind;Lcom/intellij/openapi/util/Pair;Ljava/lang/String;)Ljava/util/concurrent/Callable;
        //   420: invokeinterface java/util/concurrent/ExecutorService.submit:(Ljava/util/concurrent/Callable;)Ljava/util/concurrent/Future;
        //   425: astore          10
        //   427: aload_0        
        //   428: getfield        com/jetbrains/cidr/toolchains/CompilerInfoCache.myInProcess:Ljava/util/Map;
        //   431: aload           8
        //   433: aload           10
        //   435: invokeinterface java/util/Map.put:(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
        //   440: pop            
        //   441: aload           11
        //   443: monitorexit    
        //   444: goto            455
        //   447: astore          12
        //   449: aload           11
        //   451: monitorexit    
        //   452: aload           12
        //   454: athrow         
        //   455: invokestatic    com/intellij/openapi/progress/ProgressManager.checkCanceled:()V
        //   458: aload           10
        //   460: ldc2_w          100
        //   463: getstatic       java/util/concurrent/TimeUnit.MILLISECONDS:Ljava/util/concurrent/TimeUnit;
        //   466: invokeinterface java/util/concurrent/Future.get:(JLjava/util/concurrent/TimeUnit;)Ljava/lang/Object;
        //   471: checkcast       Lcom/jetbrains/cidr/lang/workspace/compiler/CidrCompilerResult;
        //   474: dup            
        //   475: ifnonnull       512
        //   478: new             Ljava/lang/IllegalStateException;
        //   481: dup            
        //   482: ldc             "@NotNull method %s.%s must not return null"
        //   484: ldc             2
        //   486: anewarray       Ljava/lang/Object;
        //   489: dup            
        //   490: ldc             0
        //   492: ldc             "com/jetbrains/cidr/toolchains/CompilerInfoCache"
        //   494: aastore        
        //   495: dup            
        //   496: ldc             1
        //   498: ldc             "getCompilerInfoCache"
        //   500: aastore        
        //   501: invokestatic    java/lang/String.format:(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
        //   504: invokespecial   java/lang/IllegalStateException.<init>:(Ljava/lang/String;)V
        //   507: athrow         
        //   508: invokestatic    com/jetbrains/cidr/toolchains/CompilerInfoCache.b:(Ljava/lang/Exception;)Ljava/lang/Exception;
        //   511: athrow         
        //   512: areturn        
        //   513: astore          11
        //   515: new             Lcom/intellij/openapi/progress/ProcessCanceledException;
        //   518: dup            
        //   519: invokespecial   com/intellij/openapi/progress/ProcessCanceledException.<init>:()V
        //   522: athrow         
        //   523: astore          11
        //   525: new             Ljava/lang/RuntimeException;
        //   528: dup            
        //   529: aload           11
        //   531: invokespecial   java/lang/RuntimeException.<init>:(Ljava/lang/Throwable;)V
        //   534: athrow         
        //   535: astore          11
        //   537: goto            455
        //    Signature:
        //  (Lcom/intellij/openapi/project/Project;Lcom/jetbrains/cidr/toolchains/CidrCompiler;Lcom/jetbrains/cidr/lang/workspace/compiler/OCCompilerSettings;Lcom/jetbrains/cidr/lang/OCLanguageKind;Lcom/intellij/openapi/vfs/VirtualFile;Ljava/util/concurrent/ExecutorService;)Lcom/jetbrains/cidr/lang/workspace/compiler/CidrCompilerResult<Lcom/jetbrains/cidr/toolchains/CompilerInfoCache$Entry;>;
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                                                    
        //  -----  -----  -----  -----  --------------------------------------------------------
        //  265    308    308    312    Ljava/lang/InterruptedException;
        //  260    271    274    278    Ljava/lang/InterruptedException;
        //  177    218    218    222    Ljava/lang/InterruptedException;
        //  132    173    173    177    Ljava/lang/InterruptedException;
        //  88     128    128    132    Ljava/lang/InterruptedException;
        //  44     84     84     88     Ljava/lang/InterruptedException;
        //  0      40     40     44     Ljava/lang/InterruptedException;
        //  321    347    447    455    Any
        //  382    444    447    455    Any
        //  447    452    447    455    Any
        //  458    474    513    523    Ljava/lang/InterruptedException;
        //  458    474    523    535    Ljava/util/concurrent/ExecutionException;
        //  458    474    535    540    Ljava/util/concurrent/TimeoutException;
        //  455    508    508    512    Lcom/intellij/openapi/progress/ProcessCanceledException;
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
    
    @NotNull
    private static CidrCompilerResult<Entry> a(@NotNull final Project project, @NotNull final CidrCompiler cidrCompiler, @NotNull final OCCompilerSettings ocCompilerSettings, @NotNull final OCLanguageKind ocLanguageKind, @NotNull final CidrCompilerSwitches cidrCompilerSwitches) {
        try {
            if (project == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "project", "com/jetbrains/cidr/toolchains/CompilerInfoCache", "doCollectCompilerInfo"));
            }
        }
        catch (ExecutionException ex) {
            throw b((Exception)ex);
        }
        try {
            if (cidrCompiler == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "compiler", "com/jetbrains/cidr/toolchains/CompilerInfoCache", "doCollectCompilerInfo"));
            }
        }
        catch (ExecutionException ex2) {
            throw b((Exception)ex2);
        }
        try {
            if (ocCompilerSettings == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "compilerSettings", "com/jetbrains/cidr/toolchains/CompilerInfoCache", "doCollectCompilerInfo"));
            }
        }
        catch (ExecutionException ex3) {
            throw b((Exception)ex3);
        }
        try {
            if (ocLanguageKind == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "lang", "com/jetbrains/cidr/toolchains/CompilerInfoCache", "doCollectCompilerInfo"));
            }
        }
        catch (ExecutionException ex4) {
            throw b((Exception)ex4);
        }
        try {
            if (cidrCompilerSwitches == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "switches", "com/jetbrains/cidr/toolchains/CompilerInfoCache", "doCollectCompilerInfo"));
            }
        }
        catch (ExecutionException ex5) {
            throw b((Exception)ex5);
        }
        try {
            final CompilerInfo collectInfo = cidrCompiler.collectInfo(ocLanguageKind, cidrCompilerSwitches, ocCompilerSettings.getEnvironment());
            final HeadersSearchPathTree headersSearchPathTree = new HeadersSearchPathTree();
            final Iterator<HeadersSearchPath> iterator = collectInfo.getHeadersSearchPaths().iterator();
            while (iterator.hasNext()) {
                headersSearchPathTree.addSearchPathCompacting(iterator.next());
            }
            final List<HeadersSearchPath> compactedPaths = headersSearchPathTree.getCompactedPaths();
            final ArrayList list = new ArrayList<Object>(compactedPaths.size());
            for (final HeadersSearchPath headersSearchPath : compactedPaths) {
                try {
                    if (project.isDisposed()) {
                        break;
                    }
                }
                catch (ExecutionException ex6) {
                    throw b((Exception)ex6);
                }
                ContainerUtil.addIfNotNull((Collection)list, (Object)HeadersSearchRoot.createFromHeaderSearchPath(project, headersSearchPath));
            }
            final ArrayList list2 = new ArrayList<Object>(collectInfo.getPrecompiledHeaders().size());
            final Iterator<File> iterator3 = collectInfo.getPrecompiledHeaders().iterator();
            while (iterator3.hasNext()) {
                ContainerUtil.addIfNotNull((Collection)list2, (Object)LocalFileSystem.getInstance().findFileByIoFile((File)iterator3.next()));
            }
            final CidrCompilerResult<Entry> create = CidrCompilerResult.create(new Entry(collectInfo.getDefines(), (Map)collectInfo.getFeatures(), (List)Collections.unmodifiableList((List<?>)list), (List)Collections.unmodifiableList((List<?>)list2), (List)collectInfo.getIncludeMap(), (List)collectInfo.getWarnings()));
            if (create == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/toolchains/CompilerInfoCache", "doCollectCompilerInfo"));
            }
            return create;
        }
        catch (ExecutionException ex7) {
            CidrCompilerResult<Object> error;
            try {
                error = (CidrCompilerResult<Object>)CidrCompilerResult.error((Throwable)ex7);
                if (error == null) {
                    throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/toolchains/CompilerInfoCache", "doCollectCompilerInfo"));
                }
            }
            catch (ExecutionException ex8) {
                throw b((Exception)ex8);
            }
            return (CidrCompilerResult<Entry>)error;
        }
    }
    
    static {
        ourExecutor = createExecutor(defaultMaxProcesses(Runtime.getRuntime().availableProcessors()));
    }
    
    private static Exception b(final Exception ex) {
        return ex;
    }
    
    public static class Entry
    {
        public static final Entry EMPTY;
        @NotNull
        public final String defines;
        @NotNull
        public final Map<OCCompilerFeatures.Type<?>, ?> features;
        @NotNull
        public final List<HeadersSearchRoot> headerSearchPaths;
        @NotNull
        public final List<VirtualFile> precompiledHeaders;
        @NotNull
        public final List<PrecompiledInclude> includeMap;
        @NotNull
        public final List<String> warnings;
        
        private Entry(@NotNull final String defines, @NotNull final Map<OCCompilerFeatures.Type<?>, ?> features, @NotNull final List<HeadersSearchRoot> headerSearchPaths, @NotNull final List<VirtualFile> precompiledHeaders, @NotNull final List<PrecompiledInclude> includeMap, @NotNull final List<String> warnings) {
            if (defines == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "defines", "com/jetbrains/cidr/toolchains/CompilerInfoCache$Entry", "<init>"));
            }
            if (features == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "features", "com/jetbrains/cidr/toolchains/CompilerInfoCache$Entry", "<init>"));
            }
            if (headerSearchPaths == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "headerSearchPaths", "com/jetbrains/cidr/toolchains/CompilerInfoCache$Entry", "<init>"));
            }
            if (precompiledHeaders == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "precompiledHeaders", "com/jetbrains/cidr/toolchains/CompilerInfoCache$Entry", "<init>"));
            }
            if (includeMap == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "includeMap", "com/jetbrains/cidr/toolchains/CompilerInfoCache$Entry", "<init>"));
            }
            if (warnings == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "warnings", "com/jetbrains/cidr/toolchains/CompilerInfoCache$Entry", "<init>"));
            }
            this.defines = defines;
            this.features = features;
            this.headerSearchPaths = headerSearchPaths;
            this.precompiledHeaders = precompiledHeaders;
            this.includeMap = includeMap;
            this.warnings = warnings;
        }
        
        static {
            EMPTY = new Entry("", Collections.emptyMap(), Collections.emptyList(), Collections.emptyList(), Collections.emptyList(), Collections.emptyList());
        }
    }
}
