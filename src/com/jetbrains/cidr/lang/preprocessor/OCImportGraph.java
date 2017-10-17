// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.lang.preprocessor;

import com.jetbrains.cidr.lang.symbols.symtable.FileSymbolTablesCache;
import com.intellij.openapi.progress.util.ProgressIndicatorUtils;
import com.intellij.openapi.progress.util.ReadTask;
import org.jetbrains.annotations.Contract;
import com.intellij.openapi.application.Application;
import com.intellij.openapi.application.ApplicationManager;
import java.util.concurrent.atomic.AtomicInteger;
import com.intellij.util.containers.ConcurrentMultiMap;
import java.util.concurrent.ConcurrentHashMap;
import java.util.List;
import java.util.ArrayList;
import gnu.trove.THashSet;
import com.intellij.util.Processor;
import java.util.Iterator;
import java.util.HashSet;
import com.jetbrains.cidr.lang.psi.OCFile;
import java.util.Collections;
import com.intellij.psi.PsiFile;
import com.jetbrains.cidr.lang.psi.OCConfigurationOwner;
import com.intellij.psi.PsiManager;
import com.intellij.openapi.progress.ProgressIndicator;
import org.jetbrains.annotations.Nullable;
import com.jetbrains.cidr.lang.OCLanguageKind;
import com.intellij.openapi.util.UserDataHolder;
import java.util.Collection;
import com.intellij.openapi.util.Key;
import org.jetbrains.annotations.NotNull;
import com.jetbrains.cidr.lang.workspace.OCResolveConfiguration;
import java.util.Set;
import java.util.Map;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.vfs.VirtualFile;
import com.intellij.util.containers.MultiMap;
import com.intellij.openapi.util.NotNullLazyKey;

public class OCImportGraph
{
    private static final NotNullLazyKey<MultiMap<VirtualFile, VirtualFile>, Project> HEADER_TO_ALL_ROOTS_CACHE;
    private static final NotNullLazyKey<Map<VirtualFile, Set<VirtualFile>>, OCResolveConfiguration> ROOT_TO_ALL_HEADERS_CACHE;
    private static final NotNullLazyKey<Cache, Project> IMPORTS_GRAPH;
    static final /* synthetic */ boolean $assertionsDisabled;
    
    public static void invalidateHeaderRootsCache(@NotNull final Project project) {
        try {
            if (project == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "project", "com/jetbrains/cidr/lang/preprocessor/OCImportGraph", "invalidateHeaderRootsCache"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        project.putUserData((Key)OCImportGraph.HEADER_TO_ALL_ROOTS_CACHE, (Object)null);
    }
    
    @NotNull
    public static Collection<VirtualFile> getAllHeaderRoots(@NotNull final Project project, @NotNull final VirtualFile virtualFile) {
        try {
            if (project == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "project", "com/jetbrains/cidr/lang/preprocessor/OCImportGraph", "getAllHeaderRoots"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        try {
            if (virtualFile == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "header", "com/jetbrains/cidr/lang/preprocessor/OCImportGraph", "getAllHeaderRoots"));
            }
        }
        catch (IllegalArgumentException ex2) {
            throw a(ex2);
        }
        final MultiMap multiMap = (MultiMap)OCImportGraph.HEADER_TO_ALL_ROOTS_CACHE.getValue((UserDataHolder)project);
        if (!multiMap.containsKey((Object)virtualFile)) {
            final Collection<VirtualFile> allRootsThatIncludes = findAllRootsThatIncludes(project, virtualFile, true);
            Label_0136: {
                try {
                    if (OCImportGraph.$assertionsDisabled) {
                        break Label_0136;
                    }
                    final Collection<VirtualFile> collection = allRootsThatIncludes;
                    final boolean b = collection.isEmpty();
                    if (b) {
                        break Label_0136;
                    }
                    break Label_0136;
                }
                catch (IllegalArgumentException ex3) {
                    throw a(ex3);
                }
                try {
                    final Collection<VirtualFile> collection = allRootsThatIncludes;
                    final boolean b = collection.isEmpty();
                    if (b) {
                        throw new AssertionError();
                    }
                }
                catch (IllegalArgumentException ex4) {
                    throw a(ex4);
                }
            }
            multiMap.put((Object)virtualFile, (Collection)allRootsThatIncludes);
        }
        Collection value;
        try {
            value = multiMap.get((Object)virtualFile);
            if (value == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/preprocessor/OCImportGraph", "getAllHeaderRoots"));
            }
        }
        catch (IllegalArgumentException ex5) {
            throw a(ex5);
        }
        return (Collection<VirtualFile>)value;
    }
    
    public static void buildSymbolAndRootHeaderCache(@NotNull final OCResolveConfiguration ocResolveConfiguration, @NotNull final VirtualFile virtualFile, @Nullable OCLanguageKind kind, @Nullable final ProgressIndicator progressIndicator) {
        try {
            if (ocResolveConfiguration == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "configuration", "com/jetbrains/cidr/lang/preprocessor/OCImportGraph", "buildSymbolAndRootHeaderCache"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        try {
            if (virtualFile == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "rootFile", "com/jetbrains/cidr/lang/preprocessor/OCImportGraph", "buildSymbolAndRootHeaderCache"));
            }
        }
        catch (IllegalArgumentException ex2) {
            throw a(ex2);
        }
        try {
            if (progressIndicator != null) {
                progressIndicator.checkCanceled();
            }
        }
        catch (IllegalArgumentException ex3) {
            throw a(ex3);
        }
        try {
            if (!virtualFile.isValid()) {
                return;
            }
        }
        catch (IllegalArgumentException ex4) {
            throw a(ex4);
        }
        final Project project = ocResolveConfiguration.getProject();
        try {
            if (!OCInclusionContextUtil.isNeedToFindRoot(virtualFile, project)) {
                invalidateRootHeadersCache(ocResolveConfiguration, virtualFile);
                getAllRootHeaders(ocResolveConfiguration, virtualFile, progressIndicator);
                return;
            }
        }
        catch (IllegalArgumentException ex5) {
            throw a(ex5);
        }
        final PsiFile file = PsiManager.getInstance(project).findFile(virtualFile);
        Label_0194: {
            try {
                if (!(file instanceof OCConfigurationOwner)) {
                    return;
                }
                if (kind != null) {
                    break Label_0194;
                }
            }
            catch (IllegalArgumentException ex6) {
                throw a(ex6);
            }
            kind = ((OCConfigurationOwner)file).getKind();
        }
        OCInclusionContext.sourceParsingContext(ocResolveConfiguration, kind, file).preprocessInclude(file, true);
    }
    
    public static void invalidateRootHeadersCache(final OCResolveConfiguration ocResolveConfiguration, final VirtualFile virtualFile) {
        ((Map)OCImportGraph.ROOT_TO_ALL_HEADERS_CACHE.getValue((UserDataHolder)ocResolveConfiguration)).remove(virtualFile);
    }
    
    public static void invalidateRootHeadersCache(final OCResolveConfiguration ocResolveConfiguration) {
        ((Map)OCImportGraph.ROOT_TO_ALL_HEADERS_CACHE.getValue((UserDataHolder)ocResolveConfiguration)).clear();
    }
    
    @NotNull
    public static Set<VirtualFile> getAllRootHeaders(@NotNull final OCResolveConfiguration ocResolveConfiguration, @NotNull final VirtualFile virtualFile, @Nullable final ProgressIndicator progressIndicator) {
        try {
            if (ocResolveConfiguration == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "configuration", "com/jetbrains/cidr/lang/preprocessor/OCImportGraph", "getAllRootHeaders"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        try {
            if (virtualFile == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "root", "com/jetbrains/cidr/lang/preprocessor/OCImportGraph", "getAllRootHeaders"));
            }
        }
        catch (IllegalArgumentException ex2) {
            throw a(ex2);
        }
        final Project project = ocResolveConfiguration.getProject();
        Label_0116: {
            try {
                if (OCImportGraph.$assertionsDisabled) {
                    break Label_0116;
                }
                final VirtualFile virtualFile2 = virtualFile;
                final Project project2 = project;
                final boolean b = OCInclusionContextUtil.isNeedToFindRoot(virtualFile2, project2);
                if (b) {
                    break Label_0116;
                }
                break Label_0116;
            }
            catch (IllegalArgumentException ex3) {
                throw a(ex3);
            }
            try {
                final VirtualFile virtualFile2 = virtualFile;
                final Project project2 = project;
                final boolean b = OCInclusionContextUtil.isNeedToFindRoot(virtualFile2, project2);
                if (b) {
                    throw new AssertionError((Object)"Not a root file");
                }
            }
            catch (IllegalArgumentException ex4) {
                throw a(ex4);
            }
        }
        final Map map = (Map)OCImportGraph.ROOT_TO_ALL_HEADERS_CACHE.getValue((UserDataHolder)ocResolveConfiguration);
        Set<VirtualFile> set = map.get(virtualFile);
        if (set == null) {
            final PsiManager instance = PsiManager.getInstance(project);
            PsiFile file = null;
            Label_0187: {
                try {
                    if (virtualFile.isValid()) {
                        file = instance.findFile(virtualFile);
                        break Label_0187;
                    }
                }
                catch (IllegalArgumentException ex5) {
                    throw a(ex5);
                }
                file = null;
            }
            final PsiFile psiFile = file;
            Label_0271: {
                Label_0266: {
                    Label_0226: {
                        try {
                            if (!(psiFile instanceof OCConfigurationOwner)) {
                                break Label_0266;
                            }
                            if (!OCInclusionContext.isPrecompiledHeader(virtualFile, ocResolveConfiguration)) {
                                break Label_0226;
                            }
                        }
                        catch (IllegalArgumentException ex6) {
                            throw a(ex6);
                        }
                        set = a(instance, project, ocResolveConfiguration, virtualFile, progressIndicator);
                        break Label_0271;
                    }
                    final OCInclusionContext sourceParsingContext = OCInclusionContext.sourceParsingContext(ocResolveConfiguration, ((OCConfigurationOwner)psiFile).getKind(), psiFile);
                    sourceParsingContext.preprocessInclude(psiFile, true);
                    set = sourceParsingContext.getProcessedFiles();
                    break Label_0271;
                }
                set = Collections.emptySet();
            }
            map.put(virtualFile, set);
        }
        Set<VirtualFile> set2;
        try {
            set2 = set;
            if (set2 == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/preprocessor/OCImportGraph", "getAllRootHeaders"));
            }
        }
        catch (IllegalArgumentException ex7) {
            throw a(ex7);
        }
        return set2;
    }
    
    private static Set<VirtualFile> a(@NotNull final PsiManager p0, @NotNull final Project p1, @NotNull final OCResolveConfiguration p2, @NotNull final VirtualFile p3, @Nullable final ProgressIndicator p4) {
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
        //    18: ldc             "psiManager"
        //    20: aastore        
        //    21: dup            
        //    22: ldc             1
        //    24: ldc             "com/jetbrains/cidr/lang/preprocessor/OCImportGraph"
        //    26: aastore        
        //    27: dup            
        //    28: ldc             2
        //    30: ldc             "getAllPCHRootHeaders"
        //    32: aastore        
        //    33: invokestatic    java/lang/String.format:(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
        //    36: invokespecial   java/lang/IllegalArgumentException.<init>:(Ljava/lang/String;)V
        //    39: athrow         
        //    40: invokestatic    com/jetbrains/cidr/lang/preprocessor/OCImportGraph.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
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
        //    62: ldc             "project"
        //    64: aastore        
        //    65: dup            
        //    66: ldc             1
        //    68: ldc             "com/jetbrains/cidr/lang/preprocessor/OCImportGraph"
        //    70: aastore        
        //    71: dup            
        //    72: ldc             2
        //    74: ldc             "getAllPCHRootHeaders"
        //    76: aastore        
        //    77: invokestatic    java/lang/String.format:(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
        //    80: invokespecial   java/lang/IllegalArgumentException.<init>:(Ljava/lang/String;)V
        //    83: athrow         
        //    84: invokestatic    com/jetbrains/cidr/lang/preprocessor/OCImportGraph.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
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
        //   106: ldc             "configuration"
        //   108: aastore        
        //   109: dup            
        //   110: ldc             1
        //   112: ldc             "com/jetbrains/cidr/lang/preprocessor/OCImportGraph"
        //   114: aastore        
        //   115: dup            
        //   116: ldc             2
        //   118: ldc             "getAllPCHRootHeaders"
        //   120: aastore        
        //   121: invokestatic    java/lang/String.format:(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
        //   124: invokespecial   java/lang/IllegalArgumentException.<init>:(Ljava/lang/String;)V
        //   127: athrow         
        //   128: invokestatic    com/jetbrains/cidr/lang/preprocessor/OCImportGraph.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
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
        //   150: ldc             "pch"
        //   152: aastore        
        //   153: dup            
        //   154: ldc             1
        //   156: ldc             "com/jetbrains/cidr/lang/preprocessor/OCImportGraph"
        //   158: aastore        
        //   159: dup            
        //   160: ldc             2
        //   162: ldc             "getAllPCHRootHeaders"
        //   164: aastore        
        //   165: invokestatic    java/lang/String.format:(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
        //   168: invokespecial   java/lang/IllegalArgumentException.<init>:(Ljava/lang/String;)V
        //   171: athrow         
        //   172: invokestatic    com/jetbrains/cidr/lang/preprocessor/OCImportGraph.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   175: athrow         
        //   176: new             Ljava/util/HashSet;
        //   179: dup            
        //   180: invokespecial   java/util/HashSet.<init>:()V
        //   183: astore          5
        //   185: aload_1        
        //   186: invokestatic    com/jetbrains/cidr/lang/workspace/OCWorkspaceManager.getWorkspace:(Lcom/intellij/openapi/project/Project;)Lcom/jetbrains/cidr/lang/workspace/OCWorkspace;
        //   189: astore          6
        //   191: aload_1        
        //   192: invokestatic    com/jetbrains/cidr/lang/search/scopes/OCSearchScope.getExplicitlySpecifiedProjectSourceFiles:(Lcom/intellij/openapi/project/Project;)Ljava/util/Collection;
        //   195: invokeinterface java/util/Collection.iterator:()Ljava/util/Iterator;
        //   200: astore          7
        //   202: aload           7
        //   204: invokeinterface java/util/Iterator.hasNext:()Z
        //   209: ifeq            393
        //   212: aload           7
        //   214: invokeinterface java/util/Iterator.next:()Ljava/lang/Object;
        //   219: checkcast       Lcom/intellij/openapi/vfs/VirtualFile;
        //   222: astore          8
        //   224: aload           8
        //   226: invokevirtual   com/intellij/openapi/vfs/VirtualFile.getName:()Ljava/lang/String;
        //   229: invokestatic    com/jetbrains/cidr/lang/psi/impl/OCFileImpl.isSourceCodeFile:(Ljava/lang/String;)Z
        //   232: ifeq            390
        //   235: aload_3        
        //   236: aload           8
        //   238: invokevirtual   java/lang/Object.equals:(Ljava/lang/Object;)Z
        //   241: ifne            390
        //   244: goto            251
        //   247: invokestatic    com/jetbrains/cidr/lang/preprocessor/OCImportGraph.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   250: athrow         
        //   251: aload           4
        //   253: ifnull          277
        //   256: goto            263
        //   259: invokestatic    com/jetbrains/cidr/lang/preprocessor/OCImportGraph.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   262: athrow         
        //   263: aload           4
        //   265: invokeinterface com/intellij/openapi/progress/ProgressIndicator.checkCanceled:()V
        //   270: goto            277
        //   273: invokestatic    com/jetbrains/cidr/lang/preprocessor/OCImportGraph.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   276: athrow         
        //   277: aload           6
        //   279: aload           8
        //   281: invokeinterface com/jetbrains/cidr/lang/workspace/OCWorkspace.getConfigurationsForFile:(Lcom/intellij/openapi/vfs/VirtualFile;)Ljava/util/List;
        //   286: aload_2        
        //   287: invokeinterface java/util/List.contains:(Ljava/lang/Object;)Z
        //   292: ifne            302
        //   295: goto            202
        //   298: invokestatic    com/jetbrains/cidr/lang/preprocessor/OCImportGraph.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   301: athrow         
        //   302: aload           8
        //   304: invokevirtual   com/intellij/openapi/vfs/VirtualFile.isValid:()Z
        //   307: ifeq            323
        //   310: aload_0        
        //   311: aload           8
        //   313: invokevirtual   com/intellij/psi/PsiManager.findFile:(Lcom/intellij/openapi/vfs/VirtualFile;)Lcom/intellij/psi/PsiFile;
        //   316: goto            324
        //   319: invokestatic    com/jetbrains/cidr/lang/preprocessor/OCImportGraph.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   322: athrow         
        //   323: aconst_null    
        //   324: astore          9
        //   326: aload           9
        //   328: ifnull          390
        //   331: aload           9
        //   333: instanceof      Lcom/jetbrains/cidr/lang/psi/OCConfigurationOwner;
        //   336: ifeq            390
        //   339: goto            346
        //   342: invokestatic    com/jetbrains/cidr/lang/preprocessor/OCImportGraph.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   345: athrow         
        //   346: aload           9
        //   348: checkcast       Lcom/jetbrains/cidr/lang/psi/OCConfigurationOwner;
        //   351: invokeinterface com/jetbrains/cidr/lang/psi/OCConfigurationOwner.getKind:()Lcom/jetbrains/cidr/lang/OCLanguageKind;
        //   356: astore          10
        //   358: aload           10
        //   360: invokeinterface com/jetbrains/cidr/lang/OCLanguageKind.supportsPrecompiledHeaders:()Z
        //   365: ifeq            390
        //   368: aload           5
        //   370: aload           10
        //   372: aload           9
        //   374: invokestatic    com/intellij/openapi/util/Pair.create:(Ljava/lang/Object;Ljava/lang/Object;)Lcom/intellij/openapi/util/Pair;
        //   377: invokeinterface java/util/Set.add:(Ljava/lang/Object;)Z
        //   382: pop            
        //   383: goto            390
        //   386: invokestatic    com/jetbrains/cidr/lang/preprocessor/OCImportGraph.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   389: athrow         
        //   390: goto            202
        //   393: new             Lgnu/trove/THashSet;
        //   396: dup            
        //   397: invokespecial   gnu/trove/THashSet.<init>:()V
        //   400: astore          7
        //   402: aload           5
        //   404: invokeinterface java/util/Set.iterator:()Ljava/util/Iterator;
        //   409: astore          8
        //   411: aload           8
        //   413: invokeinterface java/util/Iterator.hasNext:()Z
        //   418: ifeq            486
        //   421: aload           8
        //   423: invokeinterface java/util/Iterator.next:()Ljava/lang/Object;
        //   428: checkcast       Lcom/intellij/openapi/util/Pair;
        //   431: astore          9
        //   433: aload           4
        //   435: ifnull          452
        //   438: aload           4
        //   440: invokeinterface com/intellij/openapi/progress/ProgressIndicator.checkCanceled:()V
        //   445: goto            452
        //   448: invokestatic    com/jetbrains/cidr/lang/preprocessor/OCImportGraph.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   451: athrow         
        //   452: aload           7
        //   454: aload_2        
        //   455: aload           9
        //   457: getfield        com/intellij/openapi/util/Pair.first:Ljava/lang/Object;
        //   460: checkcast       Lcom/jetbrains/cidr/lang/OCLanguageKind;
        //   463: aload           9
        //   465: getfield        com/intellij/openapi/util/Pair.second:Ljava/lang/Object;
        //   468: checkcast       Lcom/intellij/psi/PsiFile;
        //   471: invokestatic    com/jetbrains/cidr/lang/preprocessor/OCInclusionContext.initialPCHContextWithoutRoot:(Lcom/jetbrains/cidr/lang/workspace/OCResolveConfiguration;Lcom/jetbrains/cidr/lang/OCLanguageKind;Lcom/intellij/psi/PsiFile;)Lcom/jetbrains/cidr/lang/preprocessor/OCImmutableInclusionContext;
        //   474: invokeinterface com/jetbrains/cidr/lang/preprocessor/OCImmutableInclusionContext.getProcessedFiles:()Ljava/util/Set;
        //   479: invokevirtual   gnu/trove/THashSet.addAll:(Ljava/util/Collection;)Z
        //   482: pop            
        //   483: goto            411
        //   486: aload           7
        //   488: aload_3        
        //   489: invokevirtual   gnu/trove/THashSet.remove:(Ljava/lang/Object;)Z
        //   492: pop            
        //   493: aload           7
        //   495: invokestatic    java/util/Collections.unmodifiableSet:(Ljava/util/Set;)Ljava/util/Set;
        //   498: areturn        
        //    Signature:
        //  (Lcom/intellij/psi/PsiManager;Lcom/intellij/openapi/project/Project;Lcom/jetbrains/cidr/lang/workspace/OCResolveConfiguration;Lcom/intellij/openapi/vfs/VirtualFile;Lcom/intellij/openapi/progress/ProgressIndicator;)Ljava/util/Set<Lcom/intellij/openapi/vfs/VirtualFile;>;
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                                
        //  -----  -----  -----  -----  ------------------------------------
        //  0      40     40     44     Ljava/lang/IllegalArgumentException;
        //  44     84     84     88     Ljava/lang/IllegalArgumentException;
        //  88     128    128    132    Ljava/lang/IllegalArgumentException;
        //  132    172    172    176    Ljava/lang/IllegalArgumentException;
        //  224    244    247    251    Ljava/lang/IllegalArgumentException;
        //  235    256    259    263    Ljava/lang/IllegalArgumentException;
        //  251    270    273    277    Ljava/lang/IllegalArgumentException;
        //  277    298    298    302    Ljava/lang/IllegalArgumentException;
        //  302    319    319    323    Ljava/lang/IllegalArgumentException;
        //  326    339    342    346    Ljava/lang/IllegalArgumentException;
        //  358    383    386    390    Ljava/lang/IllegalArgumentException;
        //  433    445    448    452    Ljava/lang/IllegalArgumentException;
        // 
        // The error that occurred was:
        // 
        // java.lang.IllegalStateException: Expression is linked from several locations: Label_0251:
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
    public static Set<OCResolveConfiguration> getAllHeaderConfigurations(@NotNull final OCFile ocFile, @Nullable final ProgressIndicator progressIndicator) {
        try {
            if (ocFile == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "header", "com/jetbrains/cidr/lang/preprocessor/OCImportGraph", "getAllHeaderConfigurations"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        final Project project = ocFile.getProject();
        final VirtualFile virtualFile = OCInclusionContextUtil.getVirtualFile((PsiFile)ocFile);
        Label_0109: {
            Set<OCResolveConfiguration> set = null;
            Label_0074: {
                try {
                    if (virtualFile != null) {
                        break Label_0109;
                    }
                    set = Collections.emptySet();
                    if (set == null) {
                        break Label_0074;
                    }
                    return set;
                }
                catch (IllegalArgumentException ex2) {
                    throw a(ex2);
                }
                try {
                    set = Collections.emptySet();
                    if (set == null) {
                        throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/preprocessor/OCImportGraph", "getAllHeaderConfigurations"));
                    }
                }
                catch (IllegalArgumentException ex3) {
                    throw a(ex3);
                }
            }
            return set;
        }
        final HashSet<OCResolveConfiguration> set2 = new HashSet<OCResolveConfiguration>();
        final Iterator<VirtualFile> iterator = getAllHeaderRoots(ocFile.getProject(), virtualFile).iterator();
        while (iterator.hasNext()) {
            fillHeaderConfigurationsForRoot(project, virtualFile, iterator.next(), set2, progressIndicator);
        }
        Set<Object> unmodifiableSet;
        try {
            unmodifiableSet = (Set<Object>)Collections.unmodifiableSet((Set<? extends OCResolveConfiguration>)set2);
            if (unmodifiableSet == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/preprocessor/OCImportGraph", "getAllHeaderConfigurations"));
            }
        }
        catch (IllegalArgumentException ex4) {
            throw a(ex4);
        }
        return (Set<OCResolveConfiguration>)unmodifiableSet;
    }
    
    public static void fillHeaderConfigurationsForRoot(@NotNull final Project project, @NotNull final VirtualFile virtualFile, @NotNull final VirtualFile virtualFile2, @NotNull final Set<OCResolveConfiguration> set, @Nullable final ProgressIndicator progressIndicator) {
        try {
            if (project == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "project", "com/jetbrains/cidr/lang/preprocessor/OCImportGraph", "fillHeaderConfigurationsForRoot"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        try {
            if (virtualFile == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "header", "com/jetbrains/cidr/lang/preprocessor/OCImportGraph", "fillHeaderConfigurationsForRoot"));
            }
        }
        catch (IllegalArgumentException ex2) {
            throw a(ex2);
        }
        try {
            if (virtualFile2 == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "root", "com/jetbrains/cidr/lang/preprocessor/OCImportGraph", "fillHeaderConfigurationsForRoot"));
            }
        }
        catch (IllegalArgumentException ex3) {
            throw a(ex3);
        }
        try {
            if (set == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "result", "com/jetbrains/cidr/lang/preprocessor/OCImportGraph", "fillHeaderConfigurationsForRoot"));
            }
        }
        catch (IllegalArgumentException ex4) {
            throw a(ex4);
        }
        try {
            if (progressIndicator != null) {
                progressIndicator.checkCanceled();
            }
        }
        catch (IllegalArgumentException ex5) {
            throw a(ex5);
        }
        final Collection<? extends OCResolveConfiguration> allBuildConfigurationsOfTargetsOfFile = OCInclusionContextUtil.getAllBuildConfigurationsOfTargetsOfFile(virtualFile2, project);
        if (!OCInclusionContextUtil.isNeedToFindRoot(virtualFile2, project)) {
            for (final OCResolveConfiguration ocResolveConfiguration : allBuildConfigurationsOfTargetsOfFile) {
                try {
                    if (progressIndicator != null) {
                        progressIndicator.checkCanceled();
                    }
                }
                catch (IllegalArgumentException ex6) {
                    throw a(ex6);
                }
                try {
                    if (!getAllRootHeaders(ocResolveConfiguration, virtualFile2, progressIndicator).contains(virtualFile)) {
                        continue;
                    }
                    set.add(ocResolveConfiguration);
                }
                catch (IllegalArgumentException ex7) {
                    throw a(ex7);
                }
            }
        }
        else {
            set.addAll(allBuildConfigurationsOfTargetsOfFile);
        }
    }
    
    private static boolean a(@NotNull final Project p0, @NotNull final VirtualFile p1, @NotNull final Processor<VirtualFile> p2, final boolean p3) {
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
        //    24: ldc             "com/jetbrains/cidr/lang/preprocessor/OCImportGraph"
        //    26: aastore        
        //    27: dup            
        //    28: ldc             2
        //    30: ldc             "processPossibleRootsThatInclude"
        //    32: aastore        
        //    33: invokestatic    java/lang/String.format:(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
        //    36: invokespecial   java/lang/IllegalArgumentException.<init>:(Ljava/lang/String;)V
        //    39: athrow         
        //    40: invokestatic    com/jetbrains/cidr/lang/preprocessor/OCImportGraph.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
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
        //    62: ldc             "headerFile"
        //    64: aastore        
        //    65: dup            
        //    66: ldc             1
        //    68: ldc             "com/jetbrains/cidr/lang/preprocessor/OCImportGraph"
        //    70: aastore        
        //    71: dup            
        //    72: ldc             2
        //    74: ldc             "processPossibleRootsThatInclude"
        //    76: aastore        
        //    77: invokestatic    java/lang/String.format:(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
        //    80: invokespecial   java/lang/IllegalArgumentException.<init>:(Ljava/lang/String;)V
        //    83: athrow         
        //    84: invokestatic    com/jetbrains/cidr/lang/preprocessor/OCImportGraph.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
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
        //   106: ldc             "processor"
        //   108: aastore        
        //   109: dup            
        //   110: ldc             1
        //   112: ldc             "com/jetbrains/cidr/lang/preprocessor/OCImportGraph"
        //   114: aastore        
        //   115: dup            
        //   116: ldc             2
        //   118: ldc             "processPossibleRootsThatInclude"
        //   120: aastore        
        //   121: invokestatic    java/lang/String.format:(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
        //   124: invokespecial   java/lang/IllegalArgumentException.<init>:(Ljava/lang/String;)V
        //   127: athrow         
        //   128: invokestatic    com/jetbrains/cidr/lang/preprocessor/OCImportGraph.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   131: athrow         
        //   132: new             Ljava/util/ArrayList;
        //   135: dup            
        //   136: invokespecial   java/util/ArrayList.<init>:()V
        //   139: astore          4
        //   141: new             Ljava/util/HashSet;
        //   144: dup            
        //   145: invokespecial   java/util/HashSet.<init>:()V
        //   148: astore          5
        //   150: aload           4
        //   152: aload_1        
        //   153: invokevirtual   java/util/ArrayList.add:(Ljava/lang/Object;)Z
        //   156: pop            
        //   157: aload           4
        //   159: invokevirtual   java/util/ArrayList.isEmpty:()Z
        //   162: ifne            359
        //   165: new             Ljava/util/ArrayList;
        //   168: dup            
        //   169: invokespecial   java/util/ArrayList.<init>:()V
        //   172: astore          6
        //   174: aload           4
        //   176: invokevirtual   java/util/ArrayList.iterator:()Ljava/util/Iterator;
        //   179: astore          7
        //   181: aload           7
        //   183: invokeinterface java/util/Iterator.hasNext:()Z
        //   188: ifeq            352
        //   191: aload           7
        //   193: invokeinterface java/util/Iterator.next:()Ljava/lang/Object;
        //   198: checkcast       Lcom/intellij/openapi/vfs/VirtualFile;
        //   201: astore          8
        //   203: aload           8
        //   205: ifnull          181
        //   208: aload           5
        //   210: aload           8
        //   212: invokevirtual   java/util/HashSet.add:(Ljava/lang/Object;)Z
        //   215: ifne            225
        //   218: goto            181
        //   221: invokestatic    com/jetbrains/cidr/lang/preprocessor/OCImportGraph.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   224: athrow         
        //   225: aload_0        
        //   226: aload           8
        //   228: iload_3        
        //   229: invokestatic    com/jetbrains/cidr/lang/preprocessor/OCImportGraph.findImmediateIncludingFiles:(Lcom/intellij/openapi/project/Project;Lcom/intellij/openapi/vfs/VirtualFile;Z)Ljava/util/Collection;
        //   232: astore          9
        //   234: aload           9
        //   236: invokeinterface java/util/Collection.iterator:()Ljava/util/Iterator;
        //   241: astore          10
        //   243: aload           10
        //   245: invokeinterface java/util/Iterator.hasNext:()Z
        //   250: ifeq            349
        //   253: aload           10
        //   255: invokeinterface java/util/Iterator.next:()Ljava/lang/Object;
        //   260: checkcast       Lcom/intellij/openapi/vfs/VirtualFile;
        //   263: astore          11
        //   265: aload           11
        //   267: invokevirtual   com/intellij/openapi/vfs/VirtualFile.isValid:()Z
        //   270: ifne            280
        //   273: goto            243
        //   276: invokestatic    com/jetbrains/cidr/lang/preprocessor/OCImportGraph.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   279: athrow         
        //   280: aload_0        
        //   281: invokestatic    com/intellij/psi/PsiManager.getInstance:(Lcom/intellij/openapi/project/Project;)Lcom/intellij/psi/PsiManager;
        //   284: aload           11
        //   286: invokevirtual   com/intellij/psi/PsiManager.findFile:(Lcom/intellij/openapi/vfs/VirtualFile;)Lcom/intellij/psi/PsiFile;
        //   289: astore          12
        //   291: aload           12
        //   293: instanceof      Lcom/jetbrains/cidr/lang/psi/OCFile;
        //   296: ifeq            338
        //   299: aload           12
        //   301: invokestatic    com/jetbrains/cidr/lang/preprocessor/OCInclusionContextUtil.isNeedToFindRoot:(Lcom/intellij/psi/PsiFile;)Z
        //   304: ifne            338
        //   307: goto            314
        //   310: invokestatic    com/jetbrains/cidr/lang/preprocessor/OCImportGraph.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   313: athrow         
        //   314: aload_2        
        //   315: aload           11
        //   317: invokeinterface com/intellij/util/Processor.process:(Ljava/lang/Object;)Z
        //   322: ifne            338
        //   325: goto            332
        //   328: invokestatic    com/jetbrains/cidr/lang/preprocessor/OCImportGraph.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   331: athrow         
        //   332: iconst_0       
        //   333: ireturn        
        //   334: invokestatic    com/jetbrains/cidr/lang/preprocessor/OCImportGraph.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   337: athrow         
        //   338: aload           6
        //   340: aload           11
        //   342: invokevirtual   java/util/ArrayList.add:(Ljava/lang/Object;)Z
        //   345: pop            
        //   346: goto            243
        //   349: goto            181
        //   352: aload           6
        //   354: astore          4
        //   356: goto            157
        //   359: iconst_1       
        //   360: ireturn        
        //    Signature:
        //  (Lcom/intellij/openapi/project/Project;Lcom/intellij/openapi/vfs/VirtualFile;Lcom/intellij/util/Processor<Lcom/intellij/openapi/vfs/VirtualFile;>;Z)Z
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                                
        //  -----  -----  -----  -----  ------------------------------------
        //  0      40     40     44     Ljava/lang/IllegalArgumentException;
        //  44     84     84     88     Ljava/lang/IllegalArgumentException;
        //  88     128    128    132    Ljava/lang/IllegalArgumentException;
        //  208    221    221    225    Ljava/lang/IllegalArgumentException;
        //  265    276    276    280    Ljava/lang/IllegalArgumentException;
        //  291    307    310    314    Ljava/lang/IllegalArgumentException;
        //  299    325    328    332    Ljava/lang/IllegalArgumentException;
        //  314    334    334    338    Ljava/lang/IllegalArgumentException;
        // 
        // The error that occurred was:
        // 
        // java.lang.IllegalStateException: Expression is linked from several locations: Label_0314:
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
    public static Collection<VirtualFile> findAllRootsThatIncludes(@NotNull final Project project, @NotNull final VirtualFile virtualFile, final boolean b) {
        try {
            if (project == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "project", "com/jetbrains/cidr/lang/preprocessor/OCImportGraph", "findAllRootsThatIncludes"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        try {
            if (virtualFile == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "original", "com/jetbrains/cidr/lang/preprocessor/OCImportGraph", "findAllRootsThatIncludes"));
            }
        }
        catch (IllegalArgumentException ex2) {
            throw a(ex2);
        }
        Label_0146: {
            List<VirtualFile> list = null;
            Label_0111: {
                try {
                    if (OCInclusionContextUtil.isNeedToFindRoot(virtualFile, project)) {
                        break Label_0146;
                    }
                    final VirtualFile virtualFile2 = virtualFile;
                    list = Collections.singletonList(virtualFile2);
                    if (list == null) {
                        break Label_0111;
                    }
                    return list;
                }
                catch (IllegalArgumentException ex3) {
                    throw a(ex3);
                }
                try {
                    final VirtualFile virtualFile2 = virtualFile;
                    list = Collections.singletonList(virtualFile2);
                    if (list == null) {
                        throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/preprocessor/OCImportGraph", "findAllRootsThatIncludes"));
                    }
                }
                catch (IllegalArgumentException ex4) {
                    throw a(ex4);
                }
            }
            return list;
        }
        final PsiFile file = PsiManager.getInstance(project).findFile(virtualFile);
        Label_0212: {
            List<VirtualFile> list2 = null;
            Label_0177: {
                try {
                    if (file instanceof OCFile) {
                        break Label_0212;
                    }
                    final VirtualFile virtualFile3 = virtualFile;
                    list2 = Collections.singletonList(virtualFile3);
                    if (list2 == null) {
                        break Label_0177;
                    }
                    return list2;
                }
                catch (IllegalArgumentException ex5) {
                    throw a(ex5);
                }
                try {
                    final VirtualFile virtualFile3 = virtualFile;
                    list2 = Collections.singletonList(virtualFile3);
                    if (list2 == null) {
                        throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/preprocessor/OCImportGraph", "findAllRootsThatIncludes"));
                    }
                }
                catch (IllegalArgumentException ex6) {
                    throw a(ex6);
                }
            }
            return list2;
        }
        final THashSet set = new THashSet();
        List<VirtualFile> list3 = null;
        Label_0263: {
            try {
                a(project, virtualFile, (Processor<VirtualFile>)(virtualFile -> {
                    set.add((Object)virtualFile);
                    return true;
                }), b);
                if (set.isEmpty()) {
                    final ArrayList singletonList;
                    list3 = (List<VirtualFile>)(singletonList = (ArrayList)Collections.singletonList(virtualFile));
                    break Label_0263;
                }
            }
            catch (IllegalArgumentException ex7) {
                throw a(ex7);
            }
            ArrayList singletonList;
            list3 = (List<VirtualFile>)(singletonList = new ArrayList<VirtualFile>((Collection)set));
            try {
                if (singletonList == null) {
                    throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/preprocessor/OCImportGraph", "findAllRootsThatIncludes"));
                }
            }
            catch (IllegalArgumentException ex8) {
                throw a(ex8);
            }
        }
        return list3;
    }
    
    @NotNull
    public static Collection<VirtualFile> findImmediateIncludingFiles(@NotNull final Project project, @NotNull final VirtualFile virtualFile, final boolean b) {
        try {
            if (project == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "project", "com/jetbrains/cidr/lang/preprocessor/OCImportGraph", "findImmediateIncludingFiles"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        try {
            if (virtualFile == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "file", "com/jetbrains/cidr/lang/preprocessor/OCImportGraph", "findImmediateIncludingFiles"));
            }
        }
        catch (IllegalArgumentException ex2) {
            throw a(ex2);
        }
        Collection<VirtualFile> value;
        try {
            value = ((Cache)OCImportGraph.IMPORTS_GRAPH.getValue((UserDataHolder)project)).get(virtualFile, b);
            if (value == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/preprocessor/OCImportGraph", "findImmediateIncludingFiles"));
            }
        }
        catch (IllegalArgumentException ex3) {
            throw a(ex3);
        }
        return value;
    }
    
    public static void addHeaderIncluder(@NotNull final Project project, @NotNull final VirtualFile virtualFile, @NotNull final VirtualFile virtualFile2) {
        try {
            if (project == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "project", "com/jetbrains/cidr/lang/preprocessor/OCImportGraph", "addHeaderIncluder"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        try {
            if (virtualFile == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "header", "com/jetbrains/cidr/lang/preprocessor/OCImportGraph", "addHeaderIncluder"));
            }
        }
        catch (IllegalArgumentException ex2) {
            throw a(ex2);
        }
        try {
            if (virtualFile2 == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "includer", "com/jetbrains/cidr/lang/preprocessor/OCImportGraph", "addHeaderIncluder"));
            }
        }
        catch (IllegalArgumentException ex3) {
            throw a(ex3);
        }
        ((Cache)OCImportGraph.IMPORTS_GRAPH.getValue((UserDataHolder)project)).add(virtualFile, virtualFile2);
    }
    
    public static void removeHeaderIncluder(@NotNull final Project project, @NotNull final VirtualFile virtualFile, @NotNull final VirtualFile virtualFile2) {
        try {
            if (project == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "project", "com/jetbrains/cidr/lang/preprocessor/OCImportGraph", "removeHeaderIncluder"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        try {
            if (virtualFile == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "header", "com/jetbrains/cidr/lang/preprocessor/OCImportGraph", "removeHeaderIncluder"));
            }
        }
        catch (IllegalArgumentException ex2) {
            throw a(ex2);
        }
        try {
            if (virtualFile2 == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "includer", "com/jetbrains/cidr/lang/preprocessor/OCImportGraph", "removeHeaderIncluder"));
            }
        }
        catch (IllegalArgumentException ex3) {
            throw a(ex3);
        }
        ((Cache)OCImportGraph.IMPORTS_GRAPH.getValue((UserDataHolder)project)).remove(virtualFile, virtualFile2);
    }
    
    static {
        boolean $assertionsDisabled2 = false;
        Label_0017: {
            try {
                if (!OCImportGraph.class.desiredAssertionStatus()) {
                    $assertionsDisabled2 = true;
                    break Label_0017;
                }
            }
            catch (IllegalArgumentException ex) {
                throw a(ex);
            }
            $assertionsDisabled2 = false;
        }
        $assertionsDisabled = $assertionsDisabled2;
        HEADER_TO_ALL_ROOTS_CACHE = NotNullLazyKey.create("HEADER_TO_ALL_ROOTS_CACHE", project -> new ConcurrentMultiMap());
        ROOT_TO_ALL_HEADERS_CACHE = NotNullLazyKey.create("ROOT_TO_ALL_HEADERS_CACHE", ocResolveConfiguration -> new ConcurrentHashMap());
        IMPORTS_GRAPH = NotNullLazyKey.create("IMPORT_GRAPH", project -> new Cache(project));
    }
    
    private static IllegalArgumentException a(final IllegalArgumentException ex) {
        return ex;
    }
    
    private static class Cache
    {
        @NotNull
        private final Project myProject;
        @NotNull
        private final AtomicInteger isEnsuringFilesProcessed;
        @NotNull
        private final Object myLock;
        MultiMap<VirtualFile, VirtualFile> myHeaderToIncluders;
        MultiMap<VirtualFile, VirtualFile> myAddOnlyHeaderToIncluders;
        
        private Cache(@NotNull final Project myProject) {
            if (myProject == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "project", "com/jetbrains/cidr/lang/preprocessor/OCImportGraph$Cache", "<init>"));
            }
            this.isEnsuringFilesProcessed = new AtomicInteger();
            this.myLock = new Object();
            this.myHeaderToIncluders = (MultiMap<VirtualFile, VirtualFile>)MultiMap.createConcurrentSet();
            this.myAddOnlyHeaderToIncluders = (MultiMap<VirtualFile, VirtualFile>)MultiMap.createConcurrentSet();
            this.myProject = myProject;
        }
        
        @NotNull
        public Collection<VirtualFile> get(@NotNull final VirtualFile virtualFile, final boolean b) {
            try {
                if (virtualFile == null) {
                    throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "header", "com/jetbrains/cidr/lang/preprocessor/OCImportGraph$Cache", "get"));
                }
            }
            catch (IllegalArgumentException ex) {
                throw a(ex);
            }
            if (b) {
                final Application application = ApplicationManager.getApplication();
                boolean b3 = false;
                Label_0092: {
                    Label_0083: {
                        try {
                            application.assertReadAccessAllowed();
                            if (!application.isDispatchThread()) {
                                break Label_0083;
                            }
                            final Application application2 = application;
                            final boolean b2 = application2.isUnitTestMode();
                            if (b2) {
                                break Label_0083;
                            }
                            break Label_0083;
                        }
                        catch (IllegalArgumentException ex2) {
                            throw a(ex2);
                        }
                        try {
                            final Application application2 = application;
                            final boolean b2 = application2.isUnitTestMode();
                            if (b2) {
                                b3 = true;
                                break Label_0092;
                            }
                        }
                        catch (IllegalArgumentException ex3) {
                            throw a(ex3);
                        }
                    }
                    b3 = false;
                }
                this.a(b3);
            }
            synchronized (this.myLock) {
                if (!b) {
                    final Collection<VirtualFile> a = this.a(virtualFile, true);
                    if (a != null) {
                        final Collection<VirtualFile> collection = a;
                        // monitorexit(this.myLock)
                        if (collection == null) {
                            throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/preprocessor/OCImportGraph$Cache", "get"));
                        }
                        return collection;
                    }
                }
                final Collection<VirtualFile> a2 = this.a(virtualFile, false);
                // monitorexit(this.myLock)
                if (a2 == null) {
                    throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/preprocessor/OCImportGraph$Cache", "get"));
                }
                return a2;
            }
        }
        
        @Nullable
        @Contract("_, false -> !null")
        private Collection<VirtualFile> a(@NotNull final VirtualFile virtualFile, final boolean b) {
            try {
                if (virtualFile == null) {
                    throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "header", "com/jetbrains/cidr/lang/preprocessor/OCImportGraph$Cache", "getInner"));
                }
            }
            catch (IllegalArgumentException ex) {
                throw a(ex);
            }
            synchronized (this.myLock) {
                final MultiMap<VirtualFile, VirtualFile> multiMap = b ? this.myAddOnlyHeaderToIncluders : this.myHeaderToIncluders;
                Label_0092: {
                    try {
                        if (!b || multiMap.containsKey((Object)virtualFile)) {
                            break Label_0092;
                        }
                    }
                    catch (IllegalArgumentException ex2) {
                        throw a(ex2);
                    }
                    return null;
                }
                Collection collection = multiMap.get((Object)virtualFile);
                boolean b2 = true;
                final Iterator<VirtualFile> iterator = collection.iterator();
                while (iterator.hasNext()) {
                    if (!iterator.next().isValid()) {
                        b2 = false;
                        break;
                    }
                }
                if (!b2) {
                    for (final VirtualFile virtualFile2 : new ArrayList<VirtualFile>(collection)) {
                        try {
                            if (virtualFile2.isValid()) {
                                continue;
                            }
                            multiMap.remove((Object)virtualFile, (Object)virtualFile2);
                        }
                        catch (IllegalArgumentException ex3) {
                            throw a(ex3);
                        }
                    }
                    collection = multiMap.get((Object)virtualFile);
                    try {
                        if (!b) {
                            OCImportGraph.invalidateHeaderRootsCache(this.myProject);
                        }
                    }
                    catch (IllegalArgumentException ex4) {
                        throw a(ex4);
                    }
                }
                return (Collection<VirtualFile>)collection;
            }
        }
        
        private void a(final boolean b) {
            if (b) {
                this.isEnsuringFilesProcessed.incrementAndGet();
                try {
                    this.a((ProgressIndicator)null);
                    return;
                }
                finally {
                    this.isEnsuringFilesProcessed.decrementAndGet();
                }
            }
            try {
                if (this.isEnsuringFilesProcessed.incrementAndGet() > 1) {
                    this.isEnsuringFilesProcessed.decrementAndGet();
                    return;
                }
            }
            catch (IllegalArgumentException ex) {
                throw a(ex);
            }
            ProgressIndicatorUtils.scheduleWithWriteActionPriority(new ReadTask() {
                @Override
                public void computeInReadAction(@NotNull final ProgressIndicator progressIndicator) {
                    try {
                        if (progressIndicator == null) {
                            throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "indicator", "com/jetbrains/cidr/lang/preprocessor/OCImportGraph$Cache$1", "computeInReadAction"));
                        }
                    }
                    catch (IllegalArgumentException ex) {
                        throw a(ex);
                    }
                    try {
                        Cache.this.a(progressIndicator);
                    }
                    finally {
                        Cache.this.isEnsuringFilesProcessed.decrementAndGet();
                    }
                }
                
                @Override
                public void onCanceled(@NotNull final ProgressIndicator progressIndicator) {
                    try {
                        if (progressIndicator == null) {
                            throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "indicator", "com/jetbrains/cidr/lang/preprocessor/OCImportGraph$Cache$1", "onCanceled"));
                        }
                    }
                    catch (IllegalArgumentException ex) {
                        throw a(ex);
                    }
                    Cache.this.a(false);
                }
                
                private static IllegalArgumentException a(final IllegalArgumentException ex) {
                    return ex;
                }
            });
        }
        
        private void a(@Nullable final ProgressIndicator progressIndicator) {
            Label_0034: {
                try {
                    if (this.myProject.isDisposed()) {
                        return;
                    }
                    final Cache cache = this;
                    final Project project = cache.myProject;
                    final boolean b = FileSymbolTablesCache.areSymbolsLoaded(project);
                    if (!b) {
                        return;
                    }
                    break Label_0034;
                }
                catch (IllegalArgumentException ex) {
                    throw a(ex);
                }
                try {
                    final Cache cache = this;
                    final Project project = cache.myProject;
                    final boolean b = FileSymbolTablesCache.areSymbolsLoaded(project);
                    if (!b) {
                        return;
                    }
                }
                catch (IllegalArgumentException ex2) {
                    throw a(ex2);
                }
            }
            FileSymbolTablesCache.getInstance(this.myProject).ensurePendingFilesProcessed(progressIndicator, true);
            synchronized (this.myLock) {
                this.myAddOnlyHeaderToIncluders.clear();
            }
        }
        
        public void add(@NotNull final VirtualFile virtualFile, @NotNull final VirtualFile virtualFile2) {
            try {
                if (virtualFile == null) {
                    throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "header", "com/jetbrains/cidr/lang/preprocessor/OCImportGraph$Cache", "add"));
                }
            }
            catch (IllegalArgumentException ex) {
                throw a(ex);
            }
            try {
                if (virtualFile2 == null) {
                    throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "includer", "com/jetbrains/cidr/lang/preprocessor/OCImportGraph$Cache", "add"));
                }
            }
            catch (IllegalArgumentException ex2) {
                throw a(ex2);
            }
            synchronized (this.myLock) {
                this.myHeaderToIncluders.putValue((Object)virtualFile, (Object)virtualFile2);
                if (this.myAddOnlyHeaderToIncluders.containsKey((Object)virtualFile)) {
                    this.myAddOnlyHeaderToIncluders.putValue((Object)virtualFile, (Object)virtualFile2);
                }
                OCImportGraph.invalidateHeaderRootsCache(this.myProject);
            }
        }
        
        public void remove(@NotNull final VirtualFile virtualFile, @NotNull final VirtualFile virtualFile2) {
            try {
                if (virtualFile == null) {
                    throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "header", "com/jetbrains/cidr/lang/preprocessor/OCImportGraph$Cache", "remove"));
                }
            }
            catch (IllegalArgumentException ex) {
                throw a(ex);
            }
            try {
                if (virtualFile2 == null) {
                    throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "includer", "com/jetbrains/cidr/lang/preprocessor/OCImportGraph$Cache", "remove"));
                }
            }
            catch (IllegalArgumentException ex2) {
                throw a(ex2);
            }
            synchronized (this.myLock) {
                if (this.myHeaderToIncluders.remove((Object)virtualFile, (Object)virtualFile2)) {
                    try {
                        if (!this.myAddOnlyHeaderToIncluders.containsKey((Object)virtualFile)) {
                            this.myAddOnlyHeaderToIncluders.putValues((Object)virtualFile, this.myHeaderToIncluders.get((Object)virtualFile));
                            this.myAddOnlyHeaderToIncluders.putValue((Object)virtualFile, (Object)virtualFile2);
                        }
                    }
                    catch (IllegalArgumentException ex3) {
                        throw a(ex3);
                    }
                    OCImportGraph.invalidateHeaderRootsCache(this.myProject);
                }
            }
        }
        
        private static IllegalArgumentException a(final IllegalArgumentException ex) {
            return ex;
        }
    }
}
