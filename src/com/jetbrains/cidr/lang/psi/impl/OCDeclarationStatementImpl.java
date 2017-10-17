// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.lang.psi.impl;

import com.jetbrains.cidr.lang.psi.visitors.OCVisitor;
import com.jetbrains.cidr.lang.psi.OCDeclaration;
import org.jetbrains.annotations.NotNull;
import com.intellij.lang.ASTNode;
import com.jetbrains.cidr.lang.psi.OCDeclarationStatement;

public class OCDeclarationStatementImpl extends OCElementBase implements OCDeclarationStatement
{
    public OCDeclarationStatementImpl(@NotNull final ASTNode astNode) {
        if (astNode == null) {
            throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "node", "com/jetbrains/cidr/lang/psi/impl/OCDeclarationStatementImpl", "<init>"));
        }
        super(astNode);
    }
    
    @NotNull
    @Override
    public OCDeclaration getDeclaration() {
        OCDeclaration ocDeclaration;
        try {
            ocDeclaration = this.findNotNullChildByClass(OCDeclaration.class);
            if (ocDeclaration == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/psi/impl/OCDeclarationStatementImpl", "getDeclaration"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        return ocDeclaration;
    }
    
    @Override
    public void accept(@NotNull final OCVisitor ocVisitor) {
        try {
            if (ocVisitor == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "visitor", "com/jetbrains/cidr/lang/psi/impl/OCDeclarationStatementImpl", "accept"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        ocVisitor.visitDeclarationStatement(this);
    }
    
    private static IllegalArgumentException a(final IllegalArgumentException ex) {
        return ex;
    }
}
