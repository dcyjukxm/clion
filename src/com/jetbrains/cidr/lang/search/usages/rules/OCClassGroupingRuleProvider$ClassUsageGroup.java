// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.lang.search.usages.rules;

import com.intellij.psi.PsiNamedElement;
import org.jetbrains.annotations.NotNull;
import com.jetbrains.cidr.lang.psi.OCClassDeclaration;
import com.intellij.usages.PsiNamedElementUsageGroupBase;

private static class ClassUsageGroup extends PsiNamedElementUsageGroupBase<OCClassDeclaration>
{
    private ClassUsageGroup(@NotNull final OCClassDeclaration ocClassDeclaration) {
        if (ocClassDeclaration == null) {
            throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "element", "com/jetbrains/cidr/lang/search/usages/rules/OCClassGroupingRuleProvider$ClassUsageGroup", "<init>"));
        }
        super((PsiNamedElement)ocClassDeclaration);
    }
}
