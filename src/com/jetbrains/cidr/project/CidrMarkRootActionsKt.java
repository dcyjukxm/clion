// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.project;

import org.jetbrains.annotations.NotNull;
import java.util.Iterator;
import java.util.Set;
import com.intellij.openapi.roots.SourceFolder;
import com.intellij.openapi.roots.OrderEntry;
import com.intellij.openapi.roots.OrderRootType;
import com.intellij.openapi.roots.LibraryOrderEntry;
import com.intellij.openapi.roots.ModuleFileIndex;
import com.intellij.util.containers.ContainerUtil;
import java.util.Collection;
import kotlin.collections.CollectionsKt;
import java.util.ArrayList;
import com.intellij.openapi.roots.ModuleRootManager;
import com.intellij.openapi.roots.ModuleRootModel;
import com.intellij.openapi.vfs.pointers.VirtualFilePointer;
import kotlin.collections.ArraysKt;
import com.intellij.openapi.vfs.VirtualFile;
import java.util.List;
import com.intellij.openapi.extensions.AreaInstance;
import com.intellij.openapi.roots.impl.DirectoryIndexExcludePolicy;
import com.intellij.openapi.extensions.ExtensionPointName;
import com.intellij.openapi.extensions.Extensions;
import kotlin.jvm.internal.Intrinsics;
import com.intellij.openapi.util.Key;
import com.intellij.openapi.module.Module;
import com.intellij.ide.projectView.actions.MarkRootActionBase;
import kotlin.Metadata;

@Metadata(mv = { 1, 1, 7 }, bv = { 1, 0, 2 }, k = 2, d1 = { "\u0000$\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\u001a\u0018\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H\u0002\u001a\u0018\u0010\u0006\u001a\u00020\u00012\u0006\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\nH\u0002\u001a\u0018\u0010\u000b\u001a\u00020\u00012\u0006\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\nH\u0002\u001a\u0018\u0010\f\u001a\u00020\u00012\u0006\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0002\u001a\u00020\u0003H\u0002¨\u0006\r" }, d2 = { "hasOutermostContentRootsInSelection", "", "module", "Lcom/intellij/openapi/module/Module;", "selection", "Lcom/intellij/ide/projectView/actions/MarkRootActionBase$RootsSelection;", "isInLibraries", "fileIndex", "Lcom/intellij/openapi/roots/ModuleFileIndex;", "it", "Lcom/intellij/openapi/vfs/VirtualFile;", "isLibraryRoot", "isSupported", "cidr-common" })
public final class CidrMarkRootActionsKt
{
    private static final boolean a(final MarkRootActionBase.RootsSelection rootsSelection, final Module module) {
        if (Intrinsics.areEqual((Object)module.getUserData((Key)CidrRootConfiguration.MODULE_SUPPORTS_ROOTS_CONFIGURATION), (Object)true)) {
            final Object[] extensions = Extensions.getExtensions((ExtensionPointName)CidrMarkRootActionAvailability.EP_NAME);
            int i = 0;
            while (true) {
                while (i < extensions.length) {
                    if (!((CidrMarkRootActionAvailability)extensions[i]).isAvailable(module.getProject())) {
                        final boolean b = false;
                        if (b) {
                            final DirectoryIndexExcludePolicy[] array = DirectoryIndexExcludePolicy.getExtensions((AreaInstance)module.getProject());
                            int j = 0;
                            while (true) {
                                while (j < array.length) {
                                    final DirectoryIndexExcludePolicy directoryIndexExcludePolicy = array[j];
                                    final List list = ArraysKt.asList((Object[])directoryIndexExcludePolicy.getExcludeRootsForProject());
                                    final VirtualFilePointer[] array2 = directoryIndexExcludePolicy.getExcludeRootsForModule((ModuleRootModel)ModuleRootManager.getInstance(module));
                                    final List list2 = list;
                                    final VirtualFilePointer[] array3 = array2;
                                    final ArrayList<VirtualFile> list3 = new ArrayList<VirtualFile>();
                                    final VirtualFilePointer[] array4 = array3;
                                    for (int k = 0; k < array4.length; ++k) {
                                        final VirtualFile file = array4[k].getFile();
                                        if (file != null) {
                                            list3.add(file);
                                        }
                                    }
                                    final List plus = CollectionsKt.plus((Collection)list2, (Iterable)list3);
                                    if (!plus.isEmpty() && ContainerUtil.intersects((Collection)rootsSelection.mySelectedDirectories, (Collection)plus)) {
                                        final boolean b2 = false;
                                        if (b2) {
                                            return true;
                                        }
                                        return false;
                                    }
                                    else {
                                        ++j;
                                    }
                                }
                                final boolean b2 = true;
                                continue;
                            }
                        }
                        return false;
                    }
                    else {
                        ++i;
                    }
                }
                final boolean b = true;
                continue;
            }
        }
        return false;
    }
    
    private static final boolean a(final ModuleFileIndex moduleFileIndex, final VirtualFile virtualFile) {
        final OrderEntry orderEntryForFile = moduleFileIndex.getOrderEntryForFile(virtualFile);
        return orderEntryForFile instanceof LibraryOrderEntry && (ArraysKt.contains((Object[])((LibraryOrderEntry)orderEntryForFile).getRootFiles(OrderRootType.CLASSES), (Object)virtualFile) || ArraysKt.contains((Object[])((LibraryOrderEntry)orderEntryForFile).getRootFiles(OrderRootType.SOURCES), (Object)virtualFile));
    }
    
    private static final boolean b(final ModuleFileIndex moduleFileIndex, final VirtualFile virtualFile) {
        return moduleFileIndex.getOrderEntryForFile(virtualFile) instanceof LibraryOrderEntry;
    }
    
    private static final boolean a(final Module module, final MarkRootActionBase.RootsSelection rootsSelection) {
        final ModuleRootManager instance = ModuleRootManager.getInstance(module);
        final List<VirtualFile> list = rootsSelection.mySelectedDirectories;
        final List<SourceFolder> list2 = rootsSelection.mySelectedRoots;
        final List<VirtualFile> list3 = list;
        final Iterable<Object> iterable = (Iterable<Object>)list2;
        final ArrayList<VirtualFile> list4 = new ArrayList<VirtualFile>(CollectionsKt.collectionSizeOrDefault((Iterable)list2, 10));
        final Iterator<SourceFolder> iterator = iterable.iterator();
        while (iterator.hasNext()) {
            list4.add(iterator.next().getFile());
        }
        for (final VirtualFile virtualFile : CollectionsKt.union((Iterable)list3, (Iterable)CollectionsKt.filterNotNull((Iterable)list4))) {
            if (ArraysKt.contains((Object[])instance.getContentRoots(), (Object)virtualFile) && (virtualFile.getParent() == null || !instance.getFileIndex().isInContent(virtualFile.getParent()))) {
                return true;
            }
        }
        return false;
    }
}
