// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.lang.resolve.references;

import org.jetbrains.annotations.NotNull;
import com.intellij.codeInsight.lookup.LookupElement;
import java.util.List;
import com.intellij.psi.PsiReference;

public interface OCResourceReference extends PsiReference
{
    @NotNull
    List<LookupElement> getLookupElements(final boolean p0);
}
