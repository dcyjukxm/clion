// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.modulemap.symbols;

import kotlin.jvm.internal.DefaultConstructorMarker;
import org.jetbrains.annotations.NotNull;
import kotlin.Metadata;

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
