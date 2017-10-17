// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.lang.refactoring.introduce;

import com.intellij.psi.util.PsiTreeUtil;
import com.jetbrains.cidr.lang.psi.OCFile;
import com.jetbrains.cidr.lang.psi.OCCppNamespace;
import com.intellij.psi.PsiElement;
import org.jetbrains.annotations.NotNull;
import java.util.List;
import com.intellij.openapi.editor.Editor;
import com.intellij.openapi.project.Project;
import com.jetbrains.cidr.lang.psi.OCExpression;

class OCIntroduceConstantAction$1 extends OCBaseIntroduceHandler<OCExpression> {
    @Override
    protected OCConstantInplaceIntroducer createIntroducer(final Project project, final Editor editor, final OCExpression ocExpression, final List<OCExpression> list, final String s) {
        return new OCConstantInplaceIntroducer(project, editor, ocExpression, list, s);
    }
    
    @NotNull
    @Override
    protected String getFeatureID() {
        String s;
        try {
            s = "refactoring.introduceConstant";
            if (s == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/refactoring/introduce/OCIntroduceConstantAction$1", "getFeatureID"));
            }
        }
        catch (IllegalStateException ex) {
            throw a(ex);
        }
        return s;
    }
    
    public PsiElement getElementScope(final OCExpression ocExpression) {
        return PsiTreeUtil.getParentOfType((PsiElement)ocExpression, new Class[] { OCCppNamespace.class, OCFile.class });
    }
    
    private static IllegalStateException a(final IllegalStateException ex) {
        return ex;
    }
}