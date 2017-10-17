// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.modulemap.resolve;

import com.intellij.openapi.vfs.newvfs.impl.NullVirtualFile;
import kotlin.jvm.internal.DefaultConstructorMarker;
import java.util.Iterator;
import com.intellij.openapi.util.io.FileUtil;
import kotlin.text.StringsKt;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import java.util.Collection;
import kotlin.TypeCastException;
import com.intellij.openapi.vfs.VirtualFile;
import kotlin.Metadata;
import com.intellij.openapi.vfs.newvfs.impl.FakeVirtualFile;

@Metadata(mv = { 1, 1, 7 }, bv = { 1, 0, 2 }, k = 1, d1 = { "\u0000\u0019\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0011\n\u0002\u0018\u0002\n\u0002\b\u0002*\u0001\u0000\b\n\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\u0017\u0010\u0003\u001a\f\u0012\u0006\b\u0001\u0012\u00020\u0005\u0018\u00010\u0004H\u0016¢\u0006\u0002\u0010\u0006¨\u0006\u0007" }, d2 = { "com/jetbrains/cidr/modulemap/resolve/ModuleMapInCollectionPathResolver$resolveDir$1", "Lcom/intellij/openapi/vfs/newvfs/impl/FakeVirtualFile;", "(Lcom/jetbrains/cidr/modulemap/resolve/ModuleMapInCollectionPathResolver;Ljava/lang/String;Lcom/intellij/openapi/vfs/VirtualFile;Ljava/lang/String;)V", "getChildren", "", "Lcom/intellij/openapi/vfs/VirtualFile;", "()[Lcom/intellij/openapi/vfs/VirtualFile;", "cidr-lang" })
public static final class ModuleMapInCollectionPathResolver$resolveDir$1 extends FakeVirtualFile {
    @Nullable
    public VirtualFile[] getChildren() {
        final Collection access$getFiles$p = ModuleMapInCollectionPathResolver.access$getFiles$p(this.this$0);
        Collection collection;
        try {
            collection = access$getFiles$p;
            if (collection == null) {
                throw new TypeCastException("null cannot be cast to non-null type java.util.Collection<T>");
            }
        }
        catch (TypeCastException ex) {
            throw b(ex);
        }
        final Collection collection2 = collection;
        VirtualFile[] array;
        try {
            array = collection2.toArray(new VirtualFile[collection2.size()]);
            if (array == null) {
                throw new TypeCastException("null cannot be cast to non-null type kotlin.Array<T>");
            }
        }
        catch (TypeCastException ex2) {
            throw b(ex2);
        }
        return array;
    }
    
    private static TypeCastException b(final TypeCastException ex) {
        return ex;
    }
}