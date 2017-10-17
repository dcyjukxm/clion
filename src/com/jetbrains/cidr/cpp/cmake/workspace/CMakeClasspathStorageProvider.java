// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.cpp.cmake.workspace;

import org.jetbrains.annotations.NotNull;
import com.intellij.openapi.roots.ModifiableRootModel;
import com.jetbrains.cidr.project.CidrClasspathStorageProvider;

public class CMakeClasspathStorageProvider extends CidrClasspathStorageProvider
{
    public static final String ID = "CMake";
    
    @Override
    public String getID() {
        return "CMake";
    }
    
    @Override
    protected void fillModel(@NotNull final ModifiableRootModel modifiableRootModel) {
        try {
            if (modifiableRootModel == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "model", "com/jetbrains/cidr/cpp/cmake/workspace/CMakeClasspathStorageProvider", "fillModel"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw c(ex);
        }
        CMakeWorkspace.getInstance(modifiableRootModel.getProject()).updateContentRoots(modifiableRootModel);
    }
    
    private static IllegalArgumentException c(final IllegalArgumentException ex) {
        return ex;
    }
}
