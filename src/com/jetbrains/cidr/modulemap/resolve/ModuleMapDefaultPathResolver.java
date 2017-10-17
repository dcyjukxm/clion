// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.modulemap.resolve;

import com.intellij.openapi.vfs.newvfs.impl.NullVirtualFile;
import java.util.regex.Pattern;
import kotlin.jvm.internal.DefaultConstructorMarker;
import com.intellij.openapi.vfs.VfsUtilCore;
import org.jetbrains.annotations.Nullable;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.Regex;
import org.jetbrains.annotations.NotNull;
import com.intellij.openapi.vfs.VirtualFile;
import kotlin.Metadata;

@Metadata(mv = { 1, 1, 7 }, bv = { 1, 0, 2 }, k = 1, d1 = { "\u0000\"\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u0007\u0018\u0000 \u00122\u00020\u0001:\u0001\u0012B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\u0010\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\u0003H\u0016J\u0018\u0010\n\u001a\u00020\u00012\u0006\u0010\u000b\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\bH\u0016J\u0012\u0010\u000e\u001a\u0004\u0018\u00010\f2\u0006\u0010\t\u001a\u00020\u0003H\u0016J\u0012\u0010\u000f\u001a\u0004\u0018\u00010\u00032\u0006\u0010\u0010\u001a\u00020\fH\u0016J\u0012\u0010\u0011\u001a\u0004\u0018\u00010\u00032\u0006\u0010\u0010\u001a\u00020\fH\u0016R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006¨\u0006\u0013" }, d2 = { "Lcom/jetbrains/cidr/modulemap/resolve/ModuleMapDefaultPathResolver;", "Lcom/jetbrains/cidr/modulemap/resolve/ModuleMapPathResolver;", "root", "Lcom/intellij/openapi/vfs/VirtualFile;", "(Lcom/intellij/openapi/vfs/VirtualFile;)V", "getRoot", "()Lcom/intellij/openapi/vfs/VirtualFile;", "containsHeader", "", "file", "derive", "moduleName", "", "isFramework", "getRelativeHeaderPath", "resolveDir", "relativePath", "resolveHeader", "Companion", "cidr-lang" })
public final class ModuleMapDefaultPathResolver implements ModuleMapPathResolver
{
    @NotNull
    private final VirtualFile root;
    private static final Regex regex;
    public static final Companion Companion;
    
    @Nullable
    @Override
    public VirtualFile resolveHeader(@NotNull final String s) {
        Intrinsics.checkParameterIsNotNull((Object)s, "relativePath");
        return this.root.findFileByRelativePath(ModuleMapDefaultPathResolver.Companion.a().replace((CharSequence)s, "/"));
    }
    
    @Nullable
    @Override
    public VirtualFile resolveDir(@NotNull final String s) {
        Intrinsics.checkParameterIsNotNull((Object)s, "relativePath");
        return this.resolveHeader(s);
    }
    
    @Override
    public boolean containsHeader(@NotNull final VirtualFile virtualFile) {
        Intrinsics.checkParameterIsNotNull((Object)virtualFile, "file");
        return VfsUtilCore.isAncestor(this.root, virtualFile, true);
    }
    
    @Nullable
    @Override
    public String getRelativeHeaderPath(@NotNull final VirtualFile virtualFile) {
        Intrinsics.checkParameterIsNotNull((Object)virtualFile, "file");
        return this.containsHeader(virtualFile) ? VfsUtilCore.getRelativePath(virtualFile, this.root) : null;
    }
    
    @NotNull
    @Override
    public ModuleMapPathResolver derive(@NotNull final String s, final boolean b) {
        Intrinsics.checkParameterIsNotNull((Object)s, "moduleName");
        return this;
    }
    
    @NotNull
    public final VirtualFile getRoot() {
        return this.root;
    }
    
    public ModuleMapDefaultPathResolver(@NotNull final VirtualFile root) {
        Intrinsics.checkParameterIsNotNull((Object)root, "root");
        this.root = root;
    }
    
    static {
        Companion = new Companion(null);
        regex = new Regex(Pattern.compile("^/{2,}"));
    }
    
    @NotNull
    public static final /* synthetic */ Regex access$getRegex$cp() {
        return ModuleMapDefaultPathResolver.regex;
    }
    
    @Metadata(mv = { 1, 1, 7 }, bv = { 1, 0, 2 }, k = 1, d1 = { "\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0006\u0010\u0007\u001a\u00020\bR\u0014\u0010\u0003\u001a\u00020\u0004X\u0082\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006¨\u0006\t" }, d2 = { "Lcom/jetbrains/cidr/modulemap/resolve/ModuleMapDefaultPathResolver$Companion;", "", "()V", "regex", "Lkotlin/text/Regex;", "getRegex", "()Lkotlin/text/Regex;", "createUninitialized", "Lcom/jetbrains/cidr/modulemap/resolve/ModuleMapDefaultPathResolver;", "cidr-lang" })
    public static final class Companion
    {
        private final Regex a() {
            return ModuleMapDefaultPathResolver.access$getRegex$cp();
        }
        
        @NotNull
        public final ModuleMapDefaultPathResolver createUninitialized() {
            final NullVirtualFile instance = NullVirtualFile.INSTANCE;
            Intrinsics.checkExpressionValueIsNotNull((Object)instance, "NullVirtualFile.INSTANCE");
            return new ModuleMapDefaultPathResolver((VirtualFile)instance);
        }
    }
}
