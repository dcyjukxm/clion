// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.lang.parser;

import com.intellij.psi.PsiElement;
import com.intellij.openapi.util.TextRange;
import com.jetbrains.cidr.lang.psi.visitors.OCRecursiveVisitor;

static final class OCParser$1 extends OCRecursiveVisitor {
    @Override
    protected TextRange getTextRange(final PsiElement psiElement) {
        return psiElement.getTextRange();
    }
}