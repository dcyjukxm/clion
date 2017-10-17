// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.modulemap.psi.impl;

import org.jetbrains.annotations.NotNull;
import com.intellij.lang.ASTNode;
import com.intellij.extapi.psi.ASTWrapperPsiElement;

public abstract class ModuleMapPsiElementImpl extends ASTWrapperPsiElement
{
    public ModuleMapPsiElementImpl(@NotNull final ASTNode node) {
        if (node == null) {
            throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "node", "com/jetbrains/cidr/modulemap/psi/impl/ModuleMapPsiElementImpl", "<init>"));
        }
        super(node);
    }
}
