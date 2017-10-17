// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.cpp.cmake.workspace;

import org.jetbrains.annotations.NotNull;
import com.intellij.openapi.application.Result;
import com.intellij.openapi.application.WriteAction;

class CMakeWorkspace$6 extends WriteAction {
    final /* synthetic */ CMakeWorkspaceListener val$publisher;
    final /* synthetic */ boolean val$finalCanceled;
    
    protected void run(@NotNull final Result result) throws Throwable {
        try {
            if (result == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "result", "com/jetbrains/cidr/cpp/cmake/workspace/CMakeWorkspace$6", "run"));
            }
        }
        catch (Throwable t) {
            throw a(t);
        }
        try {
            if (!CMakeWorkspace.access$300(CMakeWorkspace.this).isDisposed()) {
                this.val$publisher.reloadingFinished(this.val$finalCanceled);
            }
        }
        catch (Throwable t2) {
            throw a(t2);
        }
    }
    
    private static Throwable a(final Throwable t) {
        return t;
    }
}