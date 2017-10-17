// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.modulemap.resolve;

import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.JvmStatic;
import com.intellij.openapi.components.ServiceManager;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import com.intellij.openapi.project.Project;
import kotlin.Metadata;

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
