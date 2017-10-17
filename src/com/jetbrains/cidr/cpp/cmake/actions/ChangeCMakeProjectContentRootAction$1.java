// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.cpp.cmake.actions;

import org.jetbrains.annotations.NotNull;
import com.intellij.openapi.application.Result;
import com.intellij.openapi.vfs.VirtualFile;
import com.jetbrains.cidr.cpp.cmake.workspace.CMakeWorkspace;
import com.intellij.openapi.application.WriteAction;

static final class ChangeCMakeProjectContentRootAction$1 extends WriteAction<Object> {
    final /* synthetic */ CMakeWorkspace val$workspace;
    final /* synthetic */ VirtualFile val$file;
    
    protected void run(@NotNull final Result<Object> result) throws Throwable {
        try {
            if (result == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "result", "com/jetbrains/cidr/cpp/cmake/actions/ChangeCMakeProjectContentRootAction$1", "run"));
            }
        }
        catch (Throwable t) {
            throw a(t);
        }
        this.val$workspace.changeContentRoot(this.val$file);
    }
    
    private static Throwable a(final Throwable t) {
        return t;
    }
}