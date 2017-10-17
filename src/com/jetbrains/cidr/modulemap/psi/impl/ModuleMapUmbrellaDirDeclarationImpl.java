// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.modulemap.psi.impl;

import com.intellij.openapi.util.text.StringUtil;
import org.jetbrains.annotations.NotNull;
import com.intellij.lang.ASTNode;
import com.jetbrains.cidr.modulemap.psi.ModuleMapUmbrellaDirDeclaration;

public class ModuleMapUmbrellaDirDeclarationImpl extends ModuleMapPsiElementImpl implements ModuleMapUmbrellaDirDeclaration
{
    public ModuleMapUmbrellaDirDeclarationImpl(@NotNull final ASTNode astNode) {
        if (astNode == null) {
            throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "node", "com/jetbrains/cidr/modulemap/psi/impl/ModuleMapUmbrellaDirDeclarationImpl", "<init>"));
        }
        super(astNode);
    }
    
    @NotNull
    @Override
    public String getName() {
        String unquoteString;
        try {
            unquoteString = StringUtil.unquoteString(this.getLastChild().getText());
            if (unquoteString == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/modulemap/psi/impl/ModuleMapUmbrellaDirDeclarationImpl", "getName"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        return unquoteString;
    }
    
    private static IllegalArgumentException a(final IllegalArgumentException ex) {
        return ex;
    }
}
