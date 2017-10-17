// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.lang.psi.impl.symbols;

import java.util.Iterator;
import com.jetbrains.cidr.lang.OCLanguageKind;
import com.jetbrains.cidr.lang.workspace.OCResolveConfiguration;
import com.jetbrains.cidr.lang.preprocessor.OCImmutableInclusionContext;
import java.util.List;
import java.util.Set;
import com.jetbrains.cidr.lang.util.OCElementUtil;
import com.jetbrains.cidr.lang.symbols.symtable.FileSymbolTable;
import com.jetbrains.cidr.lang.preprocessor.OCInclusionContext;
import com.intellij.psi.PsiFile;
import com.jetbrains.cidr.lang.preprocessor.OCInclusionContextUtil;
import java.util.Collections;
import gnu.trove.THashSet;
import com.jetbrains.cidr.lang.symbols.symtable.FileSymbolTablesCache;
import org.jetbrains.annotations.Nullable;
import com.intellij.openapi.vfs.VirtualFile;
import java.util.Map;
import com.jetbrains.cidr.lang.symbols.OCSymbol;
import com.intellij.util.Processor;
import com.intellij.psi.util.PsiModificationTracker;
import org.jetbrains.annotations.NotNull;
import com.jetbrains.cidr.lang.psi.impl.OCFileImpl;
import com.intellij.psi.PsiManager;
import com.intellij.util.messages.MessageBus;
import com.jetbrains.cidr.lang.symbols.cpp.OCNamespaceLikeSymbol;
import com.intellij.openapi.project.Project;
import com.jetbrains.cidr.lang.psi.OCFile;
import com.intellij.openapi.diagnostic.Logger;

public class OCFileGlobalSymbolsCache
{
    private static final Logger LOG;
    private static final int CACHE_SIZE;
    private final SLRUSynchronizedCache<OCFile, OCFileGlobalSymbols> myCache;
    private final SLRUSynchronizedCache<OCFile, OCLightFileGlobalSymbols> myLightCache;
    private final SLRUSynchronizedCache<OCFile, OCLightFileGlobalSymbols> myTypesOnlyCache;
    private long myModCount;
    
    public static OCFileGlobalSymbolsCache getInstance(final Project project) {
        return (OCFileGlobalSymbolsCache)project.getComponent((Class)OCFileGlobalSymbolsCache.class);
    }
    
    public OCFileGlobalSymbols forFile(final OCFile ocFile) {
        return this.myCache.get(ocFile);
    }
    
    public OCNamespaceLikeSymbol lightTableForFile(final OCFile ocFile) {
        final OCFileGlobalSymbols ocFileGlobalSymbols = this.myCache.getIfCached(ocFile);
        try {
            if (ocFileGlobalSymbols != null) {
                return ocFileGlobalSymbols;
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        return this.myLightCache.get(ocFile);
    }
    
    public OCNamespaceLikeSymbol typesOnlyTableForFile(final OCFile ocFile) {
        final OCFileGlobalSymbols ocFileGlobalSymbols = this.myCache.getIfCached(ocFile);
        try {
            if (ocFileGlobalSymbols != null) {
                return ocFileGlobalSymbols;
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        final OCLightFileGlobalSymbols ocLightFileGlobalSymbols = this.myLightCache.getIfCached(ocFile);
        try {
            if (ocLightFileGlobalSymbols != null) {
                return ocLightFileGlobalSymbols;
            }
        }
        catch (IllegalArgumentException ex2) {
            throw a(ex2);
        }
        return this.myTypesOnlyCache.get(ocFile);
    }
    
    public void clear() {
        this.myCache.clear();
        this.myLightCache.clear();
        this.myTypesOnlyCache.clear();
    }
    
    public OCFileGlobalSymbolsCache(final MessageBus messageBus, final PsiManager psiManager) {
        this.myCache = new SLRUSynchronizedCache<OCFile, OCFileGlobalSymbols>(OCFileGlobalSymbolsCache.CACHE_SIZE, OCFileGlobalSymbolsCache.CACHE_SIZE) {
            @NotNull
            @Override
            public OCFileGlobalSymbols createValue(final OCFile ocFile) {
                OCFileGlobalSymbols buildSymbols;
                try {
                    buildSymbols = OCFileGlobalSymbols.buildSymbols((OCFileImpl)ocFile);
                    if (buildSymbols == null) {
                        throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/psi/impl/symbols/OCFileGlobalSymbolsCache$1", "createValue"));
                    }
                }
                catch (IllegalStateException ex) {
                    throw b(ex);
                }
                return buildSymbols;
            }
            
            private static IllegalStateException b(final IllegalStateException ex) {
                return ex;
            }
        };
        this.myLightCache = new SLRUSynchronizedCache<OCFile, OCLightFileGlobalSymbols>(20, 20) {
            @NotNull
            @Override
            public OCLightFileGlobalSymbols createValue(final OCFile ocFile) {
                OCLightFileGlobalSymbols buildSymbols;
                try {
                    buildSymbols = OCLightFileGlobalSymbols.buildSymbols((OCFileImpl)ocFile, false);
                    if (buildSymbols == null) {
                        throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/psi/impl/symbols/OCFileGlobalSymbolsCache$2", "createValue"));
                    }
                }
                catch (IllegalStateException ex) {
                    throw b(ex);
                }
                return buildSymbols;
            }
            
            private static IllegalStateException b(final IllegalStateException ex) {
                return ex;
            }
        };
        this.myTypesOnlyCache = new SLRUSynchronizedCache<OCFile, OCLightFileGlobalSymbols>(150, 150) {
            @NotNull
            @Override
            public OCLightFileGlobalSymbols createValue(final OCFile ocFile) {
                OCLightFileGlobalSymbols buildSymbols;
                try {
                    buildSymbols = OCLightFileGlobalSymbols.buildSymbols((OCFileImpl)ocFile, true);
                    if (buildSymbols == null) {
                        throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/psi/impl/symbols/OCFileGlobalSymbolsCache$3", "createValue"));
                    }
                }
                catch (IllegalStateException ex) {
                    throw b(ex);
                }
                return buildSymbols;
            }
            
            private static IllegalStateException b(final IllegalStateException ex) {
                return ex;
            }
        };
        this.myModCount = -1L;
        messageBus.connect().subscribe(PsiModificationTracker.TOPIC, (Object)new PsiModificationTracker.Listener() {
            final /* synthetic */ PsiModificationTracker val$tracker = psiManager.getModificationTracker();
            
            public void modificationCountChanged() {
                final long outOfCodeBlockModificationCount = this.val$tracker.getOutOfCodeBlockModificationCount();
                if (OCFileGlobalSymbolsCache.this.myModCount != outOfCodeBlockModificationCount) {
                    OCFileGlobalSymbolsCache.this.myModCount = outOfCodeBlockModificationCount;
                    OCFileGlobalSymbolsCache.this.clear();
                }
            }
        });
    }
    
    public static void processFile(final OCFileImpl ocFileImpl, final Processor<OCSymbol> processor, @Nullable final Map<OCSymbol, VirtualFile> map) {
        Label_0025: {
            try {
                if (processFileImpl(ocFileImpl, processor, map)) {
                    return;
                }
                final OCFileImpl ocFileImpl2 = ocFileImpl;
                final Processor<OCSymbol> processor2 = processor;
                final Map<OCSymbol, VirtualFile> map2 = map;
                final boolean b = processFileImpl(ocFileImpl2, processor2, map2);
                if (!b) {
                    break Label_0025;
                }
                return;
            }
            catch (IllegalArgumentException ex) {
                throw a(ex);
            }
            try {
                final OCFileImpl ocFileImpl2 = ocFileImpl;
                final Processor<OCSymbol> processor2 = processor;
                final Map<OCSymbol, VirtualFile> map2 = map;
                final boolean b = processFileImpl(ocFileImpl2, processor2, map2);
                if (!b) {
                    OCFileGlobalSymbolsCache.LOG.error("Symbols were collected incorrectly");
                }
            }
            catch (IllegalArgumentException ex2) {
                throw a(ex2);
            }
        }
    }
    
    public static boolean processFileImpl(@NotNull final OCFileImpl ocFileImpl, final Processor<OCSymbol> processor, @Nullable final Map<OCSymbol, VirtualFile> map) {
        try {
            if (ocFileImpl == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "file", "com/jetbrains/cidr/lang/psi/impl/symbols/OCFileGlobalSymbolsCache", "processFileImpl"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        try {
            if (!FileSymbolTablesCache.areSymbolsLoaded(ocFileImpl.getProject())) {
                return true;
            }
        }
        catch (IllegalArgumentException ex2) {
            throw a(ex2);
        }
        final VirtualFile virtualFile = ocFileImpl.getVirtualFile();
        final THashSet set = new THashSet();
        List<OCFile> list = Collections.emptyList();
        OCImmutableInclusionContext initialPCHContextWithoutRoot = null;
        OCInclusionContext ocInclusionContext = null;
        final OCImmutableInclusionContext headerContext = OCInclusionContextUtil.headerContext((PsiFile)ocFileImpl);
        final PsiFile rootFile = headerContext.getRootFile();
        final OCResolveConfiguration configuration = headerContext.getConfiguration();
        if (configuration != null) {
            final OCLanguageKind languageKind = headerContext.getLanguageKind();
            initialPCHContextWithoutRoot = OCInclusionContext.initialPCHContextWithoutRoot(configuration, languageKind, rootFile);
            list = initialPCHContextWithoutRoot.getPrecompiledHeaders();
            ocInclusionContext = OCInclusionContext.beforePCHFileContext(configuration, languageKind, rootFile);
        }
        if (ocInclusionContext == null) {
            ocInclusionContext = OCInclusionContext.beforePCHFileContext(ocFileImpl);
        }
        boolean b = false;
        for (final OCFile ocFile : list) {
            final FileSymbolTable forFile = FileSymbolTable.forFile((PsiFile)ocFile, ocInclusionContext);
            Label_0257: {
                Label_0231: {
                    try {
                        if (forFile == null) {
                            continue;
                        }
                        final OCImmutableInclusionContext ocImmutableInclusionContext = initialPCHContextWithoutRoot;
                        final VirtualFile virtualFile2 = virtualFile;
                        final boolean b2 = ocImmutableInclusionContext.isProcessed(virtualFile2);
                        if (!b2) {
                            break Label_0231;
                        }
                        break Label_0257;
                    }
                    catch (IllegalArgumentException ex3) {
                        throw a(ex3);
                    }
                    try {
                        final OCImmutableInclusionContext ocImmutableInclusionContext = initialPCHContextWithoutRoot;
                        final VirtualFile virtualFile2 = virtualFile;
                        final boolean b2 = ocImmutableInclusionContext.isProcessed(virtualFile2);
                        if (!b2) {
                            forFile.processSymbols(processor, null, (Set<FileSymbolTable>)set, map, OCElementUtil.getFilePath((PsiFile)ocFile), ocInclusionContext, null);
                            continue;
                        }
                    }
                    catch (IllegalArgumentException ex4) {
                        throw a(ex4);
                    }
                }
                break Label_0257;
                continue;
            }
            forFile.processSymbols(processor, null, (Set<FileSymbolTable>)set, map, OCElementUtil.getFilePath((PsiFile)ocFile), ocInclusionContext, virtualFile);
            b = true;
            break;
        }
        Label_0358: {
            try {
                if (b || !ocFileImpl.isHeader()) {
                    break Label_0358;
                }
            }
            catch (IllegalArgumentException ex5) {
                throw a(ex5);
            }
            final FileSymbolTable forFile2 = FileSymbolTable.forFile(rootFile, ocInclusionContext);
            if (forFile2 != null) {
                final VirtualFile virtualFile3 = OCInclusionContextUtil.getVirtualFile((PsiFile)ocFileImpl);
                try {
                    forFile2.processSymbols(processor, null, (Set<FileSymbolTable>)set, map, null, ocInclusionContext, virtualFile3);
                    if (ocInclusionContext.isProcessed(virtualFile3)) {
                        return false;
                    }
                }
                catch (IllegalArgumentException ex6) {
                    throw a(ex6);
                }
            }
        }
        final FileSymbolTable symbolTable = ocFileImpl.getSymbolTable(ocInclusionContext);
        try {
            if (symbolTable != null) {
                symbolTable.processSymbols(processor, null, (Set<FileSymbolTable>)set, map, null, ocInclusionContext, null);
            }
        }
        catch (IllegalArgumentException ex7) {
            throw a(ex7);
        }
        return true;
    }
    
    static {
        LOG = Logger.getInstance("com.jetbrains.cidr.lang.psi.impl.symbols.OCFileGlobalSymbolsCache");
        CACHE_SIZE = 2 * Runtime.getRuntime().availableProcessors();
    }
    
    private static IllegalArgumentException a(final IllegalArgumentException ex) {
        return ex;
    }
}
