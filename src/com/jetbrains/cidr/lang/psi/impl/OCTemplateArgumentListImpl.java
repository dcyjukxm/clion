// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.lang.psi.impl;

import com.jetbrains.cidr.lang.psi.OCElement;
import java.util.List;
import com.jetbrains.cidr.lang.parser.OCElementTypes;
import com.intellij.psi.tree.IElementType;
import org.jetbrains.annotations.NotNull;
import com.intellij.lang.ASTNode;
import com.intellij.psi.tree.TokenSet;
import com.jetbrains.cidr.lang.psi.OCTemplateArgumentList;

public class OCTemplateArgumentListImpl extends OCElementBase implements OCTemplateArgumentList
{
    private TokenSet TYPE_OR_EXPRESSION;
    
    public OCTemplateArgumentListImpl(@NotNull final ASTNode astNode) {
        if (astNode == null) {
            throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "node", "com/jetbrains/cidr/lang/psi/impl/OCTemplateArgumentListImpl", "<init>"));
        }
        super(astNode);
        this.TYPE_OR_EXPRESSION = TokenSet.orSet(new TokenSet[] { TokenSet.create(new IElementType[] { OCElementTypes.TYPE_ELEMENT }), OCElementTypes.EXPRESSIONS });
    }
    
    @Override
    public List<OCElement> getArguments() {
        return this.findChildrenByType(this.TYPE_OR_EXPRESSION);
    }
}
