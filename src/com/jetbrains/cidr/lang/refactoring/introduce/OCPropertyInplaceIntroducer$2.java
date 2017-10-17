// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.lang.refactoring.introduce;

import com.jetbrains.cidr.lang.psi.OCPropertyAttributesList;
import com.jetbrains.cidr.lang.refactoring.util.OCChangeUtil;
import com.intellij.psi.PsiElement;
import com.jetbrains.cidr.lang.util.OCElementFactory;
import com.intellij.psi.util.PsiTreeUtil;
import com.jetbrains.cidr.lang.psi.OCProperty;
import org.jetbrains.annotations.NotNull;
import com.intellij.openapi.application.Result;
import com.intellij.psi.PsiFile;
import com.intellij.openapi.project.Project;
import com.jetbrains.cidr.lang.symbols.objc.OCPropertySymbol;
import com.intellij.openapi.command.WriteCommandAction;

class OCPropertyInplaceIntroducer$2 extends WriteCommandAction {
    final /* synthetic */ OCPropertySymbol.PropertySemantics val$semantics;
    
    protected void run(@NotNull final Result result) throws Throwable {
        try {
            if (result == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "result", "com/jetbrains/cidr/lang/refactoring/introduce/OCPropertyInplaceIntroducer$2", "run"));
            }
        }
        catch (Throwable t) {
            throw a(t);
        }
        final OCProperty ocProperty = (OCProperty)PsiTreeUtil.getParentOfType(((OCBaseInplaceIntroducer<PsiElement, E>)OCPropertyInplaceIntroducer.this).getVariable(), (Class)OCProperty.class);
        if (ocProperty != null) {
            final OCProperty propertyDeclaration = OCElementFactory.propertyDeclaration("pr", OCPropertyInplaceIntroducer.this.myExprType, (PsiElement)ocProperty, this.val$semantics, OCPropertyInplaceIntroducer.this.myReadonly);
            final OCPropertyAttributesList propertyAttributesList = ocProperty.getPropertyAttributesList();
            try {
                if (propertyAttributesList != null) {
                    OCChangeUtil.replaceHandlingMacros((PsiElement)propertyAttributesList, (PsiElement)propertyDeclaration.getPropertyAttributesList());
                }
            }
            catch (Throwable t2) {
                throw a(t2);
            }
        }
    }
    
    private static Throwable a(final Throwable t) {
        return t;
    }
}