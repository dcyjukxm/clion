// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.lang.refactoring.introduce;

import com.intellij.openapi.util.TextRange;
import com.intellij.psi.PsiElement;
import com.jetbrains.cidr.lang.util.OCElementUtil;
import com.intellij.psi.PsiFile;
import com.intellij.openapi.editor.Editor;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.util.Pass;

class OCBaseIntroduceHandler$2 extends Pass<E> {
    final /* synthetic */ Project val$project;
    final /* synthetic */ Editor val$editor;
    final /* synthetic */ PsiFile val$file;
    
    public void pass(final E e) {
        final TextRange rangeWithMacros = OCElementUtil.getRangeWithMacros(e);
        OCBaseIntroduceHandler.access$000(OCBaseIntroduceHandler.this, this.val$project, this.val$editor, this.val$file, rangeWithMacros.getStartOffset(), rangeWithMacros.getEndOffset());
    }
}