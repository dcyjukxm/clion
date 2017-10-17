// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.lang.preprocessor;

import com.jetbrains.cidr.lang.workspace.OCWorkspaceManager;
import java.util.LinkedHashSet;
import com.intellij.util.containers.MultiMap;
import com.intellij.util.containers.ContainerUtil;
import com.intellij.openapi.util.ModificationTracker;
import com.intellij.psi.util.CachedValueProvider;
import com.intellij.testFramework.LightVirtualFileBase;
import com.jetbrains.cidr.lang.symbols.OCForeignSymbol;
import com.intellij.openapi.util.Comparing;
import com.jetbrains.cidr.lang.symbols.DeepEqual;
import org.jetbrains.annotations.Contract;
import com.jetbrains.cidr.lang.resolve.OCResolveUtil;
import com.jetbrains.cidr.lang.workspace.OCResolveRootAndConfiguration;
import com.jetbrains.cidr.lang.OCIncludeHelpers;
import com.intellij.openapi.util.Ref;
import com.jetbrains.cidr.lang.util.OCImmutableList;
import java.util.Collection;
import com.jetbrains.cidr.lang.symbols.cpp.OCIncludeSymbol;
import com.intellij.openapi.util.text.StringUtil;
import com.jetbrains.cidr.lang.workspace.OCLanguageKindCalculator;
import com.intellij.openapi.diagnostic.Logger;
import com.jetbrains.cidr.lang.OCLog;
import gnu.trove.THashSet;
import java.util.Iterator;
import java.util.ArrayList;
import com.intellij.psi.PsiManager;
import java.util.Collections;
import com.intellij.openapi.util.UserDataHolder;
import com.intellij.psi.util.CachedValuesManager;
import com.intellij.openapi.util.UserDataHolderEx;
import com.intellij.openapi.util.Couple;
import com.intellij.psi.util.CachedValue;
import com.intellij.openapi.util.Key;
import com.intellij.openapi.util.SimpleModificationTracker;
import com.jetbrains.cidr.lang.symbols.symtable.FileSymbolTable;
import java.util.Map;
import com.intellij.openapi.util.NotNullLazyKey;
import com.jetbrains.cidr.lang.workspace.compiler.OCCompilerFeatures;
import java.util.concurrent.ConcurrentHashMap;
import com.jetbrains.cidr.lang.symbols.OCSymbol;
import com.intellij.util.Processor;
import com.jetbrains.cidr.lang.psi.OCFile;
import java.util.List;
import com.intellij.psi.PsiFile;
import com.intellij.openapi.vfs.VirtualFile;
import java.util.Set;
import com.jetbrains.cidr.lang.symbols.cpp.OCMacroSymbol;
import gnu.trove.THashMap;
import com.intellij.openapi.project.Project;
import org.jetbrains.annotations.Nullable;
import com.jetbrains.cidr.lang.workspace.OCResolveConfiguration;
import org.jetbrains.annotations.NotNull;
import com.jetbrains.cidr.lang.OCLanguageKind;

public class OCInclusionContextImpl implements OCInclusionContext
{
    static int DEFAULT_MAX_INCLUSION_LEVEL;
    @NotNull
    private final OCLanguageKind myLanguageKind;
    private final boolean myNotifyLocalDefinitions;
    @Nullable
    private final OCResolveConfiguration myConfiguration;
    @NotNull
    private final Project myProject;
    @NotNull
    private final THashMap<String, OCMacroSymbol> mySubstitutions;
    @NotNull
    private final Set<String> myUndefList;
    @NotNull
    private volatile OCParsingNameScope myNameScope;
    @NotNull
    private final Set<VirtualFile> myProcessedFiles;
    @Nullable
    private final OCInclusionContextImpl myParentContext;
    private final int myInclusionLevel;
    @Nullable
    private PsiFile myRootFile;
    private volatile List<OCFile> myPrecompiledHeaders;
    @Nullable
    private SignatureBuilder mySignatureBuilder;
    private Processor<OCSymbol> myProcessingListener;
    @Nullable
    private OCContextChangeBuilder myChangeBuilder;
    private THashMap<String, OCMacroSymbol> myOverriddenSubstitutions;
    @NotNull
    private static final Object NULL_VALUE;
    @NotNull
    private final ConcurrentHashMap<OCCompilerFeatures.Type, Object> myCompilerFeatures;
    @Nullable
    private ResolvePathListener myResolvePathListener;
    static final NotNullLazyKey<Map<FileSymbolTable, Boolean>, OCResolveConfiguration> INCLUDE_RESOLVE_CACHE;
    static final NotNullLazyKey<SimpleModificationTracker, Project> PROJECT_MODIFICATION_TRACKER;
    private static final Key<CachedValue<PCHCache>> PCH_CACHE_KEY;
    static final NotNullLazyKey<Couple<ConcurrentHashMap<String, OCImmutableInclusionContext>>, OCResolveConfiguration> INITIAL_PLAIN_AND_PCH_CONTEXTS_KEY;
    static final /* synthetic */ boolean $assertionsDisabled;
    
    @NotNull
    static PCHCache getPCHCache(@NotNull final Project project) {
        try {
            if (project == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "project", "com/jetbrains/cidr/lang/preprocessor/OCInclusionContextImpl", "getPCHCache"));
            }
        }
        catch (IllegalStateException ex) {
            throw a(ex);
        }
        CachedValue cachedValue = (CachedValue)project.getUserData((Key)OCInclusionContextImpl.PCH_CACHE_KEY);
        if (cachedValue == null) {
            cachedValue = (CachedValue)((UserDataHolderEx)project).putUserDataIfAbsent((Key)OCInclusionContextImpl.PCH_CACHE_KEY, (Object)CachedValuesManager.getManager(project).createCachedValue(() -> {
                try {
                    if (project == null) {
                        throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "project", "com/jetbrains/cidr/lang/preprocessor/OCInclusionContextImpl", "lambda$getPCHCache$2"));
                    }
                }
                catch (IllegalStateException ex) {
                    throw a(ex);
                }
                return new CachedValueProvider.Result((Object)new PCHCache(project), new Object[] { simpleModificationTracker });
            }, false));
        }
        Label_0173: {
            PCHCache pchCache = null;
            Label_0138: {
                try {
                    if (!cachedValue.hasUpToDateValue()) {
                        break Label_0173;
                    }
                    final CachedValue cachedValue2 = cachedValue;
                    final Object o = cachedValue2.getValue();
                    pchCache = (PCHCache)o;
                    if (pchCache == null) {
                        break Label_0138;
                    }
                    return pchCache;
                }
                catch (IllegalStateException ex2) {
                    throw a(ex2);
                }
                try {
                    final CachedValue cachedValue2 = cachedValue;
                    final Object o = cachedValue2.getValue();
                    pchCache = (PCHCache)o;
                    if (pchCache == null) {
                        throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/preprocessor/OCInclusionContextImpl", "getPCHCache"));
                    }
                }
                catch (IllegalStateException ex3) {
                    throw a(ex3);
                }
            }
            return pchCache;
        }
        synchronized (cachedValue) {
            final PCHCache pchCache2 = (PCHCache)cachedValue.getValue();
            // monitorexit(cachedValue)
            if (pchCache2 == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/preprocessor/OCInclusionContextImpl", "getPCHCache"));
            }
            return pchCache2;
        }
    }
    
    @NotNull
    static List<OCFile> getPrecompiledHeaders(@NotNull final OCResolveConfiguration ocResolveConfiguration, @NotNull final OCLanguageKind ocLanguageKind, @NotNull final VirtualFile virtualFile) {
        try {
            if (ocResolveConfiguration == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "config", "com/jetbrains/cidr/lang/preprocessor/OCInclusionContextImpl", "getPrecompiledHeaders"));
            }
        }
        catch (IllegalStateException ex) {
            throw a(ex);
        }
        try {
            if (ocLanguageKind == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "kind", "com/jetbrains/cidr/lang/preprocessor/OCInclusionContextImpl", "getPrecompiledHeaders"));
            }
        }
        catch (IllegalStateException ex2) {
            throw a(ex2);
        }
        try {
            if (virtualFile == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "sourceFile", "com/jetbrains/cidr/lang/preprocessor/OCInclusionContextImpl", "getPrecompiledHeaders"));
            }
        }
        catch (IllegalStateException ex3) {
            throw a(ex3);
        }
        final List<VirtualFile> precompiledHeaders = ocResolveConfiguration.getPrecompiledHeaders(ocLanguageKind, virtualFile);
        Label_0199: {
            List<OCFile> list = null;
            Label_0164: {
                try {
                    if (!precompiledHeaders.isEmpty()) {
                        break Label_0199;
                    }
                    list = Collections.emptyList();
                    if (list == null) {
                        break Label_0164;
                    }
                    return list;
                }
                catch (IllegalStateException ex4) {
                    throw a(ex4);
                }
                try {
                    list = Collections.emptyList();
                    if (list == null) {
                        throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/preprocessor/OCInclusionContextImpl", "getPrecompiledHeaders"));
                    }
                }
                catch (IllegalStateException ex5) {
                    throw a(ex5);
                }
            }
            return list;
        }
        final PsiManager instance = PsiManager.getInstance(ocResolveConfiguration.getProject());
        final ArrayList list2 = new ArrayList<OCFile>(precompiledHeaders.size());
        for (final VirtualFile virtualFile2 : precompiledHeaders) {
            if (virtualFile2.isValid()) {
                final PsiFile file = instance.findFile(virtualFile2);
                try {
                    if (!(file instanceof OCFile)) {
                        continue;
                    }
                    list2.add((OCFile)file);
                }
                catch (IllegalStateException ex6) {
                    throw a(ex6);
                }
            }
        }
        ArrayList<OCFile> list3;
        try {
            list3 = (ArrayList<OCFile>)list2;
            if (list3 == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/preprocessor/OCInclusionContextImpl", "getPrecompiledHeaders"));
            }
        }
        catch (IllegalStateException ex7) {
            throw a(ex7);
        }
        return list3;
    }
    
    protected OCInclusionContextImpl(@Nullable final OCResolveConfiguration myConfiguration, @NotNull final Project myProject, @NotNull final OCLanguageKind myLanguageKind) {
        if (myProject == null) {
            throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "project", "com/jetbrains/cidr/lang/preprocessor/OCInclusionContextImpl", "<init>"));
        }
        if (myLanguageKind == null) {
            throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "languageKind", "com/jetbrains/cidr/lang/preprocessor/OCInclusionContextImpl", "<init>"));
        }
        this.mySubstitutions = (THashMap<String, OCMacroSymbol>)new THashMap();
        this.myUndefList = (Set<String>)new THashSet();
        this.myProcessedFiles = (Set<VirtualFile>)new THashSet();
        this.mySignatureBuilder = null;
        Label_0164: {
            if (!OCInclusionContextImpl.$assertionsDisabled) {
                Label_0152: {
                    try {
                        if (myConfiguration == null) {
                            break Label_0164;
                        }
                        final Project project = myProject;
                        final OCResolveConfiguration ocResolveConfiguration = myConfiguration;
                        final Project project2 = ocResolveConfiguration.getProject();
                        final boolean b = project.equals(project2);
                        if (!b) {
                            break Label_0152;
                        }
                        break Label_0164;
                    }
                    catch (IllegalStateException ex) {
                        throw a(ex);
                    }
                    try {
                        final Project project = myProject;
                        final OCResolveConfiguration ocResolveConfiguration = myConfiguration;
                        final Project project2 = ocResolveConfiguration.getProject();
                        final boolean b = project.equals(project2);
                        if (!b) {
                            throw new AssertionError();
                        }
                    }
                    catch (IllegalStateException ex2) {
                        throw a(ex2);
                    }
                }
            }
        }
        this.myConfiguration = myConfiguration;
        this.myProject = myProject;
        this.myLanguageKind = myLanguageKind;
        this.myNameScope = new OCParsingNameScope();
        this.myParentContext = null;
        this.myInclusionLevel = 0;
        this.myNotifyLocalDefinitions = false;
        this.myOverriddenSubstitutions = (THashMap<String, OCMacroSymbol>)new THashMap();
        this.myCompilerFeatures = new ConcurrentHashMap<OCCompilerFeatures.Type, Object>();
    }
    
    private OCInclusionContextImpl(@Nullable final OCResolveConfiguration myConfiguration, @NotNull final Project myProject, @NotNull final OCInclusionContextImpl myParentContext, @NotNull final PsiFile myRootFile, @NotNull final OCParsingNameScope myNameScope, final boolean myNotifyLocalDefinitions) {
        if (myProject == null) {
            throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "project", "com/jetbrains/cidr/lang/preprocessor/OCInclusionContextImpl", "<init>"));
        }
        if (myParentContext == null) {
            throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "parentContext", "com/jetbrains/cidr/lang/preprocessor/OCInclusionContextImpl", "<init>"));
        }
        if (myRootFile == null) {
            throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "rootFile", "com/jetbrains/cidr/lang/preprocessor/OCInclusionContextImpl", "<init>"));
        }
        if (myNameScope == null) {
            throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "nameScope", "com/jetbrains/cidr/lang/preprocessor/OCInclusionContextImpl", "<init>"));
        }
        this.mySubstitutions = (THashMap<String, OCMacroSymbol>)new THashMap();
        this.myUndefList = (Set<String>)new THashSet();
        this.myProcessedFiles = (Set<VirtualFile>)new THashSet();
        this.mySignatureBuilder = null;
        this.myConfiguration = myConfiguration;
        this.myProject = myProject;
        this.myLanguageKind = myParentContext.myLanguageKind;
        this.myParentContext = myParentContext;
        this.myRootFile = myRootFile;
        this.myNameScope = myNameScope;
        this.myInclusionLevel = this.myParentContext.myInclusionLevel + 1;
        this.myNotifyLocalDefinitions = myNotifyLocalDefinitions;
        this.myOverriddenSubstitutions = myParentContext.myOverriddenSubstitutions;
        this.myCompilerFeatures = myParentContext.myCompilerFeatures;
        this.myResolvePathListener = myParentContext.myResolvePathListener;
        final Project project = (myConfiguration != null) ? myConfiguration.getProject() : null;
        Logger log = null;
        boolean b = false;
        Label_0324: {
            try {
                log = OCLog.LOG;
                if (this.myInclusionLevel <= OCInclusionContext.getMaxInclusionLevel(project) + 2) {
                    b = true;
                    break Label_0324;
                }
            }
            catch (IllegalStateException ex) {
                throw a(ex);
            }
            b = false;
        }
        log.assertTrue(b, (Object)"Inclusion level is too high");
    }
    
    @NotNull
    @Override
    public OCLanguageKind getLanguageKind() {
        OCLanguageKind myLanguageKind;
        try {
            myLanguageKind = this.myLanguageKind;
            if (myLanguageKind == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/preprocessor/OCInclusionContextImpl", "getLanguageKind"));
            }
        }
        catch (IllegalStateException ex) {
            throw a(ex);
        }
        return myLanguageKind;
    }
    
    @Override
    public void addProcessedFile(@NotNull final VirtualFile virtualFile) {
        try {
            if (virtualFile == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "file", "com/jetbrains/cidr/lang/preprocessor/OCInclusionContextImpl", "addProcessedFile"));
            }
        }
        catch (IllegalStateException ex) {
            throw a(ex);
        }
        try {
            if (this.myChangeBuilder != null) {
                this.myChangeBuilder.addProcessedFile(virtualFile);
            }
        }
        catch (IllegalStateException ex2) {
            throw a(ex2);
        }
        this.myProcessedFiles.add(virtualFile);
    }
    
    @Override
    public boolean checkConformanceAndFillSignatures(@NotNull final FileSymbolTable p0) {
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
        //    18: ldc             "table"
        //    20: aastore        
        //    21: dup            
        //    22: ldc             1
        //    24: ldc             "com/jetbrains/cidr/lang/preprocessor/OCInclusionContextImpl"
        //    26: aastore        
        //    27: dup            
        //    28: ldc             2
        //    30: ldc             "checkConformanceAndFillSignatures"
        //    32: aastore        
        //    33: invokestatic    java/lang/String.format:(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
        //    36: invokespecial   java/lang/IllegalArgumentException.<init>:(Ljava/lang/String;)V
        //    39: athrow         
        //    40: invokestatic    com/jetbrains/cidr/lang/preprocessor/OCInclusionContextImpl.a:(Ljava/lang/IllegalStateException;)Ljava/lang/IllegalStateException;
        //    43: athrow         
        //    44: aload_0        
        //    45: invokespecial   com/jetbrains/cidr/lang/preprocessor/OCInclusionContextImpl.d:()V
        //    48: iconst_0       
        //    49: istore_2       
        //    50: aload_1        
        //    51: invokevirtual   com/jetbrains/cidr/lang/symbols/symtable/FileSymbolTable.getSignature:()Lcom/jetbrains/cidr/lang/symbols/symtable/ContextSignature;
        //    54: astore_3       
        //    55: aload_3        
        //    56: aload_0        
        //    57: invokevirtual   com/jetbrains/cidr/lang/symbols/symtable/ContextSignature.isCompatible:(Lcom/jetbrains/cidr/lang/preprocessor/OCInclusionContext;)Z
        //    60: ifeq            102
        //    63: aload_0        
        //    64: getfield        com/jetbrains/cidr/lang/preprocessor/OCInclusionContextImpl.myConfiguration:Lcom/jetbrains/cidr/lang/workspace/OCResolveConfiguration;
        //    67: ifnull          92
        //    70: goto            77
        //    73: invokestatic    com/jetbrains/cidr/lang/preprocessor/OCInclusionContextImpl.a:(Ljava/lang/IllegalStateException;)Ljava/lang/IllegalStateException;
        //    76: athrow         
        //    77: aload_0        
        //    78: aload_1        
        //    79: invokespecial   com/jetbrains/cidr/lang/preprocessor/OCInclusionContextImpl.a:(Lcom/jetbrains/cidr/lang/symbols/symtable/FileSymbolTable;)Z
        //    82: ifeq            100
        //    85: goto            92
        //    88: invokestatic    com/jetbrains/cidr/lang/preprocessor/OCInclusionContextImpl.a:(Ljava/lang/IllegalStateException;)Ljava/lang/IllegalStateException;
        //    91: athrow         
        //    92: iconst_1       
        //    93: goto            101
        //    96: invokestatic    com/jetbrains/cidr/lang/preprocessor/OCInclusionContextImpl.a:(Ljava/lang/IllegalStateException;)Ljava/lang/IllegalStateException;
        //    99: athrow         
        //   100: iconst_0       
        //   101: istore_2       
        //   102: iload_2        
        //   103: istore          4
        //   105: aload_0        
        //   106: iload_2        
        //   107: invokespecial   com/jetbrains/cidr/lang/preprocessor/OCInclusionContextImpl.a:(Z)V
        //   110: iload           4
        //   112: ireturn        
        //   113: astore          5
        //   115: aload_0        
        //   116: iload_2        
        //   117: invokespecial   com/jetbrains/cidr/lang/preprocessor/OCInclusionContextImpl.a:(Z)V
        //   120: aload           5
        //   122: athrow         
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                             
        //  -----  -----  -----  -----  ---------------------------------
        //  77     96     96     100    Ljava/lang/IllegalStateException;
        //  63     85     88     92     Ljava/lang/IllegalStateException;
        //  55     70     73     77     Ljava/lang/IllegalStateException;
        //  0      40     40     44     Ljava/lang/IllegalStateException;
        //  50     105    113    123    Any
        //  113    115    113    123    Any
        // 
        // The error that occurred was:
        // 
        // java.lang.IllegalStateException: Expression is linked from several locations: Label_0077:
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
    
    @Override
    public void setProcessingListener(@Nullable final Processor<OCSymbol> myProcessingListener) {
        this.myProcessingListener = myProcessingListener;
    }
    
    @Override
    public void setResolvePathListener(@Nullable final ResolvePathListener myResolvePathListener) {
        this.myResolvePathListener = myResolvePathListener;
    }
    
    @Override
    public void setChangeBuilder(@Nullable final OCContextChangeBuilder myChangeBuilder) {
        this.myChangeBuilder = myChangeBuilder;
    }
    
    @NotNull
    static OCInclusionContext emptyWithBuiltinMacros(@Nullable OCLanguageKind calculateLanguageKindFast, @NotNull final PsiFile psiFile) {
        try {
            if (psiFile == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "file", "com/jetbrains/cidr/lang/preprocessor/OCInclusionContextImpl", "emptyWithBuiltinMacros"));
            }
        }
        catch (IllegalStateException ex) {
            throw a(ex);
        }
        if (calculateLanguageKindFast == null) {
            calculateLanguageKindFast = OCLanguageKindCalculator.calculateLanguageKindFast(psiFile);
        }
        final OCInclusionContextImpl ocInclusionContextImpl = (OCInclusionContextImpl)OCInclusionContext.empty(calculateLanguageKindFast, psiFile);
        OCInclusionContextImpl ocInclusionContextImpl2;
        try {
            ocInclusionContextImpl.a(psiFile, null);
            ocInclusionContextImpl2 = ocInclusionContextImpl;
            if (ocInclusionContextImpl2 == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/preprocessor/OCInclusionContextImpl", "emptyWithBuiltinMacros"));
            }
        }
        catch (IllegalStateException ex2) {
            throw a(ex2);
        }
        return ocInclusionContextImpl2;
    }
    
    @NotNull
    static OCImmutableInclusionContext initialContextWithoutRoot(@NotNull final OCResolveConfiguration ocResolveConfiguration, @NotNull final OCLanguageKind ocLanguageKind, @NotNull final PsiFile psiFile) {
        try {
            if (ocResolveConfiguration == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "configuration", "com/jetbrains/cidr/lang/preprocessor/OCInclusionContextImpl", "initialContextWithoutRoot"));
            }
        }
        catch (IllegalStateException ex) {
            throw a(ex);
        }
        try {
            if (ocLanguageKind == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "kind", "com/jetbrains/cidr/lang/preprocessor/OCInclusionContextImpl", "initialContextWithoutRoot"));
            }
        }
        catch (IllegalStateException ex2) {
            throw a(ex2);
        }
        try {
            if (psiFile == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "file", "com/jetbrains/cidr/lang/preprocessor/OCInclusionContextImpl", "initialContextWithoutRoot"));
            }
        }
        catch (IllegalStateException ex3) {
            throw a(ex3);
        }
        final ConcurrentHashMap concurrentHashMap = (ConcurrentHashMap)((Couple)OCInclusionContextImpl.INITIAL_PLAIN_AND_PCH_CONTEXTS_KEY.getValue((UserDataHolder)ocResolveConfiguration)).first;
        final String compilerKey = ocResolveConfiguration.getCompilerSettings().getCompilerKey(ocLanguageKind, OCInclusionContextUtil.getVirtualFile(psiFile));
        final OCImmutableInclusionContext ocImmutableInclusionContext = concurrentHashMap.get(compilerKey);
        Label_0235: {
            OCImmutableInclusionContext ocImmutableInclusionContext2 = null;
            Label_0200: {
                try {
                    if (ocImmutableInclusionContext == null) {
                        break Label_0235;
                    }
                    ocImmutableInclusionContext2 = ocImmutableInclusionContext;
                    if (ocImmutableInclusionContext2 == null) {
                        break Label_0200;
                    }
                    return ocImmutableInclusionContext2;
                }
                catch (IllegalStateException ex4) {
                    throw a(ex4);
                }
                try {
                    ocImmutableInclusionContext2 = ocImmutableInclusionContext;
                    if (ocImmutableInclusionContext2 == null) {
                        throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/preprocessor/OCInclusionContextImpl", "initialContextWithoutRoot"));
                    }
                }
                catch (IllegalStateException ex5) {
                    throw a(ex5);
                }
            }
            return ocImmutableInclusionContext2;
        }
        final OCInclusionContextImpl ocInclusionContextImpl = new OCInclusionContextImpl(ocResolveConfiguration, ocResolveConfiguration.getProject(), ocLanguageKind);
        ocInclusionContextImpl.a(psiFile, ocResolveConfiguration);
        final OCInclusionContextImpl ocInclusionContextImpl2 = concurrentHashMap.putIfAbsent(compilerKey, ocInclusionContextImpl);
        OCInclusionContextImpl ocInclusionContextImpl3 = null;
        Label_0288: {
            try {
                if (ocInclusionContextImpl2 == null) {
                    final OCInclusionContextImpl ocInclusionContextImpl4;
                    ocInclusionContextImpl3 = (ocInclusionContextImpl4 = ocInclusionContextImpl);
                    break Label_0288;
                }
            }
            catch (IllegalStateException ex6) {
                throw a(ex6);
            }
            OCInclusionContextImpl ocInclusionContextImpl4;
            ocInclusionContextImpl3 = (ocInclusionContextImpl4 = ocInclusionContextImpl2);
            try {
                if (ocInclusionContextImpl4 == null) {
                    throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/preprocessor/OCInclusionContextImpl", "initialContextWithoutRoot"));
                }
            }
            catch (IllegalStateException ex7) {
                throw a(ex7);
            }
        }
        return ocInclusionContextImpl3;
    }
    
    private void a(@NotNull final PsiFile psiFile, @Nullable final OCResolveConfiguration ocResolveConfiguration) {
        try {
            if (psiFile == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "sourceFile", "com/jetbrains/cidr/lang/preprocessor/OCInclusionContextImpl", "initContext"));
            }
        }
        catch (IllegalStateException ex) {
            throw a(ex);
        }
        Label_0064: {
            try {
                if (OCInclusionContextImpl.$assertionsDisabled) {
                    break Label_0064;
                }
                final OCInclusionContextImpl ocInclusionContextImpl = this;
                final OCInclusionContextImpl ocInclusionContextImpl2 = ocInclusionContextImpl.myParentContext;
                if (ocInclusionContextImpl2 != null) {
                    break Label_0064;
                }
                break Label_0064;
            }
            catch (IllegalStateException ex2) {
                throw a(ex2);
            }
            try {
                final OCInclusionContextImpl ocInclusionContextImpl = this;
                final OCInclusionContextImpl ocInclusionContextImpl2 = ocInclusionContextImpl.myParentContext;
                if (ocInclusionContextImpl2 != null) {
                    throw new AssertionError((Object)"initContext is supposed to be called on a root context.");
                }
            }
            catch (IllegalStateException ex3) {
                throw a(ex3);
            }
        }
        final OCLanguageKind languageKind = this.getLanguageKind();
        this.mySubstitutions.putAll((Map)a(languageKind, psiFile, OCCompilerMacros.PREDEFINED_MACROS));
        if (ocResolveConfiguration != null) {
            final VirtualFile virtualFile = OCInclusionContextUtil.getVirtualFile(psiFile);
            this.mySubstitutions.putAll((Map)a(languageKind, psiFile, ocResolveConfiguration.getPreprocessorDefines(languageKind, virtualFile)));
            this.myCompilerFeatures.putAll(ocResolveConfiguration.getCompilerFeatures(languageKind, virtualFile));
        }
        this.myOverriddenSubstitutions.putAll((Map)a(languageKind, psiFile, "\n#define __OSX_AVAILABLE_BUT_DEPRECATED(_macIntro, _macDep, _iosIntro, _iosDep) __CIDR_OSX_AVAILABLE_BUT_DEPRECATED_IMPL(_macIntro, _macDep, _iosIntro, _iosDep)\n#define __OSX_AVAILABLE_BUT_DEPRECATED_MSG(_osxIntro, _osxDep, _iosIntro, _iosDep, _msg) __CIDR_OSX_AVAILABLE_BUT_DEPRECATED_IMPL(_macIntro, _macDep, _iosIntro, _iosDep)\n#define __OSX_AVAILABLE_STARTING(mac, ios) __CIDR_OSX_AVAILABLE_STARTING_IMPL(mac, ios)\n#define CF_DEPRECATED(_macIntro, _macDep, _iosIntro, _iosDep) __CIDR_OSX_DEPRECATED(_macIntro, _macDep, _iosIntro, _iosDep)\n#define CF_DEPRECATED_MAC(_macIntro, _macDep) __CIDR_OSX_DEPRECATED(_macIntro, _macDep, NA, NA)\n#define CF_DEPRECATED_IOS(_iosIntro, _iosDep) __CIDR_OSX_DEPRECATED(NA, NA, _iosIntro, _iosDep)\n#define CF_AVAILABLE(mac, ios) __CIDR_OSX_AVAILABLE(mac, ios)\n#define CF_AVAILABLE_MAC(mac) __CIDR_OSX_AVAILABLE(mac, NA)\n#define CF_AVAILABLE_IOS(mac) __CIDR_OSX_AVAILABLE(NA, mac)\n#define CF_CLASS_AVAILABLE(mac, ios) __CIDR_OSX_AVAILABLE(mac, ios)\n#define NS_AVAILABLE(mac, ios) __CIDR_OSX_AVAILABLE(mac, ios)\n#define NS_AVAILABLE_MAC(mac) __CIDR_OSX_AVAILABLE(mac, NA)\n#define NS_AVAILABLE_IOS(mac) __CIDR_OSX_AVAILABLE(NA, mac)\n#define NS_CLASS_AVAILABLE(mac, ios) __CIDR_OSX_AVAILABLE(mac, ios)\n#define NS_ENUM(_type, _name) __attribute__((NS_ENUM_MACRO)) enum _name : _type _name; enum __attribute__((NS_ENUM)) _name : _type\n\n#define NS_OPTIONS(_type, _name) __attribute__((NS_OPTIONS_MACRO)) enum _name : _type _name; enum __attribute__((NS_OPTIONS)) _name : _type\n"));
        this.myOverriddenSubstitutions.trimToSize();
    }
    
    private static Map<String, OCMacroSymbol> a(@NotNull final OCLanguageKind ocLanguageKind, @NotNull final PsiFile psiFile, @NotNull final String s) {
        try {
            if (ocLanguageKind == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "languageKind", "com/jetbrains/cidr/lang/preprocessor/OCInclusionContextImpl", "getSubstitutions"));
            }
        }
        catch (IllegalStateException ex) {
            throw a(ex);
        }
        try {
            if (psiFile == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "sourceFile", "com/jetbrains/cidr/lang/preprocessor/OCInclusionContextImpl", "getSubstitutions"));
            }
        }
        catch (IllegalStateException ex2) {
            throw a(ex2);
        }
        try {
            if (s == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "text", "com/jetbrains/cidr/lang/preprocessor/OCInclusionContextImpl", "getSubstitutions"));
            }
        }
        catch (IllegalStateException ex3) {
            throw a(ex3);
        }
        try {
            if (StringUtil.isEmptyOrSpaces(s)) {
                return Collections.emptyMap();
            }
        }
        catch (IllegalStateException ex4) {
            throw a(ex4);
        }
        final Project project = psiFile.getProject();
        final Map map = (Map)CachedValuesManager.getManager(project).getCachedValue((UserDataHolder)project, () -> CachedValueProvider.Result.create((Object)new ConcurrentHashMap(), new Object[] { ModificationTracker.NEVER_CHANGED }));
        Object mySubstitutions = map.get(s);
        if (mySubstitutions == null) {
            final OCInclusionContextImpl ocInclusionContextImpl = (OCInclusionContextImpl)OCInclusionContext.empty(ocLanguageKind, psiFile);
            final OCPreprocessingLexer ocPreprocessingLexer = new OCPreprocessingLexer(ocInclusionContextImpl, null);
            ocPreprocessingLexer.start((CharSequence)s);
            try {
                while (ocPreprocessingLexer.getTokenType() != null) {
                    ocPreprocessingLexer.advance();
                }
            }
            catch (IllegalStateException ex5) {
                throw a(ex5);
            }
            map.putIfAbsent(s, mySubstitutions = ocInclusionContextImpl.mySubstitutions);
        }
        return (Map<String, OCMacroSymbol>)mySubstitutions;
    }
    
    private boolean a(@NotNull final FileSymbolTable fileSymbolTable) {
        try {
            if (fileSymbolTable == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "table", "com/jetbrains/cidr/lang/preprocessor/OCInclusionContextImpl", "conformsToByIncludes"));
            }
        }
        catch (IllegalStateException ex) {
            throw a(ex);
        }
        final Map<FileSymbolTable, Boolean> c = this.c();
        Boolean value = c.get(fileSymbolTable);
        if (value == null) {
            value = fileSymbolTable.processIncludes((Processor<OCIncludeSymbol>)(ocIncludeSymbol -> {
                try {
                    if (fileSymbolTable == null) {
                        throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "table", "com/jetbrains/cidr/lang/preprocessor/OCInclusionContextImpl", "lambda$conformsToByIncludes$4"));
                    }
                }
                catch (IllegalStateException ex) {
                    throw a(ex);
                }
                try {
                    if (ocIncludeSymbol instanceof OCForeignSymbol) {
                        return true;
                    }
                }
                catch (IllegalStateException ex2) {
                    throw a(ex2);
                }
                final VirtualFile targetFile = ocIncludeSymbol.getTargetFile();
                try {
                    if (targetFile instanceof LightVirtualFileBase) {
                        return true;
                    }
                }
                catch (IllegalStateException ex3) {
                    throw a(ex3);
                }
                final VirtualFile containingFile = fileSymbolTable.getContainingFile();
                final OCIncludeSymbol.IncludePath includePath = ocIncludeSymbol.getIncludePath();
                final Project project = fileSymbolTable.getProject();
                final OCResolveRootAndConfiguration a = this.a();
                VirtualFile resolveIncludedFile;
                if (ocIncludeSymbol.isNext()) {
                    final Ref ref = new Ref();
                    OCIncludeHelpers.resolveNextIncludedFile(a, containingFile, containingFile, includePath, project, (Ref<VirtualFile>)ref);
                    resolveIncludedFile = (VirtualFile)ref.get();
                }
                else {
                    resolveIncludedFile = OCIncludeHelpers.resolveIncludedFile(a, containingFile, includePath, project);
                }
                return Comparing.equal((Object)targetFile, (Object)resolveIncludedFile);
            }));
            c.put(fileSymbolTable, value);
        }
        return value;
    }
    
    @Override
    public int getInclusionLevel() {
        return this.myInclusionLevel;
    }
    
    @Nullable
    @Override
    public OCImmutableInclusionContext getParent() {
        return this.myParentContext;
    }
    
    @Override
    public void setPrecompiledHeaders(@NotNull final List<OCFile> list) {
        try {
            if (list == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "precompiledHeader", "com/jetbrains/cidr/lang/preprocessor/OCInclusionContextImpl", "setPrecompiledHeaders"));
            }
        }
        catch (IllegalStateException ex) {
            throw a(ex);
        }
        this.myPrecompiledHeaders = new ArrayList<OCFile>(list);
    }
    
    @NotNull
    @Override
    public List<OCFile> getPrecompiledHeaders() {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     0: aload_0        
        //     1: getfield        com/jetbrains/cidr/lang/preprocessor/OCInclusionContextImpl.myPrecompiledHeaders:Ljava/util/List;
        //     4: ifnull          79
        //     7: aload_0        
        //     8: getfield        com/jetbrains/cidr/lang/preprocessor/OCInclusionContextImpl.myPrecompiledHeaders:Ljava/util/List;
        //    11: invokeinterface java/util/List.isEmpty:()Z
        //    16: ifne            79
        //    19: goto            26
        //    22: invokestatic    com/jetbrains/cidr/lang/preprocessor/OCInclusionContextImpl.a:(Ljava/lang/IllegalStateException;)Ljava/lang/IllegalStateException;
        //    25: athrow         
        //    26: aload_0        
        //    27: getfield        com/jetbrains/cidr/lang/preprocessor/OCInclusionContextImpl.myPrecompiledHeaders:Ljava/util/List;
        //    30: invokestatic    java/util/Collections.unmodifiableList:(Ljava/util/List;)Ljava/util/List;
        //    33: dup            
        //    34: ifnonnull       78
        //    37: goto            44
        //    40: invokestatic    com/jetbrains/cidr/lang/preprocessor/OCInclusionContextImpl.a:(Ljava/lang/IllegalStateException;)Ljava/lang/IllegalStateException;
        //    43: athrow         
        //    44: new             Ljava/lang/IllegalStateException;
        //    47: dup            
        //    48: ldc             "@NotNull method %s.%s must not return null"
        //    50: ldc             2
        //    52: anewarray       Ljava/lang/Object;
        //    55: dup            
        //    56: ldc             0
        //    58: ldc             "com/jetbrains/cidr/lang/preprocessor/OCInclusionContextImpl"
        //    60: aastore        
        //    61: dup            
        //    62: ldc             1
        //    64: ldc             "getPrecompiledHeaders"
        //    66: aastore        
        //    67: invokestatic    java/lang/String.format:(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
        //    70: invokespecial   java/lang/IllegalStateException.<init>:(Ljava/lang/String;)V
        //    73: athrow         
        //    74: invokestatic    com/jetbrains/cidr/lang/preprocessor/OCInclusionContextImpl.a:(Ljava/lang/IllegalStateException;)Ljava/lang/IllegalStateException;
        //    77: athrow         
        //    78: areturn        
        //    79: aload_0        
        //    80: getfield        com/jetbrains/cidr/lang/preprocessor/OCInclusionContextImpl.myParentContext:Lcom/jetbrains/cidr/lang/preprocessor/OCInclusionContextImpl;
        //    83: ifnonnull       96
        //    86: invokestatic    java/util/Collections.emptyList:()Ljava/util/List;
        //    89: goto            103
        //    92: invokestatic    com/jetbrains/cidr/lang/preprocessor/OCInclusionContextImpl.a:(Ljava/lang/IllegalStateException;)Ljava/lang/IllegalStateException;
        //    95: athrow         
        //    96: aload_0        
        //    97: getfield        com/jetbrains/cidr/lang/preprocessor/OCInclusionContextImpl.myParentContext:Lcom/jetbrains/cidr/lang/preprocessor/OCInclusionContextImpl;
        //   100: invokevirtual   com/jetbrains/cidr/lang/preprocessor/OCInclusionContextImpl.getPrecompiledHeaders:()Ljava/util/List;
        //   103: dup            
        //   104: ifnonnull       141
        //   107: new             Ljava/lang/IllegalStateException;
        //   110: dup            
        //   111: ldc             "@NotNull method %s.%s must not return null"
        //   113: ldc             2
        //   115: anewarray       Ljava/lang/Object;
        //   118: dup            
        //   119: ldc             0
        //   121: ldc             "com/jetbrains/cidr/lang/preprocessor/OCInclusionContextImpl"
        //   123: aastore        
        //   124: dup            
        //   125: ldc             1
        //   127: ldc             "getPrecompiledHeaders"
        //   129: aastore        
        //   130: invokestatic    java/lang/String.format:(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
        //   133: invokespecial   java/lang/IllegalStateException.<init>:(Ljava/lang/String;)V
        //   136: athrow         
        //   137: invokestatic    com/jetbrains/cidr/lang/preprocessor/OCInclusionContextImpl.a:(Ljava/lang/IllegalStateException;)Ljava/lang/IllegalStateException;
        //   140: athrow         
        //   141: areturn        
        //    Signature:
        //  ()Ljava/util/List<Lcom/jetbrains/cidr/lang/psi/OCFile;>;
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                             
        //  -----  -----  -----  -----  ---------------------------------
        //  0      19     22     26     Ljava/lang/IllegalStateException;
        //  7      37     40     44     Ljava/lang/IllegalStateException;
        //  26     74     74     78     Ljava/lang/IllegalStateException;
        //  79     92     92     96     Ljava/lang/IllegalStateException;
        //  103    137    137    141    Ljava/lang/IllegalStateException;
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
    
    @Override
    public boolean hasRootFile() {
        try {
            if (this.myRootFile != null) {
                return true;
            }
        }
        catch (IllegalStateException ex) {
            throw a(ex);
        }
        return false;
    }
    
    @NotNull
    @Override
    public PsiFile getRootFile() {
        try {
            if (this.myRootFile == null) {
                OCLog.LOG.error("No root file");
            }
        }
        catch (IllegalStateException ex) {
            throw a(ex);
        }
        PsiFile myRootFile;
        try {
            myRootFile = this.myRootFile;
            if (myRootFile == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/preprocessor/OCInclusionContextImpl", "getRootFile"));
            }
        }
        catch (IllegalStateException ex2) {
            throw a(ex2);
        }
        return myRootFile;
    }
    
    @Override
    public OCInclusionContext setRootFile(@Nullable final PsiFile myRootFile) {
        Label_0018: {
            try {
                if (this.myRootFile == null) {
                    break Label_0018;
                }
                final PsiFile psiFile = myRootFile;
                if (psiFile != null) {
                    break Label_0018;
                }
                break Label_0018;
            }
            catch (IllegalStateException ex) {
                throw a(ex);
            }
            try {
                final PsiFile psiFile = myRootFile;
                if (psiFile != null) {
                    throw new IllegalStateException("Trying to change root file for inclusion context with existing root file");
                }
            }
            catch (IllegalStateException ex2) {
                throw a(ex2);
            }
        }
        this.myRootFile = myRootFile;
        return this;
    }
    
    @Nullable
    static OCImmutableInclusionContext findInCachedPCHPrecompiledContexts(@NotNull final OCResolveConfiguration p0, @NotNull final VirtualFile p1) {
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
        //    18: ldc             "config"
        //    20: aastore        
        //    21: dup            
        //    22: ldc             1
        //    24: ldc             "com/jetbrains/cidr/lang/preprocessor/OCInclusionContextImpl"
        //    26: aastore        
        //    27: dup            
        //    28: ldc             2
        //    30: ldc             "findInCachedPCHPrecompiledContexts"
        //    32: aastore        
        //    33: invokestatic    java/lang/String.format:(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
        //    36: invokespecial   java/lang/IllegalArgumentException.<init>:(Ljava/lang/String;)V
        //    39: athrow         
        //    40: invokestatic    com/jetbrains/cidr/lang/preprocessor/OCInclusionContextImpl.a:(Ljava/lang/IllegalStateException;)Ljava/lang/IllegalStateException;
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
        //    62: ldc             "header"
        //    64: aastore        
        //    65: dup            
        //    66: ldc             1
        //    68: ldc             "com/jetbrains/cidr/lang/preprocessor/OCInclusionContextImpl"
        //    70: aastore        
        //    71: dup            
        //    72: ldc             2
        //    74: ldc             "findInCachedPCHPrecompiledContexts"
        //    76: aastore        
        //    77: invokestatic    java/lang/String.format:(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
        //    80: invokespecial   java/lang/IllegalArgumentException.<init>:(Ljava/lang/String;)V
        //    83: athrow         
        //    84: invokestatic    com/jetbrains/cidr/lang/preprocessor/OCInclusionContextImpl.a:(Ljava/lang/IllegalStateException;)Ljava/lang/IllegalStateException;
        //    87: athrow         
        //    88: getstatic       com/jetbrains/cidr/lang/preprocessor/OCInclusionContextImpl.INITIAL_PLAIN_AND_PCH_CONTEXTS_KEY:Lcom/intellij/openapi/util/NotNullLazyKey;
        //    91: aload_0        
        //    92: invokevirtual   com/intellij/openapi/util/NotNullLazyKey.getValue:(Lcom/intellij/openapi/util/UserDataHolder;)Ljava/lang/Object;
        //    95: checkcast       Lcom/intellij/openapi/util/Couple;
        //    98: getfield        com/intellij/openapi/util/Couple.second:Ljava/lang/Object;
        //   101: checkcast       Ljava/util/concurrent/ConcurrentHashMap;
        //   104: invokevirtual   java/util/concurrent/ConcurrentHashMap.values:()Ljava/util/Collection;
        //   107: astore_2       
        //   108: aconst_null    
        //   109: astore_3       
        //   110: aload_2        
        //   111: invokeinterface java/util/Collection.iterator:()Ljava/util/Iterator;
        //   116: astore          4
        //   118: aload           4
        //   120: invokeinterface java/util/Iterator.hasNext:()Z
        //   125: ifeq            278
        //   128: aload           4
        //   130: invokeinterface java/util/Iterator.next:()Ljava/lang/Object;
        //   135: checkcast       Lcom/jetbrains/cidr/lang/preprocessor/OCImmutableInclusionContext;
        //   138: astore          5
        //   140: aload           5
        //   142: invokeinterface com/jetbrains/cidr/lang/preprocessor/OCImmutableInclusionContext.getLanguageKind:()Lcom/jetbrains/cidr/lang/OCLanguageKind;
        //   147: astore          6
        //   149: getstatic       com/jetbrains/cidr/lang/preprocessor/OCInclusionContextImpl.$assertionsDisabled:Z
        //   152: ifne            184
        //   155: aload           6
        //   157: invokeinterface com/jetbrains/cidr/lang/OCLanguageKind.supportsPrecompiledHeaders:()Z
        //   162: ifne            184
        //   165: goto            172
        //   168: invokestatic    com/jetbrains/cidr/lang/preprocessor/OCInclusionContextImpl.a:(Ljava/lang/IllegalStateException;)Ljava/lang/IllegalStateException;
        //   171: athrow         
        //   172: new             Ljava/lang/AssertionError;
        //   175: dup            
        //   176: invokespecial   java/lang/AssertionError.<init>:()V
        //   179: athrow         
        //   180: invokestatic    com/jetbrains/cidr/lang/preprocessor/OCInclusionContextImpl.a:(Ljava/lang/IllegalStateException;)Ljava/lang/IllegalStateException;
        //   183: athrow         
        //   184: aload           5
        //   186: aload_1        
        //   187: invokeinterface com/jetbrains/cidr/lang/preprocessor/OCImmutableInclusionContext.isProcessed:(Lcom/intellij/openapi/vfs/VirtualFile;)Z
        //   192: ifeq            275
        //   195: getstatic       com/jetbrains/cidr/lang/preprocessor/OCInclusionContextImpl.$assertionsDisabled:Z
        //   198: ifne            235
        //   201: goto            208
        //   204: invokestatic    com/jetbrains/cidr/lang/preprocessor/OCInclusionContextImpl.a:(Ljava/lang/IllegalStateException;)Ljava/lang/IllegalStateException;
        //   207: athrow         
        //   208: aload           6
        //   210: instanceof      Lcom/jetbrains/cidr/lang/CLanguageKind;
        //   213: ifne            235
        //   216: goto            223
        //   219: invokestatic    com/jetbrains/cidr/lang/preprocessor/OCInclusionContextImpl.a:(Ljava/lang/IllegalStateException;)Ljava/lang/IllegalStateException;
        //   222: athrow         
        //   223: new             Ljava/lang/AssertionError;
        //   226: dup            
        //   227: invokespecial   java/lang/AssertionError.<init>:()V
        //   230: athrow         
        //   231: invokestatic    com/jetbrains/cidr/lang/preprocessor/OCInclusionContextImpl.a:(Ljava/lang/IllegalStateException;)Ljava/lang/IllegalStateException;
        //   234: athrow         
        //   235: aload_3        
        //   236: ifnull          272
        //   239: aload_3        
        //   240: invokeinterface com/jetbrains/cidr/lang/preprocessor/OCImmutableInclusionContext.getLanguageKind:()Lcom/jetbrains/cidr/lang/OCLanguageKind;
        //   245: aload           6
        //   247: checkcast       Lcom/jetbrains/cidr/lang/CLanguageKind;
        //   250: aload_3        
        //   251: invokeinterface com/jetbrains/cidr/lang/preprocessor/OCImmutableInclusionContext.getLanguageKind:()Lcom/jetbrains/cidr/lang/OCLanguageKind;
        //   256: checkcast       Lcom/jetbrains/cidr/lang/CLanguageKind;
        //   259: invokestatic    com/jetbrains/cidr/lang/CLanguageKind.min:(Lcom/jetbrains/cidr/lang/CLanguageKind;Lcom/jetbrains/cidr/lang/CLanguageKind;)Lcom/jetbrains/cidr/lang/CLanguageKind;
        //   262: if_acmpne       275
        //   265: goto            272
        //   268: invokestatic    com/jetbrains/cidr/lang/preprocessor/OCInclusionContextImpl.a:(Ljava/lang/IllegalStateException;)Ljava/lang/IllegalStateException;
        //   271: athrow         
        //   272: aload           5
        //   274: astore_3       
        //   275: goto            118
        //   278: aload_3        
        //   279: areturn        
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                             
        //  -----  -----  -----  -----  ---------------------------------
        //  0      40     40     44     Ljava/lang/IllegalStateException;
        //  44     84     84     88     Ljava/lang/IllegalStateException;
        //  149    165    168    172    Ljava/lang/IllegalStateException;
        //  155    180    180    184    Ljava/lang/IllegalStateException;
        //  184    201    204    208    Ljava/lang/IllegalStateException;
        //  195    216    219    223    Ljava/lang/IllegalStateException;
        //  208    231    231    235    Ljava/lang/IllegalStateException;
        //  235    265    268    272    Ljava/lang/IllegalStateException;
        // 
        // The error that occurred was:
        // 
        // java.lang.IllegalStateException: Expression is linked from several locations: Label_0208:
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
    
    private Map<FileSymbolTable, Boolean> c() {
        try {
            if (this.myConfiguration == null) {
                return (Map<FileSymbolTable, Boolean>)new THashMap();
            }
        }
        catch (IllegalStateException ex) {
            throw a(ex);
        }
        return (Map<FileSymbolTable, Boolean>)OCInclusionContextImpl.INCLUDE_RESOLVE_CACHE.getValue((UserDataHolder)this.myConfiguration);
    }
    
    @Nullable
    @Override
    public <T> T getCompilerFeature(@NotNull final OCCompilerFeatures.Type<T> type) {
        try {
            if (type == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "feature", "com/jetbrains/cidr/lang/preprocessor/OCInclusionContextImpl", "getCompilerFeature"));
            }
        }
        catch (IllegalStateException ex) {
            throw a(ex);
        }
        Object o = this.myCompilerFeatures.get(type);
        if (o == null) {
            OCInclusionContextImpl myParentContext;
            for (myParentContext = this; myParentContext.myParentContext != null; myParentContext = myParentContext.myParentContext) {}
            o = type.compute(myParentContext);
            if (o == null) {
                o = OCInclusionContextImpl.NULL_VALUE;
            }
            this.myCompilerFeatures.putIfAbsent(type, o);
        }
        try {
            if (o == OCInclusionContextImpl.NULL_VALUE) {
                final Object o2 = null;
                return (T)o2;
            }
        }
        catch (IllegalStateException ex2) {
            throw a(ex2);
        }
        final Object o2 = o;
        return (T)o2;
    }
    
    @NotNull
    @Override
    public OCParsingNameScope getNameScope() {
        OCParsingNameScope myNameScope;
        try {
            myNameScope = this.myNameScope;
            if (myNameScope == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/preprocessor/OCInclusionContextImpl", "getNameScope"));
            }
        }
        catch (IllegalStateException ex) {
            throw a(ex);
        }
        return myNameScope;
    }
    
    @NotNull
    @Override
    public OCInclusionContext derive() {
        try {
            if (this.myRootFile == null) {
                throw new IllegalStateException("Deriving from context without root file. Use 'derive(PsiFile)' instead");
            }
        }
        catch (IllegalStateException ex) {
            throw a(ex);
        }
        OCInclusionContextImpl ocInclusionContextImpl;
        try {
            ocInclusionContextImpl = new OCInclusionContextImpl(this.myConfiguration, this.myProject, this, this.myRootFile, this.myNameScope.copy(), true);
            if (ocInclusionContextImpl == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/preprocessor/OCInclusionContextImpl", "derive"));
            }
        }
        catch (IllegalStateException ex2) {
            throw a(ex2);
        }
        return ocInclusionContextImpl;
    }
    
    @NotNull
    @Override
    public OCInclusionContext derive(@NotNull final PsiFile psiFile) {
        try {
            if (psiFile == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "file", "com/jetbrains/cidr/lang/preprocessor/OCInclusionContextImpl", "derive"));
            }
        }
        catch (IllegalStateException ex) {
            throw a(ex);
        }
        try {
            if (this.myRootFile != null) {
                throw new IllegalStateException("Deriving from context with existing root file. Use 'derive()' instead");
            }
        }
        catch (IllegalStateException ex2) {
            throw a(ex2);
        }
        OCInclusionContextImpl ocInclusionContextImpl;
        try {
            ocInclusionContextImpl = new OCInclusionContextImpl(this.myConfiguration, this.myProject, this, psiFile, this.myNameScope.copy(), true);
            if (ocInclusionContextImpl == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/preprocessor/OCInclusionContextImpl", "derive"));
            }
        }
        catch (IllegalStateException ex3) {
            throw a(ex3);
        }
        return ocInclusionContextImpl;
    }
    
    @NotNull
    @Override
    public OCInclusionContext derive(@NotNull final OCParsingNameScope ocParsingNameScope) {
        try {
            if (ocParsingNameScope == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "nameScope", "com/jetbrains/cidr/lang/preprocessor/OCInclusionContextImpl", "derive"));
            }
        }
        catch (IllegalStateException ex) {
            throw a(ex);
        }
        try {
            if (this.myRootFile == null) {
                throw new IllegalStateException("Deriving from context without root file. Use 'derive(PsiFile)' instead");
            }
        }
        catch (IllegalStateException ex2) {
            throw a(ex2);
        }
        OCInclusionContextImpl ocInclusionContextImpl;
        try {
            ocInclusionContextImpl = new OCInclusionContextImpl(this.myConfiguration, this.myProject, this, this.myRootFile, ocParsingNameScope, true);
            if (ocInclusionContextImpl == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/preprocessor/OCInclusionContextImpl", "derive"));
            }
        }
        catch (IllegalStateException ex3) {
            throw a(ex3);
        }
        return ocInclusionContextImpl;
    }
    
    @NotNull
    @Override
    public OCInclusionContext deriveButDontCopyTypes(final boolean b) {
        try {
            if (this.myRootFile == null) {
                throw new IllegalStateException("Deriving from context without root file. Use 'derive(PsiFile)' instead");
            }
        }
        catch (IllegalStateException ex) {
            throw a(ex);
        }
        OCInclusionContextImpl ocInclusionContextImpl;
        try {
            ocInclusionContextImpl = new OCInclusionContextImpl(this.myConfiguration, this.myProject, this, this.myRootFile, this.myNameScope, b);
            if (ocInclusionContextImpl == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/preprocessor/OCInclusionContextImpl", "deriveButDontCopyTypes"));
            }
        }
        catch (IllegalStateException ex2) {
            throw a(ex2);
        }
        return ocInclusionContextImpl;
    }
    
    @Override
    public void define(@NotNull OCMacroSymbol ocMacroSymbol) {
        try {
            if (ocMacroSymbol == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "def", "com/jetbrains/cidr/lang/preprocessor/OCInclusionContextImpl", "define"));
            }
        }
        catch (IllegalStateException ex) {
            throw a(ex);
        }
        final String name = ocMacroSymbol.getName();
        final OCMacroSymbol ocMacroSymbol2 = (OCMacroSymbol)this.myOverriddenSubstitutions.get((Object)name);
        if (ocMacroSymbol2 != null) {
            ocMacroSymbol = ocMacroSymbol2;
        }
        try {
            this.myUndefList.remove(name);
            this.mySubstitutions.put((Object)name, (Object)ocMacroSymbol);
            if (this.myChangeBuilder != null) {
                this.myChangeBuilder.define(name, ocMacroSymbol);
            }
        }
        catch (IllegalStateException ex2) {
            throw a(ex2);
        }
    }
    
    @Override
    public void define(final String s, final String s2) {
        this.define(new OCMacroSymbol(this.getProject(), null, 0, s, OCImmutableList.emptyList(), s2));
    }
    
    @Override
    public void undef(final String s) {
        try {
            this.myUndefList.add(s);
            this.mySubstitutions.remove((Object)s);
            if (this.myChangeBuilder != null) {
                this.myChangeBuilder.undef(s);
            }
        }
        catch (IllegalStateException ex) {
            throw a(ex);
        }
    }
    
    @Override
    public boolean isDefined(@Nullable final String s) {
        try {
            if (this.getDefinition(s, OCImmutableInclusionContext.SignaturePart.HAS_DEFINITION) != null) {
                return true;
            }
        }
        catch (IllegalStateException ex) {
            throw a(ex);
        }
        return false;
    }
    
    @Nullable
    @Override
    public OCMacroSymbol getDefinition(@Nullable final String s) {
        return this.getDefinition(s, OCImmutableInclusionContext.SignaturePart.EXACT_DEFINITION);
    }
    
    private void d() {
        try {
            if (this.mySignatureBuilder != null) {
                this.mySignatureBuilder.enterConformanceCheckMode();
            }
        }
        catch (IllegalStateException ex) {
            throw a(ex);
        }
        try {
            if (this.myParentContext != null) {
                this.myParentContext.d();
            }
        }
        catch (IllegalStateException ex2) {
            throw a(ex2);
        }
    }
    
    private void a(final boolean b) {
        try {
            if (this.mySignatureBuilder != null) {
                this.mySignatureBuilder.exitConformanceCheckMode(b);
            }
        }
        catch (IllegalStateException ex) {
            throw a(ex);
        }
        try {
            if (this.myParentContext != null) {
                this.myParentContext.a(b);
            }
        }
        catch (IllegalStateException ex2) {
            throw a(ex2);
        }
    }
    
    @Nullable
    @Override
    public OCMacroSymbol getDefinition(@Nullable final String p0, @NotNull final OCImmutableInclusionContext.SignaturePart p1) {
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
        //    18: ldc             "sp"
        //    20: aastore        
        //    21: dup            
        //    22: ldc             1
        //    24: ldc             "com/jetbrains/cidr/lang/preprocessor/OCInclusionContextImpl"
        //    26: aastore        
        //    27: dup            
        //    28: ldc             2
        //    30: ldc             "getDefinition"
        //    32: aastore        
        //    33: invokestatic    java/lang/String.format:(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
        //    36: invokespecial   java/lang/IllegalArgumentException.<init>:(Ljava/lang/String;)V
        //    39: athrow         
        //    40: invokestatic    com/jetbrains/cidr/lang/preprocessor/OCInclusionContextImpl.a:(Ljava/lang/IllegalStateException;)Ljava/lang/IllegalStateException;
        //    43: athrow         
        //    44: aload_1        
        //    45: ifnonnull       54
        //    48: aconst_null    
        //    49: areturn        
        //    50: invokestatic    com/jetbrains/cidr/lang/preprocessor/OCInclusionContextImpl.a:(Ljava/lang/IllegalStateException;)Ljava/lang/IllegalStateException;
        //    53: athrow         
        //    54: aload_2        
        //    55: getstatic       com/jetbrains/cidr/lang/preprocessor/OCImmutableInclusionContext$SignaturePart.NO:Lcom/jetbrains/cidr/lang/preprocessor/OCImmutableInclusionContext$SignaturePart;
        //    58: if_acmpeq       69
        //    61: iconst_1       
        //    62: goto            70
        //    65: invokestatic    com/jetbrains/cidr/lang/preprocessor/OCInclusionContextImpl.a:(Ljava/lang/IllegalStateException;)Ljava/lang/IllegalStateException;
        //    68: athrow         
        //    69: iconst_0       
        //    70: istore          4
        //    72: aload_0        
        //    73: getfield        com/jetbrains/cidr/lang/preprocessor/OCInclusionContextImpl.myUndefList:Ljava/util/Set;
        //    76: aload_1        
        //    77: invokeinterface java/util/Set.contains:(Ljava/lang/Object;)Z
        //    82: ifeq            96
        //    85: aconst_null    
        //    86: astore_3       
        //    87: aload_0        
        //    88: getfield        com/jetbrains/cidr/lang/preprocessor/OCInclusionContextImpl.myNotifyLocalDefinitions:Z
        //    91: istore          4
        //    93: goto            151
        //    96: aload_0        
        //    97: getfield        com/jetbrains/cidr/lang/preprocessor/OCInclusionContextImpl.mySubstitutions:Lgnu/trove/THashMap;
        //   100: aload_1        
        //   101: invokevirtual   gnu/trove/THashMap.get:(Ljava/lang/Object;)Ljava/lang/Object;
        //   104: checkcast       Lcom/jetbrains/cidr/lang/symbols/cpp/OCMacroSymbol;
        //   107: astore          5
        //   109: aload           5
        //   111: ifnull          126
        //   114: aload           5
        //   116: astore_3       
        //   117: aload_0        
        //   118: getfield        com/jetbrains/cidr/lang/preprocessor/OCInclusionContextImpl.myNotifyLocalDefinitions:Z
        //   121: istore          4
        //   123: goto            151
        //   126: aload_0        
        //   127: getfield        com/jetbrains/cidr/lang/preprocessor/OCInclusionContextImpl.myParentContext:Lcom/jetbrains/cidr/lang/preprocessor/OCInclusionContextImpl;
        //   130: ifnull          149
        //   133: aload_0        
        //   134: getfield        com/jetbrains/cidr/lang/preprocessor/OCInclusionContextImpl.myParentContext:Lcom/jetbrains/cidr/lang/preprocessor/OCInclusionContextImpl;
        //   137: aload_1        
        //   138: aload_2        
        //   139: invokevirtual   com/jetbrains/cidr/lang/preprocessor/OCInclusionContextImpl.getDefinition:(Ljava/lang/String;Lcom/jetbrains/cidr/lang/preprocessor/OCImmutableInclusionContext$SignaturePart;)Lcom/jetbrains/cidr/lang/symbols/cpp/OCMacroSymbol;
        //   142: goto            150
        //   145: invokestatic    com/jetbrains/cidr/lang/preprocessor/OCInclusionContextImpl.a:(Ljava/lang/IllegalStateException;)Ljava/lang/IllegalStateException;
        //   148: athrow         
        //   149: aconst_null    
        //   150: astore_3       
        //   151: iload           4
        //   153: ifeq            263
        //   156: aload_0        
        //   157: getfield        com/jetbrains/cidr/lang/preprocessor/OCInclusionContextImpl.mySignatureBuilder:Lcom/jetbrains/cidr/lang/preprocessor/OCInclusionContext$SignatureBuilder;
        //   160: ifnull          263
        //   163: goto            170
        //   166: invokestatic    com/jetbrains/cidr/lang/preprocessor/OCInclusionContextImpl.a:(Ljava/lang/IllegalStateException;)Ljava/lang/IllegalStateException;
        //   169: athrow         
        //   170: getstatic       com/jetbrains/cidr/lang/preprocessor/OCInclusionContextImpl$2.$SwitchMap$com$jetbrains$cidr$lang$preprocessor$OCImmutableInclusionContext$SignaturePart:[I
        //   173: aload_2        
        //   174: invokevirtual   com/jetbrains/cidr/lang/preprocessor/OCImmutableInclusionContext$SignaturePart.ordinal:()I
        //   177: iaload         
        //   178: lookupswitch {
        //                1: 208
        //                2: 241
        //          default: 263
        //        }
        //   204: invokestatic    com/jetbrains/cidr/lang/preprocessor/OCInclusionContextImpl.a:(Ljava/lang/IllegalStateException;)Ljava/lang/IllegalStateException;
        //   207: athrow         
        //   208: aload_0        
        //   209: getfield        com/jetbrains/cidr/lang/preprocessor/OCInclusionContextImpl.mySignatureBuilder:Lcom/jetbrains/cidr/lang/preprocessor/OCInclusionContext$SignatureBuilder;
        //   212: aload_1        
        //   213: aload_3        
        //   214: ifnull          232
        //   217: goto            224
        //   220: invokestatic    com/jetbrains/cidr/lang/preprocessor/OCInclusionContextImpl.a:(Ljava/lang/IllegalStateException;)Ljava/lang/IllegalStateException;
        //   223: athrow         
        //   224: iconst_1       
        //   225: goto            233
        //   228: invokestatic    com/jetbrains/cidr/lang/preprocessor/OCInclusionContextImpl.a:(Ljava/lang/IllegalStateException;)Ljava/lang/IllegalStateException;
        //   231: athrow         
        //   232: iconst_0       
        //   233: invokeinterface com/jetbrains/cidr/lang/preprocessor/OCInclusionContext$SignatureBuilder.setDefined:(Ljava/lang/String;Z)V
        //   238: goto            263
        //   241: aload_3        
        //   242: ifnull          263
        //   245: aload_0        
        //   246: getfield        com/jetbrains/cidr/lang/preprocessor/OCInclusionContextImpl.mySignatureBuilder:Lcom/jetbrains/cidr/lang/preprocessor/OCInclusionContext$SignatureBuilder;
        //   249: aload_1        
        //   250: aload_3        
        //   251: invokeinterface com/jetbrains/cidr/lang/preprocessor/OCInclusionContext$SignatureBuilder.setDefinition:(Ljava/lang/String;Lcom/jetbrains/cidr/lang/symbols/cpp/OCMacroSymbol;)V
        //   256: goto            263
        //   259: invokestatic    com/jetbrains/cidr/lang/preprocessor/OCInclusionContextImpl.a:(Ljava/lang/IllegalStateException;)Ljava/lang/IllegalStateException;
        //   262: athrow         
        //   263: aload_3        
        //   264: areturn        
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                             
        //  -----  -----  -----  -----  ---------------------------------
        //  0      40     40     44     Ljava/lang/IllegalStateException;
        //  44     50     50     54     Ljava/lang/IllegalStateException;
        //  54     65     65     69     Ljava/lang/IllegalStateException;
        //  126    145    145    149    Ljava/lang/IllegalStateException;
        //  151    163    166    170    Ljava/lang/IllegalStateException;
        //  156    204    204    208    Ljava/lang/IllegalStateException;
        //  170    217    220    224    Ljava/lang/IllegalStateException;
        //  208    228    228    232    Ljava/lang/IllegalStateException;
        //  241    259    259    263    Ljava/lang/IllegalStateException;
        // 
        // The error that occurred was:
        // 
        // java.lang.IllegalStateException: Expression is linked from several locations: Label_0170:
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
    
    @Override
    public boolean isProcessed(@Nullable final VirtualFile p0) {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     0: aload_1        
        //     1: ifnull          64
        //     4: aload_0        
        //     5: getfield        com/jetbrains/cidr/lang/preprocessor/OCInclusionContextImpl.myProcessedFiles:Ljava/util/Set;
        //     8: aload_1        
        //     9: invokeinterface java/util/Set.contains:(Ljava/lang/Object;)Z
        //    14: ifne            56
        //    17: goto            24
        //    20: invokestatic    com/jetbrains/cidr/lang/preprocessor/OCInclusionContextImpl.a:(Ljava/lang/IllegalStateException;)Ljava/lang/IllegalStateException;
        //    23: athrow         
        //    24: aload_0        
        //    25: getfield        com/jetbrains/cidr/lang/preprocessor/OCInclusionContextImpl.myParentContext:Lcom/jetbrains/cidr/lang/preprocessor/OCInclusionContextImpl;
        //    28: ifnull          64
        //    31: goto            38
        //    34: invokestatic    com/jetbrains/cidr/lang/preprocessor/OCInclusionContextImpl.a:(Ljava/lang/IllegalStateException;)Ljava/lang/IllegalStateException;
        //    37: athrow         
        //    38: aload_0        
        //    39: getfield        com/jetbrains/cidr/lang/preprocessor/OCInclusionContextImpl.myParentContext:Lcom/jetbrains/cidr/lang/preprocessor/OCInclusionContextImpl;
        //    42: aload_1        
        //    43: invokevirtual   com/jetbrains/cidr/lang/preprocessor/OCInclusionContextImpl.isProcessed:(Lcom/intellij/openapi/vfs/VirtualFile;)Z
        //    46: ifeq            64
        //    49: goto            56
        //    52: invokestatic    com/jetbrains/cidr/lang/preprocessor/OCInclusionContextImpl.a:(Ljava/lang/IllegalStateException;)Ljava/lang/IllegalStateException;
        //    55: athrow         
        //    56: iconst_1       
        //    57: goto            65
        //    60: invokestatic    com/jetbrains/cidr/lang/preprocessor/OCInclusionContextImpl.a:(Ljava/lang/IllegalStateException;)Ljava/lang/IllegalStateException;
        //    63: athrow         
        //    64: iconst_0       
        //    65: ireturn        
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                             
        //  -----  -----  -----  -----  ---------------------------------
        //  0      17     20     24     Ljava/lang/IllegalStateException;
        //  4      31     34     38     Ljava/lang/IllegalStateException;
        //  24     49     52     56     Ljava/lang/IllegalStateException;
        //  38     60     60     64     Ljava/lang/IllegalStateException;
        // 
        // The error that occurred was:
        // 
        // java.lang.IllegalStateException: Expression is linked from several locations: Label_0024:
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
    public final Set<VirtualFile> getProcessedFiles() {
        final THashSet set = new THashSet();
        Set<Object> unmodifiableSet;
        try {
            this.a((Set<VirtualFile>)set);
            unmodifiableSet = (Set<Object>)Collections.unmodifiableSet((Set<? extends VirtualFile>)set);
            if (unmodifiableSet == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/preprocessor/OCInclusionContextImpl", "getProcessedFiles"));
            }
        }
        catch (IllegalStateException ex) {
            throw a(ex);
        }
        return (Set<VirtualFile>)unmodifiableSet;
    }
    
    private void a(final Set<VirtualFile> set) {
        try {
            if (this.myParentContext != null) {
                this.myParentContext.a(set);
            }
        }
        catch (IllegalStateException ex) {
            throw a(ex);
        }
        set.addAll(this.myProcessedFiles);
    }
    
    @Override
    public void setSignatureBuilder(@Nullable final SignatureBuilder mySignatureBuilder) {
        this.mySignatureBuilder = mySignatureBuilder;
    }
    
    @Nullable
    @Override
    public VirtualFile resolveNextPath(@NotNull final OCIncludeSymbol.IncludePath includePath, @NotNull final PsiFile psiFile) {
        try {
            if (includePath == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "path", "com/jetbrains/cidr/lang/preprocessor/OCInclusionContextImpl", "resolveNextPath"));
            }
        }
        catch (IllegalStateException ex) {
            throw a(ex);
        }
        try {
            if (psiFile == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "contextFile", "com/jetbrains/cidr/lang/preprocessor/OCInclusionContextImpl", "resolveNextPath"));
            }
        }
        catch (IllegalStateException ex2) {
            throw a(ex2);
        }
        final Ref ref = new Ref();
        OCIncludeHelpers.resolveNextIncludedFile(this.a(), OCInclusionContextUtil.getVirtualFile(psiFile), OCInclusionContextUtil.getVirtualFile(psiFile), includePath, psiFile.getProject(), (Ref<VirtualFile>)ref);
        final VirtualFile virtualFile = (VirtualFile)ref.get();
        try {
            if (this.myResolvePathListener != null) {
                this.myResolvePathListener.resolve(includePath, true, virtualFile);
            }
        }
        catch (IllegalStateException ex3) {
            throw a(ex3);
        }
        return virtualFile;
    }
    
    @Nullable
    @Override
    public VirtualFile resolvePath(@NotNull final OCIncludeSymbol.IncludePath includePath, @NotNull final PsiFile psiFile) {
        try {
            if (includePath == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "path", "com/jetbrains/cidr/lang/preprocessor/OCInclusionContextImpl", "resolvePath"));
            }
        }
        catch (IllegalStateException ex) {
            throw a(ex);
        }
        try {
            if (psiFile == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "contextFile", "com/jetbrains/cidr/lang/preprocessor/OCInclusionContextImpl", "resolvePath"));
            }
        }
        catch (IllegalStateException ex2) {
            throw a(ex2);
        }
        final VirtualFile resolveIncludedFile = OCIncludeHelpers.resolveIncludedFile(this.a(), OCInclusionContextUtil.getVirtualFile(psiFile), includePath, psiFile.getProject());
        try {
            if (this.myResolvePathListener != null) {
                this.myResolvePathListener.resolve(includePath, false, resolveIncludedFile);
            }
        }
        catch (IllegalStateException ex3) {
            throw a(ex3);
        }
        return resolveIncludedFile;
    }
    
    @NotNull
    private OCResolveRootAndConfiguration a() {
        final VirtualFile virtualFile = OCInclusionContextUtil.getVirtualFile(this.myRootFile);
        OCResolveRootAndConfiguration ocResolveRootAndConfiguration2 = null;
        Label_0073: {
            OCResolveRootAndConfiguration ocResolveRootAndConfiguration = null;
            Label_0038: {
                try {
                    if (virtualFile != null) {
                        break Label_0073;
                    }
                    final OCInclusionContextImpl ocInclusionContextImpl = this;
                    final OCResolveConfiguration ocResolveConfiguration = ocInclusionContextImpl.myConfiguration;
                    final OCInclusionContextImpl ocInclusionContextImpl2 = this;
                    final OCLanguageKind ocLanguageKind = ocInclusionContextImpl2.myLanguageKind;
                    ocResolveRootAndConfiguration = new OCResolveRootAndConfiguration(ocResolveConfiguration, ocLanguageKind);
                    if (ocResolveRootAndConfiguration == null) {
                        break Label_0038;
                    }
                    return ocResolveRootAndConfiguration;
                }
                catch (IllegalStateException ex) {
                    throw a(ex);
                }
                try {
                    final OCInclusionContextImpl ocInclusionContextImpl = this;
                    final OCResolveConfiguration ocResolveConfiguration = ocInclusionContextImpl.myConfiguration;
                    final OCInclusionContextImpl ocInclusionContextImpl2 = this;
                    final OCLanguageKind ocLanguageKind = ocInclusionContextImpl2.myLanguageKind;
                    ocResolveRootAndConfiguration = new OCResolveRootAndConfiguration(ocResolveConfiguration, ocLanguageKind);
                    if (ocResolveRootAndConfiguration == null) {
                        throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/preprocessor/OCInclusionContextImpl", "getRootAndConfiguration"));
                    }
                }
                catch (IllegalStateException ex2) {
                    throw a(ex2);
                }
            }
            return ocResolveRootAndConfiguration;
            try {
                ocResolveRootAndConfiguration2 = new OCResolveRootAndConfiguration(this.myConfiguration, virtualFile);
                if (ocResolveRootAndConfiguration2 == null) {
                    throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/preprocessor/OCInclusionContextImpl", "getRootAndConfiguration"));
                }
            }
            catch (IllegalStateException ex3) {
                throw a(ex3);
            }
        }
        return ocResolveRootAndConfiguration2;
    }
    
    @NotNull
    @Override
    public Project getProject() {
        Project myProject;
        try {
            myProject = this.myProject;
            if (myProject == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/preprocessor/OCInclusionContextImpl", "getProject"));
            }
        }
        catch (IllegalStateException ex) {
            throw a(ex);
        }
        return myProject;
    }
    
    @Nullable
    @Override
    public OCResolveConfiguration getConfiguration() {
        return this.myConfiguration;
    }
    
    @Override
    public boolean reserveInclude(@NotNull final VirtualFile virtualFile, final boolean b) {
        try {
            if (virtualFile == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "file", "com/jetbrains/cidr/lang/preprocessor/OCInclusionContextImpl", "reserveInclude"));
            }
        }
        catch (IllegalStateException ex) {
            throw a(ex);
        }
        if (virtualFile.isValid()) {
            final String inclusionId = OCInclusionContextUtil.inclusionId(virtualFile);
            Label_0081: {
                try {
                    if (!b) {
                        break Label_0081;
                    }
                    final OCInclusionContextImpl ocInclusionContextImpl = this;
                    final String s = inclusionId;
                    final boolean b2 = ocInclusionContextImpl.isDefined(s);
                    if (b2) {
                        return false;
                    }
                    break Label_0081;
                }
                catch (IllegalStateException ex2) {
                    throw a(ex2);
                }
                try {
                    final OCInclusionContextImpl ocInclusionContextImpl = this;
                    final String s = inclusionId;
                    final boolean b2 = ocInclusionContextImpl.isDefined(s);
                    if (b2) {
                        return false;
                    }
                }
                catch (IllegalStateException ex3) {
                    throw a(ex3);
                }
            }
            final String pragmaOnceId = OCInclusionContextUtil.pragmaOnceId(virtualFile);
            try {
                if (this.isDefined(pragmaOnceId)) {
                    return false;
                }
            }
            catch (IllegalStateException ex4) {
                throw a(ex4);
            }
            this.define(OCMacroSymbol.inclusionGuard(inclusionId));
        }
        return true;
    }
    
    @Override
    public void preprocessContextOf(@Nullable final PsiFile psiFile, @Nullable final VirtualFile virtualFile) {
        this.preprocessInclude(psiFile, true, virtualFile, this.myInclusionLevel);
    }
    
    @Override
    public void preprocessInclude(@Nullable final PsiFile psiFile, final boolean b) {
        this.preprocessInclude(psiFile, b, null, this.myInclusionLevel);
    }
    
    @Override
    public boolean preprocessInclude(@Nullable final PsiFile p0, final boolean p1, @Nullable final VirtualFile p2, final int p3) {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     0: aload_1        
        //     1: ifnull          20
        //     4: iload           4
        //     6: aconst_null    
        //     7: invokestatic    com/jetbrains/cidr/lang/preprocessor/OCInclusionContext.getMaxInclusionLevel:(Lcom/intellij/openapi/project/Project;)I
        //    10: if_icmplt       26
        //    13: goto            20
        //    16: invokestatic    com/jetbrains/cidr/lang/preprocessor/OCInclusionContextImpl.a:(Ljava/lang/IllegalStateException;)Ljava/lang/IllegalStateException;
        //    19: athrow         
        //    20: iconst_1       
        //    21: ireturn        
        //    22: invokestatic    com/jetbrains/cidr/lang/preprocessor/OCInclusionContextImpl.a:(Ljava/lang/IllegalStateException;)Ljava/lang/IllegalStateException;
        //    25: athrow         
        //    26: aload_1        
        //    27: invokestatic    com/jetbrains/cidr/lang/preprocessor/OCInclusionContextUtil.getVirtualFile:(Lcom/intellij/psi/PsiFile;)Lcom/intellij/openapi/vfs/VirtualFile;
        //    30: astore          5
        //    32: aload           5
        //    34: ifnull          54
        //    37: aload_0        
        //    38: aload           5
        //    40: iload_2        
        //    41: invokevirtual   com/jetbrains/cidr/lang/preprocessor/OCInclusionContextImpl.reserveInclude:(Lcom/intellij/openapi/vfs/VirtualFile;Z)Z
        //    44: ifne            60
        //    47: goto            54
        //    50: invokestatic    com/jetbrains/cidr/lang/preprocessor/OCInclusionContextImpl.a:(Ljava/lang/IllegalStateException;)Ljava/lang/IllegalStateException;
        //    53: athrow         
        //    54: iconst_1       
        //    55: ireturn        
        //    56: invokestatic    com/jetbrains/cidr/lang/preprocessor/OCInclusionContextImpl.a:(Ljava/lang/IllegalStateException;)Ljava/lang/IllegalStateException;
        //    59: athrow         
        //    60: aload_0        
        //    61: aload           5
        //    63: invokevirtual   com/jetbrains/cidr/lang/preprocessor/OCInclusionContextImpl.addProcessedFile:(Lcom/intellij/openapi/vfs/VirtualFile;)V
        //    66: aload_1        
        //    67: aload_0        
        //    68: invokestatic    com/jetbrains/cidr/lang/symbols/symtable/FileSymbolTable.forFile:(Lcom/intellij/psi/PsiFile;Lcom/jetbrains/cidr/lang/preprocessor/OCInclusionContext;)Lcom/jetbrains/cidr/lang/symbols/symtable/FileSymbolTable;
        //    71: astore          6
        //    73: aload           6
        //    75: ifnull          133
        //    78: aload_1        
        //    79: instanceof      Lcom/jetbrains/cidr/lang/psi/OCFile;
        //    82: ifeq            133
        //    85: goto            92
        //    88: invokestatic    com/jetbrains/cidr/lang/preprocessor/OCInclusionContextImpl.a:(Ljava/lang/IllegalStateException;)Ljava/lang/IllegalStateException;
        //    91: athrow         
        //    92: aload_0        
        //    93: aload_1        
        //    94: checkcast       Lcom/jetbrains/cidr/lang/psi/OCFile;
        //    97: aload           5
        //    99: aload_3        
        //   100: iload           4
        //   102: iconst_m1      
        //   103: iconst_m1      
        //   104: aload           6
        //   106: invokevirtual   com/jetbrains/cidr/lang/symbols/symtable/FileSymbolTable.getContents:()Ljava/util/List;
        //   109: aconst_null    
        //   110: invokevirtual   com/jetbrains/cidr/lang/preprocessor/OCInclusionContextImpl.preprocessFile:(Lcom/jetbrains/cidr/lang/psi/OCFile;Lcom/intellij/openapi/vfs/VirtualFile;Lcom/intellij/openapi/vfs/VirtualFile;IIILjava/util/List;Lcom/jetbrains/cidr/lang/preprocessor/OCContextChangeSet;)Lcom/jetbrains/cidr/lang/preprocessor/OCInclusionContext;
        //   113: ifnull          131
        //   116: goto            123
        //   119: invokestatic    com/jetbrains/cidr/lang/preprocessor/OCInclusionContextImpl.a:(Ljava/lang/IllegalStateException;)Ljava/lang/IllegalStateException;
        //   122: athrow         
        //   123: iconst_1       
        //   124: goto            132
        //   127: invokestatic    com/jetbrains/cidr/lang/preprocessor/OCInclusionContextImpl.a:(Ljava/lang/IllegalStateException;)Ljava/lang/IllegalStateException;
        //   130: athrow         
        //   131: iconst_0       
        //   132: ireturn        
        //   133: iconst_1       
        //   134: ireturn        
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                             
        //  -----  -----  -----  -----  ---------------------------------
        //  0      13     16     20     Ljava/lang/IllegalStateException;
        //  4      22     22     26     Ljava/lang/IllegalStateException;
        //  32     47     50     54     Ljava/lang/IllegalStateException;
        //  37     56     56     60     Ljava/lang/IllegalStateException;
        //  73     85     88     92     Ljava/lang/IllegalStateException;
        //  78     116    119    123    Ljava/lang/IllegalStateException;
        //  92     127    127    131    Ljava/lang/IllegalStateException;
        // 
        // The error that occurred was:
        // 
        // java.lang.IllegalStateException: Expression is linked from several locations: Label_0092:
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
    
    @Contract("_, _, null, _, _, _, _, _ -> !null")
    @Override
    public OCInclusionContext preprocessFile(@Nullable final OCFile ocFile, @Nullable final VirtualFile virtualFile, @Nullable final VirtualFile virtualFile2, final int n, final int n2, final int n3, @NotNull final List<OCSymbol> list, @Nullable final OCContextChangeSet set) {
        try {
            if (list == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "symbols", "com/jetbrains/cidr/lang/preprocessor/OCInclusionContextImpl", "preprocessFile"));
            }
        }
        catch (IllegalStateException ex) {
            throw a(ex);
        }
        final OCInclusionContextImpl.1Builder 1Builder = new OCInclusionContextImpl.1Builder(this.myNameScope, this.myChangeBuilder, ocFile, virtualFile, virtualFile2, n, set, n2, n3);
        try {
            if (OCResolveUtil.processSymbolsFromList((Processor<OCSymbol>)1Builder, list, n2, n3)) {
                this.myNameScope = 1Builder.getNameScope();
                return this;
            }
        }
        catch (IllegalStateException ex2) {
            throw a(ex2);
        }
        return null;
    }
    
    @Override
    public boolean preprocessInclude(@NotNull final PsiFile psiFile, final boolean b, @Nullable final VirtualFile virtualFile, final int n, final int n2, @Nullable final OCContextChangeSet set) {
        try {
            if (psiFile == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "file", "com/jetbrains/cidr/lang/preprocessor/OCInclusionContextImpl", "preprocessInclude"));
            }
        }
        catch (IllegalStateException ex) {
            throw a(ex);
        }
        OCContextChange changeBuilder = null;
        OCContextChange change = null;
        if (set != null) {
            change = set.getChange(n2);
            if (change == null) {
                changeBuilder = new OCContextChangeBuilderImpl(n2);
                this.setChangeBuilder((OCContextChangeBuilder)changeBuilder);
            }
        }
        boolean preprocessInclude;
        if (change == null) {
            preprocessInclude = this.preprocessInclude(psiFile, b, virtualFile, n);
        }
        else {
            change.apply(psiFile.getProject(), this);
            preprocessInclude = true;
        }
        Label_0148: {
            try {
                if (changeBuilder == null) {
                    return preprocessInclude;
                }
                final OCInclusionContextImpl ocInclusionContextImpl = this;
                final OCInclusionContextImpl ocInclusionContextImpl2 = this;
                final PsiFile psiFile2 = ocInclusionContextImpl2.myRootFile;
                final VirtualFile virtualFile2 = OCInclusionContextUtil.getVirtualFile(psiFile2);
                final boolean b2 = ocInclusionContextImpl.isProcessed(virtualFile2);
                if (!b2) {
                    break Label_0148;
                }
                return preprocessInclude;
            }
            catch (IllegalStateException ex2) {
                throw a(ex2);
            }
            try {
                final OCInclusionContextImpl ocInclusionContextImpl = this;
                final OCInclusionContextImpl ocInclusionContextImpl2 = this;
                final PsiFile psiFile2 = ocInclusionContextImpl2.myRootFile;
                final VirtualFile virtualFile2 = OCInclusionContextUtil.getVirtualFile(psiFile2);
                final boolean b2 = ocInclusionContextImpl.isProcessed(virtualFile2);
                if (!b2) {
                    set.setChange(n2, changeBuilder);
                    this.setChangeBuilder(null);
                }
            }
            catch (IllegalStateException ex3) {
                throw a(ex3);
            }
        }
        return preprocessInclude;
    }
    
    public DeepEqual.Equality<OCInclusionContextImpl> equality() {
        return new DeepEqual.Equality<OCInclusionContextImpl>() {
            @Override
            public boolean deepEqualStep(@NotNull final DeepEqual.Comparator comparator, @NotNull final OCInclusionContextImpl ocInclusionContextImpl, @NotNull final OCInclusionContextImpl ocInclusionContextImpl2) {
                try {
                    if (comparator == null) {
                        throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "c", "com/jetbrains/cidr/lang/preprocessor/OCInclusionContextImpl$1", "deepEqualStep"));
                    }
                }
                catch (IllegalArgumentException ex) {
                    throw a(ex);
                }
                try {
                    if (ocInclusionContextImpl == null) {
                        throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "first", "com/jetbrains/cidr/lang/preprocessor/OCInclusionContextImpl$1", "deepEqualStep"));
                    }
                }
                catch (IllegalArgumentException ex2) {
                    throw a(ex2);
                }
                try {
                    if (ocInclusionContextImpl2 == null) {
                        throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "second", "com/jetbrains/cidr/lang/preprocessor/OCInclusionContextImpl$1", "deepEqualStep"));
                    }
                }
                catch (IllegalArgumentException ex3) {
                    throw a(ex3);
                }
                try {
                    if (ocInclusionContextImpl.myLanguageKind != ocInclusionContextImpl2.myLanguageKind) {
                        return false;
                    }
                }
                catch (IllegalArgumentException ex4) {
                    throw a(ex4);
                }
                try {
                    if (ocInclusionContextImpl.myInclusionLevel != ocInclusionContextImpl2.myInclusionLevel) {
                        return false;
                    }
                }
                catch (IllegalArgumentException ex5) {
                    throw a(ex5);
                }
                try {
                    if (!Comparing.equal((Object)ocInclusionContextImpl.myRootFile, (Object)ocInclusionContextImpl2.myRootFile)) {
                        return false;
                    }
                }
                catch (IllegalArgumentException ex6) {
                    throw a(ex6);
                }
                try {
                    if (ocInclusionContextImpl.myNotifyLocalDefinitions != ocInclusionContextImpl2.myNotifyLocalDefinitions) {
                        return false;
                    }
                }
                catch (IllegalArgumentException ex7) {
                    throw a(ex7);
                }
                try {
                    if (ocInclusionContextImpl.myConfiguration != ocInclusionContextImpl2.myConfiguration) {
                        return false;
                    }
                }
                catch (IllegalArgumentException ex8) {
                    throw a(ex8);
                }
                try {
                    if (!ocInclusionContextImpl.myUndefList.equals(ocInclusionContextImpl2.myUndefList)) {
                        return false;
                    }
                }
                catch (IllegalArgumentException ex9) {
                    throw a(ex9);
                }
                try {
                    if (!ocInclusionContextImpl.myProcessedFiles.equals(ocInclusionContextImpl2.myProcessedFiles)) {
                        return false;
                    }
                }
                catch (IllegalArgumentException ex10) {
                    throw a(ex10);
                }
                try {
                    if (!Comparing.equal((Object)ocInclusionContextImpl.mySignatureBuilder, (Object)ocInclusionContextImpl2.mySignatureBuilder)) {
                        return false;
                    }
                }
                catch (IllegalArgumentException ex11) {
                    throw a(ex11);
                }
                try {
                    if (!comparator.equalObjects(ocInclusionContextImpl.myParentContext, ocInclusionContextImpl2.myParentContext)) {
                        return false;
                    }
                }
                catch (IllegalArgumentException ex12) {
                    throw a(ex12);
                }
                try {
                    if (!comparator.equalMaps((Map)ocInclusionContextImpl.mySubstitutions, (Map)ocInclusionContextImpl2.mySubstitutions)) {
                        return false;
                    }
                }
                catch (IllegalArgumentException ex13) {
                    throw a(ex13);
                }
                return comparator.equalObjects(ocInclusionContextImpl.myNameScope, ocInclusionContextImpl2.myNameScope);
            }
            
            private static IllegalArgumentException a(final IllegalArgumentException ex) {
                return ex;
            }
        };
    }
    
    static {
        boolean $assertionsDisabled2 = false;
        Label_0017: {
            try {
                if (!OCInclusionContextImpl.class.desiredAssertionStatus()) {
                    $assertionsDisabled2 = true;
                    break Label_0017;
                }
            }
            catch (IllegalStateException ex) {
                throw a(ex);
            }
            $assertionsDisabled2 = false;
        }
        $assertionsDisabled = $assertionsDisabled2;
        OCInclusionContextImpl.DEFAULT_MAX_INCLUSION_LEVEL = 256;
        NULL_VALUE = new Object();
        INCLUDE_RESOLVE_CACHE = NotNullLazyKey.create("INCLUDE_RESOLVE_CACHE", ocResolveConfiguration -> ContainerUtil.createConcurrentWeakMap());
        PROJECT_MODIFICATION_TRACKER = NotNullLazyKey.create("PROJECT_MODIFICATION_TRACKER", project -> new SimpleModificationTracker());
        PCH_CACHE_KEY = Key.create("PCH_CACHE_KEY");
        INITIAL_PLAIN_AND_PCH_CONTEXTS_KEY = NotNullLazyKey.create("INITIAL_CONTEXTS_KEY", ocResolveConfiguration -> Couple.of((Object)new ConcurrentHashMap(), (Object)new ConcurrentHashMap()));
    }
    
    private static IllegalStateException a(final IllegalStateException ex) {
        return ex;
    }
    
    static class PCHCache
    {
        private final MultiMap<VirtualFile, OCResolveConfiguration> pch2configs;
        
        public PCHCache(@NotNull final Project project) {
            if (project == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "project", "com/jetbrains/cidr/lang/preprocessor/OCInclusionContextImpl$PCHCache", "<init>"));
            }
            this.pch2configs = new MultiMap<VirtualFile, OCResolveConfiguration>() {
                @NotNull
                protected Collection<OCResolveConfiguration> createCollection() {
                    LinkedHashSet linkedHashSet;
                    try {
                        linkedHashSet = ContainerUtil.newLinkedHashSet();
                        if (linkedHashSet == null) {
                            throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/preprocessor/OCInclusionContextImpl$PCHCache$1", "createCollection"));
                        }
                    }
                    catch (IllegalStateException ex) {
                        throw a(ex);
                    }
                    return (Collection<OCResolveConfiguration>)linkedHashSet;
                }
                
                private static IllegalStateException a(final IllegalStateException ex) {
                    return ex;
                }
            };
            for (final OCResolveConfiguration ocResolveConfiguration : OCWorkspaceManager.getWorkspace(project).getConfigurations()) {
                final Iterator<VirtualFile> iterator2 = ocResolveConfiguration.getPrecompiledHeaders().iterator();
                while (iterator2.hasNext()) {
                    this.pch2configs.putValue((Object)iterator2.next(), (Object)ocResolveConfiguration);
                }
            }
        }
        
        public Collection<OCResolveConfiguration> getConfigurations(@NotNull final VirtualFile virtualFile) {
            try {
                if (virtualFile == null) {
                    throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "pch", "com/jetbrains/cidr/lang/preprocessor/OCInclusionContextImpl$PCHCache", "getConfigurations"));
                }
            }
            catch (IllegalArgumentException ex) {
                throw a(ex);
            }
            return Collections.unmodifiableCollection((Collection<? extends OCResolveConfiguration>)this.pch2configs.get((Object)virtualFile));
        }
        
        public boolean isPCH(@NotNull final VirtualFile virtualFile) {
            try {
                if (virtualFile == null) {
                    throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "file", "com/jetbrains/cidr/lang/preprocessor/OCInclusionContextImpl$PCHCache", "isPCH"));
                }
            }
            catch (IllegalArgumentException ex) {
                throw a(ex);
            }
            return this.pch2configs.containsKey((Object)virtualFile);
        }
        
        private static IllegalArgumentException a(final IllegalArgumentException ex) {
            return ex;
        }
    }
}
