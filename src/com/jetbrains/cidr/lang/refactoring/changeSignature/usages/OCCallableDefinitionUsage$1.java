// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.lang.refactoring.changeSignature.usages;

import com.jetbrains.cidr.lang.psi.OCBlockStatement;
import com.intellij.psi.PsiElement;
import com.jetbrains.cidr.lang.quickfixes.OCImportSymbolFix;
import com.jetbrains.cidr.lang.psi.OCReferenceElement;
import com.jetbrains.cidr.lang.psi.visitors.OCRecursiveVisitor;

class OCCallableDefinitionUsage$1 extends OCRecursiveVisitor {
    @Override
    public void visitReferenceElement(final OCReferenceElement ocReferenceElement) {
        OCImportSymbolFix.fixAllSymbolsRecursively((PsiElement)ocReferenceElement);
    }
    
    @Override
    public void visitBlockStatement(final OCBlockStatement ocBlockStatement) {
    }
}