// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.cpp.cmake.formatting;

import org.jetbrains.annotations.Nullable;
import com.intellij.openapi.util.TextRange;
import com.intellij.lang.ASTNode;
import org.jetbrains.annotations.NotNull;
import com.intellij.psi.PsiFile;
import com.intellij.formatting.Block;
import com.intellij.psi.formatter.DocumentBasedFormattingModel;
import com.intellij.lang.Language;
import com.jetbrains.cidr.cpp.cmake.CMakeLanguage;
import com.intellij.formatting.FormattingModel;
import com.intellij.psi.codeStyle.CodeStyleSettings;
import com.intellij.psi.PsiElement;
import com.intellij.formatting.FormattingModelBuilder;

public class CMakeFormattingModelBuilder implements FormattingModelBuilder
{
    @NotNull
    public FormattingModel createModel(final PsiElement psiElement, final CodeStyleSettings codeStyleSettings) {
        final PsiFile containingFile = psiElement.getContainingFile();
        DocumentBasedFormattingModel documentBasedFormattingModel;
        try {
            documentBasedFormattingModel = new DocumentBasedFormattingModel((Block)new CMakeCodeBlock(containingFile, codeStyleSettings.getCommonSettings((Language)CMakeLanguage.INSTANCE)), containingFile.getProject(), codeStyleSettings, containingFile.getFileType(), containingFile);
            if (documentBasedFormattingModel == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/cpp/cmake/formatting/CMakeFormattingModelBuilder", "createModel"));
            }
        }
        catch (IllegalStateException ex) {
            throw a(ex);
        }
        return (FormattingModel)documentBasedFormattingModel;
    }
    
    @Nullable
    public TextRange getRangeAffectingIndent(final PsiFile psiFile, final int n, final ASTNode astNode) {
        return null;
    }
    
    private static IllegalStateException a(final IllegalStateException ex) {
        return ex;
    }
}
