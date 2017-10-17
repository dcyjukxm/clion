// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.modulemap.symbols;

import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.collections.CollectionsKt;
import java.util.List;
import org.jetbrains.annotations.NotNull;
import kotlin.Metadata;

@Metadata(mv = { 1, 1, 7 }, bv = { 1, 0, 2 }, k = 1, d1 = { "\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010 \n\u0002\u0010\u000e\n\u0002\b\u0006\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u0017\u0010\u0003\u001a\b\u0012\u0004\u0012\u00020\u00050\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007R\u0014\u0010\b\u001a\u00020\u0005X\u0086D¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\n¨\u0006\u000b" }, d2 = { "Lcom/jetbrains/cidr/modulemap/symbols/ModuleMapModuleSymbol$Companion;", "", "()V", "MODULE_EXPORT_WILDCARD", "", "", "getMODULE_EXPORT_WILDCARD", "()Ljava/util/List;", "MODULE_ID_WILDCARD", "getMODULE_ID_WILDCARD", "()Ljava/lang/String;", "cidr-lang" })
public static final class Companion
{
    @NotNull
    private static final String MODULE_ID_WILDCARD = "*";
    @NotNull
    private static final List<String> MODULE_EXPORT_WILDCARD;
    
    @NotNull
    public final String getMODULE_ID_WILDCARD() {
        return Companion.MODULE_ID_WILDCARD;
    }
    
    @NotNull
    public final List<String> getMODULE_EXPORT_WILDCARD() {
        return Companion.MODULE_EXPORT_WILDCARD;
    }
    
    private Companion() {
        MODULE_ID_WILDCARD = "*";
        MODULE_EXPORT_WILDCARD = CollectionsKt.listOf((Object)this.getMODULE_ID_WILDCARD());
    }
}
