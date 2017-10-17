// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.modulemap.symbols;

import kotlin.jvm.internal.DefaultConstructorMarker;
import com.intellij.openapi.vfs.newvfs.FileAttribute;
import org.jetbrains.annotations.NotNull;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.internal.Intrinsics;
import com.intellij.openapi.components.ServiceManager;
import kotlin.Metadata;

@Metadata(mv = { 1, 1, 7 }, bv = { 1, 0, 2 }, k = 1, d1 = { "\u0000&\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\b\u0010\u000b\u001a\u00020\fH\u0007R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0006X\u0082T¢\u0006\u0002\n\u0000R\u0014\u0010\u0007\u001a\u00020\bX\u0082\u0004¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\n¨\u0006\r" }, d2 = { "Lcom/jetbrains/cidr/modulemap/symbols/ModuleMapDiskCache$Companion;", "", "()V", "SERIALIZATION_VERSION", "", "TABLES_KEY_PREFIX", "", "ourFileCacheAttribute", "Lcom/intellij/openapi/vfs/newvfs/FileAttribute;", "getOurFileCacheAttribute", "()Lcom/intellij/openapi/vfs/newvfs/FileAttribute;", "getInstance", "Lcom/jetbrains/cidr/modulemap/symbols/ModuleMapDiskCache;", "cidr-lang" })
public static final class Companion
{
    @JvmStatic
    @NotNull
    public final ModuleMapDiskCache getInstance() {
        final Object service = ServiceManager.getService((Class)ModuleMapDiskCache.class);
        Intrinsics.checkExpressionValueIsNotNull(service, "ServiceManager.getServic\u2026MapDiskCache::class.java)");
        return (ModuleMapDiskCache)service;
    }
    
    private final FileAttribute a() {
        return ModuleMapDiskCache.access$getOurFileCacheAttribute$cp();
    }
}
