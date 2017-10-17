// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.cpp.cmake.workspace;

import com.intellij.util.ui.update.MergingUpdateQueue;
import org.jetbrains.annotations.NotNull;
import com.intellij.openapi.application.Result;
import com.intellij.openapi.Disposable;
import com.intellij.openapi.application.WriteAction;

class CMakeWorkspaceWatcher$6 extends WriteAction<Object> {
    final /* synthetic */ boolean val$value;
    final /* synthetic */ Disposable val$disposable;
    
    protected void run(@NotNull final Result<Object> result) throws Throwable {
        try {
            if (result == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "result", "com/jetbrains/cidr/cpp/cmake/workspace/CMakeWorkspaceWatcher$6", "run"));
            }
        }
        catch (Throwable t) {
            throw a(t);
        }
        MergingUpdateQueue access$000 = null;
        boolean passThrough = false;
        Label_0079: {
            try {
                CMakeWorkspaceWatcher.access$702(CMakeWorkspaceWatcher.this, this.val$value);
                access$000 = CMakeWorkspaceWatcher.access$000(CMakeWorkspaceWatcher.this);
                if (!this.val$value) {
                    passThrough = true;
                    break Label_0079;
                }
            }
            catch (Throwable t2) {
                throw a(t2);
            }
            passThrough = false;
        }
        access$000.setPassThrough(passThrough);
        CMakeWorkspaceWatcher.access$800(CMakeWorkspaceWatcher.this).setForceAsyncInTests(this.val$value, this.val$disposable);
    }
    
    private static Throwable a(final Throwable t) {
        return t;
    }
}