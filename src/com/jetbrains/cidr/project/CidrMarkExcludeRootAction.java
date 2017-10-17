// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.project;

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
import java.util.List;
import com.intellij.openapi.extensions.AreaInstance;
import com.intellij.openapi.roots.impl.DirectoryIndexExcludePolicy;
import com.intellij.openapi.extensions.ExtensionPointName;
import com.intellij.openapi.extensions.Extensions;
import com.intellij.openapi.util.Key;
import com.intellij.ide.projectView.actions.MarkRootActionBase;
import com.intellij.openapi.roots.ContentEntry;
import com.intellij.openapi.application.ApplicationManager;
import kotlin.jvm.internal.Intrinsics;
import com.intellij.openapi.vfs.VirtualFile;
import com.intellij.openapi.module.Module;
import org.jetbrains.annotations.NotNull;
import com.intellij.openapi.actionSystem.AnActionEvent;
import kotlin.Metadata;
import com.intellij.ide.projectView.actions.MarkExcludeRootAction;

@Metadata(mv = { 1, 1, 7 }, bv = { 1, 0, 2 }, k = 1, d1 = { "\u0000B\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0011\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0001\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\u0018\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\bH\u0014J+\u0010\t\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\f2\u0006\u0010\u0007\u001a\u00020\b2\f\u0010\r\u001a\b\u0012\u0004\u0012\u00020\u000f0\u000eH\u0014¢\u0006\u0002\u0010\u0010J\u0018\u0010\t\u001a\u00020\u00112\u0006\u0010\u0012\u001a\u00020\u000f2\u0006\u0010\u0013\u001a\u00020\u0014H\u0014¨\u0006\u0015" }, d2 = { "Lcom/jetbrains/cidr/project/CidrMarkExcludeRootAction;", "Lcom/intellij/ide/projectView/actions/MarkExcludeRootAction;", "()V", "isEnabled", "", "selection", "Lcom/intellij/ide/projectView/actions/MarkRootActionBase$RootsSelection;", "module", "Lcom/intellij/openapi/module/Module;", "modifyRoots", "", "e", "Lcom/intellij/openapi/actionSystem/AnActionEvent;", "files", "", "Lcom/intellij/openapi/vfs/VirtualFile;", "(Lcom/intellij/openapi/actionSystem/AnActionEvent;Lcom/intellij/openapi/module/Module;[Lcom/intellij/openapi/vfs/VirtualFile;)V", "", "file", "entry", "Lcom/intellij/openapi/roots/ContentEntry;", "cidr-common" })
public final class CidrMarkExcludeRootAction extends MarkExcludeRootAction
{
    @Override
    protected void modifyRoots(@NotNull final AnActionEvent anActionEvent, @NotNull final Module module, @NotNull final VirtualFile[] array) {
        Intrinsics.checkParameterIsNotNull((Object)anActionEvent, "e");
        Intrinsics.checkParameterIsNotNull((Object)module, "module");
        Intrinsics.checkParameterIsNotNull((Object)array, "files");
        ApplicationManager.getApplication().runWriteAction((Runnable)new CidrMarkExcludeRootAction$modifyRoots.CidrMarkExcludeRootAction$modifyRoots$1(array, module));
    }
    
    @NotNull
    protected Void modifyRoots(@NotNull final VirtualFile virtualFile, @NotNull final ContentEntry contentEntry) {
        Intrinsics.checkParameterIsNotNull((Object)virtualFile, "file");
        Intrinsics.checkParameterIsNotNull((Object)contentEntry, "entry");
        throw new UnsupportedOperationException();
    }
    
    @Override
    protected boolean isEnabled(@NotNull final RootsSelection rootsSelection, @NotNull final Module module) {
        Label_0042: {
            try {
                Intrinsics.checkParameterIsNotNull((Object)rootsSelection, "selection");
                Intrinsics.checkParameterIsNotNull((Object)module, "module");
                if (!super.isEnabled(rootsSelection, module)) {
                    return false;
                }
                final RootsSelection rootsSelection2 = rootsSelection;
                final Module module2 = module;
                final boolean b = a(rootsSelection2, module2);
                if (!b) {
                    return false;
                }
                break Label_0042;
            }
            catch (UnsupportedOperationException ex) {
                throw b(ex);
            }
            try {
                final RootsSelection rootsSelection2 = rootsSelection;
                final Module module2 = module;
                final boolean b = a(rootsSelection2, module2);
                if (!b) {
                    return false;
                }
            }
            catch (UnsupportedOperationException ex2) {
                throw b(ex2);
            }
            try {
                if (!a(module, rootsSelection)) {
                    return true;
                }
            }
            catch (UnsupportedOperationException ex3) {
                throw b(ex3);
            }
        }
        return false;
    }
    
    private static UnsupportedOperationException b(final UnsupportedOperationException ex) {
        return ex;
    }
}
