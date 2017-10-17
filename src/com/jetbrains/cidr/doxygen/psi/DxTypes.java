// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.doxygen.psi;

import com.jetbrains.cidr.doxygen.psi.impl.DxParamIdImpl;
import com.jetbrains.cidr.doxygen.psi.impl.DxParamImpl;
import com.jetbrains.cidr.doxygen.psi.impl.DxDocTagImpl;
import com.jetbrains.cidr.doxygen.psi.impl.DxDocCommentImpl;
import com.intellij.psi.PsiElement;
import com.intellij.lang.ASTNode;
import com.intellij.psi.tree.IElementType;

public interface DxTypes
{
    public static final IElementType DOC_COMMENT = new DxElementType("DOC_COMMENT");
    public static final IElementType DOC_TAG = new DxElementType("DOC_TAG");
    public static final IElementType PARAM = new DxElementType("PARAM");
    public static final IElementType PARAM_ID = new DxElementType("PARAM_ID");
    public static final IElementType DOC_COMMENT_DATA = new DxTokenType("DOC_COMMENT_DATA");
    public static final IElementType DOC_COMMENT_END = new DxTokenType("DOC_COMMENT_END");
    public static final IElementType DOC_COMMENT_START = new DxTokenType("DOC_COMMENT_START");
    public static final IElementType ELLIPSIS = new DxTokenType("ELLIPSIS");
    public static final IElementType EOF_DOC_COMMENT_START = new DxTokenType("EOF_DOC_COMMENT_START");
    public static final IElementType PARAM_SEPARATOR = new DxTokenType("PARAM_SEPARATOR");
    public static final IElementType TAG_NAME = new DxTokenType("TAG_NAME");
    public static final IElementType TAG_OPTION = new DxTokenType("TAG_OPTION");
    public static final IElementType TAG_PARAM = new DxTokenType("TAG_PARAM");
    
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
}
