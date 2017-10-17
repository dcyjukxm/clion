// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.modulemap.symbols;

import kotlin.jvm.JvmStatic;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import kotlin.Metadata;

@Metadata(mv = { 1, 1, 7 }, bv = { 1, 0, 2 }, k = 1, d1 = { "\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\b\u0002\u0018\u0000 \u00072\u00020\u0001:\u0001\u0007B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006¨\u0006\b" }, d2 = { "Lcom/jetbrains/cidr/modulemap/symbols/ModuleMapSerializer$ModuleMapWrapper;", "", "moduleMap", "Lcom/jetbrains/cidr/modulemap/symbols/ModuleMapFileSymbol;", "(Lcom/jetbrains/cidr/modulemap/symbols/ModuleMapFileSymbol;)V", "getModuleMap", "()Lcom/jetbrains/cidr/modulemap/symbols/ModuleMapFileSymbol;", "Companion", "cidr-lang" })
private static final class ModuleMapWrapper
{
    @NotNull
    private final ModuleMapFileSymbol moduleMap;
    public static final Companion Companion;
    
    @NotNull
    public final ModuleMapFileSymbol getModuleMap() {
        return this.moduleMap;
    }
    
    public ModuleMapWrapper(@NotNull final ModuleMapFileSymbol moduleMap) {
        Intrinsics.checkParameterIsNotNull((Object)moduleMap, "moduleMap");
        this.moduleMap = moduleMap;
    }
    
    static {
        Companion = new Companion(null);
    }
    
    @JvmStatic
    @NotNull
    public static final ModuleMapWrapper createUninitialized() {
        return ModuleMapWrapper.Companion.createUninitialized();
    }
    
    @Metadata(mv = { 1, 1, 7 }, bv = { 1, 0, 2 }, k = 1, d1 = { "\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\b\u0010\u0003\u001a\u00020\u0004H\u0007¨\u0006\u0005" }, d2 = { "Lcom/jetbrains/cidr/modulemap/symbols/ModuleMapSerializer$ModuleMapWrapper$Companion;", "", "()V", "createUninitialized", "Lcom/jetbrains/cidr/modulemap/symbols/ModuleMapSerializer$ModuleMapWrapper;", "cidr-lang" })
    public static final class Companion
    {
        @JvmStatic
        @NotNull
        public final ModuleMapWrapper createUninitialized() {
            return new ModuleMapWrapper(ModuleMapFileSymbol.Companion.getEMPTY());
        }
    }
}
