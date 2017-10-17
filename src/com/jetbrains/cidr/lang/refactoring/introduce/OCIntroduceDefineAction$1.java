// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.lang.refactoring.introduce;

import java.util.Collections;
import com.intellij.psi.PsiFile;
import com.intellij.psi.util.PsiTreeUtil;
import com.jetbrains.cidr.lang.psi.OCFile;
import com.intellij.psi.PsiElement;
import org.jetbrains.annotations.NotNull;
import java.util.List;
import com.intellij.openapi.editor.Editor;
import com.intellij.openapi.project.Project;
import com.jetbrains.cidr.lang.psi.OCElement;

class OCIntroduceDefineAction$1 extends OCBaseIntroduceHandler<OCElement> {
    @Override
    protected OCDefineInplaceIntroducer createIntroducer(final Project project, final Editor editor, final OCElement ocElement, final List<OCElement> list, final String s) {
        return new OCDefineInplaceIntroducer(project, editor, ocElement, list, s);
    }
    
    @NotNull
    @Override
    protected String getFeatureID() {
        String s;
        try {
            s = "refactoring.introduceDefine";
            if (s == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/refactoring/introduce/OCIntroduceDefineAction$1", "getFeatureID"));
            }
        }
        catch (IllegalStateException ex) {
            throw a(ex);
        }
        return s;
    }
    
    public PsiElement getElementScope(final OCElement ocElement) {
        return PsiTreeUtil.getParentOfType((PsiElement)ocElement, (Class)OCFile.class);
    }
    
    @Override
    protected List<OCElement> collectElements(final Editor editor, final PsiFile psiFile) {
        final List<OCElement> collectElements = super.collectElements(editor, psiFile);
        try {
            if (collectElements.isEmpty()) {
                return collectElements;
            }
        }
        catch (IllegalStateException ex) {
            throw a(ex);
        }
        return Collections.singletonList(collectElements.get(0));
    }
    
    private static IllegalStateException a(final IllegalStateException ex) {
        return ex;
    }
}