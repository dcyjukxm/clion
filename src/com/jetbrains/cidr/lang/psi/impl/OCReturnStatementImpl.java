// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.lang.psi.impl;

import com.jetbrains.cidr.lang.psi.visitors.OCVisitor;
import org.jetbrains.annotations.Nullable;
import com.jetbrains.cidr.lang.parser.OCElementTypes;
import com.jetbrains.cidr.lang.psi.OCExpression;
import org.jetbrains.annotations.NotNull;
import com.intellij.lang.ASTNode;
import com.jetbrains.cidr.lang.psi.OCReturnStatement;

public class OCReturnStatementImpl extends OCElementBase implements OCReturnStatement
{
    public OCReturnStatementImpl(@NotNull final ASTNode astNode) {
        if (astNode == null) {
            throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "node", "com/jetbrains/cidr/lang/psi/impl/OCReturnStatementImpl", "<init>"));
        }
        super(astNode);
    }
    
    @Nullable
    @Override
    public OCExpression getExpression() {
        return this.findChildByType(OCElementTypes.EXPRESSIONS);
    }
    
    @Override
    public void accept(@NotNull final OCVisitor ocVisitor) {
        try {
            if (ocVisitor == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "visitor", "com/jetbrains/cidr/lang/psi/impl/OCReturnStatementImpl", "accept"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        ocVisitor.visitReturnStatement(this);
    }
    
    private static IllegalArgumentException a(final IllegalArgumentException ex) {
        return ex;
    }
}
