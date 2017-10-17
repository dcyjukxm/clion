// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.project;

import java.util.Map;
import com.intellij.openapi.vfs.VfsUtilCore;
import java.util.ArrayList;
import java.util.Iterator;
import com.intellij.openapi.roots.ModuleFileIndex;
import com.intellij.openapi.module.Module;
import com.intellij.openapi.vfs.newvfs.events.VFileMoveEvent;
import com.intellij.openapi.vfs.VirtualFile;
import java.util.Collection;
import java.util.Collections;
import gnu.trove.THashSet;
import org.jetbrains.annotations.Nullable;
import com.intellij.openapi.roots.impl.ModuleLibraryOrderEntryImpl;
import com.intellij.openapi.roots.impl.libraries.LibraryEx;
import com.intellij.openapi.roots.OrderEntry;
import com.intellij.util.containers.FactoryMap;
import com.intellij.openapi.roots.ModuleRootManager;
import org.jetbrains.annotations.NotNull;
import com.intellij.openapi.vfs.newvfs.events.VFileEvent;
import java.util.List;
import com.intellij.openapi.vfs.newvfs.BulkFileListener;

class CidrRootsSynchronizer$2 implements BulkFileListener {
    public void after(@NotNull final List<? extends VFileEvent> list) {
        try {
            if (list == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "events", "com/jetbrains/cidr/project/CidrRootsSynchronizer$2", "after"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        final Module module = CidrRootsSynchronizer.this.getModule();
        try {
            if (module == null) {
                return;
            }
        }
        catch (IllegalArgumentException ex2) {
            throw a(ex2);
        }
        final ModuleRootManager instance = ModuleRootManager.getInstance(module);
        final ModuleFileIndex fileIndex = instance.getFileIndex();
        final FactoryMap<OrderEntry, LibraryEx.ModifiableModelEx> factoryMap = new FactoryMap<OrderEntry, LibraryEx.ModifiableModelEx>() {
            @Nullable
            protected LibraryEx.ModifiableModelEx create(final OrderEntry orderEntry) {
                return (LibraryEx.ModifiableModelEx)((ModuleLibraryOrderEntryImpl)orderEntry).getLibrary().getModifiableModel();
            }
        };
        final THashSet set = new THashSet();
        Collections.addAll((Collection<? super VirtualFile>)set, instance.getContentRoots());
        Collections.addAll((Collection<? super VirtualFile>)set, instance.getSourceRoots());
        Collections.addAll((Collection<? super VirtualFile>)set, instance.getExcludeRoots());
        for (final VFileEvent vFileEvent : list) {
            try {
                if (!(vFileEvent instanceof VFileMoveEvent)) {
                    continue;
                }
            }
            catch (IllegalArgumentException ex3) {
                throw a(ex3);
            }
            for (final VirtualFile virtualFile : this.a(((VFileMoveEvent)vFileEvent).getFile(), (Collection<VirtualFile>)set)) {
                final OrderEntry orderEntryForFile = fileIndex.getOrderEntryForFile(((VFileMoveEvent)vFileEvent).getOldParent());
                final OrderEntry orderEntryForFile2 = fileIndex.getOrderEntryForFile(virtualFile.getParent());
                Label_0268: {
                    try {
                        if (orderEntryForFile instanceof ModuleLibraryOrderEntryImpl == orderEntryForFile2 instanceof ModuleLibraryOrderEntryImpl) {
                            continue;
                        }
                        final OrderEntry orderEntry = orderEntryForFile2;
                        final boolean b = orderEntry instanceof ModuleLibraryOrderEntryImpl;
                        if (b) {
                            break Label_0268;
                        }
                        break Label_0268;
                    }
                    catch (IllegalArgumentException ex4) {
                        throw a(ex4);
                    }
                    try {
                        final OrderEntry orderEntry = orderEntryForFile2;
                        final boolean b = orderEntry instanceof ModuleLibraryOrderEntryImpl;
                        if (b) {
                            ((Map<K, LibraryEx.ModifiableModelEx>)factoryMap).get(orderEntryForFile2).addExcludedRoot(virtualFile.getUrl());
                            continue;
                        }
                    }
                    catch (IllegalArgumentException ex5) {
                        throw a(ex5);
                    }
                }
                ((Map<K, LibraryEx.ModifiableModelEx>)factoryMap).get(orderEntryForFile).removeExcludedRoot(virtualFile.getUrl());
            }
        }
        final Iterator<LibraryEx.ModifiableModelEx> iterator3 = ((Map<K, LibraryEx.ModifiableModelEx>)factoryMap).values().iterator();
        while (iterator3.hasNext()) {
            iterator3.next().commit();
        }
    }
    
    private List<VirtualFile> a(final VirtualFile virtualFile, final Collection<VirtualFile> collection) {
        final ArrayList<VirtualFile> list = new ArrayList<VirtualFile>();
        VfsUtilCore.processFilesRecursively(virtualFile, virtualFile -> {
            try {
                if (collection.contains(virtualFile)) {
                    list.add(virtualFile);
                }
            }
            catch (IllegalArgumentException ex) {
                throw a(ex);
            }
            return true;
        });
        return list;
    }
    
    private static IllegalArgumentException a(final IllegalArgumentException ex) {
        return ex;
    }
}