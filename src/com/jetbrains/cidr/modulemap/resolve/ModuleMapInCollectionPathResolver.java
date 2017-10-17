// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.modulemap.resolve;

import com.intellij.openapi.vfs.newvfs.impl.NullVirtualFile;
import kotlin.jvm.internal.DefaultConstructorMarker;
import org.jetbrains.annotations.Nullable;
import java.util.Iterator;
import com.intellij.openapi.util.io.FileUtil;
import kotlin.text.StringsKt;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import java.util.Collection;
import com.intellij.openapi.vfs.VirtualFile;
import kotlin.Metadata;

@Metadata(mv = { 1, 1, 7 }, bv = { 1, 0, 2 }, k = 1, d1 = { "\u0000(\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u001e\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u0006\b\u0016\u0018\u00002\u00020\u0001B\u001d\u0012\b\b\u0002\u0010\u0002\u001a\u00020\u0003\u0012\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00030\u0005¢\u0006\u0002\u0010\u0006J\u0010\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\u0003H\u0016J\u0018\u0010\n\u001a\u00020\u00002\u0006\u0010\u000b\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\bH\u0016J\u0012\u0010\u000e\u001a\u0004\u0018\u00010\f2\u0006\u0010\t\u001a\u00020\u0003H\u0016J\u0012\u0010\u000f\u001a\u0004\u0018\u00010\u00032\u0006\u0010\u0010\u001a\u00020\fH\u0016J\u0012\u0010\u0011\u001a\u0004\u0018\u00010\u00032\u0006\u0010\u0010\u001a\u00020\fH\u0016R\u0014\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00030\u0005X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u0012" }, d2 = { "Lcom/jetbrains/cidr/modulemap/resolve/ModuleMapInCollectionPathResolver;", "Lcom/jetbrains/cidr/modulemap/resolve/ModuleMapPathResolver;", "root", "Lcom/intellij/openapi/vfs/VirtualFile;", "files", "", "(Lcom/intellij/openapi/vfs/VirtualFile;Ljava/util/Collection;)V", "containsHeader", "", "file", "derive", "moduleName", "", "isFramework", "getRelativeHeaderPath", "resolveDir", "relativePath", "resolveHeader", "cidr-lang" })
public class ModuleMapInCollectionPathResolver implements ModuleMapPathResolver
{
    private final VirtualFile root;
    private final Collection<VirtualFile> files;
    
    @Nullable
    @Override
    public VirtualFile resolveHeader(@NotNull final String s) {
        Intrinsics.checkParameterIsNotNull((Object)s, "relativePath");
        if (StringsKt.contains$default((CharSequence)s, (CharSequence)"/", false, 2, (Object)null)) {
            return null;
        }
        for (final VirtualFile next : this.files) {
            if (FileUtil.namesEqual(next.getName(), s)) {
                return next;
            }
        }
        return null;
    }
    
    @Nullable
    @Override
    public VirtualFile resolveDir(@NotNull final String s) {
        Intrinsics.checkParameterIsNotNull((Object)s, "relativePath");
        return (VirtualFile)new ModuleMapInCollectionPathResolver$resolveDir.ModuleMapInCollectionPathResolver$resolveDir$1(this, s, this.root, s);
    }
    
    @Override
    public boolean containsHeader(@NotNull final VirtualFile virtualFile) {
        Intrinsics.checkParameterIsNotNull((Object)virtualFile, "file");
        return this.files.contains(virtualFile);
    }
    
    @Nullable
    @Override
    public String getRelativeHeaderPath(@NotNull final VirtualFile virtualFile) {
        Intrinsics.checkParameterIsNotNull((Object)virtualFile, "file");
        return this.containsHeader(virtualFile) ? virtualFile.getName() : null;
    }
    
    @NotNull
    @Override
    public ModuleMapInCollectionPathResolver derive(@NotNull final String s, final boolean b) {
        Intrinsics.checkParameterIsNotNull((Object)s, "moduleName");
        return this;
    }
    
    public ModuleMapInCollectionPathResolver(@NotNull final VirtualFile root, @NotNull final Collection<? extends VirtualFile> files) {
        Intrinsics.checkParameterIsNotNull((Object)root, "root");
        Intrinsics.checkParameterIsNotNull((Object)files, "files");
        this.root = root;
        this.files = (Collection<VirtualFile>)files;
    }
}
