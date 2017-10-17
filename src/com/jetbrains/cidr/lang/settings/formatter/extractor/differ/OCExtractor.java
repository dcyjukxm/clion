// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.lang.settings.formatter.extractor.differ;

import com.intellij.psi.codeStyle.extractor.differ.Differ;
import java.util.LinkedList;
import com.intellij.psi.codeStyle.extractor.values.Value;
import java.util.Collection;
import org.jetbrains.annotations.NotNull;
import com.intellij.lang.ASTNode;
import com.jetbrains.cidr.lang.psi.OCCodeFragment;
import com.intellij.openapi.command.WriteCommandAction;
import com.intellij.psi.impl.source.codeStyle.CodeFormatterFacade;
import com.intellij.psi.impl.source.SourceTreeToPsiMap;
import com.intellij.psi.PsiElement;
import com.jetbrains.cidr.lang.util.OCElementFactory;
import com.intellij.psi.codeStyle.extractor.differ.DifferBase;
import com.intellij.psi.codeStyle.CodeStyleSettings;
import com.intellij.psi.PsiFile;
import com.intellij.openapi.project.Project;
import com.intellij.psi.codeStyle.extractor.differ.LangCodeStyleExtractor;

public class OCExtractor implements LangCodeStyleExtractor
{
    @NotNull
    public DifferBase getDiffer(final Project project, final PsiFile psiFile, final CodeStyleSettings codeStyleSettings) {
        DifferBase differBase;
        try {
            differBase = new DifferBase(project, psiFile, codeStyleSettings) {
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
            };
            if (differBase == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/settings/formatter/extractor/differ/OCExtractor", "getDiffer"));
            }
        }
        catch (IllegalStateException ex) {
            throw a(ex);
        }
        return differBase;
    }
    
    @NotNull
    public Collection<Value.VAR_KIND> getCustomVarKinds() {
        LinkedList list;
        try {
            list = new LinkedList<Value.VAR_KIND>();
            if (list == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/settings/formatter/extractor/differ/OCExtractor", "getCustomVarKinds"));
            }
        }
        catch (IllegalStateException ex) {
            throw a(ex);
        }
        return (Collection<Value.VAR_KIND>)list;
    }
    
    private static IllegalStateException a(final IllegalStateException ex) {
        return ex;
    }
}
