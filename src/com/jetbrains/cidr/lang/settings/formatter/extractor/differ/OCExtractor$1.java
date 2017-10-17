// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.lang.settings.formatter.extractor.differ;

import com.intellij.lang.ASTNode;
import com.jetbrains.cidr.lang.psi.OCCodeFragment;
import com.intellij.openapi.command.WriteCommandAction;
import com.intellij.psi.impl.source.codeStyle.CodeFormatterFacade;
import com.intellij.psi.impl.source.SourceTreeToPsiMap;
import com.intellij.psi.PsiElement;
import com.jetbrains.cidr.lang.util.OCElementFactory;
import com.intellij.psi.codeStyle.CodeStyleSettings;
import com.intellij.psi.PsiFile;
import com.intellij.openapi.project.Project;
import com.intellij.psi.codeStyle.extractor.differ.DifferBase;

class OCExtractor$1 extends DifferBase {
    static final /* synthetic */ boolean $assertionsDisabled;
    
    public String reformattedText() {
        final OCCodeFragment codeFragment = OCElementFactory.codeFragment(this.myOrigText, this.myProject, (PsiElement)this.myFile, false, false);
        final OCCodeFragment psiElement;
        final ASTNode element;
        WriteCommandAction.runWriteCommandAction(this.myProject, "CodeStyleSettings extractor", "CodeStyleSettings extractor", () -> {
            SourceTreeToPsiMap.psiElementToTree((PsiElement)psiElement);
            if (!OCExtractor$1.$assertionsDisabled && element == null) {
                throw new AssertionError();
            }
            else {
                SourceTreeToPsiMap.treeElementToPsi(new CodeFormatterFacade(this.mySettings, psiElement.getLanguage()).processElement(element));
                return;
            }
        }, new PsiFile[] { codeFragment });
        return codeFragment.getText();
    }
}