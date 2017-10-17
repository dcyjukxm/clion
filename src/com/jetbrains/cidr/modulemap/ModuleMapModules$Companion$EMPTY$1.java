// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.modulemap;

import kotlin.collections.SetsKt;
import java.util.Set;
import org.jetbrains.annotations.Nullable;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import com.jetbrains.cidr.modulemap.symbols.ModuleMapModuleSymbol;
import java.util.Collection;
import kotlin.Metadata;

@Metadata(mv = { 1, 1, 7 }, bv = { 1, 0, 2 }, k = 1, d1 = { "\u0000-\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u001e\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0000*\u0001\u0000\b\n\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\u0016\u0010\u0003\u001a\u00020\u00042\f\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00070\u0006H\u0016J\u0012\u0010\b\u001a\u0004\u0018\u00010\u00072\u0006\u0010\t\u001a\u00020\nH\u0016J\u000e\u0010\u000b\u001a\b\u0012\u0004\u0012\u00020\u00070\u0006H\u0016J\b\u0010\f\u001a\u00020\rH\u0016¨\u0006\u000e" }, d2 = { "com/jetbrains/cidr/modulemap/ModuleMapModules$Companion$EMPTY$1", "Lcom/jetbrains/cidr/modulemap/ModuleMapModules;", "()V", "add", "", "newModules", "", "Lcom/jetbrains/cidr/modulemap/symbols/ModuleMapModuleSymbol;", "getModule", "name", "", "getModules", "isEmpty", "", "cidr-lang" })
public static final class ModuleMapModules$Companion$EMPTY$1 implements ModuleMapModules {
    @Override
    public void add(@NotNull final Collection<? extends ModuleMapModuleSymbol> collection) {
        Intrinsics.checkParameterIsNotNull((Object)collection, "newModules");
        throw new UnsupportedOperationException();
    }
    
    @Nullable
    @Override
    public ModuleMapModuleSymbol getModule(@NotNull final String s) {
        Intrinsics.checkParameterIsNotNull((Object)s, "name");
        return null;
    }
    
    @Override
    public boolean isEmpty() {
        return true;
    }
    
    @NotNull
    @Override
    public Collection<ModuleMapModuleSymbol> getModules() {
        return (Collection<ModuleMapModuleSymbol>)SetsKt.emptySet();
    }
}