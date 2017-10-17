// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.lang.search.usages.rules;

import com.intellij.psi.PsiNamedElement;
import org.jetbrains.annotations.NotNull;
import com.jetbrains.cidr.lang.psi.OCFunctionDefinition;
import com.intellij.usages.PsiNamedElementUsageGroupBase;

private static class FunctionUsageGroup extends PsiNamedElementUsageGroupBase<OCFunctionDefinition>
{
    private FunctionUsageGroup(@NotNull final OCFunctionDefinition ocFunctionDefinition) {
        if (ocFunctionDefinition == null) {
            throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "element", "com/jetbrains/cidr/lang/search/usages/rules/OCFunctionGroupingRuleProvider$FunctionUsageGroup", "<init>"));
        }
        super((PsiNamedElement)ocFunctionDefinition);
    }
}
