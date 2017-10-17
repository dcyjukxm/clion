// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.modulemap.psi.impl;

import com.jetbrains.cidr.modulemap.ModuleMapParserTypes;
import org.jetbrains.annotations.NotNull;
import com.intellij.lang.ASTNode;
import com.jetbrains.cidr.modulemap.psi.ModuleMapInferredSubmoduleDeclaration;

public abstract class ModuleMapInferredSubmoduleDeclarationImpl extends ModuleMapPsiElementImpl implements ModuleMapInferredSubmoduleDeclaration
{
    public ModuleMapInferredSubmoduleDeclarationImpl(@NotNull final ASTNode astNode) {
        if (astNode == null) {
            throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "node", "com/jetbrains/cidr/modulemap/psi/impl/ModuleMapInferredSubmoduleDeclarationImpl", "<init>"));
        }
        super(astNode);
    }
    
    @Override
    public boolean isExplicit() {
        try {
            if (this.findChildByType(ModuleMapParserTypes.EXPLICIT) != null) {
                return true;
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        return false;
    }
    
    @Override
    public boolean isFramework() {
        try {
            if (this.findChildByType(ModuleMapParserTypes.FRAMEWORK) != null) {
                return true;
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        return false;
    }
    
    private static IllegalArgumentException a(final IllegalArgumentException ex) {
        return ex;
    }
}
