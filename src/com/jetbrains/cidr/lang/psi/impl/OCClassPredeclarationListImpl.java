// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.lang.psi.impl;

import com.intellij.psi.tree.IElementType;
import com.jetbrains.cidr.lang.parser.OCElementTypes;
import com.jetbrains.cidr.lang.psi.OCClassPredeclaration;
import java.util.List;
import org.jetbrains.annotations.NotNull;
import com.intellij.lang.ASTNode;
import com.jetbrains.cidr.lang.psi.OCClassPredeclarationList;

public class OCClassPredeclarationListImpl extends OCElementBase implements OCClassPredeclarationList
{
    public OCClassPredeclarationListImpl(@NotNull final ASTNode astNode) {
        if (astNode == null) {
            throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "node", "com/jetbrains/cidr/lang/psi/impl/OCClassPredeclarationListImpl", "<init>"));
        }
        super(astNode);
    }
    
    @Override
    public List<OCClassPredeclaration> getPredeclarations() {
        return this.findChildrenByType(OCElementTypes.CLASS_PREDEF);
    }
}
