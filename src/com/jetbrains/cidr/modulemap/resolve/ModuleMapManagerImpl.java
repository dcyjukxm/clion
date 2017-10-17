// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.modulemap.resolve;

import com.intellij.util.CachedValueImpl;
import com.intellij.psi.util.CachedValueProvider;
import com.jetbrains.cidr.lang.workspace.OCWorkspace;
import com.jetbrains.cidr.modulemap.symbols.ModuleMapModuleSymbol;
import java.util.Collection;
import com.jetbrains.cidr.modulemap.ModuleMapModulesImpl;
import com.intellij.util.containers.Convertor;
import com.jetbrains.cidr.lang.workspace.OCWorkspaceManager;
import java.util.Iterator;
import java.util.ArrayList;
import com.jetbrains.cidr.lang.workspace.OCResolveRootAndConfiguration;
import kotlin.jvm.functions.Function1;
import com.intellij.util.containers.ContainerUtil;
import com.jetbrains.cidr.lang.workspace.headerRoots.HeadersSearchRoot;
import java.util.List;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import com.intellij.openapi.project.Project;
import com.jetbrains.cidr.modulemap.ModuleMapModules;
import com.jetbrains.cidr.lang.workspace.OCResolveConfiguration;
import java.util.Map;
import com.intellij.psi.util.CachedValue;
import kotlin.Metadata;

@Metadata(mv = { 1, 1, 7 }, bv = { 1, 0, 2 }, k = 1, d1 = { "\u00002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010$\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0003\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\u0014\u0010\n\u001a\u000e\u0012\u0004\u0012\u00020\b\u0012\u0004\u0012\u00020\t0\u0007H\u0002J\u0016\u0010\u000b\u001a\b\u0012\u0004\u0012\u00020\r0\f2\u0006\u0010\u000e\u001a\u00020\bH\u0016J\u0010\u0010\u000f\u001a\u00020\t2\u0006\u0010\u000e\u001a\u00020\bH\u0016R \u0010\u0005\u001a\u0014\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u00020\b\u0012\u0004\u0012\u00020\t0\u00070\u0006X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u0010" }, d2 = { "Lcom/jetbrains/cidr/modulemap/resolve/ModuleMapManagerImpl;", "Lcom/jetbrains/cidr/modulemap/resolve/ModuleMapManager;", "project", "Lcom/intellij/openapi/project/Project;", "(Lcom/intellij/openapi/project/Project;)V", "cache", "Lcom/intellij/psi/util/CachedValue;", "", "Lcom/jetbrains/cidr/lang/workspace/OCResolveConfiguration;", "Lcom/jetbrains/cidr/modulemap/ModuleMapModules;", "collectConfigurationModules", "getHeaderSearchRoots", "", "Lcom/jetbrains/cidr/lang/workspace/headerRoots/HeadersSearchRoot;", "configuration", "getModules", "cidr-lang" })
public final class ModuleMapManagerImpl extends ModuleMapManager
{
    private final CachedValue<Map<OCResolveConfiguration, ModuleMapModules>> cache;
    private final Project project;
    
    @NotNull
    @Override
    public ModuleMapModules getModules(@NotNull final OCResolveConfiguration ocResolveConfiguration) {
        Intrinsics.checkParameterIsNotNull((Object)ocResolveConfiguration, "configuration");
        ModuleMapModules empty;
        if ((empty = ((Map)this.cache.getValue()).get(ocResolveConfiguration)) == null) {
            empty = ModuleMapModules.Companion.getEMPTY();
        }
        return empty;
    }
    
    @NotNull
    @Override
    public List<HeadersSearchRoot> getHeaderSearchRoots(@NotNull final OCResolveConfiguration ocResolveConfiguration) {
        Intrinsics.checkParameterIsNotNull((Object)ocResolveConfiguration, "configuration");
        final ArrayList arrayList = ContainerUtil.newArrayList();
        final Function1 function1 = (Function1)new ModuleMapManagerImpl$getHeaderSearchRoots$processor.ModuleMapManagerImpl$getHeaderSearchRoots$processor$1(arrayList);
        final OCResolveRootAndConfiguration ocResolveRootAndConfiguration = new OCResolveRootAndConfiguration(ocResolveConfiguration, ocResolveConfiguration.getMaximumLanguageKind());
        final Iterator<Object> iterator = ocResolveConfiguration.getProjectHeadersRoots().getRoots().iterator();
        while (iterator.hasNext()) {
            function1.invoke(iterator.next());
        }
        final Iterator<Object> iterator2 = ocResolveConfiguration.getLibraryHeadersRoots(ocResolveRootAndConfiguration).getRoots().iterator();
        while (iterator2.hasNext()) {
            function1.invoke(iterator2.next());
        }
        final ArrayList list = arrayList;
        Intrinsics.checkExpressionValueIsNotNull((Object)list, "headerRoots");
        return (ArrayList<HeadersSearchRoot>)list;
    }
    
    private final Map<OCResolveConfiguration, ModuleMapModules> a() {
        final OCWorkspace workspace = OCWorkspaceManager.getWorkspace(this.project);
        final Map mapFromKeys = ContainerUtil.newMapFromKeys((Iterator)workspace.getConfigurations().iterator(), (Convertor)ModuleMapManagerImpl$collectConfigurationModules$result.ModuleMapManagerImpl$collectConfigurationModules$result$1.INSTANCE);
        for (final OCResolveConfiguration ocResolveConfiguration2 : workspace.getConfigurations()) {
            final OCResolveConfiguration ocResolveConfiguration = ocResolveConfiguration2;
            Intrinsics.checkExpressionValueIsNotNull((Object)ocResolveConfiguration2, "configuration");
            for (final HeadersSearchRoot headersSearchRoot : this.getHeaderSearchRoots(ocResolveConfiguration2)) {
                final ModuleMapModulesImpl value = (ModuleMapModulesImpl)mapFromKeys.get(ocResolveConfiguration);
                if (value == null) {
                    Intrinsics.throwNpe();
                }
                value.add(headersSearchRoot.collectDeclaredModules().getModules());
            }
        }
        final Map<OCResolveConfiguration, ModuleMapModulesImpl> map = (Map<OCResolveConfiguration, ModuleMapModulesImpl>)mapFromKeys;
        Intrinsics.checkExpressionValueIsNotNull((Object)map, "result");
        return (Map<OCResolveConfiguration, ModuleMapModules>)map;
    }
    
    public ModuleMapManagerImpl(@NotNull final Project project) {
        Intrinsics.checkParameterIsNotNull((Object)project, "project");
        this.project = project;
        this.cache = (CachedValue<Map<OCResolveConfiguration, ModuleMapModules>>)new CachedValueImpl((com.intellij.psi.util.CachedValueProvider<Object>)new ModuleMapManagerImpl$cache.ModuleMapManagerImpl$cache$1(this));
    }
}
