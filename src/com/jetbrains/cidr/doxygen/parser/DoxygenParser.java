// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.doxygen.parser;

import com.jetbrains.cidr.doxygen.psi.DxTypes;
import com.intellij.psi.tree.TokenSet;
import com.intellij.lang.parser.GeneratedParserUtilBase;
import com.intellij.lang.ASTNode;
import com.intellij.lang.PsiBuilder;
import com.intellij.psi.tree.IElementType;
import com.intellij.lang.LightPsiParser;
import com.intellij.lang.PsiParser;

public class DoxygenParser implements PsiParser, LightPsiParser
{
    public ASTNode parse(final IElementType elementType, final PsiBuilder psiBuilder) {
        this.parseLight(elementType, psiBuilder);
        return psiBuilder.getTreeBuilt();
    }
    
    public void parseLight(final IElementType elementType, PsiBuilder adapt_builder_) {
        adapt_builder_ = GeneratedParserUtilBase.adapt_builder_(elementType, adapt_builder_, (PsiParser)this, null);
        final PsiBuilder.Marker enter_section_ = GeneratedParserUtilBase.enter_section_(adapt_builder_, 0, 1, null);
        boolean b;
        if (elementType == DxTypes.DOC_COMMENT) {
            b = docComment(adapt_builder_, 0);
        }
        else if (elementType == DxTypes.DOC_TAG) {
            b = docTag(adapt_builder_, 0);
        }
        else if (elementType == DxTypes.PARAM) {
            b = param(adapt_builder_, 0);
        }
        else if (elementType == DxTypes.PARAM_ID) {
            b = paramId(adapt_builder_, 0);
        }
        else {
            b = this.parse_root_(elementType, adapt_builder_, 0);
        }
        GeneratedParserUtilBase.exit_section_(adapt_builder_, 0, enter_section_, elementType, b, true, GeneratedParserUtilBase.TRUE_CONDITION);
    }
    
    protected boolean parse_root_(final IElementType elementType, final PsiBuilder psiBuilder, final int n) {
        return doxygenComment(psiBuilder, n + 1);
    }
    
    static boolean block_comment(final PsiBuilder psiBuilder, final int n) {
        if (!GeneratedParserUtilBase.recursion_guard_(psiBuilder, n, "block_comment")) {
            return false;
        }
        if (!GeneratedParserUtilBase.nextTokenIs(psiBuilder, DxTypes.DOC_COMMENT_START)) {
            return false;
        }
        final PsiBuilder.Marker enter_section_ = GeneratedParserUtilBase.enter_section_(psiBuilder);
        final boolean b = GeneratedParserUtilBase.consumeToken(psiBuilder, DxTypes.DOC_COMMENT_START) && f(psiBuilder, n + 1) && GeneratedParserUtilBase.consumeToken(psiBuilder, DxTypes.DOC_COMMENT_END);
        GeneratedParserUtilBase.exit_section_(psiBuilder, enter_section_, null, b);
        return b;
    }
    
    private static boolean f(final PsiBuilder psiBuilder, final int n) {
        if (!GeneratedParserUtilBase.recursion_guard_(psiBuilder, n, "block_comment_1")) {
            return false;
        }
        int n2 = GeneratedParserUtilBase.current_position_(psiBuilder);
        while (i(psiBuilder, n + 1)) {
            if (!GeneratedParserUtilBase.empty_element_parsed_guard_(psiBuilder, "block_comment_1", n2)) {
                return true;
            }
            n2 = GeneratedParserUtilBase.current_position_(psiBuilder);
        }
        return true;
    }
    
    private static boolean i(final PsiBuilder psiBuilder, final int n) {
        if (!GeneratedParserUtilBase.recursion_guard_(psiBuilder, n, "block_comment_1_0")) {
            return false;
        }
        final PsiBuilder.Marker enter_section_ = GeneratedParserUtilBase.enter_section_(psiBuilder);
        boolean b = GeneratedParserUtilBase.consumeToken(psiBuilder, DxTypes.TAG_OPTION);
        if (!b) {
            b = docData(psiBuilder, n + 1);
        }
        if (!b) {
            b = docTag(psiBuilder, n + 1);
        }
        GeneratedParserUtilBase.exit_section_(psiBuilder, enter_section_, null, b);
        return b;
    }
    
    public static boolean docComment(final PsiBuilder psiBuilder, final int n) {
        if (!GeneratedParserUtilBase.recursion_guard_(psiBuilder, n, "docComment")) {
            return false;
        }
        if (!GeneratedParserUtilBase.nextTokenIs(psiBuilder, "<doc comment>", DxTypes.DOC_COMMENT_START, DxTypes.EOF_DOC_COMMENT_START)) {
            return false;
        }
        final PsiBuilder.Marker enter_section_ = GeneratedParserUtilBase.enter_section_(psiBuilder, n, 0, DxTypes.DOC_COMMENT, "<doc comment>");
        boolean b = block_comment(psiBuilder, n + 1);
        if (!b) {
            b = eol_comment(psiBuilder, n + 1);
        }
        GeneratedParserUtilBase.exit_section_(psiBuilder, n, enter_section_, b, false, null);
        return b;
    }
    
    static boolean docData(final PsiBuilder psiBuilder, final int n) {
        return GeneratedParserUtilBase.consumeToken(psiBuilder, DxTypes.DOC_COMMENT_DATA);
    }
    
    public static boolean docTag(final PsiBuilder psiBuilder, final int n) {
        if (!GeneratedParserUtilBase.recursion_guard_(psiBuilder, n, "docTag")) {
            return false;
        }
        if (!GeneratedParserUtilBase.nextTokenIs(psiBuilder, DxTypes.TAG_NAME)) {
            return false;
        }
        final PsiBuilder.Marker enter_section_ = GeneratedParserUtilBase.enter_section_(psiBuilder);
        final boolean b = GeneratedParserUtilBase.consumeToken(psiBuilder, DxTypes.TAG_NAME) && j(psiBuilder, n + 1) && tagParams(psiBuilder, n + 1);
        GeneratedParserUtilBase.exit_section_(psiBuilder, enter_section_, DxTypes.DOC_TAG, b);
        return b;
    }
    
    private static boolean j(final PsiBuilder psiBuilder, final int n) {
        if (!GeneratedParserUtilBase.recursion_guard_(psiBuilder, n, "docTag_1")) {
            return false;
        }
        GeneratedParserUtilBase.consumeToken(psiBuilder, DxTypes.TAG_OPTION);
        return true;
    }
    
    static boolean doxygenComment(final PsiBuilder psiBuilder, final int n) {
        return docComment(psiBuilder, n + 1);
    }
    
    static boolean eol_comment(final PsiBuilder psiBuilder, final int n) {
        if (!GeneratedParserUtilBase.recursion_guard_(psiBuilder, n, "eol_comment")) {
            return false;
        }
        if (!GeneratedParserUtilBase.nextTokenIs(psiBuilder, DxTypes.EOF_DOC_COMMENT_START)) {
            return false;
        }
        final PsiBuilder.Marker enter_section_ = GeneratedParserUtilBase.enter_section_(psiBuilder);
        final boolean b = GeneratedParserUtilBase.consumeToken(psiBuilder, DxTypes.EOF_DOC_COMMENT_START) && e(psiBuilder, n + 1);
        GeneratedParserUtilBase.exit_section_(psiBuilder, enter_section_, null, b);
        return b;
    }
    
    private static boolean e(final PsiBuilder psiBuilder, final int n) {
        if (!GeneratedParserUtilBase.recursion_guard_(psiBuilder, n, "eol_comment_1")) {
            return false;
        }
        int n2 = GeneratedParserUtilBase.current_position_(psiBuilder);
        while (b(psiBuilder, n + 1)) {
            if (!GeneratedParserUtilBase.empty_element_parsed_guard_(psiBuilder, "eol_comment_1", n2)) {
                return true;
            }
            n2 = GeneratedParserUtilBase.current_position_(psiBuilder);
        }
        return true;
    }
    
    private static boolean b(final PsiBuilder psiBuilder, final int n) {
        if (!GeneratedParserUtilBase.recursion_guard_(psiBuilder, n, "eol_comment_1_0")) {
            return false;
        }
        final PsiBuilder.Marker enter_section_ = GeneratedParserUtilBase.enter_section_(psiBuilder);
        boolean b = GeneratedParserUtilBase.consumeToken(psiBuilder, DxTypes.TAG_OPTION);
        if (!b) {
            b = docData(psiBuilder, n + 1);
        }
        if (!b) {
            b = docTag(psiBuilder, n + 1);
        }
        GeneratedParserUtilBase.exit_section_(psiBuilder, enter_section_, null, b);
        return b;
    }
    
    public static boolean param(final PsiBuilder psiBuilder, final int n) {
        if (!GeneratedParserUtilBase.recursion_guard_(psiBuilder, n, "param")) {
            return false;
        }
        if (!GeneratedParserUtilBase.nextTokenIs(psiBuilder, "<param>", DxTypes.ELLIPSIS, DxTypes.TAG_PARAM)) {
            return false;
        }
        final PsiBuilder.Marker enter_section_ = GeneratedParserUtilBase.enter_section_(psiBuilder, n, 0, DxTypes.PARAM, "<param>");
        boolean b = paramId(psiBuilder, n + 1);
        if (!b) {
            b = a(psiBuilder, n + 1);
        }
        GeneratedParserUtilBase.exit_section_(psiBuilder, n, enter_section_, b, false, null);
        return b;
    }
    
    private static boolean a(final PsiBuilder psiBuilder, final int n) {
        if (!GeneratedParserUtilBase.recursion_guard_(psiBuilder, n, "param_1")) {
            return false;
        }
        final PsiBuilder.Marker enter_section_ = GeneratedParserUtilBase.enter_section_(psiBuilder);
        final boolean b = GeneratedParserUtilBase.consumeToken(psiBuilder, DxTypes.ELLIPSIS) && c(psiBuilder, n + 1);
        GeneratedParserUtilBase.exit_section_(psiBuilder, enter_section_, null, b);
        return b;
    }
    
    private static boolean c(final PsiBuilder psiBuilder, final int n) {
        if (!GeneratedParserUtilBase.recursion_guard_(psiBuilder, n, "param_1_1")) {
            return false;
        }
        paramId(psiBuilder, n + 1);
        return true;
    }
    
    public static boolean paramId(final PsiBuilder psiBuilder, final int n) {
        if (!GeneratedParserUtilBase.recursion_guard_(psiBuilder, n, "paramId")) {
            return false;
        }
        if (!GeneratedParserUtilBase.nextTokenIs(psiBuilder, DxTypes.TAG_PARAM)) {
            return false;
        }
        final PsiBuilder.Marker enter_section_ = GeneratedParserUtilBase.enter_section_(psiBuilder);
        final boolean consumeToken = GeneratedParserUtilBase.consumeToken(psiBuilder, DxTypes.TAG_PARAM);
        GeneratedParserUtilBase.exit_section_(psiBuilder, enter_section_, DxTypes.PARAM_ID, consumeToken);
        return consumeToken;
    }
    
    static boolean tagParams(final PsiBuilder psiBuilder, final int n) {
        if (!GeneratedParserUtilBase.recursion_guard_(psiBuilder, n, "tagParams")) {
            return false;
        }
        final PsiBuilder.Marker enter_section_ = GeneratedParserUtilBase.enter_section_(psiBuilder);
        final boolean b = h(psiBuilder, n + 1) && g(psiBuilder, n + 1);
        GeneratedParserUtilBase.exit_section_(psiBuilder, enter_section_, null, b);
        return b;
    }
    
    private static boolean h(final PsiBuilder psiBuilder, final int n) {
        if (!GeneratedParserUtilBase.recursion_guard_(psiBuilder, n, "tagParams_0")) {
            return false;
        }
        param(psiBuilder, n + 1);
        return true;
    }
    
    private static boolean g(final PsiBuilder psiBuilder, final int n) {
        if (!GeneratedParserUtilBase.recursion_guard_(psiBuilder, n, "tagParams_1")) {
            return false;
        }
        int n2 = GeneratedParserUtilBase.current_position_(psiBuilder);
        while (d(psiBuilder, n + 1)) {
            if (!GeneratedParserUtilBase.empty_element_parsed_guard_(psiBuilder, "tagParams_1", n2)) {
                return true;
            }
            n2 = GeneratedParserUtilBase.current_position_(psiBuilder);
        }
        return true;
    }
    
    private static boolean d(final PsiBuilder psiBuilder, final int n) {
        if (!GeneratedParserUtilBase.recursion_guard_(psiBuilder, n, "tagParams_1_0")) {
            return false;
        }
        final PsiBuilder.Marker enter_section_ = GeneratedParserUtilBase.enter_section_(psiBuilder);
        final boolean b = GeneratedParserUtilBase.consumeToken(psiBuilder, DxTypes.PARAM_SEPARATOR) && param(psiBuilder, n + 1);
        GeneratedParserUtilBase.exit_section_(psiBuilder, enter_section_, null, b);
        return b;
    }
}
