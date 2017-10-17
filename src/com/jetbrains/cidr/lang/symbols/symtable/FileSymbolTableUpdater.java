// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.lang.symbols.symtable;

import gnu.trove.THashSet;
import java.util.Set;
import com.intellij.openapi.application.ModalityState;
import java.util.ArrayList;
import java.util.Collection;
import org.jetbrains.annotations.NotNull;
import java.util.Iterator;
import com.jetbrains.cidr.lang.preprocessor.OCImportGraph;
import com.jetbrains.cidr.lang.workspace.OCResolveConfiguration;
import com.intellij.psi.PsiFile;
import com.jetbrains.cidr.lang.OCLanguageKind;
import com.jetbrains.cidr.lang.preprocessor.OCInclusionContextUtil;
import com.intellij.psi.PsiManager;
import com.intellij.openapi.vfs.VirtualFile;
import com.intellij.openapi.application.ApplicationManager;
import org.jetbrains.annotations.Nullable;
import com.intellij.openapi.progress.ProgressIndicator;
import java.util.concurrent.atomic.AtomicBoolean;
import com.intellij.openapi.project.Project;

class FileSymbolTableUpdater
{
    private final Object myLock;
    private final Project myProject;
    private final UpdateQueue mySourceQueue;
    private final UpdateQueue myHeaderQueue;
    private final AtomicBoolean myHugeUpdateRequested;
    private final ThreadLocal<UpdateQueue> myParentQueue;
    
    public FileSymbolTableUpdater(final Project myProject) {
        this.myLock = new Object();
        this.mySourceQueue = new UpdateQueue();
        this.myHeaderQueue = new UpdateQueue();
        this.myHugeUpdateRequested = new AtomicBoolean();
        this.myParentQueue = new ThreadLocal<UpdateQueue>();
        this.myProject = myProject;
    }
    
    public void ensurePendingFilesProcessed(@Nullable final ProgressIndicator progressIndicator, final boolean b) {
        final UpdateQueue updateQueue = this.myParentQueue.get();
        try {
            if (updateQueue == this.mySourceQueue) {
                throw new IllegalStateException("Nested updates are not allowed for source files.");
            }
        }
        catch (InterruptedException ex) {
            throw b(ex);
        }
        Label_0062: {
            Label_0048: {
                try {
                    if (updateQueue == null) {
                        break Label_0062;
                    }
                    final boolean b2 = b;
                    if (!b2) {
                        break Label_0048;
                    }
                    break Label_0062;
                }
                catch (InterruptedException ex2) {
                    throw b(ex2);
                }
                try {
                    final boolean b2 = b;
                    if (!b2) {
                        throw new IllegalStateException("Nested update may not process header files.");
                    }
                }
                catch (InterruptedException ex3) {
                    throw b(ex3);
                }
            }
            try {
                if (progressIndicator != null) {
                    progressIndicator.checkCanceled();
                }
            }
            catch (InterruptedException ex4) {
                throw b(ex4);
            }
        }
        final boolean dispatchThread = ApplicationManager.getApplication().isDispatchThread();
        while (true) {
            VirtualFile virtualFile = null;
            UpdateQueue updateQueue2 = null;
            synchronized (this.myLock) {
                while (true) {
                    Label_0208: {
                        Label_0187: {
                            Label_0166: {
                                Label_0148: {
                                    Label_0132: {
                                        Label_0119: {
                                            try {
                                                if (virtualFile != null) {
                                                    break;
                                                }
                                                final ProgressIndicator progressIndicator2 = progressIndicator;
                                                if (progressIndicator2 != null) {
                                                    break Label_0119;
                                                }
                                                break Label_0132;
                                            }
                                            catch (InterruptedException ex5) {
                                                throw b(ex5);
                                            }
                                            try {
                                                final ProgressIndicator progressIndicator2 = progressIndicator;
                                                if (progressIndicator2 != null) {
                                                    progressIndicator.checkCanceled();
                                                }
                                            }
                                            catch (InterruptedException ex6) {
                                                throw b(ex6);
                                            }
                                        }
                                        try {
                                            if (!dispatchThread) {
                                                break Label_0166;
                                            }
                                            final boolean b3 = b;
                                            if (b3) {
                                                break Label_0148;
                                            }
                                            break Label_0166;
                                        }
                                        catch (InterruptedException ex7) {
                                            throw b(ex7);
                                        }
                                    }
                                    try {
                                        final boolean b3 = b;
                                        if (!b3) {
                                            break Label_0166;
                                        }
                                        if (!this.a()) {
                                            break Label_0166;
                                        }
                                    }
                                    catch (InterruptedException ex8) {
                                        throw b(ex8);
                                    }
                                }
                                return;
                                try {
                                    if (this.mySourceQueue.hasUnprocessedFiles()) {
                                        break Label_0208;
                                    }
                                    final boolean b4 = b;
                                    if (!b4) {
                                        break Label_0187;
                                    }
                                    return;
                                }
                                catch (InterruptedException ex9) {
                                    throw b(ex9);
                                }
                            }
                            try {
                                final boolean b4 = b;
                                if (!b4) {
                                    if (this.myHeaderQueue.hasUnprocessedFiles()) {
                                        break Label_0208;
                                    }
                                }
                            }
                            catch (InterruptedException ex10) {
                                throw b(ex10);
                            }
                        }
                        return;
                    }
                    virtualFile = this.mySourceQueue.startProcessingNext();
                    updateQueue2 = this.mySourceQueue;
                    Label_0254: {
                        try {
                            if (virtualFile != null || b) {
                                break Label_0254;
                            }
                        }
                        catch (InterruptedException ex11) {
                            throw b(ex11);
                        }
                        virtualFile = this.myHeaderQueue.startProcessingNext();
                        updateQueue2 = this.myHeaderQueue;
                    }
                    if (virtualFile == null) {
                        try {
                            this.myLock.wait(1000L);
                        }
                        catch (InterruptedException ex13) {}
                        continue;
                    }
                    continue;
                    break;
                }
            }
            boolean b5 = false;
            this.myParentQueue.set(updateQueue2);
            try {
                if (progressIndicator != null) {
                    progressIndicator.checkCanceled();
                }
                Label_0348: {
                    try {
                        if (updateQueue2 == this.mySourceQueue) {
                            a(virtualFile, this.myProject, progressIndicator);
                            break Label_0348;
                        }
                    }
                    catch (InterruptedException ex12) {
                        throw b(ex12);
                    }
                    a(virtualFile, this.myProject);
                }
                b5 = true;
            }
            finally {
                this.myParentQueue.set(updateQueue);
                synchronized (this.myLock) {
                    updateQueue2.finishProcessing(virtualFile, b5);
                    this.myLock.notifyAll();
                }
            }
        }
    }
    
    private static void a(final VirtualFile virtualFile, final Project project) {
        final PsiFile file = PsiManager.getInstance(project).findFile(virtualFile);
        final FileSymbolTablesCache instance = FileSymbolTablesCache.getInstance(project);
        Label_0036: {
            try {
                if (!SymbolTableProvider.isSourceFile(file)) {
                    return;
                }
                final FileSymbolTablesCache fileSymbolTablesCache = instance;
                final VirtualFile virtualFile2 = virtualFile;
                final int n = fileSymbolTablesCache.allTablesForFileCount(virtualFile2);
                if (n == 0) {
                    break Label_0036;
                }
                return;
            }
            catch (IllegalStateException ex) {
                throw b(ex);
            }
            try {
                final FileSymbolTablesCache fileSymbolTablesCache = instance;
                final VirtualFile virtualFile2 = virtualFile;
                final int n = fileSymbolTablesCache.allTablesForFileCount(virtualFile2);
                if (n == 0) {
                    instance.forFile(file, OCInclusionContextUtil.calculateHeaderContext(file, null));
                }
            }
            catch (IllegalStateException ex2) {
                throw b(ex2);
            }
        }
    }
    
    private static void a(final VirtualFile virtualFile, final Project project, final ProgressIndicator progressIndicator) {
        final Iterator<? extends OCResolveConfiguration> iterator = OCInclusionContextUtil.getAllBuildConfigurationsForIndexing(virtualFile, project).iterator();
        while (iterator.hasNext()) {
            OCImportGraph.buildSymbolAndRootHeaderCache((OCResolveConfiguration)iterator.next(), virtualFile, null, progressIndicator);
        }
    }
    
    public void addFileForUpdate(@NotNull final VirtualFile virtualFile) {
        try {
            if (virtualFile == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "file", "com/jetbrains/cidr/lang/symbols/symtable/FileSymbolTableUpdater", "addFileForUpdate"));
            }
        }
        catch (IllegalStateException ex) {
            throw b(ex);
        }
        synchronized (this.myLock) {
            this.a(virtualFile);
            this.myLock.notifyAll();
        }
    }
    
    public void addFilesForUpdate(final Collection<VirtualFile> collection, final boolean b) {
        synchronized (this.myLock) {
            if (b) {
                FileSymbolTablesCache.getInstance(this.myProject).removeFilesFromCache(collection);
            }
            final Iterator<VirtualFile> iterator = collection.iterator();
            while (iterator.hasNext()) {
                this.a(iterator.next());
            }
            this.myLock.notifyAll();
        }
    }
    
    private void a(final VirtualFile virtualFile) {
        try {
            if (!virtualFile.isValid()) {
                return;
            }
        }
        catch (IllegalStateException ex) {
            throw b(ex);
        }
        final boolean needToFindRoot = OCInclusionContextUtil.isNeedToFindRoot(virtualFile, this.myProject);
        try {
            if (needToFindRoot) {
                this.myHeaderQueue.add(virtualFile);
                return;
            }
        }
        catch (IllegalStateException ex2) {
            throw b(ex2);
        }
        this.mySourceQueue.add(virtualFile);
    }
    
    private boolean a() {
        final int filesToUpdateSize = this.mySourceQueue.filesToUpdateSize();
        try {
            if (filesToUpdateSize < 20) {
                return false;
            }
        }
        catch (IllegalStateException ex) {
            throw b(ex);
        }
        final ArrayList list = new ArrayList((Collection<? extends E>)this.mySourceQueue.removeAllFilesToUpdate());
        this.myHugeUpdateRequested.set(false);
        final Collection<VirtualFile> collection;
        ApplicationManager.getApplication().invokeLater(() -> {
            try {
                if (this.myProject.isDisposed()) {
                    return;
                }
            }
            catch (IllegalStateException ex2) {
                throw b(ex2);
            }
            try {
                if (this.myHugeUpdateRequested.getAndSet(true)) {
                    return;
                }
            }
            catch (IllegalStateException ex3) {
                throw b(ex3);
            }
            OCSymbolTablesBuildingActivity.getInstance(this.myProject).buildSymbolsForFiles(collection);
            return;
        }, ModalityState.NON_MODAL);
        return true;
    }
    
    private static Exception b(final Exception ex) {
        return ex;
    }
    
    private static class UpdateQueue
    {
        private final Set<VirtualFile> myFilesToUpdate;
        private int myFilesInProgressCount;
        
        private UpdateQueue() {
            this.myFilesToUpdate = (Set<VirtualFile>)new THashSet();
        }
        
        public void add(final VirtualFile virtualFile) {
            this.myFilesToUpdate.add(virtualFile);
        }
        
        public VirtualFile startProcessingNext() {
            final Iterator<VirtualFile> iterator = this.myFilesToUpdate.iterator();
            while (iterator.hasNext()) {
                final VirtualFile virtualFile = iterator.next();
                iterator.remove();
                if (virtualFile.isValid()) {
                    ++this.myFilesInProgressCount;
                    return virtualFile;
                }
            }
            return null;
        }
        
        public boolean hasUnprocessedFiles() {
            return this.myFilesToUpdate.size() > 0 || this.myFilesInProgressCount > 0;
        }
        
        public void finishProcessing(final VirtualFile virtualFile, final boolean b) {
            --this.myFilesInProgressCount;
            if (!b) {
                this.myFilesToUpdate.add(virtualFile);
            }
        }
        
        public int filesToUpdateSize() {
            return this.myFilesToUpdate.size();
        }
        
        public Collection<VirtualFile> removeAllFilesToUpdate() {
            final ArrayList<VirtualFile> list = new ArrayList<VirtualFile>();
            final Iterator<VirtualFile> iterator = this.myFilesToUpdate.iterator();
            while (iterator.hasNext()) {
                final VirtualFile virtualFile = iterator.next();
                if (virtualFile.isValid()) {
                    list.add(virtualFile);
                }
                iterator.remove();
            }
            return list;
        }
    }
}
