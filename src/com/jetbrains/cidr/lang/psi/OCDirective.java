// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.lang.psi;

import com.intellij.openapi.util.TextRange;
import com.intellij.openapi.util.Pair;
import org.jetbrains.annotations.NotNull;
import com.intellij.psi.PsiElement;

public interface OCDirective extends OCElement
{
    @NotNull
    PsiElement getHeaderToken();
    
    Pair<String, TextRange> getContent(final boolean p0);
    
    Pair<String, TextRange> getContent(final PsiElement p0, final boolean p1);
}
