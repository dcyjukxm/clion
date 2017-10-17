// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.modulemap.resolve;

import kotlin.jvm.internal.DefaultConstructorMarker;
import com.intellij.openapi.vfs.VirtualFile;
import org.jetbrains.annotations.Nullable;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import kotlin.Metadata;

@Metadata(mv = { 1, 1, 7 }, bv = { 1, 0, 2 }, k = 1, d1 = { "\u0000*\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0010\u0001\n\u0002\b\u0004\b\u0002\u0018\u0000 \u00102\u00020\u0001:\u0001\u0010B\u0005¢\u0006\u0002\u0010\u0002J\u0010\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0006H\u0016J\u0018\u0010\u0007\u001a\u00020\u00002\u0006\u0010\b\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\u0004H\u0016J\u0010\u0010\u000b\u001a\u00020\t2\u0006\u0010\u0005\u001a\u00020\u0006H\u0016J\u0012\u0010\f\u001a\u0004\u0018\u00010\r2\u0006\u0010\u000e\u001a\u00020\tH\u0016J\u0012\u0010\u000f\u001a\u0004\u0018\u00010\r2\u0006\u0010\u000e\u001a\u00020\tH\u0016¨\u0006\u0011" }, d2 = { "Lcom/jetbrains/cidr/modulemap/resolve/ModuleMapPathResolver$NullResolver;", "Lcom/jetbrains/cidr/modulemap/resolve/ModuleMapPathResolver;", "()V", "containsHeader", "", "file", "Lcom/intellij/openapi/vfs/VirtualFile;", "derive", "moduleName", "", "isFramework", "getRelativeHeaderPath", "resolveDir", "", "relativePath", "resolveHeader", "Companion", "cidr-lang" })
private static final class NullResolver implements ModuleMapPathResolver
{
    @NotNull
    private static final NullResolver INSTANCE;
    public static final Companion Companion;
    
    @Nullable
    public Void resolveHeader(@NotNull final String s) {
        Intrinsics.checkParameterIsNotNull((Object)s, "relativePath");
        return null;
    }
    
    @Nullable
    public Void resolveDir(@NotNull final String s) {
        Intrinsics.checkParameterIsNotNull((Object)s, "relativePath");
        return null;
    }
    
    @Override
    public boolean containsHeader(@NotNull final VirtualFile virtualFile) {
        Intrinsics.checkParameterIsNotNull((Object)virtualFile, "file");
        return false;
    }
    
    @NotNull
    @Override
    public String getRelativeHeaderPath(@NotNull final VirtualFile virtualFile) {
        Intrinsics.checkParameterIsNotNull((Object)virtualFile, "file");
        return "";
    }
    
    @NotNull
    @Override
    public NullResolver derive(@NotNull final String s, final boolean b) {
        Intrinsics.checkParameterIsNotNull((Object)s, "moduleName");
        return this;
    }
    
    static {
        Companion = new Companion(null);
        INSTANCE = new NullResolver();
    }
    
    @NotNull
    public static final /* synthetic */ NullResolver access$getINSTANCE$cp() {
        return NullResolver.INSTANCE;
    }
    
    @Metadata(mv = { 1, 1, 7 }, bv = { 1, 0, 2 }, k = 1, d1 = { "\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u0011\u0010\u0003\u001a\u00020\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006¨\u0006\u0007" }, d2 = { "Lcom/jetbrains/cidr/modulemap/resolve/ModuleMapPathResolver$NullResolver$Companion;", "", "()V", "INSTANCE", "Lcom/jetbrains/cidr/modulemap/resolve/ModuleMapPathResolver$NullResolver;", "getINSTANCE", "()Lcom/jetbrains/cidr/modulemap/resolve/ModuleMapPathResolver$NullResolver;", "cidr-lang" })
    public static final class Companion
    {
        @NotNull
        public final NullResolver getINSTANCE() {
            return NullResolver.access$getINSTANCE$cp();
        }
    }
}
