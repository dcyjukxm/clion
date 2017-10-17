// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.lang.parser;

import com.intellij.lang.PsiBuilder;
import com.intellij.lang.ASTNode;

public class OCExpressionCodeFragmentType extends OCFileElementType
{
    protected OCExpressionCodeFragmentType(final String s) {
        super(s);
    }
    
    @Override
    public ASTNode doParse(final ASTNode astNode, final PsiBuilder psiBuilder) {
        return new OCParsing(psiBuilder, astNode.getElementType()).parseExpressionText();
    }
}
