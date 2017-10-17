// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.cpp.cmake.workspace;

import com.intellij.openapi.vfs.pointers.VirtualFilePointer;
import com.intellij.openapi.roots.ModuleRootModel;
import com.intellij.openapi.application.ReadAction;
import com.intellij.openapi.vfs.VirtualFile;
import org.jetbrains.annotations.NotNull;
import com.intellij.openapi.roots.impl.DirectoryIndexExcludePolicy;

public class CMakeExcludePolicy implements DirectoryIndexExcludePolicy
{
    @NotNull
    private final CMakeWorkspace myWorkspace;
    
    public CMakeExcludePolicy(@NotNull final CMakeWorkspace myWorkspace) {
        if (myWorkspace == null) {
            throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "workspace", "com/jetbrains/cidr/cpp/cmake/workspace/CMakeExcludePolicy", "<init>"));
        }
        this.myWorkspace = myWorkspace;
    }
    
    @NotNull
    @Override
    public VirtualFile[] getExcludeRootsForProject() {
        VirtualFile[] array;
        try {
            array = (VirtualFile[])ReadAction.compute(() -> this.myWorkspace.getExcludeRoots().toArray(VirtualFile.EMPTY_ARRAY));
            if (array == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/cpp/cmake/workspace/CMakeExcludePolicy", "getExcludeRootsForProject"));
            }
        }
        catch (RuntimeException ex) {
            throw b(ex);
        }
        return array;
    }
    
    @NotNull
    @Override
    public VirtualFilePointer[] getExcludeRootsForModule(@NotNull final ModuleRootModel moduleRootModel) {
        try {
            if (moduleRootModel == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "rootModel", "com/jetbrains/cidr/cpp/cmake/workspace/CMakeExcludePolicy", "getExcludeRootsForModule"));
            }
        }
        catch (RuntimeException ex) {
            throw b(ex);
        }
        VirtualFilePointer[] empty_ARRAY;
        try {
            empty_ARRAY = VirtualFilePointer.EMPTY_ARRAY;
            if (empty_ARRAY == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/cpp/cmake/workspace/CMakeExcludePolicy", "getExcludeRootsForModule"));
            }
        }
        catch (RuntimeException ex2) {
            throw b(ex2);
        }
        return empty_ARRAY;
    }
    
    private static RuntimeException b(final RuntimeException ex) {
        return ex;
    }
}
