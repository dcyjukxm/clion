// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.execution;

import java.util.List;
import org.jetbrains.annotations.Nullable;
import javax.swing.Icon;
import org.jetbrains.annotations.NotNull;

public interface CidrBuildTarget<BC extends CidrBuildConfiguration>
{
    @NotNull
    String getName();
    
    @NotNull
    String getProjectName();
    
    @Nullable
    Icon getIcon();
    
    boolean isExecutable();
    
    @NotNull
    List<BC> getBuildConfigurations();
}
