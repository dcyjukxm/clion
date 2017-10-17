// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.modulemap.resolve;

import com.intellij.openapi.components.ServiceManager;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.internal.Intrinsics;
import com.intellij.openapi.project.Project;
import kotlin.jvm.internal.DefaultConstructorMarker;
import com.jetbrains.cidr.lang.workspace.headerRoots.HeadersSearchRoot;
import java.util.List;
import com.jetbrains.cidr.modulemap.ModuleMapModules;
import org.jetbrains.annotations.NotNull;
import com.jetbrains.cidr.lang.workspace.OCResolveConfiguration;
import kotlin.Metadata;

@Metadata(mv = { 1, 1, 7 }, bv = { 1, 0, 2 }, k = 1, d1 = { "\u0000$\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\b&\u0018\u0000 \n2\u00020\u0001:\u0001\nB\u0005¢\u0006\u0002\u0010\u0002J\u0016\u0010\u0003\u001a\b\u0012\u0004\u0012\u00020\u00050\u00042\u0006\u0010\u0006\u001a\u00020\u0007H&J\u0010\u0010\b\u001a\u00020\t2\u0006\u0010\u0006\u001a\u00020\u0007H&¨\u0006\u000b" }, d2 = { "Lcom/jetbrains/cidr/modulemap/resolve/ModuleMapManager;", "", "()V", "getHeaderSearchRoots", "", "Lcom/jetbrains/cidr/lang/workspace/headerRoots/HeadersSearchRoot;", "configuration", "Lcom/jetbrains/cidr/lang/workspace/OCResolveConfiguration;", "getModules", "Lcom/jetbrains/cidr/modulemap/ModuleMapModules;", "Companion", "cidr-lang" })
public abstract class ModuleMapManager
{
    public static final Companion Companion;
    
    @NotNull
    public abstract ModuleMapModules getModules(@NotNull final OCResolveConfiguration p0);
    
    @NotNull
    public abstract List<HeadersSearchRoot> getHeaderSearchRoots(@NotNull final OCResolveConfiguration p0);
    
    static {
        Companion = new Companion(null);
    }
    
    @JvmStatic
    @NotNull
    public static final ModuleMapManager getInstance(@NotNull final Project project) {
        Intrinsics.checkParameterIsNotNull((Object)project, "project");
        return ModuleMapManager.Companion.getInstance(project);
    }
    
    @Metadata(mv = { 1, 1, 7 }, bv = { 1, 0, 2 }, k = 1, d1 = { "\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0010\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0006H\u0007¨\u0006\u0007" }, d2 = { "Lcom/jetbrains/cidr/modulemap/resolve/ModuleMapManager$Companion;", "", "()V", "getInstance", "Lcom/jetbrains/cidr/modulemap/resolve/ModuleMapManager;", "project", "Lcom/intellij/openapi/project/Project;", "cidr-lang" })
    public static final class Companion
    {
        @JvmStatic
        @NotNull
        public final ModuleMapManager getInstance(@NotNull final Project project) {
            Intrinsics.checkParameterIsNotNull((Object)project, "project");
            final Object service = ServiceManager.getService(project, (Class)ModuleMapManager.class);
            Intrinsics.checkExpressionValueIsNotNull(service, "ServiceManager.getServic\u2026leMapManager::class.java)");
            return (ModuleMapManager)service;
        }
    }
}
