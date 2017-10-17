// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.lang.actions;

import com.intellij.openapi.util.TextRange;
import com.intellij.psi.PsiComment;
import kotlin.Pair;
import com.jetbrains.cidr.lang.psi.impl.OCLazyBlockStatementImpl;
import com.jetbrains.cidr.lang.util.OCElementUtil;
import com.jetbrains.cidr.lang.psi.OCConstructorInitializationList;
import com.intellij.psi.util.PsiTreeUtil;
import com.jetbrains.cidr.lang.psi.OCFunctionDefinition;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import java.util.ArrayList;
import kotlin.Metadata;
import com.intellij.psi.search.PsiElementProcessor;
import com.intellij.psi.PsiElement;

@Metadata(mv = { 1, 1, 7 }, bv = { 1, 0, 2 }, k = 3, d1 = { "\u0000\u000e\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0000\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u0003H\n¢\u0006\u0002\b\u0004" }, d2 = { "<anonymous>", "", "element", "Lcom/intellij/psi/PsiElement;", "execute" })
static final class OCRemoveBodiesAndComments$actionPerformed$1<T extends PsiElement> implements PsiElementProcessor<PsiElement> {
    public final boolean execute(@NotNull final PsiElement psiElement) {
        Intrinsics.checkParameterIsNotNull((Object)psiElement, "element");
        if (PsiTreeUtil.getParentOfType(psiElement, (Class)OCFunctionDefinition.class) == null) {
            if (psiElement instanceof OCFunctionDefinition) {
                final OCConstructorInitializationList list = (OCConstructorInitializationList)PsiTreeUtil.findChildOfType(psiElement, (Class)OCConstructorInitializationList.class);
                final PsiElement psiElement2 = (list == null) ? null : OCElementUtil.getPrevSignificantSibling((PsiElement)list);
                final OCLazyBlockStatementImpl ocLazyBlockStatementImpl = (OCLazyBlockStatementImpl)PsiTreeUtil.findChildOfType(psiElement, (Class)OCLazyBlockStatementImpl.class);
                PsiElement psiElement4;
                PsiElement psiElement3;
                if ((psiElement3 = (psiElement4 = psiElement2)) == null) {
                    psiElement4 = (psiElement3 = (PsiElement)list);
                }
                if (psiElement3 == null) {
                    psiElement4 = (PsiElement)ocLazyBlockStatementImpl;
                }
                final PsiElement psiElement5 = psiElement4;
                final OCLazyBlockStatementImpl ocLazyBlockStatementImpl2 = ocLazyBlockStatementImpl;
                if (psiElement5 != null && ocLazyBlockStatementImpl2 != null) {
                    final TextRange union = psiElement5.getTextRange().union(ocLazyBlockStatementImpl2.getTextRange());
                    if (this.$pos == null || !union.contains((int)this.$pos)) {
                        this.$toReplace.add(new Pair((Object)union, (Object)";"));
                    }
                }
            }
            else if (psiElement instanceof PsiComment) {
                this.$toReplace.add(new Pair((Object)((PsiComment)psiElement).getTextRange(), (Object)""));
            }
        }
        return true;
    }
}