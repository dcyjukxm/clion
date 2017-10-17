// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.lang.asm.psi;

import org.jetbrains.annotations.NotNull;
import java.util.List;
import com.intellij.psi.PsiElement;

public interface AsmInstruction extends PsiElement
{
    @NotNull
    List<AsmOperand> getOperandList();
}
