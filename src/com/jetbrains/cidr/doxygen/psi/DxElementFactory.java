// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.doxygen.psi;

import com.intellij.openapi.fileTypes.FileType;
import com.jetbrains.cidr.doxygen.DxFileType;
import com.intellij.psi.PsiFileFactory;
import org.jetbrains.annotations.Nullable;
import com.intellij.psi.PsiElement;
import com.intellij.psi.util.PsiTreeUtil;
import com.intellij.lang.ASTNode;
import com.intellij.openapi.project.Project;

public class DxElementFactory
{
    private static final String FILE_NAME;
    
    @Nullable
    public static ASTNode createParamNode(final Project project, final String s) {
        final PsiElement childOfType = PsiTreeUtil.findChildOfType((PsiElement)createComment(project, "//! @param " + s), (Class)DxParamId.class);
        if (childOfType != null) {
            return childOfType.getNode().findChildByType(DxTypes.TAG_PARAM);
        }
        return null;
    }
    
    public static DxDocComment createComment(final Project project, final String s) {
        return (DxDocComment)a(project, false, s).getFirstChild();
    }
    
    private static DxFile a(final Project project, final boolean b, final CharSequence charSequence) {
        return (DxFile)PsiFileFactory.getInstance(project).createFileFromText(DxElementFactory.FILE_NAME, (FileType)DxFileType.INSTANCE, charSequence, System.currentTimeMillis(), b);
    }
    
    static {
        FILE_NAME = "DUMMY." + DxFileType.INSTANCE.getDefaultExtension();
    }
}
