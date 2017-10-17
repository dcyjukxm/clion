// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.lang.refactoring.introduce;

import org.jetbrains.annotations.NotNull;
import java.util.List;
import com.intellij.psi.PsiElement;
import com.intellij.openapi.editor.Editor;
import com.intellij.openapi.project.Project;

class OCIntroduceIvarAction$1 extends OCBaseLocalConvertibleHandler {
    @Override
    protected OCIvarInplaceIntroducer createIntroducer(final Project project, final Editor editor, final PsiElement psiElement, final List<PsiElement> list, final String s) {
        return new OCIvarInplaceIntroducer(project, editor, psiElement, list, s);
    }
    
    @NotNull
    @Override
    protected String getFeatureID() {
        String s;
        try {
            s = "refactoring.introduceIvar";
            if (s == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/refactoring/introduce/OCIntroduceIvarAction$1", "getFeatureID"));
            }
        }
        catch (IllegalStateException ex) {
            throw a(ex);
        }
        return s;
    }
    
    private static IllegalStateException a(final IllegalStateException ex) {
        return ex;
    }
}