// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.generate.handlers;

import com.intellij.openapi.editor.RangeMarker;
import com.intellij.psi.PsiElement;
import com.jetbrains.cidr.lang.psi.OCBlockStatement;
import com.intellij.openapi.util.Condition;
import com.intellij.openapi.util.Segment;
import com.jetbrains.cidr.lang.util.OCCodeInsightUtil;
import com.intellij.psi.PsiWhiteSpace;
import com.intellij.codeInsight.template.Template;
import com.intellij.openapi.editor.Editor;
import com.intellij.codeInsight.template.TemplateEditingAdapter;

class OCAbstractGenerateTestHandler$1$1 extends TemplateEditingAdapter {
    final /* synthetic */ int val$insertPosition;
    final /* synthetic */ Editor val$elementEditor;
    
    @Override
    public void templateFinished(final Template template, final boolean b) {
        PsiElement psiElement = WriteCommandAction.this.val$elementFile.findElementAt(this.val$insertPosition);
        if (psiElement instanceof PsiWhiteSpace) {
            psiElement = psiElement.getNextSibling();
        }
        if (psiElement != null) {
            final RangeMarker rangeMarker = this.val$elementEditor.getDocument().createRangeMarker(psiElement.getTextRange());
            WriteCommandAction.this.this$0.onTemplateFinished(WriteCommandAction.this.val$elementFile, rangeMarker);
            OCCodeInsightUtil.showCallableInEditorAndSelectBody(WriteCommandAction.this.val$elementFile, (Segment)rangeMarker, (Condition<OCBlockStatement>)WriteCommandAction.this.this$0::shouldSelectResult);
        }
    }
}