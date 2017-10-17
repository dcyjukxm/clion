// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.lang.asm.psi.impl;

import com.intellij.psi.PsiElement;
import com.intellij.psi.util.PsiTreeUtil;
import com.jetbrains.cidr.lang.asm.psi.AsmRegister;
import java.util.List;
import org.jetbrains.annotations.Nullable;
import com.jetbrains.cidr.lang.asm.psi.AsmExpression;
import com.intellij.psi.PsiElementVisitor;
import org.jetbrains.annotations.NotNull;
import com.jetbrains.cidr.lang.asm.psi.AsmVisitor;
import com.intellij.lang.ASTNode;
import com.jetbrains.cidr.lang.asm.psi.AsmMemory;
import com.intellij.extapi.psi.ASTWrapperPsiElement;

public class AsmMemoryImpl extends ASTWrapperPsiElement implements AsmMemory
{
    public AsmMemoryImpl(final ASTNode node) {
        super(node);
    }
    
    public void accept(@NotNull final AsmVisitor asmVisitor) {
        try {
            if (asmVisitor == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "visitor", "com/jetbrains/cidr/lang/asm/psi/impl/AsmMemoryImpl", "accept"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        asmVisitor.visitMemory(this);
    }
    
    public void accept(@NotNull final PsiElementVisitor visitor) {
        try {
            if (visitor == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "visitor", "com/jetbrains/cidr/lang/asm/psi/impl/AsmMemoryImpl", "accept"));
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
    public AsmExpression getExpression() {
        return this.findChildByClass(AsmExpression.class);
    }
    
    @NotNull
    @Override
    public List<AsmRegister> getRegisterList() {
        List childrenOfTypeAsList;
        try {
            childrenOfTypeAsList = PsiTreeUtil.getChildrenOfTypeAsList((PsiElement)this, (Class)AsmRegister.class);
            if (childrenOfTypeAsList == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/asm/psi/impl/AsmMemoryImpl", "getRegisterList"));
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
