// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.cpp.cmake.workspace;

import org.jetbrains.annotations.NotNull;
import com.intellij.openapi.progress.ProgressIndicator;
import com.intellij.openapi.project.Project;
import java.util.function.Consumer;
import com.intellij.openapi.progress.Task;

class CMakeWorkspaceWatcher$7 extends Task.Backgroundable {
    final /* synthetic */ boolean val$force;
    final /* synthetic */ Consumer val$reloadRunnable;
    
    public void run(@NotNull final ProgressIndicator progressIndicator) {
        try {
            if (progressIndicator == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "indicator", "com/jetbrains/cidr/cpp/cmake/workspace/CMakeWorkspaceWatcher$7", "run"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        try {
            if (CMakeWorkspaceWatcher.access$900(CMakeWorkspaceWatcher.this).get()) {
                return;
            }
        }
        catch (IllegalArgumentException ex2) {
            throw a(ex2);
        }
        CMakeWorkspaceWatcher.access$1002(CMakeWorkspaceWatcher.this, progressIndicator);
        try {
            Label_0089: {
                if (this.val$force) {
                    break Label_0089;
                }
                try {
                    if (CMakeWorkspaceWatcher.access$1100(CMakeWorkspaceWatcher.this, progressIndicator)) {
                        this.val$reloadRunnable.accept(progressIndicator);
                    }
                }
                catch (IllegalArgumentException ex3) {
                    throw a(ex3);
                }
            }
        }
        finally {
            CMakeWorkspaceWatcher.access$1002(CMakeWorkspaceWatcher.this, null);
        }
    }
    
    private static IllegalArgumentException a(final IllegalArgumentException ex) {
        return ex;
    }
}