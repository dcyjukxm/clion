// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.lang.search.usages.rules;

import com.intellij.psi.PsiNamedElement;
import org.jetbrains.annotations.NotNull;
import com.intellij.usages.PsiNamedElementUsageGroupBase;
import org.jetbrains.annotations.Nullable;
import com.intellij.usages.rules.UsageGroupingRule;
import com.intellij.openapi.project.Project;
import com.intellij.usages.UsageGroup;
import com.jetbrains.cidr.lang.psi.OCElement;
import com.jetbrains.cidr.lang.psi.OCFunctionDefinition;

public class OCFunctionGroupingRuleProvider extends OCGroupRuleProviderBase<OCFunctionDefinition>
{
    @Override
    protected Class<? extends OCElement> getUsageClass() {
        return OCFunctionDefinition.class;
    }
    
    @Override
    protected UsageGroup createUsageGroup(final OCFunctionDefinition ocFunctionDefinition) {
        return (UsageGroup)new FunctionUsageGroup(ocFunctionDefinition);
    }
    
    private static class FunctionUsageGroup extends PsiNamedElementUsageGroupBase<OCFunctionDefinition>
    {
        private FunctionUsageGroup(@NotNull final OCFunctionDefinition ocFunctionDefinition) {
            if (ocFunctionDefinition == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "element", "com/jetbrains/cidr/lang/search/usages/rules/OCFunctionGroupingRuleProvider$FunctionUsageGroup", "<init>"));
            }
            super((PsiNamedElement)ocFunctionDefinition);
        }
    }
}
