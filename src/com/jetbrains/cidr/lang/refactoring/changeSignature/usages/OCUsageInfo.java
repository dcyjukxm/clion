// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.lang.refactoring.changeSignature.usages;

import com.intellij.openapi.project.Project;
import com.jetbrains.cidr.lang.refactoring.changeSignature.OCChangeInfo;
import com.intellij.psi.PsiReference;
import org.jetbrains.annotations.NotNull;
import com.intellij.usageView.UsageInfo;
import com.intellij.psi.PsiElement;

public abstract class OCUsageInfo<T extends PsiElement> extends UsageInfo
{
    public OCUsageInfo(@NotNull final T t) {
        if (t == null) {
            throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "element", "com/jetbrains/cidr/lang/refactoring/changeSignature/usages/OCUsageInfo", "<init>"));
        }
        super((PsiElement)t);
    }
    
    public OCUsageInfo(@NotNull final PsiReference psiReference) {
        if (psiReference == null) {
            throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "reference", "com/jetbrains/cidr/lang/refactoring/changeSignature/usages/OCUsageInfo", "<init>"));
        }
        super(psiReference);
    }
    
    public int getUsageRank() {
        final PsiElement element = this.getElement();
        try {
            if (element != null) {
                return element.getTextOffset();
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        return 0;
    }
    
    public abstract boolean processUsage(@NotNull final OCChangeInfo p0, @NotNull final T p1, @NotNull final Project p2);
    
    private static IllegalArgumentException a(final IllegalArgumentException ex) {
        return ex;
    }
}
