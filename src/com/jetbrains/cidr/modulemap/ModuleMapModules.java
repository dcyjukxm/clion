// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.modulemap;

import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.DefaultConstructorMarker;
import org.jetbrains.annotations.Nullable;
import org.jetbrains.annotations.NotNull;
import com.jetbrains.cidr.modulemap.symbols.ModuleMapModuleSymbol;
import java.util.Collection;
import kotlin.Metadata;

@Metadata(mv = { 1, 1, 7 }, bv = { 1, 0, 2 }, k = 1, d1 = { "\u0000,\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u001e\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0002\bf\u0018\u0000 \r2\u00020\u0001:\u0001\rJ\u0016\u0010\u0002\u001a\u00020\u00032\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005H&J\u0012\u0010\u0007\u001a\u0004\u0018\u00010\u00062\u0006\u0010\b\u001a\u00020\tH&J\u000e\u0010\n\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005H&J\b\u0010\u000b\u001a\u00020\fH&¨\u0006\u000e" }, d2 = { "Lcom/jetbrains/cidr/modulemap/ModuleMapModules;", "", "add", "", "newModules", "", "Lcom/jetbrains/cidr/modulemap/symbols/ModuleMapModuleSymbol;", "getModule", "name", "", "getModules", "isEmpty", "", "Companion", "cidr-lang" })
public interface ModuleMapModules
{
    public static final Companion Companion = new Companion(null);
    
    void add(@NotNull final Collection<? extends ModuleMapModuleSymbol> p0);
    
    @Nullable
    ModuleMapModuleSymbol getModule(@NotNull final String p0);
    
    boolean isEmpty();
    
    @NotNull
    Collection<ModuleMapModuleSymbol> getModules();
    
    @Metadata(mv = { 1, 1, 7 }, bv = { 1, 0, 2 }, k = 1, d1 = { "\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u001e\n\u0002\u0018\u0002\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0014\u0010\u0007\u001a\u00020\u00042\f\u0010\b\u001a\b\u0012\u0004\u0012\u00020\n0\tR\u0011\u0010\u0003\u001a\u00020\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006¨\u0006\u000b" }, d2 = { "Lcom/jetbrains/cidr/modulemap/ModuleMapModules$Companion;", "", "()V", "EMPTY", "Lcom/jetbrains/cidr/modulemap/ModuleMapModules;", "getEMPTY", "()Lcom/jetbrains/cidr/modulemap/ModuleMapModules;", "create", "modules", "", "Lcom/jetbrains/cidr/modulemap/symbols/ModuleMapModuleSymbol;", "cidr-lang" })
    public static final class Companion
    {
        @NotNull
        private static final ModuleMapModules EMPTY;
        
        @NotNull
        public final ModuleMapModules getEMPTY() {
            return Companion.EMPTY;
        }
        
        @NotNull
        public final ModuleMapModules create(@NotNull final Collection<? extends ModuleMapModuleSymbol> collection) {
            Intrinsics.checkParameterIsNotNull((Object)collection, "modules");
            if (collection.isEmpty()) {
                return this.getEMPTY();
            }
            return new ModuleMapModulesImpl(collection);
        }
        
        private Companion() {
            EMPTY = (ModuleMapModules)new ModuleMapModules$Companion$EMPTY.ModuleMapModules$Companion$EMPTY$1();
        }
    }
}
