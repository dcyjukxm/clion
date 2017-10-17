// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.project;

import com.intellij.openapi.project.Project;
import kotlin.jvm.internal.Intrinsics;
import com.intellij.openapi.module.Module;
import com.intellij.openapi.vfs.VirtualFile;
import kotlin.Metadata;

@Metadata(mv = { 1, 1, 7 }, bv = { 1, 0, 2 }, k = 3, d1 = { "\u0000\b\n\u0000\n\u0002\u0010\u0002\n\u0000\u0010\u0000\u001a\u00020\u0001H\n¢\u0006\u0002\b\u0002" }, d2 = { "<anonymous>", "", "run" })
static final class CidrUnmarkRootAction$modifyRoots$1 implements Runnable {
    @Override
    public final void run() {
        final VirtualFile[] array = this.$files;
        for (int i = 0; i < array.length; ++i) {
            final VirtualFile virtualFile = array[i];
            final CidrRootConfiguration.Companion companion = CidrRootConfiguration.Companion;
            final Project project = this.$module.getProject();
            Intrinsics.checkExpressionValueIsNotNull((Object)project, "module.project");
            companion.getInstance(project).removeRoot(virtualFile);
        }
    }
}