// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.lang;

import com.jetbrains.cidr.cpp.cmake.workspace.CMakeWorkspace;
import com.jetbrains.cidr.lang.workspace.OCWorkspace;
import org.jetbrains.annotations.NotNull;
import com.intellij.openapi.project.Project;
import com.jetbrains.cidr.lang.workspace.OCWorkspaceManager;

public class CPPWorkspaceManager extends OCWorkspaceManager
{
    @NotNull
    private final Project myProject;
    
    public CPPWorkspaceManager(@NotNull final Project myProject) {
        if (myProject == null) {
            throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "project", "com/jetbrains/cidr/lang/CPPWorkspaceManager", "<init>"));
        }
        this.myProject = myProject;
    }
    
    @NotNull
    @Override
    public OCWorkspace getWorkspace() {
        CMakeWorkspace instance;
        try {
            instance = CMakeWorkspace.getInstance(this.myProject);
            if (instance == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/CPPWorkspaceManager", "getWorkspace"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw b(ex);
        }
        return instance;
    }
    
    private static IllegalArgumentException b(final IllegalArgumentException ex) {
        return ex;
    }
}
