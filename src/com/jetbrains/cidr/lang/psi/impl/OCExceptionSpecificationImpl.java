// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.lang.psi.impl;

import org.jetbrains.annotations.NotNull;
import com.intellij.lang.ASTNode;

public class OCExceptionSpecificationImpl extends OCElementBase
{
    public OCExceptionSpecificationImpl(@NotNull final ASTNode astNode) {
        if (astNode == null) {
            throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "node", "com/jetbrains/cidr/lang/psi/impl/OCExceptionSpecificationImpl", "<init>"));
        }
        super(astNode);
    }
}
