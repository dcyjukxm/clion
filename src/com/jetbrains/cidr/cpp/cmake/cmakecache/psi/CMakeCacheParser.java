// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.cpp.cmake.cmakecache.psi;

import com.jetbrains.cidr.cpp.cmake.cmakecache.psi.util.CMakeCacheParserUtil;
import com.intellij.psi.tree.TokenSet;
import com.intellij.lang.parser.GeneratedParserUtilBase;
import com.intellij.lang.ASTNode;
import com.intellij.lang.PsiBuilder;
import com.intellij.psi.tree.IElementType;
import com.intellij.lang.LightPsiParser;
import com.intellij.lang.PsiParser;

public class CMakeCacheParser implements PsiParser, LightPsiParser
{
    public ASTNode parse(final IElementType elementType, final PsiBuilder psiBuilder) {
        this.parseLight(elementType, psiBuilder);
        return psiBuilder.getTreeBuilt();
    }
    
    public void parseLight(final IElementType elementType, PsiBuilder adapt_builder_) {
        adapt_builder_ = GeneratedParserUtilBase.adapt_builder_(elementType, adapt_builder_, (PsiParser)this, null);
        final PsiBuilder.Marker enter_section_ = GeneratedParserUtilBase.enter_section_(adapt_builder_, 0, 1, null);
        boolean b;
        if (elementType == CMakeCacheElementTypes.CACHE_ENTRY) {
            b = cache_entry(adapt_builder_, 0);
        }
        else if (elementType == CMakeCacheElementTypes.ENTRY_NAME) {
            b = entry_name(adapt_builder_, 0);
        }
        else if (elementType == CMakeCacheElementTypes.ENTRY_TYPE) {
            b = entry_type(adapt_builder_, 0);
        }
        else if (elementType == CMakeCacheElementTypes.ENTRY_VALUE) {
            b = entry_value(adapt_builder_, 0);
        }
        else {
            b = this.parse_root_(elementType, adapt_builder_, 0);
        }
        GeneratedParserUtilBase.exit_section_(adapt_builder_, 0, enter_section_, elementType, b, true, CMakeCacheParserUtil.TRUE_CONDITION);
    }
    
    protected boolean parse_root_(final IElementType elementType, final PsiBuilder psiBuilder, final int n) {
        return root(psiBuilder, n + 1);
    }
    
    public static boolean cache_entry(final PsiBuilder psiBuilder, final int n) {
        if (!GeneratedParserUtilBase.recursion_guard_(psiBuilder, n, "cache_entry")) {
            return false;
        }
        if (!GeneratedParserUtilBase.nextTokenIs(psiBuilder, "<cache entry>", CMakeCacheElementTypes.KEY, CMakeCacheElementTypes.KEY_QUOTE)) {
            return false;
        }
        final PsiBuilder.Marker enter_section_ = GeneratedParserUtilBase.enter_section_(psiBuilder, n, 0, CMakeCacheElementTypes.CACHE_ENTRY, "<cache entry>");
        final boolean entry_name;
        final boolean b = (entry_name = entry_name(psiBuilder, n + 1)) && GeneratedParserUtilBase.report_error_(psiBuilder, GeneratedParserUtilBase.consumeToken(psiBuilder, CMakeCacheElementTypes.TYPE_SEPARATOR));
        final boolean b2 = entry_name && GeneratedParserUtilBase.report_error_(psiBuilder, a(psiBuilder, n + 1)) && b;
        final boolean b3 = entry_name && GeneratedParserUtilBase.report_error_(psiBuilder, GeneratedParserUtilBase.consumeToken(psiBuilder, CMakeCacheElementTypes.VALUE_SEPARATOR)) && b2;
        final boolean b4 = entry_name && b(psiBuilder, n + 1) && b3;
        GeneratedParserUtilBase.exit_section_(psiBuilder, n, enter_section_, b4, entry_name, null);
        return b4 || entry_name;
    }
    
    private static boolean a(final PsiBuilder psiBuilder, final int n) {
        if (!GeneratedParserUtilBase.recursion_guard_(psiBuilder, n, "cache_entry_2")) {
            return false;
        }
        entry_type(psiBuilder, n + 1);
        return true;
    }
    
    private static boolean b(final PsiBuilder psiBuilder, final int n) {
        if (!GeneratedParserUtilBase.recursion_guard_(psiBuilder, n, "cache_entry_4")) {
            return false;
        }
        entry_value(psiBuilder, n + 1);
        return true;
    }
    
    public static boolean entry_name(final PsiBuilder psiBuilder, final int n) {
        if (!GeneratedParserUtilBase.recursion_guard_(psiBuilder, n, "entry_name")) {
            return false;
        }
        if (!GeneratedParserUtilBase.nextTokenIs(psiBuilder, "<entry name>", CMakeCacheElementTypes.KEY, CMakeCacheElementTypes.KEY_QUOTE)) {
            return false;
        }
        final PsiBuilder.Marker enter_section_ = GeneratedParserUtilBase.enter_section_(psiBuilder, n, 0, CMakeCacheElementTypes.ENTRY_NAME, "<entry name>");
        boolean b = GeneratedParserUtilBase.consumeToken(psiBuilder, CMakeCacheElementTypes.KEY);
        if (!b) {
            b = GeneratedParserUtilBase.parseTokens(psiBuilder, 0, CMakeCacheElementTypes.KEY_QUOTE, CMakeCacheElementTypes.KEY, CMakeCacheElementTypes.KEY_QUOTE);
        }
        GeneratedParserUtilBase.exit_section_(psiBuilder, n, enter_section_, b, false, null);
        return b;
    }
    
    public static boolean entry_type(final PsiBuilder psiBuilder, final int n) {
        if (!GeneratedParserUtilBase.recursion_guard_(psiBuilder, n, "entry_type")) {
            return false;
        }
        if (!GeneratedParserUtilBase.nextTokenIs(psiBuilder, CMakeCacheElementTypes.TYPE)) {
            return false;
        }
        final PsiBuilder.Marker enter_section_ = GeneratedParserUtilBase.enter_section_(psiBuilder);
        final boolean consumeToken = GeneratedParserUtilBase.consumeToken(psiBuilder, CMakeCacheElementTypes.TYPE);
        GeneratedParserUtilBase.exit_section_(psiBuilder, enter_section_, CMakeCacheElementTypes.ENTRY_TYPE, consumeToken);
        return consumeToken;
    }
    
    public static boolean entry_value(final PsiBuilder psiBuilder, final int n) {
        if (!GeneratedParserUtilBase.recursion_guard_(psiBuilder, n, "entry_value")) {
            return false;
        }
        if (!GeneratedParserUtilBase.nextTokenIs(psiBuilder, "<entry value>", CMakeCacheElementTypes.VALUE, CMakeCacheElementTypes.VALUE_QUOTE)) {
            return false;
        }
        final PsiBuilder.Marker enter_section_ = GeneratedParserUtilBase.enter_section_(psiBuilder, n, 0, CMakeCacheElementTypes.ENTRY_VALUE, "<entry value>");
        boolean b = GeneratedParserUtilBase.consumeToken(psiBuilder, CMakeCacheElementTypes.VALUE);
        if (!b) {
            b = GeneratedParserUtilBase.parseTokens(psiBuilder, 0, CMakeCacheElementTypes.VALUE_QUOTE, CMakeCacheElementTypes.VALUE, CMakeCacheElementTypes.VALUE_QUOTE);
        }
        GeneratedParserUtilBase.exit_section_(psiBuilder, n, enter_section_, b, false, null);
        return b;
    }
    
    static boolean file_parts(final PsiBuilder psiBuilder, final int n) {
        if (!GeneratedParserUtilBase.recursion_guard_(psiBuilder, n, "file_parts")) {
            return false;
        }
        int n2 = GeneratedParserUtilBase.current_position_(psiBuilder);
        while (d(psiBuilder, n + 1)) {
            if (!GeneratedParserUtilBase.empty_element_parsed_guard_(psiBuilder, "file_parts", n2)) {
                return true;
            }
            n2 = GeneratedParserUtilBase.current_position_(psiBuilder);
        }
        return true;
    }
    
    private static boolean d(final PsiBuilder psiBuilder, final int n) {
        if (!GeneratedParserUtilBase.recursion_guard_(psiBuilder, n, "file_parts_0")) {
            return false;
        }
        final PsiBuilder.Marker enter_section_ = GeneratedParserUtilBase.enter_section_(psiBuilder);
        boolean b = cache_entry(psiBuilder, n + 1);
        if (!b) {
            b = c(psiBuilder, n + 1);
        }
        GeneratedParserUtilBase.exit_section_(psiBuilder, enter_section_, null, b);
        return b;
    }
    
    private static boolean c(final PsiBuilder psiBuilder, final int n) {
        if (!GeneratedParserUtilBase.recursion_guard_(psiBuilder, n, "file_parts_0_1")) {
            return false;
        }
        final PsiBuilder.Marker enter_section_ = GeneratedParserUtilBase.enter_section_(psiBuilder);
        final boolean b = e(psiBuilder, n + 1) && f(psiBuilder, n + 1) && GeneratedParserUtilBase.consumeToken(psiBuilder, CMakeCacheElementTypes.EOL);
        GeneratedParserUtilBase.exit_section_(psiBuilder, enter_section_, null, b);
        return b;
    }
    
    private static boolean e(final PsiBuilder psiBuilder, final int n) {
        if (!GeneratedParserUtilBase.recursion_guard_(psiBuilder, n, "file_parts_0_1_0")) {
            return false;
        }
        GeneratedParserUtilBase.consumeToken(psiBuilder, CMakeCacheElementTypes.WHITESPACES);
        return true;
    }
    
    private static boolean f(final PsiBuilder psiBuilder, final int n) {
        if (!GeneratedParserUtilBase.recursion_guard_(psiBuilder, n, "file_parts_0_1_1")) {
            return false;
        }
        GeneratedParserUtilBase.consumeToken(psiBuilder, CMakeCacheElementTypes.COMMENT);
        return true;
    }
    
    static boolean root(final PsiBuilder psiBuilder, final int n) {
        return file_parts(psiBuilder, n + 1);
    }
}
