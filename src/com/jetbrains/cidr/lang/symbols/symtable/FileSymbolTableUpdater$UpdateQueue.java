// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.lang.symbols.symtable;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import gnu.trove.THashSet;
import com.intellij.openapi.vfs.VirtualFile;
import java.util.Set;

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
