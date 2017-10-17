// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.lang.workspace;

import com.intellij.openapi.components.ServiceManager;
import org.jetbrains.annotations.NotNull;
import com.intellij.openapi.project.Project;

public abstract class OCWorkspaceManager
{
    public static OCWorkspace getWorkspace(@NotNull final Project project) {
        try {
            if (project == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "project", "com/jetbrains/cidr/lang/workspace/OCWorkspaceManager", "getWorkspace"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        return ((OCWorkspaceManager)ServiceManager.getService(project, (Class)OCWorkspaceManager.class)).getWorkspace();
    }
    
    @NotNull
    public abstract OCWorkspace getWorkspace();
    
    private static IllegalArgumentException a(final IllegalArgumentException ex) {
        return ex;
    }
}
