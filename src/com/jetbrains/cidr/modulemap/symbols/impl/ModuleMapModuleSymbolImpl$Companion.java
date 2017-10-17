// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.modulemap.symbols.impl;

import kotlin.jvm.internal.DefaultConstructorMarker;
import org.jetbrains.annotations.NotNull;
import java.util.Map;
import java.util.List;
import com.jetbrains.cidr.modulemap.symbols.ModuleMapModuleSymbol;
import kotlin.collections.MapsKt;
import kotlin.collections.CollectionsKt;
import com.jetbrains.cidr.modulemap.resolve.ModuleMapPathResolver;
import kotlin.Metadata;

@Metadata(mv = { 1, 1, 7 }, bv = { 1, 0, 2 }, k = 1, d1 = { "\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0006\u0010\u0003\u001a\u00020\u0004¨\u0006\u0005" }, d2 = { "Lcom/jetbrains/cidr/modulemap/symbols/impl/ModuleMapModuleSymbolImpl$Companion;", "", "()V", "createUninitialized", "Lcom/jetbrains/cidr/modulemap/symbols/impl/ModuleMapModuleSymbolImpl;", "cidr-lang" })
public static final class Companion
{
    @NotNull
    public final ModuleMapModuleSymbolImpl createUninitialized() {
        return new ModuleMapModuleSymbolImpl("", null, ModuleMapPathResolver.Companion.getNULL(), null, null, CollectionsKt.emptyList(), CollectionsKt.emptyList(), CollectionsKt.emptyList(), MapsKt.emptyMap(), false, false, false);
    }
}
