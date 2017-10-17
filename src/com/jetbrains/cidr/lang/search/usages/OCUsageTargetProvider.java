// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.lang.search.usages;

import com.intellij.psi.PsiElement;
import com.intellij.find.findUsages.PsiElement2UsageTargetAdapter;
import com.intellij.codeInsight.TargetElementUtil;
import com.intellij.usages.UsageTarget;
import com.intellij.psi.PsiFile;
import com.intellij.openapi.editor.Editor;
import com.intellij.usages.UsageTargetProvider;

public class OCUsageTargetProvider implements UsageTargetProvider
{
    public UsageTarget[] getTargets(final Editor editor, final PsiFile psiFile) {
        final PsiElement targetElement = TargetElementUtil.findTargetElement(editor, TargetElementUtil.getInstance().getReferenceSearchFlags());
        final UsageTarget[] array;
        if (targetElement != null) {
            array = new UsageTarget[] { new PsiElement2UsageTargetAdapter(targetElement) };
        }
        else {
            final UsageTarget[] empty_ARRAY = UsageTarget.EMPTY_ARRAY;
        }
        return array;
    }
    
    public UsageTarget[] getTargets(final PsiElement psiElement) {
        return null;
    }
}
