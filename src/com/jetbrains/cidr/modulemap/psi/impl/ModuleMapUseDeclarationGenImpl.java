// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.modulemap.psi.impl;

import org.jetbrains.annotations.Nullable;
import com.jetbrains.cidr.modulemap.psi.ModuleMapModuleId;
import com.intellij.psi.PsiElementVisitor;
import org.jetbrains.annotations.NotNull;
import com.jetbrains.cidr.modulemap.psi.ModuleMapVisitor;
import com.intellij.lang.ASTNode;
import com.jetbrains.cidr.modulemap.psi.ModuleMapUseDeclaration;

public class ModuleMapUseDeclarationGenImpl extends ModuleMapPsiElementImpl implements ModuleMapUseDeclaration
{
    public ModuleMapUseDeclarationGenImpl(final ASTNode astNode) {
        super(astNode);
    }
    
    public void accept(@NotNull final ModuleMapVisitor moduleMapVisitor) {
        try {
            if (moduleMapVisitor == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "visitor", "com/jetbrains/cidr/modulemap/psi/impl/ModuleMapUseDeclarationGenImpl", "accept"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        moduleMapVisitor.visitUseDeclaration(this);
    }
    
    public void accept(@NotNull final PsiElementVisitor visitor) {
        try {
            if (visitor == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "visitor", "com/jetbrains/cidr/modulemap/psi/impl/ModuleMapUseDeclarationGenImpl", "accept"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        try {
            if (visitor instanceof ModuleMapVisitor) {
                this.accept((ModuleMapVisitor)visitor);
                return;
            }
        }
        catch (IllegalArgumentException ex2) {
            throw a(ex2);
        }
        super.accept(visitor);
    }
    
    @Nullable
    @Override
    public ModuleMapModuleId getModuleId() {
        return this.findChildByClass(ModuleMapModuleId.class);
    }
    
    private static IllegalArgumentException a(final IllegalArgumentException ex) {
        return ex;
    }
}
