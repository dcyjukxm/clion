// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.project;

import java.util.Set;
import com.intellij.openapi.roots.SourceFolder;
import com.intellij.openapi.roots.OrderEntry;
import com.intellij.openapi.roots.OrderRootType;
import com.intellij.openapi.roots.LibraryOrderEntry;
import com.intellij.util.containers.ContainerUtil;
import java.util.Collection;
import kotlin.collections.CollectionsKt;
import java.util.ArrayList;
import com.intellij.openapi.roots.ModuleRootModel;
import com.intellij.openapi.vfs.pointers.VirtualFilePointer;
import kotlin.collections.ArraysKt;
import com.intellij.openapi.extensions.AreaInstance;
import com.intellij.openapi.roots.impl.DirectoryIndexExcludePolicy;
import com.intellij.openapi.extensions.ExtensionPointName;
import com.intellij.openapi.extensions.Extensions;
import com.intellij.openapi.util.Key;
import java.util.Iterator;
import com.intellij.openapi.roots.ModuleFileIndex;
import java.util.List;
import com.intellij.openapi.roots.ModuleRootManager;
import com.intellij.lang.LangBundle;
import com.intellij.ide.projectView.actions.MarkRootActionBase;
import org.jetbrains.annotations.Nullable;
import com.intellij.openapi.roots.ContentEntry;
import com.intellij.openapi.application.ApplicationManager;
import kotlin.jvm.internal.Intrinsics;
import com.intellij.openapi.vfs.VirtualFile;
import com.intellij.openapi.module.Module;
import org.jetbrains.annotations.NotNull;
import com.intellij.openapi.actionSystem.AnActionEvent;
import kotlin.Metadata;
import com.intellij.ide.projectView.actions.UnmarkRootAction;

@Metadata(mv = { 1, 1, 7 }, bv = { 1, 0, 2 }, k = 1, d1 = { "\u0000N\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u0011\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0001\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J$\u0010\u0003\u001a\u0004\u0018\u00010\u00042\u0006\u0010\u0005\u001a\u00020\u00062\b\u0010\u0007\u001a\u0004\u0018\u00010\b2\u0006\u0010\t\u001a\u00020\nH\u0014J\u0018\u0010\u000b\u001a\u00020\f2\u0006\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\nH\u0002J\u0018\u0010\r\u001a\u00020\u000e2\u0006\u0010\t\u001a\u00020\n2\u0006\u0010\u0007\u001a\u00020\bH\u0014J+\u0010\u000f\u001a\u00020\u00102\u0006\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\b2\f\u0010\u0011\u001a\b\u0012\u0004\u0012\u00020\u00130\u0012H\u0014¢\u0006\u0002\u0010\u0014J\u0018\u0010\u000f\u001a\u00020\u00152\u0006\u0010\u0016\u001a\u00020\u00132\u0006\u0010\u0017\u001a\u00020\u0018H\u0014¨\u0006\u0019" }, d2 = { "Lcom/jetbrains/cidr/project/CidrUnmarkRootAction;", "Lcom/intellij/ide/projectView/actions/UnmarkRootAction;", "()V", "getActionText", "", "e", "Lcom/intellij/openapi/actionSystem/AnActionEvent;", "module", "Lcom/intellij/openapi/module/Module;", "selection", "Lcom/intellij/ide/projectView/actions/MarkRootActionBase$RootsSelection;", "getSelectedRootsCount", "", "isEnabled", "", "modifyRoots", "", "files", "", "Lcom/intellij/openapi/vfs/VirtualFile;", "(Lcom/intellij/openapi/actionSystem/AnActionEvent;Lcom/intellij/openapi/module/Module;[Lcom/intellij/openapi/vfs/VirtualFile;)V", "", "file", "entry", "Lcom/intellij/openapi/roots/ContentEntry;", "cidr-common" })
public final class CidrUnmarkRootAction extends UnmarkRootAction
{
    @Override
    protected void modifyRoots(@NotNull final AnActionEvent anActionEvent, @NotNull final Module module, @NotNull final VirtualFile[] array) {
        Intrinsics.checkParameterIsNotNull((Object)anActionEvent, "e");
        Intrinsics.checkParameterIsNotNull((Object)module, "module");
        Intrinsics.checkParameterIsNotNull((Object)array, "files");
        ApplicationManager.getApplication().runWriteAction((Runnable)new CidrUnmarkRootAction$modifyRoots.CidrUnmarkRootAction$modifyRoots$1(array, module));
    }
    
    @NotNull
    protected Void modifyRoots(@NotNull final VirtualFile virtualFile, @NotNull final ContentEntry contentEntry) {
        Intrinsics.checkParameterIsNotNull((Object)virtualFile, "file");
        Intrinsics.checkParameterIsNotNull((Object)contentEntry, "entry");
        throw new UnsupportedOperationException();
    }
    
    @Nullable
    @Override
    protected String getActionText(@NotNull final AnActionEvent anActionEvent, @Nullable final Module module, @NotNull final RootsSelection rootsSelection) {
        Label_0033: {
            try {
                Intrinsics.checkParameterIsNotNull((Object)anActionEvent, "e");
                Intrinsics.checkParameterIsNotNull((Object)rootsSelection, "selection");
                if (module == null) {
                    return super.getActionText(anActionEvent, module, rootsSelection);
                }
                final CidrUnmarkRootAction cidrUnmarkRootAction = this;
                final Module module2 = module;
                final RootsSelection rootsSelection2 = rootsSelection;
                final int n = cidrUnmarkRootAction.a(module2, rootsSelection2);
                final int n2 = 1;
                if (n > n2) {
                    break Label_0033;
                }
                return null;
            }
            catch (UnsupportedOperationException ex) {
                throw b(ex);
            }
            try {
                final CidrUnmarkRootAction cidrUnmarkRootAction = this;
                final Module module2 = module;
                final RootsSelection rootsSelection2 = rootsSelection;
                final int n = cidrUnmarkRootAction.a(module2, rootsSelection2);
                final int n2 = 1;
                if (n > n2) {
                    return LangBundle.message("mark.as.unmark.several", new Object[0]);
                }
            }
            catch (UnsupportedOperationException ex2) {
                throw b(ex2);
            }
        }
        return null;
    }
    
    @Override
    protected boolean isEnabled(@NotNull final RootsSelection rootsSelection, @NotNull final Module module) {
        Label_0036: {
            try {
                Intrinsics.checkParameterIsNotNull((Object)rootsSelection, "selection");
                Intrinsics.checkParameterIsNotNull((Object)module, "module");
                if (!a(rootsSelection, module)) {
                    return false;
                }
                final CidrUnmarkRootAction cidrUnmarkRootAction = this;
                final Module module2 = module;
                final RootsSelection rootsSelection2 = rootsSelection;
                final int n = cidrUnmarkRootAction.a(module2, rootsSelection2);
                if (n > 0) {
                    break Label_0036;
                }
                return false;
            }
            catch (UnsupportedOperationException ex) {
                throw b(ex);
            }
            try {
                final CidrUnmarkRootAction cidrUnmarkRootAction = this;
                final Module module2 = module;
                final RootsSelection rootsSelection2 = rootsSelection;
                final int n = cidrUnmarkRootAction.a(module2, rootsSelection2);
                if (n > 0) {
                    return true;
                }
            }
            catch (UnsupportedOperationException ex2) {
                throw b(ex2);
            }
        }
        return false;
    }
    
    private final int a(final Module module, final RootsSelection rootsSelection) {
        final ModuleFileIndex fileIndex = ModuleRootManager.getInstance(module).getFileIndex();
        int n = rootsSelection.mySelectedRoots.size() + rootsSelection.mySelectedExcludeRoots.size();
        for (final VirtualFile virtualFile : rootsSelection.mySelectedDirectories) {
            final ModuleFileIndex moduleFileIndex = fileIndex;
            Intrinsics.checkExpressionValueIsNotNull((Object)moduleFileIndex, "fileIndex");
            final VirtualFile virtualFile2 = virtualFile;
            Intrinsics.checkExpressionValueIsNotNull((Object)virtualFile2, "it");
            if (a(moduleFileIndex, virtualFile2)) {
                n++;
            }
            try {
                if (n > 1) {
                    continue;
                }
                continue;
            }
            catch (UnsupportedOperationException ex) {
                throw b(ex);
            }
        }
        return n;
    }
    
    private static UnsupportedOperationException b(final UnsupportedOperationException ex) {
        return ex;
    }
}
