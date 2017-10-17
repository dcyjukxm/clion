// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.lang.inspections;

import com.intellij.codeInsight.intention.IntentionAction;
import java.util.List;
import com.intellij.psi.PsiElement;

private interface ElementFix
{
    List<? extends IntentionAction> getFixes(final PsiElement p0);
}
