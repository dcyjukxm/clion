// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.lang.quickfixes;

import com.jetbrains.cidr.lang.psi.OCReferenceElement;
import com.intellij.openapi.util.TextRange;
import com.intellij.psi.PsiFile;
import com.intellij.openapi.project.Project;
import com.jetbrains.cidr.lang.psi.visitors.OCRecursiveVisitor;

static final class OCImportSymbolFix$3 extends OCRecursiveVisitor {
    final /* synthetic */ Project val$project;
    final /* synthetic */ PsiFile val$containingFile;
    
    @Override
    public void visitReferenceElement(final OCReferenceElement ocReferenceElement) {
        new OCImportSymbolFix(ocReferenceElement).fixFirstItem(this.val$project, this.val$containingFile);
    }
}