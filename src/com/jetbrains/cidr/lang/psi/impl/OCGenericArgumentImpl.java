// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.lang.psi.impl;

import org.jetbrains.annotations.Nullable;
import com.intellij.psi.tree.IElementType;
import com.jetbrains.cidr.lang.parser.OCElementTypes;
import com.jetbrains.cidr.lang.symbols.DeepEqual;
import com.jetbrains.cidr.lang.psi.OCTypeElement;
import com.jetbrains.cidr.lang.symbols.OCResolveContext;
import com.jetbrains.cidr.lang.types.OCType;
import com.jetbrains.cidr.lang.psi.visitors.OCVisitor;
import org.jetbrains.annotations.NotNull;
import com.intellij.lang.ASTNode;
import com.jetbrains.cidr.lang.psi.OCGenericArgument;

public class OCGenericArgumentImpl extends OCElementBase implements OCGenericArgument
{
    public OCGenericArgumentImpl(@NotNull final ASTNode astNode) {
        if (astNode == null) {
            throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "node", "com/jetbrains/cidr/lang/psi/impl/OCGenericArgumentImpl", "<init>"));
        }
        super(astNode);
    }
    
    @Override
    public void accept(@NotNull final OCVisitor ocVisitor) {
        try {
            if (ocVisitor == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "visitor", "com/jetbrains/cidr/lang/psi/impl/OCGenericArgumentImpl", "accept"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        ocVisitor.visitGenericArgument(this);
    }
    
    @Override
    public String getNameForPresentation(final OCType.Presentation presentation, @NotNull final OCResolveContext ocResolveContext, final boolean b, final int n) {
        try {
            if (ocResolveContext == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "context", "com/jetbrains/cidr/lang/psi/impl/OCGenericArgumentImpl", "getNameForPresentation"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        final OCTypeElement typeElement = this.getTypeElement();
        try {
            if (typeElement != null) {
                return typeElement.getType().getNameForPresentation(presentation, ocResolveContext, b, n);
            }
        }
        catch (IllegalArgumentException ex2) {
            throw a(ex2);
        }
        return "";
    }
    
    @Override
    public boolean equals(final Object o, @NotNull final OCResolveContext ocResolveContext) {
        try {
            if (ocResolveContext == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "context", "com/jetbrains/cidr/lang/psi/impl/OCGenericArgumentImpl", "equals"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        final OCTypeElement typeElement = this.getTypeElement();
        Label_0074: {
            try {
                if (typeElement == null) {
                    return false;
                }
                final OCTypeElement ocTypeElement = typeElement;
                final OCType ocType = ocTypeElement.getType();
                final Object o2 = o;
                final OCResolveContext ocResolveContext2 = ocResolveContext;
                final boolean b = ocType.equals(o2, ocResolveContext2);
                if (b) {
                    break Label_0074;
                }
                return false;
            }
            catch (IllegalArgumentException ex2) {
                throw a(ex2);
            }
            try {
                final OCTypeElement ocTypeElement = typeElement;
                final OCType ocType = ocTypeElement.getType();
                final Object o2 = o;
                final OCResolveContext ocResolveContext2 = ocResolveContext;
                final boolean b = ocType.equals(o2, ocResolveContext2);
                if (b) {
                    return true;
                }
            }
            catch (IllegalArgumentException ex3) {
                throw a(ex3);
            }
        }
        return false;
    }
    
    public boolean deepEqualStep(@NotNull final DeepEqual.Comparator comparator, @NotNull final Object o, @NotNull final Object o2) {
        try {
            if (comparator == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "c", "com/jetbrains/cidr/lang/psi/impl/OCGenericArgumentImpl", "deepEqualStep"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        try {
            if (o == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "first", "com/jetbrains/cidr/lang/psi/impl/OCGenericArgumentImpl", "deepEqualStep"));
            }
        }
        catch (IllegalArgumentException ex2) {
            throw a(ex2);
        }
        try {
            if (o2 == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "second", "com/jetbrains/cidr/lang/psi/impl/OCGenericArgumentImpl", "deepEqualStep"));
            }
        }
        catch (IllegalArgumentException ex3) {
            throw a(ex3);
        }
        final OCTypeElement typeElement = this.getTypeElement();
        Label_0166: {
            try {
                if (typeElement == null) {
                    return false;
                }
                final OCTypeElement ocTypeElement = typeElement;
                final OCType ocType = ocTypeElement.getType();
                final DeepEqual.Comparator comparator2 = comparator;
                final Object o3 = o;
                final Object o4 = o2;
                final boolean b = ocType.deepEqualStep(comparator2, o3, o4);
                if (b) {
                    break Label_0166;
                }
                return false;
            }
            catch (IllegalArgumentException ex4) {
                throw a(ex4);
            }
            try {
                final OCTypeElement ocTypeElement = typeElement;
                final OCType ocType = ocTypeElement.getType();
                final DeepEqual.Comparator comparator2 = comparator;
                final Object o3 = o;
                final Object o4 = o2;
                final boolean b = ocType.deepEqualStep(comparator2, o3, o4);
                if (b) {
                    return true;
                }
            }
            catch (IllegalArgumentException ex5) {
                throw a(ex5);
            }
        }
        return false;
    }
    
    @Nullable
    @Override
    public OCTypeElement getTypeElement() {
        return this.findChildByType(OCElementTypes.TYPE_ELEMENT);
    }
    
    @Override
    public boolean isVariadic() {
        return false;
    }
    
    private static IllegalArgumentException a(final IllegalArgumentException ex) {
        return ex;
    }
}
