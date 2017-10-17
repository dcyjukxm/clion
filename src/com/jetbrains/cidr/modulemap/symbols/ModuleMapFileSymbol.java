// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.modulemap.symbols;

import kotlin.jvm.internal.DefaultConstructorMarker;
import java.util.Collection;
import org.jetbrains.annotations.Nullable;
import org.jetbrains.annotations.NotNull;
import kotlin.Metadata;

@Metadata(mv = { 1, 1, 7 }, bv = { 1, 0, 2 }, k = 1, d1 = { "\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u001e\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u000e\n\u0002\b\u0003\bf\u0018\u0000 \u000b2\u00020\u0001:\u0001\u000bJ\u0012\u0010\u0007\u001a\u0004\u0018\u00010\u00042\u0006\u0010\b\u001a\u00020\tH&J\u000e\u0010\n\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003H&R\u001a\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u00038fX¦\u0004¢\u0006\u0006\u001a\u0004\b\u0005\u0010\u0006¨\u0006\f" }, d2 = { "Lcom/jetbrains/cidr/modulemap/symbols/ModuleMapFileSymbol;", "Lcom/jetbrains/cidr/modulemap/symbols/ModuleMapSymbol;", "modules", "", "Lcom/jetbrains/cidr/modulemap/symbols/ModuleMapModuleSymbol;", "getModules", "()Ljava/util/Collection;", "findModule", "name", "", "getAllModulesRecursively", "Companion", "cidr-lang" })
public interface ModuleMapFileSymbol extends ModuleMapSymbol
{
    public static final Companion Companion = new Companion(null);
    
    @Nullable
    ModuleMapModuleSymbol findModule(@NotNull final String p0);
    
    @NotNull
    Collection<ModuleMapModuleSymbol> getModules();
    
    @NotNull
    Collection<ModuleMapModuleSymbol> getAllModulesRecursively();
    
    @Metadata(mv = { 1, 1, 7 }, bv = { 1, 0, 2 }, k = 1, d1 = { "\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u0011\u0010\u0003\u001a\u00020\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006¨\u0006\u0007" }, d2 = { "Lcom/jetbrains/cidr/modulemap/symbols/ModuleMapFileSymbol$Companion;", "", "()V", "EMPTY", "Lcom/jetbrains/cidr/modulemap/symbols/ModuleMapFileSymbol;", "getEMPTY", "()Lcom/jetbrains/cidr/modulemap/symbols/ModuleMapFileSymbol;", "cidr-lang" })
    public static final class Companion
    {
        @NotNull
        private static final ModuleMapFileSymbol EMPTY;
        
        @NotNull
        public final ModuleMapFileSymbol getEMPTY() {
            return Companion.EMPTY;
        }
        
        private Companion() {
            EMPTY = (ModuleMapFileSymbol)new ModuleMapFileSymbol$Companion$EMPTY.ModuleMapFileSymbol$Companion$EMPTY$1();
        }
    }
}
