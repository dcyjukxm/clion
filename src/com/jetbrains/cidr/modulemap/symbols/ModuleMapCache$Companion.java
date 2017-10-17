// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.modulemap.symbols;

import kotlin.jvm.internal.DefaultConstructorMarker;
import com.intellij.openapi.components.ServiceManager;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import com.intellij.openapi.project.Project;
import org.jetbrains.annotations.Nullable;
import kotlin.jvm.JvmStatic;
import kotlin.Metadata;

@Metadata(mv = { 1, 1, 7 }, bv = { 1, 0, 2 }, k = 1, d1 = { "\u0000(\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\t\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0005\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0010\u0010\r\u001a\u00020\u000e2\u0006\u0010\u000f\u001a\u00020\u0010H\u0007J\u0017\u0010\u0011\u001a\u00020\u00122\b\u0010\u0013\u001a\u0004\u0018\u00010\u0004H\u0007¢\u0006\u0002\u0010\bJ\u0017\u0010\u0014\u001a\u00020\u00122\b\u0010\u0013\u001a\u0004\u0018\u00010\u0004H\u0007¢\u0006\u0002\u0010\bJ\b\u0010\u0015\u001a\u00020\u0004H\u0007J\b\u0010\u0016\u001a\u00020\u0004H\u0007R\u001e\u0010\u0003\u001a\u0004\u0018\u00010\u0004X\u0082\u000e¢\u0006\u0010\n\u0002\u0010\t\u001a\u0004\b\u0005\u0010\u0006\"\u0004\b\u0007\u0010\bR\u001e\u0010\n\u001a\u0004\u0018\u00010\u0004X\u0082\u000e¢\u0006\u0010\n\u0002\u0010\t\u001a\u0004\b\u000b\u0010\u0006\"\u0004\b\f\u0010\b¨\u0006\u0017" }, d2 = { "Lcom/jetbrains/cidr/modulemap/symbols/ModuleMapCache$Companion;", "", "()V", "ourShouldBuildCache", "", "getOurShouldBuildCache", "()Ljava/lang/Boolean;", "setOurShouldBuildCache", "(Ljava/lang/Boolean;)V", "Ljava/lang/Boolean;", "ourShouldReloadCache", "getOurShouldReloadCache", "setOurShouldReloadCache", "getInstance", "Lcom/jetbrains/cidr/modulemap/symbols/ModuleMapCache;", "project", "Lcom/intellij/openapi/project/Project;", "setShouldBuildCache", "", "value", "setShouldReloadCache", "shouldBuildCache", "shouldReloadCache", "cidr-lang" })
public static final class Companion
{
    private final Boolean b() {
        return ModuleMapCache.access$getOurShouldBuildCache$cp();
    }
    
    private final void b(final Boolean b) {
        ModuleMapCache.access$setOurShouldBuildCache$cp(b);
    }
    
    @JvmStatic
    public final boolean shouldBuildCache() {
        final Boolean b = this.b();
        return b == null || b;
    }
    
    @JvmStatic
    public final void setShouldBuildCache(@Nullable final Boolean b) {
        this.b(b);
    }
    
    private final Boolean a() {
        return ModuleMapCache.access$getOurShouldReloadCache$cp();
    }
    
    private final void a(final Boolean b) {
        ModuleMapCache.access$setOurShouldReloadCache$cp(b);
    }
    
    @JvmStatic
    public final boolean shouldReloadCache() {
        final Boolean a = this.a();
        return a == null || a;
    }
    
    @JvmStatic
    public final void setShouldReloadCache(@Nullable final Boolean b) {
        this.a(b);
    }
    
    @JvmStatic
    @NotNull
    public final ModuleMapCache getInstance(@NotNull final Project project) {
        Intrinsics.checkParameterIsNotNull((Object)project, "project");
        final Object service = ServiceManager.getService(project, (Class)ModuleMapCache.class);
        Intrinsics.checkExpressionValueIsNotNull(service, "ServiceManager.getServic\u2026duleMapCache::class.java)");
        return (ModuleMapCache)service;
    }
}
