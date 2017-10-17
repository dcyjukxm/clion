// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.modulemap.psi;

import org.jetbrains.annotations.NotNull;
import java.util.List;
import com.intellij.psi.PsiFile;

public interface ModuleMapFile extends PsiFile, ModuleMapPsiElement
{
    @NotNull
    List<ModuleMapModuleDeclaration> getModuleDeclarations();
    
    @NotNull
    List<ModuleMapExternModuleDeclaration> getExternModuleDeclarations();
}
