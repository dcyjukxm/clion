// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.lang.preprocessor;

import com.jetbrains.cidr.lang.symbols.symtable.FileSymbolTablesCache;
import com.intellij.openapi.progress.util.ProgressIndicatorUtils;
import com.intellij.openapi.progress.util.ReadTask;
import com.intellij.openapi.progress.ProgressIndicator;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.Nullable;
import java.util.Iterator;
import java.util.ArrayList;
import com.intellij.openapi.application.Application;
import com.intellij.openapi.application.ApplicationManager;
import java.util.Collection;
import com.intellij.openapi.vfs.VirtualFile;
import com.intellij.util.containers.MultiMap;
import java.util.concurrent.atomic.AtomicInteger;
import org.jetbrains.annotations.NotNull;
import com.intellij.openapi.project.Project;

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
