// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.modulemap.symbols;

import org.jetbrains.annotations.NotNull;
import kotlin.Metadata;
import com.jetbrains.cidr.modulemap.resolve.ModuleMapDefaultPathResolver;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Lambda;

@Metadata(mv = { 1, 1, 7 }, bv = { 1, 0, 2 }, k = 3, d1 = { "\u0000\b\n\u0000\n\u0002\u0018\u0002\n\u0000\u0010\u0000\u001a\u00020\u0001H\n¢\u0006\u0002\b\u0002" }, d2 = { "<anonymous>", "Lcom/jetbrains/cidr/modulemap/resolve/ModuleMapDefaultPathResolver;", "invoke" })
static final class ModuleMapSerializer$registerSerializers$4 extends Lambda implements Function0<ModuleMapDefaultPathResolver> {
    public static final ModuleMapSerializer$registerSerializers$4 INSTANCE;
    
    @NotNull
    public final ModuleMapDefaultPathResolver invoke() {
        return ModuleMapDefaultPathResolver.Companion.createUninitialized();
    }
    
    static {
        ModuleMapSerializer$registerSerializers$4.INSTANCE = new ModuleMapSerializer$registerSerializers$4();
    }
}