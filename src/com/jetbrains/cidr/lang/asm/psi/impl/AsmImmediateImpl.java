// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.lang.asm.psi.impl;

import org.jetbrains.annotations.Nullable;
import com.jetbrains.cidr.lang.asm.psi.AsmExpression;
import com.intellij.psi.PsiElementVisitor;
import org.jetbrains.annotations.NotNull;
import com.jetbrains.cidr.lang.asm.psi.AsmVisitor;
import com.intellij.lang.ASTNode;
import com.jetbrains.cidr.lang.asm.psi.AsmImmediate;
import com.intellij.extapi.psi.ASTWrapperPsiElement;

public class AsmImmediateImpl extends ASTWrapperPsiElement implements AsmImmediate
{
    public AsmImmediateImpl(final ASTNode node) {
        super(node);
    }
    
    public void accept(@NotNull final AsmVisitor asmVisitor) {
        try {
            if (asmVisitor == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "visitor", "com/jetbrains/cidr/lang/asm/psi/impl/AsmImmediateImpl", "accept"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        asmVisitor.visitImmediate(this);
    }
    
    public void accept(@NotNull final PsiElementVisitor visitor) {
        try {
            if (visitor == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "visitor", "com/jetbrains/cidr/lang/asm/psi/impl/AsmImmediateImpl", "accept"));
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
    
    private static IllegalArgumentException a(final IllegalArgumentException ex) {
        return ex;
    }
}
