// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.lang.symbols.symtable;

import com.intellij.psi.tree.IElementType;
import com.jetbrains.cidr.lang.parser.OCElementTypes;
import com.intellij.lang.ASTNode;
import com.intellij.psi.PsiElement;
import com.jetbrains.cidr.lang.psi.OCCodeFragment;
import com.intellij.psi.impl.PsiTreeChangeEventImpl;
import com.intellij.openapi.util.Key;
import java.io.File;
import java.util.HashMap;
import com.intellij.openapi.application.ReadAction;
import com.intellij.openapi.util.ThrowableComputable;
import com.intellij.util.Function;
import com.intellij.openapi.util.text.StringUtil;
import com.intellij.openapi.util.Comparing;
import java.util.concurrent.ExecutionException;
import com.intellij.util.SmartList;
import java.util.concurrent.Future;
import com.intellij.util.containers.MultiMap;
import java.util.ArrayDeque;
import com.intellij.util.StringBuilderSpinAllocator;
import java.io.DataOutputStream;
import com.intellij.util.Consumer;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.io.DataInputStream;
import java.io.IOException;
import java.util.Collections;
import com.intellij.openapi.progress.ProgressIndicator;
import com.jetbrains.cidr.lang.workspace.OCResolveConfiguration;
import com.intellij.lang.FileASTNode;
import com.intellij.psi.impl.source.tree.FileElement;
import com.intellij.psi.impl.source.PsiFileImpl;
import com.intellij.psi.impl.file.impl.FileManager;
import com.intellij.util.FileContentUtilCore;
import com.intellij.psi.FileViewProvider;
import java.util.ArrayList;
import com.intellij.openapi.application.ModalityState;
import com.intellij.psi.impl.PsiManagerEx;
import com.jetbrains.cidr.lang.psi.OCFile;
import com.intellij.openapi.progress.ProgressManager;
import com.jetbrains.cidr.lang.preprocessor.OCInclusionContext;
import org.jetbrains.annotations.Contract;
import com.intellij.openapi.vfs.VfsUtilCore;
import com.jetbrains.cidr.lang.workspace.OCWorkspace;
import com.jetbrains.cidr.lang.search.scopes.OCSearchScope;
import com.jetbrains.cidr.lang.workspace.OCWorkspaceManager;
import java.util.LinkedHashSet;
import java.util.Collection;
import com.intellij.openapi.vfs.newvfs.NewVirtualFile;
import com.intellij.psi.PsiFile;
import com.jetbrains.cidr.lang.preprocessor.OCImportGraph;
import com.jetbrains.cidr.lang.symbols.cpp.OCIncludeSymbol;
import com.intellij.util.Processor;
import com.intellij.openapi.util.Ref;
import com.intellij.openapi.util.Couple;
import org.jetbrains.annotations.Nullable;
import com.intellij.openapi.wm.StatusBar;
import com.intellij.openapi.Disposable;
import com.intellij.openapi.wm.StatusBarWidget;
import com.jetbrains.cidr.lang.ui.OCResolveContextPanel;
import com.intellij.openapi.wm.WindowManager;
import com.intellij.openapi.util.ModificationTracker;
import com.intellij.util.messages.MessageBusConnection;
import com.intellij.openapi.startup.StartupManager;
import com.intellij.openapi.application.ApplicationManager;
import java.util.Iterator;
import com.intellij.openapi.vfs.newvfs.events.VFileCreateEvent;
import com.intellij.openapi.vfs.newvfs.events.VFileDeleteEvent;
import com.intellij.util.containers.HashSet;
import com.intellij.openapi.vfs.newvfs.events.VFileEvent;
import java.util.List;
import com.intellij.openapi.vfs.newvfs.BulkFileListener;
import com.intellij.openapi.vfs.VirtualFileManager;
import com.intellij.util.messages.Topic;
import com.jetbrains.cidr.lang.preprocessor.OCInclusionContextUtil;
import com.jetbrains.cidr.lang.workspace.OCWorkspaceModificationListener;
import com.intellij.psi.impl.PsiTreeChangePreprocessor;
import com.intellij.psi.impl.PsiManagerImpl;
import com.intellij.psi.impl.PsiModificationTrackerImpl;
import gnu.trove.THashMap;
import gnu.trove.THashSet;
import com.intellij.util.containers.ContainerUtil;
import com.intellij.psi.PsiManager;
import com.intellij.util.messages.MessageBus;
import com.intellij.util.lang.CompoundRuntimeException;
import org.jetbrains.annotations.NotNull;
import com.intellij.openapi.project.Project;
import java.util.concurrent.atomic.AtomicBoolean;
import com.intellij.openapi.util.SimpleModificationTracker;
import java.util.Set;
import com.intellij.openapi.vfs.VirtualFile;
import java.util.concurrent.ConcurrentMap;
import com.intellij.openapi.vfs.newvfs.FileAttribute;
import java.util.Map;
import com.intellij.openapi.diagnostic.Logger;
import com.intellij.openapi.components.AbstractProjectComponent;

public class FileSymbolTablesCache extends AbstractProjectComponent
{
    private static final Logger LOG;
    private static final int SERIALIZATION_INDEX_VERSION = 125;
    private static Boolean ourShouldBuildCachesInTests;
    private static Boolean ourDoNotReloadExistingSymbolsInTests;
    private static Boolean ourForceSymbolsLoadedInTests;
    private static final Map<String, FileAttribute> ourFileCacheAttributes;
    private static final FileAttribute ourFileTablesMetaAttribute;
    private static final String TABLES_KEY_PREFIX = "objc_file_symbol_tables_attribute";
    private final ConcurrentMap<VirtualFile, FileSymbolTablesPack> myCache;
    private FileSymbolTableUpdater myTableUpdater;
    private final Set<VirtualFile> myPendingReparses;
    private final Set<VirtualFile> myPendingPSIResets;
    private static final int PARALLEL_SERIALIZERS;
    private final FileSymbolTableSerializer[] mySerializers;
    private final SimpleModificationTracker myOutOfBlockModificationTracker;
    private final SimpleModificationTracker myCidrOutOfBlockModificationTracker;
    private final AtomicBoolean mySymbolsLoaded;
    private final Object myFileAndContextDependencyLock;
    private final Map<Thread, VirtualFile> myContextToAwaitingFile;
    private final Map<VirtualFile, Thread> myFileToProcessingContext;
    
    private static int b() {
        int n = 125;
        for (final SerializerProvider serializerProvider : (SerializerProvider[])SerializerProvider.EP_NAME.getExtensions()) {
            n = n * 10 + serializerProvider.getClass().getName().hashCode() + serializerProvider.getVersion();
        }
        return n;
    }
    
    @NotNull
    public static FileSymbolTablesCache getInstance(@NotNull final Project project) {
        try {
            if (project == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "project", "com/jetbrains/cidr/lang/symbols/symtable/FileSymbolTablesCache", "getInstance"));
            }
        }
        catch (CompoundRuntimeException ex) {
            throw b((Exception)ex);
        }
        FileSymbolTablesCache fileSymbolTablesCache;
        try {
            fileSymbolTablesCache = (FileSymbolTablesCache)project.getComponent((Class)FileSymbolTablesCache.class);
            if (fileSymbolTablesCache == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/symbols/symtable/FileSymbolTablesCache", "getInstance"));
            }
        }
        catch (CompoundRuntimeException ex2) {
            throw b((Exception)ex2);
        }
        return fileSymbolTablesCache;
    }
    
    public FileSymbolTablesCache(final Project project, final MessageBus messageBus, final PsiManager psiManager) {
        super(project);
        this.myCache = (ConcurrentMap<VirtualFile, FileSymbolTablesPack>)ContainerUtil.newConcurrentMap();
        this.myPendingReparses = (Set<VirtualFile>)new THashSet();
        this.myPendingPSIResets = (Set<VirtualFile>)new THashSet();
        this.myCidrOutOfBlockModificationTracker = new SimpleModificationTracker();
        this.mySymbolsLoaded = new AtomicBoolean(false);
        this.myFileAndContextDependencyLock = new Object();
        this.myContextToAwaitingFile = (Map<Thread, VirtualFile>)new THashMap();
        this.myFileToProcessingContext = (Map<VirtualFile, Thread>)new THashMap();
        this.myTableUpdater = new FileSymbolTableUpdater(project);
        this.mySerializers = new FileSymbolTableSerializer[FileSymbolTablesCache.PARALLEL_SERIALIZERS];
        int i = 0;
        try {
            while (i < FileSymbolTablesCache.PARALLEL_SERIALIZERS) {
                this.mySerializers[i] = new FileSymbolTableSerializer(project);
                ++i;
            }
        }
        catch (CompoundRuntimeException ex) {
            throw b((Exception)ex);
        }
        final PsiModificationTrackerImpl psiModificationTrackerImpl = (PsiModificationTrackerImpl)psiManager.getModificationTracker();
        try {
            this.myOutOfBlockModificationTracker = new SimpleModificationTracker() {
                public void incModificationCount() {
                    super.incModificationCount();
                    psiModificationTrackerImpl.incOutOfCodeBlockModificationCounter();
                }
            };
            if (psiManager instanceof PsiManagerImpl) {
                ((PsiManagerImpl)psiManager).addTreeChangePreprocessor(new OCCodeBlockModificationListener());
            }
        }
        catch (CompoundRuntimeException ex2) {
            throw b((Exception)ex2);
        }
        final MessageBusConnection connect = messageBus.connect();
        try {
            connect.subscribe((Topic)OCWorkspaceModificationListener.TOPIC, (Object)new OCWorkspaceModificationListener() {
                @Override
                public void buildSettingsChanged() {
                    OCSymbolTablesBuildingActivity.getInstance(FileSymbolTablesCache.this.myProject).rebuildSymbols();
                }
                
                @Override
                public void projectFilesChanged() {
                    OCInclusionContextUtil.invalidateHeaderRootAndActiveConfigurationForAllExcept(null, FileSymbolTablesCache.this.myProject);
                }
                
                @Override
                public void sourceFilesChanged() {
                    OCInclusionContextUtil.invalidateHeaderRootAndActiveConfigurationForAllExcept(null, FileSymbolTablesCache.this.myProject);
                }
                
                @Override
                public void selectedResolveConfigurationChanged() {
                    OCInclusionContextUtil.invalidateHeaderRootAndActiveConfigurationForAllExcept(null, FileSymbolTablesCache.this.myProject);
                    FileSymbolTablesCache.this.c();
                }
            });
            connect.subscribe(VirtualFileManager.VFS_CHANGES, (Object)new BulkFileListener() {
                public void after(@NotNull final List<? extends VFileEvent> list) {
                    try {
                        if (list == null) {
                            throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "events", "com/jetbrains/cidr/lang/symbols/symtable/FileSymbolTablesCache$3", "after"));
                        }
                    }
                    catch (IllegalArgumentException ex) {
                        throw a(ex);
                    }
                    final HashSet set = new HashSet();
                    for (final VFileEvent vFileEvent : list) {
                        try {
                            if (vFileEvent instanceof VFileDeleteEvent) {
                                FileSymbolTablesCache.this.a(vFileEvent.getFile(), (Set)set);
                                continue;
                            }
                        }
                        catch (IllegalArgumentException ex2) {
                            throw a(ex2);
                        }
                        if (vFileEvent instanceof VFileCreateEvent) {
                            final VirtualFile file = vFileEvent.getFile();
                            Label_0142: {
                                try {
                                    if (file == null) {
                                        continue;
                                    }
                                    final VirtualFile virtualFile = file;
                                    final boolean b = SymbolTableProvider.isSourceFile(virtualFile);
                                    if (b) {
                                        break Label_0142;
                                    }
                                    continue;
                                }
                                catch (IllegalArgumentException ex3) {
                                    throw a(ex3);
                                }
                                try {
                                    final VirtualFile virtualFile = file;
                                    final boolean b = SymbolTableProvider.isSourceFile(virtualFile);
                                    if (!b) {
                                        continue;
                                    }
                                    set.add((Object)file.getName());
                                }
                                catch (IllegalArgumentException ex4) {
                                    throw a(ex4);
                                }
                            }
                        }
                    }
                    try {
                        if (!set.isEmpty()) {
                            FileSymbolTablesCache.this.invalidateDirtyIncludes((Set<String>)set);
                        }
                    }
                    catch (IllegalArgumentException ex5) {
                        throw a(ex5);
                    }
                }
                
                private static IllegalArgumentException a(final IllegalArgumentException ex) {
                    return ex;
                }
            });
            if (ApplicationManager.getApplication().isUnitTestMode()) {
                StartupManager.getInstance(project).registerPostStartupActivity(() -> OCSymbolTablesBuildingActivity.getInstance(project).rebuildSymbols());
            }
        }
        catch (CompoundRuntimeException ex3) {
            throw b((Exception)ex3);
        }
    }
    
    @NotNull
    public ModificationTracker getOutOfBlockModificationTracker() {
        SimpleModificationTracker myOutOfBlockModificationTracker;
        try {
            myOutOfBlockModificationTracker = this.myOutOfBlockModificationTracker;
            if (myOutOfBlockModificationTracker == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/symbols/symtable/FileSymbolTablesCache", "getOutOfBlockModificationTracker"));
            }
        }
        catch (CompoundRuntimeException ex) {
            throw b((Exception)ex);
        }
        return (ModificationTracker)myOutOfBlockModificationTracker;
    }
    
    @NotNull
    public ModificationTracker getCidrOutOfBlockModificationTracker() {
        SimpleModificationTracker myCidrOutOfBlockModificationTracker;
        try {
            myCidrOutOfBlockModificationTracker = this.myCidrOutOfBlockModificationTracker;
            if (myCidrOutOfBlockModificationTracker == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/symbols/symtable/FileSymbolTablesCache", "getCidrOutOfBlockModificationTracker"));
            }
        }
        catch (CompoundRuntimeException ex) {
            throw b((Exception)ex);
        }
        return (ModificationTracker)myCidrOutOfBlockModificationTracker;
    }
    
    public void incCidrOutOfCodeBlockTracker() {
        this.myCidrOutOfBlockModificationTracker.incModificationCount();
    }
    
    public void projectOpened() {
        final StatusBar statusBar = WindowManager.getInstance().getStatusBar(this.myProject);
        try {
            if (statusBar != null) {
                statusBar.addWidget((StatusBarWidget)new OCResolveContextPanel(this.myProject), "before ReadOnlyAttribute", (Disposable)this.myProject);
            }
        }
        catch (CompoundRuntimeException ex) {
            throw b((Exception)ex);
        }
    }
    
    public boolean shouldBuildTables() {
        try {
            if (FileSymbolTablesCache.ourShouldBuildCachesInTests != Boolean.FALSE) {
                return true;
            }
        }
        catch (CompoundRuntimeException ex) {
            throw b((Exception)ex);
        }
        return false;
    }
    
    @NotNull
    public static Couple<Boolean> setShouldBuildTablesInTests(@Nullable final Boolean ourShouldBuildCachesInTests, @Nullable final Boolean ourDoNotReloadExistingSymbolsInTests) {
        final Couple of = Couple.of((Object)FileSymbolTablesCache.ourShouldBuildCachesInTests, (Object)FileSymbolTablesCache.ourDoNotReloadExistingSymbolsInTests);
        Couple couple;
        try {
            FileSymbolTablesCache.ourShouldBuildCachesInTests = ourShouldBuildCachesInTests;
            FileSymbolTablesCache.ourDoNotReloadExistingSymbolsInTests = ourDoNotReloadExistingSymbolsInTests;
            couple = of;
            if (couple == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/symbols/symtable/FileSymbolTablesCache", "setShouldBuildTablesInTests"));
            }
        }
        catch (CompoundRuntimeException ex) {
            throw b((Exception)ex);
        }
        return (Couple<Boolean>)couple;
    }
    
    public void invalidateDirtyIncludes(final Set<String> set) {
        final Ref ref = new Ref((Object)false);
        final HashSet set2 = new HashSet();
        for (final FileSymbolTable fileSymbolTable : this.allTables()) {
            final VirtualFile containingFile = fileSymbolTable.getContainingFile();
            try {
                if (containingFile == null || !containingFile.isValid()) {
                    continue;
                }
            }
            catch (CompoundRuntimeException ex) {
                throw b((Exception)ex);
            }
            final PsiFile file = PsiManager.getInstance(fileSymbolTable.getProject()).findFile(fileSymbolTable.getContainingFile());
            try {
                if (file == null) {
                    continue;
                }
                fileSymbolTable.processIncludes((Processor<OCIncludeSymbol>)(ocIncludeSymbol -> {
                    if (set.contains(ocIncludeSymbol.getLastPathComponent())) {
                        this.a(file, true, set2, true);
                        ref.set((Object)true);
                        synchronized (this.myPendingReparses) {
                            this.myPendingReparses.add(b(file));
                        }
                    }
                    return true;
                }));
            }
            catch (CompoundRuntimeException ex2) {
                throw b((Exception)ex2);
            }
        }
        try {
            if (ref.get()) {
                OCImportGraph.invalidateHeaderRootsCache(this.myProject);
                OCInclusionContextUtil.invalidateHeaderRootAndActiveConfigurationForAllExcept(null, this.myProject);
            }
        }
        catch (CompoundRuntimeException ex3) {
            throw b((Exception)ex3);
        }
        this.d();
    }
    
    public void compact() {
        final Iterator<FileSymbolTablesPack> iterator = this.myCache.values().iterator();
        while (iterator.hasNext()) {
            iterator.next().compactSynchronized();
        }
    }
    
    private void a(final VirtualFile virtualFile, final Set<String> set) {
        if (virtualFile.isDirectory()) {
            final Iterator<VirtualFile> iterator = ((NewVirtualFile)virtualFile).getCachedChildren().iterator();
            while (iterator.hasNext()) {
                this.a(iterator.next(), set);
            }
        }
        else {
            this.b(virtualFile);
            set.add(virtualFile.getName());
        }
    }
    
    @NotNull
    public Collection<VirtualFile> getFilesToBuildTablesFor() {
        ApplicationManager.getApplication().assertReadAccessAllowed();
        final LinkedHashSet set = new LinkedHashSet();
        final OCWorkspace workspace = OCWorkspaceManager.getWorkspace(this.myProject);
        final Collection<VirtualFile> explicitlySpecifiedProjectSourceFiles = OCSearchScope.getExplicitlySpecifiedProjectSourceFiles(this.myProject);
        List filter;
        try {
            set.addAll(explicitlySpecifiedProjectSourceFiles);
            set.addAll(workspace.getLibraryFilesToBuildSymbols());
            filter = ContainerUtil.filter((Collection)set, virtualFile -> SymbolTableProvider.isSourceFile(virtualFile));
            if (filter == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/symbols/symtable/FileSymbolTablesCache", "getFilesToBuildTablesFor"));
            }
        }
        catch (CompoundRuntimeException ex) {
            throw b((Exception)ex);
        }
        return (Collection<VirtualFile>)filter;
    }
    
    public Set<VirtualFile> getCachedFiles() {
        return this.myCache.keySet();
    }
    
    public void addFileToCache(@Nullable final VirtualFile virtualFile) {
        try {
            if (virtualFile == null) {
                return;
            }
        }
        catch (CompoundRuntimeException ex) {
            throw b((Exception)ex);
        }
        this.myTableUpdater.addFileForUpdate(virtualFile);
    }
    
    public void addFilesToCache(@NotNull final Collection<VirtualFile> collection) {
        try {
            if (collection == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "files", "com/jetbrains/cidr/lang/symbols/symtable/FileSymbolTablesCache", "addFilesToCache"));
            }
        }
        catch (CompoundRuntimeException ex) {
            throw b((Exception)ex);
        }
        this.myTableUpdater.addFilesForUpdate(collection, false);
    }
    
    public void removeFilesFromCache(@NotNull final Iterable<VirtualFile> iterable) {
        try {
            if (iterable == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "files", "com/jetbrains/cidr/lang/symbols/symtable/FileSymbolTablesCache", "removeFilesFromCache"));
            }
        }
        catch (CompoundRuntimeException ex) {
            throw b((Exception)ex);
        }
        final Iterator<VirtualFile> iterator = iterable.iterator();
        while (iterator.hasNext()) {
            this.b(iterator.next());
        }
    }
    
    public void removeAllChildrenFromCache(@Nullable final VirtualFile virtualFile) {
        try {
            if (virtualFile == null) {
                return;
            }
        }
        catch (CompoundRuntimeException ex) {
            throw b((Exception)ex);
        }
        for (final VirtualFile virtualFile2 : this.myCache.keySet()) {
            Label_0063: {
                try {
                    if (virtualFile2 == null) {
                        continue;
                    }
                    final VirtualFile virtualFile3 = virtualFile;
                    final VirtualFile virtualFile4 = virtualFile2;
                    final boolean b = false;
                    final boolean b2 = VfsUtilCore.isAncestor(virtualFile3, virtualFile4, b);
                    if (b2) {
                        break Label_0063;
                    }
                    continue;
                }
                catch (CompoundRuntimeException ex2) {
                    throw b((Exception)ex2);
                }
                try {
                    final VirtualFile virtualFile3 = virtualFile;
                    final VirtualFile virtualFile4 = virtualFile2;
                    final boolean b = false;
                    final boolean b2 = VfsUtilCore.isAncestor(virtualFile3, virtualFile4, b);
                    if (!b2) {
                        continue;
                    }
                    this.b(virtualFile2);
                }
                catch (CompoundRuntimeException ex3) {
                    throw b((Exception)ex3);
                }
            }
        }
    }
    
    @Nullable
    @Contract("null -> null")
    private static VirtualFile b(@Nullable final PsiFile psiFile) {
        return OCInclusionContextUtil.getVirtualFile(psiFile);
    }
    
    public FileSymbolTable calcTableUsingPSI(@NotNull final PsiFile psiFile, @NotNull final VirtualFile virtualFile, @NotNull final OCInclusionContext ocInclusionContext) {
        try {
            if (psiFile == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "file", "com/jetbrains/cidr/lang/symbols/symtable/FileSymbolTablesCache", "calcTableUsingPSI"));
            }
        }
        catch (CompoundRuntimeException ex) {
            throw b((Exception)ex);
        }
        try {
            if (virtualFile == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "virtualFile", "com/jetbrains/cidr/lang/symbols/symtable/FileSymbolTablesCache", "calcTableUsingPSI"));
            }
        }
        catch (CompoundRuntimeException ex2) {
            throw b((Exception)ex2);
        }
        try {
            if (ocInclusionContext == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "context", "com/jetbrains/cidr/lang/symbols/symtable/FileSymbolTablesCache", "calcTableUsingPSI"));
            }
        }
        catch (CompoundRuntimeException ex3) {
            throw b((Exception)ex3);
        }
        return SymbolTableProvider.getProvider(psiFile).calcTableUsingPSI(psiFile, virtualFile, ocInclusionContext);
    }
    
    @Nullable
    FileSymbolTable forFile(@NotNull final PsiFile psiFile, @NotNull final OCInclusionContext ocInclusionContext) {
        try {
            if (psiFile == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "file", "com/jetbrains/cidr/lang/symbols/symtable/FileSymbolTablesCache", "forFile"));
            }
        }
        catch (CompoundRuntimeException ex) {
            throw b((Exception)ex);
        }
        try {
            if (ocInclusionContext == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "context", "com/jetbrains/cidr/lang/symbols/symtable/FileSymbolTablesCache", "forFile"));
            }
        }
        catch (CompoundRuntimeException ex2) {
            throw b((Exception)ex2);
        }
        try {
            ProgressManager.checkCanceled();
            if (!ApplicationManager.getApplication().isReadAccessAllowed()) {
                FileSymbolTablesCache.LOG.error("deadlock may occur if 'FileSymbolTablesCache.forFile' is called outside of read-action");
            }
        }
        catch (CompoundRuntimeException ex3) {
            throw b((Exception)ex3);
        }
        try {
            if (!this.shouldBuildTables()) {
                return null;
            }
        }
        catch (CompoundRuntimeException ex4) {
            throw b((Exception)ex4);
        }
        final VirtualFile b = b(psiFile);
        Label_0153: {
            try {
                if (b == null) {
                    break Label_0153;
                }
                final VirtualFile virtualFile = b;
                final boolean b2 = virtualFile.isValid();
                if (!b2) {
                    break Label_0153;
                }
                break Label_0153;
            }
            catch (CompoundRuntimeException ex5) {
                throw b((Exception)ex5);
            }
            try {
                final VirtualFile virtualFile = b;
                final boolean b2 = virtualFile.isValid();
                if (!b2) {
                    return null;
                }
            }
            catch (CompoundRuntimeException ex6) {
                throw b((Exception)ex6);
            }
        }
        final FileSymbolTablesPack d = this.d(b);
        final Ref ref = new Ref();
        final FileSymbolTable conformingTable = d.findConformingTable(ocInclusionContext, 0, (Ref<Integer>)ref);
        try {
            if (conformingTable != null) {
                return conformingTable;
            }
        }
        catch (CompoundRuntimeException ex7) {
            throw b((Exception)ex7);
        }
        final int intValue = (int)ref.get();
        try {
            if (ocInclusionContext.getInclusionLevel() >= OCInclusionContext.getMaxInclusionLevel(this.myProject)) {
                return null;
            }
        }
        catch (CompoundRuntimeException ex8) {
            throw b((Exception)ex8);
        }
        final Thread currentThread = Thread.currentThread();
        final boolean a;
        synchronized (this.myFileAndContextDependencyLock) {
            this.myContextToAwaitingFile.put(currentThread, b);
            a = this.a(currentThread);
            try {
                if (a) {
                    this.myContextToAwaitingFile.remove(currentThread);
                }
            }
            catch (CompoundRuntimeException ex9) {
                throw b((Exception)ex9);
            }
        }
        try {
            if (a) {
                return SymbolTableProvider.getProvider(psiFile).calcTable(psiFile, b, ocInclusionContext);
            }
        }
        catch (CompoundRuntimeException ex10) {
            throw b((Exception)ex10);
        }
        synchronized (d) {
            final boolean b5;
            synchronized (this.myFileAndContextDependencyLock) {
                this.myContextToAwaitingFile.remove(currentThread);
                final Thread thread = this.myFileToProcessingContext.get(b);
                boolean b4 = false;
                Label_0439: {
                    Label_0397: {
                        Logger log = null;
                        Label_0388: {
                            try {
                                log = FileSymbolTablesCache.LOG;
                                if (thread == null) {
                                    break Label_0388;
                                }
                                final Thread thread2 = thread;
                                final Thread thread3 = currentThread;
                                if (thread2 == thread3) {
                                    break Label_0388;
                                }
                                break Label_0388;
                            }
                            catch (CompoundRuntimeException ex11) {
                                throw b((Exception)ex11);
                            }
                            try {
                                final Thread thread2 = thread;
                                final Thread thread3 = currentThread;
                                if (thread2 == thread3) {
                                    final boolean b3 = true;
                                    break Label_0397;
                                }
                            }
                            catch (CompoundRuntimeException ex12) {
                                throw b((Exception)ex12);
                            }
                        }
                        final boolean b3 = false;
                        try {
                            log.assertTrue(b3, (Object)("File is processed by another thread, deadlock detection failed! " + psiFile.getName()));
                            if (thread != null) {
                                b4 = true;
                                break Label_0439;
                            }
                        }
                        catch (CompoundRuntimeException ex13) {
                            throw b((Exception)ex13);
                        }
                    }
                    b4 = false;
                }
                b5 = b4;
                try {
                    if (!b5) {
                        this.myFileToProcessingContext.put(b, currentThread);
                    }
                }
                catch (CompoundRuntimeException ex14) {
                    throw b((Exception)ex14);
                }
            }
            try {
                ProgressManager.checkCanceled();
                final FileSymbolTable conformingTable2 = d.findConformingTable(ocInclusionContext, intValue, null);
                if (conformingTable2 != null) {
                    return conformingTable2;
                }
                OCSymbolTablesBuildingActivity.getInstance(this.myProject).assertParsingAndSymbolBuildingAllowed(currentThread);
                final FileSymbolTable calcTable = SymbolTableProvider.getProvider(psiFile).calcTable(psiFile, b, ocInclusionContext);
                ocInclusionContext.addProcessedFile(b);
                d.addCompactSynchronized(calcTable);
                return calcTable;
            }
            finally {
                if (!b5) {
                    synchronized (this.myFileAndContextDependencyLock) {
                        this.myFileToProcessingContext.remove(b);
                    }
                }
            }
        }
    }
    
    @Nullable
    public FileSymbolTable findForFile(@NotNull final VirtualFile virtualFile, @NotNull final OCInclusionContext ocInclusionContext) {
        try {
            if (virtualFile == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "file", "com/jetbrains/cidr/lang/symbols/symtable/FileSymbolTablesCache", "findForFile"));
            }
        }
        catch (CompoundRuntimeException ex) {
            throw b((Exception)ex);
        }
        try {
            if (ocInclusionContext == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "context", "com/jetbrains/cidr/lang/symbols/symtable/FileSymbolTablesCache", "findForFile"));
            }
        }
        catch (CompoundRuntimeException ex2) {
            throw b((Exception)ex2);
        }
        return this.d(virtualFile).findConformingTable(ocInclusionContext, 0, null);
    }
    
    private boolean a(@NotNull final Thread thread) {
        try {
            if (thread == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "context", "com/jetbrains/cidr/lang/symbols/symtable/FileSymbolTablesCache", "checkForDeadlock"));
            }
        }
        catch (CompoundRuntimeException ex) {
            throw b((Exception)ex);
        }
        Thread thread3;
        for (Thread thread2 = thread; thread2 != null; thread2 = thread3) {
            final VirtualFile virtualFile = this.myContextToAwaitingFile.get(thread2);
            try {
                if (virtualFile == null) {
                    return false;
                }
            }
            catch (CompoundRuntimeException ex2) {
                throw b((Exception)ex2);
            }
            thread3 = this.myFileToProcessingContext.get(virtualFile);
            try {
                if (thread3 == thread2) {
                    return false;
                }
            }
            catch (CompoundRuntimeException ex3) {
                throw b((Exception)ex3);
            }
            try {
                if (thread3 == thread) {
                    return true;
                }
            }
            catch (CompoundRuntimeException ex4) {
                throw b((Exception)ex4);
            }
        }
        return false;
    }
    
    public void scheduleReparseFile(final OCFile ocFile) {
        synchronized (this.myPendingReparses) {
            final VirtualFile b = b((PsiFile)ocFile);
            try {
                if (b != null) {
                    this.myPendingReparses.add(b);
                }
            }
            catch (CompoundRuntimeException ex) {
                throw b((Exception)ex);
            }
        }
        this.d();
    }
    
    private void e() {
        synchronized (this.myPendingReparses) {
            final Iterator<PsiFile> iterator = PsiManagerEx.getInstanceEx(this.myProject).getFileManager().getAllCachedFiles().iterator();
            while (iterator.hasNext()) {
                final VirtualFile b = b(iterator.next());
                try {
                    if (b == null) {
                        continue;
                    }
                    this.myPendingReparses.add(b);
                }
                catch (CompoundRuntimeException ex) {
                    throw b((Exception)ex);
                }
            }
        }
    }
    
    private void c() {
        this.e();
        this.d();
    }
    
    private void d() {
        synchronized (this.myPendingReparses) {
            Label_0031: {
                if (!this.myPendingPSIResets.isEmpty()) {
                    break Label_0031;
                }
                try {
                    if (!this.myPendingReparses.isEmpty()) {
                        ApplicationManager.getApplication().invokeLater(() -> this.updateDirtyFilesPSI(), ModalityState.NON_MODAL);
                    }
                }
                catch (CompoundRuntimeException ex) {
                    throw b((Exception)ex);
                }
            }
        }
    }
    
    public void reparseCachedPsiFiles() {
        this.e();
        this.updateDirtyFilesPSI();
    }
    
    public void updateDirtyFilesPSI() {
        try {
            if (this.myProject.isDisposed()) {
                return;
            }
        }
        catch (CompoundRuntimeException ex) {
            throw b((Exception)ex);
        }
        final ArrayList<VirtualFile> list = new ArrayList<VirtualFile>();
        final ArrayList list2 = new ArrayList();
        final FileManager fileManager = PsiManagerEx.getInstanceEx(this.myProject).getFileManager();
        synchronized (this.myPendingReparses) {
            this.myPendingPSIResets.removeAll(this.myPendingReparses);
            list2.addAll(this.myPendingPSIResets);
            this.myPendingPSIResets.clear();
            for (final VirtualFile virtualFile : this.myPendingReparses) {
                try {
                    if (!virtualFile.isValid()) {
                        continue;
                    }
                }
                catch (CompoundRuntimeException ex2) {
                    throw b((Exception)ex2);
                }
                final FileViewProvider cachedViewProvider = fileManager.findCachedViewProvider(virtualFile);
                PsiFile file = null;
                Label_0165: {
                    try {
                        if (cachedViewProvider == null) {
                            file = null;
                            break Label_0165;
                        }
                    }
                    catch (CompoundRuntimeException ex3) {
                        throw b((Exception)ex3);
                    }
                    file = fileManager.findFile(virtualFile);
                }
                final PsiFile psiFile = file;
                try {
                    if (psiFile == null) {
                        continue;
                    }
                }
                catch (CompoundRuntimeException ex4) {
                    throw b((Exception)ex4);
                }
                a(list, psiFile, virtualFile);
            }
            this.myPendingReparses.clear();
        }
        try {
            if (!list2.isEmpty()) {
                final List<VirtualFile> list3;
                final Iterator<VirtualFile> iterator2;
                final FileManager fileManager2;
                ApplicationManager.getApplication().runWriteAction(() -> {
                    list3.iterator();
                    while (iterator2.hasNext()) {
                        fileManager2.setViewProvider(iterator2.next(), null);
                    }
                    return;
                });
            }
        }
        catch (CompoundRuntimeException ex5) {
            throw b((Exception)ex5);
        }
        try {
            if (!list.isEmpty()) {
                FileContentUtilCore.reparseFiles((Collection)list);
            }
        }
        catch (CompoundRuntimeException ex6) {
            throw b((Exception)ex6);
        }
    }
    
    private static void a(@NotNull final List<VirtualFile> list, @NotNull final PsiFile psiFile, @NotNull final VirtualFile virtualFile) {
        try {
            if (list == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "dirty", "com/jetbrains/cidr/lang/symbols/symtable/FileSymbolTablesCache", "processDirtyFile"));
            }
        }
        catch (CompoundRuntimeException ex) {
            throw b((Exception)ex);
        }
        try {
            if (psiFile == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "file", "com/jetbrains/cidr/lang/symbols/symtable/FileSymbolTablesCache", "processDirtyFile"));
            }
        }
        catch (CompoundRuntimeException ex2) {
            throw b((Exception)ex2);
        }
        try {
            if (virtualFile == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "virtualFile", "com/jetbrains/cidr/lang/symbols/symtable/FileSymbolTablesCache", "processDirtyFile"));
            }
        }
        catch (CompoundRuntimeException ex3) {
            throw b((Exception)ex3);
        }
        try {
            if (!SymbolTableProvider.isSourceFile(virtualFile) || !(psiFile instanceof PsiFileImpl)) {
                return;
            }
        }
        catch (CompoundRuntimeException ex4) {
            throw b((Exception)ex4);
        }
        final FileElement treeElement = ((PsiFileImpl)psiFile).getTreeElement();
        Label_0181: {
            try {
                if (treeElement == null) {
                    break Label_0181;
                }
                final FileASTNode fileASTNode = (FileASTNode)treeElement;
                final boolean b = fileASTNode.isParsed();
                if (b) {
                    break Label_0181;
                }
                break Label_0181;
            }
            catch (CompoundRuntimeException ex5) {
                throw b((Exception)ex5);
            }
            try {
                final FileASTNode fileASTNode = (FileASTNode)treeElement;
                final boolean b = fileASTNode.isParsed();
                if (b) {
                    list.add(virtualFile);
                    return;
                }
            }
            catch (CompoundRuntimeException ex6) {
                throw b((Exception)ex6);
            }
        }
        ((PsiFileImpl)psiFile).clearCaches();
    }
    
    private void a(final PsiFile psiFile, final int n, final int n2, final int n3) {
        final VirtualFile b = b(psiFile);
        try {
            if (b != null) {
                this.d(b).updateOffsetsSynchronized(n, n2, n3);
            }
        }
        catch (CompoundRuntimeException ex) {
            throw b((Exception)ex);
        }
    }
    
    private void a(@Nullable final PsiFile psiFile, final boolean b, final Set<VirtualFile> set, final boolean b2) {
        final VirtualFile b3 = b(psiFile);
        Label_0034: {
            try {
                if (b3 == null) {
                    return;
                }
                final Set<VirtualFile> set2 = set;
                final VirtualFile virtualFile = b3;
                final boolean b4 = set2.add(virtualFile);
                if (!b4) {
                    return;
                }
                break Label_0034;
            }
            catch (CompoundRuntimeException ex) {
                throw b((Exception)ex);
            }
            try {
                final Set<VirtualFile> set2 = set;
                final VirtualFile virtualFile = b3;
                final boolean b4 = set2.add(virtualFile);
                if (!b4) {
                    return;
                }
            }
            catch (CompoundRuntimeException ex2) {
                throw b((Exception)ex2);
            }
            try {
                this.b(b3);
                if (psiFile.isPhysical()) {
                    this.myTableUpdater.addFileForUpdate(b3);
                }
            }
            catch (CompoundRuntimeException ex3) {
                throw b((Exception)ex3);
            }
        }
        Set<VirtualFile> set3 = null;
        Label_0096: {
            Label_0081: {
                try {
                    if (!b2) {
                        return;
                    }
                    final boolean b5 = b;
                    if (b5) {
                        break Label_0081;
                    }
                    break Label_0081;
                }
                catch (CompoundRuntimeException ex4) {
                    throw b((Exception)ex4);
                }
                try {
                    final boolean b5 = b;
                    if (b5) {
                        set3 = this.myPendingReparses;
                        break Label_0096;
                    }
                }
                catch (CompoundRuntimeException ex5) {
                    throw b((Exception)ex5);
                }
            }
            set3 = this.myPendingPSIResets;
        }
        final Set<VirtualFile> set4 = set3;
        final OCWorkspace workspace = OCWorkspaceManager.getWorkspace(this.myProject);
        if (!OCInclusionContextUtil.isNeedToFindRoot(psiFile)) {
            final Iterator<? extends OCResolveConfiguration> iterator = workspace.getConfigurations().iterator();
            while (iterator.hasNext()) {
                OCImportGraph.invalidateRootHeadersCache((OCResolveConfiguration)iterator.next(), b3);
            }
        }
        Label_0231: {
            if (!OCInclusionContext.getBuildConfigurationByPchFile(b3, this.myProject).isEmpty()) {
                final Collection<OCResolveConfiguration> collection;
                final Iterator<OCResolveConfiguration> iterator2;
                final Collection<VirtualFile> collection2;
                final Set set5;
                final Runnable runnable = () -> {
                    try {
                        if (this.myProject.isDisposed()) {
                            return;
                        }
                    }
                    catch (CompoundRuntimeException ex6) {
                        throw b((Exception)ex6);
                    }
                    collection.iterator();
                    while (iterator2.hasNext()) {
                        OCInclusionContext.onPrecompiledContextChange(iterator2.next());
                    }
                    OCSearchScope.getExplicitlySpecifiedProjectSourceFiles(this.myProject);
                    this.myTableUpdater.addFilesForUpdate(collection2, 1 != 0);
                    synchronized (this.myPendingReparses) {
                        set5.addAll(collection2);
                    }
                    return;
                };
                try {
                    if (ApplicationManager.getApplication().isUnitTestMode()) {
                        runnable.run();
                        break Label_0231;
                    }
                }
                catch (CompoundRuntimeException ex7) {
                    throw b((Exception)ex7);
                }
                ApplicationManager.getApplication().invokeLater(runnable, ModalityState.NON_MODAL);
            }
        }
        if (psiFile instanceof OCFile) {
            for (final OCFile ocFile : ((OCFile)psiFile).resetIncludingFiles()) {
                this.a((PsiFile)ocFile, b, set, true);
                synchronized (this.myPendingReparses) {
                    set4.add(b((PsiFile)ocFile));
                }
            }
        }
    }
    
    public void ensurePendingFilesProcessed() {
        this.ensurePendingFilesProcessed(null, false);
    }
    
    public void ensurePendingFilesProcessed(@Nullable final ProgressIndicator progressIndicator, final boolean b) {
        this.myTableUpdater.ensurePendingFilesProcessed(progressIndicator, b);
    }
    
    @NotNull
    public List<FileSymbolTable> allTablesForFile(final OCFile ocFile) {
        final VirtualFile b = b((PsiFile)ocFile);
        ArrayList<FileSymbolTable> tablesSynchronized = null;
        Label_0058: {
            List<FileSymbolTable> list = null;
            Label_0023: {
                try {
                    if (b != null) {
                        break Label_0058;
                    }
                    list = Collections.emptyList();
                    if (list == null) {
                        break Label_0023;
                    }
                    return list;
                }
                catch (CompoundRuntimeException ex) {
                    throw b((Exception)ex);
                }
                try {
                    list = Collections.emptyList();
                    if (list == null) {
                        throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/symbols/symtable/FileSymbolTablesCache", "allTablesForFile"));
                    }
                }
                catch (CompoundRuntimeException ex2) {
                    throw b((Exception)ex2);
                }
            }
            return list;
            try {
                tablesSynchronized = this.d(b).getTablesSynchronized();
                if (tablesSynchronized == null) {
                    throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/symbols/symtable/FileSymbolTablesCache", "allTablesForFile"));
                }
            }
            catch (CompoundRuntimeException ex3) {
                throw b((Exception)ex3);
            }
        }
        return tablesSynchronized;
    }
    
    @NotNull
    public List<FileSymbolTable> allTablesForFile(@NotNull final VirtualFile virtualFile) {
        try {
            if (virtualFile == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "virtualFile", "com/jetbrains/cidr/lang/symbols/symtable/FileSymbolTablesCache", "allTablesForFile"));
            }
        }
        catch (CompoundRuntimeException ex) {
            throw b((Exception)ex);
        }
        ArrayList<FileSymbolTable> tablesSynchronized;
        try {
            tablesSynchronized = this.d(virtualFile).getTablesSynchronized();
            if (tablesSynchronized == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/symbols/symtable/FileSymbolTablesCache", "allTablesForFile"));
            }
        }
        catch (CompoundRuntimeException ex2) {
            throw b((Exception)ex2);
        }
        return tablesSynchronized;
    }
    
    public int allTablesForFileCount(@NotNull final VirtualFile virtualFile) {
        try {
            if (virtualFile == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "virtualFile", "com/jetbrains/cidr/lang/symbols/symtable/FileSymbolTablesCache", "allTablesForFileCount"));
            }
        }
        catch (CompoundRuntimeException ex) {
            throw b((Exception)ex);
        }
        return this.d(virtualFile).getTablesCountSynchronized();
    }
    
    @NotNull
    private FileSymbolTablesPack d(@NotNull final VirtualFile virtualFile) {
        try {
            if (virtualFile == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "virtualFile", "com/jetbrains/cidr/lang/symbols/symtable/FileSymbolTablesCache", "packForFile"));
            }
        }
        catch (CompoundRuntimeException ex) {
            throw b((Exception)ex);
        }
        final FileSymbolTablesPack fileSymbolTablesPack = this.myCache.get(virtualFile);
        Label_0109: {
            FileSymbolTablesPack fileSymbolTablesPack2 = null;
            Label_0074: {
                try {
                    if (fileSymbolTablesPack == null) {
                        break Label_0109;
                    }
                    fileSymbolTablesPack2 = fileSymbolTablesPack;
                    if (fileSymbolTablesPack2 == null) {
                        break Label_0074;
                    }
                    return fileSymbolTablesPack2;
                }
                catch (CompoundRuntimeException ex2) {
                    throw b((Exception)ex2);
                }
                try {
                    fileSymbolTablesPack2 = fileSymbolTablesPack;
                    if (fileSymbolTablesPack2 == null) {
                        throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/symbols/symtable/FileSymbolTablesCache", "packForFile"));
                    }
                }
                catch (CompoundRuntimeException ex3) {
                    throw b((Exception)ex3);
                }
            }
            return fileSymbolTablesPack2;
        }
        final FileSymbolTablesPack fileSymbolTablesPack3 = new FileSymbolTablesPack();
        final FileSymbolTablesPack fileSymbolTablesPack4 = this.myCache.putIfAbsent(virtualFile, fileSymbolTablesPack3);
        FileSymbolTablesPack fileSymbolTablesPack5 = null;
        Label_0148: {
            try {
                if (fileSymbolTablesPack4 == null) {
                    final FileSymbolTablesPack fileSymbolTablesPack6;
                    fileSymbolTablesPack5 = (fileSymbolTablesPack6 = fileSymbolTablesPack3);
                    break Label_0148;
                }
            }
            catch (CompoundRuntimeException ex4) {
                throw b((Exception)ex4);
            }
            FileSymbolTablesPack fileSymbolTablesPack6;
            fileSymbolTablesPack5 = (fileSymbolTablesPack6 = fileSymbolTablesPack4);
            try {
                if (fileSymbolTablesPack6 == null) {
                    throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/symbols/symtable/FileSymbolTablesCache", "packForFile"));
                }
            }
            catch (CompoundRuntimeException ex5) {
                throw b((Exception)ex5);
            }
        }
        return fileSymbolTablesPack5;
    }
    
    public List<FileSymbolTable> allTables() {
        final ArrayList<FileSymbolTable> list = new ArrayList<FileSymbolTable>();
        final Iterator<VirtualFile> iterator = this.myCache.keySet().iterator();
        while (iterator.hasNext()) {
            list.addAll(this.allTablesForFile(iterator.next()));
        }
        return list;
    }
    
    public void clearAllTables() {
        final ArrayList<FileSymbolTablesPack> list = new ArrayList<FileSymbolTablesPack>(this.myCache.values());
        this.myCache.clear();
        final Iterator<FileSymbolTablesPack> iterator = list.iterator();
        while (iterator.hasNext()) {
            iterator.next().onRemove();
        }
    }
    
    private void b(@NotNull final VirtualFile virtualFile) {
        try {
            if (virtualFile == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "each", "com/jetbrains/cidr/lang/symbols/symtable/FileSymbolTablesCache", "clearCache"));
            }
        }
        catch (CompoundRuntimeException ex) {
            throw b((Exception)ex);
        }
        final FileSymbolTablesPack fileSymbolTablesPack = this.myCache.remove(virtualFile);
        try {
            if (fileSymbolTablesPack != null) {
                fileSymbolTablesPack.onRemove();
            }
        }
        catch (CompoundRuntimeException ex2) {
            throw b((Exception)ex2);
        }
    }
    
    @Nullable
    private static FileSymbolTablesPack a(final FileSymbolTableSerializer fileSymbolTableSerializer, final String s, final VirtualFile virtualFile) throws IOException {
        try {
            if (FileSymbolTablesCache.ourDoNotReloadExistingSymbolsInTests == Boolean.TRUE) {
                return null;
            }
        }
        catch (IOException ex) {
            throw b(ex);
        }
        final MetaData a = a(fileSymbolTableSerializer, virtualFile);
        boolean b = false;
        final Iterator<String> iterator = a.projectKeysWithPaths.keySet().iterator();
        while (iterator.hasNext()) {
            final String s2 = iterator.next();
            try {
                if (a.isFileUpToDate) {
                    if (a.a(s2)) {
                        continue;
                    }
                }
            }
            catch (IOException ex2) {
                throw b(ex2);
            }
            iterator.remove();
            c(s2).writeAttribute(virtualFile).close();
            b = true;
        }
        try {
            if (b) {
                a(fileSymbolTableSerializer, virtualFile, a);
            }
        }
        catch (IOException ex3) {
            throw b(ex3);
        }
        final DataInputStream attribute = b(s).readAttribute(virtualFile);
        Label_0170: {
            try {
                if (attribute == null) {
                    break Label_0170;
                }
                final DataInputStream dataInputStream = attribute;
                final int n = dataInputStream.available();
                if (n == 0) {
                    break Label_0170;
                }
                break Label_0170;
            }
            catch (IOException ex4) {
                throw b(ex4);
            }
            try {
                final DataInputStream dataInputStream = attribute;
                final int n = dataInputStream.available();
                if (n == 0) {
                    return null;
                }
            }
            catch (IOException ex5) {
                throw b(ex5);
            }
        }
        final long long1 = attribute.readLong();
        try {
            if (long1 == virtualFile.getTimeStamp()) {
                return fileSymbolTableSerializer.readSymbolTables(attribute, virtualFile);
            }
        }
        catch (IOException ex6) {
            throw b(ex6);
        }
        return null;
    }
    
    void serializeTables(@NotNull final String s, @NotNull final Set<VirtualFile> set, @NotNull final ProgressIndicator progressIndicator) {
        try {
            if (s == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "projectLocationHash", "com/jetbrains/cidr/lang/symbols/symtable/FileSymbolTablesCache", "serializeTables"));
            }
        }
        catch (CompoundRuntimeException ex) {
            throw b((Exception)ex);
        }
        try {
            if (set == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "tables", "com/jetbrains/cidr/lang/symbols/symtable/FileSymbolTablesCache", "serializeTables"));
            }
        }
        catch (CompoundRuntimeException ex2) {
            throw b((Exception)ex2);
        }
        try {
            if (progressIndicator == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "indicator", "com/jetbrains/cidr/lang/symbols/symtable/FileSymbolTablesCache", "serializeTables"));
            }
        }
        catch (CompoundRuntimeException ex3) {
            throw b((Exception)ex3);
        }
        this.a(progressIndicator, (Consumer<FileSymbolTableSerializer>)(fileSymbolTableSerializer -> {
            try {
                if (progressIndicator == null) {
                    throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "indicator", "com/jetbrains/cidr/lang/symbols/symtable/FileSymbolTablesCache", "lambda$serializeTables$7"));
                }
            }
            catch (CompoundRuntimeException ex) {
                throw b((Exception)ex);
            }
            boolean booleanValue = true;
            VirtualFile virtualFile;
            while (booleanValue && (virtualFile = concurrentLinkedQueue.poll()) != null) {
                progressIndicator.checkCanceled();
                booleanValue = (boolean)ReadAction.compute(() -> {
                    try {
                        if (progressIndicator == null) {
                            throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "indicator", "com/jetbrains/cidr/lang/symbols/symtable/FileSymbolTablesCache", "lambda$null$6"));
                        }
                    }
                    catch (IOException ex) {
                        throw b(ex);
                    }
                    progressIndicator.checkCanceled();
                    if (a(virtualFile)) {
                        try {
                            final MetaData a = a(fileSymbolTableSerializer, virtualFile);
                            a.projectKeysWithPaths.put(s, s2);
                            a(fileSymbolTableSerializer, virtualFile, a);
                            final DataOutputStream writeAttribute = fileAttribute.writeAttribute(virtualFile);
                            writeAttribute.writeLong(virtualFile.getTimeStamp());
                            final FileSymbolTablesPack d = this.d(virtualFile);
                            synchronized (d.getTablesAccessLock()) {
                                fileSymbolTableSerializer.writeSymbolTables(writeAttribute, d);
                            }
                            writeAttribute.close();
                        }
                        catch (IOException ex2) {
                            FileSymbolTablesCache.LOG.error("Can't serialize file symbol table", (Throwable)ex2);
                            return false;
                        }
                    }
                    return true;
                });
                progressIndicator.setFraction(atomicInteger.incrementAndGet() / n);
            }
        }));
    }
    
    public static boolean areSymbolsLoaded(@NotNull final Project project) {
        try {
            if (project == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "project", "com/jetbrains/cidr/lang/symbols/symtable/FileSymbolTablesCache", "areSymbolsLoaded"));
            }
        }
        catch (CompoundRuntimeException ex) {
            throw b((Exception)ex);
        }
        try {
            if (FileSymbolTablesCache.ourForceSymbolsLoadedInTests != null) {
                return FileSymbolTablesCache.ourForceSymbolsLoadedInTests;
            }
        }
        catch (CompoundRuntimeException ex2) {
            throw b((Exception)ex2);
        }
        final FileSymbolTablesCache fileSymbolTablesCache = (FileSymbolTablesCache)project.getComponent((Class)FileSymbolTablesCache.class);
        try {
            if (fileSymbolTablesCache == null) {
                return true;
            }
        }
        catch (CompoundRuntimeException ex3) {
            throw b((Exception)ex3);
        }
        try {
            if (!fileSymbolTablesCache.shouldBuildTables()) {
                return true;
            }
        }
        catch (CompoundRuntimeException ex4) {
            throw b((Exception)ex4);
        }
        return fileSymbolTablesCache.mySymbolsLoaded.get();
    }
    
    public static void forceSymbolsLoadedInTests(@Nullable final Boolean ourForceSymbolsLoadedInTests) {
        FileSymbolTablesCache.ourForceSymbolsLoadedInTests = ourForceSymbolsLoadedInTests;
    }
    
    public static Boolean getForceSymbolsLoadedInTests() {
        return FileSymbolTablesCache.ourForceSymbolsLoadedInTests;
    }
    
    void notifySymbolsLoaded() {
        this.mySymbolsLoaded.getAndSet(true);
    }
    
    void notifySymbolsUnloaded() {
        this.mySymbolsLoaded.getAndSet(false);
    }
    
    public Project getProject() {
        return this.myProject;
    }
    
    public void removeUnusedTables() {
        final Iterator<FileSymbolTablesPack> iterator = this.myCache.values().iterator();
        while (iterator.hasNext()) {
            iterator.next().removeUnusedTables();
        }
    }
    
    @NotNull
    public Collection<VirtualFile> getFilesWithChangedTables() {
        final ArrayList<VirtualFile> list = new ArrayList<VirtualFile>();
        for (final Map.Entry<Object, Object> entry : this.myCache.entrySet()) {
            try {
                if (!entry.getValue().isChanged()) {
                    continue;
                }
                list.add(entry.getKey());
            }
            catch (CompoundRuntimeException ex) {
                throw b((Exception)ex);
            }
        }
        ArrayList<VirtualFile> list2;
        try {
            list2 = list;
            if (list2 == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/symbols/symtable/FileSymbolTablesCache", "getFilesWithChangedTables"));
            }
        }
        catch (CompoundRuntimeException ex2) {
            throw b((Exception)ex2);
        }
        return list2;
    }
    
    @NotNull
    public Collection<VirtualFile> getFilesWithUsedTables() {
        final ArrayList<VirtualFile> list = new ArrayList<VirtualFile>();
        for (final Map.Entry<Object, Object> entry : this.myCache.entrySet()) {
            try {
                if (!entry.getValue().hasUsedTables()) {
                    continue;
                }
                list.add(entry.getKey());
            }
            catch (CompoundRuntimeException ex) {
                throw b((Exception)ex);
            }
        }
        ArrayList<VirtualFile> list2;
        try {
            list2 = list;
            if (list2 == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/symbols/symtable/FileSymbolTablesCache", "getFilesWithUsedTables"));
            }
        }
        catch (CompoundRuntimeException ex2) {
            throw b((Exception)ex2);
        }
        return list2;
    }
    
    @NotNull
    private static MetaData a(final FileSymbolTableSerializer p0, final VirtualFile p1) throws IOException {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     0: getstatic       com/jetbrains/cidr/lang/symbols/symtable/FileSymbolTablesCache.ourFileTablesMetaAttribute:Lcom/intellij/openapi/vfs/newvfs/FileAttribute;
        //     3: aload_1        
        //     4: invokevirtual   com/intellij/openapi/vfs/newvfs/FileAttribute.readAttribute:(Lcom/intellij/openapi/vfs/VirtualFile;)Ljava/io/DataInputStream;
        //     7: astore_2       
        //     8: aload_2        
        //     9: ifnull          26
        //    12: aload_2        
        //    13: invokevirtual   java/io/DataInputStream.available:()I
        //    16: ifne            79
        //    19: goto            26
        //    22: invokestatic    com/jetbrains/cidr/lang/symbols/symtable/FileSymbolTablesCache.b:(Ljava/lang/Exception;)Ljava/lang/Exception;
        //    25: athrow         
        //    26: new             Lcom/jetbrains/cidr/lang/symbols/symtable/FileSymbolTablesCache$MetaData;
        //    29: dup            
        //    30: invokespecial   com/jetbrains/cidr/lang/symbols/symtable/FileSymbolTablesCache$MetaData.<init>:()V
        //    33: dup            
        //    34: ifnonnull       78
        //    37: goto            44
        //    40: invokestatic    com/jetbrains/cidr/lang/symbols/symtable/FileSymbolTablesCache.b:(Ljava/lang/Exception;)Ljava/lang/Exception;
        //    43: athrow         
        //    44: new             Ljava/lang/IllegalStateException;
        //    47: dup            
        //    48: ldc             "@NotNull method %s.%s must not return null"
        //    50: ldc             2
        //    52: anewarray       Ljava/lang/Object;
        //    55: dup            
        //    56: ldc             0
        //    58: ldc             "com/jetbrains/cidr/lang/symbols/symtable/FileSymbolTablesCache"
        //    60: aastore        
        //    61: dup            
        //    62: ldc             1
        //    64: ldc             "readMetaData"
        //    66: aastore        
        //    67: invokestatic    java/lang/String.format:(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
        //    70: invokespecial   java/lang/IllegalStateException.<init>:(Ljava/lang/String;)V
        //    73: athrow         
        //    74: invokestatic    com/jetbrains/cidr/lang/symbols/symtable/FileSymbolTablesCache.b:(Ljava/lang/Exception;)Ljava/lang/Exception;
        //    77: athrow         
        //    78: areturn        
        //    79: aload_2        
        //    80: invokevirtual   java/io/DataInputStream.readLong:()J
        //    83: aload_1        
        //    84: invokevirtual   com/intellij/openapi/vfs/VirtualFile.getTimeStamp:()J
        //    87: lcmp           
        //    88: ifne            99
        //    91: iconst_1       
        //    92: goto            100
        //    95: invokestatic    com/jetbrains/cidr/lang/symbols/symtable/FileSymbolTablesCache.b:(Ljava/lang/Exception;)Ljava/lang/Exception;
        //    98: athrow         
        //    99: iconst_0       
        //   100: istore_3       
        //   101: aload_0        
        //   102: aload_2        
        //   103: invokevirtual   com/jetbrains/cidr/lang/symbols/symtable/FileSymbolTableSerializer.readMetaData:(Ljava/io/DataInputStream;)Lcom/jetbrains/cidr/lang/symbols/symtable/FileSymbolTablesCache$MetaData;
        //   106: astore          4
        //   108: aload           4
        //   110: ifnonnull       166
        //   113: new             Lcom/jetbrains/cidr/lang/symbols/symtable/FileSymbolTablesCache$MetaData;
        //   116: dup            
        //   117: invokespecial   com/jetbrains/cidr/lang/symbols/symtable/FileSymbolTablesCache$MetaData.<init>:()V
        //   120: dup            
        //   121: ifnonnull       165
        //   124: goto            131
        //   127: invokestatic    com/jetbrains/cidr/lang/symbols/symtable/FileSymbolTablesCache.b:(Ljava/lang/Exception;)Ljava/lang/Exception;
        //   130: athrow         
        //   131: new             Ljava/lang/IllegalStateException;
        //   134: dup            
        //   135: ldc             "@NotNull method %s.%s must not return null"
        //   137: ldc             2
        //   139: anewarray       Ljava/lang/Object;
        //   142: dup            
        //   143: ldc             0
        //   145: ldc             "com/jetbrains/cidr/lang/symbols/symtable/FileSymbolTablesCache"
        //   147: aastore        
        //   148: dup            
        //   149: ldc             1
        //   151: ldc             "readMetaData"
        //   153: aastore        
        //   154: invokestatic    java/lang/String.format:(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
        //   157: invokespecial   java/lang/IllegalStateException.<init>:(Ljava/lang/String;)V
        //   160: athrow         
        //   161: invokestatic    com/jetbrains/cidr/lang/symbols/symtable/FileSymbolTablesCache.b:(Ljava/lang/Exception;)Ljava/lang/Exception;
        //   164: athrow         
        //   165: areturn        
        //   166: aload           4
        //   168: iload_3        
        //   169: invokestatic    com/jetbrains/cidr/lang/symbols/symtable/FileSymbolTablesCache$MetaData.access$802:(Lcom/jetbrains/cidr/lang/symbols/symtable/FileSymbolTablesCache$MetaData;Z)Z
        //   172: pop            
        //   173: aload           4
        //   175: dup            
        //   176: ifnonnull       213
        //   179: new             Ljava/lang/IllegalStateException;
        //   182: dup            
        //   183: ldc             "@NotNull method %s.%s must not return null"
        //   185: ldc             2
        //   187: anewarray       Ljava/lang/Object;
        //   190: dup            
        //   191: ldc             0
        //   193: ldc             "com/jetbrains/cidr/lang/symbols/symtable/FileSymbolTablesCache"
        //   195: aastore        
        //   196: dup            
        //   197: ldc             1
        //   199: ldc             "readMetaData"
        //   201: aastore        
        //   202: invokestatic    java/lang/String.format:(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
        //   205: invokespecial   java/lang/IllegalStateException.<init>:(Ljava/lang/String;)V
        //   208: athrow         
        //   209: invokestatic    com/jetbrains/cidr/lang/symbols/symtable/FileSymbolTablesCache.b:(Ljava/lang/Exception;)Ljava/lang/Exception;
        //   212: athrow         
        //   213: areturn        
        //    Exceptions:
        //  throws java.io.IOException
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                 
        //  -----  -----  -----  -----  ---------------------
        //  8      19     22     26     Ljava/io/IOException;
        //  12     37     40     44     Ljava/io/IOException;
        //  26     74     74     78     Ljava/io/IOException;
        //  79     95     95     99     Ljava/io/IOException;
        //  108    124    127    131    Ljava/io/IOException;
        //  113    161    161    165    Ljava/io/IOException;
        //  166    209    209    213    Ljava/io/IOException;
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
    
    private static void a(final FileSymbolTableSerializer fileSymbolTableSerializer, final VirtualFile virtualFile, final MetaData metaData) throws IOException {
        final DataOutputStream writeAttribute = FileSymbolTablesCache.ourFileTablesMetaAttribute.writeAttribute(virtualFile);
        writeAttribute.writeLong(virtualFile.getTimeStamp());
        fileSymbolTableSerializer.writeMetaData(writeAttribute, metaData);
        writeAttribute.close();
    }
    
    @NotNull
    private static String a(final String s) {
        final StringBuilder alloc = StringBuilderSpinAllocator.alloc();
        try {
            alloc.append("objc_file_symbol_tables_attribute");
            alloc.append(":");
            alloc.append(s);
            final String string = alloc.toString();
            String s2;
            try {
                StringBuilderSpinAllocator.dispose(alloc);
                s2 = string;
                if (s2 == null) {
                    throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/symbols/symtable/FileSymbolTablesCache", "getKeyForSerialization"));
                }
            }
            catch (CompoundRuntimeException ex) {
                throw b((Exception)ex);
            }
            return s2;
        }
        finally {
            StringBuilderSpinAllocator.dispose(alloc);
        }
    }
    
    @NotNull
    private static FileAttribute b(final String s) {
        FileAttribute c;
        try {
            c = c(a(s));
            if (c == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/symbols/symtable/FileSymbolTablesCache", "getFileCacheAttribute"));
            }
        }
        catch (CompoundRuntimeException ex) {
            throw b((Exception)ex);
        }
        return c;
    }
    
    @NotNull
    private static FileAttribute c(@NotNull final String s) {
        try {
            if (s == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "key", "com/jetbrains/cidr/lang/symbols/symtable/FileSymbolTablesCache", "getFileCacheAttributeForProjectKey"));
            }
        }
        catch (CompoundRuntimeException ex) {
            throw b((Exception)ex);
        }
        FileAttribute fileAttribute;
        synchronized (FileSymbolTablesCache.ourFileCacheAttributes) {
            fileAttribute = FileSymbolTablesCache.ourFileCacheAttributes.get(s);
            if (fileAttribute == null) {
                fileAttribute = new FileAttribute(s, b(), false);
                FileSymbolTablesCache.ourFileCacheAttributes.put(s, fileAttribute);
            }
        }
        FileAttribute fileAttribute2;
        try {
            fileAttribute2 = fileAttribute;
            if (fileAttribute2 == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/symbols/symtable/FileSymbolTablesCache", "getFileCacheAttributeForProjectKey"));
            }
        }
        catch (CompoundRuntimeException ex2) {
            throw b((Exception)ex2);
        }
        return fileAttribute2;
    }
    
    long deserializeTables(@NotNull final String s, @NotNull final Collection<VirtualFile> collection, @NotNull final ProgressIndicator progressIndicator, final double n) {
        try {
            if (s == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "projectLocationHash", "com/jetbrains/cidr/lang/symbols/symtable/FileSymbolTablesCache", "deserializeTables"));
            }
        }
        catch (CompoundRuntimeException ex) {
            throw b((Exception)ex);
        }
        try {
            if (collection == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "filesToLoad", "com/jetbrains/cidr/lang/symbols/symtable/FileSymbolTablesCache", "deserializeTables"));
            }
        }
        catch (CompoundRuntimeException ex2) {
            throw b((Exception)ex2);
        }
        try {
            if (progressIndicator == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "indicator", "com/jetbrains/cidr/lang/symbols/symtable/FileSymbolTablesCache", "deserializeTables"));
            }
        }
        catch (CompoundRuntimeException ex3) {
            throw b((Exception)ex3);
        }
        final THashSet set = new THashSet();
        final Set concurrentSet = ContainerUtil.newConcurrentSet();
        concurrentSet.addAll(collection);
        final ArrayDeque<Object> arrayDeque = new ArrayDeque<Object>(collection);
        final Set concurrentSet2 = ContainerUtil.newConcurrentSet();
        final MultiMap multiMap = new MultiMap();
        final long n2 = concurrentSet.size();
        this.a(progressIndicator, (Consumer<FileSymbolTableSerializer>)(fileSymbolTableSerializer -> {
            try {
                if (progressIndicator == null) {
                    throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "indicator", "com/jetbrains/cidr/lang/symbols/symtable/FileSymbolTablesCache", "lambda$deserializeTables$9"));
                }
            }
            catch (IOException ex) {
                throw b(ex);
            }
            Label_0089: {
                try {
                    if (s == null) {
                        throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "projectLocationHash", "com/jetbrains/cidr/lang/symbols/symtable/FileSymbolTablesCache", "lambda$deserializeTables$9"));
                    }
                    break Label_0089;
                }
                catch (IOException ex2) {
                    throw b(ex2);
                }
                try {
                    while (true) {
                        progressIndicator.checkCanceled();
                        final VirtualFile virtualFile;
                        synchronized (arrayDeque) {
                            if (arrayDeque.isEmpty()) {
                                return;
                            }
                            virtualFile = arrayDeque.pop();
                            if (!((Set<VirtualFile>)set).add(virtualFile)) {
                                continue;
                            }
                        }
                        final FileSymbolTablesPack fileSymbolTablesPack = (FileSymbolTablesPack)ApplicationManager.getApplication().runReadAction((ThrowableComputable)new ThrowableComputable<FileSymbolTablesPack, IOException>() {
                            final /* synthetic */ ProgressIndicator val$indicator;
                            final /* synthetic */ VirtualFile val$file;
                            final /* synthetic */ FileSymbolTableSerializer val$serializer;
                            final /* synthetic */ String val$projectLocationHash;
                            final /* synthetic */ Set val$dirtySet;
                            
                            public FileSymbolTablesPack compute() throws IOException {
                                try {
                                    this.val$indicator.checkCanceled();
                                    if (!a(this.val$file)) {
                                        return null;
                                    }
                                }
                                catch (IOException ex) {
                                    throw a(ex);
                                }
                                final FileSymbolTablesPack access$1400 = a(this.val$serializer, this.val$projectLocationHash, this.val$file);
                                try {
                                    if (access$1400 == null) {
                                        this.val$dirtySet.add(this.val$file);
                                    }
                                }
                                catch (IOException ex2) {
                                    throw a(ex2);
                                }
                                return access$1400;
                            }
                            
                            private static IOException a(final IOException ex) {
                                return ex;
                            }
                        });
                        try {
                            if (fileSymbolTablesPack == null) {
                                continue;
                            }
                        }
                        catch (IOException ex3) {
                            throw b(ex3);
                        }
                        try {
                            concurrentSet.remove(virtualFile);
                            progressIndicator.setFraction(n * (n2 - concurrentSet.size()) / n2);
                            if (fileSymbolTablesPack.isEmpty()) {
                                continue;
                            }
                        }
                        catch (IOException ex4) {
                            throw b(ex4);
                        }
                        final Iterator<FileSymbolTable> iterator = fileSymbolTablesPack.tablesView().iterator();
                        while (iterator.hasNext()) {
                            iterator.next().processIncludes((Processor<OCIncludeSymbol>)(ocIncludeSymbol -> {
                                try {
                                    if (progressIndicator == null) {
                                        throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "indicator", "com/jetbrains/cidr/lang/symbols/symtable/FileSymbolTablesCache", "lambda$null$8"));
                                    }
                                }
                                catch (CompoundRuntimeException ex) {
                                    throw b((Exception)ex);
                                }
                                progressIndicator.checkCanceled();
                                final VirtualFile targetFile = ocIncludeSymbol.getTargetFile();
                                if (targetFile != null) {
                                    synchronized (arrayDeque) {
                                        arrayDeque.push(targetFile);
                                        multiMap.putValue((Object)targetFile, (Object)virtualFile);
                                    }
                                }
                                return true;
                            }));
                        }
                        this.myCache.put(virtualFile, fileSymbolTablesPack);
                    }
                }
                catch (IOException ex5) {
                    FileSymbolTablesCache.LOG.error("Can't deserialize file symbol table", (Throwable)ex5);
                }
            }
        }));
        arrayDeque.addAll(concurrentSet2);
        while (!arrayDeque.isEmpty()) {
            for (final VirtualFile virtualFile : multiMap.get((Object)arrayDeque.pop())) {
                try {
                    if (!concurrentSet2.add(virtualFile)) {
                        continue;
                    }
                    arrayDeque.push(virtualFile);
                }
                catch (CompoundRuntimeException ex4) {
                    throw b((Exception)ex4);
                }
            }
        }
        final Iterator<?> iterator2 = concurrentSet2.iterator();
        while (iterator2.hasNext()) {
            this.b((VirtualFile)iterator2.next());
        }
        return n2 - concurrentSet.size();
    }
    
    private void a(final ProgressIndicator progressIndicator, final Consumer<FileSymbolTableSerializer> consumer) {
        final ArrayList<Future> list = new ArrayList<Future>();
        for (int length = this.mySerializers.length, i = 0; i < length; ++i) {
            final FileSymbolTableSerializer[] array;
            list.add(ApplicationManager.getApplication().executeOnPooledThread(() -> consumer.consume((Object)array[i])));
        }
        final SmartList list2 = new SmartList();
        for (final Future future : list) {
            try {
                future.get();
            }
            catch (InterruptedException ex3) {}
            catch (ExecutionException ex) {
                ((List<Throwable>)list2).add(ex.getCause());
            }
        }
        try {
            if (!((List)list2).isEmpty()) {
                throw new CompoundRuntimeException((List)list2);
            }
        }
        catch (InterruptedException ex2) {
            throw b(ex2);
        }
        progressIndicator.checkCanceled();
    }
    
    private static boolean a(@NotNull final VirtualFile p0) {
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
        //    18: ldc             "file"
        //    20: aastore        
        //    21: dup            
        //    22: ldc             1
        //    24: ldc             "com/jetbrains/cidr/lang/symbols/symtable/FileSymbolTablesCache"
        //    26: aastore        
        //    27: dup            
        //    28: ldc             2
        //    30: ldc             "shouldSerializeTable"
        //    32: aastore        
        //    33: invokestatic    java/lang/String.format:(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
        //    36: invokespecial   java/lang/IllegalArgumentException.<init>:(Ljava/lang/String;)V
        //    39: athrow         
        //    40: invokestatic    com/jetbrains/cidr/lang/symbols/symtable/FileSymbolTablesCache.b:(Ljava/lang/Exception;)Ljava/lang/Exception;
        //    43: athrow         
        //    44: aload_0        
        //    45: invokevirtual   com/intellij/openapi/vfs/VirtualFile.isValid:()Z
        //    48: ifeq            87
        //    51: aload_0        
        //    52: instanceof      Lcom/intellij/openapi/vfs/VirtualFileWithId;
        //    55: ifeq            87
        //    58: goto            65
        //    61: invokestatic    com/jetbrains/cidr/lang/symbols/symtable/FileSymbolTablesCache.b:(Ljava/lang/Exception;)Ljava/lang/Exception;
        //    64: athrow         
        //    65: aload_0        
        //    66: invokevirtual   com/intellij/openapi/vfs/VirtualFile.isDirectory:()Z
        //    69: ifne            87
        //    72: goto            79
        //    75: invokestatic    com/jetbrains/cidr/lang/symbols/symtable/FileSymbolTablesCache.b:(Ljava/lang/Exception;)Ljava/lang/Exception;
        //    78: athrow         
        //    79: iconst_1       
        //    80: goto            88
        //    83: invokestatic    com/jetbrains/cidr/lang/symbols/symtable/FileSymbolTablesCache.b:(Ljava/lang/Exception;)Ljava/lang/Exception;
        //    86: athrow         
        //    87: iconst_0       
        //    88: ireturn        
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                                             
        //  -----  -----  -----  -----  -------------------------------------------------
        //  0      40     40     44     Lcom/intellij/util/lang/CompoundRuntimeException;
        //  44     58     61     65     Lcom/intellij/util/lang/CompoundRuntimeException;
        //  51     72     75     79     Lcom/intellij/util/lang/CompoundRuntimeException;
        //  65     83     83     87     Lcom/intellij/util/lang/CompoundRuntimeException;
        // 
        // The error that occurred was:
        // 
        // java.lang.IllegalStateException: Expression is linked from several locations: Label_0065:
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
    
    public void handleFileChange(@NotNull final PsiFile psiFile, final boolean b) {
        try {
            if (psiFile == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "file", "com/jetbrains/cidr/lang/symbols/symtable/FileSymbolTablesCache", "handleFileChange"));
            }
        }
        catch (CompoundRuntimeException ex) {
            throw b((Exception)ex);
        }
        try {
            if (SymbolTableProvider.isSourceFile(psiFile)) {
                this.a(psiFile);
                this.a(psiFile, false, (Set<VirtualFile>)new THashSet(), b);
                this.d();
                OCInclusionContextUtil.invalidateHeaderContextsExcept(OCInclusionContextUtil.getVirtualFile(psiFile), psiFile.getProject());
            }
        }
        catch (CompoundRuntimeException ex2) {
            throw b((Exception)ex2);
        }
        try {
            if (b) {
                OCImportGraph.invalidateHeaderRootsCache(psiFile.getProject());
                OCInclusionContextUtil.invalidateHeaderRootAndActiveConfigurationForAllExcept(OCInclusionContextUtil.getVirtualFile(psiFile), psiFile.getProject());
            }
        }
        catch (CompoundRuntimeException ex3) {
            throw b((Exception)ex3);
        }
    }
    
    private void a(@NotNull final PsiFile psiFile) {
        try {
            if (psiFile == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "file", "com/jetbrains/cidr/lang/symbols/symtable/FileSymbolTablesCache", "incOutOfCodeBlockTrackers"));
            }
        }
        catch (CompoundRuntimeException ex) {
            throw b((Exception)ex);
        }
        this.myOutOfBlockModificationTracker.incModificationCount();
        SymbolTableProvider.fireOutOfCodeBlockModification(psiFile);
    }
    
    public void dumpStats() {
        final ArrayList<Object> list = new ArrayList<Object>(this.myCache.entrySet());
        final int n;
        final List<FileSymbolTable> list2;
        final List<FileSymbolTable> list3;
        final VirtualFile virtualFile;
        final VirtualFile virtualFile2;
        final int n2;
        Collections.sort(list, (entry, entry2) -> {
            Comparing.compare(entry.getValue().getTablesCountSynchronized(), entry2.getValue().getTablesCountSynchronized());
            try {
                if (n != 0) {
                    return n;
                }
            }
            catch (CompoundRuntimeException ex) {
                throw b((Exception)ex);
            }
            entry.getValue().tablesView();
            entry2.getValue().tablesView();
            try {
                if (list2.size() <= 0 || list3.size() <= 0) {
                    return Comparing.compare(list2.size(), list3.size());
                }
            }
            catch (CompoundRuntimeException ex2) {
                throw b((Exception)ex2);
            }
            list2.get(0).getContainingFile();
            list3.get(0).getContainingFile();
            Comparing.compare((Comparable)virtualFile.getName(), (Comparable)virtualFile2.getName());
            try {
                if (n2 != 0) {
                    return n2;
                }
            }
            catch (CompoundRuntimeException ex3) {
                throw b((Exception)ex3);
            }
            return Comparing.compare((Comparable)virtualFile.getPath(), (Comparable)virtualFile2.getPath());
        });
        int n3 = 0;
        int n4 = 0;
        for (final Map.Entry<VirtualFile, V> entry3 : list) {
            ++n3;
            final VirtualFile virtualFile3 = entry3.getKey();
            final ArrayList list4 = new ArrayList<Object>(((FileSymbolTablesPack)entry3.getValue()).getTablesSynchronized());
            if (list4.size() > 1) {
                ++n4;
                final Function function = fileSymbolTable -> Integer.toString(fileSymbolTable.getUsageCount());
                System.out.println("-- " + virtualFile3.getName() + " (" + list4.size() + "): " + StringUtil.join((Collection)list4, function, ", "));
                Collections.sort((List<Object>)list4, (fileSymbolTable, fileSymbolTable2) -> Comparing.compare(fileSymbolTable2.getUsageCount(), fileSymbolTable.getUsageCount()));
                System.out.println("   " + virtualFile3.getName() + " (" + list4.size() + "): " + StringUtil.join((Collection)list4, function, ", "));
            }
        }
        System.out.println("============================");
        System.out.println("multitable files: " + n4 + " / " + n3 + " (" + n4 / n3 + ")");
    }
    
    static {
        LOG = Logger.getInstance("com.jetbrains.cidr.lang.symbols.symtable.FileSymbolTablesCache");
        FileSymbolTablesCache.ourShouldBuildCachesInTests = null;
        FileSymbolTablesCache.ourDoNotReloadExistingSymbolsInTests = null;
        FileSymbolTablesCache.ourForceSymbolsLoadedInTests = null;
        ourFileCacheAttributes = new HashMap<String, FileAttribute>();
        ourFileTablesMetaAttribute = new FileAttribute("objc_file_symbol_tables_meta_attribute", b(), false);
        PARALLEL_SERIALIZERS = Runtime.getRuntime().availableProcessors();
    }
    
    private static Exception b(final Exception ex) {
        return ex;
    }
    
    static class MetaData
    {
        private Map<String, String> projectKeysWithPaths;
        private transient boolean isFileUpToDate;
        
        MetaData() {
            this.projectKeysWithPaths = new HashMap<String, String>();
        }
        
        private boolean a(@NotNull final String s) {
            try {
                if (s == null) {
                    throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "projectKey", "com/jetbrains/cidr/lang/symbols/symtable/FileSymbolTablesCache$MetaData", "checkProjectExistsForKey"));
                }
            }
            catch (IllegalArgumentException ex) {
                throw a(ex);
            }
            final String s2 = this.projectKeysWithPaths.get(s);
            try {
                if (s2 == null || !new File(s2).exists()) {
                    return false;
                }
            }
            catch (IllegalArgumentException ex2) {
                throw a(ex2);
            }
            return true;
            b = false;
            return b;
        }
        
        private static IllegalArgumentException a(final IllegalArgumentException ex) {
            return ex;
        }
    }
    
    private class OCCodeBlockModificationListener implements PsiTreeChangePreprocessor
    {
        private final Key<String> FILE_PREPROCESSOR_STAMP;
        
        private OCCodeBlockModificationListener() {
            this.FILE_PREPROCESSOR_STAMP = (Key<String>)Key.create("FILE_PREPROCESSOR_STAMP");
        }
        
        @Override
        public void treeChanged(@NotNull final PsiTreeChangeEventImpl p0) {
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
            //    18: ldc             "event"
            //    20: aastore        
            //    21: dup            
            //    22: ldc             1
            //    24: ldc             "com/jetbrains/cidr/lang/symbols/symtable/FileSymbolTablesCache$OCCodeBlockModificationListener"
            //    26: aastore        
            //    27: dup            
            //    28: ldc             2
            //    30: ldc             "treeChanged"
            //    32: aastore        
            //    33: invokestatic    java/lang/String.format:(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
            //    36: invokespecial   java/lang/IllegalArgumentException.<init>:(Ljava/lang/String;)V
            //    39: athrow         
            //    40: invokestatic    com/jetbrains/cidr/lang/symbols/symtable/FileSymbolTablesCache$OCCodeBlockModificationListener.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
            //    43: athrow         
            //    44: iconst_0       
            //    45: istore_2       
            //    46: iconst_0       
            //    47: istore_3       
            //    48: iconst_0       
            //    49: istore          4
            //    51: aload_1        
            //    52: invokevirtual   com/intellij/psi/impl/PsiTreeChangeEventImpl.getFile:()Lcom/intellij/psi/PsiFile;
            //    55: astore          5
            //    57: aload_1        
            //    58: invokevirtual   com/intellij/psi/impl/PsiTreeChangeEventImpl.getChild:()Lcom/intellij/psi/PsiElement;
            //    61: astore          6
            //    63: aload           5
            //    65: ifnonnull       89
            //    68: aload           6
            //    70: ifnull          89
            //    73: goto            80
            //    76: invokestatic    com/jetbrains/cidr/lang/symbols/symtable/FileSymbolTablesCache$OCCodeBlockModificationListener.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
            //    79: athrow         
            //    80: aload           6
            //    82: invokeinterface com/intellij/psi/PsiElement.getContainingFile:()Lcom/intellij/psi/PsiFile;
            //    87: astore          5
            //    89: aload           5
            //    91: ifnonnull       119
            //    94: aload_1        
            //    95: invokevirtual   com/intellij/psi/impl/PsiTreeChangeEventImpl.getParent:()Lcom/intellij/psi/PsiElement;
            //    98: ifnull          119
            //   101: goto            108
            //   104: invokestatic    com/jetbrains/cidr/lang/symbols/symtable/FileSymbolTablesCache$OCCodeBlockModificationListener.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
            //   107: athrow         
            //   108: aload_1        
            //   109: invokevirtual   com/intellij/psi/impl/PsiTreeChangeEventImpl.getParent:()Lcom/intellij/psi/PsiElement;
            //   112: invokeinterface com/intellij/psi/PsiElement.getContainingFile:()Lcom/intellij/psi/PsiFile;
            //   117: astore          5
            //   119: aload_1        
            //   120: invokevirtual   com/intellij/psi/impl/PsiTreeChangeEventImpl.getParent:()Lcom/intellij/psi/PsiElement;
            //   123: astore          7
            //   125: aload           5
            //   127: invokestatic    com/jetbrains/cidr/lang/symbols/symtable/SymbolTableProvider.isSourceFile:(Lcom/intellij/psi/PsiFile;)Z
            //   130: ifne            138
            //   133: return         
            //   134: invokestatic    com/jetbrains/cidr/lang/symbols/symtable/FileSymbolTablesCache$OCCodeBlockModificationListener.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
            //   137: athrow         
            //   138: getstatic       com/jetbrains/cidr/lang/symbols/symtable/FileSymbolTablesCache$5.$SwitchMap$com$intellij$psi$impl$PsiTreeChangeEventImpl$PsiEventType:[I
            //   141: aload_1        
            //   142: invokevirtual   com/intellij/psi/impl/PsiTreeChangeEventImpl.getCode:()Lcom/intellij/psi/impl/PsiTreeChangeEventImpl$PsiEventType;
            //   145: invokevirtual   com/intellij/psi/impl/PsiTreeChangeEventImpl$PsiEventType.ordinal:()I
            //   148: iaload         
            //   149: tableswitch {
            //                2: 212
            //                3: 245
            //                4: 254
            //                5: 263
            //                6: 272
            //                7: 275
            //                8: 336
            //                9: 364
            //               10: 430
            //               11: 485
            //               12: 485
            //               13: 485
            //          default: 497
            //        }
            //   212: aload_1        
            //   213: invokevirtual   com/intellij/psi/impl/PsiTreeChangeEventImpl.getParent:()Lcom/intellij/psi/PsiElement;
            //   216: instanceof      Lcom/intellij/psi/PsiFile;
            //   219: ifeq            236
            //   222: goto            229
            //   225: invokestatic    com/jetbrains/cidr/lang/symbols/symtable/FileSymbolTablesCache$OCCodeBlockModificationListener.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
            //   228: athrow         
            //   229: goto            525
            //   232: invokestatic    com/jetbrains/cidr/lang/symbols/symtable/FileSymbolTablesCache$OCCodeBlockModificationListener.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
            //   235: athrow         
            //   236: aload_0        
            //   237: aload           5
            //   239: invokespecial   com/jetbrains/cidr/lang/symbols/symtable/FileSymbolTablesCache$OCCodeBlockModificationListener.a:(Lcom/intellij/psi/PsiFile;)V
            //   242: goto            525
            //   245: aload_0        
            //   246: aload           5
            //   248: invokespecial   com/jetbrains/cidr/lang/symbols/symtable/FileSymbolTablesCache$OCCodeBlockModificationListener.a:(Lcom/intellij/psi/PsiFile;)V
            //   251: goto            525
            //   254: aload_0        
            //   255: aload           5
            //   257: invokespecial   com/jetbrains/cidr/lang/symbols/symtable/FileSymbolTablesCache$OCCodeBlockModificationListener.a:(Lcom/intellij/psi/PsiFile;)V
            //   260: goto            525
            //   263: aload_0        
            //   264: aload           5
            //   266: invokespecial   com/jetbrains/cidr/lang/symbols/symtable/FileSymbolTablesCache$OCCodeBlockModificationListener.a:(Lcom/intellij/psi/PsiFile;)V
            //   269: goto            525
            //   272: goto            525
            //   275: aload_1        
            //   276: invokevirtual   com/intellij/psi/impl/PsiTreeChangeEventImpl.getOffset:()I
            //   279: istore_2       
            //   280: getstatic       com/jetbrains/cidr/lang/symbols/symtable/FileSymbolTablesCache$OCCodeBlockModificationListener.$assertionsDisabled:Z
            //   283: ifne            310
            //   286: aload           6
            //   288: ifnonnull       310
            //   291: goto            298
            //   294: invokestatic    com/jetbrains/cidr/lang/symbols/symtable/FileSymbolTablesCache$OCCodeBlockModificationListener.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
            //   297: athrow         
            //   298: new             Ljava/lang/AssertionError;
            //   301: dup            
            //   302: invokespecial   java/lang/AssertionError.<init>:()V
            //   305: athrow         
            //   306: invokestatic    com/jetbrains/cidr/lang/symbols/symtable/FileSymbolTablesCache$OCCodeBlockModificationListener.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
            //   309: athrow         
            //   310: iload_2        
            //   311: aload           6
            //   313: invokeinterface com/intellij/psi/PsiElement.getTextLength:()I
            //   318: iadd           
            //   319: istore_3       
            //   320: iload_3        
            //   321: iload_2        
            //   322: isub           
            //   323: istore          4
            //   325: aload_0        
            //   326: aload           5
            //   328: aload           7
            //   330: invokespecial   com/jetbrains/cidr/lang/symbols/symtable/FileSymbolTablesCache$OCCodeBlockModificationListener.a:(Lcom/intellij/psi/PsiFile;Lcom/intellij/psi/PsiElement;)V
            //   333: goto            525
            //   336: aload_1        
            //   337: invokevirtual   com/intellij/psi/impl/PsiTreeChangeEventImpl.getOffset:()I
            //   340: istore_2       
            //   341: aload_1        
            //   342: invokevirtual   com/intellij/psi/impl/PsiTreeChangeEventImpl.getOffset:()I
            //   345: istore_3       
            //   346: aload_1        
            //   347: invokevirtual   com/intellij/psi/impl/PsiTreeChangeEventImpl.getOldLength:()I
            //   350: ineg           
            //   351: istore          4
            //   353: aload_0        
            //   354: aload           5
            //   356: aload           7
            //   358: invokespecial   com/jetbrains/cidr/lang/symbols/symtable/FileSymbolTablesCache$OCCodeBlockModificationListener.a:(Lcom/intellij/psi/PsiFile;Lcom/intellij/psi/PsiElement;)V
            //   361: goto            525
            //   364: aload_1        
            //   365: invokevirtual   com/intellij/psi/impl/PsiTreeChangeEventImpl.getOffset:()I
            //   368: istore_2       
            //   369: getstatic       com/jetbrains/cidr/lang/symbols/symtable/FileSymbolTablesCache$OCCodeBlockModificationListener.$assertionsDisabled:Z
            //   372: ifne            399
            //   375: aload           6
            //   377: ifnonnull       399
            //   380: goto            387
            //   383: invokestatic    com/jetbrains/cidr/lang/symbols/symtable/FileSymbolTablesCache$OCCodeBlockModificationListener.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
            //   386: athrow         
            //   387: new             Ljava/lang/AssertionError;
            //   390: dup            
            //   391: invokespecial   java/lang/AssertionError.<init>:()V
            //   394: athrow         
            //   395: invokestatic    com/jetbrains/cidr/lang/symbols/symtable/FileSymbolTablesCache$OCCodeBlockModificationListener.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
            //   398: athrow         
            //   399: iload_2        
            //   400: aload           6
            //   402: invokeinterface com/intellij/psi/PsiElement.getTextLength:()I
            //   407: iadd           
            //   408: istore_3       
            //   409: iload_3        
            //   410: iload_2        
            //   411: isub           
            //   412: aload_1        
            //   413: invokevirtual   com/intellij/psi/impl/PsiTreeChangeEventImpl.getOldLength:()I
            //   416: isub           
            //   417: istore          4
            //   419: aload_0        
            //   420: aload           5
            //   422: aload           7
            //   424: invokespecial   com/jetbrains/cidr/lang/symbols/symtable/FileSymbolTablesCache$OCCodeBlockModificationListener.a:(Lcom/intellij/psi/PsiFile;Lcom/intellij/psi/PsiElement;)V
            //   427: goto            525
            //   430: aload_1        
            //   431: invokevirtual   com/intellij/psi/impl/PsiTreeChangeEventImpl.isGenericChange:()Z
            //   434: ifne            525
            //   437: aload           6
            //   439: ifnull          474
            //   442: goto            449
            //   445: invokestatic    com/jetbrains/cidr/lang/symbols/symtable/FileSymbolTablesCache$OCCodeBlockModificationListener.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
            //   448: athrow         
            //   449: aload_1        
            //   450: invokevirtual   com/intellij/psi/impl/PsiTreeChangeEventImpl.getOffset:()I
            //   453: istore_2       
            //   454: iload_2        
            //   455: aload           6
            //   457: invokeinterface com/intellij/psi/PsiElement.getTextLength:()I
            //   462: iadd           
            //   463: istore_3       
            //   464: iload_3        
            //   465: iload_2        
            //   466: isub           
            //   467: aload_1        
            //   468: invokevirtual   com/intellij/psi/impl/PsiTreeChangeEventImpl.getOldLength:()I
            //   471: isub           
            //   472: istore          4
            //   474: aload_0        
            //   475: aload           5
            //   477: aload           7
            //   479: invokespecial   com/jetbrains/cidr/lang/symbols/symtable/FileSymbolTablesCache$OCCodeBlockModificationListener.a:(Lcom/intellij/psi/PsiFile;Lcom/intellij/psi/PsiElement;)V
            //   482: goto            525
            //   485: aload_0        
            //   486: getfield        com/jetbrains/cidr/lang/symbols/symtable/FileSymbolTablesCache$OCCodeBlockModificationListener.this$0:Lcom/jetbrains/cidr/lang/symbols/symtable/FileSymbolTablesCache;
            //   489: aload           5
            //   491: invokestatic    com/jetbrains/cidr/lang/symbols/symtable/FileSymbolTablesCache.access$1000:(Lcom/jetbrains/cidr/lang/symbols/symtable/FileSymbolTablesCache;Lcom/intellij/psi/PsiFile;)V
            //   494: goto            525
            //   497: invokestatic    com/jetbrains/cidr/lang/symbols/symtable/FileSymbolTablesCache.access$1100:()Lcom/intellij/openapi/diagnostic/Logger;
            //   500: new             Ljava/lang/StringBuilder;
            //   503: dup            
            //   504: invokespecial   java/lang/StringBuilder.<init>:()V
            //   507: ldc             "Unknown code:"
            //   509: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
            //   512: aload_1        
            //   513: invokevirtual   com/intellij/psi/impl/PsiTreeChangeEventImpl.getCode:()Lcom/intellij/psi/impl/PsiTreeChangeEventImpl$PsiEventType;
            //   516: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/Object;)Ljava/lang/StringBuilder;
            //   519: invokevirtual   java/lang/StringBuilder.toString:()Ljava/lang/String;
            //   522: invokevirtual   com/intellij/openapi/diagnostic/Logger.error:(Ljava/lang/String;)V
            //   525: iload           4
            //   527: ifeq            550
            //   530: aload_0        
            //   531: getfield        com/jetbrains/cidr/lang/symbols/symtable/FileSymbolTablesCache$OCCodeBlockModificationListener.this$0:Lcom/jetbrains/cidr/lang/symbols/symtable/FileSymbolTablesCache;
            //   534: aload           5
            //   536: iload_2        
            //   537: iload_3        
            //   538: iload           4
            //   540: invokestatic    com/jetbrains/cidr/lang/symbols/symtable/FileSymbolTablesCache.access$1200:(Lcom/jetbrains/cidr/lang/symbols/symtable/FileSymbolTablesCache;Lcom/intellij/psi/PsiFile;III)V
            //   543: goto            550
            //   546: invokestatic    com/jetbrains/cidr/lang/symbols/symtable/FileSymbolTablesCache$OCCodeBlockModificationListener.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
            //   549: athrow         
            //   550: aload_1        
            //   551: invokevirtual   com/intellij/psi/impl/PsiTreeChangeEventImpl.isGenericChange:()Z
            //   554: ifeq            628
            //   557: aload           5
            //   559: aload_0        
            //   560: getfield        com/jetbrains/cidr/lang/symbols/symtable/FileSymbolTablesCache$OCCodeBlockModificationListener.FILE_PREPROCESSOR_STAMP:Lcom/intellij/openapi/util/Key;
            //   563: invokeinterface com/intellij/psi/PsiFile.getUserData:(Lcom/intellij/openapi/util/Key;)Ljava/lang/Object;
            //   568: checkcast       Ljava/lang/String;
            //   571: astore          8
            //   573: aload           8
            //   575: ifnull          628
            //   578: aload           8
            //   580: aload_0        
            //   581: aload           5
            //   583: invokespecial   com/jetbrains/cidr/lang/symbols/symtable/FileSymbolTablesCache$OCCodeBlockModificationListener.b:(Lcom/intellij/psi/PsiFile;)Ljava/lang/String;
            //   586: invokevirtual   java/lang/String.equals:(Ljava/lang/Object;)Z
            //   589: ifne            616
            //   592: goto            599
            //   595: invokestatic    com/jetbrains/cidr/lang/symbols/symtable/FileSymbolTablesCache$OCCodeBlockModificationListener.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
            //   598: athrow         
            //   599: aload_0        
            //   600: getfield        com/jetbrains/cidr/lang/symbols/symtable/FileSymbolTablesCache$OCCodeBlockModificationListener.this$0:Lcom/jetbrains/cidr/lang/symbols/symtable/FileSymbolTablesCache;
            //   603: aload           5
            //   605: iconst_1       
            //   606: invokevirtual   com/jetbrains/cidr/lang/symbols/symtable/FileSymbolTablesCache.handleFileChange:(Lcom/intellij/psi/PsiFile;Z)V
            //   609: goto            616
            //   612: invokestatic    com/jetbrains/cidr/lang/symbols/symtable/FileSymbolTablesCache$OCCodeBlockModificationListener.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
            //   615: athrow         
            //   616: aload           5
            //   618: aload_0        
            //   619: getfield        com/jetbrains/cidr/lang/symbols/symtable/FileSymbolTablesCache$OCCodeBlockModificationListener.FILE_PREPROCESSOR_STAMP:Lcom/intellij/openapi/util/Key;
            //   622: aconst_null    
            //   623: invokeinterface com/intellij/psi/PsiFile.putUserData:(Lcom/intellij/openapi/util/Key;Ljava/lang/Object;)V
            //   628: return         
            //    Exceptions:
            //  Try           Handler
            //  Start  End    Start  End    Type                                
            //  -----  -----  -----  -----  ------------------------------------
            //  0      40     40     44     Ljava/lang/IllegalArgumentException;
            //  63     73     76     80     Ljava/lang/IllegalArgumentException;
            //  89     101    104    108    Ljava/lang/IllegalArgumentException;
            //  125    134    134    138    Ljava/lang/IllegalArgumentException;
            //  138    222    225    229    Ljava/lang/IllegalArgumentException;
            //  212    232    232    236    Ljava/lang/IllegalArgumentException;
            //  280    291    294    298    Ljava/lang/IllegalArgumentException;
            //  286    306    306    310    Ljava/lang/IllegalArgumentException;
            //  369    380    383    387    Ljava/lang/IllegalArgumentException;
            //  375    395    395    399    Ljava/lang/IllegalArgumentException;
            //  430    442    445    449    Ljava/lang/IllegalArgumentException;
            //  525    543    546    550    Ljava/lang/IllegalArgumentException;
            //  573    592    595    599    Ljava/lang/IllegalArgumentException;
            //  578    609    612    616    Ljava/lang/IllegalArgumentException;
            // 
            // The error that occurred was:
            // 
            // java.lang.IllegalStateException: Expression is linked from several locations: Label_0212:
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
            //     at com.strobel.decompiler.languages.java.ast.AstBuilder.addTypeMembers(AstBuilder.java:556)
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
        
        private void a(@NotNull final PsiFile psiFile) {
            try {
                if (psiFile == null) {
                    throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "file", "com/jetbrains/cidr/lang/symbols/symtable/FileSymbolTablesCache$OCCodeBlockModificationListener", "processBeforeChange"));
                }
            }
            catch (IllegalArgumentException ex) {
                throw a(ex);
            }
            Label_0079: {
                try {
                    ApplicationManager.getApplication().assertIsDispatchThread();
                    if (psiFile instanceof OCCodeFragment) {
                        return;
                    }
                    final PsiFile psiFile2 = psiFile;
                    final OCCodeBlockModificationListener ocCodeBlockModificationListener = this;
                    final Key<String> key = ocCodeBlockModificationListener.FILE_PREPROCESSOR_STAMP;
                    final Object o = psiFile2.getUserData((Key)key);
                    if (o == null) {
                        break Label_0079;
                    }
                    return;
                }
                catch (IllegalArgumentException ex2) {
                    throw a(ex2);
                }
                try {
                    final PsiFile psiFile2 = psiFile;
                    final OCCodeBlockModificationListener ocCodeBlockModificationListener = this;
                    final Key<String> key = ocCodeBlockModificationListener.FILE_PREPROCESSOR_STAMP;
                    final Object o = psiFile2.getUserData((Key)key);
                    if (o == null) {
                        psiFile.putUserData((Key)this.FILE_PREPROCESSOR_STAMP, (Object)this.b(psiFile));
                    }
                }
                catch (IllegalArgumentException ex3) {
                    throw a(ex3);
                }
            }
        }
        
        private void a(final PsiFile psiFile, final PsiElement psiElement) {
            Label_0030: {
                try {
                    ApplicationManager.getApplication().assertIsDispatchThread();
                    if (psiFile instanceof OCCodeFragment) {
                        return;
                    }
                    final OCCodeBlockModificationListener ocCodeBlockModificationListener = this;
                    final PsiElement psiElement2 = psiElement;
                    final boolean b = ocCodeBlockModificationListener.a(psiElement2);
                    if (!b) {
                        break Label_0030;
                    }
                    return;
                }
                catch (IllegalArgumentException ex) {
                    throw a(ex);
                }
                try {
                    final OCCodeBlockModificationListener ocCodeBlockModificationListener = this;
                    final PsiElement psiElement2 = psiElement;
                    final boolean b = ocCodeBlockModificationListener.a(psiElement2);
                    if (!b) {
                        FileSymbolTablesCache.this.handleFileChange(psiFile, false);
                    }
                }
                catch (IllegalArgumentException ex2) {
                    throw a(ex2);
                }
            }
        }
        
        @NotNull
        private String b(final PsiFile psiFile) {
            Label_0055: {
                String s = null;
                Label_0020: {
                    try {
                        if (psiFile instanceof OCFile) {
                            break Label_0055;
                        }
                        s = "";
                        if (s == null) {
                            break Label_0020;
                        }
                        return s;
                    }
                    catch (IllegalArgumentException ex) {
                        throw a(ex);
                    }
                    try {
                        s = "";
                        if (s == null) {
                            throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/symbols/symtable/FileSymbolTablesCache$OCCodeBlockModificationListener", "macroStamp"));
                        }
                    }
                    catch (IllegalArgumentException ex2) {
                        throw a(ex2);
                    }
                }
                return s;
            }
            final StringBuilder sb = new StringBuilder();
            String string;
            try {
                this.a((ASTNode)psiFile.getNode(), sb);
                string = sb.toString();
                if (string == null) {
                    throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/symbols/symtable/FileSymbolTablesCache$OCCodeBlockModificationListener", "macroStamp"));
                }
            }
            catch (IllegalArgumentException ex3) {
                throw a(ex3);
            }
            return string;
        }
        
        private void a(@Nullable final ASTNode astNode, @NotNull final StringBuilder sb) {
            try {
                if (sb == null) {
                    throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "acc", "com/jetbrains/cidr/lang/symbols/symtable/FileSymbolTablesCache$OCCodeBlockModificationListener", "processASTNodeForMacros"));
                }
            }
            catch (IllegalArgumentException ex) {
                throw a(ex);
            }
            try {
                if (astNode == null) {
                    return;
                }
            }
            catch (IllegalArgumentException ex2) {
                throw a(ex2);
            }
            final IElementType elementType = astNode.getElementType();
            try {
                if (OCElementTypes.IMPORTANT_DIRECTIVES.contains(elementType)) {
                    sb.append(astNode.getText());
                }
            }
            catch (IllegalArgumentException ex3) {
                throw a(ex3);
            }
            for (ASTNode astNode2 = astNode.getFirstChildNode(); astNode2 != null; astNode2 = astNode2.getTreeNext()) {
                this.a(astNode2, sb);
            }
        }
        
        private boolean a(@Nullable final PsiElement p0) {
            // 
            // This method could not be decompiled.
            // 
            // Original Bytecode:
            // 
            //     0: aload_1        
            //     1: instanceof      Lcom/intellij/psi/PsiFileSystemItem;
            //     4: ifeq            13
            //     7: iconst_0       
            //     8: ireturn        
            //     9: invokestatic    com/jetbrains/cidr/lang/symbols/symtable/FileSymbolTablesCache$OCCodeBlockModificationListener.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
            //    12: athrow         
            //    13: aload_1        
            //    14: ifnull          33
            //    17: aload_1        
            //    18: invokeinterface com/intellij/psi/PsiElement.getParent:()Lcom/intellij/psi/PsiElement;
            //    23: ifnonnull       39
            //    26: goto            33
            //    29: invokestatic    com/jetbrains/cidr/lang/symbols/symtable/FileSymbolTablesCache$OCCodeBlockModificationListener.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
            //    32: athrow         
            //    33: iconst_1       
            //    34: ireturn        
            //    35: invokestatic    com/jetbrains/cidr/lang/symbols/symtable/FileSymbolTablesCache$OCCodeBlockModificationListener.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
            //    38: athrow         
            //    39: aload_1        
            //    40: astore_2       
            //    41: aload_2        
            //    42: instanceof      Lcom/intellij/psi/PsiFile;
            //    45: ifne            73
            //    48: aload_2        
            //    49: instanceof      Lcom/intellij/psi/PsiDirectory;
            //    52: ifne            73
            //    55: goto            62
            //    58: invokestatic    com/jetbrains/cidr/lang/symbols/symtable/FileSymbolTablesCache$OCCodeBlockModificationListener.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
            //    61: athrow         
            //    62: aload_2        
            //    63: ifnonnull       79
            //    66: goto            73
            //    69: invokestatic    com/jetbrains/cidr/lang/symbols/symtable/FileSymbolTablesCache$OCCodeBlockModificationListener.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
            //    72: athrow         
            //    73: iconst_0       
            //    74: ireturn        
            //    75: invokestatic    com/jetbrains/cidr/lang/symbols/symtable/FileSymbolTablesCache$OCCodeBlockModificationListener.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
            //    78: athrow         
            //    79: aload_2        
            //    80: instanceof      Lcom/jetbrains/cidr/lang/psi/OCLocalBlock;
            //    83: ifeq            106
            //    86: aload_2        
            //    87: instanceof      Lcom/jetbrains/cidr/lang/psi/impl/OCEagerBlockStatementImpl;
            //    90: ifne            106
            //    93: goto            100
            //    96: invokestatic    com/jetbrains/cidr/lang/symbols/symtable/FileSymbolTablesCache$OCCodeBlockModificationListener.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
            //    99: athrow         
            //   100: iconst_1       
            //   101: ireturn        
            //   102: invokestatic    com/jetbrains/cidr/lang/symbols/symtable/FileSymbolTablesCache$OCCodeBlockModificationListener.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
            //   105: athrow         
            //   106: aload_2        
            //   107: invokeinterface com/intellij/psi/PsiElement.getParent:()Lcom/intellij/psi/PsiElement;
            //   112: astore_2       
            //   113: goto            41
            //    Exceptions:
            //  Try           Handler
            //  Start  End    Start  End    Type                                
            //  -----  -----  -----  -----  ------------------------------------
            //  0      9      9      13     Ljava/lang/IllegalArgumentException;
            //  13     26     29     33     Ljava/lang/IllegalArgumentException;
            //  17     35     35     39     Ljava/lang/IllegalArgumentException;
            //  41     55     58     62     Ljava/lang/IllegalArgumentException;
            //  48     66     69     73     Ljava/lang/IllegalArgumentException;
            //  62     75     75     79     Ljava/lang/IllegalArgumentException;
            //  79     93     96     100    Ljava/lang/IllegalArgumentException;
            //  86     102    102    106    Ljava/lang/IllegalArgumentException;
            // 
            // The error that occurred was:
            // 
            // java.lang.IllegalStateException: Expression is linked from several locations: Label_0062:
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
            //     at com.strobel.decompiler.languages.java.ast.AstBuilder.addTypeMembers(AstBuilder.java:556)
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
            boolean $assertionsDisabled2 = false;
            Label_0017: {
                try {
                    if (!FileSymbolTablesCache.class.desiredAssertionStatus()) {
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
        }
        
        private static IllegalArgumentException a(final IllegalArgumentException ex) {
            return ex;
        }
    }
}
