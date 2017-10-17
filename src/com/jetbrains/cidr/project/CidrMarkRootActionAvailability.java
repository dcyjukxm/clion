// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.project;

import org.jetbrains.annotations.NotNull;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.extensions.ExtensionPointName;

public interface CidrMarkRootActionAvailability
{
    public static final ExtensionPointName<CidrMarkRootActionAvailability> EP_NAME = ExtensionPointName.create("cidr.markRootActionAvailability");
    
    boolean isAvailable(@NotNull final Project p0);
}
