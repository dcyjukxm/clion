// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.modulemap.resolve;

import com.intellij.testFramework.LightVirtualFile;
import kotlin.jvm.internal.DefaultConstructorMarker;
import com.jetbrains.cidr.lang.workspace.headerRoots.AppleFramework;
import com.intellij.openapi.vfs.VfsUtilCore;
import org.jetbrains.annotations.Nullable;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import com.intellij.openapi.vfs.VirtualFile;
import kotlin.Metadata;

@Metadata(mv = { 1, 1, 7 }, bv = { 1, 0, 2 }, k = 1, d1 = { "\u0000\"\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\b\u0018\u0000 \u00132\u00020\u0001:\u0001\u0013B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\u0010\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\u0003H\u0016J\u0018\u0010\n\u001a\u00020\u00012\u0006\u0010\u000b\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\bH\u0016J\u0012\u0010\u000e\u001a\u0004\u0018\u00010\u00032\u0006\u0010\t\u001a\u00020\u0003H\u0002J\u0012\u0010\u000f\u001a\u0004\u0018\u00010\f2\u0006\u0010\t\u001a\u00020\u0003H\u0016J\u0012\u0010\u0010\u001a\u0004\u0018\u00010\u00032\u0006\u0010\u0011\u001a\u00020\fH\u0016J\u0012\u0010\u0012\u001a\u0004\u0018\u00010\u00032\u0006\u0010\u0011\u001a\u00020\fH\u0016R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006¨\u0006\u0014" }, d2 = { "Lcom/jetbrains/cidr/modulemap/resolve/ModuleMapRealFrameworkPathResolver;", "Lcom/jetbrains/cidr/modulemap/resolve/ModuleMapPathResolver;", "frameworkRoot", "Lcom/intellij/openapi/vfs/VirtualFile;", "(Lcom/intellij/openapi/vfs/VirtualFile;)V", "getFrameworkRoot", "()Lcom/intellij/openapi/vfs/VirtualFile;", "containsHeader", "", "file", "derive", "moduleName", "", "isFramework", "getContainingHeaderRoot", "getRelativeHeaderPath", "resolveDir", "relativePath", "resolveHeader", "Companion", "cidr-lang" })
public final class ModuleMapRealFrameworkPathResolver implements ModuleMapPathResolver
{
    @NotNull
    private final VirtualFile frameworkRoot;
    public static final Companion Companion;
    
    @Nullable
    @Override
    public VirtualFile resolveHeader(@NotNull final String s) {
        Intrinsics.checkParameterIsNotNull((Object)s, "relativePath");
        return this.frameworkRoot.findFileByRelativePath("Headers/" + s);
    }
    
    @Nullable
    @Override
    public VirtualFile resolveDir(@NotNull final String s) {
        Intrinsics.checkParameterIsNotNull((Object)s, "relativePath");
        return this.frameworkRoot.findFileByRelativePath(s);
    }
    
    @Override
    public boolean containsHeader(@NotNull final VirtualFile virtualFile) {
        Intrinsics.checkParameterIsNotNull((Object)virtualFile, "file");
        return this.a(virtualFile) != null;
    }
    
    @Nullable
    @Override
    public String getRelativeHeaderPath(@NotNull final VirtualFile virtualFile) {
        Intrinsics.checkParameterIsNotNull((Object)virtualFile, "file");
        final VirtualFile a = this.a(virtualFile);
        if (a != null) {
            return VfsUtilCore.getRelativePath(virtualFile, a);
        }
        return null;
    }
    
    @NotNull
    @Override
    public ModuleMapPathResolver derive(@NotNull final String s, final boolean b) {
        Intrinsics.checkParameterIsNotNull((Object)s, "moduleName");
        if (b) {
            final VirtualFile fileByRelativePath = this.frameworkRoot.findFileByRelativePath("Frameworks");
            final VirtualFile virtualFile = (fileByRelativePath != null) ? fileByRelativePath.findFileByRelativePath("" + s + ".framework") : null;
            if (virtualFile != null) {
                return new ModuleMapRealFrameworkPathResolver(virtualFile);
            }
        }
        return this;
    }
    
    private final VirtualFile a(final VirtualFile virtualFile) {
        final String[] headers_DIR_NAMES = AppleFramework.HEADERS_DIR_NAMES;
        for (int i = 0; i < headers_DIR_NAMES.length; ++i) {
            final VirtualFile child = this.frameworkRoot.findChild(headers_DIR_NAMES[i]);
            if (child != null && VfsUtilCore.isAncestor(child, virtualFile, true)) {
                return child;
            }
        }
        return null;
    }
    
    @NotNull
    public final VirtualFile getFrameworkRoot() {
        return this.frameworkRoot;
    }
    
    public ModuleMapRealFrameworkPathResolver(@NotNull final VirtualFile frameworkRoot) {
        Intrinsics.checkParameterIsNotNull((Object)frameworkRoot, "frameworkRoot");
        this.frameworkRoot = frameworkRoot;
    }
    
    static {
        Companion = new Companion(null);
    }
    
    @Metadata(mv = { 1, 1, 7 }, bv = { 1, 0, 2 }, k = 1, d1 = { "\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0006\u0010\u0003\u001a\u00020\u0004¨\u0006\u0005" }, d2 = { "Lcom/jetbrains/cidr/modulemap/resolve/ModuleMapRealFrameworkPathResolver$Companion;", "", "()V", "createUninitialized", "Lcom/jetbrains/cidr/modulemap/resolve/ModuleMapRealFrameworkPathResolver;", "cidr-lang" })
    public static final class Companion
    {
        @NotNull
        public final ModuleMapRealFrameworkPathResolver createUninitialized() {
            return new ModuleMapRealFrameworkPathResolver((VirtualFile)new LightVirtualFile());
        }
    }
}
