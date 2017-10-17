// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.lang.asm.psi;

import org.jetbrains.annotations.Nullable;
import com.intellij.psi.PsiElement;

public interface AsmImmediate extends PsiElement
{
    @Nullable
    AsmExpression getExpression();
}
