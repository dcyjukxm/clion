// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.lang.asm.psi;

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
import com.intellij.psi.tree.IElementType;

public interface AsmTypes
{
    public static final IElementType DIRECTIVE = new AsmElementType("DIRECTIVE");
    public static final IElementType EXPRESSION = new AsmElementType("EXPRESSION");
    public static final IElementType IMMEDIATE = new AsmElementType("IMMEDIATE");
    public static final IElementType INSTRUCTION = new AsmElementType("INSTRUCTION");
    public static final IElementType JMP_ABSOLUTE = new AsmElementType("JMP_ABSOLUTE");
    public static final IElementType MEMORY = new AsmElementType("MEMORY");
    public static final IElementType NUMBER = new AsmElementType("NUMBER");
    public static final IElementType OPERAND = new AsmElementType("OPERAND");
    public static final IElementType REGISTER = new AsmElementType("REGISTER");
    public static final IElementType SYMBOL = new AsmElementType("SYMBOL");
    public static final IElementType SYMBOL_DYLD = new AsmElementType("SYMBOL_DYLD");
    public static final IElementType AT = new AsmTokenType("AT");
    public static final IElementType BLOCK_COMMENT = new AsmTokenType("BLOCK_COMMENT");
    public static final IElementType COLON = new AsmTokenType("COLON");
    public static final IElementType COMMA = new AsmTokenType("COMMA");
    public static final IElementType DIRECTIVE_CHARACTER = new AsmTokenType("DIRECTIVE_CHARACTER");
    public static final IElementType DIRECTIVE_NAME = new AsmTokenType("DIRECTIVE_NAME");
    public static final IElementType DOLLAR = new AsmTokenType("DOLLAR");
    public static final IElementType IDENTIFIER = new AsmTokenType("IDENTIFIER");
    public static final IElementType INTEGER = new AsmTokenType("INTEGER");
    public static final IElementType LABEL = new AsmTokenType("LABEL");
    public static final IElementType LINE_COMMENT = new AsmTokenType("LINE_COMMENT");
    public static final IElementType L_BRACE = new AsmTokenType("L_BRACE");
    public static final IElementType L_PAREN = new AsmTokenType("L_PAREN");
    public static final IElementType MINUS = new AsmTokenType("MINUS");
    public static final IElementType MNEMONIC = new AsmTokenType("MNEMONIC");
    public static final IElementType PERCENT = new AsmTokenType("PERCENT");
    public static final IElementType PREFIX = new AsmTokenType("PREFIX");
    public static final IElementType R_BRACE = new AsmTokenType("R_BRACE");
    public static final IElementType R_PAREN = new AsmTokenType("R_PAREN");
    public static final IElementType STAR = new AsmTokenType("STAR");
    public static final IElementType SYMBOL_IDENTIFIER = new AsmTokenType("SYMBOL_IDENTIFIER");
    
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
}
