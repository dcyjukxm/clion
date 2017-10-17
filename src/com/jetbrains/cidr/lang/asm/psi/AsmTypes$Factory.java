// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.lang.asm.psi;

import com.intellij.psi.tree.IElementType;
import com.jetbrains.cidr.lang.asm.psi.impl.AsmSymbolDyldImpl;
import com.jetbrains.cidr.lang.asm.psi.impl.AsmSymbolImpl;
import com.jetbrains.cidr.lang.asm.psi.impl.AsmRegisterImpl;
import com.jetbrains.cidr.lang.asm.psi.impl.AsmOperandImpl;
import com.jetbrains.cidr.lang.asm.psi.impl.AsmNumberImpl;
import com.jetbrains.cidr.lang.asm.psi.impl.AsmMemoryImpl;
import com.jetbrains.cidr.lang.asm.psi.impl.AsmJmpAbsoluteImpl;
import com.jetbrains.cidr.lang.asm.psi.impl.AsmInstructionImpl;
import com.jetbrains.cidr.lang.asm.psi.impl.AsmImmediateImpl;
import com.jetbrains.cidr.lang.asm.psi.impl.AsmExpressionImpl;
import com.jetbrains.cidr.lang.asm.psi.impl.AsmDirectiveImpl;
import com.intellij.psi.PsiElement;
import com.intellij.lang.ASTNode;

public static class Factory
{
    public static PsiElement createElement(final ASTNode astNode) {
        final IElementType elementType = astNode.getElementType();
        if (elementType == AsmTypes.DIRECTIVE) {
            return (PsiElement)new AsmDirectiveImpl(astNode);
        }
        if (elementType == AsmTypes.EXPRESSION) {
            return (PsiElement)new AsmExpressionImpl(astNode);
        }
        if (elementType == AsmTypes.IMMEDIATE) {
            return (PsiElement)new AsmImmediateImpl(astNode);
        }
        if (elementType == AsmTypes.INSTRUCTION) {
            return (PsiElement)new AsmInstructionImpl(astNode);
        }
        if (elementType == AsmTypes.JMP_ABSOLUTE) {
            return (PsiElement)new AsmJmpAbsoluteImpl(astNode);
        }
        if (elementType == AsmTypes.MEMORY) {
            return (PsiElement)new AsmMemoryImpl(astNode);
        }
        if (elementType == AsmTypes.NUMBER) {
            return (PsiElement)new AsmNumberImpl(astNode);
        }
        if (elementType == AsmTypes.OPERAND) {
            return (PsiElement)new AsmOperandImpl(astNode);
        }
        if (elementType == AsmTypes.REGISTER) {
            return (PsiElement)new AsmRegisterImpl(astNode);
        }
        if (elementType == AsmTypes.SYMBOL) {
            return (PsiElement)new AsmSymbolImpl(astNode);
        }
        if (elementType == AsmTypes.SYMBOL_DYLD) {
            return (PsiElement)new AsmSymbolDyldImpl(astNode);
        }
        throw new AssertionError((Object)("Unknown element type: " + elementType));
    }
}
