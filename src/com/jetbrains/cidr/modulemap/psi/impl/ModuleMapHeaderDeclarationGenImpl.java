// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.modulemap.psi.impl;

import org.jetbrains.annotations.Nullable;
import com.jetbrains.cidr.modulemap.psi.ModuleMapHeaderName;
import com.intellij.psi.PsiElementVisitor;
import org.jetbrains.annotations.NotNull;
import com.jetbrains.cidr.modulemap.psi.ModuleMapVisitor;
import com.intellij.lang.ASTNode;
import com.jetbrains.cidr.modulemap.psi.ModuleMapHeaderDeclaration;

public class ModuleMapHeaderDeclarationGenImpl extends ModuleMapHeaderDeclarationImpl implements ModuleMapHeaderDeclaration
{
    public ModuleMapHeaderDeclarationGenImpl(final ASTNode astNode) {
        super(astNode);
    }
    
    public void accept(@NotNull final ModuleMapVisitor moduleMapVisitor) {
        try {
            if (moduleMapVisitor == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "visitor", "com/jetbrains/cidr/modulemap/psi/impl/ModuleMapHeaderDeclarationGenImpl", "accept"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw b(ex);
        }
        moduleMapVisitor.visitHeaderDeclaration(this);
    }
    
    public void accept(@NotNull final PsiElementVisitor visitor) {
        try {
            if (visitor == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "visitor", "com/jetbrains/cidr/modulemap/psi/impl/ModuleMapHeaderDeclarationGenImpl", "accept"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw b(ex);
        }
        try {
            if (visitor instanceof ModuleMapVisitor) {
                this.accept((ModuleMapVisitor)visitor);
                return;
            }
        }
        catch (IllegalArgumentException ex2) {
            throw b(ex2);
        }
        super.accept(visitor);
    }
    
    @Nullable
    @Override
    public ModuleMapHeaderName getHeaderName() {
        return this.findChildByClass(ModuleMapHeaderName.class);
    }
    
    private static IllegalArgumentException b(final IllegalArgumentException ex) {
        return ex;
    }
}
