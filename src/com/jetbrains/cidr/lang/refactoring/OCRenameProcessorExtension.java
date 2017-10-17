// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.lang.refactoring;

import com.jetbrains.cidr.lang.refactoring.rename.OCRenameFileProcessor;
import com.intellij.psi.PsiFileSystemItem;
import com.intellij.usageView.UsageInfo;
import org.jetbrains.annotations.NotNull;
import com.intellij.psi.PsiNamedElement;
import com.intellij.openapi.extensions.ExtensionPointName;

public interface OCRenameProcessorExtension
{
    public static final ExtensionPointName<OCRenameProcessorExtension> EP = ExtensionPointName.create("cidr.lang.renameProcessorExtension");
    
    boolean skipNonCodeUsage(@NotNull final PsiNamedElement p0, @NotNull final UsageInfo p1);
    
    void findExtraVariants(@NotNull final PsiFileSystemItem p0, @NotNull final String p1, @NotNull final OCRenameFileProcessor.VariantProcessor p2);
}
