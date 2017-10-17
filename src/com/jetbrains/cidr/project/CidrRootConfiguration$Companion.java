// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.project;

import kotlin.jvm.internal.DefaultConstructorMarker;
import com.intellij.openapi.util.Key;
import com.intellij.openapi.module.Module;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import com.intellij.openapi.project.Project;
import kotlin.Metadata;

@Metadata(mv = { 1, 1, 7 }, bv = { 1, 0, 2 }, k = 1, d1 = { "\u0000.\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0010\u0010\u0006\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\tH\u0007J\u0010\u0010\n\u001a\u00020\u000b2\u0006\u0010\f\u001a\u00020\rH\u0007R\u0016\u0010\u0003\u001a\b\u0012\u0004\u0012\u00020\u00050\u00048\u0006X\u0087\u0004¢\u0006\u0002\n\u0000¨\u0006\u000e" }, d2 = { "Lcom/jetbrains/cidr/project/CidrRootConfiguration$Companion;", "", "()V", "MODULE_SUPPORTS_ROOTS_CONFIGURATION", "Lcom/intellij/openapi/util/Key;", "", "getInstance", "Lcom/jetbrains/cidr/project/CidrRootConfiguration;", "project", "Lcom/intellij/openapi/project/Project;", "setEnabledFor", "", "module", "Lcom/intellij/openapi/module/Module;", "cidr-common" })
public static final class Companion
{
    @JvmStatic
    @NotNull
    public final CidrRootConfiguration getInstance(@NotNull final Project project) {
        Intrinsics.checkParameterIsNotNull((Object)project, "project");
        final Object component = project.getComponent((Class)CidrRootConfiguration.class);
        if (component == null) {
            Intrinsics.throwNpe();
        }
        return (CidrRootConfiguration)component;
    }
    
    @JvmStatic
    public final void setEnabledFor(@NotNull final Module module) {
        Intrinsics.checkParameterIsNotNull((Object)module, "module");
        module.putUserData((Key)CidrRootConfiguration.MODULE_SUPPORTS_ROOTS_CONFIGURATION, (Object)true);
    }
}
