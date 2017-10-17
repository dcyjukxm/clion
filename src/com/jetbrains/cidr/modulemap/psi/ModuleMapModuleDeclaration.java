// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.modulemap.psi;

import org.jetbrains.annotations.NotNull;
import java.util.List;
import org.jetbrains.annotations.Nullable;

public interface ModuleMapModuleDeclaration extends ModuleMapPsiElement
{
    @Nullable
    ModuleMapAttributes getAttributes();
    
    @NotNull
    List<ModuleMapConfigMacrosDeclaration> getConfigMacrosDeclarationList();
    
    @NotNull
    List<ModuleMapConflictDeclaration> getConflictDeclarationList();
    
    @NotNull
    List<ModuleMapExportDeclaration> getExportDeclarationList();
    
    @NotNull
    List<ModuleMapHeaderDeclaration> getHeaderDeclarationList();
    
    @NotNull
    List<ModuleMapInferredSubmoduleDeclaration> getInferredSubmoduleDeclarationList();
    
    @NotNull
    List<ModuleMapLinkDeclaration> getLinkDeclarationList();
    
    @NotNull
    List<ModuleMapModuleDeclaration> getModuleDeclarationList();
    
    @Nullable
    ModuleMapModuleId getModuleId();
    
    @NotNull
    List<ModuleMapRequiresDeclaration> getRequiresDeclarationList();
    
    @NotNull
    List<ModuleMapUmbrellaDirDeclaration> getUmbrellaDirDeclarationList();
    
    @NotNull
    List<ModuleMapUseDeclaration> getUseDeclarationList();
    
    boolean isExplicit();
    
    boolean isFramework();
}
