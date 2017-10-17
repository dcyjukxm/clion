// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.modulemap.parser;

import com.jetbrains.cidr.modulemap.ModuleMapParserTypes;
import com.intellij.psi.tree.TokenSet;
import com.intellij.lang.ASTNode;
import com.intellij.lang.PsiBuilder;
import com.intellij.psi.tree.IElementType;
import com.intellij.lang.parser.GeneratedParserUtilBase;
import com.intellij.lang.LightPsiParser;
import com.intellij.lang.PsiParser;

public class ModuleMapParser implements PsiParser, LightPsiParser
{
    static final GeneratedParserUtilBase.Parser module_members_recoverer_parser_;
    
    public ASTNode parse(final IElementType elementType, final PsiBuilder psiBuilder) {
        this.parseLight(elementType, psiBuilder);
        return psiBuilder.getTreeBuilt();
    }
    
    public void parseLight(final IElementType elementType, PsiBuilder adapt_builder_) {
        adapt_builder_ = GeneratedParserUtilBase.adapt_builder_(elementType, adapt_builder_, (PsiParser)this, null);
        final PsiBuilder.Marker enter_section_ = GeneratedParserUtilBase.enter_section_(adapt_builder_, 0, 1, null);
        boolean b;
        if (elementType == ModuleMapParserTypes.ATTRIBUTE) {
            b = attribute(adapt_builder_, 0);
        }
        else if (elementType == ModuleMapParserTypes.ATTRIBUTES) {
            b = attributes(adapt_builder_, 0);
        }
        else if (elementType == ModuleMapParserTypes.CONFIG_MACRO_LIST) {
            b = config_macro_list(adapt_builder_, 0);
        }
        else if (elementType == ModuleMapParserTypes.CONFIG_MACROS_DECLARATION) {
            b = config_macros_declaration(adapt_builder_, 0);
        }
        else if (elementType == ModuleMapParserTypes.CONFLICT_DECLARATION) {
            b = conflict_declaration(adapt_builder_, 0);
        }
        else if (elementType == ModuleMapParserTypes.EXPORT_DECLARATION) {
            b = export_declaration(adapt_builder_, 0);
        }
        else if (elementType == ModuleMapParserTypes.EXTERN_MODULE_DECLARATION) {
            b = extern_module_declaration(adapt_builder_, 0);
        }
        else if (elementType == ModuleMapParserTypes.FEATURE) {
            b = feature(adapt_builder_, 0);
        }
        else if (elementType == ModuleMapParserTypes.FEATURE_LIST) {
            b = feature_list(adapt_builder_, 0);
        }
        else if (elementType == ModuleMapParserTypes.HEADER_DECLARATION) {
            b = header_declaration(adapt_builder_, 0);
        }
        else if (elementType == ModuleMapParserTypes.HEADER_NAME) {
            b = header_name(adapt_builder_, 0);
        }
        else if (elementType == ModuleMapParserTypes.INFERRED_SUBMODULE_DECLARATION) {
            b = inferred_submodule_declaration(adapt_builder_, 0);
        }
        else if (elementType == ModuleMapParserTypes.INFERRED_SUBMODULE_MEMBER) {
            b = inferred_submodule_member(adapt_builder_, 0);
        }
        else if (elementType == ModuleMapParserTypes.LINK_DECLARATION) {
            b = link_declaration(adapt_builder_, 0);
        }
        else if (elementType == ModuleMapParserTypes.MODULE_DECLARATION) {
            b = module_declaration(adapt_builder_, 0);
        }
        else if (elementType == ModuleMapParserTypes.MODULE_ID) {
            b = module_id(adapt_builder_, 0);
        }
        else if (elementType == ModuleMapParserTypes.REQUIRES_DECLARATION) {
            b = requires_declaration(adapt_builder_, 0);
        }
        else if (elementType == ModuleMapParserTypes.UMBRELLA_DIR_DECLARATION) {
            b = umbrella_dir_declaration(adapt_builder_, 0);
        }
        else if (elementType == ModuleMapParserTypes.USE_DECLARATION) {
            b = use_declaration(adapt_builder_, 0);
        }
        else if (elementType == ModuleMapParserTypes.WILDCARD_MODULE_ID) {
            b = wildcard_module_id(adapt_builder_, 0);
        }
        else {
            b = this.parse_root_(elementType, adapt_builder_, 0);
        }
        GeneratedParserUtilBase.exit_section_(adapt_builder_, 0, enter_section_, elementType, b, true, GeneratedParserUtilBase.TRUE_CONDITION);
    }
    
    protected boolean parse_root_(final IElementType elementType, final PsiBuilder psiBuilder, final int n) {
        return file(psiBuilder, n + 1);
    }
    
    public static boolean attribute(final PsiBuilder psiBuilder, final int n) {
        if (!GeneratedParserUtilBase.recursion_guard_(psiBuilder, n, "attribute")) {
            return false;
        }
        if (!GeneratedParserUtilBase.nextTokenIs(psiBuilder, ModuleMapParserTypes.L_BRACKET)) {
            return false;
        }
        final PsiBuilder.Marker enter_section_ = GeneratedParserUtilBase.enter_section_(psiBuilder, n, 0, ModuleMapParserTypes.ATTRIBUTE, null);
        final boolean consumeTokens;
        final boolean b = consumeTokens = GeneratedParserUtilBase.consumeTokens(psiBuilder, 1, ModuleMapParserTypes.L_BRACKET, ModuleMapParserTypes.IDENTIFIER, ModuleMapParserTypes.R_BRACKET);
        GeneratedParserUtilBase.exit_section_(psiBuilder, n, enter_section_, b, consumeTokens, null);
        return b || consumeTokens;
    }
    
    public static boolean attributes(final PsiBuilder psiBuilder, final int n) {
        if (!GeneratedParserUtilBase.recursion_guard_(psiBuilder, n, "attributes")) {
            return false;
        }
        if (!GeneratedParserUtilBase.nextTokenIs(psiBuilder, ModuleMapParserTypes.L_BRACKET)) {
            return false;
        }
        final PsiBuilder.Marker enter_section_ = GeneratedParserUtilBase.enter_section_(psiBuilder, n, 0, ModuleMapParserTypes.ATTRIBUTES, null);
        final boolean attribute;
        final boolean b = (attribute = attribute(psiBuilder, n + 1)) && l(psiBuilder, n + 1);
        GeneratedParserUtilBase.exit_section_(psiBuilder, n, enter_section_, b, attribute, null);
        return b || attribute;
    }
    
    private static boolean l(final PsiBuilder psiBuilder, final int n) {
        if (!GeneratedParserUtilBase.recursion_guard_(psiBuilder, n, "attributes_1")) {
            return false;
        }
        attributes(psiBuilder, n + 1);
        return true;
    }
    
    public static boolean config_macro_list(final PsiBuilder psiBuilder, final int n) {
        if (!GeneratedParserUtilBase.recursion_guard_(psiBuilder, n, "config_macro_list")) {
            return false;
        }
        if (!GeneratedParserUtilBase.nextTokenIs(psiBuilder, ModuleMapParserTypes.IDENTIFIER)) {
            return false;
        }
        final PsiBuilder.Marker enter_section_ = GeneratedParserUtilBase.enter_section_(psiBuilder, n, 0, ModuleMapParserTypes.CONFIG_MACRO_LIST, null);
        final boolean consumeToken;
        final boolean b = (consumeToken = GeneratedParserUtilBase.consumeToken(psiBuilder, ModuleMapParserTypes.IDENTIFIER)) && e(psiBuilder, n + 1);
        GeneratedParserUtilBase.exit_section_(psiBuilder, n, enter_section_, b, consumeToken, null);
        return b || consumeToken;
    }
    
    private static boolean e(final PsiBuilder psiBuilder, final int n) {
        if (!GeneratedParserUtilBase.recursion_guard_(psiBuilder, n, "config_macro_list_1")) {
            return false;
        }
        int n2 = GeneratedParserUtilBase.current_position_(psiBuilder);
        while (s(psiBuilder, n + 1)) {
            if (!GeneratedParserUtilBase.empty_element_parsed_guard_(psiBuilder, "config_macro_list_1", n2)) {
                return true;
            }
            n2 = GeneratedParserUtilBase.current_position_(psiBuilder);
        }
        return true;
    }
    
    private static boolean s(final PsiBuilder psiBuilder, final int n) {
        if (!GeneratedParserUtilBase.recursion_guard_(psiBuilder, n, "config_macro_list_1_0")) {
            return false;
        }
        final PsiBuilder.Marker enter_section_ = GeneratedParserUtilBase.enter_section_(psiBuilder, n, 0);
        final boolean consumeTokens;
        final boolean b = consumeTokens = GeneratedParserUtilBase.consumeTokens(psiBuilder, 1, ModuleMapParserTypes.COMMA, ModuleMapParserTypes.IDENTIFIER);
        GeneratedParserUtilBase.exit_section_(psiBuilder, n, enter_section_, b, consumeTokens, null);
        return b || consumeTokens;
    }
    
    public static boolean config_macros_declaration(final PsiBuilder psiBuilder, final int n) {
        if (!GeneratedParserUtilBase.recursion_guard_(psiBuilder, n, "config_macros_declaration")) {
            return false;
        }
        if (!GeneratedParserUtilBase.nextTokenIs(psiBuilder, ModuleMapParserTypes.CONFIG_MACROS)) {
            return false;
        }
        final PsiBuilder.Marker enter_section_ = GeneratedParserUtilBase.enter_section_(psiBuilder, n, 0, ModuleMapParserTypes.CONFIG_MACROS_DECLARATION, null);
        final boolean consumeToken;
        final boolean b = (consumeToken = GeneratedParserUtilBase.consumeToken(psiBuilder, ModuleMapParserTypes.CONFIG_MACROS)) && GeneratedParserUtilBase.report_error_(psiBuilder, h(psiBuilder, n + 1));
        final boolean b2 = consumeToken && config_macro_list(psiBuilder, n + 1) && b;
        GeneratedParserUtilBase.exit_section_(psiBuilder, n, enter_section_, b2, consumeToken, null);
        return b2 || consumeToken;
    }
    
    private static boolean h(final PsiBuilder psiBuilder, final int n) {
        if (!GeneratedParserUtilBase.recursion_guard_(psiBuilder, n, "config_macros_declaration_1")) {
            return false;
        }
        attributes(psiBuilder, n + 1);
        return true;
    }
    
    public static boolean conflict_declaration(final PsiBuilder psiBuilder, final int n) {
        if (!GeneratedParserUtilBase.recursion_guard_(psiBuilder, n, "conflict_declaration")) {
            return false;
        }
        if (!GeneratedParserUtilBase.nextTokenIs(psiBuilder, ModuleMapParserTypes.CONFLICT)) {
            return false;
        }
        final PsiBuilder.Marker enter_section_ = GeneratedParserUtilBase.enter_section_(psiBuilder, n, 0, ModuleMapParserTypes.CONFLICT_DECLARATION, null);
        final boolean consumeToken;
        final boolean b = (consumeToken = GeneratedParserUtilBase.consumeToken(psiBuilder, ModuleMapParserTypes.CONFLICT)) && GeneratedParserUtilBase.report_error_(psiBuilder, module_id(psiBuilder, n + 1));
        final boolean b2 = consumeToken && GeneratedParserUtilBase.report_error_(psiBuilder, GeneratedParserUtilBase.consumeTokens(psiBuilder, -1, ModuleMapParserTypes.COMMA, ModuleMapParserTypes.STRING)) && b;
        GeneratedParserUtilBase.exit_section_(psiBuilder, n, enter_section_, b2, consumeToken, null);
        return b2 || consumeToken;
    }
    
    public static boolean export_declaration(final PsiBuilder psiBuilder, final int n) {
        if (!GeneratedParserUtilBase.recursion_guard_(psiBuilder, n, "export_declaration")) {
            return false;
        }
        if (!GeneratedParserUtilBase.nextTokenIs(psiBuilder, ModuleMapParserTypes.EXPORT)) {
            return false;
        }
        final PsiBuilder.Marker enter_section_ = GeneratedParserUtilBase.enter_section_(psiBuilder, n, 0, ModuleMapParserTypes.EXPORT_DECLARATION, null);
        final boolean consumeToken;
        final boolean b = (consumeToken = GeneratedParserUtilBase.consumeToken(psiBuilder, ModuleMapParserTypes.EXPORT)) && wildcard_module_id(psiBuilder, n + 1);
        GeneratedParserUtilBase.exit_section_(psiBuilder, n, enter_section_, b, consumeToken, null);
        return b || consumeToken;
    }
    
    public static boolean extern_module_declaration(final PsiBuilder psiBuilder, final int n) {
        if (!GeneratedParserUtilBase.recursion_guard_(psiBuilder, n, "extern_module_declaration")) {
            return false;
        }
        if (!GeneratedParserUtilBase.nextTokenIs(psiBuilder, ModuleMapParserTypes.EXTERN)) {
            return false;
        }
        final PsiBuilder.Marker enter_section_ = GeneratedParserUtilBase.enter_section_(psiBuilder, n, 0, ModuleMapParserTypes.EXTERN_MODULE_DECLARATION, null);
        final boolean consumeTokens;
        final boolean b = (consumeTokens = GeneratedParserUtilBase.consumeTokens(psiBuilder, 1, ModuleMapParserTypes.EXTERN, ModuleMapParserTypes.MODULE)) && GeneratedParserUtilBase.report_error_(psiBuilder, module_id(psiBuilder, n + 1));
        final boolean b2 = consumeTokens && GeneratedParserUtilBase.consumeToken(psiBuilder, ModuleMapParserTypes.STRING) && b;
        GeneratedParserUtilBase.exit_section_(psiBuilder, n, enter_section_, b2, consumeTokens, null);
        return b2 || consumeTokens;
    }
    
    public static boolean feature(final PsiBuilder psiBuilder, final int n) {
        if (!GeneratedParserUtilBase.recursion_guard_(psiBuilder, n, "feature")) {
            return false;
        }
        if (!GeneratedParserUtilBase.nextTokenIs(psiBuilder, "<feature>", ModuleMapParserTypes.EXCL, ModuleMapParserTypes.IDENTIFIER)) {
            return false;
        }
        final PsiBuilder.Marker enter_section_ = GeneratedParserUtilBase.enter_section_(psiBuilder, n, 0, ModuleMapParserTypes.FEATURE, "<feature>");
        final boolean b = m(psiBuilder, n + 1) && GeneratedParserUtilBase.consumeToken(psiBuilder, ModuleMapParserTypes.IDENTIFIER);
        GeneratedParserUtilBase.exit_section_(psiBuilder, n, enter_section_, b, false, null);
        return b;
    }
    
    private static boolean m(final PsiBuilder psiBuilder, final int n) {
        if (!GeneratedParserUtilBase.recursion_guard_(psiBuilder, n, "feature_0")) {
            return false;
        }
        GeneratedParserUtilBase.consumeToken(psiBuilder, ModuleMapParserTypes.EXCL);
        return true;
    }
    
    public static boolean feature_list(final PsiBuilder psiBuilder, final int n) {
        if (!GeneratedParserUtilBase.recursion_guard_(psiBuilder, n, "feature_list")) {
            return false;
        }
        if (!GeneratedParserUtilBase.nextTokenIs(psiBuilder, "<feature list>", ModuleMapParserTypes.EXCL, ModuleMapParserTypes.IDENTIFIER)) {
            return false;
        }
        final PsiBuilder.Marker enter_section_ = GeneratedParserUtilBase.enter_section_(psiBuilder, n, 0, ModuleMapParserTypes.FEATURE_LIST, "<feature list>");
        final boolean feature;
        final boolean b = (feature = feature(psiBuilder, n + 1)) && t(psiBuilder, n + 1);
        GeneratedParserUtilBase.exit_section_(psiBuilder, n, enter_section_, b, feature, null);
        return b || feature;
    }
    
    private static boolean t(final PsiBuilder psiBuilder, final int n) {
        if (!GeneratedParserUtilBase.recursion_guard_(psiBuilder, n, "feature_list_1")) {
            return false;
        }
        int n2 = GeneratedParserUtilBase.current_position_(psiBuilder);
        while (o(psiBuilder, n + 1)) {
            if (!GeneratedParserUtilBase.empty_element_parsed_guard_(psiBuilder, "feature_list_1", n2)) {
                return true;
            }
            n2 = GeneratedParserUtilBase.current_position_(psiBuilder);
        }
        return true;
    }
    
    private static boolean o(final PsiBuilder psiBuilder, final int n) {
        if (!GeneratedParserUtilBase.recursion_guard_(psiBuilder, n, "feature_list_1_0")) {
            return false;
        }
        final PsiBuilder.Marker enter_section_ = GeneratedParserUtilBase.enter_section_(psiBuilder, n, 0);
        final boolean consumeToken;
        final boolean b = (consumeToken = GeneratedParserUtilBase.consumeToken(psiBuilder, ModuleMapParserTypes.COMMA)) && feature(psiBuilder, n + 1);
        GeneratedParserUtilBase.exit_section_(psiBuilder, n, enter_section_, b, consumeToken, null);
        return b || consumeToken;
    }
    
    static boolean file(final PsiBuilder psiBuilder, final int n) {
        if (!GeneratedParserUtilBase.recursion_guard_(psiBuilder, n, "file")) {
            return false;
        }
        int n2 = GeneratedParserUtilBase.current_position_(psiBuilder);
        while (module_declaration_base(psiBuilder, n + 1)) {
            if (!GeneratedParserUtilBase.empty_element_parsed_guard_(psiBuilder, "file", n2)) {
                return true;
            }
            n2 = GeneratedParserUtilBase.current_position_(psiBuilder);
        }
        return true;
    }
    
    public static boolean header_declaration(final PsiBuilder psiBuilder, final int n) {
        if (!GeneratedParserUtilBase.recursion_guard_(psiBuilder, n, "header_declaration")) {
            return false;
        }
        final PsiBuilder.Marker enter_section_ = GeneratedParserUtilBase.enter_section_(psiBuilder, n, 0, ModuleMapParserTypes.HEADER_DECLARATION, "<header declaration>");
        final boolean b2;
        final boolean b = (b2 = (g(psiBuilder, n + 1) && GeneratedParserUtilBase.consumeToken(psiBuilder, ModuleMapParserTypes.HEADER))) && header_name(psiBuilder, n + 1);
        GeneratedParserUtilBase.exit_section_(psiBuilder, n, enter_section_, b, b2, null);
        return b || b2;
    }
    
    private static boolean g(final PsiBuilder psiBuilder, final int n) {
        if (!GeneratedParserUtilBase.recursion_guard_(psiBuilder, n, "header_declaration_0")) {
            return false;
        }
        final PsiBuilder.Marker enter_section_ = GeneratedParserUtilBase.enter_section_(psiBuilder);
        boolean b = GeneratedParserUtilBase.consumeToken(psiBuilder, ModuleMapParserTypes.UMBRELLA);
        if (!b) {
            b = GeneratedParserUtilBase.consumeToken(psiBuilder, ModuleMapParserTypes.EXCLUDE);
        }
        if (!b) {
            b = u(psiBuilder, n + 1);
        }
        GeneratedParserUtilBase.exit_section_(psiBuilder, enter_section_, null, b);
        return b;
    }
    
    private static boolean u(final PsiBuilder psiBuilder, final int n) {
        if (!GeneratedParserUtilBase.recursion_guard_(psiBuilder, n, "header_declaration_0_2")) {
            return false;
        }
        final PsiBuilder.Marker enter_section_ = GeneratedParserUtilBase.enter_section_(psiBuilder);
        final boolean b = y(psiBuilder, n + 1) && q(psiBuilder, n + 1);
        GeneratedParserUtilBase.exit_section_(psiBuilder, enter_section_, null, b);
        return b;
    }
    
    private static boolean y(final PsiBuilder psiBuilder, final int n) {
        if (!GeneratedParserUtilBase.recursion_guard_(psiBuilder, n, "header_declaration_0_2_0")) {
            return false;
        }
        GeneratedParserUtilBase.consumeToken(psiBuilder, ModuleMapParserTypes.PRIVATE);
        return true;
    }
    
    private static boolean q(final PsiBuilder psiBuilder, final int n) {
        if (!GeneratedParserUtilBase.recursion_guard_(psiBuilder, n, "header_declaration_0_2_1")) {
            return false;
        }
        GeneratedParserUtilBase.consumeToken(psiBuilder, ModuleMapParserTypes.TEXTUAL);
        return true;
    }
    
    public static boolean header_name(final PsiBuilder psiBuilder, final int n) {
        if (!GeneratedParserUtilBase.recursion_guard_(psiBuilder, n, "header_name")) {
            return false;
        }
        if (!GeneratedParserUtilBase.nextTokenIs(psiBuilder, ModuleMapParserTypes.STRING)) {
            return false;
        }
        final PsiBuilder.Marker enter_section_ = GeneratedParserUtilBase.enter_section_(psiBuilder);
        final boolean consumeToken = GeneratedParserUtilBase.consumeToken(psiBuilder, ModuleMapParserTypes.STRING);
        GeneratedParserUtilBase.exit_section_(psiBuilder, enter_section_, ModuleMapParserTypes.HEADER_NAME, consumeToken);
        return consumeToken;
    }
    
    public static boolean inferred_submodule_declaration(final PsiBuilder psiBuilder, final int n) {
        if (!GeneratedParserUtilBase.recursion_guard_(psiBuilder, n, "inferred_submodule_declaration")) {
            return false;
        }
        final PsiBuilder.Marker enter_section_ = GeneratedParserUtilBase.enter_section_(psiBuilder, n, 0, ModuleMapParserTypes.INFERRED_SUBMODULE_DECLARATION, "<inferred submodule declaration>");
        final boolean b2;
        final boolean b = (b2 = (b(psiBuilder, n + 1) && n(psiBuilder, n + 1) && GeneratedParserUtilBase.consumeTokens(psiBuilder, 2, ModuleMapParserTypes.MODULE, ModuleMapParserTypes.WILDCARD))) && GeneratedParserUtilBase.report_error_(psiBuilder, r(psiBuilder, n + 1));
        final boolean b3 = b2 && GeneratedParserUtilBase.report_error_(psiBuilder, GeneratedParserUtilBase.consumeToken(psiBuilder, ModuleMapParserTypes.L_CURLY)) && b;
        final boolean b4 = b2 && GeneratedParserUtilBase.report_error_(psiBuilder, v(psiBuilder, n + 1)) && b3;
        final boolean b5 = b2 && GeneratedParserUtilBase.consumeToken(psiBuilder, ModuleMapParserTypes.R_CURLY) && b4;
        GeneratedParserUtilBase.exit_section_(psiBuilder, n, enter_section_, b5, b2, null);
        return b5 || b2;
    }
    
    private static boolean b(final PsiBuilder psiBuilder, final int n) {
        if (!GeneratedParserUtilBase.recursion_guard_(psiBuilder, n, "inferred_submodule_declaration_0")) {
            return false;
        }
        GeneratedParserUtilBase.consumeToken(psiBuilder, ModuleMapParserTypes.EXPLICIT);
        return true;
    }
    
    private static boolean n(final PsiBuilder psiBuilder, final int n) {
        if (!GeneratedParserUtilBase.recursion_guard_(psiBuilder, n, "inferred_submodule_declaration_1")) {
            return false;
        }
        GeneratedParserUtilBase.consumeToken(psiBuilder, ModuleMapParserTypes.FRAMEWORK);
        return true;
    }
    
    private static boolean r(final PsiBuilder psiBuilder, final int n) {
        if (!GeneratedParserUtilBase.recursion_guard_(psiBuilder, n, "inferred_submodule_declaration_4")) {
            return false;
        }
        attributes(psiBuilder, n + 1);
        return true;
    }
    
    private static boolean v(final PsiBuilder psiBuilder, final int n) {
        if (!GeneratedParserUtilBase.recursion_guard_(psiBuilder, n, "inferred_submodule_declaration_6")) {
            return false;
        }
        int n2 = GeneratedParserUtilBase.current_position_(psiBuilder);
        while (inferred_submodule_member(psiBuilder, n + 1)) {
            if (!GeneratedParserUtilBase.empty_element_parsed_guard_(psiBuilder, "inferred_submodule_declaration_6", n2)) {
                return true;
            }
            n2 = GeneratedParserUtilBase.current_position_(psiBuilder);
        }
        return true;
    }
    
    public static boolean inferred_submodule_member(final PsiBuilder psiBuilder, final int n) {
        if (!GeneratedParserUtilBase.recursion_guard_(psiBuilder, n, "inferred_submodule_member")) {
            return false;
        }
        if (!GeneratedParserUtilBase.nextTokenIs(psiBuilder, ModuleMapParserTypes.EXPORT)) {
            return false;
        }
        final PsiBuilder.Marker enter_section_ = GeneratedParserUtilBase.enter_section_(psiBuilder, n, 0, ModuleMapParserTypes.INFERRED_SUBMODULE_MEMBER, null);
        final boolean consumeTokens;
        final boolean b = consumeTokens = GeneratedParserUtilBase.consumeTokens(psiBuilder, 1, ModuleMapParserTypes.EXPORT, ModuleMapParserTypes.WILDCARD);
        GeneratedParserUtilBase.exit_section_(psiBuilder, n, enter_section_, b, consumeTokens, null);
        return b || consumeTokens;
    }
    
    public static boolean link_declaration(final PsiBuilder psiBuilder, final int n) {
        if (!GeneratedParserUtilBase.recursion_guard_(psiBuilder, n, "link_declaration")) {
            return false;
        }
        if (!GeneratedParserUtilBase.nextTokenIs(psiBuilder, ModuleMapParserTypes.LINK)) {
            return false;
        }
        final PsiBuilder.Marker enter_section_ = GeneratedParserUtilBase.enter_section_(psiBuilder, n, 0, ModuleMapParserTypes.LINK_DECLARATION, null);
        final boolean consumeToken;
        final boolean b = (consumeToken = GeneratedParserUtilBase.consumeToken(psiBuilder, ModuleMapParserTypes.LINK)) && GeneratedParserUtilBase.report_error_(psiBuilder, d(psiBuilder, n + 1));
        final boolean b2 = consumeToken && GeneratedParserUtilBase.consumeToken(psiBuilder, ModuleMapParserTypes.STRING) && b;
        GeneratedParserUtilBase.exit_section_(psiBuilder, n, enter_section_, b2, consumeToken, null);
        return b2 || consumeToken;
    }
    
    private static boolean d(final PsiBuilder psiBuilder, final int n) {
        if (!GeneratedParserUtilBase.recursion_guard_(psiBuilder, n, "link_declaration_1")) {
            return false;
        }
        GeneratedParserUtilBase.consumeToken(psiBuilder, ModuleMapParserTypes.FRAMEWORK);
        return true;
    }
    
    public static boolean module_declaration(final PsiBuilder psiBuilder, final int n) {
        if (!GeneratedParserUtilBase.recursion_guard_(psiBuilder, n, "module_declaration")) {
            return false;
        }
        final PsiBuilder.Marker enter_section_ = GeneratedParserUtilBase.enter_section_(psiBuilder, n, 0, ModuleMapParserTypes.MODULE_DECLARATION, "<module declaration>");
        final boolean i;
        final boolean b = (i = i(psiBuilder, n + 1)) && GeneratedParserUtilBase.report_error_(psiBuilder, module_id(psiBuilder, n + 1));
        final boolean b2 = i && GeneratedParserUtilBase.report_error_(psiBuilder, c(psiBuilder, n + 1)) && b;
        final boolean b3 = i && module_members(psiBuilder, n + 1) && b2;
        GeneratedParserUtilBase.exit_section_(psiBuilder, n, enter_section_, b3, i, null);
        return b3 || i;
    }
    
    private static boolean i(final PsiBuilder psiBuilder, final int n) {
        if (!GeneratedParserUtilBase.recursion_guard_(psiBuilder, n, "module_declaration_0")) {
            return false;
        }
        final PsiBuilder.Marker enter_section_ = GeneratedParserUtilBase.enter_section_(psiBuilder);
        final boolean b = a(psiBuilder, n + 1) && k(psiBuilder, n + 1) && GeneratedParserUtilBase.consumeToken(psiBuilder, ModuleMapParserTypes.MODULE);
        GeneratedParserUtilBase.exit_section_(psiBuilder, enter_section_, null, b);
        return b;
    }
    
    private static boolean a(final PsiBuilder psiBuilder, final int n) {
        if (!GeneratedParserUtilBase.recursion_guard_(psiBuilder, n, "module_declaration_0_0")) {
            return false;
        }
        GeneratedParserUtilBase.consumeToken(psiBuilder, ModuleMapParserTypes.EXPLICIT);
        return true;
    }
    
    private static boolean k(final PsiBuilder psiBuilder, final int n) {
        if (!GeneratedParserUtilBase.recursion_guard_(psiBuilder, n, "module_declaration_0_1")) {
            return false;
        }
        GeneratedParserUtilBase.consumeToken(psiBuilder, ModuleMapParserTypes.FRAMEWORK);
        return true;
    }
    
    private static boolean c(final PsiBuilder psiBuilder, final int n) {
        if (!GeneratedParserUtilBase.recursion_guard_(psiBuilder, n, "module_declaration_2")) {
            return false;
        }
        attributes(psiBuilder, n + 1);
        return true;
    }
    
    static boolean module_declaration_base(final PsiBuilder psiBuilder, final int n) {
        if (!GeneratedParserUtilBase.recursion_guard_(psiBuilder, n, "module_declaration_base")) {
            return false;
        }
        final PsiBuilder.Marker enter_section_ = GeneratedParserUtilBase.enter_section_(psiBuilder);
        boolean b = extern_module_declaration(psiBuilder, n + 1);
        if (!b) {
            b = module_declaration(psiBuilder, n + 1);
        }
        GeneratedParserUtilBase.exit_section_(psiBuilder, enter_section_, null, b);
        return b;
    }
    
    public static boolean module_id(final PsiBuilder psiBuilder, final int n) {
        if (!GeneratedParserUtilBase.recursion_guard_(psiBuilder, n, "module_id")) {
            return false;
        }
        if (!GeneratedParserUtilBase.nextTokenIs(psiBuilder, ModuleMapParserTypes.IDENTIFIER)) {
            return false;
        }
        final PsiBuilder.Marker enter_section_ = GeneratedParserUtilBase.enter_section_(psiBuilder, n, 0, ModuleMapParserTypes.MODULE_ID, null);
        final boolean consumeToken;
        final boolean b = (consumeToken = GeneratedParserUtilBase.consumeToken(psiBuilder, ModuleMapParserTypes.IDENTIFIER)) && x(psiBuilder, n + 1);
        GeneratedParserUtilBase.exit_section_(psiBuilder, n, enter_section_, b, consumeToken, null);
        return b || consumeToken;
    }
    
    private static boolean x(final PsiBuilder psiBuilder, final int n) {
        if (!GeneratedParserUtilBase.recursion_guard_(psiBuilder, n, "module_id_1")) {
            return false;
        }
        int n2 = GeneratedParserUtilBase.current_position_(psiBuilder);
        while (p(psiBuilder, n + 1)) {
            if (!GeneratedParserUtilBase.empty_element_parsed_guard_(psiBuilder, "module_id_1", n2)) {
                return true;
            }
            n2 = GeneratedParserUtilBase.current_position_(psiBuilder);
        }
        return true;
    }
    
    private static boolean p(final PsiBuilder psiBuilder, final int n) {
        if (!GeneratedParserUtilBase.recursion_guard_(psiBuilder, n, "module_id_1_0")) {
            return false;
        }
        final PsiBuilder.Marker enter_section_ = GeneratedParserUtilBase.enter_section_(psiBuilder, n, 0);
        final boolean consumeTokens;
        final boolean b = consumeTokens = GeneratedParserUtilBase.consumeTokens(psiBuilder, 1, ModuleMapParserTypes.DOT, ModuleMapParserTypes.IDENTIFIER);
        GeneratedParserUtilBase.exit_section_(psiBuilder, n, enter_section_, b, consumeTokens, null);
        return b || consumeTokens;
    }
    
    static boolean module_member(final PsiBuilder psiBuilder, final int n) {
        if (!GeneratedParserUtilBase.recursion_guard_(psiBuilder, n, "module_member")) {
            return false;
        }
        final PsiBuilder.Marker enter_section_ = GeneratedParserUtilBase.enter_section_(psiBuilder);
        boolean b = requires_declaration(psiBuilder, n + 1);
        if (!b) {
            b = header_declaration(psiBuilder, n + 1);
        }
        if (!b) {
            b = umbrella_dir_declaration(psiBuilder, n + 1);
        }
        if (!b) {
            b = export_declaration(psiBuilder, n + 1);
        }
        if (!b) {
            b = use_declaration(psiBuilder, n + 1);
        }
        if (!b) {
            b = link_declaration(psiBuilder, n + 1);
        }
        if (!b) {
            b = config_macros_declaration(psiBuilder, n + 1);
        }
        if (!b) {
            b = conflict_declaration(psiBuilder, n + 1);
        }
        if (!b) {
            b = submodule_declaration(psiBuilder, n + 1);
        }
        GeneratedParserUtilBase.exit_section_(psiBuilder, enter_section_, null, b);
        return b;
    }
    
    static boolean module_members(final PsiBuilder psiBuilder, final int n) {
        if (!GeneratedParserUtilBase.recursion_guard_(psiBuilder, n, "module_members")) {
            return false;
        }
        final PsiBuilder.Marker enter_section_ = GeneratedParserUtilBase.enter_section_(psiBuilder, n, 0);
        final boolean consumeToken;
        final boolean b = (consumeToken = GeneratedParserUtilBase.consumeToken(psiBuilder, ModuleMapParserTypes.L_CURLY)) && GeneratedParserUtilBase.report_error_(psiBuilder, j(psiBuilder, n + 1));
        final boolean b2 = consumeToken && GeneratedParserUtilBase.consumeToken(psiBuilder, ModuleMapParserTypes.R_CURLY) && b;
        GeneratedParserUtilBase.exit_section_(psiBuilder, n, enter_section_, b2, consumeToken, ModuleMapParser.module_members_recoverer_parser_);
        return b2 || consumeToken;
    }
    
    private static boolean j(final PsiBuilder psiBuilder, final int n) {
        if (!GeneratedParserUtilBase.recursion_guard_(psiBuilder, n, "module_members_1")) {
            return false;
        }
        int n2 = GeneratedParserUtilBase.current_position_(psiBuilder);
        while (module_member(psiBuilder, n + 1)) {
            if (!GeneratedParserUtilBase.empty_element_parsed_guard_(psiBuilder, "module_members_1", n2)) {
                return true;
            }
            n2 = GeneratedParserUtilBase.current_position_(psiBuilder);
        }
        return true;
    }
    
    static boolean module_members_recoverer(final PsiBuilder psiBuilder, final int n) {
        if (!GeneratedParserUtilBase.recursion_guard_(psiBuilder, n, "module_members_recoverer")) {
            return false;
        }
        final PsiBuilder.Marker enter_section_ = GeneratedParserUtilBase.enter_section_(psiBuilder, n, 16);
        final boolean b = !f(psiBuilder, n + 1);
        GeneratedParserUtilBase.exit_section_(psiBuilder, n, enter_section_, b, false, null);
        return b;
    }
    
    private static boolean f(final PsiBuilder psiBuilder, final int n) {
        if (!GeneratedParserUtilBase.recursion_guard_(psiBuilder, n, "module_members_recoverer_0")) {
            return false;
        }
        final PsiBuilder.Marker enter_section_ = GeneratedParserUtilBase.enter_section_(psiBuilder);
        boolean b = GeneratedParserUtilBase.consumeToken(psiBuilder, ModuleMapParserTypes.R_CURLY);
        if (!b) {
            b = GeneratedParserUtilBase.consumeToken(psiBuilder, ModuleMapParserTypes.REQUIRES);
        }
        if (!b) {
            b = GeneratedParserUtilBase.consumeToken(psiBuilder, ModuleMapParserTypes.HEADER);
        }
        if (!b) {
            b = GeneratedParserUtilBase.consumeToken(psiBuilder, ModuleMapParserTypes.EXCLUDE);
        }
        if (!b) {
            b = GeneratedParserUtilBase.consumeToken(psiBuilder, ModuleMapParserTypes.PRIVATE);
        }
        if (!b) {
            b = GeneratedParserUtilBase.consumeToken(psiBuilder, ModuleMapParserTypes.TEXTUAL);
        }
        if (!b) {
            b = GeneratedParserUtilBase.consumeToken(psiBuilder, ModuleMapParserTypes.UMBRELLA);
        }
        if (!b) {
            b = GeneratedParserUtilBase.consumeToken(psiBuilder, ModuleMapParserTypes.EXPORT);
        }
        if (!b) {
            b = GeneratedParserUtilBase.consumeToken(psiBuilder, ModuleMapParserTypes.USE);
        }
        if (!b) {
            b = GeneratedParserUtilBase.consumeToken(psiBuilder, ModuleMapParserTypes.LINK);
        }
        if (!b) {
            b = GeneratedParserUtilBase.consumeToken(psiBuilder, ModuleMapParserTypes.CONFIG_MACROS);
        }
        if (!b) {
            b = GeneratedParserUtilBase.consumeToken(psiBuilder, ModuleMapParserTypes.CONFLICT);
        }
        if (!b) {
            b = GeneratedParserUtilBase.consumeToken(psiBuilder, ModuleMapParserTypes.MODULE);
        }
        if (!b) {
            b = GeneratedParserUtilBase.consumeToken(psiBuilder, ModuleMapParserTypes.EXPLICIT);
        }
        if (!b) {
            b = GeneratedParserUtilBase.consumeToken(psiBuilder, ModuleMapParserTypes.FRAMEWORK);
        }
        GeneratedParserUtilBase.exit_section_(psiBuilder, enter_section_, null, b);
        return b;
    }
    
    public static boolean requires_declaration(final PsiBuilder psiBuilder, final int n) {
        if (!GeneratedParserUtilBase.recursion_guard_(psiBuilder, n, "requires_declaration")) {
            return false;
        }
        if (!GeneratedParserUtilBase.nextTokenIs(psiBuilder, ModuleMapParserTypes.REQUIRES)) {
            return false;
        }
        final PsiBuilder.Marker enter_section_ = GeneratedParserUtilBase.enter_section_(psiBuilder, n, 0, ModuleMapParserTypes.REQUIRES_DECLARATION, null);
        final boolean consumeToken;
        final boolean b = (consumeToken = GeneratedParserUtilBase.consumeToken(psiBuilder, ModuleMapParserTypes.REQUIRES)) && feature_list(psiBuilder, n + 1);
        GeneratedParserUtilBase.exit_section_(psiBuilder, n, enter_section_, b, consumeToken, null);
        return b || consumeToken;
    }
    
    static boolean submodule_declaration(final PsiBuilder psiBuilder, final int n) {
        if (!GeneratedParserUtilBase.recursion_guard_(psiBuilder, n, "submodule_declaration")) {
            return false;
        }
        final PsiBuilder.Marker enter_section_ = GeneratedParserUtilBase.enter_section_(psiBuilder);
        boolean b = inferred_submodule_declaration(psiBuilder, n + 1);
        if (!b) {
            b = module_declaration(psiBuilder, n + 1);
        }
        GeneratedParserUtilBase.exit_section_(psiBuilder, enter_section_, null, b);
        return b;
    }
    
    public static boolean umbrella_dir_declaration(final PsiBuilder psiBuilder, final int n) {
        if (!GeneratedParserUtilBase.recursion_guard_(psiBuilder, n, "umbrella_dir_declaration")) {
            return false;
        }
        if (!GeneratedParserUtilBase.nextTokenIs(psiBuilder, ModuleMapParserTypes.UMBRELLA)) {
            return false;
        }
        final PsiBuilder.Marker enter_section_ = GeneratedParserUtilBase.enter_section_(psiBuilder);
        final boolean consumeTokens = GeneratedParserUtilBase.consumeTokens(psiBuilder, 0, ModuleMapParserTypes.UMBRELLA, ModuleMapParserTypes.STRING);
        GeneratedParserUtilBase.exit_section_(psiBuilder, enter_section_, ModuleMapParserTypes.UMBRELLA_DIR_DECLARATION, consumeTokens);
        return consumeTokens;
    }
    
    public static boolean use_declaration(final PsiBuilder psiBuilder, final int n) {
        if (!GeneratedParserUtilBase.recursion_guard_(psiBuilder, n, "use_declaration")) {
            return false;
        }
        if (!GeneratedParserUtilBase.nextTokenIs(psiBuilder, ModuleMapParserTypes.USE)) {
            return false;
        }
        final PsiBuilder.Marker enter_section_ = GeneratedParserUtilBase.enter_section_(psiBuilder, n, 0, ModuleMapParserTypes.USE_DECLARATION, null);
        final boolean consumeToken;
        final boolean b = (consumeToken = GeneratedParserUtilBase.consumeToken(psiBuilder, ModuleMapParserTypes.USE)) && module_id(psiBuilder, n + 1);
        GeneratedParserUtilBase.exit_section_(psiBuilder, n, enter_section_, b, consumeToken, null);
        return b || consumeToken;
    }
    
    public static boolean wildcard_module_id(final PsiBuilder psiBuilder, final int n) {
        if (!GeneratedParserUtilBase.recursion_guard_(psiBuilder, n, "wildcard_module_id")) {
            return false;
        }
        if (!GeneratedParserUtilBase.nextTokenIs(psiBuilder, "<wildcard module id>", ModuleMapParserTypes.IDENTIFIER, ModuleMapParserTypes.WILDCARD)) {
            return false;
        }
        final PsiBuilder.Marker enter_section_ = GeneratedParserUtilBase.enter_section_(psiBuilder, n, 0, ModuleMapParserTypes.WILDCARD_MODULE_ID, "<wildcard module id>");
        boolean b = w(psiBuilder, n + 1);
        if (!b) {
            b = GeneratedParserUtilBase.consumeToken(psiBuilder, ModuleMapParserTypes.IDENTIFIER);
        }
        if (!b) {
            b = GeneratedParserUtilBase.consumeToken(psiBuilder, ModuleMapParserTypes.WILDCARD);
        }
        GeneratedParserUtilBase.exit_section_(psiBuilder, n, enter_section_, b, false, null);
        return b;
    }
    
    private static boolean w(final PsiBuilder psiBuilder, final int n) {
        if (!GeneratedParserUtilBase.recursion_guard_(psiBuilder, n, "wildcard_module_id_0")) {
            return false;
        }
        final PsiBuilder.Marker enter_section_ = GeneratedParserUtilBase.enter_section_(psiBuilder);
        final boolean b = GeneratedParserUtilBase.consumeTokens(psiBuilder, 0, ModuleMapParserTypes.IDENTIFIER, ModuleMapParserTypes.DOT) && wildcard_module_id(psiBuilder, n + 1);
        GeneratedParserUtilBase.exit_section_(psiBuilder, enter_section_, null, b);
        return b;
    }
    
    static {
        module_members_recoverer_parser_ = new GeneratedParserUtilBase.Parser() {
            @Override
            public boolean parse(final PsiBuilder psiBuilder, final int n) {
                return ModuleMapParser.module_members_recoverer(psiBuilder, n + 1);
            }
        };
    }
}
