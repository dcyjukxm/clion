// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.modulemap.symbols;

import com.intellij.openapi.components.ServiceManager;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.internal.DefaultConstructorMarker;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.Iterator;
import com.jetbrains.cidr.lang.workspace.OCWorkspaceManager;
import com.jetbrains.cidr.lang.workspace.OCResolveConfiguration;
import java.util.List;
import com.intellij.util.containers.ContainerUtil;
import com.jetbrains.cidr.modulemap.resolve.ModuleMapManager;
import org.jetbrains.annotations.Nullable;
import com.intellij.util.Consumer;
import com.intellij.util.Producer;
import java.util.Collection;
import com.jetbrains.cidr.lang.workspace.headerRoots.HeadersSearchRoot;
import java.util.HashSet;
import kotlin.jvm.internal.Intrinsics;
import com.jetbrains.cidr.lang.symbols.symtable.OCSymbolTablesBuildingActivity;
import org.jetbrains.annotations.NotNull;
import com.intellij.openapi.progress.ProgressIndicator;
import com.intellij.openapi.project.Project;
import kotlin.Metadata;

@Metadata(mv = { 1, 1, 7 }, bv = { 1, 0, 2 }, k = 1, d1 = { "\u0000<\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u001e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u0018\u0000 \u00122\u00020\u0001:\u0001\u0012B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\u000e\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00070\u0006H\u0002J\u0014\u0010\b\u001a\b\u0012\u0002\b\u0003\u0018\u00010\t2\u0006\u0010\n\u001a\u00020\u000bJ$\u0010\f\u001a\b\u0012\u0004\u0012\u00020\u00070\r2\f\u0010\u000e\u001a\b\u0012\u0004\u0012\u00020\u00070\u000f2\u0006\u0010\n\u001a\u00020\u000bH\u0002J\u001e\u0010\u0010\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u00070\u00112\f\u0010\u000e\u001a\b\u0012\u0004\u0012\u00020\u00070\u0006H\u0002R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u0013" }, d2 = { "Lcom/jetbrains/cidr/modulemap/symbols/ModuleMapCacheBuilder;", "", "project", "Lcom/intellij/openapi/project/Project;", "(Lcom/intellij/openapi/project/Project;)V", "getHeaderRoots", "Ljava/util/HashSet;", "Lcom/jetbrains/cidr/lang/workspace/headerRoots/HeadersSearchRoot;", "getTask", "Lcom/jetbrains/cidr/lang/symbols/symtable/OCSymbolTablesBuildingActivity$TaskProvider;", "indicator", "Lcom/intellij/openapi/progress/ProgressIndicator;", "makeConsumer", "Lcom/intellij/util/Consumer;", "items", "", "makeProducer", "Lcom/intellij/util/Producer;", "Companion", "cidr-lang" })
public final class ModuleMapCacheBuilder
{
    private final Project project;
    public static final Companion Companion;
    
    @Nullable
    public final OCSymbolTablesBuildingActivity.TaskProvider<?> getTask(@NotNull final ProgressIndicator progressIndicator) {
        Intrinsics.checkParameterIsNotNull((Object)progressIndicator, "indicator");
        final HashSet<HeadersSearchRoot> a = this.a();
        if (a.isEmpty()) {
            return null;
        }
        return (OCSymbolTablesBuildingActivity.TaskProvider<?>)new ModuleMapCacheBuilder$getTask.ModuleMapCacheBuilder$getTask$1((Producer)this.a(a), (Consumer)this.a(a, progressIndicator));
    }
    
    private final HashSet<HeadersSearchRoot> a() {
        final ModuleMapManager instance = ModuleMapManager.Companion.getInstance(this.project);
        final HashSet hashSet = ContainerUtil.newHashSet();
        for (final OCResolveConfiguration ocResolveConfiguration : OCWorkspaceManager.getWorkspace(this.project).getConfigurations()) {
            final HashSet set = hashSet;
            final ModuleMapManager moduleMapManager = instance;
            final OCResolveConfiguration ocResolveConfiguration2 = ocResolveConfiguration;
            Intrinsics.checkExpressionValueIsNotNull((Object)ocResolveConfiguration2, "it");
            set.addAll(moduleMapManager.getHeaderSearchRoots(ocResolveConfiguration2));
        }
        final HashSet set2 = hashSet;
        Intrinsics.checkExpressionValueIsNotNull((Object)set2, "items");
        return (HashSet<HeadersSearchRoot>)set2;
    }
    
    private final Producer<HeadersSearchRoot> a(final HashSet<HeadersSearchRoot> set) {
        return (Producer<HeadersSearchRoot>)new ModuleMapCacheBuilder$makeProducer.ModuleMapCacheBuilder$makeProducer$1(ContainerUtil.newArrayList((Iterable)set));
    }
    
    private final Consumer<HeadersSearchRoot> a(final Collection<? extends HeadersSearchRoot> collection, final ProgressIndicator progressIndicator) {
        return (Consumer<HeadersSearchRoot>)new ModuleMapCacheBuilder$makeConsumer$consumer.ModuleMapCacheBuilder$makeConsumer$consumer$1(progressIndicator, new AtomicInteger(0), new AtomicInteger(collection.size()));
    }
    
    public ModuleMapCacheBuilder(@NotNull final Project project) {
        Intrinsics.checkParameterIsNotNull((Object)project, "project");
        this.project = project;
    }
    
    static {
        Companion = new Companion(null);
    }
    
    @JvmStatic
    @NotNull
    public static final ModuleMapCacheBuilder getInstance(@NotNull final Project project) {
        Intrinsics.checkParameterIsNotNull((Object)project, "project");
        return ModuleMapCacheBuilder.Companion.getInstance(project);
    }
    
    @Metadata(mv = { 1, 1, 7 }, bv = { 1, 0, 2 }, k = 1, d1 = { "\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0010\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0006H\u0007¨\u0006\u0007" }, d2 = { "Lcom/jetbrains/cidr/modulemap/symbols/ModuleMapCacheBuilder$Companion;", "", "()V", "getInstance", "Lcom/jetbrains/cidr/modulemap/symbols/ModuleMapCacheBuilder;", "project", "Lcom/intellij/openapi/project/Project;", "cidr-lang" })
    public static final class Companion
    {
        @JvmStatic
        @NotNull
        public final ModuleMapCacheBuilder getInstance(@NotNull final Project project) {
            Intrinsics.checkParameterIsNotNull((Object)project, "project");
            final Object service = ServiceManager.getService(project, (Class)ModuleMapCacheBuilder.class);
            Intrinsics.checkExpressionValueIsNotNull(service, "ServiceManager.getServic\u2026CacheBuilder::class.java)");
            return (ModuleMapCacheBuilder)service;
        }
    }
}
