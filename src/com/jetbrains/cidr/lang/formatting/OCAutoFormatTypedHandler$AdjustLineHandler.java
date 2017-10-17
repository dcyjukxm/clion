// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.lang.formatting;

import com.intellij.openapi.project.Project;
import com.intellij.lang.ASTNode;
import com.intellij.psi.codeStyle.CodeStyleManager;
import com.jetbrains.cidr.lang.util.OCElementUtil;
import com.intellij.psi.codeStyle.CodeStyleSettings;
import com.intellij.openapi.editor.Editor;
import com.intellij.psi.PsiFile;

public static class AdjustLineHandler implements Handler
{
    @Override
    public boolean handle(final char c, final PsiFile psiFile, final Editor editor, final CodeStyleSettings codeStyleSettings) {
        final ASTNode node = psiFile.findElementAt(editor.getCaretModel().getOffset() - 1).getNode();
        ASTNode treeParent = null;
        ASTNode previousNonWhitespaceOrCommentSibling = null;
        switch (c) {
            case '@': {
                treeParent = node.getTreeParent();
                previousNonWhitespaceOrCommentSibling = node;
                break;
            }
            case ':': {
                treeParent = (previousNonWhitespaceOrCommentSibling = OCFormatterUtil.getPreviousNonWhitespaceOrCommentSibling(node));
                break;
            }
            default: {
                return false;
            }
        }
        ASTNode astNode = null;
        if (treeParent != null && OCElementUtil.isVisibilityKeyword(treeParent)) {
            astNode = previousNonWhitespaceOrCommentSibling;
        }
        else if (c == ':') {
            final ASTNode treeParent2 = node.getTreeParent();
            if (OCFormatterUtil.getLabelType(treeParent2) == OCFormatterUtil.LABEL_TYPE.CASE_LIKE) {
                astNode = treeParent2;
            }
        }
        final Project project = editor.getProject();
        if (astNode != null && project != null) {
            CodeStyleManager.getInstance(project).adjustLineIndent(psiFile, astNode.getStartOffset());
            return true;
        }
        return false;
    }
}
