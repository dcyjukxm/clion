// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.modulemap.symbols;

import kotlin.collections.CollectionsKt;
import java.util.Collection;
import org.jetbrains.annotations.Nullable;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import java.util.List;
import kotlin.Metadata;

@Metadata(mv = { 1, 1, 7 }, bv = { 1, 0, 2 }, k = 1, d1 = { "\u0000'\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0001\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002*\u0001\u0000\b\n\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\u0012\u0010\b\u001a\u0004\u0018\u00010\t2\u0006\u0010\n\u001a\u00020\u000bH\u0016J\u000e\u0010\f\u001a\b\u0012\u0004\u0012\u00020\u00050\u0004H\u0016R\u001a\u0010\u0003\u001a\b\u0012\u0004\u0012\u00020\u00050\u0004X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007¨\u0006\r" }, d2 = { "com/jetbrains/cidr/modulemap/symbols/ModuleMapFileSymbol$Companion$EMPTY$1", "Lcom/jetbrains/cidr/modulemap/symbols/ModuleMapFileSymbol;", "()V", "modules", "", "Lcom/jetbrains/cidr/modulemap/symbols/ModuleMapModuleSymbol;", "getModules", "()Ljava/util/List;", "findModule", "", "name", "", "getAllModulesRecursively", "cidr-lang" })
public static final class ModuleMapFileSymbol$Companion$EMPTY$1 implements ModuleMapFileSymbol {
    @NotNull
    private final List<ModuleMapModuleSymbol> modules = CollectionsKt.emptyList();
    
    @Nullable
    public Void findModule(@NotNull final String s) {
        Intrinsics.checkParameterIsNotNull((Object)s, "name");
        return null;
    }
    
    @NotNull
    @Override
    public List<ModuleMapModuleSymbol> getModules() {
        return (List<ModuleMapModuleSymbol>)this.modules;
    }
    
    @NotNull
    @Override
    public List<ModuleMapModuleSymbol> getAllModulesRecursively() {
        return (List<ModuleMapModuleSymbol>)CollectionsKt.emptyList();
    }
}