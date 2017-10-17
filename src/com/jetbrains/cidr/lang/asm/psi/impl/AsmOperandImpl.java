// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.lang.asm.psi.impl;

import com.intellij.psi.PsiElement;
import com.intellij.psi.util.PsiTreeUtil;
import com.jetbrains.cidr.lang.asm.psi.AsmRegister;
import java.util.List;
import com.jetbrains.cidr.lang.asm.psi.AsmMemory;
import com.jetbrains.cidr.lang.asm.psi.AsmJmpAbsolute;
import org.jetbrains.annotations.Nullable;
import com.jetbrains.cidr.lang.asm.psi.AsmImmediate;
import com.intellij.psi.PsiElementVisitor;
import org.jetbrains.annotations.NotNull;
import com.jetbrains.cidr.lang.asm.psi.AsmVisitor;
import com.intellij.lang.ASTNode;
import com.jetbrains.cidr.lang.asm.psi.AsmOperand;
import com.intellij.extapi.psi.ASTWrapperPsiElement;

public class AsmOperandImpl extends ASTWrapperPsiElement implements AsmOperand
{
    public AsmOperandImpl(final ASTNode node) {
        super(node);
    }
    
    public void accept(@NotNull final AsmVisitor asmVisitor) {
        try {
            if (asmVisitor == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "visitor", "com/jetbrains/cidr/lang/asm/psi/impl/AsmOperandImpl", "accept"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        asmVisitor.visitOperand(this);
    }
    
    public void accept(@NotNull final PsiElementVisitor visitor) {
        try {
            if (visitor == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "visitor", "com/jetbrains/cidr/lang/asm/psi/impl/AsmOperandImpl", "accept"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        try {
            if (visitor instanceof AsmVisitor) {
                this.accept((AsmVisitor)visitor);
                return;
            }
        }
        catch (IllegalArgumentException ex2) {
            throw a(ex2);
        }
        super.accept(visitor);
    }
    
    @Nullable
    @Override
    public AsmImmediate getImmediate() {
        return this.findChildByClass(AsmImmediate.class);
    }
    
    @Nullable
    @Override
    public AsmJmpAbsolute getJmpAbsolute() {
        return this.findChildByClass(AsmJmpAbsolute.class);
    }
    
    @Nullable
    @Override
    public AsmMemory getMemory() {
        return this.findChildByClass(AsmMemory.class);
    }
    
    @NotNull
    @Override
    public List<AsmRegister> getRegisterList() {
        List childrenOfTypeAsList;
        try {
            childrenOfTypeAsList = PsiTreeUtil.getChildrenOfTypeAsList((PsiElement)this, (Class)AsmRegister.class);
            if (childrenOfTypeAsList == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/asm/psi/impl/AsmOperandImpl", "getRegisterList"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        return (List<AsmRegister>)childrenOfTypeAsList;
    }
    
    private static IllegalArgumentException a(final IllegalArgumentException ex) {
        return ex;
    }
}
