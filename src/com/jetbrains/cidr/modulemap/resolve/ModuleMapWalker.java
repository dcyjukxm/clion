// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.modulemap.resolve;

import kotlin.jvm.internal.DefaultConstructorMarker;
import com.intellij.openapi.project.Project;
import java.util.Iterator;
import java.util.List;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Set;
import java.util.HashSet;
import com.intellij.util.containers.ContainerUtil;
import kotlin.jvm.internal.Intrinsics;
import com.jetbrains.cidr.lang.workspace.OCResolveConfiguration;
import org.jetbrains.annotations.NotNull;
import com.jetbrains.cidr.modulemap.symbols.ModuleMapModuleSymbol;
import com.intellij.util.Processor;
import kotlin.Metadata;

@Metadata(mv = { 1, 1, 7 }, bv = { 1, 0, 2 }, k = 1, d1 = { "\u00002\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010#\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0004\u0018\u00002\u00020\u0001B\u001d\u0012\b\b\u0002\u0010\u0002\u001a\u00020\u0003\u0012\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005¢\u0006\u0002\u0010\u0007J&\u0010\b\u001a\u00020\u00032\u0006\u0010\t\u001a\u00020\u00062\u0006\u0010\n\u001a\u00020\u000b2\f\u0010\f\u001a\b\u0012\u0004\u0012\u00020\u00060\rH\u0002J\u001a\u0010\u000e\u001a\u0004\u0018\u00010\u00062\u0006\u0010\u000f\u001a\u00020\u00102\u0006\u0010\n\u001a\u00020\u000bH\u0002J\u001c\u0010\u0011\u001a\u0004\u0018\u00010\u00062\u0006\u0010\u000f\u001a\u00020\u00102\b\u0010\u0012\u001a\u0004\u0018\u00010\u0006H\u0002J\u0016\u0010\u0013\u001a\u00020\u00032\u0006\u0010\t\u001a\u00020\u00062\u0006\u0010\n\u001a\u00020\u000bJ\u0016\u0010\u0013\u001a\u00020\u00032\u0006\u0010\u000f\u001a\u00020\u00102\u0006\u0010\n\u001a\u00020\u000bR\u0014\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u0014" }, d2 = { "Lcom/jetbrains/cidr/modulemap/resolve/ModuleMapWalker;", "", "recursive", "", "processor", "Lcom/intellij/util/Processor;", "Lcom/jetbrains/cidr/modulemap/symbols/ModuleMapModuleSymbol;", "(ZLcom/intellij/util/Processor;)V", "doProcess", "module", "configuration", "Lcom/jetbrains/cidr/lang/workspace/OCResolveConfiguration;", "processedModules", "", "findGlobalModule", "moduleName", "", "findLocalModule", "moduleContext", "process", "cidr-lang" })
public final class ModuleMapWalker
{
    private final boolean recursive;
    private final Processor<ModuleMapModuleSymbol> processor;
    
    public final boolean process(@NotNull final String s, @NotNull final OCResolveConfiguration ocResolveConfiguration) {
        Intrinsics.checkParameterIsNotNull((Object)s, "moduleName");
        Intrinsics.checkParameterIsNotNull((Object)ocResolveConfiguration, "configuration");
        final ModuleMapModuleSymbol a = this.a(s, ocResolveConfiguration);
        return a == null || this.process(a, ocResolveConfiguration);
    }
    
    public final boolean process(@NotNull final ModuleMapModuleSymbol moduleMapModuleSymbol, @NotNull final OCResolveConfiguration ocResolveConfiguration) {
        Intrinsics.checkParameterIsNotNull((Object)moduleMapModuleSymbol, "module");
        Intrinsics.checkParameterIsNotNull((Object)ocResolveConfiguration, "configuration");
        final HashSet hashSet = ContainerUtil.newHashSet();
        Intrinsics.checkExpressionValueIsNotNull((Object)hashSet, "ContainerUtil.newHashSet<ModuleMapModuleSymbol>()");
        return this.a(moduleMapModuleSymbol, ocResolveConfiguration, hashSet);
    }
    
    private final boolean a(final ModuleMapModuleSymbol moduleMapModuleSymbol, final OCResolveConfiguration ocResolveConfiguration, final Set<ModuleMapModuleSymbol> set) {
        if (!set.add(moduleMapModuleSymbol)) {
            return true;
        }
        if (!this.processor.process((Object)moduleMapModuleSymbol)) {
            return false;
        }
        final Processor processor = this.recursive ? ((Processor)new ModuleMapWalker$doProcess$actualProcessor.ModuleMapWalker$doProcess$actualProcessor$1(this, ocResolveConfiguration, (Set)set)) : this.processor;
        final Collection<ModuleMapModuleSymbol> collection = moduleMapModuleSymbol.getSubModules().values();
        final ArrayList<ModuleMapModuleSymbol> list = new ArrayList<ModuleMapModuleSymbol>();
        for (final ModuleMapModuleSymbol next : collection) {
            if (!next.isExplicit()) {
                list.add(next);
            }
        }
        final Iterator<Object> iterator2 = list.iterator();
        while (iterator2.hasNext()) {
            if (!processor.process((Object)iterator2.next())) {
                return false;
            }
        }
        for (final String s : moduleMapModuleSymbol.getExportModules()) {
            if (Intrinsics.areEqual((Object)s, (Object)ModuleMapModuleSymbol.Companion.getMODULE_ID_WILDCARD())) {
                if (!processor.process((Object)moduleMapModuleSymbol.getTopmostParentOrThis())) {
                    return false;
                }
                continue;
            }
            else {
                final ModuleMapModuleSymbol a = this.a(s, moduleMapModuleSymbol);
                if (a == null) {
                    continue;
                }
                if (!processor.process((Object)a)) {
                    return false;
                }
                continue;
            }
        }
        return true;
    }
    
    private final ModuleMapModuleSymbol a(final String s, final ModuleMapModuleSymbol moduleMapModuleSymbol) {
        if (moduleMapModuleSymbol != null) {
            for (ModuleMapModuleSymbol parent = moduleMapModuleSymbol; parent != null; parent = parent.getParent()) {
                final ModuleMapModuleSymbol subModule = parent.findSubModule(s);
                if (subModule != null) {
                    return subModule;
                }
            }
        }
        return null;
    }
    
    private final ModuleMapModuleSymbol a(final String s, final OCResolveConfiguration ocResolveConfiguration) {
        final ModuleMapManager.Companion companion = ModuleMapManager.Companion;
        final Project project = ocResolveConfiguration.getProject();
        Intrinsics.checkExpressionValueIsNotNull((Object)project, "configuration.project");
        return companion.getInstance(project).getModules(ocResolveConfiguration).getModule(s);
    }
    
    public ModuleMapWalker(final boolean recursive, @NotNull final Processor<ModuleMapModuleSymbol> processor) {
        Intrinsics.checkParameterIsNotNull((Object)processor, "processor");
        this.recursive = recursive;
        this.processor = processor;
    }
}
