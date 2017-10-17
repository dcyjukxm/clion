// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.modulemap.symbols.impl;

import kotlin.collections.MapsKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import java.util.Iterator;
import java.util.Set;
import java.util.HashSet;
import com.intellij.util.containers.ContainerUtil;
import java.util.Collections;
import java.util.Collection;
import org.jetbrains.annotations.Nullable;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import com.jetbrains.cidr.modulemap.symbols.ModuleMapModuleSymbol;
import java.util.Map;
import kotlin.Metadata;
import com.jetbrains.cidr.lang.symbols.DeepEqual;
import com.jetbrains.cidr.modulemap.symbols.ModuleMapFileSymbol;

@Metadata(mv = { 1, 1, 7 }, bv = { 1, 0, 2 }, k = 1, d1 = { "\u00004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010$\n\u0002\u0010\u000e\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u001e\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0007\b\u0016\u0018\u0000 \u00152\u00020\u00012\b\u0012\u0004\u0012\u00020\u00000\u0002:\u0001\u0015B\u0019\u0012\u0012\u0010\u0003\u001a\u000e\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\u00060\u0004¢\u0006\u0002\u0010\u0007J \u0010\f\u001a\u00020\r2\u0006\u0010\u000e\u001a\u00020\u000f2\u0006\u0010\u0010\u001a\u00020\u00002\u0006\u0010\u0011\u001a\u00020\u0000H\u0016J\u0012\u0010\u0012\u001a\u0004\u0018\u00010\u00062\u0006\u0010\u0013\u001a\u00020\u0005H\u0016J\u000e\u0010\u0014\u001a\b\u0012\u0004\u0012\u00020\u00060\tH\u0016R\u001a\u0010\b\u001a\b\u0012\u0004\u0012\u00020\u00060\t8VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\n\u0010\u000bR\u001a\u0010\u0003\u001a\u000e\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\u00060\u0004X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u0016" }, d2 = { "Lcom/jetbrains/cidr/modulemap/symbols/impl/ModuleMapFileSymbolImpl;", "Lcom/jetbrains/cidr/modulemap/symbols/ModuleMapFileSymbol;", "Lcom/jetbrains/cidr/lang/symbols/DeepEqual$Equality;", "myModuleDeclarationSymbolMap", "", "", "Lcom/jetbrains/cidr/modulemap/symbols/ModuleMapModuleSymbol;", "(Ljava/util/Map;)V", "modules", "", "getModules", "()Ljava/util/Collection;", "deepEqualStep", "", "c", "Lcom/jetbrains/cidr/lang/symbols/DeepEqual$Comparator;", "first", "second", "findModule", "name", "getAllModulesRecursively", "Companion", "cidr-lang" })
public class ModuleMapFileSymbolImpl implements ModuleMapFileSymbol, Equality<ModuleMapFileSymbolImpl>
{
    private final Map<String, ModuleMapModuleSymbol> myModuleDeclarationSymbolMap;
    public static final Companion Companion;
    
    @Nullable
    @Override
    public ModuleMapModuleSymbol findModule(@NotNull final String s) {
        Intrinsics.checkParameterIsNotNull((Object)s, "name");
        return this.myModuleDeclarationSymbolMap.get(s);
    }
    
    @NotNull
    @Override
    public Collection<ModuleMapModuleSymbol> getModules() {
        final Collection<Object> unmodifiableCollection = (Collection<Object>)Collections.unmodifiableCollection((Collection<? extends ModuleMapModuleSymbol>)this.myModuleDeclarationSymbolMap.values());
        Intrinsics.checkExpressionValueIsNotNull((Object)unmodifiableCollection, "Collections.unmodifiable\u2026larationSymbolMap.values)");
        return (Collection<ModuleMapModuleSymbol>)unmodifiableCollection;
    }
    
    @NotNull
    @Override
    public Collection<ModuleMapModuleSymbol> getAllModulesRecursively() {
        final HashSet hashSet = ContainerUtil.newHashSet();
        for (final ModuleMapModuleSymbol moduleMapModuleSymbol : this.getModules()) {
            hashSet.add(moduleMapModuleSymbol);
            final Companion companion = ModuleMapFileSymbolImpl.Companion;
            final HashSet<ModuleMapModuleSymbol> set = (HashSet<ModuleMapModuleSymbol>)hashSet;
            Intrinsics.checkExpressionValueIsNotNull((Object)set, "result");
            companion.a(set, moduleMapModuleSymbol);
        }
        final Collection<Object> unmodifiableCollection = (Collection<Object>)Collections.unmodifiableCollection((Collection<? extends ModuleMapModuleSymbol>)hashSet);
        Intrinsics.checkExpressionValueIsNotNull((Object)unmodifiableCollection, "Collections.unmodifiableCollection(result)");
        return (Collection<ModuleMapModuleSymbol>)unmodifiableCollection;
    }
    
    public boolean deepEqualStep(@NotNull final Comparator comparator, @NotNull final ModuleMapFileSymbolImpl moduleMapFileSymbolImpl, @NotNull final ModuleMapFileSymbolImpl moduleMapFileSymbolImpl2) {
        Intrinsics.checkParameterIsNotNull((Object)comparator, "c");
        Intrinsics.checkParameterIsNotNull((Object)moduleMapFileSymbolImpl, "first");
        Intrinsics.checkParameterIsNotNull((Object)moduleMapFileSymbolImpl2, "second");
        return comparator.equalMaps(moduleMapFileSymbolImpl.myModuleDeclarationSymbolMap, moduleMapFileSymbolImpl2.myModuleDeclarationSymbolMap);
    }
    
    public ModuleMapFileSymbolImpl(@NotNull final Map<String, ? extends ModuleMapModuleSymbol> myModuleDeclarationSymbolMap) {
        Intrinsics.checkParameterIsNotNull((Object)myModuleDeclarationSymbolMap, "myModuleDeclarationSymbolMap");
        this.myModuleDeclarationSymbolMap = (Map<String, ModuleMapModuleSymbol>)myModuleDeclarationSymbolMap;
    }
    
    static {
        Companion = new Companion(null);
    }
    
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
}
