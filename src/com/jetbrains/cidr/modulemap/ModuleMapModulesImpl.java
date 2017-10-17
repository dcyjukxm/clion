// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.modulemap;

import java.util.HashMap;
import java.util.Collections;
import org.jetbrains.annotations.Nullable;
import java.util.Iterator;
import com.intellij.util.containers.ContainerUtil;
import com.intellij.util.containers.Convertor;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import java.util.Collection;
import com.jetbrains.cidr.modulemap.symbols.ModuleMapModuleSymbol;
import java.util.Map;
import kotlin.Metadata;

@Metadata(mv = { 1, 1, 7 }, bv = { 1, 0, 2 }, k = 1, d1 = { "\u0000.\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u001e\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010%\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0004\n\u0002\u0010\u000b\n\u0000\u0018\u00002\u00020\u0001B\u0015\b\u0016\u0012\f\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003¢\u0006\u0002\u0010\u0005B\u0005¢\u0006\u0002\u0010\u0006J\u0016\u0010\n\u001a\u00020\u000b2\f\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003H\u0016J\u0012\u0010\f\u001a\u0004\u0018\u00010\u00042\u0006\u0010\r\u001a\u00020\tH\u0016J\u000e\u0010\u000e\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003H\u0016J\b\u0010\u000f\u001a\u00020\u0010H\u0016R\u001a\u0010\u0007\u001a\u000e\u0012\u0004\u0012\u00020\t\u0012\u0004\u0012\u00020\u00040\bX\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u0011" }, d2 = { "Lcom/jetbrains/cidr/modulemap/ModuleMapModulesImpl;", "Lcom/jetbrains/cidr/modulemap/ModuleMapModules;", "newModules", "", "Lcom/jetbrains/cidr/modulemap/symbols/ModuleMapModuleSymbol;", "(Ljava/util/Collection;)V", "()V", "modules", "", "", "add", "", "getModule", "name", "getModules", "isEmpty", "", "cidr-lang" })
public final class ModuleMapModulesImpl implements ModuleMapModules
{
    private final Map<String, ModuleMapModuleSymbol> modules;
    
    @Override
    public void add(@NotNull final Collection<? extends ModuleMapModuleSymbol> collection) {
        Intrinsics.checkParameterIsNotNull((Object)collection, "newModules");
        final Map<String, ModuleMapModuleSymbol> modules = this.modules;
        final Iterator<? extends ModuleMapModuleSymbol> iterator = collection.iterator();
        ModuleMapModulesImplKt$sam$Convertor$ae6b2be9 moduleMapModulesImplKt$sam$Convertor$ae6b2be9;
        final Function1 function1 = (Function1)(moduleMapModulesImplKt$sam$Convertor$ae6b2be9 = (ModuleMapModulesImplKt$sam$Convertor$ae6b2be9)ModuleMapModulesImpl$add$1.INSTANCE);
        if (function1 != null) {
            moduleMapModulesImplKt$sam$Convertor$ae6b2be9 = new ModuleMapModulesImplKt$sam$Convertor$ae6b2be9(function1);
        }
        final Map mapFromValues = ContainerUtil.newMapFromValues((Iterator)iterator, (Convertor)moduleMapModulesImplKt$sam$Convertor$ae6b2be9);
        Intrinsics.checkExpressionValueIsNotNull((Object)mapFromValues, "ContainerUtil.newMapFrom\u2026uleSymbol::qualifiedName)");
        modules.putAll(mapFromValues);
    }
    
    @Nullable
    @Override
    public ModuleMapModuleSymbol getModule(@NotNull final String s) {
        Intrinsics.checkParameterIsNotNull((Object)s, "name");
        return this.modules.get(s);
    }
    
    @Override
    public boolean isEmpty() {
        return this.modules.isEmpty();
    }
    
    @NotNull
    @Override
    public Collection<ModuleMapModuleSymbol> getModules() {
        final Collection<Object> unmodifiableCollection = (Collection<Object>)Collections.unmodifiableCollection((Collection<? extends ModuleMapModuleSymbol>)this.modules.values());
        Intrinsics.checkExpressionValueIsNotNull((Object)unmodifiableCollection, "Collections.unmodifiableCollection(modules.values)");
        return (Collection<ModuleMapModuleSymbol>)unmodifiableCollection;
    }
    
    public ModuleMapModulesImpl() {
        final HashMap hashMap = ContainerUtil.newHashMap();
        Intrinsics.checkExpressionValueIsNotNull((Object)hashMap, "ContainerUtil.newHashMap()");
        this.modules = (HashMap<String, ModuleMapModuleSymbol>)hashMap;
    }
    
    public ModuleMapModulesImpl(@NotNull final Collection<? extends ModuleMapModuleSymbol> collection) {
        Intrinsics.checkParameterIsNotNull((Object)collection, "newModules");
        this();
        this.add(collection);
    }
}
