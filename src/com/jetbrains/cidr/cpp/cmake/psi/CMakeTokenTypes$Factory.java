// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.cpp.cmake.psi;

import com.intellij.psi.tree.IElementType;
import com.intellij.psi.PsiElement;
import com.intellij.lang.ASTNode;

public static class Factory
{
    public static PsiElement createElement(final ASTNode astNode) {
        final IElementType elementType = astNode.getElementType();
        if (elementType == CMakeTokenTypes.C_MAKE_ARGUMENT) {
            return (PsiElement)new CMakeArgumentImpl(astNode);
        }
        if (elementType == CMakeTokenTypes.C_MAKE_BODY_BLOCK) {
            return (PsiElement)new CMakeBodyBlockImpl(astNode);
        }
        if (elementType == CMakeTokenTypes.C_MAKE_COMMAND) {
            return (PsiElement)new CMakeCommandImpl(astNode);
        }
        if (elementType == CMakeTokenTypes.C_MAKE_COMMAND_ARGUMENTS) {
            return (PsiElement)new CMakeCommandArgumentsImpl(astNode);
        }
        if (elementType == CMakeTokenTypes.C_MAKE_COMMAND_NAME) {
            return (PsiElement)new CMakeCommandNameImpl(astNode);
        }
        if (elementType == CMakeTokenTypes.C_MAKE_ELSE_COMMAND) {
            return (PsiElement)new CMakeElseCommandImpl(astNode);
        }
        if (elementType == CMakeTokenTypes.C_MAKE_ELSE_COMMAND_CALL) {
            return (PsiElement)new CMakeElseCommandCallImpl(astNode);
        }
        if (elementType == CMakeTokenTypes.C_MAKE_ELSE_COMMAND_CALL_NAME) {
            return (PsiElement)new CMakeElseCommandCallNameImpl(astNode);
        }
        if (elementType == CMakeTokenTypes.C_MAKE_ELSE_IF_COMMAND) {
            return (PsiElement)new CMakeElseIfCommandImpl(astNode);
        }
        if (elementType == CMakeTokenTypes.C_MAKE_ELSE_IF_COMMAND_CALL) {
            return (PsiElement)new CMakeElseIfCommandCallImpl(astNode);
        }
        if (elementType == CMakeTokenTypes.C_MAKE_ELSE_IF_COMMAND_CALL_NAME) {
            return (PsiElement)new CMakeElseIfCommandCallNameImpl(astNode);
        }
        if (elementType == CMakeTokenTypes.C_MAKE_END_FOREACH_COMMAND) {
            return (PsiElement)new CMakeEndForeachCommandImpl(astNode);
        }
        if (elementType == CMakeTokenTypes.C_MAKE_END_FOREACH_COMMAND_CALL) {
            return (PsiElement)new CMakeEndForeachCommandCallImpl(astNode);
        }
        if (elementType == CMakeTokenTypes.C_MAKE_END_FOREACH_COMMAND_CALL_NAME) {
            return (PsiElement)new CMakeEndForeachCommandCallNameImpl(astNode);
        }
        if (elementType == CMakeTokenTypes.C_MAKE_END_FUNCTION_COMMAND) {
            return (PsiElement)new CMakeEndFunctionCommandImpl(astNode);
        }
        if (elementType == CMakeTokenTypes.C_MAKE_END_FUNCTION_COMMAND_CALL) {
            return (PsiElement)new CMakeEndFunctionCommandCallImpl(astNode);
        }
        if (elementType == CMakeTokenTypes.C_MAKE_END_FUNCTION_COMMAND_CALL_NAME) {
            return (PsiElement)new CMakeEndFunctionCommandCallNameImpl(astNode);
        }
        if (elementType == CMakeTokenTypes.C_MAKE_END_IF_COMMAND) {
            return (PsiElement)new CMakeEndIfCommandImpl(astNode);
        }
        if (elementType == CMakeTokenTypes.C_MAKE_END_IF_COMMAND_CALL) {
            return (PsiElement)new CMakeEndIfCommandCallImpl(astNode);
        }
        if (elementType == CMakeTokenTypes.C_MAKE_END_IF_COMMAND_CALL_NAME) {
            return (PsiElement)new CMakeEndIfCommandCallNameImpl(astNode);
        }
        if (elementType == CMakeTokenTypes.C_MAKE_END_MACRO_COMMAND) {
            return (PsiElement)new CMakeEndMacroCommandImpl(astNode);
        }
        if (elementType == CMakeTokenTypes.C_MAKE_END_MACRO_COMMAND_CALL) {
            return (PsiElement)new CMakeEndMacroCommandCallImpl(astNode);
        }
        if (elementType == CMakeTokenTypes.C_MAKE_END_MACRO_COMMAND_CALL_NAME) {
            return (PsiElement)new CMakeEndMacroCommandCallNameImpl(astNode);
        }
        if (elementType == CMakeTokenTypes.C_MAKE_END_WHILE_COMMAND) {
            return (PsiElement)new CMakeEndWhileCommandImpl(astNode);
        }
        if (elementType == CMakeTokenTypes.C_MAKE_END_WHILE_COMMAND_CALL) {
            return (PsiElement)new CMakeEndWhileCommandCallImpl(astNode);
        }
        if (elementType == CMakeTokenTypes.C_MAKE_END_WHILE_COMMAND_CALL_NAME) {
            return (PsiElement)new CMakeEndWhileCommandCallNameImpl(astNode);
        }
        if (elementType == CMakeTokenTypes.C_MAKE_FOREACH_COMMAND) {
            return (PsiElement)new CMakeForeachCommandImpl(astNode);
        }
        if (elementType == CMakeTokenTypes.C_MAKE_FOREACH_COMMAND_CALL) {
            return (PsiElement)new CMakeForeachCommandCallImpl(astNode);
        }
        if (elementType == CMakeTokenTypes.C_MAKE_FOREACH_COMMAND_CALL_NAME) {
            return (PsiElement)new CMakeForeachCommandCallNameImpl(astNode);
        }
        if (elementType == CMakeTokenTypes.C_MAKE_FUNCTION_COMMAND) {
            return (PsiElement)new CMakeFunctionCommandImpl(astNode);
        }
        if (elementType == CMakeTokenTypes.C_MAKE_FUNCTION_COMMAND_CALL) {
            return (PsiElement)new CMakeFunctionCommandCallImpl(astNode);
        }
        if (elementType == CMakeTokenTypes.C_MAKE_FUNCTION_COMMAND_CALL_NAME) {
            return (PsiElement)new CMakeFunctionCommandCallNameImpl(astNode);
        }
        if (elementType == CMakeTokenTypes.C_MAKE_IF_COMMAND) {
            return (PsiElement)new CMakeIfCommandImpl(astNode);
        }
        if (elementType == CMakeTokenTypes.C_MAKE_IF_COMMAND_CALL) {
            return (PsiElement)new CMakeIfCommandCallImpl(astNode);
        }
        if (elementType == CMakeTokenTypes.C_MAKE_IF_COMMAND_CALL_NAME) {
            return (PsiElement)new CMakeIfCommandCallNameImpl(astNode);
        }
        if (elementType == CMakeTokenTypes.C_MAKE_LITERAL) {
            return (PsiElement)new CMakeLiteralImpl(astNode);
        }
        if (elementType == CMakeTokenTypes.C_MAKE_MACRO_COMMAND) {
            return (PsiElement)new CMakeMacroCommandImpl(astNode);
        }
        if (elementType == CMakeTokenTypes.C_MAKE_MACRO_COMMAND_CALL) {
            return (PsiElement)new CMakeMacroCommandCallImpl(astNode);
        }
        if (elementType == CMakeTokenTypes.C_MAKE_MACRO_COMMAND_CALL_NAME) {
            return (PsiElement)new CMakeMacroCommandCallNameImpl(astNode);
        }
        if (elementType == CMakeTokenTypes.C_MAKE_ROUTINE) {
            return (PsiElement)new CMakeRoutineImpl(astNode);
        }
        if (elementType == CMakeTokenTypes.C_MAKE_WHILE_COMMAND) {
            return (PsiElement)new CMakeWhileCommandImpl(astNode);
        }
        if (elementType == CMakeTokenTypes.C_MAKE_WHILE_COMMAND_CALL) {
            return (PsiElement)new CMakeWhileCommandCallImpl(astNode);
        }
        if (elementType == CMakeTokenTypes.C_MAKE_WHILE_COMMAND_CALL_NAME) {
            return (PsiElement)new CMakeWhileCommandCallNameImpl(astNode);
        }
        throw new AssertionError((Object)("Unknown element type: " + elementType));
    }
}
