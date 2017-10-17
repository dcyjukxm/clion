// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.lang.psi.impl;

import com.jetbrains.cidr.lang.types.OCObjectTypeContext;
import com.jetbrains.cidr.lang.types.OCIntType;
import com.jetbrains.cidr.lang.types.OCType;
import com.jetbrains.cidr.lang.symbols.OCResolveContext;
import org.jetbrains.annotations.NotNull;
import com.intellij.lang.ASTNode;
import com.jetbrains.cidr.lang.psi.OCExpression;

public class OCDefinedDirectiveImpl extends OCElementBase implements OCExpression
{
    public OCDefinedDirectiveImpl(@NotNull final ASTNode astNode) {
        if (astNode == null) {
            throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "node", "com/jetbrains/cidr/lang/psi/impl/OCDefinedDirectiveImpl", "<init>"));
        }
        super(astNode);
    }
    
    @NotNull
    @Override
    public OCType getType(@NotNull final OCResolveContext ocResolveContext) {
        try {
            if (ocResolveContext == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "context", "com/jetbrains/cidr/lang/psi/impl/OCDefinedDirectiveImpl", "getType"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        OCIntType int1;
        try {
            int1 = OCIntType.INT;
            if (int1 == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/psi/impl/OCDefinedDirectiveImpl", "getType"));
            }
        }
        catch (IllegalArgumentException ex2) {
            throw a(ex2);
        }
        return int1;
    }
    
    @NotNull
    @Override
    public OCType getType() {
        OCIntType int1;
        try {
            int1 = OCIntType.INT;
            if (int1 == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/psi/impl/OCDefinedDirectiveImpl", "getType"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        return int1;
    }
    
    @NotNull
    @Override
    public OCType getResolvedType() {
        OCType type;
        try {
            type = this.getType();
            if (type == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/psi/impl/OCDefinedDirectiveImpl", "getResolvedType"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        return type;
    }
    
    @NotNull
    @Override
    public OCType getResolvedType(@NotNull final OCResolveContext ocResolveContext) {
        try {
            if (ocResolveContext == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "context", "com/jetbrains/cidr/lang/psi/impl/OCDefinedDirectiveImpl", "getResolvedType"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        OCType type;
        try {
            type = this.getType(ocResolveContext);
            if (type == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/psi/impl/OCDefinedDirectiveImpl", "getResolvedType"));
            }
        }
        catch (IllegalArgumentException ex2) {
            throw a(ex2);
        }
        return type;
    }
    
    @NotNull
    @Override
    public String findBestTypeName(final OCType ocType) {
        String name;
        try {
            name = ocType.getName();
            if (name == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/psi/impl/OCDefinedDirectiveImpl", "findBestTypeName"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        return name;
    }
    
    @Override
    public OCObjectTypeContext getTypeContext() {
        return null;
    }
    
    @Override
    public OCObjectTypeContext getTypeContext(final boolean b, final boolean b2) {
        return null;
    }
    
    @Override
    public OCObjectTypeContext getTypeContext(final OCExpression ocExpression, final OCType ocType, final OCType ocType2, final boolean b, final boolean b2) {
        return null;
    }
    
    private static IllegalArgumentException a(final IllegalArgumentException ex) {
        return ex;
    }
}
