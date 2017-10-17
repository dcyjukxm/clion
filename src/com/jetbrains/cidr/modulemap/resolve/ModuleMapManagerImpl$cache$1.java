// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.modulemap.resolve;

import com.intellij.util.CachedValueImpl;
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
import com.intellij.psi.util.CachedValue;
import com.jetbrains.cidr.lang.workspace.OCWorkspaceModificationTrackers;
import com.intellij.psi.util.CachedValueProvider$Result;
import kotlin.Metadata;
import com.jetbrains.cidr.modulemap.ModuleMapModules;
import com.jetbrains.cidr.lang.workspace.OCResolveConfiguration;
import java.util.Map;
import com.intellij.psi.util.CachedValueProvider;

@Metadata(mv = { 1, 1, 7 }, bv = { 1, 0, 2 }, k = 3, d1 = { "\u0000\u0016\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010$\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001aV\u0012$\u0012\"\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\u0004 \u0005*\u0010\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\u0004\u0018\u00010\u00020\u0002 \u0005**\u0012$\u0012\"\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\u0004 \u0005*\u0010\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\u0004\u0018\u00010\u00020\u0002\u0018\u00010\u00010\u0001H\n¢\u0006\u0002\b\u0006" }, d2 = { "<anonymous>", "Lcom/intellij/psi/util/CachedValueProvider$Result;", "", "Lcom/jetbrains/cidr/lang/workspace/OCResolveConfiguration;", "Lcom/jetbrains/cidr/modulemap/ModuleMapModules;", "kotlin.jvm.PlatformType", "compute" })
static final class ModuleMapManagerImpl$cache$1<T> implements CachedValueProvider<Map<OCResolveConfiguration, ? extends ModuleMapModules>> {
    public final CachedValueProvider$Result<Map<OCResolveConfiguration, ModuleMapModules>> compute() {
        final OCWorkspaceModificationTrackers instance = OCWorkspaceModificationTrackers.getInstance(ModuleMapManagerImpl.access$getProject$p(this.this$0));
        return (CachedValueProvider$Result<Map<OCResolveConfiguration, ModuleMapModules>>)CachedValueProvider$Result.create((Object)this.this$0.a(), new Object[] { instance.getProjectsListTracker(), instance.getBuildSettingsChangesTracker(), instance.getProjectFilesListTracker() });
    }
}