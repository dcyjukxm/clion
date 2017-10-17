// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.cpp.cmake;

import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import com.intellij.openapi.project.Project;
import kotlin.Metadata;

@Metadata(mv = { 1, 1, 7 }, bv = { 1, 0, 2 }, k = 1, d1 = { "\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u000e\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0006¨\u0006\u0007" }, d2 = { "Lcom/jetbrains/cidr/cpp/cmake/CMakeSettings$Companion;", "", "()V", "getInstance", "Lcom/jetbrains/cidr/cpp/cmake/CMakeSettings;", "project", "Lcom/intellij/openapi/project/Project;", "clion" })
public static final class Companion
{
    @NotNull
    public final CMakeSettings getInstance(@NotNull final Project project) {
        Intrinsics.checkParameterIsNotNull((Object)project, "project");
        final Object component = project.getComponent((Class)CMakeSettings.class);
        if (component == null) {
            Intrinsics.throwNpe();
        }
        return (CMakeSettings)component;
    }
}
