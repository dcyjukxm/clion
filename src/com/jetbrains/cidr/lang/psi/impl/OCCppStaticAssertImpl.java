// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.lang.psi.impl;

import org.jetbrains.annotations.Nullable;
import com.intellij.psi.tree.IElementType;
import com.jetbrains.cidr.lang.parser.OCElementTypes;
import com.jetbrains.cidr.lang.psi.OCArgumentList;
import com.jetbrains.cidr.lang.psi.visitors.OCVisitor;
import org.jetbrains.annotations.NotNull;
import com.intellij.lang.ASTNode;
import com.jetbrains.cidr.lang.psi.OCCppStaticAssert;

public class OCCppStaticAssertImpl extends OCElementBase implements OCCppStaticAssert
{
    public OCCppStaticAssertImpl(@NotNull final ASTNode astNode) {
        if (astNode == null) {
            throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "node", "com/jetbrains/cidr/lang/psi/impl/OCCppStaticAssertImpl", "<init>"));
        }
        super(astNode);
    }
    
    @Override
    public void accept(@NotNull final OCVisitor ocVisitor) {
        try {
            if (ocVisitor == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "visitor", "com/jetbrains/cidr/lang/psi/impl/OCCppStaticAssertImpl", "accept"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        ocVisitor.visitStaticAssert(this);
    }
    
    @Nullable
    @Override
    public OCArgumentList getArgumentList() {
        return this.findChildByType(OCElementTypes.ARGUMENT_LIST);
    }
    
    private static IllegalArgumentException a(final IllegalArgumentException ex) {
        return ex;
    }
}
