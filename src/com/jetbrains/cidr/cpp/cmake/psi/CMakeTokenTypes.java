// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.cpp.cmake.psi;

import com.intellij.psi.PsiElement;
import com.intellij.lang.ASTNode;
import com.intellij.psi.tree.IElementType;

public interface CMakeTokenTypes
{
    public static final IElementType C_MAKE_ARGUMENT = new CMakeElementType("C_MAKE_ARGUMENT");
    public static final IElementType C_MAKE_BODY_BLOCK = new CMakeElementType("C_MAKE_BODY_BLOCK");
    public static final IElementType C_MAKE_COMMAND = new CMakeElementType("C_MAKE_COMMAND");
    public static final IElementType C_MAKE_COMMAND_ARGUMENTS = new CMakeElementType("C_MAKE_COMMAND_ARGUMENTS");
    public static final IElementType C_MAKE_COMMAND_NAME = new CMakeElementType("C_MAKE_COMMAND_NAME");
    public static final IElementType C_MAKE_ELSE_COMMAND = new CMakeElementType("C_MAKE_ELSE_COMMAND");
    public static final IElementType C_MAKE_ELSE_COMMAND_CALL = new CMakeElementType("C_MAKE_ELSE_COMMAND_CALL");
    public static final IElementType C_MAKE_ELSE_COMMAND_CALL_NAME = new CMakeElementType("C_MAKE_ELSE_COMMAND_CALL_NAME");
    public static final IElementType C_MAKE_ELSE_IF_COMMAND = new CMakeElementType("C_MAKE_ELSE_IF_COMMAND");
    public static final IElementType C_MAKE_ELSE_IF_COMMAND_CALL = new CMakeElementType("C_MAKE_ELSE_IF_COMMAND_CALL");
    public static final IElementType C_MAKE_ELSE_IF_COMMAND_CALL_NAME = new CMakeElementType("C_MAKE_ELSE_IF_COMMAND_CALL_NAME");
    public static final IElementType C_MAKE_END_FOREACH_COMMAND = new CMakeElementType("C_MAKE_END_FOREACH_COMMAND");
    public static final IElementType C_MAKE_END_FOREACH_COMMAND_CALL = new CMakeElementType("C_MAKE_END_FOREACH_COMMAND_CALL");
    public static final IElementType C_MAKE_END_FOREACH_COMMAND_CALL_NAME = new CMakeElementType("C_MAKE_END_FOREACH_COMMAND_CALL_NAME");
    public static final IElementType C_MAKE_END_FUNCTION_COMMAND = new CMakeElementType("C_MAKE_END_FUNCTION_COMMAND");
    public static final IElementType C_MAKE_END_FUNCTION_COMMAND_CALL = new CMakeElementType("C_MAKE_END_FUNCTION_COMMAND_CALL");
    public static final IElementType C_MAKE_END_FUNCTION_COMMAND_CALL_NAME = new CMakeElementType("C_MAKE_END_FUNCTION_COMMAND_CALL_NAME");
    public static final IElementType C_MAKE_END_IF_COMMAND = new CMakeElementType("C_MAKE_END_IF_COMMAND");
    public static final IElementType C_MAKE_END_IF_COMMAND_CALL = new CMakeElementType("C_MAKE_END_IF_COMMAND_CALL");
    public static final IElementType C_MAKE_END_IF_COMMAND_CALL_NAME = new CMakeElementType("C_MAKE_END_IF_COMMAND_CALL_NAME");
    public static final IElementType C_MAKE_END_MACRO_COMMAND = new CMakeElementType("C_MAKE_END_MACRO_COMMAND");
    public static final IElementType C_MAKE_END_MACRO_COMMAND_CALL = new CMakeElementType("C_MAKE_END_MACRO_COMMAND_CALL");
    public static final IElementType C_MAKE_END_MACRO_COMMAND_CALL_NAME = new CMakeElementType("C_MAKE_END_MACRO_COMMAND_CALL_NAME");
    public static final IElementType C_MAKE_END_WHILE_COMMAND = new CMakeElementType("C_MAKE_END_WHILE_COMMAND");
    public static final IElementType C_MAKE_END_WHILE_COMMAND_CALL = new CMakeElementType("C_MAKE_END_WHILE_COMMAND_CALL");
    public static final IElementType C_MAKE_END_WHILE_COMMAND_CALL_NAME = new CMakeElementType("C_MAKE_END_WHILE_COMMAND_CALL_NAME");
    public static final IElementType C_MAKE_FOREACH_COMMAND = new CMakeElementType("C_MAKE_FOREACH_COMMAND");
    public static final IElementType C_MAKE_FOREACH_COMMAND_CALL = new CMakeElementType("C_MAKE_FOREACH_COMMAND_CALL");
    public static final IElementType C_MAKE_FOREACH_COMMAND_CALL_NAME = new CMakeElementType("C_MAKE_FOREACH_COMMAND_CALL_NAME");
    public static final IElementType C_MAKE_FUNCTION_COMMAND = new CMakeElementType("C_MAKE_FUNCTION_COMMAND");
    public static final IElementType C_MAKE_FUNCTION_COMMAND_CALL = new CMakeElementType("C_MAKE_FUNCTION_COMMAND_CALL");
    public static final IElementType C_MAKE_FUNCTION_COMMAND_CALL_NAME = new CMakeElementType("C_MAKE_FUNCTION_COMMAND_CALL_NAME");
    public static final IElementType C_MAKE_IF_COMMAND = new CMakeElementType("C_MAKE_IF_COMMAND");
    public static final IElementType C_MAKE_IF_COMMAND_CALL = new CMakeElementType("C_MAKE_IF_COMMAND_CALL");
    public static final IElementType C_MAKE_IF_COMMAND_CALL_NAME = new CMakeElementType("C_MAKE_IF_COMMAND_CALL_NAME");
    public static final IElementType C_MAKE_LITERAL = new CMakeElementType("C_MAKE_LITERAL");
    public static final IElementType C_MAKE_MACRO_COMMAND = new CMakeElementType("C_MAKE_MACRO_COMMAND");
    public static final IElementType C_MAKE_MACRO_COMMAND_CALL = new CMakeElementType("C_MAKE_MACRO_COMMAND_CALL");
    public static final IElementType C_MAKE_MACRO_COMMAND_CALL_NAME = new CMakeElementType("C_MAKE_MACRO_COMMAND_CALL_NAME");
    public static final IElementType C_MAKE_ROUTINE = new CMakeElementType("C_MAKE_ROUTINE");
    public static final IElementType C_MAKE_WHILE_COMMAND = new CMakeElementType("C_MAKE_WHILE_COMMAND");
    public static final IElementType C_MAKE_WHILE_COMMAND_CALL = new CMakeElementType("C_MAKE_WHILE_COMMAND_CALL");
    public static final IElementType C_MAKE_WHILE_COMMAND_CALL_NAME = new CMakeElementType("C_MAKE_WHILE_COMMAND_CALL_NAME");
    public static final IElementType BRACKET_ARG_END = new CMakeElementType("BRACKET_ARG_END");
    public static final IElementType BRACKET_ARG_START = new CMakeElementType("BRACKET_ARG_START");
    public static final IElementType COMMENT = new CMakeElementType("comment");
    public static final IElementType ELSE = new CMakeElementType("else");
    public static final IElementType ELSEIF = new CMakeElementType("elseif");
    public static final IElementType ENDFOREACH = new CMakeElementType("endforeach");
    public static final IElementType ENDFUNCTION = new CMakeElementType("endfunction");
    public static final IElementType ENDIF = new CMakeElementType("endif");
    public static final IElementType ENDMACRO = new CMakeElementType("endmacro");
    public static final IElementType ENDWHILE = new CMakeElementType("endwhile");
    public static final IElementType EOL = new CMakeElementType("EOL");
    public static final IElementType FOREACH = new CMakeElementType("foreach");
    public static final IElementType FUNCTION = new CMakeElementType("function");
    public static final IElementType ID = new CMakeElementType("id");
    public static final IElementType IF = new CMakeElementType("if");
    public static final IElementType LITERAL = new CMakeElementType("literal");
    public static final IElementType LPAR = new CMakeElementType("(");
    public static final IElementType MACRO = new CMakeElementType("macro");
    public static final IElementType QUOTE = new CMakeElementType("QUOTE");
    public static final IElementType RPAR = new CMakeElementType(")");
    public static final IElementType SEMI = new CMakeElementType(";");
    public static final IElementType WHILE = new CMakeElementType("while");
    
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
}
