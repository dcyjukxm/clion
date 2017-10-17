// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.doxygen.psi;

import com.intellij.psi.tree.IElementType;
import com.jetbrains.cidr.doxygen.psi.impl.DxParamIdImpl;
import com.jetbrains.cidr.doxygen.psi.impl.DxParamImpl;
import com.jetbrains.cidr.doxygen.psi.impl.DxDocTagImpl;
import com.jetbrains.cidr.doxygen.psi.impl.DxDocCommentImpl;
import com.intellij.psi.PsiElement;
import com.intellij.lang.ASTNode;

public static class Factory
{
    public static PsiElement createElement(final ASTNode astNode) {
        final IElementType elementType = astNode.getElementType();
        if (elementType == DxTypes.DOC_COMMENT) {
            return (PsiElement)new DxDocCommentImpl(astNode);
        }
        if (elementType == DxTypes.DOC_TAG) {
            return (PsiElement)new DxDocTagImpl(astNode);
        }
        if (elementType == DxTypes.PARAM) {
            return (PsiElement)new DxParamImpl(astNode);
        }
        if (elementType == DxTypes.PARAM_ID) {
            return (PsiElement)new DxParamIdImpl(astNode);
        }
        throw new AssertionError((Object)("Unknown element type: " + elementType));
    }
}
