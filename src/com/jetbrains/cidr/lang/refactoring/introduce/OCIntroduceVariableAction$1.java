// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.lang.refactoring.introduce;

import com.intellij.psi.PsiElement;
import org.jetbrains.annotations.NotNull;
import java.util.List;
import com.intellij.openapi.editor.Editor;
import com.intellij.openapi.project.Project;
import com.jetbrains.cidr.lang.psi.OCExpression;

class OCIntroduceVariableAction$1 extends OCBaseIntroduceHandler<OCExpression> {
    @Override
    protected OCVariableInplaceIntroducer createIntroducer(final Project project, final Editor editor, final OCExpression ocExpression, final List<OCExpression> list, final String s) {
        return new OCVariableInplaceIntroducer(project, editor, ocExpression, list, s);
    }
    
    @NotNull
    @Override
    protected String getFeatureID() {
        String s;
        try {
            s = "refactoring.appCodeIntroduceVariable";
            if (s == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/refactoring/introduce/OCIntroduceVariableAction$1", "getFeatureID"));
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