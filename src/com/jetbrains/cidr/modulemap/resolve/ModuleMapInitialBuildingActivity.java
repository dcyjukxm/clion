// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.modulemap.resolve;

import com.jetbrains.cidr.lang.symbols.symtable.OCSymbolTablesBuildingActivity;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import com.intellij.openapi.project.Project;
import kotlin.Metadata;
import com.intellij.openapi.project.DumbAware;
import com.intellij.openapi.startup.StartupActivity;

@Metadata(mv = { 1, 1, 7 }, bv = { 1, 0, 2 }, k = 1, d1 = { "\u0000\u001c\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\u0018\u00002\u00020\u00012\u00020\u0002B\u0005¢\u0006\u0002\u0010\u0003J\u0010\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u0007H\u0016¨\u0006\b" }, d2 = { "Lcom/jetbrains/cidr/modulemap/resolve/ModuleMapInitialBuildingActivity;", "Lcom/intellij/openapi/startup/StartupActivity;", "Lcom/intellij/openapi/project/DumbAware;", "()V", "runActivity", "", "project", "Lcom/intellij/openapi/project/Project;", "cidr-lang" })
public final class ModuleMapInitialBuildingActivity implements StartupActivity, DumbAware
{
    public void runActivity(@NotNull final Project project) {
        Intrinsics.checkParameterIsNotNull((Object)project, "project");
        OCSymbolTablesBuildingActivity.getInstance(project).rebuildModuleMaps();
    }
}
