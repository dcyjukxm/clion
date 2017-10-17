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
import javax.swing.Icon;
import com.intellij.openapi.roots.ContentEntry;
import com.intellij.openapi.application.ApplicationManager;
import kotlin.jvm.internal.Intrinsics;
import com.intellij.openapi.vfs.VirtualFile;
import com.intellij.openapi.module.Module;
import org.jetbrains.annotations.NotNull;
import com.intellij.openapi.actionSystem.AnActionEvent;
import kotlin.Metadata;
import com.intellij.ide.projectView.actions.MarkRootActionBase;

@Metadata(mv = { 1, 1, 7 }, bv = { 1, 0, 2 }, k = 1, d1 = { "\u0000V\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0011\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0001\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\b&\u0018\u00002\u00020\u0001B\u0015\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0002\u0010\u0006J\u0018\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\fH\u0014J+\u0010\r\u001a\u00020\u000e2\u0006\u0010\u000f\u001a\u00020\u00102\u0006\u0010\u000b\u001a\u00020\f2\f\u0010\u0011\u001a\b\u0012\u0004\u0012\u00020\u00130\u0012H\u0014¢\u0006\u0002\u0010\u0014J\u0018\u0010\r\u001a\u00020\u00152\u0006\u0010\u0016\u001a\u00020\u00132\u0006\u0010\u0017\u001a\u00020\u0018H\u0014J\u0018\u0010\u0019\u001a\u00020\u000e2\u0006\u0010\u001a\u001a\u00020\u001b2\u0006\u0010\u0016\u001a\u00020\u0013H$¨\u0006\u001c" }, d2 = { "Lcom/jetbrains/cidr/project/CidrMarkRootAction;", "Lcom/intellij/ide/projectView/actions/MarkRootActionBase;", "text", "", "icon", "Ljavax/swing/Icon;", "(Ljava/lang/String;Ljavax/swing/Icon;)V", "isEnabled", "", "selection", "Lcom/intellij/ide/projectView/actions/MarkRootActionBase$RootsSelection;", "module", "Lcom/intellij/openapi/module/Module;", "modifyRoots", "", "e", "Lcom/intellij/openapi/actionSystem/AnActionEvent;", "files", "", "Lcom/intellij/openapi/vfs/VirtualFile;", "(Lcom/intellij/openapi/actionSystem/AnActionEvent;Lcom/intellij/openapi/module/Module;[Lcom/intellij/openapi/vfs/VirtualFile;)V", "", "file", "entry", "Lcom/intellij/openapi/roots/ContentEntry;", "registerRoot", "configuration", "Lcom/jetbrains/cidr/project/CidrRootConfiguration;", "cidr-common" })
public abstract class CidrMarkRootAction extends MarkRootActionBase
{
    @Override
    protected void modifyRoots(@NotNull final AnActionEvent anActionEvent, @NotNull final Module module, @NotNull final VirtualFile[] array) {
        Intrinsics.checkParameterIsNotNull((Object)anActionEvent, "e");
        Intrinsics.checkParameterIsNotNull((Object)module, "module");
        Intrinsics.checkParameterIsNotNull((Object)array, "files");
        ApplicationManager.getApplication().runWriteAction((Runnable)new CidrMarkRootAction$modifyRoots.CidrMarkRootAction$modifyRoots$1(this, array, module));
    }
    
    protected abstract void registerRoot(@NotNull final CidrRootConfiguration p0, @NotNull final VirtualFile p1);
    
    @NotNull
    protected Void modifyRoots(@NotNull final VirtualFile virtualFile, @NotNull final ContentEntry contentEntry) {
        Intrinsics.checkParameterIsNotNull((Object)virtualFile, "file");
        Intrinsics.checkParameterIsNotNull((Object)contentEntry, "entry");
        throw new UnsupportedOperationException();
    }
    
    @Override
    protected boolean isEnabled(@NotNull final RootsSelection rootsSelection, @NotNull final Module module) {
        Intrinsics.checkParameterIsNotNull((Object)rootsSelection, "selection");
        Intrinsics.checkParameterIsNotNull((Object)module, "module");
        return a(rootsSelection, module);
    }
    
    public CidrMarkRootAction(@NotNull final String s, @NotNull final Icon icon) {
        Intrinsics.checkParameterIsNotNull((Object)s, "text");
        Intrinsics.checkParameterIsNotNull((Object)icon, "icon");
        super(s, null, icon);
    }
}
