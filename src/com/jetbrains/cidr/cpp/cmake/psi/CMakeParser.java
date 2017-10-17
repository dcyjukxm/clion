// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.cpp.cmake.psi;

import com.jetbrains.cidr.cpp.cmake.psi.util.CMakeParserUtil;
import com.intellij.lang.parser.GeneratedParserUtilBase;
import com.intellij.lang.ASTNode;
import com.intellij.lang.PsiBuilder;
import com.intellij.psi.tree.IElementType;
import com.intellij.psi.tree.TokenSet;
import com.intellij.lang.LightPsiParser;
import com.intellij.lang.PsiParser;

public class CMakeParser implements PsiParser, LightPsiParser
{
    public static final TokenSet[] EXTENDS_SETS_;
    
    public ASTNode parse(final IElementType elementType, final PsiBuilder psiBuilder) {
        this.parseLight(elementType, psiBuilder);
        return psiBuilder.getTreeBuilt();
    }
    
    public void parseLight(final IElementType elementType, PsiBuilder adapt_builder_) {
        adapt_builder_ = GeneratedParserUtilBase.adapt_builder_(elementType, adapt_builder_, (PsiParser)this, CMakeParser.EXTENDS_SETS_);
        final PsiBuilder.Marker enter_section_ = GeneratedParserUtilBase.enter_section_(adapt_builder_, 0, 1, null);
        boolean b;
        if (elementType == CMakeElementTypes.C_MAKE_ARGUMENT) {
            b = CMakeArgument(adapt_builder_, 0);
        }
        else if (elementType == CMakeElementTypes.C_MAKE_BODY_BLOCK) {
            b = CMakeBodyBlock(adapt_builder_, 0);
        }
        else if (elementType == CMakeElementTypes.C_MAKE_COMMAND) {
            b = CMakeCommand(adapt_builder_, 0);
        }
        else if (elementType == CMakeElementTypes.C_MAKE_COMMAND_ARGUMENTS) {
            b = CMakeCommandArguments(adapt_builder_, 0);
        }
        else if (elementType == CMakeElementTypes.C_MAKE_COMMAND_NAME) {
            b = CMakeCommandName(adapt_builder_, 0);
        }
        else if (elementType == CMakeElementTypes.C_MAKE_ELSE_COMMAND) {
            b = CMakeElseCommand(adapt_builder_, 0);
        }
        else if (elementType == CMakeElementTypes.C_MAKE_ELSE_COMMAND_CALL) {
            b = CMakeElseCommandCall(adapt_builder_, 0);
        }
        else if (elementType == CMakeElementTypes.C_MAKE_ELSE_COMMAND_CALL_NAME) {
            b = CMakeElseCommandCallName(adapt_builder_, 0);
        }
        else if (elementType == CMakeElementTypes.C_MAKE_ELSE_IF_COMMAND) {
            b = CMakeElseIfCommand(adapt_builder_, 0);
        }
        else if (elementType == CMakeElementTypes.C_MAKE_ELSE_IF_COMMAND_CALL) {
            b = CMakeElseIfCommandCall(adapt_builder_, 0);
        }
        else if (elementType == CMakeElementTypes.C_MAKE_ELSE_IF_COMMAND_CALL_NAME) {
            b = CMakeElseIfCommandCallName(adapt_builder_, 0);
        }
        else if (elementType == CMakeElementTypes.C_MAKE_END_FOREACH_COMMAND) {
            b = CMakeEndForeachCommand(adapt_builder_, 0);
        }
        else if (elementType == CMakeElementTypes.C_MAKE_END_FOREACH_COMMAND_CALL) {
            b = CMakeEndForeachCommandCall(adapt_builder_, 0);
        }
        else if (elementType == CMakeElementTypes.C_MAKE_END_FOREACH_COMMAND_CALL_NAME) {
            b = CMakeEndForeachCommandCallName(adapt_builder_, 0);
        }
        else if (elementType == CMakeElementTypes.C_MAKE_END_FUNCTION_COMMAND) {
            b = CMakeEndFunctionCommand(adapt_builder_, 0);
        }
        else if (elementType == CMakeElementTypes.C_MAKE_END_FUNCTION_COMMAND_CALL) {
            b = CMakeEndFunctionCommandCall(adapt_builder_, 0);
        }
        else if (elementType == CMakeElementTypes.C_MAKE_END_FUNCTION_COMMAND_CALL_NAME) {
            b = CMakeEndFunctionCommandCallName(adapt_builder_, 0);
        }
        else if (elementType == CMakeElementTypes.C_MAKE_END_IF_COMMAND) {
            b = CMakeEndIfCommand(adapt_builder_, 0);
        }
        else if (elementType == CMakeElementTypes.C_MAKE_END_IF_COMMAND_CALL) {
            b = CMakeEndIfCommandCall(adapt_builder_, 0);
        }
        else if (elementType == CMakeElementTypes.C_MAKE_END_IF_COMMAND_CALL_NAME) {
            b = CMakeEndIfCommandCallName(adapt_builder_, 0);
        }
        else if (elementType == CMakeElementTypes.C_MAKE_END_MACRO_COMMAND) {
            b = CMakeEndMacroCommand(adapt_builder_, 0);
        }
        else if (elementType == CMakeElementTypes.C_MAKE_END_MACRO_COMMAND_CALL) {
            b = CMakeEndMacroCommandCall(adapt_builder_, 0);
        }
        else if (elementType == CMakeElementTypes.C_MAKE_END_MACRO_COMMAND_CALL_NAME) {
            b = CMakeEndMacroCommandCallName(adapt_builder_, 0);
        }
        else if (elementType == CMakeElementTypes.C_MAKE_END_WHILE_COMMAND) {
            b = CMakeEndWhileCommand(adapt_builder_, 0);
        }
        else if (elementType == CMakeElementTypes.C_MAKE_END_WHILE_COMMAND_CALL) {
            b = CMakeEndWhileCommandCall(adapt_builder_, 0);
        }
        else if (elementType == CMakeElementTypes.C_MAKE_END_WHILE_COMMAND_CALL_NAME) {
            b = CMakeEndWhileCommandCallName(adapt_builder_, 0);
        }
        else if (elementType == CMakeElementTypes.C_MAKE_FOREACH_COMMAND) {
            b = CMakeForeachCommand(adapt_builder_, 0);
        }
        else if (elementType == CMakeElementTypes.C_MAKE_FOREACH_COMMAND_CALL) {
            b = CMakeForeachCommandCall(adapt_builder_, 0);
        }
        else if (elementType == CMakeElementTypes.C_MAKE_FOREACH_COMMAND_CALL_NAME) {
            b = CMakeForeachCommandCallName(adapt_builder_, 0);
        }
        else if (elementType == CMakeElementTypes.C_MAKE_FUNCTION_COMMAND) {
            b = CMakeFunctionCommand(adapt_builder_, 0);
        }
        else if (elementType == CMakeElementTypes.C_MAKE_FUNCTION_COMMAND_CALL) {
            b = CMakeFunctionCommandCall(adapt_builder_, 0);
        }
        else if (elementType == CMakeElementTypes.C_MAKE_FUNCTION_COMMAND_CALL_NAME) {
            b = CMakeFunctionCommandCallName(adapt_builder_, 0);
        }
        else if (elementType == CMakeElementTypes.C_MAKE_IF_COMMAND) {
            b = CMakeIfCommand(adapt_builder_, 0);
        }
        else if (elementType == CMakeElementTypes.C_MAKE_IF_COMMAND_CALL) {
            b = CMakeIfCommandCall(adapt_builder_, 0);
        }
        else if (elementType == CMakeElementTypes.C_MAKE_IF_COMMAND_CALL_NAME) {
            b = CMakeIfCommandCallName(adapt_builder_, 0);
        }
        else if (elementType == CMakeElementTypes.C_MAKE_LITERAL) {
            b = CMakeLiteral(adapt_builder_, 0);
        }
        else if (elementType == CMakeElementTypes.C_MAKE_MACRO_COMMAND) {
            b = CMakeMacroCommand(adapt_builder_, 0);
        }
        else if (elementType == CMakeElementTypes.C_MAKE_MACRO_COMMAND_CALL) {
            b = CMakeMacroCommandCall(adapt_builder_, 0);
        }
        else if (elementType == CMakeElementTypes.C_MAKE_MACRO_COMMAND_CALL_NAME) {
            b = CMakeMacroCommandCallName(adapt_builder_, 0);
        }
        else if (elementType == CMakeElementTypes.C_MAKE_ROUTINE) {
            b = CMakeRoutine(adapt_builder_, 0);
        }
        else if (elementType == CMakeElementTypes.C_MAKE_WHILE_COMMAND) {
            b = CMakeWhileCommand(adapt_builder_, 0);
        }
        else if (elementType == CMakeElementTypes.C_MAKE_WHILE_COMMAND_CALL) {
            b = CMakeWhileCommandCall(adapt_builder_, 0);
        }
        else if (elementType == CMakeElementTypes.C_MAKE_WHILE_COMMAND_CALL_NAME) {
            b = CMakeWhileCommandCallName(adapt_builder_, 0);
        }
        else {
            b = this.parse_root_(elementType, adapt_builder_, 0);
        }
        GeneratedParserUtilBase.exit_section_(adapt_builder_, 0, enter_section_, elementType, b, true, CMakeParserUtil.TRUE_CONDITION);
    }
    
    protected boolean parse_root_(final IElementType elementType, final PsiBuilder psiBuilder, final int n) {
        return root(psiBuilder, n + 1);
    }
    
    public static boolean CMakeArgument(final PsiBuilder psiBuilder, final int n) {
        if (!GeneratedParserUtilBase.recursion_guard_(psiBuilder, n, "CMakeArgument")) {
            return false;
        }
        final PsiBuilder.Marker enter_section_ = GeneratedParserUtilBase.enter_section_(psiBuilder, n, 0, CMakeElementTypes.C_MAKE_ARGUMENT, "<argument>");
        boolean b = CMakeLiteral(psiBuilder, n + 1);
        if (!b) {
            b = bracket_arg(psiBuilder, n + 1);
        }
        if (!b) {
            b = quote_arg(psiBuilder, n + 1);
        }
        GeneratedParserUtilBase.exit_section_(psiBuilder, n, enter_section_, b, false, null);
        return b;
    }
    
    public static boolean CMakeBodyBlock(final PsiBuilder psiBuilder, final int n) {
        if (!GeneratedParserUtilBase.recursion_guard_(psiBuilder, n, "CMakeBodyBlock")) {
            return false;
        }
        final PsiBuilder.Marker enter_section_ = GeneratedParserUtilBase.enter_section_(psiBuilder, n, 0, CMakeElementTypes.C_MAKE_BODY_BLOCK, "<c make body block>");
        final boolean file_elements = file_elements(psiBuilder, n + 1);
        GeneratedParserUtilBase.exit_section_(psiBuilder, n, enter_section_, file_elements, false, null);
        return file_elements;
    }
    
    public static boolean CMakeCommand(final PsiBuilder psiBuilder, final int n) {
        if (!GeneratedParserUtilBase.recursion_guard_(psiBuilder, n, "CMakeCommand")) {
            return false;
        }
        if (!GeneratedParserUtilBase.nextTokenIs(psiBuilder, CMakeElementTypes.ID)) {
            return false;
        }
        final PsiBuilder.Marker enter_section_ = GeneratedParserUtilBase.enter_section_(psiBuilder, n, 0, CMakeElementTypes.C_MAKE_COMMAND, null);
        final boolean cMakeCommandName;
        final boolean b = (cMakeCommandName = CMakeCommandName(psiBuilder, n + 1)) && arg_list(psiBuilder, n + 1);
        GeneratedParserUtilBase.exit_section_(psiBuilder, n, enter_section_, b, cMakeCommandName, null);
        return b || cMakeCommandName;
    }
    
    public static boolean CMakeCommandArguments(final PsiBuilder psiBuilder, final int n) {
        if (!GeneratedParserUtilBase.recursion_guard_(psiBuilder, n, "CMakeCommandArguments")) {
            return false;
        }
        if (!GeneratedParserUtilBase.nextTokenIs(psiBuilder, CMakeElementTypes.LPAR)) {
            return false;
        }
        final PsiBuilder.Marker enter_section_ = GeneratedParserUtilBase.enter_section_(psiBuilder, n, 0, CMakeElementTypes.C_MAKE_COMMAND_ARGUMENTS, "<argument list>");
        final boolean arg_group = arg_group(psiBuilder, n + 1);
        GeneratedParserUtilBase.exit_section_(psiBuilder, n, enter_section_, arg_group, false, null);
        return arg_group;
    }
    
    public static boolean CMakeCommandName(final PsiBuilder psiBuilder, final int n) {
        if (!GeneratedParserUtilBase.recursion_guard_(psiBuilder, n, "CMakeCommandName")) {
            return false;
        }
        if (!GeneratedParserUtilBase.nextTokenIs(psiBuilder, CMakeElementTypes.ID)) {
            return false;
        }
        final PsiBuilder.Marker enter_section_ = GeneratedParserUtilBase.enter_section_(psiBuilder, n, 0, CMakeElementTypes.C_MAKE_COMMAND_NAME, "<command name>");
        final boolean consumeToken = GeneratedParserUtilBase.consumeToken(psiBuilder, CMakeElementTypes.ID);
        GeneratedParserUtilBase.exit_section_(psiBuilder, n, enter_section_, consumeToken, false, null);
        return consumeToken;
    }
    
    public static boolean CMakeElseCommand(final PsiBuilder psiBuilder, final int n) {
        if (!GeneratedParserUtilBase.recursion_guard_(psiBuilder, n, "CMakeElseCommand")) {
            return false;
        }
        if (!GeneratedParserUtilBase.nextTokenIs(psiBuilder, CMakeElementTypes.ELSE)) {
            return false;
        }
        final PsiBuilder.Marker enter_section_ = GeneratedParserUtilBase.enter_section_(psiBuilder, n, 0, CMakeElementTypes.C_MAKE_ELSE_COMMAND, null);
        final boolean cMakeElseCommandCall;
        final boolean b = (cMakeElseCommandCall = CMakeElseCommandCall(psiBuilder, n + 1)) && arg_list(psiBuilder, n + 1);
        GeneratedParserUtilBase.exit_section_(psiBuilder, n, enter_section_, b, cMakeElseCommandCall, null);
        return b || cMakeElseCommandCall;
    }
    
    public static boolean CMakeElseCommandCall(final PsiBuilder psiBuilder, final int n) {
        if (!GeneratedParserUtilBase.recursion_guard_(psiBuilder, n, "CMakeElseCommandCall")) {
            return false;
        }
        if (!GeneratedParserUtilBase.nextTokenIs(psiBuilder, CMakeElementTypes.ELSE)) {
            return false;
        }
        final PsiBuilder.Marker enter_section_ = GeneratedParserUtilBase.enter_section_(psiBuilder, n, 0, CMakeElementTypes.C_MAKE_ELSE_COMMAND_CALL, "<else call>");
        final boolean cMakeElseCommandCallName = CMakeElseCommandCallName(psiBuilder, n + 1);
        GeneratedParserUtilBase.exit_section_(psiBuilder, n, enter_section_, cMakeElseCommandCallName, false, null);
        return cMakeElseCommandCallName;
    }
    
    public static boolean CMakeElseCommandCallName(final PsiBuilder psiBuilder, final int n) {
        if (!GeneratedParserUtilBase.recursion_guard_(psiBuilder, n, "CMakeElseCommandCallName")) {
            return false;
        }
        if (!GeneratedParserUtilBase.nextTokenIs(psiBuilder, CMakeElementTypes.ELSE)) {
            return false;
        }
        final PsiBuilder.Marker enter_section_ = GeneratedParserUtilBase.enter_section_(psiBuilder, n, 0, CMakeElementTypes.C_MAKE_ELSE_COMMAND_CALL_NAME, "<else call name>");
        final boolean consumeToken = GeneratedParserUtilBase.consumeToken(psiBuilder, CMakeElementTypes.ELSE);
        GeneratedParserUtilBase.exit_section_(psiBuilder, n, enter_section_, consumeToken, false, null);
        return consumeToken;
    }
    
    public static boolean CMakeElseIfCommand(final PsiBuilder psiBuilder, final int n) {
        if (!GeneratedParserUtilBase.recursion_guard_(psiBuilder, n, "CMakeElseIfCommand")) {
            return false;
        }
        if (!GeneratedParserUtilBase.nextTokenIs(psiBuilder, CMakeElementTypes.ELSEIF)) {
            return false;
        }
        final PsiBuilder.Marker enter_section_ = GeneratedParserUtilBase.enter_section_(psiBuilder, n, 0, CMakeElementTypes.C_MAKE_ELSE_IF_COMMAND, null);
        final boolean cMakeElseIfCommandCall;
        final boolean b = (cMakeElseIfCommandCall = CMakeElseIfCommandCall(psiBuilder, n + 1)) && arg_list(psiBuilder, n + 1);
        GeneratedParserUtilBase.exit_section_(psiBuilder, n, enter_section_, b, cMakeElseIfCommandCall, null);
        return b || cMakeElseIfCommandCall;
    }
    
    public static boolean CMakeElseIfCommandCall(final PsiBuilder psiBuilder, final int n) {
        if (!GeneratedParserUtilBase.recursion_guard_(psiBuilder, n, "CMakeElseIfCommandCall")) {
            return false;
        }
        if (!GeneratedParserUtilBase.nextTokenIs(psiBuilder, CMakeElementTypes.ELSEIF)) {
            return false;
        }
        final PsiBuilder.Marker enter_section_ = GeneratedParserUtilBase.enter_section_(psiBuilder, n, 0, CMakeElementTypes.C_MAKE_ELSE_IF_COMMAND_CALL, "<elseif call>");
        final boolean cMakeElseIfCommandCallName = CMakeElseIfCommandCallName(psiBuilder, n + 1);
        GeneratedParserUtilBase.exit_section_(psiBuilder, n, enter_section_, cMakeElseIfCommandCallName, false, null);
        return cMakeElseIfCommandCallName;
    }
    
    public static boolean CMakeElseIfCommandCallName(final PsiBuilder psiBuilder, final int n) {
        if (!GeneratedParserUtilBase.recursion_guard_(psiBuilder, n, "CMakeElseIfCommandCallName")) {
            return false;
        }
        if (!GeneratedParserUtilBase.nextTokenIs(psiBuilder, CMakeElementTypes.ELSEIF)) {
            return false;
        }
        final PsiBuilder.Marker enter_section_ = GeneratedParserUtilBase.enter_section_(psiBuilder, n, 0, CMakeElementTypes.C_MAKE_ELSE_IF_COMMAND_CALL_NAME, "<elseif call name>");
        final boolean consumeToken = GeneratedParserUtilBase.consumeToken(psiBuilder, CMakeElementTypes.ELSEIF);
        GeneratedParserUtilBase.exit_section_(psiBuilder, n, enter_section_, consumeToken, false, null);
        return consumeToken;
    }
    
    public static boolean CMakeEndForeachCommand(final PsiBuilder psiBuilder, final int n) {
        if (!GeneratedParserUtilBase.recursion_guard_(psiBuilder, n, "CMakeEndForeachCommand")) {
            return false;
        }
        if (!GeneratedParserUtilBase.nextTokenIs(psiBuilder, CMakeElementTypes.ENDFOREACH)) {
            return false;
        }
        final PsiBuilder.Marker enter_section_ = GeneratedParserUtilBase.enter_section_(psiBuilder, n, 0, CMakeElementTypes.C_MAKE_END_FOREACH_COMMAND, null);
        final boolean cMakeEndForeachCommandCall;
        final boolean b = (cMakeEndForeachCommandCall = CMakeEndForeachCommandCall(psiBuilder, n + 1)) && arg_list(psiBuilder, n + 1);
        GeneratedParserUtilBase.exit_section_(psiBuilder, n, enter_section_, b, cMakeEndForeachCommandCall, null);
        return b || cMakeEndForeachCommandCall;
    }
    
    public static boolean CMakeEndForeachCommandCall(final PsiBuilder psiBuilder, final int n) {
        if (!GeneratedParserUtilBase.recursion_guard_(psiBuilder, n, "CMakeEndForeachCommandCall")) {
            return false;
        }
        if (!GeneratedParserUtilBase.nextTokenIs(psiBuilder, CMakeElementTypes.ENDFOREACH)) {
            return false;
        }
        final PsiBuilder.Marker enter_section_ = GeneratedParserUtilBase.enter_section_(psiBuilder, n, 0, CMakeElementTypes.C_MAKE_END_FOREACH_COMMAND_CALL, "<endforeach call>");
        final boolean cMakeEndForeachCommandCallName = CMakeEndForeachCommandCallName(psiBuilder, n + 1);
        GeneratedParserUtilBase.exit_section_(psiBuilder, n, enter_section_, cMakeEndForeachCommandCallName, false, null);
        return cMakeEndForeachCommandCallName;
    }
    
    public static boolean CMakeEndForeachCommandCallName(final PsiBuilder psiBuilder, final int n) {
        if (!GeneratedParserUtilBase.recursion_guard_(psiBuilder, n, "CMakeEndForeachCommandCallName")) {
            return false;
        }
        if (!GeneratedParserUtilBase.nextTokenIs(psiBuilder, CMakeElementTypes.ENDFOREACH)) {
            return false;
        }
        final PsiBuilder.Marker enter_section_ = GeneratedParserUtilBase.enter_section_(psiBuilder, n, 0, CMakeElementTypes.C_MAKE_END_FOREACH_COMMAND_CALL_NAME, "<endforeach call name>");
        final boolean consumeToken = GeneratedParserUtilBase.consumeToken(psiBuilder, CMakeElementTypes.ENDFOREACH);
        GeneratedParserUtilBase.exit_section_(psiBuilder, n, enter_section_, consumeToken, false, null);
        return consumeToken;
    }
    
    public static boolean CMakeEndFunctionCommand(final PsiBuilder psiBuilder, final int n) {
        if (!GeneratedParserUtilBase.recursion_guard_(psiBuilder, n, "CMakeEndFunctionCommand")) {
            return false;
        }
        if (!GeneratedParserUtilBase.nextTokenIs(psiBuilder, CMakeElementTypes.ENDFUNCTION)) {
            return false;
        }
        final PsiBuilder.Marker enter_section_ = GeneratedParserUtilBase.enter_section_(psiBuilder, n, 0, CMakeElementTypes.C_MAKE_END_FUNCTION_COMMAND, null);
        final boolean cMakeEndFunctionCommandCall;
        final boolean b = (cMakeEndFunctionCommandCall = CMakeEndFunctionCommandCall(psiBuilder, n + 1)) && arg_list(psiBuilder, n + 1);
        GeneratedParserUtilBase.exit_section_(psiBuilder, n, enter_section_, b, cMakeEndFunctionCommandCall, null);
        return b || cMakeEndFunctionCommandCall;
    }
    
    public static boolean CMakeEndFunctionCommandCall(final PsiBuilder psiBuilder, final int n) {
        if (!GeneratedParserUtilBase.recursion_guard_(psiBuilder, n, "CMakeEndFunctionCommandCall")) {
            return false;
        }
        if (!GeneratedParserUtilBase.nextTokenIs(psiBuilder, CMakeElementTypes.ENDFUNCTION)) {
            return false;
        }
        final PsiBuilder.Marker enter_section_ = GeneratedParserUtilBase.enter_section_(psiBuilder, n, 0, CMakeElementTypes.C_MAKE_END_FUNCTION_COMMAND_CALL, "<endfunction call>");
        final boolean cMakeEndFunctionCommandCallName = CMakeEndFunctionCommandCallName(psiBuilder, n + 1);
        GeneratedParserUtilBase.exit_section_(psiBuilder, n, enter_section_, cMakeEndFunctionCommandCallName, false, null);
        return cMakeEndFunctionCommandCallName;
    }
    
    public static boolean CMakeEndFunctionCommandCallName(final PsiBuilder psiBuilder, final int n) {
        if (!GeneratedParserUtilBase.recursion_guard_(psiBuilder, n, "CMakeEndFunctionCommandCallName")) {
            return false;
        }
        if (!GeneratedParserUtilBase.nextTokenIs(psiBuilder, CMakeElementTypes.ENDFUNCTION)) {
            return false;
        }
        final PsiBuilder.Marker enter_section_ = GeneratedParserUtilBase.enter_section_(psiBuilder, n, 0, CMakeElementTypes.C_MAKE_END_FUNCTION_COMMAND_CALL_NAME, "<endfunction call name>");
        final boolean consumeToken = GeneratedParserUtilBase.consumeToken(psiBuilder, CMakeElementTypes.ENDFUNCTION);
        GeneratedParserUtilBase.exit_section_(psiBuilder, n, enter_section_, consumeToken, false, null);
        return consumeToken;
    }
    
    public static boolean CMakeEndIfCommand(final PsiBuilder psiBuilder, final int n) {
        if (!GeneratedParserUtilBase.recursion_guard_(psiBuilder, n, "CMakeEndIfCommand")) {
            return false;
        }
        if (!GeneratedParserUtilBase.nextTokenIs(psiBuilder, CMakeElementTypes.ENDIF)) {
            return false;
        }
        final PsiBuilder.Marker enter_section_ = GeneratedParserUtilBase.enter_section_(psiBuilder, n, 0, CMakeElementTypes.C_MAKE_END_IF_COMMAND, null);
        final boolean cMakeEndIfCommandCall;
        final boolean b = (cMakeEndIfCommandCall = CMakeEndIfCommandCall(psiBuilder, n + 1)) && arg_list(psiBuilder, n + 1);
        GeneratedParserUtilBase.exit_section_(psiBuilder, n, enter_section_, b, cMakeEndIfCommandCall, null);
        return b || cMakeEndIfCommandCall;
    }
    
    public static boolean CMakeEndIfCommandCall(final PsiBuilder psiBuilder, final int n) {
        if (!GeneratedParserUtilBase.recursion_guard_(psiBuilder, n, "CMakeEndIfCommandCall")) {
            return false;
        }
        if (!GeneratedParserUtilBase.nextTokenIs(psiBuilder, CMakeElementTypes.ENDIF)) {
            return false;
        }
        final PsiBuilder.Marker enter_section_ = GeneratedParserUtilBase.enter_section_(psiBuilder, n, 0, CMakeElementTypes.C_MAKE_END_IF_COMMAND_CALL, "<endif call>");
        final boolean cMakeEndIfCommandCallName = CMakeEndIfCommandCallName(psiBuilder, n + 1);
        GeneratedParserUtilBase.exit_section_(psiBuilder, n, enter_section_, cMakeEndIfCommandCallName, false, null);
        return cMakeEndIfCommandCallName;
    }
    
    public static boolean CMakeEndIfCommandCallName(final PsiBuilder psiBuilder, final int n) {
        if (!GeneratedParserUtilBase.recursion_guard_(psiBuilder, n, "CMakeEndIfCommandCallName")) {
            return false;
        }
        if (!GeneratedParserUtilBase.nextTokenIs(psiBuilder, CMakeElementTypes.ENDIF)) {
            return false;
        }
        final PsiBuilder.Marker enter_section_ = GeneratedParserUtilBase.enter_section_(psiBuilder, n, 0, CMakeElementTypes.C_MAKE_END_IF_COMMAND_CALL_NAME, "<endif call name>");
        final boolean consumeToken = GeneratedParserUtilBase.consumeToken(psiBuilder, CMakeElementTypes.ENDIF);
        GeneratedParserUtilBase.exit_section_(psiBuilder, n, enter_section_, consumeToken, false, null);
        return consumeToken;
    }
    
    public static boolean CMakeEndMacroCommand(final PsiBuilder psiBuilder, final int n) {
        if (!GeneratedParserUtilBase.recursion_guard_(psiBuilder, n, "CMakeEndMacroCommand")) {
            return false;
        }
        if (!GeneratedParserUtilBase.nextTokenIs(psiBuilder, CMakeElementTypes.ENDMACRO)) {
            return false;
        }
        final PsiBuilder.Marker enter_section_ = GeneratedParserUtilBase.enter_section_(psiBuilder, n, 0, CMakeElementTypes.C_MAKE_END_MACRO_COMMAND, null);
        final boolean cMakeEndMacroCommandCall;
        final boolean b = (cMakeEndMacroCommandCall = CMakeEndMacroCommandCall(psiBuilder, n + 1)) && arg_list(psiBuilder, n + 1);
        GeneratedParserUtilBase.exit_section_(psiBuilder, n, enter_section_, b, cMakeEndMacroCommandCall, null);
        return b || cMakeEndMacroCommandCall;
    }
    
    public static boolean CMakeEndMacroCommandCall(final PsiBuilder psiBuilder, final int n) {
        if (!GeneratedParserUtilBase.recursion_guard_(psiBuilder, n, "CMakeEndMacroCommandCall")) {
            return false;
        }
        if (!GeneratedParserUtilBase.nextTokenIs(psiBuilder, CMakeElementTypes.ENDMACRO)) {
            return false;
        }
        final PsiBuilder.Marker enter_section_ = GeneratedParserUtilBase.enter_section_(psiBuilder, n, 0, CMakeElementTypes.C_MAKE_END_MACRO_COMMAND_CALL, "<endmacro call>");
        final boolean cMakeEndMacroCommandCallName = CMakeEndMacroCommandCallName(psiBuilder, n + 1);
        GeneratedParserUtilBase.exit_section_(psiBuilder, n, enter_section_, cMakeEndMacroCommandCallName, false, null);
        return cMakeEndMacroCommandCallName;
    }
    
    public static boolean CMakeEndMacroCommandCallName(final PsiBuilder psiBuilder, final int n) {
        if (!GeneratedParserUtilBase.recursion_guard_(psiBuilder, n, "CMakeEndMacroCommandCallName")) {
            return false;
        }
        if (!GeneratedParserUtilBase.nextTokenIs(psiBuilder, CMakeElementTypes.ENDMACRO)) {
            return false;
        }
        final PsiBuilder.Marker enter_section_ = GeneratedParserUtilBase.enter_section_(psiBuilder, n, 0, CMakeElementTypes.C_MAKE_END_MACRO_COMMAND_CALL_NAME, "<endmacro call name>");
        final boolean consumeToken = GeneratedParserUtilBase.consumeToken(psiBuilder, CMakeElementTypes.ENDMACRO);
        GeneratedParserUtilBase.exit_section_(psiBuilder, n, enter_section_, consumeToken, false, null);
        return consumeToken;
    }
    
    public static boolean CMakeEndWhileCommand(final PsiBuilder psiBuilder, final int n) {
        if (!GeneratedParserUtilBase.recursion_guard_(psiBuilder, n, "CMakeEndWhileCommand")) {
            return false;
        }
        if (!GeneratedParserUtilBase.nextTokenIs(psiBuilder, CMakeElementTypes.ENDWHILE)) {
            return false;
        }
        final PsiBuilder.Marker enter_section_ = GeneratedParserUtilBase.enter_section_(psiBuilder, n, 0, CMakeElementTypes.C_MAKE_END_WHILE_COMMAND, null);
        final boolean cMakeEndWhileCommandCall;
        final boolean b = (cMakeEndWhileCommandCall = CMakeEndWhileCommandCall(psiBuilder, n + 1)) && arg_list(psiBuilder, n + 1);
        GeneratedParserUtilBase.exit_section_(psiBuilder, n, enter_section_, b, cMakeEndWhileCommandCall, null);
        return b || cMakeEndWhileCommandCall;
    }
    
    public static boolean CMakeEndWhileCommandCall(final PsiBuilder psiBuilder, final int n) {
        if (!GeneratedParserUtilBase.recursion_guard_(psiBuilder, n, "CMakeEndWhileCommandCall")) {
            return false;
        }
        if (!GeneratedParserUtilBase.nextTokenIs(psiBuilder, CMakeElementTypes.ENDWHILE)) {
            return false;
        }
        final PsiBuilder.Marker enter_section_ = GeneratedParserUtilBase.enter_section_(psiBuilder, n, 0, CMakeElementTypes.C_MAKE_END_WHILE_COMMAND_CALL, "<endwhile call>");
        final boolean cMakeEndWhileCommandCallName = CMakeEndWhileCommandCallName(psiBuilder, n + 1);
        GeneratedParserUtilBase.exit_section_(psiBuilder, n, enter_section_, cMakeEndWhileCommandCallName, false, null);
        return cMakeEndWhileCommandCallName;
    }
    
    public static boolean CMakeEndWhileCommandCallName(final PsiBuilder psiBuilder, final int n) {
        if (!GeneratedParserUtilBase.recursion_guard_(psiBuilder, n, "CMakeEndWhileCommandCallName")) {
            return false;
        }
        if (!GeneratedParserUtilBase.nextTokenIs(psiBuilder, CMakeElementTypes.ENDWHILE)) {
            return false;
        }
        final PsiBuilder.Marker enter_section_ = GeneratedParserUtilBase.enter_section_(psiBuilder, n, 0, CMakeElementTypes.C_MAKE_END_WHILE_COMMAND_CALL_NAME, "<endwhile call name>");
        final boolean consumeToken = GeneratedParserUtilBase.consumeToken(psiBuilder, CMakeElementTypes.ENDWHILE);
        GeneratedParserUtilBase.exit_section_(psiBuilder, n, enter_section_, consumeToken, false, null);
        return consumeToken;
    }
    
    public static boolean CMakeForeachCommand(final PsiBuilder psiBuilder, final int n) {
        if (!GeneratedParserUtilBase.recursion_guard_(psiBuilder, n, "CMakeForeachCommand")) {
            return false;
        }
        if (!GeneratedParserUtilBase.nextTokenIs(psiBuilder, CMakeElementTypes.FOREACH)) {
            return false;
        }
        final PsiBuilder.Marker enter_section_ = GeneratedParserUtilBase.enter_section_(psiBuilder, n, 0, CMakeElementTypes.C_MAKE_FOREACH_COMMAND, null);
        final boolean cMakeForeachCommandCall;
        final boolean b = (cMakeForeachCommandCall = CMakeForeachCommandCall(psiBuilder, n + 1)) && GeneratedParserUtilBase.report_error_(psiBuilder, arg_list(psiBuilder, n + 1));
        final boolean b2 = cMakeForeachCommandCall && GeneratedParserUtilBase.report_error_(psiBuilder, wrap_block(psiBuilder, n + 1)) && b;
        final boolean b3 = cMakeForeachCommandCall && CMakeEndForeachCommand(psiBuilder, n + 1) && b2;
        GeneratedParserUtilBase.exit_section_(psiBuilder, n, enter_section_, b3, cMakeForeachCommandCall, null);
        return b3 || cMakeForeachCommandCall;
    }
    
    public static boolean CMakeForeachCommandCall(final PsiBuilder psiBuilder, final int n) {
        if (!GeneratedParserUtilBase.recursion_guard_(psiBuilder, n, "CMakeForeachCommandCall")) {
            return false;
        }
        if (!GeneratedParserUtilBase.nextTokenIs(psiBuilder, CMakeElementTypes.FOREACH)) {
            return false;
        }
        final PsiBuilder.Marker enter_section_ = GeneratedParserUtilBase.enter_section_(psiBuilder, n, 0, CMakeElementTypes.C_MAKE_FOREACH_COMMAND_CALL, "<foreach call>");
        final boolean cMakeForeachCommandCallName = CMakeForeachCommandCallName(psiBuilder, n + 1);
        GeneratedParserUtilBase.exit_section_(psiBuilder, n, enter_section_, cMakeForeachCommandCallName, false, null);
        return cMakeForeachCommandCallName;
    }
    
    public static boolean CMakeForeachCommandCallName(final PsiBuilder psiBuilder, final int n) {
        if (!GeneratedParserUtilBase.recursion_guard_(psiBuilder, n, "CMakeForeachCommandCallName")) {
            return false;
        }
        if (!GeneratedParserUtilBase.nextTokenIs(psiBuilder, CMakeElementTypes.FOREACH)) {
            return false;
        }
        final PsiBuilder.Marker enter_section_ = GeneratedParserUtilBase.enter_section_(psiBuilder, n, 0, CMakeElementTypes.C_MAKE_FOREACH_COMMAND_CALL_NAME, "<foreach call name>");
        final boolean consumeToken = GeneratedParserUtilBase.consumeToken(psiBuilder, CMakeElementTypes.FOREACH);
        GeneratedParserUtilBase.exit_section_(psiBuilder, n, enter_section_, consumeToken, false, null);
        return consumeToken;
    }
    
    public static boolean CMakeFunctionCommand(final PsiBuilder psiBuilder, final int n) {
        if (!GeneratedParserUtilBase.recursion_guard_(psiBuilder, n, "CMakeFunctionCommand")) {
            return false;
        }
        if (!GeneratedParserUtilBase.nextTokenIs(psiBuilder, CMakeElementTypes.FUNCTION)) {
            return false;
        }
        final PsiBuilder.Marker enter_section_ = GeneratedParserUtilBase.enter_section_(psiBuilder, n, 0, CMakeElementTypes.C_MAKE_FUNCTION_COMMAND, null);
        final boolean cMakeFunctionCommandCall;
        final boolean b = (cMakeFunctionCommandCall = CMakeFunctionCommandCall(psiBuilder, n + 1)) && GeneratedParserUtilBase.report_error_(psiBuilder, arg_list(psiBuilder, n + 1));
        final boolean b2 = cMakeFunctionCommandCall && GeneratedParserUtilBase.report_error_(psiBuilder, wrap_block(psiBuilder, n + 1)) && b;
        final boolean b3 = cMakeFunctionCommandCall && CMakeEndFunctionCommand(psiBuilder, n + 1) && b2;
        GeneratedParserUtilBase.exit_section_(psiBuilder, n, enter_section_, b3, cMakeFunctionCommandCall, null);
        return b3 || cMakeFunctionCommandCall;
    }
    
    public static boolean CMakeFunctionCommandCall(final PsiBuilder psiBuilder, final int n) {
        if (!GeneratedParserUtilBase.recursion_guard_(psiBuilder, n, "CMakeFunctionCommandCall")) {
            return false;
        }
        if (!GeneratedParserUtilBase.nextTokenIs(psiBuilder, CMakeElementTypes.FUNCTION)) {
            return false;
        }
        final PsiBuilder.Marker enter_section_ = GeneratedParserUtilBase.enter_section_(psiBuilder, n, 0, CMakeElementTypes.C_MAKE_FUNCTION_COMMAND_CALL, "<function call>");
        final boolean cMakeFunctionCommandCallName = CMakeFunctionCommandCallName(psiBuilder, n + 1);
        GeneratedParserUtilBase.exit_section_(psiBuilder, n, enter_section_, cMakeFunctionCommandCallName, false, null);
        return cMakeFunctionCommandCallName;
    }
    
    public static boolean CMakeFunctionCommandCallName(final PsiBuilder psiBuilder, final int n) {
        if (!GeneratedParserUtilBase.recursion_guard_(psiBuilder, n, "CMakeFunctionCommandCallName")) {
            return false;
        }
        if (!GeneratedParserUtilBase.nextTokenIs(psiBuilder, CMakeElementTypes.FUNCTION)) {
            return false;
        }
        final PsiBuilder.Marker enter_section_ = GeneratedParserUtilBase.enter_section_(psiBuilder, n, 0, CMakeElementTypes.C_MAKE_FUNCTION_COMMAND_CALL_NAME, "<function call name>");
        final boolean consumeToken = GeneratedParserUtilBase.consumeToken(psiBuilder, CMakeElementTypes.FUNCTION);
        GeneratedParserUtilBase.exit_section_(psiBuilder, n, enter_section_, consumeToken, false, null);
        return consumeToken;
    }
    
    public static boolean CMakeIfCommand(final PsiBuilder psiBuilder, final int n) {
        if (!GeneratedParserUtilBase.recursion_guard_(psiBuilder, n, "CMakeIfCommand")) {
            return false;
        }
        if (!GeneratedParserUtilBase.nextTokenIs(psiBuilder, CMakeElementTypes.IF)) {
            return false;
        }
        final PsiBuilder.Marker enter_section_ = GeneratedParserUtilBase.enter_section_(psiBuilder, n, 0, CMakeElementTypes.C_MAKE_IF_COMMAND, null);
        final boolean cMakeIfCommandCall;
        final boolean b = (cMakeIfCommandCall = CMakeIfCommandCall(psiBuilder, n + 1)) && GeneratedParserUtilBase.report_error_(psiBuilder, arg_list(psiBuilder, n + 1));
        final boolean b2 = cMakeIfCommandCall && GeneratedParserUtilBase.report_error_(psiBuilder, wrap_block(psiBuilder, n + 1)) && b;
        final boolean b3 = cMakeIfCommandCall && GeneratedParserUtilBase.report_error_(psiBuilder, b(psiBuilder, n + 1)) && b2;
        final boolean b4 = cMakeIfCommandCall && GeneratedParserUtilBase.report_error_(psiBuilder, a(psiBuilder, n + 1)) && b3;
        final boolean b5 = cMakeIfCommandCall && CMakeEndIfCommand(psiBuilder, n + 1) && b4;
        GeneratedParserUtilBase.exit_section_(psiBuilder, n, enter_section_, b5, cMakeIfCommandCall, null);
        return b5 || cMakeIfCommandCall;
    }
    
    private static boolean b(final PsiBuilder psiBuilder, final int n) {
        if (!GeneratedParserUtilBase.recursion_guard_(psiBuilder, n, "CMakeIfCommand_3")) {
            return false;
        }
        int n2 = GeneratedParserUtilBase.current_position_(psiBuilder);
        while (j(psiBuilder, n + 1)) {
            if (!GeneratedParserUtilBase.empty_element_parsed_guard_(psiBuilder, "CMakeIfCommand_3", n2)) {
                return true;
            }
            n2 = GeneratedParserUtilBase.current_position_(psiBuilder);
        }
        return true;
    }
    
    private static boolean j(final PsiBuilder psiBuilder, final int n) {
        if (!GeneratedParserUtilBase.recursion_guard_(psiBuilder, n, "CMakeIfCommand_3_0")) {
            return false;
        }
        final PsiBuilder.Marker enter_section_ = GeneratedParserUtilBase.enter_section_(psiBuilder);
        final boolean b = CMakeElseIfCommand(psiBuilder, n + 1) && wrap_block(psiBuilder, n + 1);
        GeneratedParserUtilBase.exit_section_(psiBuilder, enter_section_, null, b);
        return b;
    }
    
    private static boolean a(final PsiBuilder psiBuilder, final int n) {
        if (!GeneratedParserUtilBase.recursion_guard_(psiBuilder, n, "CMakeIfCommand_4")) {
            return false;
        }
        h(psiBuilder, n + 1);
        return true;
    }
    
    private static boolean h(final PsiBuilder psiBuilder, final int n) {
        if (!GeneratedParserUtilBase.recursion_guard_(psiBuilder, n, "CMakeIfCommand_4_0")) {
            return false;
        }
        final PsiBuilder.Marker enter_section_ = GeneratedParserUtilBase.enter_section_(psiBuilder);
        final boolean b = CMakeElseCommand(psiBuilder, n + 1) && wrap_block(psiBuilder, n + 1);
        GeneratedParserUtilBase.exit_section_(psiBuilder, enter_section_, null, b);
        return b;
    }
    
    public static boolean CMakeIfCommandCall(final PsiBuilder psiBuilder, final int n) {
        if (!GeneratedParserUtilBase.recursion_guard_(psiBuilder, n, "CMakeIfCommandCall")) {
            return false;
        }
        if (!GeneratedParserUtilBase.nextTokenIs(psiBuilder, CMakeElementTypes.IF)) {
            return false;
        }
        final PsiBuilder.Marker enter_section_ = GeneratedParserUtilBase.enter_section_(psiBuilder, n, 0, CMakeElementTypes.C_MAKE_IF_COMMAND_CALL, "<if call>");
        final boolean cMakeIfCommandCallName = CMakeIfCommandCallName(psiBuilder, n + 1);
        GeneratedParserUtilBase.exit_section_(psiBuilder, n, enter_section_, cMakeIfCommandCallName, false, null);
        return cMakeIfCommandCallName;
    }
    
    public static boolean CMakeIfCommandCallName(final PsiBuilder psiBuilder, final int n) {
        if (!GeneratedParserUtilBase.recursion_guard_(psiBuilder, n, "CMakeIfCommandCallName")) {
            return false;
        }
        if (!GeneratedParserUtilBase.nextTokenIs(psiBuilder, CMakeElementTypes.IF)) {
            return false;
        }
        final PsiBuilder.Marker enter_section_ = GeneratedParserUtilBase.enter_section_(psiBuilder, n, 0, CMakeElementTypes.C_MAKE_IF_COMMAND_CALL_NAME, "<if call name>");
        final boolean consumeToken = GeneratedParserUtilBase.consumeToken(psiBuilder, CMakeElementTypes.IF);
        GeneratedParserUtilBase.exit_section_(psiBuilder, n, enter_section_, consumeToken, false, null);
        return consumeToken;
    }
    
    public static boolean CMakeLiteral(final PsiBuilder psiBuilder, final int n) {
        if (!GeneratedParserUtilBase.recursion_guard_(psiBuilder, n, "CMakeLiteral")) {
            return false;
        }
        if (!GeneratedParserUtilBase.nextTokenIs(psiBuilder, CMakeElementTypes.LITERAL)) {
            return false;
        }
        final PsiBuilder.Marker enter_section_ = GeneratedParserUtilBase.enter_section_(psiBuilder, n, 0, CMakeElementTypes.C_MAKE_LITERAL, "<literal>");
        final boolean consumeToken = GeneratedParserUtilBase.consumeToken(psiBuilder, CMakeElementTypes.LITERAL);
        GeneratedParserUtilBase.exit_section_(psiBuilder, n, enter_section_, consumeToken, false, null);
        return consumeToken;
    }
    
    public static boolean CMakeMacroCommand(final PsiBuilder psiBuilder, final int n) {
        if (!GeneratedParserUtilBase.recursion_guard_(psiBuilder, n, "CMakeMacroCommand")) {
            return false;
        }
        if (!GeneratedParserUtilBase.nextTokenIs(psiBuilder, CMakeElementTypes.MACRO)) {
            return false;
        }
        final PsiBuilder.Marker enter_section_ = GeneratedParserUtilBase.enter_section_(psiBuilder, n, 0, CMakeElementTypes.C_MAKE_MACRO_COMMAND, null);
        final boolean cMakeMacroCommandCall;
        final boolean b = (cMakeMacroCommandCall = CMakeMacroCommandCall(psiBuilder, n + 1)) && GeneratedParserUtilBase.report_error_(psiBuilder, arg_list(psiBuilder, n + 1));
        final boolean b2 = cMakeMacroCommandCall && GeneratedParserUtilBase.report_error_(psiBuilder, wrap_block(psiBuilder, n + 1)) && b;
        final boolean b3 = cMakeMacroCommandCall && CMakeEndMacroCommand(psiBuilder, n + 1) && b2;
        GeneratedParserUtilBase.exit_section_(psiBuilder, n, enter_section_, b3, cMakeMacroCommandCall, null);
        return b3 || cMakeMacroCommandCall;
    }
    
    public static boolean CMakeMacroCommandCall(final PsiBuilder psiBuilder, final int n) {
        if (!GeneratedParserUtilBase.recursion_guard_(psiBuilder, n, "CMakeMacroCommandCall")) {
            return false;
        }
        if (!GeneratedParserUtilBase.nextTokenIs(psiBuilder, CMakeElementTypes.MACRO)) {
            return false;
        }
        final PsiBuilder.Marker enter_section_ = GeneratedParserUtilBase.enter_section_(psiBuilder, n, 0, CMakeElementTypes.C_MAKE_MACRO_COMMAND_CALL, "<macro call>");
        final boolean cMakeMacroCommandCallName = CMakeMacroCommandCallName(psiBuilder, n + 1);
        GeneratedParserUtilBase.exit_section_(psiBuilder, n, enter_section_, cMakeMacroCommandCallName, false, null);
        return cMakeMacroCommandCallName;
    }
    
    public static boolean CMakeMacroCommandCallName(final PsiBuilder psiBuilder, final int n) {
        if (!GeneratedParserUtilBase.recursion_guard_(psiBuilder, n, "CMakeMacroCommandCallName")) {
            return false;
        }
        if (!GeneratedParserUtilBase.nextTokenIs(psiBuilder, CMakeElementTypes.MACRO)) {
            return false;
        }
        final PsiBuilder.Marker enter_section_ = GeneratedParserUtilBase.enter_section_(psiBuilder, n, 0, CMakeElementTypes.C_MAKE_MACRO_COMMAND_CALL_NAME, "<macro call name>");
        final boolean consumeToken = GeneratedParserUtilBase.consumeToken(psiBuilder, CMakeElementTypes.MACRO);
        GeneratedParserUtilBase.exit_section_(psiBuilder, n, enter_section_, consumeToken, false, null);
        return consumeToken;
    }
    
    public static boolean CMakeRoutine(final PsiBuilder psiBuilder, final int n) {
        GeneratedParserUtilBase.exit_section_(psiBuilder, GeneratedParserUtilBase.enter_section_(psiBuilder), CMakeElementTypes.C_MAKE_ROUTINE, true);
        return true;
    }
    
    public static boolean CMakeWhileCommand(final PsiBuilder psiBuilder, final int n) {
        if (!GeneratedParserUtilBase.recursion_guard_(psiBuilder, n, "CMakeWhileCommand")) {
            return false;
        }
        if (!GeneratedParserUtilBase.nextTokenIs(psiBuilder, CMakeElementTypes.WHILE)) {
            return false;
        }
        final PsiBuilder.Marker enter_section_ = GeneratedParserUtilBase.enter_section_(psiBuilder, n, 0, CMakeElementTypes.C_MAKE_WHILE_COMMAND, null);
        final boolean cMakeWhileCommandCall;
        final boolean b = (cMakeWhileCommandCall = CMakeWhileCommandCall(psiBuilder, n + 1)) && GeneratedParserUtilBase.report_error_(psiBuilder, arg_list(psiBuilder, n + 1));
        final boolean b2 = cMakeWhileCommandCall && GeneratedParserUtilBase.report_error_(psiBuilder, wrap_block(psiBuilder, n + 1)) && b;
        final boolean b3 = cMakeWhileCommandCall && CMakeEndWhileCommand(psiBuilder, n + 1) && b2;
        GeneratedParserUtilBase.exit_section_(psiBuilder, n, enter_section_, b3, cMakeWhileCommandCall, null);
        return b3 || cMakeWhileCommandCall;
    }
    
    public static boolean CMakeWhileCommandCall(final PsiBuilder psiBuilder, final int n) {
        if (!GeneratedParserUtilBase.recursion_guard_(psiBuilder, n, "CMakeWhileCommandCall")) {
            return false;
        }
        if (!GeneratedParserUtilBase.nextTokenIs(psiBuilder, CMakeElementTypes.WHILE)) {
            return false;
        }
        final PsiBuilder.Marker enter_section_ = GeneratedParserUtilBase.enter_section_(psiBuilder, n, 0, CMakeElementTypes.C_MAKE_WHILE_COMMAND_CALL, "<while call>");
        final boolean cMakeWhileCommandCallName = CMakeWhileCommandCallName(psiBuilder, n + 1);
        GeneratedParserUtilBase.exit_section_(psiBuilder, n, enter_section_, cMakeWhileCommandCallName, false, null);
        return cMakeWhileCommandCallName;
    }
    
    public static boolean CMakeWhileCommandCallName(final PsiBuilder psiBuilder, final int n) {
        if (!GeneratedParserUtilBase.recursion_guard_(psiBuilder, n, "CMakeWhileCommandCallName")) {
            return false;
        }
        if (!GeneratedParserUtilBase.nextTokenIs(psiBuilder, CMakeElementTypes.WHILE)) {
            return false;
        }
        final PsiBuilder.Marker enter_section_ = GeneratedParserUtilBase.enter_section_(psiBuilder, n, 0, CMakeElementTypes.C_MAKE_WHILE_COMMAND_CALL_NAME, "<while call name>");
        final boolean consumeToken = GeneratedParserUtilBase.consumeToken(psiBuilder, CMakeElementTypes.WHILE);
        GeneratedParserUtilBase.exit_section_(psiBuilder, n, enter_section_, consumeToken, false, null);
        return consumeToken;
    }
    
    static boolean any_EOL(final PsiBuilder psiBuilder, final int n) {
        if (!GeneratedParserUtilBase.recursion_guard_(psiBuilder, n, "any_EOL")) {
            return false;
        }
        if (!GeneratedParserUtilBase.nextTokenIs(psiBuilder, "", CMakeElementTypes.EOL, CMakeElementTypes.COMMENT)) {
            return false;
        }
        final PsiBuilder.Marker enter_section_ = GeneratedParserUtilBase.enter_section_(psiBuilder);
        final boolean b = c(psiBuilder, n + 1) && GeneratedParserUtilBase.consumeToken(psiBuilder, CMakeElementTypes.EOL);
        GeneratedParserUtilBase.exit_section_(psiBuilder, enter_section_, null, b);
        return b;
    }
    
    private static boolean c(final PsiBuilder psiBuilder, final int n) {
        if (!GeneratedParserUtilBase.recursion_guard_(psiBuilder, n, "any_EOL_0")) {
            return false;
        }
        GeneratedParserUtilBase.consumeToken(psiBuilder, CMakeElementTypes.COMMENT);
        return true;
    }
    
    static boolean arg_group(final PsiBuilder psiBuilder, final int n) {
        if (!GeneratedParserUtilBase.recursion_guard_(psiBuilder, n, "arg_group")) {
            return false;
        }
        if (!GeneratedParserUtilBase.nextTokenIs(psiBuilder, CMakeElementTypes.LPAR)) {
            return false;
        }
        final PsiBuilder.Marker enter_section_ = GeneratedParserUtilBase.enter_section_(psiBuilder, n, 0);
        final boolean consumeToken;
        final boolean b = (consumeToken = GeneratedParserUtilBase.consumeToken(psiBuilder, CMakeElementTypes.LPAR)) && GeneratedParserUtilBase.report_error_(psiBuilder, g(psiBuilder, n + 1));
        final boolean b2 = consumeToken && GeneratedParserUtilBase.consumeToken(psiBuilder, CMakeElementTypes.RPAR) && b;
        GeneratedParserUtilBase.exit_section_(psiBuilder, n, enter_section_, b2, consumeToken, null);
        return b2 || consumeToken;
    }
    
    private static boolean g(final PsiBuilder psiBuilder, final int n) {
        if (!GeneratedParserUtilBase.recursion_guard_(psiBuilder, n, "arg_group_1")) {
            return false;
        }
        int n2 = GeneratedParserUtilBase.current_position_(psiBuilder);
        while (f(psiBuilder, n + 1)) {
            if (!GeneratedParserUtilBase.empty_element_parsed_guard_(psiBuilder, "arg_group_1", n2)) {
                return true;
            }
            n2 = GeneratedParserUtilBase.current_position_(psiBuilder);
        }
        return true;
    }
    
    private static boolean f(final PsiBuilder psiBuilder, final int n) {
        if (!GeneratedParserUtilBase.recursion_guard_(psiBuilder, n, "arg_group_1_0")) {
            return false;
        }
        final PsiBuilder.Marker enter_section_ = GeneratedParserUtilBase.enter_section_(psiBuilder);
        final boolean b = i(psiBuilder, n + 1) && d(psiBuilder, n + 1);
        GeneratedParserUtilBase.exit_section_(psiBuilder, enter_section_, null, b);
        return b;
    }
    
    private static boolean i(final PsiBuilder psiBuilder, final int n) {
        if (!GeneratedParserUtilBase.recursion_guard_(psiBuilder, n, "arg_group_1_0_0")) {
            return false;
        }
        final PsiBuilder.Marker enter_section_ = GeneratedParserUtilBase.enter_section_(psiBuilder);
        boolean b = CMakeArgument(psiBuilder, n + 1);
        if (!b) {
            b = arg_group(psiBuilder, n + 1);
        }
        GeneratedParserUtilBase.exit_section_(psiBuilder, enter_section_, null, b);
        return b;
    }
    
    private static boolean d(final PsiBuilder psiBuilder, final int n) {
        if (!GeneratedParserUtilBase.recursion_guard_(psiBuilder, n, "arg_group_1_0_1")) {
            return false;
        }
        GeneratedParserUtilBase.consumeToken(psiBuilder, CMakeElementTypes.SEMI);
        return true;
    }
    
    static boolean arg_list(final PsiBuilder psiBuilder, final int n) {
        if (!GeneratedParserUtilBase.recursion_guard_(psiBuilder, n, "arg_list")) {
            return false;
        }
        if (!GeneratedParserUtilBase.nextTokenIs(psiBuilder, CMakeElementTypes.LPAR)) {
            return false;
        }
        final PsiBuilder.Marker enter_section_ = GeneratedParserUtilBase.enter_section_(psiBuilder, n, 0);
        final boolean cMakeCommandArguments;
        final boolean b = (cMakeCommandArguments = CMakeCommandArguments(psiBuilder, n + 1)) && any_EOL(psiBuilder, n + 1);
        GeneratedParserUtilBase.exit_section_(psiBuilder, n, enter_section_, b, cMakeCommandArguments, null);
        return b || cMakeCommandArguments;
    }
    
    static boolean bracket_arg(final PsiBuilder psiBuilder, final int n) {
        if (!GeneratedParserUtilBase.recursion_guard_(psiBuilder, n, "bracket_arg")) {
            return false;
        }
        if (!GeneratedParserUtilBase.nextTokenIs(psiBuilder, CMakeElementTypes.BRACKET_ARG_START)) {
            return false;
        }
        final PsiBuilder.Marker enter_section_ = GeneratedParserUtilBase.enter_section_(psiBuilder, n, 0);
        final boolean consumeToken;
        final boolean b = (consumeToken = GeneratedParserUtilBase.consumeToken(psiBuilder, CMakeElementTypes.BRACKET_ARG_START)) && GeneratedParserUtilBase.report_error_(psiBuilder, bracket_arg_contents(psiBuilder, n + 1));
        final boolean b2 = consumeToken && GeneratedParserUtilBase.consumeToken(psiBuilder, CMakeElementTypes.BRACKET_ARG_END) && b;
        GeneratedParserUtilBase.exit_section_(psiBuilder, n, enter_section_, b2, consumeToken, null);
        return b2 || consumeToken;
    }
    
    public static boolean bracket_arg_contents(final PsiBuilder psiBuilder, final int n) {
        if (!GeneratedParserUtilBase.recursion_guard_(psiBuilder, n, "bracket_arg_contents")) {
            return false;
        }
        final PsiBuilder.Marker enter_section_ = GeneratedParserUtilBase.enter_section_(psiBuilder, n, 1, CMakeElementTypes.C_MAKE_LITERAL, "<bracket arg contents>");
        CMakeLiteral(psiBuilder, n + 1);
        GeneratedParserUtilBase.exit_section_(psiBuilder, n, enter_section_, true, false, null);
        return true;
    }
    
    static boolean command_invocation(final PsiBuilder psiBuilder, final int n) {
        if (!GeneratedParserUtilBase.recursion_guard_(psiBuilder, n, "command_invocation")) {
            return false;
        }
        final PsiBuilder.Marker enter_section_ = GeneratedParserUtilBase.enter_section_(psiBuilder);
        boolean b = CMakeIfCommand(psiBuilder, n + 1);
        if (!b) {
            b = CMakeWhileCommand(psiBuilder, n + 1);
        }
        if (!b) {
            b = CMakeForeachCommand(psiBuilder, n + 1);
        }
        if (!b) {
            b = CMakeFunctionCommand(psiBuilder, n + 1);
        }
        if (!b) {
            b = CMakeMacroCommand(psiBuilder, n + 1);
        }
        if (!b) {
            b = CMakeCommand(psiBuilder, n + 1);
        }
        GeneratedParserUtilBase.exit_section_(psiBuilder, enter_section_, null, b);
        return b;
    }
    
    static boolean file_elements(final PsiBuilder psiBuilder, final int n) {
        if (!GeneratedParserUtilBase.recursion_guard_(psiBuilder, n, "file_elements")) {
            return false;
        }
        int n2 = GeneratedParserUtilBase.current_position_(psiBuilder);
        while (e(psiBuilder, n + 1)) {
            if (!GeneratedParserUtilBase.empty_element_parsed_guard_(psiBuilder, "file_elements", n2)) {
                return true;
            }
            n2 = GeneratedParserUtilBase.current_position_(psiBuilder);
        }
        return true;
    }
    
    private static boolean e(final PsiBuilder psiBuilder, final int n) {
        if (!GeneratedParserUtilBase.recursion_guard_(psiBuilder, n, "file_elements_0")) {
            return false;
        }
        final PsiBuilder.Marker enter_section_ = GeneratedParserUtilBase.enter_section_(psiBuilder);
        boolean b = command_invocation(psiBuilder, n + 1);
        if (!b) {
            b = any_EOL(psiBuilder, n + 1);
        }
        GeneratedParserUtilBase.exit_section_(psiBuilder, enter_section_, null, b);
        return b;
    }
    
    static boolean quote_arg(final PsiBuilder psiBuilder, final int n) {
        if (!GeneratedParserUtilBase.recursion_guard_(psiBuilder, n, "quote_arg")) {
            return false;
        }
        if (!GeneratedParserUtilBase.nextTokenIs(psiBuilder, CMakeElementTypes.QUOTE)) {
            return false;
        }
        final PsiBuilder.Marker enter_section_ = GeneratedParserUtilBase.enter_section_(psiBuilder, n, 0);
        final boolean consumeToken;
        final boolean b = (consumeToken = GeneratedParserUtilBase.consumeToken(psiBuilder, CMakeElementTypes.QUOTE)) && GeneratedParserUtilBase.report_error_(psiBuilder, quote_arg_contents(psiBuilder, n + 1));
        final boolean b2 = consumeToken && GeneratedParserUtilBase.consumeToken(psiBuilder, CMakeElementTypes.QUOTE) && b;
        GeneratedParserUtilBase.exit_section_(psiBuilder, n, enter_section_, b2, consumeToken, null);
        return b2 || consumeToken;
    }
    
    public static boolean quote_arg_contents(final PsiBuilder psiBuilder, final int n) {
        if (!GeneratedParserUtilBase.recursion_guard_(psiBuilder, n, "quote_arg_contents")) {
            return false;
        }
        final PsiBuilder.Marker enter_section_ = GeneratedParserUtilBase.enter_section_(psiBuilder, n, 1, CMakeElementTypes.C_MAKE_LITERAL, "<quote arg contents>");
        CMakeLiteral(psiBuilder, n + 1);
        GeneratedParserUtilBase.exit_section_(psiBuilder, n, enter_section_, true, false, null);
        return true;
    }
    
    static boolean root(final PsiBuilder psiBuilder, final int n) {
        return file_elements(psiBuilder, n + 1);
    }
    
    static boolean wrap_block(final PsiBuilder psiBuilder, final int n) {
        if (!GeneratedParserUtilBase.recursion_guard_(psiBuilder, n, "wrap_block")) {
            return false;
        }
        final PsiBuilder.Marker enter_section_ = GeneratedParserUtilBase.enter_section_(psiBuilder);
        final boolean b = CMakeBodyBlock(psiBuilder, n + 1) && CMakeParserUtil.bindComments(psiBuilder, n + 1);
        GeneratedParserUtilBase.exit_section_(psiBuilder, enter_section_, null, b);
        return b;
    }
    
    static {
        EXTENDS_SETS_ = new TokenSet[] { GeneratedParserUtilBase.create_token_set_(CMakeElementTypes.C_MAKE_FUNCTION_COMMAND, CMakeElementTypes.C_MAKE_MACRO_COMMAND, CMakeElementTypes.C_MAKE_ROUTINE), GeneratedParserUtilBase.create_token_set_(CMakeElementTypes.C_MAKE_COMMAND, CMakeElementTypes.C_MAKE_ELSE_COMMAND, CMakeElementTypes.C_MAKE_ELSE_IF_COMMAND, CMakeElementTypes.C_MAKE_END_FOREACH_COMMAND, CMakeElementTypes.C_MAKE_END_FUNCTION_COMMAND, CMakeElementTypes.C_MAKE_END_IF_COMMAND, CMakeElementTypes.C_MAKE_END_MACRO_COMMAND, CMakeElementTypes.C_MAKE_END_WHILE_COMMAND, CMakeElementTypes.C_MAKE_FOREACH_COMMAND, CMakeElementTypes.C_MAKE_IF_COMMAND, CMakeElementTypes.C_MAKE_WHILE_COMMAND) };
    }
}
