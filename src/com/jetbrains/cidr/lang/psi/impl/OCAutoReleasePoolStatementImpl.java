// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.lang.psi.impl;

import com.jetbrains.cidr.lang.psi.visitors.OCVisitor;
import com.jetbrains.cidr.lang.parser.OCElementTypes;
import com.jetbrains.cidr.lang.psi.OCBlockStatement;
import org.jetbrains.annotations.NotNull;
import com.intellij.lang.ASTNode;
import com.jetbrains.cidr.lang.psi.OCAutoReleasePoolStatement;

public class OCAutoReleasePoolStatementImpl extends OCElementBase implements OCAutoReleasePoolStatement
{
    public OCAutoReleasePoolStatementImpl(@NotNull final ASTNode astNode) {
        if (astNode == null) {
            throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "node", "com/jetbrains/cidr/lang/psi/impl/OCAutoReleasePoolStatementImpl", "<init>"));
        }
        super(astNode);
    }
    
    @Override
    public OCBlockStatement getBody() {
        return this.findChildByType(OCElementTypes.STATEMENTS);
    }
    
    @Override
    public void accept(@NotNull final OCVisitor ocVisitor) {
        try {
            if (ocVisitor == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "visitor", "com/jetbrains/cidr/lang/psi/impl/OCAutoReleasePoolStatementImpl", "accept"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        ocVisitor.visitAutoReleasePoolStatement(this);
    }
    
    private static IllegalArgumentException a(final IllegalArgumentException ex) {
        return ex;
    }
}
