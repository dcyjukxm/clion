// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.modulemap.symbols.impl;

import kotlin.jvm.internal.DefaultConstructorMarker;
import java.util.Iterator;
import java.util.Set;
import org.jetbrains.annotations.NotNull;
import com.jetbrains.cidr.modulemap.symbols.ModuleMapModuleSymbol;
import java.util.Map;
import kotlin.collections.MapsKt;
import kotlin.Metadata;

@Metadata(mv = { 1, 1, 7 }, bv = { 1, 0, 2 }, k = 1, d1 = { "\u0000$\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010#\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0006\u0010\u0003\u001a\u00020\u0004J\u001e\u0010\u0005\u001a\u00020\u00062\f\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\t0\b2\u0006\u0010\n\u001a\u00020\tH\u0002¨\u0006\u000b" }, d2 = { "Lcom/jetbrains/cidr/modulemap/symbols/impl/ModuleMapFileSymbolImpl$Companion;", "", "()V", "createUninitialized", "Lcom/jetbrains/cidr/modulemap/symbols/impl/ModuleMapFileSymbolImpl;", "doGetSubmodules", "", "result", "", "Lcom/jetbrains/cidr/modulemap/symbols/ModuleMapModuleSymbol;", "symbol", "cidr-lang" })
public static final class Companion
{
    @NotNull
    public final ModuleMapFileSymbolImpl createUninitialized() {
        return new ModuleMapFileSymbolImpl(MapsKt.emptyMap());
    }
    
    private final void a(final Set<ModuleMapModuleSymbol> set, final ModuleMapModuleSymbol moduleMapModuleSymbol) {
        for (final ModuleMapModuleSymbol moduleMapModuleSymbol2 : moduleMapModuleSymbol.getSubModules().values()) {
            if (set.add(moduleMapModuleSymbol2)) {
                this.a(set, moduleMapModuleSymbol2);
            }
        }
    }
}
