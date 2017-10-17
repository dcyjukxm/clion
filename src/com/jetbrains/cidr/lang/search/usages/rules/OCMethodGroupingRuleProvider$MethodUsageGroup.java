// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.lang.search.usages.rules;

import com.intellij.psi.PsiNamedElement;
import org.jetbrains.annotations.NotNull;
import com.jetbrains.cidr.lang.psi.OCMethod;
import com.intellij.usages.PsiNamedElementUsageGroupBase;

private static class MethodUsageGroup extends PsiNamedElementUsageGroupBase<OCMethod>
{
    private MethodUsageGroup(@NotNull final OCMethod ocMethod) {
        if (ocMethod == null) {
            throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "element", "com/jetbrains/cidr/lang/search/usages/rules/OCMethodGroupingRuleProvider$MethodUsageGroup", "<init>"));
        }
        super((PsiNamedElement)ocMethod);
    }
}
