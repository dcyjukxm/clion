// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.lang.asm.parser;

import com.jetbrains.cidr.lang.asm.psi.AsmTypes;
import com.intellij.psi.tree.TokenSet;
import com.intellij.lang.ASTNode;
import com.intellij.lang.PsiBuilder;
import com.intellij.psi.tree.IElementType;
import com.intellij.lang.parser.GeneratedParserUtilBase;
import com.intellij.lang.LightPsiParser;
import com.intellij.lang.PsiParser;

public class AsmParser implements PsiParser, LightPsiParser
{
    static final GeneratedParserUtilBase.Parser INTEGER_parser_;
    static final GeneratedParserUtilBase.Parser PREFIX_parser_;
    static final GeneratedParserUtilBase.Parser avx512_rounding_0_0_parser_;
    static final GeneratedParserUtilBase.Parser avx512_suffix_0_0_parser_;
    static final GeneratedParserUtilBase.Parser expression_parser_;
    static final GeneratedParserUtilBase.Parser instruction_0_1_parser_;
    static final GeneratedParserUtilBase.Parser memory_displacement_parser_;
    static final GeneratedParserUtilBase.Parser memory_indirect_0_0_1_parser_;
    static final GeneratedParserUtilBase.Parser memory_indirect_0_0_2_parser_;
    static final GeneratedParserUtilBase.Parser memory_indirect_0_0_parser_;
    static final GeneratedParserUtilBase.Parser memory_indirect_parser_;
    static final GeneratedParserUtilBase.Parser operand_parser_;
    static final GeneratedParserUtilBase.Parser recovery_parser_;
    static final GeneratedParserUtilBase.Parser register_parser_;
    
    public ASTNode parse(final IElementType elementType, final PsiBuilder psiBuilder) {
        this.parseLight(elementType, psiBuilder);
        return psiBuilder.getTreeBuilt();
    }
    
    public void parseLight(final IElementType elementType, PsiBuilder adapt_builder_) {
        adapt_builder_ = GeneratedParserUtilBase.adapt_builder_(elementType, adapt_builder_, (PsiParser)this, null);
        final PsiBuilder.Marker enter_section_ = GeneratedParserUtilBase.enter_section_(adapt_builder_, 0, 1, null);
        boolean b;
        if (elementType == AsmTypes.DIRECTIVE) {
            b = directive(adapt_builder_, 0);
        }
        else if (elementType == AsmTypes.EXPRESSION) {
            b = expression(adapt_builder_, 0);
        }
        else if (elementType == AsmTypes.IMMEDIATE) {
            b = immediate(adapt_builder_, 0);
        }
        else if (elementType == AsmTypes.INSTRUCTION) {
            b = instruction(adapt_builder_, 0);
        }
        else if (elementType == AsmTypes.JMP_ABSOLUTE) {
            b = jmp_absolute(adapt_builder_, 0);
        }
        else if (elementType == AsmTypes.MEMORY) {
            b = memory(adapt_builder_, 0);
        }
        else if (elementType == AsmTypes.NUMBER) {
            b = number(adapt_builder_, 0);
        }
        else if (elementType == AsmTypes.OPERAND) {
            b = operand(adapt_builder_, 0);
        }
        else if (elementType == AsmTypes.REGISTER) {
            b = register(adapt_builder_, 0);
        }
        else if (elementType == AsmTypes.SYMBOL) {
            b = symbol(adapt_builder_, 0);
        }
        else if (elementType == AsmTypes.SYMBOL_DYLD) {
            b = symbol_dyld(adapt_builder_, 0);
        }
        else {
            b = this.parse_root_(elementType, adapt_builder_, 0);
        }
        GeneratedParserUtilBase.exit_section_(adapt_builder_, 0, enter_section_, elementType, b, true, GeneratedParserUtilBase.TRUE_CONDITION);
    }
    
    protected boolean parse_root_(final IElementType elementType, final PsiBuilder psiBuilder, final int n) {
        return asmFile(psiBuilder, n + 1);
    }
    
    static boolean asmFile(final PsiBuilder psiBuilder, final int n) {
        if (!GeneratedParserUtilBase.recursion_guard_(psiBuilder, n, "asmFile")) {
            return false;
        }
        int n2 = GeneratedParserUtilBase.current_position_(psiBuilder);
        while (p(psiBuilder, n + 1)) {
            if (!GeneratedParserUtilBase.empty_element_parsed_guard_(psiBuilder, "asmFile", n2)) {
                return true;
            }
            n2 = GeneratedParserUtilBase.current_position_(psiBuilder);
        }
        return true;
    }
    
    private static boolean p(final PsiBuilder psiBuilder, final int n) {
        if (!GeneratedParserUtilBase.recursion_guard_(psiBuilder, n, "asmFile_0")) {
            return false;
        }
        final PsiBuilder.Marker enter_section_ = GeneratedParserUtilBase.enter_section_(psiBuilder);
        boolean b = GeneratedParserUtilBase.consumeToken(psiBuilder, AsmTypes.LABEL);
        if (!b) {
            b = directive(psiBuilder, n + 1);
        }
        if (!b) {
            b = instruction(psiBuilder, n + 1);
        }
        if (!b) {
            b = GeneratedParserUtilBase.consumeToken(psiBuilder, AsmTypes.LINE_COMMENT);
        }
        if (!b) {
            b = GeneratedParserUtilBase.consumeToken(psiBuilder, AsmTypes.BLOCK_COMMENT);
        }
        GeneratedParserUtilBase.exit_section_(psiBuilder, enter_section_, null, b);
        return b;
    }
    
    static boolean atLeastOneOf2(final PsiBuilder psiBuilder, final int n, final GeneratedParserUtilBase.Parser parser, final GeneratedParserUtilBase.Parser parser2) {
        if (!GeneratedParserUtilBase.recursion_guard_(psiBuilder, n, "atLeastOneOf2")) {
            return false;
        }
        final PsiBuilder.Marker enter_section_ = GeneratedParserUtilBase.enter_section_(psiBuilder);
        boolean b = a(psiBuilder, n + 1, parser, parser2);
        if (!b) {
            b = parser2.parse(psiBuilder, n);
        }
        GeneratedParserUtilBase.exit_section_(psiBuilder, enter_section_, null, b);
        return b;
    }
    
    private static boolean a(final PsiBuilder psiBuilder, final int n, final GeneratedParserUtilBase.Parser parser, final GeneratedParserUtilBase.Parser parser2) {
        if (!GeneratedParserUtilBase.recursion_guard_(psiBuilder, n, "atLeastOneOf2_0")) {
            return false;
        }
        final PsiBuilder.Marker enter_section_ = GeneratedParserUtilBase.enter_section_(psiBuilder);
        final boolean b = parser.parse(psiBuilder, n) && d(psiBuilder, n + 1, parser2);
        GeneratedParserUtilBase.exit_section_(psiBuilder, enter_section_, null, b);
        return b;
    }
    
    private static boolean d(final PsiBuilder psiBuilder, final int n, final GeneratedParserUtilBase.Parser parser) {
        if (!GeneratedParserUtilBase.recursion_guard_(psiBuilder, n, "atLeastOneOf2_0_1")) {
            return false;
        }
        parser.parse(psiBuilder, n);
        return true;
    }
    
    static boolean atLeastOneOf3(final PsiBuilder psiBuilder, final int n, final GeneratedParserUtilBase.Parser parser, final GeneratedParserUtilBase.Parser parser2, final GeneratedParserUtilBase.Parser parser3) {
        if (!GeneratedParserUtilBase.recursion_guard_(psiBuilder, n, "atLeastOneOf3")) {
            return false;
        }
        final PsiBuilder.Marker enter_section_ = GeneratedParserUtilBase.enter_section_(psiBuilder);
        boolean b = a(psiBuilder, n + 1, parser, parser2, parser3);
        if (!b) {
            b = parser3.parse(psiBuilder, n);
        }
        GeneratedParserUtilBase.exit_section_(psiBuilder, enter_section_, null, b);
        return b;
    }
    
    private static boolean a(final PsiBuilder psiBuilder, final int n, final GeneratedParserUtilBase.Parser parser, final GeneratedParserUtilBase.Parser parser2, final GeneratedParserUtilBase.Parser parser3) {
        if (!GeneratedParserUtilBase.recursion_guard_(psiBuilder, n, "atLeastOneOf3_0")) {
            return false;
        }
        final PsiBuilder.Marker enter_section_ = GeneratedParserUtilBase.enter_section_(psiBuilder);
        final boolean b = atLeastOneOf2(psiBuilder, n + 1, parser, parser2) && c(psiBuilder, n + 1, parser3);
        GeneratedParserUtilBase.exit_section_(psiBuilder, enter_section_, null, b);
        return b;
    }
    
    private static boolean c(final PsiBuilder psiBuilder, final int n, final GeneratedParserUtilBase.Parser parser) {
        if (!GeneratedParserUtilBase.recursion_guard_(psiBuilder, n, "atLeastOneOf3_0_1")) {
            return false;
        }
        parser.parse(psiBuilder, n);
        return true;
    }
    
    static boolean avx512_rounding(final PsiBuilder psiBuilder, final int n) {
        return braced(psiBuilder, n + 1, AsmParser.avx512_rounding_0_0_parser_);
    }
    
    private static boolean m(final PsiBuilder psiBuilder, final int n) {
        if (!GeneratedParserUtilBase.recursion_guard_(psiBuilder, n, "avx512_rounding_0_0")) {
            return false;
        }
        final PsiBuilder.Marker enter_section_ = GeneratedParserUtilBase.enter_section_(psiBuilder);
        final boolean b = GeneratedParserUtilBase.consumeToken(psiBuilder, AsmTypes.IDENTIFIER) && i(psiBuilder, n + 1);
        GeneratedParserUtilBase.exit_section_(psiBuilder, enter_section_, null, b);
        return b;
    }
    
    private static boolean i(final PsiBuilder psiBuilder, final int n) {
        if (!GeneratedParserUtilBase.recursion_guard_(psiBuilder, n, "avx512_rounding_0_0_1")) {
            return false;
        }
        GeneratedParserUtilBase.parseTokens(psiBuilder, 0, AsmTypes.MINUS, AsmTypes.IDENTIFIER);
        return true;
    }
    
    static boolean avx512_suffix(final PsiBuilder psiBuilder, final int n) {
        return braced(psiBuilder, n + 1, AsmParser.avx512_suffix_0_0_parser_);
    }
    
    private static boolean e(final PsiBuilder psiBuilder, final int n) {
        if (!GeneratedParserUtilBase.recursion_guard_(psiBuilder, n, "avx512_suffix_0_0")) {
            return false;
        }
        final PsiBuilder.Marker enter_section_ = GeneratedParserUtilBase.enter_section_(psiBuilder);
        boolean b = GeneratedParserUtilBase.parseTokens(psiBuilder, 0, AsmTypes.INTEGER, AsmTypes.IDENTIFIER);
        if (!b) {
            b = register(psiBuilder, n + 1);
        }
        if (!b) {
            b = GeneratedParserUtilBase.consumeToken(psiBuilder, AsmTypes.IDENTIFIER);
        }
        GeneratedParserUtilBase.exit_section_(psiBuilder, enter_section_, null, b);
        return b;
    }
    
    static boolean braced(final PsiBuilder psiBuilder, final int n, final GeneratedParserUtilBase.Parser parser) {
        if (!GeneratedParserUtilBase.recursion_guard_(psiBuilder, n, "braced")) {
            return false;
        }
        if (!GeneratedParserUtilBase.nextTokenIs(psiBuilder, AsmTypes.L_BRACE)) {
            return false;
        }
        final PsiBuilder.Marker enter_section_ = GeneratedParserUtilBase.enter_section_(psiBuilder);
        final boolean b = GeneratedParserUtilBase.consumeToken(psiBuilder, AsmTypes.L_BRACE) && parser.parse(psiBuilder, n) && GeneratedParserUtilBase.consumeToken(psiBuilder, AsmTypes.R_BRACE);
        GeneratedParserUtilBase.exit_section_(psiBuilder, enter_section_, null, b);
        return b;
    }
    
    public static boolean directive(final PsiBuilder psiBuilder, final int n) {
        if (!GeneratedParserUtilBase.recursion_guard_(psiBuilder, n, "directive")) {
            return false;
        }
        if (!GeneratedParserUtilBase.nextTokenIs(psiBuilder, AsmTypes.DIRECTIVE_NAME)) {
            return false;
        }
        final PsiBuilder.Marker enter_section_ = GeneratedParserUtilBase.enter_section_(psiBuilder);
        final boolean b = GeneratedParserUtilBase.consumeToken(psiBuilder, AsmTypes.DIRECTIVE_NAME) && t(psiBuilder, n + 1);
        GeneratedParserUtilBase.exit_section_(psiBuilder, enter_section_, AsmTypes.DIRECTIVE, b);
        return b;
    }
    
    private static boolean t(final PsiBuilder psiBuilder, final int n) {
        if (!GeneratedParserUtilBase.recursion_guard_(psiBuilder, n, "directive_1")) {
            return false;
        }
        int n2 = GeneratedParserUtilBase.current_position_(psiBuilder);
        while (GeneratedParserUtilBase.consumeToken(psiBuilder, AsmTypes.DIRECTIVE_CHARACTER)) {
            if (!GeneratedParserUtilBase.empty_element_parsed_guard_(psiBuilder, "directive_1", n2)) {
                return true;
            }
            n2 = GeneratedParserUtilBase.current_position_(psiBuilder);
        }
        return true;
    }
    
    public static boolean expression(final PsiBuilder psiBuilder, final int n) {
        if (!GeneratedParserUtilBase.recursion_guard_(psiBuilder, n, "expression")) {
            return false;
        }
        final PsiBuilder.Marker enter_section_ = GeneratedParserUtilBase.enter_section_(psiBuilder, n, 0, AsmTypes.EXPRESSION, "<expression>");
        boolean b = parenthesized(psiBuilder, n + 1, AsmParser.expression_parser_);
        if (!b) {
            b = symbol(psiBuilder, n + 1);
        }
        if (!b) {
            b = number(psiBuilder, n + 1);
        }
        GeneratedParserUtilBase.exit_section_(psiBuilder, n, enter_section_, b, false, null);
        return b;
    }
    
    public static boolean immediate(final PsiBuilder psiBuilder, final int n) {
        if (!GeneratedParserUtilBase.recursion_guard_(psiBuilder, n, "immediate")) {
            return false;
        }
        if (!GeneratedParserUtilBase.nextTokenIs(psiBuilder, AsmTypes.DOLLAR)) {
            return false;
        }
        final PsiBuilder.Marker enter_section_ = GeneratedParserUtilBase.enter_section_(psiBuilder, n, 0, AsmTypes.IMMEDIATE, null);
        final boolean consumeToken;
        final boolean b = (consumeToken = GeneratedParserUtilBase.consumeToken(psiBuilder, AsmTypes.DOLLAR)) && expression(psiBuilder, n + 1);
        GeneratedParserUtilBase.exit_section_(psiBuilder, n, enter_section_, b, consumeToken, null);
        return b || consumeToken;
    }
    
    public static boolean instruction(final PsiBuilder psiBuilder, final int n) {
        if (!GeneratedParserUtilBase.recursion_guard_(psiBuilder, n, "instruction")) {
            return false;
        }
        final PsiBuilder.Marker enter_section_ = GeneratedParserUtilBase.enter_section_(psiBuilder, n, 0, AsmTypes.INSTRUCTION, "<instruction>");
        final boolean atLeastOneOf2 = atLeastOneOf2(psiBuilder, n + 1, AsmParser.PREFIX_parser_, AsmParser.instruction_0_1_parser_);
        GeneratedParserUtilBase.exit_section_(psiBuilder, n, enter_section_, atLeastOneOf2, false, AsmParser.recovery_parser_);
        return atLeastOneOf2;
    }
    
    private static boolean o(final PsiBuilder psiBuilder, final int n) {
        if (!GeneratedParserUtilBase.recursion_guard_(psiBuilder, n, "instruction_0_1")) {
            return false;
        }
        final PsiBuilder.Marker enter_section_ = GeneratedParserUtilBase.enter_section_(psiBuilder);
        final boolean b = GeneratedParserUtilBase.consumeToken(psiBuilder, AsmTypes.MNEMONIC) && r(psiBuilder, n + 1) && a(psiBuilder, n + 1);
        GeneratedParserUtilBase.exit_section_(psiBuilder, enter_section_, null, b);
        return b;
    }
    
    private static boolean r(final PsiBuilder psiBuilder, final int n) {
        if (!GeneratedParserUtilBase.recursion_guard_(psiBuilder, n, "instruction_0_1_1")) {
            return false;
        }
        jmp_branch_hint(psiBuilder, n + 1);
        return true;
    }
    
    private static boolean a(final PsiBuilder psiBuilder, final int n) {
        if (!GeneratedParserUtilBase.recursion_guard_(psiBuilder, n, "instruction_0_1_2")) {
            return false;
        }
        list_of(psiBuilder, n + 1, AsmParser.operand_parser_);
        return true;
    }
    
    public static boolean jmp_absolute(final PsiBuilder psiBuilder, final int n) {
        if (!GeneratedParserUtilBase.recursion_guard_(psiBuilder, n, "jmp_absolute")) {
            return false;
        }
        if (!GeneratedParserUtilBase.nextTokenIs(psiBuilder, AsmTypes.STAR)) {
            return false;
        }
        final PsiBuilder.Marker enter_section_ = GeneratedParserUtilBase.enter_section_(psiBuilder);
        final boolean consumeToken = GeneratedParserUtilBase.consumeToken(psiBuilder, AsmTypes.STAR);
        GeneratedParserUtilBase.exit_section_(psiBuilder, enter_section_, AsmTypes.JMP_ABSOLUTE, consumeToken);
        return consumeToken;
    }
    
    static boolean jmp_branch_hint(final PsiBuilder psiBuilder, final int n) {
        if (!GeneratedParserUtilBase.recursion_guard_(psiBuilder, n, "jmp_branch_hint")) {
            return false;
        }
        if (!GeneratedParserUtilBase.nextTokenIs(psiBuilder, AsmTypes.COMMA)) {
            return false;
        }
        final PsiBuilder.Marker enter_section_ = GeneratedParserUtilBase.enter_section_(psiBuilder);
        final boolean consumeTokens = GeneratedParserUtilBase.consumeTokens(psiBuilder, 0, AsmTypes.COMMA, AsmTypes.IDENTIFIER);
        GeneratedParserUtilBase.exit_section_(psiBuilder, enter_section_, null, consumeTokens);
        return consumeTokens;
    }
    
    static boolean list_of(final PsiBuilder psiBuilder, final int n, final GeneratedParserUtilBase.Parser parser) {
        if (!GeneratedParserUtilBase.recursion_guard_(psiBuilder, n, "list_of")) {
            return false;
        }
        final PsiBuilder.Marker enter_section_ = GeneratedParserUtilBase.enter_section_(psiBuilder);
        final boolean b = parser.parse(psiBuilder, n) && b(psiBuilder, n + 1, parser);
        GeneratedParserUtilBase.exit_section_(psiBuilder, enter_section_, null, b);
        return b;
    }
    
    private static boolean b(final PsiBuilder psiBuilder, final int n, final GeneratedParserUtilBase.Parser parser) {
        if (!GeneratedParserUtilBase.recursion_guard_(psiBuilder, n, "list_of_1")) {
            return false;
        }
        int n2 = GeneratedParserUtilBase.current_position_(psiBuilder);
        while (a(psiBuilder, n + 1, parser)) {
            if (!GeneratedParserUtilBase.empty_element_parsed_guard_(psiBuilder, "list_of_1", n2)) {
                return true;
            }
            n2 = GeneratedParserUtilBase.current_position_(psiBuilder);
        }
        return true;
    }
    
    private static boolean a(final PsiBuilder psiBuilder, final int n, final GeneratedParserUtilBase.Parser parser) {
        if (!GeneratedParserUtilBase.recursion_guard_(psiBuilder, n, "list_of_1_0")) {
            return false;
        }
        final PsiBuilder.Marker enter_section_ = GeneratedParserUtilBase.enter_section_(psiBuilder);
        final boolean b = GeneratedParserUtilBase.consumeToken(psiBuilder, AsmTypes.COMMA) && parser.parse(psiBuilder, n);
        GeneratedParserUtilBase.exit_section_(psiBuilder, enter_section_, null, b);
        return b;
    }
    
    public static boolean memory(final PsiBuilder psiBuilder, final int n) {
        if (!GeneratedParserUtilBase.recursion_guard_(psiBuilder, n, "memory")) {
            return false;
        }
        final PsiBuilder.Marker enter_section_ = GeneratedParserUtilBase.enter_section_(psiBuilder, n, 0, AsmTypes.MEMORY, "<memory>");
        final boolean b = g(psiBuilder, n + 1) && atLeastOneOf2(psiBuilder, n + 1, AsmParser.memory_displacement_parser_, AsmParser.memory_indirect_parser_);
        GeneratedParserUtilBase.exit_section_(psiBuilder, n, enter_section_, b, false, null);
        return b;
    }
    
    private static boolean g(final PsiBuilder psiBuilder, final int n) {
        if (!GeneratedParserUtilBase.recursion_guard_(psiBuilder, n, "memory_0")) {
            return false;
        }
        memory_segment(psiBuilder, n + 1);
        return true;
    }
    
    static boolean memory_displacement(final PsiBuilder psiBuilder, final int n) {
        return expression(psiBuilder, n + 1);
    }
    
    static boolean memory_indirect(final PsiBuilder psiBuilder, final int n) {
        return parenthesized(psiBuilder, n + 1, AsmParser.memory_indirect_0_0_parser_);
    }
    
    private static boolean n(final PsiBuilder psiBuilder, final int n) {
        if (!GeneratedParserUtilBase.recursion_guard_(psiBuilder, n, "memory_indirect_0_0_1")) {
            return false;
        }
        final PsiBuilder.Marker enter_section_ = GeneratedParserUtilBase.enter_section_(psiBuilder);
        final boolean b = GeneratedParserUtilBase.consumeToken(psiBuilder, AsmTypes.COMMA) && register(psiBuilder, n + 1);
        GeneratedParserUtilBase.exit_section_(psiBuilder, enter_section_, null, b);
        return b;
    }
    
    private static boolean f(final PsiBuilder psiBuilder, final int n) {
        if (!GeneratedParserUtilBase.recursion_guard_(psiBuilder, n, "memory_indirect_0_0_2")) {
            return false;
        }
        final PsiBuilder.Marker enter_section_ = GeneratedParserUtilBase.enter_section_(psiBuilder);
        final boolean consumeTokens = GeneratedParserUtilBase.consumeTokens(psiBuilder, 0, AsmTypes.COMMA, AsmTypes.INTEGER);
        GeneratedParserUtilBase.exit_section_(psiBuilder, enter_section_, null, consumeTokens);
        return consumeTokens;
    }
    
    static boolean memory_segment(final PsiBuilder psiBuilder, final int n) {
        if (!GeneratedParserUtilBase.recursion_guard_(psiBuilder, n, "memory_segment")) {
            return false;
        }
        if (!GeneratedParserUtilBase.nextTokenIs(psiBuilder, AsmTypes.PERCENT)) {
            return false;
        }
        final PsiBuilder.Marker enter_section_ = GeneratedParserUtilBase.enter_section_(psiBuilder);
        final boolean b = register(psiBuilder, n + 1) && GeneratedParserUtilBase.consumeToken(psiBuilder, AsmTypes.COLON);
        GeneratedParserUtilBase.exit_section_(psiBuilder, enter_section_, null, b);
        return b;
    }
    
    public static boolean number(final PsiBuilder psiBuilder, final int n) {
        if (!GeneratedParserUtilBase.recursion_guard_(psiBuilder, n, "number")) {
            return false;
        }
        if (!GeneratedParserUtilBase.nextTokenIs(psiBuilder, "<number>", AsmTypes.INTEGER, AsmTypes.MINUS)) {
            return false;
        }
        final PsiBuilder.Marker enter_section_ = GeneratedParserUtilBase.enter_section_(psiBuilder, n, 0, AsmTypes.NUMBER, "<number>");
        final boolean b = j(psiBuilder, n + 1) && GeneratedParserUtilBase.consumeToken(psiBuilder, AsmTypes.INTEGER);
        GeneratedParserUtilBase.exit_section_(psiBuilder, n, enter_section_, b, false, null);
        return b;
    }
    
    private static boolean j(final PsiBuilder psiBuilder, final int n) {
        if (!GeneratedParserUtilBase.recursion_guard_(psiBuilder, n, "number_0")) {
            return false;
        }
        GeneratedParserUtilBase.consumeToken(psiBuilder, AsmTypes.MINUS);
        return true;
    }
    
    public static boolean operand(final PsiBuilder psiBuilder, final int n) {
        if (!GeneratedParserUtilBase.recursion_guard_(psiBuilder, n, "operand")) {
            return false;
        }
        final PsiBuilder.Marker enter_section_ = GeneratedParserUtilBase.enter_section_(psiBuilder, n, 0, AsmTypes.OPERAND, "<operand>");
        boolean b = q(psiBuilder, n + 1);
        if (!b) {
            b = avx512_rounding(psiBuilder, n + 1);
        }
        GeneratedParserUtilBase.exit_section_(psiBuilder, n, enter_section_, b, false, null);
        return b;
    }
    
    private static boolean q(final PsiBuilder psiBuilder, final int n) {
        if (!GeneratedParserUtilBase.recursion_guard_(psiBuilder, n, "operand_0")) {
            return false;
        }
        final PsiBuilder.Marker enter_section_ = GeneratedParserUtilBase.enter_section_(psiBuilder);
        final boolean b = d(psiBuilder, n + 1) && l(psiBuilder, n + 1) && k(psiBuilder, n + 1);
        GeneratedParserUtilBase.exit_section_(psiBuilder, enter_section_, null, b);
        return b;
    }
    
    private static boolean d(final PsiBuilder psiBuilder, final int n) {
        if (!GeneratedParserUtilBase.recursion_guard_(psiBuilder, n, "operand_0_0")) {
            return false;
        }
        jmp_absolute(psiBuilder, n + 1);
        return true;
    }
    
    private static boolean l(final PsiBuilder psiBuilder, final int n) {
        if (!GeneratedParserUtilBase.recursion_guard_(psiBuilder, n, "operand_0_1")) {
            return false;
        }
        final PsiBuilder.Marker enter_section_ = GeneratedParserUtilBase.enter_section_(psiBuilder);
        boolean b = memory(psiBuilder, n + 1);
        if (!b) {
            b = immediate(psiBuilder, n + 1);
        }
        if (!b) {
            b = register(psiBuilder, n + 1);
        }
        GeneratedParserUtilBase.exit_section_(psiBuilder, enter_section_, null, b);
        return b;
    }
    
    private static boolean k(final PsiBuilder psiBuilder, final int n) {
        if (!GeneratedParserUtilBase.recursion_guard_(psiBuilder, n, "operand_0_2")) {
            return false;
        }
        int n2 = GeneratedParserUtilBase.current_position_(psiBuilder);
        while (avx512_suffix(psiBuilder, n + 1)) {
            if (!GeneratedParserUtilBase.empty_element_parsed_guard_(psiBuilder, "operand_0_2", n2)) {
                return true;
            }
            n2 = GeneratedParserUtilBase.current_position_(psiBuilder);
        }
        return true;
    }
    
    static boolean parenthesized(final PsiBuilder psiBuilder, final int n, final GeneratedParserUtilBase.Parser parser) {
        if (!GeneratedParserUtilBase.recursion_guard_(psiBuilder, n, "parenthesized")) {
            return false;
        }
        if (!GeneratedParserUtilBase.nextTokenIs(psiBuilder, AsmTypes.L_PAREN)) {
            return false;
        }
        final PsiBuilder.Marker enter_section_ = GeneratedParserUtilBase.enter_section_(psiBuilder);
        final boolean b = GeneratedParserUtilBase.consumeToken(psiBuilder, AsmTypes.L_PAREN) && parser.parse(psiBuilder, n) && GeneratedParserUtilBase.consumeToken(psiBuilder, AsmTypes.R_PAREN);
        GeneratedParserUtilBase.exit_section_(psiBuilder, enter_section_, null, b);
        return b;
    }
    
    static boolean recovery(final PsiBuilder psiBuilder, final int n) {
        if (!GeneratedParserUtilBase.recursion_guard_(psiBuilder, n, "recovery")) {
            return false;
        }
        final PsiBuilder.Marker enter_section_ = GeneratedParserUtilBase.enter_section_(psiBuilder, n, 16);
        final boolean b = !b(psiBuilder, n + 1);
        GeneratedParserUtilBase.exit_section_(psiBuilder, n, enter_section_, b, false, null);
        return b;
    }
    
    private static boolean b(final PsiBuilder psiBuilder, final int n) {
        if (!GeneratedParserUtilBase.recursion_guard_(psiBuilder, n, "recovery_0")) {
            return false;
        }
        final PsiBuilder.Marker enter_section_ = GeneratedParserUtilBase.enter_section_(psiBuilder);
        boolean b = GeneratedParserUtilBase.consumeToken(psiBuilder, AsmTypes.LABEL);
        if (!b) {
            b = GeneratedParserUtilBase.consumeToken(psiBuilder, AsmTypes.DIRECTIVE_NAME);
        }
        if (!b) {
            b = GeneratedParserUtilBase.consumeToken(psiBuilder, AsmTypes.PREFIX);
        }
        if (!b) {
            b = GeneratedParserUtilBase.consumeToken(psiBuilder, AsmTypes.MNEMONIC);
        }
        if (!b) {
            b = GeneratedParserUtilBase.consumeToken(psiBuilder, AsmTypes.LINE_COMMENT);
        }
        GeneratedParserUtilBase.exit_section_(psiBuilder, enter_section_, null, b);
        return b;
    }
    
    public static boolean register(final PsiBuilder psiBuilder, final int n) {
        if (!GeneratedParserUtilBase.recursion_guard_(psiBuilder, n, "register")) {
            return false;
        }
        if (!GeneratedParserUtilBase.nextTokenIs(psiBuilder, AsmTypes.PERCENT)) {
            return false;
        }
        final PsiBuilder.Marker enter_section_ = GeneratedParserUtilBase.enter_section_(psiBuilder, n, 0, AsmTypes.REGISTER, null);
        final boolean consumeToken;
        final boolean b = (consumeToken = GeneratedParserUtilBase.consumeToken(psiBuilder, AsmTypes.PERCENT)) && h(psiBuilder, n + 1);
        GeneratedParserUtilBase.exit_section_(psiBuilder, n, enter_section_, b, consumeToken, null);
        return b || consumeToken;
    }
    
    private static boolean h(final PsiBuilder psiBuilder, final int n) {
        if (!GeneratedParserUtilBase.recursion_guard_(psiBuilder, n, "register_1")) {
            return false;
        }
        final PsiBuilder.Marker enter_section_ = GeneratedParserUtilBase.enter_section_(psiBuilder);
        boolean b = u(psiBuilder, n + 1);
        if (!b) {
            b = GeneratedParserUtilBase.consumeToken(psiBuilder, AsmTypes.IDENTIFIER);
        }
        GeneratedParserUtilBase.exit_section_(psiBuilder, enter_section_, null, b);
        return b;
    }
    
    private static boolean u(final PsiBuilder psiBuilder, final int n) {
        if (!GeneratedParserUtilBase.recursion_guard_(psiBuilder, n, "register_1_0")) {
            return false;
        }
        final PsiBuilder.Marker enter_section_ = GeneratedParserUtilBase.enter_section_(psiBuilder);
        final boolean b = GeneratedParserUtilBase.consumeToken(psiBuilder, "st") && parenthesized(psiBuilder, n + 1, AsmParser.INTEGER_parser_);
        GeneratedParserUtilBase.exit_section_(psiBuilder, enter_section_, null, b);
        return b;
    }
    
    public static boolean symbol(final PsiBuilder psiBuilder, final int n) {
        if (!GeneratedParserUtilBase.recursion_guard_(psiBuilder, n, "symbol")) {
            return false;
        }
        if (!GeneratedParserUtilBase.nextTokenIs(psiBuilder, "<symbol>", AsmTypes.IDENTIFIER, AsmTypes.SYMBOL_IDENTIFIER)) {
            return false;
        }
        final PsiBuilder.Marker enter_section_ = GeneratedParserUtilBase.enter_section_(psiBuilder, n, 0, AsmTypes.SYMBOL, "<symbol>");
        final boolean b = s(psiBuilder, n + 1) && c(psiBuilder, n + 1);
        GeneratedParserUtilBase.exit_section_(psiBuilder, n, enter_section_, b, false, null);
        return b;
    }
    
    private static boolean s(final PsiBuilder psiBuilder, final int n) {
        if (!GeneratedParserUtilBase.recursion_guard_(psiBuilder, n, "symbol_0")) {
            return false;
        }
        final PsiBuilder.Marker enter_section_ = GeneratedParserUtilBase.enter_section_(psiBuilder);
        boolean b = GeneratedParserUtilBase.consumeToken(psiBuilder, AsmTypes.IDENTIFIER);
        if (!b) {
            b = GeneratedParserUtilBase.consumeToken(psiBuilder, AsmTypes.SYMBOL_IDENTIFIER);
        }
        GeneratedParserUtilBase.exit_section_(psiBuilder, enter_section_, null, b);
        return b;
    }
    
    private static boolean c(final PsiBuilder psiBuilder, final int n) {
        if (!GeneratedParserUtilBase.recursion_guard_(psiBuilder, n, "symbol_1")) {
            return false;
        }
        symbol_dyld(psiBuilder, n + 1);
        return true;
    }
    
    public static boolean symbol_dyld(final PsiBuilder psiBuilder, final int n) {
        if (!GeneratedParserUtilBase.recursion_guard_(psiBuilder, n, "symbol_dyld")) {
            return false;
        }
        if (!GeneratedParserUtilBase.nextTokenIs(psiBuilder, AsmTypes.AT)) {
            return false;
        }
        final PsiBuilder.Marker enter_section_ = GeneratedParserUtilBase.enter_section_(psiBuilder);
        final boolean consumeTokens = GeneratedParserUtilBase.consumeTokens(psiBuilder, 0, AsmTypes.AT, AsmTypes.IDENTIFIER);
        GeneratedParserUtilBase.exit_section_(psiBuilder, enter_section_, AsmTypes.SYMBOL_DYLD, consumeTokens);
        return consumeTokens;
    }
    
    static {
        INTEGER_parser_ = new GeneratedParserUtilBase.Parser() {
            @Override
            public boolean parse(final PsiBuilder psiBuilder, final int n) {
                return GeneratedParserUtilBase.consumeToken(psiBuilder, AsmTypes.INTEGER);
            }
        };
        PREFIX_parser_ = new GeneratedParserUtilBase.Parser() {
            @Override
            public boolean parse(final PsiBuilder psiBuilder, final int n) {
                return GeneratedParserUtilBase.consumeToken(psiBuilder, AsmTypes.PREFIX);
            }
        };
        avx512_rounding_0_0_parser_ = new GeneratedParserUtilBase.Parser() {
            @Override
            public boolean parse(final PsiBuilder psiBuilder, final int n) {
                return m(psiBuilder, n + 1);
            }
        };
        avx512_suffix_0_0_parser_ = new GeneratedParserUtilBase.Parser() {
            @Override
            public boolean parse(final PsiBuilder psiBuilder, final int n) {
                return e(psiBuilder, n + 1);
            }
        };
        expression_parser_ = new GeneratedParserUtilBase.Parser() {
            @Override
            public boolean parse(final PsiBuilder psiBuilder, final int n) {
                return AsmParser.expression(psiBuilder, n + 1);
            }
        };
        instruction_0_1_parser_ = new GeneratedParserUtilBase.Parser() {
            @Override
            public boolean parse(final PsiBuilder psiBuilder, final int n) {
                return o(psiBuilder, n + 1);
            }
        };
        memory_displacement_parser_ = new GeneratedParserUtilBase.Parser() {
            @Override
            public boolean parse(final PsiBuilder psiBuilder, final int n) {
                return AsmParser.memory_displacement(psiBuilder, n + 1);
            }
        };
        memory_indirect_0_0_1_parser_ = new GeneratedParserUtilBase.Parser() {
            @Override
            public boolean parse(final PsiBuilder psiBuilder, final int n) {
                return n(psiBuilder, n + 1);
            }
        };
        memory_indirect_0_0_2_parser_ = new GeneratedParserUtilBase.Parser() {
            @Override
            public boolean parse(final PsiBuilder psiBuilder, final int n) {
                return f(psiBuilder, n + 1);
            }
        };
        memory_indirect_0_0_parser_ = new GeneratedParserUtilBase.Parser() {
            @Override
            public boolean parse(final PsiBuilder psiBuilder, final int n) {
                return AsmParser.atLeastOneOf3(psiBuilder, n + 1, AsmParser.register_parser_, AsmParser.memory_indirect_0_0_1_parser_, AsmParser.memory_indirect_0_0_2_parser_);
            }
        };
        memory_indirect_parser_ = new GeneratedParserUtilBase.Parser() {
            @Override
            public boolean parse(final PsiBuilder psiBuilder, final int n) {
                return AsmParser.memory_indirect(psiBuilder, n + 1);
            }
        };
        operand_parser_ = new GeneratedParserUtilBase.Parser() {
            @Override
            public boolean parse(final PsiBuilder psiBuilder, final int n) {
                return AsmParser.operand(psiBuilder, n + 1);
            }
        };
        recovery_parser_ = new GeneratedParserUtilBase.Parser() {
            @Override
            public boolean parse(final PsiBuilder psiBuilder, final int n) {
                return AsmParser.recovery(psiBuilder, n + 1);
            }
        };
        register_parser_ = new GeneratedParserUtilBase.Parser() {
            @Override
            public boolean parse(final PsiBuilder psiBuilder, final int n) {
                return AsmParser.register(psiBuilder, n + 1);
            }
        };
    }
}
