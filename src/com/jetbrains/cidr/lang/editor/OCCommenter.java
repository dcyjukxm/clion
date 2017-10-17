// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.lang.editor;

import com.intellij.psi.PsiComment;
import com.jetbrains.cidr.lang.parser.OCTokenTypes;
import com.intellij.psi.tree.IElementType;
import com.jetbrains.cidr.lang.parser.OCElementType;
import com.intellij.lang.CodeDocumentationAwareCommenter;

public class OCCommenter implements CodeDocumentationAwareCommenter
{
    public static final OCElementType COMMENT_TOKEN_TYPE;
    public static final String BLOCK_COMMENT_PREFIX = "/*";
    public static final String BLOCK_COMMENT_SUFFIX = "*/";
    public static final String DOCUMENTATION_COMMENT_PREFIX = "/**";
    public static final String DOCUMENTATION_COMMENT_PREFIX2 = "/*!";
    public static final String EOL_DOCUMENTATION_COMMENT_PREFIX = "///";
    public static final String EOL_DOCUMENTATION_COMMENT_PREFIX2 = "//!";
    public static final String DOCUMENTATION_COMMENT_SUFFIX = "*/";
    public static final String DOCUMENTATION_COMMENT_LINE_PREFIX = "*";
    
    public String getLineCommentPrefix() {
        return "//";
    }
    
    public String getBlockCommentPrefix() {
        return "/*";
    }
    
    public String getBlockCommentSuffix() {
        return "*/";
    }
    
    public String getCommentedBlockCommentPrefix() {
        return null;
    }
    
    public String getCommentedBlockCommentSuffix() {
        return null;
    }
    
    public IElementType getLineCommentTokenType() {
        return OCTokenTypes.EOL_COMMENT;
    }
    
    public IElementType getBlockCommentTokenType() {
        return OCCommenter.COMMENT_TOKEN_TYPE;
    }
    
    public IElementType getDocumentationCommentTokenType() {
        return null;
    }
    
    public String getDocumentationCommentPrefix() {
        return "/**";
    }
    
    public String getDocumentationCommentLinePrefix() {
        return "*";
    }
    
    public String getDocumentationCommentSuffix() {
        return "*/";
    }
    
    public boolean isDocumentationComment(final PsiComment psiComment) {
        return false;
    }
    
    static {
        COMMENT_TOKEN_TYPE = OCTokenTypes.BLOCK_COMMENT;
    }
}
