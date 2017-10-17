// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.lang.search.usages.rules;

import com.intellij.psi.PsiNamedElement;
import org.jetbrains.annotations.NotNull;
import com.intellij.psi.PsiFile;
import com.intellij.usages.PsiNamedElementUsageGroupBase;

private static class FileUsageGroup extends PsiNamedElementUsageGroupBase<PsiFile>
{
    private FileUsageGroup(@NotNull final PsiFile psiFile) {
        if (psiFile == null) {
            throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "file", "com/jetbrains/cidr/lang/search/usages/rules/OCFileGroupingRuleProvider$FileUsageGroup", "<init>"));
        }
        super((PsiNamedElement)psiFile);
    }
}
