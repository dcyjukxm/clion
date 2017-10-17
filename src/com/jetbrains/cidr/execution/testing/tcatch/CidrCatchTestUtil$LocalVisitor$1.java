// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.execution.testing.tcatch;

import com.jetbrains.cidr.lang.psi.OCCallExpression;
import com.intellij.psi.PsiElement;
import com.jetbrains.cidr.lang.psi.OCReferenceElement;
import java.util.function.Consumer;
import com.jetbrains.cidr.lang.psi.OCDeclaration;

class CidrCatchTestUtil$LocalVisitor$1 extends BaseArgVisitor {
    boolean found = false;
    
    @Override
    public void visitReferenceElement(final OCReferenceElement ocReferenceElement) {
        final PsiElement nameIdentifier = ocReferenceElement.getNameIdentifier();
        if (nameIdentifier != null && nameIdentifier.getText().equals(LocalVisitor.this.myFunctionTestName)) {
            if (LocalVisitor.this.myStructName == null) {
                this.found = true;
            }
            else if (ocReferenceElement.getNamespaceQualifier() != null && ocReferenceElement.getNamespaceQualifier().getName() != null) {
                this.found = ocReferenceElement.getNamespaceQualifier().getName().equals(LocalVisitor.this.myStructName);
            }
        }
    }
    
    @Override
    public void visitCallExpression(final OCCallExpression ocCallExpression) {
        if (this.found) {
            super.visitCallExpression(ocCallExpression);
        }
    }
}