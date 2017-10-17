// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.lang.parser;

import com.intellij.lang.PsiBuilder;
import com.intellij.lang.ASTNode;
import com.intellij.lang.Language;
import com.jetbrains.cidr.lang.OCLanguage;
import com.intellij.psi.tree.IFileElementType;

public class OCFileElementType extends IFileElementType implements OCRootElementType
{
    public OCFileElementType() {
        super((Language)OCLanguage.getInstance());
    }
    
    protected OCFileElementType(final String s) {
        super(s, (Language)OCLanguage.getInstance());
    }
    
    public ASTNode parseContents(final ASTNode astNode) {
        return OCParser.getInstance().parse(this, astNode);
    }
    
    public ASTNode doParse(final ASTNode astNode, final PsiBuilder psiBuilder) {
        return OCParser.getInstance().parse(astNode.getElementType(), psiBuilder);
    }
}
