// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.lang.search.usages.rules;

import com.intellij.psi.PsiNamedElement;
import com.intellij.usages.PsiNamedElementUsageGroupBase;
import org.jetbrains.annotations.Nullable;
import com.intellij.psi.PsiFile;
import com.intellij.usages.rules.PsiElementUsage;
import com.intellij.usages.UsageGroup;
import com.intellij.usages.UsageTarget;
import org.jetbrains.annotations.NotNull;
import com.intellij.usages.Usage;
import com.intellij.usages.rules.SingleParentUsageGroupingRule;
import com.intellij.usages.rules.UsageGroupingRule;
import com.intellij.openapi.project.Project;
import com.intellij.usages.impl.FileStructureGroupRuleProvider;

public class OCFileGroupingRuleProvider implements FileStructureGroupRuleProvider
{
    @Nullable
    public UsageGroupingRule getUsageGroupingRule(final Project project) {
        return (UsageGroupingRule)new SingleParentUsageGroupingRule() {
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
        };
    }
    
    private static class FileUsageGroup extends PsiNamedElementUsageGroupBase<PsiFile>
    {
        private FileUsageGroup(@NotNull final PsiFile psiFile) {
            if (psiFile == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "file", "com/jetbrains/cidr/lang/search/usages/rules/OCFileGroupingRuleProvider$FileUsageGroup", "<init>"));
            }
            super((PsiNamedElement)psiFile);
        }
    }
}
