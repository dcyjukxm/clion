// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.lang.search.usages.rules;

import org.jetbrains.annotations.Nullable;
import com.intellij.psi.PsiFile;
import com.intellij.usages.rules.PsiElementUsage;
import com.intellij.usages.UsageGroup;
import com.intellij.usages.UsageTarget;
import org.jetbrains.annotations.NotNull;
import com.intellij.usages.Usage;
import com.intellij.usages.rules.SingleParentUsageGroupingRule;

class OCFileGroupingRuleProvider$1 extends SingleParentUsageGroupingRule {
    @Nullable
    protected UsageGroup getParentGroupFor(@NotNull final Usage usage, @NotNull final UsageTarget[] array) {
        try {
            if (usage == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "usage", "com/jetbrains/cidr/lang/search/usages/rules/OCFileGroupingRuleProvider$1", "getParentGroupFor"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        try {
            if (array == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "targets", "com/jetbrains/cidr/lang/search/usages/rules/OCFileGroupingRuleProvider$1", "getParentGroupFor"));
            }
        }
        catch (IllegalArgumentException ex2) {
            throw a(ex2);
        }
        if (usage instanceof PsiElementUsage) {
            final PsiFile containingFile = ((PsiElementUsage)usage).getElement().getContainingFile();
            try {
                if (containingFile != null) {
                    final FileUsageGroup fileUsageGroup = new FileUsageGroup(containingFile);
                    return (UsageGroup)fileUsageGroup;
                }
            }
            catch (IllegalArgumentException ex3) {
                throw a(ex3);
            }
            final FileUsageGroup fileUsageGroup = null;
            return (UsageGroup)fileUsageGroup;
        }
        return null;
    }
    
    private static IllegalArgumentException a(final IllegalArgumentException ex) {
        return ex;
    }
}