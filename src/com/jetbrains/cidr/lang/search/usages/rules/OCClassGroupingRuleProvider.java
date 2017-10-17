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
import com.jetbrains.cidr.lang.psi.OCClassDeclaration;

public class OCClassGroupingRuleProvider extends OCGroupRuleProviderBase<OCClassDeclaration>
{
    @Override
    protected Class<? extends OCElement> getUsageClass() {
        return OCClassDeclaration.class;
    }
    
    @Override
    protected UsageGroup createUsageGroup(final OCClassDeclaration ocClassDeclaration) {
        return (UsageGroup)new ClassUsageGroup(ocClassDeclaration);
    }
    
    private static class ClassUsageGroup extends PsiNamedElementUsageGroupBase<OCClassDeclaration>
    {
        private ClassUsageGroup(@NotNull final OCClassDeclaration ocClassDeclaration) {
            if (ocClassDeclaration == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "element", "com/jetbrains/cidr/lang/search/usages/rules/OCClassGroupingRuleProvider$ClassUsageGroup", "<init>"));
            }
            super((PsiNamedElement)ocClassDeclaration);
        }
    }
}
