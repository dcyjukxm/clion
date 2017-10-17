// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.lang.psi.impl;

import org.jetbrains.annotations.Nullable;
import com.intellij.psi.tree.IElementType;
import com.jetbrains.cidr.lang.parser.OCElementTypes;
import com.jetbrains.cidr.lang.psi.OCReferenceElement;
import org.jetbrains.annotations.NotNull;
import com.intellij.lang.ASTNode;
import com.jetbrains.cidr.lang.psi.OCCppBaseClause;

public class OCCppBaseClauseImpl extends OCElementBase implements OCCppBaseClause
{
    public OCCppBaseClauseImpl(@NotNull final ASTNode astNode) {
        if (astNode == null) {
            throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "node", "com/jetbrains/cidr/lang/psi/impl/OCCppBaseClauseImpl", "<init>"));
        }
        super(astNode);
    }
    
    @Nullable
    @Override
    public OCReferenceElement getReferenceElement() {
        return this.findChildByType(OCElementTypes.REFERENCE_ELEMENT);
    }
}
