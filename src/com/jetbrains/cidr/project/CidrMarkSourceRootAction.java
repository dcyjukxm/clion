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
import javax.swing.Icon;
import com.intellij.icons.AllIcons$Modules;
import com.jetbrains.cidr.CidrBundle;
import java.util.Iterator;
import com.intellij.openapi.roots.ModuleFileIndex;
import java.util.List;
import com.intellij.openapi.roots.ModuleRootManager;
import com.intellij.openapi.module.Module;
import com.intellij.ide.projectView.actions.MarkRootActionBase;
import kotlin.jvm.internal.Intrinsics;
import com.intellij.openapi.vfs.VirtualFile;
import org.jetbrains.annotations.NotNull;
import kotlin.Metadata;

@Metadata(mv = { 1, 1, 7 }, bv = { 1, 0, 2 }, k = 1, d1 = { "\u00000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\u0018\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\bH\u0014J\u0018\u0010\t\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\u000eH\u0014¨\u0006\u000f" }, d2 = { "Lcom/jetbrains/cidr/project/CidrMarkSourceRootAction;", "Lcom/jetbrains/cidr/project/CidrMarkRootAction;", "()V", "isEnabled", "", "selection", "Lcom/intellij/ide/projectView/actions/MarkRootActionBase$RootsSelection;", "module", "Lcom/intellij/openapi/module/Module;", "registerRoot", "", "configuration", "Lcom/jetbrains/cidr/project/CidrRootConfiguration;", "file", "Lcom/intellij/openapi/vfs/VirtualFile;", "cidr-common" })
public final class CidrMarkSourceRootAction extends CidrMarkRootAction
{
    @Override
    protected void registerRoot(@NotNull final CidrRootConfiguration cidrRootConfiguration, @NotNull final VirtualFile virtualFile) {
        Intrinsics.checkParameterIsNotNull((Object)cidrRootConfiguration, "configuration");
        Intrinsics.checkParameterIsNotNull((Object)virtualFile, "file");
        cidrRootConfiguration.addSourceRoot(virtualFile);
    }
    
    @Override
    protected boolean isEnabled(@NotNull final RootsSelection rootsSelection, @NotNull final Module module) {
        Intrinsics.checkParameterIsNotNull((Object)rootsSelection, "selection");
        Intrinsics.checkParameterIsNotNull((Object)module, "module");
        final ModuleFileIndex fileIndex = ModuleRootManager.getInstance(module).getFileIndex();
        if (super.isEnabled(rootsSelection, module) && rootsSelection.mySelectedRoots.isEmpty()) {
            while (true) {
                for (final VirtualFile virtualFile : rootsSelection.mySelectedDirectories) {
                    boolean b = false;
                    Label_0122: {
                        if (fileIndex.isInSourceContent(virtualFile)) {
                            final ModuleFileIndex moduleFileIndex = fileIndex;
                            Intrinsics.checkExpressionValueIsNotNull((Object)moduleFileIndex, "index");
                            final VirtualFile virtualFile2 = virtualFile;
                            Intrinsics.checkExpressionValueIsNotNull((Object)virtualFile2, "it");
                            if (!b(moduleFileIndex, virtualFile2)) {
                                b = true;
                                break Label_0122;
                            }
                        }
                        b = false;
                    }
                    if (b) {
                        final boolean b2 = false;
                        if (b2) {
                            return true;
                        }
                        return false;
                    }
                }
                final boolean b2 = true;
                continue;
            }
        }
        return false;
    }
    
    public CidrMarkSourceRootAction() {
        final String message = CidrBundle.message("project.view.actions.markAsSource", new Object[0]);
        Intrinsics.checkExpressionValueIsNotNull((Object)message, "CidrBundle.message(\"proj\u2026ew.actions.markAsSource\")");
        final Icon sourceRoot = AllIcons$Modules.SourceRoot;
        Intrinsics.checkExpressionValueIsNotNull((Object)sourceRoot, "AllIcons.Modules.SourceRoot");
        super(message, sourceRoot);
    }
}
