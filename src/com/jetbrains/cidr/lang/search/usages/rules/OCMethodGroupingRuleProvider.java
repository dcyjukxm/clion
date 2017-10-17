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
import com.jetbrains.cidr.lang.psi.OCMethod;

public class OCMethodGroupingRuleProvider extends OCGroupRuleProviderBase<OCMethod>
{
    @Override
    protected Class<? extends OCElement> getUsageClass() {
        return OCMethod.class;
    }
    
    @Override
    protected UsageGroup createUsageGroup(final OCMethod ocMethod) {
        return (UsageGroup)new MethodUsageGroup(ocMethod);
    }
    
    private static class MethodUsageGroup extends PsiNamedElementUsageGroupBase<OCMethod>
    {
        private MethodUsageGroup(@NotNull final OCMethod ocMethod) {
            if (ocMethod == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "element", "com/jetbrains/cidr/lang/search/usages/rules/OCMethodGroupingRuleProvider$MethodUsageGroup", "<init>"));
            }
            super((PsiNamedElement)ocMethod);
        }
    }
}
