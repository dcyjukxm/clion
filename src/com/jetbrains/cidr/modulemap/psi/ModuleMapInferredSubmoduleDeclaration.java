// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.modulemap.psi;

import org.jetbrains.annotations.NotNull;
import java.util.List;
import org.jetbrains.annotations.Nullable;

public interface ModuleMapInferredSubmoduleDeclaration extends ModuleMapAttributesHolder
{
    @Nullable
    ModuleMapAttributes getAttributes();
    
    @NotNull
    List<ModuleMapInferredSubmoduleMember> getInferredSubmoduleMemberList();
    
    boolean isExplicit();
    
    boolean isFramework();
}
