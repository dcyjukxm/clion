// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.lang.refactoring.introduce;

import com.jetbrains.cidr.lang.OCLanguageKind;
import org.jetbrains.annotations.NotNull;
import java.util.List;
import com.intellij.psi.PsiElement;
import com.intellij.openapi.editor.Editor;
import com.intellij.openapi.project.Project;
import com.intellij.refactoring.RefactoringActionHandler;

public class OCIntroducePropertyAction extends OCBaseIntroduceAction
{
    @Override
    public RefactoringActionHandler getHandler() {
        return (RefactoringActionHandler)new OCBaseLocalConvertibleHandler("Introduce Property") {
            @Override
            protected OCPropertyInplaceIntroducer createIntroducer(final Project project, final Editor editor, final PsiElement psiElement, final List<PsiElement> list, final String s) {
                return new OCPropertyInplaceIntroducer(project, editor, psiElement, list, s);
            }
            
            @NotNull
            @Override
            protected String getFeatureID() {
                String s;
                try {
                    s = "refactoring.introduceProperty";
                    if (s == null) {
                        throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/refactoring/introduce/OCIntroducePropertyAction$1", "getFeatureID"));
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
        };
    }
    
    @NotNull
    @Override
    protected OCLanguageKind getRequiredLanguageKind() {
        OCLanguageKind obj_C;
        try {
            obj_C = OCLanguageKind.OBJ_C;
            if (obj_C == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/refactoring/introduce/OCIntroducePropertyAction", "getRequiredLanguageKind"));
            }
        }
        catch (IllegalStateException ex) {
            throw a(ex);
        }
        return obj_C;
    }
    
    private static IllegalStateException a(final IllegalStateException ex) {
        return ex;
    }
}
