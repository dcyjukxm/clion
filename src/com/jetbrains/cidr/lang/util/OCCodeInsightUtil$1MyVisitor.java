// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.lang.util;

import com.intellij.openapi.editor.Editor;
import com.jetbrains.cidr.lang.psi.OCBlockStatement;
import com.intellij.ide.util.EditorHelper;
import com.intellij.psi.util.PsiTreeUtil;
import com.jetbrains.cidr.lang.psi.OCCallable;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiFile;
import com.intellij.openapi.util.Condition;
import com.intellij.openapi.util.TextRange;
import com.jetbrains.cidr.lang.psi.visitors.OCRecursiveVisitor;

class 1MyVisitor extends OCRecursiveVisitor
{
    public boolean found;
    private final TextRange myRange;
    final /* synthetic */ Condition val$condition;
    final /* synthetic */ PsiFile val$file;
    
    public 1MyVisitor(final TextRange myRange, final Condition val$condition, final TextRange val$file) {
        this.val$condition = val$condition;
        this.val$file = (PsiFile)val$file;
        super(myRange);
        this.found = false;
        this.myRange = myRange;
    }
    
    @Override
    public void visitElement(final PsiElement psiElement) {
        if (!this.found) {
            if (!this.myRange.intersectsStrict(psiElement.getTextRange())) {
                return;
            }
            final OCCallable ocCallable = (OCCallable)PsiTreeUtil.getContextOfType(psiElement, false, new Class[] { OCCallable.class });
            if (ocCallable != null) {
                final OCBlockStatement body = ocCallable.getBody();
                if (body != null && this.val$condition.value((Object)body)) {
                    this.found = true;
                    final Editor openInEditor = EditorHelper.openInEditor((PsiElement)this.val$file);
                    if (openInEditor != null) {
                        OCCodeInsightUtil.selectBody(openInEditor, body);
                    }
                }
            }
        }
        super.visitElement(psiElement);
    }
}
