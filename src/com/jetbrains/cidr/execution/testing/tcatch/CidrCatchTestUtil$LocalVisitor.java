// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.execution.testing.tcatch;

import com.jetbrains.cidr.lang.psi.OCCallExpression;
import com.intellij.psi.PsiElement;
import com.jetbrains.cidr.lang.psi.OCReferenceElement;
import com.jetbrains.cidr.lang.psi.visitors.OCRecursiveVisitor;
import com.jetbrains.cidr.lang.psi.OCDeclaration;
import java.util.List;
import com.intellij.openapi.util.text.StringUtil;
import java.util.function.Consumer;
import org.jetbrains.annotations.NotNull;

private static class LocalVisitor extends GlobalVisitor
{
    final String myStructName;
    final String myFunctionTestName;
    
    public LocalVisitor(@NotNull final String s, final Consumer<CidrCatchTestCache> consumer) {
        if (s == null) {
            throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "testOwnerName", "com/jetbrains/cidr/execution/testing/tcatch/CidrCatchTestUtil$LocalVisitor", "<init>"));
        }
        super(consumer);
        final List split = StringUtil.split(s, "::");
        try {
            if (split.size() == 2) {
                this.myStructName = split.get(0);
                this.myFunctionTestName = split.get(1);
                return;
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        try {
            if (split.size() == 1) {
                this.myStructName = null;
                this.myFunctionTestName = split.get(0);
                return;
            }
        }
        catch (IllegalArgumentException ex2) {
            throw a(ex2);
        }
        this.myStructName = null;
        this.myFunctionTestName = null;
    }
    
    @Override
    protected OCRecursiveVisitor createArgumentVisiter(final OCDeclaration ocDeclaration) {
        return new BaseArgVisitor(ocDeclaration, this.myDeclaratorConsumer) {
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
        };
    }
    
    private static IllegalArgumentException a(final IllegalArgumentException ex) {
        return ex;
    }
}
