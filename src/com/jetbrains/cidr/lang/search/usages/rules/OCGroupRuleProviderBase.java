// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.lang.search.usages.rules;

import com.intellij.psi.util.PsiTreeUtil;
import org.jetbrains.annotations.Nullable;
import com.intellij.psi.PsiElement;
import com.intellij.lang.Language;
import com.jetbrains.cidr.lang.OCLanguage;
import com.intellij.usages.rules.PsiElementUsage;
import com.intellij.usages.UsageGroup;
import com.intellij.usages.UsageTarget;
import org.jetbrains.annotations.NotNull;
import com.intellij.usages.Usage;
import com.intellij.usages.rules.SingleParentUsageGroupingRule;
import com.intellij.usages.rules.UsageGroupingRule;
import com.intellij.openapi.project.Project;
import com.intellij.usages.impl.FileStructureGroupRuleProvider;
import com.jetbrains.cidr.lang.psi.OCElement;

abstract class OCGroupRuleProviderBase<T extends OCElement> implements FileStructureGroupRuleProvider
{
    @Nullable
    public UsageGroupingRule getUsageGroupingRule(final Project project) {
        return (UsageGroupingRule)new SingleParentUsageGroupingRule() {
            @Nullable
            protected UsageGroup getParentGroupFor(@NotNull final Usage usage, @NotNull final UsageTarget[] array) {
                try {
                    if (usage == null) {
                        throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "usage", "com/jetbrains/cidr/lang/search/usages/rules/OCGroupRuleProviderBase$1", "getParentGroupFor"));
                    }
                }
                catch (IllegalArgumentException ex) {
                    throw a(ex);
                }
                try {
                    if (array == null) {
                        throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "targets", "com/jetbrains/cidr/lang/search/usages/rules/OCGroupRuleProviderBase$1", "getParentGroupFor"));
                    }
                }
                catch (IllegalArgumentException ex2) {
                    throw a(ex2);
                }
                if (usage instanceof PsiElementUsage) {
                    final PsiElement element = ((PsiElementUsage)usage).getElement();
                    try {
                        if (!element.getLanguage().isKindOf((Language)OCLanguage.getInstance())) {
                            return null;
                        }
                    }
                    catch (IllegalArgumentException ex3) {
                        throw a(ex3);
                    }
                    return OCGroupRuleProviderBase.this.createUsageGroupForElement(element);
                }
                return null;
            }
            
            private static IllegalArgumentException a(final IllegalArgumentException ex) {
                return ex;
            }
        };
    }
    
    protected UsageGroup createUsageGroupForElement(final PsiElement psiElement) {
        final OCElement ocElement = (OCElement)PsiTreeUtil.getParentOfType(psiElement, (Class)this.getUsageClass());
        if (this.isAcceptableElement(ocElement)) {
            return this.createUsageGroup((T)ocElement);
        }
        return null;
    }
    
    protected boolean isAcceptableElement(final OCElement ocElement) {
        return ocElement != null;
    }
    
    protected abstract Class<? extends OCElement> getUsageClass();
    
    protected abstract UsageGroup createUsageGroup(final T p0);
}
