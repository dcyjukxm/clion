// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.modulemap.psi.impl;

import com.jetbrains.cidr.modulemap.ModuleMapParserTypes;
import org.jetbrains.annotations.NotNull;
import com.intellij.lang.ASTNode;
import com.jetbrains.cidr.modulemap.psi.ModuleMapHeaderDeclaration;

public abstract class ModuleMapHeaderDeclarationImpl extends ModuleMapPsiElementImpl implements ModuleMapHeaderDeclaration
{
    public ModuleMapHeaderDeclarationImpl(@NotNull final ASTNode astNode) {
        if (astNode == null) {
            throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "node", "com/jetbrains/cidr/modulemap/psi/impl/ModuleMapHeaderDeclarationImpl", "<init>"));
        }
        super(astNode);
    }
    
    @Override
    public boolean isPrivate() {
        try {
            if (this.findChildByType(ModuleMapParserTypes.PRIVATE) != null) {
                return true;
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        return false;
    }
    
    @Override
    public boolean isTextual() {
        try {
            if (this.findChildByType(ModuleMapParserTypes.TEXTUAL) != null) {
                return true;
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        return false;
    }
    
    @Override
    public boolean isExclude() {
        try {
            if (this.findChildByType(ModuleMapParserTypes.EXCLUDE) != null) {
                return true;
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        return false;
    }
    
    @Override
    public boolean isUmbrella() {
        try {
            if (this.findChildByType(ModuleMapParserTypes.UMBRELLA) != null) {
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
