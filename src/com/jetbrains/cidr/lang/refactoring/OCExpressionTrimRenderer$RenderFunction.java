// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.lang.refactoring;

import com.jetbrains.cidr.lang.parser.OCMacroRange;
import com.intellij.psi.PsiElementVisitor;
import com.jetbrains.cidr.lang.util.OCElementUtil;
import com.intellij.util.Function;
import com.intellij.psi.PsiElement;

public static class RenderFunction<E extends PsiElement> implements Function<E, String>
{
    public String fun(final E e) {
        final StringBuilder sb = new StringBuilder();
        final OCExpressionTrimRenderer ocExpressionTrimRenderer = new OCExpressionTrimRenderer(sb);
        final OCMacroRange rangeInMacroCall = OCElementUtil.getRangeInMacroCall(e);
        if (rangeInMacroCall != null && !rangeInMacroCall.mapsToArguments()) {
            rangeInMacroCall.getMacroCall().accept((PsiElementVisitor)ocExpressionTrimRenderer);
        }
        else {
            e.accept((PsiElementVisitor)ocExpressionTrimRenderer);
        }
        return sb.toString();
    }
}
