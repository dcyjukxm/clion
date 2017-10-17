// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.lang.asm.psi;

import org.jetbrains.annotations.NotNull;
import java.util.List;
import org.jetbrains.annotations.Nullable;
import com.intellij.psi.PsiElement;

public interface AsmOperand extends PsiElement
{
    @Nullable
    AsmImmediate getImmediate();
    
    @Nullable
    AsmJmpAbsolute getJmpAbsolute();
    
    @Nullable
    AsmMemory getMemory();
    
    @NotNull
    List<AsmRegister> getRegisterList();
}
